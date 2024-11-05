package com.PlayGroundAdv.Solar.service.restore_project;

import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.PermitEnergyBatterySystemDto;
import com.PlayGroundAdv.Solar.model.ProjectBatteryDto;

@Service
public class GetArraysTab {

	final SharedUtils sharedUtils;

	public GetArraysTab(SharedUtils sharedUtils) {
		super();
		this.sharedUtils = sharedUtils;
	}

	public PermitArrayEntityResultSecond getArraysEntity(HSSFSheet sheet) {
		try {
			PermitArrayEntityResultSecond entity = new PermitArrayEntityResultSecond();
			int rowTotal = sheet.getLastRowNum();
			if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
				entity.setSystemType(sharedUtils.findStringValue(sheet, "system_type"));
				entity.setDeviceToIncorporate(sharedUtils.findStringValue(sheet, "device_to_incorporate"));
				entity.setPvModuleModEl(sharedUtils.findLongValue(sheet, "pv_module"));
				entity.setInverterModel(sharedUtils.findLongValue(sheet, "first_inverter"));
				entity.setSecondInverterModel(sharedUtils.findLongValue(sheet, "second_inverter"));
				entity.setRoofMounted(sharedUtils.findBooleanValue(sheet, "roof_mounted"));
				entity.setGroundMounted(sharedUtils.findBooleanValue(sheet, "ground_mounted"));
				entity.setCarportMounted(sharedUtils.findBooleanValue(sheet, "carport_mounted"));
				entity.setOtherMounted(sharedUtils.findBooleanValue(sheet, "other_mounted"));
				entity.setTextOther(sharedUtils.findStringValue(sheet, "text_other"));
				entity.setFrontAndBack(sharedUtils.findBooleanValue(sheet, "frontandback"));
				entity.setCantelever(sharedUtils.findBooleanValue(sheet, "catelever"));
				entity.setAttachedToExtWal(sharedUtils.findBooleanValue(sheet, "attached_to_extwal"));
				entity.setAttachedToFascia(sharedUtils.findBooleanValue(sheet, "attached_to_fascia"));
				entity.setAttachedToSkylifts(sharedUtils.findBooleanValue(sheet, "attached_to_skylifts"));
				entity.setFreeStanding(sharedUtils.findBooleanValue(sheet, "free_standing"));

				entity.setQteOfBattery(sharedUtils.findStringValue(sheet, "qte_of_battery"));
				entity.setBatteryManufacturerTrojan(sharedUtils.findBooleanValue(sheet, "battery_manufacturer_torjan"));
				entity.setBatteryManufacturerMMK(sharedUtils.findBooleanValue(sheet, "battery_manufacturer_mmk"));
				entity.setBatteryManufacturerUPG(sharedUtils.findBooleanValue(sheet, "battery_manufacturer_upg"));
				entity.setBatteryManufacturerRolls(sharedUtils.findBooleanValue(sheet, "battery_manufacturer_rolls"));
				entity.setBatteryManufacturerCrown(sharedUtils.findBooleanValue(sheet, "battery_manufacturer_crown"));
				entity.setBatteryManufacturerTesla(sharedUtils.findBooleanValue(sheet, "battery_manufacturer_tesla"));
				entity.setBatteryManufacturerOutback(
						sharedUtils.findBooleanValue(sheet, "battery_manufacturer_outback"));
				entity.setBatteryManufacturerFullriver(
						sharedUtils.findBooleanValue(sheet, "battery_manufacturer_fullriver"));
				entity.setBatteryManufacturerConcord(
						sharedUtils.findBooleanValue(sheet, "battery_manufacturer_concord"));
				entity.setBatteryManufacturerOther(sharedUtils.findBooleanValue(sheet, "battery_manufacturer_other"));
				entity.setTextBatteryOther(sharedUtils.findStringValue(sheet, "text_battery_other"));

				entity.setSystemOptimizerModel(sharedUtils.findLongValue(sheet, "system_optimizer_model"));
				
				entity.setStringOneStr(sharedUtils.findStringValue(sheet, "string_one"));
				entity.setStringTwoStr(sharedUtils.findStringValue(sheet, "string_two"));
				entity.setStringThreeStr(sharedUtils.findStringValue(sheet, "string_three"));
				entity.setStringFourStr(sharedUtils.findStringValue(sheet, "string_four"));
				entity.setStringFiveStr(sharedUtils.findStringValue(sheet, "string_five"));
				
				entity.setStringOne(sharedUtils.findIntValue(sheet, "string_one"));
				entity.setStringTwo(sharedUtils.findIntValue(sheet, "string_two"));
				entity.setStringThree(sharedUtils.findIntValue(sheet, "string_three"));
				entity.setStringFour(sharedUtils.findIntValue(sheet, "string_four"));
				entity.setStringFive(sharedUtils.findIntValue(sheet, "string_five"));
				entity.setStringSix(sharedUtils.findIntValue(sheet, "string_six"));
				entity.setStringSeven(sharedUtils.findIntValue(sheet, "string_seven"));
				entity.setStringEight(sharedUtils.findIntValue(sheet, "string_eight"));
				entity.setStringNine(sharedUtils.findIntValue(sheet, "string_nine"));
				entity.setStringTen(sharedUtils.findIntValue(sheet, "strin_gten"));
				entity.setStringEleven(sharedUtils.findIntValue(sheet, "string_eleven"));
				entity.setStringTwelve(sharedUtils.findIntValue(sheet, "string_twelve"));

				entity.setSecondStringOneStr(sharedUtils.findStringValue(sheet, "second_string_one"));
				entity.setSecondStringTwoStr(sharedUtils.findStringValue(sheet, "second_string_two"));
				entity.setSecondStringThreeStr(sharedUtils.findStringValue(sheet, "second_string_three"));
				entity.setSecondStringFourStr(sharedUtils.findStringValue(sheet, "second_string_four"));
				entity.setSecondStringFiveStr(sharedUtils.findStringValue(sheet, "second_string_five"));
				
				entity.setSecondStringOne(sharedUtils.findIntValue(sheet, "second_string_one"));
				entity.setSecondStringTwo(sharedUtils.findIntValue(sheet, "second_string_two"));
				entity.setSecondStringThree(sharedUtils.findIntValue(sheet, "second_string_three"));
				entity.setSecondStringFour(sharedUtils.findIntValue(sheet, "second_string_four"));
				entity.setSecondStringFive(sharedUtils.findIntValue(sheet, "second_string_five"));
				entity.setSecondStringSix(sharedUtils.findIntValue(sheet, "second_strin_gsix"));
				entity.setSecondStringSeven(sharedUtils.findIntValue(sheet, "second_string_seven"));
				entity.setSecondStringEight(sharedUtils.findIntValue(sheet, "second_string_eight"));
				entity.setSecondStringNine(sharedUtils.findIntValue(sheet, "second_string_nine"));
				entity.setSecondStringTen(sharedUtils.findIntValue(sheet, "second_string_ten"));
				entity.setSecondStringEleven(sharedUtils.findIntValue(sheet, "second_string_eleven"));
				entity.setSecondStringTwelve(sharedUtils.findIntValue(sheet, "second_string_twelve"));

				entity.setThirdInverterModel(sharedUtils.findLongValue(sheet, "third_inverter"));
				entity.setThirdStringOne(sharedUtils.findIntValue(sheet, "third_string_one"));
				entity.setThirdStringTwo(sharedUtils.findIntValue(sheet, "ithird_string_two"));
				entity.setThirdStringThree(sharedUtils.findIntValue(sheet, "third_string_three"));
				entity.setThirdStringFour(sharedUtils.findIntValue(sheet, "third_string_four"));
				entity.setThirdStringFive(sharedUtils.findIntValue(sheet, "third_string_five"));
				entity.setThirdStringSix(sharedUtils.findIntValue(sheet, "third_string_six"));
				entity.setThirdStringSeven(sharedUtils.findIntValue(sheet, "third_string_seven"));
				entity.setThirdStringEight(sharedUtils.findIntValue(sheet, "third_string_eight"));
				entity.setThirdStringNine(sharedUtils.findIntValue(sheet, "third_string_nine"));
				entity.setThirdStringTen(sharedUtils.findIntValue(sheet, "third_string_ten"));
				entity.setThirdStringEleven(sharedUtils.findIntValue(sheet, "third_string_eleven"));
				entity.setThirdStringTwelve(sharedUtils.findIntValue(sheet, "third_string_twelve"));

				entity.setFourthInverterModel(sharedUtils.findLongValue(sheet, "fourth_inverter"));
				entity.setFourthStringOne(sharedUtils.findIntValue(sheet, "fourth_string_one"));
				entity.setFourthStringTwo(sharedUtils.findIntValue(sheet, "fourth_string_two"));
				entity.setFourthStringThree(sharedUtils.findIntValue(sheet, "fourth_string_three"));
				entity.setFourthStringFour(sharedUtils.findIntValue(sheet, "fourth_string_four"));
				entity.setFourthStringFive(sharedUtils.findIntValue(sheet, "fourth_string_five"));
				entity.setFourthStringSix(sharedUtils.findIntValue(sheet, "fourth_string_six"));
				entity.setFourthStringSeven(sharedUtils.findIntValue(sheet, "fourth_string_seven"));
				entity.setFourthStringEight(sharedUtils.findIntValue(sheet, "fourth_string_eight"));
				entity.setFourthStringNine(sharedUtils.findIntValue(sheet, "fourth_string_nine"));
				entity.setFourthStringTen(sharedUtils.findIntValue(sheet, "fourth_string_ten"));
				entity.setFourthStringEleven(sharedUtils.findIntValue(sheet, "fourth_string_eleven"));
				entity.setFourthStringTwelve(sharedUtils.findIntValue(sheet, "fourth_string_twelve"));

				entity.setFifthInverterModel(sharedUtils.findLongValue(sheet, "fifth_inverter"));
				entity.setFifthStringOne(sharedUtils.findIntValue(sheet, "fifth_string_one"));
				entity.setFifthStringTwo(sharedUtils.findIntValue(sheet, "fifth_string_two"));
				entity.setFifthStringThree(sharedUtils.findIntValue(sheet, "fifth_string_three"));
				entity.setFifthStringFour(sharedUtils.findIntValue(sheet, "fifth_string_four"));
				entity.setFifthStringFive(sharedUtils.findIntValue(sheet, "fifth_string_five"));
				entity.setFifthStringSix(sharedUtils.findIntValue(sheet, "fifth_string_six"));
				entity.setFifthStringSeven(sharedUtils.findIntValue(sheet, "fifth_string_seven"));
				entity.setFifthStringEight(sharedUtils.findIntValue(sheet, "fifth_string_eight"));
				entity.setFifthStringNine(sharedUtils.findIntValue(sheet, "fifth_string_nine"));
				entity.setFifthStringTen(sharedUtils.findIntValue(sheet, "fifth_string_ten"));
				entity.setFifthStringEleven(sharedUtils.findIntValue(sheet, "fifth_string_eleven"));
				entity.setFifthStringTwelve(sharedUtils.findIntValue(sheet, "fifth_string_twelve"));

				entity.setNumberModulesACCircuitOne(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_one"));
				entity.setNumberModulesACCircuitTwo(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_two"));
				entity.setNumberModulesACCircuitThree(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_three"));
				entity.setNumberModulesACCircuitFour(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_four"));
				entity.setNumberModulesACCircuitFive(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_five"));
				entity.setNumberModulesACCircuitSix(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_six"));
				entity.setNumberModulesACCircuitSeven(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_seven"));
				entity.setNumberModulesACCircuitEight(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_eight"));
				entity.setNumberModulesACCircuitNine(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_nine"));
				entity.setNumberModulesACCircuitTen(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_ten"));
				entity.setNumberModulesACCircuitEleven(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_eleven"));
				entity.setNumberModulesACCircuitTweleve(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_tweleve"));

				entity.setOcpdOne(sharedUtils.findStringValue(sheet, "ocpd_one"));
				entity.setOcpdTwo(sharedUtils.findStringValue(sheet, "ocpd_two"));
				entity.setOcpdThree(sharedUtils.findStringValue(sheet, "ocpd_three"));
				entity.setOcpdFour(sharedUtils.findStringValue(sheet, "ocpd_four"));
				entity.setOcpdFive(sharedUtils.findStringValue(sheet, "ocpd_five"));
				entity.setOcpdSix(sharedUtils.findStringValue(sheet, "ocpd_six"));
				entity.setOcpdSeven(sharedUtils.findStringValue(sheet, "ocpd_seven"));
				entity.setOcpdEight(sharedUtils.findStringValue(sheet, "ocpd_eight"));
				entity.setOcpdNine(sharedUtils.findStringValue(sheet, "ocpd_nine"));
				entity.setOcpdTen(sharedUtils.findStringValue(sheet, "ocpd_ten"));
				entity.setOcpdEleven(sharedUtils.findStringValue(sheet, "ocpd_eleven"));
				entity.setOcpdTwelve(sharedUtils.findStringValue(sheet, "ocpd_twelve"));
				
				entity.setUploadCommentsLayout(sharedUtils.findStringValue(sheet, "upload_comments_layout"));
				entity.setUploadCommentsAddInfo(sharedUtils.findStringValue(sheet, "upload_comments_add_info"));
				
				entity.setInverterLocation(sharedUtils.findStringValue(sheet, "inverter_location"));
				entity.setInverterLocationOther(sharedUtils.findStringValue(sheet, "inverter_location_other"));
				entity.setInverterSameLocation(sharedUtils.findStringValue(sheet, "inverter_same_location"));
				
				entity.setNumberModulesACCircuitThirteen(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_thirteen"));
				entity.setNumberModulesACCircuitFourteen(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_fourteen"));
				entity.setNumberModulesACCircuitFifteen(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_fifteen"));
				entity.setNumberModulesACCircuitSixteen(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_sixteen"));
				entity.setNumberModulesACCircuitSeventeen(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_seventeen"));
				entity.setNumberModulesACCircuitEightteen(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_eightteen"));
				entity.setNumberModulesACCircuitNineteen(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_nineteen"));
				entity.setNumberModulesACCircuitTwenty(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_twenty"));
				entity.setNumberModulesACCircuitTwentyOne(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_twentyone"));
				entity.setNumberModulesACCircuitTwentyTwo(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_twentytwo"));
				entity.setNumberModulesACCircuitTwentyThree(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_twentythree"));
				entity.setNumberModulesACCircuitTwentyFour(sharedUtils.findStringValue(sheet, "number_modules_ac_circuit_twentyfour"));
				
				entity.setOcpdThirteen(sharedUtils.findStringValue(sheet, "ocpd_thirteen"));
				entity.setOcpdFourteen(sharedUtils.findStringValue(sheet, "ocpd_fourteen"));
				entity.setOcpdFifteen(sharedUtils.findStringValue(sheet, "ocpd_fifteen"));
				entity.setOcpdSixteen(sharedUtils.findStringValue(sheet, "ocpd_sixteen"));
				entity.setOcpdSeventeen(sharedUtils.findStringValue(sheet, "ocpd_seventeen"));
				entity.setOcpdEightteen(sharedUtils.findStringValue(sheet, "ocpd_eighteen"));
				entity.setOcpdNineteen(sharedUtils.findStringValue(sheet, "ocpd_nineteen"));
				entity.setOcpdTwenty(sharedUtils.findStringValue(sheet, "ocpd_twenty"));
				entity.setOcpdTwentyOne(sharedUtils.findStringValue(sheet, "ocpd_twentyone"));
				entity.setOcpdTwentyTwo(sharedUtils.findStringValue(sheet, "ocpd_twentytwo"));
				entity.setOcpdTwentyThree(sharedUtils.findStringValue(sheet, "ocpd_twentythree"));
				entity.setOcpdTwentyFour(sharedUtils.findStringValue(sheet, "ocpd_twentyfour"));

				entity.setMicroInverter(sharedUtils.findLongValue(sheet, "micro_inverter_entity"));
				entity.setRoofOrOpenFrame(sharedUtils.findStringValue(sheet, "roof_or_open_frame"));
				entity.setCircuitUnderGround(sharedUtils.findBooleanValue(sheet, "circuit_under_ground"));
				entity.setInverterInstalledOnRoof(sharedUtils.findBooleanValue(sheet, "inverter_installed_on_roof"));
				entity.setEnteranyTransformer(sharedUtils.findStringValue(sheet, "enter_any_transformer"));
				entity.setPatioMounted(sharedUtils.findBooleanValue(sheet, "patio_mounted"));
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return new PermitArrayEntityResultSecond();
		}
	}
	
	public PermitEnergyBatterySystemDto getBatterysystem(HSSFSheet sheet,HSSFSheet batterySheet) {
		try {
			PermitEnergyBatterySystemDto entity = new PermitEnergyBatterySystemDto();
			int rowTotal = sheet.getLastRowNum();
			if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
				entity.setId(sharedUtils.findLongValue(sheet, "id"));
				entity.setIdAts(sharedUtils.findLongValue(sheet, "id_ats"));
				entity.setIdSecondAts(sharedUtils.findLongValue(sheet, "id_second_ats"));
				entity.setIdGenerator(sharedUtils.findLongValue(sheet, "id_generator"));
				entity.setIdDcDisconnect(sharedUtils.findLongValue(sheet, "id_dc_disconnect"));
				entity.setIdAcDisconnect(sharedUtils.findLongValue(sheet, "id_ac_disconnect"));
				entity.setIdSecondAcDisconnect(sharedUtils.findLongValue(sheet, "id_second_ac_disconnect"));
				entity.setTypeGridTied(sharedUtils.findStringValue(sheet, "type_grid_tied"));
				entity.setAtsIncluded(sharedUtils.findBooleanValue(sheet, "ats_included"));
				entity.setDcDisconnectIncluded(sharedUtils.findBooleanValue(sheet, "dc_disconnect_included"));
				entity.setAcDisconnectIncluded(sharedUtils.findBooleanValue(sheet, "ac_disconnect_included"));
				entity.setGeneratorIncluded(sharedUtils.findBooleanValue(sheet, "generator_included"));
				entity.setRsdConnected(sharedUtils.findBooleanValue(sheet, "rsd_connected"));
				entity.setGeneratorStatus(sharedUtils.findStringValue(sheet, "generator_status"));
				entity.setFuelType(sharedUtils.findStringValue(sheet, "fuel_type"));
				entity.setFuelDistributionPipeType(sharedUtils.findStringValue(sheet, "fuel_distribution_pipe_type"));
				entity.setFuelDistributionPipeTypeOther(sharedUtils.findStringValue(sheet, "fuel_distribution_pipe_type_other"));
				entity.setPipeSize(sharedUtils.findStringValue(sheet, "pipe_size"));
				entity.setPipeSizeOther(sharedUtils.findStringValue(sheet, "pipe_size_other"));
				entity.setQtyAts(sharedUtils.findIntValue(sheet, "qty_ats"));
				entity.setQtySecondAts(sharedUtils.findIntValue(sheet, "qty_second_ats"));
				entity.setQtyAcd(sharedUtils.findIntValue(sheet, "qty_acd"));
				entity.setQtySecondAcd(sharedUtils.findIntValue(sheet, "qty_second_acd"));
				entity.setBatteries(new ArrayList<>());
				entity.setEssSpecificationDetails(new ArrayList<>());//A.B Not Done

				getBatteryList(batterySheet, entity);
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return new PermitEnergyBatterySystemDto();
		}
	}
	
	private void getBatteryList(HSSFSheet batterySheet, PermitEnergyBatterySystemDto entity){
		try {
			if ((batterySheet.getLastRowNum() > 0) || (batterySheet.getPhysicalNumberOfRows() > 0)) {
				int c = 2;
				while (batterySheet.getRow(1).getCell(c) != null) {
					String b = batterySheet.getRow(1).getCell(c).getStringCellValue();
					String q = batterySheet.getRow(2).getCell(c).getStringCellValue();
					Long battery = b != null && !b.equals("") ? Long.valueOf(b) : null;
					Integer quantity = q != null && !q.equals("") ? Integer.valueOf(q) : null;
					if(battery != null)
						entity.getBatteries().add(new ProjectBatteryDto(battery, quantity));
					c = c+1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
