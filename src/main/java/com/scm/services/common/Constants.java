package com.scm.services.common;

public class Constants {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 20*60;
    public static final String SIGNING_KEY = "devglan123r";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AuthenicationPath="/token/**";
    public static final String Students="/student/getallstudents/**";
    public static final String  DEFAULT_TENANT_ID = "SCMDB";
    public static final String  CURRENT_TENANT_IDENTIFIER = "CURRENT_TENANT_IDENTIFIER";
    public static final String  TENANT_KEY = "tenant";
}
