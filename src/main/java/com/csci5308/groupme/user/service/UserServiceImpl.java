package com.csci5308.groupme.user.service;

import com.csci5308.groupme.user.model.User;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

public class UserServiceImpl implements UserService {

    private final JavaMailSender emailSender;
    private final SendEmailConfiguration emailConfiguration;

    public UserServiceImpl() {
        emailConfiguration = new SendEmailConfigurationImpl();
        this.emailSender = emailConfiguration.initiateEmailSender();
    }

    @Override
    public boolean sendCredentials(User user) {
        try {
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
            e.printStackTrace();
            return false;
        }
    }
}
