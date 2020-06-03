package com.csci5308.groupme.course.service;

import java.util.List;
import com.csci5308.groupme.course.model.Course;

public interface CourseService {

	public List<Course> findAllCourses() throws Exception;
}
