package com.PlayGroundAdv.Solar.service.libraries.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ConvertersRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ExportOptimizerService {

	final HistoryActivityService historyActivityService;
	final CheckValueTypesService checkValueTypesService;
	final PathRepository pathRepo;
	final ConvertersRepository converterRepo;

	public ExportOptimizerService(HistoryActivityService historyActivityService,
			CheckValueTypesService checkValueTypesService, PathRepository pathRepo,
			ConvertersRepository converterRepo) {
		super();
		this.historyActivityService = historyActivityService;
		this.checkValueTypesService = checkValueTypesService;
		this.pathRepo = pathRepo;
		this.converterRepo = converterRepo;
	}

	public List<DCOptimizerEntity> orderOptimizer(List<DCOptimizerEntity> moduleLibrary) {
		Collections.sort(moduleLibrary, new Comparator<DCOptimizerEntity>() {
			@Override
			public int compare(DCOptimizerEntity u1, DCOptimizerEntity u2) {
				return u1.getManufacturer().compareTo(u2.getManufacturer());
			}
		});
		return moduleLibrary;
	}

	public ResponseEntity<byte[]> exportOptimizerSvc(Long id, String ipAdress, String timeZone, String numTab, String sessionId,
			String openDate) {

		try {

			FileOutputStream fileOut;
			List<DCOptimizerEntity> optimizeRe = converterRepo.findByIsDeleted(false);
			optimizeRe = orderOptimizer(optimizeRe);
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("DC Optimizer");

			HSSFCellStyle cellStyleYellow = workbook.createCellStyle();
			HSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.YELLOW.index);

			cellStyleYellow.setFillForegroundColor(HSSFColor.YELLOW.index);
			cellStyleYellow.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("MANUFACTURER");
			header.createCell(1).setCellValue("MODEL");
			header.createCell(2).setCellValue("Phase");

			header.createCell(3).setCellValue("WEIGHT");
			header.createCell(4).setCellValue("RATED OUTPUT ISC");
			header.createCell(5).setCellValue("MAX SERIES FUSE RATING");
			header.createCell(6).setCellValue("MAX INPUT VOLTAGE");
			header.createCell(7).setCellValue("MAX OUTPUT VOLTAGE");

			header.createCell(8).setCellValue("PV Module Power");
			header.createCell(9).setCellValue("MIN STRING");
			header.createCell(10).setCellValue("MAX STRING");
			header.createCell(11).setCellValue("MAX POWER/STRING");

			header.createCell(12).setCellValue("QTY MODULES PER OPT");
			header.createCell(13).setCellValue("Updated");

			header.getCell(0).setCellStyle(cellStyleYellow);
			header.getCell(1).setCellStyle(cellStyleYellow);
			header.getCell(2).setCellStyle(cellStyleYellow);
			header.getCell(3).setCellStyle(cellStyleYellow);
			header.getCell(4).setCellStyle(cellStyleYellow);
			header.getCell(5).setCellStyle(cellStyleYellow);
			header.getCell(6).setCellStyle(cellStyleYellow);
			header.getCell(7).setCellStyle(cellStyleYellow);
			header.getCell(8).setCellStyle(cellStyleYellow);
			header.getCell(9).setCellStyle(cellStyleYellow);
			header.getCell(10).setCellStyle(cellStyleYellow);
			header.getCell(11).setCellStyle(cellStyleYellow);
			header.getCell(12).setCellStyle(cellStyleYellow);
			header.getCell(13).setCellStyle(cellStyleYellow);

			DateFormat dString = new SimpleDateFormat("MM-dd-yyyy");

			int rowCount = 0;
			for (DCOptimizerEntity optimizeRow : optimizeRe) {
				Row row = sheet.createRow(++rowCount);
				Cell cell;
				for (int i = 0; i < 25; i++) {
					cell = (Cell) row.createCell(i);
				}

				row.getCell(0).setCellValue((String) optimizeRow.getManufacturer());
				row.getCell(1).setCellValue((String) optimizeRow.getModel());
				row.getCell(2).setCellValue((String) optimizeRow.getPhase());

				if (optimizeRow.getWeight() != null) {
					row.getCell(3).setCellValue((String) optimizeRow.getWeight());
				}

				if (optimizeRow.getRatedOutputIsc() != null) {
					row.getCell(4).setCellValue((String) optimizeRow.getRatedOutputIsc());
				}

				if (optimizeRow.getMaxSeriesFuseRating() != null) {
					row.getCell(5).setCellValue((String) optimizeRow.getMaxSeriesFuseRating());
				}

				if (optimizeRow.getMaxInputVoltage() != null) {
					row.getCell(6).setCellValue((String) optimizeRow.getMaxInputVoltage());
				}

				if (optimizeRow.getMaxOutputVoltage() != null) {
					row.getCell(7).setCellValue((String) optimizeRow.getMaxOutputVoltage());
				}

				if (optimizeRow.getPvModulePower() != null) {
					row.getCell(8).setCellValue((String) optimizeRow.getPvModulePower());
				}

				if (optimizeRow.getMinString() != null) {
					row.getCell(9).setCellValue((String) optimizeRow.getMinString());
				}

				if (optimizeRow.getMaxString() != null) {
					row.getCell(10).setCellValue((String) optimizeRow.getMaxString());
				}

				if (optimizeRow.getMaxPowerString() != null) {
					row.getCell(11).setCellValue((String) optimizeRow.getMaxPowerString());
				}

				row.getCell(12).setCellValue((String) optimizeRow.getQtyModuleOpt());

				row.getCell(13).setCellValue((String) optimizeRow.getLastUpDate());
			}
			for (int i = 0; i < 25; i++) {
				sheet.autoSizeColumn(i);
			}

			String url = pathRepo.findFilePath();
			if (!new File(url + "DC Optimizer Library").exists()) {
				new File(url + "DC Optimizer Library").mkdir();
			}

			String fileName = "DC Optimizer Library " + dString.format(new Date()) + ".xls";
			String filePath = url + "DC Optimizer Library/" + fileName;
			fileOut = new FileOutputStream(filePath);

			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			workbook.close();

			historyActivityService.recordActivity(id, ipAdress, timeZone, "Export DC Optimizer", true, numTab,
					sessionId, openDate);
			Path path = Paths.get(filePath);
			byte[] contents = null;
			try {
				contents = Files.readAllBytes(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/vnd.ms-excel"));
			String filename = "output.xls";
			headers.setContentDispositionFormData(filename, filename);
			headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
			return new ResponseEntity<>(contents, headers, org.springframework.http.HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			historyActivityService.recordActivity(id, ipAdress, timeZone, "Export DC Optimizer", false, numTab,
					sessionId, openDate);
			return new ResponseEntity<>(null, null, org.springframework.http.HttpStatus.NO_CONTENT);
		}

	}

}
