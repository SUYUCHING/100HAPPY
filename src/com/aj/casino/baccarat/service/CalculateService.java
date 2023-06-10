package com.aj.casino.baccarat.service;

import com.aj.db.domain.baccarat.GameLog;

public interface CalculateService {

	public boolean calculateBaccarat(GameLog gameLog)throws Exception;
}
