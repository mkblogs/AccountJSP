package com.tech.mkblogs.service.jpa.simple;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.tech.mkblogs.model.Account;

@Repository
public interface JPAAccountRepository 
			extends JpaRepository<Account, Integer>,JpaSpecificationExecutor<Account> {

	List<Account> findByAccountName(String accountName);
	List<Account> findByAmount(BigDecimal amount);
	
}
