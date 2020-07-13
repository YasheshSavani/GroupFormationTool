package com.csci5308.groupme.survey.model;

import java.util.Set;

public class Group {

	private Integer groupNo;
	private Set<Candidate> candidates;
	private Candidate pivotCandidate;
	
	
	public Integer getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
	}

	public Candidate getPivotCandidate() {
		return pivotCandidate;
	}

	public void setPivotCandidate(Candidate pivotCandidate) {
		this.pivotCandidate = pivotCandidate;
	}

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
