package com.scm.services.serviceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.bulls.scm.common.vo.StudentVO;
import com.bulls.scm.common.vo.UserVO;
import com.scm.services.dao.StudentDAO;
import com.scm.services.dao.UserDAO;
import com.scm.services.serviceinf.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAO userDao;
	
	public UserServiceImpl() {
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		
		return userDao.loadUserByUsername(username);
	}
}
