package com.csci5308.groupme.instructor.service;

import java.util.List;

import com.csci5308.groupme.instructor.model.Question;

public interface QuestionManagerService {

	public int createQuestion(Question question);
	
	public List<Question> getAllQuestions(String instructorUserName);
	
	public int deleteQuestion(String instructorUserName, Question question);
	
	public int updateQuestion(String instructorUserName, Question question);
	
}