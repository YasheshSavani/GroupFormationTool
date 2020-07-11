package com.csci5308.groupme.survey.strategy.greedy;

import java.util.List;

import com.csci5308.groupme.survey.model.Candidate;

public interface GroupingHeuristic {

	public <T> T compute();

	public void setCandidates(List<Candidate> candidates);

	
}
