package com.scm.services.config;


import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UrlPathHelper;

import com.scm.services.common.Constants;
import com.scm.services.common.JwtTokenUtil;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	
	   
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(Constants.HEADER_STRING);
        HttpServletResponse response = (HttpServletResponse) res;
    	HttpServletRequest request = (HttpServletRequest) req;
logger.warn(request.getRequestURI());
   String resourcePath=new UrlPathHelper().getPathWithinApplication(request);
   logger.warn(resourcePath);
    	response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
    	response.setHeader("Access-Control-Allow-Methods", "POST,PUT, GET, OPTIONS, DELETE");

    	response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
    	response.setHeader("Access-Control-Allow-Headers",
    			"x-requested-with, authorization, Content-Type, responseType, Authorization, credential, X-XSRF-TOKEN");

        logger.warn("filter cxaling--?");
        String username = null;
        String authToken = null;
        if (header != null && header.startsWith(Constants.TOKEN_PREFIX)) {
            authToken = header.replace(Constants.TOKEN_PREFIX,"");
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("the token is expired and not valid anymore", e);
            } catch(SignatureException e){
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        logger.info("chain"+req);
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
    		response.setStatus(HttpServletResponse.SC_OK);
    	} else {
    		chain.doFilter(req, res);
    	}
       
    }
}