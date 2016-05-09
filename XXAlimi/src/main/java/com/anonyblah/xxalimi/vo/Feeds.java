package com.anonyblah.xxalimi.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 *  Feed Entity, RSS 2.0 규격
 * 	<pre>
 *  title: Feed의 이름, Primary Key
 *  description: Feed의 설명
 *  link: Feed의 WebSite URL
 *  language: Feed가 작성된 언어
 *  copyright: Feed안의 Content에 대한 저작권 내용
 *  pubDate: Feed내의 각 Content가 게시된 날짜
 *  entries: Feed가 가지고 있는 게시글 List
 * </pre>
 *  참고 RSS 2.0 표준 규격 http://naearu.tistory.com/2982748
 */
@Getter	
@Setter
@Component
public class Feeds {

	protected int id;
	
	protected String email;

	protected String link;
	
	protected String usersfeedTitle;
	
	protected String title;
	
	protected String author;
	
	protected String copyright;
	
	protected String imageUrl;
	
	protected String language;
	
	protected String publishedDate;
	
	protected Date createdDate;
	

}
