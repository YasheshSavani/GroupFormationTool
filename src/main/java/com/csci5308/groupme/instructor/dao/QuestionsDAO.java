package com.csci5308.groupme.instructor.dao;

import com.csci5308.groupme.instructor.model.Option;
import com.csci5308.groupme.instructor.model.Question;

import java.util.List;

public interface QuestionsDAO {

    public int saveNonMCQ(String instructorUserName, Question question);

    public int saveMultipleChoiceQuestion(Question question, List<Option> optionList, String instructorUserName);

    public List<Question> findAllQuestions(String instructorUserName);

    public int removeQuestion(String instructorUserName, Question question);

}
