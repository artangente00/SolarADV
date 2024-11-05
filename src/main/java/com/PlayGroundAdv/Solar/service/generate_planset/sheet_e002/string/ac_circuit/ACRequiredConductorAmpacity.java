package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.string.ac_circuit;

import java.io.IOException;
import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetInverterData;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ACRequiredConductorAmpacity {

	static final String SMALLEST_CONDUCTOR_SIZE = "Show the smallest conductor size allowed by NEC Code for this circumstance";
	static final String CHOOSE_CONDUCTOR_SIZE = "I want to choose the conductor size";

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final GetInverterData getInverterData;

	public ACRequiredConductorAmpacity(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			GetInverterData getInverterData) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		this.getInverterData = getInverterData;
	}

	public E002Model mapACrequiredAmpacity(AcroFields form, int i, int indexAcCombiner, Boolean stepACCombiner,
			String sumIacMax, String largestIacMax, int sheetIndex, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			ESSConnectors ac, E002Model params) {
		try {

			if (checkValue.contains(ac.getSourceID(), "SUB")
					&& ((checkValue.Equals(permitProjectSiteInfo.getConnectionPoint(), "Existing")
							&& checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(),
									SMALLEST_CONDUCTOR_SIZE)
							|| checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(),
									CHOOSE_CONDUCTOR_SIZE))
							|| checkValue.Equals(permitProjectSiteInfo.getConnectionPoint(), "Proposed"))) {
				
				mapSubPanel(form, i, sheetIndex, permitProjectSiteInfo, params);
				
			} else if (i >= indexAcCombiner && checkValue.Equals(stepACCombiner, true)) {
				form.setField(sheetIndex + "-" + "AC" + i + "-CONTINUOUS-OPERATION", "1.25");
				if (checkValue.NotEquals(sumIacMax, "")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT",
							String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(sumIacMax))));
					form.setField(sheetIndex + "-" + "AC" + i + "-REQUIRED-AMPACITY",
							String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(sumIacMax) * 1.25)));
					form.setField(sheetIndex + "-" + "AC" + i + "-REQUIRED-AMPACITY1",
							String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(sumIacMax) * 1.25)));
					params.setRequiredAmpacity(Double.parseDouble(sumIacMax) * 1.25);
					params.setRequiredACConductorAmpacity(
							String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(sumIacMax) * 1.25)));
				} else
					form.setField(sheetIndex + "-" + "AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT", "Iacmax Update Req");
			} else {
				form.setField(sheetIndex + "-" + "AC" + i + "-CONTINUOUS-OPERATION", "1.25");
				if (checkValue.NotEquals(largestIacMax, "")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-REQUIRED-AMPACITY",
							String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(largestIacMax) * 1.25)));
					form.setField(sheetIndex + "-" + "AC" + i + "-REQUIRED-AMPACITY1",
							String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(largestIacMax) * 1.25)));
					params.setRequiredAmpacity(Double.parseDouble(largestIacMax) * 1.25);
					params.setRequiredACConductorAmpacity(
							String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(largestIacMax) * 1.25)));

					form.setField(sheetIndex + "-" + "AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT",
							String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(largestIacMax))));
				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT", "Iacmax Update Req");
				}
			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return params;
	}

	public void mapMicroACrequiredAmpacity(AcroFields form, PlansetUtils plansetUtils, int i, Integer indexAcCombiner,
			Boolean stepACCombiner, Inverters microInverterInfo, int sheetIndex, Boolean subPanel,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, E002Model params) {

		try {
			Float iacMaxMicroCalcul = getInverterData.getIacMax(microInverterInfo);

			if (Boolean.TRUE.equals(subPanel)
					&& ((checkValue.Equals(permitProjectSiteInfo.getConnectionPoint(), "Existing")
							&& checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(),
									SMALLEST_CONDUCTOR_SIZE)
							|| checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(),
									CHOOSE_CONDUCTOR_SIZE))
							|| checkValue.Equals(permitProjectSiteInfo.getConnectionPoint(), "Proposed"))) {

				mapSubPanel(form, i, sheetIndex, permitProjectSiteInfo, params);

			} else if (checkValue.Equals(stepACCombiner, true) && i >= indexAcCombiner) {
				form.setField(sheetIndex + "-" + "AC" + i + "-CONTINUOUS-OPERATION", "1.25");
				Float iac = iacMaxMicroCalcul;
				if (iacMaxMicroCalcul != null && iacMaxMicroCalcul > 0) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT",
							String.valueOf(new DecimalFormat("##.##")
									.format(iacMaxMicroCalcul * plansetUtils.getModulePerMicroInverter())));
				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT", "Iacmax Update Req");
					iac = 1f;
				}
				form.setField(sheetIndex + "-" + "AC" + i + "-REQUIRED-AMPACITY", String.valueOf(
						new DecimalFormat("#.0").format(iac * 1.25 * plansetUtils.getModulePerMicroInverter())));
				form.setField(sheetIndex + "-" + "AC" + i + "-REQUIRED-AMPACITY1", String.valueOf(
						new DecimalFormat("#.0").format(iac * 1.25 * plansetUtils.getModulePerMicroInverter())));
				params.setRequiredAmpacity(iac * 1.25 * plansetUtils.getModulePerMicroInverter());
				params.setRequiredACConductorAmpacity(String.valueOf(
						new DecimalFormat("#.0").format(iac * 1.25 * plansetUtils.getModulePerMicroInverter())));
			} else {
				form.setField(sheetIndex + "-" + "AC" + i + "-CONTINUOUS-OPERATION", "1.25");
				Float iac = iacMaxMicroCalcul;
				if (iacMaxMicroCalcul != null && iacMaxMicroCalcul > 0) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT", String.valueOf(
							new DecimalFormat("##.##").format(iacMaxMicroCalcul * plansetUtils.getMaxBranchMods())));
				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT", "Iacmax Update Req");
					iac = 1f;
				}
				form.setField(sheetIndex + "-" + "AC" + i + "-REQUIRED-AMPACITY",
						String.valueOf(new DecimalFormat("#.0").format(iac * 1.25 * plansetUtils.getMaxBranchMods())));
				form.setField(sheetIndex + "-" + "AC" + i + "-REQUIRED-AMPACITY1",
						String.valueOf(new DecimalFormat("#.0").format(iac * 1.25 * plansetUtils.getMaxBranchMods())));
				params.setRequiredAmpacity(iac * 1.25 * plansetUtils.getMaxBranchMods());
				params.setRequiredACConductorAmpacity(
						String.valueOf(new DecimalFormat("#.0").format(iac * 1.25 * plansetUtils.getMaxBranchMods())));
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private void mapSubPanel(AcroFields form, int i, int sheetIndex, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			E002Model params) {
		try {

			form.setField(sheetIndex + "-" + "AC" + i + "-CONTINUOUS-OPERATION", "1.0");
			float subPanelMainBreaker = -1f;
			if (checkValue.isNumeric(permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating())) {
				form.setField(sheetIndex + "-" + "AC-SUB-PANEL-1-MAIN-BREAKER-RATING",
						permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating());
				subPanelMainBreaker = Float
						.parseFloat(permitProjectSiteInfo.getIfApplicableSubPanelMainBreakerRating());
			} else
				form.setField(sheetIndex + "-" + "AC-SUB-PANEL-1-MAIN-BREAKER-RATING", "");

			// 05-27-2019 A.B if SubPanel Breaker (OCPD) at Main Service is Shown
			Boolean subPanelWithMultipleSystem = checkValue.Equals(permitProjectSiteInfo.getSolarLocation(),
					"Back-fed Breaker")
					&& (checkValue.Equals(permitProjectSiteInfo.getCombiningACCircuits(),
							"An Existing Main or Sub Panel with More Than One Back-Fed Breaker")
							|| checkValue.Equals(permitProjectSiteInfo.getCombiningACCircuits(),
									"A Proposed Main or Sub Panel with More Than One Back-Fed Breaker")) ? true : false;
			Double subPanelBreakerOCPD = -1d;

			if (checkValue.Equals(subPanelWithMultipleSystem, false)) {
				if (checkValue.Equals(permitProjectSiteInfo.getSubPanelBreakerOCPD(), "Other")
						&& checkValue.isNumeric(permitProjectSiteInfo.getSubPanelBreakerOCPDOther())) {
					form.setField(sheetIndex + "-" + "AC-SUB-PANEL-1-POC-BREAKER-AMP-RATING",
							permitProjectSiteInfo.getSubPanelBreakerOCPDOther());
					subPanelBreakerOCPD = Double.parseDouble(permitProjectSiteInfo.getSubPanelBreakerOCPDOther());
				} else if (checkValue.isNumeric(permitProjectSiteInfo.getSubPanelBreakerOCPD())) {
					form.setField(sheetIndex + "-" + "AC-SUB-PANEL-1-POC-BREAKER-AMP-RATING",
							permitProjectSiteInfo.getSubPanelBreakerOCPD());
					subPanelBreakerOCPD = Double.parseDouble(permitProjectSiteInfo.getSubPanelBreakerOCPD());
				} else
					form.setField(sheetIndex + "-" + "AC-SUB-PANEL-1-POC-BREAKER-AMP-RATING", "");
			}

			Double minInverterOutputCurrent = subPanelBreakerOCPD < 0 && subPanelMainBreaker < 0 ? 0d
					: subPanelBreakerOCPD < 0 ? subPanelMainBreaker
							: subPanelMainBreaker < 0 ? subPanelBreakerOCPD
									: checkValue.Equals(subPanelWithMultipleSystem, false)
											? Math.min(subPanelMainBreaker, subPanelBreakerOCPD)
											: 0d;

			form.setField(sheetIndex + "-" + "AC" + i + "-MAX-INVERTER-OUTPUT-CURRENT",
					String.valueOf(new DecimalFormat("##.##").format(minInverterOutputCurrent)));
			form.setField(sheetIndex + "-" + "AC" + i + "-REQUIRED-AMPACITY",
					String.valueOf(new DecimalFormat("#.0").format(minInverterOutputCurrent)));
			form.setField(sheetIndex + "-" + "AC" + i + "-REQUIRED-AMPACITY1",
					String.valueOf(new DecimalFormat("#.0").format(minInverterOutputCurrent)));
			params.setRequiredAmpacity(minInverterOutputCurrent);
			params.setRequiredACConductorAmpacity(
					String.valueOf(new DecimalFormat("#.0").format(minInverterOutputCurrent)));

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}
}
