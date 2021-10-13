package com.assignment.vaccinationmanagementsystemapplication.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.vaccinationmanagementsystemapplication.model.Feedbacks;
import com.assignment.vaccinationmanagementsystemapplication.service.FeedbacksService;

@RestController
@RequestMapping("/vaccinationManagement/Feedbacks")
public class FeedbacksResource {

	private final FeedbacksService feedbacksService;
	
	@Autowired
	public FeedbacksResource(FeedbacksService feedbacksService) {
		this.feedbacksService = feedbacksService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Feedbacks>> findAllFeedbacks(){
		List<Feedbacks> feedbacks = feedbacksService.findAllFeedbacks();
		return new ResponseEntity<>(feedbacks, HttpStatus.OK);
	}
	
	@GetMapping("/find/{feedbackId}")
	public ResponseEntity<Feedbacks> findFeedbacks(@PathVariable("feedbackId") String feedbackId){
		Feedbacks feedbacks = feedbacksService.findFeedbacks(feedbackId);
		return new ResponseEntity<Feedbacks>(feedbacks, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Feedbacks> addFeedbacks(@RequestBody Feedbacks feedback){
		Feedbacks newFeedback = feedbacksService.addFeedbacks(feedback);
		return new ResponseEntity<>(newFeedback, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Feedbacks> updateFeedbacks(@RequestBody Feedbacks feedback){
		Feedbacks updateFeedback = feedbacksService.updateFeedbacks(feedback);
		return new ResponseEntity<>(updateFeedback, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{feedbackId}")
	public ResponseEntity<?> deleteFeedbacks(@PathVariable("feedbackId") String feedbackId){
		feedbacksService.deleteFeedbacks(feedbackId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
