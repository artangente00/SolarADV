package com.PlayGroundAdv.Solar.service.restore_project;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.model.PermitHomeSiteEntityResult;

@Service
public class GetHomeSiteInfo {

	final SharedUtils sharedUtils;

	public GetHomeSiteInfo(SharedUtils sharedUtils) {
		super();
		this.sharedUtils = sharedUtils;
	}
	
	public PermitEntity getPermitEntity(HSSFSheet sheet, PermitEntity entity) {
		try {
			int rowTotal = sheet.getLastRowNum();
			if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
				entity.setHomeOwnName(sharedUtils.findStringValue(sheet, "home_own_name"));
				entity.setHomeOwnLastName(sharedUtils.findStringValue(sheet, "homeowner_last_name"));
				entity.setProjectName(sharedUtils.findStringValue(sheet, "project_name"));
				entity.setInsertRoofNote(sharedUtils.findStringValue(sheet, "insert_roof_note"));
				entity.setExistModule(sharedUtils.findBooleanValue(sheet, "exist_module"));
				entity.setExistOptimizer(sharedUtils.findBooleanValue(sheet, "exist_optimizer"));
				entity.setExistdcJunctionBox(sharedUtils.findBooleanValue(sheet, "exist_dcjunctionbox"));
				entity.setExistdcDcCombiner(sharedUtils.findBooleanValue(sheet, "exist_dccombiner"));
				entity.setExistdcDcdisconnect(sharedUtils.findBooleanValue(sheet, "exist_dcdisconnect"));
				entity.setExistInverter(sharedUtils.findBooleanValue(sheet, "exist_inverter"));
				entity.setExistAcJunctionBox(sharedUtils.findBooleanValue(sheet, "exist_acjunctionbox"));
				entity.setExistAcCombiner(sharedUtils.findBooleanValue(sheet, "exist_accombiner"));
				entity.setExistAcDisconnect(sharedUtils.findBooleanValue(sheet, "exist_acdisconnect"));
				entity.setExistProductionMeter(sharedUtils.findBooleanValue(sheet, "exist_productionmeter"));
				entity.setExistSubPanel(sharedUtils.findBooleanValue(sheet, "exist_subpanel"));
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return new PermitEntity();
		}
	}
	
