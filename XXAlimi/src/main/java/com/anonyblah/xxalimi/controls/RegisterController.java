package com.anonyblah.xxalimi.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.anonyblah.xxalimi.service.RegisterService;
import com.anonyblah.xxalimi.vo.Users;

@Controller
@RequestMapping("/register")
public class RegisterController{
	
	@Autowired
	Users user;
	
	@Autowired
	RegisterService registerService;
	
	// 회원가입 버튼 눌렀을 때
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String register() {

		return "/register/register";
	}
	@RequestMapping(value = "/success", method = RequestMethod.POST)
	public String registerSuccess(@RequestParam("username") String username, @RequestParam("email") String email, 
			@RequestParam("password") String password, @RequestParam String role) throws Exception{
		
		registerService.registerUser(username, email, password, role);
		
		return "redirect:/login";
	}
}
