package com.tech.mkblogs.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@EntityListeners(AuditingEntityListener.class)
public class Account{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "account_name")
	private String accountName;
	
	@Column(name = "account_type")
	private String accountType;

	@Column(name = "amount")
	private BigDecimal amount;
	
	@Column(name = "created_by")
	@CreatedBy
	private Integer createdBy;
	
	@Column(name = "created_name")
	private String createdName;
	
	@CreatedDate
	@Column(name = "created_ts")
	private LocalDateTime createdTs;
	
	@Column(name = "last_modified_by")
	@LastModifiedBy
	private Integer lastModifiedBy;
	
	@Column(name = "last_modified_name")
	private String lastModifiedName;
	
	@LastModifiedDate
	@Column(name = "last_modified_ts")
	private LocalDateTime lastModifiedTs;
	
	@Column(name = "version")
	@Version
	private Integer version;
}
