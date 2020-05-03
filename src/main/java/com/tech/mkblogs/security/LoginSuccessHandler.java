package com.tech.mkblogs.security;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tech.mkblogs.mapper.UserMapper;
import com.tech.mkblogs.security.config.AccountAuthConfig;
import com.tech.mkblogs.security.db.AccountUserRepository;
import com.tech.mkblogs.security.db.dto.UserSessionDTO;
import com.tech.mkblogs.security.db.model.User;
import com.tech.mkblogs.session.operations.SessionOperations;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class LoginSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	AccountUserRepository repository;
	
	@Autowired
	AccountAuthConfig authConfig;
	
	@Autowired
	SessionOperations sessionOperations; 
	
	@Override
	@Transactional
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		//set our response to OK status
        response.setStatus(HttpServletResponse.SC_OK);
        boolean admin = false;
        
        log.info("inside onAuthenticationSuccess(...) ");
        
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_ADMIN".equals(auth.getAuthority())){
              admin = true;
            }
        }
        
        if("db".equalsIgnoreCase(authConfig.getAuthType())) {
        	User loginUser = repository.findByLoginName(authentication.getName());
    		loginUser.setLastLogin(LocalDateTime.now());
    		repository.save(loginUser);
    		
    		UserSessionDTO userDTO = new UserSessionDTO();
    		userDTO = UserMapper.INSTANCE.toUserDTO(loginUser);
    		sessionOperations.storeSession(userDTO);
        }      
        
        if(admin){
          response.sendRedirect("/adminlanding");
        }else{
          response.sendRedirect("/userlanding");
        }
	}
	
}
