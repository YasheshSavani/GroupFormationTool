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

		List<Course> defaultCourseList = new ArrayList<Course>();
		Course courseOne = new Course("CSCI 5308", "Adv Software Development", 12345);
		defaultCourseList.add(courseOne);

		when(courseDAO.findAllCourses()).thenReturn(defaultCourseList);

		List<Course> checkCourseList = courseServiceImpl.findAllCourses();

		assertEquals(1, checkCourseList.size());
	}

}
