package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.dc_circuit;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ConduitFillDerating {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	final CorrectedAmpacityCalculation correctedAmpacityCalculation;
	final NEC3106B16Repository nec3106B16Repo;

	public ConduitFillDerating(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem,
			CorrectedAmpacityCalculation correctedAmpacityCalculation, NEC3106B16Repository nec3106B16Repo) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
		this.correctedAmpacityCalculation = correctedAmpacityCalculation;
		this.nec3106B16Repo = nec3106B16Repo;
	}

	public E002Model mapFillDeratingVoltageDrop(Boolean stepOptimizer, AcroFields form, int i, String DCcircuit,
			PermitConduitConductorSectionEntity circuit, int numberOfStrings, int inverterQty,
			PermitEntity permitEntity, int originCombiner, List<String> dcTradeSize, List<Integer> dcNumberOfConductors,
			Integer numberOfConductor, int sheetIndex,E002Model params) {
		params.setCorrectedAmpacity("");
		if (circuit != null) {
			params.setCorrectedAmpacity("");

			try {

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
					if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "PV MODULE")
							&& ((checkValue.Equals(circuit.getConductorTypeExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistModule(), true)))) {
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "DC/DC CONVERTER")
							&& ((checkValue.Equals(circuit.getConductorTypeTwoExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistOptimizer(), true)))) {
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "JUNCTION BOX")
							&& ((checkValue.Equals(circuit.getConductorTypeThreeExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistdcJunctionBox(), true)))) {
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "DC COMBINER")
							&& ((checkValue.Equals(circuit.getConductorTypeFourExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistdcDcCombiner(), true)))) {
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "DC DISCONNECT")
							&& ((checkValue.Equals(circuit.getConductorTypeFiveExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistdcDcdisconnect(), true)))) {
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else {
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
								String.valueOf(String.valueOf(params.getNEC310().getNinetyInsulation())));
					}
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING",
							String.valueOf(String.valueOf(params.getNEC310().getNinetyInsulation())));
					form.setField(
							sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY", String
									.valueOf(
											new DecimalFormat(
													"##.##").format(
															Float.parseFloat(params.getAmpacityCorrection().replace(',', '.'))
																	* Float.parseFloat(String
																			.valueOf(String.valueOf(
																					params.getNEC310().getNinetyInsulation()))
																			.replace(',', '.'))
																	* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
					form.setField(
							sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY1", String
									.valueOf(
											new DecimalFormat(
													"##.##").format(
															Float.parseFloat(params.getAmpacityCorrection().replace(',', '.'))
																	* Float.parseFloat(String
																			.valueOf(String.valueOf(
																					params.getNEC310().getNinetyInsulation()))
																			.replace(',', '.'))
																	* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
					params.setCorrectedAmpacity(String
							.valueOf(
									new DecimalFormat(
											"##.##").format(
													Float.parseFloat(params.getAmpacityCorrection().replace(',', '.'))
															* Float.parseFloat(String
																	.valueOf(String
																			.valueOf(params.getNEC310().getNinetyInsulation()))
																	.replace(',', '.'))
															* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
				}
			

			} catch (NumberFormatException | IOException | DocumentException e) {
				e.printStackTrace();
				technicalProblem.traiterException(e);
			}
			return params;
		
		}
		return params;

	}

	public E002Model mapFillDeratingafterRevision(AcroFields form, int i, String dcCircuit,
			PermitConduitConductorSectionEntity circuit, PermitEntity permitEntity, List<String> dcTradeSize,
			List<Integer> dcNumberOfConductors, int sheetIndex, E002Model params) {

		params.setCorrectedAmpacity("");
		if (circuit != null) {

			if (checkValue.Equals(dcCircuit.split("-")[i - 1], "PV MODULE")) {
				params = mapPVmoduleFillDerating(form, i, circuit, dcCircuit, dcTradeSize, sheetIndex, params);
			} else if (checkValue.Equals(dcCircuit.split("-")[i - 1], "DC/DC CONVERTER")) {
				params = mapOptimizerFillDerating(form, i, circuit, dcCircuit, dcTradeSize, sheetIndex, params);
			} else if (checkValue.Equals(dcCircuit.split("-")[i - 1], "JUNCTION BOX")) {
				params = mapJunctionFillDerating(form, i, circuit, dcCircuit, dcTradeSize, sheetIndex, params);
			} else if (checkValue.Equals(dcCircuit.split("-")[i - 1], "DC COMBINER")) {
				params = mapDCcombinerFillDerating(form, i, circuit, dcCircuit, dcTradeSize, sheetIndex, params);
			} else if (checkValue.Equals(dcCircuit.split("-")[i - 1], "DC DISCONNECT")) {
				params = mapDCdisconnectFillDerating(form, i, circuit, dcCircuit, dcTradeSize, sheetIndex, params);
			}

			params = correctedAmpacityCalculation.ampacityMapping(form, i, params, dcCircuit, circuit,
					permitEntity, dcNumberOfConductors, sheetIndex);
		}
		return params;

	}

	public E002Model mapPVmoduleFillDerating(AcroFields form, int i, PermitConduitConductorSectionEntity circuit,
			String dcCircuit, List<String> dcTradeSize, int sheetIndex, E002Model params) {
		try {
			if (circuit != null && checkValue.Equals(circuit.getConductorQty(), "Other")) {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						circuit.getConductorQtyOther() + "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
								circuit.getConductorQtyOther() + "", false, true));
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
								circuit.getConductorQtyOther() + "", true, true));
				getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1], circuit.getConductorQtyOther() + "",
						true, true);

			} else if (circuit != null && circuit.getConductorQty() != null) {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						circuit.getConductorQty());
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1], circuit.getConductorQty(),
								false, true));
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1], circuit.getConductorQty(),
								true, true));
				getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1], circuit.getConductorQty(), true, true);

			} else {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "");

			}
			if (circuit != null && circuit.getConductorSize() != null) {
				if (checkValue.Equals(circuit.getConductorSize(), "Other")) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE",
							"#" + circuit.getConductorSizeOther() + " AWG");
					params.setConductorSize(circuit.getConductorSizeOther());
					dcTradeSize.add("#" + circuit.getConductorSizeOther() + " AWG");
				} else if (checkValue.Equals(circuit.getConductorSize(), "Per Manufacturer")) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "#12 AWG");
					params.setConductorSize("#12 AWG");
					dcTradeSize.add("#12 AWG");
				} else {
					if (checkValue.EqualsCaseInsensitive(circuit.getConductorSize(), "EXIST")) {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
						params.setConductorSize("EXISTING");
						dcTradeSize.add("EXISTING");
					} else {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", circuit.getConductorSize());
						params.setConductorSize(circuit.getConductorSize());
						dcTradeSize.add(circuit.getConductorSize());
					}

				}
			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}

	public E002Model mapOptimizerFillDerating(AcroFields form, int i, PermitConduitConductorSectionEntity circuit,
			String dcCircuit, List<String> dcTradeSize, int sheetIndex, E002Model params) {
		try {
			if (checkValue.Equals(circuit.getConductorQtyTwo(), "Other")) {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						circuit.getConductorQtyTwoOther() + "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
								circuit.getConductorQtyTwoOther() + "", false, true));
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
								circuit.getConductorQtyTwoOther() + "", true, true));
				params.setDcampacityCorrectionB3a(getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
						circuit.getConductorQtyTwoOther() + "", true, true));

			} else if (circuit.getConductorQtyTwo() != null) {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						circuit.getConductorQtyTwo());
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1], circuit.getConductorQtyTwo(),
								false, true));
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1], circuit.getConductorQtyTwo(),
								true, true));
				params.setDcampacityCorrectionB3a(getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
						circuit.getConductorQtyTwo(), true, true));

			} else {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "");

			}

			if (circuit.getConductorSizeTwo() != null) {
				if (checkValue.Equals(circuit.getConductorSizeTwo(), "Other")) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE",
							"#" + circuit.getConductorSizeTwoOther() + " AWG");
					params.setConductorSize(circuit.getConductorSizeTwoOther());
					dcTradeSize.add("#" + circuit.getConductorSizeTwoOther() + " AWG");
				} else if (checkValue.Equals(circuit.getConductorSizeTwo(), "Per Manufacturer")) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "#12 AWG");
					params.setConductorSize("#12 AWG");
					dcTradeSize.add("#12 AWG");
				} else {
					if (checkValue.Equals(circuit.getConductorSizeTwo(), "EXIST")) {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
						params.setConductorSize("EXISTING");
						dcTradeSize.add("EXISTING");
					} else {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", circuit.getConductorSizeTwo());
						params.setConductorSize(circuit.getConductorSizeTwo());
						dcTradeSize.add(circuit.getConductorSizeTwo());
					}

				}

			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}

	public E002Model mapJunctionFillDerating(AcroFields form, int i, PermitConduitConductorSectionEntity circuit,
			String dcCircuit, List<String> dcTradeSize, int sheetIndex, E002Model params) {
		try {
			if (checkValue.Equals(circuit.getConductorQtyThree(), "Other")) {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						circuit.getConductorQtyThreeOther() + "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
								circuit.getConductorQtyThreeOther() + "", false, true));
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
								circuit.getConductorQtyThreeOther() + "", true, true));
				params.setDcampacityCorrectionB3a(getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
						circuit.getConductorQtyThreeOther() + "", true, true));

			} else if (circuit.getConductorQtyThree() != null) {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						circuit.getConductorQtyThree());
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1], circuit.getConductorQtyThree(),
								false, true));
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1], circuit.getConductorQtyThree(),
								true, true));
				params.setDcampacityCorrectionB3a(getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
						circuit.getConductorQtyThree(), true, true));

			} else {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "");

			}

			if (circuit.getConductorSizeThree() != null) {
				if (checkValue.Equals(circuit.getConductorSizeThree(), "Other")) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE",
							"#" + circuit.getConductorSizeThreeOther() + " AWG");
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE1",
							"#" + circuit.getConductorSizeThreeOther() + " AWG");
					params.setConductorSize(circuit.getConductorSizeThreeOther());
					dcTradeSize.add("#" + circuit.getConductorSizeThreeOther() + " AWG");
				} else if (checkValue.Equals(circuit.getConductorSizeThree(), "Per Manufacturer")) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "#12 AWG");
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE1", "#12 AWG");
					params.setConductorSize("#12 AWG");
					dcTradeSize.add("#12 AWG");
				} else {
					if (checkValue.Equals(circuit.getConductorSizeThree(), "EXIST")) {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE1", "EXISTING");
						params.setConductorSize("EXISTING");
						dcTradeSize.add("EXISTING");
					} else {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", circuit.getConductorSizeThree());
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE1", circuit.getConductorSizeThree());
						params.setConductorSize(circuit.getConductorSizeThree());
						dcTradeSize.add(circuit.getConductorSizeThree());
					}

				}

			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}

	public E002Model mapDCcombinerFillDerating(AcroFields form, int i, PermitConduitConductorSectionEntity circuit,
			String dcCircuit, List<String> dcTradeSize, int sheetIndex, E002Model params) {
		try {
			if (checkValue.Equals(circuit.getConductorQtyFour(), "Other")) {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						circuit.getConductorQtyFourOther() + "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
								circuit.getConductorQtyFourOther() + "", false, true));
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
								circuit.getConductorQtyFourOther() + "", true, true));
				params.setDcampacityCorrectionB3a(getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
						circuit.getConductorQtyFourOther() + "", true, true));

			} else if (circuit.getConductorQtyFour() != null) {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						circuit.getConductorQtyFour());
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1], circuit.getConductorQtyFour(),
								false, true));
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1], circuit.getConductorQtyFour(),
								true, true));
				params.setDcampacityCorrectionB3a(getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
						circuit.getConductorQtyFour(), true, true));

			} else {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "");

			}

			if (circuit.getConductorSizeFour() != null) {
				if (checkValue.Equals(circuit.getConductorSizeFour(), "Other")) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE",
							"#" + circuit.getConductorSizeFourOther() + " AWG");
					params.setConductorSize(circuit.getConductorSizeFourOther());
					dcTradeSize.add("#" + circuit.getConductorSizeFourOther() + " AWG");
				} else if (checkValue.Equals(circuit.getConductorSizeFour(), "Per Manufacturer")) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "#12 AWG");
					params.setConductorSize("#12 AWG");
					dcTradeSize.add("#12 AWG");
				} else {
					if (checkValue.Equals(circuit.getConductorSizeFour(), "EXIST")) {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
						params.setConductorSize("EXISTING");
						dcTradeSize.add("EXISTING");
					} else {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", circuit.getConductorSizeFour());
						params.setConductorSize(circuit.getConductorSizeFour());
						dcTradeSize.add(circuit.getConductorSizeFour());
					}

				}

			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}

	public E002Model mapDCdisconnectFillDerating(AcroFields form, int i, PermitConduitConductorSectionEntity circuit,
			String dcCircuit, List<String> dcTradeSize, int sheetIndex, E002Model params) {
		try {
			if (checkValue.Equals(circuit.getConductorQtyFive(), "Other")) {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						circuit.getConductorQtyFiveOther() + "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
								circuit.getConductorQtyFiveOther() + "", false, true));
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
								circuit.getConductorQtyFiveOther() + "", true, true));
				params.setDcampacityCorrectionB3a(getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
						circuit.getConductorQtyFiveOther() + "", true, true));

			} else if (circuit.getConductorQtyFive() != null) {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						circuit.getConductorQtyFive());
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1], circuit.getConductorQtyFive(),
								false, true));
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
						getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1], circuit.getConductorQtyFive(),
								true, true));
				params.setDcampacityCorrectionB3a(getDCAmpacityCorrectionB3a(circuit, dcCircuit.split("-")[i - 1],
						circuit.getConductorQtyFive(), true, true));

			} else {
				form.setField(sheetIndex + "-" + "DC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "");
				form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "");
			}

			if (circuit.getConductorSizeFive() != null) {
				if (checkValue.Equals(circuit.getConductorSizeFive(), "Other")) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE",
							"#" + circuit.getConductorSizeFiveOther() + " AWG");
					params.setConductorSize(circuit.getConductorSizeFiveOther());
					dcTradeSize.add("#" + circuit.getConductorSizeFiveOther() + " AWG");
				} else if (checkValue.Equals(circuit.getConductorSizeFive(), "Per Manufacturer")) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "#12 AWG");
					params.setConductorSize("#12 AWG");
					dcTradeSize.add("#12 AWG");
				} else {
					if (checkValue.Equals(circuit.getConductorSizeFive(), "EXIST")) {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
						params.setConductorSize("EXISTING");
						dcTradeSize.add("EXISTING");
					} else {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", circuit.getConductorSizeFive());
						params.setConductorSize(circuit.getConductorSizeFive());
						dcTradeSize.add(circuit.getConductorSizeFive());
					}

				}

			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
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

	public E002Model mapFillDeratingbeforeRevision(Boolean stepOptimizer, AcroFields form, int i, String DCcircuit,
			PermitConduitConductorSectionEntity circuit, int numberOfStrings, int inverterQty,
			PermitEntity permitEntity, int originCombiner, List<String> dcTradeSize, List<Integer> dcNumberOfConductors,
			Integer numberOfConductor, int sheetIndex,E002Model params) {

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

				if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
						&& checkValue.Equals(getDCAmpacityCorrectionB3a(circuit, DCcircuit.split("-")[i - 1],
								String.valueOf(2 * inverterQty * numberOfConductor), false, false), "N/A")) {
					params.setDcampacityCorrectionB3a("1");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "N/A");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "1.0");
				} else {
					if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1) {
						params.setDcampacityCorrectionB3a(getDCAmpacityCorrectionB3a(circuit, DCcircuit.split("-")[i - 1],
								String.valueOf(2 * inverterQty), true, false));
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								getDCAmpacityCorrectionB3a(circuit, DCcircuit.split("-")[i - 1],
										String.valueOf(2 * inverterQty), false, false));
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								getDCAmpacityCorrectionB3a(circuit, DCcircuit.split("-")[i - 1],
										String.valueOf(2 * inverterQty), true, false));
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
					if (checkValue.Equals(getDCAmpacityCorrectionB3a(circuit, DCcircuit.split("-")[i - 1],
							String.valueOf(2 * numberOfStrings * numberOfConductor), false, false), "N/A")) {
						params.setDcampacityCorrectionB3a("1");
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								"N/A");
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								"1.0");
					} else {
						params.setDcampacityCorrectionB3a(getDCAmpacityCorrectionB3a(circuit, DCcircuit.split("-")[i - 1],
								String.valueOf(2 * numberOfStrings * numberOfConductor), true, false));
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								getDCAmpacityCorrectionB3a(circuit, DCcircuit.split("-")[i - 1],
										String.valueOf(2 * numberOfStrings * numberOfConductor), false, false));
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								getDCAmpacityCorrectionB3a(circuit, DCcircuit.split("-")[i - 1],
										String.valueOf(2 * numberOfStrings * numberOfConductor), true, false));
					}
				}

			}
			// CI :24/06/2019 : CR 2742
			double nec31016Column90 = params.getRequiredAmpacity()
					/ (Float.parseFloat(params.getAmpacityCorrection()) * Float.parseFloat(params.getDcampacityCorrectionB3a()));

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

				if (checkValue.contains(DCcircuit, "-") && checkValue.Equals(DCcircuit.split("-")[i - 1], "PV MODULE")
						&& ((circuit != null && checkValue.Equals(circuit.getConductorTypeExisting(), true))
								|| (permitEntity != null && checkValue.Equals(permitEntity.getExistModule(), true)))) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY", "EXISTING");
					form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY1", "EXISTING");
					dcNumberOfConductors.add(1);
					dcTradeSize.add("EXISTING");
				} else {
					if (checkValue.contains(DCcircuit, "-")
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "PV MODULE") && circuit != null
							&& checkValue.Equals(circuit.getConductorSize(), "EXIST")) {
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
						if (checkValue.contains(DCcircuit, "-")
								&& checkValue.Equals(DCcircuit.split("-")[i - 1], "PV MODULE")
								&& ((circuit != null && checkValue.Equals(circuit.getConductorTypeExisting(), true))
										|| (permitEntity != null
												&& checkValue.Equals(permitEntity.getExistModule(), true)))) {
							form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
							form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
							form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY", "EXISTING");
							form.setField(sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY1", "EXISTING");

						} else {
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
					}
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

				if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
						&& checkValue.Equals(DCcircuit.split("-")[i - 1], "PV MODULE")
						&& ((checkValue.Equals(circuit.getConductorTypeExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistModule(), true)))) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
					dcTradeSize.add("EXISTING");
				} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
						&& checkValue.Equals(DCcircuit.split("-")[i - 1], "DC/DC CONVERTER")
						&& ((checkValue.Equals(circuit.getConductorTypeTwoExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistOptimizer(), true)))) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
					dcTradeSize.add("EXISTING");
				} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
						&& checkValue.Equals(DCcircuit.split("-")[i - 1], "JUNCTION BOX")
						&& ((checkValue.Equals(circuit.getConductorTypeThreeExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistdcJunctionBox(), true)))) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
					dcTradeSize.add("EXISTING");
				} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
						&& checkValue.Equals(DCcircuit.split("-")[i - 1], "DC COMBINER")
						&& ((checkValue.Equals(circuit.getConductorTypeFourExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistdcDcCombiner(), true)))) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
					dcTradeSize.add("EXISTING");
				} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
						&& checkValue.Equals(DCcircuit.split("-")[i - 1], "DC DISCONNECT")
						&& ((checkValue.Equals(circuit.getConductorTypeFiveExisting(), true))
								|| (checkValue.Equals(permitEntity.getExistdcDcdisconnect(), true)))) {
					form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
					dcTradeSize.add("EXISTING");
				} else {
					if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "PV MODULE")
							&& checkValue.Equals(circuit.getConductorSize(), "EXIST")) {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
					} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "DC/DC CONVERTER")
							&& checkValue.Equals(circuit.getConductorSizeTwo(), "EXIST")) {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
					} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "JUNCTION BOX")
							&& checkValue.Equals(circuit.getConductorSizeThree(), "EXIST")) {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
					} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "DC COMBINER")
							&& checkValue.Equals(circuit.getConductorSizeFour(), "EXIST")) {
						form.setField(sheetIndex + "-" + "DC" + i + "-TRADE-SIZE", "EXISTING");
					} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "DC DISCONNECT")
							&& checkValue.Equals(circuit.getConductorSizeFive(), "EXIST")) {
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
				}

				if (params.getNEC310() != null && params.getNEC310().getNinetyInsulation() != null) {
					if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "PV MODULE")
							&& ((checkValue.Equals(circuit.getConductorTypeExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistModule(), true)))) {
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "DC/DC CONVERTER")
							&& ((checkValue.Equals(circuit.getConductorTypeTwoExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistOptimizer(), true)))) {
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "JUNCTION BOX")
							&& ((checkValue.Equals(circuit.getConductorTypeThreeExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistdcJunctionBox(), true)))) {
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "DC COMBINER")
							&& ((checkValue.Equals(circuit.getConductorTypeFourExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistdcDcCombiner(), true)))) {
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else if (checkValue.contains(DCcircuit, "-") && DCcircuit.split("-").length > i - 1
							&& checkValue.Equals(DCcircuit.split("-")[i - 1], "DC DISCONNECT")
							&& ((checkValue.Equals(circuit.getConductorTypeFiveExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistdcDcdisconnect(), true)))) {
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else {
						form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
								String.valueOf(String.valueOf(params.getNEC310().getNinetyInsulation())));
					}
					form.setField(sheetIndex + "-" + "DC" + i + "-CONDUCTOR-AMPACTIY-RATING",
							String.valueOf(String.valueOf(params.getNEC310().getNinetyInsulation())));
					form.setField(
							sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY", String
									.valueOf(
											new DecimalFormat(
													"##.##").format(
															Float.parseFloat(params.getAmpacityCorrection().replace(',', '.'))
																	* Float.parseFloat(String
																			.valueOf(String.valueOf(
																					params.getNEC310().getNinetyInsulation()))
																			.replace(',', '.'))
																	* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
					form.setField(
							sheetIndex + "-" + "DC" + i + "-CORRECTED-AMPACITY1", String
									.valueOf(
											new DecimalFormat(
													"##.##").format(
															Float.parseFloat(params.getAmpacityCorrection().replace(',', '.'))
																	* Float.parseFloat(String
																			.valueOf(String.valueOf(
																					params.getNEC310().getNinetyInsulation()))
																			.replace(',', '.'))
																	* Float.parseFloat(params.getDcampacityCorrectionB3a()))));
					params.setCorrectedAmpacity(String
							.valueOf(
									new DecimalFormat(
											"##.##").format(
													Float.parseFloat(params.getAmpacityCorrection().replace(',', '.'))
															* Float.parseFloat(String
																	.valueOf(String
																			.valueOf(params.getNEC310().getNinetyInsulation()))
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
				} else{
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
