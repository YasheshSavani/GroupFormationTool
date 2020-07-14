package com.csci5308.groupme.course.courseadmin.instructor.controller;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class QuestionControllerTest {

    private final String titleStringParam = "title";
    private final String questionStringParam = "question";
    private final String typeStringParam = "type";
    private final String optionsModelAttrStringParam = "options";
    private final String title = "Python";
    private final String question = "Proficiency level";
    private final String type = QuestionTypeConstants.numericType;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "iuser", password = "password", authorities = {Roles.INSTRUCTOR})
    void questionManagerPageTest() throws Exception {
        this.mockMvc.perform(get("/instructor/questionManagerPage"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "iuser", password = "password", authorities = {Roles.INSTRUCTOR})
    void createQuestionPageTest() throws Exception {
        this.mockMvc.perform(get("/instructor/createQuestion"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("instructor/createquestion"));
    }

    @Test
    @WithMockUser(username = "iuser", password = "password", authorities = {Roles.INSTRUCTOR})
    void createQuestionTest() throws Exception {
        this.mockMvc.perform(post("/instructor/createQuestion")
                .param(titleStringParam, title)
                .param(questionStringParam, question)
                .param(typeStringParam, type))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "iuser", password = "password", authorities = {Roles.INSTRUCTOR})
    void deleteQuestionPageTest() throws Exception {
        this.mockMvc.perform(get("/instructor/deleteQuestion"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(model().attributeExists(questionStringParam));
    }

    @Test
    @WithMockUser(username = "iuser", password = "password", authorities = {Roles.INSTRUCTOR})
    void listAllQuestionsPageTest() throws Exception {
        this.mockMvc.perform(get("/instructor/listAllTitles"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("instructor/listalltitles"));
    }

    @Test
    @WithMockUser(username = "iuser", password = "password", authorities = {Roles.INSTRUCTOR})
    void listSortedTitlesByTitlesTest() throws Exception {
        this.mockMvc.perform(get("/instructor/sortTitlesByTitles"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("instructor/sortedtitles"));
    }

    @Test
    @WithMockUser(username = "iuser", password = "password", authorities = {Roles.INSTRUCTOR})
    void listSortedTitlesByDatesTest() throws Exception {
        this.mockMvc.perform(get("/instructor/sortTitlesByDates"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("instructor/sortedtitlesdates"));
    }
}