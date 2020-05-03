package com.tech.mkblogs.service.useraudit;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tech.mkblogs.security.db.dto.UserSessionDTO;
import com.tech.mkblogs.session.operations.SessionOperations;

@Service
public class UserAuditService {

	@Autowired
	SessionOperations sessionOperations;
	
	public Integer getUserId() throws Exception {
		UserSessionDTO userDTO = (UserSessionDTO) sessionOperations.fetchSession();
	    return userDTO.getId(); 
	}
	
	public String getUserName() throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 if (authentication == null || !authentication.isAuthenticated()) {
			   return null;
		 }
		 return authentication.getName();
	}
	
	public LocalDateTime getCurrentTime() {
		return LocalDateTime.now();
	}
}
