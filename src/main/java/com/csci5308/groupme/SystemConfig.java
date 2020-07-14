package com.csci5308.groupme;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.auth.config.PasswordProperties;
import com.csci5308.groupme.course.courseadmin.instructor.dao.QuestionsDao;
import com.csci5308.groupme.course.courseadmin.instructor.dao.QuestionsDaoImpl;
import com.csci5308.groupme.course.courseadmin.instructor.service.EnrollmentService;
import com.csci5308.groupme.course.courseadmin.instructor.service.EnrollmentServiceImpl;
import com.csci5308.groupme.course.courseadmin.instructor.service.QuestionManagerService;
import com.csci5308.groupme.course.courseadmin.instructor.service.QuestionManagerServiceImpl;
import com.csci5308.groupme.course.courseadmin.survey.dao.EditSurveyDao;
import com.csci5308.groupme.course.courseadmin.survey.dao.EditSurveyDaoImpl;
import com.csci5308.groupme.course.courseadmin.survey.dao.SurveyOperationDao;
import com.csci5308.groupme.course.courseadmin.survey.dao.SurveyOperationDaoImpl;
import com.csci5308.groupme.course.courseadmin.survey.service.EditSurveyService;
import com.csci5308.groupme.course.courseadmin.survey.service.EditSurveyServiceImpl;
import com.csci5308.groupme.course.courseadmin.survey.service.SurveyOperationService;
import com.csci5308.groupme.course.courseadmin.survey.service.SurveyOperationServiceImpl;
import com.csci5308.groupme.course.courseadmin.teaching_assistant.dao.TeachingAssistantDao;
import com.csci5308.groupme.course.courseadmin.teaching_assistant.dao.TeachingAssistantDaoImpl;
import com.csci5308.groupme.course.courseadmin.teaching_assistant.service.TeachingAssistantService;
import com.csci5308.groupme.course.courseadmin.teaching_assistant.service.TeachingAssistantServiceImpl;

public class SystemConfig {

    private static SystemConfig uniqueInstance = null;
    private QuestionManagerService questionManagerService;
    private QuestionsDao questionsDao;
    private TeachingAssistantDao teachingAssistantDao;
    private TeachingAssistantService teachingAssistantService;
    private DatabaseProperties databaseProperties;
    private PasswordProperties passwordProperties;
    private EnrollmentService enrollmentService;
    private SurveyOperationService surveyOperationService;
    private SurveyOperationDao surveyOperationDao;
    private EditSurveyService editSurveyService;
    private EditSurveyDao editSurveyDao;

    private SystemConfig() {
        teachingAssistantDao = new TeachingAssistantDaoImpl();
        teachingAssistantService = new TeachingAssistantServiceImpl(teachingAssistantDao);
        questionsDao = new QuestionsDaoImpl();
        questionManagerService = new QuestionManagerServiceImpl(questionsDao);
        passwordProperties = new PasswordProperties();
        databaseProperties = new DatabaseProperties();
        enrollmentService = new EnrollmentServiceImpl();
        surveyOperationService = new SurveyOperationServiceImpl(); 
        surveyOperationDao = new SurveyOperationDaoImpl();
        editSurveyService = new EditSurveyServiceImpl(editSurveyDao);
        editSurveyDao = new EditSurveyDaoImpl();
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

    public QuestionsDao getQuestionsDao() {
        return questionsDao;
    }

    public void setQuestionsDao(QuestionsDao questionsDao) {
        this.questionsDao = questionsDao;
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

    public SurveyOperationService getSurveyOperationService() {
        return surveyOperationService;
    }

    public void setSurveyOperationService(SurveyOperationService surveyOperationService) {
        this.surveyOperationService = surveyOperationService;
    }

    public SurveyOperationDao getSurveyOperationDao() {
        return surveyOperationDao;
    }

    public void setSurveyOperationDao(SurveyOperationDao surveyOperationDao) {
        this.surveyOperationDao = surveyOperationDao;
    }
    
    public EditSurveyService getEditSurveyService() {
        return editSurveyService;
    }

    public void setEditSurveyService(EditSurveyService editSurveyService) {
        this.editSurveyService = editSurveyService;
    }
    
    public EditSurveyDao getEditSurveyDao() {
        return editSurveyDao;
    }

    public void setEditSurveyDao(EditSurveyDao editSurveyDao) {
        this.editSurveyDao = editSurveyDao;
    }
}

