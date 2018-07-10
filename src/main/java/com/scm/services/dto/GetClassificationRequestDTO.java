package com.scm.services.dto;

import java.util.List;

import com.bulls.scm.common.vo.ClassificationType;

public class GetClassificationRequestDTO {

	private List<ClassificationType> types;
	
	private String scholarshipType;
	
	private String branchID;

	public List<ClassificationType> getTypes() {
		return types;
	}

	public void setTypes(List<ClassificationType> types) {
		this.types = types;
	}

	public String getScholarshipType() {
		return scholarshipType;
	}

	public void setScholarshipType(String scholarshipType) {
		this.scholarshipType = scholarshipType;
	}

	public String getBranchID() {
		return branchID;
	}

	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}
	
}
