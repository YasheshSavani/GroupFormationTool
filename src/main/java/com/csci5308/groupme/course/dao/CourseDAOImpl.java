package com.csci5308.groupme.course.dao;

import java.util.List;
import com.csci5308.groupme.course.model.Course;

public class CourseDAOImpl implements ICourseDAO{

	private static String selectAllQuery = "SELECT * FROM COURSE";
	
	@Override
	public List<Course> findAll() {

		return null;
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
