package com.csci5308.groupme.instructor.dao;

import com.csci5308.groupme.instructor.model.Option;
import com.csci5308.groupme.instructor.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoMock implements QuestionsDAO {

    @Override
    public int saveNonMCQ(String instructorUserName, Question question) {
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        return questions.size();
    }

    @Override
    public int saveMultipleChoiceQuestion(Question question, List<Option> optionList, String instructorUserName) {
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        return questions.size() + optionList.size();
    }

    @Override
    public List<Question> findAllQuestions(String instructorUserName) {
        return null;
    }

    @Override
    public int removeQuestion(String instructorUserName, Question question) {
        return 0;
    }

    @Override
    public List<Question> findAllTitles(String instructorUserName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Question> findAllSortedTitlesByTitles(String instructorUserName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Question> findAllSortedTitlesByDates(String instructorUserName) {
        // TODO Auto-generated method stub
        return null;
    }

}
