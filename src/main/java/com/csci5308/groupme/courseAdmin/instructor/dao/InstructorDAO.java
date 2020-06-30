package com.csci5308.groupme.courseAdmin.instructor.dao;

import com.csci5308.groupme.courseAdmin.instructor.model.Instructor;

public interface InstructorDAO {

    public Instructor findByUserName(String userName) throws Exception;
    
    public int save(Instructor instructor) throws Exception;
}
