package com.csci5308.groupme.instructor.controller;

import com.csci5308.groupme.course.dao.CourseDAO;
import com.csci5308.groupme.course.dao.CourseDAOImpl;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.instructor.service.InstructorService;
import com.csci5308.groupme.instructor.service.InstructorServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/InstructorTAStudent")
public class InstructorController {

    @GetMapping("")
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

    @GetMapping("/CourseAdmin")
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

    @GetMapping("/CourseAdmin/Course")
    public String courseAdmin(@RequestParam("courseCode") String courseCode,
                              @RequestParam("courseName") String courseName,
                              @RequestParam("courseCrn") String courseCrn, Model model) {
        try {
            model.addAttribute("courseCode", courseCode);
            model.addAttribute("courseName", courseName);
            model.addAttribute("courseCrn", courseCrn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "coursedetails";
    }

    /*@GetMapping("/InstructorStudent")
    public String instructorStudentHomePage() {

    }

    @GetMapping("/InstructorTA")
    public String instructorTAHomePage() {

    }

    @GetMapping("/Instructor")
    public String instructorTAHomePage() {

    }*/

    @PostMapping("/uploadfile")
    public String uploadCSV(@RequestParam("file") MultipartFile file,
                            @RequestParam("courseCode") String courseCode, Principal principal) {
        InstructorService instructorService = new InstructorServiceImpl();
        instructorService.upload(file, principal.getName(),courseCode);
        return "redirect:/InstructorTAStudent/CourseAdmin";
    }


}
