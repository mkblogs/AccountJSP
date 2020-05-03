package com.tech.mkblogs;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountJspApplicationTests {

	@Test
	void contextLoads() {
		LocalDateTime lt = LocalDateTime.now(); 
		System.out.println(lt.toLocalTime());
		System.out.println(LocalDateTime.now());
	}

}
