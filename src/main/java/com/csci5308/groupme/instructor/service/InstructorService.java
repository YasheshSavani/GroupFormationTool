package com.csci5308.groupme.instructor.service;

import com.csci5308.groupme.teaching_assistant.model.TeachingAssistant;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InstructorService {

    String findByTAEmailId(String emailId, String courseCode) throws Exception;
    public boolean upload(MultipartFile file, String instructorID, String courseCode);

}
