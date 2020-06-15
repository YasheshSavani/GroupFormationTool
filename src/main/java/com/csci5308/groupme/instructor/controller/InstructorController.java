package com.csci5308.groupme.instructor.controller;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.dao.CourseDAO;
import com.csci5308.groupme.course.dao.CourseDAOImpl;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.instructor.dao.QuestionsDAO;
import com.csci5308.groupme.instructor.dao.QuestionsDAOImpl;
import com.csci5308.groupme.instructor.model.Question;
import com.csci5308.groupme.instructor.service.InstructorService;
import com.csci5308.groupme.instructor.service.InstructorServiceImpl;
import com.csci5308.groupme.instructor.service.QuestionManagerService;
import com.csci5308.groupme.teaching_assistant.service.TeachingAssistantService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InstructorController {

	@Autowired
	InstructorService instructorService;
	
	QuestionManagerService questionManagerService;
	
	
	Logger logger = LoggerFactory.getLogger(InstructorController.class);

	@RequestMapping(value = "/courseoperation", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView showCourseAdminPage(@RequestParam("courseCode") String courseCode) {

		ModelAndView mView = new ModelAndView();
		mView.addObject("courseCode", courseCode);
		mView.setViewName("operationoncourse");
		return mView;
	}

	@RequestMapping(value = "/courseoperation", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView insertByTAEmailId(@RequestParam(value = "email") String emailTA,
			@RequestParam(value = "courseCode") String courseCode, Principal p) throws Exception {
		TeachingAssistantService teachingAssistantService = SystemConfig.instance().getTeachingAssistantService();
		String assignmentConfirmation = teachingAssistantService.findByTAEmailId(emailTA, courseCode);
		ModelAndView mView = new ModelAndView();
		if (assignmentConfirmation.equals("True")) {
			mView.addObject("status", "TA Assigned to the Course");
		} else {
			mView.addObject("status", "TA assignment Error");
		}
		mView.setViewName("coursedetails");
		return mView;
	}

	@RequestMapping(value = "/InstructorTAStudent", method = RequestMethod.GET)
	public String instructorTAStudentHomePage(Principal principal, Model model) {
		CourseDAO courseDAO = new CourseDAOImpl();
		try {
			List<Course> coursesList = courseDAO.findCoursesByStudentUserName(principal.getName());
			model.addAttribute("courses", coursesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "InstructorTAStudent";
	}

	@RequestMapping(value = "/InstructorTAStudent/CourseAdmin", method = RequestMethod.GET)
	public String courseAdmin(Model model, Principal principal) {
		CourseDAO courseDAO = new CourseDAOImpl();
		try {
			List<Course> coursesList = courseDAO.findCoursesByInstructorAndTA(principal.getName());
			model.addAttribute("courses", coursesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "CourseAdmin";
	}

	@RequestMapping(value = "/InstructorTAStudent/CourseAdmin/Course", method = RequestMethod.GET)
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

	@RequestMapping(value = "/InstructorTAStudent/InstructorStudent", method = RequestMethod.GET)
	public String instructorStudentHomePage(Principal principal, Model model) {
		instructorTAStudentHomePage(principal, model);
		return "InstructorTAStudent";
	}

	@RequestMapping(value = "/InstructorTAStudent/InstructorTA", method = RequestMethod.GET)
	public String instructorTAHomePage(Principal principal, Model model) {
		CourseDAO courseDAO = new CourseDAOImpl();
		try {
			List<Course> coursesList = courseDAO.findCoursesByInstructorAndTA(principal.getName());
			model.addAttribute("courses", coursesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "CourseAdmin";
	}

	@RequestMapping(value = "/InstructorTAStudent/uploadfile", method = RequestMethod.POST)
	public String uploadCSV(@RequestParam("file") MultipartFile file, @RequestParam("courseCode") String courseCode,
			Principal principal) {
		InstructorService instructorService = new InstructorServiceImpl();
		instructorService.upload(file, principal.getName(), courseCode);
		return "redirect:/InstructorTAStudent/CourseAdmin";
	}

	@RequestMapping(value = "/instructorhomepage", method = RequestMethod.GET)
	public ModelAndView instructorHomePage(Principal principal, Model model) {
		CourseDAO courseDAO = new CourseDAOImpl();
		ModelAndView mView = new ModelAndView();
		mView.setViewName("instructor/instructorhomepage");	
		mView.addObject("isInstructor", true);
		try {
			List<Course> coursesList = courseDAO.findCoursesByInstructor(principal.getName());
			model.addAttribute("courses", coursesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mView;
	}
	
	@RequestMapping(value = "instructor/courseAdminPage", method = RequestMethod.GET)
	public String courseAdminPage(Principal principal, Model model) {
		CourseDAO courseDAO = new CourseDAOImpl();
		try {
			List<Course> coursesList = courseDAO.findCoursesByInstructor(principal.getName());
			model.addAttribute("courses", coursesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "CourseAdmin";
	}
	
	@RequestMapping(value = "instructor/questionManagerPage", method = RequestMethod.GET)
	public String questionManagerPage(Principal principal, Model model) {
		CourseDAO courseDAO = new CourseDAOImpl();
		try {
			List<Course> coursesList = courseDAO.findCoursesByInstructor(principal.getName());
			model.addAttribute("courses", coursesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "instructor/questionmanager";
	}
	
	@RequestMapping(value = "/instructor/createQuestion", method = RequestMethod.GET)
	public String createQuestionPage(Principal principal, Model model) {
		CourseDAO courseDAO = new CourseDAOImpl();
		try {
			List<Course> coursesList = courseDAO.findCoursesByInstructor(principal.getName());
			model.addAttribute("courses", coursesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "instructor/createquestion";
	}
	
	@RequestMapping(value = "/instructor/deleteQuestion", method = RequestMethod.GET)
	public String deleteQuestionPage(Principal principal, Model model) {
		CourseDAO courseDAO = new CourseDAOImpl();
		try {
			List<Course> coursesList = courseDAO.findCoursesByInstructor(principal.getName());
			model.addAttribute("courses", coursesList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "instructor/deletequestion";
	}
	
//	@RequestMapping(value = "/instructor/listAllQuestions", method = RequestMethod.GET)
//	public String listAllQuestionsPage(Principal principal, Model model) {
//		QuestionsDAO questionDAO = new QuestionsDAOImpl();
//		try {
//			List<Course> coursesList = courseDAO.findCoursesByInstructor(principal.getName());
//			model.addAttribute("courses", coursesList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "instructor/listallquestions";
//	}
	
	@RequestMapping(value = "/instructor/listAllQuestions", method = RequestMethod.GET)
	public ModelAndView listAllQuestionsPage(Principal principal) throws Exception 
	{
		Question question = null;
		ModelAndView mView = new ModelAndView("instructor/listallquestions");
		logger.info("Logged in user is "+principal.getName());
		List<String> questionTitles = new ArrayList<>();
		//questionManagerService.test("test");
		questionManagerService.createQuestion(question);
		
		//logger.info("Sample value "+questionManagerService.getAllQuestions(principal.getName()));
		//questionTitles = questionManagerService.getAllQuestions(principal.getName());
        if (questionTitles != null) 
        {
            mView.addObject("titles", questionTitles);
        } 
        else 
        {
            mView.addObject("titles", null);
        }
        return mView;
	}
		
}
