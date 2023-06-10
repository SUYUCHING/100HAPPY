package com.aj.casino2.player.beforeLogin.account.service;

import com.aj.casino2.player.contant.EntityConstant;
import com.aj.casino2.player.dao.entity.EntityHbmDaoInf;
import com.aj.casino2.player.dao.player.PlayerHbmDaoInf;
import com.aj.casino2.player.dao.player.PlayerWalletHbmDaoInf;
import com.aj.db.domain.user.DomainSetting;
import com.aj.db.domain.user.Player;

public class PlayerAccountServiceImpl implements PlayerAccountService{

	private PlayerHbmDaoInf playerHbmDao;
	private PlayerWalletHbmDaoInf playerWalletHbmDao;
	private EntityHbmDaoInf entityHbmDao;
	
	public void setPlayerHbmDao(PlayerHbmDaoInf playerHbmDao) {
		this.playerHbmDao = playerHbmDao;
	}

	public void setPlayerWalletHbmDao(PlayerWalletHbmDaoInf playerWalletHbmDao) {
		this.playerWalletHbmDao = playerWalletHbmDao;
	}

	public void setEntityHbmDao(EntityHbmDaoInf entityHbmDao) {
		this.entityHbmDao = entityHbmDao;
	}

	@Override
	public Player createPlayer(String domain, String username, String password, String email, String phone,
			String createIp) throws Exception {
		
		try {
			
			DomainSetting domainSetting= entityHbmDao.getEntityDomainByDomain(domain, EntityConstant.DOMAIN_TYPE_PLAYER);
			
			if(domainSetting != null) {
				Player player = playerHbmDao.createPlayer(username, password, email, phone, createIp, domainSetting.getEntity().getId());
				
				 playerWalletHbmDao.createDefaultWalet(player);
				
				if(domainSetting.getAutoApproveReg().compareTo(EntityConstant.ENTITY_AUTO_APPROVE_REG) == 0) {
					playerHbmDao.approvePlayer(player);
				}
				
				
				return player;
				
			}
			
			
			throw new Exception("domainSetting not found >>> domain:"+domain);
			
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	
	
	
	
}
