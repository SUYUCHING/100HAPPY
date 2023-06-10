package com.aj.module.security.service;

import com.aj.db.domain.user.PlayerLoginTrack;

public interface PlayerLogoutService {

	/*start Tom*/
	public PlayerLoginTrack updatePlyaerIp(Long playerId,Long entityId, String ip, String reqUrl)throws Exception;
	
	/*end Tom*/
}
