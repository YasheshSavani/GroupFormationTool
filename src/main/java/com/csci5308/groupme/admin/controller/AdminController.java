package com.csci5308.groupme.admin.controller;

import com.csci5308.groupme.admin.service.AdminService;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseService;
import errors.EditCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

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
        if (status == 1) {
            message = "Course added!";
        } else if (status == EditCodes.COURSE_EXISTS) {
            message = "Course already exists";
        } else {
            message = "Something went wrong. Server couldn't insert the course into the database!";
        }
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
        if (status >= 1) {
            message = "Course deleted!";
        } else if (status == EditCodes.COURSE_DOES_NOT_EXIST) {
            message = "Course does not exist!";
        } else {
            message = "Something went wrong. Server couldn't complete the operation!";
        }
        mView.addObject("message", message);
        return mView;
    }

    @GetMapping("/admin/manageInstructors")
    public ModelAndView getManageInstructorsPage() {
        ModelAndView mView = new ModelAndView("admin/manageinstructors");
        return mView;
    }

    @GetMapping("/admin/createClass")
    public ModelAndView getAddInstucToCoursePage() {
        ModelAndView mView = new ModelAndView("admin/addinstructortocourse");
        return mView;
    }

    @PostMapping("/admin/createClass")
    public ModelAndView addInstucToCourse(@RequestParam("email") String emailId,
                                          @RequestParam("courseCode") String courseCode) throws Exception {
        String message = adminService.assignInstructorToCourse(emailId, courseCode);
        ModelAndView mView = new ModelAndView("admin/addinstructortocourse");
        mView.addObject("message", message);
        return mView;
    }

}
