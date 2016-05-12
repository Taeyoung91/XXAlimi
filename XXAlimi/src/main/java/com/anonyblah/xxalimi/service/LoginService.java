package com.anonyblah.xxalimi.service;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.anonyblah.xxalimi.vo.Users;

@Service
@Aspect
public class LoginService {
	
	@Autowired
	Users user;
	
	@Before("execution(void com.anonyblah.xxalimi.*.*.scanningFeed())")
	public void saveID(/*String email*/){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("authAOP1 : " + auth);
		if(auth != null)
			user.setEmail(auth.getName());
		
	}
	
	
	public String getID(){

		/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println("authAOP2 : " + auth);
		if(auth != null)
			return auth.getName();
		else
			return null;*/				/* 얘로 현재 사용자 계정을 반환받아야 하는데 
										*	아직 사용자 계정이 추가되기 전(로그인전)부터 실행되므로 null을 반환한다.
										*	그렇다고 로그인을 한 후더라도 @Transactional은 비동기 식이기 때문에
										*	중간에 사용자 계정을 새로 받을 수 없어서 계속 null을 반환하게 된다.
										*/
		return user.getEmail();			// 얘로 현재 사용자 email을 리턴하면 서버 사용시 이전 사용자의 계정을 반환하게 됨
		
	}
	
	public PasswordEncoder passwordEncoding(){
		
		return new BCryptPasswordEncoder();
	}
}
