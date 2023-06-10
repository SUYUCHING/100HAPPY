package com.aj.db.domain.log;

import java.io.Serializable;

import com.aj.db.domain.antertainment.AntertainmentGame;

public class StieLog implements Serializable {

	
	private Long id;
	private Integer status;
	private AntertainmentGame antertainmentGame;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public AntertainmentGame getAntertainmentGame() {
		return antertainmentGame;
	}
	public void setAntertainmentGame(AntertainmentGame antertainmentGame) {
		this.antertainmentGame = antertainmentGame;
	}
	
	
	
	
}
