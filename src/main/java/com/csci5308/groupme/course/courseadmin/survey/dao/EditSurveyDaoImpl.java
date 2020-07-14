package com.csci5308.groupme.course.courseadmin.survey.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.SystemConfig;

import sql.SurveyStatusQuery;


public class EditSurveyDaoImpl implements EditSurveyDao{
	
	Logger logger = LoggerFactory.getLogger(EditSurveyDaoImpl.class);
    private DatabaseProperties databaseProperties;
    private Connection connection = null;
	
	@Override
	public int findSurveyPublishStatus(String courseAdminUserName, String courseCode) {
		int surveyStatus = 0;
		ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        DatabaseProperties databaseProperties = new DatabaseProperties();
        try {
        	String DRIVER = databaseProperties.getDriver();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            Class.forName(DRIVER);
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to the database successfully...");
            preparedStatement = connection.prepareStatement(SurveyStatusQuery.GET_SURVEY_PUBLISH_STATUS);
            preparedStatement.setString(1, courseCode);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                return 0;
            }
            surveyStatus = resultSet.getInt("courseCode");
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Connection to database closed...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return surveyStatus;
	}

}
