package com.tech.mkblogs.service.plainjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.tech.mkblogs.config.DBProperties;
import com.tech.mkblogs.constants.SQLConstants;
import com.tech.mkblogs.filter.FilterDTO;
import com.tech.mkblogs.model.Account;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class PlainJDBCRepository {

	@Autowired
	private DBProperties dbProperties;
	
	public Account saveAccount(Account account) throws Exception {
		Connection connection = null;
		try {
			connection = getConnection();
			log.info(SQLConstants.SQL_INSERT);
			PreparedStatement preparedStatement = 
					connection.prepareStatement(SQLConstants.SQL_INSERT,
					new String[] { "id" });

			preparedStatement.setString(1, account.getAccountName());
			preparedStatement.setString(2, account.getAccountType());
			preparedStatement.setBigDecimal(3, account.getAmount());
			preparedStatement.setInt(4, account.getCreatedBy());
			preparedStatement.setString(5, account.getCreatedName());
			preparedStatement.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
			preparedStatement.setInt(7, 0);
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			Integer generatedKey = rs.next() ? rs.getInt(1) : 0;
			account.setId(generatedKey);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				log.info("Closing the connection in saveAccount() ");
				connection.close();
			}
		}
		return account;
	}

	public Account updateAccount(Account account) throws Exception {
		Connection connection = null;
		try {

			Account dbObject = getAccount(account.getId());
			if (dbObject != null) {
				log.info(SQLConstants.SQL_UPDATE);
				connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_UPDATE);

				preparedStatement.setString(1, account.getAccountName());
				preparedStatement.setString(2, account.getAccountType());
				preparedStatement.setBigDecimal(3, account.getAmount());
				preparedStatement.setInt(4, dbObject.getVersion() + 1);
				preparedStatement.setInt(5, account.getLastModifiedBy());
				preparedStatement.setString(6, account.getLastModifiedName());
				preparedStatement.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
				preparedStatement.setInt(8, account.getId());
				
				preparedStatement.executeUpdate();
			} else {
				throw new RuntimeException("Entity Not Found " + account.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				log.info("Closing the connection in updateAccount() ");
				connection.close();
			}
		}
		return account;
	}

	public Account getAccount(Integer id) throws Exception {
		Connection connection = null;
		Account account = new Account();
		try {
			connection = getConnection();
			log.info(SQLConstants.SQL_GET_ACCOUNT);
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_GET_ACCOUNT);

			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				account.setId(rs.getInt("id"));
				account.setAccountName(rs.getString("account_name"));
				account.setAccountType(rs.getString("account_type"));
				account.setAmount(rs.getBigDecimal("amount"));
				account.setVersion(rs.getInt("version"));
				account.setCreatedBy(rs.getInt("created_by"));
				account.setCreatedName(rs.getString("created_name"));
				Timestamp createdTimestamp = rs.getTimestamp("created_ts");
				if (createdTimestamp != null)
					account.setCreatedTs(createdTimestamp.toLocalDateTime());
				account.setLastModifiedBy(rs.getInt("last_modified_by"));
				account.setLastModifiedName(rs.getString("last_modified_name"));
				Timestamp modifiedTime = rs.getTimestamp("last_modified_ts");
				if (modifiedTime != null)
					account.setLastModifiedTs(modifiedTime.toLocalDateTime());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				log.info("Closing the connection in getAccount() ");
				connection.close();
			}
		}
		return account;
	}

	public List<Account> getAllData() throws Exception {
		Connection connection = null;
		List<Account> list = new ArrayList<>();
		try {
			connection = getConnection();
			log.info(SQLConstants.SQL_GET_ALL_DATA);
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_GET_ALL_DATA);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt("id"));
				account.setAccountName(rs.getString("account_name"));
				account.setAccountType(rs.getString("account_type"));
				account.setAmount(rs.getBigDecimal("amount"));
				account.setVersion(rs.getInt("version"));
				account.setCreatedBy(rs.getInt("created_by"));
				account.setCreatedName(rs.getString("created_name"));
				Timestamp createdTimestamp = rs.getTimestamp("created_ts");
				if (createdTimestamp != null)
					account.setCreatedTs(createdTimestamp.toLocalDateTime());
				account.setLastModifiedBy(rs.getInt("last_modified_by"));
				account.setLastModifiedName(rs.getString("last_modified_name"));
				Timestamp modifiedTime = rs.getTimestamp("last_modified_ts");
				if (modifiedTime != null)
					account.setLastModifiedTs(modifiedTime.toLocalDateTime());
				list.add(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				log.info("Closing the connection in getAllData() ");
				connection.close();
			}
		}
		return list;
	}
	
	public List<Account> search(FilterDTO dto) throws Exception {
		Connection connection = null;
		List<Account> list = new ArrayList<>();
		try {
			connection = getConnection();
			String searchSQL = SQLConstants.SQL_GET_ALL_DATA + " WHERE 1 = 1 ";
			if(!StringUtils.isEmpty(dto.getAccountName())) {
				searchSQL += " AND ACCOUNT_NAME LIKE '%"+dto.getAccountName() + "%'";
			}
			if(!StringUtils.isEmpty(dto.getAccountType())) {
				searchSQL += " AND ACCOUNT_TYPE ='"+dto.getAccountType() + "'";
			}
			if(!StringUtils.isEmpty(dto.getAmount())) {
				searchSQL += " AND AMOUNT = "+dto.getAmount() + "";
			}
			log.info("Search Query ::"+searchSQL);
			PreparedStatement preparedStatement = connection.prepareStatement(searchSQL);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt("id"));
				account.setAccountName(rs.getString("account_name"));
				account.setAccountType(rs.getString("account_type"));
				account.setAmount(rs.getBigDecimal("amount"));
				account.setVersion(rs.getInt("version"));
				account.setCreatedBy(rs.getInt("created_by"));
				account.setCreatedName(rs.getString("created_name"));
				Timestamp createdTimestamp = rs.getTimestamp("created_ts");
				if (createdTimestamp != null)
					account.setCreatedTs(createdTimestamp.toLocalDateTime());
				account.setLastModifiedBy(rs.getInt("last_modified_by"));
				account.setLastModifiedName(rs.getString("last_modified_name"));
				Timestamp modifiedTime = rs.getTimestamp("last_modified_ts");
				if (modifiedTime != null)
					account.setLastModifiedTs(modifiedTime.toLocalDateTime());
				list.add(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				log.info("Closing the connection in search() ");
				connection.close();
			}
		}
		return list;
	}
	
	public List<Account> findByAccountName(String accountName) throws Exception {
		Connection connection = null;
		List<Account> list = new ArrayList<>();
		try {
			connection = getConnection();
			log.info(SQLConstants.SQL_GET_NAME);
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_GET_NAME);
			
			preparedStatement.setString(1, accountName);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Account account = new Account();
				account.setId(rs.getInt("id"));
				account.setAccountName(rs.getString("account_name"));
				account.setAccountType(rs.getString("account_type"));
				account.setAmount(rs.getBigDecimal("amount"));
				account.setVersion(rs.getInt("version"));
				account.setCreatedBy(rs.getInt("created_by"));
				account.setCreatedName(rs.getString("created_name"));
				Timestamp createdTimestamp = rs.getTimestamp("created_ts");
				if (createdTimestamp != null)
					account.setCreatedTs(createdTimestamp.toLocalDateTime());
				account.setLastModifiedBy(rs.getInt("last_modified_by"));
				account.setLastModifiedName(rs.getString("last_modified_name"));
				Timestamp modifiedTime = rs.getTimestamp("last_modified_ts");
				if (modifiedTime != null)
					account.setLastModifiedTs(modifiedTime.toLocalDateTime());
				list.add(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(connection != null) {
				log.info("Closing the connection in findByAccountName() ");
				connection.close();
			}
		}
		return list;
	}
	
	
	public String deleteAccount(Integer id) throws Exception {
		String status = "success";
		Connection connection = null;
		try {
			connection = getConnection();
			log.info(SQLConstants.SQL_DELETE);
			PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_DELETE);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				log.info("Closing the connection in deleteAccount() ");
				connection.close();
			}
		}
		return status;
	}
	
	protected Connection getConnection() throws Exception {
		Connection connection = null;
		String driverClassName  = dbProperties.getDriverClassName();
		String url 				= dbProperties.getUrl();
		String username 		= dbProperties.getUsername();
		String password			= dbProperties.getPassword(); 
		
		Class.forName(driverClassName);
		connection = DriverManager.getConnection(url, username, password);
		return connection;
	}
}
