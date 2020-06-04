package com.csci5308.groupme.user.service;
import com.csci5308.groupme.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public List<User> getAll();

    public User getByUserName(String userName);

    public User getByEmail(String email);

    public List<User> getByName(String firstName, String lastName);

    public int register(User user);

    public boolean updateRole(User user, String oldRole, String newRole);
    
    public boolean delete(User user);

    public boolean sendCredentials(User user);

	public int updatePassword(String email, String newPassword);

}

