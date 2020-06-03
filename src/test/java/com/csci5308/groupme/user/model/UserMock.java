package com.csci5308.groupme.user.model;

public class UserMock {

    User user;

    public UserMock() {
        user = new User();
        user.setUserName("kharechaB00");
        user.setLastName("Kharecha");
        user.setFirstName("Karan");
        user.setEmail("kharechakaran67@gmail.com");
        user.setPassword("B00xxxxxx");
    }

    public User getUser() {
        return user;
    }
}
