package com.anonyblah.xxalimi.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anonyblah.xxalimi.domain.model.entity.Article;
import com.anonyblah.xxalimi.domain.model.entity.Feed;
import com.anonyblah.xxalimi.infrastructure.dao.SiteDao;

@Controller
@RequestMapping("/")
public class FeedController {
	
	@Autowired
	private SiteDao siteDao;
	
	@RequestMapping("/home")
	public String home(Model model) {
		List<Feed> siteList = siteDao.findAll();
		model.addAttribute("siteList", siteList);
		
		return "home";
	}
	
}
