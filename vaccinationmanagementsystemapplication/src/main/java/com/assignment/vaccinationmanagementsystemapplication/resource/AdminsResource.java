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
import com.assignment.vaccinationmanagementsystemapplication.model.Admins;
import com.assignment.vaccinationmanagementsystemapplication.service.AdminsService;

@RestController
@RequestMapping("/vaccinationManagement/Admins")
public class AdminsResource {
	private AdminsService adminsService;
	
	@Autowired
	public AdminsResource(AdminsService adminsService) {
		this.adminsService = adminsService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Admins>> getAllAdmins(){
		List<Admins> admins = adminsService.findAllAdmins();
		return new ResponseEntity<>(admins, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Admins> addAdmin(@RequestBody Admins admin){
		String tempAdminEmail = admin.getAdminEmail();
		String tempAdminId = admin.getAdminId();
		
		/*if(tempAdminEmail != null && !tempAdminEmail.equals("")) {
			Admins adminObj = adminsService.findAdminsByEmail(tempAdminEmail);
			if(adminObj != null) {
				throw new UserAlreadyExistException("User with Email: "+tempAdminEmail+" has already exist!");
			}
		}
		
		if(tempAdminId != null && !tempAdminId.equals("")) {
			Admins adminObj = adminsService.findAdminsById(tempAdminId);
			if(adminObj != null) {
				throw new UserAlreadyExistException("User with ID: "+tempAdminId+" has already exist!\nPlease enter a different Id.");
			}
		}*/
		Admins newAdmin = adminsService.addAdmins(admin);
		return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
	}
	
	@GetMapping("/findById/{adminId}")
	public ResponseEntity<Admins> findAdminsById(@PathVariable("adminId") String adminId){
		Admins admins = adminsService.findAdminsById(adminId);
		return new ResponseEntity<>(admins, HttpStatus.OK);
	}
	
	@PostMapping("/findByIdAndPassword")
	public ResponseEntity<Admins> findAdminsByIdAndPassword(@RequestBody Admins admins ){
		Admins admin = adminsService.findAdminsByIdAndPassword(admins.getAdminId(), admins.getAdminPassword());
		return new ResponseEntity<>(admin, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Admins> updateAdmins(@RequestBody Admins admins){
		Admins updateAdmin = adminsService.updateAdmins(admins);
		return new ResponseEntity<>(updateAdmin, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{adminId}")
	public ResponseEntity<?> deleteAdmins(@PathVariable("adminId") String adminId){
		adminsService.deleteAdmins(adminId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
