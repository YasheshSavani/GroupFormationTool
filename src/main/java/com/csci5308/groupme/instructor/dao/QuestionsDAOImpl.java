package com.csci5308.groupme.instructor.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.instructor.model.Option;
import com.csci5308.groupme.instructor.model.Question;
import java.sql.CallableStatement;
import java.sql.Types;
import sql.QuestionsQuery;

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
            logger.info(question.getCreatedDate().toString());
            callableStatement = connection.prepareCall("{call INSERT_QUESTION(?,?,?,?,?,?)}");
            callableStatement.setString(1, instructorUserName);
            callableStatement.setString(2, question.getQuestion());
            callableStatement.setString(3, question.getType());
            callableStatement.setString(4, question.getTitle());
            callableStatement.setDate(5, question.getCreatedDate());
            callableStatement.registerOutParameter(6, Types.INTEGER);
            rowCount = preparedStatement.executeUpdate();

            int questionId = callableStatement.getInt(6);
            for (Option option : optionList) {
                CallableStatement callableStatementOption = null;
                try {
                    if (option.getOptionText() != null) {
                        callableStatementOption = connection.prepareCall("{call INSERT_OPTIONS(?,?,?,?)}");

                        callableStatementOption.setInt(1, questionId);
                        callableStatementOption.setString(2, option.getOptionText());
                        callableStatementOption.setInt(3, option.getOptionId());
                        callableStatementOption.setInt(4, option.getDisplayOrder());
                        callableStatementOption.executeUpdate();
                        rowCount++;
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
