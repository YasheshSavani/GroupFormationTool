package com.csci5308.groupme.course.dao;

import ch.qos.logback.classic.Logger;
import com.csci5308.groupme.course.model.Course;
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
public class CourseDAOImpl implements CourseDAO {

    private final Logger logger = (Logger) LoggerFactory.getLogger(CourseDAOImpl.class);

    @Override
    public List<Course> findAllCourses() throws Exception {

        List<Course> retrievedCourseList = null;
        ResultSet resultSet = null;
        Connection connection = null;
        Statement statement = null;

        try {
            retrievedCourseList = new ArrayList<>();

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
            String selectAllQuery = "SELECT * FROM course";
            resultSet = statement.executeQuery(selectAllQuery);

            logger.info("Execution of Course Select Query is Completed...");

            while (resultSet.next()) {
                String courseName = resultSet.getString("courseName");
                String courseCode = resultSet.getString("courseCode");
                Integer courseCrn = resultSet.getInt("crn");
                retrievedCourseList.add(new Course(courseCode, courseName, courseCrn));
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

        return retrievedCourseList;
    }

    @Override
    public List<Course> findByCourseCode() {
        return null;
        // TODO Auto-generated method stub

    }

    @Override
    public List<Course> findByCourseName() {
        return null;
        // TODO Auto-generated method stub

    }

    @Override
    public List<Course> findByCourseCrn() {
        return null;
        // TODO Auto-generated method stub

    }

    @Override
    public String insertCourse() {
        return null;
        // TODO Auto-generated method stub

    }

    @Override
    public String deleteCourse() {
        return null;
        // TODO Auto-generated method stub

    }

    @Override
    public String updateCourse() {
        return null;
        // TODO Auto-generated method stub

    }

}
