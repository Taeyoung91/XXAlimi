package com.anonyblah.xxalimi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import com.anonyblah.xxalimi.vo.Users;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Setter
@Getter
public class UserDetailsImpl extends User{

	private String nickName;
	
	public UserDetailsImpl(Users user){
		super(user.getEmail(), user.getPassword(), authorities(user));
		this.nickName = user.getName();
	}
	
	private static Collection<? extends GrantedAuthority> authorities(Users user) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		System.out.println("ROLE" + user.getRole() + user.getEmail());
		
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		return authorities;
	}
}
