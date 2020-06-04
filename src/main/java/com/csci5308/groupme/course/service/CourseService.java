package com.csci5308.groupme.course.service;

import java.util.List;
import com.csci5308.groupme.course.model.Course;

public interface CourseService {

	public List<Course> findAllCourses() throws Exception;

	public List<Course> getCoursesByUserNameAndRole(String userName, String roleName) throws Exception;

	public List<Course> findCoursesByStudentUserName(String studentUserName) throws Exception;
	
	public int createCourse(Course course) throws Exception;
	
	public int delete(String courseCode) throws Exception;
}
