package com.assignment.vaccinationmanagementsystemapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.vaccinationmanagementsystemapplication.exception.UserNotFoundException;
import com.assignment.vaccinationmanagementsystemapplication.model.Admins;
import com.assignment.vaccinationmanagementsystemapplication.model.Doctors;
import com.assignment.vaccinationmanagementsystemapplication.repo.DoctorsRepo;

@Service
public class DoctorsService {
	private final DoctorsRepo doctorsRepo;
	
	@Autowired
	public DoctorsService(DoctorsRepo doctorsRepo) {
		this.doctorsRepo = doctorsRepo;
	}
	
	public Doctors addDoctors(Doctors doctor) {
		doctor.setIsQualified(false);
		return doctorsRepo.save(doctor);
	}
	
	public List<Doctors> findAllDoctors(){
		return doctorsRepo.findAll();
	}
	
	public Doctors updateDoctors(Doctors doctor) {
		return doctorsRepo.save(doctor);
	}
	
	public Doctors findDoctorsById(String doctorsId) {
		return doctorsRepo.findDoctorsByDoctorId(doctorsId)
				.orElseThrow(()-> new UserNotFoundException("User by Id "+ doctorsId+" was not found."));
	}
	
	public Doctors findDoctorsByEmail(String doctorsEmail) {
		return doctorsRepo.findDoctorsBydoctorEmail(doctorsEmail)
				.orElseThrow(()-> new UserNotFoundException("User by Email "+ doctorsEmail+" was not found."));
	}
	
	public Doctors findDoctorsByFullname(String doctorsFullname) {
		return doctorsRepo.findDoctorsBydoctorFullname(doctorsFullname)
				.orElseThrow(()-> new UserNotFoundException("User by Fullname "+ doctorsFullname+" was not found."));
	}
	
	
	public Doctors findDoctorsByIdAndPassword(String doctorsId, String doctorsPassword) {
		return doctorsRepo.findDoctorsByDoctorIdAndDoctorPassword(doctorsId, doctorsPassword)
				.orElseThrow(()-> new UserNotFoundException("User not found"));
	}
	
	
	public void deleteDoctors(String doctorsId) {
		doctorsRepo.deleteById(doctorsId);
	}
}
