package com.csci5308.groupme.course.courseadmin.survey.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.courseadmin.survey.service.EditSurveyService;

import constants.Messages;
import errors.EditCodes;

@Controller
public class EditSurveyController {
	
	EditSurveyService editSurveyService;
	
	@RequestMapping(value = "/courseAdmin/editSurvey", method = RequestMethod.GET)
	public ModelAndView getSurveyStatus(@RequestParam("courseCode") String courseCode,Principal principal) {
		String message;
		ModelAndView mView = new ModelAndView();
		editSurveyService = SystemConfig.instance().getEditSurveyService();
		int status = editSurveyService.getSurveyPublishStatus(principal.getName(), courseCode);
		if (status == EditCodes.SUCCESS) {
			message = Messages.SURVEY_ALREADY_PUBLISHED;
			mView.setViewName("CourseAdmin");
            mView.addObject("message", message);
		}
		else {
			//to edit this part and delete resumeSurvey.html
			mView.setViewName("resumeSurvey");
		}
		return null;
		
	}
}
