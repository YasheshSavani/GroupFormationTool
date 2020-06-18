package com.csci5308.groupme.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {

    @RequestMapping(value = "/operationoncourse", method = RequestMethod.GET)
    public ModelAndView showOpearationsOnCourse(@RequestParam("courseCode") String courseCode,
                                                @RequestParam("courseName") String courseName) {
        ModelAndView mView = new ModelAndView();
        mView.setViewName("coursedetails");
        mView.addObject("courseCode", courseCode);
        mView.addObject("courseName", courseName);
        return mView;
    }

    @RequestMapping(value = "/coursepage", method = RequestMethod.GET)
    public ModelAndView getCoursePage(@RequestParam("courseName") String courseName,
                                      @RequestParam("courseCode") String courseCode) throws Exception {
        ModelAndView mView = new ModelAndView();
        mView.setViewName("coursepage");
        mView.addObject("courseCode", courseCode);
        mView.addObject("courseName", courseName);
        return mView;
    }
}
