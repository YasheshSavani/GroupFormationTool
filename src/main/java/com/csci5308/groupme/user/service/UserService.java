package com.csci5308.groupme.user.service;
import com.csci5308.groupme.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    public User getByUserName(String userName);

    public User getByEmail(String email);

    public int register(User user);
    
    public int addRole(String userName, String roleName);
    
    public boolean delete(User user);

    public boolean sendCredentials(User user);

	public int updatePassword(String email, String newPassword);

}

