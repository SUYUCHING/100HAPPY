package com.aj.casino2.player.dto;

import java.util.ArrayList;
import java.util.List;

import com.aj.casino2.player.contant.GameConstant;

public class CasionCalculateRuleDto {

	
	private List<CardDto> playerCads;
	private CardDto playerOutCard;
	private List<CardDto> bancoCards;
	private CardDto bancoOutCard;
	
	private Integer winner;
	private String winnerType="";
	private Integer playerAmt = 0;
	private Integer bancoAmt = 0;
	private boolean extWine = false;
	private Integer winAmount =0;
	
	
	public CasionCalculateRuleDto(List<CardDto> playerCads, CardDto playerOutCard, List<CardDto> bancoCards,
			CardDto bancoOutCard) throws Exception {
		
		super();
		this.playerCads = playerCads;
		this.playerOutCard = playerOutCard;
		this.bancoCards = bancoCards;
		this.bancoOutCard = bancoOutCard;
		this.playerAmt = calCardsTotalAmount(playerCads) + calCardIntger(playerOutCard);
		this.bancoAmt = calCardsTotalAmount(bancoCards) + calCardIntger(bancoOutCard);
		
		
		playerAmt = playerAmt < 10 ?playerAmt :playerAmt%10;
		bancoAmt = bancoAmt< 10 ? bancoAmt: bancoAmt%10;
		
	}
	
	
	public void calCulationCardInfo()throws Exception{
		
		calculationWinner();
		calculationWinnerType();
		calculationNoWinLoseType();
		
	}
	
	
	private void calculationWinner()throws Exception{
		
		if(playerAmt.compareTo(bancoAmt)>0) {
			this.winner = GameConstant.WINNER_PLAYER_TYPE;
			this.winAmount = playerAmt - bancoAmt;
		}else if(playerAmt.compareTo(bancoAmt)<0) {
			this.winner = GameConstant.WINNER_BANCO_TYPE;
			this.winAmount = bancoAmt - playerAmt;
		}else {
			this.winner = GameConstant.WINNER_TIE_TYPE;
		}
		
		
	}
	
