package com.aj.module.security.exception;

import org.springframework.security.AuthenticationException;

public class PlayerLoginException extends AuthenticationException {

	public PlayerLoginException(String msg) {
		super(msg);
	}

}
