package com.scm.services.common;

public class Field {

	private String fieldName;
	private Object dataType;
	private boolean isMandatory;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getDataType() {
		return dataType;
	}

	public void setDataType(Object dataType) {
		this.dataType = dataType;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public Field(String fieldName, Object dataType, boolean isMandatory) {
		super();
		this.fieldName = fieldName;
		this.dataType = dataType;
		this.isMandatory = isMandatory;
	}

}
