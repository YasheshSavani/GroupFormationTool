package com.csci5308.groupme.course.dao;

import java.util.List;


import com.csci5308.groupme.course.model.Course;

public interface CourseDAO {

	public String insertCourse();
	
	public List<Course> findAllCourses() throws Exception;

	public List<Course> findByCourseCode();

	public List<Course> findByCourseName();

	public List<Course> findByCourseCrn();

	public String deleteCourse();

	public String updateCourse();

}
