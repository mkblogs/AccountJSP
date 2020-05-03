package com.tech.mkblogs.constants;

public interface SQLConstants {

	 String SQL_INSERT = "INSERT INTO account "
			+ "(account_name,account_type,amount,created_by,created_name,created_ts,VERSION) "
			+ "VALUES(?,?,?,?,?,?,?)";
	 String SQL_INSERT_NAMED_JDBC = "INSERT INTO account "
				+ "(account_name,account_type,amount,created_by,created_name,created_ts,VERSION) "
				+ "VALUES(:accountName,:accountType,:amount,:createdBy,:createdName,:createdTs,:version)";
	 String SQL_UPDATE = "UPDATE account SET account_name=?,account_type=?,amount=?,version=?,"
			+ "last_modified_by=?,last_modified_name=?,last_modified_ts=? "
			+ " WHERE id=?";
	String SQL_GET_ACCOUNT = "SELECT * FROM account where id = ?";
	String SQL_GET_ALL_DATA = "SELECT * FROM account ";
	String SQL_COUNT_DATA = "SELECT COUNT(*) FROM account ";
	String SQL_DELETE = "DELETE FROM account where id = ?";
	String SQL_GET_AMOUNT = "SELECT * FROM account where amount <= ?";
	String SQL_GET_NAME_AMOUNT = "SELECT * FROM account where account_name like ? and amount <= ?";
	String SQL_GET_ACCOUNT_NAME = "SELECT name FROM account where id = ?";
	String SQL_SEARCH_ACCOUNT_ONE = "SELECT * FROM account where 1 = 1 ";
	String SQL_GET_NAME = "SELECT * FROM account where account_name = ?";
	String SQL_GDELETE = "DELETE FROM ACCOUNT WHERE id = ?";
}
