package com.csci5308.groupme.instructor.service;

import com.csci5308.groupme.instructor.dao.InstructorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorDAO instructorDAO;

    @Override
    public String findByTAEmailId(String emailId, String courseCode) throws Exception {

        String assignmentConfirmation = null;

        try {
            assignmentConfirmation = instructorDAO.findByTAEmailId(emailId, courseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return assignmentConfirmation;


    }
}
