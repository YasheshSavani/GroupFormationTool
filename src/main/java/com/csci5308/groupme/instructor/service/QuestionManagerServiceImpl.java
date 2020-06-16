package com.csci5308.groupme.instructor.service;

import java.util.List;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.instructor.dao.QuestionsDAO;
import com.csci5308.groupme.instructor.model.Options;
import com.csci5308.groupme.instructor.model.Question;

public class QuestionManagerServiceImpl implements QuestionManagerService {

	@Override
	public String createQuestion(String instructorUserName, Question question, Options options) {
		QuestionsDAO questionsDAO = SystemConfig.instance().getQuestionsDAO();
		String message = null;
		int rowCount;
		try {
			rowCount = questionsDAO.saveQuestion(instructorUserName, question, options);
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
