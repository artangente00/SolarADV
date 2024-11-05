package com.PlayGroundAdv.Solar.service.generate_planset.s_sheets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.Flashing;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RailRackingOptionsEntity;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.entity.RoofMaterialType;
import com.PlayGroundAdv.Solar.entity.SsheetRackingMappingEntity;
import com.PlayGroundAdv.Solar.entity.SsheetSpacingMappingEntity;
import com.PlayGroundAdv.Solar.model.AHJNotesModel;
import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.repositories.PermitDrafterDataRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.PlansetLogo_SignatureMappingService;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.generate_planset.ssheets.GetRackingSSheets;
import com.PlayGroundAdv.Solar.service.generate_planset.ssheets.GetSpacingSSheets;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.pdfcleanup.PdfCleanUpLocation;
import com.itextpdf.text.pdf.pdfcleanup.PdfCleanUpProcessor;

@Service
@Transactional
public class PlanSetServiceS200 {

	final CheckValueTypesService checkValue;
	final GetPDFReaderService getPDFReaderService;
	final PlansetLogo_SignatureMappingService logoSignatureMapping;
	final GetRackingSSheets getRackingSSheets;
	final GetSpacingSSheets getSpacingSSheets;
	final UserSettingRepository userSettingRepository;
	final TechnicalProblemMsg technicalProblemMsg;
	final PermitDrafterDataRepository permitDrafterDataRepo;
	public PlanSetServiceS200(CheckValueTypesService checkValue, 
			GetPDFReaderService getPDFReaderService, PlansetLogo_SignatureMappingService logoSignatureMapping,
			GetRackingSSheets getRackingSSheets, GetSpacingSSheets getSpacingSSheets, UserSettingRepository userSettingRepository,
			TechnicalProblemMsg technicalProblemMsg, PermitDrafterDataRepository permitDrafterDataRepo) {
		super();
		this.checkValue = checkValue;
		this.getPDFReaderService = getPDFReaderService;
		this.logoSignatureMapping = logoSignatureMapping;
		this.getRackingSSheets = getRackingSSheets;
		this.getSpacingSSheets = getSpacingSSheets;
		this.userSettingRepository = userSettingRepository;
		this.technicalProblemMsg = technicalProblemMsg;
		this.permitDrafterDataRepo = permitDrafterDataRepo;
	}

	Long idUser;
	Long idPermitChange;
	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

	public void mapTableDimensions(PermitAdditionalInfoEntityResult permitAdditionalInfo,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, AcroFields form,
			List<PermitSketchEntity> listPermitSketchEntity, PermitHomeSiteInfoEntity permitHomeSite,
			Cmodulev2 moduleInfo, int sheetIndex) {
		try {
			if ((permitAdditionalInfo != null
					&& checkValue.Equals(permitAdditionalInfo.getTiltLegs(), true))
					|| (listPermitSketchEntity != null && !listPermitSketchEntity.isEmpty()
							&& listPermitSketchEntity.get(0) != null
							&& checkValue.Equals(listPermitSketchEntity.get(0).getModuleTils(), true))) {
				form.setField(sheetIndex + "-PV-MODULE-HEIGHT-ABOVE-ROOF", "12\" - 36\"");
				form.setField(sheetIndex + "-1-PV-MODULE-HEIGHT-ABOVE-ROOF", "12\" - 36\"");
			} else {
				form.setField(sheetIndex + "-PV-MODULE-HEIGHT-ABOVE-ROOF", "3\" - 6\" TYP");
				form.setField(sheetIndex + "-1-PV-MODULE-HEIGHT-ABOVE-ROOF", "3\" - 6\" TYP");
			}

			if (permitHomeSite != null && permitHomeSite.getRidgeBeamDepthAtArrays() != null) {
				if (checkValue.NotEquals(permitHomeSite.getRidgeBeamDepthAtArrays(), "Other")
						&& checkValue.NotEquals(permitHomeSite.getRidgeBeamDepthAtArrays(), "")) {
					form.setField(sheetIndex + "-RIDGE-BEAM-DEPTH",
							permitHomeSite.getRidgeBeamDepthAtArrays() + "\"");
				} else if (permitHomeSite.getRidgeBeamDepthAtArraysOther() != null) {
					form.setField(sheetIndex + "-RIDGE-BEAM-DEPTH",
							permitHomeSite.getRidgeBeamDepthAtArraysOther());
				}
			} else {
				form.setField(sheetIndex + "-RIDGE-BEAM-DEPTH", "");
			}

			if (permitProjectSiteInfo != null && permitProjectSiteInfo.getCrossSectionSize() != null) {
				if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(), "OtherSize")) {
					form.setField(sheetIndex + "-RAFTER-DEPTH", permitProjectSiteInfo.getTextOtherSize());
				} else if (checkValue.contains(permitProjectSiteInfo.getCrossSectionSize(), "x")) {
					form.setField(sheetIndex + "-RAFTER-DEPTH",
							permitProjectSiteInfo.getCrossSectionSize().split("x")[1] + "\"");
				}
			} else {
				form.setField(sheetIndex + "-RAFTER-DEPTH", "");
			}
			if (permitHomeSite != null
					&& checkValue.NotEquals(permitHomeSite.getMaxHorizontalSpanAtArrays(), "")
					&& checkValue.NotEquals(permitHomeSite.getMaxHorizontalSpanAtArraysInches(), "")) {
				form.setField(sheetIndex + "-HS1-HORIZONTAL-SPAN", permitHomeSite.getMaxHorizontalSpanAtArrays()
						+ "'" + "-" + permitHomeSite.getMaxHorizontalSpanAtArraysInches() + "\"");
			} else {
				form.setField(sheetIndex + "-HS1-HORIZONTAL-SPAN", "");
			}
			if (permitHomeSite != null
					&& checkValue.NotEquals(permitHomeSite.getMaxHorizontalSpanAtArraysHS(), "")
					&& checkValue.NotEquals(permitHomeSite.getMaxHorizontalSpanAtArraysHSInches(), "")) {
				form.setField(sheetIndex + "-HS2-HORIZONTAL-SPAN", permitHomeSite.getMaxHorizontalSpanAtArraysHS()
						+ "'" + "-" + permitHomeSite.getMaxHorizontalSpanAtArraysHSInches() + "\"");
			} else {
				form.setField(sheetIndex + "-HS2-HORIZONTAL-SPAN", "");
			}

