package com.csci5308.groupme.teaching_assistant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class TeachingAssistantServiceTest {

	
	private TeachingAssistantService taService;

	public TeachingAssistantServiceTest() 
	{
		taService= new TeachingAssistantServiceMock();
	}

	@Test
	void findByTAEmailId() throws Exception {
		String email = "testta@gmail.com";
		String courseCode = "TSCI0000";
		String assignmentConfirmation = taService.findByTAEmailId(email, courseCode);
		assertEquals("True", assignmentConfirmation);
	}

}
