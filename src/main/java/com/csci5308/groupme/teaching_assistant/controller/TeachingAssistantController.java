package com.csci5308.groupme.teaching_assistant.controller;

import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseService;

import constants.Roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class TeachingAssistantController {

    @Autowired
    CourseService courseService;

    @GetMapping("/tahomepage")
    public ModelAndView showStudentHomePage(
            @RequestParam(value = "isStudent", required = false, defaultValue = "false") boolean isStudent,
            @RequestParam(value = "isTA", required = false, defaultValue = "false") boolean isTA,
            @RequestParam(value = "isInstructor", required = false, defaultValue = "false") boolean isInstructor,
            Principal principal) throws Exception {
        String userName = principal.getName();
        String roleName = Roles.TA;
        List<Course> managedCourses = courseService.getCoursesByUserNameAndRole(userName, roleName);
        ModelAndView mView = new ModelAndView();
        mView.setViewName("ta/tahomepage");
        if (null != managedCourses) {
            mView.addObject("managedCourses", managedCourses);
        } else {
            mView.addObject("managedCourses", null);
        }
        mView.addObject("isTA", true);
        return mView;
    }

    @RequestMapping(value = "/TAcoursepage", method = RequestMethod.POST)
    public ModelAndView getCoursesByUserNameAndRole(Principal p) throws Exception {
        String userName = p.getName();
        String roleName = Roles.TA;
        List<Course> courseTACourses = courseService.getCoursesByUserNameAndRole(userName, roleName);
        ModelAndView mView = new ModelAndView();
        if (null != courseTACourses) {
            mView.addObject("courses", courseTACourses);
        } else {
            mView.addObject("courses", null);
        }
        mView.setViewName("CourseAdmin");
        return mView;
    }
}
