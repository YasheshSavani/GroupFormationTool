package com.csci5308.groupme.survey.model;

public class SurveyResponse {

	Integer surveyId;
	String userName;
	String bannerId;
	String responses;
	
	public Integer getSurveyId() {
		return surveyId;
	}
	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBannerId() {
		return bannerId;
	}
	public void setBannerId(String bannerId) {
		this.bannerId = bannerId;
	}
	public String getResponses() {
		return responses;
	}
	public void setResponses(String responses) {
		this.responses = responses;
	}		
	
}
