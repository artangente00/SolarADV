package com.PlayGroundAdv.Solar.service.generate_planset.shared;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ElectricUtilityNumber {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;

	public ElectricUtilityNumber(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
	}

	// A.B 03-18-2022 CR-PM-798
	public void mapMeterNumber(AcroFields form, int sheetIndex, String sheet, PermitHomeSiteInfoEntity permitHomeSite,
			ElectricalUtilityEntity electricalCompany) {
		try {
			if( (Boolean.TRUE.equals(isOncor(permitHomeSite, electricalCompany)))
				||
				//A.W CR-PM-798-MOD-003 25-05-2022
				(Boolean.TRUE.equals(isCenterpoint(permitHomeSite, electricalCompany)))
					) {
				String meterNumber = checkValue.isStringNotEmpty(permitHomeSite.getMeterNumber()) ? permitHomeSite.getMeterNumber() : "";
				String esiId = checkValue.isStringNotEmpty(permitHomeSite.getEsiidNumber()) ? permitHomeSite.getEsiidNumber() : "";
				form.setField(sheetIndex + "-" + sheet + "-METER NUMBER:",
						"METER NUMBER: " + meterNumber);
				form.setField(sheetIndex + "-" + sheet + "-ESID NUMBER", "ESI ID: " + esiId);
			} else {
				form.setField(sheetIndex + "-" + sheet + "-METER NUMBER:", "");
				form.setField(sheetIndex + "-" + sheet + "-ESID NUMBER", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	// A.B 03-18-2022 CR-PM-798-MOD-003
	public void mapEsiId(AcroFields form, int sheetIndex, String sheet, PermitHomeSiteInfoEntity permitHomeSite,
			ElectricalUtilityEntity electricalCompany) {
		try {
			if ((Boolean.TRUE.equals(isOncor(permitHomeSite, electricalCompany)))|| (Boolean.TRUE.equals(isCenterpoint(permitHomeSite, electricalCompany)))){
				String esiId = checkValue.isStringNotEmpty(permitHomeSite.getEsiidNumber()) ? permitHomeSite.getEsiidNumber() : "";
				form.setField(sheetIndex + "-" + sheet + "-ESID NUMBER", "ESI ID: " + esiId);
				String meterNumber = checkValue.isStringNotEmpty(permitHomeSite.getMeterNumber()) ? permitHomeSite.getMeterNumber() : "";
 				form.setField(sheetIndex + "-" + sheet + "-METER NUMBER:",
						"METER NUMBER: " + meterNumber);
			} else {
				form.setField(sheetIndex + "-" + sheet + "-ESID NUMBER", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private Boolean isOncor(PermitHomeSiteInfoEntity permitHomeSite, ElectricalUtilityEntity electricalCompany) {
		String utility = checkValue.Equals(permitHomeSite.getUtilityCompanyName(), "Other") ? permitHomeSite.getUtilityCompanyNameOther() : electricalCompany.getUtilityCompanyName();
		return checkValue.NotEquals(permitHomeSite.getState(), "CA")
				&& checkValue.containsCaseInsensitive(utility, "Oncor");
	}
	
	//A.B 06-01-2022 REV-CR-PM-798
	private Boolean isCenterpoint(PermitHomeSiteInfoEntity permitHomeSite, ElectricalUtilityEntity electricalCompany) {
		String utility = checkValue.Equals(permitHomeSite.getUtilityCompanyName(), "Other") ? permitHomeSite.getUtilityCompanyNameOther() : electricalCompany.getUtilityCompanyName();
		return checkValue.NotEquals(permitHomeSite.getState(), "CA")
				&& checkValue.EqualsCaseInsensitive(utility, "CenterPoint Energy Houston Electric LLC");
	}
}
