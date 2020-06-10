package com.csci5308.groupme.teaching_assistant.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import sql.UserQuery;

public class TeachingAssistantDaoImpl implements TeachingAssistantDao{

	private final Logger logger = (Logger) LoggerFactory.getLogger(TeachingAssistantDaoImpl.class);
	
	@Override
	public String findByTAEmailId(String emailId, String courseCode) throws Exception {
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			String databasePropertiesFilePath = "src/main/resources/database.properties";
			Reader dbPropertiesReader = new BufferedReader(new FileReader(databasePropertiesFilePath));
			Properties properties = new Properties();
			properties.load(dbPropertiesReader);

			String JDBC_DRIVER = properties.getProperty("development.driver");
			String DB_URL = properties.getProperty("development.url");
			String USER = properties.getProperty("development.username");
			String PASSWORD = properties.getProperty("development.password");
			Class.forName(JDBC_DRIVER);
			logger.info("Connecting to the selected database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			logger.info("Connected to the database successfully...");
			preparedStatement = connection.prepareStatement(UserQuery.FIND_BY_EMAIL);
			preparedStatement.setString(1, emailId);
			resultSet = preparedStatement.executeQuery();
			logger.info("Execution of Course Select Query is Completed...");
			if (!resultSet.next()) {
				return "No User account available";
			}
			do {
				String taUserName = resultSet.getString("userName");
				CallableStatement callableStatementToAddTA = connection.prepareCall("{call INSERT_TA(?,?)}");
				try {
					if (callableStatementToAddTA != null) {
						callableStatementToAddTA.setString(1, taUserName);
						callableStatementToAddTA.setString(2, courseCode);
						callableStatementToAddTA.executeUpdate();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					callableStatementToAddTA.close();
				}
			} while (resultSet.next());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resultSet.close();
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
					logger.info("Connection to database Closed...");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return "True";
	}

}
