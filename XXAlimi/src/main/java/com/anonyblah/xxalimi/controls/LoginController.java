package com.anonyblah.xxalimi.controls;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	
	@RequestMapping("/")
	public String login(){
		
		
		return "login";
	}
	
	
	@RequestMapping("/success")
	public String loginSuccess(HttpServletRequest request, Model model){
		
		System.out.println(request.getAttribute("name"));
		return "redirect:/home";
	}
	
}
