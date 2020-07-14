package com.csci5308.groupme.survey.dao;

import com.csci5308.groupme.course.courseadmin.instructor.model.Question;

import java.util.List;
import java.util.Map;

public interface SurveyOperationDao {

    public List<Question> showQuestionsOnCreateSurveyPage(String userName, String courseCode, String roleName);

    public String getJsonObjectOfQuestions(String courseCode);

    public int writeJsonObjectOfQuestions(String courseCode, String jsonString, Integer questionId, Boolean removeQuestion);

    public Map<String, Integer> checkIfSurveyExist(String courseCode);

    public List<Question> getAlreadyAddedSurveyQuestions(String userName, String roleName, String courseCode);
}