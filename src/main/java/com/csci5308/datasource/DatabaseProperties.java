package com.csci5308.datasource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseProperties {

    private String dbURL;
    private String dbUserName;
    private String dbPassword;

    public DatabaseProperties() {
        try {
            Properties databaseProperties = new Properties();
            Reader propertiesReader = new BufferedReader(new FileReader("src/main/resources/application.properties"));
            databaseProperties.load(propertiesReader);
            dbURL = databaseProperties.getProperty("spring.datasource.url");
            dbUserName = databaseProperties.getProperty("spring.datasource.username");
            dbPassword = databaseProperties.getProperty("spring.datasource.password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDbURL() {
        return dbURL;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }
}
