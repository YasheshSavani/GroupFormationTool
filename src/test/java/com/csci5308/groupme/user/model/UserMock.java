package com.csci5308.groupme.user.model;

import com.csci5308.groupme.user.model.User;

public class UserMock {

    User user;

    public UserMock() {
        user = new User("kharechaB00", "Kharecha", "Karan", "kharechakaran67@gmail.com", "B00xxxxxx");
    }

    public User getUser() {
        return user;
    }
}