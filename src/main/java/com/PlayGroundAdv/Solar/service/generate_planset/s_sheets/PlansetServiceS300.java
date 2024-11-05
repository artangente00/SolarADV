package com.PlayGroundAdv.Solar.service.generate_planset.s_sheets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.SsheetLibraryEntity;
import com.PlayGroundAdv.Solar.model.AHJNotesModel;
import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.repositories.PermitSketchRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.SsheetLibraryRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.PlansetLogo_SignatureMappingService;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ElectricUtilityNumber;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ModuleInverterMfgQty;
import com.PlayGroundAdv.Solar.service.log.SheetsLogService;
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
public class PlansetServiceS300 {

	final CheckValueTypesService checkValue;
	final GetPDFReaderService getPDFReaderService;
	final SheetsLogService missingSheetService;
	final PlansetLogo_SignatureMappingService logoSignMapping;
	final ModuleInverterMfgQty moduleInverterMfgQty;
	final PermitSketchRepository permitSketchRepo;
	final SsheetLibraryRepository sSheetRepo;
	final TechnicalProblemMsg technicalProblemMsg;
	final ElectricUtilityNumber electricUtilityNumber;

	public PlansetServiceS300(CheckValueTypesService checkValue, 
			GetPDFReaderService getPDFReaderService, SheetsLogService missingSheetService,
			PlansetLogo_SignatureMappingService logoSignMapping, ModuleInverterMfgQty moduleInverterMfgQty,
			PermitSketchRepository permitSketchRepo, TechnicalProblemMsg technicalProblemMsg,
			SsheetLibraryRepository sSheetRepo, ElectricUtilityNumber electricUtilityNumber) {
		super();
		this.checkValue = checkValue;
		this.getPDFReaderService = getPDFReaderService;
		this.missingSheetService = missingSheetService;
		this.logoSignMapping = logoSignMapping;
		this.moduleInverterMfgQty = moduleInverterMfgQty;
		this.permitSketchRepo = permitSketchRepo;
		this.technicalProblemMsg = technicalProblemMsg;
		this.sSheetRepo = sSheetRepo;
		this.electricUtilityNumber = electricUtilityNumber;
	}

	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

