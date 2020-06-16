package com.csci5308.groupme.instructor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.instructor.model.Options;
import com.csci5308.groupme.instructor.model.Question;
import com.csci5308.groupme.user.dao.UserDaoImpl;

import errors.EditCodes;
import errors.SqlErrors;
import sql.QuestionsQuery;
import sql.UserQuery;

public class QuestionsDAOImpl implements QuestionsDAO {

	Logger logger = LoggerFactory.getLogger(QuestionsDAOImpl.class);
	private DatabaseProperties databaseProperties;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	@Override
	public int saveQuestion(String instructorUserName, Question question, Options options) {
		int rowCount = 0;
		databaseProperties = SystemConfig.instance().getDatabaseProperties();
		String DB_URL = databaseProperties.getDbURL();
		String USER = databaseProperties.getDbUserName();
		String PASS = databaseProperties.getDbPassword();
		try {
			logger.info("Connecting to the selected database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
			preparedStatement = connection.prepareStatement(QuestionsQuery.SAVE_QUESTION);
			//preparedStatement.setInt(1, question.getQuestionId());
			preparedStatement.setString(1, instructorUserName);
			preparedStatement.setString(2, question.getTitle());
			preparedStatement.setString(3, question.getType());
			preparedStatement.setString(4, question.getQuestion());
			preparedStatement.setDate(5, question.getCreatedDate());
			rowCount = preparedStatement.executeUpdate();
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
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return rowCount;
	}

	@Override
	public List<Question> findAllQuestions(String instructorUserName) {
		// TODO Auto-generated method stub
		return null;
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
