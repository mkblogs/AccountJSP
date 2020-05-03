package com.tech.mkblogs.controller.admin;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.tech.mkblogs.security.db.dto.UserSessionDTO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	HttpSession session;
	
	@Autowired
	private SessionRegistry sessionRegistry;

	
	@GetMapping("/getSession")
	@ResponseBody
	public UserSessionDTO getSessionValue() {
		log.info("| Request Time - Start - getSessionValue() " + LocalTime.now());
		UserSessionDTO userInfo = (UserSessionDTO) session.getAttribute(WebApplicationContext.SCOPE_SESSION);
		System.out.println(userInfo);
		return userInfo;
	}
	
	@GetMapping("/listActiveUsers")
	@ResponseBody
	public List<String> listLoggedInUsers() {
        final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        List<String> userList = new ArrayList<String>();
        for(final Object principal : allPrincipals) {
            if(principal instanceof String) {
                userList.add(principal.toString());
            }
        }
        return userList;
    }
}
