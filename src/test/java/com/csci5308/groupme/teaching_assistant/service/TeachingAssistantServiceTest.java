package com.csci5308.groupme.teaching_assistant.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import errors.EditCodes;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class TeachingAssistantServiceTest {

    private TeachingAssistantService taService;

    public TeachingAssistantServiceTest() {
        taService = new TeachingAssistantServiceMock();
    }

    @Test
    void findByTAEmailIdTest() throws Exception {
        String email = "testta@gmail.com";
        String courseCode = "TSCI0000";
        int assignmentConfirmation = taService.assignTAToCourse(email, courseCode);
        assertEquals(EditCodes.SUCCESS, assignmentConfirmation);
    }

}
