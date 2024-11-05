package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.string.ac_circuit;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit.NecBchart;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ConduitFillDeratingBeforeRevion {

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	final NEC3106B16Repository nec3106B16Repo;
	final NecBchart necBchartService;
	final ACAmpacityCorrection acAmpacityCorrection;

	public ConduitFillDeratingBeforeRevion(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem,
			NEC3106B16Repository nec3106b16Repo, NecBchart necBchartService,
			ACAmpacityCorrection acAmpacityCorrection) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
		nec3106B16Repo = nec3106b16Repo;
		this.necBchartService = necBchartService;
		this.acAmpacityCorrection = acAmpacityCorrection;
	}

	static final String CHOOSE_CONDUCTOR_SIZE = "I want to choose the conductor size";
	static final String EXISTING_SUB_PANEL = "Show the word “Existing” instead of a conductor size (Some AHJs may not approve the permit with this wording)";

	public E002Model mapACExistingbeforeRevision(AcroFields form, int i, ESSConnectors ac, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, int sheetIndex, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			Integer numberOfConductor, PermitHomeSiteInfoEntity permitHomeSite, String invOpTemp, E002Model params) {

		params.setCorrectedACAmpacity("");
		try {

//			S.B CR-3119-MOD-004 Number of Conductors on AC circuits on E-002
			Integer conductorQty = numberOfUnderGround(form, i, ac.getCircuitSpec(), sheetIndex, permitHomeSite,
					params.getCalculatedACNumberOfConductor());

			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
					acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(), conductorQty, numberOfConductor,
							true, true));
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
					acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(), conductorQty, numberOfConductor,
							true, true));
			params.setConduitFillDerating(acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(),
					conductorQty, numberOfConductor, true, true));

			if (checkValue.Equals(acAmpacityCorrection.getACAmpacityCorrectionB2aMultiple(invOpTemp), "")) {
				params.setNec31016Column90(params.getRequiredAmpacity());
			} else
				// 24/026/2019 : CI : CR2742
				params.setNec31016Column90(params.getRequiredAmpacity()
						/ (Float.parseFloat(acAmpacityCorrection.getACAmpacityCorrectionB2aMultiple(invOpTemp))
								* Float.parseFloat(params.getConduitFillDerating())));

			if (checkValue.contains(ac.getSourceID(), "SUB")
					&& checkValue.Equals(permitProjectSiteInfo.getConnectionPoint(), "Existing")
					&& checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), CHOOSE_CONDUCTOR_SIZE)) {
				if (checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "EXIST")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
							"EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
							"EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY1", "EXISTING");
					acTradeSize.add("EXISTING");
				} else {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE",
							checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")
									? permitProjectSiteInfo.getSubPanelConductorSizeOther()
									: permitProjectSiteInfo.getSubPanelConductorSize());
					acTradeSize.add(checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")
							? permitProjectSiteInfo.getSubPanelConductorSizeOther()
							: permitProjectSiteInfo.getSubPanelConductorSize());

					String tradeSize = checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")
							? permitProjectSiteInfo.getSubPanelConductorSizeOther()
							: permitProjectSiteInfo.getSubPanelConductorSize();
					params.setNEC310(nec3106B16Repo.findFirstBytradeSze(tradeSize));
					if (params.getNEC310() != null) {
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL",
								"(" + params.getNEC310().getNumberOfConductors() + ") CU");
						params.setCalculatedACNumberOfConductor(params.getNEC310().getNumberOfConductors());
						acNumberOfConductors.add(params.getNEC310().getNumberOfConductors());
						params.getIncorrectACTradeSize().add(params.getNEC310().getTradeSze());
						if (params.getNEC310().getNumberOfConductors() == 1) {
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
									"1.0");
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
									"1.0");
							params.setConduitFillDerating("1.0");
						} else {
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
									acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(), conductorQty,
											params.getCalculatedACNumberOfConductor(), true, true));
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
									acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(), conductorQty,
											params.getCalculatedACNumberOfConductor(), true, true));
							params.setConduitFillDerating(
									acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(), conductorQty,
											params.getCalculatedACNumberOfConductor(), true, true));
						}
						if (params.getNEC310().getSeventyFiveInsulation() != null) {
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING",
									String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
									String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
							try {
								form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY",
										String.valueOf(new DecimalFormat("#.0")
												.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
														String.valueOf(params.getNEC310().getSeventyFiveInsulation())
																.replace(',', '.')))));
								form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY1",
										String.valueOf(new DecimalFormat("#.0")
												.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
														String.valueOf(params.getNEC310().getSeventyFiveInsulation())
																.replace(',', '.')))));
								params.setCorrectedACAmpacity(String.valueOf(new DecimalFormat("#.0")
										.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
												String.valueOf(params.getNEC310().getSeventyFiveInsulation())
														.replace(',', '.')))));
							} catch (Exception e) {
								e.printStackTrace();
							}

						}

					} else
						acNumberOfConductors.add(1);

				}
			} else {
				/// Get NEC_310_16_B_16///
				params.setNEC310(necBchartService.getNecBchart(params));

				if (params.getNEC310() != null) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL",
							"(" + params.getNEC310().getNumberOfConductors() + ") CU");
					params.setCalculatedACNumberOfConductor(params.getNEC310().getNumberOfConductors());
					acNumberOfConductors.add(params.getNEC310().getNumberOfConductors());

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", params.getNEC310().getTradeSze());
					params.getIncorrectACTradeSize().add(params.getNEC310().getTradeSze());
					acTradeSize.add(params.getNEC310().getTradeSze());
				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL", "CU");
					params.setCalculatedACNumberOfConductor(1);
					acNumberOfConductors.add(1);

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "");
					params.setIncorrectACTradeSize(new ArrayList<>());
					acTradeSize.add("");
				}

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
					params.setCorrectedACAmpacity(String.valueOf(new DecimalFormat("#.0")
							.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
									String.valueOf(params.getNEC310().getSeventyFiveInsulation()).replace(',', '.')))));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}

	public E002Model mapACVoltageDrop(AcroFields form, int i, ESSConnectors ac, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, int sheetIndex, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			Integer numberOfConductor, PermitHomeSiteInfoEntity permitHomeSite, String invOpTemp, E002Model params) {

		params.setCorrectedACAmpacity("");
		try {

//			S.B CR-3119-MOD-004 Number of Conductors on AC circuits on E-002
			Integer conductorQty = numberOfUnderGround(form, i, ac.getCircuitSpec(), sheetIndex, permitHomeSite,
					params.getCalculatedACNumberOfConductor());

			// 25/06/2019 CI : CR 2742
			if (numberOfConductor == 1) {
				form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "1.0");
				form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "1.0");
				params.setConduitFillDerating("1.0");

			} else {
				form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
						acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(), conductorQty,
								numberOfConductor, true, true));
				form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
						acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(), conductorQty,
								numberOfConductor, true, true));
				params.setConduitFillDerating(acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(),
						conductorQty, numberOfConductor, true, true));
			}

			if (checkValue.Equals(acAmpacityCorrection.getACAmpacityCorrectionB2aMultiple(invOpTemp), "")) {
				params.setNec31016Column90(params.getRequiredAmpacity());
			} else
				// 24/026/2019 : CI : CR2742
				params.setNec31016Column90(params.getRequiredAmpacity()
						/ (Float.parseFloat(acAmpacityCorrection.getACAmpacityCorrectionB2aMultiple(invOpTemp))
								* Float.parseFloat(params.getConduitFillDerating())));

			if (checkValue.contains(ac.getSourceID(), "SUB")
					&& checkValue.Equals(permitProjectSiteInfo.getConnectionPoint(), "Existing")
					&& checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), CHOOSE_CONDUCTOR_SIZE)) {
				if (checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "EXIST")) {
					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
							"EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
							"EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY", "EXISTING");
					form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY1", "EXISTING");
					acTradeSize.add("EXISTING");
				} else {

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE",
							checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")
									? permitProjectSiteInfo.getSubPanelConductorSizeOther()
									: permitProjectSiteInfo.getSubPanelConductorSize());
					acTradeSize.add(checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")
							? permitProjectSiteInfo.getSubPanelConductorSizeOther()
							: permitProjectSiteInfo.getSubPanelConductorSize());

					String tradeSize = checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "Other")
							? permitProjectSiteInfo.getSubPanelConductorSizeOther()
							: permitProjectSiteInfo.getSubPanelConductorSize();
					params.setNEC310(nec3106B16Repo.findFirstBytradeSze(tradeSize));
					if (params.getNEC310() != null) {
						form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL",
								"(" + params.getNEC310().getNumberOfConductors() + ") CU");
						params.setCalculatedACNumberOfConductor(params.getNEC310().getNumberOfConductors());
						acNumberOfConductors.add(params.getNEC310().getNumberOfConductors());
						params.getIncorrectACTradeSize().add(params.getNEC310().getTradeSze());
						if (params.getNEC310().getNumberOfConductors() == 1) {
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
									"1.0");
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
									"1.0");
							params.setConduitFillDerating("1.0");
						} else {
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
									acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(), conductorQty,
											params.getCalculatedACNumberOfConductor(), true, true));
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
									acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(), conductorQty,
											params.getCalculatedACNumberOfConductor(), true, true));
							params.setConduitFillDerating(
									acAmpacityCorrection.acAmpacityCorrectionB3a(ac.getCircuitSpec(), conductorQty,
											params.getCalculatedACNumberOfConductor(), true, true));
						}
						if (params.getNEC310().getSeventyFiveInsulation() != null) {
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING",
									String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
									String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
							try {
								form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY",
										String.valueOf(new DecimalFormat("#.0")
												.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
														String.valueOf(params.getNEC310().getSeventyFiveInsulation())
																.replace(',', '.')))));
								form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY1",
										String.valueOf(new DecimalFormat("#.0")
												.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
														String.valueOf(params.getNEC310().getSeventyFiveInsulation())
																.replace(',', '.')))));
								params.setCorrectedACAmpacity(String.valueOf(new DecimalFormat("#.0")
										.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
												String.valueOf(params.getNEC310().getSeventyFiveInsulation())
														.replace(',', '.')))));
							} catch (Exception e) {
								e.printStackTrace();
							}

						}

					} else
						acNumberOfConductors.add(1);

				}
			} else {

				if (params.getNEC310() != null) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL",
							"(" + params.getNEC310().getNumberOfConductors() + ") CU");
					params.setCalculatedACNumberOfConductor(params.getNEC310().getNumberOfConductors());
					acNumberOfConductors.add(params.getNEC310().getNumberOfConductors());

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", params.getNEC310().getTradeSze());
					params.getIncorrectACTradeSize().add(params.getNEC310().getTradeSze());
					acTradeSize.add(params.getNEC310().getTradeSze());
				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL", "CU");
					params.setCalculatedACNumberOfConductor(1);
					acNumberOfConductors.add(1);

					form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "");
					params.setIncorrectACTradeSize(new ArrayList<>());
					acTradeSize.add("");
				}

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
					params.setCorrectedACAmpacity(String.valueOf(new DecimalFormat("#.0")
							.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
									String.valueOf(params.getNEC310().getSeventyFiveInsulation()).replace(',', '.')))));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}

	public Integer numberOfUnderGround(AcroFields form, int i, ESSCircuitSpec acSpec, int sheetIndex,
			PermitHomeSiteInfoEntity permitHomeSite, Integer calculatedACNumberOfConductor) {
		try {
			
			if (Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
					&& checkValue.notChecked(acSpec.getConductorNeutral())) {
				form.setField(sheetIndex + "-" + "AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", String.valueOf(2));
				return 2;
			} else if ((Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
					&& Boolean.TRUE.equals(acSpec.getConductorNeutral()))
					|| (checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(acSpec.getConductorNeutral()))) {
				form.setField(sheetIndex + "-" + "AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", String.valueOf(3));
				return 3;
			} else if (checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
					&& Boolean.TRUE.equals(acSpec.getConductorNeutral())) {
				form.setField(sheetIndex + "-" + "AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", String.valueOf(4));
				return 4;
			} else {
				form.setField(sheetIndex + "-" + "AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS",
						String.valueOf(3 * calculatedACNumberOfConductor));
				return 3 * calculatedACNumberOfConductor;
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return 0;
	}

}
