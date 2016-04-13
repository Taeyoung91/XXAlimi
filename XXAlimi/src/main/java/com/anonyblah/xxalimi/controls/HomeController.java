package com.anonyblah.xxalimi.controls;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anonyblah.xxalimi.dao.FeedList;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;

/**
 * Feed 관리 Controller
 * 
 * @see FeedDao
 * @see ArticleDao
 */
@Controller
public class HomeController {

	// @Autowired // Spring에서 자동으로 Set
	// private FeedDao feedDao;

	/**
	 * @deprecated
	 */
	// @Autowired
	// private ArticleDao articleDao;

	/**
	 * 대문용
	 * @return 표시할 View의 이름
	 */
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	/**
	 * Feed목록을 불러와 JSPContext에 담아와 home View에서 FeedList를 사용
	 * 
	 * @param model
	 *            JSPContext
	 * @return 표시할 View의 이름
	 */
	@RequestMapping("/home") // "/home"으로 요청이 들어왔을때 이 Method 호출
	public String home(Model model) {
		List<SyndFeed> feedList = FeedList.feedList;
		model.addAttribute("feedList", feedList);

		return "home";
	}

	/**
	 * Feed를 누르면 그 피드의 Article들을 보여주기 위한 View Method
	 * 
	 * @param model
	 *            JSPContext
	 * @param title
	 *            Feed의 이름
	 * @return 표시할 View의 이름
	 */
	@RequestMapping("/home/feed/{title}")
	// {title}은 @PathVariable라는 Annotation이 붙은 매개변수 title을 사용
	public String viewFeed(Model model, @PathVariable String title) {
		List<SyndFeed> list = FeedList.feedList;
		SyndFeed feed = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTitle().equals(title)) {
				feed = list.get(i);
			}
		}

		model.addAttribute("feed", feed);

		return "feedView";
	}

	@RequestMapping("/home/article/{title}")
	// {title}은 @PathVariable라는 Annotation이 붙은 매개변수 title을 사용
	public String viewArticle(Model model, @PathVariable String title) {
		List<SyndFeed> list = FeedList.feedList;
		SyndFeed feed = null;
		SyndEntry article = null;
		for (int i = 0; i < list.size(); i++) {
			feed = list.get(i);
			@SuppressWarnings("unchecked")
			List<SyndEntry> articleList = feed.getEntries();
			for (int j = 0; j < articleList.size(); j++) {
				if (articleList.get(j).getTitle().equals(title)) {
					article = articleList.get(j);
				}
			}

		}

		model.addAttribute("article", article);

		return "articleView";
	}

	/**
	 * FeedFresh라는 버튼을 누르면 정해진 URI에서 Feed를 읽어 저장하고 다시 Home으로 Redirect
	 * 
	 * @return 표시할 View의 이름
	 */
	@RequestMapping("/home/refreshFeed")
	public String refreshFeed() {
		
		return "redirect:/home";
	}
	

}
