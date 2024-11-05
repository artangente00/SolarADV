package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.string.ac_circuit;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.projects.ess.ESSConnectors;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit.ACAmpacityCorrection;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ConduitFillDeratingAfterRevion {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	final NEC3106B16Repository nec3106B16Repo;
	final ACAmpacityCorrection acAmpacityCorrection;

	public ConduitFillDeratingAfterRevion(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem,
			NEC3106B16Repository nec3106b16Repo, ACAmpacityCorrection acAmpacityCorrection) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
		nec3106B16Repo = nec3106b16Repo;
		this.acAmpacityCorrection = acAmpacityCorrection;
	}

	static final String EXISTING_SUB_PANEL = "Show the word “Existing” instead of a conductor size (Some AHJs may not approve the permit with this wording)";
	static final String CHOOSE_CONDUCTOR_SIZE = "I want to choose the conductor size";

	public E002Model mapACexistingafterRevision(AcroFields form, int i, ESSConnectors ac, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, int sheetIndex, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			E002Model params) {

		try {

			if (ac != null) {

				if (checkValue.contains(ac.getSourceID(), "SUB")) {
					if (checkValue.Equals(permitProjectSiteInfo.getConnectionPoint(), "Existing") && checkValue
							.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), CHOOSE_CONDUCTOR_SIZE)) {

						if (checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")) {
							form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE",
									permitProjectSiteInfo.getSubPanelConductorSizeOther());
							params.setConductorSize(permitProjectSiteInfo.getSubPanelConductorSizeOther());
							acTradeSize.add(permitProjectSiteInfo.getSubPanelConductorSizeOther());

						} else if (checkValue.Equals(ac.getCircuitSpec().getConductorSize(), "Per Manufacturer")) {
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

					} else if (ac.getCircuitSpec().getConductorSize() != null) {
						if (checkValue.Equals(ac.getCircuitSpec().getConductorSize(), "Other")) {
							form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE",
									"#" + ac.getCircuitSpec().getConductorSizeOther() + " AWG");
							params.setConductorSize(ac.getCircuitSpec().getConductorSizeOther());
							acTradeSize.add("#" + ac.getCircuitSpec().getConductorSizeOther() + " AWG");
						} else if (checkValue.Equals(ac.getCircuitSpec().getConductorSize(), "Per Manufacturer")) {
							form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "#12 AWG");
							params.setConductorSize("#12 AWG");
							acTradeSize.add(i, "#12 AWG");
						} else {
							if (checkValue.Equals(ac.getCircuitSpec().getConductorSize(), "EXIST")) {
								form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "EXISTING");
								params.setConductorSize("EXISTING");
								acTradeSize.add("EXISTING");
							} else {
								form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE",
										ac.getCircuitSpec().getConductorSize());
								params.setConductorSize(ac.getCircuitSpec().getConductorSize());
								acTradeSize.add(ac.getCircuitSpec().getConductorSize());
							}

						}
					}
				} else if (ac.getCircuitSpec().getConductorSize() != null) {
					if (checkValue.Equals(ac.getCircuitSpec().getConductorSize(), "Other")) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE",
								"#" + ac.getCircuitSpec().getConductorSizeOther() + " AWG");
						params.setConductorSize(ac.getCircuitSpec().getConductorSizeOther());
						acTradeSize.add(i, "#" + ac.getCircuitSpec().getConductorSizeOther() + " AWG");
					} else if (checkValue.Equals(ac.getCircuitSpec().getConductorSize(), "Per Manufacturer")) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "#12 AWG");
						params.setConductorSize("12");
						acTradeSize.add("#12 AWG");
					} else if (checkValue.Equals(ac.getCircuitSpec().getConductorSize(), "EXIST")) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "EXISTING");
						params.setConductorSize("EXISTING");
						acTradeSize.add("EXISTING");
					} else {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE",
								ac.getCircuitSpec().getConductorSize());
						params.setConductorSize(ac.getCircuitSpec().getConductorSize());
						acTradeSize.add(ac.getCircuitSpec().getConductorSize());
					}
				}
				Integer compCondQty = checkValue.Equals(ac.getCircuitSpec().getConductorQty(), "Other")
						? ac.getCircuitSpec().getConductorQtyOther()
						: checkValue.isNumeric(ac.getCircuitSpec().getConductorQty())
								? Integer.parseInt(ac.getCircuitSpec().getConductorQty())
								: 1;
				if (checkValue.Equals(params.getConductorSize(), "#12 AWG")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL", "(1) CU");
					acNumberOfConductors.add(1);
					form.setField(sheetIndex + "-" + "AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", compCondQty + "");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
							acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(), compCondQty, 1, false,
									true));
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
							acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(), compCondQty, 1, true,
									true));

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
					form.setField(sheetIndex + "-" + "AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", compCondQty + "");
					if (params.getNEC310() != null) {
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
								acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(), compCondQty,
										params.getNEC310().getNumberOfConductors(), false, true));
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
								acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(), compCondQty,
										params.getNEC310().getNumberOfConductors(), true, true));
					}
				}
			}

			params = mapACcircuitAmpacity(form, i, sheetIndex, params);
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}

	public E002Model mapACcircuitAmpacity(AcroFields form, int i, int sheetIndex, E002Model params) {
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
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
							String.valueOf(params.getNEC310().getSeventyFiveInsulation()));
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING",
							String.valueOf(params.getNEC310().getSeventyFiveInsulation()));
					form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY", String.valueOf(new DecimalFormat(
							"#.0")
							.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
									String.valueOf(params.getNEC310().getSeventyFiveInsulation()).replace(',', '.')))));
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
