package com.csci5308.groupme.course.survey.service;

import java.util.List;

import com.csci5308.groupme.course.survey.model.Group;
import com.csci5308.groupme.course.survey.model.Candidate;
import com.csci5308.groupme.course.survey.strategy.GroupingStrategy;

public interface GroupFormationService {

	public List<Group> formGroups(List<Candidate> candidates, Integer groupSize);

	public GroupingStrategy getGroupingStrategy();

	public void configureGroupingStrategy(String algorithm);
	
	public int validate(List<Candidate> candidates, Integer groupSize);
	
	public List<String> getAllGroupingStrategies();
			
}
