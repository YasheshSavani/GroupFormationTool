package com.csci5308.groupme.instructor.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.LoggerFactory;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.instructor.model.Question;

import ch.qos.logback.classic.Logger;
import sql.QuestionQuery;
import sql.UserQuery;

public class QuestionsDAOImpl implements QuestionsDAO{
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(QuestionsDAOImpl.class);
	DatabaseProperties databaseProperties = new DatabaseProperties();

	@Override
	public int saveQuestion(Question question) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> findAllQuestions(String instructorUserName) throws Exception{
		logger.info("Entering find all questions DAO...");
		
		List<String> instructorQuestionList = null;
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try
		{
			instructorQuestionList = new ArrayList<>();
			
			String DB_URL = databaseProperties.getDbURL();
			String USER = databaseProperties.getDbUserName();
			String PASSWORD = databaseProperties.getDbPassword();

			logger.info("Connecting to the selected database...");

			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			logger.info("Connected to the database successfully...");
			
			preparedStatement = connection.prepareStatement(QuestionQuery.GET_QUESTION_TITLE);
			preparedStatement.setString(1, instructorUserName);
			resultSet = preparedStatement.executeQuery();
			
			if(!resultSet.next()) 
			{
				return null;
			}
			do
			{
				String questionTitle = resultSet.getString("questionTitle");
				instructorQuestionList.add(questionTitle);
				logger.info(questionTitle);
			}
			while (resultSet.next());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			//resultSet.close();
			if (preparedStatement != null) 
			{
				try 
				{
					preparedStatement.close();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			if (connection != null) 
			{
				try 
				{
					connection.close();
					logger.info("Connection to database Closed...");
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}

		}
		return instructorQuestionList;
	}

	@Override
	public int removeQuestion(String instructorUserName, Question question) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateQuestion(String instructorUserName, Question question) {
		// TODO Auto-generated method stub
		return 0;
	}

}
