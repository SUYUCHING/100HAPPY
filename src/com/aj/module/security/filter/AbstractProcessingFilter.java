package com.aj.module.security.filter;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.AuthenticationManager;
import org.springframework.security.SpringSecurityMessageSource;
import org.springframework.security.concurrent.ConcurrentLoginException;
import org.springframework.security.concurrent.SessionRegistry;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.event.authentication.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.ui.AuthenticationDetailsSource;
import org.springframework.security.ui.SpringSecurityFilter;
import org.springframework.security.ui.TargetUrlResolver;
import org.springframework.security.ui.TargetUrlResolverImpl;
import org.springframework.security.ui.WebAuthenticationDetailsSource;
import org.springframework.security.ui.rememberme.NullRememberMeServices;
import org.springframework.security.ui.rememberme.RememberMeServices;
import org.springframework.security.ui.savedrequest.SavedRequest;
import org.springframework.security.util.SessionUtils;
import org.springframework.security.util.UrlUtils;
import org.springframework.util.Assert;

import com.aj.casino2.player.module.security.constant.PlayerErrorConstant;
import com.aj.module.security.captcha.exception.CaptchaException;
import com.aj.module.security.constant.SessionConstants;
import com.aj.module.security.dto.UserDetail;
import com.aj.module.security.exception.PlayerLoginException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


public abstract class AbstractProcessingFilter extends SpringSecurityFilter implements InitializingBean, ApplicationEventPublisherAware, MessageSourceAware
{
	public static final String SPRING_SECURITY_SAVED_REQUEST_KEY = "SPRING_SECURITY_SAVED_REQUEST_KEY";
	public static final String SPRING_SECURITY_LAST_EXCEPTION_KEY = "SPRING_SECURITY_LAST_EXCEPTION";

	protected ApplicationEventPublisher eventPublisher;
	protected AuthenticationDetailsSource authenticationDetailsSource;
	private AuthenticationManager authenticationManager;
	protected MessageSourceAccessor messages;
	private Properties exceptionMappings;
	private RememberMeServices rememberMeServices;
	private TargetUrlResolver targetUrlResolver;
	private String authenticationFailureUrl;
	private String defaultTargetUrl;
	private String filterProcessesUrl;
	private boolean alwaysUseDefaultTargetUrl;
	private boolean continueChainBeforeSuccessfulAuthentication;
	private boolean useRelativeContext;
	private boolean invalidateSessionOnSuccessfulAuthentication;
	private boolean migrateInvalidatedSessionAttributes;
	private boolean allowSessionCreation;
	private boolean serverSideRedirect;
	private SessionRegistry sessionRegistry;
	private AfterLoginPropertiesSetService loginPropertiesSetService;

	public AbstractProcessingFilter()
	{
		authenticationDetailsSource = new WebAuthenticationDetailsSource();
		messages = SpringSecurityMessageSource.getAccessor();
		exceptionMappings = new Properties();
		rememberMeServices = null;
		targetUrlResolver = new TargetUrlResolverImpl();
		filterProcessesUrl = getDefaultFilterProcessesUrl();
		alwaysUseDefaultTargetUrl = false;
		continueChainBeforeSuccessfulAuthentication = false;
		useRelativeContext = false;
		invalidateSessionOnSuccessfulAuthentication = false;
		migrateInvalidatedSessionAttributes = true;
		allowSessionCreation = true;
		serverSideRedirect = true;
	}

