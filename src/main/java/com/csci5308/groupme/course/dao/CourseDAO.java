package com.csci5308.groupme.course.dao;

import java.util.List;


import com.csci5308.groupme.course.model.Course;

public interface CourseDAO {
	
	List<Course> findAllCourses() throws Exception;

	List<Course> getCoursesByUserNameAndRole(String userName, String roleName) throws Exception;

	List<Course> findCoursesByStudentUserName(String studentUserName) throws Exception;

	List<Course> findCoursesByInstructorAndTA(String studentUserName) throws Exception;

	public String insertCourse();

	public List<Course> findByCourseCode();

	public List<Course> findByCourseName();

	public List<Course> findByCourseCrn();

	public String deleteCourse();

	public String updateCourse();



}
