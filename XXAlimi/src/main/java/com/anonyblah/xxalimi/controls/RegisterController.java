package com.anonyblah.xxalimi.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.anonyblah.xxalimi.service.RegisterService;

@Controller
@RequestMapping("/register")
public class RegisterController{
	
	@Autowired
	RegisterService registerService;
	
	// 회원가입 버튼 눌렀을 때
	@RequestMapping(method = RequestMethod.GET)
	public String register() {
		System.out.println("/register");
		return "/register/register";
	}
	@RequestMapping(method = RequestMethod.POST)
	public String registerSuccess(@RequestParam("username") String username, @RequestParam("email") String email, 
			@RequestParam("password") String password, Model model) throws Exception{
		
		registerService.registerUser(username, email, password);
		
		return "redirect:/login";
	}
}
