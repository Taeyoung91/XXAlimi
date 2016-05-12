package com.anonyblah.xxalimi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anonyblah.xxalimi.rss.RSSFeedParser;
import com.sun.syndication.feed.synd.SyndFeed;


@Service
public class EnrollService {
	
	@Autowired
	FeedService feedService;
	
	@Autowired
	ArticleService articleService;
	
	
	
	
	public void enrollFeed(String feedUrl) throws Exception{
		
		RSSFeedParser parser = new RSSFeedParser(feedUrl);
		
		parser.readFeed();								
		
		SyndFeed syndFeed = parser.getFeed();			// 파서된 feed 가져옴

		feedService.insertFeed(syndFeed, feedUrl);		// 피드 주소 DB에 저장

		articleService.insertArticleList(parser, syndFeed, feedUrl);	// 해당 피드의 기사리스트 DB에 저장


	}
	
}
