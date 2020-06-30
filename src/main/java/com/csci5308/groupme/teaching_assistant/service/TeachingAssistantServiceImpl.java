package com.csci5308.groupme.teaching_assistant.service;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.teaching_assistant.dao.TeachingAssistantDao;

public class TeachingAssistantServiceImpl implements TeachingAssistantService {

    private TeachingAssistantDao teachingAssistantDao;

    @Override
    public int assignTAToCourse(String emailId, String courseCode) throws Exception {
        teachingAssistantDao = SystemConfig.instance().getTeachingAssistantDao();
        int assignmentConfirmation = 0;
        try {
            teachingAssistantDao = SystemConfig.instance().getTeachingAssistantDao();
            assignmentConfirmation = teachingAssistantDao.createTAForCourse(emailId, courseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignmentConfirmation;
    }
}
