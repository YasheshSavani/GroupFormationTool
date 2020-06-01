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
import org.springframework.security.crypto.password.PasswordEncoder;

import com.csci5308.groupme.user.model.User;

import util.RandomUtil;


@SpringBootTest
public class UserDaoImplTest {

	Logger logger = LoggerFactory.getLogger(UserDaoImplTest.class);
		
	@Autowired
	UserDao userDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void findByUserNameTest() {
		User user = new User();
		String userName = "testuser1";
		user = userDao.findByUserName(userName);
		assertNotNull(user);
		assertEquals(userName, user.getUserName());
		assertTrue(!user.getRoles().isEmpty());
		assertEquals(2, user.getRoles().size());
	}
	
	@Test
	public void finByEmailTest() {
		User user = new User();
		String email = "user.test1@gmail.com";
		user = userDao.findByEmail(email);
		assertNotNull(user);
		assertEquals(email, user.getEmail());
		assertTrue(!user.getRoles().isEmpty());
		assertEquals(2, user.getRoles().size());
	}
	
	@Test
	public void finByNameTest() {
		List<User> users = new ArrayList<User>();
		String firstName = "User";
		String lastName = "Test";
		users = userDao.findByName(firstName, lastName);
		assertTrue(!users.isEmpty());
		assertEquals(firstName + lastName, users.get(0).getFirstName() + users.get(0).getLastName());
	}
	
	@Test
	public void saveTest() {
		User user = new User();
		String randomString = RandomUtil.alphaNumeric(5);
		user.setUserName("testuser_" + randomString);
		user.setFirstName("Test");
		user.setLastName("User" + randomString);
		user.setEmail("test_" + randomString + "@gmail.com");
		user.setPassword(passwordEncoder.encode("testpassword"));
		int rowCount = userDao.save(user);
		assertEquals(1, rowCount);
	}
	
	@Test
	public void duplicateEntryTest() {
		User user = new User();
		user.setUserName("abhinav78");
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("test_");
		user.setPassword(passwordEncoder.encode("testpassword"));
		int rowCount = userDao.save(user);
		assertEquals(1, rowCount);
	}
	
//	@Test
//	public void addRoleTest() {
//		User user = new User();
//		String randomString = RandomUtil.alphaNumeric(5);
//		user.setUserName("testuser_" + randomString);
//		user.setFirstName("Test");
//		user.setLastName("User" + randomString);
//		user.setEmail("test_" + randomString + "@gmail.com");
//		user.setPassword(passwordEncoder.encode("testpassword"));
//		int rowCount = userDao.save(user);
//		assertEquals(1, rowCount);
//	}
	
}
