package com.csci5308.groupme.instructor.service;

import com.csci5308.groupme.teaching_assistant.model.TeachingAssistant;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InstructorService {

    String findByTAEmailId(String emailId, String courseCode) throws Exception;
}
