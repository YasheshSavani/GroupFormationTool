package com.csci5308.groupme.course.courseadmin.instructor.service;

import com.csci5308.groupme.auth.service.EmailService;
import com.csci5308.groupme.course.student.dao.StudentDAO;
import com.csci5308.groupme.course.student.dao.StudentDAOImpl;
import com.csci5308.groupme.course.student.model.Student;
import com.csci5308.groupme.user.model.User;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EmailService emailService;

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
                            emailService.sendCredentials(user);
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
}