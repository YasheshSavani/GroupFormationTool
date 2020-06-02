package com.csci5308.groupme.user.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import errors.EditCodes;
import util.RandomUtil;


@SpringBootTest
public class UserDaoTest {

	Logger logger = LoggerFactory.getLogger(UserDaoTest.class);
		
	@Autowired
	UserDao userDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void findByUserNameTest() {
		String userName = "testuser1";
		User user = userDao.findByUserName(userName);
		assertNotNull(user);
		assertEquals(userName, user.getUserName());
		assertTrue(!user.getRoles().isEmpty());
		assertEquals(2, user.getRoles().size());
	}
	
	@Test
	public void UserDoesNotExistTest() {
		String userName = "lorem_ipsum234";
		String email = "lorem_ipsum@gmail.com";
		User user = userDao.findByUserName(userName);
		assertNull(user);
		user = userDao.findByEmail(email);
		assertNull(user);
	}
	
	@Test
	public void finByEmailTest() {
		String email = "user.test1@gmail.com";
		User user = userDao.findByEmail(email);
		assertNotNull(user);
		assertEquals(email, user.getEmail());
		assertTrue(!user.getRoles().isEmpty());
		assertEquals(2, user.getRoles().size());
	}
	
	@Test
	public void finByNameTest() {
		String firstName = "User";
		String lastName = "Test";
		List<User> users = userDao.findByName(firstName, lastName);
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
	public void duplicateUserNameTest() {
		User user = new User();
		user.setUserName("abhinav78");
		user.setFirstName("Test");
		user.setLastName("User");
		user.setEmail("test_");
		user.setPassword(passwordEncoder.encode("testpassword"));
		int status = userDao.save(user);
		assertEquals(EditCodes.USERNAME_EXISTS, status);
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
