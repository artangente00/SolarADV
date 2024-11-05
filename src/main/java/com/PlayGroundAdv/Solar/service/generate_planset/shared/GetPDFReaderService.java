package com.PlayGroundAdv.Solar.service.generate_planset.shared;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PermitCustomizedSheetsEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.model.PlansetRevisionIndexModel;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.PermitCustomizedSheetsRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.TextField;

@Service
@Transactional
public class GetPDFReaderService {

	final CheckValueTypesService checkValue;
	final HistoryActivityService historyActivityService;
	final TechnicalProblemMsg technicalProblemMsg;
	final PathRepository pathRepo;
	final PermitRepository permitRepo;
	final PermitCustomizedSheetsRepository permitCustomizedSheetsRepo;

	public GetPDFReaderService(CheckValueTypesService checkValue, HistoryActivityService historyActivityService,
			TechnicalProblemMsg technicalProblemMsg, PathRepository pathRepo, PermitRepository permitRepo,
			PermitCustomizedSheetsRepository permitCustomizedSheetsRepo) {
		super();
		this.checkValue = checkValue;
		this.historyActivityService = historyActivityService;
		this.technicalProblemMsg = technicalProblemMsg;
		this.pathRepo = pathRepo;
		this.permitRepo = permitRepo;
		this.permitCustomizedSheetsRepo = permitCustomizedSheetsRepo;
	}

	final static List<String> TITLE_BLOCK_FIELDS_NAME = Arrays.asList("CONTRACTOR-CN", "CONTRACTOR-CN", "CONTRACTOR-LN",
			"CONTRACTOR-AD1", "CONTRACTOR-AD2", "CONTRACTOR-PH", "kW-SYSTEM-SIZE", "HOMEOWNER-NAME1", "HOMEOWNER-AD1",
			"HOMEOWNER-AD2", "PROJECT-APN", "SUMBMITTALDATE", "SUBMITTALFORPERMIT", "REVISION-1", "REVISION-1-DATE",
			"REVISION-1-SUBMITTAL-NAME", "REVISION-2", "REVISION-2-DATE", "REVISION-2-SUBMITTAL-NAME");

	public String getfilesPath() {
		return pathRepo.findFilePath();
	}