			if (moduleInfo != null && checkValue.NotEquals(moduleInfo.getLength(), "XX")) {
				if (moduleInfo.getLength() == "0") {
					form.setField(sheetIndex + "-1-D3-STANCHION-OC", new DecimalFormat("##.##").format(0) + "\"");
					form.setField(sheetIndex + "-1-D2-STANCHION-OC", new DecimalFormat("##.##").format(0) + "\"");
					form.setField(sheetIndex + "-1-D1-RAIL-OVERHANG", new DecimalFormat("##.##").format(0) + "\"");
				} else {
					Float Length = 1f;
					if (moduleInfo.getLength().contains(",")) {
						Length = Float.parseFloat(moduleInfo.getLength().replace(",", "."));
					} else if (checkValue.isNumeric(moduleInfo.getLength())) {
						Length = Float.parseFloat(moduleInfo.getLength());
					}

					form.setField(sheetIndex + "-1-D3-STANCHION-OC",
							new DecimalFormat("##.##").format((Length / 2) + 0.25) + "\"");
					form.setField(sheetIndex + "-1-D2-STANCHION-OC",
							new DecimalFormat("##.##").format(Length / 2) + "\"");
					form.setField(sheetIndex + "-1-D1-RAIL-OVERHANG",
							new DecimalFormat("##.##").format(Length / 4) + "\"");

				}
			}
			if (moduleInfo != null && checkValue.NotEquals(moduleInfo.getWidth(), "XX")) {
				Float width = 1f;
				if (moduleInfo.getWidth().contains(",")) {
					width = Float.parseFloat(moduleInfo.getWidth().replace(",", "."));
				} else if (checkValue.isNumeric(moduleInfo.getWidth())) {
					width = Float.parseFloat(moduleInfo.getWidth());
				}

				form.setField(sheetIndex + "-D3-STANCHION-OC",
						new DecimalFormat("##.##").format((width / 2) + 0.25) + "\"");
				form.setField(sheetIndex + "-D2-STANCHION-OC",
						new DecimalFormat("##.##").format(width / 2) + "\"");
				form.setField(sheetIndex + "-D1-RAIL-OVERHANG",
						new DecimalFormat("##.##").format(width / 4) + "\"");

			} else {
				form.setField(sheetIndex + "-D3-STANCHION-OC", "");
				form.setField(sheetIndex + "-D2-STANCHION-OC", "");
				form.setField(sheetIndex + "-D1-RAIL-OVERHANG", "");
			}
		} catch (IOException|DocumentException|NumberFormatException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mappingUpdates(AcroFields form, PermitHomeSiteInfoEntity permitHomeSite,
			RoofMaterialType roofMaterialType, PermitProjectSiteInfoEntity permitProjectSiteInfo, int sheetIndex) {
		try {
			if (permitHomeSite != null
					&& checkValue.NotEquals(permitHomeSite.getMaxHorizontalSpanAtArrays(), "")
					&& checkValue.NotEquals(permitHomeSite.getMaxHorizontalSpanAtArraysInches(), "")) {
				form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", permitHomeSite.getMaxHorizontalSpanAtArrays()
						+ "'" + "-" + permitHomeSite.getMaxHorizontalSpanAtArraysInches() + "\"");
				form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", permitHomeSite.getMaxHorizontalSpanAtArrays()
						+ "'" + "-" + permitHomeSite.getMaxHorizontalSpanAtArraysInches() + "\"");
			} else {
				if (roofMaterialType != null) {
					if (roofMaterialType.getId() == 1
							|| roofMaterialType.getId() == 4
							|| roofMaterialType.getId() == 5
							|| roofMaterialType.getId() == 7
							|| roofMaterialType.getId() == 9
							|| roofMaterialType.getId() == 13
							|| roofMaterialType.getId() == 14
							|| roofMaterialType.getId() == 16
							|| checkValue.Equals(roofMaterialType.getTypeRoof(), "Other")) {

						if (permitProjectSiteInfo != null
								&& checkValue.Equals(permitProjectSiteInfo.getRafterTrussSpacing(), "16")) {
							if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(), "2x4")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "9'-10\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "9'-10\" MAX");
							} else if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(),
									"2x6")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "14'-4\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "14'-4\" MAX");
							} else if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(),
									"2x8")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "18'2\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "18'2\" MAX");
							}

						} else if (permitProjectSiteInfo != null
								&& checkValue.Equals(permitProjectSiteInfo.getRafterTrussSpacing(), "24")) {
							if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(), "2x4")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "8'-0\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "8'-0\" MAX");
							} else if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(),
									"2x6")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "11'-9\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "11'-9\" MAX");
							} else if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(),
									"2x8")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "14'-10\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "14'-10\" MAX");
							}
						} else if (permitProjectSiteInfo != null && (checkValue
								.Equals(permitProjectSiteInfo.getTextOtherRatfter(), "32\"")
								|| checkValue.Equals(permitProjectSiteInfo.getTextOtherRatfter(), "32"))) {
							if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(), "2x4")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "6'-6\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "6'-6\" MAX");
							} else if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(),
									"2x6")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "9'-6\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "9'-6\" MAX");
							} else if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(),
									"2x8")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "12'-0\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "12'-0\" MAX");
							}
						}

					} else if (roofMaterialType.getId() == 2
							|| roofMaterialType.getId() == 3
							|| roofMaterialType.getId() == 10
							|| roofMaterialType.getId() == 11
							|| roofMaterialType.getId() == 12) {

						if (permitProjectSiteInfo != null
								&& checkValue.Equals(permitProjectSiteInfo.getRafterTrussSpacing(), "16")) {
							if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(), "2x4")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "8'-6\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "8'-6\" MAX");
							} else if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(),
									"2x6")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "12'-5\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "12'-5\" MAX");
							} else if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(),
									"2x8")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "15'-9\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "15'-9\" MAX");
							}
						} else if (permitProjectSiteInfo != null
								&& checkValue.Equals(permitProjectSiteInfo.getRafterTrussSpacing(), "24")) {
							if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(), "2x4")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "6'-11\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "6'-11\" MAX");
							} else if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(),
									"2x6")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "10'-2\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "10'-2\" MAX");
							} else if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(),
									"2x8")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "12'-10\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "12'-10\" MAX");
							}
						} else if (permitProjectSiteInfo != null && (checkValue
								.Equals(permitProjectSiteInfo.getTextOtherRatfter(), "32\"")
								|| checkValue.Equals(permitProjectSiteInfo.getTextOtherRatfter(), "32"))) {
							if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(), "2x4")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "5'-6\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "5'-6\" MAX");
							} else if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(),
									"2x6")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "8'-0\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "8'-0\" MAX");
							} else if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(),
									"2x8")) {
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-1", "10'-3\" MAX");
								form.setField(sheetIndex + "-MAX-RAFTER-SPAN1-2", "10'-3\" MAX");
							}
						}

					}
				}
			}
		} catch (IOException|DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapTableDimesionsOne(List<PermitSketchEntity> listPermitSketchEntity, AcroFields form,
			PermitHomeSiteInfoEntity permitHomeSite, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			int sheetIndex) {
		try {
			if (listPermitSketchEntity != null && !listPermitSketchEntity.isEmpty()) {
				if (listPermitSketchEntity.get(0) != null
						&& checkValue.NotEquals(listPermitSketchEntity.get(0).getEaveOverHang(), "null")
						&& checkValue.NotEquals(listPermitSketchEntity.get(0).getEaveOverHang(), "")) {
					if (checkValue.NotEquals(listPermitSketchEntity.get(0).getEaveOverHang(), "Other")) {
						form.setField(sheetIndex + "-OVERHANG",
								listPermitSketchEntity.get(0).getEaveOverHang() + "\"");
						form.setField(sheetIndex + "-1-OVERHANG",
								listPermitSketchEntity.get(0).getEaveOverHang() + "\"");
					} else {
						if (listPermitSketchEntity.get(0).getEaveOverHangOther() != null) {
							form.setField(sheetIndex + "-OVERHANG",
									listPermitSketchEntity.get(0).getEaveOverHangOther() + "\"");
							form.setField(sheetIndex + "-1-OVERHANG",
									listPermitSketchEntity.get(0).getEaveOverHangOther() + "\"");
						}
					}

				} else {
					form.setField(sheetIndex + "-OVERHANG", "");
					form.setField(sheetIndex + "-1-OVERHANG", "");
				}

				if (listPermitSketchEntity.get(0) != null
						&& checkValue.NotEquals(listPermitSketchEntity.get(0).getModelvalue(), "null")) {
					form.setField(sheetIndex + "-ROOF-PITCH", listPermitSketchEntity.get(0).getModelvalue() + "°");
					form.setField(sheetIndex + "-1-ROOF-PITCH",
							listPermitSketchEntity.get(0).getModelvalue() + "°");
				} else {
					form.setField(sheetIndex + "-ROOF-PITCH", "");
					form.setField(sheetIndex + "-1-ROOF-PITCH", "");
				}
			}

			if (permitHomeSite != null && permitHomeSite.getRidgeBeamDepthAtArrays() != null) {
				if (checkValue.NotEquals(permitHomeSite.getRidgeBeamDepthAtArrays(), "Other")) {
					form.setField(sheetIndex + "-1-RIDGE-BEAM-DEPTH",
							permitHomeSite.getRidgeBeamDepthAtArrays() + "\"");
				} else if (permitHomeSite.getRidgeBeamDepthAtArraysOther() != null) {
					form.setField(sheetIndex + "-1-RIDGE-BEAM-DEPTH",
							permitHomeSite.getRidgeBeamDepthAtArraysOther());
				}
			} else {
				form.setField(sheetIndex + "-1-RIDGE-BEAM-DEPTH", "");

			}

			if (permitProjectSiteInfo != null && permitProjectSiteInfo.getCrossSectionSize() != null) {
				if (checkValue.Equals(permitProjectSiteInfo.getCrossSectionSize(), "OtherSize")) {
					form.setField(sheetIndex + "-1-RAFTER-DEPTH", permitProjectSiteInfo.getTextOtherSize());
				} else if (checkValue.contains(permitProjectSiteInfo.getCrossSectionSize(), "x")) {
					form.setField(sheetIndex + "-1-RAFTER-DEPTH",
							permitProjectSiteInfo.getCrossSectionSize().split("x")[1] + "\"");
				}
			} else {
				form.setField(sheetIndex + "-1-RAFTER-DEPTH", "");
			}

			if (permitHomeSite != null
					&& checkValue.NotEquals(permitHomeSite.getMaxHorizontalSpanAtArrays(), "")
					&& checkValue.NotEquals(permitHomeSite.getMaxHorizontalSpanAtArraysInches(), "")) {
				form.setField(sheetIndex + "-1-HS1-HORIZONTAL-SPAN", permitHomeSite.getMaxHorizontalSpanAtArrays()
						+ "'" + "-" + permitHomeSite.getMaxHorizontalSpanAtArraysInches() + "\"");
			} else
				form.setField(sheetIndex + "-1-HS1-HORIZONTAL-SPAN", "");

			if (permitHomeSite != null
					&& checkValue.NotEquals(permitHomeSite.getMaxHorizontalSpanAtArraysHS(), "")
					&& checkValue.NotEquals(permitHomeSite.getMaxHorizontalSpanAtArraysHSInches(), "")) {
				form.setField(sheetIndex + "-1-HS2-HORIZONTAL-SPAN",
						permitHomeSite.getMaxHorizontalSpanAtArraysHS() + "'" + "-"
								+ permitHomeSite.getMaxHorizontalSpanAtArraysHSInches() + "\"");
			} else
				form.setField(sheetIndex + "-1-HS2-HORIZONTAL-SPAN", "");
		} catch (IOException|DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapexistingRoof(PermitProjectSiteInfoEntity permitProjectSiteInfo, AcroFields form,
			RoofMaterialType roofMaterialType, int sheetIndex) {
		try {
			if (permitProjectSiteInfo != null && permitProjectSiteInfo.getTallStructure() != null) {
				if (checkValue.Equals(permitProjectSiteInfo.getTallStructure(), "1 Story")) {
					form.setField(sheetIndex + "-MEAN-ROOF-HEIGHT", "15'");
				} else if (checkValue.Equals(permitProjectSiteInfo.getTallStructure(), "2 Story")) {
					form.setField(sheetIndex + "-MEAN-ROOF-HEIGHT", "25'");
				} else if (checkValue.Equals(permitProjectSiteInfo.getTallStructure(), "3 Story")) {
					form.setField(sheetIndex + "-MEAN-ROOF-HEIGHT", "30'");
				} else if (checkValue.Equals(permitProjectSiteInfo.getTallStructure(), "OtheStory")) {
					if (permitProjectSiteInfo.getOtherTallStructure() != null) {
						form.setField(sheetIndex + "-MEAN-ROOF-HEIGHT",
								permitProjectSiteInfo.getOtherTallStructure() + "'");
					}
				}
			} else {
				form.setField(sheetIndex + "-MEAN-ROOF-HEIGHT", "");

			}
			if (roofMaterialType != null && roofMaterialType.getMappingValue() != null) {
				form.setField(sheetIndex + "-EXISTING-ROOFING-COVERING", roofMaterialType.getMappingValue());
			}
		} catch (IOException|DocumentException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapTableOfComponent(RailRacking railRacking, RoofAttachmentsEntity stanchionFoot, Flashing flashing, 
			RoofMaterialType roofMaterialType, AcroFields form, int sheetIndex) {
		try {
			resetTableOfComponent(form, sheetIndex);
			if (railRacking != null) {
				form.setField(sheetIndex + "-1-PV-RAIL-MODEL", getMappedValue(railRacking.getPvRailSpliceType(), railRacking.getModelMappingValue()));
				form.setField(sheetIndex + "-PV-RAIL-SPLICE-TYPE 1", getMappedValue(railRacking.getPvRailSpliceType(), "PER RAIL MANUFACTURER"));
				
				 
				if (Boolean.TRUE.equals(railRacking.getIntegratedStanchion())) {
					form.setField(sheetIndex + "-STANCHIONS-MODEL", railRacking.getStanchionModelMappingValue());
				} else if (stanchionFoot != null) {
					form.setField(sheetIndex + "-STANCHIONS-MODEL", stanchionFoot.getModelMappingValue());
				} 
				
				if (roofMaterialType != null && roofMaterialType.getId() == 4) {
					form.setField(sheetIndex + "-STANCHIONS-FLASHING-MODEL", "N/A");
				} else if (flashing != null && flashing.getMappedValue() != null){
					form.setField(sheetIndex + "-STANCHIONS-FLASHING-MODEL", flashing.getMappedValue());
				} else if (Boolean.TRUE.equals(railRacking.getIntegratedFlashing()) || (stanchionFoot != null && checkValue.Equals(stanchionFoot.getIntegrated(), "Yes"))) {
					form.setField(sheetIndex + "-STANCHIONS-FLASHING-MODEL", "Integrated");
				}else {//A.Wifek CR-PM-1189
					form.setField(sheetIndex + "-STANCHIONS-FLASHING-MODEL", "N/A"); 
				}
				
				form.setField(sheetIndex + "-MID-CLAMP", getMappedValue(railRacking.getMidClamp(), "PER RAIL MANUFACTURER"));
				form.setField(sheetIndex + "-END-CLAMP", getMappedValue(railRacking.getEndClamp(), "PER RAIL MANUFACTURER"));
			}
			form.setField(sheetIndex + "-2-PV-RAIL-MODEL", "NOT USED");
			
		} catch (IOException|DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}
	
	private void resetTableOfComponent(AcroFields form, int sheetIndex) throws DocumentException {
		try {
			
			form.setField(sheetIndex + "-1-PV-RAIL-MODEL", "");
			form.setField(sheetIndex + "-PV-RAIL-SPLICE-TYPE 1", "");
			form.setField(sheetIndex + "-STANCHIONS-MODEL", "");
			form.setField(sheetIndex + "-STANCHIONS-FLASHING-MODEL", "");
			form.setField(sheetIndex + "-MID-CLAMP", "");
			form.setField(sheetIndex + "-END-CLAMP", "");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getMappedValue(RailRackingOptionsEntity op, String defaultV) {
		return op != null ? op.getValue() : defaultV;
	}
	
	

	public void mapTableofDimensionsTruss(PermitAdditionalInfoEntityResult permitAdditionalInfo, AcroFields form,
			List<PermitSketchEntity> listPermitSketchEntity, Cmodulev2 moduleInfo, int sheetIndex) {
		try {
			if ((permitAdditionalInfo != null
					&& checkValue.Equals(permitAdditionalInfo.getTiltLegs(), true))
					|| (listPermitSketchEntity != null && !listPermitSketchEntity.isEmpty()
							&& listPermitSketchEntity.get(0) != null
							&& checkValue.Equals(listPermitSketchEntity.get(0).getModuleTils(), true))) {
				form.setField(sheetIndex + "-2-PV-MODULE-HEIGHT-ABOVE-ROOF", "12\" - 36\"");
				form.setField(sheetIndex + "-1-PV-MODULE-HEIGHT-ABOVE-ROOF", "12\" - 36\"");
			} else {
				form.setField(sheetIndex + "-2-PV-MODULE-HEIGHT-ABOVE-ROOF", "3\" - 6\" TYP");
				form.setField(sheetIndex + "-1-PV-MODULE-HEIGHT-ABOVE-ROOF", "3\" - 6\" TYP");
			}

			if (listPermitSketchEntity != null && !listPermitSketchEntity.isEmpty()) {
				if (listPermitSketchEntity.get(0) != null
						&& checkValue.NotEquals(listPermitSketchEntity.get(0).getEaveOverHang(), "null")
						&& checkValue.NotEquals(listPermitSketchEntity.get(0).getEaveOverHang(), "")) {
					if (checkValue.NotEquals(listPermitSketchEntity.get(0).getEaveOverHang(), "Other")) {
						form.setField(sheetIndex + "-1-OVERHANG",
								listPermitSketchEntity.get(0).getEaveOverHang() + "\"");
						form.setField(sheetIndex + "-2-OVERHANG",
								listPermitSketchEntity.get(0).getEaveOverHang() + "\"");
					} else {
						if (listPermitSketchEntity.get(0).getEaveOverHangOther() != null) {
							form.setField(sheetIndex + "-1-OVERHANG",
									listPermitSketchEntity.get(0).getEaveOverHangOther() + "\"");
							form.setField(sheetIndex + "-2-OVERHANG",
									listPermitSketchEntity.get(0).getEaveOverHangOther() + "\"");
						}
					}

				} else {
					form.setField(sheetIndex + "-1-OVERHANG", "");
					form.setField(sheetIndex + "-2-OVERHANG", "");
				}
				if (listPermitSketchEntity.get(0) != null
						&& checkValue.NotEquals(listPermitSketchEntity.get(0).getModelvalue(), "null")) {
					form.setField(sheetIndex + "-1-ROOF-PITCH",
							listPermitSketchEntity.get(0).getModelvalue() + "°");
					form.setField(sheetIndex + "-2-ROOF-PITCH",
							listPermitSketchEntity.get(0).getModelvalue() + "°");
				} else {
					form.setField(sheetIndex + "-1-ROOF-PITCH", "");
					form.setField(sheetIndex + "-2-ROOF-PITCH", "");
				}
			}
			if (moduleInfo != null && checkValue.NotEquals(moduleInfo.getLength(), "XX")) {
				if (moduleInfo.getLength() == "0") {
					form.setField(sheetIndex + "-1-D3-STANCHION-OC", new DecimalFormat("##.##").format(0) + "\"");
					form.setField(sheetIndex + "-1-D2-STANCHION-OC", new DecimalFormat("##.##").format(0) + "\"");
					form.setField(sheetIndex + "-1-D1-RAIL-OVERHANG", new DecimalFormat("##.##").format(0) + "\"");
				} else {
					Float Length = 1f;
					if (moduleInfo.getLength().contains(",")) {
						Length = Float.parseFloat(moduleInfo.getLength().replace(",", "."));
					} else if (checkValue.isNumeric(moduleInfo.getLength())) {
						Length = Float.parseFloat(moduleInfo.getLength());
					}
					form.setField(sheetIndex + "-1-D3-STANCHION-OC",
							new DecimalFormat("##.##").format((Length / 2) + 0.25) + "\"");
					form.setField(sheetIndex + "-1-D2-STANCHION-OC",
							new DecimalFormat("##.##").format(Length / 2) + "\"");
					form.setField(sheetIndex + "-1-D1-RAIL-OVERHANG",
							new DecimalFormat("##.##").format(Length / 4) + "\"");
				}
			}

			if (moduleInfo != null && checkValue.NotEquals(moduleInfo.getWidth(), "XX")) {
				Float width = 1f;
				if (moduleInfo.getWidth().contains(",")) {
					width = Float.parseFloat(moduleInfo.getWidth().replace(",", "."));
				} else if (checkValue.isNumeric(moduleInfo.getWidth())) {
					width = Float.parseFloat(moduleInfo.getWidth());
				}
				form.setField(sheetIndex + "-2-D3-STANCHION-OC",
						new DecimalFormat("##.##").format((width / 2) + 0.25) + "\"");
				form.setField(sheetIndex + "-2-D2-STANCHION-OC",
						new DecimalFormat("##.##").format(width / 2) + "\"");
				form.setField(sheetIndex + "-2-D1-RAIL-OVERHANG",
						new DecimalFormat("##.##").format(width / 4) + "\"");

			} else {
				form.setField(sheetIndex + "-2-D3-STANCHION-OC", "");
				form.setField(sheetIndex + "-2-D2-STANCHION-OC", "");
				form.setField(sheetIndex + "-2-D1-RAIL-OVERHANG", "");
			}

			form.setField(sheetIndex + "-2-D4-STANCHION-OC", "");
			form.setField(sheetIndex + "-1-D4-STANCHION-OC", "");

			/// ------------- CR 800------------///

			form.setField(sheetIndex + "-MAX-RAFTER-SPAN1", "ENGINEERED TRUSS");
			form.setField(sheetIndex + "-MAX-RAFTER-SPAN2", "ENGINEERED TRUSS");
			form.setField(sheetIndex + "-AHJ-NOTE-S200Truss", "");
		} catch (NumberFormatException e) {

			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		} catch (IOException|DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void mapExistRoofContTruss(AcroFields form, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			RoofMaterialType roofMaterialType, int sheetIndex) {
		try {
			if (checkValue.Equals(permitProjectSiteInfo.getTallStructure(), "1 Story")) {
				form.setField(sheetIndex + "-MEAN-ROOF-HEIGHT", "15'");
			} else if (checkValue.Equals(permitProjectSiteInfo.getTallStructure(), "2 Story")) {
				form.setField(sheetIndex + "-MEAN-ROOF-HEIGHT", "25'");
			} else if (checkValue.Equals(permitProjectSiteInfo.getTallStructure(), "3 Story")) {
				form.setField(sheetIndex + "-MEAN-ROOF-HEIGHT", "30'");
			} else if (checkValue.Equals(permitProjectSiteInfo.getTallStructure(), "OtheStory")) {
				form.setField(sheetIndex + "-MEAN-ROOF-HEIGHT",
						permitProjectSiteInfo.getOtherTallStructure() + "'");
			} else {
				form.setField(sheetIndex + "-MEAN-ROOF-HEIGHT", "");

			}

			form.setField(sheetIndex + "-EXISTING-ROOFING-COVERING", roofMaterialType.getMappingValue());
		} catch (IOException|DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}


	public File buildingPDFS200(PermitHomeSiteInfoEntity permitHomeSite,
			PermitAdditionalInfoEntityResult permitAdditionalInfo, 
			PermitProjectSiteInfoEntity permitProjectSiteInfo, PermitEntity permitEntity,
			List<PermitSketchEntity> listPermitSketchEntity, Cmodulev2 moduleInfo,
			PermitArrayEntityResultSecond permitArraysEntityResult, RoofAttachmentsEntity stanchionFoot,
			RailRacking railRacking, RoofMaterialType roofMaterialType, ElectricalUtilityEntity electricalCompany,
			Flashing flashing, AuthentificationEntity userConnectedEntity, PdfReader reader, int sheetIndex,
			String filePath, Long submitId,AHJNotesModel ahjNotes) {

		// you only need a PdfStamper if you're going to change the existing PDF.
		File fileRe = null;
		Long idPermit = permitEntity.getId();
		Locale.setDefault(new Locale("en", "US"));

		if (checkValue.Equals(permitArraysEntityResult.getRoofMounted(), true)) { //Here fatma check with Arij
			if ((checkValue.Equals(permitHomeSite.getRoofRafter(), "Rafter - Strut to Walls Below")
					|| checkValue.Equals(permitHomeSite.getRoofRafter(), "Rafter - Cathedral Ceiling")
					|| checkValue.Equals(permitHomeSite.getRoofRafter(), "Rafter-Simple Attic"))) {
				try {

					fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-S200-RAFTER" + idPermit + "-"
							+ sheetIndex + ".pdf");
					if (fileRe.exists()) {
						fileRe.delete();
						fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-S200-RAFTER" + idPermit + "-"
								+ sheetIndex + ".pdf");
					}

					PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
					AcroFields form = stamper.getAcroFields();
					
					//A.B remove sheet index if exist when the project was uploaded
					if(permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
						getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
					}
					PdfReader readerOriginNEC = new PdfReader(
							Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-S200-RAFTER.pdf");
					PdfReader readerOriginCEC = new PdfReader(
							Constants.rapportPlansetFolderUrl + "PDF-S200-RAFTER.pdf");
					// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
					getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "S200");
					// A.B: Set PDF Font For Revision
					getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
							sheetIndex);
					getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
							sheetIndex);

					// A.B CR-3250 03-30 Logo & Signature Mapping
					logoSignatureMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);
					
					//A.B 07-14-2021 CR-3064
					form.setField(sheetIndex+"-"+"SPECIAL-NOTE-S-200",ahjNotes.getS200Note());

					// A.B S Sheets Quads Clean & Mapping
					cleanFileMapping(stamper);
					// A.B S Sheets Quads Mapping
					sSheetFileMapping(stamper, form, permitEntity, submitId, userConnectedEntity, stanchionFoot,
							railRacking, flashing, electricalCompany, roofMaterialType, permitHomeSite,
							permitProjectSiteInfo, sheetIndex);


					////////////////////// Table of Dimensions///////////////////
					mapTableDimensions(permitAdditionalInfo, permitProjectSiteInfo, form, listPermitSketchEntity,
							permitHomeSite, moduleInfo, sheetIndex);

					/// --- CR 800 planset mapping update ---///
					mappingUpdates(form, permitHomeSite, roofMaterialType, permitProjectSiteInfo, sheetIndex);
					form.setField(sheetIndex + "-AHJ-NOTE-S200Raft", "");
					/// ------------------------------------///
					form.setField(sheetIndex + "-D4-STANCHION-OC", "");
					form.setField(sheetIndex + "-1-D4-STANCHION-OC", "");

					////////////////////// 1- Table of Dimensions///////////////////
					mapTableDimesionsOne(listPermitSketchEntity, form, permitHomeSite, permitProjectSiteInfo,
							sheetIndex);

					////////////////////// Existing Roof Construction///////////////////
					mapexistingRoof(permitProjectSiteInfo, form, roofMaterialType, sheetIndex);

					//Table Of Component
					mapTableOfComponent(railRacking, stanchionFoot, flashing, roofMaterialType, form, sheetIndex);

					stamper.close();
					reader.close();

				} catch (IOException|DocumentException e) {
					technicalProblemMsg.traiterException(e);
				}

			} else if ((checkValue.Equals(permitHomeSite.getRoofRafter(), "Pre-Eng Roof Trusses")
					|| checkValue.Equals(permitHomeSite.getRoofRafter(), "OtherRoof"))) {

				try {

					fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-S200-TRUSS" + idPermit + "-" + sheetIndex
							+ ".pdf");
					if (fileRe.exists()) {
						fileRe.delete();
						fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-S200-TRUSS" + idPermit + "-"
								+ sheetIndex + ".pdf");
					}
					PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
					AcroFields form = stamper.getAcroFields();
					
					//A.B remove sheet index if exist when the project was uploaded
					if(permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
						getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
					}

					PdfReader readerOriginNEC = new PdfReader(
							Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-S200-TRUSS.pdf");
					PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-S200-TRUSS.pdf");

					// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
					getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "S200");
					// A.B: Set PDF Font For Revision
					getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
							sheetIndex);
					getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
							sheetIndex);

					// A.B CR-3250 03-30 Logo & Signature Mapping
					logoSignatureMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);
					
					//A.B 07-14-2021 CR-3064
					form.setField(sheetIndex+"-"+"SPECIAL-NOTE-S-200",ahjNotes.getS200Note());

					cleanFileMapping(stamper);
					// A.B S Sheets Quads Mapping
					sSheetFileMapping(stamper, form, permitEntity, submitId, userConnectedEntity, stanchionFoot,
							railRacking, flashing, electricalCompany, roofMaterialType, permitHomeSite,
							permitProjectSiteInfo, sheetIndex);

					////////////////////// Table of Dimensions///////////////////
					mapTableofDimensionsTruss(permitAdditionalInfo, form, listPermitSketchEntity, moduleInfo,
							sheetIndex);
					////////////////////// Existing Roof Construction///////////////////
					mapExistRoofContTruss(form, permitProjectSiteInfo, roofMaterialType, sheetIndex);

					//Table Of Component
					mapTableOfComponent(railRacking, stanchionFoot, flashing, roofMaterialType, form, sheetIndex);

					stamper.close();
					reader.close();

				} catch (IOException|DocumentException e) {
					e.printStackTrace();
					technicalProblemMsg.traiterException(e);
				}

			} else {

				try {

					fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-S200-RAFTER" + idPermit + "-"
							+ sheetIndex + ".pdf");
					if (fileRe.exists()) {
						fileRe.delete();
						fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-S200-RAFTER" + idPermit + "-"
								+ sheetIndex + ".pdf");
					}
					PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
					AcroFields form = stamper.getAcroFields();
					
					//A.B remove sheet index if exist when the project was uploaded
					if(permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
						getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
					}

					PdfReader readerOriginNEC = new PdfReader(
							Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-S200-RAFTER.pdf");
					PdfReader readerOriginCEC = new PdfReader(
							Constants.rapportPlansetFolderUrl + "PDF-S200-RAFTER.pdf");

					// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
					getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "S200");
					// A.B: Set PDF Font For Revision
					getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
							sheetIndex);
					getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
							sheetIndex);

					// A.B CR-3250 03-30 Logo & Signature Mapping
					logoSignatureMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);
					
					//A.B 07-14-2021 CR-3064
					form.setField(sheetIndex+"-"+"SPECIAL-NOTE-S-200",ahjNotes.getS200Note());

					//A.B CR-3361 09-21 NOT USED S-Sheet Quad title Mapping
					form.setField(sheetIndex + "-SECTION-MODULE-GROUP:ROOF-AND-RACKING-SECTION-DIAGRAM-HORIZONTAL",
							"NOT USED");
					form.setField(sheetIndex + "-SECTION-MODULE-GROUP:ROOF-AND-RACKING-SECTION-DIAGRAM-PERPENDICULAR",
							"NOT USED");
					form.setField(sheetIndex + "-ENLARGED-DETAIL:STANCHION-ATTACHMENT-BLOCKING",
							"NOT USED");
					form.setField(sheetIndex + "-ENLARGED-SECTION:STANCHION-ATTACHMENT-BLOCKING",
							"NOT USED");
					
					// A.B Clear Previous S sheet Mapping
					cleanFileMapping(stamper);
					
					// A.B S Sheets Quads Mapping
					sSheetFileMapping(stamper, form, permitEntity, submitId, userConnectedEntity, stanchionFoot,
							railRacking, flashing, electricalCompany, roofMaterialType, permitHomeSite,
							permitProjectSiteInfo, sheetIndex);
					
					stamper.close();
					reader.close();

				} catch (IOException|DocumentException e) {
					technicalProblemMsg.traiterException(e);
				}

			}
		} else {
			try {

				fileRe = new File(
						Constants.rapportPlansetFolderUrl + "PDF-S200-RAFTER" + idPermit + "-" + sheetIndex + ".pdf");
				if (fileRe.exists()) {
					fileRe.delete();
					fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-S200-RAFTER" + idPermit + "-"
							+ sheetIndex + ".pdf");
				}
				PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
				AcroFields form = stamper.getAcroFields();
				
				//A.B remove sheet index if exist when the project was uploaded
				if(permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
					getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
				}

				PdfReader readerOriginNEC = new PdfReader(
						Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-S200-RAFTER.pdf");
				PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-S200-RAFTER.pdf");

				// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
				getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "S200");
				// A.B: Set PDF Font For Revision
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
						sheetIndex);
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
						sheetIndex);

				// A.B CR-3250 03-30 Logo & Signature Mapping
				logoSignatureMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);
				
				//A.B 07-14-2021 CR-3064
				form.setField(sheetIndex+"-"+"SPECIAL-NOTE-S-200",ahjNotes.getS200Note());

				// A.B Clear Previous S sheet Mapping
				cleanFileMapping(stamper);
				form.setField(sheetIndex + "-SECTION-MODULE-GROUP:ROOF-AND-RACKING-SECTION-DIAGRAM-HORIZONTAL",
						"NOT USED");
				form.setField(sheetIndex + "-SECTION-MODULE-GROUP:ROOF-AND-RACKING-SECTION-DIAGRAM-PERPENDICULAR",
						"NOT USED");
				form.setField(sheetIndex + "-ENLARGED-DETAIL:STANCHION-ATTACHMENT-BLOCKING",
						"NOT USED");
				form.setField(sheetIndex + "-ENLARGED-SECTION:STANCHION-ATTACHMENT-BLOCKING",
						"NOT USED");
				stamper.close();
				reader.close();

			} catch (IOException|DocumentException e) {
				technicalProblemMsg.traiterException(e);
			}

		}

		return fileRe;
	}

	public void sSheetFileMapping(PdfStamper stamper, AcroFields form, PermitEntity permitEntity, Long submitId,
			AuthentificationEntity userConnectedEntity, RoofAttachmentsEntity stanchionFoot, RailRacking railRacking,
			Flashing flashing, ElectricalUtilityEntity electricalCompany, RoofMaterialType roofMaterialType,
			PermitHomeSiteInfoEntity permitHomeSite, PermitProjectSiteInfoEntity permitProjectSiteInfo,
			int sheetIndex) {

		try {

			Boolean hasFlashing = flashing != null || (stanchionFoot != null && stanchionFoot.getIntegrated() != null && stanchionFoot.getIntegrated().equals("Yes"));
			String stanchionSetting = Optional.ofNullable(this.userSettingRepository.findStanchionTypeByUserId(permitEntity.getAuthentificationEntity().getId())).orElse(null);
			String stanchionType="All Staggered";
			//F.S CR-PM-3411 revision
			if(checkValue.Equals(stanchionSetting, "Don’t Stagger Roof Anchors within Rows of Modules, but Stagger Anchors by Rows") )
				stanchionType = "Staggered Anchors by rows";
			else if(checkValue.Equals(stanchionSetting, "Don’t stagger Roof Anchors, Show as many Anchors as possible on the same framing members"))
				stanchionType = "Lined Up";
			String roofRafter = permitHomeSite.getRoofRafter();
			if (permitHomeSite.getRoofRafterOther() != null) {
				if (checkValue.Equals(permitHomeSite.getRoofRafterOther(), "OTHER")) {
					roofRafter = permitHomeSite.getSecroofRafterOther();
				} else if (checkValue.isStringNotEmpty(permitHomeSite.getRoofRafterOther())) {
					roofRafter = permitHomeSite.getRoofRafterOther();
				}
			}
			roofRafter = getRackingSSheets.getRoofRafter(roofRafter);

			String ahj = permitHomeSite.getProjectJurisOther();
			if (checkValue.Equals(permitHomeSite.getProjectJurisdiction(), "city")) {
				if (checkValue.NotEquals(permitHomeSite.getCity(), "Other")) {
					ahj = "City of " + permitHomeSite.getCity();

				} else if (permitHomeSite.getProjectJurisOther() != null) {
					ahj = "City of " + permitHomeSite.getProjectJurisOther();
				} else {
					ahj = "City of ";
				}

			} else if (permitHomeSite.getProjectJurisdiction() != null
					&& permitHomeSite.getProjectJurisdiction().contains("County")) {
				if (checkValue.NotEquals(permitHomeSite.getCity(), "Other")) {
					ahj = permitHomeSite.getProjectJurisdiction();
				} else if (permitHomeSite.getProjectJurisOther() != null) {
					ahj = "County of " + permitHomeSite.getProjectJurisOther();
				} else {
					ahj = "County of ";
				}

			}

			SsheetRackingMappingEntity rackingOne = getRackingSSheets.getRackingFileName(stanchionFoot, roofRafter,
					railRacking, flashing, electricalCompany, roofMaterialType.getTypeRoof(), ahj, "S-200", "1",hasFlashing, permitHomeSite.getState(),stanchionType);
			if (rackingOne != null) {
				quadMapping(stamper, rackingOne.getSSheetFile().getPdfName(), 45f, 520f);
				form.setField(sheetIndex + "-SECTION-MODULE-GROUP:ROOF-AND-RACKING-SECTION-DIAGRAM-HORIZONTAL",
						rackingOne.getDetailsHeading());
			} else {
				SsheetSpacingMappingEntity spacingOne = getSpacingSSheets.getSpacingFileName(permitEntity, submitId,
						userConnectedEntity, roofRafter, permitProjectSiteInfo.getRafterTrussSpacing(),
						permitHomeSite.getStanchionMaxSpacing(), "S-200", "1",stanchionType,hasFlashing);
				if (spacingOne != null) {
					quadMapping(stamper, spacingOne.getSSheetFile().getPdfName(), 45f, 520f);
					form.setField(sheetIndex + "-SECTION-MODULE-GROUP:ROOF-AND-RACKING-SECTION-DIAGRAM-HORIZONTAL",
							spacingOne.getDetailsHeading());
				}else {
					//A.B CR-3361 09-21 NOT USED S-Sheet Quad title Mapping
					form.setField(sheetIndex + "-SECTION-MODULE-GROUP:ROOF-AND-RACKING-SECTION-DIAGRAM-HORIZONTAL",
							"NOT USED");
				}
			}

			SsheetRackingMappingEntity rackingTwo = getRackingSSheets.getRackingFileName(stanchionFoot, roofRafter,
					railRacking, flashing, electricalCompany, roofMaterialType.getTypeRoof(), ahj, "S-200", "2",hasFlashing, permitHomeSite.getState(),stanchionType);
			if (rackingTwo != null) {
				quadMapping(stamper, rackingTwo.getSSheetFile().getPdfName(), 477f, 520f);
				form.setField(sheetIndex + "-SECTION-MODULE-GROUP:ROOF-AND-RACKING-SECTION-DIAGRAM-PERPENDICULAR",
						rackingTwo.getDetailsHeading());
			} else {
				SsheetSpacingMappingEntity spacingTwo = getSpacingSSheets.getSpacingFileName(permitEntity, submitId,
						userConnectedEntity, roofRafter, permitProjectSiteInfo.getRafterTrussSpacing(),
						permitHomeSite.getStanchionMaxSpacing(), "S-200", "2",stanchionType,hasFlashing);
				if (spacingTwo != null) {
					quadMapping(stamper, spacingTwo.getSSheetFile().getPdfName(), 477f, 520f);
					form.setField(
							sheetIndex + "-SECTION-MODULE-GROUP:ROOF-AND-RACKING-SECTION-DIAGRAM-PERPENDICULAR",
							spacingTwo.getDetailsHeading());
				}else {
					//A.B CR-3361 09-21 NOT USED S-Sheet Quad title Mapping
					form.setField(sheetIndex + "-SECTION-MODULE-GROUP:ROOF-AND-RACKING-SECTION-DIAGRAM-PERPENDICULAR",
							"NOT USED");
				}
			}
			
			SsheetRackingMappingEntity rackingThree = getRackingSSheets.getRackingFileName(stanchionFoot, roofRafter,
					railRacking, flashing, electricalCompany, roofMaterialType.getTypeRoof(), ahj, "S-200", "3",hasFlashing, permitHomeSite.getState(),stanchionType);
			if (rackingThree != null) {
				quadMapping(stamper, rackingThree.getSSheetFile().getPdfName(), 45f, 45f);
				form.setField(sheetIndex + "-ENLARGED-DETAIL:STANCHION-ATTACHMENT-BLOCKING",
						rackingThree.getDetailsHeading());
			} else {
				SsheetSpacingMappingEntity spacingThree = getSpacingSSheets.getSpacingFileName(permitEntity, submitId,
						userConnectedEntity, roofRafter, permitProjectSiteInfo.getRafterTrussSpacing(),
						permitHomeSite.getStanchionMaxSpacing(), "S-200", "3",stanchionType,hasFlashing);
				if (spacingThree != null) {
					quadMapping(stamper, spacingThree.getSSheetFile().getPdfName(), 45f, 45f);
					form.setField(sheetIndex + "-ENLARGED-DETAIL:STANCHION-ATTACHMENT-BLOCKING",
							spacingThree.getDetailsHeading());
				}else {
					//A.B CR-3361 09-21 NOT USED S-Sheet Quad title Mapping
					form.setField(sheetIndex + "-ENLARGED-DETAIL:STANCHION-ATTACHMENT-BLOCKING",
							"NOT USED");
				}
			}
			
			SsheetRackingMappingEntity rackingFour = getRackingSSheets.getRackingFileName(stanchionFoot, roofRafter,
					railRacking, flashing, electricalCompany, roofMaterialType.getTypeRoof(), ahj, "S-200", "4",hasFlashing, permitHomeSite.getState(),stanchionType);
			if (rackingFour != null) {
				quadMapping(stamper, rackingFour.getSSheetFile().getPdfName(), 477f, 45f);
				form.setField(sheetIndex + "-ENLARGED-SECTION:STANCHION-ATTACHMENT-BLOCKING",
						rackingFour.getDetailsHeading());
			} else {
				SsheetSpacingMappingEntity spacingFour = getSpacingSSheets.getSpacingFileName(permitEntity, submitId,
						userConnectedEntity, roofRafter, permitProjectSiteInfo.getRafterTrussSpacing(),
						permitHomeSite.getStanchionMaxSpacing(), "S-200", "4",stanchionType,hasFlashing);
				if (spacingFour != null) {
					quadMapping(stamper, spacingFour.getSSheetFile().getPdfName(), 477f, 45f);
					form.setField(sheetIndex + "-ENLARGED-SECTION:STANCHION-ATTACHMENT-BLOCKING",
							spacingFour.getDetailsHeading());
				} else {
					//A.B CR-3361 09-21 NOT USED S-Sheet Quad title Mapping
					form.setField(sheetIndex + "-ENLARGED-SECTION:STANCHION-ATTACHMENT-BLOCKING",
							"NOT USED");
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void quadMapping(PdfStamper stamper, String fileName, Float x, Float y) {
		try {
			if(new File(Constants.rapportS200FolderUrl + getRackingSSheets.getPDFPAth(fileName)).exists()) {
				String[] EXTRA = { Constants.rapportS200FolderUrl + getRackingSSheets.getPDFPAth(fileName) };
				PdfContentByte canvas = stamper.getUnderContent(1);
				PdfReader r;
				PdfImportedPage page;
				for (String path1 : EXTRA) {
					r = new PdfReader(path1);
					page = stamper.getImportedPage(r, 1);
					canvas.addTemplate(page, x, y);
					stamper.getWriter().freeReader(r);
					r.close();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// A.B Clear Previous S sheet Mapping
	public void cleanFileMapping(PdfStamper stamper) {

		List<PdfCleanUpLocation> cleanUpLocations = new ArrayList<PdfCleanUpLocation>();
		try {
			cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(45f, 530f, 470, 736f), BaseColor.WHITE)); // Details
																													// 1
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(474f, 530f, 900, 736f), BaseColor.WHITE)); // Details
																													// 2
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(45f, 40f, 470, 375f), BaseColor.WHITE)); // Details
																													// 3
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(474f, 40f, 900f, 375f), BaseColor.WHITE)); // Details
																													// 4
		} catch (Exception e) {
			e.printStackTrace();
		}
		PdfCleanUpProcessor cleaner = new PdfCleanUpProcessor(cleanUpLocations, stamper);
		
		try {
			cleaner.cleanUp();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
