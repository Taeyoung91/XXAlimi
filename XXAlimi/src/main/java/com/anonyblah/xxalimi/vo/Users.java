package com.anonyblah.xxalimi.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class Users {
	
//	@Id
	protected int no;
	
//	@Size(min = 5, max=100)
	protected String email;
	
//	@Size(min = 1, max=100)
	protected String name;
	
//	@Size(min = 8, max=1000)
	protected String password;
	
	protected String role;
	
	protected Date createdDate;
}
