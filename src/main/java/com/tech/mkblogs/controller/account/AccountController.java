package com.tech.mkblogs.controller.account;

import java.time.LocalTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tech.mkblogs.aggregator.AggregatorService;
import com.tech.mkblogs.dto.AccountDTO;
import com.tech.mkblogs.dto.LoginDTO;
import com.tech.mkblogs.filter.FilterDTO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class AccountController {

	@Autowired
	private AggregatorService aggregatorService;
	
	
	@GetMapping(value = "/landing")
	public String landing(Model model) throws Exception {
		log.info("| Request Time - Start - landingPage() " + LocalTime.now());
		return "landing";
	}
	
	@GetMapping(value = "/")
	public String welcomePage(Model model) {
		log.info("| Request Time - Start - welcomePage() " + LocalTime.now());
	    model.addAttribute("loginForm", new LoginDTO()); 
	    return "index";
	}
	
	@GetMapping(value = "/logout")
	public String logout(Model model) {
		log.info("| Request Time - Start - logout() " + LocalTime.now());
	    model.addAttribute("loginForm", new LoginDTO()); 
	    return "index";
	}
	
	@GetMapping(value = "/create")
	public String createPage(Model model) {
		log.info("| Request Time - Start - createPage() " + LocalTime.now());
		AccountDTO form = new AccountDTO();
	    model.addAttribute("accountDTO", form); 
	    return "create";
	}
	
	@PostMapping("/create")
	public String saveAccount(@ModelAttribute("accountDTO") @Valid AccountDTO accountDTO,
			BindingResult result,Model model) throws Exception {
		log.info("| Request Time - Start - saveAccount() " + LocalTime.now());
		if(result.hasErrors()) {
			return "create";
		}else {
			accountDTO = aggregatorService.saveAccount(accountDTO);
			model.addAttribute("accountDTO", accountDTO);			
			return "success";
		}
	}
	
	@GetMapping("/search")
	public String search(Model model) {
		log.info("| Request Time - Start - search() " + LocalTime.now());
		AccountDTO form = new AccountDTO();
	    model.addAttribute("accountDTO", form); 
	    return "search";
	}
	
	@PostMapping(value = "/search")
	public String searchAccount(@ModelAttribute AccountDTO accountDTO,BindingResult result,Model model) throws Exception {
		
		log.info("| Request Time - Start - searchAccount() " + LocalTime.now());
		FilterDTO dto = new FilterDTO();
		dto.setAccountName(accountDTO.getName());
		dto.setAccountType(accountDTO.getType());
		if(!StringUtils.isEmpty(accountDTO.getAmount())) {
			dto.setAmount(accountDTO.getAmount().toString());
		}
		List<AccountDTO> list = aggregatorService.search(dto);
		model.addAttribute("accounts", list);
		log.info("size :: "+list.size());
	    return "search";
	}
	
	@GetMapping(value = "/edit")
	public String editPage(Model model,@RequestParam("id") Integer accountId) throws Exception {
		log.info("| Request Time - Start - editPage() " + LocalTime.now());
		AccountDTO accountDTO = new AccountDTO();		
		accountDTO = aggregatorService.getAccount(accountId);
	    model.addAttribute("accountDTO", accountDTO); 
	    return "edit";
	}
	
	@PostMapping(value = "/edit")
	public String saveEditPage(@ModelAttribute("accountDTO") @Valid AccountDTO accountDTO,
			BindingResult result,Model model) throws Exception {
		log.info("| Request Time - Start - saveEditPage() " + LocalTime.now());
		if(result.hasErrors()) {
			return "edit";
		}else {
			accountDTO = aggregatorService.updateAccount(accountDTO);
			model.addAttribute("accountDTO", accountDTO);
			return "success";
		}	    
	}
	
	@GetMapping(value = "/view")
	public String viewPage(Model model,@RequestParam("id") Integer accountId) throws Exception {
		log.info("| Request Time - Start - viewPage() " + LocalTime.now());
		AccountDTO accountDTO = new AccountDTO();		
		accountDTO = aggregatorService.getAccount(accountId);
	    model.addAttribute("accountDTO", accountDTO); 
	    return "view";
	}
	
	@DeleteMapping(value = "/delete",produces="text/plain")
	@ResponseBody
	public String deletePage(Model model,@RequestParam("id") Integer accountId) throws Exception {
		log.info("| Request Time - Start - deletePage() " + LocalTime.now());
		String status = aggregatorService.deleteAccount(accountId);
	    return status;
	}
}
