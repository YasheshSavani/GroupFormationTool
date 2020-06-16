package com.csci5308.groupme.user.service;

import com.csci5308.groupme.user.dao.UserDao;
import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.model.UserAuthDetails;
import errors.EditCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public int register(User user) {
        int insertStatus = 0;
        String rawPassword = user.getPassword();
        if (null != userDao.findByEmail(user.getEmail())) {
            insertStatus = EditCodes.EMAIL_EXISTS;
        } else {
            logger.info("Encoding the password for {}", user.getUserName());
            user.setPassword(passwordEncoder.encode(rawPassword));
            insertStatus = userDao.save(user);
        }
        return insertStatus;
    }

    @Override
    public int addRole(String userName, String roleName) {
        int status = userDao.addRole(userName, roleName);
        return status;
    }

    @Override
    public int updatePassword(String email, String newPassword) {
        int updateStatus = 0;
        User user = this.getByEmail(email);
        if (null == user)
            updateStatus = EditCodes.EMAIL_DOES_NOT_EXIST;
        else {
            user.setPassword(passwordEncoder.encode(newPassword));
            updateStatus = userDao.update(user);
            if (updateStatus == 1)
                logger.info("Password updated.....");
        }
        return updateStatus;
    }

}
