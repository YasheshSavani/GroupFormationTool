package com.csci5308.groupme.survey.service;

import java.util.List;

import com.csci5308.groupme.survey.model.Group;
import com.csci5308.groupme.survey.model.SurveyResponse;
import com.csci5308.groupme.survey.strategy.GroupingStrategy;
import com.csci5308.groupme.survey.strategy.greedy.GroupingHeuristic;

public interface GroupFormationService {

	public List<Group> formGroups(List<SurveyResponse> surveyResponses, Integer groupSize);

	public GroupingStrategy getGroupingStrategy();

	public void setGroupingStrategy(String algorithm);
			
}
