package com.csci5308.groupme.user.service;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
		if (userDao.findByEmail(user.getEmail()) != null)
			insertStatus = EditCodes.EMAIL_EXISTS;
		else {
		logger.info("Encoding the password for {}", user.getUserName());
		user.setPassword(passwordEncoder.encode(rawPassword));
		insertStatus = userDao.save(user);
		}
		return insertStatus;
	}

	@Override
	public boolean updateRole(User user, String oldRole, String newRole) {
		// TODO Auto-generated method stub
		return false;
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
		if(updateStatus == 1)
			logger.info("Password updated.....");
		}
		return updateStatus;
	}

	@Override
	public boolean sendCredentials(User user) {
		try {
            SendEmailConfiguration emailConfiguration = new SendEmailConfigurationImpl();
            JavaMailSender emailSender = emailConfiguration.initiateEmailSender();
            String subject = "Enrolment Email";
            String content = "If you have received this email, then you are successfully enrolled as a student.\n" +
                    "Your login credentials are:\n" +
                    "Username: " + user.getUserName() + "\n" +
                    "Password: " + user.getPassword();

            MimeMessage emailContent = emailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(emailContent, false);
            messageHelper.setTo(user.getEmail());
            messageHelper.setSubject(subject);
            messageHelper.setText(content);

            emailSender.send(emailContent);
            return true;

        } catch (Exception e) {
            return false;
        }
	}

	@Override
	public boolean delete(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
