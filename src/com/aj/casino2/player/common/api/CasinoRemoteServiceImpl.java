package com.aj.casino2.player.common.api;

import com.aj.casino2.common.constant.ErrorCodeConstant;
import com.aj.casino2.player.common.service.ApiService;
import com.aj.client.CasinoGameRequestService;
import com.aj.module.exception.ApiErrorCodeException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CasinoRemoteServiceImpl extends ApiService implements CasinoGameRequestService{

	
	private CasinoGameRequestService casinoGameRequestService;

	public void setCasinoGameRequestService(CasinoGameRequestService casinoGameRequestService) {
		this.casinoGameRequestService = casinoGameRequestService;
	}
	


	@Override
	public String getPlayerGameLog(String arg0, Integer arg1) throws Exception {
	try {
			
			String res = casinoGameRequestService.getPlayerGameLog(arg0, arg1);
			if(checkRemoteResult(res)) {			
				return res;
			}
			
			JsonObject obj = new JsonParser().parse(res).getAsJsonObject().get(ErrorCodeConstant.LABEL_RESULT).getAsJsonObject();
			throw new ApiErrorCodeException(obj.get(ErrorCodeConstant.LABEL_CODE).getAsString(), obj.get(ErrorCodeConstant.LABEL_DESC).getAsString());
			
		} catch (Exception e) {
			throw e;
		}
	}


	
	
}
