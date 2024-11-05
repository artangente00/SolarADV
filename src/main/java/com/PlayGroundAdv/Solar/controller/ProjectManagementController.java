package com.PlayGroundAdv.Solar.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.RFIConfirmOfSubmitEntity;
import com.PlayGroundAdv.Solar.entity.RFIConfirmationEntity;
import com.PlayGroundAdv.Solar.entity.RFIQuestionEntity;
import com.PlayGroundAdv.Solar.entity.RFInformationEntity;
import com.PlayGroundAdv.Solar.entity.SelectDrafterSheet;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.ConfContracRfiResponseModel;
import com.PlayGroundAdv.Solar.model.DrafterInformationModel;
import com.PlayGroundAdv.Solar.model.DuplicateCustomizeSheetsModel;
import com.PlayGroundAdv.Solar.model.ElectricalUtilityModel;
import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.model.GetPermitCompanyInfoEntity;
import com.PlayGroundAdv.Solar.model.HistoriqModel;
import com.PlayGroundAdv.Solar.model.LayoutSketchArraysModel;
import com.PlayGroundAdv.Solar.model.ListManagementFavoritesModel;
import com.PlayGroundAdv.Solar.model.ModuleFavRequest;
import com.PlayGroundAdv.Solar.model.PermitDrafterDataResult;
import com.PlayGroundAdv.Solar.model.PermitResponsePrime;
import com.PlayGroundAdv.Solar.model.PermitSketchResults;
import com.PlayGroundAdv.Solar.model.RFIModelRequest;
import com.PlayGroundAdv.Solar.model.RFIQuestionEntityModel;
import com.PlayGroundAdv.Solar.model.SearchProject;
import com.PlayGroundAdv.Solar.model.SelectDrafterSheetModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.FavoriteListDto;
import com.PlayGroundAdv.Solar.model.libraries.InverterResult;
import com.PlayGroundAdv.Solar.model.libraries.InvertersModels;
import com.PlayGroundAdv.Solar.model.project.ReviewRequest;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.drafter.DrafterPackageService;
import com.PlayGroundAdv.Solar.service.drafter.DrafterSheetsService;
import com.PlayGroundAdv.Solar.service.drafter.PermitToolsDrafterService;
import com.PlayGroundAdv.Solar.service.equipment_utils.GetInverterOCPD;
import com.PlayGroundAdv.Solar.service.equipment_utils.GetModuleOCPD;
import com.PlayGroundAdv.Solar.service.generate_planset.PlanSetService;
import com.PlayGroundAdv.Solar.service.generate_planset.exception.TechnicalProblemMsg;
import com.PlayGroundAdv.Solar.service.libraries.AcDisconnectService;
import com.PlayGroundAdv.Solar.service.libraries.ConverterService;
import com.PlayGroundAdv.Solar.service.libraries.InverterService;
import com.PlayGroundAdv.Solar.service.libraries.JunctionBoxService;
import com.PlayGroundAdv.Solar.service.libraries.ModuleService;
import com.PlayGroundAdv.Solar.service.libraries.RailRackingService;
import com.PlayGroundAdv.Solar.service.libraries.RoofAttachmentService;
import com.PlayGroundAdv.Solar.service.log.HistoryActivityService;
import com.PlayGroundAdv.Solar.service.log.NotificationEntityService;
import com.PlayGroundAdv.Solar.service.mailing.MailingService;
import com.PlayGroundAdv.Solar.service.project.CheckRequiredAmpacity;
import com.PlayGroundAdv.Solar.service.project.CopyProjectToGoogleDrive;
import com.PlayGroundAdv.Solar.service.project.GetProjectByIdService;
import com.PlayGroundAdv.Solar.service.project.PermitService;
import com.PlayGroundAdv.Solar.service.project.ProjectFavoritesListManagement;
import com.PlayGroundAdv.Solar.service.project.ProjectNotAllowedRackingNote;
import com.PlayGroundAdv.Solar.service.project.RFIConfirmOfSubmitService;
import com.PlayGroundAdv.Solar.service.project.RFInformationService;
import com.PlayGroundAdv.Solar.service.project.SubmitProjectService;
import com.PlayGroundAdv.Solar.service.restore_project.ExportProjectBackup;
import com.PlayGroundAdv.Solar.service.restore_project.RestoreProject;
import com.PlayGroundAdv.Solar.service.restore_project.ReviewProject;
import com.PlayGroundAdv.Solar.service.sheets.CustomizeSheetsService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(value="/project", produces = { "application/json" })
public class ProjectManagementController {

	final PermitRepository permitRepo;
	final AuthenticationRepository userRepo;
	final PermitService permitService;
	final PermitToolsDrafterService permitToolsDrafterService;
	final DrafterSheetsService permitTotalSectionService;
	final DrafterPackageService drafterPackageService;
	final PlanSetService planSetService;
	final ModuleService getLibraryService;
	final InverterService getInverterLibraryService;
	final AcDisconnectService acDisconnectLibraryService;
	final ConverterService convertersManagementService;
	final RoofAttachmentService roofAttachmentLibraryService;
	final RailRackingService railRackingLibraryService;
	final JunctionBoxService junctionBoxLibraryService;
	final NotificationEntityService notificationEntityService;
	final HistoryActivityService historyService;
	final RFInformationService rfinformationService;
	final RFIConfirmOfSubmitService rFIConfirmOfSubmitService;
	final MailingService mailingService;
	final ExportProjectBackup exportProjectBackup;
	final CustomizeSheetsService customizeSheetsService;
	final CheckRequiredAmpacity checkRequiredAmpacity;
	final ProjectFavoritesListManagement projectFavoritesListMgt;
	final GetProjectByIdService getProjectById;
	final GetInverterOCPD getInverterOCPD;
	final GetModuleOCPD getModuleOCPD;
	final ProjectNotAllowedRackingNote notAllowedRackingNote;
	final ReviewProject reviewProject;
	final RestoreProject restoreProject;
	final CopyProjectToGoogleDrive copyProjectToGoogleDrive;
	final SubmitProjectService submitProjectService;
	final TechnicalProblemMsg technicalProblem;
	Long idUserConnected;
	Long idPermitChange;


