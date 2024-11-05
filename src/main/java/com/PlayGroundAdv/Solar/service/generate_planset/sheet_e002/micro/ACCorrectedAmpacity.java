package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.micro;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSCircuitSpec;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit.ACAmpacityCorrection;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ACCorrectedAmpacity {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblemMsg;
	final NEC3106B16Repository nec3106B16Repo;
	final ACAmpacityCorrection acAmpacityCorrection;

	public ACCorrectedAmpacity(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblemMsg,
			NEC3106B16Repository nec3106b16Repo, ACAmpacityCorrection acAmpacityCorrection) {
		super();
		this.checkValue = checkValue;
		this.technicalProblemMsg = technicalProblemMsg;
		nec3106B16Repo = nec3106b16Repo;
		this.acAmpacityCorrection = acAmpacityCorrection;
	}

	static final String EXISTING_SUB_PANEL = "Show the word “Existing” instead of a conductor size (Some AHJs may not approve the permit with this wording)";
	static final String CHOOSE_CONDUCTOR_SIZE = "I want to choose the conductor size";

	public void mapByLogic(AcroFields form, ESSConnectors ac, int i, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			int microNumberOfStrings, List<String> acTradeSize, List<Integer> acNumberOfConductors,
			List<String> acCircuitEnvironment, String conduitRun, Integer numberOfConductor, int sheetIndex,
			PermitHomeSiteInfoEntity permitHomeSite, int indexAcCombiner, E002Model params, Boolean existing,
			Boolean repeatingACMapping) {
		try {
			params.setCorrectedAmpacity("");

			//// ******* NUMBER OF UNGROUNDED CONDUCTORS *******////
			Integer conductorQty = numberUndergroundConductor(form, ac, permitHomeSite, i, sheetIndex,
					microNumberOfStrings, indexAcCombiner);

			if (ac.getSourceID().contains("ACJBOX")
					&& checkValue.Equals(permitProjectSiteInfo.getConnectionPoint(), "Existing")
					&& checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), CHOOSE_CONDUCTOR_SIZE)) {
				if (checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "EXIST")) {
					mapExisting(form, indexAcCombiner, sheetIndex, acTradeSize);
				} else {

					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE",
							checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")
									? permitProjectSiteInfo.getSubPanelConductorSizeOther()
									: permitProjectSiteInfo.getSubPanelConductorSize());
					acTradeSize.add(checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")
							? permitProjectSiteInfo.getSubPanelConductorSizeOther()
							: permitProjectSiteInfo.getSubPanelConductorSize());
					params.setNEC310(nec3106B16Repo.findFirstBytradeSze(
							checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")
									? permitProjectSiteInfo.getSubPanelConductorSizeOther()
									: permitProjectSiteInfo.getSubPanelConductorSize()));
					if (params.getNEC310() != null) {
						form.setField(sheetIndex + "-AC" + i + "-MATERIAL",
								"(" + params.getNEC310().getNumberOfConductors() + ") CU");
						acNumberOfConductors.add(params.getNEC310().getNumberOfConductors());

						form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								checkValue.Equals(ac.getCircuitSpec().getCircuitEnvironment(), "ATTIC")
										&& checkValue.Equals(conduitRun, "Romex in Attic") ? "N/A"
												: acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(),
														conductorQty, numberOfConductor, false, false));
						form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								checkValue.Equals(ac.getCircuitSpec().getCircuitEnvironment(), "ATTIC")
										&& checkValue.Equals(conduitRun, "Romex in Attic") ? "1.0"
												: acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(),
														conductorQty, numberOfConductor, true, false));
						params.setConduitFillDerating(
								checkValue.Equals(ac.getCircuitSpec().getCircuitEnvironment(), "ATTIC")
										&& checkValue.Equals(conduitRun, "Romex in Attic") ? "1"
												: acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(),
														conductorQty, numberOfConductor, true, false));

						if (params.getNEC310().getSeventyFiveInsulation() != null) {
							form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING",
									String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
							form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
									String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
							try {
								if (checkValue.NotEquals(params.getConduitFillDerating(), "N/A")
										&& checkValue.NotEquals(params.getTempDerating(), "")) {
									form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY",
											String.valueOf(new DecimalFormat("#.0")
													.format(Float.parseFloat(params.getTempDerating())
															* Float.parseFloat(String
																	.valueOf(String.valueOf(params.getNEC310()
																			.getSeventyFiveInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getConduitFillDerating()))));
									form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1",
											String.valueOf(new DecimalFormat("#.0")
													.format(Float.parseFloat(params.getTempDerating())
															* Float.parseFloat(String
																	.valueOf(String.valueOf(params.getNEC310()
																			.getSeventyFiveInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getConduitFillDerating()))));
									params.setCorrectedACAmpacity(
											String.valueOf(new DecimalFormat("#.0")
													.format(Float.parseFloat(params.getTempDerating())
															* Float.parseFloat(String
																	.valueOf(String.valueOf(params.getNEC310()
																			.getSeventyFiveInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getConduitFillDerating()))));
								} else if (checkValue.NotEquals(params.getTempDerating(), "")) {
									form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY",
											String.valueOf(new DecimalFormat("#.0").format(
													Float.parseFloat(params.getTempDerating()) * Float.parseFloat(String
															.valueOf(String.valueOf(
																	params.getNEC310().getSeventyFiveInsulation()))
															.replace(',', '.')))));
									form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1",
											String.valueOf(new DecimalFormat("#.0").format(
													Float.parseFloat(params.getTempDerating()) * Float.parseFloat(String
															.valueOf(String.valueOf(
																	params.getNEC310().getSeventyFiveInsulation()))
															.replace(',', '.')))));
									params.setCorrectedACAmpacity(String.valueOf(new DecimalFormat("#.0")
											.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(String
													.valueOf(String
															.valueOf(params.getNEC310().getSeventyFiveInsulation()))
													.replace(',', '.')))));
								}
							} catch (Exception e) {
								e.printStackTrace();
							}

						}

					} else {
						acNumberOfConductors.add(1);
						form.setField(sheetIndex + "-AC" + i + "-MATERIAL", "CU");
					}

				}
			} else if (ac.getSourceID().contains("INV")) {
				if (Boolean.TRUE.equals(existing)) {
					mapExisting(form, indexAcCombiner, sheetIndex, acTradeSize);
				} else {
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "#12 AWG");
					form.setField(sheetIndex + "-AC" + i + "-MATERIAL", "(1) CU");
					acNumberOfConductors.add(1);
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "25");
					// 05-30-2019:A.B: CONDUCTOR AMPACTIY RATING for TRADE SIZE #12 AWG equals 25
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "25");
					form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "N/A");
					form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "1.0");
					// 05-30-2019:A.B: ConduitFillDerating = 1 was removed for the equation
					form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY", String
							.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * 25)));
					form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1", String
							.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * 25)));
					params.setConduitFillDerating("1");
					acTradeSize.add("#12 AWG");
					params.setCorrectedACAmpacity(String
							.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * 25)));
				}
			} else {

				if (Boolean.TRUE.equals(existing)) {
					mapExisting(form, indexAcCombiner, sheetIndex, acTradeSize);
				} else {

					form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
							checkValue.Equals(ac.getCircuitSpec().getCircuitEnvironment(), "ATTIC")
									&& checkValue.Equals(conduitRun, "Romex in Attic") ? "N/A"
											: acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(),
													conductorQty, numberOfConductor, false, false));
					form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
							checkValue.Equals(ac.getCircuitSpec().getCircuitEnvironment(), "ATTIC")
									&& checkValue.Equals(conduitRun, "Romex in Attic") ? "1.0"
											: acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(),
													conductorQty, numberOfConductor, true, false));
					params.setConduitFillDerating(
							checkValue.Equals(ac.getCircuitSpec().getCircuitEnvironment(), "ATTIC")
									&& checkValue.Equals(conduitRun, "Romex in Attic") ? "1"
											: acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(),
													conductorQty, numberOfConductor, true, false));

					params.setNec31016Column90(
							params.getRequiredAmpacity() / (Float.parseFloat(params.getTempDerating())
									* Float.parseFloat(params.getConduitFillDerating())));

					tradeSize(params);
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", params.getNEC310().getTradeSze());
					params.getIncorrectTradeSize().add(params.getNEC310().getTradeSze());
					acTradeSize.add(params.getNEC310().getTradeSze());

					if (params.getNEC310() != null) {
						form.setField(sheetIndex + "-AC" + i + "-MATERIAL",
								"(" + params.getNEC310().getNumberOfConductors() + ") CU");
						params.setCalculatedNumberOfConductor(params.getNEC310().getNumberOfConductors());
						acNumberOfConductors.add(params.getNEC310().getNumberOfConductors());
					} else {
						acNumberOfConductors.add(1);
						params.setCalculatedNumberOfConductor(1);
						form.setField(sheetIndex + "-AC" + i + "-MATERIAL", "(1) CU");
					}

					if (params.getNEC310() != null && params.getNEC310().getSeventyFiveInsulation() != null) {
						form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING",
								String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
						form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
								String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
						try {
							if (checkValue.NotEquals(params.getConduitFillDerating(), "N/A")) {
								form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY",
										String.valueOf(
												new DecimalFormat("#.0")
														.format(Float.parseFloat(params.getTempDerating())
																* Float.parseFloat(String
																		.valueOf(String.valueOf(params.getNEC310()
																				.getSeventyFiveInsulation()))
																		.replace(',', '.'))
																* Float.parseFloat(params.getConduitFillDerating()))));
								form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1",
										String.valueOf(
												new DecimalFormat("#.0")
														.format(Float.parseFloat(params.getTempDerating())
																* Float.parseFloat(String
																		.valueOf(String.valueOf(params.getNEC310()
																				.getSeventyFiveInsulation()))
																		.replace(',', '.'))
																* Float.parseFloat(params.getConduitFillDerating()))));
								params.setCorrectedACAmpacity(
										String.valueOf(
												new DecimalFormat("#.0")
														.format(Float.parseFloat(params.getTempDerating())
																* Float.parseFloat(String
																		.valueOf(String.valueOf(params.getNEC310()
																				.getSeventyFiveInsulation()))
																		.replace(',', '.'))
																* Float.parseFloat(params.getConduitFillDerating()))));
							} else {
								form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY",
										String.valueOf(new DecimalFormat("#.0").format(
												Float.parseFloat(params.getTempDerating()) * Float.parseFloat(String
														.valueOf(String
																.valueOf(params.getNEC310().getSeventyFiveInsulation()))
														.replace(',', '.')))));
								form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1",
										String.valueOf(new DecimalFormat("#.0").format(
												Float.parseFloat(params.getTempDerating()) * Float.parseFloat(String
														.valueOf(String
																.valueOf(params.getNEC310().getSeventyFiveInsulation()))
														.replace(',', '.')))));
								params.setCorrectedACAmpacity(String.valueOf(new DecimalFormat("#.0")
										.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(String
												.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation()))
												.replace(',', '.')))));
							}
						} catch (Exception e) {

							e.printStackTrace();
						}

					}
				}
			}

			if (!checkValue.Equals(numberOfConductor, params.getCalculatedNumberOfConductor())
					&& Boolean.FALSE.equals(repeatingACMapping)) {
				acTradeSize.remove(acTradeSize.size() - 1);
				mapByLogic(form, ac, i, permitProjectSiteInfo, microNumberOfStrings, acTradeSize, acNumberOfConductors,
						acCircuitEnvironment, conduitRun, params.getCalculatedNumberOfConductor(), sheetIndex,
						permitHomeSite, indexAcCombiner, params, existing, true);
			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);

		}
	}

	private void tradeSize(E002Model params) {
		try {

			Integer nec310 = 0;
			if (checkValue.isStringInt(params.getNec31016Column90() + "")) {
				nec310 = Integer.parseInt(params.getNec31016Column90() + "");
			} else if (!checkValue.isStringInt(params.getNec31016Column90() + "")
					&& checkValue.isNumeric(params.getNec31016Column90() + "")) {
				nec310 = (int) Math.round(params.getNec31016Column90());
			}
			if (params.getRequiredAmpacity() > 30) {
				String tradeSizeValue = "";
				if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
						&& !params.getIncorrectTradeSize().isEmpty()) {
					tradeSizeValue = nec3106B16Repo.findTradeSizeBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310,
							"#12 AWG", params.getIncorrectTradeSize());// Set Max Result
				} else {
					tradeSizeValue = nec3106B16Repo.findTradeSizeBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG");// Set
				}

				if (checkValue.contains(tradeSizeValue, "AWG") && checkValue.notContains(tradeSizeValue, "/")) {
					Integer tradeNumber = Integer.parseInt(tradeSizeValue.split("\\s+")[0].split("#")[1]);
					// CR-2973 - Minimum 8 AWG Conductors on 30 Amp OCPD
					if (tradeNumber > 8) {
						if (Boolean.FALSE.equals(params.getIncorrectTradeSizeLogic())
								|| (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
										&& !params.getIncorrectTradeSize().isEmpty()
										&& !params.getIncorrectTradeSize().contains("#8 AWG"))) {
							params.setNEC310(nec3106B16Repo.findFirstBytradeSze("#8 AWG"));

						} else if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
								&& !params.getIncorrectTradeSize().isEmpty()) {
							params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310,
									"#12 AWG", params.getIncorrectTradeSize()));
						} else {
							params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG"));
						}
					} else {
						if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
								&& !params.getIncorrectTradeSize().isEmpty()) {
							params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310,
									"#12 AWG", params.getIncorrectTradeSize()));
						} else {
							params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG"));
						}
					}
				} else {
					if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
							&& !params.getIncorrectTradeSize().isEmpty()) {
						params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310,
								"#12 AWG", params.getIncorrectTradeSize()));
					} else {
						params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG"));
					}
				}
			} else {
				if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic())
						&& !params.getIncorrectTradeSize().isEmpty()) {
					params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSizeIsNotIn(nec310, "#12 AWG",
							params.getIncorrectTradeSize()));
				} else {
					params.setNEC310(nec3106B16Repo.findBySeventyFiveInsulationAndTradeSize(nec310, "#12 AWG"));
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);

		}
	}

	private void mapExisting(AcroFields form, int i, int sheetIndex, List<String> acTradeSize) {
		try {
			form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
			form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
			form.setField(sheetIndex + "-AC" + i + "- ", "EXISTING");
			form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "EXISTING");
			form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
			form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY", "EXISTING");
			form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1", "EXISTING");
			acTradeSize.add("EXISTING");
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public Integer numberUndergroundConductor(AcroFields form, ESSConnectors ac,
			PermitHomeSiteInfoEntity permitHomeSite, int i, int sheetIndex, int microNumberOfStrings,
			int indexAcCombiner) {
		try {
//			S.B CR-3119-MOD-004 Number of Conductors on AC circuits on E-002
//			A.B 08-05-2021 CR-PM-Revision-3119-MOD-005 Number of Conductors on AC circuits on E-002
			Integer branchCircuit = indexAcCombiner != -1 && indexAcCombiner <= i ? 1 : microNumberOfStrings;
			if (ac.getSourceID().contains("INV")) {

				form.setField(sheetIndex + "-AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", "2");
				return 2;

			} else if (checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true)
					&& checkValue.notChecked(ac.getCircuitSpec().getConductorNeutral())) {

				form.setField(sheetIndex + "-AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						String.valueOf(2 * branchCircuit));
				return 2 * branchCircuit;

			} else if (checkValue.Equals(permitHomeSite.getIfServiceVoltage(), true) == checkValue
					.Equals(ac.getCircuitSpec().getConductorNeutral(), true)) {

				form.setField(sheetIndex + "-AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						String.valueOf(3 * branchCircuit));
				return 3 * branchCircuit;

			} else if (checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
					&& checkValue.Equals(ac.getCircuitSpec().getConductorNeutral(), true)) {

				form.setField(sheetIndex + "-AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						String.valueOf(4 * branchCircuit));
				return 4 * branchCircuit;

			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);

		}
		return 2;
	}

	public void mapFromPortal(AcroFields form, int i, ESSCircuitSpec circuitSpec, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, int sheetIndex, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			E002Model params, Boolean isSubPanel) {
		try {

			if (Boolean.TRUE.equals(isSubPanel)) {

				if (checkValue.Equals(permitProjectSiteInfo.getConnectionPoint(), "Existing") && checkValue
						.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), CHOOSE_CONDUCTOR_SIZE)) {

					if (checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")) {
						form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE",
								permitProjectSiteInfo.getSubPanelConductorSizeOther());
						params.setConductorSize(permitProjectSiteInfo.getSubPanelConductorSizeOther());
						acTradeSize.add(permitProjectSiteInfo.getSubPanelConductorSizeOther());

					} else if (checkValue.Equals(circuitSpec.getConductorSize(), "Per Manufacturer")) {
						form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "#12 AWG");
						params.setConductorSize("#12 AWG");
						acTradeSize.add("#12 AWG");
					} else {
						if (checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "EXIST")) {
							form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
							params.setConductorSize("EXISTING");
							acTradeSize.add("EXISTING");
						} else {
							form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE",
									permitProjectSiteInfo.getSubPanelConductorSize());
							params.setConductorSize(permitProjectSiteInfo.getSubPanelConductorSize());
							acTradeSize.add(permitProjectSiteInfo.getSubPanelConductorSize());
						}

					}

				} else if (circuitSpec != null) {
					if (checkValue.Equals(circuitSpec.getConductorSize(), "Other")) {
						form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE",
								"#" + circuitSpec.getConductorSizeOther() + " AWG");
						params.setConductorSize(circuitSpec.getConductorSizeOther());
						acTradeSize.add("#" + circuitSpec.getConductorSizeOther() + " AWG");
					} else if (checkValue.Equals(circuitSpec.getConductorSize(), "Per Manufacturer")) {
						form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "#12 AWG");
						params.setConductorSize("#12 AWG");
						acTradeSize.add(i, "#12 AWG");
					} else {
						if (checkValue.Equals(circuitSpec.getConductorSize(), "EXIST")) {
							form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
							params.setConductorSize("EXISTING");
							acTradeSize.add("EXISTING");
						} else {
							form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", circuitSpec.getConductorSize());
							params.setConductorSize(circuitSpec.getConductorSize());
							acTradeSize.add(circuitSpec.getConductorSize());
						}

					}
				}
			} else if (circuitSpec.getConductorSize() != null) {
				if (checkValue.Equals(circuitSpec.getConductorSize(), "Other")) {
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE",
							"#" + circuitSpec.getConductorSizeOther() + " AWG");
					params.setConductorSize(circuitSpec.getConductorSizeOther());
					acTradeSize.add(i, "#" + circuitSpec.getConductorSizeOther() + " AWG");
				} else if (checkValue.Equals(circuitSpec.getConductorSize(), "Per Manufacturer")) {
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "#12 AWG");
					params.setConductorSize("12");
					acTradeSize.add("#12 AWG");
				} else if (checkValue.Equals(circuitSpec.getConductorSize(), "EXIST")) {
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", "EXISTING");
					params.setConductorSize("EXISTING");
					acTradeSize.add("EXISTING");
				} else {
					form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", circuitSpec.getConductorSize());
					params.setConductorSize(circuitSpec.getConductorSize());
					acTradeSize.add(circuitSpec.getConductorSize());
				}
			}

			if (checkValue.Equals(params.getConductorSize(), "#12 AWG")) {
				form.setField(sheetIndex + "-AC" + i + "-MATERIAL", "(1) CU");
				acNumberOfConductors.add(1);
				if (checkValue.Equals(circuitSpec.getConductorQty(), "Other")) {
					form.setField(sheetIndex + "-AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
							circuitSpec.getConductorQtyOther() + "");
					form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
							acAmpacityCorrection.acAmpacityCorrectionB3a(circuitSpec,
									circuitSpec.getConductorQtyOther(), 1, false, true));
					form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
							acAmpacityCorrection.acAmpacityCorrectionB3a(circuitSpec,
									circuitSpec.getConductorQtyOther(), 1, true, true));
				} else if (checkValue.isNumeric(circuitSpec.getConductorQty())) {
					form.setField(sheetIndex + "-AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
							circuitSpec.getConductorQty());
					form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
							acAmpacityCorrection.acAmpacityCorrectionB3a(circuitSpec,
									Integer.parseInt(circuitSpec.getConductorQty()), 1, false, true));
					form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
							acAmpacityCorrection.acAmpacityCorrectionB3a(circuitSpec,
									Integer.parseInt(circuitSpec.getConductorQty()), 1, true, true));
				}

				form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "25");
				form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "25");
				form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY", String
						.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * 25)));
				form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1", String
						.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * 25)));
			} else {
				/// Get NEC_310_16_B_16///
				params.setNEC310(nec3106B16Repo.findFirstBytradeSze(params.getConductorSize()));
				if (params.getNEC310() != null) {
					form.setField(sheetIndex + "-AC" + i + "-MATERIAL",
							"(" + params.getNEC310().getNumberOfConductors() + ") CU");
					acNumberOfConductors.add(params.getNEC310().getNumberOfConductors());
				} else {
					acNumberOfConductors.add(1);
				}
				if (checkValue.Equals(circuitSpec.getConductorQty(), "Other")) {
					form.setField(sheetIndex + "-AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
							circuitSpec.getConductorQtyOther() + "");
					if (params.getNEC310() != null) {
						form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								acAmpacityCorrection.acAmpacityCorrectionB3a(circuitSpec,
										circuitSpec.getConductorQtyOther(), params.getNEC310().getNumberOfConductors(),
										false, true));
						form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								acAmpacityCorrection.acAmpacityCorrectionB3a(circuitSpec,
										circuitSpec.getConductorQtyOther(), params.getNEC310().getNumberOfConductors(),
										true, true));
					}

				} else if (checkValue.isNumeric(circuitSpec.getConductorQty())) {
					form.setField(sheetIndex + "-AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
							circuitSpec.getConductorQty());
					if (params.getNEC310() != null) {
						form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								acAmpacityCorrection.acAmpacityCorrectionB3a(circuitSpec,
										Integer.parseInt(circuitSpec.getConductorQty()),
										params.getNEC310().getNumberOfConductors(), false, true));
						form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								acAmpacityCorrection.acAmpacityCorrectionB3a(circuitSpec,
										Integer.parseInt(circuitSpec.getConductorQty()),
										params.getNEC310().getNumberOfConductors(), true, true));
					}
				} else {
					form.setField(sheetIndex + "-AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", "");
					form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "");
					form.setField(sheetIndex + "-AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "");
				}

				params.setNEC310(nec3106B16Repo.findFirstBytradeSze(params.getConductorSize()));
				if (params.getNEC310() != null && params.getNEC310().getSeventyFiveInsulation() != null) {
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
							String.valueOf(params.getNEC310().getSeventyFiveInsulation()));
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING",
							String.valueOf(params.getNEC310().getSeventyFiveInsulation()));
					form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY", String.valueOf(new DecimalFormat(
							"#.0")
							.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
									String.valueOf(params.getNEC310().getSeventyFiveInsulation()).replace(',', '.')))));
					form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1", String.valueOf(new DecimalFormat(
							"#.0")
							.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
									String.valueOf(params.getNEC310().getSeventyFiveInsulation()).replace(',', '.')))));
				} else {
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "");
					form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "");
					form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY", "");
					form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1", "");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void correctedAmpacityMapping(AcroFields form, int i, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, int sheetIndex, E002Model params) {
		try {

			form.setField(sheetIndex + "-AC" + i + "-TRADE-SIZE", params.getNEC310().getTradeSze());
			acTradeSize.add(params.getNEC310().getTradeSze());

			form.setField(sheetIndex + "-AC" + i + "-MATERIAL",
					"(" + params.getNEC310().getNumberOfConductors() + ") CU");

			acNumberOfConductors.add(params.getNEC310().getNumberOfConductors());

			if (params.getNEC310().getSeventyFiveInsulation() != null) {
				form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING",
						String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
				form.setField(sheetIndex + "-AC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
						String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
				try {
					if (checkValue.NotEquals(params.getConduitFillDerating(), "N/A")
							&& checkValue.isStringNotEmpty(params.getConduitFillDerating())
							&& checkValue.NotEquals(params.getTempDerating(), "")) {

						form.setField(
								sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY", String
										.valueOf(
												new DecimalFormat("#.0")
														.format(Float.parseFloat(params.getTempDerating())
																* Float.parseFloat(String
																		.valueOf(String.valueOf(params.getNEC310()
																				.getSeventyFiveInsulation()))
																		.replace(',', '.'))
																* Float.parseFloat(params.getConduitFillDerating()))));
						form.setField(
								sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1", String
										.valueOf(
												new DecimalFormat("#.0")
														.format(Float.parseFloat(params.getTempDerating())
																* Float.parseFloat(String
																		.valueOf(String.valueOf(params.getNEC310()
																				.getSeventyFiveInsulation()))
																		.replace(',', '.'))
																* Float.parseFloat(params.getConduitFillDerating()))));
						params.setCorrectedACAmpacity(
								String.valueOf(
										new DecimalFormat(
												"#.0").format(
														Float.parseFloat(params.getTempDerating())
																* Float.parseFloat(String
																		.valueOf(String.valueOf(params.getNEC310()
																				.getSeventyFiveInsulation()))
																		.replace(',', '.'))
																* Float.parseFloat(params.getConduitFillDerating()))));
					} else if (checkValue.NotEquals(params.getTempDerating(), "")) {
						form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY", String.valueOf(new DecimalFormat(
								"#.0")
								.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
										String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation()))
												.replace(',', '.')))));
						form.setField(sheetIndex + "-AC" + i + "-CORRECTED-AMPACITY1", String.valueOf(new DecimalFormat(
								"#.0")
								.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
										String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation()))
												.replace(',', '.')))));
						params.setCorrectedACAmpacity(String.valueOf(new DecimalFormat("#.0")
								.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
										String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation()))
												.replace(',', '.')))));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}
}
