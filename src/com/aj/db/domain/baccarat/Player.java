package com.aj.db.domain.baccarat;

public class Player {

	private Long id;
	private String name;
	private String email;
	
	
	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Player(Long id) {
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
