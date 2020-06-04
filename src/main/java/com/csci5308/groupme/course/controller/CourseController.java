package com.csci5308.groupme.course.controller;

import ch.qos.logback.classic.Logger;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class CourseController {

    Logger logger = (Logger) LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/TAcoursepage", method = RequestMethod.GET)
    public ModelAndView getCoursesByUserNameAndRole(Principal p) throws Exception {
        String userName = p.getName();
        String roleName = "ROLE_TA";

        List<Course> courseTACourses = courseService.getCoursesByUserNameAndRole(userName, roleName);

        ModelAndView mView = new ModelAndView();
        if (!courseTACourses.isEmpty()) {
            mView.addObject("courseTACourses", courseTACourses);
        } else {
            mView.addObject("courseTACourses", null);
        }
        mView.setViewName("tahomepage");
        return mView;
    }

    @RequestMapping(value = "/operationoncourse", method = RequestMethod.GET)
    public ModelAndView showOpearationsOnCourse(@RequestParam("courseCode") String courseCode,
                                                @RequestParam("courseName") String courseName) {
        ModelAndView mView = new ModelAndView();
        mView.setViewName("operationoncourse");
        mView.addObject("courseCode", courseCode);
        mView.addObject("courseName", courseName);
        return mView;
    }

    @RequestMapping(value = "/coursepage", method = RequestMethod.GET)
    public ModelAndView getCoursePage(@RequestParam("courseName") String courseName,
                                      @RequestParam("courseCode") String courseCode) throws Exception {
        logger.info(courseCode);
        logger.info(courseName);
        ModelAndView mView = new ModelAndView();
        mView.setViewName("coursepage");
        mView.addObject("courseCode", courseCode);
        mView.addObject("courseName", courseName);
        return mView;
    }


}
