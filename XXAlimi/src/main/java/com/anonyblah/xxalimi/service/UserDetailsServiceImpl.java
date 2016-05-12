package com.anonyblah.xxalimi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.anonyblah.xxalimi.dao.UserDao;
import com.anonyblah.xxalimi.vo.Users;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String email){
		Users user = null;
		try {
			user = userDao.findByEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new UserDetailsImpl(user);
	}

}
