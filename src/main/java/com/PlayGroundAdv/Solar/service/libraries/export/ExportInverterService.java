package com.PlayGroundAdv.Solar.service.libraries.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;

@Service
@Transactional
public class ExportInverterService {

	final HistoryActivityService historyActivityService;
	final PathRepository pathRepo;
	final InverterRepository inverterRepo;

	public ExportInverterService(HistoryActivityService historyActivityService, PathRepository pathRepo,
			InverterRepository inverterRepo) {
		super();
		this.historyActivityService = historyActivityService;
		this.pathRepo = pathRepo;
		this.inverterRepo = inverterRepo;
	}

	public ResponseEntity<byte[]> exportInverterSvc(Long id, String ipAdress, String timeZone, String numTab, String sessionId,
			String openDate) {

		try {

			FileOutputStream fileOut;
			List<Inverters> inverter = inverterRepo.findAllByDeletedFalse();
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Inverter");

			HSSFCellStyle cellStyleRed = workbook.createCellStyle();
			HSSFCellStyle cellStyleYellow = workbook.createCellStyle();
			HSSFFont font = workbook.createFont();
			font.setColor(HSSFColor.YELLOW.index);
			cellStyleRed.setFont(font);
			cellStyleRed.setFillForegroundColor(HSSFColor.RED.index);
			cellStyleRed.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			cellStyleYellow.setFillForegroundColor(HSSFColor.YELLOW.index);
			cellStyleYellow.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			Row header = sheet.createRow(0);
			header.createCell(0).setCellValue("Manufacturer");
			header.createCell(1).setCellValue("Model");
			header.createCell(2).setCellValue("Vac");
			header.createCell(3).setCellValue("Paco");
			header.createCell(4).setCellValue("Pdco");
			header.createCell(5).setCellValue("Vdco");
			header.createCell(6).setCellValue("Pso");
			header.createCell(7).setCellValue("C0");
			header.createCell(8).setCellValue("C1");
			header.createCell(9).setCellValue("C2");
			header.createCell(10).setCellValue("C3");
			header.createCell(11).setCellValue("Pnt");
			header.createCell(12).setCellValue("Vdcmax");
			header.createCell(13).setCellValue("Idcmax");
			header.createCell(14).setCellValue("Mppt_low");
			header.createCell(15).setCellValue("Mppt_high");
			header.createCell(16).setCellValue("Power Rating(watts)");
			header.createCell(17).setCellValue("Weighted Efficiency");
			header.createCell(18).setCellValue("Micro-Inverter");
			header.createCell(19).setCellValue("Iacmax");
			header.createCell(20).setCellValue("Weight");
			header.createCell(21).setCellValue("Integrated DC Disco");
			header.createCell(22).setCellValue("Integrated AC Disco");
			header.createCell(23).setCellValue("Integrated RSD");
			header.createCell(24).setCellValue("Peak Output power");
			header.createCell(25).setCellValue("Data Sheet location");
			header.createCell(26).setCellValue("Date");

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
			header.getCell(14).setCellStyle(cellStyleYellow);
			header.getCell(15).setCellStyle(cellStyleYellow);
			header.getCell(16).setCellStyle(cellStyleRed);
			header.getCell(17).setCellStyle(cellStyleRed);
			header.getCell(18).setCellStyle(cellStyleRed);
			header.getCell(19).setCellStyle(cellStyleRed);
			header.getCell(20).setCellStyle(cellStyleRed);
			header.getCell(21).setCellStyle(cellStyleRed);
			header.getCell(22).setCellStyle(cellStyleRed);
			header.getCell(23).setCellStyle(cellStyleRed);
			header.getCell(24).setCellStyle(cellStyleRed);

			DateFormat dString = new SimpleDateFormat("MM-dd-yyyy");
			int rowCount = 0;
			for (Inverters inverterRow : inverter) {
				Row row = sheet.createRow(++rowCount);
				for (int i = 0; i < 27; i++) {
					row.createCell(i);
				}

				row.getCell(0).setCellValue((String) inverterRow.getMake());
				row.getCell(1).setCellValue((String) inverterRow.getModel());
				row.getCell(2).setCellValue((String) inverterRow.getVac());
				row.getCell(3).setCellValue((String) inverterRow.getPaco());
				row.getCell(4).setCellValue((String) inverterRow.getPdco());
				row.getCell(5).setCellValue((String) inverterRow.getVdco());
				row.getCell(6).setCellValue((String) inverterRow.getPso());
				row.getCell(7).setCellValue((String) inverterRow.getC0());
				row.getCell(8).setCellValue((String) inverterRow.getC1());
				row.getCell(9).setCellValue((String) inverterRow.getC2());
				row.getCell(10).setCellValue((String) inverterRow.getC3());
				row.getCell(11).setCellValue((String) inverterRow.getPnt());
				row.getCell(12).setCellValue((String) inverterRow.getVdcmax());
				row.getCell(13).setCellValue((String) inverterRow.getIdcmax());
				row.getCell(14).setCellValue((String) inverterRow.getMpptLow());
				row.getCell(15).setCellValue((String) inverterRow.getMpptHigh());
				row.getCell(16).setCellValue((String) inverterRow.getPowerRating());
				row.getCell(17).setCellValue((String) inverterRow.getWeightedEfficiency());
				if (inverterRow.getMicroInverter() != null) {
					row.getCell(18).setCellValue((Boolean) inverterRow.getMicroInverter());
				}
				row.getCell(19).setCellValue((String) inverterRow.getIacmax());
				row.getCell(20).setCellValue((String) inverterRow.getWeight());
				if (inverterRow.getIntegratedDCDisco() != null) {
					row.getCell(21).setCellValue((Boolean) inverterRow.getIntegratedDCDisco());
				}
				if (inverterRow.getIntegratedACDisco() != null) {
					row.getCell(22).setCellValue((Boolean) inverterRow.getIntegratedACDisco());
				}
				if (inverterRow.getIntegratedRsd() != null) {
					row.getCell(23).setCellValue((Boolean) inverterRow.getIntegratedRsd());
				}
				if (inverterRow.getPeakOutputPower() != null) {
					row.getCell(24).setCellValue(String.valueOf(inverterRow.getPeakOutputPower()));
				}
				row.getCell(25).setCellValue((String) inverterRow.getDataSheet());
				row.getCell(26).setCellValue((String) dString.format(new Date()));
			}
			for (int i = 0; i < 26; i++) {
				sheet.autoSizeColumn(i);
			}

			String url = pathRepo.findFilePath();
			if (!new File(url + "Inverter Library").exists()) {
				new File(url + "Inverter Library").mkdir();
			}

			String fileName = "Inverter Library " + dString.format(new Date()) + ".xls";
			String filePath = url + "Inverter Library/" + fileName;
			fileOut = new FileOutputStream(filePath);

			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			workbook.close();

			historyActivityService.recordActivity(id, ipAdress, timeZone, "Export Inverter", true, numTab, sessionId,
					openDate);
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
			historyActivityService.recordActivity(id, ipAdress, timeZone, "Export Inverter", false, numTab, sessionId,
					openDate);
			return new ResponseEntity<>(null, null, org.springframework.http.HttpStatus.NO_CONTENT);
		}

	}

}
