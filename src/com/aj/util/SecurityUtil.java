package com.aj.util;

import org.springframework.security.providers.encoding.Md5PasswordEncoder;

public class SecurityUtil {
	
	private static Md5PasswordEncoder encoder;

	static {
		encoder = new Md5PasswordEncoder();
	}

	public static String encodePassword(String salt, String password) {
		return encoder.encodePassword(password, salt);
	}


}
