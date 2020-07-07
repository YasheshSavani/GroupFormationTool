package com.csci5308.groupme.course.student.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseDetailsService;

@Controller
public class StudentController {

    @Autowired
    CourseDetailsService courseDetailsService;

    @GetMapping("/studenthomepage")
    public ModelAndView showStudentHomePage(
            @RequestParam(value = "isStudent", required = false, defaultValue = "false") boolean isStudent,
            @RequestParam(value = "isTA", required = false, defaultValue = "false") boolean isTA,
            @RequestParam(value = "isInstructor", required = false, defaultValue = "false") boolean isInstructor,
            Principal principal) throws Exception {
        String studentUserName = principal.getName();
        List<Course> coursesStudentEnrolledIn = courseDetailsService.findCoursesByStudentUserName(studentUserName);
        ModelAndView mView = new ModelAndView();
        mView.setViewName("student/studenthomepage");
        if (null != coursesStudentEnrolledIn) {
            mView.addObject("studentCourseDetails", coursesStudentEnrolledIn);
        } else {
            mView.addObject("studentCourseDetails", null);
        }
        mView.addObject("isTA", isTA);
        mView.addObject("isInstructor", isInstructor);
        return mView;
    }
}
