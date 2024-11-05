package com.PlayGroundAdv.Solar.service.generate_planset;

import java.io.File;
import java.io.FileOutputStream;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.repositories.PermitDrafterDataRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
@Transactional
public class PlansetServiceN001 {

	final CheckValueTypesService checkValue;
	final GetPDFReaderService getPDFReaderService;
	final PlansetLogo_SignatureMappingService logoSignatureMapping;
	final TechnicalProblemMsg technicalProblemMsg;
	final PermitDrafterDataRepository drafterDataRepo;

	public PlansetServiceN001(CheckValueTypesService checkValue, 
			GetPDFReaderService getPDFReaderService, PlansetLogo_SignatureMappingService logoSignatureMapping,
			TechnicalProblemMsg technicalProblemMsg, PermitDrafterDataRepository drafterDataRepo) {
		super();
		this.checkValue = checkValue;
		this.getPDFReaderService = getPDFReaderService;
		this.logoSignatureMapping = logoSignatureMapping;
		this.technicalProblemMsg = technicalProblemMsg;
		this.drafterDataRepo = drafterDataRepo;
	}

	public File buildingPDFN001(PermitHomeSiteInfoEntity permitHomeSite, PermitEntity permitEntity, PdfReader reader,
			int sheetIndex, String filePath) {

		// you only need a PdfStamper if you're going to change the existing PDF.
		Long idPermit = permitEntity.getId();
		try {

			File fileRe = new File(
					Constants.rapportPlansetFolderUrl + "PDF-N001" + idPermit + "-" + sheetIndex + ".pdf");
			if (fileRe.exists()) {
				fileRe.delete();
				fileRe = new File(
						Constants.rapportPlansetFolderUrl + "PDF-N001" + idPermit + "-" + sheetIndex + ".pdf");
			}
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
			AcroFields form = stamper.getAcroFields();
			
			//A.B remove sheet index if exist when the project was uploaded
			if(permitEntity.getPlansetVersion() != null && permitEntity.getPlansetVersion() > 1) {
				getPDFReaderService.removeRevisionFieldsIndex(stamper, form, sheetIndex);
			}
			PdfReader readerOriginNEC = new PdfReader(Constants.rapportPlansetFolderUrl + "NEC-PDF/" + "PDF-N001.pdf");

			// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
			getPDFReaderService.addFieldsIndex(stamper, reader, sheetIndex, "N001");
			// A.B: Set PDF Font For Revision
			getPDFReaderService.applyFontsRevision(permitEntity, stamper, readerOriginNEC, permitHomeSite, form,
					sheetIndex);

			// A.B CR-3250 03-30 Logo & Signature Mapping
			logoSignatureMapping.mapLogo_Signature(permitEntity.getAuthentificationEntity().getId(), stamper, filePath);

			stamper.close();
			reader.close();

			return fileRe;

		} catch (Exception e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
			return null;

		}

	}

}
