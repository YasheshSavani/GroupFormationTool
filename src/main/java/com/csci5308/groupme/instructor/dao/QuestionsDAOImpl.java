package com.csci5308.groupme.instructor.dao;

import com.csci5308.groupme.instructor.model.Option;
import com.csci5308.groupme.instructor.model.Question;

import java.util.List;

public class QuestionsDAOImpl implements QuestionsDAO {

    @Override
    public String saveQuestion(Question question, List<Option> optionList) {


        return "Done";
    }

    @Override
    public List<Question> findAllQuestions(String instructorUserName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int removeQuestion(String instructorUserName, Question question) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int updateQuestion(String instructorUserName, Question question) {
        // TODO Auto-generated method stub
        return 0;
    }

}
