package com.csci5308.groupme.course.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.csci5308.groupme.course.model.Course;

import ch.qos.logback.classic.Logger;

@Repository
public class CourseDAOImpl implements ICourseDAO {

	Logger logger = (Logger) LoggerFactory.getLogger(CourseDAOImpl.class);

	private static String dataBase = "groupme";
	private static String URL = "jdbc:mysql://localhost:3306/" + dataBase;
	private static String userName = "root";
	private static String userPassword = "root";

	private Statement statement = null;
	private Connection connection = null;
	private ResultSet resultSet = null;

	private static String selectAllQuery = "SELECT * FROM course";

	@Override
	public List<Course> findAllCourses() throws Exception {

		List<Course> retrievedCourseList = new ArrayList<Course>();

		connection = DriverManager.getConnection(URL, userName, userPassword);
		statement = connection.createStatement();

		resultSet = statement.executeQuery(selectAllQuery);
		while (resultSet.next()) {
			String courseName = resultSet.getString("courseName");
			String courseCode = resultSet.getString("courseCode");
			Integer courseCrn = resultSet.getInt("crn");
			retrievedCourseList.add(new Course(courseCode, courseName, courseCrn));
		}

		resultSet.close();
		statement.close();
		connection.close();
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
