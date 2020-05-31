package com.csci5308.groupme.course.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.csci5308.datasource.DataSource;
import com.csci5308.datasource.MySqlDataSource;
import com.csci5308.groupme.course.model.Course;

import ch.qos.logback.classic.Logger;

@Repository
public class CourseDAOImpl implements CourseDAO {

	Logger logger = (Logger) LoggerFactory.getLogger(CourseDAOImpl.class);

	private DataSource dataSource;

	private Statement statement = null;
	private Connection connection = null;
	private ResultSet resultSet = null;

	private static String selectAllQuery = "SELECT * FROM course";

	@Override
	public List<Course> findAllCourses() throws Exception {

		List<Course> retrievedCourseList = null;

		try {
			dataSource = new MySqlDataSource();
			retrievedCourseList = new ArrayList<Course>();
			connection = dataSource.openConnection();
			statement = connection.createStatement();

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
			statement.close();
			logger.info("Statement and ResultSet Closed...");
			dataSource.closeConnection();
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
