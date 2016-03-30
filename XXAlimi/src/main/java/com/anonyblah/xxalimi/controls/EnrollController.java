package com.anonyblah.xxalimi.controls;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anonyblah.xxalimi.dao.ArticleDao;
import com.anonyblah.xxalimi.dao.FeedDao;
import com.anonyblah.xxalimi.rss.RSSFeedParser;
import com.anonyblah.xxalimi.vo.Feed;

@Controller
@RequestMapping("/add")
public class EnrollController {
	LinkedList <String> enrollUrl = new LinkedList<String>();
	@Autowired	// Spring에서 자동으로 Set
	private FeedDao feedDao;
	
	@Autowired
	private ArticleDao articleDao;
	
	//등록을 위한 첫 페이지(검색)
	@RequestMapping("/searchPage")
	public String addHome(Model model){

	return "add/searchPage";
	}

	//검색 결과를 받아 DB에 입력할것임
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String insertPage(HttpServletRequest request, Model model){

		String feedUrl = (String)request.getParameter("feedUrl");

		RSSFeedParser rssFeedParser = new RSSFeedParser(feedUrl, articleDao);
		Feed feed = rssFeedParser.readFeed();
		feedDao.save(feed);
		return "redirect:/home";
	}
//	http://blog.rss.naver.com/naverdev.xml
//	http://feeds.gawker.com/lifehacker/vip
//	http://www.hansung.ac.kr/web/www/cmty_01_01?p_p_id=EXT_BBS&p_p_lifecycle=0&p_p_state=exclusive&p_p_mode=view&p_p_col_id=column-1&p_p_col_pos=1&p_p_col_count=3&_EXT_BBS_struts_action=%2Fext%2Fbbs%2Frss
	
	
}
