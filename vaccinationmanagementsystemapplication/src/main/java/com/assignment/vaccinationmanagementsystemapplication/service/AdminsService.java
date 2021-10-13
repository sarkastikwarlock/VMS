package com.assignment.vaccinationmanagementsystemapplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.vaccinationmanagementsystemapplication.exception.UserNotFoundException;
import com.assignment.vaccinationmanagementsystemapplication.model.Admins;
import com.assignment.vaccinationmanagementsystemapplication.model.Doctors;
import com.assignment.vaccinationmanagementsystemapplication.repo.AdminsRepo;

@Service
public class AdminsService {
	private final AdminsRepo adminsRepo;
	
	@Autowired
	public AdminsService(AdminsRepo adminsRepo) {
		this.adminsRepo = adminsRepo;
	}
	
	public Admins addAdmins(Admins admin) {
		return adminsRepo.save(admin);
	}
	
	public List<Admins> findAllAdmins(){
		return adminsRepo.findAll();
	}
	
	public Admins updateAdmins(Admins admin) {
		return adminsRepo.save(admin);
	}
	
	public Admins findAdminsById(String adminsId) {
		return adminsRepo.findAdminsByadminId(adminsId)
				.orElseThrow(()-> new UserNotFoundException("Admin by Id "+ adminsId+" was not found."));
	}
	
	public Admins findAdminsByEmail(String adminsEmail) {
		return adminsRepo.findAdminsByadminEmail(adminsEmail)
				.orElseThrow(()-> new UserNotFoundException("Admin by Email "+ adminsEmail+" was not found."));
	}
	
	public Admins findAdminsByIdAndPassword(String adminsId, String adminsPassword) {
		return adminsRepo.findAdminsByAdminIdAndAdminPassword(adminsId, adminsPassword)
				.orElseThrow(()-> new UserNotFoundException("User not found"));
	}
	
	public void deleteAdmins(String adminsId) {
		adminsRepo.deleteById(adminsId);
	}
	
}
