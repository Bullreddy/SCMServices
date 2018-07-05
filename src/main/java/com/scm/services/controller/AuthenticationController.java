package com.scm.services.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UserDetails;
import com.bulls.scm.common.vo.UserVO;
import com.scm.services.common.JwtTokenUtil;
import com.scm.services.config.AuthToken;
import com.scm.services.serviceinf.UserService;

@RestController
@RequestMapping("/token")
public class AuthenticationController {
	private final static Logger LOGGER = Logger.getLogger(AuthenticationController.class);
	
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody UserVO loginUser) throws AuthenticationException {
    	LOGGER.debug("Authentication controll calaing  begins--->");
    	
    	LOGGER.debug("loginUser.getUserName()-->"+loginUser.getUsername());
    	LOGGER.debug("loginUser.getUserName()-->"+loginUser.getPassword());
    	
      
        try {
        	  final Authentication authentication = authenticationManager.authenticate(
                      new UsernamePasswordAuthenticationToken(
                              loginUser.getUsername(),
                              loginUser.getPassword()
                      )
              );
        	  SecurityContextHolder.getContext().setAuthentication(authentication);
              final UserDetails user = userService.loadUserByUsername(loginUser.getUsername());
              final String token = jwtTokenUtil.generateToken(user);
              return ResponseEntity.ok(new AuthToken(token));
        	  } catch (DisabledException e) {
        		  return ResponseEntity.ok(null);
        } catch (BadCredentialsException e) {

        	 return ResponseEntity.ok(null);
        }
       
    }

}

