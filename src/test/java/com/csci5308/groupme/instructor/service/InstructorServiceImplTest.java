package com.csci5308.groupme.instructor.service;

import com.csci5308.groupme.instructor.dao.InstructorDAOImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class InstructorServiceImplTest {

    @Mock
    InstructorDAOImpl instructorDAO;

    @InjectMocks
    InstructorServiceImpl instructorService;
}
