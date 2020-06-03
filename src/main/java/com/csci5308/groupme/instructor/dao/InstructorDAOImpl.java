package com.csci5308.groupme.instructor.dao;

import ch.qos.logback.classic.Logger;
import com.csci5308.groupme.teaching_assistant.model.TeachingAssistant;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Repository
public class InstructorDAOImpl implements InstructorDAO {

    private final Logger logger = (Logger) LoggerFactory.getLogger(InstructorDAOImpl.class);

    @Override
    public List<TeachingAssistant> findByTAEmailId(String emailId) throws Exception {

        List<TeachingAssistant> informationOfTA = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;

        try {
            informationOfTA = new ArrayList<>();

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

            statement = connection.createStatement();
            String selectUserByEmailQuery = "SELECT userName, firstName, lastName FROM user WHERE email=" + emailId;
            resultSet = statement.executeQuery(selectUserByEmailQuery);

            logger.info("Execution of Course Select Query is Completed...");

            while (resultSet.next()) {
                String userName = resultSet.getString("userName");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                informationOfTA.add(new TeachingAssistant(userName, firstName, lastName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            if (statement != null) {
                try {
                    statement.close();
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

        return informationOfTA;
    }
}
