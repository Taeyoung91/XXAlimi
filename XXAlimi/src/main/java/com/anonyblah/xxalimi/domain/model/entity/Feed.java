package com.anonyblah.xxalimi.domain.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames=true)
@Entity
public class Feed {
	@Id
	String title;
	
	@Size(max=10000)
	String link;
	@Size(max=100000000)
	String description;
	String language;
	@Size(max=10000)
	String copyright;
	String pubDate;
	
	// 1:N 관계를 나타내는 Annotation
	@OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
	List<Article> entries = new ArrayList<Article>();
}
