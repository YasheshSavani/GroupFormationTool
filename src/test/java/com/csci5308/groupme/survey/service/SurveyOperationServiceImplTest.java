package com.csci5308.groupme.survey.service;

import com.csci5308.groupme.course.courseadmin.instructor.model.Question;
import com.csci5308.groupme.survey.dao.SurveyOperationDao;
import com.csci5308.groupme.survey.dao.SurveyOperationDaoImplMock;
import com.csci5308.groupme.survey.model.SurveyQuestion;
import constants.Messages;
import constants.Roles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class SurveyOperationServiceImplTest {

    private SurveyOperationDao surveyOperationDao;
    private SurveyOperationService surveyOperationService;
    private String userNameTest = "ysavani";
    private String roleNameTest = Roles.INSTRUCTOR;
    private String courseCodeTest = "csci0010";
    private String questionTest = "Level in Python";
    private String questionTitleTest = "Python";
    private String questionTypeTest = "Numeric";
    private String questionIdTest = "2";

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
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestionId(questionIdTest);
        surveyQuestion.setQuestion(questionTest);
        surveyQuestion.setQuestionTitle(questionTitleTest);
        surveyQuestion.setQuestionType(questionTypeTest);
        surveyQuestion.setCriterion(Messages.NONE);
        surveyQuestion.setUpperBound(Messages.NONE);
        surveyQuestion.setLowerBound(Messages.NONE);
        surveyQuestion.setWeight(Messages.NONE);
        int rowCount = surveyOperationService.addQuestionToSurvey(courseCodeTest, surveyQuestion);
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
        int rowCount = surveyOperationService.removeQuestionFromSurvey(questionIdTest, courseCodeTest);
        assertEquals(rowCount, 1);
    }
}
