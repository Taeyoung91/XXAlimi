package com.anonyblah.xxalimi.controls;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anonyblah.xxalimi.dao.FeedList;
import com.anonyblah.xxalimi.dao.KeywordList;
import com.anonyblah.xxalimi.vo.Keyword;
import com.sun.syndication.feed.synd.SyndFeed;

@Controller
@RequestMapping("/keyword")
public class KeywordController {

	@RequestMapping("/addKeywordView")
	public String keywordControl(Model model) {

		List<SyndFeed> feedList = FeedList.feedList;
		List<Keyword> keywordList = KeywordList.keywordList;
		
		model.addAttribute("feedList", feedList);
		model.addAttribute("keywordList", keywordList);
		
		return "keyword/addKeywordView";
	}

	/**
	 * 
	 * 
	 * DAO 클래스 생기면 수정
	 */
	@RequestMapping(value = "/addKeyword/{title}", method = RequestMethod.POST)
	public String addKeyword(HttpServletRequest request, @PathVariable String title) {

		String keyword = request.getParameter("keyword");

		KeywordList.keywordList.add(new Keyword(title, keyword));

		return "redirect:/home";
	}
}
