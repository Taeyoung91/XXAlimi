package com.anonyblah.xxalimi.controls;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/login")
public class LoginController {

	
	@RequestMapping("/")
	public String login(){
		
		
		return "login";
	}
	
	//회원가입 버튼 눌렀을 때
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register() {
		return "register";
	}
	
	@RequestMapping("/success")
	public String loginSuccess(HttpServletRequest request, Model model){
		
		System.out.println(request.getAttribute("name"));
		return "redirect:/home";
	}
	
}
