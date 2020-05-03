package com.tech.mkblogs.security.db.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserSessionDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String loginName;
	private String password;
	private LocalDateTime lastLogin;
	private Boolean accountExpired; 
	private Boolean accountLocked;
	private Boolean credentialsExpired;
	private Boolean enabled;
	
	private String authority;
	
	private String connectionType;
	private String primaryKeyGenerationType;
	private String authenticationType;
	private Boolean authenticationEncrypted;

}
