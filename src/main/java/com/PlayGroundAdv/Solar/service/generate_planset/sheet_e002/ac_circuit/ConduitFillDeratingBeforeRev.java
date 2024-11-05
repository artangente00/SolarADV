package com.PlayGroundAdv.Solar.service.generate_planset.sheet_e002.ac_circuit;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.model.planset_utils.E002Model;
import com.PlayGroundAdv.Solar.repositories.NEC3106B16Repository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;

@Service
public class ConduitFillDeratingBeforeRev {

	final static String CHOOSE_CONDUCTOR_SIZE = "I want to choose the conductor size";
	final static String EXISTING_SUB_PANEL = "Show the word “Existing” instead of a conductor size (Some AHJs may not approve the permit with this wording)";

	final CheckValueTypesService checkValue;
	final TechnicalProblemMsg technicalProblem;
	final NEC3106B16Repository nec3106B16Repo;
	final NecBchart necBchartService;
	final ACAmpacityCorrection acAmpacityCorrection;
	

	public ConduitFillDeratingBeforeRev(CheckValueTypesService checkValue, TechnicalProblemMsg technicalProblem,
			NEC3106B16Repository nec3106b16Repo, NecBchart necBchartService,
			ACAmpacityCorrection acAmpacityCorrection) {
		super();
		this.checkValue = checkValue;
		this.technicalProblem = technicalProblem;
		nec3106B16Repo = nec3106b16Repo;
		this.necBchartService = necBchartService;
		this.acAmpacityCorrection = acAmpacityCorrection;
	}

