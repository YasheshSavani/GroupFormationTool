package com.csci5308.groupme.survey.service;

import com.csci5308.groupme.course.courseadmin.instructor.model.Question;

import java.util.List;
import java.util.Map;

public interface SurveyOperationService {

    public List<Question> showQuestionsOnCreateSurveyPage(String courseCode, String roleName, String userName);

    public int addQuestionToSurvey(String courseCode, String questionTitle, Integer questionId, String question, String questionType);

    public int removeQuestionFromSurvey(String questionId, String courseCode);

    public Map<String, Integer> checkIfSurveyExist(String courseCode);

    public List<Question> getAlreadyAddedSurveyQuestions(String userName, String roleName, String courseCode);
}
