package com.anonyblah.xxalimi.controls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.anonyblah.xxalimi.service.RegisterService;

@Controller
public class LeaveController {
	
	@Autowired
	RegisterService registerService;
	
	@RequestMapping(value = "/leave")
	public String DeleteId(@RequestParam("email") String email) throws Exception{
//		Users tempUser = userDao.findByEmail(email);
		
		
		registerService.deleteUser(email);
		
		return "redirect:/logout";
	}
}
