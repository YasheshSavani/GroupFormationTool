package com.csci5308.groupme.instructor.service;

import java.io.Reader;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.csci5308.groupme.instructor.model.Instructor;

public interface InstructorService {

	public Instructor getByUserName(String userName) throws Exception;

	public boolean upload(MultipartFile file, String instructorID, String courseCode);

	public List<String[]> readAll(Reader reader);
	
	public Instructor getByEmail(String email) throws Exception; 
	
	public int createInstructor(Instructor instructor) throws Exception;

}
