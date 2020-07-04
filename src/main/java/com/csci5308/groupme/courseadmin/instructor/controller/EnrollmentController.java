package com.csci5308.groupme.courseadmin.instructor.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.courseadmin.instructor.service.EnrollmentService;

@Controller
public class EnrollmentController {
	
	 EnrollmentService enrollmentService;
	
	 @RequestMapping(value = "/courseAdmin/uploadfile", method = RequestMethod.POST)
	    public String uploadCSV(@RequestParam("file") MultipartFile file, @RequestParam("courseCode") String courseCode,
	                            Principal principal) {
	        enrollmentService = SystemConfig.instance().getEnrollmentService();
	        enrollmentService.upload(file, principal.getName(), courseCode);
	        return "redirect:/instructor/courseAdminPage";
	    }
}
