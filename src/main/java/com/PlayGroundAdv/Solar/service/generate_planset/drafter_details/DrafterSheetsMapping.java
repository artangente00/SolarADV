package com.PlayGroundAdv.Solar.service.generate_planset.drafter_details;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.SelectDrafterSheet;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitDrafterDataRepository;
import com.PlayGroundAdv.Solar.repositories.SelectDrafterSheetRepository;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
@Service
public class DrafterSheetsMapping {
	
	final PathRepository pathRepo;
	final PermitDrafterDataRepository permitDrafterDataRepo;
	final SelectDrafterSheetRepository selectDrafterSheetRepo;
	public DrafterSheetsMapping(PathRepository pathRepo, PermitDrafterDataRepository permitDrafterDataRepo,
			SelectDrafterSheetRepository selectDrafterSheetRepo) {
		super();
		this.pathRepo = pathRepo;
		this.permitDrafterDataRepo = permitDrafterDataRepo;
		this.selectDrafterSheetRepo = selectDrafterSheetRepo;
	}
	final static String SHEET_INDEX = "-SHEET-INDEX";
	
	public void drafterMapping(String filePath, Long permitId, File draftFile,String originPath) {
		try {
			// open the created copy
			PdfReader reader = new PdfReader(originPath);
			PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(draftFile));
			// find the file name the drafts for the permit
			String parcel = permitDrafterDataRepo.findParcelMapName(permitId);
			String parcelPath = filePath + "/" + permitId + "/drafterfiles/" + parcel;
			// locate the drafts file
			File pathCheck = new File(parcelPath);
			String[] extra = { parcelPath };
			// check if the file exists
			if (pathCheck.exists()) {
				AcroFields form = stamper.getAcroFields();
				//
				List<SelectDrafterSheet> plansetDfSheets = selectDrafterSheetRepo.findByIdPermitId(permitId);
				Map<String, List<SelectDrafterSheet>> drafterSheets = new HashMap<>();
				Map<String, List<SelectDrafterSheet>> additionalDrafterSheets = new HashMap<>();
				
				List<String> sheetNames = Arrays.asList("PV001", "PV100R", "PV100G", "PV101G", "S100", "E100-MICRO", "E100-STRING",
						"P002-MICRO", "P002-STRING");
				List<String> additionalSheetNames = Arrays.asList("N001", "PV101R", "S200-RAFTER", "S200-TRUSS", "S201", "S300", 
						"E001-MICRO", "E001-STRING", "E002-MICRO", "E002-STRING", "E003-MICRO", "E003-STRING","P001","E300");
				
				for (String sheet : sheetNames) {
					List<SelectDrafterSheet> draft= plansetDfSheets.stream().filter(s -> s.getPageSheet().equals(sheet)).collect(Collectors.toList());
					if(!draft.isEmpty()) {
						String index = sheet.replace("-MICRO", "").replace("-STRING", "").replace("-RAFTER", "").replace("-TRUSS", "");
						drafterSheets.put("-"+index+SHEET_INDEX, draft);
					}
				}
				for (String sheet : additionalSheetNames) {
					List<SelectDrafterSheet> draft= plansetDfSheets.stream().filter(s -> s.getPageSheet().equals(sheet)).collect(Collectors.toList());
					if(!draft.isEmpty()) {
						String index = sheet.replace("-MICRO", "").replace("-STRING", "").replace("-RAFTER", "").replace("-TRUSS", "");
						additionalDrafterSheets.put("-"+index+SHEET_INDEX, draft);
					}
				}
				
				for (int i = 0; i < reader.getNumberOfPages(); i++) {
					
					if (form.getFieldPositions(i + "-PV001" + SHEET_INDEX) != null) {
						int pageNum = form.getFieldPositions(i + "-PV001" + SHEET_INDEX).get(0).page;
						drafterSheetMapping(drafterSheets.get("-PV001" + SHEET_INDEX), pageNum, stamper, extra, 0, 5, 1);
					}
					if (form.getFieldPositions(i + "-PV100R" + SHEET_INDEX) != null) {
						int pageNum = form.getFieldPositions(i + "-PV100R" + SHEET_INDEX).get(0).page;
						drafterSheetMapping(drafterSheets.get("-PV100R" + SHEET_INDEX), pageNum, stamper, extra, 10, 5, 2);

					}
					if (form.getFieldPositions(i + "-S100" + SHEET_INDEX) != null) {
						int pageNum = form.getFieldPositions(i + "-S100" + SHEET_INDEX).get(0).page;
						drafterSheetMapping(drafterSheets.get("-S100" + SHEET_INDEX), pageNum, stamper, extra, 20, 2, 3);
					}
					if (form.getFieldPositions(i + "-E100" + SHEET_INDEX) != null) {
						int pageNum = form.getFieldPositions(i + "-E100" + SHEET_INDEX).get(0).page;
						drafterSheetMapping(drafterSheets.get("-E100" + SHEET_INDEX), pageNum, stamper, extra, 0, 5, 4);
					}
					if (form.getFieldPositions(i + "-P002" + SHEET_INDEX) != null) {
						int pageNum = form.getFieldPositions(i + "-P002" + SHEET_INDEX).get(0).page;
						drafterSheetMapping(drafterSheets.get("-P002" + SHEET_INDEX), pageNum, stamper, extra, 15, 10, 5);
					}
					//A.B Ground Mounted Sheets
					if (form.getFieldPositions(i + "-PV100G" + SHEET_INDEX) != null) {
						int pageNum = form.getFieldPositions(i + "-PV100G" + SHEET_INDEX).get(0).page;
						drafterSheetMapping(drafterSheets.get("-PV100G" + SHEET_INDEX), pageNum, stamper, extra, 10, 5, 2);
					}
					//A.B 11/24/2021 CR-PM-426
					if (form.getFieldPositions(i + "-PV101G" + SHEET_INDEX) != null) {
						int pageNum = form.getFieldPositions(i + "-PV101G" + SHEET_INDEX).get(0).page;
						drafterSheetMapping(drafterSheets.get("-PV101G" + SHEET_INDEX), pageNum, stamper, extra, 20, 2, 3);
					}
					for (Entry<String, List<SelectDrafterSheet>> entry : additionalDrafterSheets.entrySet()) {
						if ((entry.getKey().contains("E300") && i == (reader.getNumberOfPages() - 1)) || (!entry.getKey().contains("E300") && form.getFieldPositions(i + entry.getKey()) != null)) {
							int pageNum = entry.getKey().contains("E300") ? reader.getNumberOfPages() : form.getFieldPositions(i + entry.getKey()).get(0).page;
							drafterCustomSheetMapping(entry.getValue(), pageNum, stamper, extra);
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
	public void drafterSheetMapping(List<SelectDrafterSheet> drafterSheet, int pageNum, PdfStamper stamper, String[] extra,float x, float y, int importedPage) {
		try {
			PdfReader r;
			PdfImportedPage page;
			PdfContentByte canvas = stamper.getOverContent(pageNum);

			for (String path1 : extra) {
				r = new PdfReader(path1);
				if (r.getNumberOfPages() > importedPage - 1) {
					page = stamper.getImportedPage(r, importedPage);
					canvas.addTemplate(page, x, y);
				}

				if (drafterSheet != null && !drafterSheet.isEmpty()) {
					for (SelectDrafterSheet sheet : drafterSheet) {
						page = stamper.getImportedPage(r, sheet.getPageNumber());
						canvas.addTemplate(page, x, y);
					}
				}
				stamper.getWriter().freeReader(r);
				r.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void drafterCustomSheetMapping(List<SelectDrafterSheet> drafterSheet, int pageNum, PdfStamper stamper, String[] extra) {
		try {
			PdfReader r;
			PdfImportedPage page;
			PdfContentByte canvas = stamper.getUnderContent(pageNum);

			for (String path1 : extra) {
				r = new PdfReader(path1);
				if (drafterSheet != null && !drafterSheet.isEmpty()) {
					for (SelectDrafterSheet sheet : drafterSheet) {
						page = stamper.getImportedPage(r, sheet.getPageNumber());
						canvas.addTemplate(page, 10, 20);
					}
				}
				stamper.getWriter().freeReader(r);
				r.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
