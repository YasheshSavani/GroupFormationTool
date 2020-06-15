package com.csci5308.groupme.instructor.service;

import java.util.List;

import com.csci5308.groupme.instructor.dao.QuestionsDAO;
import com.csci5308.groupme.instructor.model.Question;

public class QuestionManagerServiceImpl implements QuestionManagerService{
	
	QuestionsDAO questionsDAO;

	@Override
	public int createQuestion(Question question) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> getAllQuestions(String instructorUserName) throws Exception{
		return questionsDAO.findAllQuestions(instructorUserName);
	}

	@Override
	public int deleteQuestion(String instructorUserName, Question question) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateQuestion(String instructorUserName, Question question) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
