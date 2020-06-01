package com.csci5308.groupme.student.service;

import com.csci5308.datasource.ProcedureManager;
import com.csci5308.groupme.student.model.Student;
import com.csci5308.groupme.user.model.User;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class StudentServiceImpl implements StudentService {

    Student student;
    ProcedureManager procedureManager;
    CallableStatement statement;

    public StudentServiceImpl(Student student) {
        this.student = student;
        this.procedureManager = new ProcedureManager();
    }

    @Override
    public boolean isNotEnrolled() throws SQLException {
        statement = procedureManager.getStoredProcedureStatement("GET_STUDENT(?)");
        statement.setString("procbannerid", student.getBannerID());
        if (statement.execute()) {
            boolean status = !statement.getResultSet().next();
            statement.close();
            return status;
        }
        return false;
    }

    @Override
    public boolean enrol(User user) {
        try {
            statement = procedureManager.getStoredProcedureStatement("INSERT_STUDENT(?, ?, ?, ?, ?, ?)");
            statement.setString("procusername", user.getUserName());
            statement.setString("procfirstname", user.getFirstName());
            statement.setString("proclastname", user.getLastName());
            statement.setString("procemail", user.getEmail());
            statement.setString("procpassword", user.getPassword());
            statement.setString("procbannerid", student.getBannerID());
            statement.executeUpdate();
            statement.close();
            return true;
        } catch (SQLException exception) {
            return false;
        }
    }
}
