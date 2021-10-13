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

import com.assignment.vaccinationmanagementsystemapplication.exception.UserAlreadyExistException;
import com.assignment.vaccinationmanagementsystemapplication.model.Patients;
import com.assignment.vaccinationmanagementsystemapplication.service.PatientsService;

@RestController
@RequestMapping("/vaccinationManagement/Patients")
public class PatientsResource {
	
	private final PatientsService patientsService;
	
	@Autowired
	public PatientsResource(PatientsService patientsService) {
		this.patientsService = patientsService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Patients>> getAllPatients(){
		List<Patients> patients = patientsService.findAllPatients();
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}
	
	@GetMapping("/findById/{patientId}")
	public ResponseEntity<Patients> findPatientsById(@PathVariable("patientId") String patientId){
		Patients patient = patientsService.findPatientsById(patientId);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}
	
	@GetMapping("/findByEmail/{patientEmail}")
	public ResponseEntity<Patients> findPatientsByEmail(@PathVariable("patientEmail") String patientEmail){
		Patients patient = patientsService.findPatientsByEmail(patientEmail);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}
	
	@GetMapping("/findByFullname/{patientFullname}")
	public ResponseEntity<Patients> findPatientsByFullname(@PathVariable("patientFullname") String patientFullname){
		Patients patient = patientsService.findPatientsByFullname(patientFullname);
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}
	
	@PostMapping("/findByIdAndPassword")
	public ResponseEntity<Patients> findPatientsByIdAndPassword(@RequestBody Patients patients){
		Patients patient = patientsService.findPatientsByIdAndPassword(patients.getPatientId(), patients.getPatientPassword());
		return new ResponseEntity<>(patient, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Patients> addPatients(@RequestBody Patients patients){
		String tempPatientEmail = patients.getPatientEmail();
		String tempPatientId = patients.getPatientId();
		/*if(tempPatientEmail != null && !tempPatientEmail.equals("")) {
			Patients patientObj = patientsService.findPatientsByEmail(tempPatientEmail);
			if(patientObj != null) {
				throw new UserAlreadyExistException("User with Email: "+tempPatientEmail+" has already exist!");
			}
		}
		
		if(tempPatientId != null && !tempPatientId.equals("")) {
			Patients patientObj = patientsService.findPatientsById(tempPatientId);
			if(patientObj != null) {
				throw new UserAlreadyExistException("User with ID: "+tempPatientId+" has already exist!\nPlease enter a different Id.");
			}
		}
		*/
		
		Patients newPatients = patientsService.addPatients(patients);
		return new ResponseEntity<>(newPatients, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Patients> updatePatients(@RequestBody Patients patients){
		Patients updatePatients = patientsService.updatePatients(patients);
		return new ResponseEntity<>(updatePatients,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{patientId}")
	public ResponseEntity<?> deletePatients(@PathVariable("patientId") String patientId){
		patientsService.deletePatients(patientId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
