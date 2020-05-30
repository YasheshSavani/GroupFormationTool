package com.csci5308.groupme.course.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.ICourseService;

import ch.qos.logback.classic.Logger;

@Controller
public class CourseController {
	
	Logger logger = (Logger) LoggerFactory.getLogger(CourseController.class);
		
	@Autowired
//	private ICourseService iCourseService;
	
	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public ModelAndView getCoursePage() {

		
//		List<Course> getCourseService;
		Course coursedetails =  new Course("CSCI 5308", "Adv. Software Development", "30490"); 
		HashMap<String, String> detailsList = new HashMap<String, String>();
		detailsList.put("courseCode", coursedetails.getCourseCode());
		detailsList.put("courseName", coursedetails.getCourseName());
		detailsList.put("courseCrn", coursedetails.getCourseCrn());

		ModelAndView mView = new ModelAndView();
		mView.addObject("details", detailsList);
		mView.setViewName("course");
		return mView;
	}
}
