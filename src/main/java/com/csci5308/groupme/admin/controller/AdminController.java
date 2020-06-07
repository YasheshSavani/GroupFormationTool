package com.csci5308.groupme.admin.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.groupme.admin.service.AdminService;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseService;
import com.csci5308.groupme.instructor.service.InstructorService;
import com.csci5308.groupme.user.model.User;

import errors.EditCodes;

@Controller
public class AdminController {

	@Autowired
	CourseService courseService;
		
	@Autowired
	AdminService adminService;
	
	@GetMapping("/admin/manageCourses")
	public ModelAndView adminLandingPage(Principal principal) {
		ModelAndView mView = new ModelAndView("admin/managecourses");
		return mView;
	}
	
	@GetMapping("/admin/addCourse")
	public ModelAndView addCoursePage(Principal principal) {	    
	    ModelAndView mView = new ModelAndView("admin/addcourse");
	     return mView;
	}
		
	@PostMapping("/admin/addCourse")
	public ModelAndView addCourse(@ModelAttribute("course") Course course) throws Exception {
	    int status = courseService.createCourse(course);
	    String message = "";
		ModelAndView mView = new ModelAndView("admin/addcourse");
		if(status == 1)
			message = "Course added!";
		else if(status == EditCodes.COURSE_EXISTS)
			message = "Course already exists";
		else
			message = "Something went wrong. Server couldn't insert the course into the database!";
	    mView.addObject("message", message);
		
		return mView;
	    
	}
	
	@GetMapping("/admin/deleteCourse")
	public ModelAndView deletCoursePage(Principal principal) {	    
	    ModelAndView mView = new ModelAndView("admin/deletecourse");
	     return mView;
	}
		
	@PostMapping("/admin/deleteCourse")
	public ModelAndView deleteCourse(@ModelAttribute("course") Course course) throws Exception {
	    int status = courseService.delete(course.getCourseCode());
	    String message = "";
		ModelAndView mView = new ModelAndView("admin/deletecourse");
		if(status >= 1)
			message = "Course deleted!";
		else if(status == EditCodes.COURSE_DOES_NOT_EXIST)
			message = "Course does not exist!";
		else
			message = "Something went wrong. Server couldn't complete the operation!";
	    mView.addObject("message", message);
		
		return mView;
	    
	}
	
	@PostMapping(value = "/admin/addinstuctortocourse")
	public ModelAndView addInstucToCourse(@RequestParam("emailId") String emailId,
			@RequestParam("courseCode") String courseCode) throws Exception {
		String statusInstructor = adminService.assignInstructorToCourse(emailId, courseCode);
		ModelAndView mView = new ModelAndView();
		if (statusInstructor == "True") {
			mView.addObject("status", "Instructor assigned");
		} else {
			mView.addObject("status", "Instructor assingment error");
		}
		mView.setViewName("addinstuctortocourse");
		return mView;
	}
	
	
	
}
