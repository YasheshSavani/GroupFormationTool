package com.csci5308.groupme.course.survey.strategy;

import java.util.List;

import com.csci5308.groupme.course.survey.model.Candidate;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

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
