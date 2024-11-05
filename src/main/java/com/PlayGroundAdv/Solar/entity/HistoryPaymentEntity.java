package com.PlayGroundAdv.Solar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "HistoryPaymentEntity")
public class HistoryPaymentEntity {

	
	@Id
	@SequenceGenerator(name="hibernate_sequence38", sequenceName = "hibernate_sequence38", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence38")  
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	@JoinColumn(name = "ID_BUYER")
	@ManyToOne
	private AuthentificationEntity authentificationEntity;
	
	
	@Column(name="IS_Pay_SUCCESS")
	private boolean isPaySucces;
	
	@Column(name="DATE_PAY")
	private Date datePay;
	
	@Column(name="DATE_PAY_STR")
	private String datePAuStr;
	
	@Column(name="NUM_CARD")
	private String numCard;
	
	
	@Column(name="DESCRIPTION")
	private String discription;


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the permitEntity
	 */
	public PermitEntity getPermitEntity() {
		return permitEntity;
	}


	/**
	 * @param permitEntity the permitEntity to set
	 */
	public void setPermitEntity(PermitEntity permitEntity) {
		this.permitEntity = permitEntity;
	}


	/**
	 * @return the authentificationEntity
	 */
	public AuthentificationEntity getAuthentificationEntity() {
		return authentificationEntity;
	}


	/**
	 * @param authentificationEntity the authentificationEntity to set
	 */
	public void setAuthentificationEntity(AuthentificationEntity authentificationEntity) {
		this.authentificationEntity = authentificationEntity;
	}


	/**
	 * @return the isPaySucces
	 */
	public boolean isPaySucces() {
		return isPaySucces;
	}


	/**
	 * @param isPaySucces the isPaySucces to set
	 */
	public void setPaySucces(boolean isPaySucces) {
		this.isPaySucces = isPaySucces;
	}


	/**
	 * @return the datePay
	 */
	public Date getDatePay() {
		return datePay;
	}


	/**
	 * @param datePay the datePay to set
	 */
	public void setDatePay(Date datePay) {
		this.datePay = datePay;
	}


	/**
	 * @return the datePAuStr
	 */
	public String getDatePAuStr() {
		return datePAuStr;
	}


	/**
	 * @param datePAuStr the datePAuStr to set
	 */
	public void setDatePAuStr(String datePAuStr) {
		this.datePAuStr = datePAuStr;
	}


	/**
	 * @return the numCard
	 */
	public String getNumCard() {
		return numCard;
	}


	/**
	 * @param numCard the numCard to set
	 */
	public void setNumCard(String numCard) {
		this.numCard = numCard;
	}


	/**
	 * @return the discription
	 */
	public String getDiscription() {
		return discription;
	}


	/**
	 * @param discription the discription to set
	 */
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	
	
	
}
