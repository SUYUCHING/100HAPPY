package com.aj.casino2.player.dao.log;

import java.math.BigDecimal;
import java.util.List;

import com.aj.db.domain.log.PlayerGameLog;
import com.aj.db.domain.user.Player;

public interface PlayerGameLogHbmDaoInf {

	public List<PlayerGameLog> getPlayerGameLog(Long playerId, Integer maxRes)throws Exception;
	public PlayerGameLog creatPlayerGameLog(Player player, BigDecimal betAmt, Long gameId)throws Exception;
	public PlayerGameLog udpatePlayerGameLog(Long logId,BigDecimal winLose)throws Exception;
	
	
	
}
