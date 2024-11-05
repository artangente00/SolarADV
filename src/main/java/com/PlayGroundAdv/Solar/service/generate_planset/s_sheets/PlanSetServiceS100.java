package com.PlayGroundAdv.Solar.service.generate_planset.s_sheets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.Engineers;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitDrafterDataEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.RoofMaterialType;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.model.AHJNotesModel;
import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.service.generate_planset.PlansetLogo_SignatureMappingService;
import com.PlayGroundAdv.Solar.service.generate_planset.drafter_details.DrfaterDetailsMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ElectricUtilityNumber;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ModuleInverterMfgQty;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
@Transactional
public class PlanSetServiceS100 {

	final CheckValueTypesService checkValue;
	final GetPDFReaderService getPDFReaderService;
	final PlansetLogo_SignatureMappingService logoSignatureMapping;
	final DrfaterDetailsMapping drfaterDetailsMapping;
	final ModuleInverterMfgQty moduleInverterMfgQty;
	final TechnicalProblemMsg technicalProblemMsg;
	final ElectricUtilityNumber electricUtilityNumber;

	public PlanSetServiceS100(CheckValueTypesService checkValue, 
			GetPDFReaderService getPDFReaderService, PlansetLogo_SignatureMappingService logoSignatureMapping,
			DrfaterDetailsMapping drfaterDetailsMapping, ModuleInverterMfgQty moduleInverterMfgQty,
			TechnicalProblemMsg technicalProblemMsg, ElectricUtilityNumber electricUtilityNumber) {
		super();
		this.checkValue = checkValue;
		this.getPDFReaderService = getPDFReaderService;
		this.logoSignatureMapping = logoSignatureMapping;
		this.drfaterDetailsMapping = drfaterDetailsMapping;
		this.moduleInverterMfgQty = moduleInverterMfgQty;
		this.technicalProblemMsg = technicalProblemMsg;
		this.electricUtilityNumber = electricUtilityNumber;
	}

	String rackingMaxPsf = "";
	String moduleWeight = "";
	String microWeight = "";
	String optimizerWeight = "";
	Float qtyModule = 0f;
	Float qtyMicro = 0f;
	Float qtyOptimizer = 0f;
	Float totalSquareFootageOfArray = 1f;

