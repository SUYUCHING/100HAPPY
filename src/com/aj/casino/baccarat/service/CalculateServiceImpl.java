package com.aj.casino.baccarat.service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import com.aj.casino.baccarat.dao.BaccaratHbmDaoInf;
import com.aj.casino.constant.BaccaratContant;
import com.aj.db.domain.baccarat.Card;
import com.aj.db.domain.baccarat.GameLog;
import com.aj.db.domain.baccarat.GameLogTotal;
import com.google.gson.Gson;

public class CalculateServiceImpl implements CalculateService {

    private BaccaratHbmDaoInf baccaratHbmDao;
    
	public void setBaccaratHbmDao(BaccaratHbmDaoInf baccaratHbmDao) {
		this.baccaratHbmDao = baccaratHbmDao;
	}
	@Override
	public boolean calculateBaccarat(GameLog gameLog) throws Exception {

		GameLogTotal gameLogTotal = gameLog.getGameLogTotal();
		
		List<GameLog> logList = baccaratHbmDao.gatGameLog(gameLogTotal.getId(), null);
		
		boolean statisticalRoad = getStatisticalRoad(logList);
		
		
		return statisticalRoad;
		
	}

	public void calculateRoad(List<GameLog> gameLogList) {
		//1.前一條有無對齊 的次數
		//2.莊家 閒家 的最高點
		//3.一旦與前一條對齊 下一把 要斷 (依據 下面 做判斷)
		/* 3.1.抓出連續對齊的次數 ex : 莊莊閒閒莊莊莊 >莊(斷了) (這樣一組 ) or 莊閒莊閒(這樣兩組) 
		 * 3.2.(只看目前 莊閒莊閒(莊閒單跳) 其餘不看) 連續對齊後 第二次斷 -> ex1, 跟計算起來有多少次這種的  ex1: 莊閒莊閒 下一把開閒 在下一把開庄
		 * 3.3 (莊莊閒閒 ) ex: 莊莊閒閒莊莊->莊  ,剩下的不評估
		 * 3.4 (莊莊莊閒閒(3~4) 五個後 不計算 ) ex: 莊莊莊閒 -> 閒(只需算出這一把)   ,剩下的不評估
		 * 3.5.(依據先前案例作判斷 計算出 有多少個 1 or 2 or 3 or 2 -> 這樣下次遇到就以2為基準
		 *  (看第一點 有幾個組合包 ) ex-> 莊莊閒閒莊莊莊 -> 莊莊閒閒莊莊莊 -> 莊莊閒閒莊莊閒閒閒 (這樣就是斷了 只有一次)
		 *  (一開始遇到 以前二次為預測 ))連續對齊 後 超過二次 斷 
		    已次類推 第二行 出現兩次斷
		
		*/
		//4. 路子不順 換場 (以上面統計 超過20-30場 沒有任何統計數據 就換桌)
		
		
	}
	
	public boolean getStatisticalRoad(List<GameLog> logList) throws Exception {
		
		
		List<List<GameLog>> winRoad =new ArrayList<>();
		List<GameLog> winRoadChild =new ArrayList<>();
		
		
		for(GameLog gameLog: logList) {
			
			if (winRoadChild.size() > 0 && winRoadChild.get(0).getWinnerType().compareTo(gameLog.getWinnerType()) != 0 && gameLog.getWinnerType().compareTo(BaccaratContant.WINNER_TYPE_TIE)!=0) 
			{
				winRoad.add(winRoadChild);
				winRoadChild = new ArrayList<>();
			}
			
			winRoadChild.add(gameLog);

		}
		winRoad.add(winRoadChild);
		
		/* start print*/ 
		int i=1;
		for(List<GameLog> list:winRoad) 
		{
			System.out.print("road "+i+": ");
			
			for(GameLog gl:list) {
				System.out.print(BaccaratContant.WIN_TYPE_MAP.get(gl.getWinnerType())+",");
			}
			
			System.out.println();
			i++;
		}
		System.out.println("=======================================");
		
		/* end print */
		
		return randInt(0,1) ==0?true : false; //TODO
		
	}
	
	public List<Card> getStatisticalCard(List<GameLog> logList) {
		
		Gson gson = new Gson();
		List<Card> cardList=new ArrayList<>();
		
		for(GameLog gameLog: logList) 
		{
			cardList.add(gson.fromJson(gameLog.getLastCard(), Card.class));
		}
		
		return cardList;
	}

	
	
	private static int randInt(int min, int max) {
		SecureRandom rand = new SecureRandom();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
}
