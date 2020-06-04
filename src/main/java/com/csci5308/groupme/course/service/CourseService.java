package com.csci5308.groupme.course.service;

import java.util.List;
import com.csci5308.groupme.course.model.Course;

public interface CourseService {

	List<Course> findAllCourses() throws Exception;

	List<Course> getCoursesByUserNameAndRole(String userName, String roleName) throws Exception;

	List<Course> findCoursesByStudentUserName(String studentUserName) throws Exception;
}
