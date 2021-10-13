package com.assignment.vaccinationmanagementsystemapplication.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.vaccinationmanagementsystemapplication.model.Doctors;

public interface DoctorsRepo extends JpaRepository<Doctors, String>{

	Optional<Doctors> findDoctorsByDoctorId(String doctorId);

	Optional<Doctors> findDoctorsBydoctorEmail(String doctorsEmail);

	Optional<Doctors> findDoctorsBydoctorFullname(String doctorsFullname);

	Optional<Doctors> findDoctorsByDoctorIdAndDoctorPassword(String doctorsId, String doctorsPassword);
	
}
