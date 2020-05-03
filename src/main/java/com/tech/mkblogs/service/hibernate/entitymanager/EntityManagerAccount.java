package com.tech.mkblogs.service.hibernate.entitymanager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.tech.mkblogs.filter.FilterDTO;
import com.tech.mkblogs.model.Account;

@Component
@Transactional
public class EntityManagerAccount {

	@Autowired
	private EntityManager entityManager;
	
	public Account saveAccount(Account account) throws Exception{
		entityManager.persist(account);
		return account;
	}
	
	public Account updateAccount(Account account) throws Exception{
		entityManager.merge(account);
		return account;
	}
	
	public Account getAccount(Integer accountId) throws Exception {
		return entityManager.find(Account.class, accountId);
	}
	
	public List<Account> getAllAccount() throws Exception {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
		Root<Account> from = criteriaQuery.from(Account.class);
		
		CriteriaQuery<Account> selectQuery = criteriaQuery.select(from);
        TypedQuery<Account> typedQuery = entityManager.createQuery(selectQuery);
        List<Account> accountList = typedQuery.getResultList();
		//entityManager.createQuery("SELECT account FROM Account account", Account.class).getResultList();
        return accountList;
	}
	
	public List<Account> search(FilterDTO dto) throws Exception {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
		Root<Account> from = criteriaQuery.from(Account.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		if(!StringUtils.isEmpty(dto.getAccountName())) {
			if(dto.isFromSearch()) {
				predicates.add(criteriaBuilder.and(criteriaBuilder.like(from.get("accountName"), "%"+dto.getAccountName()+"%")));
			}else {
				predicates.add(criteriaBuilder.and(criteriaBuilder.equal(from.get("accountName"), dto.getAccountName())));
			}
		}
		if(!StringUtils.isEmpty(dto.getAccountType())) {
			predicates.add(criteriaBuilder.and(criteriaBuilder.equal(from.get("accountType"), dto.getAccountType())));
		}
		if(!StringUtils.isEmpty(dto.getAmount())) {
			predicates.add(criteriaBuilder.and(criteriaBuilder.equal(from.get("amount"), dto.getAmount())));
		}
		CriteriaQuery<Account> selectQuery = criteriaQuery.select(from).where(predicates.toArray(new Predicate[]{}));
        TypedQuery<Account> typedQuery = entityManager.createQuery(selectQuery);
        List<Account> accountList = typedQuery.getResultList();
		return accountList;
	}
	
	public List<Account> findByAccountName(String accountName) throws Exception {
		FilterDTO filterDTO = new FilterDTO();
		filterDTO.setAccountName(accountName);
		filterDTO.setFromSearch(false);
		return search(filterDTO);
	}
	
	public String deleteAccount(Integer accountId) throws Exception {
		try {
			Account account = entityManager.find(Account.class, accountId);
			if(account != null) {
				entityManager.remove(account);
				return "Success";
			}else {
				throw new NoResultException("No Record Found for given "+accountId);
			}
		}catch(Exception e) {
			return "Failure";
		}
	}
}
