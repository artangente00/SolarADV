package com.PlayGroundAdv.Solar.model;

import java.util.Date;


public class SiteSurveyResult {

	private Long id;
	private String homeOwnName;
	private Long createdBy;
	private Long portalProject;
	private String status;
	private Boolean hasProject;
	private Boolean isCanceled;
	private Boolean submitted;

	///// ------------- action dates -----------/////

	private Date creationDate;
	private Date lastUpdatedDate;
	private Date dateOfSubmit;

	///// ------------- site information -----------/////

	private String basicTypeOfSystem;
	private Boolean hasProjectSiteImage;
	private String contactName;
	private String streetAddress;
	private String addressLine2;
	private String city;
	private String state;
	private String zIP;
	private String mainContactPhone ;
	private String otherPhone ;
	private String emailAddress ;
	private String hoa;
	private String permittingAuthority;
	private String legalOwnerName;
	private String parcelNumber;
	private String roofMaterialType;
	private String widthSeams;
	private String riskCategory;
	private String otherRiskCategory;
	private String buildingOccupancy;
	private String otherBuildingOccupancy;
	private String numberOfStories;
	private Integer meanHeight;
	private String otherTallStructure;
	private String projectEquipmentStagingLocation;
	private String ownerPreferredRoofAccessLocation;
	private String accessIssuesWith_MeterOrProposedACDisco;
	private Boolean utilityMeterOrProposedACDisco;
	private Boolean unrestrainedAnimal;
	private Boolean otheraccessissue;
	private String describeAccessIssues;
	private String contactpersonforutility;
	private String othercontactperson;
	private String contactphone;
	private String othercontactphone;
	private Boolean hasImageCapturesatRafterTrusses;
	private String imageOfSiteInformationRafter;
	private Boolean roofMounted;
	private Boolean groundMounted;
	private Boolean carportMounted;
	private Boolean otherMounted;
	private String textOther;

	///// ------------- existing main panel -----------/////

	private Boolean centerFed;
	private String mainPanelMake;
	private String mainPanelModel;
	private Boolean upgradingMainServicePanel;
	private String voltage;
	private String otherVoltage;
	private String mspbusbarRating;
	private String otherMSPBusbarRating;
	private String mSPMainBreakerRating;
	private String otherMSPMainBreakerRating;
	private String openBreakerSlots;
	private String sCIR;
	private String mainBreakerFeedGauge;
	private String otherMainBreakerFeedGauge;
	private String utilityFeederGauge;
	private Boolean copperWiresSameAsMainContact;
	private Boolean copperWiresDifferentNumber;
	private Boolean aluminumWiresSameAsMainContact;
	private Boolean aluminumWiresDifferentNumber;
	private String groundAccessible;
	private String undergroundOrOverheadFeed;
	private Boolean hasImageAtMSP;

	private String imageOfExistingMainPanel;
//
	private String subPanelBreakerOCPD;
	///// ------------- existing subpanel -----------/////

	private Boolean existingSubpanel;
	private Boolean tieInPOCIsAtSubpanel;
	private String subpanelMake;
	private String subpanelModel;
	private String subpanelVoltage;
	private String otherSubpanelVoltage;
	private String subpanelBusbarRating;
	private String otherSubpanelBusbarRating;
	private String subpanelMainBreakerRating;
	private String otherSubpanelMainBreakerRating;
	private String openBreakerSlotsinSubpanel;
	private String subpanelFeederGauge;
	private String otherSubpanelFeederGauge;
	private Boolean hasImageCapturesAtSupPanel;
	private String imageOfExistingSubPanel;

	///// ------------- site note -----------/////

	private String siteNotes;

	///// ------------- utility information -----------/////

	private String utilityCoName;
	private String meterNumber;
	private String nEMType;
	private String otherNEMType;
	private String postSolarRate;
	private String usageHistoryOffset;
	private String paceEntity;
	private String paceEntityOther;
	private String aCDiscoWillbemorethan10FromUtilityMeter;
	private String atLeast4MonthsOfelectricbillinghistory;
	private Integer squareFeetOfLivingArea;
	private Integer recentAnnualUsage;
	private Integer numberOfElectricVehicles;

	///// ------------- existing pV system at site -----------/////
	
	private Boolean existingPVSystemAtSite;
	private String pVSystemMake;
	private String pVSystemModel;
	private Integer pVQuantity;
	private String gridTiedInverterMake;
	private String gridTiedInverterModel;
	private Integer gridTiedInverterQuantity;
	private Integer existingACDisconnect;
	private String aCDiscoMake;
	private String aCDiscoModel;
	private Integer aCDiscoRating;
	private String connectionType;
	private String otherConnectionType;
	public String notesExistingPvSystem;
	private Boolean existingBattery;
	private String notesOnExistingBatterySystem;
	private Integer pVBreaker1;
	private Integer pVBreaker2;
	private Integer pVBreaker3;
	private Integer pVBreaker4;
	private Integer pVBreaker5;
	private Boolean existingACDisco;
	
	
	
	///// ------------- battery info -----------/////

	private String inverterTechnology;
	private Long inverter1Model;
	private Long inverter2Model;
	private Long inverter3Model;
	private String notesOnInverter;
	private Boolean proposedBattery;
	private String typeOfBatterySystem;
	private String batteryLocation;
	private String circuitstoRelocatetoCriticalLoadsPanel;
	private String criticalLoadPanelLocation;
	private Boolean hasImageOfExistingSolarEquipLocations;
	private String imageBatteryInfo;

	
	///// ------------- internet connection for production monitoring
	///// -----------/////

	private String productionMonitor;
	private String activeInternetConnection;
	private String modemLocation;
	private String connectTheMonitor;
	private Boolean hasImageCapturesOfRoof;
	private String verifyRoofMeasurementsPreRoofLayoutProvided;
	private Boolean hasImageCapturesOfApplicableElevationsViews;
	private String imageOfInternetConnectionRoof;
	private String imageOfInternetConnectionElevation;

	///// ------------- roof mount -----------/////

	private Boolean wireRunOnRoof;
	private Boolean wireRunInAttic;
	private Boolean tiltupModules;
	private String roofMaterial;
	private Integer numberOfLayers;
	private Integer roofAge;
	private Long pVModel;
	private String gableRakeOverhang;
	private String heightAtGutter;
	private String crossSectionSize;
	private String crossSectionSizeOther;
	private String roofStructureChart;
	private String stanchionMaxSpacing;
	private String ridgeBeamDepthAtArrays;
	private String maxSpanAtArraysHS1;
	private String maxSpanAtArraysInchesHS1;
	private String maxSpanAtArraysHS2;
	private String maxSpanAtArraysInchesHS2;
	private String rafterTrussSpacing;
	private String rafterTrussSpOther;
	private String roofRafterOther;
	private String secroofRafterOther;
	///// ------------- non roof mount -----------/////

	private Boolean nonRoofCarport;
	private Boolean nonRoofPatioCover;
	private String nonRoofContourSlope;
	private String nonRoofPathPoint;
	private String nonRoofGradingGrubbing;
	private String nonRoofSiteComposition;
	private String nonRoofElevationStructure;
	private String nonRoofExistingSecurity;
	private String nonRoofPatioCoverValue;

	///// ------------- Patio cover attached -----------/////
	private String patioCoverAttachedTypeBeam;
	private String patioCoverAttachedTypePosts;
	///// ------------- Patio cover freestanding -----------/////

	private String patioCoverFreestandingTypeBeam;
	private String patioCoverFreestandingTypePosts;
	private String patioCoverFreestandingExtendOver;
	private String patioCoverFreestandingPastEave;

	////////////// others //////////////////////////////

	private String otherVoltageOther;
	private String widthSeamsOther;
	private String roofMaterialTypeOther;
	private String postSolarRateOther;
	private String otherPatioCoverAttachedTypeBeam;
	private String otherPatioCoverAttachedTypePosts;
	private String otherPatioCoverFreestandingTypeBeam;
	private String otherPatioCoverFreestandingTypePosts;
	private String projectJurisOther;
	private Boolean frontAndBack;
	private Boolean cantelever;
	private Boolean attachedToExtWal;
	private Boolean attachedToFascia;
	private Boolean attachedToSkylifts;
	private Boolean freeStanding;
	public String roofOrOpenFrame;
	public Integer sumofexistCircuit;
	public String secondOtherVoltageOther;
	private String networkName;
	private String networkPassword;
	private String roofCondition;
	private String roofNotes;
	private String measurmentsOfArea;
	private String notesGroundMount;
	private String notesOnCarpotOrPatiot;
	private String cityOther;
	private String otherMainPanelMake;
	private Long pVModelCarpotPatio;
	private Long pVModelNonRoof;
	private String utilityCompanyNameOther;
	private String measOfAreaCarpot;
	private String homeOwnLastName;
	private String projectName;
	private String imageOfCarPortArrayLocation;
	private Boolean patioMounted;
	public SiteSurveyResult() {
		super();
	}

