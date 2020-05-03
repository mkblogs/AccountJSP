package com.tech.mkblogs.service.hibernate.entitymanager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

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
public class EntityManagerAccountService implements AccountService {

	@Autowired
	private EntityManagerAccount repository;
	
	@Autowired
	private UserAuditService auditService;
	
	
	@Override
	public AccountDTO saveAccount(AccountDTO accountDTO) throws Exception {
		log.info("Starting the saveAccount() method ");
		try {
			accountDTO.setCreatedName(auditService.getUserName());
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
			Account dbAccount = repository.getAccount(accountDTO.getAccountId());
			if(dbAccount != null) {
				accountDTO.setLastModifiedName(auditService.getUserName());
				dbAccount = AccountMapper.INSTANCE.toAccountForUpdate(accountDTO, dbAccount);
				dbAccount = repository.updateAccount(dbAccount);
				accountDTO = AccountMapper.INSTANCE.toAccountDTO(dbAccount);
			}else {
				throw new NoResultException("Entity Not Found " + accountDTO.getAccountId());
			}
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
			Account dbAccount = repository.getAccount(id);
			if(dbAccount != null) {
				accountDTO = AccountMapper.INSTANCE.toAccountDTO(dbAccount);
			}else {
				throw new RuntimeException("Entity Not Found " + id);
			}
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
			List<Account> dbList = repository.getAllAccount();
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
		log.info("Starting the search() method ");
		try {
			Iterable<Account> dbList = repository.search(dto);
			for(Account dbAccount:dbList) {
				AccountDTO accountDTO = AccountMapper.INSTANCE.toAccountDTO(dbAccount);
				list.add(accountDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Ended the search() method ");
		return list;
	}
	
	@Override
	public String deleteAccount(Integer accountId) throws Exception {
		log.info("Starting the deleteAccount() method ");
		String result = "";
		try {
			result = repository.deleteAccount(accountId);
		}catch(Exception e) {
			e.printStackTrace();
			result = "Failure";
		}
		return result;
	}
	
	
	@Override
	public List<AccountDTO> findByAccountName(String accountName) {
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		log.info("Starting the findById() method ");
		try {
			FilterDTO dto = new FilterDTO();
			dto.setAccountName(accountName);
			dto.setFromSearch(false);
			List<Account> dbList =  repository.search(dto);
			for(Account dbAccount:dbList) {
				AccountDTO accountDTO = AccountMapper.INSTANCE.toAccountDTO(dbAccount);
				list.add(accountDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Ended the findById() method ");
		return list;
	}
}