	public ProjectManagementController(PermitRepository permitRepo, AuthenticationRepository userRepo,
			PermitService permitService, PermitToolsDrafterService permitToolsDrafterService,
			DrafterSheetsService permitTotalSectionService,
			DrafterPackageService drafterPackageService, PlanSetService planSetService,
			ModuleService getLibraryService, InverterService getInverterLibraryService,
			AcDisconnectService acDisconnectLibraryService,
			ConverterService convertersManagementService,
			RoofAttachmentService roofAttachmentLibraryService,
			RailRackingService railRackingLibraryService, JunctionBoxService junctionBoxLibraryService,
			NotificationEntityService notificationEntityService,
			HistoryActivityService historyService, RFInformationService rfinformationService,
			RFIConfirmOfSubmitService rFIConfirmOfSubmitService, MailingService mailingService,
			ExportProjectBackup exportProjectBackup, CustomizeSheetsService customizeSheetsService,
			CheckRequiredAmpacity checkRequiredAmpacity, ProjectFavoritesListManagement projectFavoritesListMgt,
			GetProjectByIdService getProjectById, com.PlayGroundAdv.Solar.service.equipment_utils.GetInverterOCPD getInverterOCPD,
			GetModuleOCPD getModuleOCPD, ProjectNotAllowedRackingNote notAllowedRackingNote, ReviewProject reviewProject,
			RestoreProject restoreProject, CopyProjectToGoogleDrive copyProjectToGoogleDrive, 
			SubmitProjectService submitProjectService, TechnicalProblemMsg technicalProblem) {
		super();
		this.permitRepo = permitRepo;
		this.userRepo = userRepo;
		this.permitService = permitService;
		this.permitToolsDrafterService = permitToolsDrafterService;
		this.permitTotalSectionService = permitTotalSectionService;
		this.drafterPackageService = drafterPackageService;
		this.planSetService = planSetService;
		this.getLibraryService = getLibraryService;
		this.getInverterLibraryService = getInverterLibraryService;
		this.acDisconnectLibraryService = acDisconnectLibraryService;
		this.convertersManagementService = convertersManagementService;
		this.roofAttachmentLibraryService = roofAttachmentLibraryService;
		this.railRackingLibraryService = railRackingLibraryService;
		this.junctionBoxLibraryService = junctionBoxLibraryService;
		this.notificationEntityService = notificationEntityService;
		this.historyService = historyService;
		this.rfinformationService = rfinformationService;
		this.rFIConfirmOfSubmitService = rFIConfirmOfSubmitService;
		this.mailingService = mailingService;
		this.exportProjectBackup = exportProjectBackup;
		this.customizeSheetsService = customizeSheetsService;
		this.checkRequiredAmpacity = checkRequiredAmpacity;
		this.projectFavoritesListMgt = projectFavoritesListMgt;
		this.getProjectById = getProjectById;
		this.getInverterOCPD = getInverterOCPD;
		this.getModuleOCPD = getModuleOCPD;
		this.notAllowedRackingNote = notAllowedRackingNote;
		this.reviewProject = reviewProject;
		this.restoreProject = restoreProject;
		this.copyProjectToGoogleDrive = copyProjectToGoogleDrive;
		this.submitProjectService = submitProjectService;
		this.technicalProblem = technicalProblem;
	}

	@PostMapping("/checkIfProjectIsSubmitted")
	public Boolean checkIfProjectIsSubmitted(@RequestBody Long idPermit) throws Exception {
		return permitService.checkIfProjectIsSubmitted(idPermit);
	}

	@PostMapping("/getAskAgainValue/{idPermit}")
	public Boolean getAskAgainValue(@PathVariable Long idPermit) throws Exception {
		return permitService.getAskAgainValue(idPermit);
	}

	@PostMapping("/updateAskAgainValue/{idPermit}/{variab2}")
	public Boolean updateAskAgainValue(@PathVariable Long idPermit, @PathVariable Boolean variab2) {
		return permitService.updateAskAgainValue(idPermit, variab2);
	}

	@PostMapping("/deleteLayoutDoc/{idPermit}")
	public String deleteLayoutDoc(@PathVariable Long idPermit) throws Exception {
		return permitService.deleteLayoutDoc(idPermit);
	}

	@PostMapping("/saveProject")
	public String saveProject(HttpServletRequest request, @RequestBody HistoriqModel hm) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		GetPermitByIdResult nrrm = null;
		hm.setSessionId(request.getSession().getId());

