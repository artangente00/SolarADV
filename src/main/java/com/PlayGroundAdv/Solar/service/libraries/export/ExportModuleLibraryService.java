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
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;

@Service
@Transactional
public class ExportModuleLibraryService {

	final HistoryActivityService historyActivityService;
	final PathRepository pathRepo;
	final ModuleRepository moduleRepo;

	public ExportModuleLibraryService(HistoryActivityService historyActivityService, PathRepository pathRepo,
			ModuleRepository moduleRepo) {
		super();
		this.historyActivityService = historyActivityService;
		this.pathRepo = pathRepo;
		this.moduleRepo = moduleRepo;
	}

	public ResponseEntity<byte[]> ExportModuleLibrary(Long id, String ipAdress, String timeZone, String numTab, String sessionId,
			String openDate) {

		try {

			List<Cmodulev2> moduleLibrary = moduleRepo.findAllByDeletedFalse();

			FileOutputStream fileOut;
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Modules Library");

			sheet.setColumnWidth(0, 5000);
			sheet.setColumnWidth(1, 5000);
			sheet.setColumnWidth(2, 3000);
			sheet.setColumnWidth(3, 3000);
			sheet.setColumnWidth(4, 3000);
			sheet.setColumnWidth(5, 3000);
			sheet.setColumnWidth(6, 3000);
			sheet.setColumnWidth(7, 3000);
			sheet.setColumnWidth(8, 3000);
			sheet.setColumnWidth(9, 3000);
			sheet.setColumnWidth(10, 3000);
			sheet.setColumnWidth(11, 3000);
			sheet.setColumnWidth(12, 3000);
			sheet.setColumnWidth(13, 3000);
			sheet.setColumnWidth(14, 3000);
			sheet.setColumnWidth(15, 3000);
			sheet.setColumnWidth(16, 3000);
			sheet.setColumnWidth(17, 3000);
			sheet.setColumnWidth(18, 3000);
			sheet.setColumnWidth(19, 3000);
			sheet.setColumnWidth(20, 3000);
			sheet.setColumnWidth(21, 3000);
			sheet.setColumnWidth(22, 3000);
			sheet.setColumnWidth(23, 3000);
			sheet.setColumnWidth(24, 3000);
			sheet.setColumnWidth(25, 3000);
			sheet.setColumnWidth(26, 3000);
			sheet.setColumnWidth(27, 3000);
			sheet.setColumnWidth(28, 3000);
			sheet.setColumnWidth(29, 3000);
			sheet.setColumnWidth(30, 3000);
			sheet.setColumnWidth(31, 3000);
			sheet.setColumnWidth(32, 3000);
			// --------------- First Row --------------//
			Row row0 = sheet.createRow(0);

			row0.createCell(0).setCellValue("_");
			row0.createCell(4).setCellValue("C");
			row0.createCell(5).setCellValue("m2");

			row0.createCell(7).setCellValue("A");
			row0.createCell(8).setCellValue("V");
			row0.createCell(9).setCellValue("A");
			row0.createCell(10).setCellValue("V");
			row0.createCell(11).setCellValue("A/K");
			row0.createCell(12).setCellValue("V/K");

			row0.createCell(13).setCellValue("V");
			row0.createCell(14).setCellValue("A");
			row0.createCell(15).setCellValue("A");
			row0.createCell(16).setCellValue("Ohm");
			row0.createCell(17).setCellValue("Ohm");
			row0.createCell(18).setCellValue("%");
			row0.createCell(19).setCellValue("%k");

			row0.createCell(23).setCellValue("CAN BE HIDDEN");

			// --------------- Second Row --------------//
			Row row1 = sheet.createRow(1);

			row1.createCell(0).setCellValue("[0]");

			row1.createCell(4).setCellValue("cec_t_noct");
			row1.createCell(5).setCellValue("cec_area");
			row1.createCell(6).setCellValue("cec_n_s");
			row1.createCell(7).setCellValue("cec_i_sc_ref");
			row1.createCell(8).setCellValue("cec_v_oc_ref");
			row1.createCell(9).setCellValue("cec_i_mp_ref");
			row1.createCell(10).setCellValue("cec_iac_max");
			row1.createCell(11).setCellValue("cec_v_mp_ref");
			row1.createCell(12).setCellValue("cec_alpha_sc");
			row1.createCell(13).setCellValue("cec_beta_oc");
			row1.createCell(14).setCellValue("cec_a_ref");
			row1.createCell(15).setCellValue("cec_i_l_ref");
			row1.createCell(16).setCellValue("cec_i_o_ref");
			row1.createCell(17).setCellValue("cec_r_s");
			row1.createCell(18).setCellValue("cec_r_sh_ref");
			row1.createCell(19).setCellValue("cec_adjust");
			row1.createCell(20).setCellValue("cec_gamma_r");

			row1.createCell(22).setCellValue("cec_material");

			// --------------- Third Row --------------//

			Row row2 = sheet.createRow(2);

			HSSFCellStyle cellStyle = workbook.createCellStyle();
			Font fontRed = workbook.createFont();
			fontRed.setColor(HSSFColor.RED.index);
			cellStyle.setFont(fontRed);

			HSSFCellStyle cellStyleGras = workbook.createCellStyle();
			Font fontGras = workbook.createFont();
			fontGras.setBold(true);
			;
			cellStyleGras.setFont(fontGras);

			row2.createCell(0).setCellStyle(cellStyleGras);
			row2.createCell(1).setCellStyle(cellStyleGras);
			row2.createCell(2).setCellStyle(cellStyleGras);
			row2.createCell(3).setCellStyle(cellStyleGras);
			row2.createCell(4).setCellStyle(cellStyleGras);
			row2.createCell(5).setCellStyle(cellStyleGras);
			row2.createCell(6).setCellStyle(cellStyleGras);
			row2.createCell(7).setCellStyle(cellStyleGras);
			row2.createCell(8).setCellStyle(cellStyleGras);
			row2.createCell(9).setCellStyle(cellStyleGras);
			row2.createCell(10).setCellStyle(cellStyleGras);
			row2.createCell(11).setCellStyle(cellStyleGras);
			row2.createCell(12).setCellStyle(cellStyleGras);
			row2.createCell(13).setCellStyle(cellStyleGras);
			row2.createCell(14).setCellStyle(cellStyleGras);
			row2.createCell(15).setCellStyle(cellStyleGras);
			row2.createCell(16).setCellStyle(cellStyleGras);
			row2.createCell(17).setCellStyle(cellStyleGras);
			row2.createCell(18).setCellStyle(cellStyleGras);
			row2.createCell(19).setCellStyle(cellStyleGras);
			row2.createCell(20).setCellStyle(cellStyleGras);
			row2.createCell(21).setCellStyle(cellStyleGras);
			row2.createCell(22).setCellStyle(cellStyleGras);
			row2.createCell(23).setCellStyle(cellStyleGras);
			row2.createCell(24).setCellStyle(cellStyleGras);
			row2.createCell(25).setCellStyle(cellStyleGras);
			row2.createCell(26).setCellStyle(cellStyleGras);
			row2.createCell(27).setCellStyle(cellStyleGras);
			row2.createCell(28).setCellStyle(cellStyleGras);
			row2.createCell(29).setCellStyle(cellStyleGras);
			row2.createCell(30).setCellStyle(cellStyleGras);
			row2.createCell(31).setCellStyle(cellStyleGras);
			row2.createCell(32).setCellStyle(cellStyleGras);

			row2.getCell(0).setCellValue("Manufacturer");
			row2.getCell(1).setCellValue("Model");

			row2.getCell(2).setCellStyle(cellStyle);
			row2.getCell(2).setCellValue("BIPV");

			row2.getCell(3).setCellStyle(cellStyle);
			row2.getCell(3).setCellValue("Date");

			row2.getCell(4).setCellValue("T_NOCT");
			row2.getCell(5).setCellValue("A_c");
			row2.getCell(6).setCellValue("N_s");
			row2.getCell(7).setCellValue("I_sc_ref");
			row2.getCell(8).setCellValue("V_oc_ref");
			row2.getCell(9).setCellValue("I_mp_ref");
			row2.getCell(10).setCellValue("Iac_max");
			row2.getCell(11).setCellValue("V_mp_ref");
			row2.getCell(12).setCellValue("alpha_sc");
			row2.getCell(13).setCellValue("beta_oc");
			row2.getCell(14).setCellValue("a_ref");
			row2.getCell(15).setCellValue("I_L_ref");
			row2.getCell(16).setCellValue("I_o_ref");
			row2.getCell(17).setCellValue("R_s");
			row2.getCell(18).setCellValue("R_sh_ref");

			row2.getCell(19).setCellStyle(cellStyle);
			row2.getCell(19).setCellValue("Adjust");

			row2.getCell(20).setCellValue("gamma_r");

			row2.getCell(21).setCellStyle(cellStyle);
			row2.getCell(21).setCellValue("Version");

			row2.getCell(22).setCellValue("PTC");
			row2.getCell(23).setCellValue("Max. Series Fuse Rating");

			row2.getCell(24).setCellStyle(cellStyle);
			row2.getCell(24).setCellValue("Technology");

			row2.getCell(25).setCellValue("STC");
			row2.getCell(26).setCellValue("STC Rounded");
			row2.getCell(27).setCellValue("Length");
			row2.getCell(28).setCellValue("Width");
			row2.getCell(29).setCellValue("Depth");
			row2.getCell(30).setCellValue("Weight");

			row2.getCell(31).setCellValue("Spec Sheet Location");
			row2.getCell(32).setCellValue("Date");

			// ---------------Data Base Mapping--------------//
			DateFormat dString = new SimpleDateFormat("MM-dd-yyyy");

			int x = 0;
			while (x < moduleLibrary.size()) {

				Row row = sheet.createRow(x + 3);

				row.createCell(0).setCellValue(moduleLibrary.get(x).getMake());
				row.createCell(1).setCellValue(moduleLibrary.get(x).getModel());
				row.createCell(2).setCellValue(moduleLibrary.get(x).getBipv());
				row.createCell(3).setCellValue(moduleLibrary.get(x).getDate());
				row.createCell(4).setCellValue(moduleLibrary.get(x).gettNoct());
				row.createCell(5).setCellValue(moduleLibrary.get(x).getaC());
				row.createCell(6).setCellValue(moduleLibrary.get(x).getnS());
				row.createCell(7).setCellValue(moduleLibrary.get(x).getiScRef());
				row.createCell(8).setCellValue(moduleLibrary.get(x).getvOcRef());
				row.createCell(9).setCellValue(moduleLibrary.get(x).getiMpRef());
				row.createCell(10).setCellValue(moduleLibrary.get(x).getIacmax());
				row.createCell(11).setCellValue(moduleLibrary.get(x).getvMpRef());
				row.createCell(12).setCellValue(moduleLibrary.get(x).getAlphaSc());
				row.createCell(13).setCellValue(moduleLibrary.get(x).getBetaOc());
				row.createCell(14).setCellValue(moduleLibrary.get(x).getaRef());
				row.createCell(15).setCellValue(moduleLibrary.get(x).getiIRef());
				row.createCell(16).setCellValue(moduleLibrary.get(x).getiORef());
				row.createCell(17).setCellValue(moduleLibrary.get(x).getrS());
				row.createCell(18).setCellValue(moduleLibrary.get(x).getrShRef());
				row.createCell(19).setCellValue(moduleLibrary.get(x).getAdjust());
				row.createCell(20).setCellValue(moduleLibrary.get(x).getGammaR());
				row.createCell(21).setCellValue(moduleLibrary.get(x).getVersion());
				row.createCell(22).setCellValue(moduleLibrary.get(x).getPtc());
				row.createCell(23).setCellValue(moduleLibrary.get(x).getMaxSeriesFuseRating());
				row.createCell(24).setCellValue(moduleLibrary.get(x).getTechnology());
				row.createCell(25).setCellValue(moduleLibrary.get(x).getStc());
				row.createCell(26).setCellValue(moduleLibrary.get(x).getStcRounded());
				row.createCell(27).setCellValue(moduleLibrary.get(x).getLength());
				row.createCell(28).setCellValue(moduleLibrary.get(x).getWidth());
				row.createCell(29).setCellValue(moduleLibrary.get(x).getDepth());
				row.createCell(30).setCellValue(moduleLibrary.get(x).getWeight());
				row.createCell(32).setCellValue(dString.format(new Date()));
				x += 1;
			}

			String url = pathRepo.findFilePath();

			if (!new File(url + "Module Library").exists()) {
				new File(url + "Module Library").mkdir();
			}

			String fileName = "Modules Library " + dString.format(new Date()) + ".xls";
			String filePath = url + "Module Library/" + fileName;
			fileOut = new FileOutputStream(filePath);
			workbook.write(fileOut);
			fileOut.close();

			historyActivityService.recordActivity(id, ipAdress, timeZone, "Export Module", true, numTab, sessionId,
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
			historyActivityService.recordActivity(id, ipAdress, timeZone, "Export Module", false, numTab, sessionId,
					openDate);
			return new ResponseEntity<>(null, null, org.springframework.http.HttpStatus.NO_CONTENT);
		}

	}

}
