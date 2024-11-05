package com.PlayGroundAdv.Solar.model;

public class PermitCompanyInfoEntityResultPrime {

	private Long id;
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
	private String customerParticipated;
	private String peaceFinancedByEntityOther;
	// variable checkbox
	private String contactType;
	private Boolean meterDisco;
	private Boolean meterBuilding;
	private Boolean undertrainedAnimal;
	private Boolean otherSystem;
	private String otherSystemText;
	private Boolean jBoxUsedBetween;
	// new varible hidden
	private String developersName;
	private String developmentName;
	private String interconnectionType;
	private String peaceFinancedByEntity;
	private String namePartyReceivData;
	private String textOtherRate;
	private String textOtherNewRate;
	// CR_003
	private String typeCustomer;
	private String typeCustomerOther;
	private String typeCustomerOtherText;
	private String newRateCommercial;
	private String otherNewRateCommercial;
	private String snemApplication;
	private String thisIsNewService;
	private String developerrsNam;
	private String developersNameSecond;
	private String theProjectIsLocated;
	private String developemName;
	private String projectIs;
	private String otherProjectIs;
	private String projectWasPace;
	private String choosePaceFinanced;
	private String textOtherChoosePace;
	private String electriccvehichule1;
	private Boolean electricvehichule2;
	private Boolean electricvehichule3;
	private Boolean electricvehichule4;
	private Boolean electricvehichuleOther;
	private String otherElectricVe;
	private String applicationType;
	private String theAcDisconnectIsMoreThan;
	private String coverageamount;
	private String insuringcompanyName;
	private String expectedDate;
	private Boolean oneOrMoreOfTheAss;
	private Boolean theLocalPermitting;
	private Boolean requestPGToshutDown;
	private Boolean requestPGaEToInstalNewS;
	private String nameOfDevelopersLease;
	private Integer clamedfederalIncomeTax;
	private String whichProgram;
	private String whatRuleOrRules;
	private Boolean deEnergizingOfTheService;
	private String whatDayOfTheWeek;
	private String whatTimeOfDay;
	private String willYouNeedToObtain;
	private String describeThePointInt;

	private Integer theScirOfTheMain;
	private String policy;
	private String howManyHours;
	private String developerrrsName;
	private String pGaEPersonnelWilleNeedStr;
	private String pGaEPersonnelWilleNeed;
	private String mayOurRepresentativesContact;
	private String iAuthorizeAdvanced;
	private Boolean meterOrACDisconnectInBuilding;
	private Boolean unrestrainedAnimal;
	private Boolean checkTheApplicableOther;
	private String checkApplicableBoxesOther;
	private Boolean checkIfTheProposedSystemProduce;
	private String performanceMonitAndRep;
	private String anExistingSolarOrWind;
	private String clamedfederalIncomeTaxStr;
	private String listAnyVariations;
	private String textOtherContractType;
	private String newRateOthers;
	private String newRateOthersText;
	private String uploadCommentsUtility;

	private String uploadFileExisting;
	private String uploadFileJustification;
	private String uploadFileListAdreess;
	private String uploadFileSwitchgear;
	private String uploadFileInterconnection;

//	CR-2469 Update Projects Save Logic

	private Boolean opened;
	private Long openedBy;
	private Boolean hasEditAccess;

	public PermitCompanyInfoEntityResultPrime() {
		super();
	}

