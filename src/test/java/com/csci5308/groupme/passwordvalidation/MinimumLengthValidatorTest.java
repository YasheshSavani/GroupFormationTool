package com.csci5308.groupme.passwordvalidation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MinimumLengthValidatorTest {
	
	@Test
	public void isValid() 
	{
		int minimumLength = 5;
		String password = "passed";
		assertThat(password.length() >= minimumLength).isTrue();
		password = "fail";
		assertThat(password.length() >= minimumLength).isFalse();
	}
}
