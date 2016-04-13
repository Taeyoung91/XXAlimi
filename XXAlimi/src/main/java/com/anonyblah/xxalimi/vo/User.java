package com.anonyblah.xxalimi.vo;

import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
	
	@Id
	@Size(min = 5, max=100)
	String email;
	
	int index;
	
	@Size(min = 1, max=100)
	String name;
	
	@Size(min = 8, max=1000)
	String password;
	
	
	String latestTime;
	
}
