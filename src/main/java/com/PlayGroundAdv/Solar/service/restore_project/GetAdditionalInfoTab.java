package com.PlayGroundAdv.Solar.service.restore_project;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
import com.PlayGroundAdv.Solar.model.PermitEngineerEntityResult;

@Service
public class GetAdditionalInfoTab {

	final SharedUtils sharedUtils;

	public GetAdditionalInfoTab(SharedUtils sharedUtils) {
		super();
		this.sharedUtils = sharedUtils;
	}
	
	public PermitAdditionalInfoEntityResult getAdditionalInfo(HSSFSheet sheet) {
		try {
			PermitAdditionalInfoEntityResult entity = new PermitAdditionalInfoEntityResult();
			int rowTotal = sheet.getLastRowNum();
			if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
				entity.setFormatSize(sharedUtils.findStringValue(sheet, "format_size"));
				entity.setBatteryStorage(sharedUtils.findBooleanValue(sheet, "battery_storage"));
				entity.setTiltLegs(sharedUtils.findBooleanValue(sheet, "tilt_legs"));
				entity.setInformationCovered(sharedUtils.findBooleanValue(sheet, "information_covered"));
				entity.setRequiredElectricalStamp(sharedUtils.findBooleanValue(sheet, "required_electrical_stamp"));
				entity.setUploadComments(sharedUtils.findStringValue(sheet, "upload_comments"));
				entity.setBattery(sharedUtils.findStringValue(sheet, "battery"));
				entity.setTiltLegsMod(sharedUtils.findStringValue(sheet, "tilt_legs_mod"));
				entity.setGridTiedOrStandalone(sharedUtils.findStringValue(sheet, "grid_tied_or_standalone"));
				entity.setExistSolarSystem(sharedUtils.findBooleanValue(sheet, "exist_solar_system"));
				entity.setExistpvmodule(sharedUtils.findStringValue(sheet, "existpvmodule"));
				entity.setExistmoduleqty(sharedUtils.findIntValue(sheet, "existmoduleqty"));
				entity.setExistinvertermodel(sharedUtils.findStringValue(sheet, "existinvertermodel"));
				entity.setExistinverterqty(sharedUtils.findIntValue(sheet, "existinverterqty"));
				entity.setExistinvertermodelTwo(sharedUtils.findStringValue(sheet, "existinvertermodel_two"));
				entity.setExistinverterqtyTwo(sharedUtils.findIntValue(sheet, "existinverterqty_two"));
				entity.setExistmicromodel(sharedUtils.findStringValue(sheet, "existmicromodel"));
				entity.setExistmicroqty(sharedUtils.findIntValue(sheet, "existmicroqty"));
				entity.setExistacdisconnect(sharedUtils.findStringValue(sheet, "existacdisconnect"));
				entity.setExistpvmeter(sharedUtils.findStringValue(sheet, "existpvmeter"));
				entity.setAcdpvmorientation(sharedUtils.findStringValue(sheet, "acdpvmorientation"));
				entity.setPointofconnection(sharedUtils.findStringValue(sheet, "pointofconnection"));
				entity.setPocwillbeat(sharedUtils.findStringValue(sheet, "pocwillbeat"));
				entity.setSizebackfed(sharedUtils.findStringValue(sheet, "sizebackfed"));
				entity.setOtherPointConnection(sharedUtils.findStringValue(sheet, "other_point_connection")); 
				entity.setOtherpocwillbeat(sharedUtils.findStringValue(sheet, "otherpocwillbeat"));
				entity.setCombiningpvin(sharedUtils.findStringValue(sheet, "combiningpvin"));
				entity.setExistingInverterTech(sharedUtils.findStringValue(sheet, "existing_inverter_tech"));
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return new PermitAdditionalInfoEntityResult();
		}
	}
	
	
	public PermitEngineerEntityResult getProjectEngineer(HSSFSheet sheet) {
		try {
			PermitEngineerEntityResult entity = new PermitEngineerEntityResult();
			int rowTotal = sheet.getLastRowNum();
			if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
				entity.setApplicableEngineering(sharedUtils.findStringValue(sheet, "applicabl_engineering"));
				entity.setName(sharedUtils.findStringValue(sheet, "name"));
				entity.setEmail(sharedUtils.findStringValue(sheet, "email"));
				entity.setMobile(sharedUtils.findStringValue(sheet, "mobile"));
				entity.setPhone(sharedUtils.findStringValue(sheet, "phone"));
				entity.setLicenceNumber(sharedUtils.findStringValue(sheet, "licence_number"));
				entity.setLicenceType(sharedUtils.findStringValue(sheet, "licence_type"));
				entity.setCity(sharedUtils.findStringValue(sheet, "city"));
				entity.setState(sharedUtils.findStringValue(sheet, "state"));
				entity.setCodePostale(sharedUtils.findStringValue(sheet, "code_postale"));
				entity.setEngineeredBy(sharedUtils.findStringValue(sheet, "engineered_by"));
				entity.setDetermineModification(sharedUtils.findBooleanValue(sheet, "determine_modification"));
				entity.setIsShingles(sharedUtils.findBooleanValue(sheet, "is_shingles"));
				entity.setIndicateLayers(sharedUtils.findStringValue(sheet, "indicate_layers"));
				entity.setMpptTrachers(sharedUtils.findBooleanValue(sheet, "mppt_trachers"));
				entity.setNumberMpptTrachers(sharedUtils.findStringValue(sheet, "number_mppt_tracher"));
				entity.setNumberStringFirstMpptTrachers2(sharedUtils.findStringValue(sheet, "number_string_first_mppt_tracher"));
				entity.setNumberStringSecondMpptTrachers2(sharedUtils.findStringValue(sheet, "number_string_second_mppt_tracher"));
				entity.setNumberModuleStringFirstMpptTrachers2(sharedUtils.findStringValue(sheet, "number_module_string_first_mppt_tracher"));
				entity.setNumberModuleStringSecondMpptTrachers2(sharedUtils.findStringValue(sheet, "number_module_string_second_mppt_tracher"));
				entity.setIsTransformless(sharedUtils.findBooleanValue(sheet, "is_transformless"));
				entity.setNumberInputTransformless2(sharedUtils.findStringValue(sheet, "number_input_transformless"));
				entity.setIsCombiner(sharedUtils.findBooleanValue(sheet, "is_combiner"));
				entity.setNumberInputCombiner2(sharedUtils.findStringValue(sheet, "number_input_combiner"));
				entity.setOverhangArea(sharedUtils.findStringValue(sheet, "overhang_area"));
				entity.setRoofPitch(sharedUtils.findStringValue(sheet, "roof_pitch"));
				entity.setAdressIng(sharedUtils.findStringValue(sheet, "adress_ing"));
				// variable format int
				entity.setNumberStringFirstMpptTrachers(sharedUtils.findStringValue(sheet, "number_string_first_mppt_tracher"));
				entity.setNumberStringSecondMpptTrachers(sharedUtils.findStringValue(sheet, "number_string_second_mppt_tracher"));
				entity.setNumberModuleStringFirstMpptTrachers(sharedUtils.findStringValue(sheet, "number_module_string_first_mppt_tracher"));
				entity.setNumberModuleStringSecondMpptTrachers(sharedUtils.findStringValue(sheet, "number_module_string_second_mppt_tracher"));
				entity.setNumberInputTransformless(sharedUtils.findStringValue(sheet, "number_input_transformless"));
				entity.setNumberInputCombiner(sharedUtils.findStringValue(sheet, "number_input_combiner"));

			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return new PermitEngineerEntityResult();
		}
	}
}
