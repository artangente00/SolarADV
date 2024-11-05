package com.PlayGroundAdv.Solar.service.generate_planset.p_sheets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.planset_utils.PlansetUtils;
import com.PlayGroundAdv.Solar.service.generate_planset.PlansetLogo_SignatureMappingService;
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
public class PlanSetServiceP001 {

	final CheckValueTypesService checkValue;
	final PlansetLogo_SignatureMappingService logoSignMapping;
	final GetPDFReaderService getPDFReaderService;
	final ModuleInverterMfgQty moduleInverterMfgQty;
	final TechnicalProblemMsg technicalProblemMsg;
	final ElectricUtilityNumber electricUtilityNumber;

	public PlanSetServiceP001(CheckValueTypesService checkValue, PlansetLogo_SignatureMappingService logoSignMapping,
			GetPDFReaderService getPDFReaderService, ModuleInverterMfgQty moduleInverterMfgQty,
			TechnicalProblemMsg technicalProblemMsg, ElectricUtilityNumber electricUtilityNumber) {
		super();
		this.checkValue = checkValue;
		this.logoSignMapping = logoSignMapping;
		this.getPDFReaderService = getPDFReaderService;
		this.moduleInverterMfgQty = moduleInverterMfgQty;
		this.technicalProblemMsg = technicalProblemMsg;
		this.electricUtilityNumber = electricUtilityNumber;
	}

	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

	public File buildingPDFP001(PermitHomeSiteInfoEntity permitHomeSite, PermitEntity permitEntity,
			ElectricalUtilityEntity electricalCompany, PermitArrayEntityResultSecond permitArraysEntityResult,
			PdfReader reader, int sheetIndex, String filePath, Inverters inverterInfo, Cmodulev2 moduleInfo,
			Inverters microInverterInfo,  PlansetUtils plansetUtils) {

		// you only need a PdfStamper if you're going to change the existing PDF.

		File fileRe = null;
		try {

			fileRe = new File(
					Constants.rapportPlansetFolderUrl + "PDF-P001" + permitEntity.getId() + "-" + sheetIndex + ".pdf");
			if (fileRe.exists()) {
				fileRe.delete();
				fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-P001" + permitEntity.getId() + "-"
						+ sheetIndex + ".pdf");
			}
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
			AcroFields form = stamper.getAcroFields();

			// A.B remove sheet index if exist when the project was uploaded
			if (permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
				getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
			}

			PdfReader readerOriginNEC = new PdfReader(Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-P001.pdf");
			PdfReader readerOriginCEC = new PdfReader(Constants.rapportPlansetFolderUrl + "PDF-P001.pdf");
			// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
			getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "P001");
			// A.B: Set PDF Font For Revision
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
					sheetIndex);
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginCEC, permitHomeSite, form,
					sheetIndex);

			// A.B CR-3250 03-30 Logo & Signature Mapping
			logoSignMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);

			// A.B 01-11-2022 CR-PM-REV-2197-MOD-15
			String utility = checkValue.Equals(permitHomeSite.getUtilityCompanyName(), "Other") ? permitHomeSite.getUtilityCompanyNameOther() : electricalCompany.getUtilityCompanyName();
			moduleInverterMfgQty.moduleInverterMfgQty("-P-001", form, permitArraysEntityResult.getDeviceToIncorporate(),
					permitEntity.getId(), sheetIndex, inverterInfo, moduleInfo, microInverterInfo, utility, permitHomeSite.getMeterNumber(),    plansetUtils);
			electricUtilityNumber.mapEsiId(form, sheetIndex, "P001", permitHomeSite, electricalCompany);

			stamper.close();
			reader.close();

		} catch (IOException | DocumentException e) {
			technicalProblemMsg.traiterException(e);
		}

		return fileRe;
	}

}
