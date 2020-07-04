package com.csci5308.groupme.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci5308.groupme.course.dao.CourseDAO;
import com.csci5308.groupme.course.model.Course;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDAO courseDAO;

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

}
