package com.csci5308.groupme.survey.strategy;

import java.util.List;

import com.csci5308.groupme.survey.model.Group;
import com.csci5308.groupme.survey.strategy.greedy.Candidate;
import com.csci5308.groupme.survey.strategy.greedy.GroupingHeuristic;

public interface GroupingStrategy {

	public List<Group> group(List<Candidate> candidates, Integer groupSize);

	public void setGroupingHeuristic(GroupingHeuristic groupingHeuristic);
	
	
}
