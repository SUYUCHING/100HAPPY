package com.aj.db.domain.user;

import java.io.Serializable;
import java.math.BigDecimal;

public class PlayerWallet implements Serializable{
	
	private Long id;
	private BigDecimal credit;
	private Integer creditType;
	private Player player;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getCredit() {
		return credit;
	}
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Integer getCreditType() {
		return creditType;
	}
	public void setCreditType(Integer creditType) {
		this.creditType = creditType;
	}
	

	
	
}
