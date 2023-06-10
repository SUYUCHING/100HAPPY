package com.aj.db.domain.antertainment;

import java.io.Serializable;

public class AntertainmentGameCategory implements Serializable{

	
	private Long id;
	private String code;
	private String name;
	
	private AntertainmentCity antertainmentCity;

	
	
	public AntertainmentGameCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AntertainmentGameCategory(Long id) {
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

	public AntertainmentCity getAntertainmentCity() {
		return antertainmentCity;
	}

	public void setAntertainmentCity(AntertainmentCity antertainmentCity) {
		this.antertainmentCity = antertainmentCity;
	}
	
	
	
}
