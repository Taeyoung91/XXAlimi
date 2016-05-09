package com.anonyblah.xxalimi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anonyblah.xxalimi.dao.ArticlesDao;
import com.anonyblah.xxalimi.vo.Articles;

@Service
public class ArticleService {

	@Autowired
	LoginService loginService;
	
	@Autowired
	ArticlesDao articleDao;
	
	public List<Articles> outputArticles() throws Exception{
		return articleDao.findAllArticlesByEmail(loginService.getID());
	}
	
	public List<Articles> outputArticlesByTitle(String feedTitle) throws Exception{
		return articleDao.findArticleByTitle(loginService.getID()+feedTitle);
	}
	
}
