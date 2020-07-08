package com.csci5308.groupme.survey.strategy.greedy;

import com.csci5308.groupme.survey.constants.Heuristics;

public class GroupingHeuristicFactory {

	public GroupingHeuristic getHeuristic(String heuristic) {
		if(heuristic.equalsIgnoreCase(Heuristics.PAIR_SCORING)) {
			return new PairScoring();
		}
		return null;
	}
	
}
