package com.csci5308.groupme.instructor.dao;

import java.util.List;

import com.csci5308.groupme.instructor.model.Question;
import com.csci5308.groupme.instructor.model.Option;

public interface QuestionsDAO {

    public int saveNonMCQ(String instructorUserName, Question question);
    public int saveMultipleChoiceQuestion(Question question, List<Option> optionList, String instructorUserName);

    public List<Question> findAllQuestions(String instructorUserName);

    public int removeQuestion(String instructorUserName, Question question);

    public int updateQuestion(String instructorUserName, Question question);

}
