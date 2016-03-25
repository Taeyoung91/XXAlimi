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
 *  Feed Entity, RSS 2.0 ����
 * 	<pre>
 *  title: Feed�� �̸�
 *  description: Feed�� ����
 *  link: Feed�� WebSite URL
 *  language: Feed�� �ۼ��� ���
 *  copyright: Feed���� Content�� ���� ���۱� ����
 *  pubDate: Feed���� �� Content�� �Խõ� ��¥
 *  entries: Feed�� ������ �ִ� �Խñ��� ǥ���� List
 * </pre>
 *  ���� RSS 2.0 �԰� http://naearu.tistory.com/2982748
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
	@Size(max=10000)
	String description;
	String language;
	@Size(max=10000)
	String copyright;
	String pubDate;
	
	// 1:N ������ ��Ÿ���� Annotation
	@OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
	// Feed�� ������ �ִ� �Խñ۵�
	List<Article> entries = new ArrayList<Article>();
}
