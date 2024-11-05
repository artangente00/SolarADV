package com.PlayGroundAdv.Solar.service.export_project;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.project.GetProjectByIdService;
import com.PlayGroundAdv.Solar.service.user_management.GoogleDriveFolder;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.google.common.io.Files;

@Service
@Transactional
public class ExportProjectSvcService {

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
	final ReviseProjectExport reviseProjectExport;
	final ExportEnergyBatterySystem energyBatterySystem;
	final AuthenticationRepository userRepo;
	final GoogleDriveFolder googleDriveFolder;
	
	public ExportProjectSvcService(CheckValueTypesService checkValueTypes, GetProjectByIdService getProjectById,
			PathRepository pathRepo, PermitRepository projectRepo, ExportHomeownerInfo exportHomeownerInfo,
			ExportArraysInfo exportArraysInfo, ExportProjectInfo exportProjectInfo,
			ExportAdditonalInfo exportAdditonalInfo, ExportCircuitInfo exportCircuitInfo,
			ExportLayoutSketch exportLayoutSketch, ExportUtilityCompanyInfo exportUtilityCompanyInfo,
			ExportADVInputs exportADVInputs, ExportDrafterData exportDrafterData,
			ReviseProjectExport reviseProjectExport, ExportEnergyBatterySystem energyBatterySystem,
			AuthenticationRepository userRepo, GoogleDriveFolder googleDriveFolder) {
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
		this.reviseProjectExport = reviseProjectExport;
		this.energyBatterySystem = energyBatterySystem;
		this.userRepo = userRepo;
		this.googleDriveFolder = googleDriveFolder;
	}


