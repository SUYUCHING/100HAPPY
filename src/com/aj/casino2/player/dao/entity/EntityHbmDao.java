package com.aj.casino2.player.dao.entity;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.aj.db.domain.user.DomainSetting;
import com.aj.db.domain.user.Entity;

public class EntityHbmDao extends HibernateDaoSupport implements EntityHbmDaoInf {

	@Override
	public Entity getEntityById(Long entityId)throws Exception{
		
		return this.getHibernateTemplate().get(Entity.class, entityId);
		
	}
	
	@Override
	public DomainSetting getEntityDomainByDomain(String domain, Integer type)throws Exception{
		
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(DomainSetting.class);
		c.add(Restrictions.eq("domain", domain));
		c.add(Restrictions.eq("type", type));
		
		return (DomainSetting) c.uniqueResult();
		
		
	}
	
	@Override
	public Entity getEntityByDomain(String domain)throws Exception{
		
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(DomainSetting.class);
		c.add(Restrictions.eq("domain", domain));
		
		DomainSetting domainSetting = (DomainSetting) c.uniqueResult();
		
		return this.getHibernateTemplate().get(Entity.class,domainSetting.getEntity().getId()) ;
		
	}
	
}
