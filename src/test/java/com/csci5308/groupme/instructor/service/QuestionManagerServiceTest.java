package com.csci5308.groupme.instructor.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.csci5308.groupme.instructor.dao.QuestionsDAO;
import com.csci5308.groupme.instructor.dao.QuestionsDaoMock;
import com.csci5308.groupme.instructor.model.Options;
import com.csci5308.groupme.instructor.model.Question;

@ExtendWith(SpringExtension.class)
public class QuestionManagerServiceTest {
	private QuestionManagerService questionManagerService;
	private QuestionsDAO questionsDAO;

	public QuestionManagerServiceTest() {
		questionsDAO = new QuestionsDaoMock();
		questionManagerService = new QuestionManagerServiceImpl(questionsDAO);
	}

	@Test
	public void createQuestionNonMCQ() {
		String instructorUserName = "iuser";
		Question question = new Question();
		Options options = null;
		question.setQuestion("Which programming language is your best suit?");
		question.setType("Free text");
		question.setTitle("Programming Language Preference");
		String message = questionManagerService.createQuestion(instructorUserName, question, options);
		assertEquals("Question created!", message);
	}

//	@Test
//	public void createQuestionMCQ() {
//		List<Option> optionList = new ArrayList<>();
//		Question question = new Question();
//		question.setQuestion("How good is your Python?");
//		question.setType("Multiple choice - choose one");
//		question.setTitle("Python");
//
//		optionList.add(new Option("Beginner", 1, 1));
//		optionList.add(new Option("Intermediate", 2, 2));
//		optionList.add(new Option("Expert", 3, 3));
//
//		when(questionsDAO.saveQuestion(question, optionList)).thenReturn("Done");
//
//		String message = questionManagerService.createQuestion(question, optionList);
//
//		assertEquals("Done", message);
//	}
}
