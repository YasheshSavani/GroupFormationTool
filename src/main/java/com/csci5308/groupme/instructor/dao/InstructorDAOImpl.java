package com.csci5308.groupme.instructor.dao;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.instructor.model.Instructor;

import sql.ClassQuery;
import sql.InstructorQuery;
import sql.UserQuery;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Repository
public class InstructorDAOImpl implements InstructorDAO {

	private final Logger logger = (Logger) LoggerFactory.getLogger(InstructorDAOImpl.class);

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

	@Override
	public Instructor findByUserName(String userName) throws Exception {
		Instructor instructor = new Instructor();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
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
			preparedStatement = connection.prepareStatement(InstructorQuery.GET_INSTRUCTOR_BY_USERNAME);
			preparedStatement.setString(1, userName);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next() == false)
				return null;
			do {
				instructor.setUserName(resultSet.getString("userName"));
				instructor.setAbout(resultSet.getString("about"));
			} while (resultSet.next());

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (SQLException se) {
			}

			if (connection != null)
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return instructor;
	}
}
