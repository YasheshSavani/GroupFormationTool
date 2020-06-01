package com.csci5308.groupme.student.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentTest {

    Student student = new StudentMock().getStudent();

    @Test
    public void getUserNameTest() {
        assertEquals("kharechaB00", student.getUserName());
    }

    @Test
    public void getBannerIDTest() {
        assertEquals("B00xxxxxx", student.getBannerID());
    }

}