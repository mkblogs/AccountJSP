package com.tech.mkblogs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unchecked")
public class MyServiceFactory <T extends AccountService> {

	@Autowired
	private ApplicationContext context;
	
	public T getInstance(@NonNull final String name) {
		T t = null;
		if(context.containsBean(name)) {
			t = (T) context.getBean(name);
		}else {
			throw new RuntimeException("Unknown service name: " + name);
		}
        return t;
    }
}
