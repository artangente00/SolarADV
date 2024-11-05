package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.dc_circuit;

import java.io.IOException;
import java.text.DecimalFormat;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.project_details.GetPVModuleData;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.AmpacityCorrectionChart;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.CircuitEnvironment;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.shared.DefaultRowMapping;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class DcMicroMapping {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final AmpacityCorrectionChart ampCorrection;
	final DefaultRowMapping defaultRowMapping;
	final GetPVModuleData getPVModuleData;
	final CircuitEnvironment circuitEnvService;
	final NEC3106B16Repository nec3106B16Repo;

	public DcMicroMapping(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			AmpacityCorrectionChart ampCorrection, DefaultRowMapping defaultRowMapping, GetPVModuleData getPVModuleData,
			CircuitEnvironment circuitEnvService, NEC3106B16Repository nec3106b16Repo) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		this.ampCorrection = ampCorrection;
		this.defaultRowMapping = defaultRowMapping;
		this.getPVModuleData = getPVModuleData;
		this.circuitEnvService = circuitEnvService;
		nec3106B16Repo = nec3106b16Repo;
	}

	public void getDC1Mapping(AcroFields form, String acCircuit, PermitConduitConductorSectionEntity circuit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Integer fourPerCentAverageHigh,
			Cmodulev2 moduleInfo, int sheetIndex, String necCode, Boolean showConduitRoofAsHeight, E002Model params,
			String state) {
		try {

			form.setField(sheetIndex + "-" + "DC1-CIRCUIT-ORGIN", "PV MODULE");
			if (checkValue.contains(acCircuit, "-")) {
				form.setField(sheetIndex + "-" + "DC1-CIRCUIT-DESTINATION", acCircuit.split("-")[0]);
			}
			if ((circuit != null && checkValue.Equals(circuit.getConductorTypeExisting(), true))
					|| (permitEntity != null && checkValue.Equals(permitEntity.getExistModule(), true))) {

				defaultRowMapping.mapDcDefaultValue(1, form, sheetIndex, "EXISTING");

			} else {
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

				form.setField(sheetIndex + "-" + "DC1-CIRCUIT-ENVIRONMENT", circuit.getCircuitEnvironment());

				String tempAdder = circuitEnvService.getTempAdder(circuit.getCircuitEnvironment(), necCode, state, null,
						showConduitRoofAsHeight);
				String operatingTemperature = checkValue.Equals(circuit.getCircuitEnvironment(), "UNDERGROUND") ? "30"
						: getOperatingTemperature(fourPerCentAverageHigh, tempAdder);
				String dcAmpacityCorrectionB2a = checkValue.Equals(circuit.getCircuitEnvironment(), "UNDERGROUND")
						? "1.00"
						: ampCorrection.getDCAmpacityCorrectionB2a(operatingTemperature);
				params.setOperatingTemperatureHigh(checkValue.getFloatValue(operatingTemperature) > 85);

				if (checkValue.isNumeric(dcAmpacityCorrectionB2a)) {
					params.setNec31016Column90(checkValue.isNumeric(iScRef)
							? Float.parseFloat(iScRef) * 1.25 * 1.25 / (Float.parseFloat(dcAmpacityCorrectionB2a))
							: 0);
				}
				tempAdderFloat = Integer.parseInt(tempAdder);

				//A.B 07/14/2022 CR-PM-1016 Map 30 for Underground Circuit
				form.setField(sheetIndex + "-" + "DC1-04-PERCENT-AVERAGE-HIGH-TEMPERATURE",
						checkValue.Equals(circuit.getCircuitEnvironment(), "UNDERGROUND") ? "30"
								: fourPerCentAverageHigh + "");
				if ((userConnectedEntity != null && userConnectedEntity.getRoleEntity() != null
						&& userConnectedEntity.getRoleEntity().getId() == 2)
						|| (userConnectedEntity != null && userConnectedEntity.getRoleEntity() != null
								&& userConnectedEntity.getRoleEntity().getId() != 2
								&& (!checkValue.NotEquals(circuit.getMapFromUserInput(), false)))) {

					correctedAmpacity = getdc1beforeRevision(form, circuit, permitEntity, dcAmpacityCorrectionB2a,
							sheetIndex);

				} else if (userConnectedEntity != null && userConnectedEntity.getRoleEntity() != null
						&& userConnectedEntity.getRoleEntity().getId() != 2
						&& Boolean.TRUE.equals(circuit.getMapFromUserInput())) {

					correctedAmpacity = getdc1afterRevision(form, circuit, permitEntity, dcAmpacityCorrectionB2a,
							sheetIndex, params);
				}

				if (tempAdderFloat == 22 && checkValue.isNumeric(requiredConductorAmpacity)
						&& checkValue.isNumeric(correctedAmpacity)) {
					Float requiredAmpacityFloat = Float.valueOf(requiredConductorAmpacity);
					Float correctedAmpacityFloat = Float.valueOf(correctedAmpacity);

					if (correctedAmpacityFloat < requiredAmpacityFloat) {
						tempAdder = "0";
						operatingTemperature = checkValue.Equals(circuit.getCircuitEnvironment(), "UNDERGROUND") ? "30"
								: String.valueOf(fourPerCentAverageHigh);
						dcAmpacityCorrectionB2a = checkValue.Equals(circuit.getCircuitEnvironment(), "UNDERGROUND")
								? "1.00"
								: ampCorrection.getDCAmpacityCorrectionB2a(operatingTemperature);
						params.setOperatingTemperatureHigh(fourPerCentAverageHigh > 85);

						if (checkValue.isNumeric(dcAmpacityCorrectionB2a)) {
							params.setNec31016Column90(
									checkValue.isNumeric(iScRef)
											? Float.parseFloat(iScRef) * 1.25 * 1.25
													/ (Float.parseFloat(dcAmpacityCorrectionB2a))
											: 0);
						}

						if ((userConnectedEntity.getRoleEntity().getId() == 2)
								|| (userConnectedEntity.getRoleEntity().getId() != 2
										&& (!checkValue.NotEquals(circuit.getMapFromUserInput(), false)))) {

							getdc1beforeRevision(form, circuit, permitEntity, dcAmpacityCorrectionB2a, sheetIndex);

						} else if (userConnectedEntity.getRoleEntity().getId() != 2
								&& Boolean.TRUE.equals(circuit.getMapFromUserInput())) {

							getdc1afterRevision(form, circuit, permitEntity, dcAmpacityCorrectionB2a, sheetIndex,
									params);
						}

					}
				}
				form.setField(sheetIndex + "-" + "DC1-HEIGHT-ABOVE-ROOF",
						circuitEnvService.getHighAboveRoof(circuit.getCircuitEnvironment(), necCode, state));
				form.setField(sheetIndex + "-" + "DC1-TEMPERATURE-ADDER", tempAdder);
				form.setField(sheetIndex + "-" + "DC1-OPERATING-TEMPERATURE", operatingTemperature);
				form.setField(sheetIndex + "-" + "DC1-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION",
						dcAmpacityCorrectionB2a);
				form.setField(sheetIndex + "-" + "DC1-CONDUIT-TEMPERATURE-DERATING-AMPACITY-CORRECTION1",
						dcAmpacityCorrectionB2a);

			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}

	}

	public String getdc1beforeRevision(AcroFields form, PermitConduitConductorSectionEntity circuit,
			PermitEntity permitEntity, String ampacityCorrectionB2a, int sheetIndex) {
		String correctedAmpacity = "";
		try {

			form.setField(sheetIndex + "-" + "DC1-NUMBER-OF-UNGROUNDED-CONDUCTORS", "2");
			form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "N/A");
			form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "1.0");

			if ((circuit != null && checkValue.Equals(circuit.getConductorTypeExisting(), true))
					|| (permitEntity != null && checkValue.Equals(permitEntity.getExistModule(), true))) {
				form.setField(sheetIndex + "-" + "DC1-TRADE-SIZE", "EXISTING");
			} else if (circuit != null && checkValue.Equals(circuit.getConductorSize(), "EXIST")) {
				form.setField(sheetIndex + "-" + "DC1-TRADE-SIZE", "EXISTING");
			} else {
				form.setField(sheetIndex + "-" + "DC1-TRADE-SIZE", "#12 AWG");
			}

			if ((circuit != null && checkValue.Equals(circuit.getConductorTypeExisting(), true))
					|| (permitEntity != null && checkValue.Equals(permitEntity.getExistModule(), true))) {
				form.setField(sheetIndex + "-" + "DC1-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
			} else {
				form.setField(sheetIndex + "-" + "DC1-CONDUCTOR-AMPACTIY-RATING", "30");
			}

			form.setField(sheetIndex + "-" + "DC1-MATERIAL", "(1) CU");
			form.setField(sheetIndex + "-" + "DC1-CONDUCTOR-AMPACTIY-RATING1", "30");
			if (checkValue.isNumeric(ampacityCorrectionB2a)) {
				form.setField(sheetIndex + "-" + "DC1-CORRECTED-AMPACITY",
						String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(ampacityCorrectionB2a) * 30)));
				form.setField(sheetIndex + "-" + "DC1-CORRECTED-AMPACITY1",
						String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(ampacityCorrectionB2a) * 30)));
				correctedAmpacity = String
						.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(ampacityCorrectionB2a) * 30));
			}

		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return correctedAmpacity;
	}

	public String getdc1afterRevision(AcroFields form, PermitConduitConductorSectionEntity circuit,
			PermitEntity permitEntity, String ampacityCorrectionB2a, int sheetIndex, E002Model params) {

		String correctedAmpacity = "";

		try {
			String conductorSize = "";
			if (circuit != null && checkValue.NotEquals(circuit.getConductorSize(), "")) {
				if (checkValue.Equals(circuit.getConductorSize(), "Other")) {
					form.setField(sheetIndex + "-" + "DC1-TRADE-SIZE", "#" + circuit.getConductorSizeOther() + " AWG");
					conductorSize = circuit.getConductorSizeOther();
				} else if (checkValue.Equals(circuit.getConductorSize(), "Per Manufacturer")) {
					form.setField(sheetIndex + "-" + "DC1-TRADE-SIZE", "#12 AWG");
					conductorSize = "#12 AWG";
				} else {
					form.setField(sheetIndex + "-" + "DC1-TRADE-SIZE", circuit.getConductorSize());
					conductorSize = circuit.getConductorSize();
				}
			}

			if (checkValue.Equals(conductorSize, "#12 AWG")) {
				if (checkValue.Equals(circuit.getConductorQty(), "Other")) {
					form.setField(sheetIndex + "-" + "DC1-NUMBER-OF-UNGROUNDED-CONDUCTORS",
							circuit.getConductorQtyOther() + "");
					form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
							ampCorrection.getDCAmpacityCorrectionB3a(circuit, "PV MODULE",
									circuit.getConductorQtyOther(), 1, false));
					form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", ampCorrection
							.getDCAmpacityCorrectionB3a(circuit, "PV MODULE", circuit.getConductorQtyOther(), 1, true));
					params.setConduitFillDerating(ampCorrection.getDCAmpacityCorrectionB3a(circuit, "PV MODULE",
							circuit.getConductorQtyOther(), 1, true));
				} else if (checkValue.NotEquals(circuit.getConductorQty(), "")) {
					form.setField(sheetIndex + "-" + "DC1-NUMBER-OF-UNGROUNDED-CONDUCTORS", circuit.getConductorQty());
					form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
							ampCorrection.getDCAmpacityCorrectionB3a(circuit, "PV MODULE",
									Integer.parseInt(circuit.getConductorQty()), 1, false));
					form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
							ampCorrection.getDCAmpacityCorrectionB3a(circuit, "PV MODULE",
									Integer.parseInt(circuit.getConductorQty()), 1, true));
					params.setConduitFillDerating(ampCorrection.getDCAmpacityCorrectionB3a(circuit, "PV MODULE",
							Integer.parseInt(circuit.getConductorQty()), 1, true));
				}

				if ((checkValue.Equals(circuit.getConductorTypeExisting(), true))
						|| (permitEntity != null && checkValue.Equals(permitEntity.getExistModule(), true))) {
					form.setField(sheetIndex + "-" + "DC1-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
				} else {
					form.setField(sheetIndex + "-" + "DC1-CONDUCTOR-AMPACTIY-RATING", "30");
				}

				form.setField(sheetIndex + "-" + "DC1-CONDUCTOR-AMPACTIY-RATING1", "30");
				if (checkValue.isNumeric(ampacityCorrectionB2a)) {
					form.setField(sheetIndex + "-" + "DC1-CORRECTED-AMPACITY",
							String.valueOf(
									new DecimalFormat("#.0")
											.format(Float.parseFloat(ampacityCorrectionB2a) * Float.parseFloat("30")
													* Float.parseFloat(ampCorrection.getDCAmpacityCorrectionB3a(circuit,
															"PV MODULE", Integer.parseInt(circuit.getConductorQty()), 1,
															true)))));
					form.setField(sheetIndex + "-" + "DC1-CORRECTED-AMPACITY1",
							String.valueOf(
									new DecimalFormat("#.0")
											.format(Float.parseFloat(ampacityCorrectionB2a) * Float.parseFloat("30")
													* Float.parseFloat(ampCorrection.getDCAmpacityCorrectionB3a(circuit,
															"PV MODULE", Integer.parseInt(circuit.getConductorQty()), 1,
															true)))));
					correctedAmpacity = String.valueOf(new DecimalFormat("#.0")
							.format(Float.parseFloat(ampacityCorrectionB2a) * Float.parseFloat("30")
									* Float.parseFloat(ampCorrection.getDCAmpacityCorrectionB3a(circuit, "PV MODULE",
											Integer.parseInt(circuit.getConductorQty()), 1, true))));
				}

			} else {
				params.setNEC310(nec3106B16Repo.findFirstBytradeSze(conductorSize));
				if (params.getNEC310() != null && params.getNEC310().getNinetyInsulation() != null) {
					form.setField(sheetIndex + "-" + "DC1-MATERIAL",
							"(" + params.getNEC310().getNumberOfConductors() + ") CU");
					if (checkValue.Equals(circuit.getConductorQty(), "Other")) {
						form.setField(sheetIndex + "-" + "DC1-NUMBER-OF-UNGROUNDED-CONDUCTORS",
								circuit.getConductorQtyOther() + "");
						form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								ampCorrection.getDCAmpacityCorrectionB3a(circuit, "PV MODULE",
										circuit.getConductorQtyOther(), params.getNEC310().getNumberOfConductors(),
										false));
						form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								ampCorrection.getDCAmpacityCorrectionB3a(circuit, "PV MODULE",
										circuit.getConductorQtyOther(), params.getNEC310().getNumberOfConductors(),
										true));
						params.setConduitFillDerating(ampCorrection.getDCAmpacityCorrectionB3a(circuit, "PV MODULE",
								circuit.getConductorQtyOther(), params.getNEC310().getNumberOfConductors(), true));
					} else if (checkValue.NotEquals(circuit.getConductorQty(), "")) {
						form.setField(sheetIndex + "-" + "DC1-NUMBER-OF-UNGROUNDED-CONDUCTORS",
								circuit.getConductorQty());
						form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								ampCorrection.getDCAmpacityCorrectionB3a(circuit, "PV MODULE",
										Integer.parseInt(circuit.getConductorQty()),
										params.getNEC310().getNumberOfConductors(), false));
						form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								ampCorrection.getDCAmpacityCorrectionB3a(circuit, "PV MODULE",
										Integer.parseInt(circuit.getConductorQty()),
										params.getNEC310().getNumberOfConductors(), true));
						params.setConduitFillDerating(ampCorrection.getDCAmpacityCorrectionB3a(circuit, "PV MODULE",
								Integer.parseInt(circuit.getConductorQty()), params.getNEC310().getNumberOfConductors(),
								true));
					} else {
						form.setField(sheetIndex + "-" + "DC1-NUMBER-OF-UNGROUNDED-CONDUCTORS", "");
						form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "");
						form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "");
					}
				} else {
					form.setField(sheetIndex + "-" + "DC1-NUMBER-OF-UNGROUNDED-CONDUCTORS", "");
					form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "");
					form.setField(sheetIndex + "-" + "DC1-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "");
				}

				if ((circuit != null && checkValue.Equals(circuit.getConductorTypeExisting(), true))
						|| (permitEntity != null && checkValue.Equals(permitEntity.getExistModule(), true))) {
					form.setField(sheetIndex + "-" + "DC1-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
				} else if (params.getNEC310() != null && params.getNEC310().getNinetyInsulation() != null) {
					form.setField(sheetIndex + "-" + "DC1-CONDUCTOR-AMPACTIY-RATING",
							String.valueOf(params.getNEC310().getNinetyInsulation()));
				}
				if (params.getNEC310() != null && params.getNEC310().getNinetyInsulation() != null) {
					form.setField(sheetIndex + "-" + "DC1-CONDUCTOR-AMPACTIY-RATING1",
							String.valueOf(params.getNEC310().getNinetyInsulation()));
					if (checkValue.isNumeric(ampacityCorrectionB2a)) {
						form.setField(sheetIndex + "-" + "DC1-CORRECTED-AMPACITY",
								String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(ampacityCorrectionB2a)
										* Float.parseFloat(String.valueOf(params.getNEC310().getNinetyInsulation())
												.replace(',', '.'))
										* Float.parseFloat(ampCorrection.getDCAmpacityCorrectionB3a(circuit,
												"PV MODULE", Integer.parseInt(circuit.getConductorQty()),
												params.getNEC310().getNumberOfConductors(), true)))));
						form.setField(sheetIndex + "-" + "DC1-CORRECTED-AMPACITY1",
								String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(ampacityCorrectionB2a)
										* Float.parseFloat(String.valueOf(params.getNEC310().getNinetyInsulation())
												.replace(',', '.'))
										* Float.parseFloat(ampCorrection.getDCAmpacityCorrectionB3a(circuit,
												"PV MODULE", Integer.parseInt(circuit.getConductorQty()),
												params.getNEC310().getNumberOfConductors(), true)))));
						correctedAmpacity = String
								.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(ampacityCorrectionB2a)
										* Float.parseFloat(String.valueOf(params.getNEC310().getNinetyInsulation())
												.replace(',', '.'))
										* Float.parseFloat(ampCorrection.getDCAmpacityCorrectionB3a(circuit,
												"PV MODULE", Integer.parseInt(circuit.getConductorQty()),
												params.getNEC310().getNumberOfConductors(), true))));
					}
				} else {
					form.setField(sheetIndex + "-" + "DC1-CONDUCTOR-AMPACTIY-RATING1", "");
					form.setField(sheetIndex + "-" + "DC1-CORRECTED-AMPACITY", "");
					form.setField(sheetIndex + "-" + "DC1-CORRECTED-AMPACITY1", "");
				}

			}

		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return correctedAmpacity;
	}

	public String getOperatingTemperature(Integer fourPerCentAverageHigh, String tempAdder) {
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
