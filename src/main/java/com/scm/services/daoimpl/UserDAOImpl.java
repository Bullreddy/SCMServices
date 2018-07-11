package com.scm.services.daoimpl;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.bulls.scm.common.vo.StudentVO;
import com.bulls.scm.common.vo.UserVO;
import com.scm.services.common.MapperUtils;
import com.scm.services.config.JwtAuthenticationEntryPoint;
import com.scm.services.dao.StudentDAO;
import com.scm.services.dao.UserDAO;
import com.scm.services.dao.entity.Admission;
import com.scm.services.dao.entity.User;
import com.scm.services.dto.JwtUser;
import com.scm.services.serviceinf.UserService;
@Repository
public class UserDAOImpl  extends BaseDAOImpl implements UserDAO{

	@Autowired
	private MapperUtils mapper;
	private final static Logger LOGGER = Logger.getLogger(UserDAOImpl.class);
	
	public UserDAOImpl() {
		
	}

	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		LOGGER.info("result from loaduser");
		LOGGER.info(username);
		Query query = getEM().createNamedQuery("User.findOne");
		query.setParameter("username", username);
		
		
		try {
			User user = (User) query.getSingleResult();
			LOGGER.info("result from db");
			LOGGER.info(user);
			JwtUser userDetail = mapper.map(user,JwtUser.class);
			 return userDetail;
		}catch(NoResultException err) {
			  throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        
		//return null;	
		}
		
		
		
		
	}
}