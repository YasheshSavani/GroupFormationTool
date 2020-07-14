package com.csci5308.groupme.survey.service;

import com.csci5308.groupme.course.courseadmin.instructor.model.Question;
import com.csci5308.groupme.survey.dao.SurveyOperationDao;
import com.csci5308.groupme.survey.model.SurveyQuestion;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
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
    public int addQuestionToSurvey(String courseCode, SurveyQuestion surveyQuestion) throws Exception {

        int rowCount = 0;
        String newJson;
        Map<String, Map<String, String>> dbSurveyForm;
        Map<String, String> parameters = new HashMap<>();
        String questionID = surveyQuestion.getQuestionId();
        ObjectMapper objectMapper = new ObjectMapper();

        parameters.put("title", surveyQuestion.getQuestionTitle());
        parameters.put("question", surveyQuestion.getQuestion());
        parameters.put("type", surveyQuestion.getQuestionType());
        parameters.put("criterion", surveyQuestion.getCriterion());
        parameters.put("weight", surveyQuestion.getWeight());
        parameters.put("upperBound", surveyQuestion.getUpperBound());
        parameters.put("lowerBound", surveyQuestion.getLowerBound());
        logger.info("Parameters map created for question to be added");
        logger.info("Questions Object Created For questionId : " + surveyQuestion.getQuestionId()
                + " And Question: " + surveyQuestion.getQuestion());
        String jsonQuestionResult = surveyOperationDao.getJsonObjectOfQuestions(courseCode);
        try {
            dbSurveyForm = objectMapper.readValue(jsonQuestionResult, Map.class);
//            for (Map.Entry<?, ?> entry : dbSurveyForm.entrySet()) {
//                String questionID = (String) entry.getKey();
//                logger.info((String) entry.getKey());
//                logger.info((String) entry.getValue());
//            }
            dbSurveyForm.put(surveyQuestion.getQuestionId(), parameters);
            logger.debug(surveyQuestion.toString());
            newJson = objectMapper.writeValueAsString(dbSurveyForm);
            logger.info("Questions in survey after addition: " + newJson);
            rowCount = surveyOperationDao.insertQuestionToSurvey(courseCode, newJson, Integer.parseInt(questionID));
            logger.info("Question added to Survey : " + questionID + " : " + surveyQuestion.getQuestion());
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
    public int removeQuestionFromSurvey(String questionId, String courseCode) throws Exception {
        int rowCount = 0;
        Map<?, ?> map;
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonQuestionResult = surveyOperationDao.getJsonObjectOfQuestions(courseCode);
        try {
            map = objectMapper.readValue(jsonQuestionResult, Map.class);
            map.remove(questionId);
            String newJson = objectMapper.writeValueAsString(map);
            logger.info("New Survey Questions: " + newJson);
            rowCount = surveyOperationDao.deleteQuestionFromSurvey(courseCode, newJson, Integer.parseInt(questionId));
            logger.info("Question Removed from Survey : " + questionId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in Fetching JSON");
        }
        return rowCount;
    }
}
