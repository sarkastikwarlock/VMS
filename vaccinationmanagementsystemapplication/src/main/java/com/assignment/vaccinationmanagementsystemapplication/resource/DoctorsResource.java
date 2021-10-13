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
import com.assignment.vaccinationmanagementsystemapplication.model.Doctors;
import com.assignment.vaccinationmanagementsystemapplication.service.DoctorsService;

@RestController
@RequestMapping("/vaccinationManagement/Doctors")
public class DoctorsResource {
	
	private final DoctorsService doctorsService;
	
	@Autowired
	public DoctorsResource(DoctorsService doctorsService) {
		this.doctorsService = doctorsService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Doctors>> getAllDoctors(){
		List<Doctors> doctors = doctorsService.findAllDoctors();
		return new ResponseEntity<>(doctors, HttpStatus.OK);
	}
	
	@GetMapping("/findById/{doctorId}")
	public ResponseEntity<Doctors> findDoctorsById(@PathVariable("doctorId") String doctorId){
		Doctors doctors = doctorsService.findDoctorsById(doctorId);
		return new ResponseEntity<>(doctors, HttpStatus.OK);
	}
	
	
	@PostMapping("/findByIdAndPassword")
	public ResponseEntity<Doctors> findDoctorsByIdAndPassword(@RequestBody Doctors doctors){
		Doctors doctor = doctorsService.findDoctorsByIdAndPassword(doctors.getDoctorId(), doctors.getDoctorPassword());
		return new ResponseEntity<>(doctor, HttpStatus.OK);
	}
	
	
	@GetMapping("/findByEmail/{doctorEmail}")
	public ResponseEntity<Doctors> findDoctorsByEmail(@PathVariable("doctorEmail") String doctorEmail){
		Doctors doctors = doctorsService.findDoctorsByEmail(doctorEmail);
		return new ResponseEntity<>(doctors, HttpStatus.OK);
	}
	
	@GetMapping("/findByFullname/{doctorFullname}")
	public ResponseEntity<Doctors> findDoctorsByFullname(@PathVariable("doctorFullname") String doctorFullname){
		Doctors doctors = doctorsService.findDoctorsByFullname(doctorFullname);
		return new ResponseEntity<>(doctors, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Doctors> addDoctor(@RequestBody Doctors doctor){
		String tempDoctorEmail = doctor.getDoctorEmail();
		String tempDoctorId = doctor.getDoctorId();
		
		/*if(tempDoctorEmail != null && !tempDoctorEmail.equals("")) {
			Doctors doctorObj = doctorsService.findDoctorsByEmail(tempDoctorEmail);
			if(doctorObj != null) {
				throw new UserAlreadyExistException("User with Email: "+tempDoctorEmail+" has already exist!");
			}
		}
		
		if(tempDoctorId != null && !tempDoctorId.equals("")) {
			Doctors doctorObj = doctorsService.findDoctorsById(tempDoctorId);
			if(doctorObj != null) {
				throw new UserAlreadyExistException("User with ID: "+tempDoctorId+" has already exist!\nPlease enter a different Id.");
			}
		}*/
		Doctors newDoctor = doctorsService.addDoctors(doctor);
		return new ResponseEntity<>(newDoctor, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Doctors> updateDoctors(@RequestBody Doctors doctors){
		Doctors updateDoctor = doctorsService.updateDoctors(doctors);
		return new ResponseEntity<>(updateDoctor, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{doctorId}")
	public ResponseEntity<?> deleteDoctors(@PathVariable("doctorId") String doctorId){
		doctorsService.deleteDoctors(doctorId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	
}
