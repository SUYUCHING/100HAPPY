package com.aj.module.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.SpringSecurityMessageSource;
import org.springframework.security.concurrent.ConcurrentLoginException;
import org.springframework.security.concurrent.ConcurrentSessionController;
import org.springframework.security.concurrent.SessionInformation;
import org.springframework.security.concurrent.SessionRegistry;
import org.springframework.security.concurrent.SessionRegistryImpl;
import org.springframework.security.concurrent.SessionRegistryUtils;
import org.springframework.util.Assert;

/**
 * Base implementation of {@link ConcurrentSessionControllerImpl} which prohibits simultaneous logins.<p>By default
 * uses {@link SessionRegistryImpl}, although any <code>SessionRegistry</code> may be used.</p>
 *
 * @author Ben Alex
 * @version $Id: ConcurrentSessionControllerImpl.java 3150 2008-06-20 22:08:05Z luke_t $
 */
public class ConcurrentSessionControllerImpl implements ConcurrentSessionController, InitializingBean,
MessageSourceAware {
	//~ Instance fields ================================================================================================

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
	private SessionRegistry sessionRegistry;
	private boolean exceptionIfMaximumExceeded = false;
	private int maximumSessions = 1;

	//~ Methods ========================================================================================================


	public void afterPropertiesSet() throws Exception {
		System.out.println("ConcurrentSessionControllerImpl.afterPropertiesSet()");
		Assert.notNull(sessionRegistry, "SessionRegistry required");
		Assert.isTrue(maximumSessions != 0,
				"MaximumLogins must be either -1 to allow unlimited logins, or a positive integer to specify a maximum");
		Assert.notNull(this.messages, "A message source must be set");
	}

	/**
	 * Allows subclasses to customise behaviour when too many sessions are detected.
	 *
	 * @param sessionId the session ID of the present request
	 * @param sessions either <code>null</code> or all unexpired sessions associated with the principal
	 * @param allowableSessions DOCUMENT ME!
	 * @param registry an instance of the <code>SessionRegistry</code> for subclass use
	 *
	 * @throws ConcurrentLoginException DOCUMENT ME!
	 */
	protected void allowableSessionsExceeded(String sessionId, SessionInformation[] sessions, int allowableSessions,
			SessionRegistry registry) {
		System.out.println("ConcurrentSessionControllerImpl.allowableSessionsExceeded()");
		if (exceptionIfMaximumExceeded || (sessions == null)) {
			throw new ConcurrentLoginException("concurrent.exceededAllowed");
			//           throw new ConcurrentLoginException(messages.getMessage("concurrent.exceededAllowed",
			//                   "User login ID is in used. Please contact your administrator."));
		}

		// Determine least recently used session, and mark it for invalidation
		SessionInformation leastRecentlyUsed = null;

		System.out.println("ConcurrentSessionControllerImpl.allowableSessionsExceeded() sessions.length:"+sessions.length);
		for (int i = 0; i < sessions.length; i++) {
			if ((leastRecentlyUsed == null)
					|| sessions[i].getLastRequest().before(leastRecentlyUsed.getLastRequest())) {
				leastRecentlyUsed = sessions[i];
			}
		}

		leastRecentlyUsed.expireNow();
	}

	public void checkAuthenticationAllowed(Authentication request)
			throws AuthenticationException {
		Assert.notNull(request, "Authentication request cannot be null (violation of interface contract)");

		System.out.println("ConcurrentSessionControllerImpl.checkAuthenticationAllowed()");
		Object principal = SessionRegistryUtils.obtainPrincipalFromAuthentication(request);
		String sessionId = SessionRegistryUtils.obtainSessionIdFromAuthentication(request);

		SessionInformation[] sessions = sessionRegistry.getAllSessions(principal, false);

		int sessionCount = 0;

		if (sessions != null) {
			sessionCount = sessions.length;
		}

		if(sessionCount>0){ //means already have user login then kick the user
			for(int i=0;i<sessions.length;i++){
				SessionInformation info = sessions[i];
				info.expireNow();
			}
			sessionCount = 0;
		}

		int allowableSessions = getMaximumSessionsForThisUser(request);
		Assert.isTrue(allowableSessions != 0, "getMaximumSessionsForThisUser() must return either -1 to allow "
				+ "unlimited logins, or a positive integer to specify a maximum");

		if (sessionCount < allowableSessions) {
			System.out.println(">> sessionCount < allowableSessions");
			// They haven't got too many login sessions running at present
			return;
		} else if (allowableSessions == -1) {
			System.out.println(">> allowableSessions == -1");
			// We permit unlimited logins
			return;
		} else if (sessionCount == allowableSessions) {
			System.out.println(">> sessionCount == allowableSessions");
			// Only permit it though if this request is associated with one of the sessions
			for (int i = 0; i < sessionCount; i++) {
				if (sessions[i].getSessionId().equals(sessionId)) {
					return;
				}
			}
		}
		System.out.println(">> allowableSessionsExceeded A");
		allowableSessionsExceeded(sessionId, sessions, allowableSessions, sessionRegistry);
		System.out.println(">> allowableSessionsExceeded B");
	}

	/**
	 * Method intended for use by subclasses to override the maximum number of sessions that are permitted for
	 * a particular authentication. The default implementation simply returns the <code>maximumSessions</code> value
	 * for the bean.
	 *
	 * @param authentication to determine the maximum sessions for
	 *
	 * @return either -1 meaning unlimited, or a positive integer to limit (never zero)
	 */
	protected int getMaximumSessionsForThisUser(Authentication authentication) {
		return maximumSessions;
	}

	public void registerSuccessfulAuthentication(Authentication authentication) {
		Assert.notNull(authentication, "Authentication cannot be null (violation of interface contract)");

		Object principal = SessionRegistryUtils.obtainPrincipalFromAuthentication(authentication);
		String sessionId = SessionRegistryUtils.obtainSessionIdFromAuthentication(authentication);

		sessionRegistry.registerNewSession(sessionId, principal);
	}

	public void setExceptionIfMaximumExceeded(boolean exceptionIfMaximumExceeded) {
		this.exceptionIfMaximumExceeded = exceptionIfMaximumExceeded;
	}

	public void setMaximumSessions(int maximumSessions) {
		this.maximumSessions = maximumSessions;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messages = new MessageSourceAccessor(messageSource);
	}

	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

	public SessionRegistry getSessionRegistry() {
		return sessionRegistry;
	}
}
