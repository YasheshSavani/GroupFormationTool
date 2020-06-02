package com.csci5308.groupme.course.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CourseTest {

	private String courseCodeTest = "CSCI5308";
	private String courseNameTest = "Adv Topics in Software Development";
	private Integer courseCrnTest = 8035;

	@Test
	public void defaultConstructorTest() {

		Course course = new Course();
		assertNull(course.getCourseCode());
		assertNull(course.getCourseName());
		assertEquals(0, course.getCourseCrn());
	}

	@Test
	public void constructorWithValuesTest() {
		Course course = new Course(courseCodeTest, courseNameTest, courseCrnTest);
		assertEquals(course.getCourseCode(), courseCodeTest);
		assertEquals(course.getCourseName(), courseNameTest);
		assertEquals(course.getCourseCrn(), 8035);
	}

	@Test
	public void setCourseCodeTest() {
		Course course = new Course();
		course.setCourseCode(courseCodeTest);
		assertEquals(course.getCourseCode(), courseCodeTest);
	}

	@Test
	public void getCourseCodeTest() {
		Course course = new Course();
		course.setCourseCode(courseCodeTest);
		assertEquals(course.getCourseCode(), courseCodeTest);
	}

	@Test
	public void setCourseNameTest() {
		Course course = new Course();
		course.setCourseName(courseNameTest);
		assertEquals(course.getCourseName(), courseNameTest);
	}

	@Test
	public void getCourseNameTest() {
		Course course = new Course();
		course.setCourseName(courseNameTest);
		assertEquals(course.getCourseName(), courseNameTest);
	}

	@Test
	public void setCourseCrnTest() {
		Course course = new Course();
		course.setCourseCrn(courseCrnTest);
		assertEquals(course.getCourseCrn(), courseCrnTest);
	}

	@Test
	public void getCourseCrnTest() {
		Course course = new Course();
		course.setCourseCrn(courseCrnTest);
		assertEquals(course.getCourseCrn(), courseCrnTest);
	}

}
