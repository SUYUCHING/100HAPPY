package com.aj.module.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aj.casino2.player.contant.EntityConstant;
import com.aj.casino2.player.dao.entity.EntityHbmDaoInf;
import com.aj.casino2.player.dao.player.PlayerHbmDaoInf;
import com.aj.db.domain.user.DomainSetting;
import com.aj.db.domain.user.PlayerLoginTrack;
import com.aj.module.security.constant.SessionConstants;
import com.aj.module.security.dto.LoginRole;
import com.aj.module.security.filter.AfterLoginPropertiesSetService;

public class AfterLoginServiceImpl implements AfterLoginPropertiesSetService{
	private PlayerHbmDaoInf playerHbmDao;
	private EntityHbmDaoInf entityHbmDao;


	public void setPlayerHbmDao(PlayerHbmDaoInf playerHbmDao) {
		this.playerHbmDao = playerHbmDao;
	}

	public void setEntityHbmDao(EntityHbmDaoInf entityHbmDao) {
		this.entityHbmDao = entityHbmDao;
	}
	public void setProperties(HttpServletRequest req,HttpServletResponse response, LoginRole loginRole,boolean isAjax) {
		try {
			SecureSession secureSession = createSecureSession(loginRole);
			req.getSession().setAttribute(SessionConstants.REQ_SESSION_OBJ, secureSession);


			if (loginRole.getId() != null) {
				Long userId = new Long(loginRole.getId());
				Long entityId = new Long(loginRole.getEntityId());
				DomainSetting domainSetting = getDomainSetting(req);

				if (domainSetting.getType().compareTo(EntityConstant.DOMAIN_TYPE_PLAYER) == 0) {
					playerHbmDao.updatePlayerIp(userId, entityId, getRequestIp(req), req.getRequestURL().toString());
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected SecureSession createSecureSession(LoginRole loginRole){
		
		System.out.println("AfterLoginServiceImpl.createSecureSession() user Id: "+loginRole.getId());
		System.out.println("AfterLoginServiceImpl.createSecureSession() entity Id: "+loginRole.getEntityId());
		System.out.println("AfterLoginServiceImpl.createSecureSession() ext Player Id: "+loginRole.getExtPlayerId());
		
		SecureSession secureSession = new SecureSession();
		secureSession.setParameter(SessionConstants.REQ_PROP_USER_ID, loginRole.getId());
		secureSession.setParameter(SessionConstants.REQ_PROP_ENTITY_ID, loginRole.getEntityId());
		secureSession.setParameter(SessionConstants.REQ_PROP_USER_NAME, loginRole.getUsername());
		secureSession.setParameter(SessionConstants.REQ_PROP_EXT_PLAYER_ID, loginRole.getExtPlayerId());
//		secureSession.setParameter(SessionConstants.REQ_PROP_ACC_TYPE, loginRole.getAccountType()); //roleid

		
		return secureSession;
	}

	private String getRequestIp(HttpServletRequest req) {
		String ipAddress = req.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = req.getRemoteAddr();
		}

		String ipAddr = null;
		if (ipAddress.indexOf(",") >= 0) {
			String[] ipaddrArr = ipAddress.split(",");
			ipAddr = ipaddrArr[0];
		} else {
			ipAddr = ipAddress;
		}

		return ipAddr;
	}

	private DomainSetting getDomainSetting(HttpServletRequest req)throws Exception{

		String requestURL = req.getServerName().toString();//www.abc.com
		return entityHbmDao.getEntityDomainByDomain(requestURL, EntityConstant.DOMAIN_TYPE_PLAYER);


	}
}
