package com.assignment.vaccinationmanagementsystemapplication.model;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(schema="VaccinationCentres")
public class VaccinationCentres implements Serializable{
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@Column(unique = true)
	private String centreId;
	@Column(unique = true)
	private String centreName;
	private String centreAddress;
	@Column(unique = true)
	private String centreEmail;
	@Column(unique = true)
	private String centrePhone;
	private String centreOpening;
	private String centreClosing;
	private Boolean isQualified;
	private String centreVaccine;
	
	protected VaccinationCentres() {}
	
	public VaccinationCentres(String centreId, String centreName, String centreAddress, String centreEmail, String centrePhone, String centreOpening, String centreClosing, Boolean isQualified, String centreVaccine) {
		this.centreId = centreId;
		this.centreName = centreName;
		this.centreAddress = centreAddress;
		this.centreEmail = centreEmail;
		this.centrePhone = centrePhone;
		this.centreClosing = centreClosing;
		this.isQualified = isQualified;
		this.centreVaccine = centreVaccine;
	}

	public String getCentreId() {
		return centreId;
	}

	public void setCentreId(String centreId) {
		this.centreId = centreId;
	}

	public String getCentreName() {
		return centreName;
	}

	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}

	public String getCentreAddress() {
		return centreAddress;
	}

	public void setCentreAddress(String centreAddress) {
		this.centreAddress = centreAddress;
	}

	public String getCentreEmail() {
		return centreEmail;
	}

	public void setCentreEmail(String centreEmail) {
		this.centreEmail = centreEmail;
	}

	public String getCentrePhone() {
		return centrePhone;
	}

	public void setCentrePhone(String centrePhone) {
		this.centrePhone = centrePhone;
	}

	public String getCentreOpening() {
		return centreOpening;
	}

	public void setCentreOpening(String centreOpening) {
		this.centreOpening = centreOpening;
	}
	
	public String getCentreClosing() {
		return centreClosing;
	}

	public void setCentreClosing(String centreClosing) {
		this.centreClosing = centreClosing;
	}
	
	public Boolean getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(Boolean isQualified) {
		this.isQualified = isQualified;
	}

	public String getCentreVaccine() {
		return centreVaccine;
	}

	public void setCentreVaccine(String centreVaccine) {
		this.centreVaccine = centreVaccine;
	}

	@Override
	public String toString(){
		return "VaccinationCentres{"+
				"Id=" + centreId +
				", Name='"+centreName+ "\'" +
				", E-mail='"+centreEmail+ "\'" +
				", Phone='"+centrePhone+ "\'" +
				", Hours='"+centreOpening+"~"+centreClosing+ "\'" +
				", Qualified='"+isQualified+ "\'" +
				", Vaccine='"+centreVaccine+ "\'" +
				"}";
	}
	
}
