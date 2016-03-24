package com.anonyblah.xxalimi.domain.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class Feed {
	@Id
	final String title;
	
	final String link;
	final String description;
	final String language;
	final String copyright;
	final String pubDate;
	
	final List<Article> entries = new ArrayList<Article>();
}
