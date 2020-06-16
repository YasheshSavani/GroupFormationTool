package com.csci5308.groupme.instructor.controller;

import ch.qos.logback.classic.Logger;
import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.instructor.model.Option;
import com.csci5308.groupme.instructor.model.Options;
import com.csci5308.groupme.instructor.model.Question;
import com.csci5308.groupme.instructor.service.QuestionManagerService;
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
		Question questionObject = new Question();
		questionObject.setTitle(questionTitle);
		questionObject.setQuestion(question);
		questionObject.setType(questionType);
		ModelAndView mView = new ModelAndView();
		if (questionType.equals("Multiple choice - choose one")
				|| questionType.equals("Multiple choice - choose multiple")) {
			Options options = new Options();
			List<Option> optionList = new ArrayList<>();
			optionList.add(new Option("", count, count));
			options.setOptionList(optionList);
			mView.setViewName("instructor/createoptions");
			mView.addObject("options", options);
			mView.addObject("questionDetails", questionObject);
		} else if (questionType.equals("Numeric") || questionType.equals("Free text")) {
			questionManagerService = SystemConfig.instance().getQuestionManagerService();
			Options options = null;
			String message = questionManagerService.createQuestion(principal.getName(), questionObject, options);
			mView.setViewName("instructor/questionmanager");
			mView.addObject("message", message);
		}
		return mView;
	}

	@RequestMapping(value = "/instructor/createOptions/newOption", method = RequestMethod.POST)
	public String addOptions(@ModelAttribute Options options, Model model, @RequestParam("title") String questionTitle,
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
	public ModelAndView saveMultipleChoiceQuestion(@ModelAttribute Options options, @RequestParam("title") String questionTitle,
			@RequestParam("question") String question, @RequestParam("type") String questionType, Principal principal) {
		questionManagerService = SystemConfig.instance().getQuestionManagerService();
		ModelAndView mView = new ModelAndView();
		Question questionObject = new Question();
		questionObject.setTitle(questionTitle);
		questionObject.setQuestion(question);
		questionObject.setType(questionType);
		try {
			String message = questionManagerService.createQuestion(principal.getName(), questionObject, options);
			mView.setViewName("instructor/questionmanager");
			mView.addObject("message", message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mView;
	}

	@RequestMapping(value = "/instructor/deleteQuestion", method = RequestMethod.GET)
	public String deleteQuestionPage(Principal principal, Model model) {
		return "instructor/deletequestion";
	}

	@RequestMapping(value = "/instructor/listAllQuestions", method = RequestMethod.GET)
	public String listAllQuestionsPage(Principal principal, Model model) {
		return "instructor/listallquestions";
	}

}
