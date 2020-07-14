package com.csci5308.groupme.survey.controller;

import constants.QuestionTypeConstants;
import constants.Roles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import javax.management.relation.Role;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class SurveyOperationControllerTest {

    private final String courseCodeParamString = "courseCode";
    private final String roleNameParamString = "roleName";
    private final String questionTypeParamString = "questionType";
    private final String questionIdParamString = "questionId";
    private final String questionParamString = "question";
    private final String questionTitleParamString = "questionTitle";
    private final String courseCode = "csci0010";
    private final String roleName = Roles.TA;
    private final String instructorRoleName = Roles.INSTRUCTOR;
    private final String questionType = QuestionTypeConstants.numericType;
    private final String questionId = "1";
    private final String question = "How?";
    private final String questionTitle = "Python";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "iuser", password = "password", authorities = {Roles.INSTRUCTOR})
    void showCreateSurveyPageTest() throws Exception {
        this.mockMvc.perform(get("/survey/createSurvey")
                .param(courseCodeParamString, courseCode)
                .param(roleNameParamString, instructorRoleName)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "ysavani", password = "admin19", authorities = {Roles.STUDENT, Roles.TA})
    void addQuestionToSurveyTest() throws Exception {
        this.mockMvc.perform(post("/survey/addQuestionToSurvey")
                .param(questionIdParamString, questionId)
                .param(questionParamString, question)
                .param(questionTitleParamString, questionTitle)
                .param(courseCodeParamString, courseCode)
                .param(roleNameParamString, roleName)
                .param(questionTypeParamString, questionType)).andDo(print()).andExpect(status().is(302))
                .andExpect(view().name("redirect:/survey/createSurvey"));
    }

    @Test
    @WithMockUser(username = "ysavani", password = "admin19", authorities = {Roles.STUDENT, Roles.TA})
    void removeQuestionFromSurveyTest() throws Exception {
        this.mockMvc.perform(post("/survey/removeQuestionFromSurvey")
                .param(questionIdParamString, questionId)
                .param(questionParamString, question)
                .param(questionTitleParamString, questionTitle)
                .param(courseCodeParamString, courseCode)
                .param(roleNameParamString, roleName)
                .param(questionTypeParamString, questionType)).andDo(print()).andExpect(status().is(302))
                .andExpect(view().name("redirect:/survey/createSurvey"));
    }

    @Test
    @WithMockUser(username = "ysavani", password = "admin19", authorities = {Roles.STUDENT, Roles.TA})
    void saveSurveyAndRedirectToCourseAdminTest() throws Exception {
        this.mockMvc.perform(post("/survey/saveSurvey")
                .param(roleNameParamString, roleName)).andDo(print()).andExpect(status().is(302))
                .andExpect(view().name("redirect:/TAcoursepage"));
    }
}