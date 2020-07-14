package com.csci5308.groupme.survey.dao;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.courseadmin.instructor.model.Question;
import constants.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SurveyOperationDaoImpl implements SurveyOperationDao {

    private final Logger logger = LoggerFactory.getLogger(SurveyOperationDaoImpl.class);
    Connection connection = null;
    ResultSet resultSet = null;
    CallableStatement callableStatement = null;
    DatabaseProperties databaseProperties;

    @Override
    public List<Question> showQuestionsOnCreateSurveyPage(String userName, String courseCode, String roleName) {

        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        List<Question> notAddedQuestionsList = null;
        try {
            notAddedQuestionsList = new ArrayList<>();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();

            logger.info("SurveyOperationDaoImpl: Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("SurveyOperationDaoImpl: Connected to DataBase successfully...");
            if (roleName.equals(Roles.TA)) {
                callableStatement = connection.prepareCall("{call FIND_QUESTIONS_BY_TA_ROLE(?,?)}");
                callableStatement.setString(1, userName);
                callableStatement.setString(2, courseCode);

            } else {
                callableStatement = connection.prepareCall("{call FIND_QUESTIONS_BY_INSTRUCTOR_ROLE(?)}");
                callableStatement.setString(1, userName);
            }
            resultSet = callableStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            do {
                Integer questionId = resultSet.getInt("questionId");
                String questionTitle = resultSet.getString("questionTitle");
                String questionType = resultSet.getString("questionType");
                String question = resultSet.getString("question");
                notAddedQuestionsList.add(new Question(questionTitle, questionId, question, questionType));
            } while (resultSet.next());

        } catch (Exception e) {
            logger.info("SurveyOperationDaoImpl: ", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("SurveyOperationDaoImpl: ResultSet Closed");
                if (callableStatement != null) {
                    try {
                        callableStatement.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    logger.info("SurveyOperationDaoImpl: CallableStatement Closed");
                }
                if (connection != null) {
                    try {
                        connection.close();
                        logger.info("SurveyOperationDaoImpl: Connection to DataBase closed");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return notAddedQuestionsList;
    }

    @Override
    public String getJsonObjectOfQuestions(String courseCode) {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        String jsonResult = null;
        try {
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();

            logger.info("SurveyOperationDaoImpl: Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("SurveyOperationDaoImpl: Connected to DataBase successfully...");

            callableStatement = connection.prepareCall("{call GET_JSON_QUESTION_DATA(?)}");
            callableStatement.setString(1, courseCode);
            resultSet = callableStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            do {
                jsonResult = resultSet.getString("questions");
            } while (resultSet.next());
            logger.info("jsonResult :" + jsonResult);
        } catch (Exception e) {
            logger.info("SurveyOperationDaoImpl: ", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("SurveyOperationDaoImpl: ResultSet Closed");
                if (callableStatement != null) {
                    try {
                        callableStatement.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    logger.info("SurveyOperationDaoImpl: CallableStatement Closed");
                }
                if (connection != null) {
                    try {
                        connection.close();
                        logger.info("SurveyOperationDaoImpl: Connection to DataBase closed");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return jsonResult;
    }

    @Override
    public int writeJsonObjectOfQuestions(String courseCode, String jsonString, Integer questionId, Boolean removeQuestion) {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        int rowCount = 0;
        try {
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();

            logger.info("SurveyOperationDaoImpl: Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("SurveyOperationDaoImpl: Connected to DataBase successfully...");

            callableStatement = connection.prepareCall("{call INSERT_SURVEY_QUESTION(?,?)}");
            callableStatement.setString(1, courseCode);
            callableStatement.setString(2, jsonString);
            rowCount = callableStatement.executeUpdate();

            if (removeQuestion) {
                callableStatement = connection.prepareCall("{call REMOVE_QUESTION_FROM_SURVEY(?)}");
                callableStatement.setInt(1, questionId);

            } else {
                callableStatement = connection.prepareCall("{call UPDATE_QUESTION_FLAG(?)}");
                callableStatement.setInt(1, questionId);
            }
            callableStatement.executeUpdate();
        } catch (Exception e) {
            logger.info("SurveyOperationDaoImpl: ", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("SurveyOperationDaoImpl: ResultSet Closed");
                if (callableStatement != null) {
                    try {
                        callableStatement.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    logger.info("SurveyOperationDaoImpl: CallableStatement Closed");
                }
                if (connection != null) {
                    try {
                        connection.close();
                        logger.info("SurveyOperationDaoImpl: Connection to DataBase closed");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return rowCount;
    }

    public Map<String, Integer> checkIfSurveyExist(String courseCode) {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        Integer surveyId = 0;
        Integer isPublished = 0;
        Map<String, Integer> conditions = null;
        try {
            conditions = new HashMap<>();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();

            logger.info("SurveyOperationDaoImpl: Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("SurveyOperationDaoImpl: Connected to DataBase successfully...");

            callableStatement = connection.prepareCall("{call CHECK_IF_SURVEY_EXIST(?,?,?)}");
            callableStatement.setString(1, courseCode);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.executeUpdate();
            surveyId = callableStatement.getInt(2);
            isPublished = callableStatement.getInt(3);
            if (surveyId == 0) {
                callableStatement = connection.prepareCall("{call CREATE_SURVEY(?)}");
                callableStatement.setString(1, courseCode);
                callableStatement.executeUpdate();
            }
            conditions.put("surveyId", surveyId);
            conditions.put("isPublished", isPublished);
        } catch (Exception e) {
            logger.info("SurveyOperationDaoImpl: ", e);
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("SurveyOperationDaoImpl: CallableStatement Closed");
            }
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("SurveyOperationDaoImpl: Connection to DataBase closed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return conditions;
    }

    @Override
    public List<Question> getAlreadyAddedSurveyQuestions(String userName, String roleName, String courseCode) {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        List<Question> addedQuestionsList = null;
        try {
            addedQuestionsList = new ArrayList<>();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();

            logger.info("SurveyOperationDaoImpl: Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("SurveyOperationDaoImpl: Connected to DataBase successfully...");

            if (roleName.equals(Roles.TA)) {
                callableStatement = connection.prepareCall("{call GET_ADDED_SURVEY_QUESTIONS_BY_TA(?,?)}");
                callableStatement.setString(1, userName);
                callableStatement.setString(2, courseCode);
            } else {
                callableStatement = connection.prepareCall("{call GET_ADDED_SURVEY_QUESTIONS_BY_INSTRUCTOR(?)}");
                callableStatement.setString(1, userName);
            }
            resultSet = callableStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            do {
                Integer questionId = resultSet.getInt("questionId");
                String questionTitle = resultSet.getString("questionTitle");
                String questionType = resultSet.getString("questionType");
                String question = resultSet.getString("question");
                addedQuestionsList.add(new Question(questionTitle, questionId, question, questionType));
            } while (resultSet.next());
        } catch (Exception e) {
            logger.info("SurveyOperationDaoImpl: ", e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("SurveyOperationDaoImpl: ResultSet Closed");
            }
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("SurveyOperationDaoImpl: CallableStatement Closed");
            }
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("SurveyOperationDaoImpl: Connection to DataBase closed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return addedQuestionsList;
    }
}

