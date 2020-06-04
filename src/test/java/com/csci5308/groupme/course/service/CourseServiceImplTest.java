package com.csci5308.groupme.course.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.csci5308.groupme.course.dao.CourseDAOImpl;
import com.csci5308.groupme.course.model.Course;

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
	public void getCoursesByUserNameAndRole() throws Exception {

		String userName = "ysavani";
		String roleName = "ROLE_TA";
		List<Course> defaultCourseList = new ArrayList<>();
		Course courseOne = new Course("CSCI5308", "Adv Software Development", 12345);
		defaultCourseList.add(courseOne);
		when(courseDAO.getCoursesByUserNameAndRole(userName,roleName)).thenReturn(defaultCourseList);

		List<Course> checkCourseList = courseServiceImpl.getCoursesByUserNameAndRole(userName,roleName);
		assertEquals(1, checkCourseList.size());
	}

	@Test
	public void findCoursesByStudentUserName() throws Exception {

		String studentUserName = "ysavani";
		List<Course> defaultCourseList = new ArrayList<>();
		Course courseOne = new Course("CSCI 5308", "Adv Software Development", 12345);
		defaultCourseList.add(courseOne);
		when(courseDAO.findCoursesByStudentUserName(studentUserName)).thenReturn(defaultCourseList);

		List<Course> checkCourseList = courseServiceImpl.findCoursesByStudentUserName(studentUserName);
		assertEquals(1, checkCourseList.size());
	}

}
