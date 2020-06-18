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

    @Override
    public int saveNonMCQ(String instructorUserName, Question question) {
        int rowCount = 0;
        PreparedStatement preparedStatement = null;
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
        try {
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
            if (!resultSet.next()) {
                return null;
            }
            do {
                String questionTitle = resultSet.getString("questionTitle");
                Integer questionId = resultSet.getInt("questionId");
                Date questionDate = resultSet.getDate("dateCreated");
                instructorQuestionDetails.add(new Question(questionTitle, questionId, questionDate));
                logger.info(questionTitle);
            } while (resultSet.next());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
                    logger.info("Connection to database Closed...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return instructorQuestionDetails;
    }

    @Override
    public int removeQuestion(String instructorUserName, Question question) {
        int status = 0;
        int questionId;
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
            preparedStatement = connection.prepareStatement(QuestionsQuery.DELETE_QUESTION);
            preparedStatement.setInt(1, question.getQuestionId());
            questionId = question.getQuestionId();
            preparedStatement.setInt(1, questionId);
            status = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return status;
    }

    @Override
    public List<Question> findAllSortedTitlesByTitles(String instructorUserName) {
        List<Question> sortedTitlesList = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        DatabaseProperties databaseProperties = new DatabaseProperties();
        try {
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
            if (!resultSet.next()) {
                return null;
            }
            do {
                String questionTitle = resultSet.getString("questionTitle");
                sortedTitlesList.add(new Question(questionTitle));
                logger.info(questionTitle);
            } while (resultSet.next());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
                    logger.info("Connection to database Closed...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sortedTitlesList;
    }

    @Override
    public List<Question> findAllSortedTitlesByDates(String instructorUserName) {
        List<Question> sortedTitlesListByDates = new ArrayList<Question>();
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        String DRIVER = databaseProperties.getDriver();
        String DB_URL = databaseProperties.getDbURL();
        String USER = databaseProperties.getDbUserName();
        String PASSWORD = databaseProperties.getDbPassword();
        try {
            Class.forName(DRIVER);
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to the database successfully...");
            preparedStatement = connection.prepareStatement(QuestionsQuery.GET_TITLES_SORTED_BY_DATE);
            preparedStatement.setString(1, instructorUserName);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            do {
                Question question = new Question();
                question.setTitle(resultSet.getString("questionTitle"));
                question.setCreatedDate(resultSet.getDate("dateCreated"));
                sortedTitlesListByDates.add(question);
            } while (resultSet.next());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
                    logger.info("Connection to database Closed...");
                } catch (Exception e) {
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
        try {
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
            if (!resultSet.next()) {
                return null;
            }
            do {
                String questionTitle = resultSet.getString("questionTitle");
                Integer questionId = resultSet.getInt("questionId");
                Date questionDate = resultSet.getDate("dateCreated");
                String question = resultSet.getString("question");
                String questionType = resultSet.getString("questionType");
                instructorQuestions.add(new Question(questionTitle, questionId, questionDate, question, questionType));
                logger.info("finding question data :" + questionTitle + " " + questionId + " " + questionDate + " "
                        + question + " " + questionType);
            } while (resultSet.next());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
                    logger.info("Connection to database Closed...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return instructorQuestions;
    }
}
