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
 *  �Խñ� Entity
 * 	<pre>
 *  id: �Խñ��� ID���� PrimaryŰ
 *  feed: �� �Խñ��� ������ �ִ� Feed
 *  title: �Խñ��� ����
 *  description: �Խñ��� �ٰŸ�(��๮)
 *  link: �Խñ� URL
 *  author: �ۼ����� Email �ּ�
 *  guid: �Խñ��� �����ϰ� �������ִ� ������
 *  content: (�԰ݿ� �����ϴ� �� ����, deprecated)
 * </pre>
 *  ���� RSS 2.0 �԰� http://naearu.tistory.com/2982748
 */
@Setter
@Getter
@Entity
public class Article {
	@Id	// ��� ���� id�� primary key���� ���
	@GeneratedValue	// DB�� ���� �ڵ������� ���� �ο���
	int id;
	
	// N:1 ������ ��Ÿ���� Annotation
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
