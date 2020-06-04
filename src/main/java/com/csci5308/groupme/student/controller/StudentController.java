package com.csci5308.groupme.student.controller;

import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    CourseService courseService;

    @RequestMapping(value = "/studenthomepage", method = RequestMethod.GET)
    public ModelAndView showStudentHomePage(@RequestParam(value = "isStudent", required = false, defaultValue = "false") boolean isStudent,
                                            @RequestParam(value = "isTA", required = false, defaultValue = "false") boolean isTA,
                                            Principal p) throws Exception {
        String studentUserName = p.getName();
        List<Course> coursesStudentEnrolledIn = courseService.findCoursesByStudentUserName(studentUserName);
        ModelAndView mView = new ModelAndView();
        mView.setViewName("studenthomepage");
        if (!coursesStudentEnrolledIn.isEmpty()) {
            mView.addObject("studentCourseDetails", coursesStudentEnrolledIn);
        } else {
            mView.addObject("studentCourseDetails", null);
        }
        mView.addObject("isTA", isTA);
        return mView;
    }
}
