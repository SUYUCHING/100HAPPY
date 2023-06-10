package com.aj.casino.common.module.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.aj.casino.common.module.security.SecureSession;
import com.aj.casino.common.module.security.constant.SessionConstants;


public class BaseController {

	@Autowired
	ServletContext context;

	public Long getEntityId(HttpServletRequest req) {
		SecureSession sessObj = (SecureSession)req.getSession().getAttribute(SessionConstants.REQ_SESSION_OBJ);
		return (Long)sessObj.getParameter(SessionConstants.REQ_PROP_ENTITY_ID);
	}

	public Long getUserId(HttpServletRequest req) {
//		SecureSession sessObj = (SecureSession)req.getSession().getAttribute(SessionConstants.REQ_SESSION_OBJ);
//		return (Long)sessObj.getParameter(SessionConstants.REQ_PROP_USER_ID);
		return 1L;
	}
	
	public String getUsername(HttpServletRequest req) {
		SecureSession sessObj = (SecureSession)req.getSession().getAttribute(SessionConstants.REQ_SESSION_OBJ);
		return (String)sessObj.getParameter(SessionConstants.REQ_PROP_USER_NAME);
	}

	public Long getPlayerId(HttpServletRequest req) {
		SecureSession sessObj = (SecureSession)req.getSession().getAttribute(SessionConstants.REQ_SESSION_OBJ);
		return (Long)sessObj.getParameter(SessionConstants.REQ_PROP_USER_ID);
	}
	
	/*start Tom */ //TODO
	public Long getRoleId(HttpServletRequest req) {
		SecureSession sessObj = (SecureSession)req.getSession().getAttribute(SessionConstants.REQ_SESSION_OBJ);
		String roleId = (String)sessObj.getParameter(SessionConstants.REQ_PROP_ACC_TYPE);
		return new Long(roleId);
	}
	/*end Tom */
}
