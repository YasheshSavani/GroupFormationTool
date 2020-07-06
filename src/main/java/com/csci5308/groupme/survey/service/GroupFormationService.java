package com.csci5308.groupme.survey.service;

import java.util.List;

import com.csci5308.groupme.survey.model.Group;
import com.csci5308.groupme.survey.model.SurveyResponse;

public interface GroupFormationService {

	public List<Group> formGroups(List<SurveyResponse> surveyResponses, Integer groupSize);
		
}
