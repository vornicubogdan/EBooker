package com.app.eBooker.security;

public class JwtProperties {

	public static final String SECRET = "EBookerV1-2020";
	public static final int EXPIRATION_TIME = 3000000; // 50 minutes
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
}
