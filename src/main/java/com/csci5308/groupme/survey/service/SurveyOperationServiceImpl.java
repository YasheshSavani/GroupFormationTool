package com.csci5308.groupme.survey.service;

import com.csci5308.groupme.course.courseadmin.instructor.model.Question;
import com.csci5308.groupme.survey.dao.SurveyOperationDao;
import com.csci5308.groupme.survey.model.SurveyForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import constants.Messages;
import constants.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class SurveyOperationServiceImpl implements SurveyOperationService {

    private final Logger logger = LoggerFactory.getLogger(SurveyOperationServiceImpl.class);
    SurveyOperationDao surveyOperationDao;

    public SurveyOperationServiceImpl(SurveyOperationDao surveyOperationDao) {
        this.surveyOperationDao = surveyOperationDao;
    }

    @Override
    public List<Question> showQuestionsOnCreateSurveyPage(String courseCode, String roleName, String userName) {

        List<Question> notAddedQuestions;
        if (roleName.equals(Roles.TA)) {
            notAddedQuestions = surveyOperationDao.showQuestionsOnCreateSurveyPage(userName, courseCode, roleName);
            logger.info("Get Questions by TA username: " + userName);
        } else {
            notAddedQuestions = surveyOperationDao.showQuestionsOnCreateSurveyPage(userName, courseCode, roleName);
            logger.info("Get Questions by Instructor username : " + userName);
        }
        return notAddedQuestions;
    }

    @Override
    public int addQuestionToSurvey(String courseCode, String questionTitle, Integer questionId, String question, String questionType) {

        int rowCount = 0;
        String newJson;
        Boolean removeQuestion = false;
        Map<String, String> map;
        ObjectMapper objectMapper = new ObjectMapper();
        SurveyForm questionObj = new SurveyForm();
        questionObj.setQuestion(question);
        questionObj.setQuestionTitle(questionTitle);
        questionObj.setQuestionType(questionType);
        questionObj.setCriterion(Messages.NONE);
        questionObj.setUpperBound(Messages.NONE);
        questionObj.setLowerBound(Messages.NONE);
        questionObj.setWeight(Messages.NONE);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        logger.info("Questions Object Created For questionId : " + questionId + " And Question: " + question);
        String jsonQuestionResult = surveyOperationDao.getJsonObjectOfQuestions(courseCode);
        try {
            map = objectMapper.readValue(jsonQuestionResult, Map.class);
            map.put(questionId.toString(), questionObj.toString());
            newJson = objectMapper.writeValueAsString(map);
            logger.info("New Survey Questions: " + newJson);
            rowCount = surveyOperationDao.writeJsonObjectOfQuestions(courseCode, newJson, questionId, removeQuestion);
            logger.info("Question added to Survey : " + questionId + " : " + question);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in Fetching JSON");
        }
        return rowCount;
    }

    @Override
    public List<Question> getAlreadyAddedSurveyQuestions(String userName, String roleName, String courseCode) {

        List<Question> addedQuestions = surveyOperationDao.getAlreadyAddedSurveyQuestions(userName, roleName, courseCode);
        return addedQuestions;
    }

    @Override
    public Map<String, Integer> checkIfSurveyExist(String courseCode) {

        Map<String, Integer> conditions = surveyOperationDao.checkIfSurveyExist(courseCode);
        return conditions;
    }

    @Override
    public int removeQuestionFromSurvey(String questionId, String courseCode) {
        int rowCount = 0;
        Boolean removeQuestion = true;
        Map<String, String> map;
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonQuestionResult = surveyOperationDao.getJsonObjectOfQuestions(courseCode);
        try {
            map = objectMapper.readValue(jsonQuestionResult, Map.class);
            map.remove(questionId);
            String newJson = objectMapper.writeValueAsString(map);
            logger.info("New Survey Questions: " + newJson);
            rowCount = surveyOperationDao.writeJsonObjectOfQuestions(courseCode, newJson, Integer.parseInt(questionId), removeQuestion);
            logger.info("Question Removed from Survey : " + questionId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in Fetching JSON");

        }
        return rowCount;
    }
}
