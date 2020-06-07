package com.csci5308.groupme.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci5308.groupme.admin.dao.AdminDao;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseService;
import com.csci5308.groupme.instructor.model.Instructor;
import com.csci5308.groupme.instructor.service.InstructorService;

@Service
public class AdminServiceImpl implements AdminService {

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
		if (null == instructor)
			message = "Instructor email not found! Please check again!";
		else if (null == course) {
			message = "Course not found!";
		} else {
			int status = adminDao.createClass(instructor.getUserName(), course.getCourseCode());
			if (status == 1)
				message = "Instructor assigned to the course!";
			else
				message = "Something went wrong! The server could not insert the record into the database!";
		}
		return message;
	}

}
