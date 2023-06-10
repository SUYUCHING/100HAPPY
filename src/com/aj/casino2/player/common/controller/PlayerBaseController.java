package com.aj.casino2.player.common.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.aj.casino2.common.constant.ErrorCodeConstant;
import com.aj.module.exception.ApiErrorCodeException;
import com.aj.module.security.SecureSession;
import com.aj.module.security.constant.SessionConstants;
import com.google.gson.JsonObject;

public abstract class PlayerBaseController {
	@Autowired
	ServletContext context;

	public Long getEntityId(HttpServletRequest req) throws ApiErrorCodeException {
		
		try {
			SecureSession sessObj = (SecureSession)req.getSession().getAttribute(SessionConstants.REQ_SESSION_OBJ);
			return (Long)sessObj.getParameter(SessionConstants.REQ_PROP_ENTITY_ID);
		} catch (NullPointerException e) {
			throw new ApiErrorCodeException(ErrorCodeConstant.CODE_SESSION_ERROR, ErrorCodeConstant.DESC_SESSION_ERROR);
		}
	}

	public Long getUserId(HttpServletRequest req) throws ApiErrorCodeException{
		try {
			SecureSession sessObj = (SecureSession)req.getSession().getAttribute(SessionConstants.REQ_SESSION_OBJ);
			return (Long)sessObj.getParameter(SessionConstants.REQ_PROP_USER_ID);
		} catch (NullPointerException e) {
			throw new ApiErrorCodeException(ErrorCodeConstant.CODE_SESSION_ERROR, ErrorCodeConstant.DESC_SESSION_ERROR);
		}
	}
	public String getExtPlayerId(HttpServletRequest req) throws ApiErrorCodeException{
		try {
			SecureSession sessObj = (SecureSession)req.getSession().getAttribute(SessionConstants.REQ_SESSION_OBJ);
			return (String)sessObj.getParameter(SessionConstants.REQ_PROP_EXT_PLAYER_ID);
		} catch (NullPointerException e) {
			throw new ApiErrorCodeException(ErrorCodeConstant.CODE_SESSION_ERROR, ErrorCodeConstant.DESC_SESSION_ERROR);
		}
	}
	
	public String getUsername(HttpServletRequest req) throws ApiErrorCodeException{
		try {
			SecureSession sessObj = (SecureSession)req.getSession().getAttribute(SessionConstants.REQ_SESSION_OBJ);
			return (String)sessObj.getParameter(SessionConstants.REQ_PROP_USER_NAME);
		} catch (NullPointerException e) {
			throw new ApiErrorCodeException(ErrorCodeConstant.CODE_SESSION_ERROR, ErrorCodeConstant.DESC_SESSION_ERROR);
		}
	}

	public Long getPlayerId(HttpServletRequest req) throws ApiErrorCodeException{
		try {
			SecureSession sessObj = (SecureSession)req.getSession().getAttribute(SessionConstants.REQ_SESSION_OBJ);
			return (Long)sessObj.getParameter(SessionConstants.REQ_PROP_USER_ID);
		} catch (NullPointerException e) {
			throw new ApiErrorCodeException(ErrorCodeConstant.CODE_SESSION_ERROR, ErrorCodeConstant.DESC_SESSION_ERROR);
		}
	}
	
	/*start Tom */ //TODO
	public Long getRoleId(HttpServletRequest req) throws ApiErrorCodeException {
		SecureSession sessObj = (SecureSession)req.getSession().getAttribute(SessionConstants.REQ_SESSION_OBJ);
		String roleId = (String)sessObj.getParameter(SessionConstants.REQ_PROP_ACC_TYPE);
		return new Long(roleId);
	}
	/*end Tom */
	
	public ResponseEntity<byte[]> getHtmlFile(HttpServletRequest req, HttpServletResponse res,JsonObject jsonObject) {

		try{
			String filePath = getFilePath(req);

			InputStream in = context.getResourceAsStream(filePath);
			
			if (in==null) {
//				handle404Redirect(req,res);
			}else {
				final HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.parseMediaType("text/html"));

				String text = IOUtils.toString(in, StandardCharsets.UTF_8.name());
				//System.out.println("TestController.getHtmlFile() : " + text);
				text = processPageDataSetting(filePath, text, jsonObject, null);
				//System.out.println("TestController.getHtmlFile()22 : " + text);
				return new ResponseEntity<byte[]>(text.getBytes(StandardCharsets.UTF_8), headers, HttpStatus.OK);
			}
			
		}catch(Exception e){
			e.printStackTrace();
//			handle404Redirect(req,res);
		}

		return null;
	}
	
	private String processPageDataSetting(String url,String text,JsonObject jsonObject,Long entityId) {
		
		try {
			//logger.info("url : " + url + ", entityId : " + entityId);
			/**
			TestPageSetting setting = testPageActionService.getTestPageSetting(url,entityId);
			if(setting!=null) {

				HtmlPageSettingDto settingDto = (HtmlPageSettingDto)new Gson().fromJson(setting.getJsonData(), HtmlPageSettingDto.class);
				
				for(String key :settingDto.getParametermap().keySet()) {
					String content = settingDto.getParametermap().get(key);
					//logger.info("key : " + key);
					text = text.replaceAll("\\{"+key+"\\}", Matcher.quoteReplacement(content));
				}
			}**/
			
			if(jsonObject!=null)
				text = text.replaceAll("\\{\\$\\$data\\$\\$\\}", Matcher.quoteReplacement(jsonObject.toString()));
			return text;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void handle404Redirect(HttpServletRequest req, HttpServletResponse res) {
		try {
			res.sendRedirect("/backoffice/404.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String getFilePath(HttpServletRequest req){
		try{
			String reqUri = req.getServletPath();
			return reqUri;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void verifyParameter(HttpServletRequest req, String[] parameters) throws ApiErrorCodeException {
		
		
		
		for(String parameter : parameters) {
			String parameter2 = req.getParameter(parameter);
			
			if(parameter2==null || parameter2.trim().length()==0) {
				 throw new ApiErrorCodeException(ErrorCodeConstant.CODE_MANDATORY_ERROR, parameter+ ErrorCodeConstant.DESC_MANDATORY_ERROR);
			}
			
		}
		
	}
	
  public JsonObject catchException(Exception ex, JsonObject resObj)throws Exception{
		
		if(ex instanceof ApiErrorCodeException) {
			ApiErrorCodeException a = (ApiErrorCodeException)ex;
			resObj.addProperty(ErrorCodeConstant.LABEL_CODE, a.getErrorCode());
			resObj.addProperty(ErrorCodeConstant.LABEL_DESC, a.getErrorMessage());
		}else {
			resObj.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_FAIL_ERROR);
			resObj.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_FAIL_ERROR);
			ex.printStackTrace();
		}
		
		
		return resObj;
		
	}
	
}
