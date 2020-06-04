package com.csci5308.groupme.course.dao;

import java.util.List;


import com.csci5308.groupme.course.model.Course;

public interface CourseDAO {

	public List<Course> findAllCourses() throws Exception;

	public List<Course> getCoursesByUserNameAndRole(String userName, String roleName) throws Exception;

	public List<Course> findCoursesByStudentUserName(String studentUserName) throws Exception;
	
	public int save(Course course) throws Exception;
	
	public int remove(String courseCode) throws Exception;
	
}
