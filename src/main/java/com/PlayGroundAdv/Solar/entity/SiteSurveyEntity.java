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
@Table(name = "SiteSurveyEntity")
public class SiteSurveyEntity {


private static final long serialVersionUID = 1L;
	
	
	@Id
	@SequenceGenerator(name="SiteSurveysequence",
			           sequenceName="SiteSurveysequence",
			           allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SiteSurveysequence")  
	private Long id;
	
	/////------------- basic information -----------/////
	
	@Column(name="HOME_OWN_NAME")
	private String homeOwnName;
	
	@JoinColumn(name = "CREATED_BY")
	@ManyToOne
	private AuthentificationEntity createdBy;
	
	@JoinColumn(name = "PORTAL_PROJECT")
	@ManyToOne
	private PermitEntity portalProject;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="HAS_PROJECT")
	private Boolean hasProject;
	
	@Column(name="IS_CANCELED")
	private Boolean isCanceled;
	
	@Column(name="SUBMITTED")
	private Boolean submitted;
	
	/////------------- action dates -----------/////
	
	@Column(name="CREATION_DATE")
	private Date creationDate;
	
	@Column(name="LAST_UPDATED_DATE")
	private Date lastUpdatedDate;
	
	@Column(name="DATE_OF_SUBMIT")
	private Date dateOfSubmit;
	
	@Column(name="CANCEL_DATE")
	private Date cancelDate;
	
	/////------------- site information -----------/////
	
	@Column(name="Basic_Type_Of_System")
	private String basicTypeOfSystem; 
	
	@Column(name="Has_Project_Site_Image")
	private Boolean hasProjectSiteImage; 

	@Column(name="CONTACT_NAME")
	private String contactName; 

	@Column(name="STREET_ADDRESS")
	private String streetAddress ;

	// CR 2860
	@Column(name="ADDRESS_LINE_2")
	private String addressLine2 ;
	
	@Column(name="CITY")
	private String city ;
		
	@Column(name="STATE")
	private String state ;
		
	@Column(name="ZIP")
	private String zIP ;
	
	@Column(name="MAIN_CONTACT_PHONE")
	private String mainContactPhone ;

	@Column(name="OTHER_PHONE")
	private String otherPhone ;

	@Column(name="EMAIL_ADDRESS")
	private String emailAddress ;
		
	@Column(name="HOA")
	private String hoa ;
		
	@Column(name="PERMITTING_AUTHORITY")
	private String permittingAuthority ;

	@Column(name="LEGAL_OWNER_NAME")
	private String legalOwnerName ;
		
	@Column(name="PARCEL_NUMBER")
	private String parcelNumber; 
		
	@Column(name="ROOF_MATERIAL_TYPE")
	private String roofMaterialType ;

	@Column(name="WIDTH_SEAMS")
	private String widthSeams;
		
	@Column(name="RISK_CATEGORY")
	private String riskCategory; 
		
	@Column(name="OTHER_RISK_CATEGORY")
	private String otherRiskCategory; 
		
	@Column(name="BUILDING_OCCUPANCY")
	private String buildingOccupancy; 
		
	@Column(name="OTHER_BUILDING_OCCUPANCY")
	private String otherBuildingOccupancy;
		
	//CR 2860 : change type of number of stories from integer to string and add otherTallStructure
	@Column(name="NUMBER_OF_STORIES_")
	private String numberOfStories;
	
	@Column(name="MEAN_HEIGHT")
	private Integer meanHeight;
		
	@Column(name="OTHER_TALL_STRUCTURE")
	private String otherTallStructure;
	
	@Column(name="PROJECT_EQUIPMENT_STAGING_LOCATION")
	private String projectEquipmentStagingLocation ;
		
	@Column(name="OWNER_PREFERRED_ROOF_ACCESS_LOCATION")
	private String ownerPreferredRoofAccessLocation;
		
	@Column(name="ACCESS_ISSUES_WITH_METER_OR_PROPOSED_AC_DISCO")
	private String accessIssuesWith_MeterOrProposedACDisco; 
		
	@Column(name="UTILITY_METER_OR_PROPOSED_AC_DISCO")
	private Boolean utilityMeterOrProposedACDisco ;
	
	@Column(name="UNRESTRAINED_ANIMAL")
	private Boolean unrestrainedAnimal ;
	
