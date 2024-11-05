package com.PlayGroundAdv.Solar.service.generate_planset.drafter_details;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.repositories.PermitDrafterDataRepository;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.pdfcleanup.PdfCleanUpLocation;
import com.itextpdf.text.pdf.pdfcleanup.PdfCleanUpProcessor;

@Service
public class CleanDrafterSheets {

	final PermitDrafterDataRepository permitDrafterDataRepo;
	
	public CleanDrafterSheets(PermitDrafterDataRepository permitDrafterDataRepo) {
		super();
		this.permitDrafterDataRepo = permitDrafterDataRepo;
	}

	public void cleanDraftSheet(String url, Long permitId, File fileCopy) {
		try {
			
			// open the created copy
			PdfReader reader = new PdfReader(url + "Rapport/SampleResult" + permitId + ".pdf");
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(fileCopy));
			// find the file name the drafts for the permit
			String parcel = permitDrafterDataRepo.findByPermitEntityId(permitId).getParcelMapName();

			// locate the drafts file
			File pathCheck = new File(url + "/" + permitId + "/drafterfiles/" + parcel);
			// check if the file exists
			if (pathCheck.exists()) {
				AcroFields form = stamper.getAcroFields();

				// Delete all previous mapped drafts
				for (int i = 0; i < reader.getNumberOfPages() - 10; i++) {
					int pageNum;
					
					if (form.getFieldPositions(i + "-PV001-SHEET-INDEX") != null) {
						pageNum = form.getFieldPositions(i + "-PV001-SHEET-INDEX").get(0).page;
						List<PdfCleanUpLocation> cleanUpLocations = new ArrayList<>();
						cleanUpLocations.add(
								new PdfCleanUpLocation(pageNum, new Rectangle(43f, 300f, 519f, 724f), BaseColor.WHITE));
						try {
							PdfCleanUpProcessor cleaner = new PdfCleanUpProcessor(cleanUpLocations, stamper);
							cleaner.cleanUp();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (form.getFieldPositions(i + "-PV100R-SHEET-INDEX") != null) {
						pageNum = form.getFieldPositions(i + "-PV100R-SHEET-INDEX").get(0).page;
						List<PdfCleanUpLocation> cleanUpLocations = new ArrayList<>();
						cleanUpLocations.add(
								new PdfCleanUpLocation(pageNum, new Rectangle(271f, 717f, 890f, 736f), BaseColor.WHITE));
						cleanUpLocations.add(
								new PdfCleanUpLocation(pageNum, new Rectangle(45f, 51f, 890f, 717f), BaseColor.WHITE));
						cleanUpLocations.add(
								new PdfCleanUpLocation(pageNum, new Rectangle(502f, 51f, 890f, 37f), BaseColor.WHITE));
						try {
							PdfCleanUpProcessor cleaner = new PdfCleanUpProcessor(cleanUpLocations, stamper);
							cleaner.cleanUp();
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
					if (form.getFieldPositions(i + "-P002-SHEET-INDEX") != null) {
						pageNum = form.getFieldPositions(i + "-P002-SHEET-INDEX").get(0).page;
						List<PdfCleanUpLocation> cleanUpLocations = new ArrayList<>();
						cleanUpLocations.add(
								new PdfCleanUpLocation(pageNum, new Rectangle(69, 131, 220, 284), BaseColor.WHITE));
						cleanUpLocations.add(
								new PdfCleanUpLocation(pageNum, new Rectangle(504, 136, 706, 271), BaseColor.WHITE));
						try {
							PdfCleanUpProcessor cleaner = new PdfCleanUpProcessor(cleanUpLocations, stamper);
							cleaner.cleanUp();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (form.getFieldPositions(i + "-S100-SHEET-INDEX") != null) {
						pageNum = form.getFieldPositions(i + "-S100-SHEET-INDEX").get(0).page;
						List<PdfCleanUpLocation> cleanUpLocations = new ArrayList<>();
						cleanUpLocations.add(
								new PdfCleanUpLocation(pageNum, new Rectangle(43f, 38f, 901f, 754f), BaseColor.WHITE));
						try {
							PdfCleanUpProcessor cleaner = new PdfCleanUpProcessor(cleanUpLocations, stamper);
							PdfCleanUpProcessor.floatMultiplier = 10^13;
							cleaner.cleanUp();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (form.getFieldPositions(i + "-PV100G-SHEET-INDEX") != null) {
						pageNum = form.getFieldPositions(i + "-PV100G-SHEET-INDEX").get(0).page;
						List<PdfCleanUpLocation> cleanUpLocations = new ArrayList<>();
						cleanUpLocations.add(
								new PdfCleanUpLocation(pageNum, new Rectangle(45f, 48f, 890f, 719f), BaseColor.WHITE));
						cleanUpLocations.add(new PdfCleanUpLocation(pageNum, new Rectangle(272f, 719f, 897f, 754f),
								BaseColor.WHITE));
						cleanUpLocations.add(
								new PdfCleanUpLocation(pageNum, new Rectangle(500f, 39f, 890f, 48f), BaseColor.WHITE));
						try {
							PdfCleanUpProcessor cleaner = new PdfCleanUpProcessor(cleanUpLocations, stamper);
							cleaner.cleanUp();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (form.getFieldPositions(i + "-PV101G-SHEET-INDEX") != null) {
						pageNum = form.getFieldPositions(i + "-PV101G-SHEET-INDEX").get(0).page;
						List<PdfCleanUpLocation> cleanUpLocations = new ArrayList<>();
						cleanUpLocations.add(
								new PdfCleanUpLocation(pageNum, new Rectangle(39f, 56f, 1113f, 736f), BaseColor.WHITE));
						cleanUpLocations.add(
								new PdfCleanUpLocation(pageNum, new Rectangle(39f, 38f, 897f, 56f), BaseColor.WHITE));
						try {
							PdfCleanUpProcessor cleaner = new PdfCleanUpProcessor(cleanUpLocations, stamper);
							cleaner.cleanUp();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					if (form.getFieldPositions(i + "-E100-SHEET-INDEX") != null) {
						pageNum = form.getFieldPositions(i + "-E100-SHEET-INDEX").get(0).page;
						List<PdfCleanUpLocation> cleanUpLocations = new ArrayList<>();
						cleanUpLocations.add(
								new PdfCleanUpLocation(pageNum, new Rectangle(45f, 50f, 892f, 753f), BaseColor.WHITE));
						cleanUpLocations.add(
								new PdfCleanUpLocation(pageNum, new Rectangle(503f, 37f, 892f, 50f), BaseColor.WHITE));
						try {
							PdfCleanUpProcessor cleaner = new PdfCleanUpProcessor(cleanUpLocations, stamper);
							cleaner.cleanUp();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}
			}

			stamper.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
