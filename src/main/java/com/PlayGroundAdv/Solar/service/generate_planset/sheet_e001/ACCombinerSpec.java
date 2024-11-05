package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e001;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetInverterData;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ACCombinerSpec {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final GetInverterData getInverterData;

	public ACCombinerSpec(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg, GetInverterData getInverterData) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		this.getInverterData = getInverterData;
	}

	public void acCombinerMapping(AcroFields form, PermitProjectSiteInfoEntity siteInfo, List<Inverters> inverters,
			Integer firsttInverterQty, Integer secondtInverterQty, int inverterQty, ACCombinerSLC slcAcCombiner,
			int sheetIndex) {
		try {
			if (slcAcCombiner != null && slcAcCombiner.getId() != null) {
				accMapping(form, siteInfo, sheetIndex, slcAcCombiner);
				Float sumIacMaxInt = getInverterData.getSumIacmax(inverters, firsttInverterQty, secondtInverterQty);
				if (sumIacMaxInt > 0) {
					form.setField(sheetIndex + "-AC-COMB-1-TOTAL-INPUT-CURRENT", sumIacMaxInt + "");
				} else
					form.setField(sheetIndex + "-AC-COMB-1-TOTAL-INPUT-CURRENT", "Iacmax Update Req");

				form.setField(sheetIndex + "-AC-COMB-1-NUMBER-OF-BRANCH-CIRCUIT", inverterQty + "");

			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void acCombinerMapping(PermitProjectSiteInfoEntity siteInfo, Inverters microInverterInfo,
			ACCombinerSLC acCombiner, AcroFields form, PlansetUtils plansetUtils, ACCombinerSLC slcAcCombiner,
			int sheetIndex) {
		try {
			ACCombinerSLC acc = slcAcCombiner != null && slcAcCombiner.getId() != null ? slcAcCombiner : acCombiner;
			if (acc != null && acc.getId() != null) {

				accMapping(form, siteInfo, sheetIndex, acc);

				if (microInverterInfo != null && checkValue.NotEquals(microInverterInfo.getIacmax(), "")) {
					float inputCurrent = Float.parseFloat(microInverterInfo.getIacmax())
							* plansetUtils.getModulePerMicroInverter();
					form.setField(sheetIndex + "-AC-DISCO-1-TOTAL-INPUT-CURRENT",
							new DecimalFormat("##.##").format(inputCurrent) + "");
				} else
					form.setField(sheetIndex + "-AC-DISCO-1-TOTAL-INPUT-CURRENT", "Iacmax Update Req");

				form.setField(sheetIndex + "-AC-DISCO-1-NUMBER-OF-BRANCH-CIRCUIT",
						plansetUtils.getNumberOfBranchCircuit() + "");

			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private void accMapping(AcroFields form, PermitProjectSiteInfoEntity siteInfo, int sheetIndex, ACCombinerSLC acc) {
		try {
			form.setField(sheetIndex + "-AC-COMB-1-MANUFACTURER", acc.getManufacturerMappingValue());
			form.setField(sheetIndex + "-AC-COMB-1-MODEL-NUMBER", acc.getModelMappingValue());
			// 08-16-2019: M.A: PR-706
			if (acc.getRatedOperationalVoltage() != null
					&& checkValue.Equals(acc.getRatedOperationalVoltage(), "240VPhase")) {
				form.setField(sheetIndex + "-AC-COMB-1-RATED-NOMINAL-VOLTAGE", "240");
			} else {
				form.setField(sheetIndex + "-AC-COMB-1-RATED-NOMINAL-VOLTAGE", acc.getRatedOperationalVoltage());
			}
			form.setField(sheetIndex + "-AC-COMB-1-RATED-CURRENT", acc.getRatedCurrent());
			form.setField(sheetIndex + "-AC-COMB-1-NUMBER-OF-POLES", acc.getNumberOfPoles());
			form.setField(sheetIndex + "-AC-COMB-1-NEMA-RATING", acc.getNemaRating());
			if (checkValue.Equals(acc.getCombinerDeviceType(), "With Main Breaker")
					&& checkValue.NotEquals(siteInfo.getPanelMainBreakerRating(), "")) {
				form.setField(sheetIndex + "-AC-COMB-1-MAIN-BREAKER-SIZE", siteInfo.getPanelMainBreakerRating());
			} else
				form.setField(sheetIndex + "-AC-COMB-1-MAIN-BREAKER-SIZE", "N/A");
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}
}