	private void calculationWinnerType()throws Exception{
		
		if(this.winner.compareTo(GameConstant.WINNER_PLAYER_TYPE) == 0) {
			if(playerOutCard != null) {
				if(calCardsTotalAmount(playerCads) == 6) {
					winnerType = winnerType + ":" +GameConstant.WIN_PLAYER_SIX_TYPE;
				}
			}
			
		}else if(this.winner.compareTo(GameConstant.WINNER_BANCO_TYPE) == 0) {
			if(bancoOutCard == null) {
				if(calCardsTotalAmount(bancoCards) == 6) {
					winnerType = winnerType + ":" +GameConstant.WIN_BANCO_SIX_TYPE;
				}
			}
			
		}else if(this.winner.compareTo(GameConstant.WINNER_TIE_TYPE) == 0) {
			
		}
		
		
		
	}
	
	
	public void calculationNoWinLoseType()throws Exception{
   
		//莊對
		if(this.bancoCards.size()==2) {
			if(this.bancoCards.get(0).getNumber().compareTo(this.bancoCards.get(1).getNumber()) == 0) {
				winnerType = winnerType + ":" +GameConstant.WIN_BANCO_TWO_TYPE;
			}
		}
		
		//閒對
		if(this.playerCads.size()==2) {
			if(this.playerCads.get(0).getNumber().compareTo(this.playerCads.get(1).getNumber()) == 0) {
				winnerType = winnerType + ":" +GameConstant.WIN_PLAYER_TWO_TYPE;
			}
		}
		
		
		
		//莊龍寶
		if(bancoAmt>7) {
			if(calCardsTotalAmount(bancoCards) >7) {
				winnerType = winnerType + ":" +GameConstant.WIN_BANCO_TREASURE_TYPE;
				extWine = true;
			}
		}
		if(winner.compareTo(GameConstant.WINNER_BANCO_TYPE) == 0) {
			if(winAmount.compareTo(3)>0) {
				winnerType = winnerType + ":" +GameConstant.WIN_BANCO_TREASURE_TYPE;
			}
		}
		
			
			
		
		//閒龍寶
		if(playerAmt>7) {
			if(calCardsTotalAmount(playerCads) >7) {
				winnerType = winnerType + ":" +GameConstant.WIN_PLAYER_TREASURE_TYPE;
				extWine = true;
			} 
		}
		
		if(winner.compareTo(GameConstant.WINNER_PLAYER_TYPE) == 0) {
			if(winAmount.compareTo(3)>0) {
				winnerType = winnerType + ":" +GameConstant.WIN_PLAYER_TREASURE_TYPE;
			}
		}
	}
	
	
	private Integer calCardsTotalAmount(List<CardDto> cards){
		
		Integer exCardAmt = 0;
		
		for(CardDto card : cards) {
			Integer number = card.getNumber() > 10 ? 10 : card.getNumber();
			exCardAmt = exCardAmt + number;
		}
		
		return exCardAmt%10;
	}
	
	

	
	private Integer calCardIntger(CardDto dto) {
		
		Integer amount =0;
		if(dto != null) {
			amount = dto.getNumber() >= 10 ?0:dto.getNumber();
		}
		
		return amount;
		
	}
	
	
    public static void main(String[] args) throws Exception {
    	
    	List<CardDto> playerCards = new ArrayList<>();
    	CardDto playerCardDto = new CardDto();
    	playerCardDto.setNumber(3);
    	playerCards.add(playerCardDto);
    	
    	playerCardDto = new CardDto();
    	playerCardDto.setNumber(4);
    	playerCards.add(playerCardDto);
    	
    	CardDto playerOutCate = new CardDto();
    	playerOutCate.setNumber(11);
    	
    	List<CardDto> bancoCards = new ArrayList<>();
    	CardDto bancoCard = new CardDto();
    	bancoCard.setNumber(9);
    	bancoCards.add(bancoCard);
    	
     	bancoCard = new CardDto();
     	bancoCard.setNumber(10);
     	bancoCards.add(bancoCard);
    	
     	CardDto bancoOutCate = new CardDto();
     	bancoOutCate.setNumber(12);
     	
     	
     	CasionCalculateRuleDto cardDto = new CasionCalculateRuleDto(playerCards, playerOutCate, bancoCards, bancoOutCate);;
     	cardDto.calCulationCardInfo();
     	
     	System.out.println(">>> playerAmount: "+cardDto.getPlayerAmt());
     	System.out.println(">>> bancoAmount: "+cardDto.getBancoAmt());
     	System.out.println(">>> winner: "+cardDto.getWinner());
     	System.out.println(">>> winnerType: "+cardDto.getWinnerType());
     	System.out.println(">>> extWine: "+cardDto.isExtWine());
     	System.out.println(">>> winAmount: "+cardDto.getWinAmount());
     	
     	
	}
    
    

	public String getWinnerType() {
		if(winnerType.compareTo("") != 0) {
			if (winnerType.indexOf(":") == 0)
				winnerType = winnerType.substring(1, winnerType.length());
		}else {
			winnerType = GameConstant.WIN_NORMAL_TYPE.toString();
		}
		
		return winnerType;
	}

	public void setPlayerCads(List<CardDto> playerCads) {
		this.playerCads = playerCads;
	}

	public void setPlayerOutCard(CardDto playerOutCard) {
		this.playerOutCard = playerOutCard;
	}

	public void setBancoCards(List<CardDto> bancoCards) {
		this.bancoCards = bancoCards;
	}

	public void setBancoOutCard(CardDto bancoOutCard) {
		this.bancoOutCard = bancoOutCard;
	}

	public List<CardDto> getPlayerCads() {
		return playerCads;
	}

	public CardDto getPlayerOutCard() {
		return playerOutCard;
	}

	public List<CardDto> getBancoCards() {
		return bancoCards;
	}

	public CardDto getBancoOutCard() {
		return bancoOutCard;
	}

	public Integer getWinner() {
		return winner;
	}

	public Integer getPlayerAmt() {
		return playerAmt;
	}

	public Integer getBancoAmt() {
		return bancoAmt;
	}

	public Integer getWinAmount() {
		return winAmount;
	}

	public boolean isExtWine() {
		return extWine;
	}

	
	
}
