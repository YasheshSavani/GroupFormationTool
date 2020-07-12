package com.csci5308.groupme.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci5308.groupme.course.dao.CourseDetailsDao;
import com.csci5308.groupme.course.model.Course;

@Service
public class CourseDetailsServiceImpl implements CourseDetailsService {

	@Autowired
	CourseDetailsDao courseDetailsDao;

	@Override
	public List<Course> findAllCourses() throws Exception {
		return courseDetailsDao.findAllCourses();
	}
	
	 @Override
	    public List<Course> getCoursesByUserNameAndRole(String userName, String roleName) throws Exception {
	        return courseDetailsDao.getCoursesByUserNameAndRole(userName, roleName);
	    }

	    @Override
	    public Course getByCourseCode(String courseCode) throws Exception {
	        Course course = courseDetailsDao.findCourseByCourseCode(courseCode);
	        return course;
	    }

	    @Override
	    public List<Course> findCoursesByStudentUserName(String studentUserName) throws Exception {
	        return courseDetailsDao.findCoursesByStudentUserName(studentUserName);
	    }
	    
	    @Override
	    public List<Course> findCoursesByInstructor(String instructorUserName) throws Exception {
	        List<Course> coursesList = courseDetailsDao.findCoursesByInstructor(instructorUserName);
	        return coursesList;
	    }

}
