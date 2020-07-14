package com.csci5308.groupme.survey.strategy.greedy;

import java.util.List;

import com.csci5308.groupme.survey.model.Candidate;

public interface GroupingHeuristic {

	public Object compute(List<Candidate> candidates, Candidate...predicates);
	
}
