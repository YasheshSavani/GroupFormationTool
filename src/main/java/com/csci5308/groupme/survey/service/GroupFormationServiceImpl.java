package com.csci5308.groupme.survey.service;

import java.util.List;

import org.slf4j.LoggerFactory;

import com.csci5308.groupme.survey.constants.Algorithms;
import com.csci5308.groupme.survey.constants.Heuristics;
import com.csci5308.groupme.survey.model.Candidate;
import com.csci5308.groupme.survey.model.Group;
import com.csci5308.groupme.survey.strategy.GroupingStrategy;
import com.csci5308.groupme.survey.strategy.greedy.GreedyGroupingWithPairScores;
import com.csci5308.groupme.survey.strategy.greedy.GroupingHeuristic;
import com.csci5308.groupme.survey.strategy.greedy.GroupingHeuristicFactory;

import ch.qos.logback.classic.Logger;

public class GroupFormationServiceImpl implements GroupFormationService{

	private final Logger logger = (Logger) LoggerFactory.getLogger(GroupFormationServiceImpl.class);
	private GroupingStrategy groupingStrategy;
	private GroupingHeuristic groupingHeuristic;
	private GroupingHeuristicFactory groupingHeuristicFactory;
		
	public GroupFormationServiceImpl() {
		groupingHeuristicFactory = new GroupingHeuristicFactory();
	}
	
	@Override
	public void setGroupingStrategy(String algorithm) {
		if(algorithm.equalsIgnoreCase(Algorithms.GREEDY_GROUPING_WITH_PAIR_SCORES)) {
			this.groupingHeuristic = groupingHeuristicFactory.getHeuristic(Heuristics.PAIR_SCORING);
			groupingStrategy = new GreedyGroupingWithPairScores();
			groupingStrategy.setGroupingHeuristic(groupingHeuristic);
			logger.info("Using algorithm: {}", algorithm);			
			}		
	}
	
	@Override
	public GroupingStrategy getGroupingStrategy() {
		return groupingStrategy;
	}
		
	@Override
	public List<Group> formGroups(List<Candidate> candidates, Integer groupSize) {
		//List<Candidate> candidates = new ArrayList<Candidate>();
//		for(SurveyResponse surveyResponse: surveyResponses) {
//			candidates.add(new Candidate(surveyResponse));			
//		}
		List<Group> groups = groupingStrategy.getGroups(candidates, groupSize);
	    
		return groups;
	}
	
}
