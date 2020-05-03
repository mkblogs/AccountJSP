package com.tech.mkblogs.security.db.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class User extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String loginName;
	private String password;
	private LocalDateTime lastLogin;
	private Boolean accountExpired; 
	private Boolean accountLocked;
	private Boolean credentialsExpired;
	private Boolean enabled;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Authorities> authorities = new ArrayList<Authorities>();
	
	public Authorities addAuthorities(Authorities authority) {
		getAuthorities().add(authority);
		authority.setUser(this);
		return authority;
	}
	
	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Settings settings;
	
}
