package com.csci5308.groupme.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.csci5308.groupme.user.dao.UserDao;
import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.model.UserAuthDetails;

import errors.EditCodes;

@Service
public class UserServiceImpl implements UserService {

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
		
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		return new UserAuthDetails(user);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByUserName(String userName) {
		User user = userDao.findByUserName(userName);
	    return user;
	}

	@Override
	public User getByEmail(String email) {
		User user = userDao.findByEmail(email);
		return user;
	}

	@Override
	public List<User> getByName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int register(User user) {
        int insertStatus = 0;
        String rawPassword = user.getPassword();
		if(userDao.findByEmail(user.getEmail()) != null) 
			return EditCodes.EMAIL_EXISTS;
		logger.info(user.getUserName());
		user.setPassword(passwordEncoder.encode(rawPassword));
	    insertStatus = userDao.save(user);
		return insertStatus;
	}

	@Override
	public boolean updateRole(User user, String oldRole, String newRole) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
