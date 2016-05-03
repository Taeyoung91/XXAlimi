//package com.anonyblah.xxalimi.servlet;
//
//import java.util.HashMap;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.anonyblah.xxalimi.controls.Controller;
//
//
//public class DispatcherServlet {
//	protected void service(HttpServletRequest request) {
//		// 페이지 컨트롤러에게 전달할 Map 객체를 준비한다.
//		HashMap<String, Object> model = new HashMap<String, Object>();
//		model.put("session", request.getSession());
//		Controller pageController = (Controller) ctx.getBean(servletPath);
//		// 페이지 컨트롤러를 실행한다.
//		String viewUrl = pageController.execute(model);
//	}
//}
