package com.csci5308.groupme.survey.service;

import com.csci5308.groupme.course.courseadmin.instructor.model.Question;
import com.csci5308.groupme.survey.dao.SurveyOperationDao;
import com.csci5308.groupme.survey.dao.SurveyOperationDaoImplMock;
import constants.Roles;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SurveyOperationServiceImplTest {

    private SurveyOperationDao surveyOperationDao;
    private SurveyOperationService surveyOperationService;
    private String userNameTest = "ysavani";
    private String roleNameTest = Roles.INSTRUCTOR;
    private String courseCodeTest = "csci0010";
    private String questionTest = "Level in Python";
    private String questionTitleTest = "Python";
    private String questionTypeTest = "Numeric";
    private Integer questionIdTest = 2;

    public SurveyOperationServiceImplTest() {
        surveyOperationDao = new SurveyOperationDaoImplMock();
        surveyOperationService = new SurveyOperationServiceImpl(surveyOperationDao);
    }

    @Test
    void showQuestionsOnCreateSurveyPageTest() {
        List<Question> notAddedQuestion = surveyOperationService.showQuestionsOnCreateSurveyPage(courseCodeTest, roleNameTest, userNameTest);
        assertEquals(notAddedQuestion.size(), 1);
    }

    @Test
    void addQuestionToSurveyTest() throws Exception {
        int rowCount = surveyOperationService.addQuestionToSurvey(courseCodeTest, questionTitleTest, questionIdTest, questionTest, questionTypeTest);
        assertEquals(rowCount, 1);
    }

    @Test
    void getAlreadyAddedSurveyQuestionsTest() {
        List<Question> addedQuestion = surveyOperationService.getAlreadyAddedSurveyQuestions(userNameTest, roleNameTest, courseCodeTest);
        assertEquals(addedQuestion.size(), 1);
    }

    @Test
    void checkIfSurveyExistTest() {
        Map<String, Integer> conditions = surveyOperationService.checkIfSurveyExist(courseCodeTest);
        assertEquals(conditions.get("surveyId"), 1);
        assertEquals(conditions.get("isPublished"), 1);
    }

    @Test
    void removeQuestionFromSurveyTest() throws Exception {
        int rowCount = surveyOperationService.removeQuestionFromSurvey(questionIdTest.toString(), courseCodeTest);
        assertEquals(rowCount, 1);
    }
}
