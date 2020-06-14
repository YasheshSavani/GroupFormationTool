package com.csci5308.groupme.instructor.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class QuestionTest {

    private Integer questionIdTest = 1;
    private String titleTest = "Python";
    private String questionTest = "What is your proficiency in Python?";
    private String typeTest = "Multiple choice – choose one ";
    //    private List<Option> options = [];
    private LocalDate createdDateTest = LocalDate.now();

    @Test
    public void getQuestionIdTest() {
        Question question = new Question();
        question.setQuestionId(questionIdTest);
        assertEquals(questionIdTest, question.getQuestionId());
    }

    @Test
    public void setQuestionIdTest() {
        Question question = new Question();
        question.setQuestionId(questionIdTest);
        assertEquals(questionIdTest, question.getQuestionId());
    }

    @Test
    public void getTitleTest() {
        Question question = new Question();
        question.setTitle(titleTest);
        assertEquals(titleTest, question.getTitle());
    }

    @Test
    public void setTitleTest() {
        Question question = new Question();
        question.setTitle(titleTest);
        assertEquals(titleTest, question.getTitle());
    }

    @Test
    public void getQuestionTest() {
        Question question = new Question();
        question.setQuestion(questionTest);
        assertEquals(questionTest, question.getQuestion());
    }

    @Test
    public void setQuestionTest() {
        Question question = new Question();
        question.setQuestion(questionTest);
        assertEquals(questionTest, question.getQuestion());
    }

    @Test
    public void getTypeTest() {
        Question question = new Question();
        question.setType(typeTest);
        assertEquals(typeTest, question.getType());
    }

    @Test
    public void setTypeTest() {
        Question question = new Question();
        question.setType(typeTest);
        assertEquals(typeTest, question.getType());
    }

    @Test
    public void getCreatedDateTest() {
        Question question = new Question();
        question.setCreatedDate(createdDateTest);
        assertEquals(createdDateTest, question.getCreatedDate());
    }

    @Test
    public void setCreatedDateTest() {
        Question question = new Question();
        question.setCreatedDate(createdDateTest);
        assertEquals(createdDateTest, question.getCreatedDate());
    }
}
