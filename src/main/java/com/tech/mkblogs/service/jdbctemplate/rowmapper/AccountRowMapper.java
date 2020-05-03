package com.tech.mkblogs.service.jdbctemplate.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.tech.mkblogs.model.Account;

public class AccountRowMapper implements RowMapper<Account>{

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account account = new Account();
		account.setId(rs.getInt("id"));
		account.setAccountName(rs.getString("account_name"));
		account.setAccountType(rs.getString("account_type"));
		account.setAmount(rs.getBigDecimal("amount"));
		account.setCreatedBy(rs.getInt("created_by"));
		account.setCreatedName(rs.getString("created_name"));
		LocalDateTime createdTs = rs.getTimestamp("created_ts") == null ? null : rs.getTimestamp("created_ts").toLocalDateTime(); 
		account.setCreatedTs(createdTs);
		account.setLastModifiedBy(rs.getInt("last_modified_by"));
		account.setLastModifiedName(rs.getString("last_modified_name"));
		LocalDateTime modifiedTs = rs.getTimestamp("last_modified_ts") == null ? null : rs.getTimestamp("last_modified_ts").toLocalDateTime();
		account.setLastModifiedTs(modifiedTs);
		account.setVersion(rs.getInt("version"));
		return account;
	}
}
