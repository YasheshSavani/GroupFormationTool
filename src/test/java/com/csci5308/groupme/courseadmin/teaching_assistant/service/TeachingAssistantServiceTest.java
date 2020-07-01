package com.csci5308.groupme.courseadmin.teaching_assistant.service;

import com.csci5308.groupme.courseadmin.teaching_assistant.dao.TeachingAssistantDaoMock;
import errors.EditCodes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class TeachingAssistantServiceTest {

    private TeachingAssistantDaoMock taDAO;

    public TeachingAssistantServiceTest() {
        taDAO = new TeachingAssistantDaoMock();
    }

    @Test
    void findByTAEmailIdTest() throws Exception {
        String email = "testta@gmail.com";
        String courseCode = "TSCI0000";
        int assignmentConfirmation = taDAO.createTAForCourse(email, courseCode);
        assertEquals(EditCodes.SUCCESS, assignmentConfirmation);
    }

}
