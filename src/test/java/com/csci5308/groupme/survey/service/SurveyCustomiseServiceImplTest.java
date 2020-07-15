package com.csci5308.groupme.survey.service;

import com.csci5308.groupme.course.courseadmin.instructor.constants.QuestionTypeConstants;
import com.csci5308.groupme.survey.dao.SurveyCustomiseDao;
import com.csci5308.groupme.survey.dao.SurveyCustomiseDaoImplMock;
import com.csci5308.groupme.survey.model.SurveyQuestion;
import com.csci5308.groupme.survey.model.SurveyQuestionList;
import constants.Messages;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SurveyCustomiseServiceImplTest {

    private SurveyCustomiseDao surveyCustomiseDao;
    private SurveyCustomiseService surveyCustomiseService;
    private String courseCodeTest = "csci0010";

    public SurveyCustomiseServiceImplTest() {
        surveyCustomiseDao = new SurveyCustomiseDaoImplMock();
        surveyCustomiseService = new SurveyCustomiseServiceImpl(surveyCustomiseDao);
    }

    @Test
    void getSurveyQuestionsTest() {
        List<SurveyQuestion> surveyQuestionList;
        surveyQuestionList = surveyCustomiseService.getSurveyQuestions(courseCodeTest);
        assertEquals(surveyQuestionList.size(), 1);
    }


    @Test
    void checkIfSurveyIsPublishedTest() {
        int rowCount = surveyCustomiseService.checkIfSurveyIsPublished(courseCodeTest);
        assertEquals(rowCount, 0);
    }

    @Test
    void saveCustomisedQuestionsToSurveyTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestionId("1");
        surveyQuestion.setQuestionTitle("Python");
        surveyQuestion.setQuestion("How good are you in Python?");
        surveyQuestion.setQuestionType(QuestionTypeConstants.NUMERIC);
        surveyQuestion.setCriterion("1");
        surveyQuestion.setWeight("1");
        surveyQuestion.setUpperBound(Messages.NONE);
        surveyQuestion.setLowerBound(Messages.NONE);
        List<SurveyQuestion> questionList = new ArrayList<>();
        questionList.add(surveyQuestion);
        SurveyQuestionList surveyQuestionList = new SurveyQuestionList();
        surveyQuestionList.setSurveyQuestionList(questionList);
        int rowCount = surveyCustomiseService.saveCustomisedQuestionsToSurvey(surveyQuestionList, courseCodeTest);
        assertEquals(rowCount, 1);

    }
}