package com.aj.casino2.player.dao.antertainment;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.aj.db.domain.antertainment.AntertainmentCity;
import com.aj.db.domain.antertainment.AntertainmentGame;
import com.aj.db.domain.antertainment.AntertainmentGameCategory;
import com.aj.db.domain.antertainment.AntertainmentGameType;

public class AntertainmentHbmDao extends HibernateDaoSupport implements AntertainmentHbmDaoInf {

	@Override
	public AntertainmentCity getAntertainmentCityById(Long id)throws Exception{
		return this.getHibernateTemplate().get(AntertainmentCity.class, id);
	}
	@Override
	public AntertainmentGameCategory getAntertainmentGameCategoryById(Long id)throws Exception{
		return this.getHibernateTemplate().get(AntertainmentGameCategory.class, id);
	}
	@Override
	public AntertainmentGameType getAntertainmentGameTypeById(Long id)throws Exception{
		return this.getHibernateTemplate().get(AntertainmentGameType.class, id);
	}
	@Override
	public AntertainmentGame getAntertainmentGameById(Long id)throws Exception{
		return this.getHibernateTemplate().get(AntertainmentGame.class, id);
	}
	@Override
	public AntertainmentCity getAntertainmentCityByGameId(Long gameId)throws Exception{
		AntertainmentGame antertainmentGame = getAntertainmentGameById(gameId);
		return getAntertainmentCityById(antertainmentGame.getAntertainmentGameType().getAntertainmentGameCategory().getAntertainmentCity().getId());
	}
	
	
	@Override
	public List<AntertainmentCity> getAntertainmentCityList()throws Exception{
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(AntertainmentCity.class);
		c.addOrder(Order.asc("id"));
		return c.list();
	}
	@Override
	public List<AntertainmentGameCategory> getAntertainmentGameCategoryist(Long cityId)throws Exception{
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(AntertainmentGameCategory.class);
		c.add(Restrictions.eq("antertainmentCity.id", cityId));
		c.addOrder(Order.asc("id"));
		return c.list();
	}
	
	@Override
	public List<AntertainmentGameType> getAntertainmentGameTypeList(Long categoryId)throws Exception{
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(AntertainmentGameType.class);
		c.add(Restrictions.eq("antertainmentGameCategory.id", categoryId));
		c.addOrder(Order.asc("id"));
		return c.list();
	}
	@Override
	public List<AntertainmentGame> getAntertainmentGameList(Long gameTypeId)throws Exception{
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(AntertainmentGame.class);
		c.add(Restrictions.eq("antertainmentGameType.id", gameTypeId));
		c.addOrder(Order.asc("id"));
		return c.list();
	}
	
	
	
}

