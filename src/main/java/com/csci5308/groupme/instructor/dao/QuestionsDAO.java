package com.csci5308.groupme.instructor.dao;

import java.util.List;

import com.csci5308.groupme.instructor.model.Question;

public interface QuestionsDAO {

	public int saveQuestion(Question question);

	public List<String> findAllQuestions(String instructorUserName) throws Exception;

	public int removeQuestion(String instructorUserName, Question question);

	public int updateQuestion(String instructorUserName, Question question);

}
