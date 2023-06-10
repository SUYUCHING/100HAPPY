package com.aj.db.domain.log;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.aj.db.domain.user.EntityWallet;
import com.aj.db.domain.user.PlayerWallet;

public class EntityWalletLog implements Serializable{
	
	private Long id;
	private BigDecimal amount;
	private BigDecimal beforeCredit;
	private BigDecimal afterCredit;
	private Integer type;//deposit/withdraw
	private Date createDate;
    private String remark;
    private String actionBy;
    private Integer actionType;//operator,player,system
    private Long actionId;
    private Long refId;
	private Integer refType;
	private Integer creditType;

	private EntityWallet entityWallet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public BigDecimal getAmount() {
		return amount;
	}



	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	public BigDecimal getBeforeCredit() {
		return beforeCredit;
	}



	public void setBeforeCredit(BigDecimal beforeCredit) {
		this.beforeCredit = beforeCredit;
	}



	public BigDecimal getAfterCredit() {
		return afterCredit;
	}



	public void setAfterCredit(BigDecimal afterCredit) {
		this.afterCredit = afterCredit;
	}



	public Integer getType() {
		return type;
	}



	public void setType(Integer type) {
		this.type = type;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public String getRemark() {
		return remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}



	public String getActionBy() {
		return actionBy;
	}



	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}



	public Integer getActionType() {
		return actionType;
	}



	public void setActionType(Integer actionType) {
		this.actionType = actionType;
	}



	public Long getActionId() {
		return actionId;
	}



	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}



	public Long getRefId() {
		return refId;
	}



	public void setRefId(Long refId) {
		this.refId = refId;
	}



	public Integer getRefType() {
		return refType;
	}



	public void setRefType(Integer refType) {
		this.refType = refType;
	}



	public Integer getCreditType() {
		return creditType;
	}



	public void setCreditType(Integer creditType) {
		this.creditType = creditType;
	}

	public EntityWallet getEntityWallet() {
		return entityWallet;
	}

	public void setEntityWallet(EntityWallet entityWallet) {
		this.entityWallet = entityWallet;
	}


	
	
	
	
}
