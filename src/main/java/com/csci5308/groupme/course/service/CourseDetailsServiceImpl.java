package com.csci5308.groupme.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci5308.groupme.course.dao.CourseDetailsDAO;
import com.csci5308.groupme.course.model.Course;

@Service
public class CourseDetailsServiceImpl implements CourseDetailsService {

	@Autowired
	CourseDetailsDAO courseDetailsDAO;

	@Override
	public List<Course> findAllCourses() throws Exception {
		return courseDetailsDAO.findAllCourses();
	}
	
	 @Override
	    public List<Course> getCoursesByUserNameAndRole(String userName, String roleName) throws Exception {
	        return courseDetailsDAO.getCoursesByUserNameAndRole(userName, roleName);
	    }

	    @Override
	    public Course getByCourseCode(String courseCode) throws Exception {
	        Course course = courseDetailsDAO.findCourseByCourseCode(courseCode);
	        return course;
	    }

	    @Override
	    public List<Course> findCoursesByStudentUserName(String studentUserName) throws Exception {
	        return courseDetailsDAO.findCoursesByStudentUserName(studentUserName);
	    }
	    
	    @Override
	    public List<Course> findCoursesByInstructor(String instructorUserName) throws Exception {
	        List<Course> coursesList = courseDetailsDAO.findCoursesByInstructor(instructorUserName);
	        return coursesList;
	    }

}
