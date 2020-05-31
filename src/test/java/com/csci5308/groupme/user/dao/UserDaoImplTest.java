package com.csci5308.groupme.user.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.csci5308.groupme.user.model.User;


@SpringBootTest
public class UserDaoImplTest {

	Logger logger = LoggerFactory.getLogger(UserDaoImplTest.class);
		
	@Autowired
	UserDao userDao;
	
	@Test
	public void findByUserNameTest() {
		User user = new User();
		String userName = "testuser1";
		user = userDao.findByUserName(userName);
		assertNotNull(user);
		assertEquals(userName, user.getUserName());
	}
	
	@Test
	public void finByEmail() {
		User user = new User();
		String email = "user.test1@gmail.com";
		user = userDao.findByEmail(email);
		assertNotNull(user);
		assertEquals(email, user.getEmail());
	}
	
	@Test
	public void finByName() {
		List<User> users = new ArrayList<User>();
		String firstName = "User";
		String lastName = "Test";
		users = userDao.findByName(firstName, lastName);
		assertTrue(!users.isEmpty());
		assertEquals(firstName + lastName, users.get(0).getFirstName() + users.get(0).getLastName());
	}
	
	
}
