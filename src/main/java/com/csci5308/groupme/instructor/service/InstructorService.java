package com.csci5308.groupme.instructor.service;

public interface InstructorService {

    String findByTAEmailId(String emailId, String courseCode) throws Exception;
}
