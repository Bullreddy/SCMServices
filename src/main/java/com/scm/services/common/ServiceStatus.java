package com.scm.services.common;

public enum ServiceStatus {
	SUCCESS(200), INTERNAL_SERVER_ERROR(500), RECORD_NOTFOUND(404), BAD_REQUEST(400);

	private final Integer code;

	ServiceStatus(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public static ServiceStatus getServiceStatus(Integer code) {
		for (ServiceStatus status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return ServiceStatus.SUCCESS;
	}
}
