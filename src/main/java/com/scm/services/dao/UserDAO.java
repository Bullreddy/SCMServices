package com.scm.services.dao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.bulls.scm.common.vo.UserVO;

public interface UserDAO {

	public UserDetails loadUserByUsername(String username);
	
	
}
