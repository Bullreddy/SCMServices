package com.scm.services.dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the admission database table.
 * 
 */
@Entity
@NamedQuery(name="Admission.findAll", query="SELECT a FROM Admission a")
public class Admission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="academic_year")
	private int academicYear;

	@Temporal(TemporalType.DATE)
	@Column(name="admission_date")
	private Date admissionDate;

	@Column(name="admission_no")
	private String admissionNo;

	@Column(name="alternate_mobile_no")
	private BigDecimal alternateMobileNo;

	private int caste;

	@Temporal(TemporalType.DATE)
	private Date dob;

	@Column(name="dual_mode")
	private String dualMode;

	private String email;

	@Column(name="father_name")
	private String fatherName;

	private String gender;

	@Column(name="highest_qualification")
	private String highestQualification;

	@Id
	private int id;

	@Column(name="identification_marks")
	private String identificationMarks;

	@Column(name="mobile_no")
	private BigDecimal mobileNo;

	@Column(name="mother_name")
	private String motherName;

	private String name;

	private int phase;

	@Column(name="photo_sbmtd")
	private String photoSbmtd;

	@Column(name="present_address")
	private String presentAddress;

	@Column(name="registration_no")
	private String registrationNo;

	private String scholarship;

	private String shift;

	private int trade;

	private int type;

	private String UID_Number;

	private String unit;

	public Admission() {
	}

	public int getAcademicYear() {
		return this.academicYear;
	}

	public void setAcademicYear(int academicYear) {
		this.academicYear = academicYear;
	}

	public Date getAdmissionDate() {
		return this.admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getAdmissionNo() {
		return this.admissionNo;
	}

	public void setAdmissionNo(String admissionNo) {
		this.admissionNo = admissionNo;
	}

	public BigDecimal getAlternateMobileNo() {
		return this.alternateMobileNo;
	}

	public void setAlternateMobileNo(BigDecimal alternateMobileNo) {
		this.alternateMobileNo = alternateMobileNo;
	}

	public int getCaste() {
		return this.caste;
	}

	public void setCaste(int caste) {
		this.caste = caste;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getDualMode() {
		return this.dualMode;
	}

	public void setDualMode(String dualMode) {
		this.dualMode = dualMode;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFatherName() {
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHighestQualification() {
		return this.highestQualification;
	}

	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdentificationMarks() {
		return this.identificationMarks;
	}

	public void setIdentificationMarks(String identificationMarks) {
		this.identificationMarks = identificationMarks;
	}

	public BigDecimal getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(BigDecimal mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getMotherName() {
		return this.motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhase() {
		return this.phase;
	}

	public void setPhase(int phase) {
		this.phase = phase;
	}

	public String getPhotoSbmtd() {
		return this.photoSbmtd;
	}

	public void setPhotoSbmtd(String photoSbmtd) {
		this.photoSbmtd = photoSbmtd;
	}

	public String getPresentAddress() {
		return this.presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public String getRegistrationNo() {
		return this.registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getScholarship() {
		return this.scholarship;
	}

	public void setScholarship(String scholarship) {
		this.scholarship = scholarship;
	}

	public String getShift() {
		return this.shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public int getTrade() {
		return this.trade;
	}

	public void setTrade(int trade) {
		this.trade = trade;
	}

	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUID_Number() {
		return this.UID_Number;
	}

	public void setUID_Number(String UID_Number) {
		this.UID_Number = UID_Number;
	}

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}