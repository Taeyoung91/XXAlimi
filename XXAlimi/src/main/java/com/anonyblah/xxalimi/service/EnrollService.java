package com.anonyblah.xxalimi.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.anonyblah.xxalimi.dao.ArticlesDao;
import com.anonyblah.xxalimi.dao.FeedsDao;
import com.anonyblah.xxalimi.rss.RSSFeedParser;
import com.anonyblah.xxalimi.vo.Articles;
import com.anonyblah.xxalimi.vo.Feeds;
import com.sun.syndication.feed.synd.SyndFeed;


@Service
@Component
public class EnrollService {
	
	@Autowired
	private Articles article;
	
	@Autowired
	private ArticlesDao articleDao;
	
	@Autowired
	private Feeds feeds;
	
	@Autowired
	private FeedsDao feedsDao;
	
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	public void enrollFeed(String feedUrl, String email) throws Exception{
		RSSFeedParser parser = new RSSFeedParser(feedUrl);
		parser.readFeed();
		
		
		parser.readFeed();	// 파서된 feed 가져옴
		
		SyndFeed syndFeed = parser.getFeed();

		
		feeds.setTitle(syndFeed.getTitle());	// feed vo에 저장
		feeds.setEmail(email); // 현재 로그인딘 계정을 jsp에서 바로 가져옴
		feeds.setLink(feedUrl);					// 사용자가 저장한 링크 주소를 직접 vo에 저장
		feeds.setUsersfeedTitle(email+syndFeed.getTitle());
		feeds.setAuthor(syndFeed.getAuthor());
		feeds.setCopyright(syndFeed.getCopyright());
		if(syndFeed.getImage() != null)
			feeds.setImageUrl(syndFeed.getImage().getUrl());
		
		feeds.setLanguage(syndFeed.getLanguage());
		if(syndFeed.getPublishedDate() != null){
			feeds.setPublishedDate(dateFormat.format(syndFeed.getPublishedDate()));
			System.out.println("publishedDate: " + syndFeed.getPublishedDate());
		}

		feedsDao.insert(feeds);
		

		for(int i = 0; i < parser.getArticleEntries().size(); i++){
			article.setUsersfeedTitle(email+syndFeed.getTitle());
			article.setArticleLink(parser.getArticleEntries().get(i).getLink());
			article.setArticleTitle(parser.getArticleEntries().get(i).getTitle());
			article.setEmail(email);
			article.setFeedTitle(syndFeed.getTitle());
			article.setFeedLink(feedUrl);
			article.setContent(parser.getArticleEntries().get(i).getDescription().getValue());
			article.setArticleAuthority(parser.getArticleEntries().get(i).getAuthor());
			if(syndFeed.getPublishedDate() != null){
				article.setPublishedDate(dateFormat.format(parser.getArticleEntries().get(i).getPublishedDate()));
			}
			articleDao.insert(article);
		}
	}
	
}
