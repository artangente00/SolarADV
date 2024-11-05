package com.PlayGroundAdv.Solar.service.generate_planset;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitDrafterDataEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.model.AHJNotesModel;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.service.generate_planset.drafter_details.DrfaterDetailsMapping;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ElectricUtilityNumber;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.ModuleInverterMfgQty;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

/*
 * @author Arij
 */
@Service
@Transactional
public class PlanSetServiceE100 {

	final CheckValueTypesService checkValue;
	final GetPDFReaderService getPDFReaderService;
	final PlansetLogo_SignatureMappingService logoSignMapping;
	final DrfaterDetailsMapping drfaterDetailsMapping;
	final ModuleInverterMfgQty moduleInverterMfgQty;
	final TechnicalProblemMsg technicalProblemMsg;
	final ElectricUtilityNumber electricUtilityNumber;

	public PlanSetServiceE100(CheckValueTypesService checkValue, 
			GetPDFReaderService getPDFReaderService, PlansetLogo_SignatureMappingService logoSignMapping,
			DrfaterDetailsMapping drfaterDetailsMapping, ModuleInverterMfgQty moduleInverterMfgQty,
			TechnicalProblemMsg technicalProblemMsg, ElectricUtilityNumber electricUtilityNumber) {
		super();
		this.checkValue = checkValue;
		this.getPDFReaderService = getPDFReaderService;
		this.logoSignMapping = logoSignMapping;
		this.drfaterDetailsMapping = drfaterDetailsMapping;
		this.moduleInverterMfgQty = moduleInverterMfgQty;
		this.technicalProblemMsg = technicalProblemMsg;
		this.electricUtilityNumber = electricUtilityNumber;
	}

	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

	public File buildingPDFE100(PermitHomeSiteInfoEntity permitHomeSite, Long idPermit, PermitEntity permitEntity,
			PermitArrayEntityResultSecond permitArraysEntityResult, PermitDrafterDataEntity permitDrafterDatanfo,
			Inverters inverterInfo, ElectricalUtilityEntity electricalCompany, PdfReader reader, int sheetIndex,
			String filePath, AHJNotesModel ahjNotes, Cmodulev2 moduleInfo, Inverters microInverterInfo,
			Boolean hasUnderGroundCircuit, ACDisconnect acDisconnect,  PlansetUtils plansetUtils) {

		// you only need a PdfStamper if you're going to change the existing PDF.

		File fileRe = null;

		if ((checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Neither")
				|| checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(),
						"System Optimizer"))) {
			try {

				fileRe = new File(
						Constants.rapportPlansetFolderUrl + "PDF-E100-STRING" + idPermit + "-" + sheetIndex + ".pdf");
				if (fileRe.exists()) {
					fileRe.delete();
					fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E100-STRING" + idPermit + "-"
							+ sheetIndex + ".pdf");
				}
				PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
				AcroFields form = stamper.getAcroFields();

				// A.B remove sheet index if exist when the project was uploaded
				if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
					getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
				}

