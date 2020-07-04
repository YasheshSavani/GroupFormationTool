package com.csci5308.groupme.courseadmin.instructor.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseDetailsService;
import com.csci5308.groupme.courseadmin.instructor.service.EnrollmentService;
import com.csci5308.groupme.courseadmin.instructor.service.InstructorService;
import com.csci5308.groupme.courseadmin.teaching_assistant.service.TeachingAssistantService;

import constants.Messages;
import errors.EditCodes;

@Controller
public class InstructorController {
	
    @Autowired
    InstructorService instructorService;

    @Autowired
    CourseDetailsService courseDetailsService;

    EnrollmentService enrollmentService;

    TeachingAssistantService teachingAssistantService;

    @RequestMapping(value = "/instructorhomepage", method = RequestMethod.GET)
    public ModelAndView instructorHomePage(Principal principal, Model model) {
        ModelAndView mView = new ModelAndView();
        mView.setViewName("instructor/instructorhomepage");
        mView.addObject("isInstructor", true);
        try {
            List<Course> coursesList = courseDetailsService.findCoursesByInstructor(principal.getName());
            model.addAttribute("courses", coursesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mView;
    }

    @RequestMapping(value = "/instructor/courseAdminPage", method = RequestMethod.GET)
    public String courseAdminPage(Principal principal, Model model) {
        try {
            List<Course> coursesList = courseDetailsService.findCoursesByInstructor(principal.getName());
            model.addAttribute("courses", coursesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "CourseAdmin";
    }

    @RequestMapping(value = "/courseoperation", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView insertByTAEmailId(@RequestParam(value = "email") String emailTA,
                                          @RequestParam(value = "courseCode") String courseCode, Principal p) throws Exception {
        teachingAssistantService = SystemConfig.instance().getTeachingAssistantService();
        int assignmentConfirmation = teachingAssistantService.assignTAToCourse(emailTA, courseCode);
        ModelAndView mView = new ModelAndView();
        if (assignmentConfirmation == EditCodes.SUCCESS) {
            mView.addObject("status", Messages.TA_ASSIGNED);
        } else if(assignmentConfirmation == EditCodes.EMAIL_DOES_NOT_EXIST) {
            mView.addObject("status", Messages.EMAIL_DOES_NOT_EXIST);
        }
        else {
            mView.addObject("status", Messages.TA_ASSIGNED);
        }
        mView.setViewName("coursedetails");
        return mView;
    }

    @RequestMapping(value = "/courseAdmin/course", method = RequestMethod.GET)
    public String courseAdmin(@RequestParam("courseCode") String courseCode,
                              @RequestParam("courseName") String courseName, @RequestParam("courseCrn") String courseCrn, Model model) {
        try {
            model.addAttribute("courseCode", courseCode);
            model.addAttribute("courseName", courseName);
            model.addAttribute("courseCrn", courseCrn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "coursedetails";
    }

}
