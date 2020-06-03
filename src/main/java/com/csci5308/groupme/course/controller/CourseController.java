package com.csci5308.groupme.course.controller;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseService;

import ch.qos.logback.classic.Logger;

@Controller
public class CourseController {

	Logger logger = (Logger) LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public ModelAndView getCoursePageByRole() throws Exception {

		List<Course> getCourseService = courseService.findAllCourses();

		ModelAndView mView = new ModelAndView("GuestCoursePage");
		mView.addObject("details", getCourseService);
		return mView;
	}

    @RequestMapping(value = "/courseadmin", method = RequestMethod.GET)
    public String courseAdmin() {
        return "CourseAdmin";
    }

}
