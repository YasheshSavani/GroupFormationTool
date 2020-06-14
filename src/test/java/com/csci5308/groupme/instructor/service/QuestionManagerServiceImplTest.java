package com.csci5308.groupme.instructor.service;

import com.csci5308.groupme.instructor.dao.QuestionsDAOImpl;
import com.csci5308.groupme.instructor.model.Option;
import com.csci5308.groupme.instructor.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class QuestionManagerServiceImplTest {

    @Mock
    QuestionsDAOImpl questionsDAO;

    @InjectMocks
    QuestionManagerServiceImpl questionManagerService;

    @Test
    public void createQuestion() {
        List<Option> optionList = new ArrayList<>();
        Question question = new Question();
        question.setQuestion("How good is your Python?");
        question.setType("Multiple choice - choose one");
        question.setTitle("Python");

        optionList.add(new Option("Beginner", 1, 1));
        optionList.add(new Option("Intermediate", 2, 2));
        optionList.add(new Option("Expert", 3, 3));

        when(questionsDAO.saveQuestion(question, optionList)).thenReturn("Done");

        String message = questionManagerService.createQuestion(question, optionList);

        assertEquals("Done", message);
    }
}
