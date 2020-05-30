package com.csci5308.groupme.course.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.csci5308.groupme.course.model.Course;

@Repository
public interface ICourseDAO {

	public String insertCourse();
	
	public List<Course> findAll();

	public List<Course> findByCourseCode();

	public List<Course> findByCourseName();

	public List<Course> findByCourseCrn();

	public String deleteCourse();

	public String updateCourse();

}
