package com.csci5308.groupme.survey.service;

import com.csci5308.groupme.survey.model.SurveyQuestion;
import com.csci5308.groupme.survey.model.SurveyQuestionList;

import java.util.List;

public interface SurveyCustomiseService {

    public List<SurveyQuestion> getSurveyQuestions(String courseCode);

    public int checkIfSurveyIsPublished(String courseCode);

    int saveCustomisedQuestionsToSurvey(SurveyQuestionList surveyQuestionList, String courseCode);
}
