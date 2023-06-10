package com.aj.casino.baccarat.service;

import com.aj.casino.baccarat.dao.BaccaratHbmDaoInf;
import com.aj.casino.baccarat.dto.GameCard;
import com.aj.db.domain.baccarat.GameLog;
import com.aj.db.domain.baccarat.GameLogTotal;
import com.aj.db.domain.baccarat.Player;

public class BaccartaServiceImpl implements BaccaratService {

	private BaccaratHbmDaoInf baccaratHbmDao;
	
	
	public void setBaccaratHbmDao(BaccaratHbmDaoInf baccaratHbmDao) {
		this.baccaratHbmDao = baccaratHbmDao;
	}

	@Override
	public GameLog createGameLog(Long totalLogId, GameCard gameCard, Integer seq) throws Exception {
	
		try {
			return baccaratHbmDao.createGameLog(totalLogId, gameCard, seq);
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public Object[] createTotalLog(Long playerId, Integer classType) throws Exception {
		try {
			return baccaratHbmDao.createTotalLog(playerId,classType);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public GameLogTotal closeTotalLog(Long totalLogId) throws Exception {
		try {
			return baccaratHbmDao.closeTotalLog(totalLogId);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Player createMember(String name, String email)throws Exception{
		try {
			return baccaratHbmDao.createMember(name, email);
		} catch (Exception e) {
			throw e;
		}
	}
}
