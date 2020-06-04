package com.csci5308.groupme.course.dao;

import ch.qos.logback.classic.Logger;
import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.course.model.Course;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import sql.CourseQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseDAOImpl implements CourseDAO {

    private final Logger logger = (Logger) LoggerFactory.getLogger(CourseDAOImpl.class);
    DatabaseProperties databaseProperties = new DatabaseProperties();

    @Override
    public List<Course> findAllCourses() throws Exception {

        List<Course> retrievedCourseList = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            retrievedCourseList = new ArrayList<>();

            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();

            logger.info("Connecting to the selected database...");

            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to the database successfully...");

            preparedStatement = connection.prepareStatement(CourseQuery.SELECT_ALL_COURSE);

            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            do {
                String courseName = resultSet.getString("courseName");
                String courseCode = resultSet.getString("courseCode");
                Integer courseCrn = resultSet.getInt("crn");
                retrievedCourseList.add(new Course(courseCode, courseName, courseCrn));

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

        return retrievedCourseList;
    }

    @Override
    public List<Course> getCoursesByUserNameAndRole(String userName, String roleName) throws Exception {

        List<Course> courseAdminCoursesList = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            courseAdminCoursesList = new ArrayList<>();

            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();

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
            if (!resultSet.next()) {
                return null;
            }
            do {
                PreparedStatement getCourseStatement = null;
                ResultSet courseResultSet = null;
                try {
                    String retrievedCourseCode = resultSet.getString("courseCode");
                    getCourseStatement = connection.prepareStatement(CourseQuery.SELECT_COURSE_BY_COURSE_CODE);
                    getCourseStatement.setString(1, retrievedCourseCode);
                    courseResultSet = getCourseStatement.executeQuery();

                    if (!courseResultSet.next()) {
                        return null;
                    }
                    do {
                        String courseCode = resultSet.getString("courseCode");
                        Course course = findCourseByCourseCode(courseCode);
                        courseAdminCoursesList.add(course);

                    } while (courseResultSet.next());
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
            while (resultSet.next());
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

    @Override
    public List<Course> findCoursesByStudentUserName(String studentUserName) throws Exception {

        List<Course> retrievedCourseList = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            retrievedCourseList = new ArrayList<>();

            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();

            logger.info("Connecting to the selected database...");

            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to the database successfully...");

            preparedStatement = connection.prepareStatement(CourseQuery.SELECT_COURSE_STUDENT_ENROLLED_IN_BY_USERNAME_STUDENT);
            preparedStatement.setString(1, studentUserName);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            do {
                String courseCode = resultSet.getString("courseCode");
                Course course = findCourseByCourseCode(courseCode);
                retrievedCourseList.add(course);
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
        return retrievedCourseList;
    }

    public Course findCourseByCourseCode(String courseCode) throws Exception {
        ResultSet courseResultSet = null;
        Connection connection;
        PreparedStatement getCourseStatement = null;
        Course course = new Course();
        try {

            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();

            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            getCourseStatement = connection.prepareStatement(CourseQuery.SELECT_COURSE_BY_COURSE_CODE);
            getCourseStatement.setString(1, courseCode);
            courseResultSet = getCourseStatement.executeQuery();

            if (!courseResultSet.next()) {
                return null;
            }
            String courseName = courseResultSet.getString("courseName");
            Integer courseCrn = courseResultSet.getInt("crn");
            course = new Course(courseCode, courseName, courseCrn);
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
        return course;
    }
}
