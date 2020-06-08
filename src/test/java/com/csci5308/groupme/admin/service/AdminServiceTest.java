package com.csci5308.groupme.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.csci5308.groupme.admin.dao.AdminDao;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseService;
import com.csci5308.groupme.instructor.model.Instructor;
import com.csci5308.groupme.instructor.service.InstructorService;

import errors.EditCodes;

@ExtendWith(SpringExtension.class)
public class AdminServiceTest {
	
	
	@Mock
	private AdminDao adminDao;
	
	@Mock
	InstructorService instructorService;
	
	@Mock
	CourseService courseService;

	@InjectMocks
	private AdminServiceImpl adminServiceImpl;

	@Test
	public void assignInstructorToCourseTest() throws Exception {
		String email = "instructor_test@gmail.com";
		String courseCode = "CSCI0000";
		Instructor instructor = new Instructor();
		instructor.setUserName("testinst");
		instructor.setFirstName("Test");
		instructor.setLastName("Instructor");
		instructor.setEmail(email);
		Course course = new Course();
		course.setCourseCode(courseCode);
		course.setCourseName("Test Course");		
		when(instructorService.getByEmail(email)).thenReturn(instructor);
		when(courseService.getByCourseCode(courseCode)).thenReturn(course);
		when(adminDao.createClass(instructor.getUserName(), courseCode)).thenReturn(1);
		String message = (adminServiceImpl.assignInstructorToCourse(email, courseCode));
		assertEquals("Instructor assigned to the course!", message);

	}
	
	@Test
	public void instructorNotFoundTest() throws Exception {
		String email = "instructor_test@gmail.com";
		String courseCode = "CSCI0000";
		Instructor instructor = new Instructor();
		instructor.setUserName("testinst");
		instructor.setFirstName("Test");
		instructor.setLastName("Instructor");
		instructor.setEmail(email);
		Course course = new Course();
		course.setCourseCode(courseCode);
		course.setCourseName("Test Course");		
		when(instructorService.getByEmail(email)).thenReturn(null);
		when(courseService.getByCourseCode(courseCode)).thenReturn(course);
		when(adminDao.createClass(instructor.getUserName(), courseCode)).thenReturn(1);
		String message = (adminServiceImpl.assignInstructorToCourse(email, courseCode));
		assertEquals("Instructor email not found! Please check again!", message);

	}
	
	@Test
	public void recordExistsTest() throws Exception {
		String email = "instructor_test@gmail.com";
		String courseCode = "CSCI0000";
		Instructor instructor = new Instructor();
		instructor.setUserName("testinst");
		instructor.setFirstName("Test");
		instructor.setLastName("Instructor");
		instructor.setEmail(email);
		Course course = new Course();
		course.setCourseCode(courseCode);
		course.setCourseName("Test Course");		
		when(instructorService.getByEmail(email)).thenReturn(instructor);
		when(courseService.getByCourseCode(courseCode)).thenReturn(course);
		when(adminDao.createClass(instructor.getUserName(), courseCode)).thenReturn(EditCodes.CLASS_ALREADY_CREATED);
		String message = (adminServiceImpl.assignInstructorToCourse(email, courseCode));
		assertEquals("Record already exists!", message);

	}
	
	@Test
	public void courseNotFoundTest() throws Exception {
		String email = "instructor_test@gmail.com";
		String courseCode = "CSCI0000";
		Instructor instructor = new Instructor();
		instructor.setUserName("testinst");
		instructor.setFirstName("Test");
		instructor.setLastName("Instructor");
		instructor.setEmail(email);
		Course course = new Course();
		course.setCourseCode(courseCode);
		course.setCourseName("Test Course");		
		when(instructorService.getByEmail(email)).thenReturn(instructor);
		when(courseService.getByCourseCode(courseCode)).thenReturn(null);
		when(adminDao.createClass(instructor.getUserName(), courseCode)).thenReturn(1);
		String message = (adminServiceImpl.assignInstructorToCourse(email, courseCode));
		assertEquals("Course not found!", message);

	}
	
	@Test
	public void dbIssueTest() throws Exception {
		String email = "instructor_test@gmail.com";
		String courseCode = "CSCI0000";
		Instructor instructor = new Instructor();
		instructor.setUserName("testinst");
		instructor.setFirstName("Test");
		instructor.setLastName("Instructor");
		instructor.setEmail(email);
		Course course = new Course();
		course.setCourseCode(courseCode);
		course.setCourseName("Test Course");		
		when(instructorService.getByEmail(email)).thenReturn(instructor);
		when(courseService.getByCourseCode(courseCode)).thenReturn(course);
		when(adminDao.createClass(instructor.getUserName(), courseCode)).thenReturn(0);
		String message = (adminServiceImpl.assignInstructorToCourse(email, courseCode));
		assertEquals("Something went wrong! The server could not insert the record into the database!", message);

	}
}
