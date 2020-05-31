package com.csci5308.groupme.user.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.csci5308.groupme.user.model.User;

public interface UserService extends UserDetailsService{

	public List<User> getAll();
	
	public User getByUserName(String userName);
	
	public User getByEmail(String email);
	
	public List<User> getByName(String firstName, String lastName);
	
	public boolean create(User user);
	
	public boolean update(User user);
	
	public boolean delete(User user);
		
}
