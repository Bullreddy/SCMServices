package com.scm.services.dto;

import java.util.List;

public class GetClassificationResponseDTO {

	private List<ClassificationDTO> classifications;

	public List<ClassificationDTO> getClassifications() {
		return classifications;
	}

	public void setClassifications(List<ClassificationDTO> classifications) {
		this.classifications = classifications;
	}

}
