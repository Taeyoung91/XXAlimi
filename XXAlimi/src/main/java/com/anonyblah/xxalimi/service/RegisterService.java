package com.anonyblah.xxalimi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anonyblah.xxalimi.dao.UserDao;
import com.anonyblah.xxalimi.dao.UserRoleDao;
import com.anonyblah.xxalimi.vo.User;
import com.anonyblah.xxalimi.vo.UserRole;


@Service
public class RegisterService {

	@Autowired
	LoginService loginService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private User user;
	
	@Autowired
	private UserRole userRole;
	
	public void registerUser(String username, String email, String password) throws Exception{
		user.setName(username);
		user.setEmail(email);
		user.setPassword(loginService.passwordEncoding().encode(password));
		
		userRole.setEmail(email);
		
		userDao.insert(user);
		userRoleDao.insert(userRole);
		
	}
	
}
