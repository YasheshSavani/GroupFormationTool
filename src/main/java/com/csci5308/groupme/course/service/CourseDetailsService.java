package com.csci5308.groupme.course.service;

import java.util.List;

import com.csci5308.groupme.course.model.Course;

public interface CourseDetailsService {

	public List<Course> getCoursesByUserNameAndRole(String userName, String roleName) throws Exception;

	public List<Course> findCoursesByStudentUserName(String studentUserName) throws Exception;

	public List<Course> findAllCourses() throws Exception;

	public Course getByCourseCode(String courseCode) throws Exception;
	
	public List<Course> findCoursesByInstructor(String instructorUserName) throws Exception;

}
