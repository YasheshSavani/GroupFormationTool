package com.csci5308.groupme.instructor.dao;

import com.csci5308.groupme.instructor.model.Question;

import java.util.List;

public interface QuestionsDAO {

    public int saveQuestion(Question question);

    public List<Question> findAllQuestions(String instructorUserName);

    public int removeQuestion(String instructorUserName, Question question);

    public int updateQuestion(String instructorUserName, Question question);

}
