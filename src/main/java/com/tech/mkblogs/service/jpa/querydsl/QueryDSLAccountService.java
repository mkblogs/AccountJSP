package com.tech.mkblogs.service.jpa.querydsl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.tech.mkblogs.dto.AccountDTO;
import com.tech.mkblogs.filter.FilterDTO;
import com.tech.mkblogs.mapper.AccountMapper;
import com.tech.mkblogs.model.Account;
import com.tech.mkblogs.model.QAccount;
import com.tech.mkblogs.service.AccountService;
import com.tech.mkblogs.service.useraudit.UserAuditService;

import lombok.extern.log4j.Log4j2;
@Service
@Log4j2
public class QueryDSLAccountService implements AccountService {

	@Autowired
	private QueryDSLAccountRepository repository;
	
	@Autowired
	private UserAuditService auditService;
	
	@Override
	public AccountDTO saveAccount(AccountDTO accountDTO) throws Exception {
		log.info("Starting the saveAccount() method ");
		try {
			accountDTO.setCreatedName(auditService.getUserName());
			Account account = AccountMapper.INSTANCE.toAccount(accountDTO);
			account = repository.save(account);
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
			Optional<Account> dbAccountOptional = repository.findById(accountDTO.getAccountId());
			if(dbAccountOptional.isPresent()) {
				Account dbAccount = dbAccountOptional.get();
				accountDTO.setLastModifiedName(auditService.getUserName());
				dbAccount = AccountMapper.INSTANCE.toAccountForUpdate(accountDTO, dbAccount);
				dbAccount = repository.save(dbAccount);
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
			Optional<Account> dbObjectExists = repository.findById(id);
			if(dbObjectExists.isPresent()) {
				Account dbAccount = dbObjectExists.get();
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
			List<Account> dbList = repository.findAll();
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
			BooleanBuilder builder = new BooleanBuilder();
			
			if(!StringUtils.isEmpty(dto.getAccountName())) {
				builder.and(QAccount.account.accountName.like("%"+dto.getAccountName()+"%"));
			}
			
			if(!StringUtils.isEmpty(dto.getAccountType())) {
				builder.and(QAccount.account.accountType.eq(dto.getAccountType()));
			}
			
			if(!StringUtils.isEmpty(dto.getAmount())) {
				builder.and(QAccount.account.amount.eq(new BigDecimal(dto.getAmount())));
			}
			OrderSpecifier<String> orderSpecifier = QAccount.account.accountName.asc();
			
			Iterable<Account> dbList = repository.findAll(builder,orderSpecifier);
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
		String result = "";
		try {
			Optional<Account> optionalAccount = repository.findById(accountId);
			if(optionalAccount.isPresent()) {
				Account account = optionalAccount.get();
				repository.delete(account);
				result = "Success";
			}else {
				throw new NoResultException("No Record found "+accountId);
			}
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
			List<Account> dbList =  repository.findByAccountNameContaining(accountName);
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
