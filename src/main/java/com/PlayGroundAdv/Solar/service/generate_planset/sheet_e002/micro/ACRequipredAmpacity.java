package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.micro;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitLayoutEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.model.planset_utils.VoltageDropTable;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit.MicroConductorTemperatureDerating;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit.NecBchart;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit.RequiredConductorAmpacity;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.AmpacityCorrectionChart;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.CircuitEnvironment;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ACRequipredAmpacity {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final AmpacityCorrectionChart ampCorrection;
	final CircuitEnvironment circuitEnvService;
	final NEC3106B16Repository nec3106B16Repo;
	final ACCorrectedAmpacity acCorrectedAmpacity;
	final RequiredConductorAmpacity requiredConductorAmpacity;
	final MicroConductorTemperatureDerating conductorTempDerating;
	final NecBchart necBchartService;
	final VoltageDrop voltageDropService;

	public ACRequipredAmpacity(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			AmpacityCorrectionChart ampCorrection, CircuitEnvironment circuitEnvService,
			NEC3106B16Repository nec3106b16Repo, ACCorrectedAmpacity acCorrectedAmpacity,
			RequiredConductorAmpacity requiredConductorAmpacity,
			MicroConductorTemperatureDerating conductorTempDerating, NecBchart necBchartService,
			VoltageDrop voltageDropService) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		this.ampCorrection = ampCorrection;
		this.circuitEnvService = circuitEnvService;
		this.nec3106B16Repo = nec3106b16Repo;
		this.acCorrectedAmpacity = acCorrectedAmpacity;
		this.requiredConductorAmpacity = requiredConductorAmpacity;
		this.conductorTempDerating = conductorTempDerating;
		this.necBchartService = necBchartService;
		this.voltageDropService = voltageDropService;
	}

	static final String CHOOSE_CONDUCTOR_SIZE = "I want to choose the conductor size";

	public void requipredAmpacityMapping(AcroFields form, int i, ESSConnectors ac, PlansetUtils plansetUtils,
			Inverters microInverterInfo, PermitProjectSiteInfoEntity permitProjectSiteInfo, Integer perCentAverageHigh,
			List<String> acCircuitEnvironment, List<String> acTradeSize, List<Integer> acNumberOfConductors,
			AuthentificationEntity userConnectedEntity, PermitLayoutEntity permitLayoutEntity, int sheetIndex,
			UserSettingEntity userSetting, PermitHomeSiteInfoEntity permitHomeSite, String necCode, E002Model params,
			List<VoltageDropTable> voltageDrop, int indexAcCombiner, Boolean mapFromUserInput, Boolean existing,
			List<ESSConnectors> acList, Long projectId) {
		try {
			///// **** CONDUCTOR SPECIFICATIONS ****//////
			form.setField(sheetIndex + "-AC" + i + "-MATERIAL", "CU");
			form.setField(sheetIndex + "-AC" + i + "-TEMPERATURE", "75");

			///// **** RREQUIRED CONDUCTOR AMPACITY ****//////
			String tempAdder = "";
			requiredConductorAmpacity.mapMicroACrequiredAmpacity(form, plansetUtils, i, indexAcCombiner,
					indexAcCombiner != -1, microInverterInfo, sheetIndex, ac.getSourceID().contains("SUB"),
					permitProjectSiteInfo, params);

			tempAdder = conductorTempDerating.essconductorTemperatureDeratingMapping(form, ac, i, perCentAverageHigh,
					acCircuitEnvironment, false, sheetIndex, userSetting, necCode,
					permitLayoutEntity.getShowConduitRoofAsHeight(), params, permitHomeSite.getState());

			if (userConnectedEntity.getRoleEntity().getId() == 2
					|| (userConnectedEntity.getRoleEntity().getId() != 2 && !Boolean.TRUE.equals(mapFromUserInput))) {

				params.setCalculatedNumberOfConductor(1);
				mapping(form, ac, i, permitProjectSiteInfo, acTradeSize, acNumberOfConductors, acCircuitEnvironment,
						sheetIndex, permitHomeSite, indexAcCombiner, params, existing, plansetUtils, permitLayoutEntity,
						voltageDrop, acList, projectId);

				// A.B Recalculate Corrected Size IF:
				// Origin == INV
				// Corrected Ampacity < Required Ampacity
				if (ac.getSourceID().contains("INV") && i == 1 && checkValue.isNumeric(tempAdder)
						&& checkValue.isNumeric(params.getRequiredACConductorAmpacity())
						&& checkValue.isNumeric(params.getCorrectedACAmpacity())) {

					Float requiredAmpacityFloat = Float.valueOf(params.getRequiredACConductorAmpacity());
					Float correctedAmpacityFloat = Float.valueOf(params.getCorrectedACAmpacity());
					Float tempAdderFloat = Float.valueOf(tempAdder);
					if (tempAdderFloat > 0 && correctedAmpacityFloat < requiredAmpacityFloat) {
						conductorTempDerating.essconductorTemperatureDeratingMapping(form, ac, i, perCentAverageHigh,
								acCircuitEnvironment, true, sheetIndex, userSetting, necCode,
								permitLayoutEntity.getShowConduitRoofAsHeight(), params, permitHomeSite.getState());
					}
				}
				// A.B Recalculate Corrected Size IF:
				// Origin == INV
				// Corrected Ampacity < Required Ampacity
				if (ac.getSourceID().contains("INV") && checkValue.isNumeric(tempAdder)
						&& checkValue.isNumeric(params.getRequiredACConductorAmpacity())
						&& checkValue.isNumeric(params.getCorrectedACAmpacity())) {

					Float requiredAmpacityFloat = Float.valueOf(params.getRequiredACConductorAmpacity());
					Float correctedAmpacityFloat = Float.valueOf(params.getCorrectedACAmpacity());
					Float tempAdderFloat = Float.valueOf(tempAdder);
					if (tempAdderFloat > 0 && correctedAmpacityFloat < requiredAmpacityFloat) {
						conductorTempDerating.essconductorTemperatureDeratingMapping(form, ac, i, perCentAverageHigh,
								acCircuitEnvironment, true, sheetIndex, userSetting, necCode,
								permitLayoutEntity.getShowConduitRoofAsHeight(), params, permitHomeSite.getState());

						params.setCalculatedNumberOfConductor(1);
						acTradeSize.remove(acTradeSize.size() - 1);
						mapping(form, ac, i, permitProjectSiteInfo, acTradeSize, acNumberOfConductors,
								acCircuitEnvironment, sheetIndex, permitHomeSite, indexAcCombiner, params, existing,
								plansetUtils, permitLayoutEntity, voltageDrop, acList, projectId);
					}
				}

			} else if (userConnectedEntity.getRoleEntity().getId() != 2 && Boolean.TRUE.equals(mapFromUserInput)) {

				acCorrectedAmpacity.mapFromPortal(form, i, ac.getCircuitSpec(), acTradeSize, acNumberOfConductors,
						sheetIndex, permitProjectSiteInfo, params, ac.getSourceID().contains("ACJBOX"));

			}

		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private void mapping(AcroFields form, ESSConnectors ac, int i, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			List<String> acTradeSize, List<Integer> acNumberOfConductors, List<String> acCircuitEnvironment,
			int sheetIndex, PermitHomeSiteInfoEntity permitHomeSite, int indexAcCombiner, E002Model params,
			Boolean existing, PlansetUtils plansetUtils, PermitLayoutEntity permitLayoutEntity,
			List<VoltageDropTable> voltageDrop, List<ESSConnectors> acList, Long projectId) {
		try {

			acCorrectedAmpacity.mapByLogic(form, ac, i, permitProjectSiteInfo, plansetUtils.getNumberOfBranchCircuit(),
					acTradeSize, acNumberOfConductors, acCircuitEnvironment, permitLayoutEntity.getConduitRun(), 1,
					sheetIndex, permitHomeSite, indexAcCombiner, params, existing, false);
			// A.B Recalculate Corrected Size IF Number Of Conductor == 2 || 3
			if (params.getCalculatedACNumberOfConductor() == 2 || params.getCalculatedACNumberOfConductor() == 3) {
				repeateMapping(form, ac, i, permitProjectSiteInfo, plansetUtils.getNumberOfBranchCircuit(), acTradeSize,
						acNumberOfConductors, acCircuitEnvironment, permitLayoutEntity.getConduitRun(), sheetIndex,
						permitHomeSite, indexAcCombiner, params, existing, true);
			}

			// A.B Recalculate Corrected Size IF:
			// Origin != INV or Existing Sub Panel
			// Corrected Ampacity < Required Ampacity
			if (!ac.getSourceID().contains("INV")
					&& !(ac.getSourceID().contains("ACJBOX") && checkValue
							.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), CHOOSE_CONDUCTOR_SIZE))
					&& checkValue.getFloatValue(params.getCorrectedACAmpacity()) < checkValue
							.getFloatValue(params.getRequiredACConductorAmpacity())) {

				params.setIncorrectTradeSizeLogic(true);
				params.setTradeSizeRepeate("");
				params.setTradeSizeRepeate(params.getNEC310().getTradeSze());
				do {
					repeateMapping(form, ac, i, permitProjectSiteInfo, plansetUtils.getNumberOfBranchCircuit(),
							acTradeSize, acNumberOfConductors, acCircuitEnvironment, permitLayoutEntity.getConduitRun(),
							sheetIndex, permitHomeSite, indexAcCombiner, params, existing, true);
				} while (params.getNEC310() != null
						&& checkValue.getFloatValue(params.getCorrectedACAmpacity()) <= checkValue
								.getFloatValue(params.getRequiredACConductorAmpacity()));
			}

			// A.B Voltage Drop Calculation
			if (ac.getCircuitSpec().getCircuitLength() != null && ac.getCircuitSpec().getCircuitLength() > 0) {
				voltageDropService.voltageDrop(form, sheetIndex, indexAcCombiner, voltageDrop, acNumberOfConductors,
						params, permitHomeSite, acTradeSize, acList, permitProjectSiteInfo, projectId);
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private void repeateMapping(AcroFields form, ESSConnectors ac, int i,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, int microNumberOfStrings, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, List<String> acCircuitEnvironment, String conduitRun, int sheetIndex,
			PermitHomeSiteInfoEntity permitHomeSite, int indexAcCombiner, E002Model params, Boolean existing,
			Boolean repeatingACMapping) {
		try {
			params.setNec31016Column90(params.getRequiredAmpacity()
					/ (Float.parseFloat(params.getTempDerating()) * Float.parseFloat(params.getConduitFillDerating())));

			params.setNEC310(necBchartService.getNecBchart(params));

			if (params.getNEC310() != null) {
				acNumberOfConductors.remove(acNumberOfConductors.size() - 1);
				acTradeSize.remove(acTradeSize.size() - 1);
				acCorrectedAmpacity.mapByLogic(form, ac, i, permitProjectSiteInfo, microNumberOfStrings, acTradeSize,
						acNumberOfConductors, acCircuitEnvironment, conduitRun, params.getCalculatedNumberOfConductor(),
						sheetIndex, permitHomeSite, indexAcCombiner, params, existing, repeatingACMapping);
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}
}
