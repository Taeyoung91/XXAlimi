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

@Setter
@Getter
@Entity
public class Article {
	@Id
	@GeneratedValue
	int id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "feed_name")
	private Feed feed;
	
	@Size(max=10000)
	String title;
	@Size(max=10000)
	String description;
	@Size(max=10000)
	String link;
	String author;
	@Size(max=10000)
	String guid;
	
	@Size(max=10000)
	String content;
}
