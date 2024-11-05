package com.PlayGroundAdv.Solar.service.generate_planset.rsheets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.Flashing;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.ProjectBattery;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.entity.RsheetsLibraryEntity;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import com.PlayGroundAdv.Solar.model.ahj_library.GoverningCodesModel;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitEnergyBatterySystemRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.itextpdf.text.pdf.PdfReader;

@Service
public class MergeRsheets {

	final RsheetsMapping rSheetsMapping;
	final CheckValueTypesService checkValue;
	final PathRepository pathRepository;
	final PermitEnergyBatterySystemRepository energyBatterySystemRepo;

	public MergeRsheets(RsheetsMapping rSheetsMapping, CheckValueTypesService checkValue, PathRepository pathRepository,
			PermitEnergyBatterySystemRepository energyBatterySystemRepo) {
		super();
		this.rSheetsMapping = rSheetsMapping;
		this.checkValue = checkValue;
		this.pathRepository = pathRepository;
		this.energyBatterySystemRepo = energyBatterySystemRepo;
	}

	public PDFMergerUtility generateRsheets(PDFMergerUtility ut, Cmodulev2 moduleInfo, List<Inverters> inverters,
			DCOptimizerEntity dcOptimizer, Inverters microInverterInfo, RailRacking railRacking,
			RailRacking railRackingGround, RailRacking railRackingPatio, RailRacking railRackingCarport,
			RoofAttachmentsEntity stanchionFoot, Flashing flashing, DCCombinerDisconnectEntity dcCombinerDisconnect,
			DCCombinerDisconnectEntity seconddcCombinerDisconnect, DCCombinerDisconnectEntity thirddcCombinerDisconnect,
			DCCombinerDisconnectEntity roofTopDCDisconnect, DCCombinerDisconnectEntity jbox,
			ACCombinerSLC slcAcCombiner, ACCombinerSLC acCombiner, ACDisconnect acDisconnect,
			ACDisconnect additionalAcDisconnect, ACDisconnect secondacDisconnect,
			PermitHomeSiteInfoEntity permitHomeSite, Long idPermit, PermitEntity permitEntity,
			PermitAdvEntityResult advInputs, AuthentificationEntity userConnectedEntity, Long idUser, Long submitId,
			String projectInverterTechnology, Boolean isGroundMounted, Boolean isPatioMounted, Boolean isCarport,
			PermitEnergyBatterySystem permitEnergyBatterySystem, String asceStandard, Boolean hasBattery,
			GoverningCodesModel governingCodes) {
		try {
			Integer index = 0;
			///// --------------- R-sheet Module -------------/////
			File fileE300Module = rSheetsMapping.rSheetModule(moduleInfo, permitHomeSite, idPermit, permitEntity,
					userConnectedEntity, idUser, submitId, index);
			if (fileE300Module != null && fileE300Module.exists()) {
				index = index + getNumberOfPage(fileE300Module.getAbsolutePath());
				ut.addSource(fileE300Module);
			}
			///// --------------- R-sheet Inverter -------------/////
			if ((checkValue.Equals(projectInverterTechnology, "Neither")
					|| checkValue.Equals(projectInverterTechnology, "System Optimizer"))) {
				for (int i = 0; i < inverters.size(); i++) {
					File fileE300Inv = null;
					if (Boolean.TRUE.equals(inverters.get(i).getIntegratedACDisco())) {
						fileE300Inv = rSheetsMapping.rSheetInverterIntegACD(inverters.get(i), permitHomeSite, idPermit,
								permitEntity, userConnectedEntity, idUser, submitId, index, i);
					} else {
						fileE300Inv = rSheetsMapping.rSheetInverter(inverters.get(i), permitHomeSite, idPermit,
								permitEntity, userConnectedEntity, idUser, submitId, index, i);
					}
					if (fileE300Inv != null && fileE300Inv.exists()) {
						index = index + getNumberOfPage(fileE300Inv.getAbsolutePath());
						ut.addSource(fileE300Inv);
					}
				}

				///// --------------- R-sheet Optimizer -------------/////

				File fileE300Opti = rSheetsMapping.rSheetOptimizer(dcOptimizer, permitHomeSite, idPermit, permitEntity,
						userConnectedEntity, idUser, submitId, index);
				if (fileE300Opti != null && fileE300Opti.exists()) {
					index = index + getNumberOfPage(fileE300Opti.getAbsolutePath());
					ut.addSource(fileE300Opti);
				}

				///// --------------- R-sheet Micro Inverter Inverter -------------/////
			} else if (checkValue.Equals(projectInverterTechnology, "Micro Inverter")) {
				File fileE300MicroInverter = rSheetsMapping.rSheetMicroInverter(microInverterInfo, permitHomeSite,
						idPermit, permitEntity, userConnectedEntity, idUser, submitId, index);
				if (fileE300MicroInverter != null && fileE300MicroInverter.exists()) {
					index = index + getNumberOfPage(fileE300MicroInverter.getAbsolutePath());
					ut.addSource(fileE300MicroInverter);
				}
			}

			///// --------------- R-sheet Battery -------------/////
			if (Boolean.TRUE.equals(hasBattery)) {
				PermitEnergyBatterySystem energyBatterySystem = energyBatterySystemRepo.findByProjectId(idPermit);
				if (energyBatterySystem.getBatteries() != null && !energyBatterySystem.getBatteries().isEmpty()) {
					List<ProjectBattery> batteries = energyBatterySystem.getBatteries();
					for (int i = 0; i < batteries.size(); i++) {
						File fileE300Battery = rSheetsMapping.rSheetBattery(batteries.get(i).getBatteryId(),
								permitHomeSite, idPermit, permitEntity, userConnectedEntity, idUser, submitId, index,
								i);
						if (fileE300Battery != null && fileE300Battery.exists()) {
							index = index + getNumberOfPage(fileE300Battery.getAbsolutePath());
							ut.addSource(fileE300Battery);
						}
					}
				}

			}

			///// --------------- R-sheet Racking & Rail -------------/////
			File fileE300RoofRack = rSheetsMapping.rsheetRailRacking(railRacking, permitHomeSite, idPermit,
					permitEntity, userConnectedEntity, idUser, submitId, index, asceStandard, governingCodes, "Roof");
			if (fileE300RoofRack != null && fileE300RoofRack.exists()) {
				index = index + getNumberOfPage(fileE300RoofRack.getAbsolutePath());
				ut.addSource(fileE300RoofRack);
			}

			//// --------------- R-sheet Ground Racking & Rail -------------/////
			Boolean groundRsheetexist = false;
			if (Boolean.TRUE.equals(isGroundMounted) && railRackingGround != null) {
				File fileE300GroundRack = rSheetsMapping.groundRsheetMapping(railRackingGround, advInputs,
						permitHomeSite, idPermit, permitEntity, userConnectedEntity, idUser, submitId, index);
				if (fileE300GroundRack != null && fileE300GroundRack.exists()) {
					groundRsheetexist = true;
					index = index + getNumberOfPage(fileE300GroundRack.getAbsolutePath());
					ut.addSource(fileE300GroundRack);
				}
			}

			//// --------------- R-sheet Patio Racking & Rail -------------/////
			if (Boolean.TRUE.equals(isPatioMounted)) {
				File fileE300PatioRack = rSheetsMapping.rsheetRailRacking(railRackingPatio, permitHomeSite, idPermit,
						permitEntity, userConnectedEntity, idUser, submitId, index, asceStandard, governingCodes,
						"Patio");
				if (fileE300PatioRack != null && fileE300PatioRack.exists()) {
					index = index + getNumberOfPage(fileE300PatioRack.getAbsolutePath());
					ut.addSource(fileE300PatioRack);
				}
			}

			//// --------------- R-sheet Carport Racking & Rail -------------/////
			if (Boolean.TRUE.equals(isCarport)) {
				File fileE300CarportRack = rSheetsMapping.rsheetRailRacking(railRackingCarport, permitHomeSite,
						idPermit, permitEntity, userConnectedEntity, idUser, submitId, index, asceStandard,
						governingCodes, "Carport");
				if (fileE300CarportRack != null && fileE300CarportRack.exists()) {
					index = index + getNumberOfPage(fileE300CarportRack.getAbsolutePath());
					ut.addSource(fileE300CarportRack);
				}
			}

			///// --------------- R-sheet Roof Attachments -------------/////
			File fileE300RoofAttachement = rSheetsMapping.stanchionRsheet(stanchionFoot, permitHomeSite, idPermit,
					permitEntity, userConnectedEntity, idUser, submitId, index);
			if (fileE300RoofAttachement != null && fileE300RoofAttachement.exists()) {
				index = index + getNumberOfPage(fileE300RoofAttachement.getAbsolutePath());
				ut.addSource(fileE300RoofAttachement);
			}

			///// --------------- R-sheet Flashing -------------/////
			File fileE300Flashing = rSheetsMapping.flashingRsheet(flashing, permitHomeSite, idPermit, permitEntity,
					userConnectedEntity, idUser, submitId, index);
			if (fileE300Flashing != null && fileE300Flashing.exists()) {
				index = index + getNumberOfPage(fileE300Flashing.getAbsolutePath());
				ut.addSource(fileE300Flashing);
			}

			///// --------------- R-sheet DC Combiner Disconnect -------------/////
			File fileE300DCCombinerDisconnect = rSheetsMapping.dccdRsheet(dcCombinerDisconnect, permitHomeSite,
					idPermit, permitEntity, userConnectedEntity, idUser, submitId, index, 1);
			if (fileE300DCCombinerDisconnect != null && fileE300DCCombinerDisconnect.exists()) {
				index = index + getNumberOfPage(fileE300DCCombinerDisconnect.getAbsolutePath());
				ut.addSource(fileE300DCCombinerDisconnect);
			}
			if (dcCombinerDisconnect != seconddcCombinerDisconnect) {
				File fileE300DCCombiner = rSheetsMapping.dccdRsheet(seconddcCombinerDisconnect, permitHomeSite,
						idPermit, permitEntity, userConnectedEntity, idUser, submitId, index, 2);
				if (fileE300DCCombiner != null && fileE300DCCombiner.exists()) {
					index = index + getNumberOfPage(fileE300DCCombiner.getAbsolutePath());
					ut.addSource(fileE300DCCombiner);
				}
			}
			if (thirddcCombinerDisconnect != dcCombinerDisconnect
					&& thirddcCombinerDisconnect != seconddcCombinerDisconnect) {
				File fileE300DCDisconnect = rSheetsMapping.dccdRsheet(thirddcCombinerDisconnect, permitHomeSite,
						idPermit, permitEntity, userConnectedEntity, idUser, submitId, index, 3);
				if (fileE300DCDisconnect != null && fileE300DCDisconnect.exists()) {
					index = index + getNumberOfPage(fileE300DCDisconnect.getAbsolutePath());
					ut.addSource(fileE300DCDisconnect);
				}
			}
			if (roofTopDCDisconnect != dcCombinerDisconnect
					&& roofTopDCDisconnect != seconddcCombinerDisconnect && roofTopDCDisconnect != thirddcCombinerDisconnect) {
				File fileE300DCDisconnect = rSheetsMapping.dccdRsheet(roofTopDCDisconnect, permitHomeSite,
						idPermit, permitEntity, userConnectedEntity, idUser, submitId, index, 3);
				if (fileE300DCDisconnect != null && fileE300DCDisconnect.exists()) {
					index = index + getNumberOfPage(fileE300DCDisconnect.getAbsolutePath());
					ut.addSource(fileE300DCDisconnect);
				}
			}
			

			///// --------------- R-sheet jbox -------------/////
			File fileE300JBoxRsheets = rSheetsMapping.jBoxRsheets(jbox, permitHomeSite, idPermit, permitEntity,
					userConnectedEntity, idUser, submitId, index);
			if (fileE300JBoxRsheets != null && fileE300JBoxRsheets.exists()) {
				index = index + getNumberOfPage(fileE300JBoxRsheets.getAbsolutePath());
				ut.addSource(fileE300JBoxRsheets);
			}

			// R-sheet AC Combiner (Load Centers)/Disconnects
			File fileE300AccRsheets = rSheetsMapping.accRsheets(slcAcCombiner, permitHomeSite, idPermit, permitEntity,
					userConnectedEntity, idUser, submitId, index);
			if (fileE300AccRsheets != null && fileE300AccRsheets.exists()) {
				index = index + getNumberOfPage(fileE300AccRsheets.getAbsolutePath());
				ut.addSource(fileE300AccRsheets);
			}

			File fileE300AccdRsheets = rSheetsMapping.accdRsheets(acCombiner, permitHomeSite, idPermit, permitEntity,
					userConnectedEntity, idUser, submitId, index);
			if (fileE300AccdRsheets != null && fileE300AccdRsheets.exists()) {
				index = index + getNumberOfPage(fileE300AccdRsheets.getAbsolutePath());
				ut.addSource(fileE300AccdRsheets);
			} else {
				fileE300AccdRsheets = rSheetsMapping.accRsheets(acCombiner, permitHomeSite, idPermit, permitEntity,
						userConnectedEntity, idUser, submitId, index);
				if (fileE300AccdRsheets != null && fileE300AccdRsheets.exists()) {
					index = index + getNumberOfPage(fileE300AccdRsheets.getAbsolutePath());
					ut.addSource(fileE300AccdRsheets);
				}
			}

			File fileE300AcdRsheets = rSheetsMapping.acdRsheets(acDisconnect, permitHomeSite, idPermit, permitEntity,
					userConnectedEntity, idUser, submitId, index, 1);
			if (fileE300AcdRsheets != null && fileE300AcdRsheets.exists()) {
				index = index + getNumberOfPage(fileE300AcdRsheets.getAbsolutePath());
				ut.addSource(fileE300AcdRsheets);
			}
			if (additionalAcDisconnect != acDisconnect) {
				File fileE300AcdInstalledRsheets = rSheetsMapping.acdRsheets(additionalAcDisconnect, permitHomeSite,
						idPermit, permitEntity, userConnectedEntity, idUser, submitId, index, 2);
				if (fileE300AcdInstalledRsheets != null && fileE300AcdInstalledRsheets.exists()) {
					index = index + getNumberOfPage(fileE300AcdInstalledRsheets.getAbsolutePath());
					ut.addSource(fileE300AcdInstalledRsheets);
				}
			}
			if (secondacDisconnect != acDisconnect && secondacDisconnect != additionalAcDisconnect) {
				File fileE300Acd2Rsheets = rSheetsMapping.acdRsheets(secondacDisconnect, permitHomeSite, idPermit,
						permitEntity, userConnectedEntity, idUser, submitId, index, 3);
				if (fileE300Acd2Rsheets != null && fileE300Acd2Rsheets.exists()) {
					index = index + getNumberOfPage(fileE300Acd2Rsheets.getAbsolutePath());
					ut.addSource(fileE300Acd2Rsheets);
				}
			}

			///// --------------- R-sheet ATS -------------/////
			if (permitEnergyBatterySystem.getIdAts() != null) {
				File fileE300Ats = rSheetsMapping.atsRsheets(permitEnergyBatterySystem.getIdAts(), permitHomeSite,
						idPermit, permitEntity, userConnectedEntity, idUser, submitId, index);
				if (fileE300Ats != null && fileE300Ats.exists()) {
					index = index + getNumberOfPage(fileE300Ats.getAbsolutePath());
					ut.addSource(fileE300Ats);
				}
			}

			///// --------------- R-sheet ATS -------------/////
			if (permitEnergyBatterySystem.getIdGenerator() != null) {
				File fileE300Generator = rSheetsMapping.GeneratorRsheets(permitEnergyBatterySystem.getIdGenerator(),
						permitHomeSite, idPermit, permitEntity, userConnectedEntity, idUser, submitId, index);
				if (fileE300Generator != null && fileE300Generator.exists()) {
					index = index + getNumberOfPage(fileE300Generator.getAbsolutePath());
					ut.addSource(fileE300Generator);
				}
			}

			String filespath = pathRepository.findFilePath();
			
			// A.Wifek CR-PM-3867
			for (int i = 1; i <= (Boolean.TRUE.equals(groundRsheetexist) || Boolean.FALSE.equals(isGroundMounted) || railRackingGround == null ? 5 : 10); i++) {
				File extrasheet = rSheetsMapping.rSheetGenerate(permitHomeSite, idPermit,
						permitEntity.getAuthentificationEntity().getId(), "", "", "extrasheet" + i, index, filespath);
				index = index + 1;
				ut.addSource(extrasheet);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ut;
	}

	public void deleteRsheets(Long idPermit) {
		try {
			List<String> fileName = Arrays.asList("PV Module", "Inverter-0", "Inverter-1", "Inverter-2", "Inverter-3",
					"Inverter-4", "Inverter W INTEGRATED AC DISCO", "Roof Rail Racking", "Patio Rail Racking",
					"Carport Rail Racking", "Groung Rail Racking", "DC DC CONVERTERS", "Roof Attachments",
					"DC COMBINER DISCONNECT - 1", "DC Combiner", "DC Disconnect - 1", "Rapid Shutdown - 1",
					"DC COMBINER DISCONNECT - 2", "DC Combiner", "DC Disconnect - 2", "Rapid Shutdown - 2",
					"DC COMBINER DISCONNECT - 3", "DC Combiner", "DC Disconnect - 3", "Rapid Shutdown - 3",
					"Junction Box", "Auto Transfer Switch", "Generator", "Micro Inverter", "AC Combiner",
					"AC Combiner Disconnect", "AC Disconnect - 1", "AC Disconnect - 2", "AC Disconnect - 3");
			for (int j = 0; j < fileName.size(); j++) {
				if (new File(
						Constants.rapportPlansetFolderUrl + "PDF-E300-" + fileName.get(j) + "-" + idPermit + ".pdf")
								.exists()) {
					cleanUp(Paths.get(Constants.rapportPlansetFolderUrl + "PDF-E300-" + fileName.get(j) + "-" + idPermit
							+ ".pdf"));
				}
			}
			int b = 0;
			while (new File(Constants.rapportPlansetFolderUrl + "PDF-E300-BATTERY #" + b + "-" + idPermit + ".pdf")
					.exists()) {
				cleanUp(Paths
						.get(Constants.rapportPlansetFolderUrl + "PDF-E300-BATTERY #" + b + "-" + idPermit + ".pdf"));
				b++;
			}

			for (int i = 1; i <= 10; i++) {
				if (new File(Constants.rapportPlansetFolderUrl + "PDF-E300-extrasheet" + i + "-" + idPermit + ".pdf")
						.exists()) {
					cleanUp(Paths.get(
							Constants.rapportPlansetFolderUrl + "PDF-E300-extrasheet" + i + "-" + idPermit + ".pdf"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cleanUp(Path path) throws IOException {
		Files.delete(path);
	}

	private int getNumberOfPage(String path) throws IOException {
		PdfReader reader = new PdfReader(path);
		int index = reader.getNumberOfPages();
		reader.close();
		return index;
	}
}
