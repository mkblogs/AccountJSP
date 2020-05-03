package com.tech.mkblogs.service.plainjdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.mkblogs.dto.AccountDTO;
import com.tech.mkblogs.filter.FilterDTO;
import com.tech.mkblogs.mapper.AccountMapper;
import com.tech.mkblogs.model.Account;
import com.tech.mkblogs.service.AccountService;
import com.tech.mkblogs.service.useraudit.UserAuditService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PlainJDBCService implements AccountService {

	@Autowired
	private PlainJDBCRepository repository;
	
	@Autowired
	private UserAuditService auditService;
	
	@Override
	public AccountDTO saveAccount(AccountDTO accountDTO) throws Exception {
		log.info("Starting the saveAccount() method ");
		try {
			accountDTO.setCreatedBy(auditService.getUserId());
			accountDTO.setCreatedName(auditService.getUserName());
			accountDTO.setCreatedTs(auditService.getCurrentTime());
			Account account = AccountMapper.INSTANCE.toAccount(accountDTO);
			account = repository.saveAccount(account);
			accountDTO = AccountMapper.INSTANCE.toAccountDTO(account);
			log.info("Generated Primary Key : " + account.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Ended the saveAccount() method ");
		return accountDTO;
	}
	
	@Override
	public AccountDTO updateAccount(AccountDTO accountDTO) {
		log.info("Starting the updateAccount() method ");
		try {
			accountDTO.setLastModifiedBy(auditService.getUserId());
			accountDTO.setLastModifiedName(auditService.getUserName());
			accountDTO.setLastModifiedTs(auditService.getCurrentTime());
			Account account = AccountMapper.INSTANCE.toAccount(accountDTO);
			account = repository.updateAccount(account);
			accountDTO = AccountMapper.INSTANCE.toAccountDTO(account);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("Ended the updateAccount() method ");
		return accountDTO;
	}
	
	@Override
	public AccountDTO getAccount(Integer id) throws Exception {
		AccountDTO accountDTO = null;
		log.info("Starting the getAccount() method ");
		try {
			Account dbObject = repository.getAccount(id);
			accountDTO = AccountMapper.INSTANCE.toAccountDTO(dbObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Ended the getAccount() method ");
		return accountDTO;
	}
	
	@Override
	public List<AccountDTO> getAllData() throws Exception {
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		log.info("Starting the getAllData() method ");
		try {
			List<Account> dbList = repository.getAllData();
			for(Account dbAccount:dbList) {
				AccountDTO accountDTO = AccountMapper.INSTANCE.toAccountDTO(dbAccount);
				list.add(accountDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Ended the getAllData() method ");
		return list;
	}
	
	@Override
	public List<AccountDTO> search(FilterDTO dto) throws Exception {
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		log.info("Starting the getAllData() method ");
		try {
			List<Account> dbList = repository.search(dto);
			for(Account dbAccount:dbList) {
				AccountDTO accountDTO = AccountMapper.INSTANCE.toAccountDTO(dbAccount);
				list.add(accountDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Ended the getAllData() method ");
		return list;
	}
	
	@Override
	public String deleteAccount(Integer accountId) throws Exception {
		return repository.deleteAccount(accountId);
	}
	
	@Override
	public List<AccountDTO> findByAccountName(String accountName) {
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		log.info("Starting the findByAccountName() method ");
		try {
			List<Account> dbList =  repository.findByAccountName(accountName);
			for(Account dbAccount:dbList) {
				AccountDTO accountDTO = AccountMapper.INSTANCE.toAccountDTO(dbAccount);
				list.add(accountDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Ended the findByAccountName() method ");
		return list;
	}
}
