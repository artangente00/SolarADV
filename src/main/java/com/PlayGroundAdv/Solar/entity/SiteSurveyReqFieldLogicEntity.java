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
@Table(name = "SiteSurveyReqFieldLogicEntity")
public class SiteSurveyReqFieldLogicEntity {


	private static final long serialVersionUID = 1L;
	
	
	@Id
	@SequenceGenerator(name="SiteSurveyReqFieldLogicsequence",
			           sequenceName="SiteSurveyRequiredFieldLogicsequence",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SiteSurveyReqFieldLogicsequence")  
	private  Long id;
	
	
	
	@Column(name="STATE_REQ_FIELD")
	private  String statereqfield;

	
	
	/////------------- basic information -----------/////
	
	@Column(name="HOME_OWN_NAME")
	private  String homeOwnName;

	
	/////------------- site information -----------/////
	
	@Column(name="Basic_Type_Of_System")
	private  String basicTypeOfSystem; 

	@Column(name="CONTACT_NAME")
	private  String contactName; 

	@Column(name="STREET_ADDRESS")
	private  String streetAddress ;

	@Column(name="CITY")
	private  String city ;
		
	@Column(name="STATE")
	private  String state ;
		
	@Column(name="ZIP")
	private  String zIP ;
	
	@Column(name="MAIN_CONTACT_PHONE")
	private String mainContactPhone ;

	@Column(name="OTHER_PHONE")
	private String otherPhone ;

	@Column(name="EMAIL_ADDRESS")
	private String emailAddress ;
		
	@Column(name="HOA")
	private  String hoa ;
		
	@Column(name="PERMITTING_AUTHORITY")
	private  String permittingAuthority ;

	@Column(name="LEGAL_OWNER_NAME")
	private  String legalOwnerName ;
		
	@Column(name="PARCEL_NUMBER")
	private  String parcelNumber; 
		
	@Column(name="ROOF_MATERIAL_TYPE")
	private  String roofMaterialType ;

	@Column(name="WIDTH_SEAMS")
	private  String widthSeams;
		
	@Column(name="RISK_CATEGORY")
	private  String riskCategory; 
		
	@Column(name="BUILDING_OCCUPANCY")
	private  String buildingOccupancy; 
		
	@Column(name="NUMBER_OF_STORIES_")
	private  String numberOfStories;
	
	@Column(name="MEAN_HEIGHT")
	private String meanHeight;
		
	@Column(name="PROJECT_EQUIPMENT_STAGING_LOCATION")
	private  String projectEquipmentStagingLocation ;
		
	@Column(name="OWNER_PREFERRED_ROOF_ACCESS_LOCATION")
	private  String ownerPreferredRoofAccessLocation;
		
	@Column(name="ACCESS_ISSUES_WITH_METER_OR_PROPOSED_AC_DISCO")
	private  String accessIssuesWith_MeterOrProposedACDisco; 
		
	@Column(name="DESCRIBE_ACCESS_ISSUES")
	private  String describeAccessIssues ;
		
	@Column(name="CONTACT_PERSON_FOR_UTILITY")
	private  String contactpersonforutility ;
		
	@Column(name="OTHER_CONTACT_PERSON")
	private  String othercontactperson ;
		
	@Column(name="CONTACT_PHONE")
	private  String contactphone ;
		
	@Column(name="OTHER_CONTACT_PHONE")
	private  String othercontactphone ;

	@Column(name="IMAGE_SI_RAFTER")
	private  String imageOfSiteInformationRafter;
	
	
	/////------------- existing main panel -----------/////
	
	@Column(name="MAIN_PANEL_MAKE")
	private  String mainPanelMake ;
	
	@Column(name="MAIN_PANEL_MODEL")
	private  String mainPanelModel ;
	
	@Column(name="VOLTAGE")
	private  String voltage ;
	
	@Column(name="OTHER_VOLTAGE")
	private  String otherVoltage ;
	
	@Column(name="MSP_BUSBAR_RATING")
	private  String mspbusbarRating;
	
	@Column(name="OTHER_MSP_BUSBAR_RATING")
	private  String otherMSPBusbarRating;
	
	@Column(name="MSP_MAIN_BREAKER_RATING")
	private  String mSPMainBreakerRating;
	
	@Column(name="OPEN_BREAKER_SLOTS")
	private  String openBreakerSlots ;
	
	@Column(name="SCIR")
	private  String sCIR ;
	
	@Column(name="MAIN_BREAKER_FEED_GAUGE")
	private  String mainBreakerFeedGauge;
	
	@Column(name="UTILITY_FEEDER_GAUGE")
	private  String utilityFeederGauge;
	
	@Column(name="GROUND_ACCESSIBLE")
	private  String groundAccessible;
	
	@Column(name="UNDERGROUND_OR_OVERHEAD_FEED")
	private  String undergroundOrOverheadFeed;

	@Column(name="IMAGE_EXIST_MAIN_PANEL")
	private  String imageOfExistingMainPanel;
	
	
	/////------------- existing subpanel -----------/////
	
	@Column(name="SUBPANEL_MAKE")
	private  String subpanelMake;
	
	@Column(name="SUBPANEL_MODEL")
	private  String subpanelModel;
	
	@Column(name="SUBPANEL_VOLTAGE")
	private  String subpanelVoltage;
	
	@Column(name="OTHER_SUBPANEL_VOLTAGE")
	private  String otherSubpanelVoltage;
	
	@Column(name="SUBPANEL_BUSBAR_RATING")
	private  String subpanelBusbarRating;
	
	@Column(name="SUBPANEL_MAIN_BREAKER_RATING")
	private  String subpanelMainBreakerRating;
	
	@Column(name="OTHER_SUBPANEL_MAIN_BREAKER_RATING")
	private  String otherSubpanelMainBreakerRating;
	
	@Column(name="OPEN_BREAKER_SLOTS_IN_SUBPANEL")
	private  String openBreakerSlotsinSubpanel;
	
	@Column(name="SUBPANEL_FEEDER_GAUGE")
	private  String subpanelFeederGauge;

	@Column(name="IMAGE_EXIST_SUP_PANEL")
	private  String imageOfExistingSubPanel;
	
	
	/////------------- site note -----------/////
	 
	@Column(name="SITE_NOTES")
	private  String siteNotes;

	
	/////------------- utility information -----------/////
	
	@Column(name="UTILITY_CO_NAME")
	private  String utilityCoName;
	
	@Column(name="METER_NUMBER")
	private String meterNumber;
	
	@Column(name="NEM_TYPE")
	private  String nEMType;
	
	@Column(name="OTHER_NEM_TYPE")
	private  String otherNEMType;
	
	@Column(name="POST_SOLAR_RATE")
	private  String postSolarRate;
	
	@Column(name="USAGE_HISTORY_OFFSET")
	private  String usageHistoryOffset;
	
	@Column(name="PACE_ENTITY")
	private  String paceEntity;
	
	@Column(name="AC_DISCO_WILL_BE_MORE_THAN_10_FROM_UTILITY_METER")
	private  String aCDiscoWillbemorethan10FromUtilityMeter;
	
	@Column(name="AT_LEAST_4_MONTHS_OF_ELECTRIC_BILLING_HISTORY")
	private  String atLeast4MonthsOfelectricbillinghistory ;
	
	@Column(name="SQUARE_FEET_OF_LIVING_AREA")
	private  String squareFeetOfLivingArea;
	
	@Column(name="RECENT_ANNUAL_USAGE")
	private  String recentAnnualUsage;
	
	@Column(name="NUMBER_OF_ELECTRIC_VEHICLES")
	private  String numberOfElectricVehicles; 

	
	/////------------- existing pV system at site -----------/////
	
	@Column(name="PV_SYSTEM_MAKE")
	private  String pVSystemMake;
	
	@Column(name="PV_SYSTEM_MODEL")
	private  String pVSystemModel;
	
	@Column(name="PV_QUANTITY")
	private  String pVQuantity;
	
	@Column(name="GRID_TIED_INVERTER_MAKE")
	private  String gridTiedInverterMake;
	
	@Column(name="GRID_TIED_INVERTER_MODEL")
	private  String gridTiedInverterModel;
	
	@Column(name="GRID_TIED_INVERTER_QUANTITY")
	private  String gridTiedInverterQuantity;
	
	@Column(name="EXISTING_AC_DISCONNECT")
	private  String existingACDisco;
	
	@Column(name="AC_DISCO_MAKE")
	private  String aCDiscoMake;
	
	@Column(name="AC_DISCO_MODEL")
	private  String aCDiscoModel ;
	
	@Column(name="AC_DISCO_RATING")
	private  String aCDiscoRating ;
	
	@Column(name="CONNECTION_TYPE")
	private  String connectionType;
	
	@Column(name="PV_BREAKER_1")
	private  String pVBreaker1;
	
	@Column(name="PV_BREAKER_2")
	private  String pVBreaker2;
	
	@Column(name="PV_BREAKER_3")
	private  String pVBreaker3;
	
	@Column(name="PV_BREAKER_4")
	private  String pVBreaker4;
	
	@Column(name="PVBREAKER5")
	private  String pVBreaker5; 
	
	@Column(name="EXISTING_BATTERY")
	private String existingBattery;
	
	@Column(name="NOTES_ON_EXISTING_BATTERY_SYSTEM")
	private String notesOnExistingBatterySystem;

	
	/////------------- battery info -----------/////
	@Column(name="INVERTER_TECHNOLOGY")
	private String inverterTechnology;
	
	@Column(name="INVERTER_1_MODEL")
	private  String inverter1Model;
	
	@Column(name="INVERTER_2_MODEL")
	private  String inverter2Model;
	
	@Column(name="INVERTER_3_MODEL")
	private  String inverter3Model;
	
	@Column(name="NOTES_ON_INVERTER")
	private String notesOnInverter;
	
	@Column(name="TYPE_OF_BATTERY_SYSTEM")
	private String typeOfBatterySystem;
	
	@Column(name="BATTERY_LOCATION")
	private String batteryLocation;
	
	@Column(name="CIRCUITS_TO_RELOCATE_TO_CRITICAL_LOADS_PANEL")
	private String circuitstoRelocatetoCriticalLoadsPanel;
	
	@Column(name="CRITICAL_LOAD_PANEL_LOCATION")
	private String criticalLoadPanelLocation;
	
	@Column(name="IMAGE_BATTERY_INFO")
	private  String imageBatteryInfo;
	
	/////------------- internet connection for production monitoring -----------/////	
	
	@Column(name="PRODUCTION_MONITOR")
	private  String productionMonitor;
	
	@Column(name="ACTIVE_INTERNET_CONNECTION")
	private  String activeInternetConnection;
	
	@Column(name="MODEM_LOCATION")
	private  String modemLocation;
	
	@Column(name="CONNECT_THE_MONITOR")
	private  String connectTheMonitor ;
	
	@Column(name="VERIFY_ROOF_MEASUREMENTS_PRE_ROOF_LAYOUT_PROVIDED")
	private  String verifyRoofMeasurementsPreRoofLayoutProvided;

	@Column(name="IMAGE_IC_ROOF")
	private  String imageOfInternetConnectionRoof;
	
	@Column(name="IMAGE_IC_ELEVATION")
	private  String imageOfInternetConnectionElevation;
	
	/////------------- roof mount -----------/////
	
	@Column(name="ROOF_MATERIAL")
	private  String roofMaterial;
	
	@Column(name="NUMBER_OF_LAYERS")
	private  String numberOfLayers;
	
	@Column(name="ROOF_AGE")
	private  String roofAge;
	
	@Column(name="PV_MODEL")
	private  String pVModel;
	
	@Column(name="GABLE_RAKE_OVERHANG")
	private  String gableRakeOverhang;
	
	@Column(name="HEIGHT_AT_GUTTER")
	private  String heightAtGutter;
	
	@Column(name="CROSS_SECTION_SIZE")
	private String crossSectionSize;
	
	@Column(name="CROSS_SECTION_SIZE_OTHER")
	private String crossSectionSizeOther;
	
	@Column(name="ROOF_STRUCTURE_CHART")
	private  String roofStructureChart;
	
	@Column(name="STANCHION_MAX_SPACING")
	private  String stanchionMaxSpacing ;
	
	@Column(name="RIDGE_BEAM_DEPTH_AT_ARRAYS")
	private  String ridgeBeamDepthAtArrays;
	
	@Column(name="MAX_SPAN_AT_ARRAYS_HS1")
	private  String maxSpanAtArraysHS1;
	
	@Column(name="MAX_SPAN_AT_ARRAYS_HS2")
	private  String maxSpanAtArraysHS2;
	
	@Column(name="RAFTER_TRUSS_SPACING")
	private  String rafterTrussSpacing;
	
	@Column(name="ROOF_MODULE_AZIMUTH")
	private String roofModuleAzimuth ;
	
	@Column(name="ROOF_PITCH")
	private String roofPitch ;
	
	@Column(name="ROOF_TILT_KIT_USED")
	private String roofTiltKitUsed ;
	
	@Column(name="ROOF_EAVE_OVERHANG")
	private String roofEaveOverhang ;
	
	@Column(name = "OTHER_ROOF_EAVE_OVERHANG")
	private String otherRoofEaveOverhang ;
	
	@Column(name="ROOF_MODULE_TILT")
	private String roofModuleTilt ;
	
	@Column(name="ROOF_MODULE_QTY")
	private String roofModuleQty ;
	
	/////------------- non roof mount -----------/////
	
	@Column(name="CONTOUR_SLOPE")
	private  String nonRoofContourSlope;
	
	@Column(name="PATH_POINT")
	private  String nonRoofPathPoint;
	
	@Column(name="GRADING_GRUBBING")
	private  String nonRoofGradingGrubbing;
	
	@Column(name="SITE_COMPOSITION")
	private  String nonRoofSiteComposition;
	
	@Column(name="ELEVATION_STRUCTURE")
	private  String nonRoofElevationStructure;
	
	@Column(name="EXISTING_SECURITY")
	private  String nonRoofExistingSecurity;
	
	@Column(name="PATIO_COVER_VAL")
	private  String nonRoofPatioCoverValue;	
	
	@Column(name="NON_ROOF_MODULE_AZIMUTH")
	private String nonRoofModuleAzimuth;
	
	@Column(name="NON_ROOF_MODULE_TILT")
	private String nonRoofModuleTilt;
	
	@Column(name="NON_ROOF_MODULE_QTY")
	private String nonRoofModuleQty;
	/////------------- Patio cover attached -----------/////
	
	
	@Column(name="ATTACHED_TYPE_BEAM")
	private  String patioCoverAttachedTypeBeam;	
	
	
	@Column(name="ATTACHED_TYPE_POSTS")
	private  String patioCoverAttachedTypePosts;	
	
	
	
	/////------------- Patio cover freestanding -----------/////
	
	
	@Column(name="FREESTANDING_TYPE_BEAM")
	private  String patioCoverFreestandingTypeBeam;	
	
	@Column(name="FREESTANDING_TYPE_POSTS")
	private  String patioCoverFreestandingTypePosts;	
	
	@Column(name="FREESTANDING_EXTAND_OVER")
	private  String patioCoverFreestandingExtendOver;	
	
	
	@Column(name="FREESTANDING_PAST_EAVE")
	private  String patioCoverFreestandingPastEave;
	
	@Column(name="PATIO_MODULE_AZIMUTH")
	private String patioModuleAzimuth;
	
	@Column(name="PATIO_ROOF_PITCH")
	private String patioroofPitch;
	
	@Column(name="PATIO_ROOF_TILT_KIT_USED")
	private String patioroofTiltKitUsed;
	
	@Column(name="PATIO_ROOF_EAVE_OVERHANG")
	private String patioroofEaveOverhang;
	
	@Column(name="OTHER_PATIO_EAVE_OVERHANG")
	private String otherPatioEaveOverhang;
	
	@Column(name="PATIO_ROOF_MODULE_TILT")
	private String patioroofModuleTilt;
	
	@Column(name="PATIO_ROOF_MODULE_QTY")
	private String patioroofModuleQty;
	//*********************** others ****************************//
	
	@Column(name="OTHER_VOLTAGE_OTHER")
	private  String otherVoltageOther;
	
	
	@Column(name="ROOF_MATERIALTYPE_OTHER")
	private  String roofMaterialTypeOther;	
	
	
	@Column(name="POST_SOLARRATE_OTHER")
	private  String postSolarRateOther;	
	
	
	@Column(name="OTHER_PATIO_COVER_ATTACHEDTYPE_BEAM")
	private  String otherPatioCoverAttachedTypeBeam;	
	
	
	@Column(name="OTHER_PATIO_COVERATTACHED_TYPE_POSTS")
	private  String otherPatioCoverAttachedTypePosts;	
	
	
	@Column(name="OTHER_PATIO_COVER_FREE_STANDING_TYPE_BEAM")
	private  String otherPatioCoverFreestandingTypeBeam;	
	
	
	@Column(name="OTHER_PATIO_COVER_FREE_STANDING_TYPE_POSTS")
	private  String otherPatioCoverFreestandingTypePosts;


	@Column(name="UTILITY_METER_OR_PROPOSED_AC_DISCO")
	private  String utilityMeterOrProposedACDisco;
	
	@Column(name="CENTER_FED")
	private  String centerFed;
	

	@Column(name="EXISTING_PV_SYSTEM_AT_SITE")
	private  String existingPVSystemAtSite;
	
	
	@Column(name="UPGRADING_MAIN_SERIVCE_PANEL")
	private  String upgradingMainServicePanel;
	
	@Column(name="PROPOSED_BATTERY")
	private  String proposedBattery;
	
	@Column(name="WIN_RUN_ON_ROOF")
	private  String wireRunOnRoof;
	
	@Column(name="WIRE_RUN_IN_ATTIC")
	private  String wireRunInAttic;
	
	@Column(name="TILTUP_MODULES")
	private  String tiltupModules;
	
	@Column(name="NON_ROOF_CARPORT")
	private  String nonRoofCarport;	
	
	@Column(name="NON_ROOF_PATIO_COVER")
	private  String nonRoofPatioCover;
	
	
	@Column(name="TIE_IN_POC_IS_ATSUBPANEL")
	private  String tieInPOCIsAtSubpanel;
	
	@Column(name="EXISTING_SUBPANEL")
	private  String existingSubpanel;
	
	
	@Column(name="IMAGE_CARPORT_ARRAY_LOCATION")
	private String imageOfCarPortArrayLocation;
	
	@Column(name="IMAGE_UTILITY_INFORMATION")
	private String imageOfutilityInformation;
	
	@Column(name="IMAGE_EXISTING_PV")
	private String imageOfExistingPV;
	
	@Column(name="IMAGE_ROOF_ATTIC")
	private String imageOfRoofAttic;
	
	@Column(name="IMAGE_ARRAY_LOCATION")
	private String imageOfArrayLocation;
	
	@Column(name="SELECT_IF_ATTACHED")
	private String selectIfAttach;
	
	@Column(name="ROOF_OPEN_FRAME")
	private String roofOrOpenFrame;
	
	@Column(name="SUM_EXIST_CIRCUIT")
	private String sumofexistCircuit;
	
	@Column(name="NOTES_GROUND_MOUNT")
	private String notesGroundMount;
	
	@Column(name="ADDRESS_LINE_2")
	private String addressLine2 ;
	
	@Column(name="SUB_PANEL_BREAKER_OCPD")
	private String subPanelBreakerOCPD;
	
	@Column(name="SECOND_OTHER_VOLTAGE_OTHER")
	private String secondOtherVoltageOther;
	
	@Column(name="NOTES_EXISTING_PV_SYSTEM")
	private String notesExistingPvSystem ;
	
	@Column(name="NETWORK_NAME")
	private String networkName;
	
	@Column(name="NETWORK_PASSWORD")
	private String networkPassword;
	
	@Column(name="ROOF_CONDITION")
	private String roofCondition;
	
	@Column(name="ROOF_NOTES")
	private String roofNotes;
	
	@Column(name="MEASURMENTS_OF_AREA")
	private String measurmentsOfArea;
	
	@Column(name="NOTES_CARPOT_PATIOT")
	private String notesOnCarpotOrPatiot;
	
	@Column(name="IMAGE_UTILITY_INFO")
	private String imageOfUtilityInfo;
	
	@Column(name="IMAGE_SITE_NOTES")
	private String imageOfSiteNotes;
	
	@Column(name="PV_MODEL_CARPOT_PATIO")
	private String pVModelCarpotPatio;
	
	@Column(name="PV_MODEL_NON_ROOF")
	private String pVModelNonRoof;
	
	@Column(name="MEAS_AREA_CARPOT")
	private String measOfAreaCarpot;
	
	@Column(name="HOMEOWNER_LAST_NAME")
	private String homeOwnLastName;
	
	@Column(name="RPROJECT_NAME")
	private String projectName;
	//***************** getter & setter methods *************************//
	
	

	
	
	public String getNonRoofPatioCover() {
		return nonRoofPatioCover;
	}

	public void setNonRoofPatioCover(String nonRoofPatioCover) {
		this.nonRoofPatioCover = nonRoofPatioCover;
	}

	public String getExistingBattery() {
		return existingBattery;
	}

	public void setExistingBattery(String existingBattery) {
		this.existingBattery = existingBattery;
	}

	public String getExistingPVSystemAtSite() {
		return existingPVSystemAtSite;
	}

	public void setExistingPVSystemAtSite(String existingPVSystemAtSite) {
		this.existingPVSystemAtSite = existingPVSystemAtSite;
	}

	public String getCenterFed() {
		return centerFed;
	}

	public void setCenterFed(String centerFed) {
		this.centerFed = centerFed;
	}

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

	public String getHomeOwnName() {
		return homeOwnName;
	}

	public void setHomeOwnName(String homeOwnName) {
		this.homeOwnName = homeOwnName;
	}

	public String getBasicTypeOfSystem() {
		return basicTypeOfSystem;
	}

	public void setBasicTypeOfSystem(String basicTypeOfSystem) {
		this.basicTypeOfSystem = basicTypeOfSystem;
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

	public String getBuildingOccupancy() {
		return buildingOccupancy;
	}

	public void setBuildingOccupancy(String buildingOccupancy) {
		this.buildingOccupancy = buildingOccupancy;
	}

	public String getNumberOfStories() {
		return numberOfStories;
	}

	public void setNumberOfStories(String numberOfStories) {
		this.numberOfStories = numberOfStories;
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

	public String getImageOfSiteInformationRafter() {
		return imageOfSiteInformationRafter;
	}

	public void setImageOfSiteInformationRafter(String imageOfSiteInformationRafter) {
		this.imageOfSiteInformationRafter = imageOfSiteInformationRafter;
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

	public String getMspbusbarRating() {
		return mspbusbarRating;
	}

	public void setMspbusbarRating(String mspbusbarRating) {
		this.mspbusbarRating = mspbusbarRating;
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

	public String getUtilityFeederGauge() {
		return utilityFeederGauge;
	}

	public void setUtilityFeederGauge(String utilityFeederGauge) {
		this.utilityFeederGauge = utilityFeederGauge;
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

	public String getImageOfExistingMainPanel() {
		return imageOfExistingMainPanel;
	}

	public void setImageOfExistingMainPanel(String imageOfExistingMainPanel) {
		this.imageOfExistingMainPanel = imageOfExistingMainPanel;
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

	public String getImageOfExistingSubPanel() {
		return imageOfExistingSubPanel;
	}

	public void setImageOfExistingSubPanel(String imageOfExistingSubPanel) {
		this.imageOfExistingSubPanel = imageOfExistingSubPanel;
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

	public String getSquareFeetOfLivingArea() {
		return squareFeetOfLivingArea;
	}

	public void setSquareFeetOfLivingArea(String squareFeetOfLivingArea) {
		this.squareFeetOfLivingArea = squareFeetOfLivingArea;
	}

	public String getRecentAnnualUsage() {
		return recentAnnualUsage;
	}

	public void setRecentAnnualUsage(String recentAnnualUsage) {
		this.recentAnnualUsage = recentAnnualUsage;
	}

	public String getNumberOfElectricVehicles() {
		return numberOfElectricVehicles;
	}

	public void setNumberOfElectricVehicles(String numberOfElectricVehicles) {
		this.numberOfElectricVehicles = numberOfElectricVehicles;
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

	public String getpVQuantity() {
		return pVQuantity;
	}

	public void setpVQuantity(String pVQuantity) {
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

	public String getGridTiedInverterQuantity() {
		return gridTiedInverterQuantity;
	}

	public void setGridTiedInverterQuantity(String gridTiedInverterQuantity) {
		this.gridTiedInverterQuantity = gridTiedInverterQuantity;
	}

	public String getExistingACDisco() {
		return existingACDisco;
	}

	public void setExistingACDisco(String existingACDisco) {
		this.existingACDisco = existingACDisco;
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

	public String getACDiscoRating() {
		return aCDiscoRating;
	}

	public void setACDiscoRating(String aCDiscoRating) {
		this.aCDiscoRating = aCDiscoRating;
	}

	public String getConnectionType() {
		return connectionType;
	}

	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}

	public String getpVBreaker1() {
		return pVBreaker1;
	}

	public void setpVBreaker1(String pVBreaker1) {
		this.pVBreaker1 = pVBreaker1;
	}

	public String getpVBreaker2() {
		return pVBreaker2;
	}

	public void setpVBreaker2(String pVBreaker2) {
		this.pVBreaker2 = pVBreaker2;
	}

	public String getpVBreaker3() {
		return pVBreaker3;
	}

	public void setpVBreaker3(String pVBreaker3) {
		this.pVBreaker3 = pVBreaker3;
	}

	public String getpVBreaker4() {
		return pVBreaker4;
	}

	public void setpVBreaker4(String pVBreaker4) {
		this.pVBreaker4 = pVBreaker4;
	}

	public String getpVBreaker5() {
		return pVBreaker5;
	}

	public void setpVBreaker5(String pVBreaker5) {
		this.pVBreaker5 = pVBreaker5;
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

	public String getInverter1Model() {
		return inverter1Model;
	}

	public void setInverter1Model(String inverter1Model) {
		this.inverter1Model = inverter1Model;
	}


	public String getInverter2Model() {
		return inverter2Model;
	}

	public void setInverter2Model(String inverter2Model) {
		this.inverter2Model = inverter2Model;
	}


	public String getInverter3Model() {
		return inverter3Model;
	}

	public void setInverter3Model(String inverter3Model) {
		this.inverter3Model = inverter3Model;
	}

	public String getImageBatteryInfo() {
		return imageBatteryInfo;
	}

	public void setImageBatteryInfo(String imageBatteryInfo) {
		this.imageBatteryInfo = imageBatteryInfo;
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

	public String getVerifyRoofMeasurementsPreRoofLayoutProvided() {
		return verifyRoofMeasurementsPreRoofLayoutProvided;
	}

	public void setVerifyRoofMeasurementsPreRoofLayoutProvided(String verifyRoofMeasurementsPreRoofLayoutProvided) {
		this.verifyRoofMeasurementsPreRoofLayoutProvided = verifyRoofMeasurementsPreRoofLayoutProvided;
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

	public String getRoofMaterial() {
		return roofMaterial;
	}

	public void setRoofMaterial(String roofMaterial) {
		this.roofMaterial = roofMaterial;
	}

	public String getNumberOfLayers() {
		return numberOfLayers;
	}

	public void setNumberOfLayers(String numberOfLayers) {
		this.numberOfLayers = numberOfLayers;
	}

	public String getRoofAge() {
		return roofAge;
	}

	public void setRoofAge(String roofAge) {
		this.roofAge = roofAge;
	}

	public String getpVModel() {
		return pVModel;
	}

	public void setpVModel(String pVModel) {
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

	public String getRafterTrussSpacing() {
		return rafterTrussSpacing;
	}

	public void setRafterTrussSpacing(String rafterTrussSpacing) {
		this.rafterTrussSpacing = rafterTrussSpacing;
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

	public String getOtherVoltageOther() {
		return otherVoltageOther;
	}

	public void setOtherVoltageOther(String otherVoltageOther) {
		this.otherVoltageOther = otherVoltageOther;
	}

	public String getRoofMaterialTypeOther() {
		return roofMaterialTypeOther;
	}

	public void setRoofMaterialTypeOther(String roofMaterialTypeOther) {
		this.roofMaterialTypeOther = roofMaterialTypeOther;
	}

	public String getPostSolarRateOther() {
		return postSolarRateOther;
	}

	public void setPostSolarRateOther(String postSolarRateOther) {
		this.postSolarRateOther = postSolarRateOther;
	}

	public String getOtherPatioCoverAttachedTypeBeam() {
		return otherPatioCoverAttachedTypeBeam;
	}

	public void setOtherPatioCoverAttachedTypeBeam(String otherPatioCoverAttachedTypeBeam) {
		this.otherPatioCoverAttachedTypeBeam = otherPatioCoverAttachedTypeBeam;
	}

	public String getOtherPatioCoverAttachedTypePosts() {
		return otherPatioCoverAttachedTypePosts;
	}

	public void setOtherPatioCoverAttachedTypePosts(String otherPatioCoverAttachedTypePosts) {
		this.otherPatioCoverAttachedTypePosts = otherPatioCoverAttachedTypePosts;
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

	public String getUtilityMeterOrProposedACDisco() {
		return utilityMeterOrProposedACDisco;
	}

	public void setUtilityMeterOrProposedACDisco(String utilityMeterOrProposedACDisco) {
		this.utilityMeterOrProposedACDisco = utilityMeterOrProposedACDisco;
	}

	public String getUpgradingMainServicePanel() {
		return upgradingMainServicePanel;
	}

	public void setUpgradingMainServicePanel(String upgradingMainServicePanel) {
		this.upgradingMainServicePanel = upgradingMainServicePanel;
	}

	public String getProposedBattery() {
		return proposedBattery;
	}

	public void setProposedBattery(String proposedBattery) {
		this.proposedBattery = proposedBattery;
	}


	public String getWireRunOnRoof() {
		return wireRunOnRoof;
	}

	public void setWireRunOnRoof(String wireRunOnRoof) {
		this.wireRunOnRoof = wireRunOnRoof;
	}

	public String getWireRunInAttic() {
		return wireRunInAttic;
	}

	public void setWireRunInAttic(String wireRunInAttic) {
		this.wireRunInAttic = wireRunInAttic;
	}

	public String getTiltupModules() {
		return tiltupModules;
	}

	public void setTiltupModules(String tiltupModules) {
		this.tiltupModules = tiltupModules;
	}

	public String getNonRoofCarport() {
		return nonRoofCarport;
	}

	public void setNonRoofCarport(String nonRoofCarport) {
		this.nonRoofCarport = nonRoofCarport;
	}

	public String getTieInPOCIsAtSubpanel() {
		return tieInPOCIsAtSubpanel;
	}

	public void setTieInPOCIsAtSubpanel(String tieInPOCIsAtSubpanel) {
		this.tieInPOCIsAtSubpanel = tieInPOCIsAtSubpanel;
	}

	public String getExistingSubpanel() {
		return existingSubpanel;
	}

	public void setExistingSubpanel(String existingSubpanel) {
		this.existingSubpanel = existingSubpanel;
	}
	

	public String getImageOfCarPortArrayLocation() {
		return imageOfCarPortArrayLocation;
	}

	public void setImageOfCarPortArrayLocation(String imageOfCarPortArrayLocation) {
		this.imageOfCarPortArrayLocation = imageOfCarPortArrayLocation;
	}

	public String getImageOfutilityInformation() {
		return imageOfutilityInformation;
	}

	public void setImageOfutilityInformation(String imageOfutilityInformation) {
		this.imageOfutilityInformation = imageOfutilityInformation;
	}

	public String getImageOfExistingPV() {
		return imageOfExistingPV;
	}

	public void setImageOfExistingPV(String imageOfExistingPV) {
		this.imageOfExistingPV = imageOfExistingPV;
	}

	public String getImageOfRoofAttic() {
		return imageOfRoofAttic;
	}

	public void setImageOfRoofAttic(String imageOfRoofAttic) {
		this.imageOfRoofAttic = imageOfRoofAttic;
	}

	public String getImageOfArrayLocation() {
		return imageOfArrayLocation;
	}

	public void setImageOfArrayLocation(String imageOfArrayLocation) {
		this.imageOfArrayLocation = imageOfArrayLocation;
	}

	public String getSelectIfAttach() {
		return selectIfAttach;
	}

	public void setSelectIfAttach(String selectIfAttach) {
		this.selectIfAttach = selectIfAttach;
	}

	public String getRoofOrOpenFrame() {
		return roofOrOpenFrame;
	}

	public void setRoofOrOpenFrame(String roofOrOpenFrame) {
		this.roofOrOpenFrame = roofOrOpenFrame;
	}

	public String getSumofexistCircuit() {
		return sumofexistCircuit;
	}

	public void setSumofexistCircuit(String sumofexistCircuit) {
		this.sumofexistCircuit = sumofexistCircuit;
	}

	public String getNotesGroundMount() {
		return notesGroundMount;
	}

	public void setNotesGroundMount(String notesGroundMount) {
		this.notesGroundMount = notesGroundMount;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getSubPanelBreakerOCPD() {
		return subPanelBreakerOCPD;
	}

	public void setSubPanelBreakerOCPD(String subPanelBreakerOCPD) {
		this.subPanelBreakerOCPD = subPanelBreakerOCPD;
	}

	public String getSecondOtherVoltageOther() {
		return secondOtherVoltageOther;
	}

	public void setSecondOtherVoltageOther(String secondOtherVoltageOther) {
		this.secondOtherVoltageOther = secondOtherVoltageOther;
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

	public String getNotesOnCarpotOrPatiot() {
		return notesOnCarpotOrPatiot;
	}

	public void setNotesOnCarpotOrPatiot(String notesOnCarpotOrPatiot) {
		this.notesOnCarpotOrPatiot = notesOnCarpotOrPatiot;
	}

	public String getImageOfUtilityInfo() {
		return imageOfUtilityInfo;
	}

	public void setImageOfUtilityInfo(String imageOfUtilityInfo) {
		this.imageOfUtilityInfo = imageOfUtilityInfo;
	}

	public String getImageOfSiteNotes() {
		return imageOfSiteNotes;
	}

	public void setImageOfSiteNotes(String imageOfSiteNotes) {
		this.imageOfSiteNotes = imageOfSiteNotes;
	}

	public String getRoofModuleAzimuth() {
		return roofModuleAzimuth;
	}

	public void setRoofModuleAzimuth(String roofModuleAzimuth) {
		this.roofModuleAzimuth = roofModuleAzimuth;
	}

	public String getRoofPitch() {
		return roofPitch;
	}

	public void setRoofPitch(String roofPitch) {
		this.roofPitch = roofPitch;
	}

	public String getRoofTiltKitUsed() {
		return roofTiltKitUsed;
	}

	public void setRoofTiltKitUsed(String roofTiltKitUsed) {
		this.roofTiltKitUsed = roofTiltKitUsed;
	}

	public String getRoofEaveOverhang() {
		return roofEaveOverhang;
	}

	public void setRoofEaveOverhang(String roofEaveOverhang) {
		this.roofEaveOverhang = roofEaveOverhang;
	}

	public String getOtherRoofEaveOverhang() {
		return otherRoofEaveOverhang;
	}

	public void setOtherRoofEaveOverhang(String otherRoofEaveOverhang) {
		this.otherRoofEaveOverhang = otherRoofEaveOverhang;
	}

	public String getRoofModuleTilt() {
		return roofModuleTilt;
	}

	public void setRoofModuleTilt(String roofModuleTilt) {
		this.roofModuleTilt = roofModuleTilt;
	}

	public String getRoofModuleQty() {
		return roofModuleQty;
	}

	public void setRoofModuleQty(String roofModuleQty) {
		this.roofModuleQty = roofModuleQty;
	}

	public String getNonRoofModuleAzimuth() {
		return nonRoofModuleAzimuth;
	}

	public void setNonRoofModuleAzimuth(String nonRoofModuleAzimuth) {
		this.nonRoofModuleAzimuth = nonRoofModuleAzimuth;
	}

	public String getNonRoofModuleTilt() {
		return nonRoofModuleTilt;
	}

	public void setNonRoofModuleTilt(String nonRoofModuleTilt) {
		this.nonRoofModuleTilt = nonRoofModuleTilt;
	}

	public String getNonRoofModuleQty() {
		return nonRoofModuleQty;
	}

	public void setNonRoofModuleQty(String nonRoofModuleQty) {
		this.nonRoofModuleQty = nonRoofModuleQty;
	}

	public String getPatioModuleAzimuth() {
		return patioModuleAzimuth;
	}

	public void setPatioModuleAzimuth(String patioModuleAzimuth) {
		this.patioModuleAzimuth = patioModuleAzimuth;
	}

	public String getPatioroofPitch() {
		return patioroofPitch;
	}

	public void setPatioroofPitch(String patioroofPitch) {
		this.patioroofPitch = patioroofPitch;
	}

	public String getPatioroofTiltKitUsed() {
		return patioroofTiltKitUsed;
	}

	public void setPatioroofTiltKitUsed(String patioroofTiltKitUsed) {
		this.patioroofTiltKitUsed = patioroofTiltKitUsed;
	}

	public String getPatioroofEaveOverhang() {
		return patioroofEaveOverhang;
	}

	public void setPatioroofEaveOverhang(String patioroofEaveOverhang) {
		this.patioroofEaveOverhang = patioroofEaveOverhang;
	}

	public String getOtherPatioEaveOverhang() {
		return otherPatioEaveOverhang;
	}

	public void setOtherPatioEaveOverhang(String otherPatioEaveOverhang) {
		this.otherPatioEaveOverhang = otherPatioEaveOverhang;
	}

	public String getPatioroofModuleTilt() {
		return patioroofModuleTilt;
	}

	public void setPatioroofModuleTilt(String patioroofModuleTilt) {
		this.patioroofModuleTilt = patioroofModuleTilt;
	}

	public String getPatioroofModuleQty() {
		return patioroofModuleQty;
	}

	public void setPatioroofModuleQty(String patioroofModuleQty) {
		this.patioroofModuleQty = patioroofModuleQty;
	}

	public String getpVModelCarpotPatio() {
		return pVModelCarpotPatio;
	}

	public void setpVModelCarpotPatio(String pVModelCarpotPatio) {
		this.pVModelCarpotPatio = pVModelCarpotPatio;
	}

	public String getpVModelNonRoof() {
		return pVModelNonRoof;
	}

	public void setpVModelNonRoof(String pVModelNonRoof) {
		this.pVModelNonRoof = pVModelNonRoof;
	}

	public String getMeasOfAreaCarpot() {
		return measOfAreaCarpot;
	}

	public void setMeasOfAreaCarpot(String measOfAreaCarpot) {
		this.measOfAreaCarpot = measOfAreaCarpot;
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

	public String getMeanHeight() {
		return meanHeight;
	}
	
	public void setMeanHeight(String meanHeight) {
		this.meanHeight = meanHeight;
	}

	public String getNotesOnExistingBatterySystem() {
		return notesOnExistingBatterySystem;
	}

	public void setNotesOnExistingBatterySystem(String notesOnExistingBatterySystem) {
		this.notesOnExistingBatterySystem = notesOnExistingBatterySystem;
	}


}
