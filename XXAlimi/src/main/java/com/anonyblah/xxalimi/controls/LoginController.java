package com.anonyblah.xxalimi.controls;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.anonyblah.xxalimi.vo.User;


@Controller
public class LoginController {
	
	
	@RequestMapping("/login")
	public void login() {
		System.out.println("login");
	}
	@RequestMapping("/auth/fb")
	public String fbLogin(){
		return "/auth/fbLogin";
	}
	
	@RequestMapping("/user/success")
	public String loginSuccess(HttpServletRequest request /*Model model*/) throws Exception{
			
		return "/user/home";
	}
	
	@RequestMapping("/logout")
	public void logout() {
		System.out.println("logout");
	}
	
//	@RequestMapping("/fail")
//	public String LoginFail(){
//		return "/auth/LoginFail";
//	}
	
}