		try {
			byte[] json = mapper.writeValueAsBytes(hm.getObjectOne());
			nrrm = mapper.readValue(json, GetPermitByIdResult.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = permitService.saveProject(nrrm, hm.getIdUser(), hm.getTimeZone(), hm.getIpAdress(),
				hm.getNumTab(), hm.getSessionId(), hm.getOpenDate());
		if (result.equals("success")) {
			String fileName = "";
			if(hm.getSaveType() != null) {
				fileName = exportProjectBackup.generateSaveProjectScv(nrrm.getPermitEntity().getId(),
						 hm.getIdUser(), hm.getSaveType());
			}

			// A.B: CR-2620 Create Customize sheet List
			customizeSheetsService.getCompatileCustomizeSheets(nrrm.getPermitEntity().getId());

			return "success" + "::" + fileName;
		} else {
			return result;
		}

	}

	/******************
	 * confirm shown attachement Contractor RFI tab
	 *********************/

	@PostMapping("/confShownAttachement/{idQuestion}")
	public String confShownAttachement(@PathVariable Long idQuestion) throws Exception {
		return permitService.confShownAttachement(idQuestion);
	}

	/****************** List of RFI in tab Contractor *********************/

	@PostMapping("/contractorRFIQuestion")
	public RFIQuestionEntityModel contractorRFIQuestion(@RequestBody String[] params) throws Exception {
		return permitService.contractorRFIQuestionList(params[0], params[1]);
	}

	/************************** End **************************************/
	@PostMapping("/typeInverter")
	public Boolean typeInverter(@RequestBody Long inverterId) throws Exception {
		return permitService.typeInverter(inverterId);
	}

	@PostMapping("/testIntegrated")
	public String testIntegrated(@RequestBody Long model) throws Exception {
		return permitService.testIntegrated(model);
	}

	@PostMapping("/upload/insertNoteFiletoADV")
	public String insertNoteFiletoADV(@RequestParam("file") MultipartFile file, @RequestParam("idPermit") Long idPermit,
			@RequestParam("fileName") String fileName, @RequestParam("folderName") String folderName)
			throws ResourceNotFoundException, IOException {

		PermitEntity project = permitRepo.findById(idPermit)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found for this id :" + idPermit));
		return permitService.insertNoteFiletoADV(file, idPermit, fileName, folderName, project);
	}

	@PostMapping("/downloadADVNote")
	public ResponseEntity<byte[]> downloadADVNote(@RequestBody Long idPermit) {
		return permitService.downloadADVNote(idPermit);
	}

	@PostMapping("/getNoteFilename")
	public String getNoteFilename(@RequestBody Long idPermit) throws Exception {
		return permitService.getNoteFilename(idPermit);
	}

	@PostMapping("/updateDate/{idPermit}")
	public String updateDate(@PathVariable Long idPermit) throws Exception {
		return permitService.updatedDate(idPermit);
	}

	@PostMapping("/saveSkecthLayoutArrays")
	public String saveSkecthLayoutArrays(@RequestBody LayoutSketchArraysModel layoutSketchArrays) throws Exception {
		return permitService.saveSkecthLayoutArrays(layoutSketchArrays);
	}

//	@PostMapping("/IdUserPerPer")
//	public String IdUserPerPer(String permitId) throws Exception {
//		if ((String) httpSession.getAttribute("ctdUser") == null) {
//			
//			lockedRedirect.LockedRedirectUrl(); return null;
//		} else
//			return permitService.IdUserPerPer(permitId);
//	}

	@PostMapping("/getNameImageDraf")
	public List<String> getNameImageDraf(@RequestBody Long idPermitInfo) throws Exception {
		return permitService.urlImagesDrafter(idPermitInfo);

	}

	@PostMapping("/getSketch")
	public List<PermitSketchResults> getSketch(@RequestBody Long idPermit) throws Exception {
		return getProjectById.getSketchByPermit(idPermit);
	}

	@PostMapping("/getPicsRFI")
	public ResponseEntity<byte[]> getPicsRFI(@RequestBody String url) {
		return permitService.getPicsRFI(url);
	}

	@PostMapping("/getPicsADVProjInput")
	public ResponseEntity<byte[]> getPicTest(@RequestBody String url) {
		return permitService.getPicTest(url);
	}

	@PostMapping("/getDrafterInformation")
	public DrafterInformationModel getDrafterInformation(@RequestBody Long idPermitInfo) throws Exception {
		return permitService.getDrafterInformation(idPermitInfo);
	}

	@PostMapping("/checkComponentsSelec")
	public String checkComponentsSelec(@RequestBody String[] checkList) throws Exception {
		return permitService.checkComponentsSelec(checkList[0], checkList[1], checkList[2], checkList[3],
					checkList[4], checkList[5], checkList[6], checkList[7], checkList[8], checkList[9], checkList[10],
					checkList[11], checkList[12], checkList[13], checkList[14], checkList[15], checkList[16],
					checkList[17], Long.valueOf(checkList[18]));
	}

	@PostMapping("/saveDrafterInformation")
	public String saveDrafterInformation(@RequestBody DrafterInformationModel drafterInfo) throws Exception {
		return permitService.saveDrafterInformation(drafterInfo);
	}

	@PostMapping("/saveDrafterData/{idPermit}")
	public String saveDrafterData(@PathVariable Long idPermit, @RequestBody PermitDrafterDataResult drafterInfo)
			throws Exception {
		return permitService.saveDrafterData(idPermit, drafterInfo);
	}

	@PostMapping("/editPermit/{isDrafter}/{modeView}")
	public GetPermitByIdResult editPermit(HttpServletRequest request, @RequestBody String[] ids,
			@PathVariable Boolean isDrafter, @PathVariable Boolean modeView) throws Exception {
		if (ids != null && ids.length == 6) {
			return getProjectById.getProjectById(Long.valueOf(ids[0]), Long.valueOf(ids[1]), ids[2], ids[3],
					request.getSession().getId(), ids[4], ids[5], isDrafter, modeView);
		} else
			return getProjectById.getProjectById(Long.valueOf(ids[0]), Long.valueOf(ids[1]), ids[2], "",
					request.getSession().getId(), "", "", isDrafter, modeView);

	}

	@PostMapping("/getModuleDimensions")
	public String getModuleDimensions(@RequestBody Long moduleId) throws Exception {
		return permitService.getModuleDimensions(moduleId);
	}

	@PostMapping("/submitPermit")
	public String submitPermit(HttpServletRequest request, @RequestBody String[] submitVar) throws Exception {
		String returnDrafter;
		String returnPlanset;
		String returnPlansetResult;
		String retunrSubmmit = "Fail";
		String retunrSubmmitToRecord = "Fail";
		idUserConnected = Long.valueOf(submitVar[0]);
		idPermitChange = Long.valueOf(submitVar[1]);
		boolean historic = true;
		try {
			returnDrafter = drafterPackageService.downloadPackage(Long.valueOf(submitVar[1]),
					Long.valueOf(submitVar[0]));

		} catch (Exception e) {
			e.printStackTrace();
			returnDrafter = "Fail";
			historic = false;
			technicalProblem.traiterException(e);
		}
		System.out.println("returnDrafter " + returnDrafter);
		try {
			returnPlanset = generateFile(Long.valueOf(submitVar[0]), Long.valueOf(submitVar[1]));
			returnPlansetResult = returnPlanset.contains("Done") ? "Done" : "Fail";
		} catch (Exception e) {
			e.printStackTrace();
			returnPlanset = "Fail";
			returnPlansetResult = "Fail";
			historic = false;
			technicalProblem.traiterException(e);
		}
		System.out.println("returnPlanset " + returnPlanset);
		System.out.println("returnPlansetResult " + returnPlansetResult);
		try {
			if (returnDrafter.equals("Done") && !returnPlanset.contains("Done") && !returnPlanset.equals("Fail")) {
				retunrSubmmit = returnPlansetResult;
				retunrSubmmitToRecord = "Fail";
			}
			if (returnDrafter.equals("Done") && returnPlanset.contains("Done")) {
				retunrSubmmit = submitProjectService.submitPermitEntity(Long.valueOf(submitVar[0]),
						Long.valueOf(submitVar[1]));
				retunrSubmmitToRecord = retunrSubmmit;
			}
		} catch (Exception e) {
			e.printStackTrace();
			retunrSubmmit = "Fail";
			retunrSubmmitToRecord = "Fail";
			historic = false;
			technicalProblem.traiterException(e);
		}
		historyService.recordActivity(Long.valueOf(submitVar[0]), submitVar[2], submitVar[3],
				"Submit Permit;" + submitVar[1] + ";retunrSubmmit:   " + retunrSubmmitToRecord + "   /returnPlanset:  "
						+ returnPlansetResult + "   /returnDrafter  :    " + returnDrafter,
				historic, submitVar[4], request.getSession().getId(), submitVar[5]);

		// CR-2424
		if (retunrSubmmit != null && retunrSubmmit.equals("Fail") && !retunrSubmmit.equals("Done")) {
			submitProjectService.failedPermitEntity(Long.valueOf(submitVar[1]),Long.valueOf(submitVar[0]));
		}
		return retunrSubmmit;
	}

	@PostMapping("/getAllElectricalUtilityCompany")
	public List<ElectricalUtilityModel> getAllElectricalUtilityCompany(@RequestBody String zipCode) throws Exception {

		return permitService.getAllElectricalUtilityCompany(zipCode);
	}

	@GetMapping("/getMicroInverterManufacturer")
	public List<String> getMicroInverterManufacturer() throws Exception {
		return permitService.getMicroInverterManufacturer();
	}

	@PostMapping("/getMicroInvertersModels")
	public List<String> getMicroInvertersModels(@RequestBody String Make) throws Exception {

		return permitService.getMicroInvertersModels(Make);
	}

	@PostMapping("/saveDrafterSheets/{idPermit}")
	public String saveDrafterSheets(@RequestBody List<SelectDrafterSheetModel> drafterSheets, @PathVariable Long idPermit)
			throws Exception {

		return permitTotalSectionService.saveDrafterSheets(drafterSheets, idPermit);
	}

	@PostMapping("/getDrafterSheets")
	public List<SelectDrafterSheet> getDrafterSheets(@RequestBody Long idPermit) throws Exception {

		return permitTotalSectionService.getDrafterSheets(idPermit);
	}

	@PostMapping("/downloadPackageDrafter/{idPermit}")
	public ResponseEntity<byte[]> downloadPackageDrafter(@RequestBody String[] homeOwnName,
			@PathVariable Long idPermit) {
		return drafterPackageService.downloadPackageDrafter(homeOwnName[0], homeOwnName[1], homeOwnName[2], idPermit);
	}

	public String generateFile(Long idUser, Long idPermit) throws Exception {
		return planSetService.generateFile(idUser, idPermit);
	}

	@PostMapping("/addModule/{idUser}/{idPermitInfo}")
	public Cmodulev2 addModule(@RequestBody ModuleFavRequest hm, @PathVariable Long idUser,
			@PathVariable Long idPermitInfo) throws Exception {
		return getLibraryService.addModule(idPermitInfo, hm, idUser);
	}

	@PostMapping("/addInverter/{idUser}/{idPermitInfo}")
	public FavoriteListDto addNewInverter(@RequestBody InverterResult invereterFavRequest, @PathVariable Long idUser,
			@PathVariable Long idPermitInfo) throws Exception {
		return getInverterLibraryService.addNewInverter(idPermitInfo, invereterFavRequest, idUser);
	}

	@PostMapping("/checkInverterExistent/{idUserCo}")
	public List<ComponentModel> checkInverterExistent(@RequestBody InverterResult inverter,
			@PathVariable Long idUserCo) throws Exception {
		return getInverterLibraryService.checkInverterExistent(inverter, idUserCo);
	}

	/******** Test if the Converter is Favorit or not ***********/

	@PostMapping("/testConvertersFavorit")

	public boolean testConvertersFavorit(@RequestBody String[] testConvertersFavorit) throws Exception {

		return convertersManagementService.testConverterFav(Long.valueOf(testConvertersFavorit[0]),
				Long.valueOf(testConvertersFavorit[1]), testConvertersFavorit[2]);
	}

	@PostMapping("/getDrafterDataFiles")
	public HashMap<String, List<String>> getDrafterDataFiles(@RequestBody Long permitId) throws IOException {

		return permitService.getDrafterDatafiles(permitId);
	}

	// CR-3230 Check if permit contains drafts
	@PostMapping("/checkDraftsForPermit")
	public Boolean checkDraftsForPermit(@RequestBody Long permitId) throws IOException {

		return permitService.checkDraftsForPermit(permitId);
	}

	@PostMapping("/getAdditionalInfoFiles")
	public List<String> getAdditionalInfoFiles(@RequestBody Long permitId) throws Exception {

		return permitService.getAdditionalInfoFiles(permitId);
	}

	@PostMapping("/getNoteSketchFilesOne")
	public List<String> getNoteSketchFilesOne(@RequestBody Long permitId) throws Exception {

		return getProjectById.getNoteSketchFilesOne(permitId);
	}

	@PostMapping("/getBOSFiles")
	public List<String> getBOSFiles(@RequestBody String permitId) throws Exception {

		return permitService.getBOSFiles(permitId);
	}

	@PostMapping("/getUtilityBillFiles")
	public List<String> getUtilityBillFiles(@RequestBody String idPermit) throws Exception {

		return permitService.getUtilityBillFiles(idPermit);
	}

	@PostMapping("/getNoteSketchFilesTwo")
	public List<String> getNoteSketchFilesTwo(@RequestBody Long permitId) throws Exception {

		return getProjectById.getNoteSketchFilesTwo(permitId);
	}

	@PostMapping("/getOcpdNumber")
	public double getOcpdNumber(@RequestBody Long[] inverters) throws Exception {

		return getInverterOCPD.getOcpdNumber(inverters[0], inverters[1], inverters[2], inverters[3], inverters[4]);
	}

	@PostMapping("/getInverterOcpdNumber")
	public double getInverterOcpdNumber(@RequestBody Long InverterId) throws Exception {

		return getInverterOCPD.getInverterOcpdNumber(InverterId);
	}

	// CR-1409----------------
	@PostMapping("/getOcpdNumberMicroInverterS")
	public double getOcpdNumberMicroInverterS(@RequestBody Long microInverterID) throws Exception {
		return getInverterOCPD.getOcpdNumberMicroInverterS(microInverterID);
	}

	@PostMapping("/getWireQtyMicroInverter")
	public String getWireQtyMicroInverter(@RequestBody Long microInverterId) throws Exception {
		return planSetService.getWireQtyMicroInverter(microInverterId);
	}

	@PostMapping("/getWireQtyInverter")
	public String getWireQtyInverter(@RequestBody Long inverterId) throws Exception {
		return planSetService.getWireQtyInverter(inverterId);
	}

	@PostMapping("/getModuleOcpdNumber")
	public double getModuleOcpdNumber(@RequestBody Long module) {
		return getModuleOCPD.getModuleOcpdNumber(module);
	}

	@PostMapping("/saveADVRFI/{idPermit}/{idUser}")
	public String saveADVRFI(@PathVariable Long idPermit, @PathVariable Long idUser, @RequestBody String[][] rfiModel) {
		List<RFIModelRequest> rfiList = new ArrayList<RFIModelRequest>();
		for (int i = 0; i < rfiModel[5].length; i++) {
			RFIModelRequest rfi = new RFIModelRequest(rfiModel[0][i], rfiModel[1][i], rfiModel[2][i], rfiModel[3][i],
					rfiModel[4][i], rfiModel[5][i]);
			rfiList.add(rfi);
		}
		return rfinformationService.saveAdvRfInformation(idPermit, idUser, rfiList);
	}

	@PostMapping("/getRfiByPermit/{idPermit}")
	public List<RFInformationEntity> getRfiByPermit(@PathVariable Long idPermit) throws Exception {
		return rfinformationService.getRfibyPermit(idPermit);
	}

	@PostMapping("/saveContConfDraft/{idPermit}/{idUser}")
	public String saveContConfDraft(@RequestBody String[][] params, @PathVariable Long idPermit,
			@PathVariable Long idUser) {
		List<ConfContracRfiResponseModel> contListRfi = new ArrayList<ConfContracRfiResponseModel>();

		for (int i = 0; i < params[1].length; i++) {
			ConfContracRfiResponseModel confContracRfiResponseModel = new ConfContracRfiResponseModel(
					Long.valueOf(params[0][i]), Boolean.parseBoolean(params[1][i]), params[2][i]);
			contListRfi.add(confContracRfiResponseModel);
		}

		return rfinformationService.saveContractorResponse(contListRfi);
	}

	/********************
	 * Init submited RFI to the RFIConfirmOfSubmitEntity
	 **********************/
	@PostMapping("/initSubmitContractorRFI/{idPermit}")
	public RFIConfirmOfSubmitEntity initSubmitContractorRFI(@PathVariable Long idPermit) throws Exception {
		return rFIConfirmOfSubmitService.submitContractorRFI(idPermit);
	}

	/**********************
	 * End of Init submited RFI to the RFIConfirmOfSubmitEntity
	 ****************/

	@PostMapping("/updateSheetRFI/{idPermit}")
	public String updateSheetRFI(@PathVariable Long idPermit) throws Exception {
		return rfinformationService.updateContractorSheetRFI(idPermit);
	}

	@PostMapping("/submitADVRFI")
	public String submitADVRFI(@RequestBody String[] AdvRfiInfo) throws Exception {
		return rfinformationService.submitADVRFI(Long.valueOf(AdvRfiInfo[0]), Long.valueOf(AdvRfiInfo[1]),
				AdvRfiInfo[2]);
	}

	/********************
	 * Add submited RFI to the RFIConfirmOfSubmitEntity
	 **********************/
	@PostMapping("/addOrUpdateSubmitRFI/{idPermit}")
	public RFIConfirmOfSubmitEntity addOrUpdateSubmitRFI(@PathVariable Long idPermit) throws Exception {
		return rFIConfirmOfSubmitService.addOrUpdateSubmitRFI(idPermit);
	}

	/**********************
	 * End of Add submited RFI to the RFIConfirmOfSubmitEntity
	 ****************/
	@PostMapping("/addNotifSubmitRfiADV/{idPermit}/{idUser}")
	public String addNotifSubmitRfiADV(@PathVariable Long idPermit, @PathVariable Long idUser) throws Exception {
		return rfinformationService.addNotifSubmitRfiADV(idUser, idPermit);
	}

	@PostMapping("/addNotifSubmitRfiContractor/{idPermit}/{idUser}")
	public String addNotifSubmitRfiContractor(@PathVariable Long idPermit, @PathVariable Long idUser) throws Exception {
		return rfinformationService.addNotifSubmitRfiContractor(idUser, idPermit);
	}

	@PostMapping("/submitContractorRfi/{idPermit}/{idUser}")
	public String submitContractorRfi(@PathVariable Long idPermit, @PathVariable Long idUser) throws Exception {
		return rfinformationService.submitContractorRFI(idPermit, idUser);
	}

	@PostMapping("/getConfirmationRFI/{idPermit}")
	public RFIConfirmationEntity getConfirmationRFI(@PathVariable Long idPermit) throws Exception {
		return rfinformationService.getConfirmationRfi(idPermit);
	}

	@PostMapping("/getListManagementFavorites")
	public ListManagementFavoritesModel getListManagementFavorites(@RequestBody Long[] ids) throws Exception {
		return projectFavoritesListMgt.getListManagementFavorites(ids[0], ids[1]);
	}

	/// ------ Junction Box -----///
	@PostMapping("/getJBox")
	public List<DCCombinerDisconnectEntity> getJBox(@RequestBody Long IdPemit) throws Exception {
		return junctionBoxLibraryService.getJBox(IdPemit);
	}

	@PostMapping("/getContractorJBox")
	public List<DCCombinerDisconnectEntity> getContractorJBox(@RequestBody Long IdUser) throws Exception {
		return junctionBoxLibraryService.getContractorJBox(IdUser);
	}

	@PostMapping("/checkModuleExistent/{idUserCo}")
	public List<ModuleFavRequest> checkModuleExistent(@RequestBody ModuleFavRequest module, @PathVariable Long idUserCo)
			throws Exception {
		return getLibraryService.checkModuleExistent(module, idUserCo);
	}

	@PostMapping("/addRFIQuestion")
	public String addRFIQuestion(@RequestBody String[] RFIQuestion) throws Exception {
		return permitService.addRFIQuestion(RFIQuestion[0], RFIQuestion[1], RFIQuestion[2]);
	}

	@PostMapping("/getRFIQuestion")
	public List<RFIQuestionEntity> getRFIQuestion(@RequestBody List<String> rfiFields) throws Exception {
		return rfinformationService.getRFIQuestion(rfiFields);
	}

	@PostMapping("/removeFromUsersFavList/{componentType}/{contractorID}/{idUserConnect}")
	public String removeFromUsersFavList(@PathVariable String componentType, @RequestBody Long[] removedFavList,
			@PathVariable Long contractorID, @PathVariable Long idUserConnect) throws Exception {
		return permitService.removeFromUsersFavList(componentType, removedFavList, contractorID, idUserConnect);
	}

	/// ------ refresh the Roof Attachements Favorite List -----///
	@PostMapping("/testRefreshRoofAttachements")
	public boolean testRefreshRoofAttachements(@RequestBody Long[] RoofAttInfo) throws Exception {
		return roofAttachmentLibraryService.refreshRoofAttachements(RoofAttInfo[0], RoofAttInfo[1]);
	}


	@PostMapping("/getRemovedListManufAndModel")
	public List<String> getRemovedListManufAndModel(@RequestBody String[] removedFavList) throws Exception {
		return permitService.getRemovedListManufAndModel(removedFavList);
	}

	@PostMapping("/getRemovedInverterListManufModel")
	public List<String> getRemovedInverterListManufModel(@RequestBody String[] removedFavList) throws Exception {
		return permitService.getRemovedInverterListManufModel(removedFavList);
	}

	@PostMapping("/getUtilityCompanyInfo")
	public GetPermitCompanyInfoEntity getUtilityCompanyInfo(@RequestBody Long[] ids) throws Exception {
		return permitService.getUtilityCompanyInfo(ids[0], ids[1]);
	}

	@PostMapping("/saveUtilityCompany/{idPermit}")
	public String saveUtilityCompany(@RequestBody GetPermitByIdResult permitModel, @PathVariable Long idPermit)
			throws Exception {
		return permitService.saveUtilityCompany(permitModel, idPermit);
	}

	@PostMapping("/getACDisconnectType")
	public String[] getACDisconnectType(@RequestBody String acDisconnect) throws Exception {
		return acDisconnectLibraryService.getACDisconnectType(acDisconnect);
	}

//	CR-1676
	@PostMapping("/getModuleNameSpace")
	public double getModuleNameSpace(@RequestBody String PvModule) throws Exception {

		return permitService.getModuleNameSpace(PvModule);
	}
	// CR2050

	@PostMapping("/getUtilityCompany")
	public ElectricalUtilityEntity getUtilityCompany(@RequestBody String UCid) throws Exception {
		return permitService.getUtilityCompany(UCid);
	}

	@PostMapping("/getPermit")
	public PermitEntity getPermit(@RequestBody Long idPermit) throws Exception {
		return permitService.getPermit(idPermit);
	}

	@PostMapping("/getUtilityName")
	public String getUtilityName(@RequestBody String utility) throws Exception {
		return permitService.getUtilityName(utility);
	}

	// CR-2219
	@PostMapping("/sendWarningEmail")
	public String sendWarningEmail(@RequestBody String[] mailInfo)
			throws AddressException, UnsupportedEncodingException {
		return mailingService.sendWarningEmail(mailInfo[0], mailInfo[1], mailInfo[2]);
	}

	@PostMapping("/sendSubmitEmail")
	public String sendSubmitEmail(@RequestBody Long[] mailInfo) throws Exception {
		return copyProjectToGoogleDrive.copyProjectOnDrive(mailInfo[0], mailInfo[1]);

	}

	@PostMapping("/getRoofAttachment")
	public String getRoofAttachment(@RequestBody String[] roofMaterial) throws Exception {
		return permitService.getRoofAttachment(roofMaterial[0], Long.valueOf(roofMaterial[1]));
	}

	@PostMapping("/mapExistingConductor")
	public String mapExistingConductor(@RequestBody String[] existConductor) throws Exception {
		return permitService.mapExistingConductor(existConductor[0], Long.valueOf(existConductor[1]));
	}

	@PostMapping("/mapNoExistingConductor")
	public String mapNoExistingConductor(@RequestBody String[] existConductor) throws Exception {
		return permitService.mapNoExistingConductor(existConductor[0], Long.valueOf(existConductor[1]));
	}

	@PostMapping("/getRailConnectionInfo")
	public List<String> getRailConnectionInfo(@RequestBody Long idRail) throws Exception {
		return roofAttachmentLibraryService.getRailConnectionInfo(idRail);
	}

	@PostMapping("/getRoofMaterial")
	public String getRoofMaterial(@RequestBody Long roofID) {
		return drafterPackageService.getRoofMaterialType(roofID);

	}

	// A.B 03-27: CR-2478 import Project previous version
	@PostMapping("/restoreProjectRevision")
	public GetPermitByIdResult restoreProjectRevision(@RequestBody String[] ids) {
		// Params: idProject, version, userID, timeZone, ip
		return restoreProject.restoreProjectRevision(Long.valueOf(ids[0]), ids[1], Long.valueOf(ids[2]),
				ids[3], ids[4]);

	}

	// 18-04-2019: C.I: Check if the component is deleted
	@PostMapping("/isComponentDeleted/{idComponent}")
	public String isComponentDeleted(@RequestBody String componentType, @PathVariable Long idComponent) {
		return permitService.isComponentDeleted(idComponent, componentType);
	}

	// A.B: CR-2620 Customize sheet Parameter Initiate
	@PostMapping("/getDuplicateCompatileCustomizeSheets")
	public HashMap<String, List<DuplicateCustomizeSheetsModel>> getDuplicateCompatileCustomizeSheets(
			@RequestBody Long idPermit) {
		return customizeSheetsService.getDuplicateCompatileCustomizeSheets(idPermit);
	}

	@PostMapping("/saveConductorSizeNotes")
	public String saveConductorSizeNotes(@RequestBody String[] notes) {
		return permitService.saveConductorSizeNotes(notes[0], Long.valueOf(notes[1]));
	}

	// A.B: CR-2620 Customize sheet Parameter Initiate
	@PostMapping("/updateCusSheets")
	public String updateCusSheets(@RequestBody Long[] ids) {
		return customizeSheetsService.updateCusSheets(ids);
	}

	// S.B CR-2627 Multiple Modules per Micro
	@PostMapping("/getModPerMicro")
	public Double[] getModPerMicro(@RequestBody Long microInverter) {
		return permitService.getModPerMicro(microInverter);
	}

	// S.B CR-2627 Multiple Modules per Micro
	@PostMapping("/getOcpdMax")
	public String getOcpdMax(@RequestBody Long microInverterId) {
		return permitService.getOcpdMax(microInverterId);
	}

	// A.B: CR-2620 Customize sheet Parameter Initiate
	@PostMapping("/checkSubPanelAmpacityCorrection")
	public Boolean checkSubPanelAmpacityCorrection(@RequestBody Long idPermit) {
		return checkRequiredAmpacity.checkSubPanelAmpacityCorrection(idPermit);
	}

	@PostMapping("/downloadConductorSizeFiles")
	public ResponseEntity<byte[]> downloadConductorSizeFiles(@RequestBody Long idPermit) {
		return permitService.downloadConductorSizeFiles(idPermit);
	}

	// CR-2777 get Utility Company
	@PostMapping("/getUtilityList")
	public List<String> getUtilityList() throws Exception {

		return permitService.getUtilityList();
	}

	// CR-2777 get All inverters
	@PostMapping("/getAllInverters")
	public List<ComponentModel> getAllInverters() throws Exception {

		return permitService.getAllInverters();
	}

	// CR-2777 get All inverters
	@PostMapping("/getAllRailToRoofConnection")
	public List<ComponentModel> getAllRailToRoofConnection() throws Exception {

		return permitService.getAllRailToRoofConnection();
	}

	// CR-2777 get All Rail Racking
	@PostMapping("/getAllRailRackingList")
	public List<ComponentModel> getAllRailRackingList() throws Exception {

		return permitService.getAllRailRackingList();
	}

	// CR-2777 get All Battery
	@PostMapping("/getAllBattery")
	public List<ComponentModel> getAllBattery() throws Exception {

		return permitService.getAllBattery();
	}

	// CR-2777 get All Tilt Legs
	@PostMapping("/getTiltLegsList")
	public List<ComponentModel> getTiltLegsList() throws Exception {

		return permitService.getTiltLegsList();
	}

	@PostMapping("/searchProject")
	public Page<PermitResponsePrime> searchProject(@RequestBody SearchProject searchProject) throws Exception {
		return permitService.searchProject(searchProject);
	}
	
	@PostMapping("/globalSearchSuperUser")
	public Page<PermitResponsePrime> globalSearchSuperUser(@RequestBody String[] params) throws Exception {
		return permitService.globalSearchSuperUser(params[0], params[1], Integer.parseInt(params[2]), Integer.parseInt(params[3]));
	}
	
	@PostMapping("/globalSearchSuperUserSort")
	public Page<PermitResponsePrime> globalSearchSuperUserSort(@RequestBody String[] params) throws Exception {
		return permitService.globalSearchSuperUserSort(params[0], params[1], Integer.parseInt(params[2]), Integer.parseInt(params[3]), params[4], params[5]);
	}
	
	@PostMapping("/globalSearchDrafter")
	public Page<PermitResponsePrime> globalSearchDrafter(@RequestBody String[] params) throws Exception {
		return permitService.globalSearchDrafter(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2]));
	}
	
	@PostMapping("/globalSearchDrafterSort")
	public Page<PermitResponsePrime> globalSearchDrafterSort(@RequestBody String[] params) throws Exception {
		return permitService.globalSearchDrafterSort(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2]), params[3], params[4]);
	}
	
	@PostMapping("/globalSearchContractor/{idUser}")
	public Page<PermitResponsePrime> globalSearchContractor(@PathVariable("idUser") Long idUser, @RequestBody String[] params) throws Exception {
		return permitService.globalSearchContractor(idUser, params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2]));
	}
	
	@PostMapping("/globalSearchContractorSort/{idUser}")
	public Page<PermitResponsePrime> globalSearchContractorSort(@PathVariable("idUser") Long idUser, @RequestBody String[] params) throws Exception {
		return permitService.globalSearchContractorSort(idUser, params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2]), params[3], params[4]);
	}

	@PostMapping("/getInvertersModels")
	public InvertersModels getInvertersModels(@RequestBody Long idPermit) throws Exception {

		return permitService.getInvertersModels(idPermit);
	}

	// 27/08/2019 : CR 2860 / CI : get conduit run from site survey when choose
	// inverter technology
	@PostMapping("/getConduitRun")
	public String getConduitRun(@RequestBody String[] varList) throws Exception {

		return permitService.getConduitRun(Long.valueOf(varList[0]), varList[1]);
	}

	@PostMapping("/getDrafterSheetsNumber")
	public Integer getDrafterSheetsNumber(@RequestBody Long idPermit) throws Exception {

		return permitService.getDrafterSheetsNumber(idPermit);
	}

	// 05/11/2019 :CI/ CR 2984 Send Email for Missing AHJ Governing Codes
	@PostMapping("/sendEmailMissingAHJCodes")
	public String sendEmailMissingAHJCodes(@RequestBody String[] mailInfo) throws Exception {
		return "";
		//return permitServiceEdit.sendEmailMissingAHJCodes(Long.valueOf(mailInfo[0]), mailInfo[1], mailInfo[2]);

	}

	@PostMapping("/getProjectOwnerSettings")
	public UserSettingEntity getProjectOwnerSettings(@RequestBody Long idPermit) throws Exception {

		return permitService.getProjectOwnerSettings(idPermit);
	}

	@PostMapping(value = "/upload/insertRackingNoteFiletoADV")
	public String insertRackingNotetoADV(@RequestParam("uploadedFile") MultipartFile file,
			@RequestParam("fileName") String fileName, @RequestParam("idPermit") Long idPermit) throws IOException {

		return notAllowedRackingNote.insertRackingNotetoADV(file, fileName, idPermit);
	}


	@PostMapping("/downloadADVRackingNote/{idPermit}")
	public ResponseEntity<byte[]> downloadADVRackingNote(@PathVariable Long idPermit, @RequestBody String fileSpec) {
		return notAllowedRackingNote.downloadADVRackingNote(idPermit, fileSpec);
	}
	
	@PostMapping("/reviewProject")
	public GetPermitByIdResult reviewProject(@RequestBody ReviewRequest data) {
		return reviewProject.getProjectById(data);
	}
	
	// F.B CR-388 Remove Layout & Site Plan Files
	@PostMapping("/removeLayoutFile/{idPermit}")
	public String removeLayoutFile(@PathVariable String idPermit, @RequestBody String fileName) throws Exception {
		return permitService.removeLayoutFile(Long.valueOf(idPermit),fileName);
	}
	
}
