package com.aj.casino2.player.afterLogin.dto;

import java.util.ArrayList;
import java.util.List;

public class PlayerGameInfoDto {
	
	private List<PlayerGameInfoDataDto> city = new ArrayList<>();
	private List<PlayerGameInfoDataDto> gameCategory = new ArrayList<>();
	private List<PlayerGameInfoDataDto> gameType = new ArrayList<>();
	private List<PlayerGameInfoDataDto> game = new ArrayList<>();
	
	
	public List<PlayerGameInfoDataDto> getCity() {
		return city;
	}
	public void setCity(List<PlayerGameInfoDataDto> city) {
		this.city = city;
	}
	public List<PlayerGameInfoDataDto> getGame() {
		return game;
	}
	public void setGame(List<PlayerGameInfoDataDto> game) {
		this.game = game;
	}
	public List<PlayerGameInfoDataDto> getGameCategory() {
		return gameCategory;
	}
	public void setGameCategory(List<PlayerGameInfoDataDto> gameCategory) {
		this.gameCategory = gameCategory;
	}
	public List<PlayerGameInfoDataDto> getGameType() {
		return gameType;
	}
	public void setGameType(List<PlayerGameInfoDataDto> gameType) {
		this.gameType = gameType;
	}
	
	
	
}
