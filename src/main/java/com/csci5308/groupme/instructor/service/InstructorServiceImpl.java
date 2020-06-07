package com.csci5308.groupme.instructor.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.csci5308.groupme.instructor.dao.InstructorDAO;
import com.csci5308.groupme.instructor.model.Instructor;
import com.csci5308.groupme.student.dao.StudentDAO;
import com.csci5308.groupme.student.dao.StudentDAOImpl;
import com.csci5308.groupme.student.model.Student;
import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserService;
import com.csci5308.groupme.user.service.UserServiceImpl;
import com.opencsv.CSVReader;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	private InstructorDAO instructorDAO;

	@Autowired
	private UserService userService;

	@Override
	public String findByTAEmailId(String emailId, String courseCode) throws Exception {

		String assignmentConfirmation = null;

		try {
			assignmentConfirmation = instructorDAO.findByTAEmailId(emailId, courseCode);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return assignmentConfirmation;
	}

	@Override
	public boolean upload(MultipartFile file, String instructorID, String courseCode) {
		try {
			Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
			List<String[]> records = readAll(reader);

			if (records != null) {
				for (String[] record : records.subList(1, records.size())) {
					Student student = new Student(record[0] + record[1], record[0]);
					StudentDAO studentDAO = new StudentDAOImpl(student);
					if (studentDAO.isNotEnrolled()) {
						User user = new User();
						user.setUserName(record[0] + record[1]);
						user.setLastName(record[1]);
						user.setFirstName(record[2]);
						user.setEmail(record[3]);
						user.setPassword(record[0]);
						if (studentDAO.enrol(user, instructorID, courseCode)) {
							UserService userService = new UserServiceImpl();
							userService.sendCredentials(user);
						}
					}
				}
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	@Override
	public List<String[]> readAll(Reader reader) {
		CSVReader csvReader = new CSVReader(reader);
		try {
			List<String[]> records = csvReader.readAll();
			reader.close();
			csvReader.close();
			return records;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Instructor getByUserName(String userName) throws Exception {
		Instructor instructor = instructorDAO.findByUserName(userName);
		if (null != instructor) {
			User user = userService.getByUserName(userName);
			instructor.setFirstName(user.getFirstName());
			instructor.setLastName(user.getLastName());
			instructor.setEmail(user.getEmail());
		}
		return instructor;
	}

	@Override
	public Instructor getByEmail(String email) throws Exception {
		User user = userService.getByEmail(email);
		Instructor instructor = instructorDAO.findByUserName(user.getUserName());
		if (null != instructor) {
			instructor.setFirstName(user.getFirstName());
			instructor.setLastName(user.getLastName());
			instructor.setEmail(user.getEmail());
		}
		return instructor;
	}

}
