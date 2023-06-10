package com.aj.module.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aj.module.security.dto.LoginRole;



public interface AfterLoginPropertiesSetService {

	public void setProperties(HttpServletRequest request, HttpServletResponse response,LoginRole loginRole,boolean isAjax);
	
}