	@Column(name="OTHER_ACCESS_ISSUE")
	private Boolean otheraccessissue ;
		
	@Column(name="DESCRIBE_ACCESS_ISSUES")
	private String describeAccessIssues ;
		
	@Column(name="CONTACT_PERSON_FOR_UTILITY")
	private String contactpersonforutility ;
		
	@Column(name="OTHER_CONTACT_PERSON")
	private String othercontactperson ;
		
	@Column(name="CONTACT_PHONE")
	private String contactphone ;
		
	@Column(name="OTHER_CONTACT_PHONE")
	private String othercontactphone ;

	@Column(name="HAS_IMAGE_CAPTURES_AT_RAFTERTRUSSES")
	private Boolean hasImageCapturesatRafterTrusses ;

	@Column(name="IMAGE_SI_RAFTER")
	private String imageOfSiteInformationRafter;
	
	@Column(name="ROOF_MOUNTED")
	private Boolean roofMounted ;
	
	@Column(name="GROUND_MOUNTED")
	private Boolean groundMounted ;
	
	@Column(name="CARPORT_MOUNTED")
	private Boolean carportMounted;
	
	@Column(name="PATIO_MOUNTED")
	private Boolean patioMounted;
	
	@Column(name="TEXT_OTHER")
	private String textOther ;
	
	@Column(name="OTHER_MOUNTED")
	private Boolean otherMounted ;
	
	/////------------- existing main panel -----------/////
	

	@Column(name="CENTER_FED")
	private  Boolean centerFed;
	
	@Column(name="MAIN_PANEL_MAKE")
	private String mainPanelMake ;
	
	@Column(name="MAIN_PANEL_MODEL")
	private String mainPanelModel ;
	
	@Column(name="UPGRADING_MAIN_SERVICE_PANEL")
	private Boolean upgradingMainServicePanel;
	
	@Column(name="VOLTAGE")
	private String voltage ;
	
	@Column(name="OTHER_VOLTAGE")
	private String otherVoltage ;
	
	@Column(name="MSP_BUSBAR_RATING")
	private String mspbusbarRating;
	
	@Column(name="OTHER_MSP_BUSBAR_RATING")
	private String otherMSPBusbarRating;
	
	@Column(name="MSP_MAIN_BREAKER_RATING")
	private String mSPMainBreakerRating;
	
	@Column(name="OTHER_MSP_MAIN_BREAKER_RATING")
	private String otherMSPMainBreakerRating;
	
	@Column(name="OPEN_BREAKER_SLOTS")
	private String openBreakerSlots ;
	
	@Column(name="SCIR")
	private String sCIR ;
	
	@Column(name="MAIN_BREAKER_FEED_GAUGE")
	private String mainBreakerFeedGauge;
	
	@Column(name="OTHER_MAIN_BREAKER_FEED_GAUGE")
	private String otherMainBreakerFeedGauge;
	
	@Column(name="UTILITY_FEEDER_GAUGE")
	private String utilityFeederGauge;
	
	@Column(name="COPPER_WIRES_SAME_AS_MAIN_CONTACT")
	private  Boolean copperWiresSameAsMainContact;
	
	@Column(name="COPPER_WIRES_DIFFERENT_NUMBER")
	private  Boolean copperWiresDifferentNumber;
	
	@Column(name="ALUMINUM_WIRES_SAME_AS_MAIN_CONTACT")
	private  Boolean aluminumWiresSameAsMainContact;
	
	@Column(name="ALUMINUM_WIRES_DIFFERENT_NUMBER")
	private  Boolean aluminumWiresDifferentNumber;
	
	@Column(name="GROUND_ACCESSIBLE")
	private String groundAccessible;
	
	@Column(name="UNDERGROUND_OR_OVERHEAD_FEED")
	private String undergroundOrOverheadFeed;
	
	@Column(name="HAS_IMAGE_AT_MSP")
	private Boolean hasImageAtMSP; 

	@Column(name="IMAGE_EXIST_MAIN_PANEL")
	private String imageOfExistingMainPanel;
	
	
	/////------------- existing subpanel -----------/////
	
	
	@Column(name="EXISTING_SUBPANEL")
	private Boolean existingSubpanel ;
	