	public String generateProjectScv(Long idProject, Long idUser) {

		try {

			Integer rrVersion = projectRepo.findRRVersion(idProject);
			String fileDirPath = pathRepo.findFilePath();
			String excelFilePath = fileDirPath + idProject + "/"
					+ idProject + ".xls";
			if (rrVersion != null && rrVersion > 0 && new File(excelFilePath).exists()) {
				try {
					return reviseProjectExport.updateProjectCsv(idProject);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			AuthentificationEntity user = userRepo.findById(idUser).orElse(new AuthentificationEntity());
			GetPermitByIdResult permit = getProjectById.getProjectById(idProject, null, "", "", "", "", "", true, false);
			FileOutputStream fileOut;
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet homeOwnerSheet = workbook.createSheet("HOMEOWNER SITE INFO");
			HSSFSheet arraysSheet = workbook.createSheet("ARRAY(S)");
			HSSFSheet bosSheet = workbook.createSheet("BALANCE OF SYSTEMS (BOS)");
			HSSFSheet conduitContractorSheet = workbook.createSheet("CONDUIT & CONDUCTOR SEGMENTS");
			HSSFSheet addInfoSheet = workbook.createSheet("ADDITIONAL INFO");
			HSSFSheet layoutsheet = workbook.createSheet("LAYOUT SKETCH");
			HSSFSheet utilitySheet = workbook.createSheet("UTILITY COMPANY INFO");
			HSSFSheet advSheet = workbook.createSheet("ADV PERMITS INPUTS");
			HSSFSheet drafterSheet = workbook.createSheet("DRAFTER DATA");

			HSSFCellStyle cellStyleOrange = workbook.createCellStyle();
			HSSFCellStyle cellStyleBlue = workbook.createCellStyle();
			HSSFCellStyle cellStyleRose = workbook.createCellStyle();
			HSSFCellStyle cellStyleRed = workbook.createCellStyle();
			HSSFCellStyle cellStyleGreen = workbook.createCellStyle();
			HSSFCellStyle cellStyleYellow = workbook.createCellStyle();
			HSSFCellStyle cellStyleSkyBlue = workbook.createCellStyle();
			HSSFCellStyle cellStyleOlive = workbook.createCellStyle();
			HSSFCellStyle cellStyleViolet = workbook.createCellStyle();
			HSSFCellStyle cellStyleYellowLight = workbook.createCellStyle();
			
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
			
			cellStyleYellowLight.setFillForegroundColor(HSSFColor.YELLOW.index);
			cellStyleYellowLight.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			/*
			 * HOMEOWNER/SITE INFO 156 ----> 1220 ( Done )
			 */
			Row homeOwnerRow = homeOwnerSheet.createRow(0);
			homeOwnerRow.createCell(0).setCellStyle(cellStyleOrange);
			homeOwnerRow.getCell(0).setCellValue("HOMEOWNER/SITE INFO");

			homeOwnerRow = homeOwnerSheet.createRow(1);

			homeOwnerRow.createCell(0).setCellStyle(cellStyleOrange);
			homeOwnerRow.createCell(1).setCellStyle(cellStyleOrange);

			homeOwnerRow.getCell(0).setCellValue("Attribute Name");
			homeOwnerRow.getCell(1).setCellValue("Orig.");

			LinkedHashMap<String, String> homeOwner = exportHomeownerInfo.homeOwnerMap(permit);

			Iterator<String> keySetIteratorHomeOwner = homeOwner.keySet().iterator();

			int i = 2;
			while (keySetIteratorHomeOwner.hasNext()) {
				homeOwnerRow = homeOwnerSheet.createRow(i);
				String key = keySetIteratorHomeOwner.next();
				homeOwnerRow.createCell(0).setCellValue(key);

				// R.G 27-01-2022 PP-688
				if (key.equals("Florida Roof Rafter Design note")
						&& checkValueTypes.isStringNotEmpty(homeOwner.get(key))) {
					homeOwnerRow.createCell(1).setCellStyle(cellStyleYellowLight);
					homeOwnerRow.getCell(1).setCellValue(homeOwner.get(key));
				} else {
					homeOwnerRow.createCell(1).setCellValue(homeOwner.get(key));
				}

				i++;
			}

			/*
			 * ARRAY(S) 1221 ------> 2073 ( Done )
			 */
			Row arraysRow = arraysSheet.createRow(0);
			arraysRow.createCell(0).setCellStyle(cellStyleBlue);
			arraysRow.getCell(0).setCellValue("ARRAY(S)");

			arraysRow = arraysSheet.createRow(1);

			arraysRow.createCell(0).setCellStyle(cellStyleBlue);
			arraysRow.createCell(1).setCellStyle(cellStyleBlue);

			arraysRow.getCell(0).setCellValue("Attribute Name");
			arraysRow.getCell(1).setCellValue("Orig.");

			LinkedHashMap<String, String> arrays = exportArraysInfo.arraysMap(permit);
			LinkedHashMap<String, String> energySystem = energyBatterySystem.energyBatterySystem(permit);
			if(energySystem != null && !energySystem.isEmpty()) {
				arrays.putAll(energyBatterySystem.energyBatterySystem(permit));
			}
				
			Iterator<String> keySetIteratorArrays = arrays.keySet().iterator();

			int i1 = 2;
			while (keySetIteratorArrays.hasNext()) {
				arraysRow = arraysSheet.createRow(i1);
				String key = keySetIteratorArrays.next();
				arraysRow.createCell(0).setCellValue(key);
			
				// R.G 27-01-2022 PP-688
				if ((key.equals("Upload Comments") || key.equals("Non-Eligible Inverters notes"))
						&& checkValueTypes.isStringNotEmpty(arrays.get(key))) {
					arraysRow.createCell(1).setCellStyle(cellStyleYellowLight);
					arraysRow.getCell(1).setCellValue(arrays.get(key));
				} else {
					arraysRow.createCell(1).setCellValue(arrays.get(key));
				}

				i1++;
			}

			/*
			 * BALANCE OF SYSTEMS (BOS) 2077 -----> 6244 ( Done )
			 */
			Row bosRow = bosSheet.createRow(0);
			bosRow.createCell(0).setCellStyle(cellStyleRose);
			bosRow.getCell(0).setCellValue("BALANCE OF SYSTEMS (BOS)");

			bosRow = bosSheet.createRow(1);

			bosRow.createCell(0).setCellStyle(cellStyleRose);
			bosRow.createCell(1).setCellStyle(cellStyleRose);

			bosRow.getCell(0).setCellValue("Attribute Name");
			bosRow.getCell(1).setCellValue("Orig.");

			LinkedHashMap<String, String> bos = exportProjectInfo.balanceOfSystem(permit);

			Iterator<String> keySetIteratorBOS = bos.keySet().iterator();

			int i2 = 2;

			while (keySetIteratorBOS.hasNext()) {
				bosRow = bosSheet.createRow(i2);
				String key = keySetIteratorBOS.next();
				bosRow.createCell(0).setCellValue(key);
				//R.G 27-01-2022 PP-688
				if ((key.equals("Upload Comments") || key.equals("Not allowed roof material note"))
						&& checkValueTypes.isStringNotEmpty(bos.get(key))) {
					bosRow.createCell(1).setCellStyle(cellStyleYellowLight);
					bosRow.getCell(1).setCellValue(bos.get(key));
				} else {
					bosRow.createCell(1).setCellValue(bos.get(key));
				}

				i2++;
			}

			/*
			 * CONDUIT & CONDUCTOR SEGMENTS 6248 ------> 7581 ( Done )
			 */

			Row conduitContractorRow = conduitContractorSheet.createRow(0);

			conduitContractorRow.createCell(0).setCellStyle(cellStyleRed);
			conduitContractorRow.getCell(0).setCellValue("CONDUIT & CONDUCTOR SEGMENTS");

			conduitContractorRow = conduitContractorSheet.createRow(1);

			conduitContractorRow.createCell(0).setCellStyle(cellStyleRed);
			conduitContractorRow.createCell(1).setCellStyle(cellStyleRed);

			conduitContractorRow.getCell(0).setCellValue("Attribute Name");
			conduitContractorRow.getCell(1).setCellValue("Orig.");

			LinkedHashMap<String, String> conduitContractor = exportCircuitInfo.conduitContractorSection(permit);

			Iterator<String> keySetIteratorConduitContr = conduitContractor.keySet().iterator();

			int i3 = 2;
			while (keySetIteratorConduitContr.hasNext()) {
				conduitContractorRow = conduitContractorSheet.createRow(i3);
				String key = keySetIteratorConduitContr.next();
				conduitContractorRow.createCell(0).setCellValue(key);
				conduitContractorRow.createCell(1).setCellValue(conduitContractor.get(key));
				i3++;
			}

			/*
			 * Addional Info Tab 7585 -----> 8561 ( Done )
			 */

			Row additionalRow = addInfoSheet.createRow(0);

			additionalRow.createCell(0).setCellStyle(cellStyleGreen);
			additionalRow.getCell(0).setCellValue("Addional Info Tab");

			additionalRow = addInfoSheet.createRow(1);

			additionalRow.createCell(0).setCellStyle(cellStyleGreen);
			additionalRow.createCell(1).setCellStyle(cellStyleGreen);

			additionalRow.getCell(0).setCellValue("Attribute Name");
			additionalRow.getCell(1).setCellValue("Orig.");

			LinkedHashMap<String, String> addtionalInfo = exportAdditonalInfo.additionalInfo(permit);

			Iterator<String> keySetIteratoradditionalInfo = addtionalInfo.keySet().iterator();
			int i4 = 2;
			while (keySetIteratoradditionalInfo.hasNext()) {
				additionalRow = addInfoSheet.createRow(i4);
				String key = keySetIteratoradditionalInfo.next();
				additionalRow.createCell(0).setCellValue(key);
				//R.G 27-01-2022 PP-688
				if ((key.equals("Upload Comments") || key.equals("Installation Guidelines"))
						&& checkValueTypes.isStringNotEmpty(addtionalInfo.get(key))) {
					additionalRow.createCell(1).setCellStyle(cellStyleYellowLight);
					additionalRow.getCell(1).setCellValue(addtionalInfo.get(key));
				} else {
					additionalRow.createCell(1).setCellValue(addtionalInfo.get(key));
				}
				
				i4++;
			}

			/*
			 * Layout Sketch Tab 8565 ----> 9130 ( Done )
			 */
			Row sketchLayoutRow = layoutsheet.createRow(0);

			sketchLayoutRow.createCell(0).setCellStyle(cellStyleYellow);
			sketchLayoutRow.getCell(0).setCellValue("Layout Sketch Tab");

			sketchLayoutRow = layoutsheet.createRow(1);

			sketchLayoutRow.createCell(0).setCellStyle(cellStyleYellow);
			sketchLayoutRow.createCell(1).setCellStyle(cellStyleYellow);

			sketchLayoutRow.getCell(0).setCellValue("Attribute Name");
			sketchLayoutRow.getCell(1).setCellValue("Orig.");

			LinkedHashMap<String, String> sktechLayout = exportLayoutSketch.layoutSketch(permit);

			Iterator<String> keySetIteratorSketchLayout = sktechLayout.keySet().iterator();

			int i5 = 2;
			while (keySetIteratorSketchLayout.hasNext()) {
				sketchLayoutRow = layoutsheet.createRow(i5);
				String key = keySetIteratorSketchLayout.next();
				sketchLayoutRow.createCell(0).setCellValue(key);

				// R.G 27-01-2022 PP-688
				if ((key.equals("Upload Comments") || key.equals(
						"Insert or enter the note if any you wish to display on your plan set sheet that indicates all Fire Setbacks")
						|| key.equals("Sketch Note") || key.equals("Additional Information Upload Comments"))
						&& checkValueTypes.isStringNotEmpty(sktechLayout.get(key))) {
					sketchLayoutRow.createCell(1).setCellStyle(cellStyleYellowLight);
					sketchLayoutRow.getCell(1).setCellValue(sktechLayout.get(key));
				} else {
					sketchLayoutRow.createCell(1).setCellValue(sktechLayout.get(key));
				}

				i5++;
			}

			/*
			 * Utility Company Info 9134 -----> 10240 ( Done )
			 */
			Row utilityRow = utilitySheet.createRow(0);

			utilityRow.createCell(0).setCellStyle(cellStyleSkyBlue);
			utilityRow.getCell(0).setCellValue("Utility Company Info");

			utilityRow = utilitySheet.createRow(1);

			utilityRow.createCell(0).setCellStyle(cellStyleSkyBlue);
			utilityRow.createCell(1).setCellStyle(cellStyleSkyBlue);

			utilityRow.getCell(0).setCellValue("Attribute Name");
			utilityRow.getCell(1).setCellValue("Orig.");

			LinkedHashMap<String, String> utilityCompany = exportUtilityCompanyInfo.utilityCompany(permit);

			Iterator<String> keySetIteratorUtility = utilityCompany.keySet().iterator();

			int i6 = 2;
			while (keySetIteratorUtility.hasNext()) {
				utilityRow = utilitySheet.createRow(i6);
				String key = keySetIteratorUtility.next();
				utilityRow.createCell(0).setCellValue(key);
				utilityRow.createCell(1).setCellValue(utilityCompany.get(key));
				i6++;
			}

			/*
			 * ADV Permits Inputs Tab 10244 -----> 10699 ( Done )
			 */

			Row advRow = advSheet.createRow(0);

			advRow.createCell(0).setCellStyle(cellStyleOlive);
			advRow.getCell(0).setCellValue("ADV Permits Inputs Tab");

			advRow = advSheet.createRow(1);

			advRow.createCell(0).setCellStyle(cellStyleOlive);
			advRow.createCell(1).setCellStyle(cellStyleOlive);

			advRow.getCell(0).setCellValue("Attribute Name");
			advRow.getCell(1).setCellValue("Orig.");

			LinkedHashMap<String, String> adv = exportADVInputs.advPermitsInputs(permit);

			Iterator<String> keySetIteratorAdv = adv.keySet().iterator();

			int i7 = 2;
			while (keySetIteratorAdv.hasNext()) {
				advRow = advSheet.createRow(i7);
				String key = keySetIteratorAdv.next();
				advRow.createCell(0).setCellValue(key);
				//R.G 27-01-2022 PP-688
				if ((key.equals("Upload Google Earth Image Comments") || key.equals("Upload NearMap Image Comments"))
						&& checkValueTypes.isStringNotEmpty(adv.get(key))) {
					advRow.createCell(1).setCellStyle(cellStyleYellowLight);
					advRow.getCell(1).setCellValue(adv.get(key));
				} else {
					advRow.createCell(1).setCellValue(adv.get(key));
				}
			
				i7++;
			}

			/*
			 * Drafter Data Tab 10702 ----> 11017 (Done)
			 */

			Row drafterRow = drafterSheet.createRow(0);

			drafterRow.createCell(0).setCellStyle(cellStyleViolet);
			drafterRow.getCell(0).setCellValue("Drafter Data Tab");

			drafterRow = drafterSheet.createRow(1);

			drafterRow.createCell(0).setCellStyle(cellStyleViolet);
			drafterRow.createCell(1).setCellStyle(cellStyleViolet);

			drafterRow.getCell(0).setCellValue("Attribute Name");
			drafterRow.getCell(1).setCellValue("Orig.");

			LinkedHashMap<String, String> drafter = exportDrafterData.drafterData(permit);

			Iterator<String> keySetIteratordrafter = drafter.keySet().iterator();

			int i8 = 2;
			while (keySetIteratordrafter.hasNext()) {
				drafterRow = drafterSheet.createRow(i8);
				String key = keySetIteratordrafter.next();
				drafterRow.createCell(0).setCellValue(key);
				drafterRow.createCell(1).setCellValue(drafter.get(key));
				i8++;
			}

			String googleDrivePath = pathRepo.findGoogleDriveFilePath();
			File exportSaveFolder = null;
			exportSaveFolder = new File(fileDirPath + permit.getPermitEntity().getId());
			if (!exportSaveFolder.exists()) {
				new File(fileDirPath + permit.getPermitEntity().getId()).mkdirs();
			}

			String filePath = fileDirPath + permit.getPermitEntity().getId() + "/" + permit.getPermitEntity().getId()
					+ ".xls";
			fileOut = new FileOutputStream(filePath);
			for (int c = 0; c < homeOwnerSheet.getLastRowNum(); c++) {
				homeOwnerSheet.autoSizeColumn(c);
			}
			for (int c = 0; c < arraysSheet.getLastRowNum(); c++) {
				arraysSheet.autoSizeColumn(c);
			}
			for (int c = 0; c < bosSheet.getLastRowNum(); c++) {
				bosSheet.autoSizeColumn(c);
			}
			for (int c = 0; c < conduitContractorSheet.getLastRowNum(); c++) {
				conduitContractorSheet.autoSizeColumn(c);
			}
			for (int c = 0; c < addInfoSheet.getLastRowNum(); c++) {
				addInfoSheet.autoSizeColumn(c);
			}
			for (int c = 0; c < layoutsheet.getLastRowNum(); c++) {
				layoutsheet.autoSizeColumn(c);
			}
			for (int c = 0; c < utilitySheet.getLastRowNum(); c++) {
				utilitySheet.autoSizeColumn(c);
			}
			for (int c = 0; c < advSheet.getLastRowNum(); c++) {
				advSheet.autoSizeColumn(c);
			}
			for (int c = 0; c < drafterSheet.getLastRowNum(); c++) {
				drafterSheet.autoSizeColumn(c);
			}
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			workbook.close();

			// A.B 08-26: CR-2847 Save Export File to google drive
			Date date = new Date(); // this object contains the current date value
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");

			String folderName = permit.getPermitEntity().getProjectName();
			if (!checkValueTypes.isStringNotEmpty(permit.getPermitEntity().getProjectName()))
				folderName = permit.getPermitEntity().getHomeOwnLastName() + ", "
						+ permit.getPermitEntity().getHomeOwnName();

			// A.B 10-28 Rev 8 CR-2847 Add folder under company name else under owner full
			// name
			String ownerFolderName = googleDriveFolder.getfolderName(permit.getPermitEntity().getAuthentificationEntity());
			
			if (new File(googleDrivePath + ownerFolderName + "/" + folderName + "/Team Project Folder").exists()) {
				Path expSrcFile = Paths.get(fileDirPath + permit.getPermitEntity().getId() + "/"
						+ permit.getPermitEntity().getId() + ".xls");
				Path expDestFile = Paths.get(googleDrivePath + ownerFolderName + "/" + folderName
						+ "/Team Project Folder/" + folderName + "_" + formatter.format(date) +"_"+user.getFirstName()+" "+user.getLastName()+ ".xls");
				Files.copy(expSrcFile.toFile(), expDestFile.toFile());
			}

			return permit.getPermitEntity().getId() + "/" + permit.getPermitEntity().getId() + ".xls";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	



	
	
	

}