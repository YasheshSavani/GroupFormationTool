package com.csci5308.groupme.course.dao;

import com.csci5308.groupme.course.model.Course;

import java.util.List;

public interface CourseDAO {

    List<Course> findAllCourses() throws Exception;

    List<Course> getCoursesByUserNameAndRole(String userName, String roleName) throws Exception;

    List<Course> findCoursesByStudentUserName(String studentUserName) throws Exception;

    List<Course> findCoursesByInstructorAndTA(String userName) throws Exception;

    List<Course> findCoursesByInstructor(String instructorUserName) throws Exception;

    Course findCourseByCourseCode(String courseCode) throws Exception;

    public int save(Course course) throws Exception;

    public int remove(String courseCode) throws Exception;

}
