package com.aj.db.domain.user;

import java.io.Serializable;

public class Entity implements Serializable{
	
	private Long id;
	private String name;
	private String code;
	
	
	public Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Entity(Long id) {
		super();
		this.id = id;
	}
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	

}
