package com.csci5308.groupme.teaching_assistant.service;

import com.csci5308.groupme.teaching_assistant.dao.TeachingAssistantDao;
import com.csci5308.groupme.teaching_assistant.dao.TeachingAssistantDaoMock;

public class TeachingAssistantServiceMock implements TeachingAssistantService {

    @Override
    public String findByTAEmailId(String emailId, String courseCode) throws Exception {
        TeachingAssistantDao teachingAssistantDao = new TeachingAssistantDaoMock();
        return teachingAssistantDao.findByTAEmailId(emailId, courseCode);
    }

}
