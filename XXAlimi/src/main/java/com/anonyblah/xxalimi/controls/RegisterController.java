package com.anonyblah.xxalimi.controls;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.anonyblah.xxalimi.dao.UserDao;
import com.anonyblah.xxalimi.dao.UserRoleDao;
import com.anonyblah.xxalimi.vo.User;
import com.anonyblah.xxalimi.vo.UserRole;

@Controller
@RequestMapping("/register")
public class RegisterController{
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private User user;
	
	@Autowired
	private UserRole userRole;
	
	// 회원가입 버튼 눌렀을 때
	@RequestMapping(/*value = "/input",*/ method = RequestMethod.GET)
	public String register() {
		System.out.println("/register");
		return "/register/register";
	}
	@RequestMapping(/*value = "/success", */method = RequestMethod.POST)
	public String registerSuccess(HttpServletRequest request, Model model) throws Exception{
		System.out.println("/register/success");

		user.setName((String)request.getParameter("username"));
		user.setEmail((String)request.getParameter("email"));
		user.setPassword(passwordEncoder.encode((String)request.getParameter("password")));
		
		userRole.setEmail((String)request.getParameter("email"));
		System.out.println("user:" + user.getEmail());
		userDao.insert(user);
		userRoleDao.insert(userRole);
		return "redirect:/login";
	}
}
