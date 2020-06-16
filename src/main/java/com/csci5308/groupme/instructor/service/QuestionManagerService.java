package com.csci5308.groupme.instructor.service;

import java.util.List;

import com.csci5308.groupme.instructor.model.Options;
import com.csci5308.groupme.instructor.model.Question;

public interface QuestionManagerService {

	public String createQuestion(String instructorUserName, Question question, Options options);
	
	public List<Question> getAllQuestions(String instructorUserName);
	
	public int deleteQuestion(String instructorUserName, Question question);
	
	public int updateQuestion(String instructorUserName, Question question);
	
}
