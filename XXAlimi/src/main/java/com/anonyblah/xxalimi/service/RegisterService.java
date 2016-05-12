package com.anonyblah.xxalimi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.anonyblah.xxalimi.dao.UserDao;
import com.anonyblah.xxalimi.vo.Users;


@Service
public class RegisterService {

	@Autowired
	LoginService loginService;
	
	@Autowired
	private UserDao userDao;
	
	
	@Autowired
	private Users user;
	
	
	public void registerUser(String username, String email, String password, String role) throws Exception{
		user.setName(username);
		user.setEmail(email);
		user.setPassword(loginService.passwordEncoding().encode(password));
		user.setRole(role);
		
		userDao.insert(user);

		UserDetailsImpl userDetails = new UserDetailsImpl(user);
		Authentication authentication =
				new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}
		
}
