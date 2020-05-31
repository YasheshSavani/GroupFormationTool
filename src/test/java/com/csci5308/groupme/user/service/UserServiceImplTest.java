package com.csci5308.groupme.user.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserServiceImplTest {

    UserMock userMock = new UserMock();
    UserService userService = new UserServiceImpl();

    @Test
    public void sendCredentialsTest() {
        boolean status = userService.sendCredentials(userMock.getUser());
        assertTrue(status);
    }

}
