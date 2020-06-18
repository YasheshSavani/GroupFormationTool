package com.csci5308.groupme.auth.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.Properties;

public class PasswordProperties {
    private int minLength;
    private int maxLength;
    private int minUpperCase;
    private int minLowerCase;
    private int minSpecialChars;
    private String invalidChars;
    private int lastXPasswords;

    public PasswordProperties() {
        try {
            Properties passwordProperties = new Properties();
            Reader propertiesReader = new BufferedReader(new FileReader("src/main/resources/password.properties"));
            passwordProperties.load(propertiesReader);
            minLength = Integer.parseInt(passwordProperties.getProperty("minLength"));
            maxLength = Integer.parseInt(passwordProperties.getProperty("maxLength"));
            minUpperCase = Integer.parseInt(passwordProperties.getProperty("minUpperCase"));
            minLowerCase = Integer.parseInt(passwordProperties.getProperty("minLowerCase"));
            minSpecialChars = Integer.parseInt(passwordProperties.getProperty("minSpecialChars"));
            invalidChars = passwordProperties.getProperty("invalidChars");
            lastXPasswords = Integer.parseInt(passwordProperties.getProperty("lastX"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMinUpperCase() {
        return minUpperCase;
    }

    public void setMinUpperCase(int minUpperCase) {
        this.minUpperCase = minUpperCase;
    }

    public int getMinLowerCase() {
        return minLowerCase;
    }

    public void setMinLowerCase(int minLowercase) {
        this.minLowerCase = minLowercase;
    }

    public int getMinSpecialChars() {
        return minSpecialChars;
    }

    public void setMinSpecialChars(int minSpecialChars) {
        this.minSpecialChars = minSpecialChars;
    }

    public String getInvalidChars() {
        return invalidChars;
    }

    public void setInvalidChars(String invalidChars) {
        this.invalidChars = invalidChars;
    }

    public int getLastXPasswords() {
        return lastXPasswords;
    }

    public void setLastXPasswords(int lastXPasswords) {
        this.lastXPasswords = lastXPasswords;
    }

}
