package com.csci5308.groupme.instructor.service;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.instructor.dao.QuestionsDAO;
import com.csci5308.groupme.instructor.model.Option;
import com.csci5308.groupme.instructor.model.Question;

import java.util.List;

public class QuestionManagerServiceImpl implements QuestionManagerService {

    @Override
    public String createQuestion(Question question, List<Option> optionList) {
        QuestionsDAO questionsDAO = SystemConfig.instance().getQuestionsDAO();
        String message = null;
        try {

            message = questionsDAO.saveQuestion(question, optionList);

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
