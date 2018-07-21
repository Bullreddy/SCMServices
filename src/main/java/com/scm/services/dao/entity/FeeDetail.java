package com.scm.services.dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "fee_Details")
@NamedQueries({
@NamedQuery(name="FeeDetails.findByParams", query="SELECT a FROM FeeDetail a WHERE a.studentId= :studentId and a.yearId = :yearId")
})
public class FeeDetail implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	//@OneToOne
	@Column(name = "student_id")
	private int studentId;

	@Column(name = "amount")
	private BigDecimal amount;
	

	@OneToOne
	@JoinColumn(name="student_id",insertable=false,updatable=false)
	Admission student;

	@OneToOne
	@JoinColumn(name="year_id",insertable=false,updatable=false)
	private Classification yearId;

	@Column(name = "collected_by")
	private int collectedBy;
	
	@OneToOne
	@JoinColumn(name="collected_by",insertable=false,updatable=false)
	private User collectedByUser;

	@Column(name = "created_date")
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@Column(name = "collected_date")
	@Temporal(TemporalType.DATE)
	private Date collectedDate;

	@Column(name = "created_by")
	private int createdBy;
	
	@OneToOne
	@JoinColumn(name="created_by",insertable=false,updatable=false)
	private User createdByUser;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Classification getYearId() {
		return yearId;
	}

	public void setYearId(Classification yearId) {
		this.yearId = yearId;
	}

	public int getCollectedBy() {
		return collectedBy;
	}

	public void setCollectedBy(int collectedBy) {
		this.collectedBy = collectedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Admission getStudent() {
		return student;
	}

	public void setStudent(Admission student) {
		this.student = student;
	}

	public User getCollectedByUser() {
		return collectedByUser;
	}

	public void setCollectedByUser(User collectedByUser) {
		this.collectedByUser = collectedByUser;
	}

	public User getCreatedByUser() {
		return createdByUser;
	}

	public void setCreatedByUser(User createdByUser) {
		this.createdByUser = createdByUser;
	}

	public Date getCollectedDate() {
		return collectedDate;
	}

	public void setCollectedDate(Date collectedDate) {
		this.collectedDate = collectedDate;
	}

}
