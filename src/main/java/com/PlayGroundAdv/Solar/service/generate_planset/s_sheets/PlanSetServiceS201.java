package com.PlayGroundAdv.Solar.service.generate_planset.s_sheets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.Flashing;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.entity.RoofMaterialType;
import com.PlayGroundAdv.Solar.entity.SsheetRackingMappingEntity;
import com.PlayGroundAdv.Solar.entity.SsheetSpacingMappingEntity;
import com.PlayGroundAdv.Solar.model.AHJNotesModel;
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
public class PlanSetServiceS201 {

	final CheckValueTypesService checkValueTypesService;
	final GetPDFReaderService getPDFReaderService;
	final PlansetLogo_SignatureMappingService logoSignatureMapping;
	final GetRackingSSheets getRackingSSheets;
	final GetSpacingSSheets getSpacingSSheets;
	final UserSettingRepository userSettingRepository;
	final TechnicalProblemMsg technicalProblemMsg;

	public PlanSetServiceS201(CheckValueTypesService checkValueTypesService, 
			GetPDFReaderService getPDFReaderService, PlansetLogo_SignatureMappingService logoSignatureMapping,
			GetRackingSSheets getRackingSSheets, GetSpacingSSheets getSpacingSSheets,
			UserSettingRepository userSettingRepository, TechnicalProblemMsg technicalProblemMsg) {
		super();
		this.checkValueTypesService = checkValueTypesService;
		this.getPDFReaderService = getPDFReaderService;
		this.logoSignatureMapping = logoSignatureMapping;
		this.getRackingSSheets = getRackingSSheets;
		this.getSpacingSSheets = getSpacingSSheets;
		this.userSettingRepository = userSettingRepository;
		this.technicalProblemMsg = technicalProblemMsg;
	}

	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

	public File buildingPDFS201(PermitHomeSiteInfoEntity permitHomeSite, 
			PermitProjectSiteInfoEntity permitProjectSiteInfo, PermitEntity permitEntity,
			ElectricalUtilityEntity electricalCompany, AuthentificationEntity userConnectedEntity, PdfReader reader,
			int sheetIndex, String filePath, Flashing flashing,
			RoofAttachmentsEntity stanchionFoot, RailRacking railRacking, RoofMaterialType roofMaterialType,
			Long submitId,AHJNotesModel ahjNotes) {

		// you only need a PdfStamper if you're going to change the existing PDF.
		File fileRe = null;
		try {

			fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-S201" + permitEntity.getId() + "-" + sheetIndex + ".pdf");
			if (fileRe.exists()) {
				fileRe.delete();
				fileRe = new File(
						Constants.rapportPlansetFolderUrl + "PDF-S201" + permitEntity.getId() + "-" + sheetIndex + ".pdf");
			}
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
			AcroFields form = stamper.getAcroFields();
			
			//A.B remove sheet index if exist when the project was uploaded
			if(permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
				getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
			}
			// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
			getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "S201");
			// A.B: Set PDF Font For Revision
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, reader, permitHomeSite, form, sheetIndex);

