package com.scm.services.serviceinf;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.bulls.scm.common.vo.UserVO;

public interface UserService extends UserDetailsService{

	public UserDetails loadUserByUsername(String username);
	
	
}
