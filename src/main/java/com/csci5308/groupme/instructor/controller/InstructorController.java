package com.csci5308.groupme.instructor.controller;

import com.csci5308.groupme.instructor.service.InstructorService;
import com.csci5308.groupme.student.model.Student;
import com.csci5308.groupme.student.service.StudentService;
import com.csci5308.groupme.student.service.StudentServiceImpl;
import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserService;
import com.csci5308.groupme.user.service.UserServiceImpl;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.security.Principal;
import java.util.List;

@Controller
public class InstructorController {


    @Autowired
    InstructorService instructorService;

    Logger logger = LoggerFactory.getLogger(InstructorController.class);

    @RequestMapping(value = "/courseoperation", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showCourseAdminPage(@RequestParam("courseCode") String courseCode) {

        ModelAndView mView = new ModelAndView();
        mView.addObject("courseCode", courseCode);
        mView.setViewName("operationoncourse");
        return mView;
    }

    @RequestMapping(value = "/courseoperation", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView insertByTAEmailId(@RequestParam(value = "email") String emailTA, @RequestParam(value = "courseCode") String courseCode, Principal p) throws Exception {
        String assignmentConfirmation = instructorService.findByTAEmailId(emailTA, courseCode);
        ModelAndView mView = new ModelAndView();
        if (assignmentConfirmation.equals("True")) {
            mView.addObject("status", "TA Assigned to the Course");
        } else {
            mView.addObject("status", "TA assignment Error");
        }
        mView.setViewName("operationoncourse");
        return mView;
    }
}
