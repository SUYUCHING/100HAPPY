package com.aj.casino2.player.afterLogin.game.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.aj.casino2.player.afterLogin.dto.PlayerGameInfoDataDto;
import com.aj.casino2.player.afterLogin.dto.PlayerGameInfoDto;
import com.aj.casino2.player.afterLogin.game.dto.BancoCardDto;
import com.aj.casino2.player.afterLogin.game.dto.GameDto;
import com.aj.casino2.player.afterLogin.game.dto.PlayerCardDto;
import com.aj.casino2.player.common.service.ApiService;
import com.aj.casino2.player.contant.GameConstant;
import com.aj.casino2.player.dao.antertainment.AntertainmentHbmDaoInf;
import com.aj.casino2.player.dao.entity.EntityHbmDaoInf;
import com.aj.casino2.player.dao.log.PlayerGameLogHbmDaoInf;
import com.aj.casino2.player.dao.player.PlayerHbmDaoInf;
import com.aj.casino2.player.dto.CasionCalculateRuleDto;
import com.aj.db.domain.antertainment.AntertainmentCity;
import com.aj.db.domain.antertainment.AntertainmentGame;
import com.aj.db.domain.antertainment.AntertainmentGameCategory;
import com.aj.db.domain.antertainment.AntertainmentGameType;
import com.aj.db.domain.log.PlayerGameLog;
import com.aj.db.domain.user.Player;

public class PlayerGameRequestServiceImpl  implements PlayerGameRequestService {

	private AntertainmentHbmDaoInf antertainmentHbmDao;
	private PlayerGameLogHbmDaoInf playerGameLogHbmDao;
	private PlayerHbmDaoInf playerHbmDao;
	private EntityHbmDaoInf entityHbmDao;
	private ApiService apiService;
	
	public void setAntertainmentHbmDao(AntertainmentHbmDaoInf antertainmentHbmDao) {
		this.antertainmentHbmDao = antertainmentHbmDao;
	}

	public void setPlayerGameLogHbmDao(PlayerGameLogHbmDaoInf playerGameLogHbmDao) {
		this.playerGameLogHbmDao = playerGameLogHbmDao;
	}

	public void setPlayerHbmDao(PlayerHbmDaoInf playerHbmDao) {
		this.playerHbmDao = playerHbmDao;
	}

	public void setEntityHbmDao(EntityHbmDaoInf entityHbmDao) {
		this.entityHbmDao = entityHbmDao;
	}



	public void setApiService(ApiService apiService) {
		this.apiService = apiService;
	}

	@Override
	public PlayerGameInfoDto getGameInfo(String extPlayerId, Long userId, Long entityId) throws Exception {
		try {

			
			List<PlayerGameLog> playerGameLogList = playerGameLogHbmDao.getPlayerGameLog(userId, 1);
			
			List<AntertainmentCity> antertainmentCityList = antertainmentHbmDao.getAntertainmentCityList();

			List<AntertainmentGameCategory> catrgoryList = new ArrayList<>();
			List<AntertainmentGameType> gameTypeList = new ArrayList<>();
			List<AntertainmentGame> gameList = new ArrayList<>();
			
			AntertainmentCity antertainmentCity =null;
			AntertainmentGameCategory antertainmentGameCategory =null;
			AntertainmentGameType antertainmentGameType =null;
			AntertainmentGame antertainmentGame =null;
			
			PlayerGameInfoDto dto = new PlayerGameInfoDto();
			List<PlayerGameInfoDataDto> cityDtoList = dto.getCity();
			List<PlayerGameInfoDataDto> categoryDtoList = dto.getGameCategory();
			List<PlayerGameInfoDataDto> gameTypeDtoList = dto.getGameType();
			List<PlayerGameInfoDataDto> gameDtoList = dto.getGame();
			
			
			if (playerGameLogList.size()>0 ) {
				PlayerGameLog pGameLog = playerGameLogList.get(0);
				antertainmentGame =  antertainmentHbmDao.getAntertainmentGameById(pGameLog.getAntertainmentGame().getId());
				antertainmentGameType =  antertainmentHbmDao.getAntertainmentGameTypeById(antertainmentGame.getAntertainmentGameType().getId());
				antertainmentGameCategory =  antertainmentHbmDao.getAntertainmentGameCategoryById(antertainmentGameType.getAntertainmentGameCategory().getId());
				antertainmentCity =  antertainmentHbmDao.getAntertainmentCityById(antertainmentGameCategory.getAntertainmentCity().getId());
			}
			
			
			if(antertainmentCityList.size()>0) {
				antertainmentCity = antertainmentCity == null ?antertainmentCityList.get(0):antertainmentCity;
			    catrgoryList = antertainmentHbmDao.getAntertainmentGameCategoryist(antertainmentCity.getId());
			}
			if(catrgoryList.size()>0) {
				antertainmentGameCategory = antertainmentGameCategory == null ?catrgoryList.get(0):antertainmentGameCategory;
				 gameTypeList = antertainmentHbmDao.getAntertainmentGameTypeList(antertainmentCity.getId());
			}
			
			if(gameTypeList.size()>0) {
				antertainmentGameType = antertainmentGameType == null ?gameTypeList.get(0):antertainmentGameType;
				gameList = antertainmentHbmDao.getAntertainmentGameList(antertainmentGameType.getId());
			}
			
			if(gameList.size()>0) {
				antertainmentGame = antertainmentGame == null ?gameList.get(0):antertainmentGame;
			}

		
			

			int index = 0;
			Integer getIndex = null;
			for (AntertainmentCity city : antertainmentCityList) {
				PlayerGameInfoDataDto cityDto = new PlayerGameInfoDataDto();
				cityDto.setId(city.getId());
				cityDto.setName(city.getName());
				cityDtoList.add(cityDto);
				
				if(antertainmentCity.getId().compareTo(city.getId()) == 0) {
					getIndex = index;
				}
				index++;
			}
			
			if(getIndex != null) {
				 Collections.swap(cityDtoList, 0, getIndex);
			}
			
			
			index = 0;
			getIndex = null;
			for (AntertainmentGameCategory category : catrgoryList) {
				PlayerGameInfoDataDto catrgoryDto = new PlayerGameInfoDataDto();
				catrgoryDto.setId(category.getId());
				catrgoryDto.setName(category.getName());
				categoryDtoList.add(catrgoryDto);
				
				if(antertainmentGameCategory.getId().compareTo(category.getId()) == 0) {
					getIndex = index;
				}
				index++;
			}
			
			if(getIndex != null) {
				 Collections.swap(categoryDtoList, 0, getIndex);
			}
			
			
			index = 0;
			getIndex = null;
			
			for (AntertainmentGameType gameType : gameTypeList) {
				PlayerGameInfoDataDto gameTypeDto = new PlayerGameInfoDataDto();
				gameTypeDto.setId(gameType.getId());
				gameTypeDto.setName(gameType.getName());
				gameTypeDtoList.add(gameTypeDto);
				
				if(antertainmentGameType.getId().compareTo(gameType.getId()) == 0) {
					getIndex = index;
				}
				index++;
			}
			
			
			if(getIndex != null) {
				 Collections.swap(gameTypeDtoList, 0, getIndex);
			}
			
			index = 0;
			getIndex = null;
			
			for (AntertainmentGame game : gameList) {
				PlayerGameInfoDataDto gameDto = new PlayerGameInfoDataDto();
				gameDto.setId(game.getId());
				gameDto.setName(game.getName());
				gameDtoList.add(gameDto);
				
				if(antertainmentGame.getId().compareTo(game.getId()) == 0) {
					getIndex = index;
				}
				index++;
			}
			
			if(getIndex != null) {
				 Collections.swap(gameDtoList, 0, getIndex);
			}
			
			
			
			
		

			return dto;

		} catch (Exception e) {
			throw e;
		}

	}
	
	
	