	@Column(name="TIE_IN_POC_IS_AT_SUBPANEL")
	private Boolean tieInPOCIsAtSubpanel;
	
	@Column(name="SUBPANEL_MAKE")
	private String subpanelMake;
	
	@Column(name="SUBPANEL_MODEL")
	private String subpanelModel;
	
	@Column(name="SUBPANEL_VOLTAGE")
	private String subpanelVoltage;
	
	@Column(name="OTHER_SUBPANEL_VOLTAGE")
	private String otherSubpanelVoltage;
	
	@Column(name="SUBPANEL_BUSBAR_RATING")
	private String subpanelBusbarRating;
	
	@Column(name="OTHER_SUBPANEL_BUSBAR_RATING")
	private String otherSubpanelBusbarRating;
	
	@Column(name="SUBPANEL_MAIN_BREAKER_RATING")
	private String subpanelMainBreakerRating;
	
	@Column(name="OTHER_SUBPANEL_MAIN_BREAKER_RATING")
	private String otherSubpanelMainBreakerRating;
	
	@Column(name="OPEN_BREAKER_SLOTS_IN_SUBPANEL")
	private String openBreakerSlotsinSubpanel;
	
	@Column(name="SUBPANEL_FEEDER_GAUGE")
	private String subpanelFeederGauge;
	
	@Column(name="OTHER_SUBPANEL_FEEDER_GAUGE")
	private String otherSubpanelFeederGauge;
	
	@Column(name="HAS_IMAGE_CAPTURES_AT_SUP_PANEL")
	private Boolean hasImageCapturesAtSupPanel; 

	@Column(name="IMAGE_EXIST_SUP_PANEL")
	private String imageOfExistingSubPanel;
	
	
	@Column(name="SUB_PANEL_BREAKER_OCPD")
	private String subPanelBreakerOCPD;
	
	@Column(name="SECOND_OTHER_VOLTAGE_OTHER")
	private String secondOtherVoltageOther;
	/////------------- site note -----------/////
	 
	@Column(name="SITE_NOTES")
	private String siteNotes;

	
	/////------------- utility information -----------/////
	
	@Column(name="UTILITY_CO_NAME")
	private String utilityCoName;
	
	@Column(name="METER_NUMBER")
	private String meterNumber;
	
	@Column(name="NEM_TYPE")
	private String nEMType;
	
	@Column(name="OTHER_NEM_TYPE")
	private String otherNEMType;
	
	@Column(name="POST_SOLAR_RATE")
	private String postSolarRate;
	
	@Column(name="USAGE_HISTORY_OFFSET")
	private String usageHistoryOffset;
	
	@Column(name="PACE_ENTITY")
	private String paceEntity;
	
	@Column(name="PACE_ENTITY_OTHER")
	private  String paceEntityOther;
	
	@Column(name="AC_DISCO_WILL_BE_MORE_THAN_10_FROM_UTILITY_METER")
	private String aCDiscoWillbemorethan10FromUtilityMeter;
	
	@Column(name="AT_LEAST_4_MONTHS_OF_ELECTRIC_BILLING_HISTORY")
	private String atLeast4MonthsOfelectricbillinghistory ;
	
	@Column(name="SQUARE_FEET_OF_LIVING_AREA")
	private Integer squareFeetOfLivingArea;
	
	@Column(name="RECENT_ANNUAL_USAGE")
	private Integer recentAnnualUsage;
	
	@Column(name="NUMBER_OF_ELECTRIC_VEHICLES")
	private Integer numberOfElectricVehicles; 

	
	/////------------- existing pV system at site -----------/////
	
	
	@Column(name="EXISTING_PV_SYSTEM_AT_SITE")
	private Boolean existingPVSystemAtSite;
	
	@Column(name="PV_SYSTEM_MAKE")
	private String pVSystemMake;
	
	@Column(name="PV_SYSTEM_MODEL")
	private String pVSystemModel;
	
	@Column(name="PV_QUANTITY")
	private Integer pVQuantity;
	
	@Column(name="GRID_TIED_INVERTER_MAKE")
	private String gridTiedInverterMake;
	
	@Column(name="GRID_TIED_INVERTER_MODEL")
	private String gridTiedInverterModel;
	
	@Column(name="GRID_TIED_INVERTER_QUANTITY")
	private Integer gridTiedInverterQuantity;
	
