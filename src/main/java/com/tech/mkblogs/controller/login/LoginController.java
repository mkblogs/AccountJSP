package com.tech.mkblogs.controller.login;

import java.time.LocalTime;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tech.mkblogs.dto.SettingsDTO;
import com.tech.mkblogs.mapper.UserMapper;
import com.tech.mkblogs.security.db.AccountUserRepository;
import com.tech.mkblogs.security.db.dto.UserSessionDTO;
import com.tech.mkblogs.security.db.model.Settings;
import com.tech.mkblogs.security.db.model.User;
import com.tech.mkblogs.session.operations.SessionOperations;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class LoginController {

	@Autowired
	SessionOperations sessionOperations;
	
	@Autowired
	AccountUserRepository repository;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/userlanding")
	public String userlanding(HttpSession session) {
		return "userlanding";
	}
	
	
	@GetMapping("/adminlanding")
	public String adminlanding() {
		return "adminlanding";
	}
	
	@GetMapping(value = "/settings")
	public String showSettingsPage(Model model) throws Exception {
		log.info("| Request Time - Start - showSettingsPage() " + LocalTime.now());
		SettingsDTO settingsDTO = new SettingsDTO();
		UserSessionDTO userSessionDTO = (UserSessionDTO) sessionOperations.fetchSession();
		settingsDTO.setConnectionType(userSessionDTO.getConnectionType());
		settingsDTO.setPrimaryKeyGenerationType(userSessionDTO.getPrimaryKeyGenerationType());
		settingsDTO.setAuthenticationType(userSessionDTO.getAuthenticationType());
		settingsDTO.setAuthenticationEncrypted(userSessionDTO.getAuthenticationEncrypted());
		settingsDTO.setMessage("");
		model.addAttribute("settingsDTO", settingsDTO);
		return "settings";
	}
	
	@PostMapping(value = "/settings")
	public String saveSettings(@ModelAttribute("settingsDTO") SettingsDTO settingsDTO, 
			Model model) throws Exception {
		log.info("| Request Time - Start - saveSettings() " + LocalTime.now());
		String message = "";
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User loginUser = repository.findByLoginName(auth.getName());
			Settings settings = loginUser.getSettings();
			settings.setConnectionType(settingsDTO.getConnectionType());
			settings.setPrimaryKeyGenerationType(settingsDTO.getPrimaryKeyGenerationType());
			//Remaining here
			message = "Successfully Updated..";
			
			UserSessionDTO userDTO = new UserSessionDTO();
    		userDTO = UserMapper.INSTANCE.toUserDTO(loginUser);
    		sessionOperations.storeSession(userDTO);
			
		}catch(Exception e) {
			message = "Failed to update Settings :"+e.getMessage();
			e.printStackTrace();
		}
		settingsDTO.setMessage(message);		
		model.addAttribute("settingsDTO", settingsDTO);
		return "settings";
	}
}
