package com.PlayGroundAdv.Solar.service.generate_planset.p_sheets;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetInverterData;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ACCombinerDisconnectMapping {

	final CheckValueTypesService checkValue;
	final GetInverterData getInverterData;
	final TechnicalProblemMsg technicalProblemMsg;

	public ACCombinerDisconnectMapping(CheckValueTypesService checkValue, GetInverterData getInverterData,
			TechnicalProblemMsg technicalProblemMsg) {
		super();
		this.checkValue = checkValue;
		this.getInverterData = getInverterData;
		this.technicalProblemMsg = technicalProblemMsg;
	}

	static final String AC_DISCO = "-AC-DISCO-";
	static final String RATED_CURRENT = "-RATED-CURRENT";

	public void mapACDRatedCurrent(AcroFields form, int sheetIndex, int accIndex, String ratedCurrent) {
		try {
			if (ratedCurrent != null) {
				form.setField(sheetIndex + AC_DISCO + accIndex + RATED_CURRENT, ratedCurrent);
				form.setField(sheetIndex + AC_DISCO + accIndex + RATED_CURRENT + "1", ratedCurrent);
				if (accIndex == 1) {
					form.setField(sheetIndex + "-DUAL-POWER-SOURCES-RATED-AC-OUTPUT-CURRENT", ratedCurrent);
				}
			} else {
				form.setField(sheetIndex + AC_DISCO + accIndex + RATED_CURRENT, "");
				form.setField(sheetIndex + AC_DISCO + accIndex + RATED_CURRENT + "1", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapACCRatedCurrent(AcroFields form, int sheetIndex, int accIndex, String ratedCurrent) {
		try {
			form.setField(sheetIndex + AC_DISCO + accIndex + RATED_CURRENT, ratedCurrent);
			form.setField(sheetIndex + AC_DISCO + accIndex + RATED_CURRENT + "1", ratedCurrent);
			if (accIndex == 1) {
				form.setField(sheetIndex + "-DUAL-POWER-SOURCES-RATED-AC-OUTPUT-CURRENT", ratedCurrent);
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapACDOperationVoltage(AcroFields form, int sheetIndex, int accIndex, ACDisconnect acDisconnect,
			PermitHomeSiteInfoEntity permitHomeSite) {
		try {
			String voltage = getVoltage(acDisconnect, permitHomeSite);
			form.setField(sheetIndex + AC_DISCO + accIndex + "-RATED-NOMINAL-VOLTAGE", voltage);
			form.setField(sheetIndex + AC_DISCO + accIndex + "-RATED-NOMINAL-VOLTAGE1", voltage);
			form.setField(sheetIndex + "-" + "DUAL-POWER-SOURCES-NOMINAL-VOLTAGE-OUTPUT", voltage);
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	// A.B 03-18-2022 CR-PM-766-MOD-001
	public void mapACCOperationVoltage(AcroFields form, int sheetIndex, int accIndex, Inverters microInverterInfo) {
		try {
			String microInverterVac = getInverterData.getVac(microInverterInfo);
			form.setField(sheetIndex + AC_DISCO + accIndex + "-RATED-NOMINAL-VOLTAGE", microInverterVac);
			form.setField(sheetIndex + AC_DISCO + accIndex + "-RATED-NOMINAL-VOLTAGE1", microInverterVac);
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private String getVoltage(ACDisconnect acDisconnect, PermitHomeSiteInfoEntity permitHomeSite) {
		try {
			if (Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())) {
				return "240";
			}
			return checkValue.contains(acDisconnect.getRatedOperationalVoltage(), "/")
					? acDisconnect.getRatedOperationalVoltage().split("/")[1]
					: checkValue.Equals(acDisconnect.getRatedOperationalVoltage(), "240VPhase") ? "240"
							: acDisconnect.getRatedOperationalVoltage();
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return "";
		}
	}
}
