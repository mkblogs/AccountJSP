package com.tech.mkblogs.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.tech.mkblogs.validation.AlreadyExistsValidator.AlreadyExists;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AlreadyExists(message = "{account.name.alreadyexists}")
public class AccountDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer accountId;
	
	@NotEmpty(message = "{account.name.notempty}")
	@Size(min = 2,max = 50,message = "{account.name.size}")
	private String name;
	
	@NotEmpty(message = "{account.type.notempty}")
	@Size(min = 2,max = 50,message = "{account.type.size}")
	private String type;
	
	@NotNull(message = "{account.amount.notnull}")
	@DecimalMin(value = "99.00",inclusive = false,message = "{account.amount.min}")
    @Digits(integer=5, fraction=2,message = "{account.amount.decimal}")
	private BigDecimal amount;
	
	private Integer createdBy;
	private String createdName;
	private LocalDateTime createdTs;
	private Integer lastModifiedBy;
	private String lastModifiedName;
	private LocalDateTime lastModifiedTs;
	private Integer version;
}