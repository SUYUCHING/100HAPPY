package com.aj.casino2.player.afterLogin.game.dto;

import java.util.List;

import com.aj.casino2.player.dto.CardDto;

public class PlayerCardDto {

	
	private List<CardDto> firstCards;
	private CardDto outCard;
	
	public List<CardDto> getFirstCards() {
		return firstCards;
	}
	public void setFirstCards(List<CardDto> firstCards) {
		this.firstCards = firstCards;
	}
	public CardDto getOutCard() {
		return outCard;
	}
	public void setOutCard(CardDto outCard) {
		this.outCard = outCard;
	}
	
	
	
	
	
	
}
