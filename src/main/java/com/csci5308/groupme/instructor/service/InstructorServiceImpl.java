package com.csci5308.groupme.instructor.service;

import com.csci5308.groupme.instructor.dao.InstructorDAO;
import com.csci5308.groupme.teaching_assistant.model.TeachingAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    @Autowired
    InstructorDAO instructorDAO;

    @Override
    public List<TeachingAssistant> findByTAEmailId(String emailId) throws Exception {

        List<TeachingAssistant> informationOfTA = null;

        try {
            informationOfTA = instructorDAO.findByTAEmailId(emailId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return informationOfTA;



    }
}
