package com.csci5308.groupme.course.courseadmin.instructor.controller;

import ch.qos.logback.classic.Logger;
import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.courseadmin.instructor.model.ListOfOptions;
import com.csci5308.groupme.course.courseadmin.instructor.model.Option;
import com.csci5308.groupme.course.courseadmin.instructor.model.Question;
import com.csci5308.groupme.course.courseadmin.instructor.service.QuestionManagerService;
import constants.Messages;
import constants.QuestionTypeConstants;
import errors.EditCodes;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {

    Logger logger = (Logger) LoggerFactory.getLogger(QuestionController.class);
    QuestionManagerService questionManagerService;

    @RequestMapping(value = "/instructor/questionManagerPage", method = RequestMethod.GET)
    public String questionManagerPage(Principal principal, Model model) {
        return "instructor/questionmanager";
    }

    @RequestMapping(value = "/instructor/createQuestion", method = RequestMethod.GET)
    public ModelAndView createQuestionPage() {
        ModelAndView mView = new ModelAndView();
        mView.setViewName("instructor/createquestion");
        return mView;
    }

    @RequestMapping(value = "/instructor/createQuestion", method = RequestMethod.POST)
    public ModelAndView createQuestion(@RequestParam("title") String questionTitle,
                                       @RequestParam("question") String question, @RequestParam("type") String questionType, Principal principal) {
        Integer count = 1;
        String message;
        Question questionObject = new Question();
        questionObject.setTitle(questionTitle);
        questionObject.setQuestion(question);
        questionObject.setType(questionType);
        ModelAndView mView = new ModelAndView();
        if (questionType.equals(QuestionTypeConstants.mcqChooseOne)
                || questionType.equals(QuestionTypeConstants.mcqChooseMultiple)) {
            ListOfOptions options = new ListOfOptions();
            List<Option> optionList = new ArrayList<>();
            optionList.add(new Option("", count, count));
            options.setOptionList(optionList);
            mView.setViewName("instructor/createoptions");
            mView.addObject("options", options);
            mView.addObject("questionDetails", questionObject);
        } else if (questionType.equals(QuestionTypeConstants.numericType)
                || questionType.equals(QuestionTypeConstants.freeTextType)) {
            questionManagerService = SystemConfig.instance().getQuestionManagerService();
            ListOfOptions options = null;
            int status = questionManagerService.createQuestion(principal.getName(), questionObject, options);
            if (status == EditCodes.SUCCESS) {
                message = Messages.QUESTION_CREATED;
            } else {
                message = Messages.FAILURE;
            }
            mView.setViewName("instructor/questionmanager");
            mView.addObject("message", message);
        }
        return mView;
    }

    @RequestMapping(value = "/instructor/createOptions/newOption", method = RequestMethod.POST)
    public String addOptions(@ModelAttribute ListOfOptions options, Model model, @RequestParam("title") String questionTitle,
                             @RequestParam("question") String question, @RequestParam("type") String questionType, Principal principal) {
        Question questionObject = new Question();
        questionObject.setTitle(questionTitle);
        questionObject.setQuestion(question);
        questionObject.setType(questionType);
        List<Option> optionList = options.getOptionList();
        optionList.add(new Option("", optionList.size() + 1, optionList.size() + 1));
        options.setOptionList(optionList);
        model.addAttribute("options", options);
        model.addAttribute("questionDetails", questionObject);
        return "instructor/createoptions";
    }

    @RequestMapping(value = "/instructor/saveMultipleChoiceQuestion", method = RequestMethod.POST)
    public ModelAndView saveMultipleChoiceQuestion(@ModelAttribute ListOfOptions options, @RequestParam("title") String questionTitle,
                                                   @RequestParam("question") String question, @RequestParam("type") String questionType, Principal principal) {
        String message;
        questionManagerService = SystemConfig.instance().getQuestionManagerService();
        ModelAndView mView = new ModelAndView();
        Question questionObject = new Question();
        questionObject.setTitle(questionTitle);
        questionObject.setQuestion(question);
        questionObject.setType(questionType);
        try {
            int status = questionManagerService.createQuestion(principal.getName(), questionObject, options);
            if (status == EditCodes.SUCCESS) {
                message = Messages.QUESTION_CREATED;
            } else {
                message = Messages.FAILURE;
            }
            mView.setViewName("instructor/questionmanager");
            mView.addObject("message", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mView;
    }

    @RequestMapping(value = "/instructor/deleteQuestion", method = RequestMethod.GET)
    public String deleteQuestionPage(Principal principal, Model model) {
        questionManagerService = SystemConfig.instance().getQuestionManagerService();
        Question question = new Question();
        List<Question> listOfQuestions = questionManagerService.getAllQuestions(principal.getName());
        model.addAttribute("details", listOfQuestions);
        model.addAttribute("question", question);
        return "instructor/deletequestion";
    }

    @RequestMapping(value = "/instructor/deleteQuestion", method = RequestMethod.POST)
    public ModelAndView deleteQuestionPageSubmit(@ModelAttribute("question") Question question, Principal principal, Model model) {
        String message;
        questionManagerService = SystemConfig.instance().getQuestionManagerService();
        ModelAndView mView = new ModelAndView();
        logger.info("question selected is" + question.getQuestionId());
        int status = questionManagerService.deleteQuestion(principal.getName(), question);
        if (status == EditCodes.SUCCESS) {
            message = Messages.QUESTION_DELETED;
        } else {
            message = Messages.FAILURE;
        }
        mView.setViewName("instructor/questionmanager");
        mView.addObject("message", message);
        return mView;
    }

    @RequestMapping(value = "/instructor/listAllTitles", method = RequestMethod.GET)
    public ModelAndView listAllQuestionsPage(Principal principal) {
        questionManagerService = SystemConfig.instance().getQuestionManagerService();
        List<Question> listQuestionDetails = questionManagerService.getAllTitles(principal.getName());
        ModelAndView mView = new ModelAndView("instructor/listalltitles");
        if (listQuestionDetails != null) {
            mView.addObject("titles", listQuestionDetails);
        } else {
            mView.addObject("titles", null);
        }
        return mView;
    }

    @RequestMapping(value = "/instructor/sortTitlesByTitles", method = RequestMethod.GET)
    public ModelAndView listSortedTitlesByTitles(Principal principal) {
        questionManagerService = SystemConfig.instance().getQuestionManagerService();
        List<Question> listSortedTitlesByTitles = questionManagerService.getAllSortedTitlesByTitles(principal.getName());
        ModelAndView mView = new ModelAndView("instructor/sortedtitles");
        if (listSortedTitlesByTitles != null) {
            mView.addObject("sortedTitles", listSortedTitlesByTitles);
        } else {
            mView.addObject("sortedTitles", null);
        }
        return mView;
    }

    @RequestMapping(value = "/instructor/sortTitlesByDates", method = RequestMethod.GET)
    public ModelAndView listSortedTitlesByDates(Principal principal) {
        questionManagerService = SystemConfig.instance().getQuestionManagerService();
        List<Question> listSortedTitlesByDates = questionManagerService.getAllSortedTitlesByDates(principal.getName());
        ModelAndView mView = new ModelAndView("instructor/sortedtitlesdates");
        if (listSortedTitlesByDates != null) {
            mView.addObject("sortedTitles", listSortedTitlesByDates);
        } else {
            mView.addObject("sortedTitles", null);
        }
        return mView;
    }
}
