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

/**
 * Feed 관리 Controller
 * @see FeedDao
 * @see ArticleDao
 */
@Controller
public class FeedController {
	
	@Autowired	// Spring이 자동으로 Set
	private FeedDao feedDao;
	
	/**
	 * @deprecated
	 */
	@Autowired
	private ArticleDao articleDao;
	
	/**
	 * Feed들을 모두 가져와 JSPContext에 넣어두고 home View를 불러온다.
	 * @param model JSPContext
	 * @return 표시할 View의 이름
	 */
	@RequestMapping("/home")		// "/home"으로 요청이 들어오면 이 Method 실행
	public String home(Model model) {
		List<Feed> feedList = feedDao.findAll();
		model.addAttribute("feedList", feedList);
		
		return "home";
	}
	
	/**
	 * Feed를 눌렀을 때 그 Feed에서 갱신해온 게시글을 보여주는 View를 호출
	 * @param	model JSPContext
	 * @param title Feed의 이름
	 * @return 표시할 View의 이름
	 */
	@RequestMapping("/home/{title}") 
	//{title}은 @PathVariable로 Annotation된 매개변수 title을 사용하기 위해 쓰임
	public String viewFeed(Model model, @PathVariable String title) {
		Feed feed = feedDao.findOne(title);
		model.addAttribute("feed", feed);
		
		return "feedView";
	}
	
	/**
	 * FeedFresh 버튼을 눌렀을 때 RSS 주소에서 긁어와 FeedDao에 저장
	 * @return 표시할 View의 이름
	 */
	@RequestMapping("/home/refreshFeed")
	public String refreshFeed() {
		RSSFeedParser rssFeedParser = new RSSFeedParser("http://www.hansung.ac.kr/web/www/cmty_01_01?p_p_id=EXT_BBS&p_p_lifecycle=0&p_p_state=exclusive&p_p_mode=view&p_p_col_id=column-1&p_p_col_pos=1&p_p_col_count=3&_EXT_BBS_struts_action=%2Fext%2Fbbs%2Frss", articleDao);
		Feed feed = rssFeedParser.readFeed();
		
		feedDao.save(feed);
		return "redirect:/home";
	}
}
