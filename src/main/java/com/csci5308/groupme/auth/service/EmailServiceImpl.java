package com.csci5308.groupme.auth.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.csci5308.groupme.auth.AuthConstants;
import com.csci5308.groupme.user.model.User;

@Service
public class EmailServiceImpl implements EmailService{

	@Autowired
	private JavaMailSender javaMailsender;
		
	@Override
	public boolean sendCredentials(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sendPasswordRecovery(String email) {
		try {
			
			String changePasswordLink = AuthConstants.BASE_URL  
					+ "/resetPassword?secretCode=" + AuthConstants.SECRET_CODE 
					+ "&email=" + email;
			String	subject = "Password Reset Link";
            String content = "Change your password here: \r\n" +
            				  changePasswordLink;
            MimeMessage emailContent = javaMailsender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(emailContent, false);
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);
            messageHelper.setText(content);
            javaMailsender.send(emailContent);
            return true;

        } catch (Exception e) {
            return false;
        }
	}
	
}
