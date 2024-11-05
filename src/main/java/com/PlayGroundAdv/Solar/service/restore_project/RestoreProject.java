package com.PlayGroundAdv.Solar.service.restore_project;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitEnergyBatterySystemRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSConnectorsRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSInputsRepository;
import com.PlayGroundAdv.Solar.repositories.project.ess.ESSSegmentsRepository;
import com.PlayGroundAdv.Solar.service.equipment_utils.GetComponentsIdUtils;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.project.GetProjectByIdService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class RestoreProject {

	@PersistenceContext
	EntityManager em;

	final CheckValueTypesService checkValue;
	final GetProjectByIdService getProjectById;
	final HistoryActivityService historicActivity;
	final RestoreProjectEquipmentValue restoreProjectEquip;
	final GetComponentsIdUtils getComponentsId;
	final PermitEnergyBatterySystemRepository energyBatterySystemRepo;
	final ESSConnectorsRepository essConnectorsRepo;
	final ESSSegmentsRepository essSegmentsRepo;
	final ESSInputsRepository essInputsRepo;
	final PathRepository pathRepo;

	public RestoreProject(CheckValueTypesService checkValue, GetProjectByIdService getProjectById,
			HistoryActivityService historicActivity, RestoreProjectEquipmentValue restoreProjectEquip,
			GetComponentsIdUtils getComponentsId, PermitEnergyBatterySystemRepository energyBatterySystemRepo,
			ESSConnectorsRepository essConnectorsRepo, ESSSegmentsRepository essSegmentsRepo,
			ESSInputsRepository essInputsRepo, PathRepository pathRepo) {
		super();
		this.checkValue = checkValue;
		this.getProjectById = getProjectById;
		this.historicActivity = historicActivity;
		this.restoreProjectEquip = restoreProjectEquip;
		this.getComponentsId = getComponentsId;
		this.energyBatterySystemRepo = energyBatterySystemRepo;
		this.essConnectorsRepo = essConnectorsRepo;
		this.essSegmentsRepo = essSegmentsRepo;
		this.essInputsRepo = essInputsRepo;
		this.pathRepo = pathRepo;
	}

	// A.B 03-27: CR-2478 import Project previous version
	public GetPermitByIdResult restoreProjectRevision(Long idProject, String version, Long user, String timeZone,
			String ip) {

		try {
			String path = pathRepo.findFilePath();
			File exportSaveFolder = null;
			exportSaveFolder = new File(path + "exportSave/" + idProject);
			File[] listOfFiles = exportSaveFolder.listFiles();
			String sheetName = "";
			int k = 0;
			while (checkValue.Equals(sheetName, "") && k < listOfFiles.length) {
				sheetName = checkValue.contains(listOfFiles[k].getName(), version) ? listOfFiles[k].getName() : "";
				k++;
			}
			String filePath = path + "exportSave/" + idProject + "/" + sheetName;

			String[] dbList = { "public.permit_entity", "public.permit_home_site_info_entity",
					"public.permit_arrays_entity", "public.permit_project_site_info_entity",
					"public.permit_conduit_conductor_section_entity", "public.conduit_conductor_circuit_entity",
					"public.permit_layout_entity", "public.permit_drafter_data_entity", "public.permit_engineer_entity",
					"public.permit_additional_info_entity", "public.permit_adv_entity",
					"public.permit_company_info_entity", "public.permit_weather_entity",
					"public.permit_energy_battery_system", "public.project_files" };
			String[] dbListDynamic = { "public.permit_sketch_entity", "public.permit_total_section_entity",
					"public.permit_customized_sheets_entity", "public.project_battery", "public.esssegments",
					"public.esscircuit_spec", "public.essconnectors", "public.essnodes", "public.essinputs" };

			FileInputStream inputStream = new FileInputStream(new File(filePath));
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

			String pvModule = null;
			String firstInverter = null;
			String secondInverter = null;
			String thirdInverter = null;
			String fourthInverter = null;
			String fifthInverter = null;

			Boolean pvModuleRecovered = false;
			Boolean firstInverterRecovered = false;
			Boolean secondInverterRecovered = false;
			Boolean thirdInverterRecovered = false;
			Boolean fourthInverterRecovered = false;
			Boolean fifthInverterRecovered = false;

			for (int i = 0; i < dbListDynamic.length; i++) {
				String sName = dbListDynamic[i].length() <= 31 ? dbListDynamic[i]
						: dbListDynamic[i].substring(0, Math.min(dbListDynamic[i].length(), 31));
				HSSFSheet sheet = workbook.getSheet(sName);
				if (sheet != null) {
					String idPermit = checkValue.Equals(dbListDynamic[i], "public.permit_customized_sheets_entity")
							? "project"
							: checkValue.Equals(dbListDynamic[i], "public.project_battery")
									|| checkValue.Equals(dbListDynamic[i], "public.essnodes") ? "project_id"
											: "id_permit";
					Long foreignKey = idProject;
					if (checkValue.Equals(dbListDynamic[i], "public.project_battery")) {
						foreignKey = energyBatterySystemRepo.findIdByProjectId(idProject);
					}
					// A.B 09-08-2022 CR-1030 ESS Config
//					Select Connectors Spec
					if (checkValue.Equals(dbListDynamic[i], "public.esscircuit_spec")) {
						List<String> ids = essConnectorsRepo.findSpecIds(idProject);
						String joinedString = ids.stream().collect(Collectors.joining(", ", "(", ")"));
						String builderRemove = "DELETE from " + dbListDynamic[i] + " where id IN " + joinedString;
						if (!builderRemove.contains("IN ()")) {
							String connectorRemove = "DELETE FROM public.essconnectors WHERE circuit_spec_id IN "
									+ joinedString + "";
							Query qRemove = em.createNativeQuery(connectorRemove);
							qRemove.executeUpdate();

							qRemove = em.createNativeQuery(builderRemove);
							qRemove.executeUpdate();
						}
					} else
//					Select Connectors Segment
					if (checkValue.Equals(dbListDynamic[i], "public.esssegments")) {
						List<String> ids = essSegmentsRepo.findByProject(idProject);
						String joinedString = ids.stream().collect(Collectors.joining(", ", "(", ")"));
						String builderRemove = "DELETE from " + dbListDynamic[i] + " where id IN " + joinedString;
						if (!builderRemove.contains("IN ()")) {
							Query qRemove = em.createNativeQuery(builderRemove);
							qRemove.executeUpdate();
						}
					} else
//					Select Connectors Inputs
					if (checkValue.Equals(dbListDynamic[i], "public.essinputs")) {
						List<String> ids = essInputsRepo.findByProject(idProject);
						String joinedString = ids.stream().collect(Collectors.joining(", ", "(", ")"));
						String builderRemove = "DELETE from " + dbListDynamic[i] + " where id IN " + joinedString;
						if (!builderRemove.contains("IN ()")) {
							Query qRemove = em.createNativeQuery(builderRemove);
							qRemove.executeUpdate();
						}
					} else if (foreignKey != null) {
						String builderRemove = "DELETE FROM " + dbListDynamic[i] + " WHERE  " + idPermit + " = "
								+ foreignKey;
						Query qRemove = em.createNativeQuery(builderRemove);
						qRemove.executeUpdate();
					}

					int columnsNumber = sheet.getLastRowNum() + 1;
					int columnsNum = 2;

					do {
						StringBuilder builderS = new StringBuilder("INSERT INTO " + dbListDynamic[i] + " ( ");
						for (int j = 0; j < columnsNumber && sheet.getRow(j) != null; j++) {
							Row rowValues = sheet.getRow(j);
							String comma = j == columnsNumber - 1 ? " " : ", ";
							builderS.append(" " + rowValues.getCell(0) + comma);
						}
						builderS.append(" ) VALUES ( ");
						for (int j = 0; j < columnsNumber && sheet.getRow(j) != null; j++) {
							Row rowValues = sheet.getRow(j);
							String comma = j == columnsNumber - 1 ? " " : ", ";
							String value = null;

							if ((checkValue.Equals(rowValues.getCell(1).getStringCellValue(), "timestamp")
									|| checkValue.Equals(rowValues.getCell(1).getStringCellValue(), "varchar"))
									&& rowValues.getCell(columnsNum) != null
									&& !rowValues.getCell(columnsNum).getStringCellValue().equals("")) {
								value = " '" + rowValues.getCell(columnsNum).getStringCellValue() + "'";
								builderS.append(" " + value + comma);
							} else if (checkValue.Equals(rowValues.getCell(1).getStringCellValue(), "bool")
									&& rowValues.getCell(columnsNum) != null
									&& !rowValues.getCell(columnsNum).getStringCellValue().equals("")) {
								value = rowValues.getCell(columnsNum).getStringCellValue().equals("t") ? " true"
										: " false";
								builderS.append(" " + value + comma);
							} else if (rowValues.getCell(columnsNum) != null
									&& !rowValues.getCell(columnsNum).getStringCellValue().equals("")) {
								value = rowValues.getCell(columnsNum).getStringCellValue();
								builderS.append(" " + value + comma);
							} else {
								builderS.append(" " + "null" + comma);
							}

						}
						builderS.append(" ) ;");
						if (builderS != null && !builderS.toString().contains("VALUES (  null,")
								&& !builderS.toString().contains("VALUES (  )")) {
							Query q = em.createNativeQuery(builderS.toString());
							q.executeUpdate();
						}

						columnsNum++;
					} while (sheet.getRow(0) != null && sheet.getRow(0).getCell(columnsNum) != null);
				}

			}

			for (int i = 0; i < dbList.length; i++) {
				String sName = dbList[i].length() <= 31 ? dbList[i]
						: dbList[i].substring(0, Math.min(dbList[i].length(), 31));
				HSSFSheet sheet = workbook.getSheet(sName);
				if (sheet != null) {
					String idPermit = checkValue.Equals(dbList[i], "public.permit_entity") ? "id" : "id_permit";
					StringBuilder builderS = new StringBuilder("UPDATE " + dbList[i] + " SET ");
					int columnsNumber = sheet.getLastRowNum() + 1;

					for (int j = 0; j < columnsNumber; j++) {
						Row rowValues = sheet.getRow(j);

						String comma = j == columnsNumber - 1 ? " " : ", ";
						String value = null;
						if (checkValue.Equals(dbList[i], "public.permit_arrays_entity")) {

							if (rowValues.getCell(0).getStringCellValue().equals("pv_module_model")
									&& checkValue.contains(rowValues.getCell(2).getStringCellValue(), ":")) {
								pvModule = rowValues.getCell(2).getStringCellValue();
							} else if (rowValues.getCell(0).getStringCellValue().equals("pv_module"))
								pvModuleRecovered = true;

							if (rowValues.getCell(0).getStringCellValue().equals("invert_model")
									&& checkValue.contains(rowValues.getCell(2).getStringCellValue(), ":")) {
								firstInverter = rowValues.getCell(2).getStringCellValue();
							} else if (rowValues.getCell(0).getStringCellValue().equals("first_inverter"))
								firstInverterRecovered = true;

							if (rowValues.getCell(0).getStringCellValue().equals("second_invert_model")
									&& checkValue.contains(rowValues.getCell(2).getStringCellValue(), ":")) {
								secondInverter = rowValues.getCell(2).getStringCellValue();
							} else if (rowValues.getCell(0).getStringCellValue().equals("second_inverter"))
								secondInverterRecovered = true;

							if (rowValues.getCell(0).getStringCellValue().equals("third_inverter_model")
									&& checkValue.contains(rowValues.getCell(2).getStringCellValue(), ":")) {
								thirdInverter = rowValues.getCell(2).getStringCellValue();
							} else if (rowValues.getCell(0).getStringCellValue().equals("third_inverter"))
								thirdInverterRecovered = true;

							if (rowValues.getCell(0).getStringCellValue().equals("fourth_inverter_model")
									&& checkValue.contains(rowValues.getCell(2).getStringCellValue(), ":")) {
								fourthInverter = rowValues.getCell(2).getStringCellValue();
							} else if (rowValues.getCell(0).getStringCellValue().equals("fourth_inverter"))
								fourthInverterRecovered = true;

							if (rowValues.getCell(0).getStringCellValue().equals("fifth_inverter_model")
									&& checkValue.contains(rowValues.getCell(2).getStringCellValue(), ":")) {
								fifthInverter = rowValues.getCell(2).getStringCellValue();
							} else if (rowValues.getCell(0).getStringCellValue().equals("fifth_inverter"))
								fifthInverterRecovered = true;

						}

						if (rowValues.getCell(0).getStringCellValue().equals("system_optimizer_model")
								&& checkValue.Equals(rowValues.getCell(1).getStringCellValue(), "varchar")) {

							if (rowValues.getCell(2).getStringCellValue() != null
									&& !checkValue.isNumeric(rowValues.getCell(2).getStringCellValue())) {
								// A.B 02-25 Set New Value
								Long id = getComponentsId.testDCOptimizer(rowValues.getCell(2).getStringCellValue()); // component
																														// Id
								value = " = " + id;
								builderS.append(" " + rowValues.getCell(0) + value + comma);
							}

						} else if (rowValues.getCell(0).getStringCellValue().equals("disconnect_model_two")
								&& checkValue.Equals(rowValues.getCell(1).getStringCellValue(), "varchar")) {

							if (rowValues.getCell(2).getStringCellValue() != null
									&& !checkValue.isNumeric(rowValues.getCell(2).getStringCellValue())) {
								// A.B 02-25 Set New Value
								Long id = getComponentsId.testDcCombiner(rowValues.getCell(2).getStringCellValue()); // component
																														// Id
								value = " = " + id;
								builderS.append(" " + rowValues.getCell(0) + value + comma);
							}

						} else if (rowValues.getCell(0).getStringCellValue().equals("disconnect_model_three")
								&& checkValue.Equals(rowValues.getCell(1).getStringCellValue(), "varchar")) {

							if (rowValues.getCell(2).getStringCellValue() != null
									&& !checkValue.isNumeric(rowValues.getCell(2).getStringCellValue())) {
								// A.B 02-25 Set New Value
								Long id = getComponentsId.testDcCombiner(rowValues.getCell(2).getStringCellValue()); // component
																														// Id
								value = " = " + id;
								builderS.append(" " + rowValues.getCell(0) + value + comma);
							}

						} else if (rowValues.getCell(0).getStringCellValue().equals("roof_top_dc_combiner")
								&& checkValue.Equals(rowValues.getCell(1).getStringCellValue(), "varchar")) {

							if (rowValues.getCell(2).getStringCellValue() != null
									&& !checkValue.isNumeric(rowValues.getCell(2).getStringCellValue())) {
								// A.B 02-25 Set New Value
								Long id = getComponentsId.testDcCombiner(rowValues.getCell(2).getStringCellValue()); // component
																														// Id
								value = " = " + id;
								builderS.append(" " + rowValues.getCell(0) + value + comma);
							}

						} else if (rowValues.getCell(0).getStringCellValue().equals("roof_top_dc_disco")
								&& checkValue.Equals(rowValues.getCell(1).getStringCellValue(), "varchar")) {

							if (rowValues.getCell(2).getStringCellValue() != null
									&& !checkValue.isNumeric(rowValues.getCell(2).getStringCellValue())) {
								// A.B 02-25 Set New Value
								Long id = getComponentsId.testDcCombiner(rowValues.getCell(2).getStringCellValue()); // component
																														// Id
								value = " = " + id;
								builderS.append(" " + rowValues.getCell(0) + value + comma);
							}

						} else if (rowValues.getCell(0).getStringCellValue().equals("dccombiner_disconnect_entity")
								&& checkValue.Equals(rowValues.getCell(1).getStringCellValue(), "varchar")) {

							if (rowValues.getCell(2).getStringCellValue() != null
									&& !checkValue.isNumeric(rowValues.getCell(2).getStringCellValue())) {
								// A.B 02-25 Set New Value
								Long id = getComponentsId.testDcCombiner(rowValues.getCell(2).getStringCellValue()); // component
																														// Id
								value = " = " + id;
								builderS.append(" " + rowValues.getCell(0) + value + comma);
							}

						} else if (rowValues.getCell(0).getStringCellValue().equals("roof_top_ac_combiner")
								&& checkValue.Equals(rowValues.getCell(1).getStringCellValue(), "varchar")) {

							if (rowValues.getCell(2).getStringCellValue() != null
									&& !checkValue.isNumeric(rowValues.getCell(2).getStringCellValue())) {
								// A.B 02-25 Set New Value
								String id = rowValues.getCell(2).getStringCellValue().split(":")[0]; // component Id
								value = " = " + id;
								builderS.append(" " + rowValues.getCell(0) + value + comma);
							}

						} else if (rowValues.getCell(0).getStringCellValue().equals("ac_combiner_installed")
								&& checkValue.Equals(rowValues.getCell(1).getStringCellValue(), "varchar")) {

							if (rowValues.getCell(2).getStringCellValue() != null
									&& !checkValue.isNumeric(rowValues.getCell(2).getStringCellValue())) {
								// A.B 02-25 Set New Value
								String id = rowValues.getCell(2).getStringCellValue().split(":")[0]; // component Id
								value = " = " + id;
								builderS.append(" " + rowValues.getCell(0) + value + comma);
							}

						} else if (rowValues.getCell(0).getStringCellValue()
								.equals("ground_level_ac_combiner_box_model")
								&& checkValue.Equals(rowValues.getCell(1).getStringCellValue(), "varchar")) {

							if (rowValues.getCell(2).getStringCellValue() != null
									&& !checkValue.isNumeric(rowValues.getCell(2).getStringCellValue())) {
								// A.B 02-25 Set New Value
								String id = rowValues.getCell(2).getStringCellValue().split(":")[0]; // component Id
								value = " = " + id;
								builderS.append(" " + rowValues.getCell(0) + value + comma);
							}

						}

						else if (!rowValues.getCell(0).getStringCellValue().equals("id")
								&& !rowValues.getCell(0).getStringCellValue().equals("opened_by")
								&& !rowValues.getCell(0).getStringCellValue().equals("opened")
								&& !rowValues.getCell(0).getStringCellValue().equals("has_edit_request")
								&& !(checkValue.Equals(dbList[i], "public.permit_entity")
										&& (checkValue.contains(rowValues.getCell(0) + "", "upload_comments")
												|| checkValue.contains(rowValues.getCell(0) + "", "file")))) {
							if ((checkValue.Equals(rowValues.getCell(1).getStringCellValue(), "timestamp")
									|| checkValue.Equals(rowValues.getCell(1).getStringCellValue(), "varchar"))
									&& rowValues.getCell(2) != null
									&& !rowValues.getCell(2).getStringCellValue().equals("")) {
								value = " = '" + rowValues.getCell(2).getStringCellValue() + "'";
								builderS.append(" " + rowValues.getCell(0) + value + comma);
							} else if (checkValue.Equals(rowValues.getCell(1).getStringCellValue(), "bool")
									&& rowValues.getCell(2) != null
									&& !rowValues.getCell(2).getStringCellValue().equals("")) {
								value = rowValues.getCell(2).getStringCellValue().equals("t") ? " = true" : " = false";
								builderS.append(" " + rowValues.getCell(0) + value + comma);
							} else if (rowValues.getCell(2) != null
									&& !rowValues.getCell(2).getStringCellValue().equals("")) {
								value = " = " + rowValues.getCell(2).getStringCellValue();
								builderS.append(" " + rowValues.getCell(0) + value + comma);
							} else {
								builderS.append(" " + rowValues.getCell(0) + " = null" + comma);
							}
						}
					}

					builderS.append(" WHERE " + idPermit + " = " + idProject + " ;");

					String builderString = builderS.toString();
					builderString = builderString.replace(",  WHERE", " WHERE");
					Query q = em.createNativeQuery(builderString);
					q.executeUpdate();
				}

			}

			// end Restore dynamic drafter data
			workbook.close();

			TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
			Calendar calSaveP = Calendar.getInstance(); // creates calendar
			calSaveP.setTime(new Date());

			// A.B Update Old Projects PV Module and Inverter dropdowns

			if (pvModule != null && !pvModuleRecovered) {
				restoreProjectEquip.checkPVModuleValue(idProject, pvModule);
			}
			if (firstInverter != null && !firstInverterRecovered) {
				restoreProjectEquip.checkFirstInverterValue(idProject, firstInverter);
			}
			if (secondInverter != null && !secondInverterRecovered) {
				restoreProjectEquip.checkSecondInverterValue(idProject, secondInverter);
			}
			if (thirdInverter != null && !thirdInverterRecovered) {
				restoreProjectEquip.checkThirdInverterValue(idProject, thirdInverter);
			}
			if (fourthInverter != null && !fourthInverterRecovered) {
				restoreProjectEquip.checkFourthInverterValue(idProject, fourthInverter);
			}
			if (fifthInverter != null && !fifthInverterRecovered) {
				restoreProjectEquip.checkFifthInverterValue(idProject, fifthInverter);
			}

			historicActivity.recordActivity(user, ip, timeZone,
					"Restore the " + version + ";" + idProject + ";Edit success", true, "", "", "");
			return getProjectById.getProjectById(idProject, user, timeZone, "", "", "", ip, false, false);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
