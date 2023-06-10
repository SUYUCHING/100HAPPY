package com.aj.db.domain.log;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.aj.db.domain.antertainment.AntertainmentGameType;
import com.aj.db.domain.baccarat.Player;

public class PlayerGameStatus implements Serializable{

	private Long id;
	private BigDecimal ttWinLose;
	private Integer seq;
	private Integer status;
	private BigDecimal ttBet;
	private Date createDate;
	private Date endDate;
	
	
	private Player player;
	private AntertainmentGameType antertainmentGameType;
	
	
	
	public PlayerGameStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlayerGameStatus(Long id) {
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
	public BigDecimal getTtWinLose() {
		return ttWinLose;
	}
	public void setTtWinLose(BigDecimal ttWinLose) {
		this.ttWinLose = ttWinLose;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getTtBet() {
		return ttBet;
	}
	public void setTtBet(BigDecimal ttBet) {
		this.ttBet = ttBet;
	}
	public AntertainmentGameType getAntertainmentGameType() {
		return antertainmentGameType;
	}
	public void setAntertainmentGameType(AntertainmentGameType antertainmentGameType) {
		this.antertainmentGameType = antertainmentGameType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
}
