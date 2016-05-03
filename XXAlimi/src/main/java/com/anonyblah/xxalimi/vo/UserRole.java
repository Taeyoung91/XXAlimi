package com.anonyblah.xxalimi.vo;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class UserRole {
	int no;
	String email;
	String role;
}
