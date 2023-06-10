package com.aj.db.domain.antertainment;

import java.io.Serializable;

public class AntertainmentGameType implements Serializable{

	private Long id;
	private String code;
	private String name;
	private AntertainmentGameCategory antertainmentGameCategory;
	
	
	
	
	public AntertainmentGameType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AntertainmentGameType(Long id) {
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
	public AntertainmentGameCategory getAntertainmentGameCategory() {
		return antertainmentGameCategory;
	}
	public void setAntertainmentGameCategory(AntertainmentGameCategory antertainmentGameCategory) {
		this.antertainmentGameCategory = antertainmentGameCategory;
	}
	
	
	
	
}
