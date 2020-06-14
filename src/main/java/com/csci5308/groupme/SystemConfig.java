package com.csci5308.groupme;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.auth.config.PasswordProperties;
import com.csci5308.groupme.course.dao.CourseDAO;
import com.csci5308.groupme.course.dao.CourseDAOImpl;
import com.csci5308.groupme.course.service.CourseService;
import com.csci5308.groupme.course.service.CourseServiceImpl;
import com.csci5308.groupme.instructor.dao.InstructorDAO;
import com.csci5308.groupme.instructor.dao.InstructorDAOImpl;
import com.csci5308.groupme.instructor.dao.QuestionsDAO;
import com.csci5308.groupme.instructor.dao.QuestionsDAOImpl;
import com.csci5308.groupme.instructor.service.InstructorService;
import com.csci5308.groupme.instructor.service.InstructorServiceImpl;
import com.csci5308.groupme.instructor.service.QuestionManagerService;
import com.csci5308.groupme.instructor.service.QuestionManagerServiceImpl;
import com.csci5308.groupme.teaching_assistant.dao.TeachingAssistantDao;
import com.csci5308.groupme.teaching_assistant.dao.TeachingAssistantDaoImpl;
import com.csci5308.groupme.teaching_assistant.service.TeachingAssistantService;
import com.csci5308.groupme.teaching_assistant.service.TeachingAssistantServiceImpl;
import com.csci5308.groupme.user.dao.UserDao;
import com.csci5308.groupme.user.dao.UserDaoImpl;
import com.csci5308.groupme.user.service.UserService;
import com.csci5308.groupme.user.service.UserServiceImpl;

public class SystemConfig {

    private static SystemConfig uniqueInstance = null;
    private QuestionManagerService questionManagerService;
    private QuestionsDAO questionsDAO;
    private TeachingAssistantDao teachingAssistantDao;
    private TeachingAssistantService teachingAssistantService;
    private DatabaseProperties databaseProperties;
    private PasswordProperties passwordProperties;
    private CourseService courseService;
    private CourseDAO courseDAO;
    private InstructorService instructorService;
    private InstructorDAO instructorDAO;
    private UserService userService;
    private UserDao userDao;

    // This private constructor ensures that no class other than System can allocate
    // the System object. The compiler would prevent it.
    private SystemConfig() {
        teachingAssistantDao = new TeachingAssistantDaoImpl();
        teachingAssistantService = new TeachingAssistantServiceImpl();
        passwordProperties = new PasswordProperties();
        databaseProperties = new DatabaseProperties();
    }

    // This is the way the rest of the application gets access to the System object.
    public static SystemConfig instance() {
        // Using lazy initialization, this is the one and only place that the System
        // object will be instantiated.
        if (null == uniqueInstance) {
            uniqueInstance = new SystemConfig();
        }
        return uniqueInstance;
    }

    public TeachingAssistantDao getTeachingAssistantDao() {
        return teachingAssistantDao;
    }

    public void setTeachingAssistantDao(TeachingAssistantDao teachingAssistantDao) {
        this.teachingAssistantDao = teachingAssistantDao;
    }

    public TeachingAssistantService getTeachingAssistantService() {
        return teachingAssistantService;
    }

    public void setTeachingAssistantService(TeachingAssistantService teachingAssistantService) {
        this.teachingAssistantService = teachingAssistantService;
    }

    public QuestionManagerService getQuestionManagerService() {
        return new QuestionManagerServiceImpl();
    }

    public void setQuestionManagerService(QuestionManagerService questionManagerService) {
        this.questionManagerService = questionManagerService;
    }

    public QuestionsDAO getQuestionsDAO() {
        return new QuestionsDAOImpl();
    }

    public void setQuestionsDAO(QuestionsDAO questionsDAO) {
        this.questionsDAO = questionsDAO;
    }

    public DatabaseProperties getDatabaseProperties() {
        return databaseProperties;
    }

    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public PasswordProperties getPasswordProperties() {
        return passwordProperties;
    }

    public void setPasswordProperties(PasswordProperties passwordProperties) {
        this.passwordProperties = passwordProperties;
    }

    public CourseService getCourseService() {
        return new CourseServiceImpl();
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public CourseDAO getCourseDAO() {
        return new CourseDAOImpl();
    }

    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    public InstructorService getInstructorService() {
        return new InstructorServiceImpl();
    }

    public void setInstructorService(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    public InstructorDAO getInstructorDAO() {
        return new InstructorDAOImpl();
    }

    public void setInstructorDAO(InstructorDAO instructorDAO) {
        this.instructorDAO = instructorDAO;
    }

    public UserService getUserService() {
        return new UserServiceImpl();
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}