	// A.B: CR-2620 Check IF The Planset Has Revision
	public Boolean checkStateResevation(String state, PermitEntity permitEntity) {
		try {
			if (permitEntity != null && permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1
					&& !((checkValue.Equals(permitEntity.getPlansetState(), "CA") && checkValue.NotEquals(state, "CA"))
							|| (checkValue.NotEquals(permitEntity.getPlansetState(), "CA")
									&& checkValue.Equals(state, "CA")))) {
				return true;
			} else {
				if (permitEntity != null) {
					permitEntity.setPlansetVersion(1);
					permitEntity.setReleasev2("");
					permitEntity.setReleasev3("");
					permitRepo.save(permitEntity);
				}
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	// A.B: CR-2620 Check IF The Planset Has Revision
	public Boolean checkInvTechnologyResevation(String inverterTechnology, PermitEntity permitEntity) {

		try {
			String plansetInverterTechnologies = "";
			String currentInverterTechnologies = "";
			if (checkValue.Equals(permitEntity.getPlansetInverterTechnologies(), "Neither")
					|| checkValue.Equals(permitEntity.getPlansetInverterTechnologies(), "System Optimizer")) {
				plansetInverterTechnologies = "STRING";
			} else
				plansetInverterTechnologies = "MICRO";

			if (checkValue.Equals(inverterTechnology, "Neither")
					|| checkValue.Equals(inverterTechnology, "System Optimizer")) {
				currentInverterTechnologies = "STRING";
			} else
				currentInverterTechnologies = "MICRO";

			if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetInverterTechnologies() != null
					&& permitEntity.getPlansetVersion() > 1
					&& checkValue.isStringNotEmpty(permitEntity.getPlansetInverterTechnologies())
					&& checkValue.isStringNotEmpty(inverterTechnology)
					&& checkValue.Equals(plansetInverterTechnologies, currentInverterTechnologies)) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// A.B: CR-2620 Check IF The Planset Has Revision
	public Boolean checkInvTechnologyRemoveResevation(String inverterTechnology, PermitEntity permitEntity) {

		try {
			String plansetInverterTechnologies = "";
			String currentInverterTechnologies = "";
			if (checkValue.Equals(permitEntity.getPlansetInverterTechnologies(), "Neither")
					|| checkValue.Equals(permitEntity.getPlansetInverterTechnologies(), "System Optimizer")) {
				plansetInverterTechnologies = "STRING";
			} else
				plansetInverterTechnologies = "MICRO";

			if (checkValue.Equals(inverterTechnology, "Neither")
					|| checkValue.Equals(inverterTechnology, "System Optimizer")) {
				currentInverterTechnologies = "STRING";
			} else
				currentInverterTechnologies = "MICRO";

			if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetInverterTechnologies() != null
					&& permitEntity.getPlansetVersion() > 1
					&& checkValue.isStringNotEmpty(permitEntity.getPlansetInverterTechnologies())
					&& checkValue.isStringNotEmpty(inverterTechnology)
					&& checkValue.NotEquals(plansetInverterTechnologies, currentInverterTechnologies)) {
				permitEntity.setPlansetInverterTechnologies(inverterTechnology);
				permitRepo.save(permitEntity);
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// A.B: CR-2620 Check IF The Planset Has Revision
	public Boolean checkRoofRafterRemoveResevation(String projectRoofDesign, PermitEntity permitEntity) {

		try {

			if (permitEntity != null && permitEntity.getPlansetVersion() != null
					&& permitEntity.getPlansetRoofRafter() != null && permitEntity.getPlansetVersion() > 1
					&& checkValue.isStringNotEmpty(permitEntity.getPlansetRoofRafter())
					&& checkValue.isStringNotEmpty(projectRoofDesign)
					&& checkValue.NotEquals(projectRoofDesign, permitEntity.getPlansetRoofRafter())) {
				permitEntity.setPlansetRoofRafter(projectRoofDesign);
				permitRepo.save(permitEntity);
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// A.B: CR-2620 get Pdf Reader Resevation
	public List<PlansetRevisionIndexModel> getPdfReaderResevation(Long idPermit, String pdfTitle)
			throws DocumentException {

		List<PlansetRevisionIndexModel> listPages = new ArrayList<>();
		try {

			File file = new File(getfilesPath() + "Rapport/SampleResult-PageNumber-" + idPermit + ".pdf");
			if (file.exists()) {
				file.delete();
				file = new File(getfilesPath() + "Rapport/SampleResult-PageNumber-" + idPermit + ".pdf");
			}
			PdfReader readerPage = new PdfReader(getfilesPath() + "Rapport/SampleResult" + idPermit + ".pdf");
			PdfStamper stamper = new PdfStamper(readerPage, new FileOutputStream(file));
			AcroFields form = stamper.getAcroFields();

			for (int i = 0; i < readerPage.getNumberOfPages(); i++) {

				if (form.getFieldPositions(i + "-" + pdfTitle + "-SHEET-INDEX") != null) {
					int page = form.getFieldPositions(i + "-" + pdfTitle + "-SHEET-INDEX").get(0).page;
					PlansetRevisionIndexModel pageIndex = new PlansetRevisionIndexModel();
					pageIndex.setPageIndex(i);
					pageIndex.setPageNumber(String.valueOf(page));
					listPages.add(pageIndex);
				}
			}

			// A.B 08-06 Planset Revision before CR-2845
			if (listPages == null || listPages.isEmpty()) {
				for (int i = 0; i < readerPage.getNumberOfPages(); i++) {

					if (form.getFieldPositions(i + "-" + pdfTitle + "-kW-SYSTEM-SIZE") != null) {
						int page = form.getFieldPositions(i + "-" + pdfTitle + "-kW-SYSTEM-SIZE").get(0).page;
						PlansetRevisionIndexModel pageIndex = new PlansetRevisionIndexModel();
						pageIndex.setPageIndex(i);
						pageIndex.setPageNumber(String.valueOf(page));
						listPages.add(pageIndex);
					}
				}
				// A.B Planset Revision before CR-2620
				if (form.getFieldPositions(pdfTitle + "-kW-SYSTEM-SIZE") != null) {
					int page = form.getFieldPositions(pdfTitle + "-kW-SYSTEM-SIZE").get(0).page;
					PlansetRevisionIndexModel pageIndex = new PlansetRevisionIndexModel();
					pageIndex.setPageIndex(listPages.size());
					pageIndex.setPageNumber(String.valueOf(page));
					listPages.add(pageIndex);
				}
			}

			stamper.close();
			readerPage.close();
			file.delete();

		} catch (IOException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return listPages;
	}

	public List<Long> getCompatibleCustomize(Long idPermit, String sheetIndex, Long idUserConnected) {

		List<Long> ids = new ArrayList<>();
		try {
			List<PermitCustomizedSheetsEntity> sheet = permitCustomizedSheetsRepo
					.findByProjectIdAndSheetPdfName(idPermit, sheetIndex);
			if (!sheet.isEmpty()) {
				if (sheet.size() == 1) {
					ids.add(sheet.get(0).getSheet().getId());
				} else {
					List<PermitCustomizedSheetsEntity> sheetC = permitCustomizedSheetsRepo
							.findByProjectIdAndSheetPdfNameAndMasterSheet(idPermit, sheetIndex, true);
					if (sheetC.size() == 1) {
						ids.add(sheetC.get(0).getSheet().getId());
						// A.B: Send Notification to Portal Log
						try {
							historyActivityService.recordActivity(idUserConnected, "", "",
									"Customize Sheet;The Planset sheet " + sheetIndex + " where the ID equals "
											+ sheetC.get(0).getSheet().getId()
											+ " was selected as the master sheet for the project "
											+ sheetC.get(0).getProject().getHomeOwnName() + ";Edit success",
									true, "", "", "");
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						for (int i = 0; i < sheet.size(); i++) {
							ids.add(sheet.get(i).getSheet().getId());
						}
						// A.B: Send Notification to Portal Log
						try {
							historyActivityService.recordActivity(idUserConnected, "", "",
									"Customize Sheet;The project " + sheet.get(0).getProject().getHomeOwnName()
											+ " has " + sheet.size() + " customize " + sheetIndex
											+ " sheets;Edit success",
									true, "", "", "");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return ids;
	}

	// A.B: CR-2620 get Pdf Reader From Revision Planset
	public PdfReader getPdfReaderFromRevision(Long idPermit, String page) {

		try {
			PdfReader reader = new PdfReader(getfilesPath() + "Rapport/SampleResult" + idPermit + ".pdf");
			reader.selectPages(String.valueOf(page));
			String path = String.format("%sRapport/SampleResult%sCopy.pdf", getfilesPath(), idPermit);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(path));
			stamper.close();
			reader.close();

			return new PdfReader(getfilesPath() + "Rapport/SampleResult" + idPermit + "Copy.pdf");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void addFieldsIndex(PdfStamper stamper, PdfReader readerOrigin, int sheetIndex, String index) {

		try {
			stamper.getWriter().getAcroForm().setNeedAppearances(false);
			Set<String> fldNames = readerOrigin.getAcroFields().getFields().keySet();
			for (String fldName : fldNames) {

				if (TITLE_BLOCK_FIELDS_NAME.indexOf(fldName) > -1) {
					stamper.getAcroFields().renameField(fldName, sheetIndex + "-" + index + "-" + fldName);
				} else
					stamper.getAcroFields().renameField(fldName, sheetIndex + "-" + fldName);

			}
			// A.B Reset Content for revision
			PdfContentByte content = stamper.getUnderContent(1);
			content.reset();

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void applyFontsRevision(PermitEntity permitEntity, PdfStamper stamper, PdfReader readerOrigin,
			PermitHomeSiteInfoEntity permitHomeSite, AcroFields form, int sheetIndex) {

		try {
			if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1
					&& !((checkValue.Equals(permitEntity.getPlansetState(), "CA")
							&& checkValue.NotEquals(permitHomeSite.getState(), "CA"))
							|| (checkValue.NotEquals(permitEntity.getPlansetState(), "CA")
									&& checkValue.Equals(permitHomeSite.getState(), "CA")))) {
				stamper.getWriter().getAcroForm().setNeedAppearances(false);
				BaseFont font = BaseFont.createFont("c:/windows/fonts/calibri.ttf", BaseFont.WINANSI, true);
				BaseFont bold = BaseFont.createFont("c:/windows/fonts/calibrib.ttf", BaseFont.WINANSI, true);
				BaseFont arial = BaseFont.createFont("c:/windows/fonts/arial.ttf", BaseFont.WINANSI, true);
				BaseFont arialBold = null;
				if (new File("c:/windows/fonts/arialbd.ttf").exists()) {
					arialBold = BaseFont.createFont("c:/windows/fonts/arialbd.ttf", BaseFont.WINANSI, true);
				}

				Set<String> fldNames = form.getFields().keySet();
				for (String fldName : fldNames) {
					String fontName = null;
					AcroFields.Item item = readerOrigin.getAcroFields().getFieldItem(fldName);
					if (item != null) {
						PdfDictionary merged = item.getMerged(0);
						TextField textField = new TextField(null, null, null);
						readerOrigin.getAcroFields().decodeGenericDictionary(merged, textField);
						if (textField != null && textField.getFont() != null) {
							String[][] fullFontName = textField.getFont().getFullFontName();
							fontName = fullFontName[0][3];
							float fontSize = textField.getFontSize();
							if (checkValue.Equals(fontName, "Calibri")) {
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textfont", font,
										null);
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textsize",
										fontSize, null);
							} else if (checkValue.Equals(fontName, "Calibri,Bold")) {
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textfont", bold,
										null);
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textsize",
										fontSize, null);
							} else if (checkValue.Equals(fontName, "FXRIAY+Calibri")) {
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textfont",
										BaseFont.createFont("c:/windows/fonts/FXRIAY+Calibri.ttf", BaseFont.WINANSI,
												true),
										null);
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textsize",
										fontSize, null);
							} else if (checkValue.Equals(fontName, "SAVURJ+Calibri")) {
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textfont",
										BaseFont.createFont("c:/windows/fonts/SAVURJ+Calibri.ttf", BaseFont.WINANSI,
												true),
										null);
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textsize",
										fontSize, null);
							} else if (checkValue.Equals(fontName, "SNPUUI+Calibri")) {
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textfont",
										BaseFont.createFont("c:/windows/fonts/SNPUUI+Calibri.ttf", BaseFont.WINANSI,
												true),
										null);
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textsize",
										fontSize, null);
							} else if (checkValue.Equals(fontName, "CUXAZL+Calibri")) {
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textfont",
										BaseFont.createFont("c:/windows/fonts/CUXAZL+Calibri.ttf", BaseFont.WINANSI,
												true),
										null);
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textsize",
										fontSize, null);
							} else if (checkValue.Equals(fontName, "OOAHEL+Calibri")) {
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textfont",
										BaseFont.createFont("c:/windows/fonts/OOAHEL+Calibri.ttf", BaseFont.WINANSI,
												true),
										null);
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textsize",
										fontSize, null);
							} else if (checkValue.Equals(fontName, "QQOEIV+Calibri")) {
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textfont",
										BaseFont.createFont("c:/windows/fonts/QQOEIV+Calibri.ttf", BaseFont.WINANSI,
												true),
										null);
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textsize",
										fontSize, null);
							} else if (checkValue.Equals(fontName, "NWUMWK+Calibri")) {
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textfont",
										BaseFont.createFont("c:/windows/fonts/NWUMWK+Calibri.ttf", BaseFont.WINANSI,
												true),
										null);
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textsize",
										fontSize, null);
							} else if (checkValue.Equals(fontName, "MEFWIV+Calibri")) {
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textfont",
										BaseFont.createFont("c:/windows/fonts/MEFWIV+Calibri.ttf", BaseFont.WINANSI,
												true),
										null);
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textsize",
										fontSize, null);
							} else if (checkValue.Equals(fontName, "Arial,Bold")) {
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textfont",
										arialBold, null);
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textsize",
										fontSize, null);
							} else if (checkValue.Equals(fontName, "Arial")) {
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textfont", arial,
										null);
								stamper.getAcroFields().setFieldProperty(sheetIndex + "-" + fldName, "textsize",
										fontSize, null);
							}

						}
					} else {
						AcroFields.Item itemRev = readerOrigin.getAcroFields()
								.getFieldItem(fldName.replace(sheetIndex + "-", ""));
						if (itemRev != null) {
							PdfDictionary merged = itemRev.getMerged(0);
							TextField textField = new TextField(null, null, null);
							readerOrigin.getAcroFields().decodeGenericDictionary(merged, textField);
							if (textField != null && textField.getFont() != null) {
								String[][] fullFontName = textField.getFont().getFullFontName();
								fontName = fullFontName[0][3];
								float fontSize = textField.getFontSize();
								if (checkValue.Equals(fontName, "Calibri")) {
									stamper.getAcroFields().setFieldProperty(fldName, "textfont", font, null);
									stamper.getAcroFields().setFieldProperty(fldName, "textsize", fontSize, null);
								} else if (checkValue.Equals(fontName, "Calibri,Bold")) {
									stamper.getAcroFields().setFieldProperty(fldName, "textfont", bold, null);
									stamper.getAcroFields().setFieldProperty(fldName, "textsize", fontSize, null);
								} else if (checkValue.Equals(fontName, "FXRIAY+Calibri")) {
									stamper.getAcroFields().setFieldProperty(fldName, "textfont", BaseFont.createFont(
											"c:/windows/fonts/FXRIAY+Calibri.ttf", BaseFont.WINANSI, true), null);
									stamper.getAcroFields().setFieldProperty(fldName, "textsize", fontSize, null);
								} else if (checkValue.Equals(fontName, "SAVURJ+Calibri")) {
									stamper.getAcroFields().setFieldProperty(fldName, "textfont", BaseFont.createFont(
											"c:/windows/fonts/SAVURJ+Calibri.ttf", BaseFont.WINANSI, true), null);
									stamper.getAcroFields().setFieldProperty(fldName, "textsize", fontSize, null);
								} else if (checkValue.Equals(fontName, "SNPUUI+Calibri")) {
									stamper.getAcroFields().setFieldProperty(fldName, "textfont", BaseFont.createFont(
											"c:/windows/fonts/SNPUUI+Calibri.ttf", BaseFont.WINANSI, true), null);
									stamper.getAcroFields().setFieldProperty(fldName, "textsize", fontSize, null);
								} else if (checkValue.Equals(fontName, "CUXAZL+Calibri")) {
									stamper.getAcroFields().setFieldProperty(fldName, "textfont", BaseFont.createFont(
											"c:/windows/fonts/CUXAZL+Calibri.ttf", BaseFont.WINANSI, true), null);
									stamper.getAcroFields().setFieldProperty(fldName, "textsize", fontSize, null);
								} else if (checkValue.Equals(fontName, "OOAHEL+Calibri")) {
									stamper.getAcroFields().setFieldProperty(fldName, "textfont", BaseFont.createFont(
											"c:/windows/fonts/OOAHEL+Calibri.ttf", BaseFont.WINANSI, true), null);
									stamper.getAcroFields().setFieldProperty(fldName, "textsize", fontSize, null);
								} else if (checkValue.Equals(fontName, "QQOEIV+Calibri")) {
									stamper.getAcroFields().setFieldProperty(fldName, "textfont", BaseFont.createFont(
											"c:/windows/fonts/QQOEIV+Calibri.ttf", BaseFont.WINANSI, true), null);
									stamper.getAcroFields().setFieldProperty(fldName, "textsize", fontSize, null);
								} else if (checkValue.Equals(fontName, "NWUMWK+Calibri")) {
									stamper.getAcroFields().setFieldProperty(fldName, "textfont", BaseFont.createFont(
											"c:/windows/fonts/NWUMWK+Calibri.ttf", BaseFont.WINANSI, true), null);
									stamper.getAcroFields().setFieldProperty(fldName, "textsize", fontSize, null);
								} else if (checkValue.Equals(fontName, "MEFWIV+Calibri")) {
									stamper.getAcroFields().setFieldProperty(fldName, "textfont", BaseFont.createFont(
											"c:/windows/fonts/MEFWIV+Calibri.ttf", BaseFont.WINANSI, true), null);
									stamper.getAcroFields().setFieldProperty(fldName, "textsize", fontSize, null);
								} else if (checkValue.Equals(fontName, "Arial,Bold")) {
									stamper.getAcroFields().setFieldProperty(fldName, "textfont", arialBold, null);
									stamper.getAcroFields().setFieldProperty(fldName, "textsize", fontSize, null);
								} else if (checkValue.Equals(fontName, "Arial")) {
									stamper.getAcroFields().setFieldProperty(fldName, "textfont", arial, null);
									stamper.getAcroFields().setFieldProperty(fldName, "textsize", fontSize, null);
								}

							}
						}

					}
				}

			}
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
	}

	public void addFieldsIndex(PdfStamper stamper, String index) {

		try {
			stamper.getWriter().getAcroForm().setNeedAppearances(false);
			for (String fldName : TITLE_BLOCK_FIELDS_NAME) {
				stamper.getAcroFields().renameField(fldName, index + "-" + fldName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void removeFieldsIndex(PdfStamper stamper, int pv001Index, String index) {

		try {
			stamper.getWriter().getAcroForm().setNeedAppearances(false);
			for (String fldName : TITLE_BLOCK_FIELDS_NAME) {
				stamper.getAcroFields().renameField(pv001Index + "-" + index + "-" + fldName, fldName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}

	}

	public void removeRevisionFieldsIndex(PdfStamper stamper, AcroFields form, int sheetIndex) {

		try {
			stamper.getWriter().getAcroForm().setNeedAppearances(false);
			Set<String> fldNames = form.getFields().keySet();
			List<String> toupdate = new ArrayList<>();

			for (String fldName : fldNames) {
				if (fldName != null && fldName.contains(sheetIndex + "-")) {
					toupdate.add(fldName);
				}
			}
			for (String fldName : toupdate) {
				stamper.getAcroFields().renameField(fldName, fldName.replaceFirst(sheetIndex + "-", ""));
			}
		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}

	}

}
