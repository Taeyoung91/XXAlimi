package com.anonyblah.xxalimi.domain.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Article {
	@Id
	@GeneratedValue
	int id;
	
	String title;
	String description;
	String link;
	String author;
	String guid;
	
	String content;
}
