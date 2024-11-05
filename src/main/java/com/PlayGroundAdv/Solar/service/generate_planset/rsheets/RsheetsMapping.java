package com.PlayGroundAdv.Solar.service.generate_planset.rsheets;

import java.io.File;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.Constants.Constants;
import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Battery;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.Flashing;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.entity.libraries.ATS;
import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.entity.libraries.Generator;
import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import com.PlayGroundAdv.Solar.model.ahj_library.GoverningCodesModel;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.sheets.RsheetsLibraryRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class RsheetsMapping {

	final static String R_SHEET_PATH = Constants.rapportRSheetFolderUrl + "ReferenceSheets/";

	final CheckValueTypesService checkValue;
	final PathRepository pathRepo;
	final PlanSetServiceE300 plansetServiceE300;
	final RsheetsLibraryRepository rSheetsLibraryRepo;
	final CreateGroundSheetName createGroundSheetName;
	final MissingSheetService missingSheetService;

	public RsheetsMapping(CheckValueTypesService checkValue, PathRepository pathRepo,
			PlanSetServiceE300 plansetServiceE300, MissingSheetService missingSheetService,
			RsheetsLibraryRepository rSheetsLibraryRepo, CreateGroundSheetName createGroundSheetName) {
		super();
		this.checkValue = checkValue;
		this.pathRepo = pathRepo;
		this.plansetServiceE300 = plansetServiceE300;
		this.missingSheetService = missingSheetService;
		this.rSheetsLibraryRepo = rSheetsLibraryRepo;
		this.createGroundSheetName = createGroundSheetName;
	}

	public File rSheetModule(Cmodulev2 moduleInfo, PermitHomeSiteInfoEntity permitHomeSite, Long idPermit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser, Long submitId,
			Integer index) {

		File rSheetFile = null;
		try {
			if (moduleInfo != null) {
				rSheetFile = rSheetMapping("MOD_", moduleInfo.getMake().trim(), moduleInfo.getModel().trim(),
						"PV MODULE CUT SHEET", "PV Module", permitHomeSite, idPermit, permitEntity, userConnectedEntity,
						idUser, submitId, index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File rSheetInverter(Inverters inverterInfo, PermitHomeSiteInfoEntity permitHomeSite, Long idPermit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser, Long submitId,
			Integer index, int indexInverter) {

		File rSheetFile = null;
		try {
			if (inverterInfo != null) {
				rSheetFile = rSheetMapping("INV_", inverterInfo.getMake().trim(), inverterInfo.getModel().trim(),
						"INVERTER CUT SHEET", "Inverter-" + indexInverter, permitHomeSite, idPermit, permitEntity,
						userConnectedEntity, idUser, submitId, index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File rSheetInverterIntegACD(Inverters inverterInfo, PermitHomeSiteInfoEntity permitHomeSite, Long idPermit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser, Long submitId,
			Integer index, int indexInverter) {

		File rSheetFile = null;
		try {
			if (inverterInfo != null) {
				rSheetFile = rSheetMapping("INV-ACD_", inverterInfo.getMake().trim(), inverterInfo.getModel().trim(),
						"INVERTER W INTEGRATED AC DISCO CUT SHEET", "Inverter W INTEGRATED AC DISCO", permitHomeSite,
						idPermit, permitEntity, userConnectedEntity, idUser, submitId, index);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File rSheetOptimizer(DCOptimizerEntity dcOptimizer, PermitHomeSiteInfoEntity permitHomeSite, Long idPermit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser, Long submitId,
			Integer index) {
		File rSheetFile = null;
		try {
			if (dcOptimizer != null) {
				rSheetFile = rSheetMapping("OPTI_", dcOptimizer.getManufacturer().trim(), dcOptimizer.getModel().trim(),
						"DC/DC CONVERTERS CUT SHEET", "DC DC CONVERTERS", permitHomeSite, idPermit, permitEntity,
						userConnectedEntity, idUser, submitId, index);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File rSheetBattery(Battery battery, PermitHomeSiteInfoEntity permitHomeSite, Long idPermit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser, Long submitId,
			Integer index, int batteryIndex) {
		File rSheetFile = null;
		try {
			if (battery != null) {
				if (battery.getEssEnergy() != null && Boolean.TRUE.equals(battery.getEssEnergy())) {
					rSheetFile = rSheetMapping("BAT_", battery.getManufacturer().trim(), battery.getModel().trim(),
							"ENERGY STORAGE SYSTEM CUT SHEET", "BATTERY #" + batteryIndex, permitHomeSite, idPermit,
							permitEntity, userConnectedEntity, idUser, submitId, index);
				} else {
					rSheetFile = rSheetMapping("BAT_", battery.getManufacturer().trim(), battery.getModel().trim(),
							"BATTERY CUT SHEET", "BATTERY #" + batteryIndex, permitHomeSite, idPermit, permitEntity,
							userConnectedEntity, idUser, submitId, index);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File rSheetMicroInverter(Inverters microInverterInfo, PermitHomeSiteInfoEntity permitHomeSite, Long idPermit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser, Long submitId,
			Integer index) {
		File rSheetFile = null;
		try {
			if (microInverterInfo != null) {
				rSheetFile = rSheetMapping("MINV_", microInverterInfo.getMake().trim(),
						microInverterInfo.getModel().trim(), "INVERTER CUT SHEET", "Micro Inverter", permitHomeSite,
						idPermit, permitEntity, userConnectedEntity, idUser, submitId, index);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File rsheetRailRacking(RailRacking railRacking, PermitHomeSiteInfoEntity permitHomeSite, Long idPermit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser, Long submitId,
			Integer index, String asceStandard, GoverningCodesModel governingCodes, String rackingType) {
		File rSheetFile = null;
		try {
			if (railRacking != null) {
				rSheetFile = rackingRSheetMapping("RACK_", railRacking.getManufacturer().trim(),
						railRacking.getModel().trim(), asceStandard, "RACKING & RAIL CUT SHEET",
						rackingType + " Rail Racking", permitHomeSite, idPermit, permitEntity, userConnectedEntity,
						idUser, submitId, index, governingCodes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File stanchionRsheet(RoofAttachmentsEntity stanchionFoot, PermitHomeSiteInfoEntity permitHomeSite,
			Long idPermit, PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser,
			Long submitId, Integer index) {
		File rSheetFile = null;
		try {
			if (stanchionFoot != null) {
				rSheetFile = rSheetMapping("FOOT_", stanchionFoot.getManufacturer().trim(),
						stanchionFoot.getModel().trim(), "STANCHION - ROOF ATTACHMENT CUT SHEET", "Roof Attachments",
						permitHomeSite, idPermit, permitEntity, userConnectedEntity, idUser, submitId, index);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File flashingRsheet(Flashing flashing, PermitHomeSiteInfoEntity permitHomeSite, Long idPermit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser, Long submitId,
			Integer index) {
		File rSheetFile = null;
		try {
			if (flashing != null) {
				rSheetFile = rSheetMapping("Flas_", flashing.getManufacturer().trim(), flashing.getModel().trim(),
						"STANCHION - ROOF ATTACHMENT CUT SHEET", "Roof Attachments", permitHomeSite, idPermit,
						permitEntity, userConnectedEntity, idUser, submitId, index);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File dccdRsheet(DCCombinerDisconnectEntity dcCombinerDisconnect, PermitHomeSiteInfoEntity permitHomeSite,
			Long idPermit, PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser,
			Long submitId, Integer index, Integer indexDcc) {
		File rSheetFile = null;
		try {
			if (dcCombinerDisconnect != null) {
				String tag = "DCCD_";
				String equipment = "DC Combiner Disconnect";
				String cutSheet = "DC COMBINER DISCONNECT CUT SHEET";
				if (checkValue.Equals(dcCombinerDisconnect.getTypeDc(), "DC Combiner")) {
					tag = "DCC_";
					equipment = "DC Combiner - " + indexDcc;
					cutSheet = "DC COMBINER CUT SHEET";
				} else if (checkValue.Equals(dcCombinerDisconnect.getTypeDc(), "DC Disconnect")) {
					tag = "DCD_";
					equipment = "DC Disconnect - " + indexDcc;
					cutSheet = "DC DISCONNECT CUT SHEET";
				} else if (checkValue.Equals(dcCombinerDisconnect.getTypeDc(), "Rapid Shutdown")) {
					tag = "RSD_";
					equipment = "Rapid Shutdown - " + indexDcc;
					cutSheet = "RAPID SHUTDOWN CUT SHEET";
				}
				rSheetFile = rSheetMapping(tag, dcCombinerDisconnect.getManufacturer().trim(),
						dcCombinerDisconnect.getModel().trim(), cutSheet, equipment, permitHomeSite, idPermit,
						permitEntity, userConnectedEntity, idUser, submitId, index);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File jBoxRsheets(DCCombinerDisconnectEntity jbox, PermitHomeSiteInfoEntity permitHomeSite, Long idPermit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser, Long submitId,
			Integer index) {
		File rSheetFile = null;
		try {
			if (jbox != null) {
				rSheetFile = rSheetMapping("JB_", jbox.getManufacturer().trim(), jbox.getModel().trim(),
						"JUNCTION BOX CUT SHEET", "Junction Box", permitHomeSite, idPermit, permitEntity,
						userConnectedEntity, idUser, submitId, index);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File accRsheets(ACCombinerSLC acCombiner, PermitHomeSiteInfoEntity permitHomeSite, Long idPermit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser, Long submitId,
			Integer index) {
		File rSheetFile = null;
		try {
			if (acCombiner != null) {
				rSheetFile = rSheetMapping("ACC_", acCombiner.getManufacturer().trim(), acCombiner.getModel().trim(),
						"AC COMBINER CUT SHEET", "AC Combiner", permitHomeSite, idPermit, permitEntity,
						userConnectedEntity, idUser, submitId, index);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File accdRsheets(ACCombinerSLC acCombiner, PermitHomeSiteInfoEntity permitHomeSite, Long idPermit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser, Long submitId,
			Integer index) {
		File rSheetFile = null;
		try {
			if (acCombiner != null) {
				rSheetFile = rSheetMapping("ACCD_", acCombiner.getManufacturer().trim(), acCombiner.getModel().trim(),
						"AC COMBINER DISCONNECT CUT SHEET", "AC Combiner Disconnect", permitHomeSite, idPermit,
						permitEntity, userConnectedEntity, idUser, submitId, index);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File acdRsheets(ACDisconnect acDisconnect, PermitHomeSiteInfoEntity permitHomeSite, Long idPermit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser, Long submitId,
			Integer index, Integer indexAcd) {
		File rSheetFile = null;
		try {
			if (acDisconnect != null) {
				rSheetFile = rSheetMapping("ACD_", acDisconnect.getManufacturer().trim(),
						acDisconnect.getModel().trim(), "AC DISCONNECT CUT SHEET", "AC Disconnect - " + indexAcd,
						permitHomeSite, idPermit, permitEntity, userConnectedEntity, idUser, submitId, index);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File atsRsheets(ATS ats, PermitHomeSiteInfoEntity permitHomeSite, Long idPermit, PermitEntity permitEntity,
			AuthentificationEntity userConnectedEntity, Long idUser, Long submitId, Integer index) {
		File rSheetFile = null;
		try {
			if (ats != null) {
				rSheetFile = rSheetMapping("ATS_", ats.getManufacturer().trim(), ats.getModel().trim(),
						"AUTO TRANSFER SWITCH CUT SHEET", "Auto Transfer Switch", permitHomeSite, idPermit,
						permitEntity, userConnectedEntity, idUser, submitId, index);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File GeneratorRsheets(Generator generator, PermitHomeSiteInfoEntity permitHomeSite, Long idPermit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser, Long submitId,
			Integer index) {
		File rSheetFile = null;
		try {
			if (generator != null) {
				rSheetFile = rSheetMapping("GEN_", generator.getManufacturer().trim(), generator.getModel().trim(),
						"GENERATOR CUT SHEET", "Generator", permitHomeSite, idPermit, permitEntity, userConnectedEntity,
						idUser, submitId, index);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File rSheetMapping(String tag, String make, String model, String equipmentCutSheet, String equipment,
			PermitHomeSiteInfoEntity permitHomeSite, Long idPermit, PermitEntity permitEntity,
			AuthentificationEntity userConnectedEntity, Long idUser, Long submitId, Integer index) {

		File rSheetFile = null;
		try {

			String path = tag + make + "_" + model + ".pdf";
			String pathWithstate = permitHomeSite.getState() + "_" + tag + make + "_" + model + ".pdf";

			File fileRe = new File(R_SHEET_PATH + path);
			File fileReWithstate = new File(R_SHEET_PATH + pathWithstate);

			Boolean isFileExist = rSheetsLibraryRepo
					.existsByPdfNameInAndIsDeletedFalse(Arrays.asList(path, pathWithstate));
			if (Boolean.TRUE.equals(isFileExist)) {
				String filesPath = pathRepo.findFilePath();
				if (fileReWithstate.exists()) {
					rSheetFile = rSheetGenerate(permitHomeSite, idPermit,
							permitEntity.getAuthentificationEntity().getId(), pathWithstate, equipmentCutSheet,
							equipment, index, filesPath);

				} else if (fileRe.exists()) {
					rSheetFile = rSheetGenerate(permitHomeSite, idPermit,
							permitEntity.getAuthentificationEntity().getId(), path, equipmentCutSheet, equipment, index,
							filesPath);
				}
			} else {
				String notifMsg = missingSheetService.createRsheetNotifMessage(make, model,
						userConnectedEntity.getFirstName(), userConnectedEntity.getLastName(),
						permitEntity.getProjectName(), permitEntity.getHomeOwnName(),
						permitEntity.getHomeOwnLastName());
				missingSheetService.missingRSheet(idUser, "Missing " + equipment + " R-sheet", notifMsg, true, path,
						equipment + " R-sheet", submitId, permitEntity, userConnectedEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	private String getAscStandar(String asceStandard, GoverningCodesModel governingCodes) {
		if (checkValue.isStringNotEmpty(asceStandard)) {
			return "_" + asceStandard;
		} else if (checkValue.Equals(governingCodes.getIrc(), "2018")
				|| checkValue.Equals(governingCodes.getIbc(), "2018")
				|| checkValue.Equals(governingCodes.getCbc(), "2019")
				|| checkValue.Equals(governingCodes.getCrc(), "2019")) {
			return "_ASCE 7-16";
		} else {
			return "_ASCE 7-10";
		}
	}

	public File rackingRSheetMapping(String tag, String make, String model, String asceStandard,
			String equipmentCutSheet, String equipment, PermitHomeSiteInfoEntity permitHomeSite, Long idPermit,
			PermitEntity permitEntity, AuthentificationEntity userConnectedEntity, Long idUser, Long submitId,
			Integer index, GoverningCodesModel governingCodes) {

		File rSheetFile = null;
		try {
			String asce = getAscStandar(asceStandard, governingCodes);
			String path = tag + make + "_" + model + asce + ".pdf";
			String pathWithstate = permitHomeSite.getState() + "_" + tag + make + "_" + model + asce + ".pdf";

			File fileRe = new File(R_SHEET_PATH + path.replace(asce, ""));
			File fileReWithstate = new File(R_SHEET_PATH + pathWithstate.replace(asce, ""));
			File fileReAsce = new File(R_SHEET_PATH + path);
			File fileReWithstateAsce = new File(R_SHEET_PATH + pathWithstate);
			Boolean isFileExist = rSheetsLibraryRepo.existsByPdfNameInAndIsDeletedFalse(
					Arrays.asList(path, pathWithstate, path.replace(asce, ""), pathWithstate.replace(asce, "")));
			if (Boolean.TRUE.equals(isFileExist)) {
				String filesPath = pathRepo.findFilePath();
				if (Boolean.TRUE.equals(rSheetsLibraryRepo.existsByPdfNameAndIsDeletedFalse(pathWithstate))
						&& fileReWithstateAsce.exists()) {
					rSheetFile = rSheetGenerate(permitHomeSite, idPermit,
							permitEntity.getAuthentificationEntity().getId(), pathWithstate, equipmentCutSheet,
							equipment, index, filesPath);

				} else if (Boolean.TRUE
						.equals(rSheetsLibraryRepo.existsByPdfNameAndIsDeletedFalse(pathWithstate.replace(asce, "")))
						&& fileReWithstate.exists()) {
					rSheetFile = rSheetGenerate(permitHomeSite, idPermit,
							permitEntity.getAuthentificationEntity().getId(), pathWithstate.replace(asce, ""),
							equipmentCutSheet, equipment, index, filesPath);

				} else if (Boolean.TRUE.equals(rSheetsLibraryRepo.existsByPdfNameAndIsDeletedFalse(path))
						&& fileReAsce.exists()) {
					rSheetFile = rSheetGenerate(permitHomeSite, idPermit,
							permitEntity.getAuthentificationEntity().getId(), path, equipmentCutSheet, equipment, index,
							filesPath);

				} else if (Boolean.TRUE
						.equals(rSheetsLibraryRepo.existsByPdfNameAndIsDeletedFalse(path.replace(asce, "")))
						&& fileRe.exists()) {
					rSheetFile = rSheetGenerate(permitHomeSite, idPermit,
							permitEntity.getAuthentificationEntity().getId(), path.replace(asce, ""), equipmentCutSheet,
							equipment, index, filesPath);

				}
			} else {
				String notifMsg = missingSheetService.createRsheetNotifMessage(make, model,
						userConnectedEntity.getFirstName(), userConnectedEntity.getLastName(),
						permitEntity.getProjectName(), permitEntity.getHomeOwnName(),
						permitEntity.getHomeOwnLastName());
				missingSheetService.missingRSheet(idUser, "Missing " + equipment + " R-sheet", notifMsg, true, path,
						equipment + " R-sheet", submitId, permitEntity, userConnectedEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rSheetFile;
	}

	public File groundRsheetMapping(RailRacking railRackingGround, PermitAdvEntityResult permitAdvEntityInfo,
			PermitHomeSiteInfoEntity permitHomeSite, Long idPermit, PermitEntity permitEntity,
			AuthentificationEntity userConnectedEntity, Long idUser, Long submitId, Integer index) {
		try {
			
			String pdfName = rSheetsLibraryRepo.findPdfName(idPermit);
			if (!checkValue.isStringNotEmpty(pdfName) || !new File(R_SHEET_PATH + pdfName).exists()) {
				pdfName = createGroundSheetName.groundRaiRacking(railRackingGround, permitAdvEntityInfo,
						permitHomeSite, permitEntity, userConnectedEntity, idUser, submitId);
			}
			String filesPath = pathRepo.findFilePath();
			File fileRe = new File(R_SHEET_PATH + pdfName);
			if (checkValue.isStringNotEmpty(pdfName) && fileRe.exists()) {
				return rSheetGenerate(permitHomeSite, idPermit, permitEntity.getAuthentificationEntity().getId(),
						pdfName, "RACKING & RAIL CUT SHEET", "Groung Rail Racking", index, filesPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public File rSheetGenerate(PermitHomeSiteInfoEntity permitHomeSite, Long idPermit, Long projectOwner,
			String rSheetPath, String equipmentCutSheet, String equipment, Integer index, String filePath) {
		try {
			return plansetServiceE300.buildingPDFE300(permitHomeSite, idPermit, projectOwner, rSheetPath,
					equipmentCutSheet, equipment, index, filePath);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
