package com.csci5308.groupme.instructor.service;

import com.csci5308.groupme.instructor.dao.InstructorDAOImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class InstructorServiceImplTest {

    @Mock
    InstructorDAOImpl instructorDAO;

    @InjectMocks
    InstructorServiceImpl instructorService;

    @Test
    void findByTAEmailId() throws Exception {
        String userNameTest = "savani";
        String roleNameTest = "ROLE_TA";

        when(instructorDAO.findByTAEmailId(userNameTest, roleNameTest)).thenReturn("TA Assigned");

        String assignmentConfirmation = instructorService.findByTAEmailId(userNameTest, roleNameTest);
        assertEquals("TA Assigned", assignmentConfirmation);
    }


}
