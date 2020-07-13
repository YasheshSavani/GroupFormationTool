package com.csci5308.groupme.survey.model;

import java.util.Map;

public class Candidate {
	
	private Integer surveyId;
	private String userName;
	private String bannerId;
	private String firstName;
	private String lastName;
	private String responses;
	private Double fitness;	
	private Map<?,?> questionResponsesMap;	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
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
	public Double getFitness() {
		return fitness;
	}
	public void setFitness(Double fitness) {
		this.fitness = fitness;
	}		
	
	public Map<?, ?> getQuestionResponsesMap() {
		return questionResponsesMap;
	}
	public void setQuestionResponsesMap(Map<?, ?> questionResponsesMap) {
		this.questionResponsesMap = questionResponsesMap;
	}
	
}
