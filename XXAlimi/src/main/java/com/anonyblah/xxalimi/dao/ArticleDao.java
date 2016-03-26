package com.anonyblah.xxalimi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anonyblah.xxalimi.vo.Article;

/**
 * Article Data Access Object, JPA Repository(Data의 유형, Data의 Primary Key의 유형)을 상속받음
 * @see Article
 */
public interface ArticleDao extends JpaRepository<Article, Integer> {

}
