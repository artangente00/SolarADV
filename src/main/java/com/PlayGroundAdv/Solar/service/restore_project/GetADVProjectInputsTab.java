package com.PlayGroundAdv.Solar.service.restore_project;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import com.PlayGroundAdv.Solar.model.PermtiWeatherEntityResult;

@Service
public class GetADVProjectInputsTab {

	final SharedUtils sharedUtils;

	public GetADVProjectInputsTab(SharedUtils sharedUtils) {
		super();
		this.sharedUtils = sharedUtils;
	}
	
	public PermtiWeatherEntityResult getWeatherInputs(HSSFSheet sheet) {
		try {
			PermtiWeatherEntityResult entity = new PermtiWeatherEntityResult();
			int rowTotal = sheet.getLastRowNum();
			if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
				entity.setElevation(sharedUtils.findStringValue(sheet, "elevation"));
				entity.setQuatrePourCentAverageHigh(sharedUtils.findStringValue(sheet, "quatre_average_high"));
				entity.setDeuxPourCentAverageHigh(sharedUtils.findStringValue(sheet, "deux_average_high"));
				entity.setExtremeMinimum(sharedUtils.findStringValue(sheet, "extreme_minimum"));
				entity.setQuatrePourCentAvHighOther(sharedUtils.findStringValue(sheet, "quatre_cent_average_high_other"));
				entity.setDeuxPourCentAverageHighOther(sharedUtils.findStringValue(sheet, "deux_cent_average_high_other"));
				entity.setExtremeMinimumOther(sharedUtils.findStringValue(sheet, "extreme_minimum_other"));
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return new PermtiWeatherEntityResult();
		}
	}

	
	public PermitAdvEntityResult getADVInputs(HSSFSheet sheet) {
		try {
			PermitAdvEntityResult entity = new PermitAdvEntityResult();
			int rowTotal = sheet.getLastRowNum();
			if ((rowTotal > 0) || (sheet.getPhysicalNumberOfRows() > 0)) {
				entity.setWindSpeed(sharedUtils.findStringValue(sheet, "wind_speed"));
				entity.setSnowLoad(sharedUtils.findStringValue(sheet, "snow_load"));
				entity.setGoogleImage(sharedUtils.findStringValue(sheet, "google_image"));
				entity.setMapImage(sharedUtils.findStringValue(sheet, "map_image"));
				entity.setpVRailQteString(sharedUtils.findStringValue(sheet, "p_v_rail_qte"));
				entity.setpVRailLength(sharedUtils.findStringValue(sheet, "p_v_rail_length"));
				entity.setStanchionQteString(sharedUtils.findStringValue(sheet, "stanchion_qte"));
				entity.setStanchionLength(sharedUtils.findStringValue(sheet, "stanchion_length"));
				entity.setSpliceQteString(sharedUtils.findStringValue(sheet, "splice_qte"));
				entity.setSpliceLength(sharedUtils.findStringValue(sheet, "splice_length"));
				entity.setS200QteString(sharedUtils.findStringValue(sheet, "s200_qte"));
				entity.setS200Length(sharedUtils.findStringValue(sheet, "s200_length"));
				entity.setPv1(sharedUtils.findStringValue(sheet, "pv1"));
				entity.setCustomersServiceAgreementIDNumber(sharedUtils.findStringValue(sheet, "customers_service_agreement_id_number"));
				entity.setCustomersRateSchedule(sharedUtils.findStringValue(sheet, "customers_rate_schedule"));
				entity.setEngineeringFirm(sharedUtils.findStringValue(sheet, "engineering_firm"));
				entity.setCustomersAccountNumber(sharedUtils.findStringValue(sheet, "customers_account_number"));
				entity.setCustomerName(sharedUtils.findStringValue(sheet, "customer_name"));
				entity.setpVRailQte(sharedUtils.findStringValue(sheet, "p_v_rail_qte"));
				entity.setStanchionQte(sharedUtils.findStringValue(sheet, "stanchion_qte"));
				entity.setSpliceQte(sharedUtils.findStringValue(sheet, "splice_qte"));
				entity.setS200Qte(sharedUtils.findStringValue(sheet, "s200_qte"));
				entity.setWindSpeedOther(sharedUtils.findStringValue(sheet, "win_speed_other"));
				entity.setSnowLoadOther(sharedUtils.findStringValue(sheet, "snow_load_other"));
				entity.setUploadCommentsGoogle(sharedUtils.findStringValue(sheet, "upload_comments_google"));
				entity.setUploadCommentsNearMap(sharedUtils.findStringValue(sheet, "upload_comments_near_map"));
				entity.setModuleLayout(sharedUtils.findStringValue(sheet, "module_layout"));
				entity.setSizeOfPipe(sharedUtils.findStringValue(sheet, "size_of_pipe"));
				entity.setThicknessOfPipe(sharedUtils.findStringValue(sheet, "thickness_of_pipe"));
				entity.setBracedUnbraced(sharedUtils.findStringValue(sheet, "braced_unbraced"));
				entity.setFootingDiameter(sharedUtils.findStringValue(sheet, "footing_diameter"));
				entity.setModuleLayoutOther(sharedUtils.findStringValue(sheet, "module_layout_other"));
				entity.setSizeOfPipeOther(sharedUtils.findStringValue(sheet, "size_of_pipe_other"));
				entity.setThicknessOfPipeOther(sharedUtils.findStringValue(sheet, "thickness_of_pipe_other"));
				entity.setFootingDiameterOther(sharedUtils.findStringValue(sheet, "footing_diameter_other"));
				entity.setOpenTldLibrary(sharedUtils.findBooleanValue(sheet, "open_tld_library"));
				entity.setTldShortList(sharedUtils.findBooleanValue(sheet, "tld_short_list"));
				entity.setTldList(sharedUtils.findStringValue(sheet, "tld_list"));
				entity.setrSheetList(sharedUtils.findStringValue(sheet, "r_sheet_list"));
				entity.setBracedUnbracedOther(sharedUtils.findStringValue(sheet, "braced_unbraced_other"));
				entity.setGoogleZoom(sharedUtils.findIntValue(sheet, "google_zoom"));
			}
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return new PermitAdvEntityResult();
		}
	}

}
