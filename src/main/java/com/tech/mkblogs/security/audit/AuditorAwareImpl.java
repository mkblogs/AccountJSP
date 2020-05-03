package com.tech.mkblogs.security.audit;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.tech.mkblogs.security.db.dto.UserSessionDTO;
import com.tech.mkblogs.session.operations.SessionOperations;

@Component
public class AuditorAwareImpl implements AuditorAware<Integer>{
	
	@Autowired
	SessionOperations sessionOperations;
	
	@Override
	public Optional<Integer> getCurrentAuditor() {
		UserSessionDTO userInfo =  sessionOperations.fetchSession();
		return  Optional.of(userInfo.getId()); 
	}
}
