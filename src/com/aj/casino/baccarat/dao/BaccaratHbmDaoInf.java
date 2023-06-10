package com.aj.casino.baccarat.dao;

import java.util.List;

import com.aj.casino.baccarat.dto.GameCard;
import com.aj.db.domain.baccarat.GameLog;
import com.aj.db.domain.baccarat.GameLogTotal;
import com.aj.db.domain.baccarat.Player;

public interface BaccaratHbmDaoInf {

	public GameLog createGameLog(Long totalLogId, GameCard gameCard, Integer seq)throws Exception;
	public Object[] createTotalLog(Long playerId, Integer classType)throws Exception;
	public GameLogTotal closeTotalLog(Long totalLogId)throws Exception;
	public List<GameLog> gatGameLog(Long totalLogId, Integer max)throws Exception;
	public Player createMember(String name, String email)throws Exception;
}
