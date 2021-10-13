package com.assignment.vaccinationmanagementsystemapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.vaccinationmanagementsystemapplication.exception.UserNotFoundException;
import com.assignment.vaccinationmanagementsystemapplication.model.Admins;
import com.assignment.vaccinationmanagementsystemapplication.model.Patients;
import com.assignment.vaccinationmanagementsystemapplication.repo.PatientsRepo;

@Service
public class PatientsService {

	private final PatientsRepo patientsRepo;
	
	@Autowired
	private PatientsService(PatientsRepo patientsRepo) {
		this.patientsRepo = patientsRepo;
	}
	
	public Patients addPatients(Patients patients) {
		return patientsRepo.save(patients);
	}
	
	public List<Patients> findAllPatients(){
		return patientsRepo.findAll();
	}
	
	public Patients findPatientsById(String patientsId) {
		return patientsRepo.findPatientsBypatientId(patientsId)
				.orElseThrow(()-> new UserNotFoundException("Patient with id "+patientsId+" is not found"));
	}
	
	public Patients findPatientsByEmail(String patientsEmail) {
		return patientsRepo.findPatientsBypatientEmail(patientsEmail)
				.orElseThrow(()-> new UserNotFoundException("Patient with Email "+patientsEmail+" is not found"));
	}
	
	public Patients findPatientsByFullname(String patientsFullname) {
		return patientsRepo.findPatientsBypatientFullname(patientsFullname)
				.orElseThrow(()-> new UserNotFoundException("Patient with Fullname "+patientsFullname+" is not found"));
	}
	
	public Patients findPatientsByIdAndPassword(String patientsId, String patientsPassword) {
		return patientsRepo.findPatientsByPatientIdAndPatientPassword(patientsId, patientsPassword)
				.orElseThrow(()-> new UserNotFoundException("User not found"));
	}
	
	public Patients updatePatients(Patients patients) {
		return patientsRepo.save(patients);
	}
	
	public void deletePatients(String patientsId) {
		patientsRepo.deleteById(patientsId);
	}
}
