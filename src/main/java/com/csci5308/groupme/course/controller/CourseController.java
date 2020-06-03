package com.csci5308.groupme.course.controller;

import ch.qos.logback.classic.Logger;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CourseController {

    Logger logger = (Logger) LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/course", method = RequestMethod.GET)
    public ModelAndView getCoursesForGuest() throws Exception {

        List<Course> guestCourses = courseService.findAllCourses();
        ModelAndView mView = new ModelAndView();
        if (guestCourses.size() > 0) {

            mView.addObject("details", guestCourses);
        } else {
            mView.addObject("details", null);
        }
        mView.setViewName("guestcoursepage");
        return mView;
    }

    @RequestMapping(value = "/courseadminpage", method = RequestMethod.GET)
    public ModelAndView getCoursesByUserNameAndRole() throws Exception {
        String userName =  "ysavani";   //p.getName();
        String roleName = "ROLE_TA";

        List<Course> courseAdminCourses = courseService.getCoursesByUserNameAndRole(userName, roleName);

        ModelAndView mView = new ModelAndView();
        if (courseAdminCourses.size() > 0) {
            mView.addObject("courseAdminCourses", courseAdminCourses);
        } else {
            mView.addObject("courseAdminCourses", null);
        }
        mView.setViewName("courseadminpage");
        return mView;
    }

    @RequestMapping(value = "/courseadmin", method = RequestMethod.GET)
    public String courseAdmin() {
        return "CourseAdmin";
    }

}
