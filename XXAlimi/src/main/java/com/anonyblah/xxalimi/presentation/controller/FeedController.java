package com.anonyblah.xxalimi.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anonyblah.xxalimi.domain.model.entity.Feed;
import com.anonyblah.xxalimi.func.rss.RSSFeedParser;
import com.anonyblah.xxalimi.infrastructure.dao.ArticleDao;
import com.anonyblah.xxalimi.infrastructure.dao.FeedDao;

@Controller
public class FeedController {
	
	@Autowired
	private FeedDao feedDao;
	@Autowired
	private ArticleDao articleDao;
	
	@RequestMapping("/home")
	public String home(Model model) {
		List<Feed> feedList = feedDao.findAll();
		model.addAttribute("feedList", feedList);
		
		return "home";
	}
	
	@RequestMapping("/home/{title}")
	public String viewFeed(Model model, @PathVariable String title) {
		Feed feed = feedDao.findOne(title);
		model.addAttribute("feed", feed);
		
		return "feedView";
	}
	
	@RequestMapping("/home/refreshFeed")
	public String refreshFeed() {
		RSSFeedParser rssFeedParser = new RSSFeedParser("http://www.vogella.com/article.rss", articleDao);
		feedDao.save(rssFeedParser.readFeed());
		
		return "redirect:/home";
	}
}