			// A.B CR-3250 03-30 Logo & Signature Mapping
			logoSignatureMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);
			
			//A.B 07-14-2021 CR-3064
			form.setField(sheetIndex+"-"+"SPECIAL-NOTE-S-201",ahjNotes.getS201Note());
			// A.B S Sheets Quads Clean & Mapping
			cleanFileMapping(stamper);
			// A.B S Sheets Quads Mapping
			sSheetFileMapping(stamper, form, permitEntity, submitId, userConnectedEntity, stanchionFoot, railRacking,
					flashing, electricalCompany, roofMaterialType, permitHomeSite, permitProjectSiteInfo, sheetIndex);

			stamper.close();
			reader.close();

		} catch (IOException|DocumentException e) {
			technicalProblemMsg.traiterException(e);
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
			if(checkValueTypesService.Equals(stanchionSetting, "Don’t Stagger Roof Anchors within Rows of Modules, but Stagger Anchors by Rows") )
				stanchionType = "Staggered Anchors by rows";
			else if(checkValueTypesService.Equals(stanchionSetting, "Don’t stagger Roof Anchors, Show as many Anchors as possible on the same framing members"))
				stanchionType = "Lined Up";
			String roofRafter = getRackingSSheets.getRoofRafter(permitHomeSite.getRoofRafter());
			if (permitHomeSite.getRoofRafterOther() != null) {
				if (checkValueTypesService.Equals(permitHomeSite.getRoofRafterOther(), "OTHER")) {
					roofRafter = permitHomeSite.getSecroofRafterOther();
				} else if (checkValueTypesService.isStringNotEmpty(permitHomeSite.getRoofRafterOther())) {
					roofRafter = permitHomeSite.getRoofRafterOther();
				}
			}

			String ahj = permitHomeSite.getProjectJurisOther();
			if (checkValueTypesService.Equals(permitHomeSite.getProjectJurisdiction(), "city")) {
				if (checkValueTypesService.NotEquals(permitHomeSite.getCity(), "Other")) {
					ahj = "City of " + permitHomeSite.getCity();

				} else if (permitHomeSite.getProjectJurisOther() != null) {
					ahj = "City of " + permitHomeSite.getProjectJurisOther();
				} else {
					ahj = "City of ";
				}

			} else if (permitHomeSite.getProjectJurisdiction() != null
					&& permitHomeSite.getProjectJurisdiction().contains("County")) {
				if (checkValueTypesService.NotEquals(permitHomeSite.getCity(), "Other")) {
					ahj = permitHomeSite.getProjectJurisdiction();
				} else if (permitHomeSite.getProjectJurisOther() != null) {
					ahj = "County of " + permitHomeSite.getProjectJurisOther();
				} else {
					ahj = "County of ";
				}

			}

			SsheetRackingMappingEntity rackingOne = getRackingSSheets.getRackingFileName(stanchionFoot, roofRafter,
					railRacking, flashing, electricalCompany, roofMaterialType.getTypeRoof(), ahj, "S-201", "1",hasFlashing, permitHomeSite.getState(),stanchionType);
			if (rackingOne != null) {
				quadMapping(stamper, rackingOne.getSSheetFile().getPdfName(), 45f, 400f);
				form.setField(sheetIndex + "-STANCHION-ATTACHMENT-DETAIL", rackingOne.getDetailsHeading());
			} else {
				SsheetSpacingMappingEntity spacingOne = getSpacingSSheets.getSpacingFileName(permitEntity, submitId,
						userConnectedEntity, roofRafter, permitProjectSiteInfo.getRafterTrussSpacing(),
						permitHomeSite.getStanchionMaxSpacing(), "S-201", "1", stanchionType,hasFlashing);
				if (spacingOne != null) {
					quadMapping(stamper, spacingOne.getSSheetFile().getPdfName(), 45f, 400f);
					form.setField(sheetIndex + "-STANCHION-ATTACHMENT-DETAIL", spacingOne.getDetailsHeading());
				} else { 
					//A.B CR-3361 09-21 NOT USED S-Sheet Quad title Mapping
					form.setField(sheetIndex + "-STANCHION-ATTACHMENT-DETAIL", "NOT USED");
				}
			}

			SsheetRackingMappingEntity rackingTwo = getRackingSSheets.getRackingFileName(stanchionFoot, roofRafter,
					railRacking, flashing, electricalCompany, roofMaterialType.getTypeRoof(), ahj, "S-201", "2",hasFlashing, permitHomeSite.getState(),stanchionType);
			if (rackingTwo != null) {
				quadMapping(stamper, rackingTwo.getSSheetFile().getPdfName(), 477f, 400f);
				form.setField(sheetIndex + "-RAIL-SPLICE-DETAIL", rackingTwo.getDetailsHeading());
			} else {
				SsheetSpacingMappingEntity spacingTwo = getSpacingSSheets.getSpacingFileName(permitEntity, submitId,
						userConnectedEntity, roofRafter, permitProjectSiteInfo.getRafterTrussSpacing(),
						permitHomeSite.getStanchionMaxSpacing(), "S-201", "2", stanchionType,hasFlashing);
				if (spacingTwo != null) {
					quadMapping(stamper, spacingTwo.getSSheetFile().getPdfName(), 477f, 400f);
					form.setField(sheetIndex + "-RAIL-SPLICE-DETAIL", spacingTwo.getDetailsHeading());
				} else { 
					//A.B CR-3361 09-21 NOT USED S-Sheet Quad title Mapping
					form.setField(sheetIndex + "-RAIL-SPLICE-DETAIL", "NOT USED");
				}
			}

			SsheetRackingMappingEntity rackingThree = getRackingSSheets.getRackingFileName(stanchionFoot, roofRafter,
					railRacking, flashing, electricalCompany, roofMaterialType.getTypeRoof(), ahj, "S-201", "3",hasFlashing, permitHomeSite.getState(),stanchionType);
			if (rackingThree != null) {
				quadMapping(stamper, rackingThree.getSSheetFile().getPdfName(), 45f, 40f);
				form.setField(sheetIndex + "-RAIL-EXTENSION-OPTION-FOR-MODULE-GROUP-EQUIPMENT",
						rackingThree.getDetailsHeading());
			} else {
				SsheetSpacingMappingEntity spacingThree = getSpacingSSheets.getSpacingFileName(permitEntity, submitId,
						userConnectedEntity, roofRafter, permitProjectSiteInfo.getRafterTrussSpacing(),
						permitHomeSite.getStanchionMaxSpacing(), "S-201", "3", stanchionType,hasFlashing);
				if (spacingThree != null) {
					quadMapping(stamper, spacingThree.getSSheetFile().getPdfName(), 45f, 40f);
					form.setField(sheetIndex + "-RAIL-EXTENSION-OPTION-FOR-MODULE-GROUP-EQUIPMENT",
							spacingThree.getDetailsHeading());
				} else { 
					//A.B CR-3361 09-21 NOT USED S-Sheet Quad title Mapping
					form.setField(sheetIndex + "-RAIL-EXTENSION-OPTION-FOR-MODULE-GROUP-EQUIPMENT",
							"NOT USED");
				}
			}

			SsheetRackingMappingEntity rackingFour = getRackingSSheets.getRackingFileName(stanchionFoot, roofRafter,
					railRacking, flashing, electricalCompany, roofMaterialType.getTypeRoof(), ahj, "S-201", "4",hasFlashing, permitHomeSite.getState(),stanchionType);
			if (rackingFour != null) {
				quadMapping(stamper, rackingFour.getSSheetFile().getPdfName(), 477f, 40f);
				form.setField(sheetIndex + "-RAFTER-HOLE-DETAIL-DIAGFIG-1", rackingFour.getDetailsHeading());
			} else {
				SsheetSpacingMappingEntity spacingFour = getSpacingSSheets.getSpacingFileName(permitEntity, submitId,
						userConnectedEntity, roofRafter, permitProjectSiteInfo.getRafterTrussSpacing(),
						permitHomeSite.getStanchionMaxSpacing(), "S-201", "4", stanchionType,hasFlashing);
				if (spacingFour != null) {
					quadMapping(stamper, spacingFour.getSSheetFile().getPdfName(), 477f, 40f);
					form.setField(sheetIndex + "-RAFTER-HOLE-DETAIL-DIAGFIG-1", spacingFour.getDetailsHeading());
				} else {
					//A.B CR-3361 09-21 NOT USED S-Sheet Quad title Mapping
					form.setField(sheetIndex + "-RAFTER-HOLE-DETAIL-DIAGFIG-1",
							"NOT USED");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void quadMapping(PdfStamper stamper, String fileName, Float x, Float y) {
		try {
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

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// A.B Clear Previous S sheet Mapping
	public void cleanFileMapping(PdfStamper stamper) {

		List<PdfCleanUpLocation> cleanUpLocations = new ArrayList<PdfCleanUpLocation>();
		try {
			cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(45f, 399f, 470, 736f), BaseColor.WHITE)); // Details
																													// 1
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {
			cleanUpLocations.add(new PdfCleanUpLocation(1, new Rectangle(474f, 399f, 900, 736f), BaseColor.WHITE)); // Details
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
