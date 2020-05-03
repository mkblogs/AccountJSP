package com.tech.mkblogs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsDTO {
	
	private Integer id;
	private String connectionType;
	private String primaryKeyGenerationType;
	private String authenticationType;
	private Boolean authenticationEncrypted;
	
	private String message;
}
