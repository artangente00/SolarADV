package com.PlayGroundAdv.Solar.model;

public class PermitCompanyInfoEntityResult {

	private String accountNumber;
	private String serviceAgreement;
	private String existingRate;
	private String newRate;
	private String costPaid;
	private String claimedFederal;
	private String nameDeveloper;
	private String checkApply;
	private String kwhUsage;
	private Boolean authorizatingAdvanced;
	private String contactHomeowner;
	private String scir;
	private String systemOwner;
	private Boolean paceFinanced;
	private String meterAccess;
	private Integer plannedAnnual;
	private Boolean newService;
	private Boolean newSubdivition;
	private Boolean nonProfileStatus;
	private Boolean systemMeetDIH;
	private Boolean jBoxUsedBetween;

	public PermitCompanyInfoEntityResult() {
		super();
	}

	public PermitCompanyInfoEntityResult(String accountNumber, String serviceAgreement, String existingRate,
			String newRate, String costPaid, String claimedFederal, String nameDeveloper, String checkApply,
			String kwhUsage, Boolean authorizatingAdvanced, String contactHomeowner, String scir, String systemOwner,
			Boolean paceFinanced, String meterAccess, Integer plannedAnnual, Boolean newService, Boolean newSubdivition,
			Boolean nonProfileStatus, Boolean systemMeetDIH, Boolean jBoxUsedBetween) {
		super();
		this.accountNumber = accountNumber;
		this.serviceAgreement = serviceAgreement;
		this.existingRate = existingRate;
		this.newRate = newRate;
		this.costPaid = costPaid;
		this.claimedFederal = claimedFederal;
		this.nameDeveloper = nameDeveloper;
		this.checkApply = checkApply;
		this.kwhUsage = kwhUsage;
		this.authorizatingAdvanced = authorizatingAdvanced;
		this.contactHomeowner = contactHomeowner;
		this.scir = scir;
		this.systemOwner = systemOwner;
		this.paceFinanced = paceFinanced;
		this.meterAccess = meterAccess;
		this.plannedAnnual = plannedAnnual;
		this.newService = newService;
		this.newSubdivition = newSubdivition;
		this.nonProfileStatus = nonProfileStatus;
		this.systemMeetDIH = systemMeetDIH;
		this.jBoxUsedBetween = jBoxUsedBetween;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getServiceAgreement() {
		return serviceAgreement;
	}

	public void setServiceAgreement(String serviceAgreement) {
		this.serviceAgreement = serviceAgreement;
	}

	public String getExistingRate() {
		return existingRate;
	}

	public void setExistingRate(String existingRate) {
		this.existingRate = existingRate;
	}

	public String getNewRate() {
		return newRate;
	}

	public void setNewRate(String newRate) {
		this.newRate = newRate;
	}

	public String getCostPaid() {
		return costPaid;
	}

	public void setCostPaid(String costPaid) {
		this.costPaid = costPaid;
	}

	public String getClaimedFederal() {
		return claimedFederal;
	}

	public void setClaimedFederal(String claimedFederal) {
		this.claimedFederal = claimedFederal;
	}

	public String getNameDeveloper() {
		return nameDeveloper;
	}

	public void setNameDeveloper(String nameDeveloper) {
		this.nameDeveloper = nameDeveloper;
	}

	public String getCheckApply() {
		return checkApply;
	}

	public void setCheckApply(String checkApply) {
		this.checkApply = checkApply;
	}

	public String getKwhUsage() {
		return kwhUsage;
	}

	public void setKwhUsage(String kwhUsage) {
		this.kwhUsage = kwhUsage;
	}

	public Boolean getAuthorizatingAdvanced() {
		return authorizatingAdvanced;
	}

	public void setAuthorizatingAdvanced(Boolean authorizatingAdvanced) {
		this.authorizatingAdvanced = authorizatingAdvanced;
	}

	public String getContactHomeowner() {
		return contactHomeowner;
	}

	public void setContactHomeowner(String contactHomeowner) {
		this.contactHomeowner = contactHomeowner;
	}

	public String getScir() {
		return scir;
	}

	public void setScir(String scir) {
		this.scir = scir;
	}

	public String getSystemOwner() {
		return systemOwner;
	}

	public void setSystemOwner(String systemOwner) {
		this.systemOwner = systemOwner;
	}

	public Boolean getPaceFinanced() {
		return paceFinanced;
	}

	public void setPaceFinanced(Boolean paceFinanced) {
		this.paceFinanced = paceFinanced;
	}

	public String getMeterAccess() {
		return meterAccess;
	}

	public void setMeterAccess(String meterAccess) {
		this.meterAccess = meterAccess;
	}

	public Integer getPlannedAnnual() {
		return plannedAnnual;
	}

	public void setPlannedAnnual(Integer plannedAnnual) {
		this.plannedAnnual = plannedAnnual;
	}

	public Boolean getNewService() {
		return newService;
	}

	public void setNewService(Boolean newService) {
		this.newService = newService;
	}

	public Boolean getNewSubdivition() {
		return newSubdivition;
	}

	public void setNewSubdivition(Boolean newSubdivition) {
		this.newSubdivition = newSubdivition;
	}

	public Boolean getNonProfileStatus() {
		return nonProfileStatus;
	}

	public void setNonProfileStatus(Boolean nonProfileStatus) {
		this.nonProfileStatus = nonProfileStatus;
	}

	public Boolean getSystemMeetDIH() {
		return systemMeetDIH;
	}

	public void setSystemMeetDIH(Boolean systemMeetDIH) {
		this.systemMeetDIH = systemMeetDIH;
	}

	@Override
	public String toString() {
		return "PermitCompanyInfoEntityResult [accountNumber=" + accountNumber + ", serviceAgreement="
				+ serviceAgreement + ", existingRate=" + existingRate + ", newRate=" + newRate + ", costPaid="
				+ costPaid + ", claimedFederal=" + claimedFederal + ", nameDeveloper=" + nameDeveloper + ", checkApply="
				+ checkApply + ", kwhUsage=" + kwhUsage + ", authorizatingAdvanced=" + authorizatingAdvanced
				+ ", contactHomeowner=" + contactHomeowner + ", scir=" + scir + ", systemOwner=" + systemOwner
				+ ", paceFinanced=" + paceFinanced + ", meterAccess=" + meterAccess + ", plannedAnnual=" + plannedAnnual
				+ ", newService=" + newService + ", newSubdivition=" + newSubdivition + ", nonProfileStatus="
				+ nonProfileStatus + ", systemMeetDIH=" + systemMeetDIH + ", jBoxUsedBetween=" + jBoxUsedBetween + "]";
	}

	public Boolean getJBoxUsedBetween() {
		return jBoxUsedBetween;
	}

	public void setJBoxUsedBetween(Boolean jBoxUsedBetween) {
		this.jBoxUsedBetween = jBoxUsedBetween;
	}

}
