package com.aj.casino2.player.dao.player;

import java.math.BigDecimal;

import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.aj.casino2.player.contant.PlayerWalletConstant;
import com.aj.db.domain.log.PlayerWalletLog;
import com.aj.db.domain.user.Player;
import com.aj.db.domain.user.PlayerWallet;

public class PlayerWalletHbmDao extends HibernateDaoSupport implements PlayerWalletHbmDaoInf {

	
	@Override
	public PlayerWallet createDefaultWalet(Player player)throws Exception{
		
		PlayerWallet playerWallet = new PlayerWallet();
		playerWallet.setCredit(BigDecimal.ZERO);
		playerWallet.setCreditType(PlayerWalletConstant.CREDIT_CASH_TYPE);
		playerWallet.setPlayer(player);
		
		this.getHibernateTemplate().save(playerWallet);
		
		return playerWallet;
		
	}
	
}
