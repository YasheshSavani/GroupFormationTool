package com.csci5308.groupme.course.service;

import com.csci5308.groupme.course.dao.CourseDAO;
import com.csci5308.groupme.course.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDAO courseDAO;

    @Override
    public List<Course> findAllCourses() throws Exception {
        return courseDAO.findAllCourses();
    }

    @Override
    public List<Course> getCoursesByUserNameAndRole(String userName, String roleName) throws Exception {
        return courseDAO.getCoursesByUserNameAndRole(userName, roleName);
    }

    @Override
    public Course getByCourseCode(String courseCode) throws Exception {
        Course course = courseDAO.findCourseByCourseCode(courseCode);
        return course;
    }

    @Override
    public List<Course> findCoursesByStudentUserName(String studentUserName) throws Exception {
        return courseDAO.findCoursesByStudentUserName(studentUserName);
    }

    @Override
    public int createCourse(Course course) throws Exception {
        int status = courseDAO.save(course);
        return status;
    }

    @Override
    public int delete(String courseCode) throws Exception {
        int rowCount = courseDAO.remove(courseCode);
        return rowCount;
    }

    @Override
    public List<Course> findCoursesByInstructor(String instructorUserName) throws Exception {
        List<Course> coursesList = courseDAO.findCoursesByInstructor(instructorUserName);
        return coursesList;
    }
}
