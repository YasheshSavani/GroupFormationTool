package com.csci5308.groupme.teaching_assistant.dao;

public class TeachingAssistantDaoMock implements TeachingAssistantDao {

    @Override
    public String findByTAEmailId(String emailId, String courseCode) throws Exception {
        String emailMock = "testta@gmail.com";
        String courseCodeMock = "TSCI0000";
        if (emailId.equals(emailMock) && courseCode.equals(courseCodeMock))
            return "True";
        else
            return "No User account available";
    }

}
