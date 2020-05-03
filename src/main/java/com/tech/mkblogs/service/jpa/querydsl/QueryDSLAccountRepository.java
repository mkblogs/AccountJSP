package com.tech.mkblogs.service.jpa.querydsl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.tech.mkblogs.model.Account;

public interface QueryDSLAccountRepository 
		extends JpaRepository<Account, Integer>,QuerydslPredicateExecutor<Account>{

	List<Account> findByAccountNameContaining(String accountName);
	List<Account> findByAmount(BigDecimal amount);
}
