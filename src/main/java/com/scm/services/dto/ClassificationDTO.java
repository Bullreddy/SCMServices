package com.scm.services.dto;

import com.scm.services.common.ClassificationType;

public class ClassificationDTO {

	private long id;

	private String code;

	private String name;
	
	private ClassificationType classificationType;

	public ClassificationDTO() {
	}

	public ClassificationDTO(long id) {
		this.id = id;
	}

	public ClassificationDTO(String code, String name) {
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

	public ClassificationType getClassificationType() {
		return classificationType;
	}

	public void setClassificationType(ClassificationType classificationType) {
		this.classificationType = classificationType;
	}

} // class User