	public SiteSurveyResult(Long id, String homeOwnName, Long createdBy, Long portalProject, String status,
			Boolean hasProject, Boolean isCanceled, Boolean submitted, Date creationDate, Date lastUpdatedDate,
			Date dateOfSubmit, String basicTypeOfSystem, Boolean hasProjectSiteImage, String contactName,
			String streetAddress, String city, String state, String zIP,String mainContactPhone, String otherPhone ,
			String emailAddress , String hoa, String permittingAuthority,
			String legalOwnerName, String parcelNumber, String roofMaterialType, String widthSeams, String riskCategory,
			String otherRiskCategory, String buildingOccupancy, String otherBuildingOccupancy, String numberOfStories,Integer meanHeight,
			String projectEquipmentStagingLocation, String ownerPreferredRoofAccessLocation,
			String accessIssuesWith_MeterOrProposedACDisco, Boolean utilityMeterOrProposedACDisco,
			Boolean unrestrainedAnimal, Boolean otheraccessissue, String describeAccessIssues,
			String contactpersonforutility, String othercontactperson, String contactphone, String othercontactphone,
			Boolean hasImageCapturesatRafterTrusses, Boolean centerFed, String mainPanelMake, String mainPanelModel,
			Boolean upgradingMainServicePanel, String voltage, String otherVoltage, String mspbusbarRating,
			String otherMSPBusbarRating, String mSPMainBreakerRating, String otherMSPMainBreakerRating,
			String openBreakerSlots, String sCIR, String mainBreakerFeedGauge,String otherMainBreakerFeedGauge, String utilityFeederGauge,
			Boolean copperWiresSameAsMainContact, Boolean copperWiresDifferentNumber,
			Boolean aluminumWiresSameAsMainContact, Boolean aluminumWiresDifferentNumber, String groundAccessible,
			String undergroundOrOverheadFeed, Boolean hasImageAtMSP, Boolean existingSubpanel,
			Boolean tieInPOCIsAtSubpanel, String subpanelMake, String subpanelModel, String subpanelVoltage,
			String otherSubpanelVoltage,
			String subpanelBusbarRating, String otherSubpanelBusbarRating, String subpanelMainBreakerRating,
			String otherSubpanelMainBreakerRating, String openBreakerSlotsinSubpanel, String subpanelFeederGauge,
			String otherSubpanelFeederGauge,
			Boolean hasImageCapturesAtSupPanel, String siteNotes, String utilityCoName,String meterNumber, String nEMType,
			String otherNEMType, String postSolarRate, String usageHistoryOffset, String paceEntity, String paceEntityOther,
			String aCDiscoWillbemorethan10FromUtilityMeter, String atLeast4MonthsOfelectricbillinghistory,
			Integer squareFeetOfLivingArea, Integer recentAnnualUsage, Integer numberOfElectricVehicles,
			
			Boolean existingPVSystemAtSite, String pVSystemMake, String pVSystemModel, Integer pVQuantity,
			String gridTiedInverterMake, String gridTiedInverterModel, Integer gridTiedInverterQuantity,
			Integer existingACDisconnect, String aCDiscoMake, String aCDiscoModel, Integer aCDiscoRating,
			String connectionType, String otherConnectionType, String notesExistingPvSystem, Boolean existingBattery, String notesOnExistingBatterySystem,
			Integer pVBreaker1, Integer pVBreaker2, Integer pVBreaker3, Integer pVBreaker4,Integer pVBreaker5, 
			
			String inverterTechnology, Long inverter1Model, Long inverter2Model,
			Long inverter3Model, String notesOnInverter, Boolean proposedBattery,
			String typeOfBatterySystem, String batteryLocation, String circuitstoRelocatetoCriticalLoadsPanel, 
			String criticalLoadPanelLocation, Boolean hasImageOfExistingSolarEquipLocations, String imageBatteryInfo,
			 
			String productionMonitor, String activeInternetConnection,
			String modemLocation, String connectTheMonitor, Boolean hasImageCapturesOfRoof,
			String verifyRoofMeasurementsPreRoofLayoutProvided, Boolean hasImageCapturesOfApplicableElevationsViews,
			Boolean wireRunOnRoof, Boolean wireRunInAttic, Boolean tiltupModules,
			String roofMaterial, Integer numberOfLayers, Integer roofAge, Long pVModel,String gableRakeOverhang,
			String heightAtGutter, String crossSectionSize, String crossSectionSizeOther, String roofStructureChart,
			String stanchionMaxSpacing, String ridgeBeamDepthAtArrays, String maxSpanAtArraysHS1,String maxSpanAtArraysInchesHS1,
			String maxSpanAtArraysHS2, String maxSpanAtArraysInchesHS2, String rafterTrussSpacing, String rafterTrussSpOther,
			Boolean nonRoofCarport, Boolean nonRoofPatioCover,
			String nonRoofContourSlope, String nonRoofPathPoint, String nonRoofGradingGrubbing,
			String nonRoofSiteComposition, String nonRoofElevationStructure, String nonRoofExistingSecurity,
			String nonRoofPatioCoverValue, String patioCoverAttachedTypeBeam, String patioCoverAttachedTypePosts,
			String patioCoverFreestandingTypeBeam, String patioCoverFreestandingTypePosts,
			String patioCoverFreestandingExtendOver, String patioCoverFreestandingPastEave, String otherVoltageOther,
			String widthSeamsOther, String roofMaterialTypeOther, String postSolarRateOther,
			String otherPatioCoverAttachedTypeBeam, String otherPatioCoverAttachedTypePosts,
			String otherPatioCoverFreestandingTypeBeam, String otherPatioCoverFreestandingTypePosts,
			String imageOfSiteInformationRafter, String imageOfExistingMainPanel, String imageOfExistingSubPanel,
			String imageOfInternetConnectionRoof, String imageOfInternetConnectionElevation,
			Boolean existingACDisco, String addressLine2, String projectJurisOther, String otherTallStructure,
			Boolean roofMounted, Boolean groundMounted, Boolean carportMounted, String textOther,
			String subPanelBreakerOCPD, String roofRafterOther, String secroofRafterOther,
			Boolean frontAndBack, Boolean cantelever, Boolean attachedToExtWal, Boolean attachedToFascia, Boolean attachedToSkylifts,
			Boolean freeStanding, String roofOrOpenFrame,
			Integer sumofexistCircuit, String secondOtherVoltageOther, String networkName,
			String networkPassword, String roofCondition, String roofNotes, String measurmentsOfArea,
			String notesGroundMount, String notesOnCarpotOrPatiot, String cityOther, String otherMainPanelMake,
			Boolean otherMounted, Long pVModelCarpotPatio, Long pVModelNonRoof, String utilityCompanyNameOther,
			String measOfAreaCarpot, String homeOwnLastName, String projectName,String imageOfCarPortArrayLocation,
			Boolean patioMounted) {
		super();
		this.id = id;
		this.homeOwnName = homeOwnName;
		this.createdBy = createdBy;
		this.portalProject = portalProject;
		this.status = status;
		this.hasProject = hasProject;
		this.isCanceled = isCanceled;
		this.submitted = submitted;
		this.creationDate = creationDate;
		this.lastUpdatedDate = lastUpdatedDate;
		this.dateOfSubmit = dateOfSubmit;
		this.basicTypeOfSystem = basicTypeOfSystem;
		this.hasProjectSiteImage = hasProjectSiteImage;
		this.contactName = contactName;
		this.streetAddress = streetAddress;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.zIP = zIP;
		this.mainContactPhone = mainContactPhone;
		this.otherPhone = otherPhone;
		this.emailAddress = emailAddress;
		this.hoa = hoa;
		this.permittingAuthority = permittingAuthority;
		this.legalOwnerName = legalOwnerName;
		this.parcelNumber = parcelNumber;
		this.roofMaterialType = roofMaterialType;
		this.widthSeams = widthSeams;
		this.riskCategory = riskCategory;
		this.otherRiskCategory = otherRiskCategory;
		this.buildingOccupancy = buildingOccupancy;
		this.otherBuildingOccupancy = otherBuildingOccupancy;
		this.numberOfStories = numberOfStories;
		this.meanHeight = meanHeight;
		this.otherTallStructure = otherTallStructure;
		this.projectEquipmentStagingLocation = projectEquipmentStagingLocation;
		this.ownerPreferredRoofAccessLocation = ownerPreferredRoofAccessLocation;
		this.accessIssuesWith_MeterOrProposedACDisco = accessIssuesWith_MeterOrProposedACDisco;
		this.utilityMeterOrProposedACDisco = utilityMeterOrProposedACDisco;
		this.unrestrainedAnimal = unrestrainedAnimal;
		this.otheraccessissue = otheraccessissue;
		this.describeAccessIssues = describeAccessIssues;
		this.contactpersonforutility = contactpersonforutility;
		this.othercontactperson = othercontactperson;
		this.contactphone = contactphone;
		this.othercontactphone = othercontactphone;
		this.hasImageCapturesatRafterTrusses = hasImageCapturesatRafterTrusses;
		this.centerFed = centerFed;
		this.mainPanelMake = mainPanelMake;
		this.mainPanelModel = mainPanelModel;
		this.upgradingMainServicePanel = upgradingMainServicePanel;
		this.voltage = voltage;
		this.otherVoltage = otherVoltage;
		this.mspbusbarRating = mspbusbarRating;
		this.otherMSPBusbarRating = otherMSPBusbarRating;
		this.mSPMainBreakerRating = mSPMainBreakerRating;
		this.otherMSPMainBreakerRating = otherMSPMainBreakerRating;
		this.openBreakerSlots = openBreakerSlots;
		this.sCIR = sCIR;
		this.mainBreakerFeedGauge = mainBreakerFeedGauge;
		this.otherMainBreakerFeedGauge = otherMainBreakerFeedGauge;
		this.utilityFeederGauge = utilityFeederGauge;
		this.copperWiresSameAsMainContact = copperWiresSameAsMainContact;
		this.copperWiresDifferentNumber = copperWiresDifferentNumber;
		this.aluminumWiresSameAsMainContact = aluminumWiresSameAsMainContact;
		this.aluminumWiresDifferentNumber = aluminumWiresDifferentNumber;
		this.groundAccessible = groundAccessible;
		this.undergroundOrOverheadFeed = undergroundOrOverheadFeed;
		this.hasImageAtMSP = hasImageAtMSP;
		this.existingSubpanel = existingSubpanel;
		this.tieInPOCIsAtSubpanel = tieInPOCIsAtSubpanel;
		this.subpanelMake = subpanelMake;
		this.subpanelModel = subpanelModel;
		this.subpanelVoltage = subpanelVoltage;
		this.otherSubpanelVoltage = otherSubpanelVoltage;
		this.subpanelBusbarRating = subpanelBusbarRating;
		this.otherSubpanelBusbarRating = otherSubpanelBusbarRating;
		this.subpanelMainBreakerRating = subpanelMainBreakerRating;
		this.otherSubpanelMainBreakerRating = otherSubpanelMainBreakerRating;
		this.openBreakerSlotsinSubpanel = openBreakerSlotsinSubpanel;
		this.subpanelFeederGauge = subpanelFeederGauge;
		this.otherSubpanelFeederGauge = otherSubpanelFeederGauge;
		this.hasImageCapturesAtSupPanel = hasImageCapturesAtSupPanel;
		this.siteNotes = siteNotes;
		this.utilityCoName = utilityCoName;
		this.meterNumber = meterNumber;
		this.nEMType = nEMType;
		this.otherNEMType = otherNEMType;
		this.postSolarRate = postSolarRate;
		this.usageHistoryOffset = usageHistoryOffset;
		this.paceEntity = paceEntity;
		this.paceEntityOther =paceEntityOther;
		this.aCDiscoWillbemorethan10FromUtilityMeter = aCDiscoWillbemorethan10FromUtilityMeter;
		this.atLeast4MonthsOfelectricbillinghistory = atLeast4MonthsOfelectricbillinghistory;
		this.squareFeetOfLivingArea = squareFeetOfLivingArea;
		this.recentAnnualUsage = recentAnnualUsage;
		this.numberOfElectricVehicles = numberOfElectricVehicles;
		this.existingPVSystemAtSite = existingPVSystemAtSite;
		this.pVSystemMake = pVSystemMake;
		this.pVSystemModel = pVSystemModel;
		this.pVQuantity = pVQuantity;
		this.gridTiedInverterMake = gridTiedInverterMake;
		this.gridTiedInverterModel = gridTiedInverterModel;
		this.gridTiedInverterQuantity = gridTiedInverterQuantity;
		this.existingACDisconnect = existingACDisconnect;
		this.aCDiscoMake = aCDiscoMake;
		this.aCDiscoModel = aCDiscoModel;
		this.aCDiscoRating = aCDiscoRating;
		this.connectionType = connectionType;
		this.otherConnectionType = otherConnectionType;
		this.pVBreaker1 = pVBreaker1;
		this.pVBreaker2 = pVBreaker2;
		this.pVBreaker3 = pVBreaker3;
		this.pVBreaker4 = pVBreaker4;
		this.pVBreaker5 = pVBreaker5;
		this.existingBattery = existingBattery;
		this.notesOnExistingBatterySystem =notesOnExistingBatterySystem;
		this.hasImageOfExistingSolarEquipLocations = hasImageOfExistingSolarEquipLocations;
		this.proposedBattery = proposedBattery;
		this.circuitstoRelocatetoCriticalLoadsPanel = circuitstoRelocatetoCriticalLoadsPanel;
		this.batteryLocation = batteryLocation;
		this.inverter1Model = inverter1Model;
		this.inverter2Model = inverter2Model;
		this.inverter3Model = inverter3Model;
		this.productionMonitor = productionMonitor;
		this.activeInternetConnection = activeInternetConnection;
		this.modemLocation = modemLocation;
		this.connectTheMonitor = connectTheMonitor;
		this.hasImageCapturesOfRoof = hasImageCapturesOfRoof;
		this.verifyRoofMeasurementsPreRoofLayoutProvided = verifyRoofMeasurementsPreRoofLayoutProvided;
		this.hasImageCapturesOfApplicableElevationsViews = hasImageCapturesOfApplicableElevationsViews;
		this.wireRunOnRoof = wireRunOnRoof;
		this.wireRunInAttic = wireRunInAttic;
		this.tiltupModules = tiltupModules;
		this.roofMaterial = roofMaterial;
		this.numberOfLayers = numberOfLayers;
		this.roofAge = roofAge;
		this.pVModel = pVModel;
		this.gableRakeOverhang = gableRakeOverhang;
		this.heightAtGutter = heightAtGutter;
		this.crossSectionSize = crossSectionSize;
		this.crossSectionSizeOther = crossSectionSizeOther;
		this.roofStructureChart = roofStructureChart;
		this.stanchionMaxSpacing = stanchionMaxSpacing;
		this.ridgeBeamDepthAtArrays = ridgeBeamDepthAtArrays;
		this.maxSpanAtArraysHS1 = maxSpanAtArraysHS1;
		this.maxSpanAtArraysInchesHS1 = maxSpanAtArraysInchesHS1;
		this.maxSpanAtArraysHS2 = maxSpanAtArraysHS2;
		this.maxSpanAtArraysInchesHS2 = maxSpanAtArraysInchesHS2;
		this.rafterTrussSpacing = rafterTrussSpacing;
		this.rafterTrussSpOther = rafterTrussSpOther;
		this.nonRoofCarport = nonRoofCarport;
		this.nonRoofPatioCover = nonRoofPatioCover;
		this.nonRoofContourSlope = nonRoofContourSlope;
		this.nonRoofPathPoint = nonRoofPathPoint;
		this.nonRoofGradingGrubbing = nonRoofGradingGrubbing;
		this.nonRoofSiteComposition = nonRoofSiteComposition;
		this.nonRoofElevationStructure = nonRoofElevationStructure;
		this.nonRoofExistingSecurity = nonRoofExistingSecurity;
		this.nonRoofPatioCoverValue = nonRoofPatioCoverValue;
		this.patioCoverAttachedTypeBeam = patioCoverAttachedTypeBeam;
		this.patioCoverAttachedTypePosts = patioCoverAttachedTypePosts;
		this.patioCoverFreestandingTypeBeam = patioCoverFreestandingTypeBeam;
		this.patioCoverFreestandingTypePosts = patioCoverFreestandingTypePosts;
		this.patioCoverFreestandingExtendOver = patioCoverFreestandingExtendOver;
		this.patioCoverFreestandingPastEave = patioCoverFreestandingPastEave;
		this.otherVoltageOther = otherVoltageOther;
		this.widthSeamsOther = widthSeamsOther;
		this.roofMaterialTypeOther = roofMaterialTypeOther;
		this.postSolarRateOther = postSolarRateOther;
		this.otherPatioCoverAttachedTypeBeam = otherPatioCoverAttachedTypeBeam;
		this.otherPatioCoverAttachedTypePosts = otherPatioCoverAttachedTypePosts;
		this.otherPatioCoverFreestandingTypeBeam = otherPatioCoverFreestandingTypeBeam;
		this.otherPatioCoverFreestandingTypePosts = otherPatioCoverFreestandingTypePosts;
		this.imageOfSiteInformationRafter = imageOfSiteInformationRafter;
		this.imageOfExistingMainPanel = imageOfExistingMainPanel;
		this.imageOfExistingSubPanel = imageOfExistingSubPanel;
		this.imageBatteryInfo = imageBatteryInfo;
		this.inverterTechnology = inverterTechnology;
		this.notesOnInverter = notesOnInverter;
		this.typeOfBatterySystem = typeOfBatterySystem;
		this.criticalLoadPanelLocation = criticalLoadPanelLocation;
		this.imageOfInternetConnectionRoof = imageOfInternetConnectionRoof;
		this.imageOfInternetConnectionElevation = imageOfInternetConnectionElevation;
		this.existingACDisco = existingACDisco;
		this.roofMounted = roofMounted;
		this.groundMounted = groundMounted;
		this.carportMounted = carportMounted;
		this.textOther = textOther;
		this.subPanelBreakerOCPD = subPanelBreakerOCPD;
		this.roofRafterOther = roofRafterOther;
		this.secroofRafterOther = secroofRafterOther;
		this.projectJurisOther = projectJurisOther;
		this.frontAndBack = frontAndBack;
		this.cantelever = cantelever;
		this.attachedToExtWal = attachedToExtWal;
		this.attachedToFascia = attachedToFascia;
		this.attachedToSkylifts = attachedToSkylifts;
		this.freeStanding = freeStanding;
		this.roofOrOpenFrame = roofOrOpenFrame;
		this.sumofexistCircuit = sumofexistCircuit;
		this.secondOtherVoltageOther = secondOtherVoltageOther;
		this.notesExistingPvSystem = notesExistingPvSystem;
		this.networkName = networkName;
		this.networkPassword = networkPassword;
		this.roofCondition = roofCondition;
		this.roofNotes = roofNotes;
		this.measurmentsOfArea = measurmentsOfArea;
		this.notesGroundMount = notesGroundMount;
		this.notesOnCarpotOrPatiot = notesOnCarpotOrPatiot;
		this.cityOther = cityOther;
		this.otherMainPanelMake = otherMainPanelMake;
		this.otherMounted = otherMounted;
		this.pVModelCarpotPatio = pVModelCarpotPatio;
		this.pVModelNonRoof = pVModelNonRoof;
		this.utilityCompanyNameOther = utilityCompanyNameOther;
		this.measOfAreaCarpot = measOfAreaCarpot;
		this.homeOwnLastName = homeOwnLastName;
		this.projectName = projectName;
		this.imageOfCarPortArrayLocation =imageOfCarPortArrayLocation;
		this.patioMounted = patioMounted;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHomeOwnName() {
		return homeOwnName;
	}

	public void setHomeOwnName(String homeOwnName) {
		this.homeOwnName = homeOwnName;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getPortalProject() {
		return portalProject;
	}

	public void setPortalProject(Long portalProject) {
		this.portalProject = portalProject;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean isHasProject() {
		return hasProject;
	}

	public void setHasProject(Boolean hasProject) {
		this.hasProject = hasProject;
	}

	public Boolean isCanceled() {
		return isCanceled;
	}

	public void setCanceled(Boolean isCanceled) {
		this.isCanceled = isCanceled;
	}

	public Boolean isSubmitted() {
		return submitted;
	}

	public void setSubmitted(Boolean submitted) {
		this.submitted = submitted;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public Date getDateOfSubmit() {
		return dateOfSubmit;
	}

	public void setDateOfSubmit(Date dateOfSubmit) {
		this.dateOfSubmit = dateOfSubmit;
	}

	public String getBasicTypeOfSystem() {
		return basicTypeOfSystem;
	}

	public void setBasicTypeOfSystem(String basicTypeOfSystem) {
		this.basicTypeOfSystem = basicTypeOfSystem;
	}

	public Boolean isHasProjectSiteImage() {
		return hasProjectSiteImage;
	}

	public void setHasProjectSiteImage(Boolean hasProjectSiteImage) {
		this.hasProjectSiteImage = hasProjectSiteImage;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getzIP() {
		return zIP;
	}

	public void setzIP(String zIP) {
		this.zIP = zIP;
	}

	public String getHoa() {
		return hoa;
	}

	public void setHoa(String hoa) {
		this.hoa = hoa;
	}

	public String getPermittingAuthority() {
		return permittingAuthority;
	}

	public void setPermittingAuthority(String permittingAuthority) {
		this.permittingAuthority = permittingAuthority;
	}

	public String getLegalOwnerName() {
		return legalOwnerName;
	}

	public void setLegalOwnerName(String legalOwnerName) {
		this.legalOwnerName = legalOwnerName;
	}

	public String getParcelNumber() {
		return parcelNumber;
	}

	public void setParcelNumber(String parcelNumber) {
		this.parcelNumber = parcelNumber;
	}

	public String getRoofMaterialType() {
		return roofMaterialType;
	}

	public void setRoofMaterialType(String roofMaterialType) {
		this.roofMaterialType = roofMaterialType;
	}

	public String getWidthSeams() {
		return widthSeams;
	}

	public void setWidthSeams(String widthSeams) {
		this.widthSeams = widthSeams;
	}

	public String getRiskCategory() {
		return riskCategory;
	}

	public void setRiskCategory(String riskCategory) {
		this.riskCategory = riskCategory;
	}

	public String getOtherRiskCategory() {
		return otherRiskCategory;
	}

	public void setOtherRiskCategory(String otherRiskCategory) {
		this.otherRiskCategory = otherRiskCategory;
	}

	public String getBuildingOccupancy() {
		return buildingOccupancy;
	}

	public void setBuildingOccupancy(String buildingOccupancy) {
		this.buildingOccupancy = buildingOccupancy;
	}

	public String getOtherBuildingOccupancy() {
		return otherBuildingOccupancy;
	}

	public void setOtherBuildingOccupancy(String otherBuildingOccupancy) {
		this.otherBuildingOccupancy = otherBuildingOccupancy;
	}

	public String getNumberOfStories() {
		return numberOfStories;
	}

	public void setNumberOfStories(String numberOfStories) {
		this.numberOfStories = numberOfStories;
	}

	public String getOtherTallStructure() {
		return otherTallStructure;
	}

	public void setOtherTallStructure(String otherTallStructure) {
		this.otherTallStructure = otherTallStructure;
	}

	public String getProjectEquipmentStagingLocation() {
		return projectEquipmentStagingLocation;
	}

	public void setProjectEquipmentStagingLocation(String projectEquipmentStagingLocation) {
		this.projectEquipmentStagingLocation = projectEquipmentStagingLocation;
	}

	public String getOwnerPreferredRoofAccessLocation() {
		return ownerPreferredRoofAccessLocation;
	}

	public void setOwnerPreferredRoofAccessLocation(String ownerPreferredRoofAccessLocation) {
		this.ownerPreferredRoofAccessLocation = ownerPreferredRoofAccessLocation;
	}

	public String getAccessIssuesWith_MeterOrProposedACDisco() {
		return accessIssuesWith_MeterOrProposedACDisco;
	}

	public void setAccessIssuesWith_MeterOrProposedACDisco(String accessIssuesWith_MeterOrProposedACDisco) {
		this.accessIssuesWith_MeterOrProposedACDisco = accessIssuesWith_MeterOrProposedACDisco;
	}

	public Boolean isUtilityMeterOrProposedACDisco() {
		return utilityMeterOrProposedACDisco;
	}

	public void setUtilityMeterOrProposedACDisco(Boolean utilityMeterOrProposedACDisco) {
		this.utilityMeterOrProposedACDisco = utilityMeterOrProposedACDisco;
	}

	public Boolean isUnrestrainedAnimal() {
		return unrestrainedAnimal;
	}

	public void setUnrestrainedAnimal(Boolean unrestrainedAnimal) {
		this.unrestrainedAnimal = unrestrainedAnimal;
	}

	public Boolean isOtheraccessissue() {
		return otheraccessissue;
	}

	public void setOtheraccessissue(Boolean otheraccessissue) {
		this.otheraccessissue = otheraccessissue;
	}

	public String getDescribeAccessIssues() {
		return describeAccessIssues;
	}

	public void setDescribeAccessIssues(String describeAccessIssues) {
		this.describeAccessIssues = describeAccessIssues;
	}

	public String getContactpersonforutility() {
		return contactpersonforutility;
	}

	public void setContactpersonforutility(String contactpersonforutility) {
		this.contactpersonforutility = contactpersonforutility;
	}

	public String getOthercontactperson() {
		return othercontactperson;
	}

	public void setOthercontactperson(String othercontactperson) {
		this.othercontactperson = othercontactperson;
	}

	public String getContactphone() {
		return contactphone;
	}

	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}

	public String getOthercontactphone() {
		return othercontactphone;
	}

	public void setOthercontactphone(String othercontactphone) {
		this.othercontactphone = othercontactphone;
	}

	public Boolean isHasImageCapturesatRafterTrusses() {
		return hasImageCapturesatRafterTrusses;
	}

	public void setHasImageCapturesatRafterTrusses(Boolean hasImageCapturesatRafterTrusses) {
		this.hasImageCapturesatRafterTrusses = hasImageCapturesatRafterTrusses;
	}

	public Boolean isCenterFed() {
		return centerFed;
	}

	public void setCenterFed(Boolean centerFed) {
		this.centerFed = centerFed;
	}

	public String getMainPanelMake() {
		return mainPanelMake;
	}

	public void setMainPanelMake(String mainPanelMake) {
		this.mainPanelMake = mainPanelMake;
	}

	public String getMainPanelModel() {
		return mainPanelModel;
	}

	public void setMainPanelModel(String mainPanelModel) {
		this.mainPanelModel = mainPanelModel;
	}

	public Boolean isUpgradingMainServicePanel() {
		return upgradingMainServicePanel;
	}

	public void setUpgradingMainServicePanel(Boolean upgradingMainServicePanel) {
		this.upgradingMainServicePanel = upgradingMainServicePanel;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public String getOtherVoltage() {
		return otherVoltage;
	}

	public void setOtherVoltage(String otherVoltage) {
		this.otherVoltage = otherVoltage;
	}


	public String getOtherMSPBusbarRating() {
		return otherMSPBusbarRating;
	}

	public void setOtherMSPBusbarRating(String otherMSPBusbarRating) {
		this.otherMSPBusbarRating = otherMSPBusbarRating;
	}

	public String getmSPMainBreakerRating() {
		return mSPMainBreakerRating;
	}

	public void setmSPMainBreakerRating(String mSPMainBreakerRating) {
		this.mSPMainBreakerRating = mSPMainBreakerRating;
	}

	public String getOtherMSPMainBreakerRating() {
		return otherMSPMainBreakerRating;
	}

	public void setOtherMSPMainBreakerRating(String otherMSPMainBreakerRating) {
		this.otherMSPMainBreakerRating = otherMSPMainBreakerRating;
	}

	public String getOpenBreakerSlots() {
		return openBreakerSlots;
	}

	public void setOpenBreakerSlots(String openBreakerSlots) {
		this.openBreakerSlots = openBreakerSlots;
	}

	public String getsCIR() {
		return sCIR;
	}

	public void setsCIR(String sCIR) {
		this.sCIR = sCIR;
	}

	public String getMainBreakerFeedGauge() {
		return mainBreakerFeedGauge;
	}

	public void setMainBreakerFeedGauge(String mainBreakerFeedGauge) {
		this.mainBreakerFeedGauge = mainBreakerFeedGauge;
	}

	public String getOtherMainBreakerFeedGauge() {
		return otherMainBreakerFeedGauge;
	}

	public void setOtherMainBreakerFeedGauge(String otherMainBreakerFeedGauge) {
		this.otherMainBreakerFeedGauge = otherMainBreakerFeedGauge;
	}

	public String getUtilityFeederGauge() {
		return utilityFeederGauge;
	}

	public void setUtilityFeederGauge(String utilityFeederGauge) {
		this.utilityFeederGauge = utilityFeederGauge;
	}

	public Boolean isCopperWiresSameAsMainContact() {
		return copperWiresSameAsMainContact;
	}

	public void setCopperWiresSameAsMainContact(Boolean copperWiresSameAsMainContact) {
		this.copperWiresSameAsMainContact = copperWiresSameAsMainContact;
	}

	public Boolean isCopperWiresDifferentNumber() {
		return copperWiresDifferentNumber;
	}

	public void setCopperWiresDifferentNumber(Boolean copperWiresDifferentNumber) {
		this.copperWiresDifferentNumber = copperWiresDifferentNumber;
	}

	public Boolean isAluminumWiresSameAsMainContact() {
		return aluminumWiresSameAsMainContact;
	}

	public void setAluminumWiresSameAsMainContact(Boolean aluminumWiresSameAsMainContact) {
		this.aluminumWiresSameAsMainContact = aluminumWiresSameAsMainContact;
	}

	public Boolean isAluminumWiresDifferentNumber() {
		return aluminumWiresDifferentNumber;
	}

	public void setAluminumWiresDifferentNumber(Boolean aluminumWiresDifferentNumber) {
		this.aluminumWiresDifferentNumber = aluminumWiresDifferentNumber;
	}

	public String getGroundAccessible() {
		return groundAccessible;
	}

	public void setGroundAccessible(String groundAccessible) {
		this.groundAccessible = groundAccessible;
	}

	public String getUndergroundOrOverheadFeed() {
		return undergroundOrOverheadFeed;
	}

	public void setUndergroundOrOverheadFeed(String undergroundOrOverheadFeed) {
		this.undergroundOrOverheadFeed = undergroundOrOverheadFeed;
	}

	public Boolean isHasImageAtMSP() {
		return hasImageAtMSP;
	}

	public void setHasImageAtMSP(Boolean hasImageAtMSP) {
		this.hasImageAtMSP = hasImageAtMSP;
	}

	public Boolean isExistingSubpanel() {
		return existingSubpanel;
	}

	public void setExistingSubpanel(Boolean existingSubpanel) {
		this.existingSubpanel = existingSubpanel;
	}

	public Boolean isTieInPOCIsAtSubpanel() {
		return tieInPOCIsAtSubpanel;
	}

	public void setTieInPOCIsAtSubpanel(Boolean tieInPOCIsAtSubpanel) {
		this.tieInPOCIsAtSubpanel = tieInPOCIsAtSubpanel;
	}

	public String getSubpanelMake() {
		return subpanelMake;
	}

	public void setSubpanelMake(String subpanelMake) {
		this.subpanelMake = subpanelMake;
	}

	public String getSubpanelModel() {
		return subpanelModel;
	}

	public void setSubpanelModel(String subpanelModel) {
		this.subpanelModel = subpanelModel;
	}

	public String getSubpanelVoltage() {
		return subpanelVoltage;
	}

	public void setSubpanelVoltage(String subpanelVoltage) {
		this.subpanelVoltage = subpanelVoltage;
	}

	public String getOtherSubpanelVoltage() {
		return otherSubpanelVoltage;
	}

	public void setOtherSubpanelVoltage(String otherSubpanelVoltage) {
		this.otherSubpanelVoltage = otherSubpanelVoltage;
	}

	public String getSubpanelBusbarRating() {
		return subpanelBusbarRating;
	}

	public void setSubpanelBusbarRating(String subpanelBusbarRating) {
		this.subpanelBusbarRating = subpanelBusbarRating;
	}

	public String getOtherSubpanelBusbarRating() {
		return otherSubpanelBusbarRating;
	}

	public void setOtherSubpanelBusbarRating(String otherSubpanelBusbarRating) {
		this.otherSubpanelBusbarRating = otherSubpanelBusbarRating;
	}

	public String getSubpanelMainBreakerRating() {
		return subpanelMainBreakerRating;
	}

	public void setSubpanelMainBreakerRating(String subpanelMainBreakerRating) {
		this.subpanelMainBreakerRating = subpanelMainBreakerRating;
	}

	public String getOtherSubpanelMainBreakerRating() {
		return otherSubpanelMainBreakerRating;
	}

	public void setOtherSubpanelMainBreakerRating(String otherSubpanelMainBreakerRating) {
		this.otherSubpanelMainBreakerRating = otherSubpanelMainBreakerRating;
	}

	public String getOpenBreakerSlotsinSubpanel() {
		return openBreakerSlotsinSubpanel;
	}

	public void setOpenBreakerSlotsinSubpanel(String openBreakerSlotsinSubpanel) {
		this.openBreakerSlotsinSubpanel = openBreakerSlotsinSubpanel;
	}

	public String getSubpanelFeederGauge() {
		return subpanelFeederGauge;
	}

	public void setSubpanelFeederGauge(String subpanelFeederGauge) {
		this.subpanelFeederGauge = subpanelFeederGauge;
	}

	public String getOtherSubpanelFeederGauge() {
		return otherSubpanelFeederGauge;
	}

	public void setOtherSubpanelFeederGauge(String otherSubpanelFeederGauge) {
		this.otherSubpanelFeederGauge = otherSubpanelFeederGauge;
	}

	public Boolean isHasImageCapturesAtSupPanel() {
		return hasImageCapturesAtSupPanel;
	}

	public void setHasImageCapturesAtSupPanel(Boolean hasImageCapturesAtSupPanel) {
		this.hasImageCapturesAtSupPanel = hasImageCapturesAtSupPanel;
	}

	public String getSiteNotes() {
		return siteNotes;
	}

	public void setSiteNotes(String siteNotes) {
		this.siteNotes = siteNotes;
	}

	public String getUtilityCoName() {
		return utilityCoName;
	}

	public void setUtilityCoName(String utilityCoName) {
		this.utilityCoName = utilityCoName;
	}

	public String getMeterNumber() {
		return meterNumber;
	}

	public void setMeterNumber(String meterNumber) {
		this.meterNumber = meterNumber;
	}

	public String getnEMType() {
		return nEMType;
	}

	public void setnEMType(String nEMType) {
		this.nEMType = nEMType;
	}

	public String getOtherNEMType() {
		return otherNEMType;
	}

	public void setOtherNEMType(String otherNEMType) {
		this.otherNEMType = otherNEMType;
	}

	public String getPostSolarRate() {
		return postSolarRate;
	}

	public void setPostSolarRate(String postSolarRate) {
		this.postSolarRate = postSolarRate;
	}

	public String getUsageHistoryOffset() {
		return usageHistoryOffset;
	}

	public void setUsageHistoryOffset(String usageHistoryOffset) {
		this.usageHistoryOffset = usageHistoryOffset;
	}

	public String getPaceEntity() {
		return paceEntity;
	}

	public void setPaceEntity(String paceEntity) {
		this.paceEntity = paceEntity;
	}

	public String getPaceEntityOther() {
		return paceEntityOther;
	}

	public void setPaceEntityOther(String paceEntityOther) {
		this.paceEntityOther = paceEntityOther;
	}

	public String getaCDiscoWillbemorethan10FromUtilityMeter() {
		return aCDiscoWillbemorethan10FromUtilityMeter;
	}

	public void setaCDiscoWillbemorethan10FromUtilityMeter(String aCDiscoWillbemorethan10FromUtilityMeter) {
		this.aCDiscoWillbemorethan10FromUtilityMeter = aCDiscoWillbemorethan10FromUtilityMeter;
	}

	public String getAtLeast4MonthsOfelectricbillinghistory() {
		return atLeast4MonthsOfelectricbillinghistory;
	}

	public void setAtLeast4MonthsOfelectricbillinghistory(String atLeast4MonthsOfelectricbillinghistory) {
		this.atLeast4MonthsOfelectricbillinghistory = atLeast4MonthsOfelectricbillinghistory;
	}

	public Integer getSquareFeetOfLivingArea() {
		return squareFeetOfLivingArea;
	}

	public void setSquareFeetOfLivingArea(Integer squareFeetOfLivingArea) {
		this.squareFeetOfLivingArea = squareFeetOfLivingArea;
	}

	public Integer getRecentAnnualUsage() {
		return recentAnnualUsage;
	}

	public void setRecentAnnualUsage(Integer recentAnnualUsage) {
		this.recentAnnualUsage = recentAnnualUsage;
	}

	public Integer getNumberOfElectricVehicles() {
		return numberOfElectricVehicles;
	}

	public void setNumberOfElectricVehicles(Integer numberOfElectricVehicles) {
		this.numberOfElectricVehicles = numberOfElectricVehicles;
	}

	public Boolean isExistingPVSystemAtSite() {
		return existingPVSystemAtSite;
	}

	public void setExistingPVSystemAtSite(Boolean existingPVSystemAtSite) {
		this.existingPVSystemAtSite = existingPVSystemAtSite;
	}

	public String getpVSystemMake() {
		return pVSystemMake;
	}

	public void setpVSystemMake(String pVSystemMake) {
		this.pVSystemMake = pVSystemMake;
	}

	public String getpVSystemModel() {
		return pVSystemModel;
	}

	public void setpVSystemModel(String pVSystemModel) {
		this.pVSystemModel = pVSystemModel;
	}

	public Integer getpVQuantity() {
		return pVQuantity;
	}

	public void setpVQuantity(Integer pVQuantity) {
		this.pVQuantity = pVQuantity;
	}

	public String getGridTiedInverterMake() {
		return gridTiedInverterMake;
	}

	public void setGridTiedInverterMake(String gridTiedInverterMake) {
		this.gridTiedInverterMake = gridTiedInverterMake;
	}

	public String getGridTiedInverterModel() {
		return gridTiedInverterModel;
	}

	public void setGridTiedInverterModel(String gridTiedInverterModel) {
		this.gridTiedInverterModel = gridTiedInverterModel;
	}

	public Integer getGridTiedInverterQuantity() {
		return gridTiedInverterQuantity;
	}

	public void setGridTiedInverterQuantity(Integer gridTiedInverterQuantity) {
		this.gridTiedInverterQuantity = gridTiedInverterQuantity;
	}

	public Integer getExistingACDisconnect() {
		return existingACDisconnect;
	}

	public void setExistingACDisconnect(Integer existingACDisconnect) {
		this.existingACDisconnect = existingACDisconnect;
	}

	public String getaCDiscoMake() {
		return aCDiscoMake;
	}

	public void setaCDiscoMake(String aCDiscoMake) {
		this.aCDiscoMake = aCDiscoMake;
	}

	public String getaCDiscoModel() {
		return aCDiscoModel;
	}

	public void setaCDiscoModel(String aCDiscoModel) {
		this.aCDiscoModel = aCDiscoModel;
	}

	public Integer getACDiscoRating() {
		return aCDiscoRating;
	}

	public void setACDiscoRating(Integer aCDiscoRating) {
		this.aCDiscoRating = aCDiscoRating;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public Integer getpVBreaker1() {
		return pVBreaker1;
	}

	public void setpVBreaker1(Integer pVBreaker1) {
		this.pVBreaker1 = pVBreaker1;
	}

	public Integer getpVBreaker2() {
		return pVBreaker2;
	}

	public void setpVBreaker2(Integer pVBreaker2) {
		this.pVBreaker2 = pVBreaker2;
	}

	public Integer getpVBreaker3() {
		return pVBreaker3;
	}

	public void setpVBreaker3(Integer pVBreaker3) {
		this.pVBreaker3 = pVBreaker3;
	}

	public Integer getpVBreaker4() {
		return pVBreaker4;
	}

	public void setpVBreaker4(Integer pVBreaker4) {
		this.pVBreaker4 = pVBreaker4;
	}

	public Integer getpVBreaker5() {
		return pVBreaker5;
	}

	public void setpVBreaker5(Integer pVBreaker5) {
		this.pVBreaker5 = pVBreaker5;
	}

	public Boolean isExistingBattery() {
		return existingBattery;
	}

	public void setExistingBattery(Boolean existingBattery) {
		this.existingBattery = existingBattery;
	}


	public Boolean isHasImageOfExistingSolarEquipLocations() {
		return hasImageOfExistingSolarEquipLocations;
	}

	public void setHasImageOfExistingSolarEquipLocations(Boolean hasImageOfExistingSolarEquipLocations) {
		this.hasImageOfExistingSolarEquipLocations = hasImageOfExistingSolarEquipLocations;
	}

	public Boolean isProposedBattery() {
		return proposedBattery;
	}

	public void setProposedBattery(Boolean proposedBattery) {
		this.proposedBattery = proposedBattery;
	}


	public String getCircuitstoRelocatetoCriticalLoadsPanel() {
		return circuitstoRelocatetoCriticalLoadsPanel;
	}

	public void setCircuitstoRelocatetoCriticalLoadsPanel(String circuitstoRelocatetoCriticalLoadsPanel) {
		this.circuitstoRelocatetoCriticalLoadsPanel = circuitstoRelocatetoCriticalLoadsPanel;
	}

	public String getBatteryLocation() {
		return batteryLocation;
	}

	public void setBatteryLocation(String batteryLocation) {
		this.batteryLocation = batteryLocation;
	}

	public Long getInverter1Model() {
		return inverter1Model;
	}

	public void setInverter1Model(Long inverter1Model) {
		this.inverter1Model = inverter1Model;
	}

	public Long getInverter2Model() {
		return inverter2Model;
	}

	public void setInverter2Model(Long inverter2Model) {
		this.inverter2Model = inverter2Model;
	}

	public Long getInverter3Model() {
		return inverter3Model;
	}

	public void setInverter3Model(Long inverter3Model) {
		this.inverter3Model = inverter3Model;
	}

	public String getProductionMonitor() {
		return productionMonitor;
	}

	public void setProductionMonitor(String productionMonitor) {
		this.productionMonitor = productionMonitor;
	}

	public String getActiveInternetConnection() {
		return activeInternetConnection;
	}

	public void setActiveInternetConnection(String activeInternetConnection) {
		this.activeInternetConnection = activeInternetConnection;
	}

	public String getModemLocation() {
		return modemLocation;
	}

	public void setModemLocation(String modemLocation) {
		this.modemLocation = modemLocation;
	}

	public String getConnectTheMonitor() {
		return connectTheMonitor;
	}

	public void setConnectTheMonitor(String connectTheMonitor) {
		this.connectTheMonitor = connectTheMonitor;
	}

	public Boolean isHasImageCapturesOfRoof() {
		return hasImageCapturesOfRoof;
	}

	public void setHasImageCapturesOfRoof(Boolean hasImageCapturesOfRoof) {
		this.hasImageCapturesOfRoof = hasImageCapturesOfRoof;
	}

	public String getVerifyRoofMeasurementsPreRoofLayoutProvided() {
		return verifyRoofMeasurementsPreRoofLayoutProvided;
	}

	public void setVerifyRoofMeasurementsPreRoofLayoutProvided(String verifyRoofMeasurementsPreRoofLayoutProvided) {
		this.verifyRoofMeasurementsPreRoofLayoutProvided = verifyRoofMeasurementsPreRoofLayoutProvided;
	}

	public Boolean isHasImageCapturesOfApplicableElevationsViews() {
		return hasImageCapturesOfApplicableElevationsViews;
	}

	public void setHasImageCapturesOfApplicableElevationsViews(Boolean hasImageCapturesOfApplicableElevationsViews) {
		this.hasImageCapturesOfApplicableElevationsViews = hasImageCapturesOfApplicableElevationsViews;
	}

	public Boolean isWireRunOnRoof() {
		return wireRunOnRoof;
	}

	public void setWireRunOnRoof(Boolean wireRunOnRoof) {
		this.wireRunOnRoof = wireRunOnRoof;
	}

	public Boolean isWireRunInAttic() {
		return wireRunInAttic;
	}

	public void setWireRunInAttic(Boolean wireRunInAttic) {
		this.wireRunInAttic = wireRunInAttic;
	}

	public Boolean isTiltupModules() {
		return tiltupModules;
	}

	public void setTiltupModules(Boolean tiltupModules) {
		this.tiltupModules = tiltupModules;
	}

	public String getRoofMaterial() {
		return roofMaterial;
	}

	public void setRoofMaterial(String roofMaterial) {
		this.roofMaterial = roofMaterial;
	}

	public Integer getNumberOfLayers() {
		return numberOfLayers;
	}

	public void setNumberOfLayers(Integer numberOfLayers) {
		this.numberOfLayers = numberOfLayers;
	}

	public Integer getRoofAge() {
		return roofAge;
	}

	public void setRoofAge(Integer roofAge) {
		this.roofAge = roofAge;
	}


	public Long getpVModel() {
		return pVModel;
	}

	public void setpVModel(Long pVModel) {
		this.pVModel = pVModel;
	}

	public String getGableRakeOverhang() {
		return gableRakeOverhang;
	}

	public void setGableRakeOverhang(String gableRakeOverhang) {
		this.gableRakeOverhang = gableRakeOverhang;
	}

	public String getHeightAtGutter() {
		return heightAtGutter;
	}

	public void setHeightAtGutter(String heightAtGutter) {
		this.heightAtGutter = heightAtGutter;
	}

	public String getCrossSectionSize() {
		return crossSectionSize;
	}

	public void setCrossSectionSize(String crossSectionSize) {
		this.crossSectionSize = crossSectionSize;
	}

	public String getCrossSectionSizeOther() {
		return crossSectionSizeOther;
	}

	public void setCrossSectionSizeOther(String crossSectionSizeOther) {
		this.crossSectionSizeOther = crossSectionSizeOther;
	}

	public String getRoofStructureChart() {
		return roofStructureChart;
	}

	public void setRoofStructureChart(String roofStructureChart) {
		this.roofStructureChart = roofStructureChart;
	}

	public String getStanchionMaxSpacing() {
		return stanchionMaxSpacing;
	}

	public void setStanchionMaxSpacing(String stanchionMaxSpacing) {
		this.stanchionMaxSpacing = stanchionMaxSpacing;
	}

	public String getRidgeBeamDepthAtArrays() {
		return ridgeBeamDepthAtArrays;
	}

	public void setRidgeBeamDepthAtArrays(String ridgeBeamDepthAtArrays) {
		this.ridgeBeamDepthAtArrays = ridgeBeamDepthAtArrays;
	}

	public String getMaxSpanAtArraysHS1() {
		return maxSpanAtArraysHS1;
	}

	public void setMaxSpanAtArraysHS1(String maxSpanAtArraysHS1) {
		this.maxSpanAtArraysHS1 = maxSpanAtArraysHS1;
	}

	public String getMaxSpanAtArraysHS2() {
		return maxSpanAtArraysHS2;
	}

	public void setMaxSpanAtArraysHS2(String maxSpanAtArraysHS2) {
		this.maxSpanAtArraysHS2 = maxSpanAtArraysHS2;
	}

	public String getMaxSpanAtArraysInchesHS1() {
		return maxSpanAtArraysInchesHS1;
	}

	public void setMaxSpanAtArraysInchesHS1(String maxSpanAtArraysInchesHS1) {
		this.maxSpanAtArraysInchesHS1 = maxSpanAtArraysInchesHS1;
	}

	public String getMaxSpanAtArraysInchesHS2() {
		return maxSpanAtArraysInchesHS2;
	}

	public void setMaxSpanAtArraysInchesHS2(String maxSpanAtArraysInchesHS2) {
		this.maxSpanAtArraysInchesHS2 = maxSpanAtArraysInchesHS2;
	}

	public String getRafterTrussSpacing() {
		return rafterTrussSpacing;
	}

	public void setRafterTrussSpacing(String rafterTrussSpacing) {
		this.rafterTrussSpacing = rafterTrussSpacing;
	}

	public Boolean isNonRoofCarport() {
		return nonRoofCarport;
	}

	public void setNonRoofCarport(Boolean nonRoofCarport) {
		this.nonRoofCarport = nonRoofCarport;
	}

	public Boolean isNonRoofPatioCover() {
		return nonRoofPatioCover;
	}

	public void setNonRoofPatioCover(Boolean nonRoofPatioCover) {
		this.nonRoofPatioCover = nonRoofPatioCover;
	}

	public String getNonRoofContourSlope() {
		return nonRoofContourSlope;
	}

	public void setNonRoofContourSlope(String nonRoofContourSlope) {
		this.nonRoofContourSlope = nonRoofContourSlope;
	}

	public String getNonRoofPathPoint() {
		return nonRoofPathPoint;
	}

	public void setNonRoofPathPoint(String nonRoofPathPoint) {
		this.nonRoofPathPoint = nonRoofPathPoint;
	}

	public String getNonRoofGradingGrubbing() {
		return nonRoofGradingGrubbing;
	}

	public void setNonRoofGradingGrubbing(String nonRoofGradingGrubbing) {
		this.nonRoofGradingGrubbing = nonRoofGradingGrubbing;
	}

	public String getNonRoofSiteComposition() {
		return nonRoofSiteComposition;
	}

	public void setNonRoofSiteComposition(String nonRoofSiteComposition) {
		this.nonRoofSiteComposition = nonRoofSiteComposition;
	}

	public String getNonRoofElevationStructure() {
		return nonRoofElevationStructure;
	}

	public void setNonRoofElevationStructure(String nonRoofElevationStructure) {
		this.nonRoofElevationStructure = nonRoofElevationStructure;
	}

	public String getNonRoofExistingSecurity() {
		return nonRoofExistingSecurity;
	}

	public void setNonRoofExistingSecurity(String nonRoofExistingSecurity) {
		this.nonRoofExistingSecurity = nonRoofExistingSecurity;
	}

	public String getNonRoofPatioCoverValue() {
		return nonRoofPatioCoverValue;
	}

	public void setNonRoofPatioCoverValue(String nonRoofPatioCoverValue) {
		this.nonRoofPatioCoverValue = nonRoofPatioCoverValue;
	}

	public String getPatioCoverAttachedTypeBeam() {
		return patioCoverAttachedTypeBeam;
	}

	public void setPatioCoverAttachedTypeBeam(String patioCoverAttachedTypeBeam) {
		this.patioCoverAttachedTypeBeam = patioCoverAttachedTypeBeam;
	}

	public String getPatioCoverAttachedTypePosts() {
		return patioCoverAttachedTypePosts;
	}

	public void setPatioCoverAttachedTypePosts(String patioCoverAttachedTypePosts) {
		this.patioCoverAttachedTypePosts = patioCoverAttachedTypePosts;
	}

	public String getPatioCoverFreestandingTypeBeam() {
		return patioCoverFreestandingTypeBeam;
	}

	public void setPatioCoverFreestandingTypeBeam(String patioCoverFreestandingTypeBeam) {
		this.patioCoverFreestandingTypeBeam = patioCoverFreestandingTypeBeam;
	}

	public String getPatioCoverFreestandingTypePosts() {
		return patioCoverFreestandingTypePosts;
	}

	public void setPatioCoverFreestandingTypePosts(String patioCoverFreestandingTypePosts) {
		this.patioCoverFreestandingTypePosts = patioCoverFreestandingTypePosts;
	}

	public String getPatioCoverFreestandingExtendOver() {
		return patioCoverFreestandingExtendOver;
	}

	public void setPatioCoverFreestandingExtendOver(String patioCoverFreestandingExtendOver) {
		this.patioCoverFreestandingExtendOver = patioCoverFreestandingExtendOver;
	}

	public String getPatioCoverFreestandingPastEave() {
		return patioCoverFreestandingPastEave;
	}

	public void setPatioCoverFreestandingPastEave(String patioCoverFreestandingPastEave) {
		this.patioCoverFreestandingPastEave = patioCoverFreestandingPastEave;
	}

	public String getImageOfSiteInformationRafter() {
		return imageOfSiteInformationRafter;
	}

	public void setImageOfSiteInformationRafter(String imageOfSiteInformationRafter) {
		this.imageOfSiteInformationRafter = imageOfSiteInformationRafter;
	}

	public String getImageOfExistingMainPanel() {
		return imageOfExistingMainPanel;
	}

	public void setImageOfExistingMainPanel(String imageOfExistingMainPanel) {
		this.imageOfExistingMainPanel = imageOfExistingMainPanel;
	}

	public String getImageOfExistingSubPanel() {
		return imageOfExistingSubPanel;
	}

	public void setImageOfExistingSubPanel(String imageOfExistingSubPanel) {
		this.imageOfExistingSubPanel = imageOfExistingSubPanel;
	}

	public String getImageBatteryInfo() {
		return imageBatteryInfo;
	}

	public void setImageBatteryInfo(String imageBatteryInfo) {
		this.imageBatteryInfo = imageBatteryInfo;
	}

	public Boolean getIsCanceled() {
		return isCanceled;
	}

	public void setIsCanceled(Boolean isCanceled) {
		this.isCanceled = isCanceled;
	}

	public String getInverterTechnology() {
		return inverterTechnology;
	}

	public void setInverterTechnology(String inverterTechnology) {
		this.inverterTechnology = inverterTechnology;
	}

	public String getNotesOnInverter() {
		return notesOnInverter;
	}

	public void setNotesOnInverter(String notesOnInverter) {
		this.notesOnInverter = notesOnInverter;
	}

	public String getTypeOfBatterySystem() {
		return typeOfBatterySystem;
	}

	public void setTypeOfBatterySystem(String typeOfBatterySystem) {
		this.typeOfBatterySystem = typeOfBatterySystem;
	}

	public String getCriticalLoadPanelLocation() {
		return criticalLoadPanelLocation;
	}

	public void setCriticalLoadPanelLocation(String criticalLoadPanelLocation) {
		this.criticalLoadPanelLocation = criticalLoadPanelLocation;
	}

	public String getWidthSeamsOther() {
		return widthSeamsOther;
	}

	public void setWidthSeamsOther(String widthSeamsOther) {
		this.widthSeamsOther = widthSeamsOther;
	}

	public String getRoofMaterialTypeOther() {
		return roofMaterialTypeOther;
	}

	public void setRoofMaterialTypeOther(String roofMaterialTypeOther) {
		this.roofMaterialTypeOther = roofMaterialTypeOther;
	}

	public Boolean getHasProject() {
		return hasProject;
	}

	public Boolean getSubmitted() {
		return submitted;
	}

	public Boolean getHasProjectSiteImage() {
		return hasProjectSiteImage;
	}

	public Boolean getUtilityMeterOrProposedACDisco() {
		return utilityMeterOrProposedACDisco;
	}

	public Boolean getUnrestrainedAnimal() {
		return unrestrainedAnimal;
	}

	public Boolean getOtheraccessissue() {
		return otheraccessissue;
	}

	public Boolean getHasImageCapturesatRafterTrusses() {
		return hasImageCapturesatRafterTrusses;
	}

	public Boolean getCenterFed() {
		return centerFed;
	}

	public Boolean getUpgradingMainServicePanel() {
		return upgradingMainServicePanel;
	}

	public Boolean getCopperWiresSameAsMainContact() {
		return copperWiresSameAsMainContact;
	}

	public Boolean getCopperWiresDifferentNumber() {
		return copperWiresDifferentNumber;
	}

	public Boolean getAluminumWiresSameAsMainContact() {
		return aluminumWiresSameAsMainContact;
	}

	public Boolean getAluminumWiresDifferentNumber() {
		return aluminumWiresDifferentNumber;
	}

	public Boolean getHasImageAtMSP() {
		return hasImageAtMSP;
	}

	public Boolean getExistingSubpanel() {
		return existingSubpanel;
	}

	public Boolean getTieInPOCIsAtSubpanel() {
		return tieInPOCIsAtSubpanel;
	}

	public Boolean getHasImageCapturesAtSupPanel() {
		return hasImageCapturesAtSupPanel;
	}

	public Boolean getExistingBattery() {
		return existingBattery;
	}

	public Boolean getExistingPVSystemAtSite() {
		return existingPVSystemAtSite;
	}

	public Boolean getProposedBattery() {
		return proposedBattery;
	}

	public Boolean getHasImageOfExistingSolarEquipLocations() {
		return hasImageOfExistingSolarEquipLocations;
	}

	public Boolean getHasImageCapturesOfRoof() {
		return hasImageCapturesOfRoof;
	}

	public Boolean getHasImageCapturesOfApplicableElevationsViews() {
		return hasImageCapturesOfApplicableElevationsViews;
	}

	public Boolean getWireRunOnRoof() {
		return wireRunOnRoof;
	}

	public Boolean getWireRunInAttic() {
		return wireRunInAttic;
	}

	public Boolean getTiltupModules() {
		return tiltupModules;
	}


	public Boolean getNonRoofCarport() {
		return nonRoofCarport;
	}

	public Boolean getNonRoofPatioCover() {
		return nonRoofPatioCover;
	}

	public String getImageOfInternetConnectionRoof() {
		return imageOfInternetConnectionRoof;
	}

	public void setImageOfInternetConnectionRoof(String imageOfInternetConnectionRoof) {
		this.imageOfInternetConnectionRoof = imageOfInternetConnectionRoof;
	}

	public String getImageOfInternetConnectionElevation() {
		return imageOfInternetConnectionElevation;
	}

	public void setImageOfInternetConnectionElevation(String imageOfInternetConnectionElevation) {
		this.imageOfInternetConnectionElevation = imageOfInternetConnectionElevation;
	}

	public Boolean getExistingACDisco() {
		return existingACDisco;
	}

	public void setExistingACDisco(Boolean existingACDisco) {
		this.existingACDisco = existingACDisco;
	}

	public String getOtherVoltageOther() {
		return otherVoltageOther;
	}

	public void setOtherVoltageOther(String otherVoltageOther) {
		this.otherVoltageOther = otherVoltageOther;
	}

	public String getPostSolarRateOther() {
		return postSolarRateOther;
	}

	public void setPostSolarRateOther(String postSolarRateOther) {
		this.postSolarRateOther = postSolarRateOther;
	}

	public Boolean getFrontAndBack() {
		return frontAndBack;
	}

	public void setFrontAndBack(Boolean frontAndBack) {
		this.frontAndBack = frontAndBack;
	}

	public Boolean getCantelever() {
		return cantelever;
	}

	public void setCantelever(Boolean cantelever) {
		this.cantelever = cantelever;
	}

	public Boolean getAttachedToExtWal() {
		return attachedToExtWal;
	}

	public void setAttachedToExtWal(Boolean attachedToExtWal) {
		this.attachedToExtWal = attachedToExtWal;
	}

	public Boolean getAttachedToFascia() {
		return attachedToFascia;
	}

	public void setAttachedToFascia(Boolean attachedToFascia) {
		this.attachedToFascia = attachedToFascia;
	}

	public Boolean getAttachedToSkylifts() {
		return attachedToSkylifts;
	}

	public void setAttachedToSkylifts(Boolean attachedToSkylifts) {
		this.attachedToSkylifts = attachedToSkylifts;
	}

	public Boolean getFreeStanding() {
		return freeStanding;
	}

	public void setFreeStanding(Boolean freeStanding) {
		this.freeStanding = freeStanding;
	}

	public String getRoofOrOpenFrame() {
		return roofOrOpenFrame;
	}

	public void setRoofOrOpenFrame(String roofOrOpenFrame) {
		this.roofOrOpenFrame = roofOrOpenFrame;
	}

	public Integer getSumofexistCircuit() {
		return sumofexistCircuit;
	}

	public void setSumofexistCircuit(Integer sumofexistCircuit) {
		this.sumofexistCircuit = sumofexistCircuit;
	}

	public String getSecondOtherVoltageOther() {
		return secondOtherVoltageOther;
	}

	public void setSecondOtherVoltageOther(String secondOtherVoltageOther) {
		this.secondOtherVoltageOther = secondOtherVoltageOther;
	}

	public String getOtherPatioCoverAttachedTypeBeam() {
		return otherPatioCoverAttachedTypeBeam;
	}

	public void setOtherPatioCoverAttachedTypeBeam(String otherPatioCoverAttachedTypeBeam) {
		this.otherPatioCoverAttachedTypeBeam = otherPatioCoverAttachedTypeBeam;
	}

	public String getOtherPatioCoverFreestandingTypeBeam() {
		return otherPatioCoverFreestandingTypeBeam;
	}

	public void setOtherPatioCoverFreestandingTypeBeam(String otherPatioCoverFreestandingTypeBeam) {
		this.otherPatioCoverFreestandingTypeBeam = otherPatioCoverFreestandingTypeBeam;
	}

	public String getOtherPatioCoverFreestandingTypePosts() {
		return otherPatioCoverFreestandingTypePosts;
	}

	public void setOtherPatioCoverFreestandingTypePosts(String otherPatioCoverFreestandingTypePosts) {
		this.otherPatioCoverFreestandingTypePosts = otherPatioCoverFreestandingTypePosts;
	}

	public String getOtherPatioCoverAttachedTypePosts() {
		return otherPatioCoverAttachedTypePosts;
	}

	public void setOtherPatioCoverAttachedTypePosts(String otherPatioCoverAttachedTypePosts) {
		this.otherPatioCoverAttachedTypePosts = otherPatioCoverAttachedTypePosts;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public Boolean getRoofMounted() {
		return roofMounted;
	}

	public void setRoofMounted(Boolean roofMounted) {
		this.roofMounted = roofMounted;
	}

	public Boolean getGroundMounted() {
		return groundMounted;
	}

	public void setGroundMounted(Boolean groundMounted) {
		this.groundMounted = groundMounted;
	}

	public Boolean getCarportMounted() {
		return carportMounted;
	}

	public void setCarportMounted(Boolean carportMounted) {
		this.carportMounted = carportMounted;
	}

	public String getTextOther() {
		return textOther;
	}

	public void setTextOther(String textOther) {
		this.textOther = textOther;
	}

	public String getSubPanelBreakerOCPD() {
		return subPanelBreakerOCPD;
	}

	public void setSubPanelBreakerOCPD(String subPanelBreakerOCPD) {
		this.subPanelBreakerOCPD = subPanelBreakerOCPD;
	}

	public String getRoofRafterOther() {
		return roofRafterOther;
	}

	public void setRoofRafterOther(String roofRafterOther) {
		this.roofRafterOther = roofRafterOther;
	}

	public String getSecroofRafterOther() {
		return secroofRafterOther;
	}

	public void setSecroofRafterOther(String secroofRafterOther) {
		this.secroofRafterOther = secroofRafterOther;
	}

	public String getProjectJurisOther() {
		return projectJurisOther;
	}

	public void setProjectJurisOther(String projectJurisOther) {
		this.projectJurisOther = projectJurisOther;
	}

	public String getNotesExistingPvSystem() {
		return notesExistingPvSystem;
	}

	public void setNotesExistingPvSystem(String notesExistingPvSystem) {
		this.notesExistingPvSystem = notesExistingPvSystem;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public String getNetworkPassword() {
		return networkPassword;
	}

	public void setNetworkPassword(String networkPassword) {
		this.networkPassword = networkPassword;
	}

	public String getRoofCondition() {
		return roofCondition;
	}

	public void setRoofCondition(String roofCondition) {
		this.roofCondition = roofCondition;
	}

	public String getRoofNotes() {
		return roofNotes;
	}

	public void setRoofNotes(String roofNotes) {
		this.roofNotes = roofNotes;
	}

	public String getMeasurmentsOfArea() {
		return measurmentsOfArea;
	}

	public void setMeasurmentsOfArea(String measurmentsOfArea) {
		this.measurmentsOfArea = measurmentsOfArea;
	}

	public String getNotesGroundMount() {
		return notesGroundMount;
	}

	public void setNotesGroundMount(String notesGroundMount) {
		this.notesGroundMount = notesGroundMount;
	}

	public String getNotesOnCarpotOrPatiot() {
		return notesOnCarpotOrPatiot;
	}

	public void setNotesOnCarpotOrPatiot(String notesOnCarpotOrPatiot) {
		this.notesOnCarpotOrPatiot = notesOnCarpotOrPatiot;
	}

	public String getCityOther() {
		return cityOther;
	}

	public void setCityOther(String cityOther) {
		this.cityOther = cityOther;
	}

	public String getOtherMainPanelMake() {
		return otherMainPanelMake;
	}

	public void setOtherMainPanelMake(String otherMainPanelMake) {
		this.otherMainPanelMake = otherMainPanelMake;
	}

	public Boolean getOtherMounted() {
		return otherMounted;
	}

	public void setOtherMounted(Boolean otherMounted) {
		this.otherMounted = otherMounted;
	}

	public Long getpVModelCarpotPatio() {
		return pVModelCarpotPatio;
	}

	public void setpVModelCarpotPatio(Long pVModelCarpotPatio) {
		this.pVModelCarpotPatio = pVModelCarpotPatio;
	}

	public Long getpVModelNonRoof() {
		return pVModelNonRoof;
	}

	public void setpVModelNonRoof(Long pVModelNonRoof) {
		this.pVModelNonRoof = pVModelNonRoof;
	}

	public String getUtilityCompanyNameOther() {
		return utilityCompanyNameOther;
	}

	public void setUtilityCompanyNameOther(String utilityCompanyNameOther) {
		this.utilityCompanyNameOther = utilityCompanyNameOther;
	}

	public String getMeasOfAreaCarpot() {
		return measOfAreaCarpot;
	}

	public void setMeasOfAreaCarpot(String measOfAreaCarpot) {
		this.measOfAreaCarpot = measOfAreaCarpot;
	}

	public String getRafterTrussSpOther() {
		return rafterTrussSpOther;
	}

	public void setRafterTrussSpOther(String rafterTrussSpOther) {
		this.rafterTrussSpOther = rafterTrussSpOther;
	}

	public String getHomeOwnLastName() {
		return homeOwnLastName;
	}

	public void setHomeOwnLastName(String homeOwnLastName) {
		this.homeOwnLastName = homeOwnLastName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getMainContactPhone() {
		return mainContactPhone;
	}

	public void setMainContactPhone(String mainContactPhone) {
		this.mainContactPhone = mainContactPhone;
	}

	public String getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(String otherPhone) {
		this.otherPhone = otherPhone;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Integer getMeanHeight() {
		return meanHeight;
	}

	public void setMeanHeight(Integer meanHeight) {
		this.meanHeight = meanHeight;
	}

	public String getNotesOnExistingBatterySystem() {
		return notesOnExistingBatterySystem;
	}

	public void setNotesOnExistingBatterySystem(String notesOnExistingBatterySystem) {
		this.notesOnExistingBatterySystem = notesOnExistingBatterySystem;
	}

	public String getOtherConnectionType() {
		return otherConnectionType;
	}

	public void setOtherConnectionType(String otherConnectionType) {
		this.otherConnectionType = otherConnectionType;
	}

	public String getMspbusbarRating() {
		return mspbusbarRating;
	}

	public void setMspbusbarRating(String mspbusbarRating) {
		this.mspbusbarRating = mspbusbarRating;
	}

	public String getImageOfCarPortArrayLocation() {
		return imageOfCarPortArrayLocation;
	}

	public void setImageOfCarPortArrayLocation(String imageOfCarPortArrayLocation) {
		this.imageOfCarPortArrayLocation = imageOfCarPortArrayLocation;
	}

	public Boolean getPatioMounted() {
		return patioMounted;
	}

	public void setPatioMounted(Boolean patioMounted) {
		this.patioMounted = patioMounted;
	}
	
}
