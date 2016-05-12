package com.anonyblah.xxalimi.controls;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public void login() {	}

	@RequestMapping("/auth/fb")
	public String fbLogin() {
		return "/auth/fbLogin";
	}

	@RequestMapping("/logout")
	public void logout() {}

	// @RequestMapping("/fail")
	// public String LoginFail(){
	// return "/auth/LoginFail";
	// }

}
