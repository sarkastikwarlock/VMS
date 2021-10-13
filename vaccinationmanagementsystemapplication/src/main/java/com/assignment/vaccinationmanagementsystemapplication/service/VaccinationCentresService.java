package com.assignment.vaccinationmanagementsystemapplication.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.vaccinationmanagementsystemapplication.exception.UserNotFoundException;
import com.assignment.vaccinationmanagementsystemapplication.model.VaccinationCentres;
import com.assignment.vaccinationmanagementsystemapplication.repo.VaccinationCentresRepo;

@Service
public class VaccinationCentresService {
	
	private final VaccinationCentresRepo centreRepo;
	
	@Autowired
	public VaccinationCentresService(VaccinationCentresRepo centreRepo) {
		this.centreRepo = centreRepo;
	}
	
	public VaccinationCentres addCentres(VaccinationCentres centre) {
		centre.setCentreId(UUID.randomUUID().toString());
		return centreRepo.save(centre);
	}
	
	public List<VaccinationCentres> findAllCentres() {
		return  centreRepo.findAll();
	}
	
	public VaccinationCentres findCentresByName(String centreName) {
		return centreRepo.findVaccinationCentresBycentreName(centreName)
				.orElseThrow(()-> new UserNotFoundException("Vaccination centre "+centreName+" is not found."));
	}
	
	public VaccinationCentres findCentresByAddress(String centreAddress) {
		return centreRepo.findVaccinationCentresBycentreAddress(centreAddress)
				.orElseThrow(()-> new UserNotFoundException("Vaccination centre with address "+centreAddress+" is not found."));
	}
	
	public VaccinationCentres updateCentres(VaccinationCentres centre) {
		return centreRepo.save(centre);
	}
	
	public void deleteCentres(String centreId) {
		centreRepo.deleteById(centreId);
	}
}
