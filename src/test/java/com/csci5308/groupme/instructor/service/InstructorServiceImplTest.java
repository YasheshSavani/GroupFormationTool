package com.csci5308.groupme.instructor.service;

import com.csci5308.groupme.instructor.dao.InstructorDAOImpl;
import com.csci5308.groupme.instructor.model.Instructor;
import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserServiceImpl;
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
    UserServiceImpl userService;

    @Mock
    InstructorDAOImpl instructorDAO;

    @InjectMocks
    InstructorServiceImpl instructorService;

    @Test
    public void createInstructorTest() throws Exception {
        Instructor instructor = new Instructor();
        instructor.setUserName("inst_test");
        instructor.setAbout("PhD");
        when(instructorDAO.save(instructor)).thenReturn(1);
        int status = instructorService.createInstructor(instructor);
        assertEquals(1, status);
    }

    @Test
    public void getByUserName() throws Exception {
        String userName = "inst_test";
        Instructor instructorTest = new Instructor();
        instructorTest.setFirstName("karan");
        instructorTest.setUserName("inst_test");
        instructorTest.setAbout("PhD");
        User user = new User();
        user.setFirstName("karan");
        user.setUserName("inst_test");
        when(instructorDAO.findByUserName(userName)).thenReturn(instructorTest);
        when(userService.getByUserName(userName)).thenReturn(user);
        Instructor instructor = instructorService.getByUserName(userName);
        assertEquals(instructor.getFirstName(), instructorTest.getFirstName());
    }

    @Test
    public void getByEmail() throws Exception {
        String email = "instructor@dal.ca";
        User user = new User();
        user.setFirstName("karan");
        user.setUserName("inst_test");
        Instructor instructorTest = new Instructor();
        instructorTest.setFirstName("karan");
        instructorTest.setUserName("inst_test");
        instructorTest.setAbout("PhD");
        when(userService.getByEmail(email)).thenReturn(user);
        when(instructorDAO.findByUserName(user.getUserName())).thenReturn(instructorTest);
        Instructor instructor = instructorService.getByEmail(email);
        assertEquals(instructor.getFirstName(), instructorTest.getFirstName());
    }
}
