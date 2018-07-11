package com.scm.services.controller;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.scm.services.common.Constants;


@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

	private static final String DEFAULT_TENANT_ID = "tenant_1";

	@Override
	public String resolveCurrentTenantIdentifier() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (requestAttributes != null) {
			String identifier = (String) requestAttributes.getAttribute("CURRENT_TENANT_IDENTIFIER",RequestAttributes.SCOPE_REQUEST);
			if (identifier != null) {
				return identifier;
			}
		}
		return DEFAULT_TENANT_ID;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}