package com.aj.casino2.player.dao.log;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.aj.db.domain.antertainment.AntertainmentGame;
import com.aj.db.domain.log.PlayerGameLog;
import com.aj.db.domain.user.Player;

public class PlayerGameLogHbmDao extends HibernateDaoSupport implements PlayerGameLogHbmDaoInf {

	
	@Override
	public List<PlayerGameLog> getPlayerGameLog(Long playerId, Integer maxRes)throws Exception{
		
		StringBuffer strBuff = new StringBuffer();
		strBuff.append(" select playerGameLog ");
		strBuff.append(" from PlayerGameLog playerGameLog ");
		strBuff.append(" where playerGameLog.player.id = :obj1 ");
		
		Query query = getSessionFactory().getCurrentSession().createQuery(strBuff.toString());
		query.setParameter("obj1", playerId);
		if(maxRes != null)
		   query.setMaxResults(maxRes);
		
		
		return query.list();
		
	}
	
	@Override
	public PlayerGameLog creatPlayerGameLog(Player player, BigDecimal betAmt, Long gameId)throws Exception{
		
		PlayerGameLog playerGameLog = new PlayerGameLog();
		playerGameLog.setCreateDate(new Date());
		playerGameLog.setPlayer(player);
		playerGameLog.setBetAmt(betAmt);
		playerGameLog.setAntertainmentGame(new AntertainmentGame(gameId));
		
		this.getHibernateTemplate().save(playerGameLog);
		
		return playerGameLog;
		
		
	}
	
	@Override
	public PlayerGameLog udpatePlayerGameLog(Long logId,BigDecimal winLose)throws Exception{
		
		PlayerGameLog playerGameLog = this.getHibernateTemplate().get(PlayerGameLog.class, logId);
		playerGameLog.setWinLose(winLose);
		
		this.getHibernateTemplate().update(playerGameLog);;
		
		return playerGameLog;
	}
	
	
}
