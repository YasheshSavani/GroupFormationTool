package com.csci5308.groupme.instructor.service;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;

public interface IInstructorService {

    String uploadCSV(MultipartFile file, Model model);

}
