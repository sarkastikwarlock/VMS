package com.assignment.vaccinationmanagementsystemapplication.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.vaccinationmanagementsystemapplication.model.VaccinationCentres;

public interface VaccinationCentresRepo extends JpaRepository<VaccinationCentres, String>{

	Optional<VaccinationCentres> findVaccinationCentresBycentreName(String centreName);

	Optional<VaccinationCentres> findVaccinationCentresBycentreAddress(String centreAddress);
}
