package com.PlayGroundAdv.Solar.service.restore_project;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;

@Service
public class GetBosTab {
	
	final SharedUtils sharedUtils;

	public GetBosTab(SharedUtils sharedUtils) {
		super();
		this.sharedUtils = sharedUtils;
	}
	
	public PermitProjectSiteInfoEntityTwo getProjectBalanceOfSystem(HSSFSheet sheet) {
		try {
			PermitProjectSiteInfoEntityTwo entity = new PermitProjectSiteInfoEntityTwo();
			int rowTotal = sheet.getLastRowNum();
			if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
				
				entity.setRailRakingModel(sharedUtils.findLongValue(sheet, "rail_racking_model"));
				entity.setTrackingSystemManufacturer(sharedUtils.findStringValue(sheet, "tracking_system_manufacturer"));
				entity.setTrackingSystemModel(sharedUtils.findStringValue(sheet, "tracking_system_model"));
				entity.setInverterModel(sharedUtils.findStringValue(sheet, "invert_model"));
				entity.setRafterTrussSpacing(sharedUtils.findStringValue(sheet, "rafter_truss_spacing"));
				entity.setCrossSectionSize(sharedUtils.findStringValue(sheet, "cross_section_size"));
				entity.setSpanBetweenAttachment(sharedUtils.findStringValue(sheet, "span_between_attachement"));
				entity.setRoofMaterialType(sharedUtils.findLongValue(sheet, "roof_material_type"));
				entity.setRoofMaterialTypeOther(sharedUtils.findStringValue(sheet, "roof_material_type_other"));
				entity.setRankingRoofManufacturer(sharedUtils.findStringValue(sheet, "ranking_roof_manufacturer"));
				entity.setRankingRoofModel(sharedUtils.findStringValue(sheet, "ranking_roof_model"));
				entity.setModuleGrounding(sharedUtils.findStringValue(sheet, "module_grounding"));
				entity.setDisconnectManufacturer(sharedUtils.findStringValue(sheet, "disconnect_manufacturer"));
				entity.setDisconnectModel(sharedUtils.findLongValue(sheet, "disconnect_model"));
				entity.setQuantityRooftop(sharedUtils.findStringValue(sheet, "quantity_rooftop"));
				entity.setSolarLocation(sharedUtils.findStringValue(sheet, "solar_location"));
				entity.setMainPanelUpgrade(sharedUtils.findBooleanValue(sheet, "main_panel_upgrade"));
				entity.setPanelBusRating(sharedUtils.findStringValue(sheet, "panel_bus_rating"));
				entity.setSolarInterconnection(sharedUtils.findStringValue(sheet, "solar_interconnection"));
				entity.setSecondSolarInterconnection(sharedUtils.findStringValue(sheet, "second_solar_interconnection"));
				entity.setUseDisconectSwith(sharedUtils.findStringValue(sheet, "use_disconnect_swith"));
				entity.setUsedByInverterManufacturer(sharedUtils.findBooleanValue(sheet, "used_by_inverter_manufacturer"));
				entity.setUsedRevenue(sharedUtils.findBooleanValue(sheet, "used_revenue"));
				entity.setSubPanelMainBreakerRating(sharedUtils.findStringValue(sheet, "sup_panel_main_breaker_rating"));
				entity.setSubPanelBusRating(sharedUtils.findStringValue(sheet, "sup_panel_bus_rating"));
				entity.setPanelExistingProposed(sharedUtils.findStringValue(sheet, "panel_existing_proposed"));
				entity.setQuantityofCombinerBox(sharedUtils.findStringValue(sheet, "quantity_of_combiner_box"));
				entity.setQuantityofCombinerBoxOther(sharedUtils.findStringValue(sheet, "quantity_of_combiner_box_other"));
				entity.setTrackingSystemManufacturerOther(sharedUtils.findStringValue(sheet, "tracking_systeme_manufacturer_other"));
				entity.setTrackingSystemModelOther(sharedUtils.findStringValue(sheet, "tracking_system_model_other"));
				entity.setRooftopACCombinerModel(sharedUtils.findStringValue(sheet, "roof_top_ac_combiner_model"));
				entity.setTextOtherSize(sharedUtils.findStringValue(sheet, "text_other_size"));
				entity.setACDisconnectSwitchManufacturer(sharedUtils.findStringValue(sheet, "ac_disconnect_switch_manufacturer"));
				entity.setACDisconnectSwitchModel(sharedUtils.findStringValue(sheet, "ac_disconnect_switch_model"));
				entity.setACDisconnectSwitchManufacturerOther(sharedUtils.findStringValue(sheet, "ac_disconnect_switch_manufacturer_other"));
				entity.setACDisconnectSwitchModelOther(sharedUtils.findStringValue(sheet, "ac_disconnect_switch_model_other"));
				entity.setDCDisconnectSwitchManufacturer(sharedUtils.findStringValue(sheet, "dc_disconnect_switch_manufacturer"));
				entity.setDCDisconnectSwitchModel(sharedUtils.findStringValue(sheet, "dc_disconnect_switch_model"));
				entity.setLeasePPAMeterManufacturer(sharedUtils.findStringValue(sheet, "lease_ppa_meter_manufacturer"));
				entity.setLeasePPAMeterModel(sharedUtils.findStringValue(sheet, "lease_ppa_meter_model"));
				//
				entity.setTrackingSystemManufacturerForSecondTracker(sharedUtils.findStringValue(sheet, "tarcking_system_manufacturer_for_second_tracker"));
				entity.setTrackingSystemManufacturerForSecondTrackerOther(sharedUtils.findStringValue(sheet, "tarcking_system_manufacturer_for_second_tracker_other"));
				entity.setTrackingSystemModelForSecondTracker(sharedUtils.findStringValue(sheet, "tarcking_system_model_for_second_tracker"));
				entity.setTrackingSystemModelForSecondTrackerOther(sharedUtils.findStringValue(sheet, "tarcking_system_model_for_second_tracker_other"));
				//
				entity.setRankingRoofManufacturerOther(sharedUtils.findStringValue(sheet, "racking_roof_fmanufacturer_other"));
				entity.setRankingRoofModelOther(sharedUtils.findStringValue(sheet, "rancking_roof_model_other"));
				entity.setModuleGroundingOther(sharedUtils.findStringValue(sheet, "module_grounding_other"));
				entity.setDisconnectManufacturerOther(sharedUtils.findStringValue(sheet, "disconnect_manufacturer_other"));
				entity.setDisconnectModelOther(sharedUtils.findStringValue(sheet, "disconnect_model_other"));
				entity.setRailConnectionModel(sharedUtils.findStringValue(sheet, "rail_connection_model"));
				entity.setAtticJBoxesbeUtilized(sharedUtils.findBooleanValue(sheet, "attic_jboxsbe_utlized"));
				entity.setDescriptionOfBackFeed(sharedUtils.findStringValue(sheet, "description_of_back_feed"));
				entity.setGroundLevelACDisconnectEnclosure(sharedUtils.findStringValue(sheet, "groundlevel_ac_disconnect_enclosure"));
				entity.setPanelBusRatingOther(sharedUtils.findStringValue(sheet, "panel_bus_rating_other"));
				entity.setPanelMainBreakerRating(sharedUtils.findStringValue(sheet, "panel_main_breaker_rating"));
				entity.setPanelMainBreakerRatingOther(sharedUtils.findStringValue(sheet, "panel_main_breaker_rating_other"));
				entity.setSolarInterconnectionOther(sharedUtils.findStringValue(sheet, "solar_interconnection_other"));
				entity.setSecondSolarInterconnectionOther(sharedUtils.findStringValue(sheet, "second_solar_interconnection_other"));
				entity.setCombiningACCircuits(sharedUtils.findStringValue(sheet, "combinig_ac_circuits"));
				entity.setMorInterconnectingBackFeed(sharedUtils.findBooleanValue(sheet, "more_interconnecting_back_feed"));
				entity.setRailRakingModelforGroundMounted(sharedUtils.findLongValue(sheet, "rail_raking_model_for_ground_mounted"));
				entity.setRailRakingforPatioMounted(sharedUtils.findLongValue(sheet, "rail_raking_for_patio_mounted"));
				entity.setSizeAndTypeAtticJBox(sharedUtils.findStringValue(sheet, "size_and_type_attic_jbox"));
				entity.setSizeAndTypeAtticJBoxOther(sharedUtils.findStringValue(sheet, "size_and_type_attic_jbox_other"));
				entity.setIfApplicableSubPanelMainBreakerRating(sharedUtils.findStringValue(sheet, "if_applicable_sub_panel_main_breaker_rating"));
				entity.setProposedSubPanelManufacturer(sharedUtils.findStringValue(sheet, "prposed_sub_panel_manufacturer"));
				entity.setProposedSubPanelModel(sharedUtils.findStringValue(sheet, "prposed_sub_panel_model"));
				entity.setProposedSubPanelModelOther(sharedUtils.findStringValue(sheet, "prposed_sub_panel_model_other"));
				entity.setGroundLevelACCombinerBoxModel(sharedUtils.findLongValue(sheet, "ground_level_ac_combiner_box_model"));
				entity.setGroundLevelACCombinerDisconnectModel(sharedUtils.findStringValue(sheet, "ground_level_ac_combiner_disconnect_model"));
				entity.setGroundLevelACJunctionBoxManufacturer(sharedUtils.findStringValue(sheet, "ground_level_ac_junction_box_manufacturer"));
				entity.setGroundLevelACJunctionBoxModel(sharedUtils.findStringValue(sheet, "ground_level_ac_junction_box_model"));
				entity.setEquipmentRoofMountedACCombinerBox(sharedUtils.findBooleanValue(sheet, "equipement_roof_mounted_combiner_box"));
				entity.setEquipmentRoofMountedACCombinerDisconnect(sharedUtils.findBooleanValue(sheet, "equipement_roof_mounted_ac_combiner_disconnect"));
				entity.setEquipmentRoofMountedJunctionBox(sharedUtils.findBooleanValue(sheet, "equipement_roof_mounted_junction_box"));
				entity.setEquipmentRoofMountedSingleCircuit(sharedUtils.findBooleanValue(sheet, "equipement_roof_mounted_single_circuit"));
				entity.setEquipmentGroundLevelACCombinerBox(sharedUtils.findBooleanValue(sheet, "equipement_ground_level_ac_combiner_box"));
				entity.setEquipmentGroundLevelACCombinerDisconnect(sharedUtils.findBooleanValue(sheet, "equipement_ground_level_ac_combiner_disconnect"));
				entity.setEquipmentGroundLevelACSubPanel(sharedUtils.findBooleanValue(sheet, "equipement_ground_level_ac_sub_panel"));
				entity.setEquipmentGroundLevelACJunctionBox(sharedUtils.findBooleanValue(sheet, "equipement_ground_level_ac_junction_box"));
				entity.setEquipmentCombiningInExistingSubPanel(sharedUtils.findBooleanValue(sheet, "equipement_combining_in_existing_sub_panel"));
				entity.setEquipmentCombiningInProposedSubPanel(sharedUtils.findBooleanValue(sheet, "equipement_combining_in_proposed_sub_panel"));
				entity.setEquipmentCombiningInMainPanel(sharedUtils.findBooleanValue(sheet, "equipement_combining_in_main_panel"));
				entity.setEquipmentisOther(sharedUtils.findBooleanValue(sheet, "equipement_is_other"));
				entity.setEquipmentOther(sharedUtils.findStringValue(sheet, "equipement_other"));
				entity.setRoofMountedACCombinerBoxManufacturer(sharedUtils.findStringValue(sheet, "roof_mounted_ac_combiner_box_manufacturer"));
				entity.setRoofMountedACCombinerBoxManufacturerOther(sharedUtils.findStringValue(sheet, "roof_mounted_ac_combiner_box_manufacturer_other"));
				entity.setRoofMountedACCombinerBoxModel(sharedUtils.findStringValue(sheet, "roof_mounted_ac_combiner_box_model"));
				entity.setRoofMountedACCombinerBoxModelOther(sharedUtils.findStringValue(sheet, "roof_mounted_ac_combiner_box_model_other"));
				entity.setRoofMountedACCombiningDisconnectManufacturer(sharedUtils.findStringValue(sheet, "roof_mounted_ac_combining_disconnect_manufacturer"));
				entity.setRoofMountedACCombiningDisconnectManufacturerOther(sharedUtils.findStringValue(sheet, "roof_mounted_ac_combining_disconnect_manufacturer_other"));
				entity.setRoofMountedACCombiningDisconnectModel(sharedUtils.findStringValue(sheet, "roof_mounted_ac_combining_disconnect_model"));
				entity.setRoofMountedACCombiningDisconnectModelOther(sharedUtils.findStringValue(sheet, "roof_mounted_ac_combining_disconnect_model_other"));
				entity.setRoofMountedACJunctionBoxManufacturer(sharedUtils.findStringValue(sheet, "roof_mounted_ac_juction_box_manufacturer"));
				entity.setRoofMountedACJunctionBoxManufacturerOther(sharedUtils.findStringValue(sheet, "roof_mounted_ac_juction_box_manufacturer_other"));
				entity.setRoofMountedACJunctionBoxModel(sharedUtils.findStringValue(sheet, "roof_mounted_ac_juction_box_model"));
				entity.setRoofMountedACJunctionBoxModelOther(sharedUtils.findStringValue(sheet, "roof_mounted_ac_juction_box_model_other"));
				entity.setRoofMountedSingleCircuitACDisconnectManufacturer(sharedUtils.findStringValue(sheet, "roof_mounted_single_circuit_ac_disconnect_manufacturer"));
				entity.setRoofMountedSingleCircuitACDisconnectManufacturerOther(sharedUtils.findStringValue(sheet, "roof_mounted_single_circuit_ac_disconnect_manufacturer_other"));
				entity.setRoofMountedSingleCircuitACDisconnectModel(sharedUtils.findStringValue(sheet, "roof_mounted_single_circuit_ac_disconnect_model"));
				entity.setRoofMountedSingleCircuitACDisconnectModelOther(sharedUtils.findStringValue(sheet, "roof_mounted_single_circuit_ac_disconnect_model_other"));
				entity.setEquipmenModelOther(sharedUtils.findStringValue(sheet, "equipement_model_other"));
				entity.setEquipmenManufacturerOther(sharedUtils.findStringValue(sheet, "equipement_manufacturer_other"));
				entity.setProposedMainPanelManufacturer(sharedUtils.findStringValue(sheet, "proposed_main_panel_manufacturer"));
				entity.setProposedMainPanelManufacturerOther(sharedUtils.findStringValue(sheet, "proposed_main_panel_manufacturer_other"));
				entity.setProposedMainPanelModel(sharedUtils.findStringValue(sheet, "proposed_main_panel_model"));
				entity.setProposedMainPanelModelOther(sharedUtils.findStringValue(sheet, "proposed_main_panel_model_other"));
				entity.setDeratingthisPanelString(sharedUtils.findStringValue(sheet, "derating_this_panel_string"));
				entity.setGroundLevelACJunctionBoxManufacturerOther(sharedUtils.findStringValue(sheet, "ground_level_ac_junction_box_manufacturer_other"));
				entity.setGroundLevelACJunctionBoxModelOther(sharedUtils.findStringValue(sheet, "ground_level_ac_junction_box_model_other"));
				entity.setSubPanelBreakerOCPD(sharedUtils.findStringValue(sheet, "sub_panel_breaker_ocpd"));
				entity.setMainBreakerLocatedEndBusBar(sharedUtils.findBooleanValue(sheet, "main_braiker_located_end_bus_bar"));
				entity.setInstallationGuidelines(sharedUtils.findStringValue(sheet, "information_covered"));
				entity.setTextOtherRatfter(sharedUtils.findStringValue(sheet, "text_other_rafter"));
				entity.setDisconnectModelTwo(sharedUtils.findLongValue(sheet, "disconnect_model_two"));
				entity.setDisconnectModelThree(sharedUtils.findLongValue(sheet, "disconnect_model_three"));
				entity.setRooftopACCombinerModelTwo(sharedUtils.findStringValue(sheet, "roof_top_ac_combiner_model_two"));
				entity.setTallStructure(sharedUtils.findStringValue(sheet, "tall_other"));
				entity.setOtherTallStructure(sharedUtils.findStringValue(sheet, "other_tall_structure"));
				entity.setMeanHeight(sharedUtils.findIntValue(sheet, "mean_height"));

				entity.setExistingMainPanelManufac(sharedUtils.findStringValue(sheet, "existing_main_panel_manufac"));
				entity.setExistingMainPanelManufacOther(sharedUtils.findStringValue(sheet, "existing_main_panel_manufac_other"));

				entity.setGroundLevelACJunctionBoxManufacturerString(sharedUtils.findStringValue(sheet, "ground_level_ac_junction_box_manufacturer_string"));
				entity.setGroundLevelACJunctionBoxManufacturerStringOther(sharedUtils.findStringValue(sheet, "ground_level_ac_junction_box_manufacturer_string_other"));
				entity.setGroundLevelACJunctionBoxModelString(sharedUtils.findStringValue(sheet, "ground_level_ac_junction_box_model_string"));
				entity.setGroundLevelACJunctionBoxModelStringOther(sharedUtils.findStringValue(sheet, "ground_level_ac_junction_box_model_string_other"));
				entity.setGroundLevelACSubPanelManufacturer(sharedUtils.findStringValue(sheet, "ground_level_ac_sub_panel_manufacturer"));
				entity.setGroundLevelACSubPanelModel(sharedUtils.findStringValue(sheet, "ground_level_ac_sub_panel_model"));
				entity.setGroundLevelACJunctionBoxManufactuereOtherText(sharedUtils.findStringValue(sheet, "ground_level_ac_junction_box_manufacturer_other_text"));
				entity.setGroundLevelACJunctionBoxModelOtherText(sharedUtils.findStringValue(sheet, "ground_level_ac_junction_box_model_other_text"));
				entity.setProposedSubPanelManufacturerOther(sharedUtils.findStringValue(sheet, "proposed_sub_panel_manufacturer_other"));
				entity.setSolarLocationOther(sharedUtils.findStringValue(sheet, "solar_location_other"));
				entity.setLeasePPAMeterModelOther(sharedUtils.findStringValue(sheet, "lease_ppa_meter_model_other"));
				entity.setLeasePPAMeterManufacturerOther(sharedUtils.findStringValue(sheet, "lease_ppa_meter_manufacturer_other"));
				entity.setSubPanelBusRatingOther(sharedUtils.findStringValue(sheet, "sub_panel_bus_rating_other"));
				entity.setSubPanelBreakerOCPDOther(sharedUtils.findStringValue(sheet, "sub_panel_breaker_ocpd_other"));
				entity.setLocation(sharedUtils.findStringValue(sheet, "location"));
				entity.setLocationTwo(sharedUtils.findStringValue(sheet, "locationtwo"));
				entity.setLocationThree(sharedUtils.findStringValue(sheet, "locationthree"));
				entity.setInstallingDCBo(sharedUtils.findBooleanValue(sheet, "installingdcbo"));
				entity.setLocationFive(sharedUtils.findStringValue(sheet, "locationfive"));
				entity.setLocationSix(sharedUtils.findStringValue(sheet, "locationsix"));
				entity.setLocationFour(sharedUtils.findStringValue(sheet, "locationfour"));
				entity.setProposedMainPanMan(sharedUtils.findStringValue(sheet, "proposedmainpanman"));
				entity.setThirdSolarInterconnection(sharedUtils.findStringValue(sheet, "thirdsolarinterconnection"));
				entity.setFourthSolarInterconnection(sharedUtils.findStringValue(sheet, "fourthsolarinterconnection"));
				entity.setFifthSolarInterconnection(sharedUtils.findStringValue(sheet, "fifthsolarinterconnection"));
				entity.setThirdSolarInterconnectionOther(sharedUtils.findStringValue(sheet, "thirdsolarinterconnectionother"));
				entity.setFourthSolarInterconnectionOther(sharedUtils.findStringValue(sheet, "fourthsolarinterconnectionother"));
				entity.setFifthSolarInterconnectionOther(sharedUtils.findStringValue(sheet, "fifthsolarinterconnectionother"));
				entity.setThepontOfTheC(sharedUtils.findStringValue(sheet, "thepontofthec"));
				entity.setConnectionPoint(sharedUtils.findStringValue(sheet, "connectionpoint"));
				entity.setThepontOfTheCOther(sharedUtils.findStringValue(sheet, "thepontofthecother"));
				entity.setPanelLocation(sharedUtils.findStringValue(sheet, "panel_location"));
				entity.setDisconnectLocation(sharedUtils.findStringValue(sheet, "disconnect_location"));
				entity.setUploadComments(sharedUtils.findStringValue(sheet, "upload_comments"));
				entity.setRoofTopACCombinerDisconnect(sharedUtils.findStringValue(sheet, "roof_top_ac_combiner_disconnect"));
				entity.setInstallRoofTopACDiscoCombiner(sharedUtils.findBooleanValue(sheet, "install_roof_topacdisco_combiner"));
				entity.setMsphasNoBranchCircuitBreakers(sharedUtils.findBooleanValue(sheet, "msphas_no_branch_circuit_breakers"));

				entity.setProposedACCombMainBreaker(sharedUtils.findBooleanValue(sheet, "proposed_accomb_main_breaker"));
				entity.setProposedACCombMainBreakerRating(sharedUtils.findStringValue(sheet, "proposed_accomb_main_breaker_rating"));
				entity.setProposedACCombMainBreakerRatingOther(sharedUtils.findStringValue(sheet, "proposed_accomb_main_breaker_rating_other"));
				entity.setMicroInverterCabling(sharedUtils.findStringValue(sheet, "micro_inverter_cabling"));
				entity.setRoofTopJbox(sharedUtils.findStringValue(sheet, "roof_top_jbox"));
				entity.setRoofTopACDisco(sharedUtils.findStringValue(sheet, "roof_top_ac_disco"));
				entity.setRoofTopACCombiner(sharedUtils.findLongValue(sheet, "roof_top_ac_combiner"));
				entity.setTransitioningPVWireIn(sharedUtils.findStringValue(sheet, "transitioning_pv_wire_in"));
				entity.setRoofTopJboxDC(sharedUtils.findStringValue(sheet, "roof_top_jbox_dc"));
				entity.setRoofTopDCDisco(sharedUtils.findLongValue(sheet, "roof_top_dc_disco"));
				entity.setRoofTopDCCombiner(sharedUtils.findLongValue(sheet, "roof_top_dc_combiner"));
				entity.setQtyIndependentACDisco(sharedUtils.findIntValue(sheet, "qty_independant_ac_disco"));
				entity.setFlashing(sharedUtils.findStringValue(sheet, "flashing"));
				entity.setLeasePPAMeter(sharedUtils.findStringValue(sheet, "lease_ppa_meter"));
				entity.setProposedSubPanel(sharedUtils.findStringValue(sheet, "proposed_sub_panel"));
				entity.setInstallingACCombiner(sharedUtils.findBooleanValue(sheet, "installing_ac_combiner"));
				entity.setaCCombinerInstalled(sharedUtils.findLongValue(sheet, "ac_combiner_installed"));
				entity.setNorthToShouthFin(sharedUtils.findStringValue(sheet, "north_to_south_fin"));
				entity.setNorthToShouthFinOther(sharedUtils.findIntValue(sheet, "north_to_south_fin_other"));
				entity.setHeightOfSouth(sharedUtils.findIntValue(sheet, "height_of_south"));
				entity.setSubPanelConductorSizing(sharedUtils.findStringValue(sheet, "sub_panel_conductor_sizing"));
				entity.setSubPanelConductorSize(sharedUtils.findStringValue(sheet, "sub_panel_conductor_size"));
				entity.setSubPanelConductorSizeOther(sharedUtils.findStringValue(sheet, "sub_panel_conductor_size_other"));
				entity.setSubPanelConductorSizeNote(sharedUtils.findStringValue(sheet, "sub_panel_conductor_size_note"));
				entity.setSubPanelConductorSizeFiles(sharedUtils.findBooleanValue(sheet, "sub_panel_conductor_size_files"));

				entity.setCheckSiteSurveyOCPDValidity(sharedUtils.findBooleanValue(sheet, "check_site_survey_ocpd_validity"));
				entity.setIncludeTransformer(sharedUtils.findBooleanValue(sheet, "include_transformer"));
				entity.setRailRakingforCarport(sharedUtils.findLongValue(sheet, "rail_raking_for_carport"));
				entity.setQtyJunctionBox(sharedUtils.findIntValue(sheet, "qty_junction_box"));

				entity.setSubPanelSpecification(sharedUtils.findBooleanValue(sheet, "sub_panel_specification"));
				entity.setSubPanelBusRatingCombining(sharedUtils.findStringValue(sheet, "sub_panel_bus_rating_combining"));
				entity.setSubPanelBusRatingCombiningOther(sharedUtils.findIntValue(sheet, "sub_panel_bus_rating_combining_other"));
				entity.setSubPanelMainBreakerRatingCombining(sharedUtils.findStringValue(sheet, "sub_panel_main_breaker_rating_combining"));
				entity.setSubPanelMainBreakerRatingCombiningOther(sharedUtils.findIntValue(sheet, "sub_panel_main_breaker_rating_combining_other"));
				entity.setSubPanelBreakerAtMainServiceCombining(sharedUtils.findStringValue(sheet, "sub_panel_breaker_at_main_service_combining"));
				entity.setSubPanelBreakerAtMainServiceCombiningOther(sharedUtils.findIntValue(sheet, "sub_panel_breaker_at_main_service_combining_other"));
				entity.setAcDisconnectThree(sharedUtils.findLongValue(sheet, "ac_disconnect_three_id"));
				entity.setAcDisconnectFour(sharedUtils.findLongValue(sheet, "ac_disconnect_four_id"));
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return new PermitProjectSiteInfoEntityTwo();
		}
	}
	
}
