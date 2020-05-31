package com.csci5308.groupme.student.service;

import com.csci5308.groupme.user.service.User;

import java.sql.SQLException;

public interface StudentService {

    boolean isNotEnrolled() throws SQLException;

    boolean enrol(User user) throws SQLException;

}
