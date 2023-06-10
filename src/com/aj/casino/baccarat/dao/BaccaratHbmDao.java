package com.aj.casino.baccarat.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.aj.casino.baccarat.dto.GameCard;
import com.aj.casino.constant.BaccaratContant;
import com.aj.casino.constant.ClassConstant;
import com.aj.casino.constant.LogConstant;
import com.aj.db.domain.baccarat.Card;
import com.aj.db.domain.baccarat.GameLog;
import com.aj.db.domain.baccarat.GameLogTotal;
import com.aj.db.domain.baccarat.Player;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class BaccaratHbmDao extends HibernateDaoSupport implements BaccaratHbmDaoInf {

	@Override
	public GameLog createGameLog(Long totalLogId, GameCard gameCard, Integer seq) throws Exception {
		GameLogTotal gameLogTotal = getHibernateTemplate().get(GameLogTotal.class, totalLogId);
		
		Map<Integer, String> winnerMap = gameCard.getWinTypeStr();
		GameLog gameLog = new GameLog();
		
		for(Integer winner:winnerMap.keySet()) {
			if(winner.compareTo(BaccaratContant.WINNER_TYPE_BANCO)==0) {
				gameLog.setBancoTypeString(winnerMap.get(winner));
			}else if(winner.compareTo(BaccaratContant.WINNER_TYPE_PLAYER)==0) {
				gameLog.setPlayerTypeString(winnerMap.get(winner));
			}else if(winner.compareTo(BaccaratContant.WINNER_TYPE_TIE)==0) {
				gameLog.setTieType(Integer.parseInt(winnerMap.get(winner)));
			}
			gameLog.setWinnerType(winner);
		}
		Gson gson = new Gson();
		String json = gson.toJson(gameCard);
		gameLog.setCardString(json);
		gameLog.setSeq(seq);
		gameLog.setGameLogTotal(gameLogTotal);
		
		getHibernateTemplate().save(gameLog);
		
		Card card = updateTotalLog(gameLog,gameCard);
		
		gameLog.setLastCard(gson.toJson(card));
		
		getHibernateTemplate().update(gameLog);
		
		return gameLog;
		
//		return gameLog;
	}

	public Card updateTotalLog(GameLog gameLog,GameCard gameCard) throws Exception{
		Long totalLogId=gameLog.getGameLogTotal().getId();
		
		StringBuffer strBuff = new StringBuffer();
		strBuff.append(" select log");
		strBuff.append(" from GameLog log  ");
		strBuff.append(" where log.gameLogTotal.id=:obj1  ");
		
		Query query = getSessionFactory().getCurrentSession().createQuery(strBuff.toString());
		query.setParameter("obj1", totalLogId);
		List<GameLog> list = query.list();
		Map<Integer,Integer> bacnoMap=new HashMap<Integer,Integer>();
		Map<Integer,Integer> playerMap=new HashMap<Integer,Integer>();
		Map<Integer,Integer> tieMap=new HashMap<Integer,Integer>();
		Map<Integer,Integer> winMap=new HashMap<Integer,Integer>();
		GameLogTotal gameLogTotal=null;
		
		
		for(GameLog log:list) {
			gameLogTotal=log.getGameLogTotal();
			String bancoTypeStr = log.getBancoTypeString();
			String playerTypeStr = log.getPlayerTypeString();
			Integer tieType = log.getTieType();
			Integer winner=log.getWinnerType();
			String[] bancoArr= {};
			String[] playerArr= {};
			if(bancoTypeStr!=null)
			 bancoArr=bancoTypeStr.split(",");
			
			for(String typeStr:bancoArr) {
				Integer type=Integer.parseInt(typeStr);
				if(BaccaratContant.BANCO_TYPE_MAP.containsKey(type)) {
					Integer cType = bacnoMap.get(type);
					if(cType==null)
						cType=0;
					bacnoMap.put(type, cType+1);
				}
				
			}
			
			if(playerTypeStr!=null)
			playerArr=playerTypeStr.split(",");
			
			for(String typeStr:playerArr) {
				Integer type=Integer.parseInt(typeStr);
				if(BaccaratContant.PLAYER_TYPE_MAP.containsKey(type)) {
					Integer cType = playerMap.get(type);
					if(cType==null)
						cType=0;
					playerMap.put(type, cType+1);
				}
				
			}
			
			if (tieType != null) {
				Integer type = tieMap.get(tieType);
				if (type == null)
					type = 0;
				tieMap.put(tieType, type + 1);
			}
			
			if(winner !=null) {
				Integer type = winMap.get(winner);
				if (type == null)
					type = 0;
				winMap.put(winner, type + 1);
			}
		}
		
		for(Integer key: bacnoMap.keySet()) {
			if(key.compareTo(BaccaratContant.BANCO_TYPE_TWO_SUP_SIX)==0) {
				gameLogTotal.setTotalSuperSix(bacnoMap.get(key));
			}else if(key.compareTo(BaccaratContant.BANCO_TYPE_TWO_BANCO)==0) {
				gameLogTotal.setTotalTwoBanco(bacnoMap.get(key));
			}else if(key.compareTo(BaccaratContant.BANCO_TYPE_TREASURE)==0) {
				gameLogTotal.setTotalBancoTreasure(bacnoMap.get(key));
			}
			gameLogTotal.setTotalBanco(winMap.get(BaccaratContant.WINNER_TYPE_BANCO));
		}
		
		for(Integer key: playerMap.keySet()) {
			if(key.compareTo(BaccaratContant.PLAYER_TYPE_TREASURE)==0) {
				gameLogTotal.setTotalPlayerTreasure(playerMap.get(key));
			}else if(key.compareTo(BaccaratContant.PLAYER_TYPE_TWO_PLAYER)==0) {
				gameLogTotal.setTotalTwoPlayer(playerMap.get(key));
			}
			gameLogTotal.setTotalPlayer(winMap.get(BaccaratContant.WINNER_TYPE_PLAYER));
		}
		if(tieMap != null) {
			gameLogTotal.setTotalTie(tieMap.size());
		}
		
		getHibernateTemplate().update(gameLogTotal);
		
		return actionCard(gameLog,gameCard);
//		return gameLogTotal;
	}
	@Override
	public Object[] createTotalLog(Long playerId, Integer classType) throws Exception {
		
		StringBuffer strBuff = new StringBuffer();
		strBuff.append(" update GameLogTotal log set log.status=:obj1 ");
		strBuff.append(" where log.classType=:obj2  "); //TODO but no userId
		strBuff.append(" and log.status=:obj3  ");
		
		Query query = getSessionFactory().getCurrentSession().createQuery(strBuff.toString());
		query.setParameter("obj1", LogConstant.LOG_STATUS_END);
		query.setParameter("obj2", classType);
		query.setParameter("obj3", LogConstant.LOG_STATUS_START);
		
		int exResult = query.executeUpdate();
		System.out.println("BaccaratHbmDao.createTotalLog.updateTotalLogStatusCount: "+exResult +" : type:"+ClassConstant.CLASS_MAP.get(classType));
		
		Card card = actionCard(null,null);
		
		GameLogTotal gameLogTotal = new GameLogTotal();
		gameLogTotal.setStartDate(new Date());
		gameLogTotal.setPlayer(new Player(playerId));
		gameLogTotal.setClassType(classType);
		gameLogTotal.setStatus(LogConstant.LOG_STATUS_START);
		gameLogTotal.setCard(card);
		getHibernateTemplate().save(gameLogTotal);
		
		
		strBuff = new StringBuffer();
		strBuff.append(" select count(log)");
		strBuff.append(" from GameLogTotal log  ");
		strBuff.append(" where log.player.id=:obj1  ");
		strBuff.append(" and log.classType=:obj2  ");
		
		query = getSessionFactory().getCurrentSession().createQuery(strBuff.toString());
		query.setParameter("obj1", playerId);
		query.setParameter("obj2", classType);
		
		Long totalRecord=(Long)query.uniqueResult();
		
		Object[] obj= {gameLogTotal.getId(),totalRecord};
		return obj;
	}

	@Override
	public GameLogTotal closeTotalLog(Long totalLogId) throws Exception {
	GameLogTotal gameLogTotal = getHibernateTemplate().get(GameLogTotal.class, totalLogId);
		gameLogTotal.setEndDate(new Date());
		gameLogTotal.setStatus(LogConstant.LOG_STATUS_END);
		getHibernateTemplate().update(gameLogTotal);
		
		return gameLogTotal;
	}

	@Override
	public List<GameLog> gatGameLog(Long totalLogId, Integer max) throws Exception {

		StringBuffer strBuff = new StringBuffer();
		strBuff.append(" select log");
		strBuff.append(" from GameLog log  ");
		strBuff.append(" where log.gameLogTotal.id=:obj1  ");
		strBuff.append(" order by f_seq asc  ");
		
		Query query = getSessionFactory().getCurrentSession().createQuery(strBuff.toString());
		query.setParameter("obj1", totalLogId);
		if(max!=null)
		query.setMaxResults(max);
		return (List<GameLog>)query.list();
	}

	@Override
	public Player createMember(String name, String email) throws Exception {
		
		Player player = new Player();
		player.setEmail(email);
		player.setName(name);
		
		getHibernateTemplate().save(player);
		return player;
	}
	
	
	/* function */
	
	private Card actionCard(GameLog gameLog,GameCard gameCard) {
		Integer ttCard=32;
		
		
		if (gameLog != null) {
			Card card = gameLog.getGameLogTotal().getCard();

			String[] bancoArr = gameCard.getBancoStr();
			String[] playerArr = gameCard.getPlayerStr();
			for (String bCard : bancoArr) {
				card.updateCard(bCard);
			}
			for (String pCard : playerArr) {
				card.updateCard(pCard);
			}

			getHibernateTemplate().update(card);
			
			return card;
		} else {
			Card card = new Card();
			card.setP1(ttCard);card.setP2(ttCard);card.setP3(ttCard);card.setP4(ttCard);
			card.setP5(ttCard);card.setP6(ttCard);card.setP7(ttCard);card.setP8(ttCard);
			card.setP9(ttCard);card.setP10(ttCard);card.setP11(ttCard);card.setP12(ttCard);
			card.setP13(ttCard);
			
			card.setS1(ttCard);card.setS2(ttCard);card.setS3(ttCard);card.setS4(ttCard);
			card.setS5(ttCard);card.setS6(ttCard);card.setS7(ttCard);card.setS8(ttCard);
			card.setS9(ttCard);card.setS10(ttCard);card.setS11(ttCard);card.setS12(ttCard);
			card.setS13(ttCard);
			
			card.setH1(ttCard);card.setH2(ttCard);card.setH3(ttCard);card.setH4(ttCard);
			card.setH5(ttCard);card.setH6(ttCard);card.setH7(ttCard);card.setH8(ttCard);
			card.setH9(ttCard);card.setH10(ttCard);card.setH11(ttCard);card.setH12(ttCard);
			card.setH13(ttCard);
			
			card.setB1(ttCard);card.setB2(ttCard);card.setB3(ttCard);card.setB4(ttCard);
			card.setB5(ttCard);card.setB6(ttCard);card.setB7(ttCard);card.setB8(ttCard);
			card.setB9(ttCard);card.setB10(ttCard);	card.setB11(ttCard);card.setB12(ttCard);
			card.setB13(ttCard);

			getHibernateTemplate().save(card);
			
			return card;
		}
		
		
	}

}
