package com.csci5308.groupme.teaching_assistant.service;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.teaching_assistant.dao.TeachingAssistantDao;

public class TeachingAssistantServiceImpl implements TeachingAssistantService{
	
	TeachingAssistantDao teachingassistantDao = SystemConfig.instance().getTeachingAssistantDao();
	
	@Override
	public String findByTAEmailId(String emailId, String courseCode) throws Exception {

		String assignmentConfirmation = null;

		try {
			assignmentConfirmation = teachingassistantDao.findByTAEmailId(emailId, courseCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return assignmentConfirmation;
	}
}