				PdfReader readerOriginNEC = new PdfReader(
						Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-E100-STRING.pdf");
				PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-E100-STRING.pdf");
				// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
				getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "E100");
				// A.B: Set PDF Font For Revision
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
						sheetIndex);
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
						sheetIndex);

				// A.B CR-3250 03-30 Logo & Signature Mapping
				logoSignMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper,
						filePath);

				// A.B 07-14-2021 CR-3064
				form.setField(sheetIndex + "-" + "SPECIAL-NOTE-E-100", ahjNotes.getE100Note());
				// A.B 09-22 CR-3438-MOD-002
				drfaterDetailsMapping.e100ScaleMapping(form, sheetIndex, "SCALE-E100", permitDrafterDatanfo);
				
				if (acDisconnect != null){
					// F.S: 5/28/20 CR-PM-3290 Map Ac disconnect Note to E-100
					form.setField(sheetIndex+"-"+"E100-ACD-Note", "PV AC DISCONNECT LOCATED ON ACCESSIBLE EXTERIOR WALL WITH EXTERNAL HANDLE VISIBLE, LOCKABLE & LABELED WITHIN 10 FEET OF THE METER.");
				}else form.setField(sheetIndex+"-"+"E100-ACD-Note", "");
				
				// A.B 01-11-2022 CR-PM-REV-2197-MOD-15
				String utility = checkValue.Equals(permitHomeSite.getUtilityCompanyName(), "Other") ? permitHomeSite.getUtilityCompanyNameOther() : electricalCompany.getUtilityCompanyName();
				moduleInverterMfgQty.moduleInverterMfgQtyStringInverter("-E-100", form, sheetIndex, idPermit, inverterInfo,
						moduleInfo,( checkValue.containsCaseInsensitive(utility,"Oncor") || checkValue.EqualsCaseInsensitive(utility,"CenterPoint Energy Houston Electric LLC")), permitHomeSite.getMeterNumber());
				
				electricUtilityNumber.mapEsiId(form, sheetIndex, "E100", permitHomeSite, electricalCompany);
				mapUnderGroundTrenchingDetail(stamper, hasUnderGroundCircuit);
				stamper.close();
				reader.close();

			} catch (IOException | DocumentException e) {
				technicalProblemMsg.traiterException(e);
			}

		} else if ((checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "Micro Inverter")
				|| checkValue.Equals(permitArraysEntityResult.getDeviceToIncorporate(), "AC Modules"))) {

			try {

				fileRe = new File(
						Constants.rapportPlansetFolderUrl + "PDF-E100-MICRO" + idPermit + "-" + sheetIndex + ".pdf");
				if (fileRe.exists()) {
					fileRe.delete();
					fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E100-MICRO" + idPermit + "-" + sheetIndex
							+ ".pdf");
				}
				PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
				AcroFields form = stamper.getAcroFields();

				// A.B remove sheet index if exist when the project was uploaded
				if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
					getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
				}

				PdfReader readerOriginNEC = new PdfReader(
						Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-E100-MICRO.pdf");
				PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-E100-MICRO.pdf");

				// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
				getPDFReaderService.addFieldsIndex(stamper, readerOriginNEC, sheetIndex, "E100");
				getPDFReaderService.addFieldsIndex(stamper, readerOriginCEC, sheetIndex, "E100");
				// A.B: Set PDF Font For Revision
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
						sheetIndex);
				getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
						sheetIndex);

				// A.B CR-3250 03-30 Logo & Signature Mapping
				logoSignMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper,
						filePath);

				// A.B 07-14-2021 CR-3064
				form.setField(sheetIndex + "-" + "SPECIAL-NOTE-E-100", ahjNotes.getE100Note());
				// A.B 09-22 CR-3438-MOD-002
				
				drfaterDetailsMapping.e100ScaleMapping(form, sheetIndex, "SCALE-E100", permitDrafterDatanfo);
				
				if (acDisconnect != null){
					// F.S: 5/28/20 CR-PM-3290 Map Ac disconnect Note to E-100
					form.setField(sheetIndex+"-"+"E100-ACD-Note", "PV AC DISCONNECT LOCATED ON ACCESSIBLE EXTERIOR WALL WITH EXTERNAL HANDLE VISIBLE, LOCKABLE & LABELED WITHIN 10 FEET OF THE METER.");
				}else form.setField(sheetIndex+"-"+"E100-ACD-Note", "");
				
				// A.B 01-11-2022 CR-PM-REV-2197-MOD-15
				String utility = checkValue.Equals(permitHomeSite.getUtilityCompanyName(), "Other") ? permitHomeSite.getUtilityCompanyNameOther() : electricalCompany.getUtilityCompanyName();
				moduleInverterMfgQty.moduleInverterMfgQtyMicro("-E-100",form, permitArraysEntityResult.getDeviceToIncorporate(),
						idPermit, sheetIndex, moduleInfo, microInverterInfo, (checkValue.containsCaseInsensitive(utility,"Oncor") || checkValue.EqualsCaseInsensitive(utility,"CenterPoint Energy Houston Electric LLC")), permitHomeSite.getMeterNumber(),    plansetUtils);
				//A.W CR-PM-798 25-05-2022
				
				electricUtilityNumber.mapEsiId(form, sheetIndex, "E100", permitHomeSite, electricalCompany);
				
				// A.B CR-PM-3227
				mapUnderGroundTrenchingDetail(stamper, hasUnderGroundCircuit);
				
				stamper.close();
				reader.close();

			} catch (IOException | DocumentException e) {
				e.printStackTrace();
				technicalProblemMsg.traiterException(e);
			}
		}
		return fileRe;
	}

	private void mapUnderGroundTrenchingDetail(PdfStamper stamper, Boolean hasUnderGroundCircuit) {
		try {
			// A.B 08-01-2022 REV-CR-PM-3227
			if (Boolean.TRUE.equals(hasUnderGroundCircuit)) {
				String[] EXTRA = { Constants.rapportPlansetFolderUrl + "Trench Detail.pdf" };
				PdfContentByte canvas = stamper.getUnderContent(1);
				PdfReader r;
				PdfImportedPage page;
				for (String path1 : EXTRA) {
					r = new PdfReader(path1);
					page = stamper.getImportedPage(r, 1);
					canvas.addTemplate(page, 910f, 65f);
					stamper.getWriter().freeReader(r);
					r.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