	@Column(name="EXISTING_AC_DISCONNECT")
	private Integer existingACDisconnect;
	
	@Column(name="AC_DISCO_MAKE")
	private String aCDiscoMake;
	
	@Column(name="AC_DISCO_MODEL")
	private String aCDiscoModel ;
	
	@Column(name="AC_DISCO_RATING")
	private Integer aCDiscoRating ;
	
	@Column(name="CONNECTION_TYPE")
	private String connectionType;
	
	@Column(name="OTHER_CONNECTION_TYPE")
	private String otherConnectionType;
	
	@Column(name="PV_BREAKER_1")
	private Integer pVBreaker1;
	
	@Column(name="PV_BREAKER_2")
	private Integer pVBreaker2;
	
	@Column(name="PV_BREAKER_3")
	private Integer pVBreaker3;
	
	@Column(name="PV_BREAKER_4")
	private Integer pVBreaker4;
	
	@Column(name="PVBREAKER5")
	private Integer pVBreaker5; 

	@Column(name="NOTES_EXISTING_PV_SYSTEM")
	private String notesExistingPvSystem ;
	
	@Column(name="EXISTING_BATTERY")
	private Boolean existingBattery;
	
	@Column(name="NOTES_ON_EXISTING_BATTERY_SYSTEM")
	private String notesOnExistingBatterySystem;
	
	
	/////------------- battery info -----------/////
	

	@Column(name="INVERTER_TECHNOLOGY")
	private String inverterTechnology;
	
	//A.B 01-20 Inverter Update by Entity
	@JoinColumn(name = "FIRST_INVERTER")
	@ManyToOne 
	private Inverters firstInverter;
	
	@JoinColumn(name = "SECOND_INVERTER")
	@ManyToOne 
	private Inverters secondInverter;
	
	@JoinColumn(name = "THIRD_INVERTER")
	@ManyToOne 
	private Inverters thirdInverter;
	
	@Column(name="NOTES_ON_INVERTER")
	private String notesOnInverter;
	
	@Column(name="PROPOSED_BATTERY")
	private Boolean proposedBattery;
	
	@Column(name="TYPE_OF_BATTERY_SYSTEM")
	private String typeOfBatterySystem;
	
	@Column(name="BATTERY_LOCATION")
	private String batteryLocation;
	
	@Column(name="CIRCUITS_TO_RELOCATE_TO_CRITICAL_LOADS_PANEL")
	private String circuitstoRelocatetoCriticalLoadsPanel;
	
	@Column(name="CRITICAL_LOAD_PANEL_LOCATION")
	private String criticalLoadPanelLocation;
	
	@Column(name="HAS_IMAGE_CAPTURES_OF_EXISTING_SOLAR_EQUIP_LOCATIONS")
	private Boolean hasImageOfExistingSolarEquipLocations;

	@Column(name="IMAGE_BATTERY_INFO")
	private String imageBatteryInfo;
	
	//A.B Old Inverter Models
	@Column(name="INVERTER_1_MODEL")
	private String inverter1Model;
	
	@Column(name="INVERTER_2_MODEL")
	private String inverter2Model;
	
	@Column(name="INVERTER_3_MODEL")
	private String inverter3Model;
	
	/////------------- internet connection for production monitoring -----------/////	
	
	@Column(name="PRODUCTION_MONITOR")
	private String productionMonitor;
	
	@Column(name="ACTIVE_INTERNET_CONNECTION")
	private String activeInternetConnection;
	
	@Column(name="MODEM_LOCATION")
	private String modemLocation;
	
	@Column(name="CONNECT_THE_MONITOR")
	private String connectTheMonitor ;
	
	@Column(name="HAS_IMAGE_CAPTURES_OF_ROOF")
	private Boolean hasImageCapturesOfRoof;
	
	@Column(name="VERIFY_ROOF_MEASUREMENTS_PRE_ROOF_LAYOUT_PROVIDED")
	private String verifyRoofMeasurementsPreRoofLayoutProvided;
	
	@Column(name="HAS_IMAGE_CAPTURES_OF_APPLICABLE_ELEVATIONS_VIEWS")
	private Boolean hasImageCapturesOfApplicableElevationsViews;

	@Column(name="IMAGE_IC_ROOF")
	private String imageOfInternetConnectionRoof;
	
