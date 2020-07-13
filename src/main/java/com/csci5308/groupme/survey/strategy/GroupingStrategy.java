package com.csci5308.groupme.survey.strategy;

import java.util.List;

import com.csci5308.groupme.survey.model.Candidate;
import com.csci5308.groupme.survey.model.Group;
import com.csci5308.groupme.survey.strategy.greedy.GroupingHeuristic;

public interface GroupingStrategy {

	public List<Group> getGroups(List<Candidate> candidates, Integer groupSize);

	public void setGroupingHeuristic(GroupingHeuristic groupingHeuristic);
		
}
