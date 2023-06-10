package com.aj.casino2.player.dao.entity;

import com.aj.db.domain.user.DomainSetting;
import com.aj.db.domain.user.Entity;

public interface EntityHbmDaoInf {
	
	public Entity getEntityById(Long entityId)throws Exception;
	
	public DomainSetting getEntityDomainByDomain(String domain, Integer type)throws Exception;
	
	public Entity getEntityByDomain(String domain)throws Exception;

}
