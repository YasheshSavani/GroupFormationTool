package com.csci5308.groupme.survey.service;

import java.util.List;

import com.csci5308.groupme.survey.model.Candidate;
import com.csci5308.groupme.survey.model.Group;
import com.csci5308.groupme.survey.strategy.GroupingStrategy;

public interface GroupFormationService {

	public List<Group> formGroups(List<Candidate> candidates, Integer groupSize);

	public GroupingStrategy getGroupingStrategy();

	public void setGroupingStrategy(String algorithm);
			
}
