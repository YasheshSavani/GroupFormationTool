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
import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserService;

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

	@Autowired
	private UserService userService;

	@Override
	public String assignInstructorToCourse(String emailId, String courseCode) throws Exception {
		String message = "";
		Instructor instructor = instructorService.getByEmail(emailId);
		Course course = courseService.getByCourseCode(courseCode);
		if (null == course)
			message = "Course not found!";
		else if (null == instructor) {
			int status = makeUserAsInstructor(emailId);
			if (status == EditCodes.USERNAME_DOES_NOT_EXIST) {
				message = "Instructor not found! Please check again!";
			} else if (status == 1) {				
		         message = this.assignInstructorToCourse(emailId, courseCode);
			} else {
				message = "Something went wrong! Could not create class for the given instructor and course";
			}
		} else {
			int status = adminDao.createClass(instructor.getUserName(), course.getCourseCode());
			logger.info("Status {}", status);
			if (status == EditCodes.CLASS_ALREADY_CREATED)
				message = "Record already exists!";
			else if (status == 1)
				message = "Instructor assigned to the course!";
			else
				message = "Something went wrong! The server could not insert the record into the database!";
		}
		return message;
	}

	@Override
	public int makeUserAsInstructor(String emailId) throws Exception {
		User user = userService.getByEmail(emailId);
		Instructor instructor = new Instructor();
		int status = 0;
		if (null == user) {
			status = EditCodes.USERNAME_DOES_NOT_EXIST;
		} else {
			logger.info(user.getUserName());
			instructor.setUserName(user.getUserName());
			status = instructorService.createInstructor(instructor);
		}
		return status;
	}
}
