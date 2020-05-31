package com.csci5308.groupme.course.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class CourseControllerTest {

//	@Autowired
//	private MockMvc mockMvc;
//
//	@Test
//	void getCoursePageByRoleTest() throws Exception {
//
//		this.mockMvc.perform(get("/course")).andDo(print()).andExpect(status().isOk())
//				.andExpect(view().name("GuestCoursePage"));
//
//	}
}
