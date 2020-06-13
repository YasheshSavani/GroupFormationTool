package com.csci5308.groupme.instructor.dao;

import ch.qos.logback.classic.Logger;
import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.instructor.model.Instructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import sql.InstructorQuery;
import sql.UserQuery;

import java.sql.*;

@Repository
public class InstructorDAOImpl implements InstructorDAO {

    private final Logger logger = (Logger) LoggerFactory.getLogger(InstructorDAOImpl.class);

    @Override
    public Instructor findByUserName(String userName) throws Exception {
        Instructor instructor = new Instructor();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        DatabaseProperties databaseProperties = SystemConfig.instance().getDatabaseProperties();
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
            if (!resultSet.next()) {
                return null;
            }
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
