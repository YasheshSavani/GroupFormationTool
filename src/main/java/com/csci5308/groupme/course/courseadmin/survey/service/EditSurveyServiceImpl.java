package com.csci5308.groupme.course.courseadmin.survey.service;

import org.slf4j.LoggerFactory;

import com.csci5308.groupme.course.courseadmin.survey.dao.EditSurveyDao;

import ch.qos.logback.classic.Logger;

public class EditSurveyServiceImpl implements EditSurveyService{
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(EditSurveyServiceImpl.class);
	
	private final EditSurveyDao editSurveyDao;
	
	public EditSurveyServiceImpl(EditSurveyDao editSurveyDao) {
		this.editSurveyDao = editSurveyDao;
	}
	
	@Override
	public int getSurveyPublishStatus(String courseAdminUserName, String courseCode) {
		int status = 0;
		try {
			status = editSurveyDao.findSurveyPublishStatus(courseAdminUserName,courseCode);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
