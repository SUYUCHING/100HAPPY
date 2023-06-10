package com.aj.module.security.dto;

import org.springframework.security.userdetails.UserDetails;

public interface LoginRole extends UserDetails {
	
	Long getId();
	String getParam1();
	String getParam2();
	String getParam3();
	String getAccountType();
	Long getEntityId();
	String getEntityIds();
	String getExtPlayerId();
	
	LoginInfo getLoginInfo();
	
	
}
