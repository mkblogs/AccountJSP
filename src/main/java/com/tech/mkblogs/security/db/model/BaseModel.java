package com.tech.mkblogs.security.db.model;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

/**
 * All model classes are suppose to implement from this base model class.
 * @author 
 * @version 2.0
 * 
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseModel {
	
	@CreatedDate
	protected LocalDateTime createdTs;
	
	@CreatedBy
	protected Integer createdBy;
	
	@LastModifiedDate
	protected LocalDateTime lastModifiedTs;
	
	@LastModifiedBy
	protected Integer lastModifiedBy;
	
	@Version
	protected Integer version;
	
}
