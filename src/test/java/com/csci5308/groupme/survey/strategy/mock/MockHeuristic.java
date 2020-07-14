package com.csci5308.groupme.survey.strategy.mock;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.csci5308.groupme.survey.model.Candidate;
import com.csci5308.groupme.survey.strategy.greedy.GroupingHeuristic;
import com.csci5308.groupme.survey.strategy.greedy.PairScores;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;
import constants.FilePathConstants;

public class MockHeuristic implements GroupingHeuristic{

	private final Logger logger = (Logger) LoggerFactory.getLogger(MockHeuristic.class);
	
	@Override
	public List<Candidate> compute(List<Candidate> candidates, Candidate... predicates) {
		logger.debug("Mock heuristic" + candidates.get(0).getQuestionResponsesMap());
		for(Candidate candidate: candidates) {
			candidate.setFitness(Math.random());
		}
		return candidates;
	}

}
