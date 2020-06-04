package com.csci5308.groupme.instructor.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface InstructorService {

    public boolean upload(MultipartFile file, String instructorID, String courseCode);

}
