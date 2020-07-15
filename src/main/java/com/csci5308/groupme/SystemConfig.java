package com.csci5308.groupme;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.auth.config.PasswordProperties;
import com.csci5308.groupme.course.courseadmin.instructor.dao.QuestionsDAO;
import com.csci5308.groupme.course.courseadmin.instructor.dao.QuestionsDAOImpl;
import com.csci5308.groupme.course.courseadmin.instructor.service.EnrollmentService;
import com.csci5308.groupme.course.courseadmin.instructor.service.EnrollmentServiceImpl;
import com.csci5308.groupme.course.courseadmin.instructor.service.QuestionManagerService;
import com.csci5308.groupme.course.courseadmin.instructor.service.QuestionManagerServiceImpl;
import com.csci5308.groupme.course.courseadmin.teaching_assistant.dao.TeachingAssistantDao;
import com.csci5308.groupme.course.courseadmin.teaching_assistant.dao.TeachingAssistantDaoImpl;
import com.csci5308.groupme.course.courseadmin.teaching_assistant.service.TeachingAssistantService;
import com.csci5308.groupme.course.courseadmin.teaching_assistant.service.TeachingAssistantServiceImpl;
import com.csci5308.groupme.survey.service.GroupFormationService;
import com.csci5308.groupme.survey.service.GroupFormationServiceImpl;

public class SystemConfig {

    private static SystemConfig uniqueInstance = null;
    private QuestionManagerService questionManagerService;
    private QuestionsDAO questionsDAO;
    private TeachingAssistantDao teachingAssistantDao;
    private TeachingAssistantService teachingAssistantService;
    private DatabaseProperties databaseProperties;
    private PasswordProperties passwordProperties;
    private EnrollmentService enrollmentService;
    private GroupFormationService groupFormationService;

    private SystemConfig() {
        teachingAssistantDao = new TeachingAssistantDaoImpl();
        teachingAssistantService = new TeachingAssistantServiceImpl(teachingAssistantDao);
        questionsDAO = new QuestionsDAOImpl();
        questionManagerService = new QuestionManagerServiceImpl(questionsDAO);
        passwordProperties = new PasswordProperties();
        databaseProperties = new DatabaseProperties();
        enrollmentService = new EnrollmentServiceImpl();
        groupFormationService = new GroupFormationServiceImpl();
    }

	public static SystemConfig instance() {
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
        return questionManagerService;
    }

    public void setQuestionManagerService(QuestionManagerService questionManagerService) {
        this.questionManagerService = questionManagerService;
    }

    public QuestionsDAO getQuestionsDAO() {
        return questionsDAO;
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

    public EnrollmentService getEnrollmentService() {
        return enrollmentService;
    }

    public void setEnrollmentService(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }
    
    public GroupFormationService getGroupFormationService() {
		return groupFormationService;
	}

	public void setGroupFormationService(GroupFormationService groupFormationService) {
		this.groupFormationService = groupFormationService;
	}

}

