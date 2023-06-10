package com.aj.module.security.dto;

import java.io.Serializable;
import java.security.Principal;

public class SecurityPrincipleDto implements Principal, Serializable{

	private final String username;
	private final Long entityId;
	
	public SecurityPrincipleDto(String username,Long entityId){
		this.username = username;
		this.entityId = entityId;
	}

	public String getName() {
		return getId();
	}

	public Long getEntityId() {
		return entityId;
	}
	
	public String getUsername(){
		return username;
	}

	public String getId(){
		return username+(entityId==null?"":":"+entityId);
//		return username;
	}
}
