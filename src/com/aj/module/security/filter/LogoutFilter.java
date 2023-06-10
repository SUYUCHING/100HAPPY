package com.aj.module.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.ui.FilterChainOrder;
import org.springframework.security.ui.SpringSecurityFilter;
import org.springframework.security.ui.logout.LogoutHandler;
import org.springframework.security.ui.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.util.RedirectUtils;
import org.springframework.security.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.aj.module.security.SecureSession;
import com.aj.module.security.constant.SessionConstants;
import com.aj.module.security.service.PlayerLogoutService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * 
 */
public class LogoutFilter extends SpringSecurityFilter
{
	private String filterProcessesUrl;
    private String logoutSuccessUrl;
    private LogoutHandler handlers[];
    private boolean useRelativeContext;
    private PlayerLogoutService playerLogoutService;

    public LogoutFilter(String logoutSuccessUrl, LogoutHandler handlers[])
    {
        filterProcessesUrl = "/j_spring_security_logout";
       // Assert.notEmpty(handlers, "LogoutHandlers are required");
        this.logoutSuccessUrl = logoutSuccessUrl;
        Assert.isTrue(UrlUtils.isValidRedirectUrl(logoutSuccessUrl), logoutSuccessUrl + " isn't a valid redirect URL");
        this.handlers = handlers;
    }
    
    private boolean isAjaxRequest(HttpServletRequest request) {  
		String url = request.getRequestURL().toString();
		if(url.indexOf("/func")>0){
			return true;
		}else{
			return false;
		}
	} 

	private Gson gson = new Gson();

    public void doFilterHttp(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        boolean flag2 = requiresLogout(request, response);
    	if(flag2)
        {
        	SecureSession sessObj = (SecureSession)request.getSession().getAttribute(SessionConstants.REQ_SESSION_OBJ);
        	String accType = null;
        	Long userId = null;
        	Long entityId = null;
        	if(sessObj!=null){
        		accType = (String)sessObj.getParameter(SessionConstants.REQ_PROP_ACC_TYPE);
        		userId=(Long)sessObj.getParameter(SessionConstants.REQ_PROP_USER_ID);
        		entityId=(Long)sessObj.getParameter(SessionConstants.REQ_PROP_ENTITY_ID);
        	}
        	
            //destroy from session manager
			if (playerLogoutService != null) {
				try {
					if (userId != null)
						playerLogoutService.updatePlyaerIp(userId,entityId, null, request.getRequestURL().toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
            
            org.springframework.security.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(super.logger.isDebugEnabled())
                super.logger.debug("Logging out user '" + auth + "' and redirecting to logout page");
            for(int i = 0; i < handlers.length; i++)
                handlers[i].logout(request, response, auth);

            String targetUrl = logoutSuccessUrl;
            /*if(accType==null){
            	//default
            }else{
            	if(accType.equals(CmoConstant.ROLE_PLAYER_ID.toString())){
            		//default
               }else if(accType.equals(CmoConstant.ROLE_ADMIN_ID.toString())){
               	 targetUrl = "/adminLogout.jsp";
               }else{
            	 //default
               }
            }*/
            
            boolean flag = isAjaxRequest(request);
            if(flag){
            	HttpSession session = request.getSession(false);
            	if (session != null) {
            	    session.invalidate();
            	}
            	Cookie terminate = new Cookie(TokenBasedRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, null);
            	String contextPath = request.getContextPath();
            	terminate.setPath(contextPath != null && contextPath.length() > 0 ? contextPath : "/");
            	terminate.setMaxAge(0);

            	JsonObject jsonObject = new JsonObject();
   		        jsonObject.addProperty("code", "0");
   		        jsonObject.addProperty("description", "logout success");
            	
            	response.addCookie(terminate);
            	response.setContentType("text/plain");
				response.getOutputStream().write(jsonObject.toString().getBytes("UTF-8"));
				response.flushBuffer();
				//printline
            }
            else{
            	//request.getRequestDispatcher(targetUrl).forward(request, response);
            	sendRedirect(request, response, targetUrl);
            }
            
            return;
        } else
        {
            chain.doFilter(request, response);
            return;
        }
    }

    protected boolean requiresLogout(HttpServletRequest request, HttpServletResponse response)
    {
        String uri = request.getRequestURI();
        int pathParamIndex = uri.indexOf(';');
        if(pathParamIndex > 0)
            uri = uri.substring(0, pathParamIndex);
        int queryParamIndex = uri.indexOf('?');
        if(queryParamIndex > 0)
            uri = uri.substring(0, queryParamIndex);
        if("".equals(request.getContextPath()))
            return uri.endsWith(filterProcessesUrl);
        else
            return uri.endsWith(request.getContextPath() + filterProcessesUrl);
    }

    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response)
    {
        String targetUrl = request.getParameter("logoutSuccessUrl");
        if(!StringUtils.hasLength(targetUrl))
            targetUrl = getLogoutSuccessUrl();
        if(!StringUtils.hasLength(targetUrl))
            targetUrl = request.getHeader("Referer");
        if(!StringUtils.hasLength(targetUrl))
            targetUrl = "/";
        return targetUrl;
    }

    protected void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url)
        throws IOException
    {
        RedirectUtils.sendRedirect(request, response, url, useRelativeContext);
    }

    public void setFilterProcessesUrl(String filterProcessesUrl)
    {
        Assert.hasText(filterProcessesUrl, "FilterProcessesUrl required");
        Assert.isTrue(UrlUtils.isValidRedirectUrl(filterProcessesUrl), filterProcessesUrl + " isn't a valid redirect URL");
        this.filterProcessesUrl = filterProcessesUrl;
    }
    private String getRequestIp(HttpServletRequest req) {
		String ipAddress = req.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = req.getRemoteAddr();
		}

		String ipAddr = null;
		if (ipAddress.indexOf(",") >= 0) {
			String[] ipaddrArr = ipAddress.split(",");
			ipAddr = ipaddrArr[0];
		} else {
			ipAddr = ipAddress;
		}

		return ipAddr;
	}
    
    public void setPlayerLogoutService(PlayerLogoutService playerLogoutService) {
		this.playerLogoutService = playerLogoutService;
	}

	protected String getLogoutSuccessUrl()
    {
        return logoutSuccessUrl;
    }

    protected String getFilterProcessesUrl()
    {
        return filterProcessesUrl;
    }

    public void setUseRelativeContext(boolean useRelativeContext)
    {
        this.useRelativeContext = useRelativeContext;
    }

	public int getOrder()
    {
        return FilterChainOrder.LOGOUT_FILTER;
    }


    
}
