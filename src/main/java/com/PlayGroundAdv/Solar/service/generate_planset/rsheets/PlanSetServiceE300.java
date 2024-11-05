package com.PlayGroundAdv.Solar.service.generate_planset.rsheets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.transaction.Transactional;

import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.service.generate_planset.PlansetLogo_SignatureMappingService;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.generate_planset.shared.GetPDFReaderService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
@Transactional
public class PlanSetServiceE300 {

	final CheckValueTypesService checkValueTypesService;
	final GetPDFReaderService getPDFReaderService;
	final PlansetLogo_SignatureMappingService logoSignatureMapping;
	final TechnicalProblemMsg technicalProblemMsg;

	public PlanSetServiceE300(CheckValueTypesService checkValueTypesService, 
			GetPDFReaderService getPDFReaderService, PlansetLogo_SignatureMappingService logoSignatureMapping,
			TechnicalProblemMsg technicalProblemMsg) {
		super();
		this.checkValueTypesService = checkValueTypesService;
		this.getPDFReaderService = getPDFReaderService;
		this.logoSignatureMapping = logoSignatureMapping;
		this.technicalProblemMsg = technicalProblemMsg;
	}

	static final String R_SHEET_PATH = Constants.rapportRSheetFolderUrl + "ReferenceSheets/";

	public File buildingPDFE300(PermitHomeSiteInfoEntity permitHomeSite, Long idPermit, Long projectOwnerId,
			String rSheetPath, String equipmentCutSheet, String equipment, Integer index, String filePath) {

		// you only need a PdfStamper if you're going to change the existing PDF.

		PdfReader reader;
		File fileRSheet = null;
		String pdfPath = "";
		if (permitHomeSite != null && checkValueTypesService.NotEquals(permitHomeSite.getState(), "CA")) {
			pdfPath = "NEC-PDF/";
		}
		try {

			int pages = 0;
			String path = checkValueTypesService.contains(rSheetPath, ".pdf") ? rSheetPath : rSheetPath + ".pdf";
			if (checkValueTypesService.NotEquals(rSheetPath, "") && new File(R_SHEET_PATH + path).exists()) {
				PdfReader readerR = new PdfReader(R_SHEET_PATH + path);
				pages = readerR.getNumberOfPages();
				readerR.close();
			}

			if (pages == 0) {
				try {
					File fileRe = null;
					reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-E300.pdf");
					fileRe = new File(
							Constants.rapportPlansetFolderUrl + "PDF-E300-" + equipment + "-" + idPermit + ".pdf");

					PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
					AcroFields form = stamper.getAcroFields();
					// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
					getPDFReaderService.addFieldsIndex(stamper, reader, index, "E300");

					// A.B CR-3250 03-30 Logo & Signature Mapping
					logoSignatureMapping.mapLogo_Signature(projectOwnerId, stamper, filePath);

					int j = 100 + index;
					form.setField(index + "-" + "E300-SHEET-INDEX", "R-" + j);

					stamper.close();
					reader.close();
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			} else {
				for (int i = 1; pages != 0 && i < (pages + 1); i++) {

					try {

						File fileRe = null;
						reader = new PdfReader(Constants.rapportPlansetFolderUrl + pdfPath + "PDF-E300.pdf");
						fileRe = new File(Constants.rapportPlansetFolderUrl + "PDF-E300-" + equipment + "-" + idPermit
								+ "-" + i + ".pdf");

						PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileRe));
						AcroFields form = stamper.getAcroFields();
						// A.B: Set PDF Fields Index Ex: From FieldsName To Index-FieldsName
						getPDFReaderService.addFieldsIndex(stamper, reader, index + (i - 1), "E300");

						// A.B CR-3250 03-30 Logo & Signature Mapping
						logoSignatureMapping.mapLogo_Signature(projectOwnerId, stamper, filePath);

						try {
							String[] EXTRA = { R_SHEET_PATH + path };
							PdfContentByte canvas = stamper.getUnderContent(1);
							PdfReader r;
							PdfImportedPage page;
							for (String path1 : EXTRA) {
								r = new PdfReader(path1);
								page = stamper.getImportedPage(r, i);
								canvas.addTemplate(page, 0, 2);
								stamper.getWriter().freeReader(r);
								r.close();
							}
						} catch (IOException e) {
							e.printStackTrace();

						}

						int j = 100 + index + (i - 1);
						form.setField((index + (i - 1)) + "-" + "E300-SHEET-INDEX", "R-" + j);
						form.setField((index + (i - 1)) + "-" + "EQUIPMENT-CUT-SHEETS", equipmentCutSheet);

						stamper.close();
						reader.close();

					} catch (IOException e) {
						e.printStackTrace();
						technicalProblemMsg.traiterException(e);
					} catch (DocumentException dE) {
						dE.printStackTrace();
						technicalProblemMsg.traiterException(dE);
					}

				}
				// --------------------------------------------- Merge PDFs
				// -------------------------------------------------------//
				try {
					PDFMergerUtility ut = new PDFMergerUtility();
					for (int i = 1; i < (pages + 1); i++) {
						File fileR = new File(Constants.rapportPlansetFolderUrl + "PDF-E300-" + equipment + "-"
								+ idPermit + "-" + i + ".pdf");
						ut.addSource(fileR);
					}

					ut.setDestinationFileName(
							Constants.rapportPlansetFolderUrl + "PDF-E300-" + equipment + "-" + idPermit + ".pdf");
					ut.mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
				} catch (Exception e) {
					e.printStackTrace();
				}

				// ---------------------------------------------- Delete PDFS
				// ------------------------------------------------------//

				for (int i = 1; i < (pages + 1); i++) {
					File fileR = new File(Constants.rapportPlansetFolderUrl + "PDF-E300-" + equipment + "-" + idPermit
							+ "-" + i + ".pdf");
					if (fileR.exists()) {
						fileR.delete();
					}
				}

			}

			fileRSheet = new File(
					Constants.rapportPlansetFolderUrl + "PDF-E300-" + equipment + "-" + idPermit + ".pdf");

		} catch (IOException e) {
			e.printStackTrace();
			technicalProblemMsg.traiterException(e);
		}
		return fileRSheet;

	}

}
