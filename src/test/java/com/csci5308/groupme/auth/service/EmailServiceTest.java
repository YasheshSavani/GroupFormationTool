package com.csci5308.groupme.auth.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.csci5308.groupme.auth.AuthConstants;

@SpringBootTest
public class EmailServiceTest {

	@Autowired
	EmailService emailService;
	
	@Test
	public void findByUserNameTest() {
		boolean status = emailService.sendPasswordRecovery(AuthConstants.TEST_EMAIL);
		assertTrue(status);
	}
	
}
