package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.string.dc_circuit;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSCircuitSpec;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class DCConduitFillDerating {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	final DCCorrectedAmpacity correctedAmpacityCalculation;
	final NEC3106B16Repository nec3106B16Repo;

	public DCConduitFillDerating(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem,
			DCCorrectedAmpacity correctedAmpacityCalculation, NEC3106B16Repository nec3106B16Repo) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
		this.correctedAmpacityCalculation = correctedAmpacityCalculation;
		this.nec3106B16Repo = nec3106B16Repo;
	}

	public E002Model mapFillDeratingVoltageDrop(AcroFields form, int i, List<String> dcTradeSize,
			List<Integer> dcNumberOfConductors, int sheetIndex, E002Model params) {

		try {
			if (params != null) {
				params.setCorrectedAmpacity("");
				params.setTradeSizeRepeate(params.getNEC310().getTradeSze());
				form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL",
						"(" + params.getNEC310().getNumberOfConductors() + ") CU");
				params.setCalculatedNumberOfConductor(params.getNEC310().getNumberOfConductors());
				dcNumberOfConductors.add(params.getNEC310().getNumberOfConductors());

				form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", params.getNEC310().getTradeSze());
				List<String> incorrectTradeSize = params.getIncorrectTradeSize();
				incorrectTradeSize.add(params.getNEC310().getTradeSze());
				params.setIncorrectTradeSize(incorrectTradeSize);
				dcTradeSize.add(params.getNEC310().getTradeSze());

				if (params.getNEC310() != null && params.getNEC310().getNinetyInsulation() != null) {
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
							String.valueOf(String.valueOf(params.getNEC310().getNinetyInsulation())));
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING",
							String.valueOf(String.valueOf(params.getNEC310().getNinetyInsulation())));
					form.setField(
							sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY", String
									.valueOf(
											new DecimalFormat("##.##").format(
													Float.parseFloat(params.getAmpacityCorrection().replace(',', '.'))
															* Float.parseFloat(String
																	.valueOf(String.valueOf(
																			params.getNEC310().getNinetyInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
					form.setField(
							sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY1", String
									.valueOf(
											new DecimalFormat("##.##").format(
													Float.parseFloat(params.getAmpacityCorrection().replace(',', '.'))
															* Float.parseFloat(String
																	.valueOf(String.valueOf(
																			params.getNEC310().getNinetyInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
					params.setCorrectedAmpacity(
							String.valueOf(
									new DecimalFormat(
											"##.##").format(
													Float.parseFloat(params.getAmpacityCorrection().replace(',', '.'))
															* Float.parseFloat(String
																	.valueOf(String.valueOf(
																			params.getNEC310().getNinetyInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
				}
			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;

	}

	public E002Model mapFillDeratingafterRevision(AcroFields form, int i, ESSCircuitSpec circuitSpec,
			List<String> dcTradeSize, List<Integer> dcNumberOfConductors, int sheetIndex, E002Model params) {

		try {
			params.setCorrectedAmpacity("");
			if (circuitSpec != null) {
				String condQty = checkValue.Equals(circuitSpec.getConductorQty(), "Other")
						? circuitSpec.getConductorQtyOther() + ""
						: circuitSpec.getConductorQty();
				params.setDcampacityCorrectionB3a(
						dcAmpacityCorrectionB3a(circuitSpec.getConductorSize(), condQty, true, true));
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", condQty);
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
						params.getDcampacityCorrectionB3a());
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
						params.getDcampacityCorrectionB3a());

				if (circuitSpec.getConductorSize() != null) {
					if (checkValue.Equals(circuitSpec.getConductorSize(), "Other")) {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE",
								"#" + circuitSpec.getConductorSizeOther() + " AWG");
						params.setConductorSize(circuitSpec.getConductorSizeOther());
						dcTradeSize.add("#" + circuitSpec.getConductorSizeOther() + " AWG");
					} else if (checkValue.Equals(circuitSpec.getConductorSize(), "Per Manufacturer")) {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "#12 AWG");
						params.setConductorSize("#12 AWG");
						dcTradeSize.add("#12 AWG");
					} else {
						if (checkValue.EqualsCaseInsensitive(circuitSpec.getConductorSize(), "EXIST")) {
							form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
							params.setConductorSize("EXISTING");
							dcTradeSize.add("EXISTING");
						} else {
							form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", circuitSpec.getConductorSize());
							params.setConductorSize(circuitSpec.getConductorSize());
							dcTradeSize.add(circuitSpec.getConductorSize());
						}

					}
				}

				params = correctedAmpacityCalculation.ampacityMapping(form, i, params, dcNumberOfConductors,
						sheetIndex);
			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}

		return params;

	}

	public String dcAmpacityCorrectionB3a(String conduitSize, String numberOfConductorString, Boolean calculating,
			Boolean mapAfterRevision) {
		try {
			if (Boolean.TRUE.equals(mapAfterRevision) && checkValue.Equals(conduitSize, "N/A")) {
				return Boolean.TRUE.equals(calculating) ? "1" : "N/A";
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return getCorrectionB3a(numberOfConductorString);
	}

	public String getDCAmpacityCorrectionB3a(PermitConduitConductorSectionEntity circuit, String origin,
			String numberOfConductorString, Boolean calculating, Boolean mapAfterRevision) {

		try {
			if (circuit != null) {
				if (checkValue.Equals(origin, "PV MODULE")) {
					if (mapAfterRevision && checkValue.Equals(circuit.getConduitSize(), "N/A")) {
						return calculating ? "1" : "N/A";
					} else if (circuit.getConduitType() != null) {
						return getCorrectionB3a(numberOfConductorString);
					}

				} else if (checkValue.Equals(origin, "DC/DC CONVERTER")) {
					if (mapAfterRevision && checkValue.Equals(circuit.getConduitSizeTwo(), "N/A")) {
						return calculating ? "1" : "N/A";
					} else if (circuit.getConduitTypeTwo() != null) {
						return getCorrectionB3a(numberOfConductorString);
					}

				} else if (checkValue.Equals(origin, "JUNCTION BOX")) {
					if (mapAfterRevision && checkValue.Equals(circuit.getConduitSizeThree(), "N/A")) {
						return calculating ? "1" : calculating ? "1" : "N/A";
					} else if (circuit.getConduitTypeThree() != null) {
						return getCorrectionB3a(numberOfConductorString);
					}

				} else if (checkValue.Equals(origin, "DC COMBINER")) {
					if (mapAfterRevision && checkValue.Equals(circuit.getConduitSizeFour(), "N/A")) {
						return calculating ? "1" : "N/A";
					} else if (circuit.getConduitTypeFour() != null) {
						return getCorrectionB3a(numberOfConductorString);
					}

				} else if (checkValue.Equals(origin, "DC DISCONNECT")) {
					if (mapAfterRevision && checkValue.Equals(circuit.getConduitSizeFive(), "N/A")) {
						return calculating ? "1" : "N/A";
					} else if (circuit.getConduitTypeFive() != null) {
						return getCorrectionB3a(numberOfConductorString);
					}

				}
			}
			return getCorrectionB3a(numberOfConductorString);
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return getCorrectionB3a(numberOfConductorString);
		}

	}

	public E002Model mapFillDeratingbeforeRevision(Boolean stepOptimizer, AcroFields form, int i,
			List<ESSConnectors> dcList, int numberOfStrings, int inverterQty, int originCombiner,
			List<String> dcTradeSize, List<Integer> dcNumberOfConductors, Integer numberOfConductor, int sheetIndex,
			E002Model params) {

		params.setCorrectedAmpacity("");

		try {

			if (checkValue.Equals(stepOptimizer, true) && i == 1) {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", String.valueOf(2));
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "N/A");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "1.0");
				params.setDcampacityCorrectionB3a("1");

			} else if (originCombiner > 0 && i >= originCombiner) {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						String.valueOf(2 * inverterQty * numberOfConductor));

				if (dcList.size() > i - 1 && checkValue
						.Equals(dcAmpacityCorrectionB3a(dcList.get(i - 1).getCircuitSpec().getConduitSize(),
								String.valueOf(2 * inverterQty * numberOfConductor), false, false), "N/A")) {
					params.setDcampacityCorrectionB3a("1");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "N/A");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "1.0");
				} else {
					if (dcList.size() > i - 1) {
						params.setDcampacityCorrectionB3a(
								dcAmpacityCorrectionB3a(dcList.get(i - 1).getCircuitSpec().getConduitSize(),
										String.valueOf(2 * inverterQty), true, false));
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								params.getDcampacityCorrectionB3a());
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								params.getDcampacityCorrectionB3a());
					}
				}

			} else {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						String.valueOf(2 * numberOfStrings * numberOfConductor));
				if ((checkValue.Equals(stepOptimizer, true) && i == 2) || i == 1) {

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "N/A");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "1.0");
					params.setDcampacityCorrectionB3a("1");

				} else {
					if (checkValue.Equals(dcAmpacityCorrectionB3a(dcList.get(i - 1).getCircuitSpec().getConduitSize(),
							String.valueOf(2 * numberOfStrings * numberOfConductor), false, false), "N/A")) {
						params.setDcampacityCorrectionB3a("1");
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								"N/A");
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								"1.0");
					} else {
						params.setDcampacityCorrectionB3a(
								dcAmpacityCorrectionB3a(dcList.get(i - 1).getCircuitSpec().getConduitSize(),
										String.valueOf(2 * numberOfStrings * numberOfConductor), true, false));
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								params.getDcampacityCorrectionB3a());
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								params.getDcampacityCorrectionB3a());
					}
				}

			}
			// CI :24/06/2019 : CR 2742
			double nec31016Column90 = params.getRequiredAmpacity() / (Float.parseFloat(params.getAmpacityCorrection())
					* Float.parseFloat(params.getDcampacityCorrectionB3a()));

			Integer nec310Col90 = 0;
			if (checkValue.isStringInt(nec31016Column90 + "")) {
				nec310Col90 = Integer.parseInt(nec31016Column90 + "");
			} else if (!checkValue.isStringInt(nec31016Column90 + "") && checkValue.isNumeric(nec31016Column90 + "")) {
				nec310Col90 = (int) Math.round(nec31016Column90);
			}

			if (Boolean.TRUE.equals(params.getIncorrectTradeSizeLogic()) && !params.getIncorrectTradeSize().isEmpty()) {
				params.setNEC310(nec3106B16Repo.findFirstByNinetyInsulationGreaterThanAndTradeSzeIsNotIn(nec310Col90,
						params.getIncorrectTradeSize()));
			} else {
				params.setNEC310(nec3106B16Repo.findFirstByNinetyInsulationGreaterThan(nec310Col90));
			}

			if (i == 1) {
				params.setTradeSizeRepeate("#12 AWG");

				if (checkValue.Equals(dcList.get(i - 1).getCircuitSpec().getConductorSize(), "EXIST")) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY1", "EXISTING");
					dcNumberOfConductors.add(1);
					dcTradeSize.add("EXISTING");
				} else {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "#12 AWG");
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL", "(1) CU");
					dcNumberOfConductors.add(1);
					dcTradeSize.add("#12 AWG");

					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "30");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING", "30");

					form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY",
							String.valueOf(new DecimalFormat("##.##")
									.format(Float.parseFloat(params.getAmpacityCorrection().replace(',', '.')) * 30
											* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
					form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY1",
							String.valueOf(new DecimalFormat("##.##")
									.format(Float.parseFloat(params.getAmpacityCorrection().replace(',', '.')) * 30
											* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
					params.setCorrectedAmpacity(String.valueOf(new DecimalFormat("##.##")
							.format(Float.parseFloat(params.getAmpacityCorrection().replace(',', '.')) * 30
									* Float.parseFloat(params.getDcampacityCorrectionB3a()))));

				}

			} else {
				if (params.getNEC310() != null) {
					params.setTradeSizeRepeate(params.getNEC310().getTradeSze());
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL",
							"(" + params.getNEC310().getNumberOfConductors() + ") CU");
					params.setCalculatedNumberOfConductor(params.getNEC310().getNumberOfConductors());
					dcNumberOfConductors.add(params.getNEC310().getNumberOfConductors());
				} else {
					dcNumberOfConductors.add(1);
					params.setTradeSizeRepeate("");
					form.setField(sheetIndex + "-" + "DC" + i + "-MATERIAL", "CU");
					params.setCalculatedNumberOfConductor(1);
				}

				if (dcList.size() > i - 1
						&& checkValue.Equals(dcList.get(i - 1).getCircuitSpec().getConductorSize(), "EXIST")) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
				} else if (params.getNEC310() != null) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", params.getNEC310().getTradeSze());
					List<String> incorrectTradeSize = params.getIncorrectTradeSize();
					incorrectTradeSize.add(params.getNEC310().getTradeSze());
					params.setIncorrectTradeSize(incorrectTradeSize);
					dcTradeSize.add(params.getNEC310().getTradeSze());

				} else {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "");
					params.setIncorrectTradeSize(new ArrayList<>());
					dcTradeSize.add("");
				}

				if (params.getNEC310() != null && params.getNEC310().getNinetyInsulation() != null) {
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
							String.valueOf(String.valueOf(params.getNEC310().getNinetyInsulation())));
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING",
							String.valueOf(String.valueOf(params.getNEC310().getNinetyInsulation())));
					form.setField(
							sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY", String
									.valueOf(
											new DecimalFormat("##.##").format(
													Float.parseFloat(params.getAmpacityCorrection().replace(',', '.'))
															* Float.parseFloat(String
																	.valueOf(String.valueOf(
																			params.getNEC310().getNinetyInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
					form.setField(
							sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY1", String
									.valueOf(
											new DecimalFormat("##.##").format(
													Float.parseFloat(params.getAmpacityCorrection().replace(',', '.'))
															* Float.parseFloat(String
																	.valueOf(String.valueOf(
																			params.getNEC310().getNinetyInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
					params.setCorrectedAmpacity(
							String.valueOf(
									new DecimalFormat(
											"##.##").format(
													Float.parseFloat(params.getAmpacityCorrection().replace(',', '.'))
															* Float.parseFloat(String
																	.valueOf(String.valueOf(
																			params.getNEC310().getNinetyInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
				}
			}

		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}

	public String getCorrectionB3a(String numberOfConductorString) {

		try {
			if (checkValue.isStringInt(numberOfConductorString)) {
				Integer numberOfConductor = Integer.parseInt(numberOfConductorString);
				if (numberOfConductor >= 1 && numberOfConductor <= 3) {
					return "1.0";
				} else if (numberOfConductor >= 4 && numberOfConductor <= 6) {
					return "0.80";
				} else if (numberOfConductor >= 7 && numberOfConductor <= 9) {
					return "0.70";
				} else if (numberOfConductor >= 10 && numberOfConductor <= 20) {
					return "0.50";
				} else if (numberOfConductor >= 21 && numberOfConductor <= 30) {
					return "0.45";
				} else if (numberOfConductor >= 31 && numberOfConductor <= 40) {
					return "0.40";
				} else if (numberOfConductor >= 41) {
					return "0.35";
				} else {
					return "N/A";
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return "";
	}
}
