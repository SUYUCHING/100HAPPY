package com.aj.casino.baccarat.dto;

import java.util.Arrays;
import java.util.Map;

public class GameCard {

	private String[] bancoStr;
	private String[] playerStr;
	private Map<Integer,String> winTypeStr;
	private Integer bTtPoint;
	private Integer pTtPoint;
	
	
	public Integer getbTtPoint() {
		return bTtPoint;
	}
	public void setbTtPoint(Integer bTtPoint) {
		this.bTtPoint = bTtPoint;
	}
	public Integer getpTtPoint() {
		return pTtPoint;
	}
	public void setpTtPoint(Integer pTtPoint) {
		this.pTtPoint = pTtPoint;
	}
	public String[] getBancoStr() {
		return bancoStr;
	}
	public void setBancoStr(String[] bancoStr) {
		this.bancoStr = bancoStr;
		bTtPoint=calculateCardTotal(bancoStr);
	}
	public String[] getPlayerStr() {
		return playerStr;
	}
	public void setPlayerStr(String[] playerStr) {
		this.playerStr = playerStr;
		pTtPoint=calculateCardTotal(playerStr);
	}
	public Map<Integer, String> getWinTypeStr() {
		return winTypeStr;
	}
	public void setWinTypeStr(Map<Integer, String> winTypeStr) {
		this.winTypeStr = winTypeStr;
	}
	
	private Integer calculateCardTotal(String[] data) {
		
		Integer ttPoints=0;
		
		for(String card:data) {
			String cardNumStr = card.substring(1);
			Integer cardNum=Integer.parseInt(cardNumStr);
			if(cardNum>=10) {
				cardNum=10;
			}
			ttPoints=ttPoints+cardNum;
		}
		
		
		if(ttPoints.toString().length()<2) {
			return Integer.parseInt(ttPoints.toString());
		}
		return Integer.parseInt(ttPoints.toString().substring(1));
	}
	@Override
	public String toString() {
		return "GameCard [bancoStr=" + Arrays.toString(bancoStr) + ", playerStr=" + Arrays.toString(playerStr)
				+ ", winTypeStr=" + winTypeStr + ", bTtPoint=" + bTtPoint + ", pTtPoint=" + pTtPoint + "]";
	}
	
	
	
}
