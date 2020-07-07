package com.csci5308.groupme.course.student.model;

import com.csci5308.groupme.course.student.model.Student;

public class StudentMock {

    Student student;

    public StudentMock() {
        student = new Student("kharechaB00", "B00xxxxxx");
    }

    public Student getStudent() {
        return student;
    }
}
