package com.anonyblah.xxalimi.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anonyblah.xxalimi.dao.FeedsDao;
import com.anonyblah.xxalimi.vo.Feeds;
import com.sun.syndication.feed.synd.SyndFeed;

@Service
public class FeedService {

	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	Feeds feeds;
	
	@Autowired // Spring에서 자동으로 Set
	private FeedsDao feedDao;

	public void searchLog() {
		
	}
	
	public List<Feeds> outputFeedByEmail(String email) throws Exception {
		return feedDao.findAllFeedsByEmail(email);
	}
	
	public List<Feeds> outputFeed() throws Exception{
		return feedDao.findAllFeeds();
		//return feedDao.findAllFeedsByEmail(loginService.getID());
	}
	
	public void insertFeed(SyndFeed syndFeed, String feedUrl) throws Exception{
	
		feeds.setTitle(syndFeed.getTitle());	// feed vo에 저장
		feeds.setEmail(loginService.getID()); 	// 현재 로그인딘 계정을 jsp에서 바로 가져옴
		feeds.setLink(feedUrl);					// 사용자가 저장한 링크 주소를 직접 vo에 저장
		feeds.setUsersfeedTitle(loginService.getID()+syndFeed.getTitle());
		feeds.setAuthor(syndFeed.getAuthor());
		feeds.setCopyright(syndFeed.getCopyright());
		if(syndFeed.getImage() != null)
			feeds.setImageUrl(syndFeed.getImage().getUrl());
		
		feeds.setLanguage(syndFeed.getLanguage());
		if(syndFeed.getPublishedDate() != null){
			feeds.setPublishedDate(dateFormat.format(syndFeed.getPublishedDate()));
			System.out.println("publishedDate: " + syndFeed.getPublishedDate());
		}

		feedDao.insert(feeds);
	}
	
	public void deleteFeed(String feedUrl) throws Exception{
		feedDao.delete(feedUrl, loginService.getID());
	}
	
	
}
