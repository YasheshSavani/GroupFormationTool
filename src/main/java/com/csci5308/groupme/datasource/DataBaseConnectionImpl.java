package com.csci5308.groupme.datasource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnectionImpl implements DataBaseConnection {

    private String dbURL;
    private String dbUserName;
    private String dbPassword;
    private Connection connection;

    public DataBaseConnectionImpl() {
        try {
            Properties applicationProperties = new Properties();
            Reader propertiesReader = new BufferedReader(new FileReader("src/main/resources/application.properties"));
            applicationProperties.load(propertiesReader);
            dbURL = applicationProperties.getProperty("spring.datasource.url");
            dbUserName = applicationProperties.getProperty("spring.datasource.username");
            dbPassword = applicationProperties.getProperty("spring.datasource.password");
            connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (this.connection.isClosed()){
            this.connection = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
        }
        return this.connection;
    }

    @Override
    public void closeConnection() throws SQLException {
        this.connection.close();
    }

}
