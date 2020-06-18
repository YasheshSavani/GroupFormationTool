package com.csci5308.groupme.teaching_assistant.service;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.teaching_assistant.dao.TeachingAssistantDao;

public class TeachingAssistantServiceImpl implements TeachingAssistantService{
	
private TeachingAssistantDao teachingAssistantDao;

	@Override
	public String findByTAEmailId(String emailId, String courseCode) throws Exception {
		teachingAssistantDao = SystemConfig.instance().getTeachingAssistantDao();
		String assignmentConfirmation = null;
		try {
			teachingAssistantDao = SystemConfig.instance().getTeachingAssistantDao();
			assignmentConfirmation = teachingAssistantDao.findByTAEmailId(emailId, courseCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return assignmentConfirmation;
	}
}
