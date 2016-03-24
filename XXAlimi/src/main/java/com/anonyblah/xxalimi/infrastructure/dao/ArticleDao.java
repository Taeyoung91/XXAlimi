package com.anonyblah.xxalimi.infrastructure.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anonyblah.xxalimi.domain.model.entity.Article;

public interface ArticleDao extends JpaRepository<Article, Integer> {

}
