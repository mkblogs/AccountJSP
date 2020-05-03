package com.tech.mkblogs.service.jdbctemplate.rowmapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tech.mkblogs.constants.SQLConstants;
import com.tech.mkblogs.filter.FilterDTO;
import com.tech.mkblogs.model.Account;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class RowMapperRepository {

	@Autowired
	private JdbcTemplate dbcpJdbcTemplate;
	
	public Account saveAccount(Account account) throws Exception {
		try {
			KeyHolder generatedKeyHolder =  new  GeneratedKeyHolder();
			dbcpJdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1, account.getAccountName());
					preparedStatement.setString(2, account.getAccountType());
		            preparedStatement.setBigDecimal(3, account.getAmount());
		            preparedStatement.setInt(4, account.getCreatedBy());
		            preparedStatement.setString(5, account.getCreatedName());            
		            preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
		            preparedStatement.setInt(7, 0);
					return preparedStatement;
				}
			}, generatedKeyHolder);
			Integer generatedKey = generatedKeyHolder.getKey() != null ? generatedKeyHolder.getKey().intValue() : 0;
			account.setId(generatedKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}

	public Account updateAccount(Account account) throws Exception {
		try {
			log.info("Starting the method " + account);
			Optional<Account> dbObjectOptional = getAccount(account.getId());
			if (dbObjectOptional.isPresent()) {
				Account dbAccount = dbObjectOptional.get();
				Integer version = 0;
				if(dbAccount.getVersion() != null) {
					version = dbAccount.getVersion() + 1;
				}
				Object[] values = {account.getAccountName(),
						account.getAccountType(),account.getAmount(),version,
						account.getLastModifiedBy(),
						account.getLastModifiedName(),
						Timestamp.valueOf(LocalDateTime.now()),
						account.getId()};
				
				dbcpJdbcTemplate.update(SQLConstants.SQL_UPDATE,values);
			} else {
				throw new RuntimeException("Entity Not Found " + account.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}

	public Optional<Account> getAccount(Integer id) throws Exception {
		try {
			Account account =  dbcpJdbcTemplate.queryForObject(SQLConstants.SQL_GET_ACCOUNT,
	                new Object[]{id},new AccountRowMapper());
			Optional<Account> optionalObject = Optional.of(account);
			return optionalObject;
		}catch(EmptyResultDataAccessException e) {
			return Optional.empty();
		}catch(Exception e1){
			return Optional.empty();
		}
	}

	public List<Account> getAllData() throws Exception {
		return dbcpJdbcTemplate.query(SQLConstants.SQL_GET_ALL_DATA,new AccountRowMapper());
	}
	
	public List<Account> search(FilterDTO dto) throws Exception {
		String name = dto.getAccountName();
		String type = dto.getAccountType();
		String amount = dto.getAmount();
		
		String sql = SQLConstants.SQL_SEARCH_ACCOUNT_ONE;
		if(name != null && !(name.isEmpty()))
			sql = sql + " and account_name like  '%"+name+"%'";
		if(type != null && !(type.isEmpty()))
			sql = sql + " and account_type =  '"+type+"' ";
		if(amount != null && !(amount.isEmpty()))
			sql = sql + " and amount =  "+amount+" ";
		
		log.info(sql);
		 return dbcpJdbcTemplate.query(sql,new AccountRowMapper());
	}
	
	public List<Account> findByAccountName(String accountName) throws Exception {
		return dbcpJdbcTemplate.query(SQLConstants.SQL_GET_NAME,new Object[]{accountName},new AccountRowMapper());
	}
	
	public Integer deleteById(Integer id) {
		return dbcpJdbcTemplate.update(SQLConstants.SQL_DELETE,id);
	}
}