	public PermitHomeSiteEntityResult getPermitHomeOwner(HSSFSheet sheet) {
		try {
			PermitHomeSiteEntityResult entity = new PermitHomeSiteEntityResult();
			int rowTotal = sheet.getLastRowNum();
			if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
				entity.setUtilityCompanyName(sharedUtils.findStringValue(sheet, "utility_company_name"));
				entity.setFormattedAddress(sharedUtils.findStringValue(sheet, "formatted_address"));
				entity.setSiteAddress(sharedUtils.findStringValue(sheet, "site_address"));
				entity.setAddressLine2(sharedUtils.findStringValue(sheet, "address_line_2"));
				entity.setCity(sharedUtils.findStringValue(sheet, "city"));
				entity.setState(sharedUtils.findStringValue(sheet, "state"));
				entity.setPostalCode(sharedUtils.findStringValue(sheet, "postal_code"));
				entity.setSecondaryAddress(sharedUtils.findStringValue(sheet, "secondary_address"));
				entity.setSecondaryAddressLine2(sharedUtils.findStringValue(sheet, "secondary_address_line_2"));
				entity.setSecondaryCity(sharedUtils.findStringValue(sheet, "secondary_address_line_2"));
				entity.setSecondaryState(sharedUtils.findStringValue(sheet, "secondary_state"));
				entity.setSecondaryPostalCode(sharedUtils.findStringValue(sheet, "secondary_state"));
				entity.setHomePhone(sharedUtils.findStringValue(sheet, "home_phone"));
				entity.setCellPhone(sharedUtils.findStringValue(sheet, "cell_phone"));
				entity.setOtherPhone(sharedUtils.findStringValue(sheet, "other_phone"));
				entity.setEmailPhone(sharedUtils.findStringValue(sheet, "email_phone"));
				entity.setPropertyAPN(sharedUtils.findStringValue(sheet, "property_apn"));
				entity.setFootageStructure(sharedUtils.findStringValue(sheet, "footage_structure"));
				entity.setResidenceBindingCategory(sharedUtils.findStringValue(sheet, "residence_binding_category"));
				entity.setConstructionType(sharedUtils.findStringValue(sheet, "construction_type"));
				entity.setRoofRafter(sharedUtils.findStringValue(sheet, "roof_rafter"));
				entity.setBuildingRisk(sharedUtils.findStringValue(sheet, "building_risk"));
				entity.setBuildingOccupancy(sharedUtils.findStringValue(sheet, "building_occupancy"));
				entity.setHomeOwnName(sharedUtils.findStringValue(sheet, ""));
				entity.setTextOtherConst(sharedUtils.findStringValue(sheet, "text_other_const"));
				entity.setTextOtherExpo(sharedUtils.findStringValue(sheet, "text_other_expo"));
				entity.setTextOtherRoof(sharedUtils.findStringValue(sheet, ""));
				entity.setTextOtherBuildOccup(sharedUtils.findStringValue(sheet, "text_other_build_occup"));
				entity.setTextOtherBuild(sharedUtils.findStringValue(sheet, "text_other_buil"));
				entity.setServiceVoltage(sharedUtils.findStringValue(sheet, "service_voltage"));
				entity.setServiceVoltageOther(sharedUtils.findStringValue(sheet, "service_voltage_other"));
				entity.setRidgeBeamDepthAtArrays(sharedUtils.findStringValue(sheet, "ridge_beam_depth_at_arrays"));
				entity.setMaxHorizontalSpanAtArrays(sharedUtils.findStringValue(sheet, "max_horizontal_span_at_arrays"));
				entity.setMaxHorizontalSpanAtArraysHS(sharedUtils.findStringValue(sheet, "max_horizontal_span_at_arrays_hs"));
				entity.setMaxHorizontalSpanAtArraysInches(sharedUtils.findStringValue(sheet, "max_horizontal_span_at_arrays_inches"));
				entity.setMaxHorizontalSpanAtArraysHSInches(sharedUtils.findStringValue(sheet, "max_horizontal_span_at_arrays_hs_inches"));
				entity.setBuildingRiskOther(sharedUtils.findStringValue(sheet, "building_risk_other"));
				entity.setStanchionMaxSpacing(sharedUtils.findStringValue(sheet, "stanchion_max_spacing"));
				entity.setStanchionMaxSpacingOther(sharedUtils.findStringValue(sheet, "stanchion_max_spacing_other"));
				entity.setRidgeBeamDepthAtArraysOther(sharedUtils.findStringValue(sheet, "ridge_beam_depth_at_arrays_other"));
				entity.setUtilityCompanyNameOther(sharedUtils.findStringValue(sheet, "utility_company_name_other"));
				entity.setCityOther(sharedUtils.findStringValue(sheet, "cityother"));
				entity.setProjectJurisdiction(sharedUtils.findStringValue(sheet, "projectjurisother"));
				entity.setProjectJurisOther(sharedUtils.findStringValue(sheet, "projectjurisdiction"));
				entity.setSecondaryCityOther(sharedUtils.findStringValue(sheet, "secondarycityother"));
				entity.setSecroofRafterOther(sharedUtils.findStringValue(sheet, "roof_rafter_other"));
				entity.setRoofRafterOther(sharedUtils.findStringValue(sheet, "roof_raf_other"));
				entity.setMeterNumber(sharedUtils.findStringValue(sheet, "meter_number"));
				entity.setSameMailing(sharedUtils.findBooleanValue(sheet, "same_mailing"));
				entity.setIfServiceVoltage(sharedUtils.findBooleanValue(sheet, "if_service_voltage"));
				entity.setLatitude(sharedUtils.findFloatValue(sheet, "latitude"));
				entity.setLongitude(sharedUtils.findFloatValue(sheet, "longitude"));
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return new PermitHomeSiteEntityResult();
		}
	}
}
