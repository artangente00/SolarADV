package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RFIConfirmOfSubmitEntity")
public class RFIConfirmOfSubmitEntity implements Serializable {
	
	@Id 
	private Long id;
	
	private boolean submittedAddedToRFI;
	private boolean submitAddedFromContractorRFI;
	
	
	
	
	public RFIConfirmOfSubmitEntity(Long id, boolean submittedAddedToRFI, boolean submitAddedFromContractorRFI) {
		super();
		this.id = id;
		this.submittedAddedToRFI = submittedAddedToRFI;
		this.submitAddedFromContractorRFI = submitAddedFromContractorRFI;
	}
	public RFIConfirmOfSubmitEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RFIConfirmOfSubmitEntity(boolean submittedAddedToRFI, boolean submitAddedFromContractorRFI) {
		super();
		this.submittedAddedToRFI = submittedAddedToRFI;
		this.submitAddedFromContractorRFI = submitAddedFromContractorRFI;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public boolean isSubmittedAddedToRFI() {
		return submittedAddedToRFI;
	}
	public void setSubmittedAddedToRFI(boolean submittedAddedToRFI) {
		this.submittedAddedToRFI = submittedAddedToRFI;
	}
	public boolean isSubmitAddedFromContractorRFI() {
		return submitAddedFromContractorRFI;
	}
	public void setSubmitAddedFromContractorRFI(boolean submitAddedFromContractorRFI) {
		this.submitAddedFromContractorRFI = submitAddedFromContractorRFI;
	}
	
	
	
	

}
