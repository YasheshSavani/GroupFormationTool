package com.csci5308.groupme.instructor.service;

import java.sql.Date;
import java.util.List;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.instructor.dao.QuestionsDAO;
import com.csci5308.groupme.instructor.model.Options;
import com.csci5308.groupme.instructor.model.Question;

public class QuestionManagerServiceImpl implements QuestionManagerService {

	private QuestionsDAO questionsDAO;

	public QuestionManagerServiceImpl() {
		super();
	}

	public QuestionManagerServiceImpl(QuestionsDAO questionsDAO) {
		this.questionsDAO = questionsDAO;
	}

	@Override
	public String createQuestion(String instructorUserName, Question question, Options options) {
		questionsDAO = SystemConfig.instance().getQuestionsDAO();
		String message = null;
		int rowCount;
		try {
			if (question.getType().equals("Numeric") || question.getType().equals("Free text")) {
				rowCount = questionsDAO.saveNonMCQ(instructorUserName, question);
			}
			else {				
				//Write MCQ related call here : 
				//rowCount = questionsDAO.saveMCQ(instructorUserName, question, options);
				rowCount = 0;
			}
			if (rowCount == 1) {
				message = "Question created!";
			} else {
				message = "Something went wrong! Question was not inserted into the database";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public List<Question> getAllQuestions(String instructorUserName) {
		// TODO Auto-generated method stub
		return null;
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
