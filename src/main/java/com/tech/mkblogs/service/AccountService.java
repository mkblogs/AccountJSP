package com.tech.mkblogs.service;

import java.util.List;

import com.tech.mkblogs.dto.AccountDTO;
import com.tech.mkblogs.filter.FilterDTO;

public interface AccountService {

	public AccountDTO saveAccount(AccountDTO accountDTO) throws Exception;
	public AccountDTO updateAccount(AccountDTO accountDTO);
	public AccountDTO getAccount(Integer id) throws Exception;
	public List<AccountDTO> getAllData() throws Exception;
	public List<AccountDTO> search(FilterDTO dto) throws Exception;
	public String deleteAccount(Integer accountId) throws Exception;
	
	public List<AccountDTO> findByAccountName(String accountName);
	
	
}
