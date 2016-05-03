package com.anonyblah.xxalimi.controls;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anonyblah.xxalimi.dao.ArticleDao;
import com.anonyblah.xxalimi.dao.FeedList;
import com.anonyblah.xxalimi.dao.FeedsDao;
import com.anonyblah.xxalimi.rss.RSSFeedParser;
import com.anonyblah.xxalimi.scheduler.ParserList;
import com.anonyblah.xxalimi.vo.Article;
import com.anonyblah.xxalimi.vo.Feeds;
import com.anonyblah.xxalimi.vo.User;
import com.sun.syndication.feed.synd.SyndFeed;


@Controller
@RequestMapping("/add")
public class EnrollController {
	
//	@Autowired
//	private ParserDao parserDao;
	
	@Autowired
	RSSFeedParser parser;
	
	@Autowired
	private Feeds feeds;
	
	@Autowired
	private FeedsDao feedsDao;
	
	@Autowired
	private Article article;
	
	@Autowired
	private ArticleDao articleDao;
	
	//등록을 위한 첫 페이지(검색)
	@RequestMapping("/searchPage")
	public String addcControl(Model model){
		
		//검색기능구현예정		
		return "add/searchPage";
	}

	//검색 결과를 받아 DB에 입력할것임
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String insertPage(HttpServletRequest request, Model model) throws Exception{
		

		String feedUrl = (String)request.getParameter("feedUrl");
//		RSSFeedParser parser = new RSSFeedParser(feedUrl);
		parser.setFeedUrl(feedUrl);
		
		
		
		
		System.out.println((String)request.getParameter("email"));
		
		SyndFeed syndFeed = parser.getFeed();	// 파서된 feed 가져옴
		
		feeds.setTitle(syndFeed.getTitle());	// feed vo에 저장
		feeds.setEmail((String)request.getParameter("email")); // 현재 로그인딘 계정을 jsp에서 바로 가져옴
		feeds.setLink(feedUrl);					// 사용자가 저장한 링크 주소를 직접 vo에 저장
		feeds.setAuthor(syndFeed.getAuthor());
		feeds.setCopyright(syndFeed.getCopyright());
		feeds.setImageUrl(syndFeed.getImage().getUrl());
		feeds.setLanguage(syndFeed.getLanguage());
		feeds.setPublishedDate(syndFeed.getPublishedDate().toString());
		
//		article.setArticleLink(syndFeed.getEntries().get(0).);
		
		feedsDao.insert(feeds);
		
		
		
//		System.out.println("Author" + feed.getAuthor());
//		System.out.println("Copyright" + feed.getCopyright());
//		System.out.println("Description" + feed.getDescription());
//		System.out.println("Encoding" + feed.getEncoding());
//		System.out.println("FeedType" + feed.getFeedType());
//		System.out.println("Language" + feed.getLanguage());
//		System.out.println("ImageUrl" + feed.getImage().getUrl());
//		System.out.println("Link" + feed.getLink());
//		System.out.println("Title" + feed.getTitle());
//		System.out.println("Uri" + feed.getUri());
//		System.out.println("DescriptionEx" + feed.getDescriptionEx());
//		System.out.println("PublishedDate" + feed.getPublishedDate());
//		System.out.println("SupportedFeedTypes0" + feed.getSupportedFeedTypes().get(0));
//		System.out.println("SupportedFeedTypes1" + feed.getSupportedFeedTypes().get(1));
		FeedList.feedList.add(syndFeed);
		//feedDao.save(feed);
		
		ParserList.parserList.add(parser);
		
		
		return "redirect:/user/home";
	}
	
//  http://newssearch.naver.com/search.naver?where=rss&query=iPhone&rcdate=1&rcdate_ds=1997-1-1&rcdate_de=2007-01-25&srchm=qd&cat=all&pd=1
//	http://newssearch.naver.com/search.naver?where=rss&query=%C3%E0%B1%B8&field=0
//	http://newssearch.naver.com/search.naver?where=rss&query=%BE%DF%B1%B8&field=0
//	http://blog.rss.naver.com/naverdev.xml
//	http://feeds.gawker.com/lifehacker/vip
//	http://www.hansung.ac.kr/web/www/cmty_01_01?p_p_id=EXT_BBS&p_p_lifecycle=0&p_p_state=exclusive&p_p_mode=view&p_p_col_id=column-1&p_p_col_pos=1&p_p_col_count=3&_EXT_BBS_struts_action=%2Fext%2Fbbs%2Frss
	
	
}
