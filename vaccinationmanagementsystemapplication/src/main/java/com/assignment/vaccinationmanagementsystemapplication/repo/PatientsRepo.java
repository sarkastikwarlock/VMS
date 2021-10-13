package com.assignment.vaccinationmanagementsystemapplication.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.vaccinationmanagementsystemapplication.model.Patients;

public interface PatientsRepo extends JpaRepository<Patients, String>{

	Optional<Patients> findPatientsBypatientId(String patientsId);

	Optional<Patients> findPatientsBypatientEmail(String patientsEmail);

	Optional<Patients> findPatientsBypatientFullname(String patientsFullname);

	Optional<Patients> findPatientsByPatientIdAndPatientPassword(String patientsId, String patientsPassword);


}
