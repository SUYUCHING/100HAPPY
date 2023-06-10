package com.aj.db.domain.log;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.aj.db.domain.antertainment.AntertainmentGame;
import com.aj.db.domain.user.Player;

public class PlayerGameLog implements Serializable{

	private Long id;
	private Date createDate;
	private Player player;
	private BigDecimal betAmt;
	private BigDecimal winLose;
	private AntertainmentGame antertainmentGame;

	public PlayerGameLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlayerGameLog(Long id) {
		super();
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public BigDecimal getBetAmt() {
		return betAmt;
	}
	public void setBetAmt(BigDecimal betAmt) {
		this.betAmt = betAmt;
	}
	public BigDecimal getWinLose() {
		return winLose;
	}
	public void setWinLose(BigDecimal winLose) {
		this.winLose = winLose;
	}
	public AntertainmentGame getAntertainmentGame() {
		return antertainmentGame;
	}
	public void setAntertainmentGame(AntertainmentGame antertainmentGame) {
		this.antertainmentGame = antertainmentGame;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
}
