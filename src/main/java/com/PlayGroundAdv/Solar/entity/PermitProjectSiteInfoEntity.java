package com.PlayGroundAdv.Solar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "PermitProjectSiteInfoEntity")
public class PermitProjectSiteInfoEntity implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="hibernate_sequence7", sequenceName = "hibernate_sequence7", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="hibernate_sequence7")  
	private Long id;
	
	@JoinColumn(name = "ID_PERMIT")
	@ManyToOne
	private PermitEntity permitEntity;
	
	@JoinColumn(name = "RAIL_RACKING_MODEL")
	@ManyToOne
	private RailRacking railRakingModel;
	
	@Column(name="TRACKING_SYSTEM_MANUFACTURER")
	private String trackingSystemManufacturer;
	
	@Column(name="TRACKING_SYSTEM_MODEL")
	private String trackingSystemModel;
	
	@Column(name="INVERT_MODEL")
	private String inverterModel;
	
	@Column(name="RAFTER_TRUSS_SPACING")
	private String rafterTrussSpacing;
	
	@Column(name="CROSS_SECTION_SIZE")
	private String crossSectionSize;
	
	@Column(name="SPAN_BETWEEN_ATTACHEMENT")
	private String spanBetweenAttachment;
	
	@Column(name="ROOF_MATERIAL_TYPE")
	private Long roofMaterialType;
	
	@Column(name="ROOF_MATERIAL_TYPE_OTHER")
	private String roofMaterialTypeOther;
	
	@Column(name="RANKING_ROOF_MANUFACTURER")
	private String rankingRoofManufacturer;
	
	@Column(name="RANKING_ROOF_MODEL")
	private String rankingRoofModel;
	
	@Column(name="MODULE_GROUNDING")
	private String moduleGrounding;
	
	@Column(name="DISCONNECT_MANUFACTURER")
	private String disconnectManufacturer;
	
	@JoinColumn(name="DISCONNECT_MODEL")
	@ManyToOne
	private DCCombinerDisconnectEntity disconnectModel;
	
	@Column(name="DISCONNECT_MODEL_MANU") //M.A : 02-11 : For old value
	private String disconnectModelManuf;
	
	@Column(name="QUANTITY_ROOFTOP")
	private String quantityRooftop;
	
	@Column(name="SOLAR_LOCATION")
	private String solarLocation;
	
	@Column(name="MAIN_PANEL_UPGRADE")
	private Boolean mainPanelUpgrade;
	
	@Column(name="PANEL_BUS_RATING")
	private String panelBusRating;
	
	@Column(name="SOLAR_INTERCONNECTION")
	private String solarInterconnection;
	
	@Column(name="SECOND_SOLAR_INTERCONNECTION")
	private String secondSolarInterconnection;
	
	@Column(name="USE_DISCONNECT_SWITH")
	private String useDisconectSwith;
	
	@Column(name="USED_BY_INVERTER_MANUFACTURER")
	private Boolean usedByInverterManufacturer;
	
	@Column(name="USED_REVENUE")
	private Boolean usedRevenue;
	
	@Column(name="SUP_PANEL_BUS_Rating")
	private String SubPanelBusRating;
	
	@Column(name="SUP_PANEL_MAIN_BREAKER_RATING")
	private String SubPanelMainBreakerRating;
	
	@Column(name="PANEL_EXISTING_PROPOSED")
	private String panelExistingProposed;
	
	@Column(name="TRACKING_SYSTEME_MANUFACTURER_OTHER")
	private String trackingSystemManufacturerOther;
	
	@Column(name="TRACKING_SYSTEM_MODEL_OTHER")
	private String trackingSystemModelOther;
	
	@Column(name="ROOF_TOP_AC_COMBINER_MODEL")
	private String rooftopACCombinerModel;
	
	@Column(name="AC_DISCONNECT_SWITCH_MANUFACTURER")
	private String ACDisconnectSwitchManufacturer;
	
	
	@Column(name="AC_DISCONNECT_SWITCH_MODEL")
	private String ACDisconnectSwitchModel;
	
	
	@Column(name="AC_DISCONNECT_SWITCH_MANUFACTURER_OTHER")
	private String ACDisconnectSwitchManufacturerOther;
	
	
	@Column(name="AC_DISCONNECT_SWITCH_MODEL_OTHER")
	private String ACDisconnectSwitchModelOther;
	
	
	@Column(name="DC_DISCONNECT_SWITCH_MANUFACTURER")
	private String DCDisconnectSwitchManufacturer;
	
	
	@Column(name="DC_DISCONNECT_SWITCH_MODEL")
	private String DCDisconnectSwitchModel;
	
	
	@Column(name="LEASE_PPA_METER_MANUFACTURER")
	private String LeasePPAMeterManufacturer;
	
	
	@Column(name="LEASE_PPA_METER_MODEL")
	private String LeasePPAMeterModel;
	
	
	@Column(name="TEXT_OTHER_SIZE")
	private String textOtherSize;
	
	@Column(name="QUANTITY_OF_COMBINER_BOX")
	private String quantityofCombinerBox;
	
	
	@Column(name="QUANTITY_OF_COMBINER_BOX_OTHER")
	private String quantityofCombinerBoxOther;
	
	@Column(name="TARCKING_SYSTEM_MANUFACTURER_FOR_SECOND_TRACKER")
	private String trackingSystemManufacturerForSecondTracker;
	
	@Column(name="TARCKING_SYSTEM_MANUFACTURER_FOR_SECOND_TRACKER_OTHER")
	private String trackingSystemManufacturerForSecondTrackerOther;
	
	@Column(name="TARCKING_SYSTEM_MODEL_FOR_SECOND_TRACKER")
	private String trackingSystemModelForSecondTracker;
	
	@Column(name="TARCKING_SYSTEM_MODEL_FOR_SECOND_TRACKER_OTHER")
	private String trackingSystemModelForSecondTrackerOther;
	
	@Column(name="RACKING_ROOF_FMANUFACTURER_OTHER")
	private String 	rankingRoofManufacturerOther;
	
	@Column(name="RANCKING_ROOF_MODEL_OTHER")
	private String  rankingRoofModelOther;
	
	@Column(name="MODULE_GROUNDING_OTHER")
	private String  moduleGroundingOther;
	
	
	@Column(name="DISCONNECT_MANUFACTURER_OTHER")
	private String  disconnectManufacturerOther;
	
	
	@Column(name="DISCONNECT_MODEL_OTHER")
	private String  disconnectModelOther;
	
	@Column(name="RAIL_CONNECTION_MODEL")
	private String railConnectionModel;
	
	@Column(name="ATTIC_JBOXSBE_UTLIZED")
	private Boolean atticJBoxesbeUtilized;
	
	@Column(name="DESCRIPTION_OF_BACK_FEED")
	private String descriptionOfBackFeed;
	
	@Column(name="GROUNDLEVEL_AC_DISCONNECT_ENCLOSURE")
	private String groundLevelACDisconnectEnclosure;
	
	@Column(name="PANEL_BUS_RATING_OTHER")
	private String panelBusRatingOther;
	
	@Column(name="PANEL_MAIN_BREAKER_RATING")
	private String panelMainBreakerRating;
	
	@Column(name="PANEL_MAIN_BREAKER_RATING_OTHER")
	private String panelMainBreakerRatingOther;
	
	@Column(name="SOLAR_INTERCONNECTION_OTHER")
	private String solarInterconnectionOther;
	
	@Column(name="SECOND_SOLAR_INTERCONNECTION_OTHER")
	private String secondSolarInterconnectionOther;
	
	@Column(name="COMBINIG_AC_CIRCUITS")
	private String combiningACCircuits;
	
	@Column(name="MORE_INTERCONNECTING_BACK_FEED")
	private Boolean morInterconnectingBackFeed;
	
	//CR_003
	@JoinColumn(name = "RAIL_RAKING_MODEL_FOR_GROUND_MOUNTED")
	@ManyToOne
	private RailRacking railRakingModelforGroundMounted;
	
	
	@Column(name="SIZE_AND_TYPE_ATTIC_JBOX")
	private String sizeAndTypeAtticJBox;
	
	@Column(name="SIZE_AND_TYPE_ATTIC_JBOX_OTHER")
	private String sizeAndTypeAtticJBoxOther;
	
	@Column(name="IF_APPLICABLE_SUB_PANEL_MAIN_BREAKER_RATING")
	private String ifApplicableSubPanelMainBreakerRating;
	
	@Column(name="PRPOSED_SUB_PANEL_MANUFACTURER")
	private String proposedSubPanelManufacturer;
	
	@Column(name="PRPOSED_SUB_PANEL_MODEL")
	private String proposedSubPanelModel;
	
	@Column(name="PRPOSED_SUB_PANEL_MODEL_Other")
	private String proposedSubPanelModelOther;
	
	@JoinColumn(name="GROUND_LEVEL_AC_COMBINER_BOX_MODEL")
	@ManyToOne
	private ACCombinerSLC groundLevelACCombinerBoxModel;
	
	@Column(name="GROUND_LEVEL_AC_COMBINER_BOX_MODEL_STRING") //M.A : 24-02
	private String groundLevelACCombinerBoxModelString;
	
	@Column(name="GROUND_LEVEL_AC_COMBINER_DISCONNECT_MODEL")
	private String groundLevelACCombinerDisconnectModel;
	
	@Column(name="GROUND_LEVEL_AC_JUNCTION_BOX_MANUFACTURER")
	private String groundLevelACJunctionBoxManufacturer;
	
	@Column(name="GROUND_LEVEL_AC_JUNCTION_BOX_MODEL")
	private String groundLevelACJunctionBoxModel;
	
	@Column(name="EQUIPEMENT_ROOF_MOUNTED_COMBINER_BOX")
	private Boolean equipmentRoofMountedACCombinerBox;
	
	@Column(name="EQUIPEMENT_ROOF_MOUNTED_AC_COMBINER_DISCONNECT")
	private Boolean equipmentRoofMountedACCombinerDisconnect;
	
	@Column(name="EQUIPEMENT_ROOF_MOUNTED_JUNCTION_BOX")
	private Boolean equipmentRoofMountedJunctionBox;
	
	@Column(name="EQUIPEMENT_ROOF_MOUNTED_SINGLE_CIRCUIT")
	private Boolean equipmentRoofMountedSingleCircuit;
	
	@Column(name="EQUIPEMENT_GROUND_LEVEL_AC_COMBINER_BOX")
	private Boolean equipmentGroundLevelACCombinerBox;
	
	@Column(name="EQUIPEMENT_GROUND_LEVEL_AC_COMBINER_DISCONNECT")
	private Boolean equipmentGroundLevelACCombinerDisconnect;
	
	@Column(name="EQUIPEMENT_GROUND_LEVEL_AC_SUB_PANEL")
	private Boolean equipmentGroundLevelACSubPanel;
	
	@Column(name="EQUIPEMENT_GROUND_LEVEL_AC_JUNCTION_BOX")
	private Boolean equipmentGroundLevelACJunctionBox;
	
	@Column(name="EQUIPEMENT_COMBINING_IN_EXISTING_SUB_PANEL")
	private Boolean equipmentCombiningInExistingSubPanel;
	
	@Column(name="EQUIPEMENT_COMBINING_IN_PROPOSED_SUB_PANEL")
	private Boolean equipmentCombiningInProposedSubPanel;
	
	@Column(name="EQUIPEMENT_COMBINING_IN_MAIN_PANEL")
	private Boolean equipmentCombiningInMainPanel;
	
	@Column(name="EQUIPEMENT_IS_OTHER")
	private Boolean equipmentisOther;
	
	@Column(name="EQUIPEMENT_OTHER")
	private String equipmentOther;
	
	@Column(name="ROOF_MOUNTED_AC_COMBINER_BOX_MANUFACTURER")
	private String roofMountedACCombinerBoxManufacturer;
	
	@Column(name="ROOF_MOUNTED_AC_COMBINER_BOX_MANUFACTURER_OTHER")
	private String roofMountedACCombinerBoxManufacturerOther;
	
	@Column(name="ROOF_MOUNTED_AC_COMBINER_BOX_MODEL")
	private String roofMountedACCombinerBoxModel;
	
	@Column(name="ROOF_MOUNTED_AC_COMBINER_BOX_MODEL_OTHER")
	private String roofMountedACCombinerBoxModelOther;
	
	@Column(name="ROOF_MOUNTED_AC_COMBINING_DISCONNECT_MANUFACTURER")
	private String roofMountedACCombiningDisconnectManufacturer;
	
	@Column(name="ROOF_MOUNTED_AC_COMBINING_DISCONNECT_MANUFACTURER_OTHER")
	private String roofMountedACCombiningDisconnectManufacturerOther;
	
	@Column(name="ROOF_MOUNTED_AC_COMBINING_DISCONNECT_MODEL")
	private String roofMountedACCombiningDisconnectModel;
	
	@Column(name="ROOF_MOUNTED_AC_COMBINING_DISCONNECT_MODEL_OTHER")
	private String roofMountedACCombiningDisconnectModelOther;
	
	@Column(name="ROOF_MOUNTED_AC_JUCTION_BOX_MANUFACTURER")
	private String roofMountedACJunctionBoxManufacturer;
	
	@Column(name="ROOF_MOUNTED_AC_JUCTION_BOX_MANUFACTURER_OTHER")
	private String roofMountedACJunctionBoxManufacturerOther;
	
	@Column(name="ROOF_MOUNTED_AC_JUCTION_BOX_MODEL")
	private String roofMountedACJunctionBoxModel;
	
	@Column(name="ROOF_MOUNTED_AC_JUCTION_BOX_MODEL_OTHER")
	private String roofMountedACJunctionBoxModelOther;
	
	@Column(name="ROOF_MOUNTED_SINGLE_CIRCUIT_AC_DISCONNECT_MANUFACTURER")
	private String roofMountedSingleCircuitACDisconnectManufacturer;
	
	@Column(name="ROOF_MOUNTED_SINGLE_CIRCUIT_AC_DISCONNECT_MANUFACTURER_OTHER")
	private String roofMountedSingleCircuitACDisconnectManufacturerOther;
	
	@Column(name="ROOF_MOUNTED_SINGLE_CIRCUIT_AC_DISCONNECT_MODEL")
	private String roofMountedSingleCircuitACDisconnectModel;
	
	@Column(name="ROOF_MOUNTED_SINGLE_CIRCUIT_AC_DISCONNECT_MODEL_OTHER")
	private String roofMountedSingleCircuitACDisconnectModelOther;
	
	@Column(name="EQUIPEMENT_MODEL_OTHER")
	private String equipmenModelOther;
	
	@Column(name="EQUIPEMENT_MANUFACTURER_OTHER")
	private String equipmenManufacturerOther;
	
	@Column(name="PROPOSED_MAIN_PANEL_MANUFACTURER")
	private String proposedMainPanelManufacturer;
	
	@Column(name="PROPOSED_MAIN_PANEL_MANUFACTURER_OTHER")
	private String proposedMainPanelManufacturerOther;
	
	@Column(name="PROPOSED_MAIN_PANEL_MODEL")
	private String proposedMainPanelModel;
	
	@Column(name="PROPOSED_MAIN_PANEL_MODEL_OTHER")
	private String proposedMainPanelModelOther;
	
	@Column(name="DERATING_THIS_PANEL_STRING")
	private String deratingthisPanelString;
	
	@Column(name="Ground_LEVEL_AC_JUNCTION_BOX_MANUFACTURER_OTHER")
	private String groundLevelACJunctionBoxManufacturerOther;
	
	@Column(name="Ground_LEVEL_AC_JUNCTION_BOX_MODEL_OTHER")
	private String groundLevelACJunctionBoxModelOther;
	
	@Column(name="MAIN_BRAIKER_OCPD_FILE")
	private byte[] mainBraikerOcpdFile;

	
	@Column(name="SUB_PANEL_BREAKER_OCPD")
	private String subPanelBreakerOCPD;
	
	
	@Column(name="MAIN_BRAIKER_LOCATED_END_BUS_BAR")
	private Boolean MainBreakerLocatedEndBusBar;
	
	
	@Column(name="INFORMATION_COVERED")
	private String installationGuidelines;
	
	@Column(name="TEXT_OTHER_RAFTER")
	private String textOtherRatfter;
	
	// CR004
	@Column(name="DISCONNECT_MANUFACTURER_TWO")
	private String disconnectManufacturerTwo;
	
	@Column(name="DISCONNECT_MODEL_OTHER_TWO")
	private String disconnectManufacturerOtherTwo;
	
	@JoinColumn(name="DISCONNECT_MODEL_TWO")
	@ManyToOne
	private DCCombinerDisconnectEntity disconnectModelTwo;
	
	@Column(name="DISCONNECT_MODEL_MANU_TWO") //M.A: 02-11: for the old value
	private String disconnectModelManuTwo;
	
	@Column(name="DISCONNECT_MANUFACTURER_TWO_OTHER")
	private String disconnectModelTwoOther;

	@Column(name="DISCONNECT_MANUFACTURER_THREE")
	private String disconnectManufacturerThree;
	
	@Column(name="DISCONNECT_MANUFACTURER_THREE_OTHER")
	private String disconnectManufacturerThreeOther;
	
	@JoinColumn(name="DISCONNECT_MODEL_THREE") 
	@ManyToOne
	private DCCombinerDisconnectEntity disconnectModelThree;
	
	@Column(name="DISCONNECT_MODEL_MANUF_THREE") //M.A: 11-02 : for old value
	private String disconnectModelManufThree;

	@Column(name="DISCONNECT_MODEL_THREE_OTHER")
	private String disconnectModelThreeOther;

	@Column(name="ROOF_TOP_AC_COMBINER_MODEL_TWO")
	private String rooftopACCombinerModelTwo;
	
	@Column(name="TALL_OTHER")
	private String tallStructure;
	
	
	@Column(name="OTHER_TALL_STRUCTURE")
	private String otherTallStructure;
	
	
	@Column(name="MEAN_HEIGHT")
	private Integer meanHeight;
	
	@Column(name="EXISTING_MAIN_PANEL_MANUFAC")
	private String existingMainPanelManufac;
	
	@Column(name="EXISTING_MAIN_PANEL_MANUFAC_OTHER")
	private String existingMainPanelManufacOther;
	
	@Column(name="GROUND_LEVEL_AC_JUNCTION_BOX_MANUFACTURER_STRING")
	private String groundLevelACJunctionBoxManufacturerString;
	
	@Column(name="GROUND_LEVEL_AC_JUNCTION_BOX_MANUFACTURER_STRING_OTHER")
	private String groundLevelACJunctionBoxManufacturerStringOther;
	
	@Column(name="GROUND_LEVEL_AC_JUNCTION_BOX_MODEL_STRING")
	private String groundLevelACJunctionBoxModelString;
	
	@Column(name="GROUND_LEVEL_AC_JUNCTION_BOX_MODEL_STRING_OTHER")
	private String groundLevelACJunctionBoxModelStringOther;
	
	@Column(name="GROUND_LEVEL_AC_SUB_PANEL_MANUFACTURER")
	private String groundLevelACSubPanelManufacturer;
	
	@Column(name="GROUND_LEVEL_AC_SUB_PANEL_MODEL")
	private String groundLevelACSubPanelModel;
	
	@Column(name="GROUND_LEVEL_AC_JUNCTION_BOX_MANUFACTURER_OTHER_TEXT")
	private String groundLevelACJunctionBoxManufactuereOtherText;
	
	@Column(name="GROUND_LEVEL_AC_JUNCTION_BOX_MODEL_OTHER_TEXT")
	private String groundLevelACJunctionBoxModelOtherText;
	
	@Column(name="PROPOSED_SUB_PANEL_MANUFACTURER_OTHER")
	private String proposedSubPanelManufacturerOther;
	
	@Column(name="SOLAR_LOCATION_OTHER")
	private String solarLocationOther;
	
	@Column(name="LEASE_PPA_METER_MODEL_OTHER")
	private String LeasePPAMeterModelOther;
	
	@Column(name="LEASE_PPA_METER_MANUFACTURER_OTHER")
	private String LeasePPAMeterManufacturerOther;
	
	@Column(name="SUB_PANEL_BUS_RATING_OTHER")
	private String SubPanelBusRatingOther;
	
	@Column(name="SUB_PANEL_BREAKER_OCPD_OTHER")
	private String subPanelBreakerOCPDOther;
	
	@Column(name="LOCATION")
	private String location;
	
	@Column(name="LOCATIONTWO")
	private String locationTwo;
	
	@Column(name="LOCATIONTHREE")
	private String locationThree;
	
	@Column(name="INSTALLINGDCBO")
	private Boolean installingDCBo;
	
	@Column(name="DCJBOXTYPE")
	private String dcJboxType;
	
	@Column(name="LOCATIONDC")
	private String locationDC;
	
	@Column(name="LOCATIONFIVE")
	private String locationFive;
	
	@Column(name="LOCATIONSIX")
	private String locationSix;
	
	@Column(name="LOCATIONFOUR")
	private String locationFour;
	
	@Column(name="PROPOSEDMAINPANMAN")
	private String proposedMainPanMan;
	
	@Column(name="THIRDSOLARINTERCONNECTION")
	private String thirdSolarInterconnection;
	
	@Column(name="FOURTHSOLARINTERCONNECTION")
	private String fourthSolarInterconnection;
	
	@Column(name="FIFTHSOLARINTERCONNECTION")
	private String fifthSolarInterconnection;
	
	@Column(name="THIRDSOLARINTERCONNECTIONOTHER")
	private String thirdSolarInterconnectionOther;
	
	@Column(name="FOURTHSOLARINTERCONNECTIONOTHER")
	private String fourthSolarInterconnectionOther;
	
	@Column(name="FIFTHSOLARINTERCONNECTIONOTHER")
	private String fifthSolarInterconnectionOther;
	
	@Column(name="THEPONTOFTHEC")
	private String thepontOfTheC;
	
	@Column(name="CONNECTIONPOINT")
	private String connectionPoint;
	
	@Column(name="THEPONTOFTHECOTHER")
	private String thepontOfTheCOther;
	
	@Column(name="DCJBOXTYPEOTHER")
	private String dcJboxTypeOther;
	
	@Column(name="PANEL_LOCATION")
	private String panelLocation;
	
	@Column(name="DISCONNECT_LOCATION")
	private String disconnectLocation;
	
	
	@Column(name="uploadComments")
	private String uploadComments;
	
	@Column(name="roofTopACCombinerDisconnect")
	private String roofTopACCombinerDisconnect;
	
	@Column(name="installRoofTopACDiscoCombiner")
	private Boolean installRoofTopACDiscoCombiner;
	
	@Column(name="installRoofTopDcJbox")
	private Boolean installRoofTopDcJbox;
	
	@Column(name="roofTopDcJboxType")
	private String roofTopDcJboxType;
	
	@Column(name="MSPHAS_NO_BRANCH_CIRCUIT_BREAKERS")
	private Boolean msphasNoBranchCircuitBreakers; 
	
	@Column(name="PROPOSED_ACCOMB_MAIN_BREAKER")
	private Boolean proposedACCombMainBreaker; 
	
	@Column(name="PROPOSED_ACCOMB_MAIN_BREAKER_RATING")
	private String proposedACCombMainBreakerRating;
	
	@Column(name="PROPOSED_ACCOMB_MAIN_BREAKER_RATING_OTHER")
	private String proposedACCombMainBreakerRatingOther;
	
	@Column(name="MICRO_INVERTER_CABLING")
	private String microInverterCabling;
	
	@Column(name="ROOF_TOP_JBOX")
	private String roofTopJbox ;
	
	@Column(name="ROOF_TOP_AC_DISCO")
	private String roofTopACDisco ;
	
	@JoinColumn(name="ROOF_TOP_AC_COMBINER")
	@ManyToOne
	private ACCombinerSLC roofTopACCombiner ;
	
	@Column(name="ROOF_TOP_AC_COMBINER_STRING")
	private String roofTopACCombinerString ;
	
	@Column(name="TRANSITIONING_PV_WIRE_IN")
	private String transitioningPVWireIn ;
	
	@Column(name="ROOF_TOP_JBOX_DC")
	private String roofTopJboxDC ;
	
	@JoinColumn(name="ROOF_TOP_DC_DISCO")
	@ManyToOne
	private DCCombinerDisconnectEntity roofTopDCDisco ;
	
	@Column(name="ROOF_TOP_DC_DISCO_MODEL") //M.A: 02-11: for the old value
	private String roofTopDCDiscoModel ;
	
	@JoinColumn(name="ROOF_TOP_DC_COMBINER")
	@ManyToOne
	private DCCombinerDisconnectEntity roofTopDCCombiner ;
	
	@Column(name="ROOF_TOP_DC_COMBINER_MODEL")//M.A: 02-11: for the old value
	private String roofTopDCCombinerModel ;
	
	
	
	@Column(name="QTY_INDEPENDANT_AC_DISCO")
	private Integer qtyIndependentACDisco ;
	
	@Column(name="FLASHING")
	private String flashing;
	
	@Column(name="LEASE_PPA_METER")
	private String leasePPAMeter;
	
	@Column(name="PROPOSED_SUB_PANEL")
	private String proposedSubPanel;

	@Column(name="INSTALLING_AC_COMBINER")
	private Boolean installingACCombiner;

	@JoinColumn(name="AC_COMBINER_INSTALLED")
	@ManyToOne
	private ACCombinerSLC aCCombinerInstalled;
	
	@Column(name="AC_COMBINER_INSTALLED_STRING")
	private String aCCombinerInstalledString; //M.A : 24-02-2020
	
	@Column(name="NORTH_TO_SOUTH_FIN")
	private String northToShouthFin;
	
	@Column(name="NORTH_TO_SOUTH_FIN_OTHER")
	private Integer northToShouthFinOther;
	
	@Column(name="HEIGHT_OF_SOUTH")
	private Integer heightOfSouth;
	
	@Column(name="SUB_PANEL_CONDUCTOR_SIZING")
	private String subPanelConductorSizing;
	
	@Column(name="SUB_PANEL_CONDUCTOR_SIZE")
	private String subPanelConductorSize;
	
	@Column(name="SUB_PANEL_CONDUCTOR_SIZE_OTHER")
	private String subPanelConductorSizeOther;
	
	@Column(name="SUB_PANEL_CONDUCTOR_SIZE_NOTE")
	private String subPanelConductorSizeNote;
	
	
	@Column(name = "SUB_PANEL_CONDUCTOR_SIZE_FILES")
	private Boolean subPanelConductorSizeFiles;

	//M.A : CR-NUATN-3140 Update Components Dropdown
	@Column(name="RAIL_RAIKING_MODEL_OBJECT")
	private String railRaikingModelObject;
	
	@Column(name="RAIL_RAIKING_GROUND_MODEL_OBJECT")
	private String railRaikingGroundModelObject;
	
	@Column(name="RAIL_RAIKING_POLE_MODEL_OBJECT")
	private String railRaikingPoleModelObject;
	
	// 19/08/2019 : CI : CR2860 : If checkSiteSurveyOCPDValidity equals true should
	// check if the value mapped from the site survey of "Main (Service Entrance)
	// Panel Main Breaker Rating" , "Sub Panel Breaker (OCPD) at Main Service" and
	// "Sub Panel Main Breaker Rating" are valid, else should choose the highest
	// valid option

	@Column(name = "CHECK_SITE_SURVEY_OCPD_VALIDITY")
	private Boolean checkSiteSurveyOCPDValidity;
	
	//S.B CR-3119-MOD-006 21/04/2020
	@Column(name = "INCLUDE_TRANSFORMER")
	private Boolean includeTransformer;
	
	@JoinColumn(name = "RAIL_RAKING_FOR_PATIO_MOUNTED")
	@ManyToOne
	private RailRacking railRakingforPatioMounted;
	
	@JoinColumn(name = "RAIL_RAKING_FOR_CARPORT")
	@ManyToOne
	private RailRacking railRakingforCarport;
	
	private Integer qtyJunctionBox;
	
//	A.B 08-30-2021 CR-PM-3862
	@Column
	private Boolean subPanelSpecification;

	@Column
	private String subPanelBusRatingCombining;

	@Column
	private Integer subPanelBusRatingCombiningOther;

	@Column
	private String subPanelMainBreakerRatingCombining;

	@Column
	private Integer subPanelMainBreakerRatingCombiningOther;

	@Column
	private String subPanelBreakerAtMainServiceCombining;

	@Column
	private Integer subPanelBreakerAtMainServiceCombiningOther;
	
	@JoinColumn
	@ManyToOne
	private ACDisconnect acDisconnectThree;
	
	@JoinColumn
	@ManyToOne
	private ACDisconnect acDisconnectFour;
}
