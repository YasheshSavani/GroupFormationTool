package com.csci5308.groupme.course.courseadmin.teaching_assistant.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.csci5308.groupme.course.courseadmin.teaching_assistant.dao.TeachingAssistantDaoMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.csci5308.groupme.course.courseadmin.teaching_assistant.dao.TeachingAssistantDao;

import errors.EditCodes;

@ExtendWith(SpringExtension.class)
public class TeachingAssistantServiceTest {

    private TeachingAssistantDao taDao;
    private TeachingAssistantServiceImpl teachingAssistantService;
   
    public TeachingAssistantServiceTest() {
    	taDao = new TeachingAssistantDaoMock();
    	teachingAssistantService = new TeachingAssistantServiceImpl(taDao);
    }

    @Test
    void assignTAToCourseTest() throws Exception {
        String email = "testta@gmail.com";
        String courseCode = "TSCI0000";
        int assignmentConfirmation = teachingAssistantService.assignTAToCourse(email, courseCode);
        assertEquals(EditCodes.SUCCESS, assignmentConfirmation);
    }

}
