package com.csci5308.groupme.auth.service;

import javax.mail.internet.MimeMessage;

import com.csci5308.groupme.user.service.SendEmailConfiguration;
import com.csci5308.groupme.user.service.SendEmailConfigurationImpl;
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
		try {
			SendEmailConfiguration emailConfiguration = new SendEmailConfigurationImpl();
			JavaMailSender emailSender = emailConfiguration.initiateEmailSender();
			String subject = "Enrolment Email";
			String content = "If you have received this email, then you are successfully enrolled as a student.\n"
					+ "Your login credentials are:\n" + "Username: " + user.getUserName() + "\n" + "Password: "
					+ user.getPassword();
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
