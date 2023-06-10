package com.aj.module.security.dto;

import org.springframework.security.GrantedAuthority;

import com.aj.db.domain.user.Entity;

public class UserDetail  implements LoginRole {
	
	private String organization;
	private GrantedAuthority[] authorities;
	private String param1;
	private String param2;
	private String param3;
//	private String domain;
	private LoginInfo loginInfo;
	private String loginUsername;
	private String entityIds;
	private String username;
	private Long id;
	private Entity entity;
	private String password;
	private String extPlayerId;
	
	
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getAccountType() {
		return getOrganization();
	}
	
	public GrantedAuthority[] getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(GrantedAuthority[] authorities) {
		this.authorities = authorities;
	}

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		return true;
	}

	public String getParam1() {
		return param1;
	}
	
	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}
	
	@Override
	public Long getEntityId() {
		System.out.println("User Detail Get Id : " + getEntity().getId());
		if(getEntity()!=null){
			return getEntity().getId();
		}
		return null;
	}
	
	
	public void setEntityIds(String entityIds) {
		this.entityIds = entityIds;
	}

	@Override
	public String getEntityIds() {
		return entityIds;
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	public String getLoginUsername() {
		return loginUsername;
	}

	public void setLoginUsername(String loginUsername) {
		this.loginUsername = loginUsername;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getExtPlayerId() {
		return extPlayerId;
	}

	public void setExtPlayerId(String extPlayerId) {
		this.extPlayerId = extPlayerId;
	}

	
	
	
}