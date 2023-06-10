package com.aj.casino2.player.afterLogin.game.service;

import java.util.List;

import com.aj.casino2.player.afterLogin.dto.PlayerGameInfoDataDto;
import com.aj.casino2.player.afterLogin.dto.PlayerGameInfoDto;

public interface PlayerGameRequestService {

	public PlayerGameInfoDto getGameInfo(String extPlayerId, Long userId, Long entityId)throws Exception;
	
	public List<PlayerGameInfoDataDto> getGameCategoryList(Long cityId, Long userId, Long entityId)throws Exception;

	public List<PlayerGameInfoDataDto> getGameTypeList(Long categoryId, Long userId, Long entityId)throws Exception;
 	
 	public List<PlayerGameInfoDataDto> getGameList(Long gameTypeId, Long userId, Long entityId)throws Exception;
 	
	
}
