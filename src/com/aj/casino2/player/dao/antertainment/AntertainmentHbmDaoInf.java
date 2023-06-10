package com.aj.casino2.player.dao.antertainment;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.aj.db.domain.antertainment.AntertainmentCity;
import com.aj.db.domain.antertainment.AntertainmentGame;
import com.aj.db.domain.antertainment.AntertainmentGameCategory;
import com.aj.db.domain.antertainment.AntertainmentGameType;

public interface AntertainmentHbmDaoInf {

	public AntertainmentCity getAntertainmentCityById(Long id)throws Exception;
	public AntertainmentCity getAntertainmentCityByGameId(Long gameId)throws Exception;
	public AntertainmentGameCategory getAntertainmentGameCategoryById(Long id)throws Exception;
	public AntertainmentGameType getAntertainmentGameTypeById(Long id)throws Exception;
	public AntertainmentGame getAntertainmentGameById(Long id)throws Exception;
	
	public List<AntertainmentCity> getAntertainmentCityList()throws Exception;
	public List<AntertainmentGameCategory> getAntertainmentGameCategoryist(Long cityId)throws Exception;
	public List<AntertainmentGameType> getAntertainmentGameTypeList(Long categoryId)throws Exception;
	public List<AntertainmentGame> getAntertainmentGameList(Long gameTypeId)throws Exception;
	
	
	
}
