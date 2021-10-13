package com.assignment.vaccinationmanagementsystemapplication.resource;

import java.util.List;

import javax.transaction.Transactional;

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

import com.assignment.vaccinationmanagementsystemapplication.model.VaccinationCentres;
import com.assignment.vaccinationmanagementsystemapplication.service.VaccinationCentresService;

@RestController
@RequestMapping("/vaccinationManagement/VaccinationCentres")
public class VaccinationCentresResource {

	private final VaccinationCentresService centresService;
	
	@Autowired
	public VaccinationCentresResource(VaccinationCentresService centresService) {
		this.centresService = centresService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<VaccinationCentres>> getAllCentres(){
		List<VaccinationCentres> centres = centresService.findAllCentres();
		return new ResponseEntity<>(centres, HttpStatus.OK);
	}
	
	@GetMapping("/findByName/{centreName}")
	public ResponseEntity<VaccinationCentres> findCentresByName(@PathVariable("centreName") String centreName){
		VaccinationCentres centres = centresService.findCentresByName(centreName);
		return new ResponseEntity<>(centres, HttpStatus.OK);
	}
	
	@GetMapping("/findByAddress/{centreAddress}")
	public ResponseEntity<VaccinationCentres> findCentresByAddress(@PathVariable("centreAddress") String centreAddress){
		VaccinationCentres centres = centresService.findCentresByAddress(centreAddress);
		return new ResponseEntity<>(centres, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<VaccinationCentres> addCentres(@RequestBody VaccinationCentres centres){
		VaccinationCentres newCentre = centresService.addCentres(centres);
		return new ResponseEntity<>(newCentre, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<VaccinationCentres> updateCentres(@RequestBody VaccinationCentres centres){
		VaccinationCentres updateCentre = centresService.updateCentres(centres);
		return new ResponseEntity<>(updateCentre, HttpStatus.OK);
	}
	
	@Transactional
	@DeleteMapping("/delete/{centreId}")
	public ResponseEntity<?> deleteCentres(@PathVariable("centreId") String centreId){
		centresService.deleteCentres(centreId );
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
