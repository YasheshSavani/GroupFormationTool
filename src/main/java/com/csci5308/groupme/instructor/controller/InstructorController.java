package com.csci5308.groupme.instructor.controller;

import com.csci5308.groupme.student.model.Student;
import com.csci5308.groupme.student.service.StudentService;
import com.csci5308.groupme.student.service.StudentServiceImpl;
import com.csci5308.groupme.user.model.User;
import com.csci5308.groupme.user.service.UserService;
import com.csci5308.groupme.user.service.UserServiceImpl;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import com.csci5308.groupme.instructor.service.InstructorService;
import com.csci5308.groupme.teaching_assistant.model.TeachingAssistant;
import org.omg.PortableServer.REQUEST_PROCESSING_POLICY_ID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class InstructorController {

    @PostMapping("/uploadfile")
    public String uploadCSV(@RequestParam("file") MultipartFile file, Model model) {
        try {
            Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
            List<String[]> records = readAll(reader);

            if (records != null) {
                for (String[] record : records.subList(1, records.size())) {
                    Student student = new Student(record[0] + record[1], record[0]);
                    StudentService studentService = new StudentServiceImpl(student);
                    if (studentService.isNotEnrolled()) {
                        User user = new User();
                        user.setUserName(record[0] + record[1]);
                        user.setLastName(record[1]);
                        user.setFirstName(record[2]);
                        user.setEmail(record[3]);
                        user.setPassword(record[0]);
                        String instructorID = "";
                        String courseID = "";
                        if (studentService.enrol(user, instructorID, courseID)) {
                            UserService userService = new UserServiceImpl();
                            userService.sendCredentials(user);
                            System.out.println("Student Enrolled Successfully");
                        }
                    }
                }
                model.addAttribute("status", "Students enrolled successfully.");
                return "CourseAdmin";
            }
        } catch (Exception e) {
            model.addAttribute("status", "Enrolment failed.");
            return "CourseAdmin";
        }
        model.addAttribute("status", "Enrolment failed.");
        return "CourseAdmin";
    }

    private List<String[]> readAll(Reader reader) {
        CSVReader csvReader = new CSVReader(reader);
        try {
            List<String[]> records = csvReader.readAll();
            reader.close();
            csvReader.close();
            return records;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    Logger logger = LoggerFactory.getLogger(InstructorController.class);

    @Autowired
    InstructorService instructorService;

    @RequestMapping(value = "/courseoperation", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView showCourseOperationPage(@RequestParam("courseCode") String courseCode,
                                                @RequestParam("courseName") String courseName) {

        ModelAndView mView = new ModelAndView();
        mView.addObject("courseCode", courseCode);
        mView.addObject("courseName", courseName);
        mView.setViewName("operationsoncourse");
        return mView;
    }

    @RequestMapping(value = "/assignTA", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView checkTaAvailability(@RequestParam(value = "email") String emailTA) throws Exception {
        List<TeachingAssistant> informationOfTA = instructorService.findByTAEmailId(emailTA);
        ModelAndView mView = new ModelAndView();
        mView.setViewName("operationsoncourse");
        mView.addObject("dataOfTA", informationOfTA);
        mView.addObject("email", emailTA);
        return mView;
    }

    @RequestMapping(value = "/courseoperation/insertInfo",method = RequestMethod.POST)
    @ResponseBody
    public void insertAssignedTADataInDatabase(@RequestParam(value = "courseCode") String courseCode,
                                               @RequestParam(value = "userName") String userName){

     }


}
