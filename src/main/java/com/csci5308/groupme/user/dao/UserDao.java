package com.csci5308.groupme.user.dao;

import java.util.List;

import com.csci5308.groupme.user.model.User;

public interface UserDao {

    public List<User> findAll();
	
	public User findByUserName(String userName);
	
	public User findByEmail(String email);
	
	public List<User> findByName(String firstName, String lastName);
	
	public int save(User user);
	
	public int addRole(String userName, String roleName);
	
	public boolean updateRole(User user, String oldRole, String newRole);
		
	public boolean remove(User user);
	
}
