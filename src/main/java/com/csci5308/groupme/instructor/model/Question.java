package com.csci5308.groupme.instructor.model;

import java.sql.Date;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

public class Question {

    private Integer questionId;
    private String title;
    private String question;
    private String type;
    private Date createdDate;

    public Question() {
	}

    public Question(String questionTitle) {
        this.title = questionTitle;
    }
    
    public Question(String questionTitle, Integer questionId, Date questionDate) {
        this.title = questionTitle;
        this.questionId = questionId;
        this.createdDate = questionDate;
	}

	public Question(String questionTitle, Integer questionId, Date questionDate, String question, String questionType) {
        this.title = questionTitle;
        this.questionId = questionId;
        this.createdDate = questionDate;
        this.question = question;
        this.type = questionType;
        //logger.info("type is "+type);
	}

	public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedDate() {

        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


}
