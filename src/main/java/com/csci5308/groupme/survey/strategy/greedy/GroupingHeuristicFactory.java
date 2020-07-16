package com.csci5308.groupme.survey.strategy.greedy;

import com.csci5308.groupme.survey.constants.Heuristics;
import com.csci5308.groupme.survey.strategy.MockHeuristic;
;

public class GroupingHeuristicFactory {

	public static GroupingHeuristic getHeuristic(String heuristic) {
		if(heuristic.equalsIgnoreCase(Heuristics.PAIR_SCORES)) {
			return new PairScores();
		}
		if(heuristic.equalsIgnoreCase(Heuristics.MOCK)) {
			return new MockHeuristic();
		}
		return null;
	}
	
}
