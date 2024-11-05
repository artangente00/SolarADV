package com.PlayGroundAdv.Solar.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "SiteSurveyReqFieldSettingEntity")
public class SiteSurveyReqFieldSettingEntity {


	private static final long serialVersionUID = 1L;
	
	
	@Id
	@SequenceGenerator(name="SiteSurveyReqFieldSettingsequence",
			           sequenceName="SiteSurveyRequiredFieldSettingsequence",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SiteSurveyReqFieldSettingsequence")  
	private  Long id;
	
	
	
	@Column(name="STATE_REQ_FIELD")
	private  String statereqfield;
	
	
	public Boolean getRequired() {
		return required;
	}


	public void setRequired(Boolean required) {
		this.required = required;
	}


	@Column(name="HAS_REQ_FIELD")
	private  Boolean required;
	
	/////------------- basic information -----------/////
	
	@Column(name="HOME_OWN_NAME")
	private  Boolean homeOwnName;
	
	
	/////------------- site information -----------/////
	
	@Column(name="Basic_Type_Of_System")
	private  Boolean basicTypeOfSystem; 

	@Column(name="CONTACT_NAME")
	private  Boolean contactName; 

	@Column(name="STREET_ADDRESS")
	private  Boolean streetAddress ;

	@Column(name="CITY")
	private  Boolean city ;
	
	@Column(name="CITY_OTHER")
	private  Boolean cityOther ;
		
	@Column(name="STATE")
	private  Boolean state ;
		
	@Column(name="ZIP")
	private  Boolean zIP ;
	
	@Column(name="MAIN_CONTACT_PHONE")
	private Boolean mainContactPhone ;

	@Column(name="OTHER_PHONE")
	private Boolean otherPhone ;

	@Column(name="EMAIL_ADDRESS")
	private Boolean emailAddress ;
		
	@Column(name="HOA")
	private  Boolean hoa ;
		
	@Column(name="PERMITTING_AUTHORITY")
	private  Boolean permittingAuthority ;
	
	@Column(name="PROJECT_JURISOTHER")
	private  Boolean projectJurisOther ;

	@Column(name="LEGAL_OWNER_NAME")
	private  Boolean legalOwnerName ;
		
	@Column(name="PARCEL_NUMBER")
	private  Boolean parcelNumber; 
		
	@Column(name="ROOF_MATERIAL_TYPE")
	private  Boolean roofMaterialType ;

	@Column(name="WIDTH_SEAMS")
	private  Boolean widthSeams;
		
	@Column(name="RISK_CATEGORY")
	private  Boolean riskCategory; 
		
	@Column(name="OTHER_RISK_CATEGORY")
	private  Boolean otherRiskCategory; 
		
	@Column(name="BUILDING_OCCUPANCY")
	private  Boolean buildingOccupancy; 
	
	@Column(name="OTHER_BUILDING_OCCUPANCY")
	private  Boolean otherBuildingOccupancy; 
		
	@Column(name="NUMBER_OF_STORIES_")
	private  Boolean numberOfStories;
	
	@Column(name="OTHER_TALL_STRUCTURE")
	private  Boolean otherTallStructure;
	
	@Column(name="MEAN_HEIGHT")
	private Boolean meanHeight;
		
	@Column(name="PROJECT_EQUIPMENT_STAGING_LOCATION")
	private  Boolean projectEquipmentStagingLocation ;
		
	@Column(name="OWNER_PREFERRED_ROOF_ACCESS_LOCATION")
	private  Boolean ownerPreferredRoofAccessLocation;
		
	@Column(name="ACCESS_ISSUES_WITH_METER_OR_PROPOSED_AC_DISCO")
	private  Boolean accessIssuesWith_MeterOrProposedACDisco; 
		
	@Column(name="DESCRIBE_ACCESS_ISSUES")
	private  Boolean describeAccessIssues ;
		
	@Column(name="CONTACT_PERSON_FOR_UTILITY")
	private  Boolean contactpersonforutility ;
		
	@Column(name="OTHER_CONTACT_PERSON")
	private  Boolean othercontactperson ;
		
	@Column(name="CONTACT_PHONE")
	private  Boolean contactphone ;
		
	@Column(name="OTHER_CONTACT_PHONE")
	private  Boolean othercontactphone ;

	@Column(name="IMAGE_SI_RAFTER")
	private  Boolean imageOfSiteInformationRafter;
	
	
	/////------------- existing main panel -----------/////
	
	@Column(name="MAIN_PANEL_MAKE")
	private  Boolean mainPanelMake ;
	
	@Column(name="OTHER_MAIN_PANEL_MAKE")
	private  Boolean otherMainPanelMake ;
	
	@Column(name="MAIN_PANEL_MODEL")
	private  Boolean mainPanelModel ;
	
	@Column(name="VOLTAGE")
	private  Boolean voltage ;
	
	@Column(name="OTHER_VOLTAGE")
	private  Boolean otherVoltage ;
	
	@Column(name="MSP_BUSBAR_RATING")
	private  Boolean mspbusbarRating;
	
	@Column(name="OTHER_MSP_BUSBAR_RATING")
	private  Boolean otherMSPBusbarRating;
	
	@Column(name="MSP_MAIN_BREAKER_RATING")
	private  Boolean mSPMainBreakerRating;
	
	@Column(name="OTHER_MSP_MAIN_BREAKER_RATING")
	private  Boolean otherMSPMainBreakerRating;
	
	@Column(name="OPEN_BREAKER_SLOTS")
	private  Boolean openBreakerSlots ;
	
	@Column(name="SCIR")
	private  Boolean sCIR ;
	
	@Column(name="MAIN_BREAKER_FEED_GAUGE")
	private  Boolean mainBreakerFeedGauge;
	
	@Column(name="OTHER_MAIN_BREAKER_FEED_GAUGE")
	private  Boolean otherMainBreakerFeedGauge;
	
	@Column(name="UTILITY_FEEDER_GAUGE")
	private  Boolean utilityFeederGauge;
	
	@Column(name="GROUND_ACCESSIBLE")
	private  Boolean groundAccessible;
	
	@Column(name="UNDERGROUND_OR_OVERHEAD_FEED")
	private  Boolean undergroundOrOverheadFeed;

	@Column(name="IMAGE_EXIST_MAIN_PANEL")
	private  Boolean imageOfExistingMainPanel;
	
	
	/////------------- existing subpanel -----------/////
	
	@Column(name="SUBPANEL_MAKE")
	private  Boolean subpanelMake;
	
	@Column(name="SUBPANEL_MODEL")
	private  Boolean subpanelModel;
	
	@Column(name="SUBPANEL_VOLTAGE")
	private  Boolean subpanelVoltage;
	
	@Column(name="OTHER_SUBPANEL_VOLTAGE")
	private  Boolean otherSubpanelVoltage;
	
	@Column(name="SUBPANEL_BUSBAR_RATING")
	private  Boolean subpanelBusbarRating;
	
	@Column(name="OTHER_SUBPANEL_BUSBAR_RATING")
	private  Boolean otherSubpanelBusbarRating;
	
	@Column(name="SUBPANEL_MAIN_BREAKER_RATING")
	private  Boolean subpanelMainBreakerRating;
	
	@Column(name="OTHER_SUBPANEL_MAIN_BREAKER_RATING")
	private  Boolean otherSubpanelMainBreakerRating;
	
	@Column(name="OPEN_BREAKER_SLOTS_IN_SUBPANEL")
	private  Boolean openBreakerSlotsinSubpanel;
	
	@Column(name="SUBPANEL_FEEDER_GAUGE")
	private  Boolean subpanelFeederGauge;
	
	@Column(name="Other_SUBPANEL_FEEDER_GAUGE")
	private  Boolean otherSubpanelFeederGauge;

	
	/////------------- site note -----------/////
	 
	@Column(name="SITE_NOTES")
	private  Boolean siteNotes;

	
	/////------------- utility information -----------/////
	
	@Column(name="UTILITY_CO_NAME")
	private  Boolean utilityCoName;
	
	@Column(name="UTILITY_COMPANY_NAME_OTHER")
	private  Boolean utilityCompanyNameOther;
	
	@Column(name="METER_NUMBER")
	private Boolean meterNumber;
	
	@Column(name="NEM_TYPE")
	private  Boolean nEMType;
	
	@Column(name="OTHER_NEM_TYPE")
	private  Boolean otherNEMType;
	
	@Column(name="POST_SOLAR_RATE")
	private  Boolean postSolarRate;
	
	@Column(name="USAGE_HISTORY_OFFSET")
	private  Boolean usageHistoryOffset;
	
	@Column(name="PACE_ENTITY")
	private  Boolean paceEntity;
	
	@Column(name="PACE_ENTITY_OTHER")
	private  Boolean paceEntityOther;
	
	@Column(name="AC_DISCO_WILL_BE_MORE_THAN_10_FROM_UTILITY_METER")
	private  Boolean aCDiscoWillbemorethan10FromUtilityMeter;
	
	@Column(name="AT_LEAST_4_MONTHS_OF_ELECTRIC_BILLING_HISTORY")
	private  Boolean atLeast4MonthsOfelectricbillinghistory ;
	
	@Column(name="SQUARE_FEET_OF_LIVING_AREA")
	private  Boolean squareFeetOfLivingArea;
	
	@Column(name="RECENT_ANNUAL_USAGE")
	private  Boolean recentAnnualUsage;
	
	@Column(name="NUMBER_OF_ELECTRIC_VEHICLES")
	private  Boolean numberOfElectricVehicles; 

	
	/////------------- existing pV system at site -----------/////
	
	@Column(name="PV_SYSTEM_MAKE")
	private  Boolean pVSystemMake;
	
	@Column(name="PV_SYSTEM_MODEL")
	private  Boolean pVSystemModel;
	
	@Column(name="PV_QUANTITY")
	private  Boolean pVQuantity;
	
	@Column(name="GRID_TIED_INVERTER_MAKE")
	private  Boolean gridTiedInverterMake;
	
	@Column(name="GRID_TIED_INVERTER_MODEL")
	private  Boolean gridTiedInverterModel;
	
	@Column(name="GRID_TIED_INVERTER_QUANTITY")
	private  Boolean gridTiedInverterQuantity;
	
	@Column(name="EXISTING_AC_DISCONNECT")
	private  Boolean existingACDisconnect;
	
	@Column(name="AC_DISCO_MAKE")
	private  Boolean aCDiscoMake;
	
	@Column(name="AC_DISCO_MODEL")
	private  Boolean aCDiscoModel ;
	
	@Column(name="AC_DISCO_RATING")
	private  Boolean aCDiscoRating ;
	
	@Column(name="CONNECTION_TYPE")
	private  Boolean connectionType;
	
	@Column(name="OTHER_CONNECTION_TYPE")
	private Boolean otherConnectionType;
	
	@Column(name="PV_BREAKER_1")
	private  Boolean pVBreaker1;
	
	@Column(name="PV_BREAKER_2")
	private  Boolean pVBreaker2;
	
	@Column(name="PV_BREAKER_3")
	private  Boolean pVBreaker3;
	
	@Column(name="PV_BREAKER_4")
	private  Boolean pVBreaker4;
	
	@Column(name="PVBREAKER5")
	private  Boolean pVBreaker5; 
	
	@Column(name="EXISTING_AC_DISCO")
	private  Boolean existingACDisco; 
	
	@Column(name="NOTES_ON_EXISTING_BATTERY_SYSTEM")
	private Boolean notesOnExistingBatterySystem;

	
	/////------------- battery info -----------/////
	
	@Column(name="INVERTER_TECHNOLOGY")
	private Boolean inverterTechnology;
	
	@Column(name="INVERTER_1_MODEL")
	private  Boolean inverter1Model;
	
	@Column(name="INVERTER_2_MODEL")
	private  Boolean inverter2Model;
	
	@Column(name="INVERTER_3_MODEL")
	private  Boolean inverter3Model;
	
	@Column(name="NOTES_ON_INVERTER")
	private Boolean notesOnInverter;
	
	@Column(name="TYPE_OF_BATTERY_SYSTEM")
	private Boolean typeOfBatterySystem;
	
	@Column(name="BATTERY_LOCATION")
	private Boolean batteryLocation;
	
	@Column(name="CIRCUITS_TO_RELOCATE_TO_CRITICAL_LOADS_PANEL")
	private Boolean circuitstoRelocatetoCriticalLoadsPanel;
	
	@Column(name="CRITICAL_LOAD_PANEL_LOCATION")
	private Boolean criticalLoadPanelLocation;
	
	@Column(name="IMAGE_BATTERY_INFO")
	private  Boolean imageBatteryInfo;
	
	/////------------- internet connection for production monitoring -----------/////	
	
	@Column(name="PRODUCTION_MONITOR")
	private  Boolean productionMonitor;
	
	@Column(name="ACTIVE_INTERNET_CONNECTION")
	private  Boolean activeInternetConnection;
	
	@Column(name="MODEM_LOCATION")
	private  Boolean modemLocation;
	
	@Column(name="CONNECT_THE_MONITOR")
	private  Boolean connectTheMonitor ;
	
	@Column(name="VERIFY_ROOF_MEASUREMENTS_PRE_ROOF_LAYOUT_PROVIDED")
	private  Boolean verifyRoofMeasurementsPreRoofLayoutProvided;

	@Column(name="IMAGE_IC_ROOF")
	private  Boolean imageOfInternetConnectionRoof;
	
	@Column(name="IMAGE_IC_ELEVATION")
	private  Boolean imageOfInternetConnectionElevation;
	
	/////------------- roof mount -----------/////
	
	@Column(name="ROOF_MATERIAL")
	private  Boolean roofMaterial;
	
	@Column(name="NUMBER_OF_LAYERS")
	private  Boolean numberOfLayers;
	
	@Column(name="ROOF_AGE")
	private  Boolean roofAge;
	
	@Column(name="PV_MODEL")
	private  Boolean pVModel;
	
	@Column(name="GABLE_RAKE_OVERHANG")
	private  Boolean gableRakeOverhang;
	
	@Column(name="HEIGHT_AT_GUTTER")
	private  Boolean heightAtGutter;
	
	@Column(name="CROSS_SECTION_SIZE")
	private Boolean crossSectionSize;
	
	@Column(name="CROSS_SECTION_SIZE_OTHER")
	private Boolean crossSectionSizeOther;
	
	@Column(name="ROOF_STRUCTURE_CHART")
	private  Boolean roofStructureChart;
	
	@Column(name="STANCHION_MAX_SPACING")
	private  Boolean stanchionMaxSpacing ;
	
	@Column(name="RIDGE_BEAM_DEPTH_AT_ARRAYS")
	private  Boolean ridgeBeamDepthAtArrays;
	
	@Column(name="MAX_SPAN_AT_ARRAYS_HS1")
	private  Boolean maxSpanAtArraysHS1;
	
	@Column(name="MAX_SPAN_AT_ARRAYS_HS2")
	private  Boolean maxSpanAtArraysHS2;
	
	@Column(name="RAFTER_TRUSS_SPACING")
	private  Boolean rafterTrussSpacing;
	
	@Column(name="RAFTER_TRUSS_OTHER")
	private  Boolean rafterTrussSpOther ;
	
	@Column(name="ROOF_MODULE_AZIMUTH")
	private Boolean roofModuleAzimuth ;
	
	@Column(name="ROOF_PITCH")
	private Boolean roofPitch ;
	
	@Column(name="ROOF_TILT_KIT_USED")
	private Boolean roofTiltKitUsed ;
	
	@Column(name="ROOF_EAVE_OVERHANG")
	private Boolean roofEaveOverhang ;
	
	@Column(name = "OTHER_ROOF_EAVE_OVERHANG")
	private Boolean otherRoofEaveOverhang ;
	
	@Column(name="ROOF_MODULE_TILT")
	private Boolean roofModuleTilt ;
	
	@Column(name="ROOF_MODULE_QTY")
	private Boolean roofModuleQty ;
    
          
	
	/////------------- non roof mount -----------/////
	
	@Column(name="CONTOUR_SLOPE")
	private  Boolean nonRoofContourSlope;
	
	@Column(name="PATH_POINT")
	private  Boolean nonRoofPathPoint;
	
	@Column(name="GRADING_GRUBBING")
	private  Boolean nonRoofGradingGrubbing;
	
	@Column(name="SITE_COMPOSITION")
	private  Boolean nonRoofSiteComposition;
	
	@Column(name="ELEVATION_STRUCTURE")
	private  Boolean nonRoofElevationStructure;
	
	@Column(name="EXISTING_SECURITY")
	private  Boolean nonRoofExistingSecurity;
	
	@Column(name="PATIO_COVER_VAL")
	private  Boolean nonRoofPatioCoverValue;	
	
	@Column(name="NON_ROOF_MODULE_AZIMUTH")
	private Boolean nonRoofModuleAzimuth;
	
	@Column(name="NON_ROOF_MODULE_TILT")
	private Boolean nonRoofModuleTilt;
	
	@Column(name="NON_ROOF_MODULE_QTY")
	private Boolean nonRoofModuleQty;
	
	/////------------- Patio cover attached -----------/////
	
	
	@Column(name="ATTACHED_TYPE_BEAM")
	private  Boolean patioCoverAttachedTypeBeam;	
	
	
	@Column(name="ATTACHED_TYPE_POSTS")
	private  Boolean patioCoverAttachedTypePosts;	
	
	
	/////------------- Patio cover freestanding -----------/////
	
	
	@Column(name="FREESTANDING_TYPE_BEAM")
	private  Boolean patioCoverFreestandingTypeBeam;	
	
	
	@Column(name="FREESTANDING_TYPE_POSTS")
	private  Boolean patioCoverFreestandingTypePosts;	
	
	
	@Column(name="FREESTANDING_EXTAND_OVER")
	private  Boolean patioCoverFreestandingExtendOver;	
	
	
	@Column(name="FREESTANDING_PAST_EAVE")
	private  Boolean patioCoverFreestandingPastEave;
	
	@Column(name="PATIO_MODULE_AZIMUTH")
	private Boolean patioModuleAzimuth;
	
	@Column(name="PATIO_ROOF_PITCH")
	private Boolean patioroofPitch;
	
	@Column(name="PATIO_ROOF_TILT_KIT_USED")
	private Boolean patioroofTiltKitUsed;
	
	@Column(name="PATIO_ROOF_EAVE_OVERHANG")
	private Boolean patioroofEaveOverhang;
	
	@Column(name="OTHER_PATIO_EAVE_OVERHANG")
	private Boolean otherPatioEaveOverhang;
	
	@Column(name="PATIO_ROOF_MODULE_TILT")
	private Boolean patioroofModuleTilt;
	
	@Column(name="PATIO_ROOF_MODULE_QTY")
	private Boolean patioroofModuleQty;
	//*********************** others ****************************//
	
	@Column(name="OTHER_VOLTAGE_OTHER")
	private  Boolean otherVoltageOther;	
	
	
	@Column(name="WIDTHSEAMS_OTHER")
	private  Boolean widthSeamsOther;	
	
	
	@Column(name="ROOF_MATERIALTYPE_OTHER")
	private  Boolean roofMaterialTypeOther;	
	
	
	@Column(name="POST_SOLARRATE_OTHER")
	private  Boolean postSolarRateOther;	
	
	
	@Column(name="OTHER_PATIO_COVER_ATTACHEDTYPE_BEAM")
	private  Boolean otherPatioCoverAttachedTypeBeam;	
	
	
	@Column(name="OTHER_PATIO_COVERATTACHED_TYPE_POSTS")
	private  Boolean otherPatioCoverAttachedTypePosts;	
	
	
	@Column(name="OTHER_PATIO_COVER_FREE_STANDING_TYPE_BEAM")
	private  Boolean otherPatioCoverFreestandingTypeBeam;	
	
	
	@Column(name="OTHER_PATIO_COVER_FREE_STANDING_TYPE_POSTS")
	private  Boolean otherPatioCoverFreestandingTypePosts;


	@Column(name="UTILITY_METER_OR_PROPOSED_AC_DISCO")
	private  Boolean utilityMeterOrProposedACDisco;

	@Column(name="IMAGE_CARPORT_ARRAY_LOCATION")
	private Boolean imageOfCarPortArrayLocation;
	
	@Column(name="IMAGE_UTILITY_INFORMATION")
	private Boolean imageOfutilityInformation;
	
	@Column(name="IMAGE_EXISTING_PV")
	private Boolean imageOfExistingPV;
	
	@Column(name="IMAGE_ROOF_ATTIC")
	private Boolean imageOfRoofAttic;
	
	@Column(name="IMAGE_ARRAY_LOCATION")
	private Boolean imageOfArrayLocation;
	
	@Column(name="SELECT_IF_ATTACHED")
	private Boolean selectIfAttach;
	
	@Column(name="ROOF_OPEN_FRAME")
	private Boolean roofOrOpenFrame;
	
	@Column(name="SUM_EXIST_CIRCUIT")
	private Boolean sumofexistCircuit;
	
	@Column(name="NOTES_GROUND_MOUNT")
	private Boolean notesGroundMount;
	
	@Column(name="ADDRESS_LINE_2")
	private Boolean addressLine2 ;
	
	@Column(name="SUB_PANEL_BREAKER_OCPD")
	private Boolean subPanelBreakerOCPD;
	
	@Column(name="SECOND_OTHER_VOLTAGE_OTHER")
	private Boolean secondOtherVoltageOther;
	
	@Column(name="NOTES_EXISTING_PV_SYSTEM")
	private Boolean notesExistingPvSystem ;
	
	@Column(name="NETWORK_NAME")
	private Boolean networkName;
	
	@Column(name="NETWORK_PASSWORD")
	private Boolean networkPassword;
	
	@Column(name="ROOF_CONDITION")
	private Boolean roofCondition;
	
	@Column(name="ROOF_NOTES")
	private Boolean roofNotes;
	
	@Column(name="MEASURMENTS_OF_AREA")
	private Boolean measurmentsOfArea;
	
	@Column(name="NOTES_CARPOT_PATIOT")
	private Boolean notesOnCarpotOrPatiot;
	
	@Column(name="NON_ROOF_CARPOT")
	private Boolean nonRoofCarport;
	
	@Column(name="NON_ROOF_PATIOT_COVER")
	private Boolean nonRoofPatioCover;
	
	@Column(name="WIRE_RUN_ROOF")
	private Boolean wireRunOnRoof;
	
	@Column(name="WIRE_RUN_ATTIC")
	private Boolean wireRunInAttic;
	
	@Column(name="TILT_UP_MODULES")
	private Boolean tiltupModules;
	
	@Column(name="EXISTING_BATTERY")
	private Boolean existingBattery;
	
	@Column(name="BATTERY_INVERTER_IS_GRID_TIED")
	private Boolean batteryInverterIsGridTiedInverter;
	
	@Column(name="PROPOSED_BATTERY")
	private Boolean proposedBattery;
	
	@Column(name="EXISTING_PV_SYSTEM")
	private Boolean existingPVSystemAtSite;
	
	@Column(name="IMAGE_UTILITY_INFO")
	private Boolean imageOfUtilityInfo;
	
	@Column(name="IMAGE_SITE_NOTES")
	private Boolean imageOfSiteNotes;
	
	@Column(name="EXISTING_SUB_PANEL")
	private Boolean existingSubpanel;
	
	@Column(name="TIE_POC_AT_SUB_PANEL")
	private Boolean tieInPOCIsAtSubpanel;
	
	@Column(name="IMAGE_EXISTING_SUB_PANEL")
	private Boolean imageOfExistingSubPanel;
	
	@Column(name="CENTER_FED")
	private Boolean centerFed;
	
	@Column(name="UPGRADING_MAIN_PANEL")
	private Boolean upgradingMainServicePanel;
	
	@Column(name="PV_MODEL_CARPOT_PATIO")
	private Boolean pVModelCarpotPatio;
	
	@Column(name="PV_MODEL_NON_ROOF")
	private Boolean pVModelNonRoof;
	
	@Column(name="MEAS_AREA_CARPOT")
	private Boolean measOfAreaCarpot;
	
	@Column(name="HOMEOWNER_LAST_NAME")
	private Boolean homeOwnLastName;
	
	@Column(name="RPROJECT_NAME")
	private Boolean projectName;
	
	
	//***************** getter & setter methods *************************//
	
	
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getStatereqfield() {
		return statereqfield;
	}


	public void setStatereqfield(String statereqfield) {
		this.statereqfield = statereqfield;
	}


	public Boolean getHomeOwnName() {
		return homeOwnName;
	}


	public void setHomeOwnName(Boolean homeOwnName) {
		this.homeOwnName = homeOwnName;
	}

	public Boolean getBasicTypeOfSystem() {
		return basicTypeOfSystem;
	}


	public void setBasicTypeOfSystem(Boolean basicTypeOfSystem) {
		this.basicTypeOfSystem = basicTypeOfSystem;
	}


	public Boolean getContactName() {
		return contactName;
	}


	public void setContactName(Boolean contactName) {
		this.contactName = contactName;
	}


	public Boolean getStreetAddress() {
		return streetAddress;
	}


	public void setStreetAddress(Boolean streetAddress) {
		this.streetAddress = streetAddress;
	}


	public Boolean getCity() {
		return city;
	}


	public void setCity(Boolean city) {
		this.city = city;
	}


	public Boolean getState() {
		return state;
	}


	public void setState(Boolean state) {
		this.state = state;
	}


	public Boolean getzIP() {
		return zIP;
	}


	public void setzIP(Boolean zIP) {
		this.zIP = zIP;
	}


	public Boolean getHoa() {
		return hoa;
	}


	public void setHoa(Boolean hoa) {
		this.hoa = hoa;
	}


	public Boolean getPermittingAuthority() {
		return permittingAuthority;
	}


	public void setPermittingAuthority(Boolean permittingAuthority) {
		this.permittingAuthority = permittingAuthority;
	}


	public Boolean getProjectJurisOther() {
		return projectJurisOther;
	}


	public void setProjectJurisOther(Boolean projectJurisOther) {
		this.projectJurisOther = projectJurisOther;
	}


	public Boolean getLegalOwnerName() {
		return legalOwnerName;
	}


	public void setLegalOwnerName(Boolean legalOwnerName) {
		this.legalOwnerName = legalOwnerName;
	}


	public Boolean getParcelNumber() {
		return parcelNumber;
	}


	public void setParcelNumber(Boolean parcelNumber) {
		this.parcelNumber = parcelNumber;
	}


	public Boolean getRoofMaterialType() {
		return roofMaterialType;
	}


	public void setRoofMaterialType(Boolean roofMaterialType) {
		this.roofMaterialType = roofMaterialType;
	}


	public Boolean getWidthSeams() {
		return widthSeams;
	}


	public void setWidthSeams(Boolean widthSeams) {
		this.widthSeams = widthSeams;
	}


	public Boolean getRiskCategory() {
		return riskCategory;
	}


	public void setRiskCategory(Boolean riskCategory) {
		this.riskCategory = riskCategory;
	}


	public Boolean getOtherRiskCategory() {
		return otherRiskCategory;
	}


	public void setOtherRiskCategory(Boolean otherRiskCategory) {
		this.otherRiskCategory = otherRiskCategory;
	}


	public Boolean getBuildingOccupancy() {
		return buildingOccupancy;
	}


	public void setBuildingOccupancy(Boolean buildingOccupancy) {
		this.buildingOccupancy = buildingOccupancy;
	}


	public Boolean getOtherBuildingOccupancy() {
		return otherBuildingOccupancy;
	}


	public void setOtherBuildingOccupancy(Boolean otherBuildingOccupancy) {
		this.otherBuildingOccupancy = otherBuildingOccupancy;
	}


	public Boolean getNumberOfStories() {
		return numberOfStories;
	}


	public void setNumberOfStories(Boolean numberOfStories) {
		this.numberOfStories = numberOfStories;
	}


	public Boolean getOtherTallStructure() {
		return otherTallStructure;
	}


	public void setOtherTallStructure(Boolean otherTallStructure) {
		this.otherTallStructure = otherTallStructure;
	}


	public Boolean getProjectEquipmentStagingLocation() {
		return projectEquipmentStagingLocation;
	}


	public void setProjectEquipmentStagingLocation(Boolean projectEquipmentStagingLocation) {
		this.projectEquipmentStagingLocation = projectEquipmentStagingLocation;
	}


	public Boolean getOwnerPreferredRoofAccessLocation() {
		return ownerPreferredRoofAccessLocation;
	}


	public void setOwnerPreferredRoofAccessLocation(Boolean ownerPreferredRoofAccessLocation) {
		this.ownerPreferredRoofAccessLocation = ownerPreferredRoofAccessLocation;
	}


	public Boolean getAccessIssuesWith_MeterOrProposedACDisco() {
		return accessIssuesWith_MeterOrProposedACDisco;
	}


	public void setAccessIssuesWith_MeterOrProposedACDisco(Boolean accessIssuesWith_MeterOrProposedACDisco) {
		this.accessIssuesWith_MeterOrProposedACDisco = accessIssuesWith_MeterOrProposedACDisco;
	}


	public Boolean getDescribeAccessIssues() {
		return describeAccessIssues;
	}


	public void setDescribeAccessIssues(Boolean describeAccessIssues) {
		this.describeAccessIssues = describeAccessIssues;
	}


	public Boolean getContactpersonforutility() {
		return contactpersonforutility;
	}


	public void setContactpersonforutility(Boolean contactpersonforutility) {
		this.contactpersonforutility = contactpersonforutility;
	}


	public Boolean getOthercontactperson() {
		return othercontactperson;
	}


	public void setOthercontactperson(Boolean othercontactperson) {
		this.othercontactperson = othercontactperson;
	}


	public Boolean getContactphone() {
		return contactphone;
	}


	public void setContactphone(Boolean contactphone) {
		this.contactphone = contactphone;
	}


	public Boolean getOthercontactphone() {
		return othercontactphone;
	}


	public void setOthercontactphone(Boolean othercontactphone) {
		this.othercontactphone = othercontactphone;
	}


	public Boolean getImageOfSiteInformationRafter() {
		return imageOfSiteInformationRafter;
	}


	public void setImageOfSiteInformationRafter(Boolean imageOfSiteInformationRafter) {
		this.imageOfSiteInformationRafter = imageOfSiteInformationRafter;
	}


	public Boolean getMainPanelMake() {
		return mainPanelMake;
	}


	public void setMainPanelMake(Boolean mainPanelMake) {
		this.mainPanelMake = mainPanelMake;
	}


	public Boolean getOtherMainPanelMake() {
		return otherMainPanelMake;
	}


	public void setOtherMainPanelMake(Boolean otherMainPanelMake) {
		this.otherMainPanelMake = otherMainPanelMake;
	}


	public Boolean getMainPanelModel() {
		return mainPanelModel;
	}


	public void setMainPanelModel(Boolean mainPanelModel) {
		this.mainPanelModel = mainPanelModel;
	}


	public Boolean getVoltage() {
		return voltage;
	}


	public void setVoltage(Boolean voltage) {
		this.voltage = voltage;
	}


	public Boolean getOtherVoltage() {
		return otherVoltage;
	}


	public void setOtherVoltage(Boolean otherVoltage) {
		this.otherVoltage = otherVoltage;
	}


	public Boolean getMspbusbarRating() {
		return mspbusbarRating;
	}


	public void setMspbusbarRating(Boolean mspbusbarRating) {
		this.mspbusbarRating = mspbusbarRating;
	}


	public Boolean getOtherMSPBusbarRating() {
		return otherMSPBusbarRating;
	}


	public void setOtherMSPBusbarRating(Boolean otherMSPBusbarRating) {
		this.otherMSPBusbarRating = otherMSPBusbarRating;
	}


	public Boolean getmSPMainBreakerRating() {
		return mSPMainBreakerRating;
	}


	public void setmSPMainBreakerRating(Boolean mSPMainBreakerRating) {
		this.mSPMainBreakerRating = mSPMainBreakerRating;
	}


	public Boolean getOtherMSPMainBreakerRating() {
		return otherMSPMainBreakerRating;
	}


	public void setOtherMSPMainBreakerRating(Boolean otherMSPMainBreakerRating) {
		this.otherMSPMainBreakerRating = otherMSPMainBreakerRating;
	}


	public Boolean getOpenBreakerSlots() {
		return openBreakerSlots;
	}


	public void setOpenBreakerSlots(Boolean openBreakerSlots) {
		this.openBreakerSlots = openBreakerSlots;
	}


	public Boolean getsCIR() {
		return sCIR;
	}


	public void setsCIR(Boolean sCIR) {
		this.sCIR = sCIR;
	}


	public Boolean getMainBreakerFeedGauge() {
		return mainBreakerFeedGauge;
	}


	public void setMainBreakerFeedGauge(Boolean mainBreakerFeedGauge) {
		this.mainBreakerFeedGauge = mainBreakerFeedGauge;
	}

	public Boolean getOtherMainBreakerFeedGauge() {
		return otherMainBreakerFeedGauge;
	}


	public void setOtherMainBreakerFeedGauge(Boolean otherMainBreakerFeedGauge) {
		this.otherMainBreakerFeedGauge = otherMainBreakerFeedGauge;
	}


	public Boolean getUtilityFeederGauge() {
		return utilityFeederGauge;
	}


	public void setUtilityFeederGauge(Boolean utilityFeederGauge) {
		this.utilityFeederGauge = utilityFeederGauge;
	}


	public Boolean getGroundAccessible() {
		return groundAccessible;
	}


	public void setGroundAccessible(Boolean groundAccessible) {
		this.groundAccessible = groundAccessible;
	}


	public Boolean getUndergroundOrOverheadFeed() {
		return undergroundOrOverheadFeed;
	}


	public void setUndergroundOrOverheadFeed(Boolean undergroundOrOverheadFeed) {
		this.undergroundOrOverheadFeed = undergroundOrOverheadFeed;
	}


	public Boolean getImageOfExistingMainPanel() {
		return imageOfExistingMainPanel;
	}


	public void setImageOfExistingMainPanel(Boolean imageOfExistingMainPanel) {
		this.imageOfExistingMainPanel = imageOfExistingMainPanel;
	}


	public Boolean getSubpanelMake() {
		return subpanelMake;
	}


	public void setSubpanelMake(Boolean subpanelMake) {
		this.subpanelMake = subpanelMake;
	}


	public Boolean getSubpanelModel() {
		return subpanelModel;
	}


	public void setSubpanelModel(Boolean subpanelModel) {
		this.subpanelModel = subpanelModel;
	}


	public Boolean getSubpanelVoltage() {
		return subpanelVoltage;
	}


	public void setSubpanelVoltage(Boolean subpanelVoltage) {
		this.subpanelVoltage = subpanelVoltage;
	}


	public Boolean getOtherSubpanelVoltage() {
		return otherSubpanelVoltage;
	}


	public void setOtherSubpanelVoltage(Boolean otherSubpanelVoltage) {
		this.otherSubpanelVoltage = otherSubpanelVoltage;
	}

	public Boolean getSubpanelBusbarRating() {
		return subpanelBusbarRating;
	}


	public void setSubpanelBusbarRating(Boolean subpanelBusbarRating) {
		this.subpanelBusbarRating = subpanelBusbarRating;
	}


	public Boolean getOtherSubpanelBusbarRating() {
		return otherSubpanelBusbarRating;
	}


	public void setOtherSubpanelBusbarRating(Boolean otherSubpanelBusbarRating) {
		this.otherSubpanelBusbarRating = otherSubpanelBusbarRating;
	}


	public Boolean getSubpanelMainBreakerRating() {
		return subpanelMainBreakerRating;
	}


	public void setSubpanelMainBreakerRating(Boolean subpanelMainBreakerRating) {
		this.subpanelMainBreakerRating = subpanelMainBreakerRating;
	}


	public Boolean getOtherSubpanelMainBreakerRating() {
		return otherSubpanelMainBreakerRating;
	}


	public void setOtherSubpanelMainBreakerRating(Boolean otherSubpanelMainBreakerRating) {
		this.otherSubpanelMainBreakerRating = otherSubpanelMainBreakerRating;
	}


	public Boolean getOpenBreakerSlotsinSubpanel() {
		return openBreakerSlotsinSubpanel;
	}


	public void setOpenBreakerSlotsinSubpanel(Boolean openBreakerSlotsinSubpanel) {
		this.openBreakerSlotsinSubpanel = openBreakerSlotsinSubpanel;
	}


	public Boolean getSubpanelFeederGauge() {
		return subpanelFeederGauge;
	}


	public void setSubpanelFeederGauge(Boolean subpanelFeederGauge) {
		this.subpanelFeederGauge = subpanelFeederGauge;
	}


	public Boolean getImageOfExistingSubPanel() {
		return imageOfExistingSubPanel;
	}


	public void setImageOfExistingSubPanel(Boolean imageOfExistingSubPanel) {
		this.imageOfExistingSubPanel = imageOfExistingSubPanel;
	}


	public Boolean getSiteNotes() {
		return siteNotes;
	}


	public void setSiteNotes(Boolean siteNotes) {
		this.siteNotes = siteNotes;
	}


	public Boolean getUtilityCoName() {
		return utilityCoName;
	}


	public void setUtilityCoName(Boolean utilityCoName) {
		this.utilityCoName = utilityCoName;
	}

	public Boolean getUtilityCompanyNameOther() {
		return utilityCompanyNameOther;
	}


	public void setUtilityCompanyNameOther(Boolean utilityCompanyNameOther) {
		this.utilityCompanyNameOther = utilityCompanyNameOther;
	}


	public Boolean getMeterNumber() {
		return meterNumber;
	}

	public void setMeterNumber(Boolean meterNumber) {
		this.meterNumber = meterNumber;
	}


	public Boolean getnEMType() {
		return nEMType;
	}


	public void setnEMType(Boolean nEMType) {
		this.nEMType = nEMType;
	}


	public Boolean getOtherNEMType() {
		return otherNEMType;
	}


	public void setOtherNEMType(Boolean otherNEMType) {
		this.otherNEMType = otherNEMType;
	}


	public Boolean getPostSolarRate() {
		return postSolarRate;
	}


	public void setPostSolarRate(Boolean postSolarRate) {
		this.postSolarRate = postSolarRate;
	}


	public Boolean getUsageHistoryOffset() {
		return usageHistoryOffset;
	}


	public void setUsageHistoryOffset(Boolean usageHistoryOffset) {
		this.usageHistoryOffset = usageHistoryOffset;
	}


	public Boolean getPaceEntity() {
		return paceEntity;
	}


	public void setPaceEntity(Boolean paceEntity) {
		this.paceEntity = paceEntity;
	}


	public Boolean getPaceEntityOther() {
		return paceEntityOther;
	}


	public void setPaceEntityOther(Boolean paceEntityOther) {
		this.paceEntityOther = paceEntityOther;
	}


	public Boolean getaCDiscoWillbemorethan10FromUtilityMeter() {
		return aCDiscoWillbemorethan10FromUtilityMeter;
	}


	public void setaCDiscoWillbemorethan10FromUtilityMeter(Boolean aCDiscoWillbemorethan10FromUtilityMeter) {
		this.aCDiscoWillbemorethan10FromUtilityMeter = aCDiscoWillbemorethan10FromUtilityMeter;
	}


	public Boolean getAtLeast4MonthsOfelectricbillinghistory() {
		return atLeast4MonthsOfelectricbillinghistory;
	}


	public void setAtLeast4MonthsOfelectricbillinghistory(Boolean atLeast4MonthsOfelectricbillinghistory) {
		this.atLeast4MonthsOfelectricbillinghistory = atLeast4MonthsOfelectricbillinghistory;
	}


	public Boolean getSquareFeetOfLivingArea() {
		return squareFeetOfLivingArea;
	}


	public void setSquareFeetOfLivingArea(Boolean squareFeetOfLivingArea) {
		this.squareFeetOfLivingArea = squareFeetOfLivingArea;
	}


	public Boolean getRecentAnnualUsage() {
		return recentAnnualUsage;
	}


	public void setRecentAnnualUsage(Boolean recentAnnualUsage) {
		this.recentAnnualUsage = recentAnnualUsage;
	}


	public Boolean getNumberOfElectricVehicles() {
		return numberOfElectricVehicles;
	}


	public void setNumberOfElectricVehicles(Boolean numberOfElectricVehicles) {
		this.numberOfElectricVehicles = numberOfElectricVehicles;
	}


	public Boolean getpVSystemMake() {
		return pVSystemMake;
	}


	public void setpVSystemMake(Boolean pVSystemMake) {
		this.pVSystemMake = pVSystemMake;
	}


	public Boolean getpVSystemModel() {
		return pVSystemModel;
	}


	public void setpVSystemModel(Boolean pVSystemModel) {
		this.pVSystemModel = pVSystemModel;
	}


	public Boolean getpVQuantity() {
		return pVQuantity;
	}


	public void setpVQuantity(Boolean pVQuantity) {
		this.pVQuantity = pVQuantity;
	}


	public Boolean getGridTiedInverterMake() {
		return gridTiedInverterMake;
	}


	public void setGridTiedInverterMake(Boolean gridTiedInverterMake) {
		this.gridTiedInverterMake = gridTiedInverterMake;
	}


	public Boolean getGridTiedInverterModel() {
		return gridTiedInverterModel;
	}


	public void setGridTiedInverterModel(Boolean gridTiedInverterModel) {
		this.gridTiedInverterModel = gridTiedInverterModel;
	}


	public Boolean getGridTiedInverterQuantity() {
		return gridTiedInverterQuantity;
	}


	public void setGridTiedInverterQuantity(Boolean gridTiedInverterQuantity) {
		this.gridTiedInverterQuantity = gridTiedInverterQuantity;
	}


	public Boolean getExistingACDisconnect() {
		return existingACDisconnect;
	}


	public void setExistingACDisconnect(Boolean existingACDisconnect) {
		this.existingACDisconnect = existingACDisconnect;
	}


	public Boolean getaCDiscoMake() {
		return aCDiscoMake;
	}


	public void setaCDiscoMake(Boolean aCDiscoMake) {
		this.aCDiscoMake = aCDiscoMake;
	}


	public Boolean getaCDiscoModel() {
		return aCDiscoModel;
	}


	public void setaCDiscoModel(Boolean aCDiscoModel) {
		this.aCDiscoModel = aCDiscoModel;
	}


	public Boolean getACDiscoRating() {
		return aCDiscoRating;
	}


	public void setACDiscoRating(Boolean aCDiscoRating) {
		this.aCDiscoRating = aCDiscoRating;
	}


	public Boolean getConnectionType() {
		return connectionType;
	}


	public void setConnectionType(Boolean connectionType) {
		this.connectionType = connectionType;
	}


	public Boolean getpVBreaker1() {
		return pVBreaker1;
	}


	public void setpVBreaker1(Boolean pVBreaker1) {
		this.pVBreaker1 = pVBreaker1;
	}


	public Boolean getpVBreaker2() {
		return pVBreaker2;
	}


	public void setpVBreaker2(Boolean pVBreaker2) {
		this.pVBreaker2 = pVBreaker2;
	}


	public Boolean getpVBreaker3() {
		return pVBreaker3;
	}


	public void setpVBreaker3(Boolean pVBreaker3) {
		this.pVBreaker3 = pVBreaker3;
	}


	public Boolean getpVBreaker4() {
		return pVBreaker4;
	}


	public void setpVBreaker4(Boolean pVBreaker4) {
		this.pVBreaker4 = pVBreaker4;
	}


	public Boolean getpVBreaker5() {
		return pVBreaker5;
	}


	public void setpVBreaker5(Boolean pVBreaker5) {
		this.pVBreaker5 = pVBreaker5;
	}


	public Boolean getCircuitstoRelocatetoCriticalLoadsPanel() {
		return circuitstoRelocatetoCriticalLoadsPanel;
	}


	public void setCircuitstoRelocatetoCriticalLoadsPanel(Boolean circuitstoRelocatetoCriticalLoadsPanel) {
		this.circuitstoRelocatetoCriticalLoadsPanel = circuitstoRelocatetoCriticalLoadsPanel;
	}


	public Boolean getBatteryLocation() {
		return batteryLocation;
	}


	public void setBatteryLocation(Boolean batteryLocation) {
		this.batteryLocation = batteryLocation;
	}


	public Boolean getInverterTechnology() {
		return inverterTechnology;
	}


	public void setInverterTechnology(Boolean inverterTechnology) {
		this.inverterTechnology = inverterTechnology;
	}


	public Boolean getNotesOnInverter() {
		return notesOnInverter;
	}


	public void setNotesOnInverter(Boolean notesOnInverter) {
		this.notesOnInverter = notesOnInverter;
	}


	public Boolean getTypeOfBatterySystem() {
		return typeOfBatterySystem;
	}


	public void setTypeOfBatterySystem(Boolean typeOfBatterySystem) {
		this.typeOfBatterySystem = typeOfBatterySystem;
	}


	public Boolean getCriticalLoadPanelLocation() {
		return criticalLoadPanelLocation;
	}


	public void setCriticalLoadPanelLocation(Boolean criticalLoadPanelLocation) {
		this.criticalLoadPanelLocation = criticalLoadPanelLocation;
	}


	public Boolean getInverter1Model() {
		return inverter1Model;
	}


	public void setInverter1Model(Boolean inverter1Model) {
		this.inverter1Model = inverter1Model;
	}

	public Boolean getInverter2Model() {
		return inverter2Model;
	}


	public void setInverter2Model(Boolean inverter2Model) {
		this.inverter2Model = inverter2Model;
	}



	public Boolean getInverter3Model() {
		return inverter3Model;
	}


	public void setInverter3Model(Boolean inverter3Model) {
		this.inverter3Model = inverter3Model;
	}


	public Boolean getImageBatteryInfo() {
		return imageBatteryInfo;
	}


	public void setImageBatteryInfo(Boolean imageBatteryInfo) {
		this.imageBatteryInfo = imageBatteryInfo;
	}


	public Boolean getProductionMonitor() {
		return productionMonitor;
	}


	public void setProductionMonitor(Boolean productionMonitor) {
		this.productionMonitor = productionMonitor;
	}


	public Boolean getActiveInternetConnection() {
		return activeInternetConnection;
	}


	public void setActiveInternetConnection(Boolean activeInternetConnection) {
		this.activeInternetConnection = activeInternetConnection;
	}


	public Boolean getModemLocation() {
		return modemLocation;
	}


	public void setModemLocation(Boolean modemLocation) {
		this.modemLocation = modemLocation;
	}


	public Boolean getConnectTheMonitor() {
		return connectTheMonitor;
	}


	public void setConnectTheMonitor(Boolean connectTheMonitor) {
		this.connectTheMonitor = connectTheMonitor;
	}


	public Boolean getVerifyRoofMeasurementsPreRoofLayoutProvided() {
		return verifyRoofMeasurementsPreRoofLayoutProvided;
	}


	public void setVerifyRoofMeasurementsPreRoofLayoutProvided(Boolean verifyRoofMeasurementsPreRoofLayoutProvided) {
		this.verifyRoofMeasurementsPreRoofLayoutProvided = verifyRoofMeasurementsPreRoofLayoutProvided;
	}


	public Boolean getImageOfInternetConnectionRoof() {
		return imageOfInternetConnectionRoof;
	}


	public void setImageOfInternetConnectionRoof(Boolean imageOfInternetConnectionRoof) {
		this.imageOfInternetConnectionRoof = imageOfInternetConnectionRoof;
	}


	public Boolean getImageOfInternetConnectionElevation() {
		return imageOfInternetConnectionElevation;
	}


	public void setImageOfInternetConnectionElevation(Boolean imageOfInternetConnectionElevation) {
		this.imageOfInternetConnectionElevation = imageOfInternetConnectionElevation;
	}


	public Boolean getRoofMaterial() {
		return roofMaterial;
	}


	public void setRoofMaterial(Boolean roofMaterial) {
		this.roofMaterial = roofMaterial;
	}


	public Boolean getNumberOfLayers() {
		return numberOfLayers;
	}


	public void setNumberOfLayers(Boolean numberOfLayers) {
		this.numberOfLayers = numberOfLayers;
	}


	public Boolean getRoofAge() {
		return roofAge;
	}


	public void setRoofAge(Boolean roofAge) {
		this.roofAge = roofAge;
	}


	public Boolean getpVModel() {
		return pVModel;
	}


	public void setpVModel(Boolean pVModel) {
		this.pVModel = pVModel;
	}


	public Boolean getGableRakeOverhang() {
		return gableRakeOverhang;
	}


	public void setGableRakeOverhang(Boolean gableRakeOverhang) {
		this.gableRakeOverhang = gableRakeOverhang;
	}


	public Boolean getHeightAtGutter() {
		return heightAtGutter;
	}


	public void setHeightAtGutter(Boolean heightAtGutter) {
		this.heightAtGutter = heightAtGutter;
	}

	public Boolean getCrossSectionSize() {
		return crossSectionSize;
	}


	public void setCrossSectionSize(Boolean crossSectionSize) {
		this.crossSectionSize = crossSectionSize;
	}


	public Boolean getCrossSectionSizeOther() {
		return crossSectionSizeOther;
	}


	public void setCrossSectionSizeOther(Boolean crossSectionSizeOther) {
		this.crossSectionSizeOther = crossSectionSizeOther;
	}


	public Boolean getRoofStructureChart() {
		return roofStructureChart;
	}


	public void setRoofStructureChart(Boolean roofStructureChart) {
		this.roofStructureChart = roofStructureChart;
	}


	public Boolean getStanchionMaxSpacing() {
		return stanchionMaxSpacing;
	}


	public void setStanchionMaxSpacing(Boolean stanchionMaxSpacing) {
		this.stanchionMaxSpacing = stanchionMaxSpacing;
	}


	public Boolean getRidgeBeamDepthAtArrays() {
		return ridgeBeamDepthAtArrays;
	}


	public void setRidgeBeamDepthAtArrays(Boolean ridgeBeamDepthAtArrays) {
		this.ridgeBeamDepthAtArrays = ridgeBeamDepthAtArrays;
	}


	public Boolean getMaxSpanAtArraysHS1() {
		return maxSpanAtArraysHS1;
	}


	public void setMaxSpanAtArraysHS1(Boolean maxSpanAtArraysHS1) {
		this.maxSpanAtArraysHS1 = maxSpanAtArraysHS1;
	}


	public Boolean getMaxSpanAtArraysHS2() {
		return maxSpanAtArraysHS2;
	}


	public void setMaxSpanAtArraysHS2(Boolean maxSpanAtArraysHS2) {
		this.maxSpanAtArraysHS2 = maxSpanAtArraysHS2;
	}


	public Boolean getRafterTrussSpacing() {
		return rafterTrussSpacing;
	}


	public void setRafterTrussSpacing(Boolean rafterTrussSpacing) {
		this.rafterTrussSpacing = rafterTrussSpacing;
	}


	public Boolean getRafterTrussSpOther() {
		return rafterTrussSpOther;
	}


	public void setRafterTrussSpOther(Boolean rafterTrussSpOther) {
		this.rafterTrussSpOther = rafterTrussSpOther;
	}


	public Boolean getNonRoofContourSlope() {
		return nonRoofContourSlope;
	}


	public void setNonRoofContourSlope(Boolean nonRoofContourSlope) {
		this.nonRoofContourSlope = nonRoofContourSlope;
	}


	public Boolean getNonRoofPathPoint() {
		return nonRoofPathPoint;
	}


	public void setNonRoofPathPoint(Boolean nonRoofPathPoint) {
		this.nonRoofPathPoint = nonRoofPathPoint;
	}


	public Boolean getNonRoofGradingGrubbing() {
		return nonRoofGradingGrubbing;
	}


	public void setNonRoofGradingGrubbing(Boolean nonRoofGradingGrubbing) {
		this.nonRoofGradingGrubbing = nonRoofGradingGrubbing;
	}


	public Boolean getNonRoofSiteComposition() {
		return nonRoofSiteComposition;
	}


	public void setNonRoofSiteComposition(Boolean nonRoofSiteComposition) {
		this.nonRoofSiteComposition = nonRoofSiteComposition;
	}


	public Boolean getNonRoofElevationStructure() {
		return nonRoofElevationStructure;
	}


	public void setNonRoofElevationStructure(Boolean nonRoofElevationStructure) {
		this.nonRoofElevationStructure = nonRoofElevationStructure;
	}


	public Boolean getNonRoofExistingSecurity() {
		return nonRoofExistingSecurity;
	}


	public void setNonRoofExistingSecurity(Boolean nonRoofExistingSecurity) {
		this.nonRoofExistingSecurity = nonRoofExistingSecurity;
	}


	public Boolean getNonRoofPatioCoverValue() {
		return nonRoofPatioCoverValue;
	}


	public void setNonRoofPatioCoverValue(Boolean nonRoofPatioCoverValue) {
		this.nonRoofPatioCoverValue = nonRoofPatioCoverValue;
	}


	public Boolean getPatioCoverAttachedTypeBeam() {
		return patioCoverAttachedTypeBeam;
	}


	public void setPatioCoverAttachedTypeBeam(Boolean patioCoverAttachedTypeBeam) {
		this.patioCoverAttachedTypeBeam = patioCoverAttachedTypeBeam;
	}


	public Boolean getPatioCoverAttachedTypePosts() {
		return patioCoverAttachedTypePosts;
	}


	public void setPatioCoverAttachedTypePosts(Boolean patioCoverAttachedTypePosts) {
		this.patioCoverAttachedTypePosts = patioCoverAttachedTypePosts;
	}


	public Boolean getPatioCoverFreestandingTypeBeam() {
		return patioCoverFreestandingTypeBeam;
	}


	public void setPatioCoverFreestandingTypeBeam(Boolean patioCoverFreestandingTypeBeam) {
		this.patioCoverFreestandingTypeBeam = patioCoverFreestandingTypeBeam;
	}


	public Boolean getPatioCoverFreestandingTypePosts() {
		return patioCoverFreestandingTypePosts;
	}


	public void setPatioCoverFreestandingTypePosts(Boolean patioCoverFreestandingTypePosts) {
		this.patioCoverFreestandingTypePosts = patioCoverFreestandingTypePosts;
	}


	public Boolean getPatioCoverFreestandingExtendOver() {
		return patioCoverFreestandingExtendOver;
	}


	public void setPatioCoverFreestandingExtendOver(Boolean patioCoverFreestandingExtendOver) {
		this.patioCoverFreestandingExtendOver = patioCoverFreestandingExtendOver;
	}


	public Boolean getPatioCoverFreestandingPastEave() {
		return patioCoverFreestandingPastEave;
	}


	public void setPatioCoverFreestandingPastEave(Boolean patioCoverFreestandingPastEave) {
		this.patioCoverFreestandingPastEave = patioCoverFreestandingPastEave;
	}


	public Boolean getOtherVoltageOther() {
		return otherVoltageOther;
	}


	public void setOtherVoltageOther(Boolean otherVoltageOther) {
		this.otherVoltageOther = otherVoltageOther;
	}


	public Boolean getWidthSeamsOther() {
		return widthSeamsOther;
	}


	public void setWidthSeamsOther(Boolean widthSeamsOther) {
		this.widthSeamsOther = widthSeamsOther;
	}


	public Boolean getRoofMaterialTypeOther() {
		return roofMaterialTypeOther;
	}


	public void setRoofMaterialTypeOther(Boolean roofMaterialTypeOther) {
		this.roofMaterialTypeOther = roofMaterialTypeOther;
	}


	public Boolean getPostSolarRateOther() {
		return postSolarRateOther;
	}


	public void setPostSolarRateOther(Boolean postSolarRateOther) {
		this.postSolarRateOther = postSolarRateOther;
	}


	public Boolean getOtherPatioCoverAttachedTypeBeam() {
		return otherPatioCoverAttachedTypeBeam;
	}


	public void setOtherPatioCoverAttachedTypeBeam(Boolean otherPatioCoverAttachedTypeBeam) {
		this.otherPatioCoverAttachedTypeBeam = otherPatioCoverAttachedTypeBeam;
	}


	public Boolean getOtherPatioCoverAttachedTypePosts() {
		return otherPatioCoverAttachedTypePosts;
	}


	public void setOtherPatioCoverAttachedTypePosts(Boolean otherPatioCoverAttachedTypePosts) {
		this.otherPatioCoverAttachedTypePosts = otherPatioCoverAttachedTypePosts;
	}


	public Boolean getOtherPatioCoverFreestandingTypeBeam() {
		return otherPatioCoverFreestandingTypeBeam;
	}


	public void setOtherPatioCoverFreestandingTypeBeam(Boolean otherPatioCoverFreestandingTypeBeam) {
		this.otherPatioCoverFreestandingTypeBeam = otherPatioCoverFreestandingTypeBeam;
	}


	public Boolean getOtherPatioCoverFreestandingTypePosts() {
		return otherPatioCoverFreestandingTypePosts;
	}


	public void setOtherPatioCoverFreestandingTypePosts(Boolean otherPatioCoverFreestandingTypePosts) {
		this.otherPatioCoverFreestandingTypePosts = otherPatioCoverFreestandingTypePosts;
	}


	public Boolean getUtilityMeterOrProposedACDisco() {
		return utilityMeterOrProposedACDisco;
	}


	public void setUtilityMeterOrProposedACDisco(Boolean utilityMeterOrProposedACDisco) {
		this.utilityMeterOrProposedACDisco = utilityMeterOrProposedACDisco;
	}



	public Boolean getSelectIfAttach() {
		return selectIfAttach;
	}


	public void setSelectIfAttach(Boolean selectIfAttach) {
		this.selectIfAttach = selectIfAttach;
	}


	public Boolean getRoofOrOpenFrame() {
		return roofOrOpenFrame;
	}


	public void setRoofOrOpenFrame(Boolean roofOrOpenFrame) {
		this.roofOrOpenFrame = roofOrOpenFrame;
	}


	public Boolean getSumofexistCircuit() {
		return sumofexistCircuit;
	}


	public void setSumofexistCircuit(Boolean sumofexistCircuit) {
		this.sumofexistCircuit = sumofexistCircuit;
	}


	public Boolean getNotesGroundMount() {
		return notesGroundMount;
	}


	public void setNotesGroundMount(Boolean notesGroundMount) {
		this.notesGroundMount = notesGroundMount;
	}


	public Boolean getAddressLine2() {
		return addressLine2;
	}


	public void setAddressLine2(Boolean addressLine2) {
		this.addressLine2 = addressLine2;
	}


	public Boolean getSubPanelBreakerOCPD() {
		return subPanelBreakerOCPD;
	}


	public void setSubPanelBreakerOCPD(Boolean subPanelBreakerOCPD) {
		this.subPanelBreakerOCPD = subPanelBreakerOCPD;
	}


	public Boolean getSecondOtherVoltageOther() {
		return secondOtherVoltageOther;
	}


	public void setSecondOtherVoltageOther(Boolean secondOtherVoltageOther) {
		this.secondOtherVoltageOther = secondOtherVoltageOther;
	}


	public Boolean getNotesExistingPvSystem() {
		return notesExistingPvSystem;
	}


	public void setNotesExistingPvSystem(Boolean notesExistingPvSystem) {
		this.notesExistingPvSystem = notesExistingPvSystem;
	}


	public Boolean getNetworkName() {
		return networkName;
	}


	public void setNetworkName(Boolean networkName) {
		this.networkName = networkName;
	}


	public Boolean getNetworkPassword() {
		return networkPassword;
	}


	public void setNetworkPassword(Boolean networkPassword) {
		this.networkPassword = networkPassword;
	}


	public Boolean getRoofCondition() {
		return roofCondition;
	}


	public void setRoofCondition(Boolean roofCondition) {
		this.roofCondition = roofCondition;
	}


	public Boolean getRoofNotes() {
		return roofNotes;
	}


	public void setRoofNotes(Boolean roofNotes) {
		this.roofNotes = roofNotes;
	}


	public Boolean getMeasurmentsOfArea() {
		return measurmentsOfArea;
	}


	public void setMeasurmentsOfArea(Boolean measurmentsOfArea) {
		this.measurmentsOfArea = measurmentsOfArea;
	}


	public Boolean getNotesOnCarpotOrPatiot() {
		return notesOnCarpotOrPatiot;
	}


	public void setNotesOnCarpotOrPatiot(Boolean notesOnCarpotOrPatiot) {
		this.notesOnCarpotOrPatiot = notesOnCarpotOrPatiot;
	}
	

	public Boolean getNonRoofCarport() {
		return nonRoofCarport;
	}


	public void setNonRoofCarport(Boolean nonRoofCarport) {
		this.nonRoofCarport = nonRoofCarport;
	}


	public Boolean getNonRoofPatioCover() {
		return nonRoofPatioCover;
	}


	public void setNonRoofPatioCover(Boolean nonRoofPatioCover) {
		this.nonRoofPatioCover = nonRoofPatioCover;
	}


	public Boolean getWireRunOnRoof() {
		return wireRunOnRoof;
	}


	public void setWireRunOnRoof(Boolean wireRunOnRoof) {
		this.wireRunOnRoof = wireRunOnRoof;
	}


	public Boolean getWireRunInAttic() {
		return wireRunInAttic;
	}


	public void setWireRunInAttic(Boolean wireRunInAttic) {
		this.wireRunInAttic = wireRunInAttic;
	}


	public Boolean getTiltupModules() {
		return tiltupModules;
	}


	public void setTiltupModules(Boolean tiltupModules) {
		this.tiltupModules = tiltupModules;
	}


	public Boolean getRoofModuleAzimuth() {
		return roofModuleAzimuth;
	}


	public void setRoofModuleAzimuth(Boolean roofModuleAzimuth) {
		this.roofModuleAzimuth = roofModuleAzimuth;
	}


	public Boolean getRoofPitch() {
		return roofPitch;
	}


	public void setRoofPitch(Boolean roofPitch) {
		this.roofPitch = roofPitch;
	}


	public Boolean getRoofTiltKitUsed() {
		return roofTiltKitUsed;
	}


	public void setRoofTiltKitUsed(Boolean roofTiltKitUsed) {
		this.roofTiltKitUsed = roofTiltKitUsed;
	}


	public Boolean getRoofEaveOverhang() {
		return roofEaveOverhang;
	}


	public void setRoofEaveOverhang(Boolean roofEaveOverhang) {
		this.roofEaveOverhang = roofEaveOverhang;
	}


	public Boolean getOtherRoofEaveOverhang() {
		return otherRoofEaveOverhang;
	}


	public void setOtherRoofEaveOverhang(Boolean otherRoofEaveOverhang) {
		this.otherRoofEaveOverhang = otherRoofEaveOverhang;
	}


	public Boolean getRoofModuleTilt() {
		return roofModuleTilt;
	}


	public void setRoofModuleTilt(Boolean roofModuleTilt) {
		this.roofModuleTilt = roofModuleTilt;
	}


	public Boolean getRoofModuleQty() {
		return roofModuleQty;
	}


	public void setRoofModuleQty(Boolean roofModuleQty) {
		this.roofModuleQty = roofModuleQty;
	}


	public Boolean getNonRoofModuleAzimuth() {
		return nonRoofModuleAzimuth;
	}


	public void setNonRoofModuleAzimuth(Boolean nonRoofModuleAzimuth) {
		this.nonRoofModuleAzimuth = nonRoofModuleAzimuth;
	}


	public Boolean getNonRoofModuleTilt() {
		return nonRoofModuleTilt;
	}


	public void setNonRoofModuleTilt(Boolean nonRoofModuleTilt) {
		this.nonRoofModuleTilt = nonRoofModuleTilt;
	}


	public Boolean getNonRoofModuleQty() {
		return nonRoofModuleQty;
	}


	public void setNonRoofModuleQty(Boolean nonRoofModuleQty) {
		this.nonRoofModuleQty = nonRoofModuleQty;
	}


	public Boolean getPatioModuleAzimuth() {
		return patioModuleAzimuth;
	}


	public void setPatioModuleAzimuth(Boolean patioModuleAzimuth) {
		this.patioModuleAzimuth = patioModuleAzimuth;
	}


	public Boolean getPatioroofPitch() {
		return patioroofPitch;
	}


	public void setPatioroofPitch(Boolean patioroofPitch) {
		this.patioroofPitch = patioroofPitch;
	}


	public Boolean getPatioroofTiltKitUsed() {
		return patioroofTiltKitUsed;
	}


	public void setPatioroofTiltKitUsed(Boolean patioroofTiltKitUsed) {
		this.patioroofTiltKitUsed = patioroofTiltKitUsed;
	}


	public Boolean getPatioroofEaveOverhang() {
		return patioroofEaveOverhang;
	}


	public void setPatioroofEaveOverhang(Boolean patioroofEaveOverhang) {
		this.patioroofEaveOverhang = patioroofEaveOverhang;
	}


	public Boolean getOtherPatioEaveOverhang() {
		return otherPatioEaveOverhang;
	}


	public void setOtherPatioEaveOverhang(Boolean otherPatioEaveOverhang) {
		this.otherPatioEaveOverhang = otherPatioEaveOverhang;
	}


	public Boolean getPatioroofModuleTilt() {
		return patioroofModuleTilt;
	}


	public void setPatioroofModuleTilt(Boolean patioroofModuleTilt) {
		this.patioroofModuleTilt = patioroofModuleTilt;
	}


	public Boolean getPatioroofModuleQty() {
		return patioroofModuleQty;
	}


	public void setPatioroofModuleQty(Boolean patioroofModuleQty) {
		this.patioroofModuleQty = patioroofModuleQty;
	}
	
	public Boolean getExistingBattery() {
		return existingBattery;
	}


	public void setExistingBattery(Boolean existingBattery) {
		this.existingBattery = existingBattery;
	}


	public Boolean getBatteryInverterIsGridTiedInverter() {
		return batteryInverterIsGridTiedInverter;
	}


	public void setBatteryInverterIsGridTiedInverter(Boolean batteryInverterIsGridTiedInverter) {
		this.batteryInverterIsGridTiedInverter = batteryInverterIsGridTiedInverter;
	}


	public Boolean getProposedBattery() {
		return proposedBattery;
	}


	public void setProposedBattery(Boolean proposedBattery) {
		this.proposedBattery = proposedBattery;
	}


	public Boolean getExistingPVSystemAtSite() {
		return existingPVSystemAtSite;
	}


	public void setExistingPVSystemAtSite(Boolean existingPVSystemAtSite) {
		this.existingPVSystemAtSite = existingPVSystemAtSite;
	}


	public Boolean getExistingACDisco() {
		return existingACDisco;
	}


	public void setExistingACDisco(Boolean existingACDisco) {
		this.existingACDisco = existingACDisco;
	}


	public Boolean getImageOfUtilityInfo() {
		return imageOfUtilityInfo;
	}


	public void setImageOfUtilityInfo(Boolean imageOfUtilityInfo) {
		this.imageOfUtilityInfo = imageOfUtilityInfo;
	}


	public Boolean getImageOfSiteNotes() {
		return imageOfSiteNotes;
	}


	public void setImageOfSiteNotes(Boolean imageOfSiteNotes) {
		this.imageOfSiteNotes = imageOfSiteNotes;
	}


	public Boolean getExistingSubpanel() {
		return existingSubpanel;
	}


	public void setExistingSubpanel(Boolean existingSubpanel) {
		this.existingSubpanel = existingSubpanel;
	}


	public Boolean getTieInPOCIsAtSubpanel() {
		return tieInPOCIsAtSubpanel;
	}


	public void setTieInPOCIsAtSubpanel(Boolean tieInPOCIsAtSubpanel) {
		this.tieInPOCIsAtSubpanel = tieInPOCIsAtSubpanel;
	}


	public Boolean getCenterFed() {
		return centerFed;
	}


	public void setCenterFed(Boolean centerFed) {
		this.centerFed = centerFed;
	}


	public Boolean getUpgradingMainServicePanel() {
		return upgradingMainServicePanel;
	}


	public void setUpgradingMainServicePanel(Boolean upgradingMainServicePanel) {
		this.upgradingMainServicePanel = upgradingMainServicePanel;
	}


	public Boolean getpVModelCarpotPatio() {
		return pVModelCarpotPatio;
	}


	public void setpVModelCarpotPatio(Boolean pVModelCarpotPatio) {
		this.pVModelCarpotPatio = pVModelCarpotPatio;
	}


	public Boolean getpVModelNonRoof() {
		return pVModelNonRoof;
	}


	public void setpVModelNonRoof(Boolean pVModelNonRoof) {
		this.pVModelNonRoof = pVModelNonRoof;
	}


	public Boolean getImageOfCarPortArrayLocation() {
		return imageOfCarPortArrayLocation;
	}


	public void setImageOfCarPortArrayLocation(Boolean imageOfCarPortArrayLocation) {
		this.imageOfCarPortArrayLocation = imageOfCarPortArrayLocation;
	}


	public Boolean getImageOfutilityInformation() {
		return imageOfutilityInformation;
	}


	public void setImageOfutilityInformation(Boolean imageOfutilityInformation) {
		this.imageOfutilityInformation = imageOfutilityInformation;
	}


	public Boolean getImageOfExistingPV() {
		return imageOfExistingPV;
	}


	public void setImageOfExistingPV(Boolean imageOfExistingPV) {
		this.imageOfExistingPV = imageOfExistingPV;
	}


	public Boolean getImageOfRoofAttic() {
		return imageOfRoofAttic;
	}


	public void setImageOfRoofAttic(Boolean imageOfRoofAttic) {
		this.imageOfRoofAttic = imageOfRoofAttic;
	}


	public Boolean getImageOfArrayLocation() {
		return imageOfArrayLocation;
	}


	public void setImageOfArrayLocation(Boolean imageOfArrayLocation) {
		this.imageOfArrayLocation = imageOfArrayLocation;
	}


	public Boolean getMeasOfAreaCarpot() {
		return measOfAreaCarpot;
	}


	public void setMeasOfAreaCarpot(Boolean measOfAreaCarpot) {
		this.measOfAreaCarpot = measOfAreaCarpot;
	}


	public Boolean getHomeOwnLastName() {
		return homeOwnLastName;
	}


	public void setHomeOwnLastName(Boolean homeOwnLastName) {
		this.homeOwnLastName = homeOwnLastName;
	}


	public Boolean getProjectName() {
		return projectName;
	}


	public void setProjectName(Boolean projectName) {
		this.projectName = projectName;
	}


	public Boolean getCityOther() {
		return cityOther;
	}


	public void setCityOther(Boolean cityOther) {
		this.cityOther = cityOther;
	}

	public Boolean getMainContactPhone() {
		return mainContactPhone;
	}

	public void setMainContactPhone(Boolean mainContactPhone) {
		this.mainContactPhone = mainContactPhone;
	}

	public Boolean getOtherPhone() {
		return otherPhone;
	}

	public void setOtherPhone(Boolean otherPhone) {
		this.otherPhone = otherPhone;
	}

	public Boolean getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(Boolean emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Boolean getMeanHeight() {
		return meanHeight;
	}
	
	public void setMeanHeight(Boolean meanHeight) {
		this.meanHeight = meanHeight;
	}


	public Boolean getNotesOnExistingBatterySystem() {
		return notesOnExistingBatterySystem;
	}


	public void setNotesOnExistingBatterySystem(Boolean notesOnExistingBatterySystem) {
		this.notesOnExistingBatterySystem = notesOnExistingBatterySystem;
	}


	public Boolean getOtherConnectionType() {
		return otherConnectionType;
	}


	public void setOtherConnectionType(Boolean otherConnectionType) {
		this.otherConnectionType = otherConnectionType;
	}


	public Boolean getOtherSubpanelFeederGauge() {
		return otherSubpanelFeederGauge;
	}


	public void setOtherSubpanelFeederGauge(Boolean otherSubpanelFeederGauge) {
		this.otherSubpanelFeederGauge = otherSubpanelFeederGauge;
	}


}
