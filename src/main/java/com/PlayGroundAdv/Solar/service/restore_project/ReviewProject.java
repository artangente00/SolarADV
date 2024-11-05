package com.PlayGroundAdv.Solar.service.restore_project;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.model.GetPermitByIdResult;
import com.PlayGroundAdv.Solar.model.OCPDMainPanelModel;
import com.PlayGroundAdv.Solar.model.PermitArrayEntityResultSecond;
import com.PlayGroundAdv.Solar.model.PermitConduitConductorSectionEntitieResult;
import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
import com.PlayGroundAdv.Solar.model.SystemAttributesModel;
import com.PlayGroundAdv.Solar.model.project.ReviewRequest;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.ProjectNotesRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.project.CalculMinOCPDMainPanel;
import com.PlayGroundAdv.Solar.service.project.GetProjectByIdService;
import com.PlayGroundAdv.Solar.service.project.GetProjectDetailsUtils;
import com.PlayGroundAdv.Solar.service.project.GetSystemSpec;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class ReviewProject {
	
	final CheckValueTypesService checkValue;
	final PathRepository pathRepo;
	final GetHomeSiteInfo homeSiteInfo;
	final GetArraysTab arraysTab;
	final GetBosTab bosTab;
	final GetConduitConductorTab conduitConductorTab;
	final GetLayoutSketchTab layoutSketchTab;
	final GetAdditionalInfoTab additionalInfoTab;
	final GetADVProjectInputsTab advProjectInputsTab;
	final GetSystemSpec systemSpec;
	final PermitRepository permitRepo;
	final UserSettingRepository userSettingRepo;
	final GetProjectByIdService getProjectByIdService;
	final GetProjectDetailsUtils getProjectDetailsUtils;
	final CalculMinOCPDMainPanel calculMinOCPDMainPanel;
	final ProjectNotesRepository projectNotesRepo;

	public ReviewProject(CheckValueTypesService checkValue, PathRepository pathRepo, GetHomeSiteInfo homeSiteInfo,
			GetArraysTab arraysTab, GetBosTab bosTab, GetConduitConductorTab conduitConductorTab,
			GetLayoutSketchTab layoutSketchTab, GetAdditionalInfoTab additionalInfoTab,
			GetADVProjectInputsTab advProjectInputsTab, GetSystemSpec systemSpec, PermitRepository permitRepo,
			UserSettingRepository userSettingRepo, GetProjectByIdService getProjectByIdService,
			GetProjectDetailsUtils getProjectDetailsUtils, CalculMinOCPDMainPanel calculMinOCPDMainPanel,
			ProjectNotesRepository projectNotesRepo) {
		super();
		this.checkValue = checkValue;
		this.pathRepo = pathRepo;
		this.homeSiteInfo = homeSiteInfo;
		this.arraysTab = arraysTab;
		this.bosTab = bosTab;
		this.conduitConductorTab = conduitConductorTab;
		this.layoutSketchTab = layoutSketchTab;
		this.additionalInfoTab = additionalInfoTab;
		this.advProjectInputsTab = advProjectInputsTab;
		this.systemSpec = systemSpec;
		this.permitRepo = permitRepo;
		this.userSettingRepo = userSettingRepo;
		this.getProjectByIdService = getProjectByIdService;
		this.getProjectDetailsUtils = getProjectDetailsUtils;
		this.calculMinOCPDMainPanel = calculMinOCPDMainPanel;
		this.projectNotesRepo = projectNotesRepo;
	}


	public GetPermitByIdResult getProjectById(ReviewRequest data) {

		GetPermitByIdResult getPermitByIdResult = new GetPermitByIdResult();

		try {
			
			getPermitByIdResult = getProjectByIdService.getProjectById(data.getIdProject(), data.getIdUser(), null, null, null, null, null, false, true);
			if (getPermitByIdResult != null && getPermitByIdResult.getPermitEntity() != null) {
				
				String[] dbList = {"public.permit_entity","public.permit_home_site_info_entity","public.permit_arrays_entity","public.permit_project_site_info_entity",
		                "public.permit_conduit_conductor_section_entity","public.permit_layout_entity","public.permit_engineer_entity",
		                "public.permit_additional_info_entity","public.permit_adv_entity","public.permit_weather_entity","public.permit_energy_battery_system"};
				
				String path = pathRepo.findFilePath();
				File exportSaveFolder = new File(path+"exportSave/" + data.getIdProject());
				File[] listOfFiles = exportSaveFolder.listFiles();
				String sheetName= "";
				int k =0;
				while (checkValue.Equals(sheetName, "") && k < listOfFiles.length) {
					sheetName= checkValue.contains(listOfFiles[k].getName(), data.getVersion()) ? listOfFiles[k].getName() : "";
					k++;
				}
				String filePath = path+"exportSave/" + data.getIdProject() + "/" + sheetName;
				FileInputStream inputStream = new FileInputStream(new File(filePath));
		        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		        
		        PermitArrayEntityResultSecond arrays = new PermitArrayEntityResultSecond();
		        PermitProjectSiteInfoEntityTwo bos = new PermitProjectSiteInfoEntityTwo();
		        PermitConduitConductorSectionEntitieResult conduitConductor = new PermitConduitConductorSectionEntitieResult();
		        
		        for (int i = 0; i < dbList.length; i++) {
		        	String sName = dbList[i].length() <= 31 ? dbList[i] : dbList[i].substring(0, Math.min(dbList[i].length(), 31));
					HSSFSheet sheet = workbook.getSheet(sName);
					if(sheet != null) {
						
						if (dbList[i].equals("public.permit_entity")) {
							getPermitByIdResult.setPermitEntity(homeSiteInfo.getPermitEntity(sheet, getPermitByIdResult.getPermitEntity()));
						} else if (dbList[i].equals("public.permit_home_site_info_entity")) {
							getPermitByIdResult.setPermitHomeSiteEntityResult(homeSiteInfo.getPermitHomeOwner(sheet));
						} else if (dbList[i].equals("public.permit_arrays_entity")) {
							arrays = arraysTab.getArraysEntity(sheet);
						} else if (dbList[i].equals("public.permit_project_site_info_entity")) {
							bos = bosTab.getProjectBalanceOfSystem(sheet);
						} else if (dbList[i].equals("public.permit_conduit_conductor_section_entity")) {
							conduitConductor = conduitConductorTab.getConduitConductorSection(sheet);
						} else if (dbList[i].equals("public.permit_layout_entity")) {
							getPermitByIdResult.setLayoutSketch(layoutSketchTab.getLayoutSketch(sheet, getPermitByIdResult.getLayoutSketch()));
						} else if (dbList[i].equals("public.permit_engineer_entity")) {
							getPermitByIdResult.setPermitEngineerEntityResult(additionalInfoTab.getProjectEngineer(sheet));
						} else if (dbList[i].equals("public.permit_additional_info_entity")) {
							getPermitByIdResult.setPermitAdditionalInfoEntity(additionalInfoTab.getAdditionalInfo(sheet));
						} else  if (dbList[i].equals("public.permit_adv_entity")) {
							getPermitByIdResult.setPermitAdvEntityResult(advProjectInputsTab.getADVInputs(sheet));
						} else  if (dbList[i].equals("public.permit_weather_entity")) {
							getPermitByIdResult.setPermtiWeatherEntityResult(advProjectInputsTab.getWeatherInputs(sheet));
						} else  if (dbList[i].equals("public.permit_energy_battery_system")) {
							HSSFSheet batterySheet = workbook.getSheet("public.project_battery");
							getPermitByIdResult.setPermitEnergyBatterySystem(arraysTab.getBatterysystem(sheet,batterySheet));
						}
					}
		        }
		        HSSFSheet arraySheet = workbook.getSheet("public.permit_sketch_entity");
				getPermitByIdResult.setArrayLayout(layoutSketchTab.getSketchByPermit(arraySheet));
				
				
		        /***************** Get Project System Attributes *********************/
				SystemAttributesModel system = systemSpec.getProjectSystemAttributes(arrays, bos, conduitConductor);
				
				/***************** Get Inverter OCPD Attributes *********************/
				OCPDMainPanelModel ocpdMainPanelModel = new OCPDMainPanelModel(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
						0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
				if (Boolean.TRUE.equals(getProjectDetailsUtils
						.isStringOrOptimizerProject(arrays.getDeviceToIncorporate()))) {
					ocpdMainPanelModel = calculMinOCPDMainPanel.stringMinOCPDMainPanel(data.getIdProject(), arrays);
				}else if (arrays.getDeviceToIncorporate() != null && arrays.getDeviceToIncorporate().equals("AC Modules")) {
					ocpdMainPanelModel = calculMinOCPDMainPanel.acModuleMinOCPDMainPanel(data.getIdProject(), arrays);
				}
				
				getPermitByIdResult.setPermitArraysEntity(arrays);
				getPermitByIdResult.setPermitProjectSiteInfoEntityTwo(bos);						
				getPermitByIdResult.setPermitConduitConductorSection(conduitConductor);
				getPermitByIdResult.setSystemAttributes(system);
				getPermitByIdResult.setOcpdMainPanel(ocpdMainPanelModel);
			}

			return getPermitByIdResult;

		} catch (Exception e) {

			e.printStackTrace();
			return getPermitByIdResult;

		}

	
	}
	
}
