package com.aj.casino.baccarat.service;

import com.aj.casino.baccarat.dto.GameCard;
import com.aj.db.domain.baccarat.GameLog;
import com.aj.db.domain.baccarat.GameLogTotal;
import com.aj.db.domain.baccarat.Player;

public interface BaccaratService {

	public GameLog createGameLog(Long totalLogId, GameCard gameCard, Integer seq)throws Exception;
	public Object[] createTotalLog(Long playerId, Integer classType)throws Exception;
	public GameLogTotal closeTotalLog(Long totalLogId)throws Exception;
	public Player createMember(String name, String email)throws Exception;
}
