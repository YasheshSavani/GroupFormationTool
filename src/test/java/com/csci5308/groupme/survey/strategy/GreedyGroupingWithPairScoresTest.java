package com.csci5308.groupme.survey.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.csci5308.groupme.survey.model.Candidate;
import com.csci5308.groupme.survey.model.Group;
import com.csci5308.groupme.survey.strategy.greedy.GreedyGroupingWithPairScores;
import com.csci5308.groupme.survey.strategy.greedy.GroupingHeuristic;
import com.csci5308.groupme.survey.strategy.mock.MockHeuristic;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;
import constants.FilePathConstants;

@ExtendWith(SpringExtension.class)
public class GreedyGroupingWithPairScoresTest {

	private final Logger logger = (Logger) LoggerFactory.getLogger(GreedyGroupingWithPairScoresTest.class);

	private static Map<?, ?> testResponses;
	private static GreedyGroupingWithPairScores greedyGrouping;
	private static Candidate pivotCandidate;
	private static GroupingHeuristic groupingHeuristic;

	@BeforeAll
	public static void init() {
		pivotCandidate = new Candidate();
		greedyGrouping = new GreedyGroupingWithPairScores();
		groupingHeuristic = new MockHeuristic();
		greedyGrouping.setGroupingHeuristic(groupingHeuristic);
		try {
			ObjectMapper mapper = new ObjectMapper();
			testResponses = mapper.readValue(Paths.get(FilePathConstants.TEST_RESPONSES_JSON_FILE).toFile(), Map.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	@Test
	public void getGroupsTest() throws Exception {
		int groupSize = 3;
		List<Candidate> candidates = new ArrayList<Candidate>();
		for (Map.Entry<?, ?> entry : testResponses.entrySet()) {
			Candidate candidate = new Candidate();
			candidate.setQuestionResponsesMap((Map<?, ?>) entry.getValue());
			candidates.add(candidate);
		}
		List<Group> groups = greedyGrouping.getGroups(candidates, groupSize);
		for(Group group: groups) {
			logger.debug("------Group-{}-------", group.getGroupNo());
			int i = 1;
			for(Candidate candidate: group.getCandidates()) {
				logger.debug("Candidate-{} fitness: {}", i++, candidate.getFitness());
			}
		}
		assert(groups.size() >= candidates.size()/groupSize);
		
	}
	
	@Test
	public void getGroupsRemainderGroupTest() throws Exception {
		int groupSize = 4;
		List<Candidate> candidates = new ArrayList<Candidate>();
		for (Map.Entry<?, ?> entry : testResponses.entrySet()) {
			Candidate candidate = new Candidate();
			candidate.setQuestionResponsesMap((Map<?, ?>) entry.getValue());
			candidates.add(candidate);
		}
		List<Group> groups = greedyGrouping.getGroups(candidates, groupSize);
		for(Group group: groups) {
			logger.debug("------Group-{}-------", group.getGroupNo());
			int i = 1;
			for(Candidate candidate: group.getCandidates()) {
				logger.debug("Candidate-{} fitness: {}", i++, candidate.getFitness());
			}
		}
		int remainderGroupSize = groups.get(groups.size() - 1).getCandidates().size();
		assert(groups.size() >= candidates.size()/groupSize);
		assertEquals(remainderGroupSize, candidates.size()%groupSize);
	}

	@Test
	public void generateOneGreedyGroupTest() throws Exception {
		List<Candidate> candidates = new ArrayList<Candidate>();
		pivotCandidate.setQuestionResponsesMap((Map<?, ?>) testResponses.get("c1"));
		for (Map.Entry<?, ?> entry : testResponses.entrySet()) {
			Candidate candidate = new Candidate();
			candidate.setQuestionResponsesMap((Map<?, ?>) entry.getValue());
			candidates.add(candidate);
		}
		int groupSize = 4;
		Group group = greedyGrouping.generateOneGreedyGroup(candidates, pivotCandidate, groupSize);
		int i = 1;
		for (Candidate candidate : group.getCandidates()) {
			logger.debug("Candidate-{} fitness: {}", i++, candidate.getFitness());
		}
		assert (group.getCandidates().size() == groupSize);
	}

}
