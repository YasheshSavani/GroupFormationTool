package com.csci5308.groupme.course.dao;

import ch.qos.logback.classic.Logger;
import com.csci5308.groupme.course.model.Course;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import sql.CourseQuery;

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

@Repository
public class CourseDAOImpl implements CourseDAO {

    private final Logger logger = (Logger) LoggerFactory.getLogger(CourseDAOImpl.class);

    @Override
    public List<Course> findAllCourses() throws Exception {

        List<Course> retrievedCourseList = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            retrievedCourseList = new ArrayList<>();

            String databasePropertiesFilePath = "src/main/resources/database.properties";
            Reader dbPropertiesReader = new BufferedReader(new FileReader(databasePropertiesFilePath));
            Properties properties = new Properties();
            properties.load(dbPropertiesReader);

//            String JDBC_DRIVER = properties.getProperty("development.driver");
            String DB_URL = properties.getProperty("development.url");
            String USER = properties.getProperty("development.username");
            String PASSWORD = properties.getProperty("development.password");

            logger.info("Connecting to the selected database...");

            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to the database successfully...");

            preparedStatement = connection.prepareStatement(CourseQuery.SELECT_ALL_COURSE);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.getFetchSize() > 0) {
                while (resultSet.next()) {
                    String courseName = resultSet.getString("courseName");
                    String courseCode = resultSet.getString("courseCode");
                    Integer courseCrn = resultSet.getInt("crn");
                    retrievedCourseList.add(new Course(courseCode, courseName, courseCrn));
                }
            } else {
                return null;
            }

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

    @Override
    public List<Course> getCoursesByUserNameAndRole(String userName, String roleName) throws Exception {

        List<Course> courseAdminCoursesList = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            courseAdminCoursesList = new ArrayList<>();

            String databasePropertiesFilePath = "src/main/resources/database.properties";
            Reader dbPropertiesReader = new BufferedReader(new FileReader(databasePropertiesFilePath));
            Properties properties = new Properties();
            properties.load(dbPropertiesReader);

//            String JDBC_DRIVER = properties.getProperty("development.driver");
            String DB_URL = properties.getProperty("development.url");
            String USER = properties.getProperty("development.username");
            String PASSWORD = properties.getProperty("development.password");

            logger.info("Connecting to the selected database...");

            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to the database successfully...");

            if (roleName.equals("ROLE_TA")) {
                preparedStatement = connection.prepareStatement(CourseQuery.SELECT_COURSES_BY_USERNAME_TA);
                preparedStatement.setString(1, userName);
                resultSet = preparedStatement.executeQuery();
            } else if (roleName.equals("ROLE_INSTRUCTOR")) {
                preparedStatement = connection.prepareStatement(CourseQuery.SELECT_COURSES_BY_USERNAME_INSTRUCTOR);
                preparedStatement.setString(1, userName);
                resultSet = preparedStatement.executeQuery();
            }
            if (resultSet.getFetchSize() > 0) {
                while (resultSet.next()) {
                    PreparedStatement getCourseStatement = null;
                    ResultSet courseResultSet = null;
                    try {
                        String retrievedCourseCode = resultSet.getString("courseCode");
                        getCourseStatement = connection.prepareStatement(CourseQuery.SELECT_COURSE_BY_COURSE_CODE);
                        getCourseStatement.setString(1, retrievedCourseCode);
                        courseResultSet = getCourseStatement.executeQuery();

                        if (courseResultSet.getFetchSize() > 0) {
                            String courseCode = courseResultSet.getString("courseCode");
                            String courseName = courseResultSet.getString("courseName");
                            Integer courseCrn = courseResultSet.getInt("crn");
                            courseAdminCoursesList.add(new Course(courseCode, courseName, courseCrn));

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        courseResultSet.close();
                        if (getCourseStatement != null) {
                            try {
                                getCourseStatement.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            } else {
                return null;
            }
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
        return courseAdminCoursesList;
    }
}
