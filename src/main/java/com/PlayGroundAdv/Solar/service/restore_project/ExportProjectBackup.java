package com.PlayGroundAdv.Solar.service.restore_project;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.PathEntity;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitEnergyBatterySystemRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSConnectorsRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSInputsRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSSegmentsRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class ExportProjectBackup {

	@PersistenceContext
	EntityManager em;

	final CheckValueTypesService checkValue;
	final ESSConnectorsRepository essConnectorsRepo;
	final AuthenticationRepository userRepo;
	final PathRepository pathRepo;
	final PermitEnergyBatterySystemRepository energyBatterySystemRepo;
	final ESSSegmentsRepository essSegmentsRepo;
	final ESSInputsRepository essInputsRepo;

	public ExportProjectBackup(CheckValueTypesService checkValue,
			PermitEnergyBatterySystemRepository energyBatterySystemRepo, AuthenticationRepository userRepo,
			PathRepository pathRepo, ESSSegmentsRepository essSegmentsRepo, ESSConnectorsRepository essConnectorsRepo,
			ESSInputsRepository essInputsRepo) {
		super();
		this.checkValue = checkValue;
		this.energyBatterySystemRepo = energyBatterySystemRepo;
		this.userRepo = userRepo;
		this.pathRepo = pathRepo;
		this.essSegmentsRepo = essSegmentsRepo;
		this.essConnectorsRepo = essConnectorsRepo;
		this.essInputsRepo = essInputsRepo;
	}

	// A.B 03-25: CR-2478 export Project previous version
	public String generateSaveProjectScv(Long idProject, Long userID, String saveType) {

		TimeZone timeZone = TimeZone.getTimeZone("PST8PDT"); // e.g. "PST"
		TimeZone.setDefault(timeZone);
		Date saveDate = new Date();
		try {
			String sheetName = "";
			String time = "";

			if (saveDate != null && (saveDate + "").contains(":")) {
				time = (saveDate + "").replace(":", "-");

			}
			PathEntity path = pathRepo.findById(1L).orElse(null);

			File exportSaveFolder = new File(path.getDir() + "exportSave/" + idProject);
			if (!exportSaveFolder.exists()) {
				new File(path.getDir() + "exportSave/" + idProject).mkdirs();
			}
			File[] listOfFiles = exportSaveFolder.listFiles();
			String filePath = path.getDir() + "exportSave/" + idProject + "/" + saveType + '-' + userID + "-Version "
					+ (listOfFiles.length + 1) + " - " + time + ".xls";
			sheetName = saveType + '-' + userID + "-Version " + (listOfFiles.length + 1) + " - " + time;
			FileOutputStream fileOut;
			String[] dbList = { "public.permit_entity", "public.permit_home_site_info_entity",
					"public.permit_arrays_entity", "public.permit_project_site_info_entity",
					"public.permit_conduit_conductor_section_entity", "public.conduit_conductor_circuit_entity",
					"public.permit_layout_entity", "public.permit_drafter_data_entity", "public.permit_engineer_entity",
					"public.permit_additional_info_entity", "public.permit_adv_entity",
					"public.permit_company_info_entity", "public.permit_weather_entity",
					"public.permit_energy_battery_system" };
			String[] dbListDynamic = { "public.permit_sketch_entity", "public.permit_total_section_entity",
					"public.permit_customized_sheets_entity", "public.project_battery", "public.essconnectors",
					"public.essnodes", "public.esscircuit_spec", "public.essinputs", "public.esssegments" };
			HSSFWorkbook workbook = new HSSFWorkbook();

			HSSFCellStyle cellStyleOrange = workbook.createCellStyle();
			HSSFCellStyle cellStyleBlue = workbook.createCellStyle();
			cellStyleOrange.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
			cellStyleOrange.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			cellStyleBlue.setFillForegroundColor(HSSFColor.GOLD.index);
			cellStyleBlue.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			Font fontGras = workbook.createFont();
			fontGras.setBold(true);
			cellStyleOrange.setFont(fontGras);

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(path.getDbPath(), path.getDbName(), path.getDbPassword());
			for (int i = 0; i < dbList.length; i++) {

				String idPermit = checkValue.Equals(dbList[i], "public.permit_entity") ? "id" : "id_permit";
				String query = "select * from " + dbList[i] + " where " + idPermit + " = " + idProject;
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();

				HSSFSheet sheet = workbook.createSheet(dbList[i]);
				sheet.setColumnWidth(0, 10000);
				sheet.setColumnWidth(1, 2000);
				sheet.setColumnWidth(2, 10000);

				for (int j = 1; j <= columnsNumber; j++) {
					Row homeOwnerRow = sheet.createRow(j - 1);
					homeOwnerRow.createCell(0).setCellStyle(cellStyleOrange);
					homeOwnerRow.getCell(0).setCellValue(rsmd.getColumnName(j));
				}

				for (int j = 1; j <= columnsNumber; j++) {
					Row homeOwnerRowType = sheet.getRow(j - 1);
					homeOwnerRowType.createCell(1).setCellStyle(cellStyleBlue);
					homeOwnerRowType.getCell(1).setCellValue(rsmd.getColumnTypeName(j));
				}

				while (rs.next()) {
					for (int j = 1; j <= columnsNumber; j++) {
						Row homeOwnerRowValues = sheet.getRow(j - 1);
						homeOwnerRowValues.createCell(2).setCellValue(rs.getString(j) != null ? rs.getString(j) : "");
					}
				}
			}
			for (int i = 0; i < dbListDynamic.length; i++) {
				String idPermit = checkValue.Equals(dbListDynamic[i], "public.permit_customized_sheets_entity")
						? "project"
						: checkValue.Equals(dbListDynamic[i], "public.project_battery")
								|| checkValue.Equals(dbListDynamic[i], "public.essnodes") ? "project_id" : "id_permit";
				Long foreignKey = idProject;
				if (checkValue.Equals(dbListDynamic[i], "public.project_battery")) {
					foreignKey = energyBatterySystemRepo.findIdByProjectId(idProject);
				}
				String query = "select * from " + dbListDynamic[i] + " where " + idPermit + " = " + foreignKey;

				// A.B 09-08-2022 CR-1030 ESS Config
//				Select Connectors Spec
				if (checkValue.Equals(dbListDynamic[i], "public.esscircuit_spec")) {
					List<String> ids = essConnectorsRepo.findSpecIds(idProject);
					String joinedString = ids.stream().collect(Collectors.joining(", ", "(", ")"));
					query = "select * from " + dbListDynamic[i] + " where id IN " + joinedString;
				}
//				Select Connectors Segment
				if (checkValue.Equals(dbListDynamic[i], "public.esssegments")) {
					List<String> ids = essSegmentsRepo.findByProject(idProject);
					String joinedString = ids.stream().collect(Collectors.joining(", ", "(", ")"));
					query = "select * from " + dbListDynamic[i] + " where id IN " + joinedString;
				}
//				Select Connectors Inputs
				if (checkValue.Equals(dbListDynamic[i], "public.essinputs")) {
					List<String> ids = essInputsRepo.findByProject(idProject);
					String joinedString = ids.stream().collect(Collectors.joining(", ", "(", ")"));
					query = "select * from " + dbListDynamic[i] + " where id IN " + joinedString;
				}

				HSSFSheet sheet = workbook.createSheet(dbListDynamic[i]);
				sheet.setColumnWidth(0, 10000);
				sheet.setColumnWidth(1, 2000);
				sheet.setColumnWidth(2, 10000);
				if (!query.contains("IN ()")) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					int columnsNumber = rsmd.getColumnCount();

					for (int j = 1; j <= columnsNumber; j++) {
						Row homeOwnerRow = sheet.createRow(j - 1);
						homeOwnerRow.createCell(0).setCellStyle(cellStyleOrange);
						homeOwnerRow.getCell(0).setCellValue(rsmd.getColumnName(j));
					}

					for (int j = 1; j <= columnsNumber; j++) {
						Row homeOwnerRowType = sheet.getRow(j - 1);
						homeOwnerRowType.createCell(1).setCellStyle(cellStyleBlue);
						homeOwnerRowType.getCell(1).setCellValue(rsmd.getColumnTypeName(j));
					}
					int rowNumber = 0;
					while (rs.next()) {
						for (int j = 1; j <= columnsNumber; j++) {
							Row homeOwnerRowValues = sheet.getRow(j - 1);
							homeOwnerRowValues.createCell(2 + rowNumber)
									.setCellValue(rs.getString(j) != null ? rs.getString(j) : "");
						}
						rowNumber++;
					}
				}

			}

			fileOut = new FileOutputStream(filePath);
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			workbook.close();
			conn.close();
			timeZone = TimeZone.getTimeZone("PST8PDT");
			TimeZone.setDefault(timeZone);
			return sheetName.split("-")[0] + " - " + sheetName.split("-")[2] + " - " + sheetName.split("-")[3] + ':'
					+ sheetName.split("-")[4] + " - " + userRepo.getUserName(userID);
		} catch (Exception e) {
			e.printStackTrace();
			timeZone = TimeZone.getTimeZone("PST8PDT");
			TimeZone.setDefault(timeZone);
			return "";
		}

	}

}
