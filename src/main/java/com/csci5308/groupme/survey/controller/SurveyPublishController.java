package com.csci5308.groupme.survey.controller;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.survey.service.SurveyPublishService;
import constants.Roles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SurveyPublishController {

    private final String courseCodeParamString = "courseCode";
    private final String roleNameParamString = "roleName";
    SurveyPublishService surveyPublishService;

    @RequestMapping(value = "/publishSurvey", method = RequestMethod.GET)
    public ModelAndView publishSurvey(@RequestParam(roleNameParamString) String roleName, @RequestParam(courseCodeParamString) String courseCode) {

        surveyPublishService = SystemConfig.instance().getSurveyPublishService();
        String message = surveyPublishService.publishSurveyForStudents(roleName, courseCode);
        ModelAndView mView = new ModelAndView();
        mView.setViewName("survey/publishsurvey");
        mView.addObject("roleName", roleName);
        mView.addObject("publisherMessage", message);
        return mView;
    }

    @RequestMapping(value = "/exitPublishSurveyPage", method = RequestMethod.POST)
    public ModelAndView exitPublishSurveyPage(@RequestParam(roleNameParamString) String roleName) {

        ModelAndView mView = new ModelAndView();
        if (roleName.equals(Roles.TA)) {
            mView.setViewName("redirect:/TAcoursepage");
        } else {
            mView.setViewName("redirect:/instructor/courseAdminPage");
        }
        mView.addObject("roleName", roleName);
        return mView;
    }
}
