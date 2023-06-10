package com.aj.db.domain.user;

import java.io.Serializable;

public class DomainSetting implements Serializable{

	private Long id;
	private String domain;
	private Integer autoApproveReg;//EntityConstant
	private Integer type;//web or player
	
	private Entity entity;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public Integer getAutoApproveReg() {
		return autoApproveReg;
	}
	public void setAutoApproveReg(Integer autoApproveReg) {
		this.autoApproveReg = autoApproveReg;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
