package com.assignment.vaccinationmanagementsystemapplication.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="Admins")
public class Admins implements Serializable{
	
	@Id
	@Column(unique = true)
	private String adminId;
	@Column(unique = true)
	private String adminEmail;
	private String adminPassword;
	
	protected Admins() {}
	
	public Admins(String adminId, String adminEmail, String adminPassword) {
		this.adminId = adminId;
		this.adminEmail = adminEmail;
		this.adminPassword = adminPassword;
	}
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	
	@Override
	public String toString() {
		return "Admins{"+
				"Id=" + adminId +
				", E-mail='"+adminEmail+ "\'" +
				", Password='"+adminPassword+ "\'" +
				"}";
	}
	
}
