package com.anonyblah.xxalimi.controls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anonyblah.xxalimi.dao.ArticleDao;
import com.anonyblah.xxalimi.dao.FeedDao;
import com.anonyblah.xxalimi.rss.RSSFeedParser;
import com.anonyblah.xxalimi.vo.Article;
import com.anonyblah.xxalimi.vo.Feed;

/**
 * Feed 관리 Controller
 * @see FeedDao
 * @see ArticleDao
 */
@Controller
public class HomeController {
	
	@Autowired	// Spring에서 자동으로 Set
	private FeedDao feedDao;
	
	/**
	 * @deprecated
	 */
	@Autowired
	private ArticleDao articleDao;
	
	/**
	 * Feed목록을 불러와 JSPContext에 담아와 home View에서 FeedList를 사용
	 * @param model JSPContext
	 * @return 표시할 View의 이름
	 */
	@RequestMapping("/home")		// "/home"으로 요청이 들어왔을때 이 Method 호출
	public String home(Model model) {
		List<Feed> feedList = feedDao.findAll();
		model.addAttribute("feedList", feedList);
		
		return "home";
	}
	 
	/**
	 * Feed를 누르면 그 피드의 Article들을 보여주기 위한 View Method
	 * @param	model JSPContext
	 * @param title Feed의 이름
	 * @return 표시할 View의 이름
	 */
	@RequestMapping("/home/feed/{title}") 
	//{title}은 @PathVariable라는 Annotation이 붙은 매개변수 title을 사용
	public String viewFeed(Model model, @PathVariable String title) {
		Feed feed = feedDao.findOne(title);
		model.addAttribute("feed", feed);
		
		return "feedView";
	}
	
	@RequestMapping("/home/article/{id}") 
	//{title}은 @PathVariable라는 Annotation이 붙은 매개변수 title을 사용
	public String viewArticle(Model model, @PathVariable Integer id) {
		
		Article article = articleDao.findOne(id); 
		model.addAttribute("article", article);
		
		return "articleView";
	}
	
	
	/**
	 * FeedFresh라는 버튼을 누르면 정해진 URI에서 Feed를 읽어 저장하고 다시 Home으로 Redirect
	 * @return 표시할 View의 이름
	 */
	@RequestMapping("/home/refreshFeed")
	public String refreshFeed() {
//		RSSFeedParser rssFeedParser = new RSSFeedParser("http://www.hansung.ac.kr/web/www/cmty_01_01?p_p_id=EXT_BBS&p_p_lifecycle=0&p_p_state=exclusive&p_p_mode=view&p_p_col_id=column-1&p_p_col_pos=1&p_p_col_count=3&_EXT_BBS_struts_action=%2Fext%2Fbbs%2Frss", articleDao);
//		Feed feed = rssFeedParser.readFeed();
//		
//		feedDao.save(feed);
		return "redirect:/home";
	}
}
