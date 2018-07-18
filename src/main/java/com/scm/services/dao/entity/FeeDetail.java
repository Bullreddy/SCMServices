package com.scm.services.dao.entity;

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

@Entity
@Table(name = "fee_Details")
@NamedQueries({
@NamedQuery(name="FeeDetails.findByParams", query="SELECT a FROM FeeDetail a WHERE a.studentId= :studentId and a.yearId = :yearId")
})
public class FeeDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne
	@JoinColumn(name = "id")
	private Admission studentId;

	@Column(name = "amount")
	private BigDecimal amount;

	@OneToOne
	@JoinColumn(name="id",insertable=false,updatable=false)
	private Classification yearId;

	@Column(name = "collected_by")
	private User collectedBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "created_by")
	private User createdBy;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Admission getStudentId() {
		return studentId;
	}

	public void setStudentId(Admission studentId) {
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

	public User getCollectedBy() {
		return collectedBy;
	}

	public void setCollectedBy(User collectedBy) {
		this.collectedBy = collectedBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

}
