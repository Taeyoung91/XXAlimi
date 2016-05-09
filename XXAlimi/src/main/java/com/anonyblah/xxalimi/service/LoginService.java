package com.anonyblah.xxalimi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.anonyblah.xxalimi.vo.User;

@Service
public class LoginService {
	
	@Autowired
	User user;
	
	public void saveID(String email){
		user.setEmail(email);
	}
	
	public String getID(){
		return user.getEmail();
	}
	
	public PasswordEncoder passwordEncoding(){
		
		return new BCryptPasswordEncoder();
	}
}