	public E002Model mapACExistingbeforeRevision(AcroFields form, int i, String acCircuit,
			PermitConduitConductorSectionEntity circuit, PermitEntity permitEntity, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, int sheetIndex, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			Integer numberOfConductor, PermitHomeSiteInfoEntity permitHomeSite, String invOpTemp, E002Model params) {
		
		params.setCorrectedACAmpacity("");
		try {
			
//			S.B CR-3119-MOD-004 Number of Conductors on AC circuits on E-002
			Integer conductorQty = numberOfUnderGround(form, i, acCircuit, circuit, sheetIndex, permitHomeSite, params.getCalculatedACNumberOfConductor());

			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
					acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1], conductorQty, numberOfConductor, true,
							true));
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
					acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1], conductorQty, numberOfConductor, true,
							true));
			params.setConduitFillDerating(acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1], conductorQty,
					numberOfConductor, true, true));
			
			if (checkValue.Equals(acAmpacityCorrection.getACAmpacityCorrectionB2aMultiple(invOpTemp), "")) {
				params.setNec31016Column90(params.getRequiredAmpacity());
			} else
				// 24/026/2019 : CI : CR2742
				params.setNec31016Column90(params.getRequiredAmpacity() / (Float.parseFloat(acAmpacityCorrection.getACAmpacityCorrectionB2aMultiple(invOpTemp))
						* Float.parseFloat(params.getConduitFillDerating())));

			if (checkValue.contains(acCircuit, "-") && checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
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
									acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1], conductorQty,
											params.getCalculatedACNumberOfConductor(), true, true));
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
									acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1], conductorQty,
											params.getCalculatedACNumberOfConductor(), true, true));
							params.setConduitFillDerating(acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1], conductorQty,
									params.getCalculatedACNumberOfConductor(), true, true));
						}
						if (params.getNEC310().getSeventyFiveInsulation() != null) {
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING",
									String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
									String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
							try {
								form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY",
										String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating())
												* Float.parseFloat(String.valueOf(params.getNEC310().getSeventyFiveInsulation())
														.replace(',', '.')))));
								form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY1",
										String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating())
												* Float.parseFloat(String.valueOf(params.getNEC310().getSeventyFiveInsulation())
														.replace(',', '.')))));
								params.setCorrectedACAmpacity(String.valueOf(new DecimalFormat("#.0")
										.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(String
												.valueOf(params.getNEC310().getSeventyFiveInsulation()).replace(',', '.')))));
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

				Boolean existingTradeSize = existingACTradeSize(form, i, sheetIndex, acCircuit, permitEntity, circuit,
						permitProjectSiteInfo, params.getIncorrectACTradeSize());

				if (Boolean.FALSE.equals(existingTradeSize)) {
					if (params.getNEC310() != null) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", params.getNEC310().getTradeSze());
						params.getIncorrectACTradeSize().add(params.getNEC310().getTradeSze());
						acTradeSize.add(params.getNEC310().getTradeSze());
					} else {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "");
						params.setIncorrectACTradeSize(new ArrayList<>());
						acTradeSize.add("");
					}
				}
				if (params.getNEC310() != null) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL",
							"(" + params.getNEC310().getNumberOfConductors() + ") CU");
					params.setCalculatedACNumberOfConductor(params.getNEC310().getNumberOfConductors());
					acNumberOfConductors.add(params.getNEC310().getNumberOfConductors());
				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL", "CU");
					params.setCalculatedACNumberOfConductor(1);
					acNumberOfConductors.add(1);
				}

				if (params.getNEC310() != null && params.getNEC310().getSeventyFiveInsulation() != null) {
					Boolean existConductorAmpacity = existConductorAmpacityRating(form, i, sheetIndex, acCircuit,
							permitEntity, circuit, permitProjectSiteInfo);

					if (Boolean.FALSE.equals(existConductorAmpacity)) {
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
								String.valueOf(params.getNEC310().getSeventyFiveInsulation()));
					}

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING",
							String.valueOf(params.getNEC310().getSeventyFiveInsulation()));
					form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY",
							String.valueOf(
									new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
											String.valueOf(params.getNEC310().getSeventyFiveInsulation()).replace(',', '.')))));
					form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY1",
							String.valueOf(
									new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
											String.valueOf(params.getNEC310().getSeventyFiveInsulation()).replace(',', '.')))));
					params.setCorrectedACAmpacity(String
							.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
									String.valueOf(params.getNEC310().getSeventyFiveInsulation()).replace(',', '.')))));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}
	
	
	public E002Model mapACVoltageDrop(AcroFields form, int i, String acCircuit,
			PermitConduitConductorSectionEntity circuit, PermitEntity permitEntity, List<String> acTradeSize,
			List<Integer> acNumberOfConductors, int sheetIndex, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			Integer numberOfConductor, PermitHomeSiteInfoEntity permitHomeSite, String invOpTemp, E002Model params) {
		
		params.setCorrectedACAmpacity("");
		try {

//			S.B CR-3119-MOD-004 Number of Conductors on AC circuits on E-002
			Integer conductorQty = numberOfUnderGround(form, i, acCircuit, circuit, sheetIndex, permitHomeSite, params.getCalculatedACNumberOfConductor());
			
			// 25/06/2019 CI : CR 2742
			if (numberOfConductor == 1) {
				form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION", "1.0");
				form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1", "1.0");
				params.setConduitFillDerating("1.0");

			} else {
				form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION",
						acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1], conductorQty, numberOfConductor, true,
								true));
				form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
						acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1], conductorQty, numberOfConductor, true,
								true));
				params.setConduitFillDerating(acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1], conductorQty,
						numberOfConductor, true, true));
			}

			if (checkValue.Equals(acAmpacityCorrection.getACAmpacityCorrectionB2aMultiple(invOpTemp), "")) {
				params.setNec31016Column90(params.getRequiredAmpacity());
			} else
				// 24/026/2019 : CI : CR2742
				params.setNec31016Column90(params.getRequiredAmpacity() / (Float.parseFloat(acAmpacityCorrection.getACAmpacityCorrectionB2aMultiple(invOpTemp))
						* Float.parseFloat(params.getConduitFillDerating())));

			if (checkValue.contains(acCircuit, "-") && checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
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
									acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1], conductorQty,
											params.getCalculatedACNumberOfConductor(), true, true));
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUIT-FILL-DERATING-AMPACITY-CORRECTION1",
									acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1], conductorQty,
											params.getCalculatedACNumberOfConductor(), true, true));
							params.setConduitFillDerating(acAmpacityCorrection.getACAmpacityCorrectionB3a(circuit, acCircuit.split("-")[i - 1], conductorQty,
									params.getCalculatedACNumberOfConductor(), true, true));
						}
						if (params.getNEC310().getSeventyFiveInsulation() != null) {
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING",
									String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
							form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
									String.valueOf(String.valueOf(params.getNEC310().getSeventyFiveInsulation())));
							try {
								form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY",
										String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating())
												* Float.parseFloat(String.valueOf(params.getNEC310().getSeventyFiveInsulation())
														.replace(',', '.')))));
								form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY1",
										String.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating())
												* Float.parseFloat(String.valueOf(params.getNEC310().getSeventyFiveInsulation())
														.replace(',', '.')))));
								params.setCorrectedACAmpacity(String.valueOf(new DecimalFormat("#.0")
										.format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(String
												.valueOf(params.getNEC310().getSeventyFiveInsulation()).replace(',', '.')))));
							} catch (Exception e) {
								e.printStackTrace();
							}

						}

					} else
						acNumberOfConductors.add(1);

				}
			} else {

				Boolean existingTradeSize = existingACTradeSize(form, i, sheetIndex, acCircuit, permitEntity, circuit,
						permitProjectSiteInfo, params.getIncorrectACTradeSize());
				if (Boolean.FALSE.equals(existingTradeSize)) {
					if (params.getNEC310() != null) {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", params.getNEC310().getTradeSze());
						params.getIncorrectACTradeSize().add(params.getNEC310().getTradeSze());
						acTradeSize.add(params.getNEC310().getTradeSze());
					} else {
						form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "");
						params.setIncorrectACTradeSize(new ArrayList<>());
						acTradeSize.add("");
					}
				}
				if (params.getNEC310() != null) {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL",
							"(" + params.getNEC310().getNumberOfConductors() + ") CU");
					params.setCalculatedACNumberOfConductor(params.getNEC310().getNumberOfConductors());
					acNumberOfConductors.add(params.getNEC310().getNumberOfConductors());
				} else {
					form.setField(sheetIndex + "-" + "AC" + i + "-MATERIAL", "CU");
					params.setCalculatedACNumberOfConductor(1);
					acNumberOfConductors.add(1);
				}

				if (params.getNEC310() != null && params.getNEC310().getSeventyFiveInsulation() != null) {
					Boolean existConductorAmpacity = existConductorAmpacityRating(form, i, sheetIndex, acCircuit,
							permitEntity, circuit, permitProjectSiteInfo);

					if (Boolean.FALSE.equals(existConductorAmpacity)) {
						form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1",
								String.valueOf(params.getNEC310().getSeventyFiveInsulation()));
					}

					form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING",
							String.valueOf(params.getNEC310().getSeventyFiveInsulation()));
					form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY",
							String.valueOf(
									new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
											String.valueOf(params.getNEC310().getSeventyFiveInsulation()).replace(',', '.')))));
					form.setField(sheetIndex + "-" + "AC" + i + "-CORRECTED-AMPACITY1",
							String.valueOf(
									new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
											String.valueOf(params.getNEC310().getSeventyFiveInsulation()).replace(',', '.')))));
					params.setCorrectedACAmpacity(String
							.valueOf(new DecimalFormat("#.0").format(Float.parseFloat(params.getTempDerating()) * Float.parseFloat(
									String.valueOf(params.getNEC310().getSeventyFiveInsulation()).replace(',', '.')))));
				}
			}


		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
		}
		return params;
	}

	private Boolean setExistACTradeSize(AcroFields form, int i, int sheetIndex, List<String> acTradeSize) {
		try {
			form.setField(sheetIndex + "-" + "AC" + i + "-TRADE-SIZE", "EXISTING");
			acTradeSize.add("EXISTING");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return false;
		}
	}

	private Boolean existingACTradeSize(AcroFields form, int i, int sheetIndex, String acCircuit,
			PermitEntity permitEntity, PermitConduitConductorSectionEntity circuit,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, List<String> acTradeSize) {

		try {
			if (checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")
					&& (checkValue.Equals(circuit.getConductorTypeSixExisting(), true)
							|| checkValue.Equals(permitEntity.getExistInverter(), true)
							|| checkValue.Equals(circuit.getConductorSizeSix(), "EXIST"))) {
				return setExistACTradeSize(form, i, sheetIndex, acTradeSize);
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
					&& (checkValue.Equals(circuit.getConductorTypeSevenExisting(), true)
							|| checkValue.Equals(permitEntity.getExistAcJunctionBox(), true)
							|| checkValue.Equals(circuit.getConductorSizeSeven(), "EXIST"))) {
				return setExistACTradeSize(form, i, sheetIndex, acTradeSize);
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
					&& (checkValue.Equals(circuit.getConductorTypeEightExisting(), true)
							|| checkValue.Equals(permitEntity.getExistAcCombiner(), true)
							|| checkValue.Equals(circuit.getConductorSizeEight(), "EXIST"))) {
				return setExistACTradeSize(form, i, sheetIndex, acTradeSize);
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
					&& (checkValue.Equals(circuit.getConductorTypeNineExisting(), true)
							|| checkValue.Equals(permitEntity.getExistAcDisconnect(), true)
							|| checkValue.Equals(circuit.getConductorSizeNine(), "EXIST"))) {
				return setExistACTradeSize(form, i, sheetIndex, acTradeSize);
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
					&& (checkValue.Equals(circuit.getConductorTypeNineTwoExisting(), true)
							|| checkValue.Equals(permitEntity.getExistAcDisconnect(), true)
							|| checkValue.Equals(circuit.getConductorSizeNineTwo(), "EXIST"))) {
				return setExistACTradeSize(form, i, sheetIndex, acTradeSize);
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
					&& (checkValue.Equals(circuit.getConductorTypeTenExisting(), true)
							|| checkValue.Equals(permitEntity.getExistProductionMeter(), true)
							|| checkValue.Equals(circuit.getConductorSizeTen(), "EXIST"))) {
				return setExistACTradeSize(form, i, sheetIndex, acTradeSize);
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
					&& (checkValue.Equals(circuit.getConductorTypeElevenExisting(), true)
							|| checkValue.Equals(permitEntity.getExistSubPanel(), true)
							||  checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSize(), "EXIST")
							|| checkValue.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), EXISTING_SUB_PANEL))) {
				return setExistACTradeSize(form, i, sheetIndex, acTradeSize);
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return false;
		}
	}

	private Boolean setExistConductorAmpacity(AcroFields form, int i, int sheetIndex) {
		try {
			form.setField(sheetIndex + "-" + "AC" + i + "-CONDUCTOR-AMPACTIY-RATING1", "EXISTING");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return false;
		}
	}

	private Boolean existConductorAmpacityRating(AcroFields form, int i, int sheetIndex, String acCircuit,
			PermitEntity permitEntity, PermitConduitConductorSectionEntity circuit,
			PermitProjectSiteInfoEntity permitProjectSiteInfo) {
		try {
			if (checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")
					&& ((Boolean.TRUE.equals(circuit.getConductorTypeSixExisting()))
							|| (Boolean.TRUE.equals(permitEntity.getExistInverter())))) {
				return setExistConductorAmpacity(form, i, sheetIndex);
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
					&& ((Boolean.TRUE.equals(circuit.getConductorTypeSevenExisting()))
							|| (Boolean.TRUE.equals(permitEntity.getExistAcJunctionBox())))) {
				return setExistConductorAmpacity(form, i, sheetIndex);
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
					&& ((Boolean.TRUE.equals(circuit.getConductorTypeEightExisting()))
							|| (Boolean.TRUE.equals(permitEntity.getExistAcCombiner())))) {
				return setExistConductorAmpacity(form, i, sheetIndex);
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
					&& ((Boolean.TRUE.equals(circuit.getConductorTypeNineExisting()))
							|| (Boolean.TRUE.equals(permitEntity.getExistAcDisconnect())))) {
				return setExistConductorAmpacity(form, i, sheetIndex);
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
					&& ((Boolean.TRUE.equals(circuit.getConductorTypeNineTwoExisting()))
							|| (Boolean.TRUE.equals(permitEntity.getExistAcDisconnect())))) {
				return setExistConductorAmpacity(form, i, sheetIndex);
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
					&& ((Boolean.TRUE.equals(circuit.getConductorTypeTenExisting()))
							|| (Boolean.TRUE.equals(permitEntity.getExistProductionMeter())))) {
				return setExistConductorAmpacity(form, i, sheetIndex);
			} else if (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
					&& ((Boolean.TRUE.equals(circuit.getConductorTypeElevenExisting()))
							|| (Boolean.TRUE.equals(permitEntity.getExistSubPanel()) || checkValue
									.Equals(permitProjectSiteInfo.getSubPanelConductorSizing(), EXISTING_SUB_PANEL)))) {
				return setExistConductorAmpacity(form, i, sheetIndex);
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblem.traiterException(e);
			return false;
		}
	}

	public Integer numberOfUnderGround(AcroFields form, int i, String acCircuit,
			PermitConduitConductorSectionEntity circuit, int sheetIndex, PermitHomeSiteInfoEntity permitHomeSite,
			Integer calculatedACNumberOfConductor) {
		try {
			if ((checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")
					&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
					&& checkValue.notChecked(circuit.getConductorNeutralSix()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
							&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralSeven()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
							&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralEight()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
							&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralNine()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
							&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralNineTwo()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
							&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralTen()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
							&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralEleven()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "TRANSFORMER")
							&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralTwelve()))) {
				form.setField(sheetIndex + "-" + "AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", String.valueOf(2));
				return 2;
			} else if ((checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")
					&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
					&& Boolean.TRUE.equals(circuit.getConductorNeutralSix()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
							&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
							&& Boolean.TRUE.equals(circuit.getConductorNeutralSeven()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
							&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
							&& Boolean.TRUE.equals(circuit.getConductorNeutralEight()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
							&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
							&& Boolean.TRUE.equals(circuit.getConductorNeutralNine()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
							&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
							&& Boolean.TRUE.equals(circuit.getConductorNeutralNineTwo()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
							&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
							&& Boolean.TRUE.equals(circuit.getConductorNeutralTen()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
							&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
							&& Boolean.TRUE.equals(circuit.getConductorNeutralEleven()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "TRANSFORMER")
							&& Boolean.TRUE.equals(permitHomeSite.getIfServiceVoltage())
							&& Boolean.TRUE.equals(circuit.getConductorNeutralTwelve()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralSix()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralSeven()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralEight()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralNine()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralNineTwo()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralTen()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralEleven()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "TRANSFORMER")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& checkValue.notChecked(circuit.getConductorNeutralTwelve()))) {
				form.setField(sheetIndex + "-" + "AC" + i + "-NUMBER-OF-UNGROUNDED-CONDUCTORS", String.valueOf(3));
				return 3;
			} else if ((checkValue.Equals(acCircuit.split("-")[i - 1], "INVERTER")
					&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
					&& Boolean.TRUE.equals(circuit.getConductorNeutralSix()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "JUNCTION BOX")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& Boolean.TRUE.equals(circuit.getConductorNeutralSeven()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC COMBINER/LOAD CENTER")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& Boolean.TRUE.equals(circuit.getConductorNeutralEight()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECT")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& Boolean.TRUE.equals(circuit.getConductorNeutralNine()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "AC DISCONNECTTwo")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& Boolean.TRUE.equals(circuit.getConductorNeutralNineTwo()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "PRODUCTION METER")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& Boolean.TRUE.equals(circuit.getConductorNeutralTen()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "SUB PANEL")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& Boolean.TRUE.equals(circuit.getConductorNeutralEleven()))
					|| (checkValue.Equals(acCircuit.split("-")[i - 1], "TRANSFORMER")
							&& checkValue.notChecked(permitHomeSite.getIfServiceVoltage())
							&& Boolean.TRUE.equals(circuit.getConductorNeutralTwelve()))) {
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

