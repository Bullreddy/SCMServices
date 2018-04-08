package com.scm.services.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "configuration")
public class Classification {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotNull
	@Size(min = 3, max = 80)
	private String code;

	@NotNull
	@Size(min = 2, max = 80)
	private String name;
	
	@NotNull
	@Size(min = 2, max = 80)
	@Column(name="master_type")
	private String classificationType;

	public Classification() {
	}

	public Classification(long id) {
		this.id = id;
	}

	public Classification(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long value) {
		this.id = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getClassificationType() {
		return classificationType;
	}

	public void setClassificationType(String classificationType) {
		this.classificationType = classificationType;
	}

} 
