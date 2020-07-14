package com.csci5308.groupme.survey.model;

import constants.QuestionTypeConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SurveyFormTest {

    private String questionIdTest = "1";
    private String questionTitleTest = "Python";
    private String questionTypeTest = QuestionTypeConstants.numericType;
    private String questionTest = "Proficiency Level";
    private String criterionTest = "similar";
    private String weightTest = "1";
    private String upperBoundTest = "8";
    private String lowerBoundTest = "3";

    @Test
    void getQuestionIdTest() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setQuestionId(questionIdTest);
        assertEquals(surveyForm.getQuestionId(), questionIdTest);
    }

    @Test
    void setQuestionIdTest() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setQuestionId(questionIdTest);
        assertEquals(surveyForm.getQuestionId(), questionIdTest);
    }

    @Test
    void getQuestionTitleTest() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setQuestionTitle(questionTitleTest);
        assertEquals(surveyForm.getQuestionTitle(), questionTitleTest);
    }

    @Test
    void setQuestionTitleTest() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setQuestionTitle(questionTitleTest);
        assertEquals(surveyForm.getQuestionTitle(), questionTitleTest);
    }

    @Test
    void getQuestionTypeTest() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setQuestionType(questionTypeTest);
        assertEquals(surveyForm.getQuestionType(), questionTypeTest);
    }

    @Test
    void setQuestionType() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setQuestionType(questionTypeTest);
        assertEquals(surveyForm.getQuestionType(), questionTypeTest);
    }

    @Test
    void getQuestionTest() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setQuestion(questionTest);
        assertEquals(surveyForm.getQuestion(), questionTest);
    }

    @Test
    void setQuestionTest() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setQuestion(questionTest);
        assertEquals(surveyForm.getQuestion(), questionTest);
    }

    @Test
    void getCriterionTest() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setCriterion(criterionTest);
        assertEquals(surveyForm.getCriterion(), criterionTest);
    }

    @Test
    void setCriterionTest() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setCriterion(criterionTest);
        assertEquals(surveyForm.getCriterion(), criterionTest);
    }

    @Test
    void getWeightTest() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setWeight(weightTest);
        assertEquals(surveyForm.getWeight(), weightTest);
    }

    @Test
    void setWeightTest() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setWeight(weightTest);
        assertEquals(surveyForm.getWeight(), weightTest);
    }

    @Test
    void getUpperBoundTest() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setUpperBound(upperBoundTest);
        assertEquals(surveyForm.getUpperBound(), upperBoundTest);
    }

    @Test
    void setUpperBoundTest() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setUpperBound(upperBoundTest);
        assertEquals(surveyForm.getUpperBound(), upperBoundTest);
    }

    @Test
    void getLowerBoundTest() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setLowerBound(lowerBoundTest);
        assertEquals(surveyForm.getLowerBound(), lowerBoundTest);
    }

    @Test
    void setLowerBoundTest() {
        SurveyForm surveyForm = new SurveyForm();
        surveyForm.setLowerBound(lowerBoundTest);
        assertEquals(surveyForm.getLowerBound(), lowerBoundTest);
    }
}