    @Override
	public List<PlayerGameInfoDataDto> getGameCategoryList(Long cityId, Long userId, Long entityId)throws Exception{
		
		try {
			List<AntertainmentGameCategory> antertainmentGameCategoryist = antertainmentHbmDao.getAntertainmentGameCategoryist(cityId);
			List<PlayerGameInfoDataDto> dtoList = new ArrayList<>();
			for(AntertainmentGameCategory category: antertainmentGameCategoryist) {
				PlayerGameInfoDataDto dto = new PlayerGameInfoDataDto();
				dto.setId(category.getId());
				dto.setName(category.getName());
				dtoList.add(dto);
			}
			
			return dtoList;
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	
    @Override
   	public List<PlayerGameInfoDataDto> getGameTypeList(Long categoryId, Long userId, Long entityId)throws Exception{
   		
   		try {
   			List<AntertainmentGameType> antertainmentGameTypeist = antertainmentHbmDao.getAntertainmentGameTypeList(categoryId);
   			List<PlayerGameInfoDataDto> dtoList = new ArrayList<>();
   			for(AntertainmentGameType gameType: antertainmentGameTypeist) {
   				PlayerGameInfoDataDto dto = new PlayerGameInfoDataDto();
   				dto.setId(gameType.getId());
   				dto.setName(gameType.getName());
   				dtoList.add(dto);
   			}
   			
   			return dtoList;
   			
   		} catch (Exception e) {
   			throw e;
   		}
   		
   	}
    
    @Override
   	public List<PlayerGameInfoDataDto> getGameList(Long gameTypeId, Long userId, Long entityId)throws Exception{
   		
   		try {
   			List<AntertainmentGame> antertainmentGameist = antertainmentHbmDao.getAntertainmentGameList(gameTypeId);
   			List<PlayerGameInfoDataDto> dtoList = new ArrayList<>();
   			for(AntertainmentGame game: antertainmentGameist) {
   				PlayerGameInfoDataDto dto = new PlayerGameInfoDataDto();
   				dto.setId(game.getId());
   				dto.setName(game.getName());
   				dtoList.add(dto);
   			}
   			
   			return dtoList;
   			
   		} catch (Exception e) {
   			throw e;
   		}
   		
   	}
	
	
    
    
    
    public void bet(Long gameId,String data, BigDecimal amount,String refPlayerId, Long userId, Long entityId)throws Exception{
    	
    	try {
    		
    		Player player = playerHbmDao.getPlayerById(userId, entityId);
    		
    		playerGameLogHbmDao.creatPlayerGameLog(player, amount, gameId);
    		
    		
    		
			
		} catch (Exception e) {
			throw e;
		}
    	
    }
    
    

}
