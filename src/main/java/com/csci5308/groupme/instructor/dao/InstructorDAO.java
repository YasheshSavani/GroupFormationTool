package com.csci5308.groupme.instructor.dao;

import com.csci5308.groupme.teaching_assistant.model.TeachingAssistant;

import java.util.List;

public interface InstructorDAO {

    public List<TeachingAssistant> findByTAEmailId(String emailId) throws Exception;
}
