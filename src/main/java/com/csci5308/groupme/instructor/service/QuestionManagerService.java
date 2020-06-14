package com.csci5308.groupme.instructor.service;

import java.util.List;

import com.csci5308.groupme.instructor.model.Option;
import com.csci5308.groupme.instructor.model.Question;

public interface QuestionManagerService {

	public String createQuestion(Question question, List<Option> optionList);
	
	public List<Question> getAllQuestions(String instructorUserName);
	
	public int deleteQuestion(String instructorUserName, Question question);
	
	public int updateQuestion(String instructorUserName, Question question);
	
}