	public PermitCompanyInfoEntityResultPrime(Long id, String accountNumber, String serviceAgreement,
			String existingRate, String newRate, String costPaid, String claimedFederal, String nameDeveloper,
			String checkApply, String kwhUsage, Boolean authorizatingAdvanced, String contactHomeowner, String scir,
			String systemOwner, Boolean paceFinanced, String meterAccess, Integer plannedAnnual, Boolean newService,
			Boolean newSubdivition, Boolean nonProfileStatus, Boolean systemMeetDIH, String contactType,
			Boolean meterDisco, Boolean meterBuilding, Boolean undertrainedAnimal, Boolean otherSystem,
			String otherSystemText, Boolean jBoxUsedBetween, String developersName, String developmentName,
			String interconnectionType, String peaceFinancedByEntity, String namePartyReceivData, String textOtherRate,
			String textOtherNewRate, String customerParticipated, String peaceFinancedByEntityOther,
			String typeCustomer, String typeCustomerOther, String typeCustomerOtherText, String newRateCommercial,
			String otherNewRateCommercial, String snemApplication, String thisIsNewService, String developerrsNam,
			String developersNameSecond, String theProjectIsLocated, String developemName, String projectIs,
			String otherProjectIs, String projectWasPace, String choosePaceFinanced, String textOtherChoosePace,
			String electriccvehichule1, Boolean electricvehichule2, Boolean electricvehichule3,
			Boolean electricvehichule4, Boolean electricvehichuleOther, String otherElectricVe, String applicationType,
			String theAcDisconnectIsMoreThan, String coverageamount, String insuringcompanyName, String expectedDate,
			Boolean oneOrMoreOfTheAss, Boolean theLocalPermitting, Boolean requestPGToshutDown,
			Boolean requestPGaEToInstalNewS, String nameOfDevelopersLease, Integer clamedfederalIncomeTax,
			String whichProgram, String whatRuleOrRules, Boolean deEnergizingOfTheService, String whatDayOfTheWeek,
			String whatTimeOfDay, String willYouNeedToObtain, String describeThePointInt, Integer theScirOfTheMain,
			String policy, String howManyHours, String developerrrsName, String pGaEPersonnelWilleNeed,
			String mayOurRepresentativesContact, String iAuthorizeAdvanced, Boolean meterOrACDisconnectInBuilding,
			Boolean unrestrainedAnimal, Boolean checkTheApplicableOther, String checkApplicableBoxesOther,
			Boolean checkIfTheProposedSystemProduce, String performanceMonitAndRep, String anExistingSolarOrWind,
			String clamedfederalIncomeTaxStr, String listAnyVariations, String textOtherContractType,
			String newRateOthers, String newRateOthersText, String uploadCommentsUtility, String uploadFileExisting,
			String uploadFileJustification, String uploadFileListAdreess, String uploadFileSwitchgear,
			String uploadFileInterconnection, Boolean opened, Long openedBy) {

		super();
		this.id = id;
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
		this.contactType = contactType;
		this.meterDisco = meterDisco;
		this.meterBuilding = meterBuilding;
		this.undertrainedAnimal = undertrainedAnimal;
		this.otherSystem = otherSystem;
		this.otherSystemText = otherSystemText;
		this.jBoxUsedBetween = jBoxUsedBetween;
		this.developersName = developersName;
		this.developmentName = developmentName;
		this.interconnectionType = interconnectionType;
		this.peaceFinancedByEntity = peaceFinancedByEntity;
		this.namePartyReceivData = namePartyReceivData;
		this.textOtherRate = textOtherRate;
		this.textOtherNewRate = textOtherNewRate;
		this.customerParticipated = customerParticipated;
		this.peaceFinancedByEntityOther = peaceFinancedByEntityOther;
		this.typeCustomer = typeCustomer;
		this.typeCustomerOther = typeCustomerOther;
		this.typeCustomerOtherText = typeCustomerOtherText;
		this.newRateCommercial = newRateCommercial;
		this.otherNewRateCommercial = otherNewRateCommercial;
		this.snemApplication = snemApplication;
		this.thisIsNewService = thisIsNewService;
		this.developerrsNam = developerrsNam;
		this.developersNameSecond = developersNameSecond;
		this.theProjectIsLocated = theProjectIsLocated;
		this.developemName = developemName;
		this.projectIs = projectIs;
		this.otherProjectIs = otherProjectIs;
		this.projectWasPace = projectWasPace;
		this.choosePaceFinanced = choosePaceFinanced;
		this.textOtherChoosePace = textOtherChoosePace;
		this.electriccvehichule1 = electriccvehichule1;
		this.electricvehichule2 = electricvehichule2;
		this.electricvehichule3 = electricvehichule3;
		this.electricvehichule4 = electricvehichule4;
		this.electricvehichuleOther = electricvehichuleOther;
		this.otherElectricVe = otherElectricVe;
		this.applicationType = applicationType;
		this.theAcDisconnectIsMoreThan = theAcDisconnectIsMoreThan;
		this.coverageamount = coverageamount;
		this.insuringcompanyName = insuringcompanyName;
		this.expectedDate = expectedDate;
		this.oneOrMoreOfTheAss = oneOrMoreOfTheAss;
		this.theLocalPermitting = theLocalPermitting;
		this.requestPGToshutDown = requestPGToshutDown;
		this.requestPGaEToInstalNewS = requestPGaEToInstalNewS;
		this.nameOfDevelopersLease = nameOfDevelopersLease;
		this.clamedfederalIncomeTax = clamedfederalIncomeTax;
		this.whichProgram = whichProgram;
		this.whatRuleOrRules = whatRuleOrRules;
		this.deEnergizingOfTheService = deEnergizingOfTheService;
		this.whatDayOfTheWeek = whatDayOfTheWeek;
		this.whatTimeOfDay = whatTimeOfDay;
		this.willYouNeedToObtain = willYouNeedToObtain;
		this.describeThePointInt = describeThePointInt;
		this.theScirOfTheMain = theScirOfTheMain;
		this.policy = policy;
		this.howManyHours = howManyHours;
		this.developerrrsName = developerrrsName;
		this.pGaEPersonnelWilleNeedStr = pGaEPersonnelWilleNeed;
		this.mayOurRepresentativesContact = mayOurRepresentativesContact;
		this.iAuthorizeAdvanced = iAuthorizeAdvanced;
		this.meterOrACDisconnectInBuilding = meterOrACDisconnectInBuilding;
		this.unrestrainedAnimal = unrestrainedAnimal;
		this.checkTheApplicableOther = checkTheApplicableOther;
		this.checkApplicableBoxesOther = checkApplicableBoxesOther;
		this.checkIfTheProposedSystemProduce = checkIfTheProposedSystemProduce;
		this.performanceMonitAndRep = performanceMonitAndRep;
		this.anExistingSolarOrWind = anExistingSolarOrWind;
		this.clamedfederalIncomeTaxStr = clamedfederalIncomeTaxStr;
		this.listAnyVariations = listAnyVariations;
		this.textOtherContractType = textOtherContractType;
		this.newRateOthers = newRateOthers;
		this.newRateOthersText = newRateOthersText;
		this.uploadCommentsUtility = uploadCommentsUtility;
		this.uploadFileExisting = uploadFileExisting;
		this.uploadFileJustification = uploadFileJustification;
		this.uploadFileListAdreess = uploadFileListAdreess;
		this.uploadFileSwitchgear = uploadFileSwitchgear;
		this.uploadFileInterconnection = uploadFileInterconnection;
		this.opened = opened;
		this.openedBy = openedBy;
	}

