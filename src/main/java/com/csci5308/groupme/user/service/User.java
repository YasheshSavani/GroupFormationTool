package com.csci5308.groupme.user.service;

public class User {

    private String userName;
    private String firstName;
    private String lastName;
    private String emailaddress;
    private String password;

    public User(String userName, String lastName, String firstName, String emailaddress, String password) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.emailaddress = emailaddress;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
