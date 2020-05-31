package com.csci5308.groupme.user.dao;

import java.util.List;

import com.csci5308.groupme.user.model.User;

public interface UserDao {

    public List<User> findAll();
	
	public User findByUserName(String userName);
	
	public User findByEmail(String email);
	
	public List<User> findByName(String firstName, String lastName);
	
	public boolean save(User user);
	
	public boolean update(User user);
	
	public boolean remove(User user);
	
}
