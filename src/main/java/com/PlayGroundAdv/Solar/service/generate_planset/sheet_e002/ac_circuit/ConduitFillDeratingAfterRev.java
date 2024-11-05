package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ConduitFillDeratingAfterRev {

	final static String EXISTING_SUB_PANEL = "Show the word “Existing” instead of a conductor size (Some AHJs may not approve the permit with this wording)";
	final static String CHOOSE_CONDUCTOR_SIZE = "I want to choose the conductor size";
	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	final NEC3106B16Repository nec3106B16Repo;
	final ACAmpacityCorrection acAmpacityCorrection;

	public ConduitFillDeratingAfterRev(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem,
			NEC3106B16Repository nec3106b16Repo, ACAmpacityCorrection acAmpacityCorrection) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
		nec3106B16Repo = nec3106b16Repo;
		this.acAmpacityCorrection = acAmpacityCorrection;
	}

	public E002Model mapACexistingafterRevision(AcroFields form, int i, String acCircuit,
			PermitConduitConductorSectionEntity circuit, PermitEntity permitEntity, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, int sheetIndex, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			E002Model params) {

		try {

			if (circuit != null && checkValue.contains(acCircuit, "-")) {

				if (checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")) {
					params = mapACcircuit(form, i, circuit, acCircuit, acTradeSize, acNumberOfConductors, sheetIndex,
							params, circuit.getConductorSizeSix(), circuit.getConductorSizeSixOther(),
							circuit.getConductorQtySix(), circuit.getConductorQtySixOther(), false,
							permitProjectSiteInfo);

				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")) {
					params = mapACcircuit(form, i, circuit, acCircuit, acTradeSize, acNumberOfConductors, sheetIndex,
							params, circuit.getConductorSizeSeven(), circuit.getConductorSizeSevenOther(),
							circuit.getConductorQtySeven(), circuit.getConductorQtySevenOther(), false,
							permitProjectSiteInfo);

				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")) {
					params = mapACcircuit(form, i, circuit, acCircuit, acTradeSize, acNumberOfConductors, sheetIndex,
							params, circuit.getConductorSizeEight(), circuit.getConductorSizeEightOther(),
							circuit.getConductorQtyEight(), circuit.getConductorQtyEightOther(), false,
							permitProjectSiteInfo);

				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")) {
					params = mapACcircuit(form, i, circuit, acCircuit, acTradeSize, acNumberOfConductors, sheetIndex,
							params, circuit.getConductorSizeNine(), circuit.getConductorSizeNineOther(),
							circuit.getConductorQtyNine(), circuit.getConductorQtyNineOther(), false,
							permitProjectSiteInfo);

				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")) {
					params = mapACcircuit(form, i, circuit, acCircuit, acTradeSize, acNumberOfConductors, sheetIndex,
							params, circuit.getConductorSizeNineTwo(), circuit.getConductorSizeNineTwoOther(),
							circuit.getConductorQtyNineTwo(), circuit.getConductorQtyNineTwoOther(), false,
							permitProjectSiteInfo);

				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")) {
					params = mapACcircuit(form, i, circuit, acCircuit, acTradeSize, acNumberOfConductors, sheetIndex,
							params, circuit.getConductorSizeTen(), circuit.getConductorSizeTenOther(),
							circuit.getConductorQtyTen(), circuit.getConductorQtyTenOther(), false,
							permitProjectSiteInfo);

				} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")) {
					params = mapACcircuit(form, i, circuit, acCircuit, acTradeSize, acNumberOfConductors, sheetIndex,
							params, circuit.getConductorSizeEleven(), circuit.getConductorSizeElevenOther(),
							circuit.getConductorQtyEleven(), circuit.getConductorQtyElevenOther(), true,
							permitProjectSiteInfo);

				}
			}

			params = mapACcircuitAmpacity(form, i, circuit, acCircuit, permitEntity, sheetIndex, permitProjectSiteInfo,
					params);
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}

	public E002Model mapACcircuit(AcroFields form, int i, PermitConduitConductorSectionEntity circuit, String acCircuit,
			List<String> acTradeSize, List<Integer> acNumberOfConductors, int sheetIndex, E002Model params,
			String compCondSize, String compCondSizeOther, String compCondQty, Integer compCondQtyOther,
			Boolean isSubPanel, PermitProjectSiteInfoEntity permitProjectSiteInfo) {
		try {
			if (Boolean.TRUE.equals(isSubPanel)) {
				if (checkValue.Equals(permitProjectSiteInfo.getConnectionPoint(), "Existing") && checkValue
						.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), CHOOSE_CONDUCTOR_SIZE)) {

					if (checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE",
								permitProjectSiteInfo.getSubPanelConductorSizeOther());
						params.setConductorSize(permitProjectSiteInfo.getSubPanelConductorSizeOther());
						acTradeSize.add(permitProjectSiteInfo.getSubPanelConductorSizeOther());

					} else if (checkValue.Equals(compCondSize, "Per Manufacturer")) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "#12 AWG");
						params.setConductorSize("#12 AWG");
						acTradeSize.add("#12 AWG");
					} else {
						if (checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "EXIST")) {
							form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "EXISTING");
							params.setConductorSize("EXISTING");
							acTradeSize.add("EXISTING");
						} else {
							form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE",
									permitProjectSiteInfo.getSubPanelConductorSize());
							params.setConductorSize(permitProjectSiteInfo.getSubPanelConductorSize());
							acTradeSize.add(permitProjectSiteInfo.getSubPanelConductorSize());
						}

					}

				} else if (circuit != null && compCondSize != null) {
					if (checkValue.Equals(compCondSize, "Other")) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "#" + compCondSizeOther + " AWG");
						params.setConductorSize(compCondSizeOther);
						acTradeSize.add("#" + compCondSizeOther + " AWG");
					} else if (checkValue.Equals(compCondSize, "Per Manufacturer")) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "#12 AWG");
						params.setConductorSize("#12 AWG");
						acTradeSize.add(i, "#12 AWG");
					} else {
						if (checkValue.Equals(compCondSize, "EXIST")) {
							form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "EXISTING");
							params.setConductorSize("EXISTING");
							acTradeSize.add("EXISTING");
						} else {
							form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", compCondSize);
							params.setConductorSize(compCondSize);
							acTradeSize.add(compCondSize);
						}

					}
				}
			} else if (compCondSize != null) {
				if (checkValue.Equals(compCondSize, "Other")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "#" + compCondSizeOther + " AWG");
					params.setConductorSize(compCondSizeOther);
					acTradeSize.add(i, "#" + compCondSizeOther + " AWG");
				} else if (checkValue.Equals(compCondSize, "Per Manufacturer")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "#12 AWG");
					params.setConductorSize("12");
					acTradeSize.add("#12 AWG");
				} else if (checkValue.Equals(compCondSize, "EXIST")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "EXISTING");
					params.setConductorSize("EXISTING");
					acTradeSize.add("EXISTING");
				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", compCondSize);
					params.setConductorSize(compCondSize);
					acTradeSize.add(compCondSize);
				}
			}

			if (checkValue.Equals(params.getConductorSize(), "#12 AWG")) {
				form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL", "(1) CU");
				acNumberOfConductors.add(1);
				if (checkValue.Equals(compCondQty, "Other")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
							compCondQtyOther + "");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
							acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1],
									compCondQtyOther, 1, false, true));
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
							acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1],
									compCondQtyOther, 1, true, true));
				} else if (checkValue.isNumeric(compCondQty)) {
					form.setField(sheetIndex + "-" + "AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", compCondQty);
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
							acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1],
									Integer.parseInt(compCondQty), 1, false, true));
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
							acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1],
									Integer.parseInt(compCondQty), 1, true, true));
				}

			} else {
				/// Get NEC_310_16_B_16///
				params.setNEC310(nec3106B16Repo.findFirstBytradeSze(params.getConductorSize()));
				if (params.getNEC310() != null) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL",
							"(" + params.getNEC310().getNumberOfConductors() + ") CU");
					acNumberOfConductors.add(params.getNEC310().getNumberOfConductors());
				} else {
					acNumberOfConductors.add(1);
				}
				if (circuit != null && checkValue.Equals(compCondQty, "Other")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
							compCondQtyOther + "");
					if (params.getNEC310() != null && checkValue.contains(acCircuit, "-")) {
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1],
										compCondQtyOther, params.getNEC310().getNumberOfConductors(), false,
										true));
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1],
										compCondQtyOther, params.getNEC310().getNumberOfConductors(), true, true));
					}

				} else if (circuit != null && checkValue.isNumeric(compCondQty)) {
					form.setField(sheetIndex + "-" + "AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", compCondQty);
					if (params.getNEC310() != null && checkValue.contains(acCircuit, "-")) {
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1],
										Integer.parseInt(compCondQty), params.getNEC310().getNumberOfConductors(), false, true));
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1],
										Integer.parseInt(compCondQty), params.getNEC310().getNumberOfConductors(), true, true));
					}
				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", "");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "");
				}
			}

		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}

	public E002Model mapACcircuitAmpacity(AcroFields form, int i, PermitConduitConductorSectionEntity circuit,
			String acCircuit, PermitEntity permitEntity, int sheetIndex,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, E002Model params) {
		try {

			if (checkValue.Equals(params.getConductorSize(), "#12 AWG")) {
				form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "25");
				form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "25");
				form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY", String
						.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * 25)));
				form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY1", String
						.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * 25)));
			} else {

				params.setNEC310(nec3106B16Repo.findFirstBytradeSze(params.getConductorSize()));

				if (params.getNEC310() != null && params.getNEC310().getSeventyFiveInsulation() != null) {
					if (checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")
							&& ((checkValue.Equals(circuit.getConductorTypeSixExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistInverter(), true)))) {
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
							&& ((checkValue.Equals(circuit.getConductorTypeSevenExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistAcJunctionBox(), true)))) {
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
							&& ((checkValue.Equals(circuit.getConductorTypeEightExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistAcCombiner(), true)))) {
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
							&& ((checkValue.Equals(circuit.getConductorTypeNineExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistAcDisconnect(), true)))) {
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
							&& ((checkValue.Equals(circuit.getConductorTypeNineTwoExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistAcDisconnect(), true)))) {
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
							&& ((checkValue.Equals(circuit.getConductorTypeTenExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistProductionMeter(), true)))) {
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
							&& ((checkValue.Equals(circuit.getConductorTypeElevenExisting(), true))
									|| (checkValue.Equals(permitEntity.getExistSubPanel(), true)) || checkValue.Equals(
											permitProjectSiteInfo.getSubPanelConductorSizing(), EXISTING_SUB_PANEL))) {
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					} else {
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
								String.valueOf(params.getNEC310().getSeventyFiveInsulation()));
					}
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING",
							String.valueOf(params.getNEC310().getSeventyFiveInsulation()));
					form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY",
							String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating())
									* Float.parseFloat(String.valueOf(params.getNEC310().getSeventyFiveInsulation())
											.replace(',', '.')))));
					form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY1",
							String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating())
									* Float.parseFloat(String.valueOf(params.getNEC310().getSeventyFiveInsulation())
											.replace(',', '.')))));
				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "");
					form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY", "");
					form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY1", "");
				}

			}
		} catch (NumberFormatException | IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}
}
