package com.csci5308.groupme.teaching_assistant.dao;

public interface TeachingAssistantDao {

	String findByTAEmailId(String emailId, String courseCode) throws Exception;

}
