package com.csci5308.groupme.course.courseadmin.teaching_assistant.service;

import com.csci5308.groupme.course.courseadmin.teaching_assistant.dao.TeachingAssistantDao;

public class TeachingAssistantServiceImpl implements TeachingAssistantService {

    private TeachingAssistantDao teachingAssistantDao;
    
    public TeachingAssistantServiceImpl(TeachingAssistantDao teachingAssistantDao) {
        this.teachingAssistantDao = teachingAssistantDao;
    }

    @Override
    public int assignTAToCourse(String emailId, String courseCode) throws Exception {
        int assignmentConfirmation = 0;
        try {
            assignmentConfirmation = teachingAssistantDao.createTAForCourse(emailId, courseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignmentConfirmation;
    }
}