	public PermitCompanyInfoEntityResultPrime(Long id, String newRate, String costPaid, String kwhUsage,
			String contactHomeowner, Integer plannedAnnual, Boolean newService, String contactType, Boolean meterDisco,
			Boolean meterBuilding, Boolean otherSystem, String otherSystemText, String interconnectionType,
			String textOtherNewRate, String typeCustomer, String typeCustomerOther, String typeCustomerOtherText,
			String newRateCommercial, String otherNewRateCommercial, String snemApplication, String thisIsNewService,
			String developersNameSecond, String theProjectIsLocated, String developemName, String projectIs,
			String projectWasPace, String choosePaceFinanced, String textOtherChoosePace, String electriccvehichule1,
			String otherElectricVe, String applicationType, String theAcDisconnectIsMoreThan, String coverageamount,
			String insuringcompanyName, String expectedDate, Boolean oneOrMoreOfTheAss, Boolean theLocalPermitting,
			Boolean requestPGToshutDown, Boolean requestPGaEToInstalNewS, String nameOfDevelopersLease,
			String whichProgram, String whatRuleOrRules, Boolean deEnergizingOfTheService, String whatDayOfTheWeek,
			String whatTimeOfDay, String willYouNeedToObtain, String describeThePointInt, Integer theScirOfTheMain,
			String policy, String howManyHours, String pGaEPersonnelWilleNeed, String iAuthorizeAdvanced,
			Boolean unrestrainedAnimal, Boolean checkIfTheProposedSystemProduce, String performanceMonitAndRep,
			String anExistingSolarOrWind, String clamedfederalIncomeTaxStr, String listAnyVariations,
			String textOtherContractType, String newRateOthers, String uploadCommentsUtility, String uploadFileExisting,
			String uploadFileJustification, String uploadFileListAdreess, String uploadFileSwitchgear,
			String uploadFileInterconnection, Boolean jBoxUsedBetween) {
		super();
		this.id = id;
		this.newRate = newRate;
		this.costPaid = costPaid;
		this.kwhUsage = kwhUsage;
		this.contactHomeowner = contactHomeowner;
		this.plannedAnnual = plannedAnnual;
		this.newService = newService;
		this.contactType = contactType;
		this.meterDisco = meterDisco;
		this.meterBuilding = meterBuilding;
		this.otherSystem = otherSystem;
		this.otherSystemText = otherSystemText;
		this.interconnectionType = interconnectionType;
		this.textOtherNewRate = textOtherNewRate;
		this.typeCustomer = typeCustomer;
		this.typeCustomerOther = typeCustomerOther;
		this.typeCustomerOtherText = typeCustomerOtherText;
		this.newRateCommercial = newRateCommercial;
		this.otherNewRateCommercial = otherNewRateCommercial;
		this.snemApplication = snemApplication;
		this.thisIsNewService = thisIsNewService;
		this.developersNameSecond = developersNameSecond;
		this.theProjectIsLocated = theProjectIsLocated;
		this.developemName = developemName;
		this.projectIs = projectIs;
		this.projectWasPace = projectWasPace;
		this.choosePaceFinanced = choosePaceFinanced;
		this.textOtherChoosePace = textOtherChoosePace;
		this.electriccvehichule1 = electriccvehichule1;
		this.otherElectricVe = otherElectricVe;
		this.applicationType = applicationType;
		this.theAcDisconnectIsMoreThan = theAcDisconnectIsMoreThan;
		this.coverageamount = coverageamount;
		this.insuringcompanyName = insuringcompanyName;
		this.expectedDate = expectedDate;
		this.oneOrMoreOfTheAss = oneOrMoreOfTheAss;
		this.theLocalPermitting = theLocalPermitting;
		this.requestPGToshutDown = requestPGToshutDown;
		this.requestPGaEToInstalNewS = requestPGaEToInstalNewS;
		this.nameOfDevelopersLease = nameOfDevelopersLease;
		this.whichProgram = whichProgram;
		this.whatRuleOrRules = whatRuleOrRules;
		this.deEnergizingOfTheService = deEnergizingOfTheService;
		this.whatDayOfTheWeek = whatDayOfTheWeek;
		this.whatTimeOfDay = whatTimeOfDay;
		this.willYouNeedToObtain = willYouNeedToObtain;
		this.describeThePointInt = describeThePointInt;
		this.theScirOfTheMain = theScirOfTheMain;
		this.policy = policy;
		this.howManyHours = howManyHours;
		this.pGaEPersonnelWilleNeed = pGaEPersonnelWilleNeed;
		this.iAuthorizeAdvanced = iAuthorizeAdvanced;
		this.unrestrainedAnimal = unrestrainedAnimal;
		this.checkIfTheProposedSystemProduce = checkIfTheProposedSystemProduce;
		this.performanceMonitAndRep = performanceMonitAndRep;
		this.anExistingSolarOrWind = anExistingSolarOrWind;
		this.clamedfederalIncomeTaxStr = clamedfederalIncomeTaxStr;
		this.listAnyVariations = listAnyVariations;
		this.textOtherContractType = textOtherContractType;
		this.newRateOthers = newRateOthers;
		this.uploadCommentsUtility = uploadCommentsUtility;
		this.uploadFileExisting = uploadFileExisting;
		this.uploadFileJustification = uploadFileJustification;
		this.uploadFileListAdreess = uploadFileListAdreess;
		this.uploadFileSwitchgear = uploadFileSwitchgear;
		this.uploadFileInterconnection = uploadFileInterconnection;
		this.jBoxUsedBetween = jBoxUsedBetween;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setClaimedFederal(String claimedFederalString) {
		this.claimedFederal = claimedFederalString;
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

	public String getCustomerParticipated() {
		return customerParticipated;
	}

	public void setCustomerParticipated(String customerParticipated) {
		this.customerParticipated = customerParticipated;
	}

	public String getPeaceFinancedByEntityOther() {
		return peaceFinancedByEntityOther;
	}

	public void setPeaceFinancedByEntityOther(String peaceFinancedByEntityOther) {
		this.peaceFinancedByEntityOther = peaceFinancedByEntityOther;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public Boolean getMeterDisco() {
		return meterDisco;
	}

	public void setMeterDisco(Boolean meterDisco) {
		this.meterDisco = meterDisco;
	}

	public Boolean getMeterBuilding() {
		return meterBuilding;
	}

	public void setMeterBuilding(Boolean meterBuilding) {
		this.meterBuilding = meterBuilding;
	}

	public Boolean getUndertrainedAnimal() {
		return undertrainedAnimal;
	}

	public void setUndertrainedAnimal(Boolean undertrainedAnimal) {
		this.undertrainedAnimal = undertrainedAnimal;
	}

	public Boolean getOtherSystem() {
		return otherSystem;
	}

	public void setOtherSystem(Boolean otherSystem) {
		this.otherSystem = otherSystem;
	}

	public String getOtherSystemText() {
		return otherSystemText;
	}

	public void setOtherSystemText(String otherSystemText) {
		this.otherSystemText = otherSystemText;
	}

	public Boolean getjBoxUsedBetween() {
		return jBoxUsedBetween;
	}

	public void setjBoxUsedBetween(Boolean jBoxUsedBetween) {
		this.jBoxUsedBetween = jBoxUsedBetween;
	}

	public String getDevelopersName() {
		return developersName;
	}

	public void setDevelopersName(String developersName) {
		this.developersName = developersName;
	}

	public String getDevelopmentName() {
		return developmentName;
	}

	public void setDevelopmentName(String developmentName) {
		this.developmentName = developmentName;
	}

	public String getInterconnectionType() {
		return interconnectionType;
	}

	public void setInterconnectionType(String interconnectionType) {
		this.interconnectionType = interconnectionType;
	}

	public String getPeaceFinancedByEntity() {
		return peaceFinancedByEntity;
	}

	public void setPeaceFinancedByEntity(String peaceFinancedByEntity) {
		this.peaceFinancedByEntity = peaceFinancedByEntity;
	}

	public String getNamePartyReceivData() {
		return namePartyReceivData;
	}

	public void setNamePartyReceivData(String namePartyReceivData) {
		this.namePartyReceivData = namePartyReceivData;
	}

	public String getTextOtherRate() {
		return textOtherRate;
	}

	public void setTextOtherRate(String textOtherRate) {
		this.textOtherRate = textOtherRate;
	}

	public String getTextOtherNewRate() {
		return textOtherNewRate;
	}

	public void setTextOtherNewRate(String textOtherNewRate) {
		this.textOtherNewRate = textOtherNewRate;
	}

	public String getTypeCustomer() {
		return typeCustomer;
	}

	public void setTypeCustomer(String typeCustomer) {
		this.typeCustomer = typeCustomer;
	}

	public String getTypeCustomerOther() {
		return typeCustomerOther;
	}

	public void setTypeCustomerOther(String typeCustomerOther) {
		this.typeCustomerOther = typeCustomerOther;
	}

	public String getTypeCustomerOtherText() {
		return typeCustomerOtherText;
	}

	public void setTypeCustomerOtherText(String typeCustomerOtherText) {
		this.typeCustomerOtherText = typeCustomerOtherText;
	}

	public String getNewRateCommercial() {
		return newRateCommercial;
	}

	public void setNewRateCommercial(String newRateCommercial) {
		this.newRateCommercial = newRateCommercial;
	}

	public String getOtherNewRateCommercial() {
		return otherNewRateCommercial;
	}

	public void setOtherNewRateCommercial(String otherNewRateCommercial) {
		this.otherNewRateCommercial = otherNewRateCommercial;
	}

	public String getSnemApplication() {
		return snemApplication;
	}

	public void setSnemApplication(String snemApplication) {
		this.snemApplication = snemApplication;
	}

	public String getThisIsNewService() {
		return thisIsNewService;
	}

	public void setThisIsNewService(String thisIsNewService) {
		this.thisIsNewService = thisIsNewService;
	}

	public String getDeveloperrsNam() {
		return developerrsNam;
	}

	public void setDeveloperrsNam(String developerrsNam) {
		this.developerrsNam = developerrsNam;
	}

	public String getDevelopersNameSecond() {
		return developersNameSecond;
	}

	public void setDevelopersNameSecond(String developersNameSecond) {
		this.developersNameSecond = developersNameSecond;
	}

	public String getTheProjectIsLocated() {
		return theProjectIsLocated;
	}

	public void setTheProjectIsLocated(String theProjectIsLocated) {
		this.theProjectIsLocated = theProjectIsLocated;
	}

	public String getDevelopemName() {
		return developemName;
	}

	public void setDevelopemName(String developemName) {
		this.developemName = developemName;
	}

	public String getProjectIs() {
		return projectIs;
	}

	public void setProjectIs(String projectIs) {
		this.projectIs = projectIs;
	}

	public String getOtherProjectIs() {
		return otherProjectIs;
	}

	public void setOtherProjectIs(String otherProjectIs) {
		this.otherProjectIs = otherProjectIs;
	}

	public String getProjectWasPace() {
		return projectWasPace;
	}

	public void setProjectWasPace(String projectWasPace) {
		this.projectWasPace = projectWasPace;
	}

	public String getChoosePaceFinanced() {
		return choosePaceFinanced;
	}

	public void setChoosePaceFinanced(String choosePaceFinanced) {
		this.choosePaceFinanced = choosePaceFinanced;
	}

	public String getTextOtherChoosePace() {
		return textOtherChoosePace;
	}

	public void setTextOtherChoosePace(String textOtherChoosePace) {
		this.textOtherChoosePace = textOtherChoosePace;
	}

	public String getElectriccvehichule1() {
		return electriccvehichule1;
	}

	public void setElectriccvehichule1(String electriccvehichule1) {
		this.electriccvehichule1 = electriccvehichule1;
	}

	public Boolean getElectricvehichule2() {
		return electricvehichule2;
	}

	public void setElectricvehichule2(Boolean electricvehichule2) {
		this.electricvehichule2 = electricvehichule2;
	}

	public Boolean getElectricvehichule3() {
		return electricvehichule3;
	}

	public void setElectricvehichule3(Boolean electricvehichule3) {
		this.electricvehichule3 = electricvehichule3;
	}

	public Boolean getElectricvehichule4() {
		return electricvehichule4;
	}

	public void setElectricvehichule4(Boolean electricvehichule4) {
		this.electricvehichule4 = electricvehichule4;
	}

	public Boolean getElectricvehichuleOther() {
		return electricvehichuleOther;
	}

	public void setElectricvehichuleOther(Boolean electricvehichuleOther) {
		this.electricvehichuleOther = electricvehichuleOther;
	}

	public String getOtherElectricVe() {
		return otherElectricVe;
	}

	public void setOtherElectricVe(String otherElectricVe) {
		this.otherElectricVe = otherElectricVe;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public String getTheAcDisconnectIsMoreThan() {
		return theAcDisconnectIsMoreThan;
	}

	public void setTheAcDisconnectIsMoreThan(String theAcDisconnectIsMoreThan) {
		this.theAcDisconnectIsMoreThan = theAcDisconnectIsMoreThan;
	}

	public String getCoverageamount() {
		return coverageamount;
	}

	public void setCoverageamount(String coverageamount) {
		this.coverageamount = coverageamount;
	}

	public String getInsuringcompanyName() {
		return insuringcompanyName;
	}

	public void setInsuringcompanyName(String insuringcompanyName) {
		this.insuringcompanyName = insuringcompanyName;
	}

	public String getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(String expectedDate) {
		this.expectedDate = expectedDate;
	}

	public Boolean getOneOrMoreOfTheAss() {
		return oneOrMoreOfTheAss;
	}

	public void setOneOrMoreOfTheAss(Boolean oneOrMoreOfTheAss) {
		this.oneOrMoreOfTheAss = oneOrMoreOfTheAss;
	}

	public Boolean getTheLocalPermitting() {
		return theLocalPermitting;
	}

	public void setTheLocalPermitting(Boolean theLocalPermitting) {
		this.theLocalPermitting = theLocalPermitting;
	}

	public Boolean getRequestPGToshutDown() {
		return requestPGToshutDown;
	}

	public void setRequestPGToshutDown(Boolean requestPGToshutDown) {
		this.requestPGToshutDown = requestPGToshutDown;
	}

	public Boolean getRequestPGaEToInstalNewS() {
		return requestPGaEToInstalNewS;
	}

	public void setRequestPGaEToInstalNewS(Boolean requestPGaEToInstalNewS) {
		this.requestPGaEToInstalNewS = requestPGaEToInstalNewS;
	}

	public String getNameOfDevelopersLease() {
		return nameOfDevelopersLease;
	}

	public void setNameOfDevelopersLease(String nameOfDevelopersLease) {
		this.nameOfDevelopersLease = nameOfDevelopersLease;
	}

	public Integer getClamedfederalIncomeTax() {
		return clamedfederalIncomeTax;
	}

	public void setClamedfederalIncomeTax(Integer clamedfederalIncomeTax) {
		this.clamedfederalIncomeTax = clamedfederalIncomeTax;
	}

	public String getWhichProgram() {
		return whichProgram;
	}

	public void setWhichProgram(String whichProgram) {
		this.whichProgram = whichProgram;
	}

	public String getWhatRuleOrRules() {
		return whatRuleOrRules;
	}

	public void setWhatRuleOrRules(String whatRuleOrRules) {
		this.whatRuleOrRules = whatRuleOrRules;
	}

	public Boolean getDeEnergizingOfTheService() {
		return deEnergizingOfTheService;
	}

	public void setDeEnergizingOfTheService(Boolean deEnergizingOfTheService) {
		this.deEnergizingOfTheService = deEnergizingOfTheService;
	}

	public String getWhatDayOfTheWeek() {
		return whatDayOfTheWeek;
	}

	public void setWhatDayOfTheWeek(String whatDayOfTheWeek) {
		this.whatDayOfTheWeek = whatDayOfTheWeek;
	}

	public String getWhatTimeOfDay() {
		return whatTimeOfDay;
	}

	public void setWhatTimeOfDay(String whatTimeOfDay) {
		this.whatTimeOfDay = whatTimeOfDay;
	}

	public String getWillYouNeedToObtain() {
		return willYouNeedToObtain;
	}

	public void setWillYouNeedToObtain(String willYouNeedToObtain) {
		this.willYouNeedToObtain = willYouNeedToObtain;
	}

	public String getDescribeThePointInt() {
		return describeThePointInt;
	}

	public void setDescribeThePointInt(String describeThePointInt) {
		this.describeThePointInt = describeThePointInt;
	}

	public Integer getTheScirOfTheMain() {
		return theScirOfTheMain;
	}

	public void setTheScirOfTheMain(Integer theScirOfTheMain) {
		this.theScirOfTheMain = theScirOfTheMain;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public String getHowManyHours() {
		return howManyHours;
	}

	public void setHowManyHours(String howManyHours) {
		this.howManyHours = howManyHours;
	}

	public String getDeveloperrrsName() {
		return developerrrsName;
	}

	public void setDeveloperrrsName(String developerrrsName) {
		this.developerrrsName = developerrrsName;
	}

	public String getpGaEPersonnelWilleNeedStr() {
		return pGaEPersonnelWilleNeedStr;
	}

	public void setpGaEPersonnelWilleNeedStr(String pGaEPersonnelWilleNeedStr) {
		this.pGaEPersonnelWilleNeedStr = pGaEPersonnelWilleNeedStr;
	}

	public String getpGaEPersonnelWilleNeed() {
		return pGaEPersonnelWilleNeed;
	}

	public void setpGaEPersonnelWilleNeed(String pGaEPersonnelWilleNeed) {
		this.pGaEPersonnelWilleNeed = pGaEPersonnelWilleNeed;
	}

	public String getMayOurRepresentativesContact() {
		return mayOurRepresentativesContact;
	}

	public void setMayOurRepresentativesContact(String mayOurRepresentativesContact) {
		this.mayOurRepresentativesContact = mayOurRepresentativesContact;
	}

	public String getiAuthorizeAdvanced() {
		return iAuthorizeAdvanced;
	}

	public void setiAuthorizeAdvanced(String iAuthorizeAdvanced) {
		this.iAuthorizeAdvanced = iAuthorizeAdvanced;
	}

	public Boolean getMeterOrACDisconnectInBuilding() {
		return meterOrACDisconnectInBuilding;
	}

	public void setMeterOrACDisconnectInBuilding(Boolean meterOrACDisconnectInBuilding) {
		this.meterOrACDisconnectInBuilding = meterOrACDisconnectInBuilding;
	}

	public Boolean getUnrestrainedAnimal() {
		return unrestrainedAnimal;
	}

	public void setUnrestrainedAnimal(Boolean unrestrainedAnimal) {
		this.unrestrainedAnimal = unrestrainedAnimal;
	}

	public Boolean getCheckTheApplicableOther() {
		return checkTheApplicableOther;
	}

	public void setCheckTheApplicableOther(Boolean checkTheApplicableOther) {
		this.checkTheApplicableOther = checkTheApplicableOther;
	}

	public String getCheckApplicableBoxesOther() {
		return checkApplicableBoxesOther;
	}

	public void setCheckApplicableBoxesOther(String checkApplicableBoxesOther) {
		this.checkApplicableBoxesOther = checkApplicableBoxesOther;
	}

	public Boolean getCheckIfTheProposedSystemProduce() {
		return checkIfTheProposedSystemProduce;
	}

	public void setCheckIfTheProposedSystemProduce(Boolean checkIfTheProposedSystemProduce) {
		this.checkIfTheProposedSystemProduce = checkIfTheProposedSystemProduce;
	}

	public String getPerformanceMonitAndRep() {
		return performanceMonitAndRep;
	}

	public void setPerformanceMonitAndRep(String performanceMonitAndRep) {
		this.performanceMonitAndRep = performanceMonitAndRep;
	}

	public String getAnExistingSolarOrWind() {
		return anExistingSolarOrWind;
	}

	public void setAnExistingSolarOrWind(String anExistingSolarOrWind) {
		this.anExistingSolarOrWind = anExistingSolarOrWind;
	}

	public String getClamedfederalIncomeTaxStr() {
		return clamedfederalIncomeTaxStr;
	}

	public void setClamedfederalIncomeTaxStr(String clamedfederalIncomeTaxStr) {
		this.clamedfederalIncomeTaxStr = clamedfederalIncomeTaxStr;
	}

	public String getListAnyVariations() {
		return listAnyVariations;
	}

	public void setListAnyVariations(String listAnyVariations) {
		this.listAnyVariations = listAnyVariations;
	}

	public String getTextOtherContractType() {
		return textOtherContractType;
	}

	public void setTextOtherContractType(String textOtherContractType) {
		this.textOtherContractType = textOtherContractType;
	}

	public String getNewRateOthers() {
		return newRateOthers;
	}

	public void setNewRateOthers(String newRateOthers) {
		this.newRateOthers = newRateOthers;
	}

	public String getNewRateOthersText() {
		return newRateOthersText;
	}

	public void setNewRateOthersText(String newRateOthersText) {
		this.newRateOthersText = newRateOthersText;
	}

	public String getUploadCommentsUtility() {
		return uploadCommentsUtility;
	}

	public void setUploadCommentsUtility(String uploadCommentsUtility) {
		this.uploadCommentsUtility = uploadCommentsUtility;
	}

	public String getUploadFileExisting() {
		return uploadFileExisting;
	}

	public void setUploadFileExisting(String uploadFileExisting) {
		this.uploadFileExisting = uploadFileExisting;
	}

	public String getUploadFileJustification() {
		return uploadFileJustification;
	}

	public void setUploadFileJustification(String uploadFileJustification) {
		this.uploadFileJustification = uploadFileJustification;
	}

	public String getUploadFileListAdreess() {
		return uploadFileListAdreess;
	}

	public void setUploadFileListAdreess(String uploadFileListAdreess) {
		this.uploadFileListAdreess = uploadFileListAdreess;
	}

	public String getUploadFileSwitchgear() {
		return uploadFileSwitchgear;
	}

	public void setUploadFileSwitchgear(String uploadFileSwitchgear) {
		this.uploadFileSwitchgear = uploadFileSwitchgear;
	}

	public String getUploadFileInterconnection() {
		return uploadFileInterconnection;
	}

	public void setUploadFileInterconnection(String uploadFileInterconnection) {
		this.uploadFileInterconnection = uploadFileInterconnection;
	}

	public Boolean getOpened() {
		return opened;
	}

	public void setOpened(Boolean opened) {
		this.opened = opened;
	}

	public Long getOpenedBy() {
		return openedBy;
	}

	public void setOpenedBy(Long openedBy) {
		this.openedBy = openedBy;
	}

	public Boolean getHasEditAccess() {
		return hasEditAccess;
	}

	public void setHasEditAccess(Boolean hasEditAccess) {
		this.hasEditAccess = hasEditAccess;
	}

}
