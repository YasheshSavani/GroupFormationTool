package com.csci5308.groupme.course.courseadmin.instructor.service;

import com.csci5308.groupme.course.courseadmin.instructor.constants.QuestionTypeConstants;
import com.csci5308.groupme.course.courseadmin.instructor.dao.QuestionsDao;
import com.csci5308.groupme.course.courseadmin.instructor.dao.QuestionsDaoMock;
import com.csci5308.groupme.course.courseadmin.instructor.model.ListOfOptions;
import com.csci5308.groupme.course.courseadmin.instructor.model.Option;
import com.csci5308.groupme.course.courseadmin.instructor.model.Question;

import errors.EditCodes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class QuestionManagerServiceTest {
    private QuestionManagerService questionManagerService;
    private QuestionsDao questionsDao;

    public QuestionManagerServiceTest() {
        questionsDao = new QuestionsDaoMock();
        questionManagerService = new QuestionManagerServiceImpl(questionsDao);
    }

    @Test
    public void createQuestionNonMCQTest() {
        String instructorUserName = "iuser";
        Question question = new Question();
        ListOfOptions options = null;
        question.setQuestion("Which programming language is your best suit?");
        question.setType(QuestionTypeConstants.FREE_TEXT);
        question.setTitle("Programming Language Preference");
        int status = questionManagerService.createQuestion(instructorUserName, question, options);
        assertEquals(EditCodes.SUCCESS, status);
    }

    @Test
    public void createQuestionMCQTest() {
        ListOfOptions options = new ListOfOptions();
        Question question = new Question();
        question.setQuestion("How good is your Python?");
        question.setType(QuestionTypeConstants.MCQ_CHOOSE_ONE);
        question.setTitle("Python");
        String instructorUserName = "iuser";
        List<Option> optionList = new ArrayList<>();
        optionList.add(new Option("Beginner", 1, 1));
        optionList.add(new Option("Intermediate", 2, 2));
        optionList.add(new Option("Expert", 3, 3));
        options.setOptionList(optionList);
        int status = questionManagerService.createQuestion(instructorUserName, question, options);
        assertEquals(4, status);
    }

    @Test
    public void getAllTitlesTest() {
        String instructorUserName = "iuser";
        List<Question> questions = questionManagerService.getAllTitles(instructorUserName);
        assertEquals(1, questions.size());
    }

    @Test
    public void deleteQuestionTest() {
        String instructorUserName = "iuser";
        Question question = new Question();
        question.setQuestionId(1);
        int status = questionManagerService.deleteQuestion(instructorUserName, question);
        assertEquals(EditCodes.SUCCESS, status);
    }

    @Test
    public void getAllSortedTitlesByTitlesTest() {
        String instructorUserName = "iuser";
        List<Question> questions = questionManagerService.getAllSortedTitlesByTitles(instructorUserName);
        assertEquals(questions.size(), 2);
    }

    @Test
    public void getAllSortedTitlesByDatesTest() {
        String instructorUserName = "iuser";
        List<Question> questions = questionManagerService.getAllSortedTitlesByDates(instructorUserName);
        assertEquals(questions.size(), 2);
    }

    @Test
    public void getAllQuestionsTest() {
        String instructorUserName = "iuser";
        List<Question> questions = questionManagerService.getAllQuestions(instructorUserName);
        assertEquals(questions.size(), 1);

    }
}
