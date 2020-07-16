package com.csci5308.groupme.survey.strategy;

import java.util.ArrayList;
import java.util.List;

import com.csci5308.groupme.survey.model.Candidate;
import com.csci5308.groupme.survey.model.Group;
import com.csci5308.groupme.survey.strategy.greedy.GroupingHeuristic;

@SuppressWarnings("unused")
public class MockGroupingStrategy implements GroupingStrategy{

	private GroupingHeuristic groupingHeuristic;
	
	@Override
	public List<Group> getGroups(List<Candidate> candidates, Integer groupSize) {		
		int groupNo = 0;
		List<Group> groups = new ArrayList<Group>();		
		for (int i = 0; i < candidates.size() / groupSize; i++) {
			Group group = new Group();
			group.setGroupNo(groupNo++);
			groups.add(group);
		}
		return groups;
	}

	@Override
	public void setGroupingHeuristic(GroupingHeuristic groupingHeuristic) {
		this.groupingHeuristic = new MockHeuristic();		
	}
}
