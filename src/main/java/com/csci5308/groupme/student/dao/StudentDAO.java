package com.csci5308.groupme.student.dao;

import com.csci5308.groupme.user.model.User;

import java.sql.SQLException;

public interface StudentDAO {

    boolean isNotEnrolled() throws SQLException;

    boolean enrol(User user, String instructorID, String courseID) throws SQLException;
}
