package com.aj.db.domain.user;

import java.io.Serializable;
import java.util.Date;

public class PlayerLoginTrack implements Serializable {
	
	private Long id;
	private String ip;
	private Date loginDate;
	private Integer type;//login,logout
	private Date logoutDate;
	private Player player;
	private String loginUrl;
	private Integer loginType;//mobile ,browser
	
	
	public PlayerLoginTrack() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getLogoutDate() {
		return logoutDate;
	}
	public void setLogoutDate(Date logoutDate) {
		this.logoutDate = logoutDate;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public String getLoginUrl() {
		return loginUrl;
	}
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}
	public Integer getLoginType() {
		return loginType;
	}
	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}
	
	
	
	
	
	
	
}
