package com.csci5308.groupme.instructor.dao;

import java.util.ArrayList;
import java.util.List;

import com.csci5308.groupme.instructor.model.Options;
import com.csci5308.groupme.instructor.model.Question;

public class QuestionsDaoMock implements QuestionsDAO{

	@Override
	public int saveQuestion(String instructorUserName, Question question, Options options) {
		List<Question> questions = new ArrayList<Question>();
		questions.add(question);
		return questions.size();
	}

	@Override
	public List<Question> findAllQuestions(String instructorUserName) {
		return null;
	}

	@Override
	public int removeQuestion(String instructorUserName, Question question) {
		return 0;
	}

	@Override
	public int updateQuestion(String instructorUserName, Question question) {
		return 0;
	}

}
