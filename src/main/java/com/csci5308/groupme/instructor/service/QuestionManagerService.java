package com.csci5308.groupme.instructor.service;

import com.csci5308.groupme.instructor.model.Options;
import com.csci5308.groupme.instructor.model.Question;

import java.util.List;

public interface QuestionManagerService {

    public int createQuestion(String instructorUserName, Question question, Options options);

    public List<Question> getAllTitles(String instructorUserName);

    public int deleteQuestion(String instructorUserName, Question question);

    public List<Question> getAllSortedTitlesByTitles(String instructorUserName);

    public List<Question> getAllSortedTitlesByDates(String instructorUserName);

    public List<Question> getAllQuestions(String instructorUserName);
}
