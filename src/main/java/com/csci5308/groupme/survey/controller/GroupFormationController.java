package com.csci5308.groupme.survey.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.survey.constants.Algorithms;
import com.csci5308.groupme.survey.model.Candidate;
import com.csci5308.groupme.survey.model.Group;
import com.csci5308.groupme.survey.model.PrettyResponse;
import com.csci5308.groupme.survey.service.GroupFormationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import constants.FilePathConstants;

@Controller
public class GroupFormationController {
	private final Logger logger = LoggerFactory.getLogger(GroupFormationController.class);
	private GroupFormationService groupFormationService = SystemConfig.instance().getGroupFormationService();
	private static Map<?, ?> testResponses;

	@RequestMapping(value = "/getGroups", method = RequestMethod.GET)
	public String showGroupsFormed(Model model) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			testResponses = mapper.readValue(Paths.get(FilePathConstants.TEST_RESPONSES_JSON_FILE).toFile(), Map.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int groupSize = 3;
		List<Candidate> candidates = new ArrayList<Candidate>();
		int i = 0;
		for (Map.Entry<?, ?> entry : testResponses.entrySet()) {
			Candidate candidate = new Candidate();
			candidate.setUserName("testuser");
			candidate.setBannerId("B0" + i++);
			candidate.setFirstName("Test");
			candidate.setLastName("User" + i++);
			candidate.setQuestionResponsesMap((Map<?, ?>) entry.getValue());
			candidates.add(candidate);
		}
		groupFormationService.setGroupingStrategy(Algorithms.GREEDY_GROUPING_WITH_PAIR_SCORES);
		List<Group> groups = groupFormationService.formGroups(candidates, groupSize);
		for (Group group : groups) {
			logger.info("Groups formed: Group-" + group.getGroupNo());

			for (Candidate candidate : group.getCandidates()) {
				logger.info("candidate id : {} ", candidate.getBannerId());
				for (PrettyResponse prettyResponse : candidate.getPrettyResponses()) {
					logger.info("Title : {}", prettyResponse.getTitle());
				}
			}
		}
		model.addAttribute("groups", groups);
		return "survey/displaygroups";
	}
}
