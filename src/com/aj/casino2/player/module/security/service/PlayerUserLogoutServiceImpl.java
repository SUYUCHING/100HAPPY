package com.aj.casino2.player.module.security.service;

import com.aj.casino2.player.dao.player.PlayerHbmDaoInf;
import com.aj.db.domain.user.PlayerLoginTrack;
import com.aj.module.security.service.PlayerLogoutService;

public class PlayerUserLogoutServiceImpl implements PlayerLogoutService{

	/* start Tom */
	private PlayerHbmDaoInf playerHbmDao;
	
	public void setPlayerHbmDao(PlayerHbmDaoInf playerHbmDao) {
		this.playerHbmDao = playerHbmDao;
	}




	@Override
	public PlayerLoginTrack updatePlyaerIp(Long playerId,Long entityId, String ip, String reqUrl) throws Exception {
		try {
			PlayerLoginTrack track =  playerHbmDao.updatePlayerIp(playerId,entityId, ip, reqUrl);
			
			
			return track;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
	/* End Tom */
}
