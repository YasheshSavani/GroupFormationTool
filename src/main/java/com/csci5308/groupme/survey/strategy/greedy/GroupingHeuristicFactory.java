package com.csci5308.groupme.survey.strategy.greedy;

import com.csci5308.groupme.survey.constants.Heuristics;

public class GroupingHeuristicFactory {

	public static GroupingHeuristic getHeuristic(String heuristic) {
		if(heuristic.equalsIgnoreCase(Heuristics.PAIR_SCORES)) {
			return new PairScores();
		}
		else if(heuristic.equalsIgnoreCase(Heuristics.MOCK)) {
			return new PairScores();
		}
		return null;
	}
	
}
