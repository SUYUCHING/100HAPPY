package com.aj.db.domain.user;

import java.io.Serializable;
import java.math.BigDecimal;

public class EntityWallet implements Serializable {

	private Long id;
	private BigDecimal credit;
	private Integer type;
	private Entity entity;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getCredit() {
		return credit;
	}
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	
	
}