	public void afterPropertiesSet()
			throws Exception
	{
		  System.out.println("AbstractProcessingFilter.afterPropertiesSet() ");
		Assert.hasLength(filterProcessesUrl, "filterProcessesUrl must be specified");
		Assert.isTrue(UrlUtils.isValidRedirectUrl(filterProcessesUrl), filterProcessesUrl + " isn't a valid redirect URL");
		Assert.hasLength(defaultTargetUrl, "defaultTargetUrl must be specified");
		Assert.isTrue(UrlUtils.isValidRedirectUrl(defaultTargetUrl), defaultTargetUrl + " isn't a valid redirect URL");
		Assert.isTrue(UrlUtils.isValidRedirectUrl(authenticationFailureUrl), authenticationFailureUrl + " isn't a valid redirect URL");
		//Assert.notNull(authenticationManager, "authenticationManager must be specified");
		//Assert.notNull(targetUrlResolver, "targetUrlResolver cannot be null");
		if(rememberMeServices == null) {
           System.out.println("AbstractProcessingFilter.afterPropertiesSet() >> rememberMeServices == null");
			rememberMeServices = new NullRememberMeServices();
			
		}
	}

	public abstract Authentication attemptAuthentication(HttpServletRequest httpservletrequest)
			throws AuthenticationException;

	private boolean isAjaxRequest(HttpServletRequest request) {  
		return true;
	} 
	
	private boolean isDirectFormRequest(HttpServletRequest request) {  
		
		String url = request.getRequestURL().toString();
		if(url.indexOf("/j_spring_security_check")>0 && request.getMethod().compareTo("GET")==0){
			return true;
		}else{
			return false;
		}

	} 

	private Gson gson = new Gson();

