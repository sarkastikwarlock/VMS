package com.assignment.vaccinationmanagementsystemapplication.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.vaccinationmanagementsystemapplication.model.Admins;

public interface AdminsRepo extends JpaRepository<Admins, String>{

	Optional<Admins> findAdminsByadminId(String adminId);

	
	Optional<Admins> findAdminsByAdminIdAndAdminPassword(String adminId, String adminPassword);


	Optional<Admins> findAdminsByadminEmail(String adminEmail);
	
}
