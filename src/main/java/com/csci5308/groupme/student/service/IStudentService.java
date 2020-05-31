package com.csci5308.groupme.student.service;

import java.sql.SQLException;

public interface IStudentService {

    boolean isNotEnrolled() throws SQLException;

    boolean enrol(User user) throws SQLException;

}
