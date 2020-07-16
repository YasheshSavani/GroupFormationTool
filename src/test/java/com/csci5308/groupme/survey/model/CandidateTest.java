package com.csci5308.groupme.survey.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;
import constants.FilePathConstants;

public class CandidateTest {

	private static Map<?, ?> candidateResponses;
	private static String userName;
	private static String firstName;
	private static String lastName;
	private static String bannerId;
	private static Integer surveyId;
	private static String stringifiedResponses;
	private static Double fitness;
	private static Candidate candidate;
	private static String questionId;

	@BeforeAll
	public static void init() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<?, ?> allSurveyResponses = mapper
					.readValue(Paths.get(FilePathConstants.TEST_RESPONSES_JSON_FILE).toFile(), Map.class);
			candidateResponses = (Map<?, ?>) allSurveyResponses.get("c1");
			questionId = (String) candidateResponses.entrySet().iterator().next().getKey();
			Map<?, ?> questionParamsMap = (Map<?, ?>) candidateResponses.get(questionId);
			userName = "testuser";
			firstName = "Test";
			lastName = "User";
			bannerId = "B0021";
			surveyId = 3;
			stringifiedResponses = mapper.writeValueAsString(candidateResponses);
			fitness = 0.98234;
			candidate = new Candidate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Test
	public void getFirstNameTest() {
		candidate.setFirstName(firstName);
		assertEquals(firstName, candidate.getFirstName());
	}

	@Test
	public void getLastNameTest() {
		candidate.setLastName(lastName);
		assertEquals(lastName, candidate.getLastName());
	}

	@Test
	public void getSurveyIdTest() {
		candidate.setSurveyId(surveyId);
		assertEquals(surveyId, candidate.getSurveyId());
	}

	@Test
	public void getUserNameTest() {
		candidate.setUserName(userName);
		assertEquals(userName, candidate.getUserName());
	}

	@Test
	public void getBannerIdTest() {
		candidate.setBannerId(bannerId);
		assertEquals(bannerId, candidate.getBannerId());
	}

	@Test
	public void getStringifiedResponsesTest() {
		candidate.setStringifiedResponses(stringifiedResponses);
		assertEquals(stringifiedResponses, candidate.getStringifiedResponses());
	}

	@Test
	public void getFitnessTest() {
		candidate.setFitness(fitness);
		assertEquals(fitness, candidate.getFitness());
	}

	@Test
	public void getQuestionResponsesMapTest() {
		candidate.setQuestionResponsesMap(candidateResponses);
		assertEquals(candidateResponses, candidate.getQuestionResponsesMap());
	}

	@Test
	public void getPrettyResponsesTest() {
		candidate.setQuestionResponsesMap(candidateResponses);
		List<PrettyResponse> prettyResponses = candidate.getPrettyResponses();
		assertEquals(questionId, prettyResponses.get(0).getQuestionId());
	}

}