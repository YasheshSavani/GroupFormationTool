package com.csci5308.groupme.instructor.service;

import java.sql.Date;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.csci5308.groupme.instructor.QuestionTypeConstants;
import com.csci5308.groupme.instructor.dao.QuestionsDAO;
import com.csci5308.groupme.instructor.model.Option;
import com.csci5308.groupme.instructor.model.Options;
import com.csci5308.groupme.instructor.model.Question;

import ch.qos.logback.classic.Logger;

public class QuestionManagerServiceImpl implements QuestionManagerService {

    private final Logger logger = (Logger) LoggerFactory.getLogger(QuestionManagerServiceImpl.class);

    private final QuestionsDAO questionsDAO;

    public QuestionManagerServiceImpl(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    @Override
    public int createQuestion(String instructorUserName, Question question, Options options) {
        int status = 0;
        long millis = System.currentTimeMillis();
        Date createdDate = new Date(millis);
        try {
            if (question.getType().equals(QuestionTypeConstants.numericType) || question.getType().equals(QuestionTypeConstants.freeTextType)) {
                question.setCreatedDate(createdDate);
                status = questionsDAO.saveNonMCQ(instructorUserName, question);
            } else {
                List<Option> optionList = options.getOptionList();
                question.setCreatedDate(createdDate);
                status = questionsDAO.saveMultipleChoiceQuestion(question, optionList, instructorUserName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Question> getAllTitles(String instructorUserName) {
        return questionsDAO.findAllTitles(instructorUserName);
    }

    @Override
    public int deleteQuestion(String instructorUserName, Question question) {
        int status = 0;
        try {
        	status = questionsDAO.removeQuestion(instructorUserName, question);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Question> getAllSortedTitlesByTitles(String instructorUserName) {
        return questionsDAO.findAllSortedTitlesByTitles(instructorUserName);
    }

    @Override
    public List<Question> getAllSortedTitlesByDates(String instructorUserName) {
        List<Question> questions = questionsDAO.findAllSortedTitlesByDates(instructorUserName);
        for (Question question : questions) {
            logger.info(question.getTitle());
        }
        return questions;
    }

    @Override
    public List<Question> getAllQuestions(String instructorUserName) {
        return questionsDAO.findAllQuestions(instructorUserName);
    }

}