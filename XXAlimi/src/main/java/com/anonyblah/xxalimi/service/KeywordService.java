package com.anonyblah.xxalimi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.anonyblah.xxalimi.dao.KeywordDao;
import com.anonyblah.xxalimi.vo.Keywords;

@Service
public class KeywordService {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	KeywordDao keywordDao;
	
	@Autowired
	Keywords keywords;
	
	public void insertKeyword(String keyword, String feedtitle, String feedLink) throws Exception{
		keywords.setEmail(loginService.getID());
		keywords.setKeyword(keyword);
		keywords.setTitle(feedtitle);
		keywords.setFeedLink(feedLink);
		keywordDao.insert(keywords);
	}
	
	public List<Keywords> OutputKeyword() throws Exception{
		return keywordDao.findTitleAndKeywordByEmail(loginService.getID());
	}
	
	public void deleteKeyword(String feedUrl) throws Exception{
		keywordDao.delete(loginService.getID(), feedUrl);
	}
}
