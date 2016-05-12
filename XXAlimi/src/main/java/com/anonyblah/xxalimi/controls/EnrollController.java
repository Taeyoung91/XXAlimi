package com.anonyblah.xxalimi.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.anonyblah.xxalimi.service.EnrollService;


@Controller
@RequestMapping("/add")
public class EnrollController {
		
	@Autowired
	private EnrollService enrollService;
	
	
	
	//등록을 위한 첫 페이지(검색)
	@RequestMapping("/searchPage")
	public String addControl(Model model){
		
		//검색기능구현예정		
		return "add/searchPage";
	}

	//검색 결과를 받아 DB에 입력할것임
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String insertPage(@RequestParam String feedUrl) throws Exception{
		
		enrollService.enrollFeed(feedUrl);
		
		return "redirect:/home";
	}
	
//  http://newssearch.naver.com/search.naver?where=rss&query=iPhone&rcdate=1&rcdate_ds=1997-1-1&rcdate_de=2007-01-25&srchm=qd&cat=all&pd=1
//	http://newssearch.naver.com/search.naver?where=rss&query=%C3%E0%B1%B8&field=0
//	http://newssearch.naver.com/search.naver?where=rss&query=%BE%DF%B1%B8&field=0
//	http://blog.rss.naver.com/naverdev.xml
//	http://feeds.gawker.com/lifehacker/vip
//	http://www.hansung.ac.kr/web/www/cmty_01_01?p_p_id=EXT_BBS&p_p_lifecycle=0&p_p_state=exclusive&p_p_mode=view&p_p_col_id=column-1&p_p_col_pos=1&p_p_col_count=3&_EXT_BBS_struts_action=%2Fext%2Fbbs%2Frss
	
	
}
