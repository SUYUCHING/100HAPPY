package com.aj.db.domain.baccarat;

import java.io.Serializable;

public class GameLog implements Serializable{
	
	private Long id;
    private String bancoTypeString;
    private String playerTypeString;
    private String cardString;
    private Integer tieType;
    private Integer winnerType;
	private Integer seq;
	private GameLogTotal gameLogTotal;
	private String lastCard; //card json String
	
	
	
	public String getLastCard() {
		return lastCard;
	}
	public void setLastCard(String lastCard) {
		this.lastCard = lastCard;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getBancoTypeString() {
		return bancoTypeString;
	}
	public void setBancoTypeString(String bancoTypeString) {
		this.bancoTypeString = bancoTypeString;
	}
	public String getPlayerTypeString() {
		return playerTypeString;
	}
	public void setPlayerTypeString(String playerTypeString) {
		this.playerTypeString = playerTypeString;
	}
	public String getCardString() {
		return cardString;
	}
	public void setCardString(String cardString) {
		this.cardString = cardString;
	}
	public Integer getTieType() {
		return tieType;
	}
	public void setTieType(Integer tieType) {
		this.tieType = tieType;
	}
	public Integer getWinnerType() {
		return winnerType;
	}
	public void setWinnerType(Integer winnerType) {
		this.winnerType = winnerType;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public GameLogTotal getGameLogTotal() {
		return gameLogTotal;
	}
	public void setGameLogTotal(GameLogTotal gameLogTotal) {
		this.gameLogTotal = gameLogTotal;
	}
	
	
}
