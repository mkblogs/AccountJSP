package com.tech.mkblogs.aggregator;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.tech.mkblogs.dto.AccountDTO;
import com.tech.mkblogs.filter.FilterDTO;
import com.tech.mkblogs.security.db.dto.UserSessionDTO;
import com.tech.mkblogs.service.AccountService;
import com.tech.mkblogs.service.hibernate.entitymanager.EntityManagerAccountService;
import com.tech.mkblogs.service.jdbctemplate.hikari.HikariAccountService;
import com.tech.mkblogs.service.jdbctemplate.rowmapper.RowMapperAccountService;
import com.tech.mkblogs.service.jdbctemplate.tomcat.TomcatAccountService;
import com.tech.mkblogs.service.jpa.querydsl.QueryDSLAccountService;
import com.tech.mkblogs.service.jpa.simple.JPAAccountService;
import com.tech.mkblogs.service.plainjdbc.PlainJDBCService;
import com.tech.mkblogs.session.operations.SessionOperations;

@Service
public class AggregatorService {

		
	private AccountService accountService;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	SessionOperations sessionOperations;
	
	 @PostConstruct
	  public void init(){
		 //String connectionType ="db"; //userInfo.getConnectionType();
		// getServiceObjct(connectionType);
	  }
	 
	 /**
	  * 
	  * @param connectionType
	  */
	 private void getServiceObjct(String connectionType) {
		 switch (connectionType) {
			case "PLAIN_JDBC":
				try {
					accountService = applicationContext.getBean(PlainJDBCService.class);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Exception while getting the PlainJDBCService from context ::"+e.getMessage());
				}
			break;
			case "TOMCAT":
				try {
					accountService = applicationContext.getBean(TomcatAccountService.class);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Exception while getting the PlainJDBCService from context ::"+e.getMessage());
				}
			break;
			case "ROW_MAPPER":
				try {
					accountService = applicationContext.getBean(RowMapperAccountService.class);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Exception while getting the PlainJDBCService from context ::"+e.getMessage());
				}
			break;
			case "HIKARI":
				try {
					accountService = applicationContext.getBean(HikariAccountService.class);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Exception while getting the PlainJDBCService from context ::"+e.getMessage());
				}
			break;
			case "ENTITY_MANAGER":
				try {
					accountService = applicationContext.getBean(EntityManagerAccountService.class);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Exception while getting the PlainJDBCService from context ::"+e.getMessage());
				}
			break;
			case "JPA":
				try {
					accountService = applicationContext.getBean(JPAAccountService.class);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Exception while getting the JPAAccountService from context ::"+e.getMessage());
				}
				
			break;	
			case "QUERY_DSL":
				try {
					accountService = applicationContext.getBean(QueryDSLAccountService.class);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Exception while getting the QueryDSLAccountService from context ::"+e.getMessage());
				}
			break;
			default:
				try {
					accountService = applicationContext.getBean(PlainJDBCService.class);
				}catch(Exception e) {
					e.printStackTrace();
					throw new RuntimeException("Exception while getting the PlainJDBCService from context ::"+e.getMessage());
				}
				break;
		}
	 }
	 
	 public AccountDTO saveAccount(AccountDTO accountDTO) throws Exception{
		 UserSessionDTO userInfo = sessionOperations.fetchSession();
		 String connectionType = userInfo.getConnectionType();
		 getServiceObjct(connectionType);
		 return accountService.saveAccount(accountDTO);
	 }
	
	 public AccountDTO updateAccount(AccountDTO accountDTO) {
		 UserSessionDTO userInfo = sessionOperations.fetchSession();
		 String connectionType = userInfo.getConnectionType();
		 getServiceObjct(connectionType);
		 return accountService.updateAccount(accountDTO);
	 }
	 
	 
	 public List<AccountDTO> findByAccountName(String accountName){
		 UserSessionDTO userInfo = sessionOperations.fetchSession();
		 String connectionType = userInfo.getConnectionType();
		 getServiceObjct(connectionType);
		 return accountService.findByAccountName(accountName);
	 }
	 
	 public AccountDTO getAccount(Integer id) throws Exception{
		 UserSessionDTO userInfo = sessionOperations.fetchSession();
		 String connectionType = userInfo.getConnectionType();
		 getServiceObjct(connectionType);
		 return accountService.getAccount(id);
	 }
	 
	 public List<AccountDTO> getAllData() throws Exception{
		 UserSessionDTO userInfo = sessionOperations.fetchSession();
		 String connectionType = userInfo.getConnectionType();
		 getServiceObjct(connectionType);
		 return accountService.getAllData();
	 }
	 
	 public List<AccountDTO> search(FilterDTO dto) throws Exception {
		 UserSessionDTO userInfo = sessionOperations.fetchSession();
		 String connectionType = userInfo.getConnectionType();
		 getServiceObjct(connectionType);
		 return accountService.search(dto);
	 }
	 
	 public String deleteAccount(Integer accountId) throws Exception {
		 UserSessionDTO userInfo = sessionOperations.fetchSession();
		 String connectionType = userInfo.getConnectionType();
		 getServiceObjct(connectionType);
		 return accountService.deleteAccount(accountId);
	 }
}
