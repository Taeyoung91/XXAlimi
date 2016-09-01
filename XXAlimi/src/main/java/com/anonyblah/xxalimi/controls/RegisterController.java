package com.anonyblah.xxalimi.controls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.anonyblah.xxalimi.dao.UserDao;
import com.anonyblah.xxalimi.service.RegisterService;
import com.anonyblah.xxalimi.vo.Users;

@Controller
@RequestMapping("/register")
public class RegisterController{
	
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	RegisterService registerService;
	
	// 회원가입 버튼 눌렀을 때
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String register(Model model) throws Exception {
		
		List<Users> userList = userDao.findAllUsers();
		List<String> emailList = new ArrayList<String>();
		for(int i=0; i<userList.size(); i++){
			emailList.add(userList.get(i).getEmail());
		}
		model.addAttribute("emailList", emailList);

		return "/register/register";
	}
	@RequestMapping(value = "/success", method = RequestMethod.POST)
	public String registerSuccess(@RequestParam("username") String username, @RequestParam("email") String email, 
			@RequestParam("password") String password, @RequestParam("password_confirm") String password_confirm, 
			@RequestParam String role, Model model) throws Exception{
		
		
		registerService.registerUser(username, email, password, role);
		
		return "redirect:/login";
	}
	

}
