package com.aj.casino2.player.dao.player;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.aj.casino2.common.constant.ErrorCodeConstant;
import com.aj.casino2.player.contant.PlayerConstant;
import com.aj.db.domain.user.Entity;
import com.aj.db.domain.user.Player;
import com.aj.db.domain.user.PlayerLoginTrack;
import com.aj.module.exception.ApiErrorCodeException;
import com.aj.util.SecurityUtil;

public class PlayerHbmDao extends HibernateDaoSupport implements PlayerHbmDaoInf {

	
	@Override
	public Player createPlayer(String username,String password, String email, String phone, String createIp, Long entityId)throws Exception{
		
		//check username
		Player player =getPlayerByUsername(username, entityId);
		
		if(player != null) {
			throw new ApiErrorCodeException(ErrorCodeConstant.CODE_DUPLICATE_USERNAME_ERROR,
					ErrorCodeConstant.DESC_DUPLICATE_USERNAME_ERROR);
		}
		
		//check phone number
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Player.class);
		c.add(Restrictions.eq("phone", phone));
		player = (Player) c.uniqueResult();
		
		if(player != null) {
			throw new ApiErrorCodeException(ErrorCodeConstant.CODE_DUPLICATE_PHONE_ERROR,
					ErrorCodeConstant.DESC_DUPLICATE_PHONE_ERROR);
		}
		
		//check email
		c = getSessionFactory().getCurrentSession().createCriteria(Player.class);
		c.add(Restrictions.eq("email", email));
		player = (Player) c.uniqueResult();
		
		if(player != null) {
			throw new ApiErrorCodeException(ErrorCodeConstant.CODE_DUPLICATE_PHONE_ERROR,
					ErrorCodeConstant.DESC_DUPLICATE_PHONE_ERROR);
		}
		
		String encPassword = SecurityUtil.encodePassword(username, password);

		Entity entity = getHibernateTemplate().get(Entity.class, entityId);
		
		//create
		player = new Player();
		player.setEmail(email);
		player.setName(username);
		player.setPhone(phone);
		player.setPassword(encPassword);
		player.setCreateDate(new Date());
		player.setCreateIp(createIp);
		player.setEntity(entity);
		player.setStatus(PlayerConstant.PLAYER_STATUS_PENDING);

		this.getHibernateTemplate().save(player);
		
		
		player.setRefPlayerId(entity.getCode()+"_"+player.getId());
		
		this.getHibernateTemplate().update(player);
		
		return player;
	}
	
	@Override
	public Player approvePlayer(Player player)throws Exception{
		
		player.setStatus(PlayerConstant.PLAYER_STATUS_SUCCESS);
		this.getHibernateTemplate().update(player);
		
		return player;
		
		
	}

	@Override
	public PlayerLoginTrack updatePlayerIp(Long id, Long entityId, String ip, String reqUrl) throws Exception {
		
        Player player=getPlayerById(id, entityId);
		
		Integer trackType = null;
		
		if (ip != null) {
			//login
			player.setLoginIp(ip);
			player.setLoginDate(new Date());
			player.setOnline(PlayerConstant.PLAYER_LOGIN_TYPE);
			trackType = PlayerConstant.PLAYER_LOGIN_TYPE;
		} else {
			//logout
			player.setLastLoginIp(player.getLoginIp());
			player.setLastLoginDate(player.getLoginDate());
			player.setOnline(PlayerConstant.PLAYER_LOGOUT_TYPE);
			player.setLoginIp(null);
			player.setLoginDate(null);
			trackType = PlayerConstant.PLAYER_LOGOUT_TYPE;
		}
		this.getHibernateTemplate().update(player);
		
		
		PlayerLoginTrack playerLoginTrack = new PlayerLoginTrack();
		playerLoginTrack.setIp(ip);
		playerLoginTrack.setLoginDate(new Date());
		playerLoginTrack.setLoginUrl(reqUrl);
		playerLoginTrack.setPlayer(player);
		playerLoginTrack.setLoginType(null);//TODO
		playerLoginTrack.setType(trackType);
		
		getHibernateTemplate().save(playerLoginTrack);
		
		return playerLoginTrack;
	}
	
	@Override
	public Player getPlayerByUsername( String username, Long entityId)throws Exception{
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Player.class);
		c.add(Restrictions.eq("name", username));
		c.add(Restrictions.eq("entity.id", entityId));
		return (Player) c.uniqueResult();
	}
	
	
	@Override
	public Player getPlayerById(Long id, Long entityId)throws Exception{
		Criteria c = getSessionFactory().getCurrentSession().createCriteria(Player.class);
		c.add(Restrictions.eq("id", id));
		c.add(Restrictions.eq("entity.id", entityId));
		return (Player) c.uniqueResult();
	}
	
	
}
