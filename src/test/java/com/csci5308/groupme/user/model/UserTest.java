package com.csci5308.groupme.user.model;

import com.csci5308.groupme.user.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user = new UserMock().getUser();

    @Test
    public void getUserNameTest(){
        assertEquals("kharechaB00", user.getUserName());
    }

    @Test
    public void getFirstNameTest(){
        assertEquals("Karan", user.getFirstName());
    }

    @Test
    public void getLastNameTest(){
        assertEquals("Kharecha", user.getLastName());
    }

    @Test
    public void getEmailaddressTest(){
        assertEquals("kharechakaran67@gmail.com", user.getEmailaddress());
    }

    @Test
    public void getPassword(){
        assertEquals("B00xxxxxx", user.getPassword());
    }

}