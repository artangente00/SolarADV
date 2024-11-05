package com.PlayGroundAdv.Solar.service.libraries;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

import com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ACDisconnectRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ATSRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.AcCombinerSLCRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.BatteryRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ConvertersRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.DcCombinerDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.FlashingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.GeneratorRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.LeasePPAMeterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ProposedSubPanelRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RailRackingRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofAttachmentsRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.TiltLegsRepository;

@Service
public class ExportVerificationReport {

	final PathRepository pathRepo;
	final ACDisconnectRepository aCDisconnectRepo;
	final ModuleRepository moduleRepo;
	final InverterRepository inverterRepo;
	final ConvertersRepository converterRepo;
	final RailRackingRepository railRepo;
	final RoofAttachmentsRepository roofRepo;
	final FlashingRepository flashingRepo;
	final DcCombinerDiscoRepository dcCombRepo;
	final AcCombinerSLCRepository slcRepo;
	final LeasePPAMeterRepository lppamRepo;
	final BatteryRepository batteryRepo;
	final ATSRepository atsRepo;
	final GeneratorRepository generatorRepo;
	final TiltLegsRepository tiltRepo;
	final ProposedSubPanelRepository panalRepo;

	public ExportVerificationReport(PathRepository pathRepo, ACDisconnectRepository aCDisconnectRepo,
			ModuleRepository moduleRepo, InverterRepository inverterRepo, ConvertersRepository converterRepo,
			RailRackingRepository railRepo, RoofAttachmentsRepository roofRepo, FlashingRepository flashingRepo,
			DcCombinerDiscoRepository dcCombRepo, AcCombinerSLCRepository slcRepo, LeasePPAMeterRepository lppamRepo,
			BatteryRepository batteryRepo, ATSRepository atsRepo, GeneratorRepository generatorRepo,
			TiltLegsRepository tiltRepo, ProposedSubPanelRepository panalRepo) {
		super();
		this.pathRepo = pathRepo;
		this.aCDisconnectRepo = aCDisconnectRepo;
		this.moduleRepo = moduleRepo;
		this.inverterRepo = inverterRepo;
		this.converterRepo = converterRepo;
		this.railRepo = railRepo;
		this.roofRepo = roofRepo;
		this.flashingRepo = flashingRepo;
		this.dcCombRepo = dcCombRepo;
		this.slcRepo = slcRepo;
		this.lppamRepo = lppamRepo;
		this.batteryRepo = batteryRepo;
		this.atsRepo = atsRepo;
		this.generatorRepo = generatorRepo;
		this.tiltRepo = tiltRepo;
		this.panalRepo = panalRepo;
	}

	public ResponseEntity<byte[]> getVerificationReport(String title) {

		try {

			FileOutputStream fileOut;
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(title);

			HSSFCellStyle cellStyleOrange = workbook.createCellStyle();
			cellStyleOrange.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
			cellStyleOrange.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			Row header = sheet.createRow(0);
			
			List<String> h = Arrays.asList("Manufacturer", "Model", "Added by", "Added on (PST)", "Last Verified by",
					"Last Verified on (PST)", "Update by 1st time", "Update by 2nd time", "Update by 3rd time");
			for (int i = 0; i < h.size(); i++) {
				header.createCell(i).setCellStyle(cellStyleOrange);
				header.getCell(i).setCellValue(h.get(i));
			}

			DateFormat dString = new SimpleDateFormat("MM-dd-yyyy' 'HH'h'mm");
			dString.setTimeZone(TimeZone.getTimeZone("PST8PDT"));
			List<EquipmentVerificationModel> list = new ArrayList<>();
			switch (title) {
			case "AC Disconnect Library Verification":
				list = aCDisconnectRepo.findVerifiedList();
				break;
			case "Module Library Verification":
				list = moduleRepo.findVerifiedList();
				break;
			case "Inverter Library Verification":
				list = inverterRepo.findVerifiedList();
				break;
			case "DC_DC Converter or ML RSD Library Verification":
				list = converterRepo.findVerifiedList();
				break;
			case "Racking Mounting Library Verification":
				list = railRepo.findVerifiedList();
				break;
			case "Roof Attachments Library Verification":
				list = roofRepo.findVerifiedList();
				break;
			case "Flashing Library Verification":
				list = flashingRepo.findVerifiedList();
				break;
			case "DC Combiner or Disconnects Library Verification":
				list = dcCombRepo.findVerifiedList();
				break;
			case "Junction Box Library Verification":
				list = dcCombRepo.findVerifiedListJB();
				break;
			case "AC Combiner SLC Library Verification":
				list = slcRepo.findVerifiedList();
				break;
			case "Revenue or Performance Monitoring Meter Library Verification":
				list = lppamRepo.findVerifiedList();
				break;
			case "Battery Library Verification":
				list = batteryRepo.findVerifiedList();
				break;
			case "ATS Library Verification":
				list = atsRepo.findVerifiedList();
				break;
			case "Generator Library Verification":
				list = generatorRepo.findVerifiedList();
				break;
			case "Tilt Legs Library Verification":
				list = tiltRepo.findVerifiedList();
				break;
			case "Sub Panel Library Verification":
				list = panalRepo.findVerifiedList();
				break;

			default:
				break;
			}
			int rowCount = 0;
			for (EquipmentVerificationModel comp : list) {
				Row row = sheet.createRow(++rowCount);
				row.createCell(0).setCellValue(comp.getManufacturer());
				row.createCell(1).setCellValue(comp.getModel());
				row.createCell(2).setCellValue(comp.getAddedBy());
				if (comp.getAddedOn() != null) {
					row.createCell(3).setCellValue(dString.format(comp.getAddedOn()));
				}
				row.createCell(4).setCellValue(comp.getVerifiedBy());
				if (comp.getVerifiedOn() != null) {
					row.createCell(5).setCellValue(dString.format(comp.getVerifiedOn()));
				}
				row.createCell(6).setCellValue(comp.getFirstUpdater());
				row.createCell(7).setCellValue(comp.getSecondUpdater());
				row.createCell(8).setCellValue(comp.getThirdUpdater());

			}
			for (int i = 0; i < 9; i++) {
				sheet.autoSizeColumn(i);
			}

			String url = pathRepo.findFilePath() + "Equipment Verification Report/";
			if (!new File(url).exists()) {
				new File(url).mkdir();
			}

			String fileName = title + " " + dString.format(new Date()) + ".xls";
			String filePath = url + "/" + fileName;
			fileOut = new FileOutputStream(filePath);

			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			workbook.close();
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
			return new ResponseEntity<>(null, null, org.springframework.http.HttpStatus.NO_CONTENT);
		}

	}

}
