package com.csci5308.groupme.course.survey.dao;

import com.csci5308.groupme.course.survey.dao.SurveyCustomiseDao;

public class SurveyCustomiseDaoImplMock implements SurveyCustomiseDao {

    private final String surveyQuestionTest = "{" +
            "\"1\": { " +
            " \"title\": \"default\"," +
            "\"question\": \"default\"," +
            "\"type\": \"Numeric\"," +
            "\"criterion\": \"1\"," +
            "\"weight\": \"5\"," +
            "\"upperBound\": \"4\"," +
            "\"lowerBound\": \"5\"}" +
            "}";

    @Override
    public String getSurveyQuestions(String courseCode) {
        return surveyQuestionTest;
    }

    @Override
    public int checkIfSurveyIsPublished(String courseCode) {
        return 0;
    }

    @Override
    public int insertCustomisedQuestionsToSurvey(String courseCode, String newSurveyQuestions) {
        return 1;
    }
}
