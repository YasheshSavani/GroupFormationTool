package com.csci5308.groupme.course.courseadmin.survey.dao;

public interface EditSurveyDao {
	
	public int findSurveyPublishStatus(String courseAdminUserName, String courseCode);
}
