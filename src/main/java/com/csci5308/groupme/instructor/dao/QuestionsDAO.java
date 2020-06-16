package com.csci5308.groupme.instructor.dao;

import java.util.List;

import com.csci5308.groupme.instructor.model.Options;
import com.csci5308.groupme.instructor.model.Question;

public interface QuestionsDAO {

    public int saveQuestion(String instructorUserName, Question question, Options options);

    public List<Question> findAllQuestions(String instructorUserName);

    public int removeQuestion(String instructorUserName, Question question);

    public int updateQuestion(String instructorUserName, Question question);

}
