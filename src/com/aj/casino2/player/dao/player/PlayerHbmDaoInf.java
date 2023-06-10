package com.aj.casino2.player.dao.player;

import com.aj.db.domain.user.Player;
import com.aj.db.domain.user.PlayerLoginTrack;

public interface PlayerHbmDaoInf {

	public PlayerLoginTrack updatePlayerIp(Long id ,Long entityId, String ip, String reqUrl)throws Exception;
	
	public Player createPlayer(String username,String password, String email, String phone, String createIp, Long entityId)throws Exception;
	
	public Player approvePlayer(Player player)throws Exception;
	
	public Player getPlayerByUsername( String username, Long entityId)throws Exception;
	
	public Player getPlayerById(Long id, Long entityId)throws Exception;
	
	
}
