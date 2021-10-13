package com.assignment.vaccinationmanagementsystemapplication.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "Doctors")
public class Doctors implements Serializable{
	
	@Id
	@Column(unique = true)
	private String doctorId;
	private String doctorFullname;
	@Column(unique = true)
	private String doctorEmail;
	private String doctorPassword;
	@Column(unique = true)
	private String doctorPhone;
	private String doctorDOB;
	private String doctorField;
	@Column(unique = true)
	private String doctorAccreditation;
	private Boolean isQualified;
	private String doctorFirstCentre;
	private String doctorFirstTime;
	private String doctorFirstDate;
	private String doctorSecondCentre;
	private String doctorSecondTime;
	private String doctorSecondDate;
	private String doctorThirdCentre;
	private String doctorThirdTime;
	private String doctorThirdDate;
	private Boolean isAGp;
	
	protected Doctors() {}
	
	public Doctors(String doctorId, String doctorFullname, String doctorEmail, String doctorPassword, String doctorPhone, String doctorDOB, String doctorField, String doctorAccreditation, Boolean isQualified, String doctorFirstCentre, String doctorSecondCentre, String doctorThirdCentre, String doctorFirstTime, String doctorSecondTime, String doctorThirdTime, Boolean isAGp, String doctorFirstDate, String doctorSecondDate, String doctorThirdDate) {
		this.doctorId = doctorId;
		this.doctorFullname = doctorFullname;
		this.doctorEmail = doctorEmail;
		this.doctorPassword = doctorPassword;
		this.doctorPhone = doctorPhone;
		this.doctorDOB = doctorDOB;
		this.doctorField = doctorField;
		this.doctorAccreditation = doctorAccreditation;
		this.isQualified = isQualified;
		this.doctorFirstCentre = doctorFirstCentre;
		this.doctorFirstTime = doctorFirstTime;
		this.doctorSecondCentre = doctorSecondCentre;
		this.doctorSecondTime = doctorSecondTime;
		this.doctorThirdCentre = doctorThirdCentre;
		this.doctorThirdTime = doctorThirdTime;
		this.doctorFirstDate = doctorFirstDate;
		this.doctorSecondDate = doctorSecondDate;
		this.doctorThirdDate = doctorThirdDate;
		this.isAGp = isAGp;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorFullname() {
		return doctorFullname;
	}

	public void setDoctorFullname(String doctorFullname) {
		this.doctorFullname = doctorFullname;
	}

	public String getDoctorEmail() {
		return doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}

	public String getDoctorPassword() {
		return doctorPassword;
	}

	public void setDoctorPassword(String doctorPassword) {
		this.doctorPassword = doctorPassword;
	}

	public String getDoctorPhone() {
		return doctorPhone;
	}

	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}

	public String getDoctorDOB() {
		return doctorDOB;
	}

	public void setDoctorDOB(String doctorDOB) {
		this.doctorDOB = doctorDOB;
	}

	public String getDoctorField() {
		return doctorField;
	}

	public void setDoctorField(String doctorField) {
		this.doctorField = doctorField;
	}

	public String getDoctorAccreditation() {
		return doctorAccreditation;
	}

	public void setDoctorAccredition(String doctorAccreditation) {
		this.doctorAccreditation = doctorAccreditation;
	}

	public Boolean getIsQualified() {
		return isQualified;
	}

	public void setIsQualified(Boolean isQualified) {
		this.isQualified = isQualified;
	}
	
	public String getDoctorFirstCentre() {
		return doctorFirstCentre;
	}

	public void setDoctorFirstCentre(String doctorFirstCentre) {
		this.doctorFirstCentre = doctorFirstCentre;
	}

	public String getDoctorFirstTime() {
		return doctorFirstTime;
	}

	public void setDoctorFirstTime(String doctorFirstTime) {
		this.doctorFirstTime = doctorFirstTime;
	}

	public String getDoctorSecondCentre() {
		return doctorSecondCentre;
	}

	public void setDoctorSecondCentre(String doctorSecondCentre) {
		this.doctorSecondCentre = doctorSecondCentre;
	}

	public String getDoctorSecondTime() {
		return doctorSecondTime;
	}

	public void setDoctorSecondTime(String doctorSecondTime) {
		this.doctorSecondTime = doctorSecondTime;
	}

	public String getDoctorThirdCentre() {
		return doctorThirdCentre;
	}

	public void setDoctorThirdCentre(String doctorThirdCentre) {
		this.doctorThirdCentre = doctorThirdCentre;
	}

	public String getDoctorThirdTime() {
		return doctorThirdTime;
	}

	public void setDoctorThirdTime(String doctorThirdTime) {
		this.doctorThirdTime = doctorThirdTime;
	}
	
	

	public String getDoctorFirstDate() {
		return doctorFirstDate;
	}

	public void setDoctorFirstDate(String doctorFirstDate) {
		this.doctorFirstDate = doctorFirstDate;
	}

	public String getDoctorSecondDate() {
		return doctorSecondDate;
	}

	public void setDoctorSecondDate(String doctorSecondDate) {
		this.doctorSecondDate = doctorSecondDate;
	}

	public String getDoctorThirdDate() {
		return doctorThirdDate;
	}

	public void setDoctorThirdDate(String doctorThirdDate) {
		this.doctorThirdDate = doctorThirdDate;
	}

	public Boolean getIsAGp() {
		return isAGp;
	}

	public void setIsAGp(Boolean isAGp) {
		this.isAGp = isAGp;
	}

	public void setDoctorAccreditation(String doctorAccreditation) {
		this.doctorAccreditation = doctorAccreditation;
	}

	@Override
	public String toString() {
		return "Doctors{"+
				"Id=" + doctorId +
				", Fullname='"+doctorFullname+ "\'" +
				", E-mail='"+doctorEmail+ "\'" +
				", Password='"+doctorPassword+ "\'" +
				", Phone='"+doctorPhone+ "\'" +
				", Date of Birth='"+doctorDOB+ "\'" +
				", Specilisation='"+doctorField+ "\'" +
				", Doctor's Accreditation Id='"+doctorAccreditation+ "\'" +
				", Qualified='"+isQualified+ "\'" +
				", First Centre='"+doctorFirstCentre+ "\'" +
				", First Time='"+doctorFirstTime+ "\'" +
				", First Date='"+doctorFirstDate+ "\'" +
				", Second Centre='"+doctorSecondCentre+ "\'" +
				", Second Time='"+doctorSecondTime+ "\'" +
				", Second Date='"+doctorSecondDate+ "\'" +
				", Third Centre='"+doctorThirdCentre+ "\'" +
				", Third Time='"+doctorThirdTime+ "\'" +
				", Third Date='"+doctorThirdDate+ "\'" +
				", Is a Gp='"+isAGp+ "\'" +
				"}";
	}
	
}
