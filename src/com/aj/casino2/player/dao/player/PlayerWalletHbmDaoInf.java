package com.aj.casino2.player.dao.player;

import com.aj.db.domain.user.Player;
import com.aj.db.domain.user.PlayerWallet;

public interface PlayerWalletHbmDaoInf {

	public PlayerWallet createDefaultWalet(Player player)throws Exception;
	
	
}
