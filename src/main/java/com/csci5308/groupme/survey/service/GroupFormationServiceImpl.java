package com.csci5308.groupme.survey.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;

import com.csci5308.groupme.survey.constants.Algorithms;
import com.csci5308.groupme.survey.constants.Heuristics;
import com.csci5308.groupme.survey.model.Candidate;
import com.csci5308.groupme.survey.model.Group;
import com.csci5308.groupme.survey.strategy.GroupingStrategy;
import com.csci5308.groupme.survey.strategy.MockGroupingStrategy;
import com.csci5308.groupme.survey.strategy.greedy.GreedyGroupingWithPairScores;
import com.csci5308.groupme.survey.strategy.greedy.GroupingHeuristic;
import com.csci5308.groupme.survey.strategy.greedy.GroupingHeuristicFactory;

import ch.qos.logback.classic.Logger;
import errors.EditCodes;

public class GroupFormationServiceImpl implements GroupFormationService {

	private final Logger logger = (Logger) LoggerFactory.getLogger(GroupFormationServiceImpl.class);
	private GroupingStrategy groupingStrategy;
	private GroupingHeuristic groupingHeuristic;
	private String algorithm;

	@Override
	public void configureGroupingStrategy(String algorithm) {
		this.algorithm = algorithm;
		if (this.algorithm.equalsIgnoreCase(Algorithms.GREEDY_GROUPING_WITH_PAIR_SCORES)) {
			groupingStrategy = new GreedyGroupingWithPairScores();
			this.groupingHeuristic = GroupingHeuristicFactory.getHeuristic(Heuristics.PAIR_SCORES);
			groupingStrategy.setGroupingHeuristic(groupingHeuristic);
			logger.info("Using algorithm: {}", algorithm);
		}
		else if(this.algorithm.equalsIgnoreCase(Algorithms.MOCK_STRATEGY)) {
			groupingStrategy = new MockGroupingStrategy();
		}
	}

	@Override
	public GroupingStrategy getGroupingStrategy() {
		return groupingStrategy;
	}

	@Override
	public List<Group> formGroups(List<Candidate> candidates, Integer groupSize) {
		logger.debug("Executing algorithm: {}", algorithm);
		List<Group> groups = groupingStrategy.getGroups(candidates, groupSize);
		return groups;
	}

	@Override
	public int validate(List<Candidate> candidates, Integer groupSize) {
		int status = EditCodes.SUCCESS;
		if (groupSize == 0) {
			status = EditCodes.GROUP_SIZE_IS_ZERO;
		} else if (groupSize > candidates.size()) {
			status = EditCodes.GROUP_SIZE_GREATER_THAN_STRENGTH;
		}
		return status;
	}

	@Override
	public List<String> getAllGroupingStrategies() {
		List<String> strategies = new ArrayList<String>();
		strategies.add(Algorithms.GREEDY_GROUPING_WITH_PAIR_SCORES);
		return strategies;
	}

}
