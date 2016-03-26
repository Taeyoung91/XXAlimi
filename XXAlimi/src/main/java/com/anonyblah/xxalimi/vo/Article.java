package com.anonyblah.xxalimi.vo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

/**
 *  게시글 Entity
 * 	<pre>
 *  id: 게시글의 ID, Primary Key
 *  feed: 이 게시글을 가지고 있는 Feed
 *  title: 게시글의 제목
 *  description: 게시글의 줄거리
 *  link: 게시글 URL
 *  author: 게시자의 Email
 *  guid: 게시글을 유일하게 구분해주는 구분자
 *  content: (RSS 표준 규격에 없는 속성, deprecated)
 * </pre>
 *  참고 RSS 2.0 표준 규격 http://naearu.tistory.com/2982748
 */
@Setter
@Getter
@Entity
public class Article {
	@Id	// 이 Field가 Primary Key임을 알림
	@GeneratedValue	// DB에서 자동으로 생성해주는 값임을 알림
	int id;
	
	// N:1 관계 Annotation
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "feed_name")
	private Feed feed;
	
	@Size(max=10000)
	String title;
	@Size(max=100000000)
	String description;
	@Size(max=10000)
	String link;
	String author;
	@Size(max=10000)
	String guid;
	
	/**
	 * @deprecated
	 */
	@Size(max=10000)
	String content;
}