	@Column(name="IMAGE_IC_ELEVATION")
	private String imageOfInternetConnectionElevation;
	
	@Column(name="NETWORK_NAME")
	private String networkName;
	
	@Column(name="NETWORK_PASSWORD")
	private String networkPassword;
	
	/////------------- roof mount -----------/////
	
	@Column(name="WIRE_RUN_ON_ROOF")
	private Boolean wireRunOnRoof;
	
	@Column(name="WIRE_RUN_IN_ATTIC")
	private Boolean wireRunInAttic;
	
	@Column(name="TILTUP_MODULES")
	private Boolean tiltupModules;
	
	@Column(name="ROOF_MATERIAL")
	private String roofMaterial;
	
	@Column(name="NUMBER_OF_LAYERS")
	private Integer numberOfLayers;
	
	@Column(name="ROOF_AGE")
	private Integer roofAge;
	
	@Column(name="PV_MODEL")
	private String pVModel;
	
	@Column(name="GABLE_RAKE_OVERHANG")
	private String gableRakeOverhang;
	
	@Column(name="HEIGHT_AT_GUTTER")
	private String heightAtGutter;

	@Column(name="CROSS_SECTION_SIZE")
	private String crossSectionSize;
	
	@Column(name="CROSS_SECTION_SIZE_OTHER")
	private String crossSectionSizeOther;
	
	@Column(name="ROOF_STRUCTURE_CHART")
	private String roofStructureChart;
	
	@Column(name="STANCHION_MAX_SPACING")
	private String stanchionMaxSpacing ;
	
	@Column(name="RIDGE_BEAM_DEPTH_AT_ARRAYS")
	private String ridgeBeamDepthAtArrays;
	
	@Column(name="MAX_SPAN_AT_ARRAYS_HS1")
	private String maxSpanAtArraysHS1;
	
	@Column(name="MAX_SPAN_AT_ARRAYS_INCHES_HS1")
	private String maxSpanAtArraysInchesHS1;
	
	@Column(name="MAX_SPAN_AT_ARRAYS_HS2")
	private String maxSpanAtArraysHS2;
	
	@Column(name="MAX_SPAN_AT_ARRAYS_INCHES_HS2")
	private String maxSpanAtArraysInchesHS2;
	
	@Column(name="RAFTER_TRUSS_SPACING")
	private String rafterTrussSpacing;
	
	@Column(name="ROOF_RAFTER_OTHER")
	private String roofRafterOther;
	
	@Column(name="SEC_ROOF_RAFTER_OTHER")
	private String secroofRafterOther;
	
	@Column(name="ROOF_CONDITION")
	private String roofCondition;
	
	@Column(name="ROOF_NOTES")
	private String roofNotes;
	
	/////------------- non roof mount -----------/////
	
	@Column(name="CARPORT")
	private Boolean nonRoofCarport;
	
	@Column(name="PATIO_COVER")
	private Boolean nonRoofPatioCover;
	
	@Column(name="CONTOUR_SLOPE")
	private String nonRoofContourSlope;
	
	@Column(name="PATH_POINT")
	private String nonRoofPathPoint;
	
	@Column(name="GRADING_GRUBBING")
	private String nonRoofGradingGrubbing;
	
	@Column(name="SITE_COMPOSITION")
	private String nonRoofSiteComposition;
	
	@Column(name="ELEVATION_STRUCTURE")
	private String nonRoofElevationStructure;
	
	@Column(name="EXISTING_SECURITY")
	private String nonRoofExistingSecurity;
	
	@Column(name="PATIO_COVER_VAL")
	private String nonRoofPatioCoverValue;
	
	@Column(name="MEASURMENTS_OF_AREA")
	private String measurmentsOfArea;
	
	
	/////------------- Patio cover attached -----------/////
	
	
	@Column(name="ATTACHED_TYPE_BEAM")
	private String patioCoverAttachedTypeBeam;	
	
	
	@Column(name="ATTACHED_TYPE_POSTS")
	private String patioCoverAttachedTypePosts;	
	
	
	
