package com.aj.casino2.player.beforeLogin.account.service;

import com.aj.db.domain.user.Player;

public interface PlayerAccountService {
	
	public Player createPlayer(String domain,String username, String password, String email, String phone, String createIp)throws Exception;
	
}
