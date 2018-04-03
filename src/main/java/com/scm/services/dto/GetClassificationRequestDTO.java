package com.scm.services.dto;

import java.util.List;

import com.bulls.scm.common.vo.ClassificationType;

public class GetClassificationRequestDTO {

	private List<ClassificationType> types;

	public List<ClassificationType> getTypes() {
		return types;
	}

	public void setTypes(List<ClassificationType> types) {
		this.types = types;
	}
}
