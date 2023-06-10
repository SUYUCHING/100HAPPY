package com.aj.module.security.captcha.exception;

import org.springframework.security.AuthenticationException;

public class CaptchaException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8168787450205548212L;

	public CaptchaException(String msg) {
		super(msg);
	}
}
