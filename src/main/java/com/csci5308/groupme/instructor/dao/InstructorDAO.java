package com.csci5308.groupme.instructor.dao;

import com.csci5308.groupme.teaching_assistant.model.TeachingAssistant;

import java.util.List;

public interface InstructorDAO {

    String findByTAEmailId(String emailId, String courseCodes) throws Exception;
}
