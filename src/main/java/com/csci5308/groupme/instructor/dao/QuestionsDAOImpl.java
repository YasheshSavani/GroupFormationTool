package com.csci5308.groupme.instructor.dao;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.instructor.model.Option;
import com.csci5308.groupme.instructor.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.QuestionsQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDAOImpl implements QuestionsDAO {

    Logger logger = LoggerFactory.getLogger(QuestionsDAOImpl.class);
    private DatabaseProperties databaseProperties;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    @Override
    public int saveNonMCQ(String instructorUserName, Question question) {
        int rowCount = 0;
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        String DB_URL = databaseProperties.getDbURL();
        String USER = databaseProperties.getDbUserName();
        String PASS = databaseProperties.getDbPassword();
        try {
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            preparedStatement = connection.prepareStatement(QuestionsQuery.SAVE_QUESTION);
            preparedStatement.setString(1, instructorUserName);
            preparedStatement.setString(2, question.getTitle());
            preparedStatement.setString(3, question.getType());
            preparedStatement.setString(4, question.getQuestion());
            preparedStatement.setDate(5, question.getCreatedDate());
            rowCount = preparedStatement.executeUpdate();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException se) {
                se.printStackTrace();
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
    public int saveMultipleChoiceQuestion(Question question, List<Option> optionList, String instructorUserName) {
        int rowCount = 0;
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        logger.info("Size: " + optionList.size());
        Connection connection = null;
        CallableStatement callableStatement = null;
        try {
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            String DRIVER = databaseProperties.getDriver();
            Class.forName(DRIVER);
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to the database successfully...");
            callableStatement = connection.prepareCall("{call INSERT_QUESTION(?,?,?,?,?,?)}");
            callableStatement.setString(1, instructorUserName);
            callableStatement.setString(2, question.getQuestion());
            callableStatement.setString(3, question.getType());
            callableStatement.setString(4, question.getTitle());
            callableStatement.setDate(5, question.getCreatedDate());
            callableStatement.registerOutParameter(6, Types.INTEGER);
            rowCount = callableStatement.executeUpdate();
            int questionId = callableStatement.getInt(6);
            for (Option option : optionList) {
                CallableStatement callableStatementOption = null;
                try {
                    if (!option.getOptionText().isEmpty()) {
                        callableStatementOption = connection.prepareCall("{call INSERT_OPTIONS(?,?,?,?)}");
                        callableStatementOption.setInt(1, questionId);
                        callableStatementOption.setString(2, option.getOptionText());
                        callableStatementOption.setInt(3, option.getOptionId());
                        callableStatementOption.setInt(4, option.getDisplayOrder());
                        int rowAdded = callableStatementOption.executeUpdate();
                        rowCount = rowCount + rowAdded;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (callableStatementOption != null) {
                        try {
                            callableStatementOption.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (callableStatement != null) {
                try {
                    callableStatement.close();
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
        return rowCount;
    }

    @Override
    public List<Question> findAllTitles(String instructorUserName) {
        List<Question> instructorQuestionDetails = null;
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		DatabaseProperties databaseProperties = new DatabaseProperties();
		try
		{
			instructorQuestionDetails = new ArrayList<>();
			String DRIVER = databaseProperties.getDriver();
			String DB_URL = databaseProperties.getDbURL();
			String USER = databaseProperties.getDbUserName();
			String PASSWORD = databaseProperties.getDbPassword();
		    Class.forName(DRIVER);
			logger.info("Connecting to the selected database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			logger.info("Connected to the database successfully...");
			preparedStatement = connection.prepareStatement(QuestionsQuery.GET_QUESTION_TITLE);
			preparedStatement.setString(1, instructorUserName);
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) 
			{
				return null;
			}
			do
			{
                String questionTitle = resultSet.getString("questionTitle");
                Integer questionId = resultSet.getInt("questionId");
                Date questionDate = resultSet.getDate("dateCreated");
				instructorQuestionDetails.add(new Question(questionTitle, questionId, questionDate ));
				//logger.info(questionTitle);
			}
			while (resultSet.next());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
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
		return instructorQuestionDetails;
	}

    @Override
    public int removeQuestion(String instructorUserName, Question question) {
		int rowCount = 0;
		int exists = 0;
		int questionId;
		ResultSet resultSet = null;
		
        String questionType = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        DatabaseProperties databaseProperties = new DatabaseProperties();
        try
		{
			String DRIVER = databaseProperties.getDriver();
			String DB_URL = databaseProperties.getDbURL();
			String USER = databaseProperties.getDbUserName();
			String PASSWORD = databaseProperties.getDbPassword();
		    Class.forName(DRIVER);
			logger.info("Connecting to the selected database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			logger.info("Connected to the database successfully...");
			preparedStatement = connection.prepareStatement(QuestionsQuery.SEARCH_OCCURENCE);
			questionId = question.getQuestionId();
			logger.info("question id in DAO is "+questionId);
			preparedStatement.setInt(1, questionId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet != null)
			{
				exists = 1;
			}
            if(exists == 1)
            {
                preparedStatement = connection.prepareStatement(QuestionsQuery.DELETE_QUESTION_OPTIONS);
                questionId = question.getQuestionId();
                preparedStatement.setInt(1, questionId);
                rowCount = preparedStatement.executeUpdate();
            }
            preparedStatement = connection.prepareStatement(QuestionsQuery.DELETE_QUESTION);
            questionId = question.getQuestionId();
            preparedStatement.setInt(1, questionId);
            rowCount = preparedStatement.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
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
        return rowCount;
    }

    @Override
    public List<Question> findAllSortedTitlesByTitles(String instructorUserName) {
        List<Question> sortedTitlesList = null;
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		DatabaseProperties databaseProperties = new DatabaseProperties();
		try
		{
			sortedTitlesList = new ArrayList<>();
			String DRIVER = databaseProperties.getDriver();
			String DB_URL = databaseProperties.getDbURL();
			String USER = databaseProperties.getDbUserName();
			String PASSWORD = databaseProperties.getDbPassword();
		    Class.forName(DRIVER);
			logger.info("Connecting to the selected database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			logger.info("Connected to the database successfully...");
			preparedStatement = connection.prepareStatement(QuestionsQuery.GET_SORTED_TITLE);
			preparedStatement.setString(1, instructorUserName);
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) 
			{
				return null;
			}
			do
			{
                String questionTitle = resultSet.getString("questionTitle");
				sortedTitlesList.add(new Question(questionTitle));
				//logger.info(questionTitle);
			}
			while (resultSet.next());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
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
		return sortedTitlesList;
    }

    @Override
    public List<Question> findAllSortedTitlesByDates(String instructorUserName) {
		Question question = new Question();
        List<Question> sortedTitlesListByDates = null;
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		DatabaseProperties databaseProperties = new DatabaseProperties();
		try
		{
			sortedTitlesListByDates = new ArrayList<>();
			String DRIVER = databaseProperties.getDriver();
			String DB_URL = databaseProperties.getDbURL();
			String USER = databaseProperties.getDbUserName();
			String PASSWORD = databaseProperties.getDbPassword();
		    Class.forName(DRIVER);
			logger.info("Connecting to the selected database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			logger.info("Connected to the database successfully...");
			preparedStatement = connection.prepareStatement(QuestionsQuery.GET_SORTED_TITLE_DATE);
			preparedStatement.setString(1, instructorUserName);
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) 
			{
				return null;
			}
			do
			{
				String questionTitle = resultSet.getString("questionTitle");
				Date questionDate = resultSet.getDate("dateCreated");
				question.setTitle(questionTitle);
				question.setCreatedDate(questionDate);
				sortedTitlesListByDates.add(question);
				//logger.info(questionTitle);
			}
			while (resultSet.next());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
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
		return sortedTitlesListByDates;
    }

    @Override
    public List<Question> findAllQuestions(String instructorUserName) {
        List<Question> instructorQuestions = null;
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		DatabaseProperties databaseProperties = new DatabaseProperties();
		try
		{
			
			instructorQuestions = new ArrayList<>();
			String DRIVER = databaseProperties.getDriver();
			String DB_URL = databaseProperties.getDbURL();
			String USER = databaseProperties.getDbUserName();
			String PASSWORD = databaseProperties.getDbPassword();
		    Class.forName(DRIVER);
			logger.info("Connecting to the selected database...");
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			logger.info("Connected to the database successfully...");
			preparedStatement = connection.prepareStatement(QuestionsQuery.GET_ALL_QUESTIONS);
			preparedStatement.setString(1, instructorUserName);
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()) 
			{
				return null;
			}
			do
			{
                String questionTitle = resultSet.getString("questionTitle");
                Integer questionId = resultSet.getInt("questionId");
                Date questionDate = resultSet.getDate("dateCreated");
                String question = resultSet.getString("question");
                String questionType = resultSet.getString("questionType");
				instructorQuestions.add(new Question(questionTitle, questionId, questionDate, question, questionType ));
				logger.info("finding question data :"+ questionTitle+" "+questionId+" "+questionDate+" "+question+" "+questionType);
			}
			while (resultSet.next());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
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
		return instructorQuestions;
    }
}
