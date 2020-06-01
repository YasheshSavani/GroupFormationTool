package com.csci5308.groupme.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    @GetMapping("/courseadmin")
    public String courseAdmin() {
        return "courseadmin";
    }

}
