package com.aj.db.domain.antertainment;

import java.io.Serializable;

public class AntertainmentGame implements Serializable{

	private Long id;
	private String code;
	private String name;
	private AntertainmentGameType antertainmentGameType;
	private Integer type;
	
	
	public AntertainmentGame() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AntertainmentGame(Long id) {
		super();
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AntertainmentGameType getAntertainmentGameType() {
		return antertainmentGameType;
	}
	public void setAntertainmentGameType(AntertainmentGameType antertainmentGameType) {
		this.antertainmentGameType = antertainmentGameType;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
