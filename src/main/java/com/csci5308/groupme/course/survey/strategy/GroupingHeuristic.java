package com.csci5308.groupme.course.survey.strategy;

import java.util.List;

import com.csci5308.groupme.course.survey.model.Candidate;

public interface GroupingHeuristic {

	public Object compute(List<Candidate> candidates, Candidate...predicates);
	
}
