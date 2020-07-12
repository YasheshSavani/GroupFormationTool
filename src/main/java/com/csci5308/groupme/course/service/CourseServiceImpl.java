package com.csci5308.groupme.course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csci5308.groupme.course.dao.CourseDao;
import com.csci5308.groupme.course.model.Course;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDao courseDao;

    @Override
    public int createCourse(Course course) throws Exception {
        int status = courseDao.save(course);
        return status;
    }

    @Override
    public int delete(String courseCode) throws Exception {
        int rowCount = courseDao.remove(courseCode);
        return rowCount;
    }

}
