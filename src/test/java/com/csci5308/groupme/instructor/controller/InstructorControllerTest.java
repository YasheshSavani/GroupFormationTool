package com.csci5308.groupme.instructor.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class InstructorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin_test", password = "admin19", authorities = {"ROLE_ADMIN"})
    void showCourseAdminPage() throws Exception {

        this.mockMvc.perform(get("/courseoperation").param("courseCode", "CSCI5308")).andDo(print()).andExpect(status().isOk())
                .andExpect(model().attributeExists("courseCode"));
    }

    @Test
    @WithMockUser(username = "test3", password = "Test000", authorities = {"ROLE_INSTRUCTOR", "ROLE_TA"})
    void instructorTAHomePageTest() throws Exception {
        this.mockMvc.perform(get("/InstructorTAStudent/InstructorTA")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testuser2", password = "admin19", authorities = {"ROLE_INSTRUCTOR", "ROLE_TA", "ROLE_STUDENT"})
    void InstructorTAStudentTest() throws Exception {
        this.mockMvc.perform(get("/InstructorTAStudent")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testuser1", password = "admin19", authorities = {"ROLE_INSTRUCTOR", "ROLE_STUDENT"})
    void instructorStudentHomePageTest() throws Exception {
        this.mockMvc.perform(get("/InstructorTAStudent/InstructorStudent")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "testuser1", password = "admin19", authorities = {"ROLE_INSTRUCTOR"})
    void instructorHomePageTest() throws Exception {
        this.mockMvc.perform(get("/InstructorTAStudent/Instructor")).andDo(print()).andExpect(status().isOk());
    }
}
