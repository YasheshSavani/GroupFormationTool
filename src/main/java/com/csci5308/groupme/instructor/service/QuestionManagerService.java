package com.csci5308.groupme.instructor.service;

import java.util.List;

import com.csci5308.groupme.instructor.model.Options;
import com.csci5308.groupme.instructor.model.Question;

public interface QuestionManagerService {

	public String createQuestion(String instructorUserName, Question question, Options options);
	
	public List<Question> getAllTitles(String instructorUserName);
	
	public String deleteQuestion(String instructorUserName, Question question);

	public List<Question> getAllSortedTitlesByTitles(String instructorUserName);

	public List<Question> getAllSortedTitlesByDates(String instructorUserName);

	public List<Question> getAllQuestions(String instructorUserName);
}
