package com.scm.services.config;
import java.io.Serializable;
public class AuthToken implements Serializable {

	    private static final long serialVersionUID = 1250166508152483573L;

	    private final String token;

	    public AuthToken(String token) {
	        this.token = token;
	    }

	    public String getToken() {
	        return this.token;
	    }
	
}
