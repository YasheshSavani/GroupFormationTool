package com.csci5308.groupme.course.dao;

import java.util.List;

import com.csci5308.groupme.course.model.Course;

public interface CourseDetailsDao {

	List<Course> findAllCourses() throws Exception;

	List<Course> getCoursesByUserNameAndRole(String userName, String roleName) throws Exception;

	List<Course> findCoursesByStudentUserName(String studentUserName) throws Exception;

	List<Course> findCoursesByInstructor(String instructorUserName) throws Exception;

	Course findCourseByCourseCode(String courseCode) throws Exception;

}