	public void doFilterHttp(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		System.out.println("AbstractProcessingFilter.doFilterHttp() doFilterHttp");
		System.out.println(requiresAuthentication(request, response));
		if(requiresAuthentication(request, response))
		{
			/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth!=null&&!(auth instanceof AnonymousAuthenticationToken)){
				//send redirect
				RedirectUtils.sendRedirect(request, response, defaultTargetUrl, useRelativeContext);
				return;
			}*/
			
			//check is it using GET or POST
			/*System.out.println("AbstractProcessingFilter.doFilterHttp() AA");
			if(isDirectFormRequest(request)) {
				//response.sendRedirect("/index.html");
				//send redirect
				RedirectUtils.sendRedirect(request, response, authenticationFailureUrl, useRelativeContext);
				return;
			}
			System.out.println("AbstractProcessingFilter.doFilterHttp() BB");*/
			System.out.println(" requiresAuthentication>> true ");
	
			if(super.logger.isDebugEnabled())
				System.out.println(" isDebugEnabled() >> true ");
				super.logger.debug("Request is to process authentication");
			Authentication authResult;
			try
			{
				System.out.println(" authResult A ");
				onPreAuthentication(request, response);
				System.out.println(" authResult B ");
				authResult = attemptAuthentication(request);
				System.out.println(" authResult C ");
			}
			catch(AuthenticationException failed)
			{
				
				
				System.out.println(">> AuthenticationException");
				
				//ajax
				if(isAjaxRequest(request)){
					
					System.out.println(">>isAjaxRequest: "+failed.getMessage());
					
					String[] errorArr = failed.getMessage().split(",");
					String code=null;
					String message =null;
					
					
					if(errorArr.length>1) {
						code = errorArr[0];
						message = errorArr[1];
					}else {
						code = "-1";
						message = failed.getMessage();
						
					}
					
					System.out.println(">>>failed : " + message);

					SecurityContextHolder.getContext().setAuthentication(null);

					String failedType = SessionConstants.LOGIN_ERROR;
					if(failed instanceof CaptchaException){
						failedType = SessionConstants.LOGIN_ERROR_CAPTCHA;
					}else if(failed instanceof PlayerLoginException) {
						if(failed.getMessage().compareTo(PlayerErrorConstant.ERROR_USER_NOT_FOUND)==0) {
							failedType = SessionConstants.LOGIN_ERROR;
						}else if(failed.getMessage().compareTo(PlayerErrorConstant.ERROR_USER_LOCK)==0) {
							failedType = SessionConstants.LOGIN_ERROR_LOCKED;
						}
					}
					
					
					JsonObject jsonObject = new JsonObject();
					jsonObject.addProperty("code", code);
					jsonObject.addProperty("description", message);
					
					response.setContentType("text/plain");
					response.getOutputStream().write(jsonObject.toString().getBytes("UTF-8"));
					response.flushBuffer();
				}else{
					unsuccessfulAuthentication(request, response, failed);
				}
				return;
			}
			if(continueChainBeforeSuccessfulAuthentication)
				chain.doFilter(request, response);
			successfulAuthentication(request, response, authResult);
			return;
		} else
		{
			System.out.println("AbstractProcessingFilter.doFilterHttp() after login");
			chain.doFilter(request, response);
			System.out.println("AbstractProcessingFilter.doFilterHttp() after login end");
			return;
		}
	}

	public static String obtainFullSavedRequestUrl(HttpServletRequest request)
	{
		System.out.println("AbstractProcessingFilter.obtainFullSavedRequestUrl()");
		SavedRequest savedRequest = getSavedRequest(request);
		return savedRequest != null ? savedRequest.getFullRequestUrl() : null;
	}

	private static SavedRequest getSavedRequest(HttpServletRequest request)
	{
		System.out.println("AbstractProcessingFilter.getSavedRequest() A");
		HttpSession session = request.getSession(false);
		if(session == null)
		{
			System.out.println("AbstractProcessingFilter.getSavedRequest() null");
			return null;
		} else
		{
			System.out.println("AbstractProcessingFilter.getSavedRequest() true");
			SavedRequest savedRequest = (SavedRequest)session.getAttribute("SPRING_SECURITY_SAVED_REQUEST_KEY");
			return savedRequest;
		}
	}

	protected void onPreAuthentication(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
			throws AuthenticationException, IOException
	{
	}

	protected void onSuccessfulAuthentication(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, Authentication authentication)
			throws IOException
	{
	}

	protected void onUnsuccessfulAuthentication(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse, AuthenticationException authenticationexception)
			throws IOException
	{
	}

	protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response)
	{
		String uri = request.getRequestURI();

		int pathParamIndex = uri.indexOf(';');
		if(pathParamIndex > 0)
			uri = uri.substring(0, pathParamIndex);
		if("".equals(request.getContextPath())){
			return uri.endsWith(filterProcessesUrl);
		}else{
			return uri.endsWith(request.getContextPath() + filterProcessesUrl);
		}
	}

	protected void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url)
			throws IOException
	{
		url = request.getContextPath()+url;
		response.sendRedirect(url);
		//RedirectUtils.sendRedirect(request, response, url, useRelativeContext);
	}

	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult)
			throws IOException, ServletException
	{
		if(super.logger.isDebugEnabled())
			super.logger.debug("Authentication success: " + authResult.toString());
		SecurityContextHolder.getContext().setAuthentication(authResult);
		if(super.logger.isDebugEnabled())
			super.logger.debug("Updated SecurityContextHolder to contain the following Authentication: '" + authResult + "'");
		if(invalidateSessionOnSuccessfulAuthentication)
			SessionUtils.startNewSessionIfRequired(request, migrateInvalidatedSessionAttributes, sessionRegistry);
		String targetUrl = determineTargetUrl(request);
		if(super.logger.isDebugEnabled())
			super.logger.debug("Redirecting to target URL from HTTP Session (or default): " + targetUrl);
		onSuccessfulAuthentication(request, response, authResult);
		rememberMeServices.loginSuccess(request, response, authResult);
		if(eventPublisher != null)
			eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, getClass()));

		boolean isAjax = isAjaxRequest(request);
		//create secure session
		try
		{
			System.out.println("AbstractProcessingFilter.successfulAuthentication()");
			
			UserDetail userDetail = (UserDetail)authResult.getPrincipal();
			targetUrl = defaultTargetUrl;
			//logger.info(">>>>>> successfulAuthentication targetUrl : " + targetUrl);

			if(loginPropertiesSetService!=null){
				//boolean flag = isAjax || isForm;
				loginPropertiesSetService.setProperties(request, response, userDetail,isAjax);
			}
			//logger.info(">>>>>> successfulAuthentication targetUrl22 : " + targetUrl);
		}
		catch(Exception ignored) {ignored.printStackTrace();}

		//logger.info(">>>>>> successfulAuthentication isAjax : " + isAjax);
		if(!isAjax){
		      //if(serverSideRedirect)
		      //  request.getRequestDispatcher(targetUrl).forward(request, response);
		      //else
		      sendRedirect(request, response, targetUrl);
		   }else {
		      response.setContentType("text/plain");
		      
		      
		      JsonObject jsonObject = new JsonObject();
		      jsonObject.addProperty("code", "0");
		      jsonObject.addProperty("description", "login success");
				
		      response.getOutputStream().write(jsonObject.toString().getBytes("UTF-8"));
		      response.flushBuffer();
		    }
	}

	protected String determineTargetUrl(HttpServletRequest request)
	{
		String targetUrl = alwaysUseDefaultTargetUrl ? null : targetUrlResolver.determineTargetUrl(getSavedRequest(request), request, SecurityContextHolder.getContext().getAuthentication());
		if(targetUrl == null)
			targetUrl = getDefaultTargetUrl();
		return targetUrl;
	}

	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)
			throws IOException, ServletException
	{
		SecurityContextHolder.getContext().setAuthentication(null);
		if(super.logger.isDebugEnabled())
			super.logger.debug("Updated SecurityContextHolder to contain null Authentication");


		System.out.println(">> unsuccessfulAuthentication");
		
		String failureUrl = determineFailureUrl(request, failed);
		//System.out.println("AbstractProcessingFilter.unsuccessfulAuthentication() :: " + failureUrl);
		System.out.println(">> failureUrl:"+failureUrl);

		if(failed instanceof CaptchaException){
			request.setAttribute(SessionConstants.LOGIN_ERROR_CAPTCHA, failed.getMessage());
		}else if(failed instanceof ConcurrentLoginException){
			request.setAttribute(SessionConstants.LOGIN_ERROR_CONCURRENT, failed.getMessage());
		}else{
			request.setAttribute(SessionConstants.LOGIN_ERROR, failed.getMessage());
		}

		if(super.logger.isDebugEnabled())
			super.logger.debug("Authentication request failed: " + failed.toString());
		try
		{
			HttpSession session = request.getSession(false);
			if(session != null || allowSessionCreation)
				request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", failed);
		}
		catch(Exception ignored) { }
		onUnsuccessfulAuthentication(request, response, failed);
		rememberMeServices.loginFail(request, response);
		
		if(failureUrl == null)
			response.sendError(401, "Authentication Failed:" + failed.getMessage());
		else{
			//System.out.println("AbstractProcessingFilter.serverSideRedirect() :: " + serverSideRedirect);
			//if(serverSideRedirect)
			//	request.getRequestDispatcher(failureUrl).forward(request, response);
			//else
			//	sendRedirect(request, response, failureUrl);
			String errCode = "";
			if(failed instanceof CaptchaException){
				errCode = SessionConstants.LOGIN_ERROR_CAPTCHA;
			}else if(failed instanceof ConcurrentLoginException){
				errCode = SessionConstants.LOGIN_ERROR_CONCURRENT;
			}else{
				errCode = SessionConstants.LOGIN_ERROR;
			}
			sendRedirect(request, response, failureUrl+"?v="+errCode);
				
		}
	}

	protected String determineFailureUrl(HttpServletRequest request, AuthenticationException failed)
	{
		return exceptionMappings.getProperty(failed.getClass().getName(), authenticationFailureUrl);
	}

	public String getAuthenticationFailureUrl()
	{
		return authenticationFailureUrl;
	}

	public void setAuthenticationFailureUrl(String authenticationFailureUrl)
	{
		this.authenticationFailureUrl = authenticationFailureUrl;
	}

	protected AuthenticationManager getAuthenticationManager()
	{
		return authenticationManager;
	}

	public void setAuthenticationManager(AuthenticationManager authenticationManager)
	{
		this.authenticationManager = authenticationManager;
	}

	public abstract String getDefaultFilterProcessesUrl();

	public String getDefaultTargetUrl()
	{
		return defaultTargetUrl;
	}

	public void setDefaultTargetUrl(String defaultTargetUrl)
	{
		Assert.isTrue(defaultTargetUrl.startsWith("/") | defaultTargetUrl.startsWith("http"), "defaultTarget must start with '/' or with 'http(s)'");
		this.defaultTargetUrl = defaultTargetUrl;
	}

	Properties getExceptionMappings()
	{
		return new Properties(exceptionMappings);
	}

	public void setExceptionMappings(Properties exceptionMappings)
	{
		this.exceptionMappings = exceptionMappings;
	}

	public String getFilterProcessesUrl()
	{
		return filterProcessesUrl;
	}

	public void setFilterProcessesUrl(String filterProcessesUrl)
	{
		this.filterProcessesUrl = filterProcessesUrl;
	}

	public RememberMeServices getRememberMeServices()
	{
		return rememberMeServices;
	}

	public void setRememberMeServices(RememberMeServices rememberMeServices)
	{
		this.rememberMeServices = rememberMeServices;
	}

	boolean isAlwaysUseDefaultTargetUrl()
	{
		return alwaysUseDefaultTargetUrl;
	}

	public void setAlwaysUseDefaultTargetUrl(boolean alwaysUseDefaultTargetUrl)
	{
		this.alwaysUseDefaultTargetUrl = alwaysUseDefaultTargetUrl;
	}

	public void setContinueChainBeforeSuccessfulAuthentication(boolean continueChainBeforeSuccessfulAuthentication)
	{
		this.continueChainBeforeSuccessfulAuthentication = continueChainBeforeSuccessfulAuthentication;
	}

	public void setApplicationEventPublisher(ApplicationEventPublisher eventPublisher)
	{
		this.eventPublisher = eventPublisher;
	}

	public void setAuthenticationDetailsSource(AuthenticationDetailsSource authenticationDetailsSource)
	{
		//Assert.notNull(authenticationDetailsSource, "AuthenticationDetailsSource required");
		this.authenticationDetailsSource = authenticationDetailsSource;
	}

	public void setMessageSource(MessageSource messageSource)
	{
		messages = new MessageSourceAccessor(messageSource);
	}

	public void setInvalidateSessionOnSuccessfulAuthentication(boolean invalidateSessionOnSuccessfulAuthentication)
	{
		this.invalidateSessionOnSuccessfulAuthentication = invalidateSessionOnSuccessfulAuthentication;
	}

	public void setMigrateInvalidatedSessionAttributes(boolean migrateInvalidatedSessionAttributes)
	{
		this.migrateInvalidatedSessionAttributes = migrateInvalidatedSessionAttributes;
	}

	public AuthenticationDetailsSource getAuthenticationDetailsSource()
	{
		return authenticationDetailsSource;
	}

	public void setUseRelativeContext(boolean useRelativeContext)
	{
		this.useRelativeContext = useRelativeContext;
	}

	protected boolean getAllowSessionCreation()
	{
		return allowSessionCreation;
	}

	public void setAllowSessionCreation(boolean allowSessionCreation)
	{
		this.allowSessionCreation = allowSessionCreation;
	}

	protected TargetUrlResolver getTargetUrlResolver()
	{
		return targetUrlResolver;
	}

	public void setTargetUrlResolver(TargetUrlResolver targetUrlResolver)
	{
		this.targetUrlResolver = targetUrlResolver;
	}

	public void setServerSideRedirect(boolean serverSideRedirect)
	{
		this.serverSideRedirect = serverSideRedirect;
	}

	public void setSessionRegistry(SessionRegistry sessionRegistry)
	{
		this.sessionRegistry = sessionRegistry;
	}

	public void setLoginPropertiesSetService(
			AfterLoginPropertiesSetService loginPropertiesSetService) {
		this.loginPropertiesSetService = loginPropertiesSetService;
	}


}