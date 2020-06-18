package com.csci5308.groupme.course.service;

import com.csci5308.groupme.course.dao.CourseDAOImpl;
import com.csci5308.groupme.course.model.Course;
import errors.EditCodes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CourseServiceImplTest {

    @Mock
    private CourseDAOImpl courseDAO;

    @InjectMocks
    private CourseServiceImpl courseServiceImpl;

    @Test
    public void findAllCoursesTest() throws Exception {

        List<Course> defaultCourseList = new ArrayList<>();
        Course courseOne = new Course("CSCI5308", "Adv Software Development", 12345);
        defaultCourseList.add(courseOne);
        when(courseDAO.findAllCourses()).thenReturn(defaultCourseList);
        List<Course> checkCourseList = courseServiceImpl.findAllCourses();
        assertEquals(1, checkCourseList.size());
    }

    @Test
    public void getCoursesByUserNameAndRoleTest() throws Exception {

        String userName = "ysavani";
        String roleName = "ROLE_TA";
        List<Course> defaultCourseList = new ArrayList<>();
        Course courseOne = new Course("CSCI5308", "Adv Software Development", 12345);
        defaultCourseList.add(courseOne);
        when(courseDAO.getCoursesByUserNameAndRole(userName, roleName)).thenReturn(defaultCourseList);
        List<Course> checkCourseList = courseServiceImpl.getCoursesByUserNameAndRole(userName, roleName);
        assertEquals(1, checkCourseList.size());
    }

    @Test
    public void findCoursesByStudentUserNameTest() throws Exception {

        String studentUserName = "ysavani";
        List<Course> defaultCourseList = new ArrayList<>();
        Course courseOne = new Course("CSCI 5308", "Adv Software Development", 12345);
        defaultCourseList.add(courseOne);
        when(courseDAO.findCoursesByStudentUserName(studentUserName)).thenReturn(defaultCourseList);
        List<Course> checkCourseList = courseServiceImpl.findCoursesByStudentUserName(studentUserName);
        assertEquals(1, checkCourseList.size());
    }

    @Test
    public void saveCourseTest() throws Exception {
        Course course = new Course("CSCI 5308", "Adv Software Development", 12345);
        when(courseDAO.save(course)).thenReturn(1);
        assertEquals(1, courseServiceImpl.createCourse(course));
    }

    @Test
    public void courseExistsTest() throws Exception {
        Course course = new Course("CSCI 5308", "Adv Software Development", 12345);
        when(courseDAO.save(course)).thenReturn(EditCodes.COURSE_EXISTS);
        assertEquals(EditCodes.COURSE_EXISTS, courseServiceImpl.createCourse(course));
    }

    @Test
    public void deleteCourseTest() throws Exception {
        Course course = new Course("CSCI 5308", "Adv Software Development", 12345);
        when(courseDAO.remove(course.getCourseCode())).thenReturn(1);
        assertEquals(1, courseServiceImpl.delete(course.getCourseCode()));
    }

    @Test
    public void noCourseToDeleteTest() throws Exception {
        Course course = new Course("CSCI 5308", "Adv Software Development", 12345);
        when(courseDAO.remove(course.getCourseCode())).thenReturn(EditCodes.COURSE_DOES_NOT_EXIST);
        assertEquals(EditCodes.COURSE_DOES_NOT_EXIST, courseServiceImpl.delete(course.getCourseCode()));
    }


}
