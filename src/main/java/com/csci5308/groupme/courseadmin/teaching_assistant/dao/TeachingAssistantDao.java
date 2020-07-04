package com.csci5308.groupme.courseadmin.teaching_assistant.dao;

public interface TeachingAssistantDao {

    int createTAForCourse(String emailId, String courseCode) throws Exception;
}
