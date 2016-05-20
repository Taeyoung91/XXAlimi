package com.anonyblah.xxalimi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
	@Autowired private SessionRegistry sessionRegistry;
	
	public List<Object> getLoggedInUsers() {
		return sessionRegistry.getAllPrincipals();
	}
}
