package com.csci5308.groupme.user.model;

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
        assertEquals("kharechakaran67@gmail.com", user.getEmail());
    }

    @Test
    public void getPassword(){
        assertEquals("B00xxxxxx", user.getPassword());
    }

    @Test
    public void setUserNameTest(){
        User user = new User();
        user.setUserName("kharechaB00");
        assertEquals("kharechaB00", user.getUserName());
    }

    @Test
    public void setFirstNameTest(){
        User user = new User();
        user.setFirstName("Karan");
        assertEquals("Karan", user.getFirstName());
    }

    @Test
    public void setLastnameTest(){
        User user = new User();
        user.setLastName("Kharecha");
        assertEquals("Kharecha", user.getLastName());
    }


    @Test
    public void setEmailaddressTest(){
        User user = new User();
        user.setEmail("kharechakaran67@gmail.com");
        assertEquals("kharechakaran67@gmail.com", user.getEmail());
    }

    @Test
    public void setPassword(){
        User user = new User();
        user.setPassword("B00xxxxxx");
        assertEquals("B00xxxxxx", user.getPassword());
    }

}