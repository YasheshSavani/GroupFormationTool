package com.csci5308.groupme.user.service;

import org.springframework.mail.javamail.JavaMailSender;

public interface SendEmailConfiguration {

    JavaMailSender initiateEmailSender();

}
