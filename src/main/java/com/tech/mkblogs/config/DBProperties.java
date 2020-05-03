package com.tech.mkblogs.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class DBProperties {

	String url;
	String username;
	String password;
	String driverClassName;
	
}
