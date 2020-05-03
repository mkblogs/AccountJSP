package com.tech.mkblogs.session.operations;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.tech.mkblogs.security.db.dto.UserSessionDTO;

@Component
public class SessionOperations {

	@Autowired
	private HttpSession session;
	
	public void storeSession(UserSessionDTO userDTO) {
		session.setAttribute(WebApplicationContext.SCOPE_SESSION, userDTO);
	}
	
	public UserSessionDTO fetchSession() {
		UserSessionDTO userDTO = (UserSessionDTO) session.getAttribute(WebApplicationContext.SCOPE_SESSION);
		return userDTO;
	}
}
