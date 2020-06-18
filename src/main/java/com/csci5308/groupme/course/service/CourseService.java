package com.csci5308.groupme.course.service;

import com.csci5308.groupme.course.model.Course;

import java.util.List;

public interface CourseService {

    public List<Course> findAllCourses() throws Exception;

    public Course getByCourseCode(String courseCode) throws Exception;

    public List<Course> getCoursesByUserNameAndRole(String userName, String roleName) throws Exception;

    public List<Course> findCoursesByStudentUserName(String studentUserName) throws Exception;

    public int createCourse(Course course) throws Exception;

    public int delete(String courseCode) throws Exception;

    public List<Course> findCoursesByInstructor(String instructorUserName) throws Exception;
}
