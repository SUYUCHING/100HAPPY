package com.aj.casino2.player.afterLogin.game.dto;

public class GameDto {
	
	private PlayerCardDto player;
	private BancoCardDto banco;
	
	
	public PlayerCardDto getPlayer() {
		return player;
	}
	public void setPlayer(PlayerCardDto player) {
		this.player = player;
	}
	public BancoCardDto getBanco() {
		return banco;
	}
	public void setBanco(BancoCardDto banco) {
		this.banco = banco;
	}
	
	
	
}
