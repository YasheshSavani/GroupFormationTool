package com.csci5308.groupme.auth.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class HomeControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void getApplicationPageTest() throws Exception {
		this.mockMvc.perform(get("/").secure(true)).andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("index"));
	}
	
	@Test
	void forbidAdminLandingPageTest() throws Exception {
		this.mockMvc.perform(get("/admin").secure(true)).andDo(print()).andExpect(status().is(302));
	}
	
	@Test
	void forbidGuestLandingPageTest() throws Exception {
		this.mockMvc.perform(get("/guest").secure(true)).andDo(print()).andExpect(status().is(302));
	}
	
	@Test
	void forbidUserLandingPageTest() throws Exception {
		this.mockMvc.perform(get("/home").secure(true)).andDo(print()).andExpect(status().is(302));
	}
	
	@Test
	@WithMockUser(username = "admin_test", password = "admin19", authorities = { "ROLE_ADMIN" })
	void adminHomePageTest() throws Exception {
		this.mockMvc.perform(get("/admin").secure(true)).andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("admin/home_admin"));
	}
	
	@Test
	@WithMockUser(username = "guest_test", password = "testpassword", authorities = { "ROLE_GUEST" })
	void guestHomePageTest() throws Exception {
		this.mockMvc.perform(get("/guest").secure(true)).andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("guest/home_guest"));
	}
	
	@Test
	@WithMockUser(username = "student_test", password = "testpassword", authorities = { "ROLE_STUDENT"})
	void studentHomePageTest() throws Exception {
//		this.mockMvc.perform(get("/home?isStudent=true").secure(true)).andDo(print()).andExpect(status().isOk())
//				.andExpect(view().name("home"));
	}
	
	@Test
	@WithMockUser(username = "ta_test", password = "testpassword", authorities = { "ROLE_TA"})
	void taHomePageTest() throws Exception {
//		this.mockMvc.perform(get("/home?isTA=true").secure(true)).andDo(print()).andExpect(status().isOk())
//				.andExpect(view().name("home"));
	}
	
	@Test
	@WithMockUser(username = "instructor_test", password = "testpassword", authorities = { "ROLE_INSTRUCTOR"})
	void instructorHomePageTest() throws Exception {
//		this.mockMvc.perform(get("/home?isInstructor=true").secure(true)).andDo(print()).andExpect(status().isOk())
//				.andExpect(view().name("home"));
	}
	
	@Test
	@WithMockUser(username = "stdent_and_ta_test", password = "testpassword", authorities = { "ROLE_STUDENT", "ROLE_TA"})
	void studentTAHomePageTest() throws Exception {
//		this.mockMvc.perform(get("/home?isStudent=true&isTA=true").secure(true)).andDo(print()).andExpect(status().isOk())
//				.andExpect(view().name("home"));
	}

	@Test
	@WithMockUser(username = "stdent_and_inst_test", password = "testpassword", authorities = { "ROLE_STUDENT", "ROLE_INSTRUCTOR"})
	void studentInstructorHomePageTest() throws Exception {
//		this.mockMvc.perform(get("/home?isStudent=true&isInstructor=true").secure(true)).andDo(print()).andExpect(status().isOk())
//				.andExpect(view().name("home"));
	}
	
	@Test
	@WithMockUser(username = "ta_and_inst_test", password = "testpassword", authorities = { "ROLE_TA", "ROLE_INSTRUCTOR"})
	void taInstructorHomePageTest() throws Exception {
//		this.mockMvc.perform(get("/home?isTA=true&isInstructor=true").secure(true)).andDo(print()).andExpect(status().isOk())
//				.andExpect(view().name("home"));
	}
	
	@Test
	@WithMockUser(username = "student_ta_inst_test", password = "testpassword", authorities = {"ROLE_STUDENT", "ROLE_TA", "ROLE_INSTRUCTOR"})
	void studentTAInstructorHomePageTest() throws Exception {
//		this.mockMvc.perform(get("/home?isStudent=true&isTA=true&isInstructor=true").secure(true)).andDo(print()).andExpect(status().isOk())
//				.andExpect(view().name("home"));
	}
		
}
