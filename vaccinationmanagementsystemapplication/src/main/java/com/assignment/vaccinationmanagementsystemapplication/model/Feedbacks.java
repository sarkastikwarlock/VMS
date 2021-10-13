package com.assignment.vaccinationmanagementsystemapplication.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(schema="Feedbacks")
public class Feedbacks implements Serializable{
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(unique = true)
	private String feedbackId;
	private int feedbackRatings;
	private String feedbackType;
	private String feedbackDetails;
	private String feedbackTime;
	
	protected Feedbacks() {}
	
	public Feedbacks(String feedbackId, int feedbackRatings, String feedbackType, String feedbackDetails, String feedbackTime) {
		this.feedbackId = feedbackId;
		this.feedbackRatings = feedbackRatings;
		this.feedbackType = feedbackType;
		this.feedbackDetails = feedbackDetails;
		this.feedbackTime = feedbackTime;
	}

	public String getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}

	public int getFeedbackRatings() {
		return feedbackRatings;
	}

	public void setFeedbackRatings(int feedbackRatings) {
		this.feedbackRatings = feedbackRatings;
	}

	public String getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

	public String getFeedbackDetails() {
		return feedbackDetails;
	}

	public void setFeedbackDetails(String feedbackDetails) {
		this.feedbackDetails = feedbackDetails;
	}
	
	public String getFeedbackTime() {
		return feedbackTime;
	}

	public void setFeedbackTime(String feedbackTime) {
		this.feedbackTime = feedbackTime;
	}

	@Override
	public String toString() {
		return "Feedbacks{"+
				"Id=" + feedbackId +
				", Ratings='"+feedbackRatings+ "\'" +
				", Type='"+feedbackType+ "\'" +
				", Details='"+feedbackDetails+ "\'" +
				", Time='"+feedbackTime+ "\'" +
				"}";
	}
}
