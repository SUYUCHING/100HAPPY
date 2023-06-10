package com.aj.db.domain.baccarat;

import java.io.Serializable;
import java.util.Date;

public class GameLogTotal implements Serializable{

	private Long id;
	private Integer totalBanco;
	private Integer totalPlayer;
	private Integer totalSuperSix;
	private Integer totalBancoTreasure;
	private Integer totalTwoBanco;
	private Integer totalPlayerTreasure;
	private Integer totalTwoPlayer;
	private Integer totalTie;
	private Date startDate;
	private Date endDate;
	private Integer status;
	
	private Integer classType;
	private Player player;
	
	private Card card;
	

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getClassType() {
		return classType;
	}

	public void setClassType(Integer classType) {
		this.classType = classType;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public GameLogTotal() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GameLogTotal(Long id) {
		super();
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getTotalBanco() {
		return totalBanco;
	}
	public void setTotalBanco(Integer totalBanco) {
		this.totalBanco = totalBanco;
	}
	public Integer getTotalPlayer() {
		return totalPlayer;
	}
	public void setTotalPlayer(Integer totalPlayer) {
		this.totalPlayer = totalPlayer;
	}
	public Integer getTotalSuperSix() {
		return totalSuperSix;
	}
	public void setTotalSuperSix(Integer totalSuperSix) {
		this.totalSuperSix = totalSuperSix;
	}
	public Integer getTotalBancoTreasure() {
		return totalBancoTreasure;
	}
	public void setTotalBancoTreasure(Integer totalBancoTreasure) {
		this.totalBancoTreasure = totalBancoTreasure;
	}
	public Integer getTotalTwoBanco() {
		return totalTwoBanco;
	}
	public void setTotalTwoBanco(Integer totalTwoBanco) {
		this.totalTwoBanco = totalTwoBanco;
	}
	public Integer getTotalPlayerTreasure() {
		return totalPlayerTreasure;
	}
	public void setTotalPlayerTreasure(Integer totalPlayerTreasure) {
		this.totalPlayerTreasure = totalPlayerTreasure;
	}
	public Integer getTotalTwoPlayer() {
		return totalTwoPlayer;
	}
	public void setTotalTwoPlayer(Integer totalTwoPlayer) {
		this.totalTwoPlayer = totalTwoPlayer;
	}
	public Integer getTotalTie() {
		return totalTie;
	}
	public void setTotalTie(Integer totalTie) {
		this.totalTie = totalTie;
	}
	
	
	
}
