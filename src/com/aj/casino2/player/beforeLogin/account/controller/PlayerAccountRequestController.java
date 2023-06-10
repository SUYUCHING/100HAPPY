package com.aj.casino2.player.beforeLogin.account.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aj.casino2.common.constant.ErrorCodeConstant;
import com.aj.casino2.player.beforeLogin.account.service.PlayerAccountService;
import com.aj.casino2.player.common.controller.PlayerBaseController;
import com.aj.module.exception.ApiErrorCodeException;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/breq/account")
public class PlayerAccountRequestController extends PlayerBaseController{

	private PlayerAccountService playerAccountService;
	
	public void setPlayerAccountService(PlayerAccountService playerAccountService) {
		this.playerAccountService = playerAccountService;
	}

	private String getRequestIp(HttpServletRequest req) {
		String ipAddress = req.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = req.getRemoteAddr();
		}

		String ipAddr = null;
		if(ipAddress.indexOf(",")>=0) {
			String[] ipaddrArr = ipAddress.split(",");
			ipAddr = ipaddrArr[0];
		}else {
			ipAddr = ipAddress;
		}

		return ipAddr;
	}
	
	// declare parameter
	private static String REQ_USERNAME = "username";
	private static String REQ_PASSWORD = "password";
	private static String REQ_CONFIRM_PASSWORD = "confirm_password";
	private static String REQ_EMAIL = "email";
	private static String REQ_MOBILE_NO = "mobile_no";
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody String register(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		System.out.println("PlayerAccountRequestController.register() start ");
		JsonObject resultObj = new JsonObject();
		resultObj.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_SUCCESS);
		resultObj.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_SUCCESS);
		
		try {
			verifyParameter(req, new String[] {REQ_USERNAME,REQ_PASSWORD,REQ_CONFIRM_PASSWORD,REQ_EMAIL,REQ_MOBILE_NO});
			
			String username = req.getParameter(REQ_USERNAME);
			String password = req.getParameter(REQ_PASSWORD);
			String confirmPassword = req.getParameter(REQ_CONFIRM_PASSWORD);
			String email = req.getParameter(REQ_EMAIL);
			String mobileNo = req.getParameter(REQ_MOBILE_NO);
			String domain = req.getServerName().toString();
			
			if(password.compareTo(confirmPassword) != 0) {
				resultObj.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_CONFIRM_PASSWORD_ERROR);
				resultObj.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_CONFIRM_PASSWORD_ERROR);
				return resultObj.toString();
			}
			
			
			playerAccountService.createPlayer(domain, username, password, email, mobileNo, getRequestIp(req));
		} catch (Exception e) {
			catchException(e, resultObj);
		}

		return resultObj.toString();
	}
	
	
	
	
}
