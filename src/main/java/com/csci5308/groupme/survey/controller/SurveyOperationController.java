package com.csci5308.groupme.survey.controller;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.courseadmin.instructor.model.Question;
import com.csci5308.groupme.survey.service.SurveyOperationService;
import constants.Messages;
import constants.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/survey")
public class SurveyOperationController {

    private final String courseCodeParamString = "courseCode";
    private final String roleNameParamString = "roleName";
    private final String questionTypeParamString = "questionType";
    private final String questionIdParamString = "questionId";
    private final String questionParamString = "question";
    private final String questionTitleParamString = "questionTitle";


    private final Logger logger = LoggerFactory.getLogger(SurveyOperationController.class);

    SurveyOperationService surveyOperationService;

    @RequestMapping(value = "/createSurvey", method = RequestMethod.GET)
    public ModelAndView showCreateSurveyPage(@RequestParam(courseCodeParamString) String courseCode,
                                             @RequestParam(roleNameParamString) String roleName,
                                             Principal principal) {
        String message;
        List<Question> addedQuestions;
        surveyOperationService = SystemConfig.instance().getSurveyOperationService();
        ModelAndView mView = new ModelAndView();
        String userName = principal.getName();
        Map<String, Integer> conditions = surveyOperationService.checkIfSurveyExist(courseCode);
        if (conditions.get("isPublished") != 1) {
            logger.info("Survey for course: " + courseCode + " is not Published");
            if (conditions.get("surveyId") != 0) {
                logger.info("Survey Exists for course: " + courseCode);
                addedQuestions = surveyOperationService.getAlreadyAddedSurveyQuestions(userName, roleName, courseCode);
            } else {
                addedQuestions = new ArrayList<>();
            }
            List<Question> notAddedQuestions = surveyOperationService.showQuestionsOnCreateSurveyPage(courseCode, roleName, userName);
            if (null != notAddedQuestions) {
                mView.addObject("notAddedQuestions", notAddedQuestions);
                mView.addObject("addedQuestions", addedQuestions);
                message = Messages.QUESTIONS_FETCHED;
                mView.addObject("message", message);
            } else {
                notAddedQuestions = new ArrayList<>();
                mView.addObject("notAddedQuestions", notAddedQuestions);
                mView.addObject("addedQuestions", addedQuestions);
                message = Messages.QUESTIONS_NOT_FETCHED;
                mView.addObject("message", message);
            }
            mView.addObject("publisherMessage", Messages.SURVEY_NOT_PUBLISHED);
        } else {
            logger.info("Survey for course: " + courseCode + " is Pulished");
            mView.addObject("publisherMessage", Messages.SURVEY_PUBLISHED);
        }
        mView.addObject("courseCode", courseCode);
        mView.addObject("roleName", roleName);
        mView.setViewName("survey/createsurvey");
        return mView;
    }

    @RequestMapping(value = "/addQuestionToSurvey", method = RequestMethod.POST)
    public ModelAndView addQuestionToSurvey(@RequestParam(value = questionIdParamString) String questionId,
                                            @RequestParam(value = questionParamString) String question,
                                            @RequestParam(value = questionTitleParamString) String questionTitle,
                                            @RequestParam(value = courseCodeParamString) String courseCode,
                                            @RequestParam(value = roleNameParamString) String roleName,
                                            @RequestParam(value = questionTypeParamString) String questionType) throws Exception {
        surveyOperationService = SystemConfig.instance().getSurveyOperationService();
        logger.info(question);
        logger.info(questionTitle);
        logger.info(questionId);
        int rowCount = surveyOperationService.addQuestionToSurvey(courseCode, questionTitle, Integer.parseInt(questionId), question, questionType);
        ModelAndView mView = new ModelAndView();
        mView.setViewName("redirect:/survey/createSurvey");
        mView.addObject("courseCode", courseCode);
        mView.addObject("roleName", roleName);
        return mView;
    }

    @RequestMapping(value = "/removeQuestionFromSurvey", method = RequestMethod.POST)
    public ModelAndView removeQuestionFromSurvey(@RequestParam(value = questionIdParamString) String questionId,
                                                 @RequestParam(value = questionParamString) String question,
                                                 @RequestParam(value = questionTitleParamString) String questionTitle,
                                                 @RequestParam(value = courseCodeParamString) String courseCode,
                                                 @RequestParam(value = roleNameParamString) String roleName,
                                                 @RequestParam(value = questionTypeParamString) String questionType) throws Exception {
        surveyOperationService = SystemConfig.instance().getSurveyOperationService();
        int rowCount = surveyOperationService.removeQuestionFromSurvey(questionId, courseCode);
        ModelAndView mView = new ModelAndView();
        mView.setViewName("redirect:/survey/createSurvey");
        mView.addObject("courseCode", courseCode);
        mView.addObject("roleName", roleName);
        return mView;
    }

    @RequestMapping(value = "/saveSurvey", method = RequestMethod.POST)
    public ModelAndView saveSurveyAndRedirectToCourseAdmin(@RequestParam(value = roleNameParamString) String roleName) {
        ModelAndView mView = new ModelAndView();
        if (roleName.equals(Roles.TA)) {
            mView.setViewName("redirect:/TAcoursepage");
        } else {
            mView.setViewName("redirect:/instructor/courseAdminPage");
        }
        logger.info("Role: " + roleName);
        mView.addObject("roleName", roleName);
        return mView;
    }

}
