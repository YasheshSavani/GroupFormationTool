package com.csci5308.groupme.teaching_assistant.service;

public interface TeachingAssistantService {

	String findByTAEmailId(String emailId, String courseCode) throws Exception;

}
