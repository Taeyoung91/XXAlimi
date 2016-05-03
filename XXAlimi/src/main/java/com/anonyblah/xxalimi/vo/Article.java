package com.anonyblah.xxalimi.vo;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class Article {
	
	protected int id;
	
	protected String articleLink;
	
	protected String feedLink;
	
	protected String content;
	
	protected String articleAuthority;
	
	protected String articleTitle;
	
}