	// *******06/03/2019 : CI :Junit test and correction*******
	public void mapS300Sheet(RailRacking railRackingGround, PermitAdvEntityResult advInfo,
			PermitHomeSiteInfoEntity permitHomeSite, PdfStamper stamper, Long idPermit,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, Cmodulev2 moduleInfo, PermitEntity permitEntity,
			AuthentificationEntity userConnectedEntity, Long submitId) {
		if (railRackingGround != null && railRackingGround.getManufacturer() != null
				&& railRackingGround.getModel() != null) {

			if(permitEntity.getPlansetVersion() > 1) {
				
				List<PdfCleanUpLocation> cleanUpLocations = new ArrayList<>();
				try {
					cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(45f, 397f, 575, 755f), BaseColor.WHITE));
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(579f, 397f, 903, 755f), BaseColor.WHITE));
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(903f, 397f, 1111, 666f), BaseColor.WHITE));
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(45f, 45f, 575, 394f), BaseColor.WHITE));
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(579f, 45f, 1111f, 394f), BaseColor.WHITE));
				} catch (Exception e) {
					e.printStackTrace();
				}
				PdfCleanUpProcessor cleaner = new PdfCleanUpProcessor(cleanUpLocations, stamper);
				try {
					cleaner.cleanUp();
				} catch (IOException|DocumentException e2) {
					e2.printStackTrace();
				}
			}

			String railMake = railRackingGround.getManufacturer().trim();
			String moduleLayout = "";
			String sizeOfPipe = "";
			String footingDiameter = "";
			String exposureCategory = "";
			String winSpeed = "";
			String snowLoad = "";

			String pdfName = "";
			String pdfPath = "";
			if (railMake.contains("SnapNrack")) {
				railMake = "SR";
			} else if (railMake.contains("IronRidge")) {
				railMake = "IR";
			}
			if (advInfo != null && checkValue.NotEquals(advInfo.getModuleLayout(), "")) {
				if (checkValue.Equals(advInfo.getModuleLayout(), "Other")
						&& checkValue.NotEquals(advInfo.getModuleLayoutOther(), "")) {
					moduleLayout = advInfo.getModuleLayoutOther();
				} else {
					if (checkValue.Equals(advInfo.getModuleLayout(), "3upL")) {
						moduleLayout = "3UPL";
					} else if (checkValue.Equals(advInfo.getModuleLayout(), "4upL")) {
						moduleLayout = "4UPL";
					} else if (checkValue.Equals(advInfo.getModuleLayout(), "5upL")) {
						moduleLayout = "5UPL";
					} else if (checkValue.Equals(advInfo.getModuleLayout(), "6upL")) {
						moduleLayout = "6UPL";
					} else if (checkValue.Equals(advInfo.getModuleLayout(), "2upP")) {
						moduleLayout = "2UPP";
					} else if (checkValue.Equals(advInfo.getModuleLayout(), "3upP")) {
						moduleLayout = "3UPP";
					} else if (checkValue.Equals(advInfo.getModuleLayout(), "4upP")) {
						moduleLayout = "4UPP";
					}
				}

			}

			if (advInfo != null && checkValue.NotEquals(advInfo.getSizeOfPipe(), "")) {
				if (checkValue.Equals(advInfo.getSizeOfPipe(), "Other")) {
					// *******06/03/2019 : CI :Junit test and correction*******
					if (checkValue.NotEquals(advInfo.getSizeOfPipeOther(), "")) {
						sizeOfPipe = "_(" + advInfo.getSizeOfPipeOther().split("\"")[0] + "”";
					}
				} else
					sizeOfPipe = "_(" + advInfo.getSizeOfPipe() + "”";
			}
			if (checkValue.Equals(sizeOfPipe, "1-1⁄2")) {
				sizeOfPipe = "_(1-½" + "”";
			} else if (checkValue.Equals(sizeOfPipe, "2-1⁄2")) {
				sizeOfPipe = "_(2-½" + "”";
			}
			if (advInfo != null && checkValue.NotEquals(advInfo.getFootingDiameter(), "")) {
				if (checkValue.Equals(advInfo.getFootingDiameter(), "Other")) {
					// *******06/03/2019 : CI :Junit test and correction*******
					if (checkValue.NotEquals(advInfo.getFootingDiameterOther(), "")) {
						footingDiameter = "_" + advInfo.getFootingDiameterOther().split("\"")[0] + "”";
					}
				} else
					footingDiameter = "_" + advInfo.getFootingDiameter() + "”";
			}

			if (permitHomeSite != null
					&& checkValue.NotEquals(permitHomeSite.getResidenceBindingCategory(), "")) {
				if (checkValue.Equals(permitHomeSite.getResidenceBindingCategory(), "Other")) {
					if (checkValue.NotEquals(permitHomeSite.getTextOtherExpo(), "")) {
						exposureCategory = "_EX-" + permitHomeSite.getTextOtherExpo();
					}
				}

				else
					exposureCategory = "_EX-" + permitHomeSite.getResidenceBindingCategory().split(" ")[1];
			}

			if (advInfo != null && checkValue.NotEquals(advInfo.getWindSpeed(), "")) {
				if (checkValue.Equals(advInfo.getWindSpeed(), "Other")) {
					if (checkValue.NotEquals(advInfo.getWindSpeedOther(), "")) {
						winSpeed = "_WS" + advInfo.getWindSpeedOther();
					}
				} else
					winSpeed = "_WS" + advInfo.getWindSpeed();
			}

			if (advInfo != null && checkValue.NotEquals(advInfo.getSnowLoad(), "")) {
				if (checkValue.Equals(advInfo.getSnowLoad(), "Other")) {
					if (checkValue.NotEquals(advInfo.getSnowLoadOther(), "")) {
						snowLoad = "_SL" + advInfo.getSnowLoadOther();
					}
				} else
					snowLoad = "_SL" + advInfo.getSnowLoad();
			}

			if (!checkValue.NotEquals(sizeOfPipe, "")) {
				pdfName = "S_GM_" + railMake + "_(" + moduleLayout + footingDiameter + exposureCategory + winSpeed
						+ snowLoad + ").pdf";
				pdfPath = "S_GM_" + railMake + "_(" + moduleLayout + footingDiameter + exposureCategory + winSpeed
						+ snowLoad + ")";
			} else {
				pdfName = "S_GM_" + railMake + sizeOfPipe + "_" + moduleLayout + footingDiameter + exposureCategory
						+ winSpeed + snowLoad + ").pdf";
				pdfPath = "S_GM_" + railMake + sizeOfPipe + "_" + moduleLayout + footingDiameter + exposureCategory
						+ winSpeed + snowLoad + ")";
			}

			File fileRe = null;
			String path = "";

			if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/" + pdfName).exists()) {
				fileRe = new File(Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/" + pdfName);
				path = Constants.rapportS200FolderUrl + "PlansetS200S201/New S200 S201 PDFs/";
			} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/DPW SOLAR/" + pdfName).exists()) {
				fileRe = new File(Constants.rapportS200FolderUrl + "PlansetS200S201/DPW SOLAR/" + pdfName);
				path = Constants.rapportS200FolderUrl + "PlansetS200S201/DPW SOLAR/";
			} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR ROOFTRAC/" + pdfName)
					.exists()) {
				fileRe = new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR ROOFTRAC/" + pdfName);
				path = Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR ROOFTRAC/";
			} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/" + pdfName)
					.exists()) {
				fileRe = new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/" + pdfName);
				path = Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/";
			} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/" + pdfName)
					.exists()) {
				fileRe = new File(Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/" + pdfName);
				path = Constants.rapportS200FolderUrl + "PlansetS200S201/PROSOLAR TILETRAC/";
			} else if (new File(Constants.rapportS200FolderUrl + "PlansetS200S201/Stanchion Spacing/" + pdfName)
					.exists()) {
				fileRe = new File(Constants.rapportS200FolderUrl + "PlansetS200S201/Stanchion Spacing/" + pdfName);
				path = Constants.rapportS200FolderUrl + "PlansetS200S201/Stanchion Spacing/";
			}

			SsheetLibraryEntity sSheet = sSheetRepo.findFirstByPdfNameAndIsDeleted(pdfName, false);

			if (fileRe != null && fileRe.exists() && sSheet != null) {

				try {

					PdfReader readerSsheet = new PdfReader(path + pdfName);
					File fileSsheet = new File(path + pdfPath + idPermit + ".pdf");
					if (fileSsheet.exists()) {
						fileSsheet.delete();
						fileSsheet = new File(path + pdfPath + idPermit + ".pdf");
					}
					PdfStamper stamperSsheet = new PdfStamper(readerSsheet, new FileOutputStream(fileSsheet));
					AcroFields formSsheet = stamperSsheet.getAcroFields();

					if (permitProjectSiteInfo != null) {
						if (checkValue.Equals(permitProjectSiteInfo.getNorthToShouthFin(), "Other")) {
							formSsheet.setField("GM-SLOPE-PERCENTAGE",
									permitProjectSiteInfo.getNorthToShouthFinOther() + "");
						} else {
							formSsheet.setField("GM-SLOPE-PERCENTAGE", permitProjectSiteInfo.getNorthToShouthFin());
						}
					}

					if (checkValue.Equals(advInfo.getSizeOfPipe(), "Other")) {
						formSsheet.setField("GM-SIZE-OF-PIPE", advInfo.getSizeOfPipeOther());
					} else {
						formSsheet.setField("GM-SIZE-OF-PIPE", advInfo.getSizeOfPipe());
					}

					if (checkValue.Equals(advInfo.getThicknessOfPipe(), "Other")) {
						formSsheet.setField("GM-THICKNESS-OF-PIPE", advInfo.getThicknessOfPipeOther());
					} else {
						formSsheet.setField("GM-THICKNESS-OF-PIPE", advInfo.getThicknessOfPipe());
					}

					if (advInfo.getBracedUnbraced() != null) {
						if (checkValue.Equals(advInfo.getBracedUnbraced(), "Braced")) {
							formSsheet.setField("GM-BRACED-OR-UNBRACED", "USED");
						} else if (checkValue.Equals(advInfo.getBracedUnbraced(), "Unbraced")) {
							formSsheet.setField("GM-BRACED-OR-UNBRACED", "NOT USED");
						}
					}

					if (checkValue.Equals(advInfo.getFootingDiameter(), "Other")) {
						formSsheet.setField("GM-FOOTING-DIAMETER", advInfo.getFootingDiameterOther());
					} else {
						formSsheet.setField("GM-FOOTING-DIAMETER", advInfo.getFootingDiameter());
					}
					// *******08/03/2019 : CI :Junit test and correction*******
					double h1 = 0;
					if (permitProjectSiteInfo != null && permitProjectSiteInfo.getHeightOfSouth() != null) {
						h1 = permitProjectSiteInfo.getHeightOfSouth();
					}
					double fi = 0;
					double aw = 0;
					double ps = 0;
					double h3 = 0;

					try {
						List<PermitSketchEntity> sketch = permitSketchRepo.findByPermitEntityId(idPermit);
						if (!sketch.isEmpty() && checkValue.isStringDouble(sketch.get(0).getModelvalue())) {
							fi = Math.toRadians(Double.parseDouble(sketch.get(0).getModelvalue()));
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
						technicalProblemMsg.traiterException(e);
					}

					try {
						if (checkValue.Equals(advInfo.getModuleLayout(), "Other")) {
							if (checkValue.contains((advInfo.getModuleLayoutOther()).toUpperCase(),
									"upL".toUpperCase()) && moduleInfo != null
									&& checkValue.isStringDouble(moduleInfo.getWidth())) {
								aw = Double.parseDouble(moduleInfo.getWidth())
										* Double.parseDouble(advInfo.getModuleLayoutOther().split("upL")[0]);
							} else if (checkValue.contains((advInfo.getModuleLayoutOther()).toUpperCase(),
									"upP".toUpperCase()) && moduleInfo != null
									&& checkValue.isStringDouble(moduleInfo.getWidth())) {
								aw = Double.parseDouble(moduleInfo.getLength())
										* Double.parseDouble(advInfo.getModuleLayoutOther().split("upP")[0]);
							}
						} else if (advInfo.getModuleLayout() != null && moduleInfo != null) {
							if (advInfo.getModuleLayout().contains("3upL")
									&& checkValue.isStringDouble(moduleInfo.getWidth())) {
								aw = Double.parseDouble(moduleInfo.getWidth()) * 3;
							} else if (advInfo.getModuleLayout().contains("4upL")
									&& checkValue.isStringDouble(moduleInfo.getWidth())) {
								aw = Double.parseDouble(moduleInfo.getWidth()) * 4;
							} else if (advInfo.getModuleLayout().contains("5upL")
									&& checkValue.isStringDouble(moduleInfo.getWidth())) {
								aw = Double.parseDouble(moduleInfo.getWidth()) * 5;
							} else if (advInfo.getModuleLayout().contains("6upL")
									&& checkValue.isStringDouble(moduleInfo.getWidth())) {
								aw = Double.parseDouble(moduleInfo.getWidth()) * 6;
							} else if (advInfo.getModuleLayout().contains("2upP")
									&& checkValue.isStringDouble(moduleInfo.getLength())) {
								aw = Double.parseDouble(moduleInfo.getLength()) * 2;
							} else if (advInfo.getModuleLayout().contains("3upP")
									&& checkValue.isStringDouble(moduleInfo.getLength())) {
								aw = Double.parseDouble(moduleInfo.getLength()) * 3;
							} else if (advInfo.getModuleLayout().contains("4upP")
									&& checkValue.isStringDouble(moduleInfo.getLength())) {
								aw = Double.parseDouble(moduleInfo.getLength()) * 4;
							}
						}

					} catch (NumberFormatException e1) {
						e1.printStackTrace();
						technicalProblemMsg.traiterException(e1);
					}

					if (checkValue.Equals(railMake, "IR")) {
						if (checkValue.Equals(advInfo.getModuleLayout(), "Other")) {
							if (checkValue.Equals(advInfo.getModuleLayoutOther(), "5upL")) {
								ps = 108;
							} else {
								ps = 90;
							}
						} else if (checkValue.Equals(advInfo.getModuleLayout(), "5upL")) {
							ps = 108;
						} else {
							ps = 90;
						}
					}

					double mazArrayHeight = 0;
					String mazArrayHeightString = "";
					try {

						h3 = (Math.tan(fi) * ps) + ((Math.sin(fi) * aw) / 2) - (Math.tan(fi) * (ps / 2));

						mazArrayHeight = h1 + h3;
						mazArrayHeightString = String.valueOf(new DecimalFormat("##.##").format(mazArrayHeight));
					} catch (NumberFormatException e) {
						e.printStackTrace();
						technicalProblemMsg.traiterException(e);
					}

					formSsheet.setField("GM-ARRAY-MAX-HEIGHT-VALUE", mazArrayHeightString);
					stamperSsheet.setFormFlattening(true);
					stamperSsheet.close();
					readerSsheet.close();
				} catch (IOException | DocumentException e) {
					e.printStackTrace();
					technicalProblemMsg.traiterException(e);
				}

				try {
					String[] EXTRA = { path + pdfPath + idPermit + ".pdf" };
					PdfContentByte canvas = stamper.getUnderContent(1);
					PdfReader r;
					PdfImportedPage page;
					for (String path1 : EXTRA) {
						r = new PdfReader(path1);
						page = stamper.getImportedPage(r, 1);
						canvas.addTemplate(page, 0, 2);
						stamper.getWriter().freeReader(r);
						r.close();
					}
				} catch (IOException e) {
					technicalProblemMsg.traiterException(e);
				}

			} else {
				// A.B 02-14 log Missing sheet
				missingSheetService.insertMissingSheet(pdfName, "Ground S-sheet", submitId, permitEntity,
						userConnectedEntity);
			}
		}
	}

	public File buildingPDFS300(PermitHomeSiteInfoEntity permitHomeSite, Long idPermit,
			ElectricalUtilityEntity electricalCompany, PermitEntity permitEntity,
			AuthentificationEntity userConnectedEntity, RailRacking railRackingGround, PermitAdvEntityResult advInfo,
			PermitProjectSiteInfoEntity permitProjectSiteInfo, Cmodulev2 moduleInfo,
			PermitArrayEntityResultSecond permitArraysEntityResult, PdfReader reader, int sheetIndex, String filePath,
			Inverters inverterInfo, Long submitId, AHJNotesModel ahjNotes, Inverters microInverterInfo,  PlansetUtils plansetUtils) {

		File fileRe = null;

		try {

			fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-S300" + idPermit + "-" + sheetIndex + ".pdf");
			if (fileRe.exists()) {
				fileRe.delete();
				fileRe = new File(
						Constants.rapportPlansetFolderUrl + "PDF-S300" + idPermit + "-" + sheetIndex + ".pdf");
			}

			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
			AcroFields form = stamper.getAcroFields();

			// A.B remove sheet index if exist when the project was uploaded
			if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
				getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
			}

			PdfReader readerOriginNEC = new PdfReader(Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-S300.pdf");
			PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-S300.pdf");

			// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
			getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "S300");
			// A.B: Set PDF Font For Revision
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
					sheetIndex);
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
					sheetIndex);

			// A.B CR-3250 03-30 Logo & Signature Mapping
			logoSignMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);

			// A.B 07-14-2021 CR-3064
			form.setField(sheetIndex + "-" + "SPECIAL-NOTE-S-300", ahjNotes.getS300Note());

			mapS300Sheet(railRackingGround, advInfo, permitHomeSite, stamper, idPermit, permitProjectSiteInfo,
					moduleInfo, permitEntity, userConnectedEntity, submitId);
			electricUtilityNumber.mapEsiId(form, sheetIndex, "S300", permitHomeSite, electricalCompany);

			// A.B 09-02-2021 CR-PM-REV-2197
			// A.B 01-11-2022 CR-PM-REV-2197-MOD-15
			String utility = checkValue.Equals(permitHomeSite.getUtilityCompanyName(), "Other") ? permitHomeSite.getUtilityCompanyNameOther() : electricalCompany.getUtilityCompanyName();
			moduleInverterMfgQty.moduleInverterMfgQty("-S-300",form, permitArraysEntityResult.getDeviceToIncorporate(),
					permitEntity.getId(), sheetIndex, inverterInfo, moduleInfo, microInverterInfo, utility, permitHomeSite.getMeterNumber(),    plansetUtils);

			stamper.close();
			reader.close();
			return fileRe;
		} catch (IOException | DocumentException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return null;
		}

	}

}
