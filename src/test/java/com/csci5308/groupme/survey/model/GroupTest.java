package com.csci5308.groupme.survey.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

public class GroupTest {
	private final Logger logger = (Logger) LoggerFactory.getLogger(GroupTest.class);
	private static Group group;

	@BeforeAll
	public static void init() {
		group = new Group();
	}

	@Test
	public void getGroupNoTest() {
		group.setGroupNo(1);
		assertEquals(1, group.getGroupNo());
	}

	@Test
	public void getPivotCandidate() {
		Candidate pivotCandidate = new Candidate();
		pivotCandidate.setUserName("pivot");
		group.setPivotCandidate(pivotCandidate);
		String pivotUserName = group.getPivotCandidate().getUserName();
		assertEquals("pivot", pivotUserName);
	}

	@Test
	public void addAndGetCandidatesTest() {
		for (int i = 0; i < 5; i++) {
			Candidate candidate = new Candidate();
			candidate.setUserName("testUser" + i);
			group.add(candidate);
		}
		assertEquals(5, group.getCandidates().size());
	}

	@Test
	public void sortCandidatesByFitness() {
		Group group = new Group();
		for (int i = 0; i < 5; i++) {
			Candidate candidate = new Candidate();
			candidate.setFitness((double) i);
			group.add(candidate);
		}
		group.sortCandidatesByFitness();
		List<Candidate> candidates = group.getCandidates();
		assertTrue(candidates.get(0).getFitness() > candidates.get(1).getFitness());
		assertTrue(candidates.get(1).getFitness() > candidates.get(2).getFitness());
		assertTrue(candidates.get(2).getFitness() > candidates.get(3).getFitness());
		assertTrue(candidates.get(3).getFitness() > candidates.get(4).getFitness());
	}

}
