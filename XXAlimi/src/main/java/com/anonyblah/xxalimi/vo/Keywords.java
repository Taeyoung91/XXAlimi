package com.anonyblah.xxalimi.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter	
@Setter
@Component
public class Keywords {
	
	int keyword_id;
	String email;
	String title;
	String keyword;
	String feedLink;
	protected Date createdDate;

}
