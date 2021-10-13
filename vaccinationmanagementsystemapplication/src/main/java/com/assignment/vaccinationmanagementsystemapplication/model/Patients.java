package com.assignment.vaccinationmanagementsystemapplication.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(schema="Patients")
public class Patients implements Serializable{
	
	@Id
	@Column(unique = true)
	private String patientId;
	private String patientFullname;
	@Column(unique = true)
	private String patientEmail;
	private String patientPassword;
	@Column(unique = true)
	private String patientPhone;
	private String patientDOB;
	private String patientFirstCentre;
	private String patientFirstTime;
	private String patientFirstDate;
	private String patientSecondCentre;
	private String patientSecondTime;
	private String patientSecondDate;
	private String patientVaccinationType;
	private Boolean firstDoseIsDone;
	private Boolean secondDoseIsDone;

	
	protected Patients() {}
	
	public Patients(String patientId, String patientFullname, String patientEmail, String patientPassword, String patientPhone, String patientDOB, String patientFirstCentre, String patientFirstTime, String patientFirstDate,String patientSecondCentre, String patientSecondTime, String patientSecondDate, String patientVaccinationType, boolean firstDoseIsDone, boolean secondDoseIsDone) {
		this.patientId = patientId;
		this.patientFullname = patientFullname;
		this.patientEmail = patientEmail;
		this.patientPassword = patientPassword;
		this.patientPhone = patientPhone;
		this.patientDOB = patientDOB;
		this.patientFirstCentre = patientFirstCentre;
		this.patientFirstTime = patientFirstTime;
		this.patientFirstDate = patientFirstDate;
		this.patientSecondCentre = patientSecondCentre;
		this.patientSecondTime = patientSecondTime;
		this.patientSecondDate = patientSecondDate;
		this.patientVaccinationType = patientVaccinationType;
		this.firstDoseIsDone = firstDoseIsDone;
		this.secondDoseIsDone = secondDoseIsDone;

	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientFullname() {
		return patientFullname;
	}

	public void setPatientFullname(String patientFullname) {
		this.patientFullname = patientFullname;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public String getPatientPassword() {
		return patientPassword;
	}

	public void setPatientPassword(String patientPassword) {
		this.patientPassword = patientPassword;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public String getPatientDOB() {
		return patientDOB;
	}

	public void setPatientDOB(String patientDOB) {
		this.patientDOB = patientDOB;
	}
	
	public String getPatientFirstCentre() {
		return patientFirstCentre;
	}

	public void setPatientFirstCentre(String patientFirstCentre) {
		this.patientFirstCentre = patientFirstCentre;
	}

	public String getPatientFirstTime() {
		return patientFirstTime;
	}

	public void setPatientFirstTime(String patientFirstTime) {
		this.patientFirstTime = patientFirstTime;
	}

	public String getPatientSecondCentre() {
		return patientSecondCentre;
	}

	public void setPatientSecondCentre(String patientSecondCentre) {
		this.patientSecondCentre = patientSecondCentre;
	}

	public String getPatientSecondTime() {
		return patientSecondTime;
	}

	public void setPatientSecondTime(String patientSecondTime) {
		this.patientSecondTime = patientSecondTime;
	}

	public String getPatientFirstDate() {
		return patientFirstDate;
	}

	public void setPatientFirstDate(String patientFirstDate) {
		this.patientFirstDate = patientFirstDate;
	}

	public String getPatientSecondDate() {
		return patientSecondDate;
	}

	public void setPatientSecondDate(String patientSecondDate) {
		this.patientSecondDate = patientSecondDate;
	}

	public String getPatientVaccinationType() {
		return patientVaccinationType;
	}

	public void setPatientVaccinationType(String patientVaccinationType) {
		this.patientVaccinationType = patientVaccinationType;
	}

	public Boolean getFirstDoseIsDone() {
		return firstDoseIsDone;
	}

	public void setFirstDoseIsDone(Boolean firstDoseIsDone) {
		this.firstDoseIsDone = firstDoseIsDone;
	}
	

	public Boolean getSecondDoseIsDone() {
		return secondDoseIsDone;
	}

	public void setSecondDoseIsDone(Boolean secondDoseIsDone) {
		this.secondDoseIsDone = secondDoseIsDone;
	}

	@Override
	public String toString() {
		return "Patients{"+
				"Id=" + patientId +
				", Fullname='"+patientFullname+ "\'" +
				", E-mail='"+patientEmail+ "\'" +
				", Password='"+patientPassword+ "\'" +
				", Phone='"+patientPhone+ "\'" +
				", Date of Birth='"+patientDOB+ "\'" +
				", First Centre='"+patientFirstCentre+ "\'" +
				", First Time='"+patientFirstTime+ "\'" +
				", First Date='"+patientFirstDate+ "\'" +
				", Second Centre='"+patientSecondCentre+ "\'" +
				", Second Time='"+patientSecondTime+ "\'" +
				", Second Date='"+patientSecondDate+ "\'" +
				", Vaccination Type='"+patientVaccinationType+ "\'" +
				", First Dose is Done='"+firstDoseIsDone+ "\'" +
				", Second Dose is Done='"+firstDoseIsDone+ "\'" +
				"}";
	}
}
