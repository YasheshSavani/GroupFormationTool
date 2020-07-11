package com.csci5308.groupme.survey.model;

import java.util.Set;

public class Group {

	private Set<Candidate> candidates;
	
	public Set<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(Set<Candidate> group) {
		this.candidates = group;
	}

	public void add(Candidate candidate) {
		candidates.add(candidate);
	}
		
}
