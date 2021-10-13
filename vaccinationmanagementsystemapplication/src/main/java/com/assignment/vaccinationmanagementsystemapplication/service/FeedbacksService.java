package com.assignment.vaccinationmanagementsystemapplication.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.vaccinationmanagementsystemapplication.exception.UserNotFoundException;
import com.assignment.vaccinationmanagementsystemapplication.model.Feedbacks;
import com.assignment.vaccinationmanagementsystemapplication.repo.FeedbacksRepo;

@Service
public class FeedbacksService {

	private final FeedbacksRepo feedbacksRepo;
	
	@Autowired
	public FeedbacksService(FeedbacksRepo feedbacksRepo) {
		this.feedbacksRepo = feedbacksRepo;
	}
	
	public Feedbacks addFeedbacks(Feedbacks feedbacks) {
		feedbacks.setFeedbackId(UUID.randomUUID().toString());
		return feedbacksRepo.save(feedbacks);
	}
	
	public Feedbacks updateFeedbacks(Feedbacks feedbacks) {
		return feedbacksRepo.save(feedbacks);
	}
	
	public List<Feedbacks> findAllFeedbacks(){
		return feedbacksRepo.findAll();
	}
	
	public Feedbacks findFeedbacks(String feedbackId) {
		return feedbacksRepo.findFeedbacksByfeedbackId(feedbackId).orElseThrow(()->new UserNotFoundException("Feedback with id "+feedbackId+" is not found."));
	}
	
	public void deleteFeedbacks(String feedbacksId) {
		feedbacksRepo.deleteById(feedbacksId);
	}
	
	
	
}
