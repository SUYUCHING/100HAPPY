package com.aj.casino2.player.dto;

import com.aj.db.domain.baccart2.Card;

public class CardDto {

	private Integer color;
	private Integer number=0;
	
	

	public static final Integer DEFAULT_CATD_COUNT = 4;

	public Card updateDefaultCatd(Card card, Integer value) throws Exception {

		Integer ttCatd = value * DEFAULT_CATD_COUNT;

		new Card(ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd,
				ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd,
				ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd,
				ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd, ttCatd);

		return card;
	}
	
	
	
	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