	/////------------- Patio cover freestanding -----------/////
	
	
	@Column(name="FREESTANDING_TYPE_BEAM")
	private String patioCoverFreestandingTypeBeam;	
	
	
	@Column(name="FREESTANDING_TYPE_POSTS")
	private String patioCoverFreestandingTypePosts;	
	
	
	@Column(name="FREESTANDING_EXTAND_OVER")
	private String patioCoverFreestandingExtendOver;	
	
	
	@Column(name="FREESTANDING_PAST_EAVE")
	private String patioCoverFreestandingPastEave;
	
	@Column(name="NOTES_CARPOT_PATIOT")
	private String notesOnCarpotOrPatiot;
	
	
	//*********************** others ****************************//
	
	@Column(name="OTHER_VOLTAGE_OTHER")
	private String otherVoltageOther;	
	
	
	@Column(name="WIDTHSEAMS_OTHER")
	private String widthSeamsOther;	
	
	
	@Column(name="ROOF_MATERIALTYPE_OTHER")
	private String roofMaterialTypeOther;	
	
	
	@Column(name="POST_SOLARRATE_OTHER")
	private String postSolarRateOther;	
	
	
	@Column(name="OTHER_PATIO_COVER_ATTACHEDTYPE_BEAM")
	private String otherPatioCoverAttachedTypeBeam;	
	
	
	@Column(name="OTHER_PATIO_COVERATTACHED_TYPE_POSTS")
	private String otherPatioCoverAttachedTypePosts;	
	
	
	@Column(name="OTHER_PATIO_COVER_FREE_STANDING_TYPE_BEAM")
	private String otherPatioCoverFreestandingTypeBeam;	
	
	
	@Column(name="OTHER_PATIO_COVER_FREE_STANDING_TYPE_POSTS")
	private String otherPatioCoverFreestandingTypePosts;
	
	@Column(name="EXISTING_AC_DISCO")
	private Boolean existingACDisco;
	
	@Column(name="IMAGE_SITE_NOTE")
	private String imageOfSiteNote;
	
	@Column(name="IMAGE_UTILITY_INFORMATION")
	private String imageOfutilityInformation;
	
	@Column(name="IMAGE_EXISTING_PV")
	private String imageOfExistingPV;
	
	@Column(name="IMAGE_ROOF_ATTIC")
	private String imageOfRoofAttic;
	
	@Column(name="IMAGE_ARRAY_LOCATION")
	private String imageOfArrayLocation;
	
	@Column(name="IMAGE_CARPORT_ARRAY_LOCATION")
	private String imageOfCarPortArrayLocation;
	
	@Column(name="PROJECT_JURIS_OTHER")
	private String projectJurisOther;
	
	@Column(name="FRONTANDBACK")
	private Boolean frontAndBack;
	
	@Column(name="CATELEVER")
	private Boolean cantelever;
	
	@Column(name="ATTACHED_TO_EXTWAL")
	private Boolean attachedToExtWal;
	
	@Column(name="ATTACHED_TO_FASCIA")
	private Boolean attachedToFascia;
	
	@Column(name="ATTACHED_TO_SKYLIFTS")
	private Boolean attachedToSkylifts;
	
	@Column(name="FREE_STANDING")
	private Boolean freeStanding;
	
	@Column(name="ROOF_OPEN_FRAME")
	private String roofOrOpenFrame;
	
	@Column(name="SUM_EXIST_CIRCUIT")
	private Integer sumofexistCircuit;
	
	@Column(name="NOTES_GROUND_MOUNT")
	private String notesGroundMount;
	
	@Column(name="CITY_OTHER")
	private String cityOther;
	
	@Column(name="OTHER_MAIN_PANEL_MAKE")
	private String otherMainPanelMake;
	
	@Column(name="PV_MODEL_CARPOT_PATIO")
	private String pVModelCarpotPatio;
	
	@Column(name="PV_MODEL_NON_ROOF")
	private String pVModelNonRoof;
	
	@Column(name="UTILITY_COMPANY_NAME_OTHER")
	private String utilityCompanyNameOther;
	
	@Column(name="MEAS_AREA_CARPOT")
	private String measOfAreaCarpot;
	
	@Column(name="RAFTER_TRUSS_OTHER")
	private String rafterTrussSpOther;
	
	@Column(name="HOMEOWNER_LAST_NAME")
	private String homeOwnLastName;
	
	@Column(name="RPROJECT_NAME")
	private String projectName;
	
