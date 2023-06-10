package com.aj.casino2.player.afterLogin.dto;

import java.util.List;

public class PlayerGameInfoDataDto {

	private Long id;
	private String name;
	
	private List<PlayerGameInfoDataDto> detail;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PlayerGameInfoDataDto> getDetail() {
		return detail;
	}
	public void setDetail(List<PlayerGameInfoDataDto> detail) {
		this.detail = detail;
	}
	
	
	
	
}
