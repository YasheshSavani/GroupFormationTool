package com.csci5308.groupme;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.instructor.dao.QuestionsDAO;
import com.csci5308.groupme.instructor.dao.QuestionsDAOImpl;
import com.csci5308.groupme.instructor.service.QuestionManagerService;
import com.csci5308.groupme.instructor.service.QuestionManagerServiceImpl;
import com.csci5308.groupme.teaching_assistant.dao.TeachingAssistantDao;
import com.csci5308.groupme.teaching_assistant.dao.TeachingAssistantDaoImpl;
import com.csci5308.groupme.teaching_assistant.service.TeachingAssistantService;
import com.csci5308.groupme.teaching_assistant.service.TeachingAssistantServiceImpl;

public class SystemConfig {
	
	private static SystemConfig uniqueInstance = null;
	private QuestionManagerService questionManagerService;
	private QuestionsDAO questionsDAO;
	private TeachingAssistantDao teachingAssistantDao;
	private TeachingAssistantService teachingAssistantService;
	private DatabaseProperties databaseProperties;
		
	// This private constructor ensures that no class other than System can allocate
	// the System object. The compiler would prevent it.
	private SystemConfig()
	{		
		questionManagerService = new QuestionManagerServiceImpl();
		questionsDAO = new QuestionsDAOImpl();
		databaseProperties = new DatabaseProperties();
		teachingAssistantDao = new TeachingAssistantDaoImpl();
		teachingAssistantService = new TeachingAssistantServiceImpl();
	}
	
	// This is the way the rest of the application gets access to the System object.
		public static SystemConfig instance()
		{
			// Using lazy initialization, this is the one and only place that the System
			// object will be instantiated.
			if (null == uniqueInstance)
			{
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
	
	
}

