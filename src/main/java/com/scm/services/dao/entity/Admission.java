package com.scm.services.dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the admission database table.
 * 
 */
@Entity
@NamedQueries({
@NamedQuery(name="Admission.findAll", query="SELECT a FROM Admission a"),
@NamedQuery(name="Admission.findOne", query="SELECT a FROM Admission a WHERE a.admissionNo=:number")
})
@NamedStoredProcedureQuery(
		name = "getstudentsByFilter", 
		procedureName = "getstudents", 
		resultClasses = Admission.class, 
		parameters = {
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class,name="phase"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class,name="trade"),
				@StoredProcedureParameter(mode = ParameterMode.IN, type = Integer.class,name="years")
		}
	)
public class Admission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="academic_year")
	private int academicYearID;
	
	@OneToOne
	@JoinColumn(name="phase",insertable=false,updatable=false)
	Classification academicYear;

	@Temporal(TemporalType.DATE)
	@Column(name="admission_date")
	private Date admissionDate;

	@Column(name="admission_no")
	private String admissionNo;

	@Column(name="alternate_mobile_no")
	private BigDecimal alternateMobileNo;

	private int caste;

	@OneToOne
	@JoinColumn(name="caste",insertable=false,updatable=false)
	private Classification casteDesc;

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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="identification_marks")
	private String identificationMarks;

	@Column(name="mobile_no")
	private BigDecimal mobileNo;

	@Column(name="mother_name")
	private String motherName;

	private String name;

	@Column(name="phase")
	private int phaseID;
	
	@OneToOne
	@JoinColumn(name="phase",insertable=false,updatable=false)
	Classification phase;

	@Column(name="photo_sbmtd")
	private String photoSbmtd;

	@Column(name="present_address")
	private String presentAddress;

	@Column(name="registration_no")
	private String registrationNo;

	private String scholarship;

	private String shift;

	@Column(name="trade")
	private int tradeID;

	@Column(name="type")
	private int typeID;
	
	@OneToOne
	@JoinColumn(name="trade",insertable=false,updatable=false)
	Classification trade;
	
	@OneToOne
	@JoinColumn(name="type",insertable=false,updatable=false)
	Classification type;

	private String UID_Number;

	private String unit;
	
	@Column(name = "category")
	private String category;
	
	@OneToMany(mappedBy="studentid")
	List<StudentCertificate> studentCertificates;
	
	

	public Admission() {
	}

	public int getAcademicYearID() {
		return this.academicYearID;
	}

	public void setAcademicYearID(int academicYearID) {
		this.academicYearID = academicYearID;
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
	
	public Classification getCasteDesc() {
		return casteDesc;
	}

	public void setCasteDesc(Classification casteDesc) {
		this.casteDesc = casteDesc;
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

	public int getPhaseID() {
		return this.phaseID;
	}

	public void setPhaseID(int phaseID) {
		this.phaseID = phaseID;
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

	public int getTradeID() {
		return this.tradeID;
	}

	public void setTradeID(int tradeID) {
		this.tradeID = tradeID;
	}

	public int getTypeID() {
		return this.typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
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

	public Classification getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(Classification academicYear) {
		this.academicYear = academicYear;
	}

	public Classification getPhase() {
		return phase;
	}

	public void setPhase(Classification phase) {
		this.phase = phase;
	}

	public Classification getTrade() {
		return trade;
	}

	public void setTrade(Classification trade) {
		this.trade = trade;
	}

	public Classification getType() {
		return type;
	}

	public void setType(Classification type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<StudentCertificate> getStudentCertificates() {
		return studentCertificates;
	}

	public void setStudentCertificates(List<StudentCertificate> studentCertificates) {
		this.studentCertificates = studentCertificates;
	}
	
	

}