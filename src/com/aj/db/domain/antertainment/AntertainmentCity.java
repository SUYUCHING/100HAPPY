package com.aj.db.domain.antertainment;

import java.io.Serializable;

public class AntertainmentCity implements Serializable{

	private Long id;
	private String code;
	private String name;
	private String data;
	
	
	
	public AntertainmentCity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AntertainmentCity(Long id) {
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
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
