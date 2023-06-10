package com.aj.module.security.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.security.AuthenticationException;
import org.springframework.security.AuthenticationServiceException;
import org.springframework.security.BadCredentialsException;
import org.springframework.security.providers.AuthenticationProvider;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.providers.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.providers.dao.SaltSource;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.providers.encoding.PlaintextPasswordEncoder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.aj.module.security.dto.SecurityPrincipleDto;
import com.aj.module.security.exception.PlayerLoginException;
import com.aj.module.security.service.UserDetailsService;

/**
 * An {@link AuthenticationProvider} implementation that retrieves user details
 * from an {@link UserDetailsService}.
 *
 * @author Ben Alex
 * @version $Id: DaoAuthenticationProvider.java 2653 2008-02-18 20:18:40Z luke_t $
 */
public class DaoAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	//~ Instance fields ================================================================================================

	private PasswordEncoder passwordEncoder = new PlaintextPasswordEncoder();

	private SaltSource saltSource;

	private UserDetailsService userDetailsService;

	private boolean includeDetailsObject = true;

	//~ Methods ========================================================================================================

	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		Object salt = null;

		if (this.saltSource != null) {
			salt = this.saltSource.getSalt(userDetails);
		}

		if (authentication.getCredentials() == null) {
			throw new BadCredentialsException(messages.getMessage(
					"password.invalid", "Invalid password."),
					includeDetailsObject ? userDetails : null);
		}

		String presentedPassword = authentication.getCredentials().toString();
		if (!passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
			throw new BadCredentialsException(messages.getMessage(
					"password.invalid", "Invalid password."),
					includeDetailsObject ? userDetails : null);
		}

	}

	protected void doAfterPropertiesSet() throws Exception {
		//Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
	}

	protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
	throws AuthenticationException {
		UserDetails loadedUser;

		try {

			
			System.out.println("username1: "+username);
			if( authentication.getPrincipal() instanceof SecurityPrincipleDto){
				username = ((SecurityPrincipleDto)authentication.getPrincipal()).getUsername();
				System.out.println("username2: "+username);
			}

			/*if(authentication.getAuthorities().length>0){
				String auth = authentication.getAuthorities()[0].getAuthority();
				String prop = roleMapper.getRoleTypes().getProperty(auth);
				String methodName = prop.split(AuthenticationProcessingFilter.COMMON_SEP)[3];
				try{
					Method method = this.getUserDetailsService().getClass().getMethod(methodName, new Class[]{String.class});
					loadedUser = (UserDetails)method.invoke(this.getUserDetailsService(), new Object[]{username});
				}catch(Exception ex){
					ex.printStackTrace();
					loadedUser = null;
				}

			}else{
				loadedUser = this.getUserDetailsService().loadUserByUsername(username);
			}*/
			loadedUser = this.getUserDetailsService().loadUserByUsername(username);
			 
			System.out.println("DaoAuthenticationProvider.loadedUser: "+loadedUser);
			
		}
		catch (DataAccessException repositoryProblem) {
			System.out.println("repositoryProblem");
			throw new AuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}
		catch (UsernameNotFoundException unfe) {
			System.out.println("unfe");
			throw new AuthenticationServiceException(unfe.getMessage());
		}
		catch (PlayerLoginException ple) {
			System.out.println("ple");
			throw ple;
		}

		if (loadedUser == null) {
			throw new AuthenticationServiceException(
			"UserDetailsService returned null, which is an interface contract violation");
		}
		return loadedUser;
	}
	
	/**
	protected Authentication createSuccessAuthentication(Object principal, Authentication authentication,
			UserDetails user) {
		// Ensure we return the original credentials the user supplied,
		// so subsequent attempts are successful even with encoded passwords.
		// Also ensure we return the original getDetails(), so that future
		// authentication events after cache expiry contain the details
		
		System.out.println("1xxx------"+principal);
		User uuser = (User)user;
		if(uuser.getEntity()!=null){
			principal = uuser.getUsername()+":"+uuser.getEntity().getId();
		}
		System.out.println("2xxx------"+principal);
		
		UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(principal,
				authentication.getCredentials(), user.getAuthorities());
		result.setDetails(authentication.getDetails());

		return result;
	}**/

	/**
	 * Sets the PasswordEncoder instance to be used to encode and validate passwords.
	 * If not set, {@link PlaintextPasswordEncoder} will be used by default.
	 *
	 * @param passwordEncoder The passwordEncoder to use
	 */
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	protected PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	/**
	 * The source of salts to use when decoding passwords. <code>null</code>
	 * is a valid value, meaning the <code>DaoAuthenticationProvider</code>
	 * will present <code>null</code> to the relevant <code>PasswordEncoder</code>.
	 *
	 * @param saltSource to use when attempting to decode passwords via the <code>PasswordEncoder</code>
	 */
	public void setSaltSource(SaltSource saltSource) {
		this.saltSource = saltSource;
	}

	protected SaltSource getSaltSource() {
		return saltSource;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	protected UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	protected boolean isIncludeDetailsObject() {
		return includeDetailsObject;
	}

	/**
	 * Determines whether the UserDetails will be included in the <tt>extraInformation</tt> field of a
	 * thrown BadCredentialsException. Defaults to true, but can be set to false if the exception will be
	 * used with a remoting protocol, for example.
	 *
	 * @deprecated use {@link org.springframework.security.providers.ProviderManager#setClearExtraInformation(boolean)}
	 */
	public void setIncludeDetailsObject(boolean includeDetailsObject) {
		this.includeDetailsObject = includeDetailsObject;
	}

}
