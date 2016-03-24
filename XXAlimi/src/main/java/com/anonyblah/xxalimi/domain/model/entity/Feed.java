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
	
	@OneToMany(mappedBy = "feed", cascade = CascadeType.ALL)
	//@ElementCollection(fetch = FetchType.EAGER)
	//Entity�� List�⺭ ������ ������ ���ܰ� ���. ���� �װ��� �����ִ� Annotation
	List<Article> entries = new ArrayList<Article>();
}
