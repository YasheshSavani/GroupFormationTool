package com.csci5308.groupme.instructor.controller;

import com.csci5308.groupme.instructor.service.InstructorService;
import com.csci5308.groupme.instructor.service.InstructorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class InstructorController {
/*
    @GetMapping("/InstructorTAStudent")
    public String instructorTAStudentHomePage() {

    }

    @GetMapping("/InstructorStudent")
    public String instructorStudentHomePage() {

    }

    @GetMapping("/InstructorTA")
    public String instructorTAHomePage() {

    }

    @GetMapping("/Instructor")
    public String instructorTAHomePage() {

    }*/

    @PostMapping("/uploadfile")
    public String uploadCSV(@RequestParam("file") MultipartFile file, Model model) {
        InstructorService instructorService = new InstructorServiceImpl();
        boolean status = instructorService.upload(file);
        String message = status ? "Student Enrolled Successfully." : "Enrollment failed.";
        model.addAttribute("status", message);
        return "CourseAdmin";
    }


}
