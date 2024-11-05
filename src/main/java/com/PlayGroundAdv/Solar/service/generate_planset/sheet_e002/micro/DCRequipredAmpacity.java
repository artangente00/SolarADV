package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.micro;

import java.io.IOException;
import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetPVModuleData;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.AmpacityCorrectionChart;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.CircuitEnvironment;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class DCRequipredAmpacity {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final AmpacityCorrectionChart ampCorrection;
	final GetPVModuleData getPVModuleData;
	final CircuitEnvironment circuitEnvService;
	final NEC3106B16Repository nec3106B16Repo;
	final DCCorrectedAmpacityMicro dcCorrectedAmpacity;

	public DCRequipredAmpacity(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			AmpacityCorrectionChart ampCorrection, GetPVModuleData getPVModuleData,
			CircuitEnvironment circuitEnvService, NEC3106B16Repository nec3106b16Repo,
			DCCorrectedAmpacityMicro dcCorrectedAmpacity) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		this.ampCorrection = ampCorrection;
		this.getPVModuleData = getPVModuleData;
		this.circuitEnvService = circuitEnvService;
		this.nec3106B16Repo = nec3106b16Repo;
		this.dcCorrectedAmpacity = dcCorrectedAmpacity;
	}

	public void requipredAmpacityMapping(AcroFields form, ESSConnectors dc, PermitConduitConductorSectionEntity circuit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Integer fourPerCentAverageHigh,
			Cmodulev2 moduleInfo, int sheetIndex, String necCode, Boolean showConduitRoofAsHeight, E002Model params,
			String state) {
		try {

			String iScRef = getPVModuleData.getIScRef(moduleInfo);
			String requiredConductorAmpacity = "";
			String correctedAmpacity = "";
			Integer tempAdderFloat = 0;
			form.setField(sheetIndex + "-" + "DC1-MATERIAL", "CU");
			form.setField(sheetIndex + "-" + "DC1-TEMPERATURE", "90");
			form.setField(sheetIndex + "-" + "DC1-HIGHEST-MODULE-Isc-IN-CIRCUIT", iScRef);
			if (checkValue.isNumeric(iScRef)) {
				form.setField(sheetIndex + "-" + "DC1-REQUIRED-AMPACITY",
						String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(iScRef) * 1.25 * 1.25)));
				form.setField(sheetIndex + "-" + "DC1-REQUIRED-AMPACITY1",
						String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(iScRef) * 1.25 * 1.25)));
				requiredConductorAmpacity = String
						.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(iScRef) * 1.25 * 1.25));
			} else {
				form.setField(sheetIndex + "-" + "DC1-REQUIRED-AMPACITY", "");
				form.setField(sheetIndex + "-" + "DC1-REQUIRED-AMPACITY1", "");
			}

			form.setField(sheetIndex + "-" + "DC1-NUMBER-OF-PARALLEL-MODULES-IN-SERIES-STRING", "1");
			form.setField(sheetIndex + "-" + "DC1-MAX-CURRENT-OF-CIRCUIT", "1.25");
			form.setField(sheetIndex + "-" + "DC1-CONTINUOUS-OPERATION", "1.25");

			form.setField(sheetIndex + "-" + "DC1-CIRCUIT-ENVIRONMENT", dc.getCircuitSpec().getCircuitEnvironment());

			String tempAdder = circuitEnvService.getTempAdder(dc.getCircuitSpec().getCircuitEnvironment(), necCode,
					state, null, showConduitRoofAsHeight);
			String operatingTemperature = checkValue.Equals(dc.getCircuitSpec().getCircuitEnvironment(), "UNDERGROUND")
					? "30"
					: getOperatingTemperature(fourPerCentAverageHigh, tempAdder);
			String dcAmpacityCorrectionB2a = checkValue.Equals(dc.getCircuitSpec().getCircuitEnvironment(),
					"UNDERGROUND") ? "1.00" : ampCorrection.getDCAmpacityCorrectionB2a(operatingTemperature);
			params.setOperatingTemperatureHigh(checkValue.getFloatValue(operatingTemperature) > 85);

			if (checkValue.isNumeric(dcAmpacityCorrectionB2a)) {
				params.setNec31016Column90(checkValue.isNumeric(iScRef)
						? Float.parseFloat(iScRef) * 1.25 * 1.25 / (Float.parseFloat(dcAmpacityCorrectionB2a))
						: 0);
			}
			tempAdderFloat = Integer.parseInt(tempAdder);

			// A.B 07/14/2022 CR-PM-1016 Map 30 for Underground Circuit
			form.setField(sheetIndex + "-" + "DC1-04-PERCENT-AVERAGE-HIGH-TEMPERATURE",
					checkValue.Equals(dc.getCircuitSpec().getCircuitEnvironment(), "UNDERGROUND") ? "30"
							: fourPerCentAverageHigh + "");
			if ((userConnectedEntity != null && userConnectedEntity.getRoleEntity() != null
					&& userConnectedEntity.getRoleEntity().getId() == 2)
					|| (userConnectedEntity != null && userConnectedEntity.getRoleEntity() != null
							&& userConnectedEntity.getRoleEntity().getId() != 2
							&& (!checkValue.NotEquals(circuit.getMapFromUserInput(), false)))) {

				correctedAmpacity = dcCorrectedAmpacity.mapByLogic(form, sheetIndex, dc.getCircuitSpec(), permitEntity,
						dcAmpacityCorrectionB2a);

			} else if (userConnectedEntity != null && userConnectedEntity.getRoleEntity() != null
					&& userConnectedEntity.getRoleEntity().getId() != 2
					&& Boolean.TRUE.equals(circuit.getMapFromUserInput())) {

				correctedAmpacity = dcCorrectedAmpacity.mapFromPortal(form, sheetIndex, dc.getCircuitSpec(),
						permitEntity, dcAmpacityCorrectionB2a, params);
			}

			if (tempAdderFloat == 22 && checkValue.isNumeric(requiredConductorAmpacity)
					&& checkValue.isNumeric(correctedAmpacity)) {
				Float requiredAmpacityFloat = Float.valueOf(requiredConductorAmpacity);
				Float correctedAmpacityFloat = Float.valueOf(correctedAmpacity);

				if (correctedAmpacityFloat < requiredAmpacityFloat) {
					tempAdder = "0";
					operatingTemperature = checkValue.Equals(dc.getCircuitSpec().getCircuitEnvironment(), "UNDERGROUND")
							? "30"
							: String.valueOf(fourPerCentAverageHigh);
					dcAmpacityCorrectionB2a = checkValue.Equals(dc.getCircuitSpec().getCircuitEnvironment(),
							"UNDERGROUND") ? "1.00" : ampCorrection.getDCAmpacityCorrectionB2a(operatingTemperature);
					params.setOperatingTemperatureHigh(fourPerCentAverageHigh > 85);

					if (checkValue.isNumeric(dcAmpacityCorrectionB2a)) {
						params.setNec31016Column90(checkValue.isNumeric(iScRef)
								? Float.parseFloat(iScRef) * 1.25 * 1.25 / (Float.parseFloat(dcAmpacityCorrectionB2a))
								: 0);
					}

					if ((userConnectedEntity.getRoleEntity().getId() == 2)
							|| (userConnectedEntity.getRoleEntity().getId() != 2
									&& (!checkValue.NotEquals(circuit.getMapFromUserInput(), false)))) {

						dcCorrectedAmpacity.mapByLogic(form, sheetIndex, dc.getCircuitSpec(), permitEntity,
								dcAmpacityCorrectionB2a);

					} else if (userConnectedEntity.getRoleEntity().getId() != 2
							&& Boolean.TRUE.equals(circuit.getMapFromUserInput())) {

						dcCorrectedAmpacity.mapFromPortal(form, sheetIndex, dc.getCircuitSpec(), permitEntity,
								dcAmpacityCorrectionB2a, params);
					}

				}
			}
			form.setField(sheetIndex + "-" + "DC1-HEIGHT-ABOVE-ROOF",
					circuitEnvService.getHighAboveRoof(dc.getCircuitSpec().getCircuitEnvironment(), necCode, state));
			form.setField(sheetIndex + "-" + "DC1-TEMPERATURE-ADDER", tempAdder);
			form.setField(sheetIndex + "-" + "DC1-OPERATING-TEMPERATURE", operatingTemperature);
			form.setField(sheetIndex + "-" + "DC1-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION",
					dcAmpacityCorrectionB2a);
			form.setField(sheetIndex + "-" + "DC1-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION1",
					dcAmpacityCorrectionB2a);

		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	private String getOperatingTemperature(Integer fourPerCentAverageHigh, String tempAdder) {
		try {
			if (checkValue.isStringInt(tempAdder) && fourPerCentAverageHigh != null) {
				return (fourPerCentAverageHigh + Integer.parseInt(tempAdder)) + "";
			} else
				return "0";

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return "0";
		}
	}
}
