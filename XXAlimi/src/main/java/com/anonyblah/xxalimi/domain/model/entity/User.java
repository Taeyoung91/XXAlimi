package com.anonyblah.xxalimi.domain.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {
	@Id
	String id;
	
	@NotNull
	@Size(min=8)
	@Column(nullable=false)
	String pw;
	
	List<Feed> rssSites;
}
