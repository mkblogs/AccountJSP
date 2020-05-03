package com.tech.mkblogs.security.inmemory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Component;

import com.tech.mkblogs.security.config.AccountAuthConfig;

@Component
@SuppressWarnings("deprecation")
public class InMemoryAccountUserDetails {

	@Autowired
	private AccountAuthConfig authConfig;

	public void configureInMemoryUserVerification(AuthenticationManagerBuilder auth) throws Exception {
		if (!authConfig.getEncyprted()) {
			auth.inMemoryAuthentication().withUser("user").password("{noop}user@123").roles("USER");
			auth.inMemoryAuthentication().withUser("admin").password("{noop}admin@123").roles("ADMIN");
			auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance());
		} else {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			auth.inMemoryAuthentication().passwordEncoder(encoder).withUser("user")
					.password(encoder.encode("admin@123")).roles("USER");
			auth.inMemoryAuthentication().passwordEncoder(encoder).withUser("admin")
					.password(encoder.encode("admin@123")).roles("ADMIN");
		}
	}

}
