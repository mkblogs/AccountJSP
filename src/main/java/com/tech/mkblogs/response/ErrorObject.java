package com.tech.mkblogs.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ErrorObject {
	private String errorCode;
	private String fieldName;
	private String errorDescription;
	private Timestamp errorCreatedTs;
}
