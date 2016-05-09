package com.anonyblah.xxalimi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anonyblah.xxalimi.dao.FeedsDao;
import com.anonyblah.xxalimi.vo.Feeds;

@Service
public class FeedService {

	@Autowired
	LoginService loginService;
	
	@Autowired // Spring에서 자동으로 Set
	private FeedsDao feedDao;

	
	
	public List<Feeds> outputFeed() throws Exception{
		return feedDao.findAllFeedsByEmail(loginService.getID());
	}
	
	
	public void deleteFeed(String feedUrl) throws Exception{
		feedDao.delete(feedUrl, loginService.getID());
	}
	
	
}
