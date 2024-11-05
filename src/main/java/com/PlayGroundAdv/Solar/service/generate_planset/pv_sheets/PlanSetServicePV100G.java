package com.PlayGroundAdv.Solar.service.generate_planset.pv_sheets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitDrafterDataEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;
import com.PlayGroundAdv.Solar.model.AHJNotesModel;
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
public class PlanSetServicePV100G {

	final CheckValueTypesService checkValue;
	final GetPDFReaderService getPDFReaderService;
	final PlansetLogo_SignatureMappingService logoSignatureMapping;
	final DrfaterDetailsMapping drfaterDetailsMapping;
	final ModuleInverterMfgQty moduleInverterMfgQty;
	final TechnicalProblemMsg technicalProblemMsg;
	final ElectricUtilityNumber electricUtilityNumber;

	public PlanSetServicePV100G(CheckValueTypesService checkValue, 
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

	public void mapPermitSketchEntity(List<PermitSketchEntity> listPermitSketchEntity, AcroFields form,
			int sheetIndex) {
		try {
			if (listPermitSketchEntity != null) {
				/*****************************************
				 * thTH AND TILS TABLE
				 *********************/
				for (int j = 0; j < 11; j++) {
					form.setField(sheetIndex + "-" + "SECTION-" + j + "-AZIMUTH", "");
					form.setField(sheetIndex + "-" + "SECTION-" + j, "");
					form.setField(sheetIndex + "-" + "SECTION-" + j + "-ROOF-PITCH-TILT", "");
				}
				int i = 0;
				for (PermitSketchEntity permitSketchEntity : listPermitSketchEntity) {
					if (checkValue.NotEquals(permitSketchEntity.getAzimuth(), "null")) {
						i++;
						form.setField(sheetIndex + "-" + "SECTION-" + i + "-AZIMUTH", permitSketchEntity.getAzimuth());
						form.setField(sheetIndex + "-" + "SECTION-" + i,
								"SECTION-" + permitSketchEntity.getArraySketch());
						form.setField(sheetIndex + "-" + "SECTION-" + i + "-ROOF-PITCH-TILT",
								permitSketchEntity.getModelvalue() + "°");

					}
				}

				for (i = listPermitSketchEntity.size() + 1; i < 11; i++) {
					form.setField(sheetIndex + "-" + "SECTION-" + i + "-AZIMUTH", "");
					form.setField(sheetIndex + "-" + "SECTION-" + i, "");
					form.setField(sheetIndex + "-" + "SECTION-" + i + "-ROOF-PITCH-TILT", "");
				}
			}
		} catch (IOException | DocumentException e) {
			technicalProblemMsg.traiterException(e);
		}
	}

	public File buildingPDFPV100G(PermitHomeSiteInfoEntity permitHomeSite, PermitEntity permitEntity,
			List<PermitSketchEntity> listPermitSketchEntity, PermitDrafterDataEntity permitDrafterDatanfo,
			PermitArrayEntityResultSecond permitArraysEntityResult, ElectricalUtilityEntity electricalCompany,
			PdfReader reader, int sheetIndex, String filePath, Inverters inverterInfo, Cmodulev2 moduleInfo,
			Inverters microInverterInfo, AHJNotesModel ahjNotes,  PlansetUtils plansetUtils) {

		// you only need a PdfStamper if you're going to change the existing PDF.

		File fileRe = null;
		Long idPermit = permitEntity.getId();

		Locale.setDefault(new Locale("en", "US"));
		try {

			fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-PV100G" + idPermit + "-" + sheetIndex + ".pdf");
			if (fileRe.exists()) {
				fileRe.delete();
				fileRe = new File(
						Constants.rapportPlansetFolderUrl + "PDF-PV100G" + idPermit + "-" + sheetIndex + ".pdf");
			}

			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
			// stamper.getPdfLayer
			AcroFields form = stamper.getAcroFields();
			
			//A.B remove sheet index if exist when the project was uploaded
			if(permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
				getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
			}

			PdfReader readerOriginNEC = new PdfReader(
					Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-PV100G.pdf");
			PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-PV100G.pdf");
			// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
			getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "PV100G");
			// A.B: Set PDF Font For Revision
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
					sheetIndex);
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
					sheetIndex);

			// A.B CR-3250 03-30 Logo & Signature Mapping
			logoSignatureMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);

			mapPermitSketchEntity(listPermitSketchEntity, form, sheetIndex);
			/*****************************************
			 * END AZIMUTH AND TILS TABLE
			 *********************/
			// A.B 09-22 CR-3438-MOD-002
			drfaterDetailsMapping.pv100ScaleMapping(form, sheetIndex, "SCALE-PV100G", permitDrafterDatanfo);

			// A.B 07-14-2021 CR-3064
			form.setField(sheetIndex + "-" + "SPECIAL-NOTE-PV-100G", ahjNotes.getPV100GNote());
			electricUtilityNumber.mapEsiId(form, sheetIndex, "PV100G", permitHomeSite, electricalCompany);
			if (checkValue.NotEquals(permitHomeSite.getState(), "CA")) {
				form.setField(sheetIndex + "-" + "PV100G-ACD-NOTE",
						"PV AC DISCONNECT LOCATED ON ACCESSIBLE EXTERIOR WALL WITH EXTERNAL HANDLE VISIBLE, LOCKABLE & LABELED WITHIN 10 FEET OF THE METER");
			}

			// A.B 01-11-2022 CR-PM-REV-2197-MOD-15
			String utility = checkValue.Equals(permitHomeSite.getUtilityCompanyName(), "Other")
					? permitHomeSite.getUtilityCompanyNameOther()
					: electricalCompany.getUtilityCompanyName();
			moduleInverterMfgQty.moduleInverterMfgQty("-PV-100G",form, permitArraysEntityResult.getDeviceToIncorporate(), idPermit,
					sheetIndex, inverterInfo, moduleInfo, microInverterInfo, utility, permitHomeSite.getMeterNumber(),    plansetUtils);
			stamper.close();
			reader.close();

		} catch (IOException | DocumentException e) {
			technicalProblemMsg.traiterException(e);
		}
		return fileRe;
	}

}
