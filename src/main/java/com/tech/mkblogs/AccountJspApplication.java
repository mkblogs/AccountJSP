package com.tech.mkblogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AccountJspApplication  {

	public static void main(String[] args) {
		SpringApplication.run(AccountJspApplication.class, args);
	}
}
