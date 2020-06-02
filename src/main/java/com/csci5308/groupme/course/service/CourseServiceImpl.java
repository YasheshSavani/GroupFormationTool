package com.csci5308.groupme.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci5308.groupme.course.dao.CourseDAO;
import com.csci5308.groupme.course.model.Course;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDAO courseDAO;;

	@Override
	public List<Course> findAllCourses() throws Exception {

		List<Course> getCourseDatafromDAO = courseDAO.findAllCourses();

		return getCourseDatafromDAO;
	}

}
