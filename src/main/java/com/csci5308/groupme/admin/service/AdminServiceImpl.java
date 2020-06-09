package com.csci5308.groupme.admin.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci5308.groupme.admin.dao.AdminDao;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseService;
import com.csci5308.groupme.instructor.model.Instructor;
import com.csci5308.groupme.instructor.service.InstructorService;
import com.csci5308.groupme.instructor.service.InstructorServiceImpl;

import ch.qos.logback.classic.Logger;
import errors.EditCodes;
import errors.SqlErrors;

@Service
public class AdminServiceImpl implements AdminService {

	private final Logger logger = (Logger) LoggerFactory.getLogger(InstructorServiceImpl.class);
	
	@Autowired
	private AdminDao adminDao;

	@Autowired
	private InstructorService instructorService;

	@Autowired
	private CourseService courseService;

	@Override
	public String assignInstructorToCourse(String emailId, String courseCode) throws Exception {
		String message = "";
		Instructor instructor = instructorService.getByEmail(emailId);
		Course course = courseService.getByCourseCode(courseCode);
		if (null == course)
			message = "Course not found!";
		else if (null == instructor) {
			message = "Instructor email not found! Please check again!";
		} else {
			int status = adminDao.createClass(instructor.getUserName(), course.getCourseCode());
			logger.info("Status {}", status);
			if(status == EditCodes.CLASS_ALREADY_CREATED)
				message = "Record already exists!";
			else if (status == 1)
				message = "Instructor assigned to the course!";
			else
				message = "Something went wrong! The server could not insert the record into the database!";
		}
		return message;
	}

}
