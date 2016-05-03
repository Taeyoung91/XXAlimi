package com.anonyblah.xxalimi.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anonyblah.xxalimi.vo.Feeds;


@Controller
public class LoginController {
	
	@Autowired
	Feeds feeds;
	
	@RequestMapping("/login")
	public void login() {
		System.out.println("login");
	}
	@RequestMapping("/auth/fb")
	public String fbLogin(){
		return "/auth/fbLogin";
	}
	
	@RequestMapping("/user/success")
	public String loginSuccess(/*HttpServletRequest request, Model model*/){
		
//		System.out.println(request.getAttribute("name"));
		return "redirect:/user/home";
	}
	
//	@RequestMapping("/fail")
//	public String LoginFail(){
//		return "/auth/LoginFail";
//	}
	
}
