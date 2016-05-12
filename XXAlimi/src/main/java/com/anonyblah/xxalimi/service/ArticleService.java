package com.anonyblah.xxalimi.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anonyblah.xxalimi.dao.ArticlesDao;
import com.anonyblah.xxalimi.rss.RSSFeedParser;
import com.anonyblah.xxalimi.vo.Articles;
import com.sun.syndication.feed.synd.SyndFeed;

@Service
public class ArticleService {

	
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	Articles article;
	
	@Autowired
	ArticlesDao articleDao;
	
	public List<Articles> outputArticles() throws Exception{
		return articleDao.findAllArticlesByEmail(loginService.getID());
	}
	
	public List<Articles> outputArticlesByTitle(String feedTitle) throws Exception{
		return articleDao.findArticleByTitle(loginService.getID()+feedTitle);
	}
	
	public List<Articles> outputArticlesByLink(String feedLink) throws Exception{
		return articleDao.findArticlesByEmailAndFeedUrl(feedLink, loginService.getID());
	}
	
	public void insertArticleList(RSSFeedParser parser, SyndFeed syndFeed, String feedLink) throws Exception{
		for(int i = 0; i < parser.getArticleEntries().size(); i++){
			article.setUsersfeedTitle(loginService.getID()+syndFeed.getTitle());
			article.setArticleLink(parser.getArticleEntries().get(i).getLink());
			article.setArticleTitle(parser.getArticleEntries().get(i).getTitle());
			article.setEmail(loginService.getID());
			article.setFeedTitle(syndFeed.getTitle());
			article.setFeedLink(feedLink);
			article.setContent(parser.getArticleEntries().get(i).getDescription().getValue());
			article.setArticleAuthority(parser.getArticleEntries().get(i).getAuthor());
			if(syndFeed.getPublishedDate() != null){
				article.setPublishedDate(dateFormat.format(parser.getArticleEntries().get(i).getPublishedDate()));
			}
			articleDao.insert(article);
		}
	}
		
}
