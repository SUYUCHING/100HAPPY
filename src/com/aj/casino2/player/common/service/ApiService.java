package com.aj.casino2.player.common.service;

import java.math.BigDecimal;

import com.aj.casino2.common.constant.ErrorCodeConstant;
import com.aj.casino2.player.contant.GameConstant;
import com.aj.client.CasinoGameRequestService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiService {

	private CasinoGameRequestService casinoRemoteService;
	
	public void setCasinoRemoteService(CasinoGameRequestService casinoRemoteService) {
		this.casinoRemoteService = casinoRemoteService;
	}


	public boolean checkRemoteResult(String result)throws Exception{
		
       try {
			
			JsonObject obj = new JsonParser().parse(result).getAsJsonObject();
			
			if(obj.get(ErrorCodeConstant.LABEL_CODE).getAsString().compareTo(ErrorCodeConstant.CODE_SUCCESS) == 0) {
				return true;
			}
			
			return false;
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	public String getGameInfo(Integer gameType,String playerId, Integer max)throws Exception{
		
		String res = null;
		try {
			if(gameType.compareTo(GameConstant.GAME_TYPE_CASINO) == 0) {
				res = casinoRemoteService.getPlayerGameLog(playerId, max);
			}
			
			
		} catch (Exception e) {
			throw e;
		}
		
		
		return res;
	}
	
	public String bet(Integer gameType, Long gameId ,String data, BigDecimal betAmount,  String playerId, Long playerGameLogId)throws Exception{
		
		String res = null;
		try {
			if(gameType.compareTo(GameConstant.GAME_TYPE_CASINO) == 0) {
			}
			
			
		} catch (Exception e) {
			throw e;
		}
		
		
		return res;
	}
	
	
}
