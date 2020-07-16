package com.csci5308.groupme.course.survey.strategy;

import java.util.List;

import com.csci5308.groupme.course.survey.model.Candidate;
import com.csci5308.groupme.course.survey.model.Group;

public interface GroupingStrategy {

	public List<Group> getGroups(List<Candidate> candidates, Integer groupSize);

	public void setGroupingHeuristic(GroupingHeuristic groupingHeuristic);
		
}
