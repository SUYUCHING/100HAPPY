package com.aj.module.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.ui.FilterChainOrder;
import org.springframework.security.util.TextUtils;
import org.springframework.util.Assert;

import com.aj.casino2.player.dao.entity.EntityHbmDaoInf;
import com.aj.db.domain.user.DomainSetting;
import com.aj.module.security.dto.SecurityPrincipleDto;

public class AuthenticationProcessingFilter extends AbstractProcessingFilter
{
	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "j_username";
	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "j_password";
	public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";
	private String usernameParameter;
	private String passwordParameter;
   
	private EntityHbmDaoInf entityHbmDao;
	private Integer domainType;
	
	public void setDomainType(Integer domainType) {
		this.domainType = domainType;
	}

	public AuthenticationProcessingFilter()
	{
		usernameParameter = "j_username";
		passwordParameter = "j_password";
	}

	public Authentication attemptAuthentication(HttpServletRequest request)throws AuthenticationException
	{
		
		String username = obtainUsername(request);
		String password = obtainPassword(request);
//		System.out.println("AuthenticationProcessingFilter.attemptAuthentication() : " + username + " - " + password);

		if(username == null)
			username = "";
		if(password == null)
			password = "";
		username = username.trim();
		password = password.trim();

		System.err.println(">> username :"+username);
		System.err.println(">> password :"+password);
		UsernamePasswordAuthenticationToken authRequest  = null;
		
		String requestURL = request.getServerName().toString();
		
		DomainSetting domainSetting = null;
		try {
			domainSetting = entityHbmDao.getEntityDomainByDomain(requestURL,domainType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("AuthenticationProcessingFilter.attemptAuthentication() username: "+username);
		System.out.println("AuthenticationProcessingFilter.attemptAuthentication() domainSetting Id: "+domainSetting.getId());
		System.out.println("AuthenticationProcessingFilter.attemptAuthentication() entity Id: "+domainSetting.getEntity().getId());
		
		SecurityPrincipleDto principleDto = new SecurityPrincipleDto(username, domainSetting.getEntity().getId());
		authRequest  = new UsernamePasswordAuthenticationToken(principleDto, password);

		HttpSession session = request.getSession(false);
		if(session != null || getAllowSessionCreation()) {
			request.getSession().setAttribute("SPRING_SECURITY_LAST_USERNAME", TextUtils.escapeEntities(username));
			System.out.println("session yes");
		}
		setDetails(request, authRequest);
		System.out.println("setDetails");
		try{
			System.out.println(">>set authenticate");
			Authentication authenticate = getAuthenticationManager().authenticate(authRequest);
			System.out.println(">>set authenticate end");
			
			return authenticate;
		}catch(AuthenticationException ae){
			System.out.println(">> ae");
			throw ae;
		}catch(Throwable th){
			System.out.println(">> th");
			th.printStackTrace();
			throw th;
		}
	}

	public String getDefaultFilterProcessesUrl()
	{
		return "/j_spring_security_check";
	}

	protected String obtainPassword(HttpServletRequest request)
	{
		return request.getParameter(passwordParameter);
	}

	protected String obtainUsername(HttpServletRequest request)
	{
		return request.getParameter(usernameParameter);
	}


	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest)
	{
		authRequest.setDetails(super.authenticationDetailsSource.buildDetails(request));
	}

	public void setUsernameParameter(String usernameParameter)
	{
		Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
		this.usernameParameter = usernameParameter;
	}

	public void setPasswordParameter(String passwordParameter)
	{
		Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
		this.passwordParameter = passwordParameter;
	}

	public int getOrder()
	{
		return FilterChainOrder.AUTHENTICATION_PROCESSING_FILTER;
	}

	String getUsernameParameter()
	{
		return usernameParameter;
	}

	String getPasswordParameter()
	{
		return passwordParameter;
	}

	public void setEntityHbmDao(EntityHbmDaoInf entityHbmDao) {
		this.entityHbmDao = entityHbmDao;
	}


}
