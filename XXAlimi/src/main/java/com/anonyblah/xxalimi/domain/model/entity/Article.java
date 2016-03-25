package com.anonyblah.xxalimi.domain.model.entity;

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
 *  id: 게시글의 ID이자 Primary키
 *  feed: 이 게시글을 가지고 있는 Feed
 *  title: 게시글의 제목
 *  description: 게시글의 줄거리(요약문)
 *  link: 게시글 URL
 *  author: 작성자의 Email 주소
 *  guid: 게시글을 유일하게 구분해주는 구분자
 *  content: (규격에 대응하는 값 없음, deprecated)
 * </pre>
 *  참고 RSS 2.0 규격 http://naearu.tistory.com/2982748
 */
@Setter
@Getter
@Entity
public class Article {
	@Id	// 멤버 변수 id를 primary key임을 명시
	@GeneratedValue	// DB에 의해 자동적으로 값이 부여됨
	int id;
	
	// N:1 연결을 나타내는 Annotation
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
