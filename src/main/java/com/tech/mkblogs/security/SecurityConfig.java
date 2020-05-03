package com.tech.mkblogs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	LoginSuccessHandler successHandler;
	
	@Autowired
	AccountAuthenticateManager manager;
   
  // Securing the urls and allowing role-based access to these urls.
  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()      
      .antMatchers("/").permitAll();

      loginCode(http);
      sessionCode(http);
      
      http.csrf().disable();
   
  }
  
  private void loginCode(HttpSecurity http) throws Exception {
	  http.formLogin().loginPage("/login")
      .successHandler(successHandler)
      .failureUrl("/login?error=true")
      .permitAll();
  }
  
  private void sessionCode(HttpSecurity http) throws Exception {
	  http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry());
  }

  @Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return manager;
	}
  
  @Bean
  public SessionRegistry sessionRegistry() {
      return new SessionRegistryImpl();
  }

}
