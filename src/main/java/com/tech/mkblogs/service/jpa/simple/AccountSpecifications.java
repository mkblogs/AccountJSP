package com.tech.mkblogs.service.jpa.simple;

import java.math.BigDecimal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.tech.mkblogs.filter.FilterDTO;
import com.tech.mkblogs.model.Account;

@SuppressWarnings("serial")
public class AccountSpecifications {

	/**
	 * Get The Records based On FilterObjects
	 * 
	 * @param filterDTO
	 * 
	 * @return Specification<Account>
	 *         
	 */
	public static Specification<Account> getAccountByFilterObjects(FilterDTO filterDTO) {
		String accountName = filterDTO.getAccountName();
		String accountType = filterDTO.getAccountType();
		String amount      = filterDTO.getAmount();
		
		return Specification.where(getAccountByAccountName(accountName))
						.and(getAccountByAccountType(accountType)
						.and(getAccountByAmount(amount)));
	}
	/**
	 * 
	 * @param accountName
	 * @return
	 */
	public static Specification<Account> getAccountByAccountName(String accountName) {
		return new Specification<Account>() {
			@Override
			public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				
				if(!StringUtils.isEmpty(accountName)) {
					Predicate likePredicate =  criteriaBuilder.like(root.get("accountName"), "%" + accountName + "%");
					System.out.println();
					return likePredicate;
				}
				return null;
			}
		};
	}
	
	/**
	 * 
	 * @param accountName
	 * @return
	 */
	
	public static Specification<Account> getAccountByAccountType(String accountType) {
		return new Specification<Account>() {
			@Override
			public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				
				if(!StringUtils.isEmpty(accountType)) {
					Predicate equalPredicate = criteriaBuilder.equal(root.get("accountType"),  accountType);
					return equalPredicate;
				}
				return null;
			}
		};
	}
	
	/**
	 * 
	 * @param accountName
	 * @return
	 */
	public static Specification<Account> getAccountByAmount(String amount) {
		return new Specification<Account>() {
			@Override
			public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				
				if(!StringUtils.isEmpty(amount)) {
					Predicate equalPredicate = criteriaBuilder.equal(root.get("amount"),  new BigDecimal(amount));
					return equalPredicate;
				}
				return null;
			}
		};
	}
}
