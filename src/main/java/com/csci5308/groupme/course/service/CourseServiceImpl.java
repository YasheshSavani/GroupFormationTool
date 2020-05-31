package com.csci5308.groupme.course.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci5308.groupme.course.dao.CourseDAOImpl;
import com.csci5308.groupme.course.dao.ICourseDAO;
import com.csci5308.groupme.course.model.Course;

@Service
public class CourseServiceImpl implements ICourseService {

	private ICourseDAO iCourseDAO = new CourseDAOImpl();
	
	@Override
	public List<Course> findAllCourses() throws Exception {
		
		List<Course> getCourseDatafromDAO = iCourseDAO.findAllCourses();

		return getCourseDatafromDAO;
	}
	
	

}
