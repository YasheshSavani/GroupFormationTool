package com.csci5308.groupme.student.service;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.student.model.Student;
import com.csci5308.groupme.user.model.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class StudentServiceImpl implements StudentService {

    Student student;
    Connection connection;
    CallableStatement statement;
    DatabaseProperties databaseProperties;

    public StudentServiceImpl(Student student) {
        this.student = student;
        this.databaseProperties = new DatabaseProperties();
    }

    @Override
    public boolean isNotEnrolled() {
        try {
            boolean status = false;
            connection = DriverManager.getConnection(
                    databaseProperties.getDbURL(), databaseProperties.getDbUserName(), databaseProperties.getDbPassword());
            statement = connection.prepareCall("{call GET_STUDENT(?)}");
            if (statement != null) {
                statement.setString("procbannerid", student.getBannerID());
                if (statement.execute()) {
                    status = !statement.getResultSet().next();
                }
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            return status;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean enrol(User user) {
        try {
            connection = DriverManager.getConnection(
                    databaseProperties.getDbURL(), databaseProperties.getDbUserName(), databaseProperties.getDbPassword());
            statement = connection.prepareCall("{call INSERT_STUDENT(?, ?, ?, ?, ?, ?)}");
            if (statement != null) {
                statement.setString("procusername", user.getUserName());
                statement.setString("procfirstname", user.getFirstName());
                statement.setString("proclastname", user.getLastName());
                statement.setString("procemail", user.getEmail());
                statement.setString("procpassword", user.getPassword());
                statement.setString("procbannerid", student.getBannerID());
                statement.executeUpdate();
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
