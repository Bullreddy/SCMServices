package com.scm.services.dao.entity;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the student_certificates database table.
 * 
 */
@Entity
@Table(name="student_certificates")
@NamedQueries({
	@NamedQuery(name="StudentCertificate.findAll", query="SELECT s FROM StudentCertificate s"),
	@NamedQuery(name="getStudentCertificateByStudentAndCertificateID", query="SELECT s FROM StudentCertificate s where s.studentid = :studentID and s.certificateid = :certificateID"),
	@NamedQuery(name="getStudentCertificateByStudentID", query="SELECT s FROM StudentCertificate s where s.studentid = :studentID")
})



public class StudentCertificate implements Serializable {
	private static final long serialVersionUID = 1L;

	private int certificateid;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private int studentid;

	public StudentCertificate() {
	}

	public int getCertificateid() {
		return this.certificateid;
	}

	public void setCertificateid(int certificateid) {
		this.certificateid = certificateid;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentid() {
		return this.studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

}