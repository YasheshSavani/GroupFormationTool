package com.csci5308.groupme.instructor.service;

import ch.qos.logback.classic.Logger;
import com.csci5308.groupme.instructor.QuestionTypeConstants;
import com.csci5308.groupme.instructor.dao.QuestionsDAO;
import com.csci5308.groupme.instructor.model.Option;
import com.csci5308.groupme.instructor.model.Options;
import com.csci5308.groupme.instructor.model.Question;
import errors.EditCodes;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;

public class QuestionManagerServiceImpl implements QuestionManagerService {

    private final Logger logger = (Logger) LoggerFactory.getLogger(QuestionManagerServiceImpl.class);

    private final QuestionsDAO questionsDAO;

    public QuestionManagerServiceImpl(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    @Override
    public String createQuestion(String instructorUserName, Question question, Options options) {
        String message = null;
        int rowCount;
        long millis = System.currentTimeMillis();
        Date createdDate = new Date(millis);
        try {
            if (question.getType().equals(QuestionTypeConstants.numericType) || question.getType().equals(QuestionTypeConstants.freeTextType)) {
                question.setCreatedDate(createdDate);
                rowCount = questionsDAO.saveNonMCQ(instructorUserName, question);
            } else {
                List<Option> optionList = options.getOptionList();
                question.setCreatedDate(createdDate);
                rowCount = questionsDAO.saveMultipleChoiceQuestion(question, optionList, instructorUserName);
            }
            if (rowCount >= EditCodes.INITIAL_ROWCOUNT) {
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
    public List<Question> getAllTitles(String instructorUserName) {
        return questionsDAO.findAllTitles(instructorUserName);
    }

    @Override
    public String deleteQuestion(String instructorUserName, Question question) {
        String message = null;
        int rowCount;
        try {
            rowCount = questionsDAO.removeQuestion(instructorUserName, question);
            if (rowCount >= EditCodes.INITIAL_ROWCOUNT) {
                message = "Question deleted!";
            } else {
                message = "Something went wrong! Question was not deleted from the database";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
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