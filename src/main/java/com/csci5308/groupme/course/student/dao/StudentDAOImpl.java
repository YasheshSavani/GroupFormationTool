package com.csci5308.groupme.course.student.dao;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.course.student.model.Student;
import com.csci5308.groupme.user.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class StudentDAOImpl implements StudentDAO {

    Student student;
    Connection connection = null;
    CallableStatement statement = null;
    DatabaseProperties databaseProperties;
    BCryptPasswordEncoder passwordEncoder;

    public StudentDAOImpl(Student student) {
        this.student = student;
        this.databaseProperties = new DatabaseProperties();
    }

    @Override
    public boolean isNotEnrolled() {
        try {
            boolean status = false;
            connection = DriverManager.getConnection(databaseProperties.getDbURL(), databaseProperties.getDbUserName(),
                    databaseProperties.getDbPassword());
            statement = connection.prepareCall("{call GET_STUDENT(?)}");
            if (statement != null) {
                statement.setString("procbannerid", student.getBannerID());
                if (statement.execute()) {
                    status = !statement.getResultSet().next();
                }
            }
            return status;
        } catch (Exception e) {
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean enrol(User user, String instructorID, String courseID) {
        try {
            passwordEncoder = new BCryptPasswordEncoder(10);
            connection = DriverManager.getConnection(databaseProperties.getDbURL(), databaseProperties.getDbUserName(),
                    databaseProperties.getDbPassword());
            statement = connection.prepareCall("{call INSERT_STUDENT(?, ?, ?, ?, ?, ?, ?, ?)}");
            if (statement != null) {
                statement.setString("procusername", user.getUserName());
                statement.setString("procfirstname", user.getFirstName());
                statement.setString("proclastname", user.getLastName());
                statement.setString("procemail", user.getEmail());
                statement.setString("procpassword", passwordEncoder.encode(user.getPassword()));
                statement.setString("procbannerid", student.getBannerID());
                statement.setString("procinstructorid", instructorID);
                statement.setString("proccoursecode", courseID);
                statement.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
