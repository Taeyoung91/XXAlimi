package com.anonyblah.xxalimi.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class Articles {
	
	protected int id;
	
	protected String usersfeedTitle;
	
	protected String articleLink;
	
	protected String email;
	
	protected String feedTitle;
	
	protected String feedLink;
	
	protected String content;
	
	protected String articleAuthority;
	
	protected String articleTitle;
	
	protected String publishedDate;
	
	protected String keyword;
	
	protected Date createdDate;
	
	
}
