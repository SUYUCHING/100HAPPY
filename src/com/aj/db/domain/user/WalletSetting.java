package com.aj.db.domain.user;

import java.io.Serializable;
import java.math.BigDecimal;

public class WalletSetting implements Serializable{
	
	private Long id;
	private Integer creditType;
	private Integer convertCreditType;
	private BigDecimal rate;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCreditType() {
		return creditType;
	}
	public void setCreditType(Integer creditType) {
		this.creditType = creditType;
	}
	public Integer getConvertCreditType() {
		return convertCreditType;
	}
	public void setConvertCreditType(Integer convertCreditType) {
		this.convertCreditType = convertCreditType;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	
	
	

}
