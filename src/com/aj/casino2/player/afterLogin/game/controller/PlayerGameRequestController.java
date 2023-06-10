package com.aj.casino2.player.afterLogin.game.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aj.casino2.common.constant.ErrorCodeConstant;
import com.aj.casino2.player.afterLogin.dto.PlayerGameInfoDataDto;
import com.aj.casino2.player.afterLogin.dto.PlayerGameInfoDto;
import com.aj.casino2.player.afterLogin.game.service.PlayerGameRequestService;
import com.aj.casino2.player.common.controller.PlayerBaseController;
import com.aj.module.exception.ApiErrorCodeException;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("areq/game")
public class PlayerGameRequestController extends PlayerBaseController{

	
	private PlayerGameRequestService playerGameRequestService;
	
	public void setPlayerGameRequestService(PlayerGameRequestService playerGameRequestService) {
		this.playerGameRequestService = playerGameRequestService;
	}

	// declare parameter
	private static String REQ_CITY_ID = "cityId";
	private static String REQ_CATRGORY_ID = "categoryId";
	private static String REQ_GAME_TYPE_ID = "gameTypeId";

	@RequestMapping(value = "/getGameInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String getGameInfo(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		System.out.println("PlayerGameRequestController.getGameInfo() start ");
		JsonObject resultObj = new JsonObject();
		resultObj.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_SUCCESS);
		resultObj.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_SUCCESS);

		try {

			PlayerGameInfoDto gameInfo = playerGameRequestService.getGameInfo(getExtPlayerId(req), getUserId(req), getEntityId(req));

			JsonObject obj = (JsonObject)new Gson().toJsonTree(gameInfo, new TypeToken<PlayerGameInfoDto>() {}.getType());
			
			resultObj.add(ErrorCodeConstant.LABEL_RESULT, obj);
			
		} catch (Exception e) {
			catchException(e, resultObj);
		}

		return resultObj.toString();
	}

	
	@RequestMapping(value = "/getGameCategory", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String getGameCategory(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		System.out.println("PlayerGameRequestController.getGameCategory() start ");
		JsonObject resultObj = new JsonObject();
		resultObj.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_SUCCESS);
		resultObj.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_SUCCESS);

		try {
			verifyParameter(req, new String[] {REQ_CITY_ID});
			
			String cityId = req.getParameter(REQ_CITY_ID);
			
			List<PlayerGameInfoDataDto> gameCategoryList = playerGameRequestService.getGameCategoryList(new Long(cityId),getUserId(req), getEntityId(req));

			JsonArray arr = (JsonArray)new Gson().toJsonTree(gameCategoryList, new TypeToken<List<PlayerGameInfoDataDto>>() {}.getType());
			
			resultObj.add(ErrorCodeConstant.LABEL_RESULT, arr);
			
			
		} catch (Exception e) {
			catchException(e, resultObj);
		}

		return resultObj.toString();
	}
	
	
	@RequestMapping(value = "/getGameType", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String getGameType(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		System.out.println("PlayerGameRequestController.getGameType() start ");
		JsonObject resultObj = new JsonObject();
		resultObj.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_SUCCESS);
		resultObj.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_SUCCESS);

		try {
			verifyParameter(req, new String[] {REQ_CATRGORY_ID});
			
			String categoryIdStr = req.getParameter(REQ_CATRGORY_ID);
			
			List<PlayerGameInfoDataDto> gameTypeList = playerGameRequestService.getGameTypeList(new Long(categoryIdStr),getUserId(req), getEntityId(req));

			JsonArray arr = (JsonArray)new Gson().toJsonTree(gameTypeList, new TypeToken<List<PlayerGameInfoDataDto>>() {}.getType());
			
			resultObj.add(ErrorCodeConstant.LABEL_RESULT, arr);
			
			
		} catch (Exception e) {
			catchException(e, resultObj);
		}

		return resultObj.toString();
	}
	
	
	@RequestMapping(value = "/getGame", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody String getGame(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		System.out.println("PlayerGameRequestController.getGame() start ");
		JsonObject resultObj = new JsonObject();
		resultObj.addProperty(ErrorCodeConstant.LABEL_CODE, ErrorCodeConstant.CODE_SUCCESS);
		resultObj.addProperty(ErrorCodeConstant.LABEL_DESC, ErrorCodeConstant.DESC_SUCCESS);

		try {
			verifyParameter(req, new String[] {REQ_GAME_TYPE_ID});
			
			String gameTypeIdStr = req.getParameter(REQ_GAME_TYPE_ID);
			
			List<PlayerGameInfoDataDto> gameList = playerGameRequestService.getGameList(new Long(gameTypeIdStr),getUserId(req), getEntityId(req));

			JsonArray arr = (JsonArray)new Gson().toJsonTree(gameList, new TypeToken<List<PlayerGameInfoDataDto>>() {}.getType());
			
			resultObj.add(ErrorCodeConstant.LABEL_RESULT, arr);
			
			
		} catch (Exception e) {
			catchException(e, resultObj);
		}

		return resultObj.toString();
	}
}
