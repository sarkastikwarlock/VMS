package com.assignment.vaccinationmanagementsystemapplication.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.vaccinationmanagementsystemapplication.model.Feedbacks;

public interface FeedbacksRepo extends JpaRepository<Feedbacks, String>{

	Optional<Feedbacks> findFeedbacksByfeedbackId(String feedbackId);


}
