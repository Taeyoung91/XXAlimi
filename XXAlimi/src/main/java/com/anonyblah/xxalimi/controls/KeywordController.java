package com.anonyblah.xxalimi.controls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.anonyblah.xxalimi.service.FeedService;
import com.anonyblah.xxalimi.service.KeywordService;
import com.anonyblah.xxalimi.vo.Feeds;
import com.anonyblah.xxalimi.vo.Keywords;

@Controller
@RequestMapping("/keyword")
public class KeywordController {

	
	@Autowired
	FeedService feedService;
	
	@Autowired
	KeywordService keywordService;
	
	
	
	@RequestMapping("/addKeywordView")
	public String keywordControl(Model model) throws Exception {


		List<Feeds> feedList = feedService.outputFeed();
		List<Keywords> keywordList = keywordService.OutputKeyword();
		
		model.addAttribute("feedList", feedList);
		model.addAttribute("keywordList", keywordList);
		
		return "keyword/addKeywordView";
	}

	/**
	 * 
	 * 
	 * DAO 클래스 생기면 수정
	 * @throws Exception 
	 */
	@RequestMapping(value = "/addKeyword/{title}", method = RequestMethod.POST)
	public String addKeyword(@RequestParam String feedLink, @RequestParam String keyword, @RequestParam String feedtitle, @PathVariable String title) throws Exception {

		keywordService.insertKeyword(keyword, feedtitle, feedLink);

		return "redirect:/home";
	}
}