	//CI : 20/01/2020 Update PV MOdule By ID
	@JoinColumn(name = "PV_MODULE")
	@ManyToOne 
	private  Cmodulev2 pvModule;
	
	@JoinColumn(name = "PV_MODULE_CARPOT_PATIO")
	@ManyToOne 
	private  Cmodulev2 pvModuleCarpotPatio;
	
	@JoinColumn(name = "PV_MODULE_NON_ROOF")
	@ManyToOne 
	private  Cmodulev2 pvModuleNonRoof;
	
	//***************** getter & setter methods *************************//
	
	

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

	public AuthentificationEntity getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(AuthentificationEntity createdBy) {
		this.createdBy = createdBy;
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

	public PermitEntity getPortalProject() {
		return portalProject;
	}

	public void setPortalProject(PermitEntity portalProject) {
		this.portalProject = portalProject;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
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

	public String getOtherMainBreakerFeedGauge() {
		return otherMainBreakerFeedGauge;
	}

	public void setOtherMainBreakerFeedGauge(String otherMainBreakerFeedGauge) {
		this.otherMainBreakerFeedGauge = otherMainBreakerFeedGauge;
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
		aCDiscoRating = aCDiscoRating;
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

	public Boolean getIsCanceled() {
		return isCanceled;
	}

	public void setIsCanceled(Boolean isCanceled) {
		this.isCanceled = isCanceled;
	}

	public String getOtherVoltageOther() {
		return otherVoltageOther;
	}

	public void setOtherVoltageOther(String otherVoltageOther) {
		this.otherVoltageOther = otherVoltageOther;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	public Boolean getExistingPVSystemAtSite() {
		return existingPVSystemAtSite;
	}

	public Boolean getExistingBattery() {
		return existingBattery;
	}


	public Boolean getHasImageOfExistingSolarEquipLocations() {
		return hasImageOfExistingSolarEquipLocations;
	}

	public Boolean getProposedBattery() {
		return proposedBattery;
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

	public String getImageOfSiteNote() {
		return imageOfSiteNote;
	}

	public void setImageOfSiteNote(String imageOfSiteNote) {
		this.imageOfSiteNote = imageOfSiteNote;
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

	public String getImageOfCarPortArrayLocation() {
		return imageOfCarPortArrayLocation;
	}

	public void setImageOfCarPortArrayLocation(String imageOfCarPortArrayLocation) {
		this.imageOfCarPortArrayLocation = imageOfCarPortArrayLocation;
	}

	public String getProjectJurisOther() {
		return projectJurisOther;
	}

	public void setProjectJurisOther(String projectJurisOther) {
		this.projectJurisOther = projectJurisOther;
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

	public Cmodulev2 getPvModule() {
		return pvModule;
	}

	public void setPvModule(Cmodulev2 pvModule) {
		this.pvModule = pvModule;
	}

	public Cmodulev2 getPvModuleCarpotPatio() {
		return pvModuleCarpotPatio;
	}

	public void setPvModuleCarpotPatio(Cmodulev2 pvModuleCarpotPatio) {
		this.pvModuleCarpotPatio = pvModuleCarpotPatio;
	}

	public Cmodulev2 getPvModuleNonRoof() {
		return pvModuleNonRoof;
	}

	public void setPvModuleNonRoof(Cmodulev2 pvModuleNonRoof) {
		this.pvModuleNonRoof = pvModuleNonRoof;
	}

	//
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
//
	public Inverters getFirstInverter() {
		return firstInverter;
	}

	public void setFirstInverter(Inverters firstInverter) {
		this.firstInverter = firstInverter;
	}

	public Inverters getSecondInverter() {
		return secondInverter;
	}

	public void setSecondInverter(Inverters secondInverter) {
		this.secondInverter = secondInverter;
	}

	public Inverters getThirdInverter() {
		return thirdInverter;
	}

	public void setThirdInverter(Inverters thirdInverter) {
		this.thirdInverter = thirdInverter;
	}

	public String getMspbusbarRating() {
		return mspbusbarRating;
	}

	public void setMspbusbarRating(String mspbusbarRating) {
		this.mspbusbarRating = mspbusbarRating;
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

	public Boolean getPatioMounted() {
		return patioMounted;
	}

	public void setPatioMounted(Boolean patioMounted) {
		this.patioMounted = patioMounted;
	}
	
	
}
