package com.csci5308.groupme.survey.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.csci5308.groupme.course.dao.CourseDetailsDAOImpl;
import com.csci5308.groupme.course.model.Course;
import com.csci5308.groupme.course.service.CourseDetailsServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import constants.FilePathConstants;

@ExtendWith(SpringExtension.class)
public class PairScoringTest {

	@Mock
	private CourseDetailsDAOImpl courseDAO;

	@InjectMocks
	private CourseDetailsServiceImpl courseDetailsServiceImpl;

	@BeforeAll
	public static void init() {
		try {
			// create object mapper instance
			ObjectMapper mapper = new ObjectMapper();

			// convert JSON file to map
			List<Map<?, ?>> maps = mapper.readValue(Paths.get(FilePathConstants.SAMPLE_JSON_FILE).toFile(),
					new TypeReference<List<Map<?, ?>>>() {
					});
			// List<Map<String, Object>> data = mapper.readValue(json, new
			// TypeReference<List<Map<String, Object>>>(){});
			// print map entries
			for (Map<?, ?> map : maps) {
				for (Map.Entry<?, ?> entry : map.entrySet()) {
					System.out.println(entry.getKey() + "=" + entry.getValue());
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Test
	public void calculatePairScoreTest() throws Exception {

	}
}
