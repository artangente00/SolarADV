package com.PlayGroundAdv.Solar.service.export_project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.service.project.GetProjectByIdService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ReviseProjectExport {

	final CheckValueTypesService checkValueTypes;
	final GetProjectByIdService getProjectById;
	final PathRepository pathRepo;
	final PermitRepository projectRepo;
	final ExportHomeownerInfo exportHomeownerInfo;
	final ExportArraysInfo exportArraysInfo;
	final ExportProjectInfo exportProjectInfo;
	final ExportAdditonalInfo exportAdditonalInfo;
	final ExportCircuitInfo exportCircuitInfo;
	final ExportLayoutSketch exportLayoutSketch;
	final ExportUtilityCompanyInfo exportUtilityCompanyInfo;
	final ExportADVInputs exportADVInputs;
	final ExportDrafterData exportDrafterData;

	public ReviseProjectExport(CheckValueTypesService checkValueTypes, GetProjectByIdService getProjectById,
			PathRepository pathRepo, PermitRepository projectRepo, ExportHomeownerInfo exportHomeownerInfo,
			ExportArraysInfo exportArraysInfo, ExportProjectInfo exportProjectInfo,
			ExportAdditonalInfo exportAdditonalInfo, ExportCircuitInfo exportCircuitInfo,
			ExportLayoutSketch exportLayoutSketch, ExportUtilityCompanyInfo exportUtilityCompanyInfo,
			ExportADVInputs exportADVInputs, ExportDrafterData exportDrafterData) {
		super();
		this.checkValueTypes = checkValueTypes;
		this.getProjectById = getProjectById;
		this.pathRepo = pathRepo;
		this.projectRepo = projectRepo;
		this.exportHomeownerInfo = exportHomeownerInfo;
		this.exportArraysInfo = exportArraysInfo;
		this.exportProjectInfo = exportProjectInfo;
		this.exportAdditonalInfo = exportAdditonalInfo;
		this.exportCircuitInfo = exportCircuitInfo;
		this.exportLayoutSketch = exportLayoutSketch;
		this.exportUtilityCompanyInfo = exportUtilityCompanyInfo;
		this.exportADVInputs = exportADVInputs;
		this.exportDrafterData = exportDrafterData;
	}
	
	public String updateProjectCsv(Long idProject) {
		try {
			/*
			 * Query queryPath =
			 * em.createQuery("SELECT u  from PathEntity u  where u.id = :p1 ")
			 * .setParameter("p1", 1); PathEntity path = (PathEntity)
			 * queryPath.getSingleResult();
			 */
			GetPermitByIdResult permit = getProjectById.getProjectById(idProject, null, "", "", "", "", "", true, false);

			Integer version = permit.getPermitEntity().getRRVersion();
			String fileDirPath = pathRepo.findFilePath();
			String excelFilePath = fileDirPath + permit.getPermitEntity().getId() + "/"
					+ permit.getPermitEntity().getId() + ".xls";
			FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

			HSSFSheet homeOwnerSheet = workbook.getSheet("HOMEOWNER SITE INFO");
			HSSFSheet arraysSheet = workbook.getSheet("ARRAY(S)");
			HSSFSheet bosSheet = workbook.getSheet("BALANCE OF SYSTEMS (BOS)");
			HSSFSheet conduitContractorSheet = workbook.getSheet("CONDUIT & CONDUCTOR SEGMENTS");
			HSSFSheet addInfoSheet = workbook.getSheet("ADDITIONAL INFO");
			HSSFSheet layoutsheet = workbook.getSheet("LAYOUT SKETCH");
			HSSFSheet utilitySheet = workbook.getSheet("UTILITY COMPANY INFO");
			HSSFSheet advSheet = workbook.getSheet("ADV PERMITS INPUTS");
			HSSFSheet drafterSheet = workbook.getSheet("DRAFTER DATA");

			HSSFCellStyle cellStyleOrange = workbook.createCellStyle();
			HSSFCellStyle cellStyleBlue = workbook.createCellStyle();
			HSSFCellStyle cellStyleRose = workbook.createCellStyle();
			HSSFCellStyle cellStyleRed = workbook.createCellStyle();
			HSSFCellStyle cellStyleGreen = workbook.createCellStyle();
			HSSFCellStyle cellStyleYellow = workbook.createCellStyle();
			HSSFCellStyle cellStyleSkyBlue = workbook.createCellStyle();
			HSSFCellStyle cellStyleOlive = workbook.createCellStyle();
			HSSFCellStyle cellStyleViolet = workbook.createCellStyle();

			cellStyleOrange.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
			cellStyleOrange.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			cellStyleBlue.setFillForegroundColor(HSSFColor.GOLD.index);
			cellStyleBlue.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			cellStyleRose.setFillForegroundColor(HSSFColor.LIME.index);
			cellStyleRose.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			cellStyleRed.setFillForegroundColor(HSSFColor.AQUA.index);
			cellStyleRed.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			cellStyleGreen.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
			cellStyleGreen.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			cellStyleYellow.setFillForegroundColor(HSSFColor.TAN.index);
			cellStyleYellow.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			cellStyleSkyBlue.setFillForegroundColor(HSSFColor.LAVENDER.index);
			cellStyleSkyBlue.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			cellStyleOlive.setFillForegroundColor(HSSFColor.ROSE.index);
			cellStyleOlive.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			cellStyleViolet.setFillForegroundColor(HSSFColor.PLUM.index);
			cellStyleViolet.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			// Create High Lighted Style
			CellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			// Home Owner
			LinkedHashMap<String, String> homeOwner = exportHomeownerInfo.homeOwnerMap(permit);
			Iterator<String> keySetIteratorHomeOwner = homeOwner.keySet().iterator();
			Row homeOwnerRow = homeOwnerSheet.getRow(1);
			homeOwnerRow.createCell(version + 1).setCellStyle(cellStyleOrange);
			homeOwnerRow.getCell(version + 1).setCellValue("REV" + version);
			int iHome = 2;
			while (keySetIteratorHomeOwner.hasNext()) {
				homeOwnerRow = homeOwnerSheet.getRow(iHome);
				String key = keySetIteratorHomeOwner.next();

				homeOwnerRow.createCell(version + 1).setCellValue(homeOwner.get(key));
				Cell cell =  homeOwnerRow.getCell(version) != null ? homeOwnerRow.getCell(version)
						: homeOwnerRow.createCell(version);
			
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						if (checkValueTypes.NotEquals(cell.getStringCellValue(),
								homeOwnerRow.getCell(version + 1).getStringCellValue())) {
							for (int j = 0; j < version + 2; j++) {
								homeOwnerRow.getCell(j).setCellStyle(style);
							}
						} else if (cell.getStringCellValue() == null
								&& homeOwnerRow.getCell(version + 1).getStringCellValue() != null) {
							for (int j = 0; j < version + 2; j++) {
								homeOwnerRow.getCell(j).setCellStyle(style);
							}
						}
						break;
					case Cell.CELL_TYPE_BOOLEAN:
						if (cell.getBooleanCellValue() != homeOwnerRow.getCell(version + 1).getBooleanCellValue()) {
							for (int j = 0; j < version + 2; j++) {
								homeOwnerRow.getCell(j).setCellStyle(style);
							}
						}
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if (cell.getNumericCellValue() != homeOwnerRow.getCell(version + 1).getNumericCellValue()) {
							for (int j = 0; j < version + 2; j++) {
								homeOwnerRow.getCell(j).setCellStyle(style);
							}
						}
						break;
					}
					iHome++;
				
				
				
			}

			// ARRAY(S)
			LinkedHashMap<String, String> arrays = exportArraysInfo.arraysMap(permit);
			Iterator<String> keySetIteratorArrays = arrays.keySet().iterator();

			Row arraysRow = arraysSheet.getRow(1);

			arraysRow.createCell(version + 1).setCellStyle(cellStyleBlue);
			arraysRow.getCell(version + 1).setCellValue("REV" + version);
			LinkedHashMap<String, List<String>> oldValues = new LinkedHashMap<>();
			int iArrays = 2;
			for (int rowIndex = 2; rowIndex <= arraysSheet.getLastRowNum(); rowIndex++) {
				Row row = arraysSheet.getRow(rowIndex);
				if (row != null) {
					String cellTitle = row.getCell(0).getStringCellValue();
					List<String> cellValues = new ArrayList<>();

					for (int i = 1; i < version + 1; i++) {
						Cell cell = row.getCell(i);
						if (cell != null)
							cellValues.add(cell.getStringCellValue());
					}
					oldValues.put(cellTitle, cellValues);
				}
			}
			Set<String> oldkeys = oldValues.keySet();
			while (keySetIteratorArrays.hasNext()) {
				arraysRow = arraysSheet.getRow(iArrays) != null ? arraysSheet.getRow(iArrays)
						: arraysSheet.createRow(iArrays);
				String key = keySetIteratorArrays.next();
				arraysRow.createCell(0).setCellValue(key);
				
				if (oldkeys.contains(key)) {
					for (int i = 1; i < version + 1; i++) {
						arraysRow.createCell(i).setCellValue(oldValues.get(key).get(0));
					}
				} else {
					for (int i = 1; i < version + 1; i++) {
						arraysRow.createCell(i).setCellValue("");
					}
				}

				arraysRow.createCell(version + 1).setCellValue(arrays.get(key));
				Cell cell = arraysRow.getCell(version) != null ? arraysRow.getCell(version)
						: arraysRow.createCell(version);
				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					
					if (checkValueTypes.NotEquals(cell.getStringCellValue(),
							arraysRow.getCell(version + 1).getStringCellValue())) {
						for (int j = 0; j < version + 2; j++) {
							arraysRow.getCell(j).setCellStyle(style);
						}
					} else if (cell.getStringCellValue() == null
							&& arraysRow.getCell(version + 1).getStringCellValue() != null) {
						for (int j = 0; j < version + 2; j++) {
							arraysRow.getCell(j).setCellStyle(style);
						}
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					if (cell.getBooleanCellValue() != arraysRow.getCell(version + 1).getBooleanCellValue()) {
						for (int j = 0; j < version + 2; j++) {
							arraysRow.getCell(j).setCellStyle(style);
						}
					}
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (cell.getNumericCellValue() != arraysRow.getCell(version + 1).getNumericCellValue()) {
						for (int j = 0; j < version + 2; j++) {
							arraysRow.getCell(j).setCellStyle(style);
						}
					}
					break;
				}
				iArrays++;
			}
			for (int rowIndex = iArrays; rowIndex <= arraysSheet.getLastRowNum(); rowIndex++) {
				Row row = arraysSheet.getRow(rowIndex);
				arraysSheet.removeRow(row);
			}
			// BALANCE OF SYSTEMS (BOS)
			LinkedHashMap<String, String> bos = exportProjectInfo.balanceOfSystem(permit);
			Iterator<String> keySetIteratorBOS = bos.keySet().iterator();

			Row bosRow = bosSheet.getRow(1);
			bosRow.createCell(version + 1).setCellStyle(cellStyleRose);
			bosRow.getCell(version + 1).setCellValue("REV" + version);

			int iBos = 2;
			while (keySetIteratorBOS.hasNext()) {
				bosRow = bosSheet.getRow(iBos) != null ? bosSheet.getRow(iBos) : bosSheet.createRow(iBos);
				String key = keySetIteratorBOS.next();

				bosRow.createCell(version + 1).setCellValue(bos.get(key));
				Cell cell = bosRow.getCell(version) != null ? bosRow.getCell(version) : bosRow.createCell(version);

				if (cell != null && checkValueTypes.NotEquals(cell.getStringCellValue(),
						bosRow.getCell(version + 1).getStringCellValue())) {
					for (int j = 0; j < version + 2; j++) {
						bosRow.getCell(j).setCellStyle(style);
					}
				} else if (cell != null && cell.getStringCellValue() == null
						&& bosRow.getCell(version + 1).getStringCellValue() != null) {
					for (int j = 0; j < version + 2; j++) {
						bosRow.getCell(j).setCellStyle(style);
					}
				}

				iBos++;
			}

			// CONDUIT & CONDUCTOR SEGMENTS
			LinkedHashMap<String, String> conduitContractor = exportCircuitInfo.conduitContractorSection(permit);
			Iterator<String> keySetIteratorConduitContr = conduitContractor.keySet().iterator();

			Row conduitContractorRow = conduitContractorSheet.getRow(1);
			conduitContractorRow.createCell(version + 1).setCellStyle(cellStyleRed);
			conduitContractorRow.getCell(version + 1).setCellValue("REV" + version);

			int iCcs = 2;
			while (keySetIteratorConduitContr.hasNext()) {
				conduitContractorRow = conduitContractorSheet.getRow(iCcs);
				String key = keySetIteratorConduitContr.next();

				conduitContractorRow.createCell(version + 1).setCellValue(conduitContractor.get(key));
				Cell cell = conduitContractorRow.getCell(version) != null ? conduitContractorRow.getCell(version) : conduitContractorRow.createCell(version) ;

				switch (cell.getCellType()) {

				case Cell.CELL_TYPE_STRING:
					if (checkValueTypes.NotEquals(cell.getStringCellValue(),
							conduitContractorRow.getCell(version + 1).getStringCellValue())) {
						for (int j = 0; j < version + 2; j++) {
							conduitContractorRow.getCell(j).setCellStyle(style);
						}
					} else if (cell.getStringCellValue() == null
							&& conduitContractorRow.getCell(version + 1).getStringCellValue() != null) {
						for (int j = 0; j < version + 2; j++) {
							conduitContractorRow.getCell(j).setCellStyle(style);
						}
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					if (cell.getBooleanCellValue() != conduitContractorRow.getCell(version + 1).getBooleanCellValue()) {
						for (int j = 0; j < version + 2; j++) {
							conduitContractorRow.getCell(j).setCellStyle(style);
						}
					}
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (cell.getNumericCellValue() != conduitContractorRow.getCell(version + 1).getNumericCellValue()) {
						for (int j = 0; j < version + 2; j++) {
							conduitContractorRow.getCell(j).setCellStyle(style);
						}
					}
					break;
				}
				iCcs++;
			}

			// ADDITIONAL INFO
			LinkedHashMap<String, String> addtionalInfo = exportAdditonalInfo.additionalInfo(permit);
			Iterator<String> keySetIteratoradditionalInfo = addtionalInfo.keySet().iterator();

			Row additionalRow = addInfoSheet.getRow(1);
			additionalRow.createCell(version + 1).setCellStyle(cellStyleGreen);
			additionalRow.getCell(version + 1).setCellValue("REV" + version);

			int iAnf = 2;
			while (keySetIteratoradditionalInfo.hasNext()) {

				additionalRow = addInfoSheet.getRow(iAnf);
				String key = keySetIteratoradditionalInfo.next();

				additionalRow.createCell(version + 1).setCellValue(addtionalInfo.get(key));
				Cell cell = additionalRow.getCell(version) != null ? additionalRow.getCell(version) : additionalRow.createCell(version);

				switch (cell.getCellType()) {

				case Cell.CELL_TYPE_STRING:
					if (checkValueTypes.NotEquals(cell.getStringCellValue(),
							additionalRow.getCell(version + 1).getStringCellValue())) {
						for (int j = 0; j < version + 2; j++) {
							additionalRow.getCell(j).setCellStyle(style);
						}
					} else if (cell.getStringCellValue() == null
							&& additionalRow.getCell(version + 1).getStringCellValue() != null) {
						for (int j = 0; j < version + 2; j++) {
							additionalRow.getCell(j).setCellStyle(style);
						}
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					if (cell.getBooleanCellValue() != additionalRow.getCell(version + 1).getBooleanCellValue()) {
						for (int j = 0; j < version + 2; j++) {
							additionalRow.getCell(j).setCellStyle(style);
						}
					}
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (cell.getNumericCellValue() != additionalRow.getCell(version + 1).getNumericCellValue()) {
						for (int j = 0; j < version + 2; j++) {
							additionalRow.getCell(j).setCellStyle(style);
						}
					}
					break;
				}
				iAnf++;
			}

			// LAYOUT SKETCH
			LinkedHashMap<String, String> sktechLayout = exportLayoutSketch.layoutSketch(permit);
			Iterator<String> keySetIteratorSketchLayout = sktechLayout.keySet().iterator();

			Row sketchLayoutRow = layoutsheet.getRow(1);
			sketchLayoutRow.createCell(version + 1).setCellStyle(cellStyleYellow);
			sketchLayoutRow.getCell(version + 1).setCellValue("REV" + version);

			int nemberRow = layoutsheet.getPhysicalNumberOfRows();
			int nemberRowBis = layoutsheet.getPhysicalNumberOfRows();
			int iLskecth = 2;
			while (keySetIteratorSketchLayout.hasNext()) {
				boolean found = false;
				String key = keySetIteratorSketchLayout.next();

				for (int i = 2; i < nemberRow; i++) {

					sketchLayoutRow = layoutsheet.getRow(i);

					if (checkValueTypes.Equals(key, sketchLayoutRow.getCell(0).getStringCellValue())) {
						found = true;

						sketchLayoutRow.createCell(version + 1).setCellValue(sktechLayout.get(key));
						Cell cell = sketchLayoutRow.getCell(version) != null ? sketchLayoutRow.getCell(version) : sketchLayoutRow.createCell(version);

						switch (cell.getCellType()) {

						case Cell.CELL_TYPE_STRING:
							if (checkValueTypes.NotEquals(cell.getStringCellValue(),
									sketchLayoutRow.getCell(version + 1).getStringCellValue())) {
								for (int j = 0; j < version + 2; j++) {
									sketchLayoutRow.getCell(j).setCellStyle(style);
								}
							} else if (cell.getStringCellValue() == null
									&& sketchLayoutRow.getCell(version + 1).getStringCellValue() != null) {
								for (int j = 0; j < version + 2; j++) {
									sketchLayoutRow.getCell(j).setCellStyle(style);
								}
							}
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							if (cell.getBooleanCellValue() != sketchLayoutRow.getCell(version + 1)
									.getBooleanCellValue()) {
								for (int j = 0; j < version + 2; j++) {
									sketchLayoutRow.getCell(j).setCellStyle(style);
								}
							}
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (cell.getNumericCellValue() != sketchLayoutRow.getCell(version + 1)
									.getNumericCellValue()) {
								for (int j = 0; j < version + 2; j++) {
									sketchLayoutRow.getCell(j).setCellStyle(style);
								}
							}
							break;
						}

					}
				}
				if (!found) {

					sketchLayoutRow = layoutsheet.createRow(nemberRowBis);
					sketchLayoutRow.createCell(version + 1).setCellValue(sktechLayout.get(key));
					sketchLayoutRow.createCell(0).setCellValue(key);
					for (int i = 0; i < version + 2; i++) {
						if (i != 0 && i != version + 1) {
							sketchLayoutRow.createCell(i).setCellStyle(style);
						} else {
							sketchLayoutRow.getCell(i).setCellStyle(style);
						}
					}
					nemberRowBis++;
				}
				iLskecth++;
			}

			// UTILITY COMPANY INFO
			LinkedHashMap<String, String> utilityCompany = exportUtilityCompanyInfo.utilityCompany(permit);
			Iterator<String> keySetIteratorUtility = utilityCompany.keySet().iterator();

			Row utilityRow = utilitySheet.getRow(1);
			utilityRow.createCell(version + 1).setCellStyle(cellStyleSkyBlue);
			utilityRow.getCell(version + 1).setCellValue("REV" + version);

			int iUcompany = 2;
			while (keySetIteratorUtility.hasNext()) {

				utilityRow = utilitySheet.getRow(iUcompany) != null ? utilitySheet.getRow(iUcompany) : utilitySheet.createRow(iUcompany);
				String key = keySetIteratorUtility.next();

				utilityRow.createCell(version + 1).setCellValue(utilityCompany.get(key));
				Cell cell = utilityRow.getCell(version) != null ? utilityRow.getCell(version) : utilityRow.createCell(version);

				switch (cell.getCellType()) {

				case Cell.CELL_TYPE_STRING:
					if (checkValueTypes.NotEquals(cell.getStringCellValue(),
							utilityRow.getCell(version + 1).getStringCellValue())) {
						for (int j = 0; j < version + 2; j++) {
							utilityRow.getCell(j).setCellStyle(style);
						}
					} else if (cell.getStringCellValue() == null
							&& utilityRow.getCell(version + 1).getStringCellValue() != null) {
						for (int j = 0; j < version + 2; j++) {
							utilityRow.getCell(j).setCellStyle(style);
						}
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					if (cell.getBooleanCellValue() != utilityRow.getCell(version + 1).getBooleanCellValue()) {
						for (int j = 0; j < version + 2; j++) {
							utilityRow.getCell(j).setCellStyle(style);
						}
					}
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (cell.getNumericCellValue() != utilityRow.getCell(version + 1).getNumericCellValue()) {
						for (int j = 0; j < version + 2; j++) {
							utilityRow.getCell(j).setCellStyle(style);
						}
					}
					break;
				}
				iUcompany++;
			}

			// ADV PERMITS INPUTS
			LinkedHashMap<String, String> adv = exportADVInputs.advPermitsInputs(permit);
			Iterator<String> keySetIteratorAdv = adv.keySet().iterator();

			Row advRow = advSheet.getRow(1);
			advRow.createCell(version + 1).setCellStyle(cellStyleOlive);
			advRow.getCell(version + 1).setCellValue("REV" + version);

			int iAdv = 2;
			while (keySetIteratorAdv.hasNext()) {
				advRow = advSheet.getRow(iAdv);
				String key = keySetIteratorAdv.next();

				advRow.createCell(version + 1).setCellValue(adv.get(key));
				Cell cell = advRow.getCell(version) != null ? advRow.getCell(version) : advRow.createCell(version);

				switch (cell.getCellType()) {

				case Cell.CELL_TYPE_STRING:
					if (checkValueTypes.NotEquals(cell.getStringCellValue(),
							advRow.getCell(version + 1).getStringCellValue())) {
						for (int j = 0; j < version + 2; j++) {
							advRow.getCell(j).setCellStyle(style);
						}
					} else if (cell.getStringCellValue() == null
							&& advRow.getCell(version + 1).getStringCellValue() != null) {
						for (int j = 0; j < version + 2; j++) {
							advRow.getCell(j).setCellStyle(style);
						}
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					if (cell.getBooleanCellValue() != advRow.getCell(version + 1).getBooleanCellValue()) {
						for (int j = 0; j < version + 2; j++) {
							advRow.getCell(j).setCellStyle(style);
						}
					}
					break;
				case Cell.CELL_TYPE_NUMERIC:
					if (cell.getNumericCellValue() != advRow.getCell(version + 1).getNumericCellValue()) {
						for (int j = 0; j < version + 2; j++) {
							advRow.getCell(j).setCellStyle(style);
						}
					}
					break;

				}
				iAdv++;
			}

			// DRAFTER DATA
			LinkedHashMap<String, String> drafter = exportDrafterData.drafterData(permit);
			Iterator<String> keySetIteratordrafter = drafter.keySet().iterator();

			Row drafterRow = drafterSheet.getRow(1);
			drafterRow.createCell(version + 1).setCellStyle(cellStyleViolet);
			drafterRow.getCell(version + 1).setCellValue("REV" + version);

			int nemberRowDraft = drafterSheet.getPhysicalNumberOfRows();
			int nemberRowBisDraft = drafterSheet.getPhysicalNumberOfRows();

			int iDraft = 2;
			while (keySetIteratordrafter.hasNext()) {
				boolean found = false;
				String key = keySetIteratordrafter.next();

				for (int i = 2; i < nemberRowDraft; i++) {

					drafterRow = drafterSheet.getRow(i);

					if (checkValueTypes.Equals(key, drafterRow.getCell(0).getStringCellValue())) {
						found = true;
						drafterRow.createCell(version + 1).setCellValue(drafter.get(key));
						Cell cell = drafterRow.getCell(version) != null ? drafterRow.getCell(version) : drafterRow.createCell(version);
						switch (cell.getCellType()) {

						case Cell.CELL_TYPE_STRING:
							if (checkValueTypes.NotEquals(cell.getStringCellValue(),
									drafterRow.getCell(version + 1).getStringCellValue())) {
								for (int j = 0; j < version + 2; j++) {
									drafterRow.getCell(j).setCellStyle(style);
								}
							} else if (cell.getStringCellValue() == null
									&& drafterRow.getCell(version + 1).getStringCellValue() != null) {
								for (int j = 0; j < version + 2; j++) {
									drafterRow.getCell(j).setCellStyle(style);
								}
							}
							break;
						case Cell.CELL_TYPE_BOOLEAN:
							if (cell.getBooleanCellValue() != drafterRow.getCell(version + 1).getBooleanCellValue()) {
								for (int j = 0; j < version + 2; j++) {
									drafterRow.getCell(j).setCellStyle(style);
								}
							}
							break;
						case Cell.CELL_TYPE_NUMERIC:
							if (cell.getNumericCellValue() != drafterRow.getCell(version + 1).getNumericCellValue()) {
								for (int j = 0; j < version + 2; j++) {
									drafterRow.getCell(j).setCellStyle(style);
								}
							}
							break;
						}
					}
				}
				if (!found) {
					drafterRow = drafterSheet.createRow(nemberRowBisDraft);
					drafterRow.createCell(version + 1).setCellValue(drafter.get(key));
					drafterRow.createCell(0).setCellValue(key);
					for (int i = 0; i < version + 2; i++) {
						if (i != 0 && i != version + 1) {
							drafterRow.createCell(i).setCellStyle(style);
						} else {
							drafterRow.getCell(i).setCellStyle(style);
						}
					}
					nemberRowBisDraft++;
				}

				iDraft++;
			}

			inputStream.close();
			// Open FileOutputStream to write updates
			FileOutputStream output_file = new FileOutputStream(new File(excelFilePath));
			// write changes
			workbook.write(output_file);
			// close the stream
			output_file.close();

			return permit.getPermitEntity().getId() + "/" + permit.getPermitEntity().getId() + ".xls";

		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
