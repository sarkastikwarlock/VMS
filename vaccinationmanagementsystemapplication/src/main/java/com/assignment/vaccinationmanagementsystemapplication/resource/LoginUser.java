package com.assignment.vaccinationmanagementsystemapplication.resource;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.vaccinationmanagementsystemapplication.exception.UserNotFoundException;
import com.assignment.vaccinationmanagementsystemapplication.model.Admins;
import com.assignment.vaccinationmanagementsystemapplication.model.Doctors;
import com.assignment.vaccinationmanagementsystemapplication.model.Patients;
import com.assignment.vaccinationmanagementsystemapplication.model.User;
import com.assignment.vaccinationmanagementsystemapplication.service.AdminsService;
import com.assignment.vaccinationmanagementsystemapplication.service.DoctorsService;
import com.assignment.vaccinationmanagementsystemapplication.service.PatientsService;

@RestController
public class LoginUser {
	
	private AdminsService adminsService;
	private DoctorsService doctorsService;
	private PatientsService patientsService;
	
	
	@GetMapping("/login")
	public Object loginUserAsAdmin(@RequestBody User user) {
		String tempId = user.getUserId();
		String tempPassword = user.getUserPassword();
		Admins adminObj = null;
		Doctors doctorObj = null;
		Patients patientObj = null;
		
		if(tempId != null && tempId != null) {
			adminObj = adminsService.findAdminsByIdAndPassword(tempId, tempPassword);
			System.out.println(adminObj);
			doctorObj = doctorsService.findDoctorsByIdAndPassword(tempId, tempPassword);
			System.out.println(doctorObj);
			patientObj = patientsService.findPatientsById(tempId);
			System.out.println(patientObj.getPatientId());
		}
		
		if(adminObj!=null) {
			return adminObj;
		}else if(doctorObj!=null) {
			return doctorObj;
		}else if(patientObj!=null) {
			return patientObj;
		}
		
		if(adminObj == null && doctorObj == null && patientObj == null) {
			throw new UserNotFoundException("User not found");
		}
		return null;
		
		
		
	}

}
