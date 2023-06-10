package com.aj.db.domain.user;

import java.io.Serializable;
import java.util.Date;

public class Player implements Serializable{

	
	private Long id;
	private String name;
	private String password;
	private String email;
	private String phone;
	private Integer status;
	
	private Date createDate;
	private String createIp;
	
	private Date loginDate;
	private String loginIp;
	
	private Date lastLoginDate;
	private String lastLoginIp;
	private Integer online;
	private Entity entity;
	
	private String refPlayerId;
	
	
	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getCreateIp() {
		return createIp;
	}
	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public Integer getOnline() {
		return online;
	}
	public void setOnline(Integer online) {
		this.online = online;
	}
	public String getRefPlayerId() {
		return refPlayerId;
	}
	public void setRefPlayerId(String refPlayerId) {
		this.refPlayerId = refPlayerId;
	}
	
	
	
}