	public void mapModuleInfo(Cmodulev2 moduleInfo, Inverters microInverterInfo,
			DCOptimizerEntity dcOptimizer, PlansetUtils plansetUtils) {
		try {
			if (moduleInfo != null) {

				Float widthFloat = 0f;
				Float lengthFloat = 0f;
				String width = "";
				if (moduleInfo.getWidth() != null && moduleInfo.getWidth().contains(",")) {
					width = moduleInfo.getWidth().replace(",", ".");
					widthFloat = Float.parseFloat(width);
				} else if (checkValue.NotEquals(moduleInfo.getWidth(), "XX")) {
					width = moduleInfo.getWidth();
					widthFloat = Float.parseFloat(width);
				}

				String length = "";
				if (moduleInfo.getLength() != null && moduleInfo.getLength().contains(",")) {
					length = moduleInfo.getLength().replace(",", ".");
					lengthFloat = Float.parseFloat(length);
				} else if (checkValue.NotEquals(moduleInfo.getLength(), "XX")) {
					length = moduleInfo.getLength();
					lengthFloat = Float.parseFloat(length);
				}
				totalSquareFootageOfArray = ((widthFloat * lengthFloat) / 144) * plansetUtils.getModuleQty();
			}

			if (moduleInfo != null && checkValue.NotEquals(moduleInfo.getWeight(), "XX")) {
				if (moduleInfo.getWeight().contains(",")) {
					moduleWeight = moduleInfo.getWeight().replace(",", ".");
				} else {
					moduleWeight = moduleInfo.getWeight();
				}
				qtyModule = plansetUtils.getModuleQty() * Float.parseFloat(moduleWeight);
				// - - -Weight Micro - --//
				if (plansetUtils.getModulePerMicroInverter() > 0 && microInverterInfo != null) {
					if (checkValue.NotEquals(microInverterInfo.getWeight(), "<3.5")) {
						if (microInverterInfo.getWeight().contains(",")) {
							microWeight = microInverterInfo.getWeight().replace(",", ".");
						} else {
							microWeight = microInverterInfo.getWeight();
						}
						qtyMicro = plansetUtils.getModulePerMicroInverter() * Float.parseFloat(microWeight);
					} else {
						qtyMicro = plansetUtils.getModulePerMicroInverter() * 1.5f;
					}
				}

				// - - -Weight Optimizer - --//
				if (plansetUtils.getQtyDCConverter() > 0) {
					if (dcOptimizer != null && checkValue.NotEquals(dcOptimizer.getWeight(), "TBD")) {
						if (dcOptimizer.getWeight().contains(",")) {
							optimizerWeight = dcOptimizer.getWeight().replace(",", ".");
						} else {
							optimizerWeight = dcOptimizer.getWeight();
						}
						qtyOptimizer = plansetUtils.getQtyDCConverter() * Float.parseFloat(optimizerWeight);
					} else {
						qtyOptimizer = plansetUtils.getQtyDCConverter() * 1.2f;
					}
				}
				if (totalSquareFootageOfArray != 0.0 && totalSquareFootageOfArray != 0f) {
					rackingMaxPsf = String
							.valueOf(new DecimalFormat("##.##")
									.format(((qtyModule + qtyMicro + qtyOptimizer) / totalSquareFootageOfArray) + 0.5))
							+ " PSF";
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapHomeInfo(AcroFields form, int sheetIndex, PermitHomeSiteInfoEntity permitHomeSite,
			ElectricalUtilityEntity electricalCompany, PermitArrayEntityResultSecond permitArraysEntityResult) {
		try {

			electricUtilityNumber.mapEsiId(form, sheetIndex, "S100", permitHomeSite, electricalCompany);
			if (checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)) {
				if (permitHomeSite.getStanchionMaxSpacing() != null
						&& checkValue.NotEquals(permitHomeSite.getStanchionMaxSpacing(), "")) {
					String maxSpan = permitHomeSite.getStanchionMaxSpacing().equals("24") ? "2’-0”"
							: (permitHomeSite.getStanchionMaxSpacing().equals("48") ? "4’-0”"
									: (permitHomeSite.getStanchionMaxSpacing().equals("72") ? "6’-0”"
											: (permitHomeSite.getStanchionMaxSpacing().equals("96") ? "8’-0”"
													: permitHomeSite.getStanchionMaxSpacingOther())));
					form.setField(sheetIndex + "-" + "S-100-Note-A",
							"A.  FOR MANUFACTURED PLATED WOOD TRUSSES AT SLOPES OF FLAT TO 6:12, THE HORIZONTAL ANCHOR SPACING SHALL NOT EXCEED "
									+ maxSpan
									+ " AND ANCHORS IN ADJACENT ROWS SHALL BE STAGGERED. UNLESS NOTED OTHERWISE PER RACKING MANUFACTURER CERTIFIED ENGINEERED PRODUCT AND LOCAL REQUIREMENTS.");

				}
			}

		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	// *******25/03/2019 : CI :CR 2389 Update S-100 Existing Roof Construction
	// Mapping******
	public void mapRoofStructural(PermitProjectSiteInfoEntity permitProjectSiteInfo,
			PermitHomeSiteInfoEntity permitHomeSite, AcroFields form,
			PermitArrayEntityResultSecond permitArraysEntityResult, RoofMaterialType roofMaterialType,
			PermitAdvEntityResult permitAdvEntityInfo, Engineers engineer, int sheetIndex) {
		try {
			if (permitProjectSiteInfo != null && permitHomeSite != null && permitArraysEntityResult != null
					&& checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)) {
				if (permitAdvEntityInfo != null
						&& ((checkValue.NotEquals(permitAdvEntityInfo.getSnowLoad(), "")
								&& checkValue.isStringInt(permitAdvEntityInfo.getSnowLoad())
								&& Integer.parseInt(permitAdvEntityInfo.getSnowLoad()) < 15)
								|| (checkValue.Equals(permitAdvEntityInfo.getSnowLoad(), "Other")
										&& checkValue.NotEquals(permitAdvEntityInfo.getSnowLoadOther(), "")
										&& checkValue.isNumeric(permitAdvEntityInfo.getSnowLoadOther())
										&& Float.valueOf(permitAdvEntityInfo.getSnowLoadOther()) < 15))
						&& (engineer != null && checkValue.Equals(engineer.getCompany(), "Vector Engineers"))) {

					String roofRafter = "";
					if (checkValue.Equals(permitProjectSiteInfo.getRafterTrussSpacing(), "Other")) {
						String guillemet = "\"";
						if (permitProjectSiteInfo.getTextOtherRatfter() != null
								&& permitProjectSiteInfo.getTextOtherRatfter().length() > 0) {
							if (checkValue.Equals(permitProjectSiteInfo.getTextOtherRatfter()
									.substring(permitProjectSiteInfo.getTextOtherRatfter().length() - 1), guillemet)) {
								// fform.setField(sheetIndexProperty("EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
								// "textsize", new Float(11), null);
								if (permitHomeSite.getRoofRafter() != null) {
									if (checkValue.Equals(permitHomeSite.getRoofRafter(),
											"Rafter - Strut to Walls Below")) {
										roofRafter = "Framing - Strut to Walls Below";
									} else if (checkValue.Equals(permitHomeSite.getRoofRafter(),
											"Rafter - Cathedral Ceiling")) {
										roofRafter = "Framing - Cathedral Ceiling";
									} else if (checkValue.Equals(permitHomeSite.getRoofRafter(),
											"Rafter-Simple Attic")) {
										roofRafter = "Framing - Simple Attic";
									} else if (checkValue.Equals(permitHomeSite.getRoofRafter(),
											"Pre-Eng Roof Trusses")) {
										roofRafter = "Pre-Eng Roof Framing";
									}
									form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
											roofRafter + " " + permitProjectSiteInfo.getTextOtherRatfter() + " O.C.");
								} else if (checkValue.NotEquals(permitHomeSite.getRoofRafterOther(), "Other")) {
									if (checkValue.Equals(permitHomeSite.getRoofRafterOther(),
											"Flat Roof With Trusses")) {
										form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
												"Flat Roof With Framing " + permitProjectSiteInfo.getTextOtherRatfter()
														+ " O.C.");
									} else
										form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
												permitHomeSite.getRoofRafterOther() + " "
														+ permitProjectSiteInfo.getTextOtherRatfter() + " O.C.");
								} else if (checkValue.Equals(permitHomeSite.getRoofRafterOther(), "Other")
										&& permitHomeSite.getSecroofRafterOther() != null) {
									form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
											permitHomeSite.getSecroofRafterOther() + " "
													+ permitProjectSiteInfo.getTextOtherRatfter() + " O.C.");
								}
							} else {
								if (permitHomeSite.getRoofRafter() != null) {
									if (checkValue.Equals(permitHomeSite.getRoofRafter(),
											"Rafter - Strut to Walls Below")) {
										roofRafter = "Framing - Strut to Walls Below";
									} else if (checkValue.Equals(permitHomeSite.getRoofRafter(),
											"Rafter - Cathedral Ceiling")) {
										roofRafter = "Framing - Cathedral Ceiling";
									} else if (checkValue.Equals(permitHomeSite.getRoofRafter(),
											"Rafter-Simple Attic")) {
										roofRafter = "Framing - Simple Attic";
									} else if (checkValue.Equals(permitHomeSite.getRoofRafter(),
											"Pre-Eng Roof Trusses")) {
										roofRafter = "Pre-Eng Roof Framing";
									}
									form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION", roofRafter
											+ " " + permitProjectSiteInfo.getTextOtherRatfter() + "\"" + " O.C.");
								} else if (checkValue.NotEquals(permitHomeSite.getRoofRafterOther(), "Other")) {
									if (checkValue.Equals(permitHomeSite.getRoofRafterOther(),
											"Flat Roof With Trusses")) {
										form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
												"Flat Roof With Framing " + permitProjectSiteInfo.getTextOtherRatfter()
														+ "\"" + " O.C.");
									} else
										form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
												permitHomeSite.getRoofRafterOther() + " "
														+ permitProjectSiteInfo.getTextOtherRatfter() + "\"" + " O.C.");
								} else if (checkValue.Equals(permitHomeSite.getRoofRafterOther(), "Other")
										&& permitHomeSite.getSecroofRafterOther() != null) {
									form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
											permitHomeSite.getSecroofRafterOther() + " "
													+ permitProjectSiteInfo.getTextOtherRatfter() + "\"" + " O.C.");
								}
							}
						}
					} else {
						if (checkValue.NotEquals(permitProjectSiteInfo.getRafterTrussSpacing(), "")) {
							if (permitHomeSite.getRoofRafter() != null) {
								if (checkValue.Equals(permitHomeSite.getRoofRafter(),
										"Rafter - Strut to Walls Below")) {
									roofRafter = "Framing - Strut to Walls Below";
								} else if (checkValue.Equals(permitHomeSite.getRoofRafter(),
										"Rafter - Cathedral Ceiling")) {
									roofRafter = "Framing - Cathedral Ceiling";
								} else if (checkValue.Equals(permitHomeSite.getRoofRafter(), "Rafter-Simple Attic")) {
									roofRafter = "Framing - Simple Attic";
								} else if (checkValue.Equals(permitHomeSite.getRoofRafter(), "Pre-Eng Roof Trusses")) {
									roofRafter = "Pre-Eng Roof Framing";
								}
								form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION", roofRafter
										+ " " + permitProjectSiteInfo.getRafterTrussSpacing() + "\"" + " O.C.");
							} else if (checkValue.NotEquals(permitHomeSite.getRoofRafterOther(), "Other")) {
								if (checkValue.Equals(permitHomeSite.getRoofRafterOther(), "Flat Roof With Trusses")) {
									form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
											"Flat Roof With Framing " + permitProjectSiteInfo.getRafterTrussSpacing()
													+ "\"" + " O.C.");
								} else
									form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
											permitHomeSite.getRoofRafterOther() + " "
													+ permitProjectSiteInfo.getRafterTrussSpacing() + "\"" + " O.C.");
							} else if (checkValue.Equals(permitHomeSite.getRoofRafterOther(), "Other")
									&& permitHomeSite.getSecroofRafterOther() != null) {
								form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
										permitHomeSite.getSecroofRafterOther() + " "
												+ permitProjectSiteInfo.getRafterTrussSpacing() + "\"" + " O.C.");
							}
						}
					}
					/// --- CR 800 planset mapping update ---///

					String roofStructrual = "";
					String rafterTrussSpacing = "";

					if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(), "OtherSize")) {
						if (checkValue.NotEquals(permitProjectSiteInfo.getTextOtherSize(), "")
								&& permitProjectSiteInfo.getTextOtherSize().contains("x")
								&& permitProjectSiteInfo.getTextOtherSize().split("x").length > 0) {
							String roofStructrual1 = permitProjectSiteInfo.getTextOtherSize().split("x")[0];
							String roofStructrual2 = permitProjectSiteInfo.getTextOtherSize().split("x").length == 2
									? permitProjectSiteInfo.getTextOtherSize().split("x")[1]
									: "";
							if (checkValue.isStringNotEmpty(roofStructrual1) && !roofStructrual1.contains("\"")) {
								roofStructrual1 = roofStructrual1 + "\"";
							}
							if (checkValue.isStringNotEmpty(roofStructrual2) && !roofStructrual2.contains("\"")) {
								roofStructrual2 = roofStructrual2 + "\"";
							}
							roofStructrual = roofStructrual1 + "x" + roofStructrual2;
						} else {
							roofStructrual = permitProjectSiteInfo.getTextOtherSize();
						}
					} else if (permitProjectSiteInfo.getCrossSectionSize() != null
							&& permitProjectSiteInfo.getCrossSectionSize().contains("x")
							&& permitProjectSiteInfo.getCrossSectionSize().split("x").length > 0) {
						roofStructrual = permitProjectSiteInfo.getCrossSectionSize().split("x")[0] + "\"" + "x";
					}

					if (checkValue.Equals(permitProjectSiteInfo.getRafterTrussSpacing(), "Other")) {
						if (checkValue.NotEquals(permitProjectSiteInfo.getTextOtherRatfter(), "")) {
							if (permitProjectSiteInfo.getTextOtherRatfter().contains("\"")) {
								rafterTrussSpacing = permitProjectSiteInfo.getTextOtherRatfter();
							} else
								rafterTrussSpacing = permitProjectSiteInfo.getTextOtherRatfter() + "\"";
						}
					} else if (checkValue.NotEquals(permitProjectSiteInfo.getRafterTrussSpacing(), "")) {
						rafterTrussSpacing = permitProjectSiteInfo.getRafterTrussSpacing() + "\"";
					}

					form.setField(sheetIndex + "-" + "FRAMING-INFO",
							roofStructrual + " FRAMING @ MAX " + rafterTrussSpacing + " OC");

					/// ------------------------------------///
					if (roofMaterialType != null && roofMaterialType.getMappingValue() != null) {
						form.setField(sheetIndex + "-" + "EXISTING-ROOFING-COVERING",
								roofMaterialType.getMappingValue());
					}
				} else if (permitAdvEntityInfo != null && (((checkValue.NotEquals(permitAdvEntityInfo.getSnowLoad(), "")
						&& checkValue.isStringInt(permitAdvEntityInfo.getSnowLoad())
						&& Integer.parseInt(permitAdvEntityInfo.getSnowLoad()) >= 15)
						|| (checkValue.Equals(permitAdvEntityInfo.getSnowLoad(), "Other")
								&& checkValue.NotEquals(permitAdvEntityInfo.getSnowLoadOther(), "")
								&& checkValue.isNumeric(permitAdvEntityInfo.getSnowLoadOther())
								&& Float.valueOf(permitAdvEntityInfo.getSnowLoadOther()) >= 15))
						|| (((checkValue.NotEquals(permitAdvEntityInfo.getSnowLoad(), "")
								&& checkValue.isStringInt(permitAdvEntityInfo.getSnowLoad())
								&& Integer.parseInt(permitAdvEntityInfo.getSnowLoad()) < 15)
								|| (checkValue.Equals(permitAdvEntityInfo.getSnowLoad(), "Other")
										&& checkValue.NotEquals(permitAdvEntityInfo.getSnowLoadOther(), "")
										&& checkValue.isNumeric(permitAdvEntityInfo.getSnowLoadOther())
										&& Float.valueOf(permitAdvEntityInfo.getSnowLoadOther()) < 15))
								&& (engineer == null || (engineer != null
										&& checkValue.NotEquals(engineer.getCompany(), "Vector Engineers")))))) {

					if (checkValue.Equals(permitProjectSiteInfo.getRafterTrussSpacing(), "Other")) {
						String guillemet = "\"";
						if (permitProjectSiteInfo.getTextOtherRatfter() != null
								&& permitProjectSiteInfo.getTextOtherRatfter().length() > 0) {
							if (checkValue.Equals(permitProjectSiteInfo.getTextOtherRatfter()
									.substring(permitProjectSiteInfo.getTextOtherRatfter().length() - 1), guillemet)) {
								// fform.setField(sheetIndexProperty("EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
								// "textsize", new Float(11), null);
								if (permitHomeSite.getRoofRafter() != null) {
									form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
											permitHomeSite.getRoofRafter() + " "
													+ permitProjectSiteInfo.getTextOtherRatfter() + " O.C.");
								} else if (checkValue.NotEquals(permitHomeSite.getRoofRafterOther(), "Other")) {
									form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
											permitHomeSite.getRoofRafterOther() + " "
													+ permitProjectSiteInfo.getTextOtherRatfter() + " O.C.");
								} else if (checkValue.Equals(permitHomeSite.getRoofRafterOther(), "Other")
										&& permitHomeSite.getSecroofRafterOther() != null) {
									form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
											permitHomeSite.getSecroofRafterOther() + " "
													+ permitProjectSiteInfo.getTextOtherRatfter() + " O.C.");
								}
							} else {
								if (permitHomeSite.getRoofRafter() != null) {
									form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
											permitHomeSite.getRoofRafter() + " "
													+ permitProjectSiteInfo.getTextOtherRatfter() + "\"" + " O.C.");
								} else if (checkValue.NotEquals(permitHomeSite.getRoofRafterOther(), "Other")) {
									form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
											permitHomeSite.getRoofRafterOther() + " "
													+ permitProjectSiteInfo.getTextOtherRatfter() + "\"" + " O.C.");
								} else if (checkValue.Equals(permitHomeSite.getRoofRafterOther(), "Other")
										&& permitHomeSite.getSecroofRafterOther() != null) {
									form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
											permitHomeSite.getSecroofRafterOther() + " "
													+ permitProjectSiteInfo.getTextOtherRatfter() + "\"" + " O.C.");
								}

							}
						} else {
							if (permitHomeSite.getRoofRafter() != null) {
								form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
										permitHomeSite.getRoofRafter() + " "
												+ permitProjectSiteInfo.getRafterTrussSpacing() + "\"" + " O.C.");
							} else if (checkValue.NotEquals(permitHomeSite.getRoofRafterOther(), "Other")) {
								form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
										permitHomeSite.getRoofRafterOther() + " "
												+ permitProjectSiteInfo.getRafterTrussSpacing() + "\"" + " O.C.");
							} else if (checkValue.Equals(permitHomeSite.getRoofRafterOther(), "Other")
									&& permitHomeSite.getSecroofRafterOther() != null) {
								form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
										permitHomeSite.getSecroofRafterOther() + " "
												+ permitProjectSiteInfo.getRafterTrussSpacing() + "\"" + " O.C.");
							}
						}
					} else {
						if (permitHomeSite.getRoofRafter() != null) {
							form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
									permitHomeSite.getRoofRafter() + " " + permitProjectSiteInfo.getRafterTrussSpacing()
											+ "\"" + " O.C.");
						} else if (checkValue.NotEquals(permitHomeSite.getRoofRafterOther(), "Other")) {
							form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
									permitHomeSite.getRoofRafterOther() + " "
											+ permitProjectSiteInfo.getRafterTrussSpacing() + "\"" + " O.C.");
						} else if (checkValue.Equals(permitHomeSite.getRoofRafterOther(), "Other")
								&& permitHomeSite.getSecroofRafterOther() != null) {
							form.setField(sheetIndex + "-" + "EXISTING-ROOF-STRUCTURAL-CONSTRUCTION",
									permitHomeSite.getSecroofRafterOther() + " "
											+ permitProjectSiteInfo.getRafterTrussSpacing() + "\"" + " O.C.");
						}
					}
					String roofStructrual = "";
					String rafterTrussSpacing = "";

					if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(), "OtherSize")) {

						if (checkValue.NotEquals(permitProjectSiteInfo.getTextOtherSize(), "")
								&& permitProjectSiteInfo.getTextOtherSize().contains("x")
								&& permitProjectSiteInfo.getTextOtherSize().split("x").length > 0) {
							String roofStructrual1 = permitProjectSiteInfo.getTextOtherSize().split("x")[0];
							String roofStructrual2 = permitProjectSiteInfo.getTextOtherSize().split("x").length == 2
									? permitProjectSiteInfo.getTextOtherSize().split("x")[1]
									: "";
							if (checkValue.isStringNotEmpty(roofStructrual1) && !roofStructrual1.contains("\"")) {
								roofStructrual1 = roofStructrual1 + "\"";
							}
							if (checkValue.isStringNotEmpty(roofStructrual2) && !roofStructrual2.contains("\"")) {
								roofStructrual2 = roofStructrual2 + "\"";
							}
							roofStructrual = roofStructrual1 + "x" + roofStructrual2;
						} else {
							roofStructrual = permitProjectSiteInfo.getTextOtherSize();
						}

					} else if (permitProjectSiteInfo.getCrossSectionSize() != null) {
						if (permitProjectSiteInfo.getCrossSectionSize().contains("x")
								&& permitProjectSiteInfo.getCrossSectionSize().split("x").length > 0) {
							String roofStructrual1 = permitProjectSiteInfo.getCrossSectionSize().split("x")[0] + "\"";
							String roofStructrual2 = permitProjectSiteInfo.getCrossSectionSize().split("x").length == 2
									? permitProjectSiteInfo.getCrossSectionSize().split("x")[1] + "\""
									: "";
							roofStructrual = roofStructrual1 + "x" + roofStructrual2;
						} else {
							roofStructrual = permitProjectSiteInfo.getCrossSectionSize();
						}
					}

					if (checkValue.Equals(permitProjectSiteInfo.getRafterTrussSpacing(), "Other")) {
						if (checkValue.NotEquals(permitProjectSiteInfo.getTextOtherRatfter(), "")) {
							if (permitProjectSiteInfo.getTextOtherRatfter().contains("\"")) {
								rafterTrussSpacing = permitProjectSiteInfo.getTextOtherRatfter();
							} else
								rafterTrussSpacing = permitProjectSiteInfo.getTextOtherRatfter() + "\"";
						}
					} else if (checkValue.NotEquals(permitProjectSiteInfo.getRafterTrussSpacing(), "")) {
						rafterTrussSpacing = permitProjectSiteInfo.getRafterTrussSpacing() + "\"";
					}
					// F.S 7/28/20 REV-CR-2712
					form.setField(sheetIndex + "-" + "FRAMING-INFO",
							roofStructrual + " @ " + rafterTrussSpacing + " MAX OC");

					/// ------------------------------------///
					if (roofMaterialType != null && roofMaterialType.getMappingValue() != null) {
						form.setField(sheetIndex + "-" + "EXISTING-ROOFING-COVERING",
								roofMaterialType.getMappingValue());
					}
				}
			}
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public File buildingPDFS100(PermitHomeSiteInfoEntity permitHomeSite,
			PermitArrayEntityResultSecond permitArraysEntityResult,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, PermitEntity permitEntity,
			PermitAdvEntityResult permitAdvEntityInfo, PermitDrafterDataEntity permitDrafterDatanfo,
			Cmodulev2 moduleInfo, Inverters microInverterInfo, DCOptimizerEntity dcOptimizer,
			PlansetUtils plansetUtils, ElectricalUtilityEntity electricalCompany,
			RoofMaterialType roofMaterialType, Engineers engineer, PdfReader reader, int sheetIndex, String filePath,
			AHJNotesModel ahjNotes, Inverters inverterInfo) {

		// you only need a PdfStamper if you're going to change the existing PDF.
		File fileRe = null;
		Long idPermit = permitEntity.getId();
		rackingMaxPsf = "";
		moduleWeight = "";
		microWeight = "";
		optimizerWeight = "";
		qtyModule = 0f;
		qtyMicro = 0f;
		qtyOptimizer = 0f;
		totalSquareFootageOfArray = 1f;

		try {

			fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-S100" + idPermit + "-" + sheetIndex + ".pdf");
			if (fileRe.exists()) {
				fileRe.delete();
				fileRe = new File(
						Constants.rapportPlansetFolderUrl + "PDF-S100" + idPermit + "-" + sheetIndex + ".pdf");
			}
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
			AcroFields form = stamper.getAcroFields();
			
			//A.B remove sheet index if exist when the project was uploaded
			if(permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
				getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
			}

			PdfReader readerOriginNEC = new PdfReader(Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-S100.pdf");
			PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-S100.pdf");
			// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
			getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "S100");
			// A.B: Set PDF Font For Revision
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
					sheetIndex);
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
					sheetIndex);

			// A.B CR-3250 03-30 Logo & Signature Mapping
			logoSignatureMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);

			// A.B 07-14-2021 CR-3064
			form.setField(sheetIndex + "-" + "SPECIAL-NOTE-S-100", ahjNotes.getS100Note());

			mapModuleInfo(moduleInfo, microInverterInfo, dcOptimizer, plansetUtils);
			
			// A.B 01-11-2022 CR-PM-REV-2197-MOD-15
			String utility = checkValue.Equals(permitHomeSite.getUtilityCompanyName(), "Other") ? permitHomeSite.getUtilityCompanyNameOther() : electricalCompany.getUtilityCompanyName();
			moduleInverterMfgQty.moduleInverterMfgQty("-S-100", form, permitArraysEntityResult.getDeviceToIncorporate(), idPermit,
					sheetIndex, inverterInfo, moduleInfo, microInverterInfo, utility, permitHomeSite.getMeterNumber(),    plansetUtils);
			mapHomeInfo(form, sheetIndex, permitHomeSite, electricalCompany, permitArraysEntityResult);

			mapRoofStructural(permitProjectSiteInfo, permitHomeSite, form, permitArraysEntityResult, roofMaterialType,
					permitAdvEntityInfo, engineer, sheetIndex);
			form.setField(sheetIndex + "-" + "SOLAR-MAX-PSF", rackingMaxPsf);
			// A.B 09-22 CR-3438-MOD-002
			drfaterDetailsMapping.s100ScaleMapping(form, sheetIndex, "SCALE", permitDrafterDatanfo);

			if (permitProjectSiteInfo != null) {

				form.setField(sheetIndex + "-" + "PV-RAIL-1-QTY", "");
				form.setField(sheetIndex + "-" + "PV-RAIL-1-LENGTH", "");
				form.setField(sheetIndex + "-" + "PV-RAIL-1-MODEL", "");
				form.setField(sheetIndex + "-" + "PV-RAIL-SPLICE-1-QTY", "");
				form.setField(sheetIndex + "-" + "PV-RAIL-SPLICE-1-LENGTH", "");
				form.setField(sheetIndex + "-" + "PV-RAIL-SPLICE-1-MODEL", "");
				////////////////////////////////////////////////////
				form.setField(sheetIndex + "-" + "PV-RAIL-2-QTY", "");
				form.setField(sheetIndex + "-" + "PV-RAIL-2-LENGTH", "");
				form.setField(sheetIndex + "-" + "PV-RAIL-2-MODEL", "");
				form.setField(sheetIndex + "-" + "PV-RAIL-SPLICE-2-QTY", "");
				form.setField(sheetIndex + "-" + "PV-RAIL-SPLICE-2-LENGTH", "");
				form.setField(sheetIndex + "-" + "PV-RAIL-SPLICE-2-MODEL", "");
				/////////////////////////////////////////////////////////
				form.setField(sheetIndex + "-" + "ROOF-ATTACHMENT-QTY", "");
				form.setField(sheetIndex + "-" + "ROOF-ATTACHMENT-HGT", "");
				form.setField(sheetIndex + "-" + "ROOF-ATTACHMENT-MODEL", "");
			}

			stamper.close();
			reader.close();

		} catch (IOException | DocumentException e) {
			technicalProblemMsg.traiterException(e);
		}
		return fileRe;
	}

}
