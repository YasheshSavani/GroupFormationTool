package com.csci5308.groupme.course.model;

public class Course {

	private String courseCode;
	private String courseName;
	private String courseCrn;

	
	public Course() {
		super();
	}

	public Course(String courseCode, String courseName, String courseCrn) {
		super();
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseCrn = courseCrn;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseId(String courseId) {
		this.courseCode = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseCrn() {
		return courseCrn;
	}

	public void setCourseCrn(String courseCrn) {
		this.courseCrn = courseCrn;
	}

}
