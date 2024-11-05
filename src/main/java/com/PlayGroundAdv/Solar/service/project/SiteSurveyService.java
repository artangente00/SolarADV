package com.PlayGroundAdv.Solar.service.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.transaction.Transactional;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.entity.ElectricalUtilityEntity;
import com.PlayGroundAdv.Solar.entity.InverterLibraryEntity;
import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.entity.PermitAdditionalInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitArraysEntity;
import com.PlayGroundAdv.Solar.entity.PermitCompanyInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitHomeSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitLayoutEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;
import com.PlayGroundAdv.Solar.entity.SiteSurveyArrayChartEntity;
import com.PlayGroundAdv.Solar.entity.SiteSurveyArrayChartNonRoofEntity;
import com.PlayGroundAdv.Solar.entity.SiteSurveyArrayChartPatioEntity;
import com.PlayGroundAdv.Solar.entity.SiteSurveyCostumFieldEntity;
import com.PlayGroundAdv.Solar.entity.SiteSurveyEntity;
import com.PlayGroundAdv.Solar.entity.SiteSurveyReqFieldLogicEntity;
import com.PlayGroundAdv.Solar.entity.SiteSurveyReqFieldSettingEntity;
import com.PlayGroundAdv.Solar.entity.SiteSurveyTextAreaFieldsEntity;
import com.PlayGroundAdv.Solar.entity.users.UserSettingEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.CostumFieldModelResult;
import com.PlayGroundAdv.Solar.model.LayoutSketchArraysModel;
import com.PlayGroundAdv.Solar.model.NewSiteSurveyModel;
import com.PlayGroundAdv.Solar.model.PermitResponse;
import com.PlayGroundAdv.Solar.model.PermitSketchResults;
import com.PlayGroundAdv.Solar.model.SiteSurveyCostumFieldModel;
import com.PlayGroundAdv.Solar.model.SiteSurveyFieldSetting;
import com.PlayGroundAdv.Solar.model.SiteSurveyModel;
import com.PlayGroundAdv.Solar.model.SiteSurveyResult;
import com.PlayGroundAdv.Solar.model.UserInformationResult;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.PermitAdditionalInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitArraysRepository;
import com.PlayGroundAdv.Solar.repositories.PermitCompanyInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitHomeSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitLayoutRepository;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.repositories.PermitRepository;
import com.PlayGroundAdv.Solar.repositories.PermitSketchRepository;
import com.PlayGroundAdv.Solar.repositories.SiteSurveyRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ElectricalUtilityRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InverterRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.InvertersFavoritesRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.ModuleRepository;
import com.PlayGroundAdv.Solar.repositories.libraries.RoofMaterialTypeRepository;
import com.PlayGroundAdv.Solar.repositories.site_survey.SiteSurveyArrayChartNonRoofRepository;
import com.PlayGroundAdv.Solar.repositories.site_survey.SiteSurveyArrayChartPatioRepository;
import com.PlayGroundAdv.Solar.repositories.site_survey.SiteSurveyArrayChartRepository;
import com.PlayGroundAdv.Solar.repositories.site_survey.SiteSurveyCostumFieldRepository;
import com.PlayGroundAdv.Solar.repositories.site_survey.SiteSurveyReqFieldLogicRepository;
import com.PlayGroundAdv.Solar.repositories.site_survey.SiteSurveyReqFieldSettingRepository;
import com.PlayGroundAdv.Solar.repositories.site_survey.SiteSurveyTextAreaFieldsRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.repositories.users.UserSettingRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;
import com.google.api.client.util.DateTime;

@Service
@Transactional
public class SiteSurveyService {

	
	final NewProjectService newProject;
	final CheckValueTypesService checkValue;
	final AuthenticationRepository authRepo;
	final UserSettingRepository userSettingRepo;
	final ModuleRepository moduleRepo;
	final InverterRepository inverterRepo;
	final InvertersFavoritesRepository inverterFavRepo;
	final PermitArraysRepository projectArraysRepo;
	final PathRepository pathRepo;
	final SiteSurveyRepository siteSurveyRepo;
	final PermitRepository permitRepo;
	final PermitHomeSiteInfoRepository permitHomeSiteInfoRepo;
	final PermitProjectSiteInfoRepository permitProjectSiteInfoRepo;
	final PermitCompanyInfoRepository permitCompanyInfoRepo;
	final PermitLayoutRepository permitLayoutRepo;
	final PermitAdditionalInfoRepository permitAdditionalInfoRepo;
	final PermitSketchRepository permitSketchRepo;
	final SiteSurveyArrayChartRepository siteSurveyArrayChartRepo;
	final SiteSurveyArrayChartPatioRepository siteSurveyArrayChartPatioRepo;
	final SiteSurveyArrayChartNonRoofRepository siteSurveyArrayChartNonRoofRepo;
	final SiteSurveyReqFieldSettingRepository siteSurveyReqFieldSettingRepo;
	final SiteSurveyReqFieldLogicRepository siteSurveyReqFieldLogicRepo;
	final SiteSurveyTextAreaFieldsRepository siteSurveyTextAreaFieldsRepo;
	final ElectricalUtilityRepository electricalUtilityRepo;
	final RoofMaterialTypeRepository roofMaterialTypeRepo;
	final SiteSurveyCostumFieldRepository siteSurveyCostumFieldRepo;
	
	public SiteSurveyService(NewProjectService newProject, CheckValueTypesService checkValue,
			AuthenticationRepository authRepo, UserSettingRepository userSettingRepo, ModuleRepository moduleRepo,
			InverterRepository inverterRepo, InvertersFavoritesRepository inverterFavRepo,
			PermitArraysRepository projectArraysRepo, PathRepository pathRepo, SiteSurveyRepository siteSurveyRepo,
			PermitRepository permitRepo, PermitHomeSiteInfoRepository permitHomeSiteInfoRepo,
			PermitProjectSiteInfoRepository permitProjectSiteInfoRepo, PermitCompanyInfoRepository permitCompanyInfoRepo,
			PermitLayoutRepository permitLayoutRepo, PermitAdditionalInfoRepository permitAdditionalInfoRepo,
			PermitSketchRepository permitSketchRepo, SiteSurveyArrayChartRepository siteSurveyArrayChartRepo,
			SiteSurveyArrayChartPatioRepository siteSurveyArrayChartPatioRepo,
			SiteSurveyArrayChartNonRoofRepository siteSurveyArrayChartNonRoofRepo,
			SiteSurveyReqFieldSettingRepository siteSurveyReqFieldSettingRepo,
			SiteSurveyReqFieldLogicRepository siteSurveyReqFieldLogicRepo,
			SiteSurveyTextAreaFieldsRepository siteSurveyTextAreaFieldsRepo,
			ElectricalUtilityRepository electricalUtilityRepo, RoofMaterialTypeRepository roofMaterialTypeRepo,
			SiteSurveyCostumFieldRepository siteSurveyCostumFieldRepo) {
		super();
		this.newProject = newProject;
		this.checkValue = checkValue;
		this.authRepo = authRepo;
		this.userSettingRepo = userSettingRepo;
		this.moduleRepo = moduleRepo;
		this.inverterRepo = inverterRepo;
		this.inverterFavRepo = inverterFavRepo;
		this.projectArraysRepo = projectArraysRepo;
		this.pathRepo = pathRepo;
		this.siteSurveyRepo = siteSurveyRepo;
		this.permitRepo = permitRepo;
		this.permitHomeSiteInfoRepo = permitHomeSiteInfoRepo;
		this.permitProjectSiteInfoRepo = permitProjectSiteInfoRepo;
		this.permitCompanyInfoRepo = permitCompanyInfoRepo;
		this.permitLayoutRepo = permitLayoutRepo;
		this.permitAdditionalInfoRepo = permitAdditionalInfoRepo;
		this.permitSketchRepo = permitSketchRepo;
		this.siteSurveyArrayChartRepo = siteSurveyArrayChartRepo;
		this.siteSurveyArrayChartPatioRepo = siteSurveyArrayChartPatioRepo;
		this.siteSurveyArrayChartNonRoofRepo = siteSurveyArrayChartNonRoofRepo;
		this.siteSurveyReqFieldSettingRepo = siteSurveyReqFieldSettingRepo;
		this.siteSurveyReqFieldLogicRepo = siteSurveyReqFieldLogicRepo;
		this.siteSurveyTextAreaFieldsRepo = siteSurveyTextAreaFieldsRepo;
		this.electricalUtilityRepo = electricalUtilityRepo;
		this.roofMaterialTypeRepo = roofMaterialTypeRepo;
		this.siteSurveyCostumFieldRepo = siteSurveyCostumFieldRepo;
	}

	public String getfilesPath() {
		
		String filePath="";
		
		try {
			filePath = pathRepo.findFilePath();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		return filePath;
	}
	
	/**
	 * ADD NEW SITE SURVEY
	 * @param siteSurvey
	 * @return
	 */
	public SiteSurveyModel addSiteSurvey(NewSiteSurveyModel siteSurvey){
		
		SiteSurveyModel siteSurveyModel = new SiteSurveyModel();
		try {
			SiteSurveyEntity siteSurveyEntity = new SiteSurveyEntity();			
			TimeZone.setDefault (  TimeZone.getTimeZone ("PST8PDT")) ;
			DateTime dt = new DateTime(new Date(),TimeZone.getTimeZone("PST8PDT"));
			
			SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			isoFormat.setTimeZone(TimeZone.getTimeZone("PST8PDT"));
			Date date = isoFormat.parse(dt+"");

			AuthentificationEntity user = authRepo.findById(Long.valueOf(siteSurvey.getOwnerID())).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +Long.valueOf(siteSurvey.getOwnerID())));

			
			siteSurveyEntity.setHomeOwnName(siteSurvey.getHomeOwnName());
			siteSurveyEntity.setHomeOwnLastName(siteSurvey.getHomeOwnLastName());
			siteSurveyEntity.setProjectName(siteSurvey.getProjectName());
			siteSurveyEntity.setStreetAddress(siteSurvey.getStreetAddress());
			if(siteSurvey.getBasicTypeOfSystem() != null && siteSurvey.getBasicTypeOfSystem().equals("Roof Mount")) {
				siteSurveyEntity.setRoofMounted(true);
			}else if(siteSurvey.getBasicTypeOfSystem() != null && siteSurvey.getBasicTypeOfSystem().equals("Ground Roof Mount")) {
				siteSurveyEntity.setGroundMounted(true);
			}else if(siteSurvey.getBasicTypeOfSystem() != null && siteSurvey.getBasicTypeOfSystem().equals("Patio Cover")) {
				siteSurveyEntity.setPatioMounted(true);
			}else if(siteSurvey.getBasicTypeOfSystem() != null && siteSurvey.getBasicTypeOfSystem().equals("Carport")) {
				siteSurveyEntity.setCarportMounted(true);
			}
			siteSurveyEntity.setState(siteSurvey.getState());
			siteSurveyEntity.setCreatedBy(user);
			
			//*******Default Config ********//
			siteSurveyEntity.setSubmitted(false);
			siteSurveyEntity.setCanceled(false);
			siteSurveyEntity.setStatus("Draft");
			siteSurveyEntity.setCreationDate(date);
			siteSurveyEntity.setHasProject(false);
			siteSurveyRepo.save(siteSurveyEntity);
			
			siteSurveyModel.setSiteSurveyID(siteSurveyEntity.getId());
			siteSurveyModel.setHomeOwnName(siteSurveyEntity.getHomeOwnName());
			siteSurveyModel.setHomeOwnLastName(siteSurveyEntity.getHomeOwnLastName());
			siteSurveyModel.setProjectName(siteSurveyEntity.getProjectName());
			siteSurveyModel.setStreetAddress(siteSurveyEntity.getStreetAddress()); ;
			siteSurveyModel.setBasicTypeOfSystem(siteSurveyEntity.getBasicTypeOfSystem());
			siteSurveyModel.setStatus(siteSurveyEntity.getStatus());
			siteSurveyModel.setCreationDate(siteSurveyEntity.getCreationDate().toString());
			
			
			if (siteSurveyEntity.getLastUpdatedDate() != null) {
				siteSurveyModel.setLastUpdatedDate(siteSurveyEntity.getLastUpdatedDate().toString());
			}
			if (siteSurveyEntity.getDateOfSubmit() != null) {
				siteSurveyModel.setDateOfSubmit(siteSurveyEntity.getDateOfSubmit().toString());
			}
			siteSurveyModel.setSubmitted(siteSurveyEntity.isSubmitted());
			siteSurveyModel.setOwnerID(getUserInformation(user.getId()).getId());
			siteSurveyModel.setOwnerFirstName(getUserInformation(user.getId()).getFirstName());
			siteSurveyModel.setOwnerLastName(getUserInformation(user.getId()).getLastName());
			siteSurveyModel.setOwnerCompany(getUserInformation(user.getId()).getCompany());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siteSurveyModel;
	}
	
	
	public UserInformationResult getUserInformation(Long userID) {
		
		try {
			return siteSurveyRepo.getUserInformationResult(userID);
		} catch (Exception e) {
			e.printStackTrace();
			return	new UserInformationResult();
		}
	}
	
	/**
	 * GET ALL SITE SURVEY DASHBOARD
	 * @param userID
	 * @return
	 */
	
	public List<SiteSurveyModel> getTenSiteSurvey(Long userID, int nbPage) {
		
		List<SiteSurveyModel> allSiteSurvey =  new ArrayList<>(); 
		Page<SiteSurveyEntity> siteSurvey = new PageImpl<>(new ArrayList<>());
		
		try {
			
			Pageable pageable = PageRequest.of(nbPage - 1 , 10, Sort.by("creationDate").descending());
			AuthentificationEntity user = authRepo.findById(userID).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +userID));
			
			if (user.getRoleEntity().getId() == 1 || user.getRoleEntity().getId() == 3 || user.getRoleEntity().getId() == 5) {
				if(checkValue.contains(user.getEmail(), "@nuagetechnologies-tn.com")) {
					 siteSurvey = siteSurveyRepo.findByIsCanceled(false, pageable);
				}else {
					 siteSurvey = siteSurveyRepo.getSiteSurveyNotEmail(false, "%@nuagetechnologies-tn.com", pageable);
				      }
				
				
			} else if (user.getRoleEntity().getId() == 2){
				if(checkValue.contains(user.getEmail(), "@nuagetechnologies-tn.com")) {
					siteSurvey = siteSurveyRepo.findByIsCanceledAndCreatedById(false, user.getId(), pageable);
				}else {
					siteSurvey = siteSurveyRepo.getSiteSurveyByIdNotEmail(false, user.getId(), "%@nuagetechnologies-tn.com", pageable);
					
				}
				
			}
			
			if (siteSurvey != null && !siteSurvey.getContent().isEmpty()) {
				Long sizeSS = siteSurvey.getTotalElements();
				return siteSurvey.getContent().stream()
						.map(siteSurveyModel -> new SiteSurveyModel(siteSurveyModel.getId(),
								siteSurveyModel.getHomeOwnName(),
								siteSurveyModel.getHomeOwnLastName(),
								siteSurveyModel.getProjectName(),
								siteSurveyModel.getStreetAddress(),
								siteSurveyModel.getBasicTypeOfSystem(),
								siteSurveyModel.getStatus(),
								siteSurveyModel.getCreationDate() != null ? siteSurveyModel.getCreationDate().toString() : null,
								siteSurveyModel.getLastUpdatedDate() != null ? siteSurveyModel.getLastUpdatedDate().toString() : null,
								siteSurveyModel.getDateOfSubmit() != null ? siteSurveyModel.getDateOfSubmit().toString() : null,
								siteSurveyModel.isSubmitted(),
								getUserInformation(siteSurveyModel.getCreatedBy().getId()).getId(),
								getUserInformation(siteSurveyModel.getCreatedBy().getId()).getFirstName(),
								getUserInformation(siteSurveyModel.getCreatedBy().getId()).getLastName(),
								getUserInformation(siteSurveyModel.getCreatedBy().getId()).getCompany(),
								siteSurveyModel.isHasProjectSiteImage(),sizeSS)).collect(Collectors.toList());

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return allSiteSurvey;
	}
	
	/**
	 * GET SITE SURVEY FOR EDIT
	 * @param siteSurveyID
	 * @return
	 */
	public SiteSurveyResult getSiteSurvey(Long siteSurveyID){
		
		SiteSurveyResult siteSurvey =  new SiteSurveyResult(); 
		
		try {
				siteSurvey = siteSurveyRepo.getSiteSurveyResult(siteSurveyID);
				//A.B 01-16 Test Romoved & Fav Removed
				if (siteSurvey.getInverter1Model() != null) {
					siteSurvey.setInverter1Model(testInverterRemoved(siteSurvey.getInverter1Model(), siteSurvey.getCreatedBy(),siteSurvey.getId(),1)) ;
				}
				if (siteSurvey.getInverter2Model() != null) {
					siteSurvey.setInverter2Model(testInverterRemoved(siteSurvey.getInverter2Model(), siteSurvey.getCreatedBy(),siteSurvey.getId(),2)) ;
				}
				if (siteSurvey.getInverter3Model() != null) {
					siteSurvey.setInverter3Model(testInverterRemoved(siteSurvey.getInverter3Model(), siteSurvey.getCreatedBy(),siteSurvey.getId(),3)) ;
				}
				if(siteSurvey.getMaxSpanAtArraysHS1() == null) siteSurvey.setMaxSpanAtArraysHS1("");
				if(siteSurvey.getMaxSpanAtArraysInchesHS1() == null) siteSurvey.setMaxSpanAtArraysInchesHS1("");
				if(siteSurvey.getMaxSpanAtArraysHS2() == null) siteSurvey.setMaxSpanAtArraysHS2("");
				if(siteSurvey.getMaxSpanAtArraysInchesHS2() == null) siteSurvey.setMaxSpanAtArraysInchesHS2("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return siteSurvey;
	}
	
	/**
	 * SAVE SITE SURVEY
	 * @param siteSurvey
	 * @return RESULT
	 */
	
	public String saveAsDraft(SiteSurveyResult siteSurvey){
		
		try {
			TimeZone.setDefault (  TimeZone.getTimeZone ("PST8PDT")) ;
			DateTime dt = new DateTime(new Date(),TimeZone.getTimeZone("PST8PDT"));
			SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			isoFormat.setTimeZone(TimeZone.getTimeZone("PST8PDT"));
			Date date = isoFormat.parse(dt+"");
			
			SiteSurveyEntity siteSurveyEntity = siteSurveyRepo.findByIdAndIsCanceled(siteSurvey.getId(), false);
			
			if (siteSurveyEntity != null) {
				
				siteSurveyEntity.setHomeOwnName(siteSurvey.getHomeOwnName());
				siteSurveyEntity.setHomeOwnLastName(siteSurvey.getHomeOwnLastName());
				siteSurveyEntity.setProjectName(siteSurvey.getProjectName());
				siteSurveyEntity.setStatus("In Progress");
				
				/////------------- action dates -----------/////
				
				siteSurveyEntity.setLastUpdatedDate(date);
				
				/////------------- site information -----------/////
				
				siteSurveyEntity.setRoofMounted(siteSurvey.getRoofMounted());
				siteSurveyEntity.setGroundMounted(siteSurvey.getGroundMounted());
				siteSurveyEntity.setCarportMounted(siteSurvey.getCarportMounted());
				siteSurveyEntity.setPatioMounted(siteSurvey.getPatioMounted());
				siteSurveyEntity.setOtherMounted(siteSurvey.getOtherMounted());
				siteSurveyEntity.setTextOther(siteSurvey.getTextOther());
				siteSurveyEntity.setContactName(siteSurvey.getContactName()); 
				siteSurveyEntity.setStreetAddress(siteSurvey.getStreetAddress()); 
				siteSurveyEntity.setAddressLine2(siteSurvey.getAddressLine2());
				siteSurveyEntity.setCity(siteSurvey.getCity()); 
				siteSurveyEntity.setState(siteSurvey.getState()); 
				siteSurveyEntity.setzIP(siteSurvey.getzIP()); 
				siteSurveyEntity.setMainContactPhone(siteSurvey.getMainContactPhone());  
				siteSurveyEntity.setOtherPhone(siteSurvey.getOtherPhone()); 
				siteSurveyEntity.setEmailAddress(siteSurvey.getEmailAddress());
				siteSurveyEntity.setHoa(siteSurvey.getHoa()); 
				siteSurveyEntity.setPermittingAuthority(siteSurvey.getPermittingAuthority()); 
				siteSurveyEntity.setLegalOwnerName(siteSurvey.getLegalOwnerName()); 
				siteSurveyEntity.setParcelNumber(siteSurvey.getParcelNumber()); 
				siteSurveyEntity.setRoofMaterialType(siteSurvey.getRoofMaterialType()); 
				siteSurveyEntity.setWidthSeams(siteSurvey.getWidthSeams());
				siteSurveyEntity.setWidthSeamsOther(siteSurvey.getWidthSeamsOther());
				siteSurveyEntity.setRiskCategory(siteSurvey.getRiskCategory());
				siteSurveyEntity.setOtherRiskCategory(siteSurvey.getOtherRiskCategory());
				siteSurveyEntity.setBuildingOccupancy(siteSurvey.getBuildingOccupancy());
				siteSurveyEntity.setOtherBuildingOccupancy(siteSurvey.getOtherBuildingOccupancy());
				siteSurveyEntity.setNumberOfStories(siteSurvey.getNumberOfStories());
				siteSurveyEntity.setMeanHeight(siteSurvey.getMeanHeight());
				siteSurveyEntity.setProjectEquipmentStagingLocation(siteSurvey.getProjectEquipmentStagingLocation()); 
				siteSurveyEntity.setOwnerPreferredRoofAccessLocation(siteSurvey.getOwnerPreferredRoofAccessLocation());
				siteSurveyEntity.setAccessIssuesWith_MeterOrProposedACDisco(siteSurvey.getAccessIssuesWith_MeterOrProposedACDisco()); 
				siteSurveyEntity.setUtilityMeterOrProposedACDisco(siteSurvey.isUtilityMeterOrProposedACDisco()); 
				siteSurveyEntity.setUnrestrainedAnimal(siteSurvey.isUnrestrainedAnimal()); 
				siteSurveyEntity.setOtheraccessissue(siteSurvey.isOtheraccessissue()); 
				siteSurveyEntity.setDescribeAccessIssues(siteSurvey.getDescribeAccessIssues()); 
				siteSurveyEntity.setContactpersonforutility(siteSurvey.getContactpersonforutility()); 
				siteSurveyEntity.setOthercontactperson(siteSurvey.getOthercontactperson()); 
				siteSurveyEntity.setContactphone(siteSurvey.getContactphone()); 
				siteSurveyEntity.setOthercontactphone(siteSurvey.getOthercontactphone()); 
				siteSurveyEntity.setProjectJurisOther(siteSurvey.getProjectJurisOther());
				siteSurveyEntity.setFrontAndBack(siteSurvey.getFrontAndBack());
				siteSurveyEntity.setCantelever(siteSurvey.getCantelever());
				siteSurveyEntity.setAttachedToExtWal(siteSurvey.getAttachedToExtWal());
				siteSurveyEntity.setAttachedToFascia(siteSurvey.getAttachedToFascia());
				siteSurveyEntity.setAttachedToSkylifts(siteSurvey.getAttachedToSkylifts());
				siteSurveyEntity.setFreeStanding(siteSurvey.getFreeStanding());
				siteSurveyEntity.setRoofOrOpenFrame(siteSurvey.getRoofOrOpenFrame());
				siteSurveyEntity.setSumofexistCircuit(siteSurvey.getSumofexistCircuit());
				siteSurveyEntity.setSecondOtherVoltageOther(siteSurvey.getSecondOtherVoltageOther());
				siteSurveyEntity.setNotesExistingPvSystem(siteSurvey.getNotesExistingPvSystem());
				siteSurveyEntity.setNetworkName(siteSurvey.getNetworkName());
				siteSurveyEntity.setNetworkPassword(siteSurvey.getNetworkPassword());
				siteSurveyEntity.setRoofCondition(siteSurvey.getRoofCondition());
				siteSurveyEntity.setRoofNotes(siteSurvey.getRoofNotes());
				siteSurveyEntity.setMeasurmentsOfArea(siteSurvey.getMeasurmentsOfArea());
				siteSurveyEntity.setNotesGroundMount(siteSurvey.getNotesGroundMount());
				siteSurveyEntity.setNotesOnCarpotOrPatiot(siteSurvey.getNotesOnCarpotOrPatiot());
				siteSurveyEntity.setCityOther(siteSurvey.getCityOther());
				
				/////------------- existing main panel -----------/////
				
				siteSurveyEntity.setCenterFed(siteSurvey.isCenterFed());
				siteSurveyEntity.setMainPanelMake(siteSurvey.getMainPanelMake()); 
				siteSurveyEntity.setOtherMainPanelMake(siteSurvey.getOtherMainPanelMake());
				siteSurveyEntity.setMainPanelModel(siteSurvey.getMainPanelModel()); 
				siteSurveyEntity.setUpgradingMainServicePanel(siteSurvey.isUpgradingMainServicePanel());
				siteSurveyEntity.setVoltage(siteSurvey.getVoltage()); 
				siteSurveyEntity.setOtherVoltage(siteSurvey.getOtherVoltage()); 
				siteSurveyEntity.setOtherVoltageOther(siteSurvey.getOtherVoltageOther());
				siteSurveyEntity.setMspbusbarRating(siteSurvey.getMspbusbarRating());
				siteSurveyEntity.setOtherMSPBusbarRating(siteSurvey.getOtherMSPBusbarRating());
				siteSurveyEntity.setmSPMainBreakerRating(siteSurvey.getmSPMainBreakerRating());
				siteSurveyEntity.setOtherMSPMainBreakerRating(siteSurvey.getOtherMSPMainBreakerRating());
				siteSurveyEntity.setOpenBreakerSlots(siteSurvey.getOpenBreakerSlots()); 
				siteSurveyEntity.setsCIR(siteSurvey.getsCIR());
				siteSurveyEntity.setMainBreakerFeedGauge(siteSurvey.getMainBreakerFeedGauge()); 
				siteSurveyEntity.setOtherMainBreakerFeedGauge(siteSurvey.getOtherMainBreakerFeedGauge());
				siteSurveyEntity.setUtilityFeederGauge(siteSurvey.getUtilityFeederGauge());
				siteSurveyEntity.setCopperWiresSameAsMainContact(siteSurvey.isCopperWiresSameAsMainContact());
				siteSurveyEntity.setCopperWiresDifferentNumber(siteSurvey.isCopperWiresDifferentNumber());
				siteSurveyEntity.setAluminumWiresSameAsMainContact(siteSurvey.isAluminumWiresSameAsMainContact());
				siteSurveyEntity.setAluminumWiresDifferentNumber(siteSurvey.isAluminumWiresDifferentNumber());
				siteSurveyEntity.setGroundAccessible(siteSurvey.getGroundAccessible());
				siteSurveyEntity.setUndergroundOrOverheadFeed(siteSurvey.getUndergroundOrOverheadFeed());

				/////------------- existing subpanel -----------/////
				
				siteSurveyEntity.setExistingSubpanel(siteSurvey.isExistingSubpanel()); 
				siteSurveyEntity.setTieInPOCIsAtSubpanel(siteSurvey.isTieInPOCIsAtSubpanel());
				siteSurveyEntity.setSubpanelMake(siteSurvey.getSubpanelMake());
				siteSurveyEntity.setSubpanelModel(siteSurvey.getSubpanelModel());
				siteSurveyEntity.setSubpanelVoltage(siteSurvey.getSubpanelVoltage());
				siteSurveyEntity.setOtherSubpanelVoltage(siteSurvey.getOtherSubpanelVoltage());
				siteSurveyEntity.setSubpanelBusbarRating(siteSurvey.getSubpanelBusbarRating());
				siteSurveyEntity.setOtherSubpanelBusbarRating(siteSurvey.getOtherSubpanelBusbarRating());
				siteSurveyEntity.setSubpanelMainBreakerRating(siteSurvey.getSubpanelMainBreakerRating());
				siteSurveyEntity.setOtherSubpanelMainBreakerRating(siteSurvey.getOtherSubpanelMainBreakerRating());
				siteSurveyEntity.setOpenBreakerSlotsinSubpanel(siteSurvey.getOpenBreakerSlotsinSubpanel());
				siteSurveyEntity.setSubpanelFeederGauge(siteSurvey.getSubpanelFeederGauge());
				siteSurveyEntity.setOtherSubpanelFeederGauge(siteSurvey.getOtherSubpanelFeederGauge());
				// 06/08/2019 : CI  : CR 2680
				siteSurveyEntity.setSubPanelBreakerOCPD(siteSurvey.getSubPanelBreakerOCPD());
				
				/////------------- site note -----------/////
				 
				siteSurveyEntity.setSiteNotes(siteSurvey.getSiteNotes());
				
				/////------------- utility information -----------/////
				
				siteSurveyEntity.setUtilityCoName(siteSurvey.getUtilityCoName());
				siteSurveyEntity.setMeterNumber(siteSurvey.getMeterNumber());
				siteSurveyEntity.setnEMType(siteSurvey.getnEMType());
				siteSurveyEntity.setOtherNEMType(siteSurvey.getOtherNEMType());
				siteSurveyEntity.setPostSolarRate(siteSurvey.getPostSolarRate());
				siteSurveyEntity.setPostSolarRateOther(siteSurvey.getPostSolarRateOther());
				siteSurveyEntity.setUsageHistoryOffset(siteSurvey.getUsageHistoryOffset());
				siteSurveyEntity.setPaceEntity(siteSurvey.getPaceEntity());
				siteSurveyEntity.setPaceEntityOther(siteSurvey.getPaceEntityOther());
				siteSurveyEntity.setaCDiscoWillbemorethan10FromUtilityMeter(siteSurvey.getaCDiscoWillbemorethan10FromUtilityMeter());
				siteSurveyEntity.setAtLeast4MonthsOfelectricbillinghistory(siteSurvey.getAtLeast4MonthsOfelectricbillinghistory()); 
				siteSurveyEntity.setSquareFeetOfLivingArea(siteSurvey.getSquareFeetOfLivingArea());
				siteSurveyEntity.setRecentAnnualUsage(siteSurvey.getRecentAnnualUsage());
				siteSurveyEntity.setNumberOfElectricVehicles(siteSurvey.getNumberOfElectricVehicles()); 
				
				/////------------- existing pV system at site -----------/////
				
				siteSurveyEntity.setExistingBattery(siteSurvey.isExistingBattery());
				siteSurveyEntity.setNotesOnExistingBatterySystem(siteSurvey.getNotesOnExistingBatterySystem());
				siteSurveyEntity.setExistingPVSystemAtSite(siteSurvey.isExistingPVSystemAtSite());
				siteSurveyEntity.setpVSystemMake(siteSurvey.getpVSystemMake());
				siteSurveyEntity.setpVSystemModel(siteSurvey.getpVSystemModel());
				siteSurveyEntity.setpVQuantity(siteSurvey.getpVQuantity());
				siteSurveyEntity.setGridTiedInverterMake(siteSurvey.getGridTiedInverterMake());
				siteSurveyEntity.setGridTiedInverterModel(siteSurvey.getGridTiedInverterModel());
				siteSurveyEntity.setGridTiedInverterQuantity(siteSurvey.getGridTiedInverterQuantity());
				siteSurveyEntity.setExistingACDisco(siteSurvey.getExistingACDisco());
				siteSurveyEntity.setaCDiscoMake(siteSurvey.getaCDiscoMake());
				siteSurveyEntity.setaCDiscoModel(siteSurvey.getaCDiscoModel()); 
				siteSurveyEntity.setACDiscoRating(siteSurvey.getACDiscoRating()); 
				siteSurveyEntity.setConnectionType(siteSurvey.getConnectionType());
				siteSurveyEntity.setOtherConnectionType(siteSurvey.getOtherConnectionType());
				siteSurveyEntity.setpVBreaker1(siteSurvey.getpVBreaker1());
				siteSurveyEntity.setpVBreaker2(siteSurvey.getpVBreaker2());
				siteSurveyEntity.setpVBreaker3(siteSurvey.getpVBreaker3());
				siteSurveyEntity.setpVBreaker4(siteSurvey.getpVBreaker4());
				siteSurveyEntity.setpVBreaker5(siteSurvey.getpVBreaker5()); 

				/////------------- battery info -----------/////
				
				
				siteSurveyEntity.setHasImageOfExistingSolarEquipLocations(siteSurvey.isHasImageOfExistingSolarEquipLocations());
				siteSurveyEntity.setProposedBattery(siteSurvey.isProposedBattery());
				siteSurveyEntity.setCircuitstoRelocatetoCriticalLoadsPanel(siteSurvey.getCircuitstoRelocatetoCriticalLoadsPanel());
				siteSurveyEntity.setBatteryLocation(siteSurvey.getBatteryLocation());
				
				//A.B 04-16 CR-3255-007
				siteSurveyEntity.setInverterTechnology(siteSurvey.getInverterTechnology());
				siteSurveyEntity.setNotesOnInverter(siteSurvey.getNotesOnInverter());
				siteSurveyEntity.setTypeOfBatterySystem(siteSurvey.getTypeOfBatterySystem());
				siteSurveyEntity.setCriticalLoadPanelLocation(siteSurvey.getCriticalLoadPanelLocation());
				
				//A.B 01-20
				if (siteSurvey.getInverter1Model() != null) {
					Inverters inverter  = inverterRepo.findById(siteSurvey.getInverter1Model()).get();
					siteSurveyEntity.setFirstInverter(inverter);
				}
				if (siteSurvey.getInverter2Model() != null) {
					Inverters inverter  = inverterRepo.findById(siteSurvey.getInverter2Model()).get();
					siteSurveyEntity.setSecondInverter(inverter);
				}
				if (siteSurvey.getInverter3Model() != null) {
					Inverters inverter  = inverterRepo.findById(siteSurvey.getInverter3Model()).get();
					siteSurveyEntity.setThirdInverter(inverter);
				}
				siteSurveyEntity.setInverter1Model(null);
				siteSurveyEntity.setInverter2Model(null);
				siteSurveyEntity.setInverter3Model(null);
		

				/////------------- internet connection for production monitoring -----------/////	
				
				siteSurveyEntity.setProductionMonitor(siteSurvey.getProductionMonitor());
				siteSurveyEntity.setActiveInternetConnection(siteSurvey.getActiveInternetConnection());
				siteSurveyEntity.setModemLocation(siteSurvey.getModemLocation());
				siteSurveyEntity.setConnectTheMonitor(siteSurvey.getConnectTheMonitor()); 
				siteSurveyEntity.setVerifyRoofMeasurementsPreRoofLayoutProvided(siteSurvey.getVerifyRoofMeasurementsPreRoofLayoutProvided());
				
				/////------------- roof mount -----------/////
				
				siteSurveyEntity.setWireRunOnRoof(siteSurvey.isWireRunOnRoof());
				siteSurveyEntity.setWireRunInAttic(siteSurvey.isWireRunInAttic());
				siteSurveyEntity.setTiltupModules(siteSurvey.isTiltupModules());
				siteSurveyEntity.setRoofMaterial(siteSurvey.getRoofMaterial());
				siteSurveyEntity.setNumberOfLayers(siteSurvey.getNumberOfLayers());
				siteSurveyEntity.setRoofAge(siteSurvey.getRoofAge());
				if (checkValue.isLongPositive(siteSurvey.getpVModel())) {
					Cmodulev2 module  = moduleRepo.findById(siteSurvey.getpVModel()).get();
					siteSurveyEntity.setPvModule(module);
				}
				if (checkValue.isLongPositive(siteSurvey.getpVModelCarpotPatio())) {
					Cmodulev2 module  = moduleRepo.findById(siteSurvey.getpVModelCarpotPatio()).get();
				siteSurveyEntity.setPvModuleCarpotPatio(module);
				}
				if (checkValue.isLongPositive(siteSurvey.getpVModelNonRoof())) {
					Cmodulev2 module  = moduleRepo.findById(siteSurvey.getpVModelNonRoof()).get();
				siteSurveyEntity.setPvModuleNonRoof(module);
				}
				siteSurveyEntity.setGableRakeOverhang(siteSurvey.getGableRakeOverhang());
				siteSurveyEntity.setHeightAtGutter(siteSurvey.getHeightAtGutter());
				siteSurveyEntity.setCrossSectionSize(siteSurvey.getCrossSectionSize()); 
				siteSurveyEntity.setCrossSectionSizeOther(siteSurvey.getCrossSectionSizeOther()); 
				siteSurveyEntity.setRoofStructureChart(siteSurvey.getRoofStructureChart());
				siteSurveyEntity.setStanchionMaxSpacing(siteSurvey.getStanchionMaxSpacing()); 
				siteSurveyEntity.setRidgeBeamDepthAtArrays(siteSurvey.getRidgeBeamDepthAtArrays());
				siteSurveyEntity.setMaxSpanAtArraysHS1(siteSurvey.getMaxSpanAtArraysHS1());
				siteSurveyEntity.setMaxSpanAtArraysHS2(siteSurvey.getMaxSpanAtArraysHS2());
				siteSurveyEntity.setMaxSpanAtArraysInchesHS1(siteSurvey.getMaxSpanAtArraysInchesHS1());
				siteSurveyEntity.setMaxSpanAtArraysInchesHS2(siteSurvey.getMaxSpanAtArraysInchesHS2());
				siteSurveyEntity.setRafterTrussSpacing(siteSurvey.getRafterTrussSpacing());
				siteSurveyEntity.setRafterTrussSpOther(siteSurvey.getRafterTrussSpOther());
				//07/08/2019 / CI : CR 2860 : Save field other roof rafter truss
				siteSurveyEntity.setRoofRafterOther(siteSurvey.getRoofRafterOther());
				siteSurveyEntity.setSecroofRafterOther(siteSurvey.getSecroofRafterOther());
				/////------------- non roof mount -----------/////
				
				siteSurveyEntity.setNonRoofCarport(siteSurvey.isNonRoofCarport());
				siteSurveyEntity.setNonRoofPatioCover(siteSurvey.isNonRoofPatioCover());
				siteSurveyEntity.setNonRoofContourSlope(siteSurvey.getNonRoofContourSlope());
				siteSurveyEntity.setNonRoofPathPoint(siteSurvey.getNonRoofPathPoint());
				siteSurveyEntity.setNonRoofGradingGrubbing(siteSurvey.getNonRoofGradingGrubbing());
				siteSurveyEntity.setNonRoofSiteComposition(siteSurvey.getNonRoofSiteComposition());
				siteSurveyEntity.setNonRoofElevationStructure(siteSurvey.getNonRoofElevationStructure());
				siteSurveyEntity.setNonRoofExistingSecurity(siteSurvey.getNonRoofExistingSecurity());
				siteSurveyEntity.setNonRoofPatioCoverValue(siteSurvey.getNonRoofPatioCoverValue());	
				
				/////------------- Patio cover attached -----------/////
				siteSurveyEntity.setPatioCoverAttachedTypeBeam(siteSurvey.getPatioCoverAttachedTypeBeam());	
				siteSurveyEntity.setPatioCoverAttachedTypePosts(siteSurvey.getPatioCoverAttachedTypePosts());	
				/////------------- Patio cover freestanding -----------/////
				
				siteSurveyEntity.setPatioCoverFreestandingTypeBeam(siteSurvey.getPatioCoverFreestandingTypeBeam());	
				siteSurveyEntity.setPatioCoverFreestandingTypePosts(siteSurvey.getPatioCoverFreestandingTypePosts());	
				siteSurveyEntity.setPatioCoverFreestandingExtendOver(siteSurvey.getPatioCoverFreestandingExtendOver());	
				siteSurveyEntity.setPatioCoverFreestandingPastEave(siteSurvey.getPatioCoverFreestandingPastEave());
				siteSurveyEntity.setOtherPatioCoverFreestandingTypeBeam(siteSurvey.getOtherPatioCoverFreestandingTypeBeam());	
				siteSurveyEntity.setOtherPatioCoverFreestandingTypePosts(siteSurvey.getOtherPatioCoverFreestandingTypePosts());	
				siteSurveyEntity.setOtherTallStructure(siteSurvey.getOtherTallStructure());
				siteSurveyEntity.setUtilityCompanyNameOther(siteSurvey.getUtilityCompanyNameOther());
				siteSurveyEntity.setMeasOfAreaCarpot(siteSurvey.getMeasOfAreaCarpot());
				
				siteSurveyRepo.save(siteSurveyEntity);
				
				return "Done";
				
			}else return "Fail";
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}
	
	/**
	 * SUBMIT SITE SURVEY
	 * @param siteSurvey
	 * @return RESULT 
	 */
	public String submitSiteSurvey(SiteSurveyResult siteSurvey) {

		try {
			TimeZone.setDefault(TimeZone.getTimeZone("PST8PDT"));
			DateTime dt = new DateTime(new Date(), TimeZone.getTimeZone("PST8PDT"));
			SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			isoFormat.setTimeZone(TimeZone.getTimeZone("PST8PDT"));
			Date date = isoFormat.parse(dt + "");

			SiteSurveyEntity siteSurveyEntity = siteSurveyRepo.findByIdAndIsCanceled(siteSurvey.getId(), false);
			String existProject = checkDuplicatedProject(siteSurvey.getProjectName(), siteSurvey.getHomeOwnName(),
					siteSurvey.getHomeOwnLastName(), siteSurvey.getId());

			if (checkValue.Equals(existProject, "exist")) {
				return "exist";
			}

			if (siteSurveyEntity != null) {

				siteSurveyEntity.setStatus("Submitted");
				siteSurveyEntity.setSubmitted(true);
				///// ------------- action dates -----------/////
				siteSurveyEntity.setDateOfSubmit(date);

				// 29/08/2019/ CI : CR 2875 : Enter the Site Survey as a Project on the Projects
				// Dashboard when the Site Survey has been Submitted
				UserSettingEntity userSetting = userSettingRepo.findByUserIdId(siteSurvey.getCreatedBy());
				
				if (siteSurveyEntity.getHasProject() == null || checkValue.Equals(siteSurveyEntity.getHasProject(), false) || siteSurveyEntity.getPortalProject() == null) {
					if (userSetting != null && checkValue.Equals(userSetting.getSolarPermit(), true)) {
						String permitEntity = newProject.addPermit(siteSurvey.getHomeOwnName(),
								siteSurvey.getCreatedBy(), false, siteSurvey.getHomeOwnLastName(),
								siteSurvey.getProjectName());
						
						if (!checkValue.Equals(permitEntity, "exist")) {
							PermitEntity permit = permitRepo.findByIdAndIsCanceled(Long.valueOf(permitEntity), false);
							if (permit != null) {
								siteSurveyEntity.setPortalProject(permit);
								siteSurveyEntity.setHasProject(true);
								siteSurvey.setPortalProject(permit.getId());
								siteSurvey.setHasProject(true);
							}
						}

					} else {
						siteSurveyEntity.setHasProject(false);
						siteSurvey.setHasProject(false);
					}
						
				}
				siteSurveyRepo.save(siteSurveyEntity);
			}
		
			if (Boolean.TRUE.equals(siteSurveyEntity.isHasProject()) && checkValue.isLongPositive(siteSurvey.getPortalProject())) {
				return synchronizationproject(siteSurvey);
			} else
				return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}
	
	
	
	public String synchronizationproject(SiteSurveyResult siteSurvey) {
		try {

			
			TimeZone.setDefault (TimeZone.getTimeZone ("PST8PDT")) ;
			Calendar updatingDate = Calendar.getInstance(); 
			
			UserSettingEntity userSetting = new UserSettingEntity();
			String inverterTechnology="";
			PermitEntity permitEntity = permitRepo.findByIdAndIsCanceled(siteSurvey.getPortalProject(), false);
			if (permitEntity != null) {
					permitEntity.setHomeOwnName(siteSurvey.getHomeOwnName());
					permitEntity.setHomeOwnLastName(siteSurvey.getHomeOwnLastName());
					permitEntity.setProjectName(siteSurvey.getProjectName());
					permitEntity.setUpdateDate(updatingDate.getTime());
					userSetting =  userSettingRepo.findByUserIdId(permitEntity.getAuthentificationEntity().getId());
					permitRepo.save(permitEntity);
									
			}
			 
					PermitHomeSiteInfoEntity permitHomeSiteInfoEntity = permitHomeSiteInfoRepo.findOneByPermitEntityId(siteSurvey.getPortalProject());
			if (permitHomeSiteInfoEntity!= null) {
					permitHomeSiteInfoEntity.setSiteAddress(siteSurvey.getStreetAddress());
					permitHomeSiteInfoEntity.setAddressLine2(siteSurvey.getAddressLine2()); 
					permitHomeSiteInfoEntity.setCity(siteSurvey.getCity());
					permitHomeSiteInfoEntity.setCityOther(siteSurvey.getCityOther());
					permitHomeSiteInfoEntity.setState(siteSurvey.getState());
					permitHomeSiteInfoEntity.setPostalCode(siteSurvey.getzIP());
					permitHomeSiteInfoEntity.setHomePhone(siteSurvey.getMainContactPhone());
					permitHomeSiteInfoEntity.setOtherPhone(siteSurvey.getOtherPhone()); 
					permitHomeSiteInfoEntity.setEmailPhone(siteSurvey.getEmailAddress());
					permitHomeSiteInfoEntity.setProjectJurisdiction(siteSurvey.getPermittingAuthority());
					permitHomeSiteInfoEntity.setPropertyAPN(siteSurvey.getParcelNumber());
					if(siteSurvey.getRiskCategory()!=null) {
						if (checkValue.Equals(siteSurvey.getRiskCategory(), "Other")) {
							permitHomeSiteInfoEntity.setBuildingRisk("Other");
							permitHomeSiteInfoEntity.setBuildingRiskOther(siteSurvey.getOtherRiskCategory());

						} else {
							if(checkValue.Equals(siteSurvey.getRiskCategory(), "Residential (II)")) {
								permitHomeSiteInfoEntity.setBuildingRisk("Risk Category II");
							}else {
								permitHomeSiteInfoEntity.setBuildingRisk(siteSurvey.getRiskCategory());
							}
							
						}
				     }
					
					permitHomeSiteInfoEntity.setBuildingOccupancy(siteSurvey.getBuildingOccupancy());
					permitHomeSiteInfoEntity.setTextOtherBuildOccup(siteSurvey.getOtherBuildingOccupancy());


					// 06/08/2019 : CI : CR2680 : if “240V single phase (Std. Residential Serv.)” is
					// checked in Voltage field, the "Service is 240V Single (Split) Phase" checkbox
					// on the Homeowner/Site Info Tab should be checked
					if (checkValue.Equals(siteSurvey.getVoltage(),
							"240V single phase (Std. Residential Serv.)")) {
						permitHomeSiteInfoEntity.setIfServiceVoltage(true);
					} else {
						permitHomeSiteInfoEntity.setIfServiceVoltage(false);
						if (checkValue.Equals(siteSurvey.getVoltage(), "Other")) {
							if (checkValue.Equals(siteSurvey.getOtherVoltage(),
									"Three Phase Four Wire Wye 120/208V")) {
								permitHomeSiteInfoEntity.setServiceVoltage("120/208V");
							} else if (checkValue.Equals(siteSurvey.getOtherVoltage(),
									"Three Phase Four Wire Wye 277/480V")) {
								permitHomeSiteInfoEntity.setServiceVoltage("277/480V");
							} else if (checkValue.Equals(siteSurvey.getOtherVoltage(),
									"Three Phase Three Wire Delta 240V")) {
								permitHomeSiteInfoEntity.setServiceVoltage("240V");
							} else if (checkValue.Equals(siteSurvey.getOtherVoltage(),
									"Three Phase Three Wire Delta 400V")) {
								permitHomeSiteInfoEntity.setServiceVoltage("400V");
							} else if (checkValue.Equals(siteSurvey.getOtherVoltage(),
									"Three Phase Three Wire Delta 480V")) {
								permitHomeSiteInfoEntity.setServiceVoltage("480V");
							} else if (checkValue.Equals(siteSurvey.getOtherVoltage(),
									"Three Phase Three Wire Delta 600V")) {
								permitHomeSiteInfoEntity.setServiceVoltage("600V");
							} else if (checkValue.Equals(siteSurvey.getOtherVoltage(), "Other")) {
								permitHomeSiteInfoEntity.setServiceVoltage("Other");
								permitHomeSiteInfoEntity.setServiceVoltageOther(siteSurvey.getOtherVoltageOther());
							}else {
								permitHomeSiteInfoEntity.setServiceVoltage(siteSurvey.getOtherVoltage());
							}

						}

					}
					permitHomeSiteInfoEntity.setUtilityCompanyName(siteSurvey.getUtilityCoName());
					permitHomeSiteInfoEntity.setMeterNumber(siteSurvey.getMeterNumber());
					permitHomeSiteInfoEntity.setUtilityCompanyNameOther(siteSurvey.getUtilityCompanyNameOther());
					permitHomeSiteInfoEntity.setRoofRafter(siteSurvey.getRoofStructureChart());
					permitHomeSiteInfoEntity.setRoofRafterOther(siteSurvey.getRoofRafterOther());
					permitHomeSiteInfoEntity.setSecroofRafterOther(siteSurvey.getSecroofRafterOther());
					permitHomeSiteInfoEntity.setStanchionMaxSpacing(siteSurvey.getStanchionMaxSpacing());
					
					// 07/08/2019 CI : CR 2860 : Change Ridge Beam Depth at Arrays” logic mapping to
					// “Roof Member Cross Section Size” on Homeowner/Site Info Tab
					if (checkValue.Equals(siteSurvey.getRidgeBeamDepthAtArrays(), "3 1/2 - nominal: 2X4")) {
						permitHomeSiteInfoEntity.setRidgeBeamDepthAtArrays("3 1/2");
					} else if (checkValue.Equals(siteSurvey.getRidgeBeamDepthAtArrays(), "5 1/2 - nominal: 2X6")) {
						permitHomeSiteInfoEntity.setRidgeBeamDepthAtArrays("5 1/2");
					} else if (checkValue.Equals(siteSurvey.getRidgeBeamDepthAtArrays(), "7 1/4 - nominal: 2X8")) {
						permitHomeSiteInfoEntity.setRidgeBeamDepthAtArrays("7 1/4");
					} else if (checkValue.Equals(siteSurvey.getRidgeBeamDepthAtArrays(), "9 1/4 - nominal: 2X10")) {
						permitHomeSiteInfoEntity.setRidgeBeamDepthAtArrays("9 1/4");
					} else if (checkValue.Equals(siteSurvey.getRidgeBeamDepthAtArrays(), "11 1/4 - nominal: 2X12")) {
						permitHomeSiteInfoEntity.setRidgeBeamDepthAtArrays("11 1/4");
					} else if (checkValue.Equals(siteSurvey.getRidgeBeamDepthAtArrays(), "3 3/4 - nominal: 2X4")) {
						permitHomeSiteInfoEntity.setRidgeBeamDepthAtArrays("3 3/4");
					} else if (checkValue.Equals(siteSurvey.getRidgeBeamDepthAtArrays(), "5 3/4 - nominal: 2X6")) {
						permitHomeSiteInfoEntity.setRidgeBeamDepthAtArrays("5 3/4");
					} else if (checkValue.Equals(siteSurvey.getRidgeBeamDepthAtArrays(), "7 3/4 - nominal: 2X8")) {
						permitHomeSiteInfoEntity.setRidgeBeamDepthAtArrays("7 3/4");
					} else if (checkValue.Equals(siteSurvey.getRidgeBeamDepthAtArrays(), "9 3/4 - nominal: 2X10")) {
						permitHomeSiteInfoEntity.setRidgeBeamDepthAtArrays("9 3/4");
					} else if (checkValue.Equals(siteSurvey.getRidgeBeamDepthAtArrays(), "11 3/4 - nominal: 2X12")) {
						permitHomeSiteInfoEntity.setRidgeBeamDepthAtArrays("11 3/4");
					}
					permitHomeSiteInfoEntity.setCityOther(siteSurvey.getCityOther());
					permitHomeSiteInfoEntity.setProjectJurisOther(siteSurvey.getProjectJurisOther());

					permitHomeSiteInfoEntity.setMaxHorizontalSpanAtArrays(siteSurvey.getMaxSpanAtArraysHS1());
					permitHomeSiteInfoEntity.setMaxHorizontalSpanAtArraysInches(siteSurvey.getMaxSpanAtArraysInchesHS1());
					permitHomeSiteInfoEntity.setMaxHorizontalSpanAtArraysHS(siteSurvey.getMaxSpanAtArraysHS2());
					permitHomeSiteInfoEntity.setMaxHorizontalSpanAtArraysHSInches(siteSurvey.getMaxSpanAtArraysInchesHS2());
					permitHomeSiteInfoRepo.save(permitHomeSiteInfoEntity);
				}
			
	
			PermitProjectSiteInfoEntity permitProjectSiteInfoEntity = permitProjectSiteInfoRepo.findOneByPermitEntityId(siteSurvey.getPortalProject());
			if (permitProjectSiteInfoEntity != null) {
					permitProjectSiteInfoEntity.setRoofMaterialType(checkValue.isNumeric(siteSurvey.getRoofMaterialType()) ? Long.valueOf(siteSurvey.getRoofMaterialType()) : null );
					if(siteSurvey.getNumberOfStories()!=null) {
						if (checkValue.Equals(siteSurvey.getNumberOfStories(), "OtheStory")) {
							permitProjectSiteInfoEntity.setTallStructure("OtheStory");
							permitProjectSiteInfoEntity.setOtherTallStructure(siteSurvey.getOtherTallStructure());

						} else {
							permitProjectSiteInfoEntity.setTallStructure(siteSurvey.getNumberOfStories());
						}
//					if(siteSurvey.getNumberOfStories()!=1 && siteSurvey.getNumberOfStories()!=2 && siteSurvey.getNumberOfStories()!=3 ) {
//						permitProjectSiteInfoEntity.setTallStructure("OtheStory");
//						permitProjectSiteInfoEntity.setOtherTallStructure(siteSurvey.getNumberOfStories()+" story");
//					}else {
//					permitProjectSiteInfoEntity.setTallStructure(siteSurvey.getNumberOfStories()+" Story");
//					}
					}
					permitProjectSiteInfoEntity.setMeanHeight(siteSurvey.getMeanHeight());
					permitProjectSiteInfoEntity.setMainPanelUpgrade(siteSurvey.isUpgradingMainServicePanel());
					if(checkValue.Equals(siteSurvey.getMainPanelMake(),"SquareD")){
					permitProjectSiteInfoEntity.setExistingMainPanelManufac("Square D");
					}
					else permitProjectSiteInfoEntity.setExistingMainPanelManufac(siteSurvey.getMainPanelMake());
					permitProjectSiteInfoEntity.setExistingMainPanelManufacOther(siteSurvey.getOtherMainPanelMake());
					permitProjectSiteInfoEntity.setProposedMainPanMan(siteSurvey.getMainPanelMake());
					permitProjectSiteInfoEntity.setPanelBusRating(siteSurvey.getMspbusbarRating());
					permitProjectSiteInfoEntity.setPanelBusRatingOther(siteSurvey.getOtherMSPBusbarRating());
					permitProjectSiteInfoEntity.setPanelMainBreakerRating(siteSurvey.getmSPMainBreakerRating());
					permitProjectSiteInfoEntity.setPanelMainBreakerRatingOther(siteSurvey.getOtherMSPMainBreakerRating());
				
					
					if (checkValue.Equals(siteSurvey.isTieInPOCIsAtSubpanel(),true)) {
						permitProjectSiteInfoEntity.setThepontOfTheC("Sub Panel");
						permitProjectSiteInfoEntity.setSubPanelBusRating(siteSurvey.getSubpanelBusbarRating());
						permitProjectSiteInfoEntity.setSubPanelBusRatingOther(siteSurvey.getOtherSubpanelBusbarRating());
						permitProjectSiteInfoEntity.setIfApplicableSubPanelMainBreakerRating(siteSurvey.getSubpanelMainBreakerRating());
						if (checkValue.Equals(siteSurvey.isExistingSubpanel(),true)) {
							permitProjectSiteInfoEntity.setConnectionPoint("Existing");
							permitProjectSiteInfoEntity.setSubPanelConductorSizing("I want to choose the conductor size");
							permitProjectSiteInfoEntity.setSubPanelConductorSize(siteSurvey.getSubpanelFeederGauge());
							permitProjectSiteInfoEntity.setSubPanelConductorSizeOther(siteSurvey.getOtherSubpanelFeederGauge());
						}else permitProjectSiteInfoEntity.setConnectionPoint("Proposed");
					}
					else 
					{
						permitProjectSiteInfoEntity.setThepontOfTheC("");
						permitProjectSiteInfoEntity.setSubPanelBusRating("");
						permitProjectSiteInfoEntity.setIfApplicableSubPanelMainBreakerRating("");
					}
					permitProjectSiteInfoEntity.setSubPanelBreakerOCPD(siteSurvey.getSubPanelBreakerOCPD());
					permitProjectSiteInfoEntity.setRafterTrussSpacing(siteSurvey.getRafterTrussSpacing());
					permitProjectSiteInfoEntity.setTextOtherRatfter(siteSurvey.getRafterTrussSpOther());
					permitProjectSiteInfoEntity.setOtherTallStructure(siteSurvey.getOtherTallStructure());
					permitProjectSiteInfoEntity.setCheckSiteSurveyOCPDValidity(true);
					
					permitProjectSiteInfoEntity.setCrossSectionSize(siteSurvey.getCrossSectionSize());
					permitProjectSiteInfoEntity.setTextOtherSize(siteSurvey.getCrossSectionSizeOther());
					permitProjectSiteInfoRepo.save(permitProjectSiteInfoEntity);

			}
			

			 PermitCompanyInfoEntity permitCompanyInfoEntity = permitCompanyInfoRepo.findOneByPermitEntityId(siteSurvey.getPortalProject());
			if (permitCompanyInfoEntity != null) {

					if (siteSurvey.getsCIR() != null) {
						permitCompanyInfoEntity.setTheScirOfTheMain(Integer.parseInt(siteSurvey.getsCIR()));
					}
					
					if (siteSurvey.getnEMType() != null) {
						permitCompanyInfoEntity.setSnemApplication("Yes");
						permitCompanyInfoEntity.setApplicationType(siteSurvey.getnEMType());
					}else {
						permitCompanyInfoEntity.setSnemApplication("No");
						
					}
					permitCompanyInfoEntity.setProjectWasPace(checkValue.isStringNotEmpty(siteSurvey.getPaceEntity()) ? "yes" : "no" );
					permitCompanyInfoEntity.setChoosePaceFinanced(siteSurvey.getPaceEntity());
					permitCompanyInfoEntity.setTextOtherChoosePace(siteSurvey.getPaceEntityOther());
					permitCompanyInfoEntity.setTheAcDisconnectIsMoreThan(siteSurvey.getaCDiscoWillbemorethan10FromUtilityMeter());
					if (siteSurvey.getRecentAnnualUsage() != null) {
					permitCompanyInfoEntity.setKwhUsage(siteSurvey.getRecentAnnualUsage()+"");
					}
					if (siteSurvey.getNumberOfElectricVehicles() != null && siteSurvey.getNumberOfElectricVehicles() >= 0 && siteSurvey.getNumberOfElectricVehicles() <= 2) {
						permitCompanyInfoEntity.setElectriccvehichule1("*"+siteSurvey.getNumberOfElectricVehicles());
					} else if (siteSurvey.getNumberOfElectricVehicles() != null && siteSurvey.getNumberOfElectricVehicles() > 2) {
						permitCompanyInfoEntity.setElectriccvehichule1("*other");
						permitCompanyInfoEntity.setOtherElectricVe(siteSurvey.getNumberOfElectricVehicles()+"");
					}
					permitCompanyInfoRepo.save(permitCompanyInfoEntity);

			}

			PermitArraysEntity permitArraysEntity = projectArraysRepo.findOneByPermitEntityId(siteSurvey.getPortalProject());
			if (permitArraysEntity != null) {
				
					permitArraysEntity.setDeviceToIncorporate(siteSurvey.getInverterTechnology());
					inverterTechnology = siteSurvey.getInverterTechnology();
					
					permitArraysEntity.setRoofMounted(siteSurvey.getRoofMounted());
					permitArraysEntity.setGroundMounted(siteSurvey.getGroundMounted());
					permitArraysEntity.setCarportMounted(siteSurvey.getCarportMounted());
					permitArraysEntity.setPatioMounted(siteSurvey.getPatioMounted());
					permitArraysEntity.setOtherMounted(siteSurvey.getOtherMounted());
					permitArraysEntity.setTextOther(siteSurvey.getTextOther());
					
					//A.B 01-20
					if (siteSurvey.getInverter1Model() != null) {
						Inverters inverter  = inverterRepo.findById(siteSurvey.getInverter1Model()).get();
						if(checkValue.Equals(inverter.getMicroInverter(), true)) {
							if(checkValue.Equals(inverterTechnology, "System Optimizer") || checkValue.Equals(inverterTechnology, "Neither")) {
								permitArraysEntity.setFirstInverter(inverter);
							}else permitArraysEntity.setMicroInverterEntity(inverter);
						}else permitArraysEntity.setFirstInverter(inverter);
					}
					if (siteSurvey.getInverter2Model() != null) {
						Inverters inverter  = inverterRepo.findById(siteSurvey.getInverter2Model()).get();
						permitArraysEntity.setSecondInverter(inverter);
					}

					if (siteSurvey.getInverter3Model() != null) {
						Inverters inverter  = inverterRepo.findById(siteSurvey.getInverter3Model()).get();
						permitArraysEntity.setThirdInverter(inverter);
					}
				
					
					if(checkValue.Equals(siteSurvey.getRoofMounted(), true)
							|| (!checkValue.NotEquals(siteSurvey.getRoofMounted(), false)
									&& !checkValue.NotEquals(siteSurvey.getGroundMounted(), false)
									&& !checkValue.NotEquals(siteSurvey.getCarportMounted(), false)
									&& !checkValue.NotEquals(siteSurvey.getPatioMounted(), false)
									&& !checkValue.NotEquals(siteSurvey.getOtherMounted(), false))) {
						if (checkValue.isLongPositive(siteSurvey.getpVModel( ))) {
							Cmodulev2 module  = moduleRepo.findById(siteSurvey.getpVModel()).get();
						permitArraysEntity.setPvModule(module);
						}
					}else if(!checkValue.NotEquals(siteSurvey.getRoofMounted(), false)
							&& (checkValue.Equals(siteSurvey.getCarportMounted(), true) || checkValue.Equals(siteSurvey.getPatioMounted(), true))) {
						if (checkValue.isLongPositive(siteSurvey.getpVModelCarpotPatio( ))) {
							Cmodulev2 module  = moduleRepo.findById(siteSurvey.getpVModelCarpotPatio()).get();
						permitArraysEntity.setPvModule(module);
						}
					}else if(!checkValue.NotEquals(siteSurvey.getRoofMounted(), false)
							&& !checkValue.NotEquals(siteSurvey.getCarportMounted(), false)
							&& !checkValue.NotEquals(siteSurvey.getPatioMounted(), false)
							&& checkValue.Equals(siteSurvey.getGroundMounted(), true)) {
						if (checkValue.isLongPositive(siteSurvey.getpVModelNonRoof())) {
							Cmodulev2 module  = moduleRepo.findById(siteSurvey.getpVModelNonRoof()).get();
						permitArraysEntity.setPvModule(module);
						}
					}
					permitArraysEntity.setFrontAndBack(siteSurvey.getFrontAndBack());
					permitArraysEntity.setCantelever(siteSurvey.getCantelever());
					permitArraysEntity.setAttachedToExtWal(siteSurvey.getAttachedToExtWal());
					permitArraysEntity.setAttachedToFascia(siteSurvey.getAttachedToFascia());
					permitArraysEntity.setAttachedToSkylifts(siteSurvey.getAttachedToSkylifts());
					permitArraysEntity.setFreeStanding(siteSurvey.getFreeStanding());
					permitArraysEntity.setRoofOrOpenFrame(siteSurvey.getRoofOrOpenFrame());
					projectArraysRepo.save(permitArraysEntity);

				
			}
			
		
					PermitLayoutEntity permitLayoutEntity = permitLayoutRepo.findOneByPermitEntityId(siteSurvey.getPortalProject());
			if (permitLayoutEntity != null) {
					// 07/08/2019 / CI: CR 2860
					// If “Wire Run on Roof” is checked, the "Roof Top" checkbox under “Will conduit
					// be run in attic or on roof?” on the Layout/Sketch Tab should be checked.
					if ((checkValue.Equals(siteSurvey.isWireRunOnRoof(), true)
							&& (!checkValue.NotEquals(inverterTechnology, "")
									|| !checkValue.NotEquals(inverterTechnology, "System Optimizer")
									|| !checkValue.NotEquals(inverterTechnology, "Neither")))
							|| (checkValue.Equals(siteSurvey.isWireRunOnRoof(), true)
									&& (checkValue.Equals(inverterTechnology, "Micro Inverter")
											|| checkValue.Equals(inverterTechnology, "AC Modules"))
									&& (!checkValue.NotEquals(userSetting.getUseRomexInAttic(), "")
											|| !checkValue.NotEquals(userSetting.getUseRomexInAttic(), false)))) {
						permitLayoutEntity.setConduitRun("Roof Top");
					}

					// If “Wire Run in Attic” is checked AND the Inverter Technology equals
					// “String Inverters” OR “System Optimizers”, the "Attic" checkbox under “Will
					// conduit be run in attic or on roof?” on the Layout/Sketch Tab should be
					// checked.
					else
					if (checkValue.Equals(siteSurvey.isWireRunInAttic(), true)
							&& (checkValue.Equals(inverterTechnology, "System Optimizer")
									|| checkValue.Equals(inverterTechnology, "Neither"))) {
						permitLayoutEntity.setConduitRun("Attic");
					} 
					
					else
					// If “Wire Run in Attic” is checked AND the Inverter Technology equals
					// “Microinverters” OR “AC Modules” AND the “Always use Romex in attic when
					// using Microinverters or AC modules on a roof-mounted system.” in Profile
					// Settings is not checked, the "Conduit in Attic" checkbox under “Will conduit
					// be run in attic or on roof?” on the Layout/Sketch Tab should be checked.
					if (checkValue.Equals(siteSurvey.isWireRunInAttic(), true) && (checkValue
							.Equals(inverterTechnology, "Micro Inverter")
							|| checkValue.Equals(inverterTechnology, "AC Modules"))
									&& (!checkValue.NotEquals(userSetting.getUseRomexInAttic(), "")
											|| !checkValue.NotEquals(userSetting.getUseRomexInAttic(), false))) {
						permitLayoutEntity.setConduitRun("Attic");

					}
					
					permitLayoutRepo.save(permitLayoutEntity);
				
			}
		
			// 07/08/2019 / CI: CR 2860 
			PermitAdditionalInfoEntity permitAdditionalInfoEntity = permitAdditionalInfoRepo.findOneByPermitEntityId(siteSurvey.getPortalProject());
			if (permitAdditionalInfoEntity != null) {
					if(checkValue.Equals(siteSurvey.isTiltupModules(), true)) {
						permitAdditionalInfoEntity.setTiltLegs(true);
					}
					else permitAdditionalInfoEntity.setTiltLegs(false);
					permitAdditionalInfoRepo.save(permitAdditionalInfoEntity);
			
			}
			

			if (checkValue.Equals(siteSurvey.getRoofMounted(), true)
					|| (!checkValue.NotEquals(siteSurvey.getRoofMounted(), false)
							&& !checkValue.NotEquals(siteSurvey.getGroundMounted(), false)
							&& !checkValue.NotEquals(siteSurvey.getCarportMounted(), false)
							&& !checkValue.NotEquals(siteSurvey.getPatioMounted(), false)
							&& !checkValue.NotEquals(siteSurvey.getOtherMounted(), false))) {

				List<PermitSketchResults> siteSurveyArrays = siteSurveyRepo.getSiteSurveyArrayChart(siteSurvey.getId());
				synchronizationLayoutSketchArrays(siteSurveyArrays, siteSurvey.getPortalProject());
			}

			// 16/08/2019/ CI :CR 2860 : import sketch table to patio cover tab
			else if (!checkValue.NotEquals(siteSurvey.getRoofMounted(), false)
					&& (checkValue.Equals(siteSurvey.getCarportMounted(), true) || checkValue.Equals(siteSurvey.getPatioMounted(), true))) {

				List<PermitSketchResults> siteSurveyArrays = siteSurveyRepo.getSiteSurveyArrayChartPatio(siteSurvey.getId());
				synchronizationLayoutSketchArrays(siteSurveyArrays, siteSurvey.getPortalProject());
			}

			// 16/08/2019/ CI :CR 2860 : import sketch table to non roof mount tab
			else if ((!checkValue.NotEquals(siteSurvey.getRoofMounted(), false)
					&& !checkValue.NotEquals(siteSurvey.getCarportMounted(), false)
					&& !checkValue.NotEquals(siteSurvey.getPatioMounted(), false))
					&& (checkValue.Equals(siteSurvey.getGroundMounted(), true))) {
				
				List<PermitSketchResults> siteSurveyArrays = siteSurveyRepo.getSiteSurveyArrayChartNonRoof(siteSurvey.getId());
				synchronizationLayoutSketchArrays(siteSurveyArrays, siteSurvey.getPortalProject());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		return "";
	}
	
public SiteSurveyModel SynchronizationSurvey(Long idProject, Long idUser) {
	
	SiteSurveyModel siteSurveyModel = new SiteSurveyModel();
	try {
		SiteSurveyEntity siteSurveyEntity = new SiteSurveyEntity();

		PermitEntity project = permitRepo.findById(idProject).orElseThrow(
				() -> new ResourceNotFoundException("Permit not found for this id :" +idProject));
		TimeZone.setDefault (  TimeZone.getTimeZone ("PST8PDT")) ;
		DateTime dt = new DateTime(new Date(),TimeZone.getTimeZone("PST8PDT"));
		SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		isoFormat.setTimeZone(TimeZone.getTimeZone("PST8PDT"));
		Date date = isoFormat.parse(dt+"");
		AuthentificationEntity user = authRepo.findById(idUser).orElseThrow(
				() -> new ResourceNotFoundException("User not found for this id :" +idUser));
		
		siteSurveyEntity.setHomeOwnName(project.getHomeOwnName());
		siteSurveyEntity.setHomeOwnLastName(project.getHomeOwnLastName());
		siteSurveyEntity.setProjectName(project.getProjectName());
//		siteSurveyEntity.setStreetAddress(project.getStreetAddress());
//		siteSurveyEntity.setBasicTypeOfSystem(project.getBasicTypeOfSystem());
		siteSurveyEntity.setCreatedBy(user);
		
		//*******Default Config ********//
		siteSurveyEntity.setSubmitted(false);
		siteSurveyEntity.setCanceled(false);
		siteSurveyEntity.setStatus("Draft");
		siteSurveyEntity.setCreationDate(date);
		siteSurveyEntity.setHasProject(false);
		
		
			PermitHomeSiteInfoEntity permitHomeSiteInfoEntity = permitHomeSiteInfoRepo.findOneByPermitEntityId(project.getId());
			if (permitHomeSiteInfoEntity != null) {
					siteSurveyEntity.setStreetAddress(permitHomeSiteInfoEntity.getSiteAddress());
					//08/08/2019 : CI / CR2860 :map address line 2 value from project to site survey 
					siteSurveyEntity.setAddressLine2(permitHomeSiteInfoEntity.getAddressLine2());
					siteSurveyEntity.setCity(permitHomeSiteInfoEntity.getCity());
					siteSurveyEntity.setCityOther(permitHomeSiteInfoEntity.getCityOther());
					siteSurveyEntity.setState(permitHomeSiteInfoEntity.getState());
					siteSurveyEntity.setzIP(permitHomeSiteInfoEntity.getPostalCode());
					siteSurveyEntity.setMainContactPhone(permitHomeSiteInfoEntity.getHomePhone());  
					siteSurveyEntity.setOtherPhone(permitHomeSiteInfoEntity.getOtherPhone()); 
					siteSurveyEntity.setEmailAddress(permitHomeSiteInfoEntity.getEmailPhone());
					siteSurveyEntity.setPermittingAuthority(permitHomeSiteInfoEntity.getProjectJurisdiction());
					siteSurveyEntity.setParcelNumber(permitHomeSiteInfoEntity.getPropertyAPN());
				
					if(permitHomeSiteInfoEntity.getBuildingRisk()!= null) {
						if(checkValue.Equals(permitHomeSiteInfoEntity.getBuildingRisk(),"Other")) {
							siteSurveyEntity.setRiskCategory("Other");
							siteSurveyEntity.setOtherRiskCategory(permitHomeSiteInfoEntity.getBuildingRiskOther());
						}
						else
						{
							if(checkValue.Equals(permitHomeSiteInfoEntity.getBuildingRisk(), "Risk Category II")) {
								siteSurveyEntity.setRiskCategory("Residential (II)");
							}else {
								siteSurveyEntity.setRiskCategory(permitHomeSiteInfoEntity.getBuildingRisk());
							}
						}
					}
					
					siteSurveyEntity.setBuildingOccupancy(permitHomeSiteInfoEntity.getBuildingOccupancy());
					siteSurveyEntity.setOtherBuildingOccupancy(permitHomeSiteInfoEntity.getTextOtherBuildOccup());
					
					// 08/08/2019 : CI : CR2680 : if “240V single phase (Std. Residential Serv.)” is
					// checked in Voltage field, the "Service is 240V Single (Split) Phase" checkbox
					// on the Homeowner/Site Info Tab should be checked
					if (checkValue.Equals(permitHomeSiteInfoEntity.getIfServiceVoltage(), true)) {
						siteSurveyEntity.setVoltage("240V single phase (Std. Residential Serv.)");
					} else {
						siteSurveyEntity.setVoltage("Other");
						if (checkValue.Equals(permitHomeSiteInfoEntity.getServiceVoltage(), "120/208V")) {
							siteSurveyEntity.setOtherVoltage("Three Phase Four Wire Wye 120/208V");
						} else if (checkValue.Equals(permitHomeSiteInfoEntity.getServiceVoltage(),
								"277/480V")) {
							siteSurveyEntity.setOtherVoltage("Three Phase Four Wire Wye 277/480V");
						} else if (checkValue.Equals(permitHomeSiteInfoEntity.getServiceVoltage(),
								"240V")) {
							siteSurveyEntity.setOtherVoltage("Three Phase Three Wire Delta 240V");
						} else if (checkValue.Equals(permitHomeSiteInfoEntity.getServiceVoltage(),
								"400V")) {
							siteSurveyEntity.setOtherVoltage("Three Phase Three Wire Delta 400V");
						} else if (checkValue.Equals(permitHomeSiteInfoEntity.getServiceVoltage(),
								"480V")) {
							siteSurveyEntity.setOtherVoltage("Three Phase Three Wire Delta 480V");
						} else if (checkValue.Equals(permitHomeSiteInfoEntity.getServiceVoltage(),
								"600V")) {
							siteSurveyEntity.setOtherVoltage("Three Phase Three Wire Delta 600V");
						} else if (checkValue.Equals(permitHomeSiteInfoEntity.getServiceVoltage(),
								"Other")) {
							siteSurveyEntity.setOtherVoltage("Other");
							siteSurveyEntity.setOtherVoltageOther(permitHomeSiteInfoEntity.getServiceVoltageOther());
						}else {
							siteSurveyEntity.setOtherVoltage(permitHomeSiteInfoEntity.getServiceVoltage());
						}

					}
					siteSurveyEntity.setUtilityCoName(permitHomeSiteInfoEntity.getUtilityCompanyName());
					siteSurveyEntity.setMeterNumber(permitHomeSiteInfoEntity.getMeterNumber());
					siteSurveyEntity.setRoofStructureChart(permitHomeSiteInfoEntity.getRoofRafter());
					siteSurveyEntity.setRoofRafterOther(permitHomeSiteInfoEntity.getRoofRafterOther());
					siteSurveyEntity.setSecroofRafterOther(permitHomeSiteInfoEntity.getSecroofRafterOther());
					siteSurveyEntity.setStanchionMaxSpacing(permitHomeSiteInfoEntity.getStanchionMaxSpacing());
					
					// 08/08/2019 CI : CR 2860 : Change Ridge Beam Depth at Arrays” logic mapping to
					// “Roof Member Cross Section Size” on Homeowner/Site Info Tab
					if (checkValue.Equals(permitHomeSiteInfoEntity.getRidgeBeamDepthAtArrays(), "3 1/2")) {
						siteSurveyEntity.setRidgeBeamDepthAtArrays("3 1/2 - nominal: 2X4");
					} else if (checkValue.Equals(permitHomeSiteInfoEntity.getRidgeBeamDepthAtArrays(), "5 1/2")) {
						siteSurveyEntity.setRidgeBeamDepthAtArrays("5 1/2 - nominal: 2X6");
					} else if (checkValue.Equals(permitHomeSiteInfoEntity.getRidgeBeamDepthAtArrays(), "7 1/4")) {
						siteSurveyEntity.setRidgeBeamDepthAtArrays("7 1/4 - nominal: 2X8");
					} else if (checkValue.Equals(permitHomeSiteInfoEntity.getRidgeBeamDepthAtArrays(), "9 1/4")) {
						siteSurveyEntity.setRidgeBeamDepthAtArrays("9 1/4 - nominal: 2X10");
					} else if (checkValue.Equals(permitHomeSiteInfoEntity.getRidgeBeamDepthAtArrays(), "11 1/4")) {
						siteSurveyEntity.setRidgeBeamDepthAtArrays("11 1/4 - nominal: 2X12");
					} else if (checkValue.Equals(permitHomeSiteInfoEntity.getRidgeBeamDepthAtArrays(), "3 3/4")) {
						siteSurveyEntity.setRidgeBeamDepthAtArrays("3 3/4 - nominal: 2X4");
					} else if (checkValue.Equals(permitHomeSiteInfoEntity.getRidgeBeamDepthAtArrays(), "5 3/4")) {
						siteSurveyEntity.setRidgeBeamDepthAtArrays("5 3/4 - nominal: 2X6");
					} else if (checkValue.Equals(permitHomeSiteInfoEntity.getRidgeBeamDepthAtArrays(), "7 3/4")) {
						siteSurveyEntity.setRidgeBeamDepthAtArrays("7 3/4 - nominal: 2X8");
					} else if (checkValue.Equals(permitHomeSiteInfoEntity.getRidgeBeamDepthAtArrays(), "9 3/4")) {
						siteSurveyEntity.setRidgeBeamDepthAtArrays("9 3/4 - nominal: 2X10");
					} else if (checkValue.Equals(permitHomeSiteInfoEntity.getRidgeBeamDepthAtArrays(), "11 3/4")) {
						siteSurveyEntity.setRidgeBeamDepthAtArrays("11 3/4 - nominal: 2X12");
					}
					
					siteSurveyEntity.setProjectJurisOther(permitHomeSiteInfoEntity.getProjectJurisOther());
					
					siteSurveyEntity.setMaxSpanAtArraysHS1(permitHomeSiteInfoEntity.getMaxHorizontalSpanAtArrays());
					siteSurveyEntity.setMaxSpanAtArraysInchesHS1(permitHomeSiteInfoEntity.getMaxHorizontalSpanAtArraysInches());
					siteSurveyEntity.setMaxSpanAtArraysHS2(permitHomeSiteInfoEntity.getMaxHorizontalSpanAtArraysHS());
					siteSurveyEntity.setMaxSpanAtArraysInchesHS2(permitHomeSiteInfoEntity.getMaxHorizontalSpanAtArraysHSInches());
					
					

			}

			PermitProjectSiteInfoEntity permitProjectSiteInfoEntity = permitProjectSiteInfoRepo.findOneByPermitEntityId(project.getId());
			if (permitProjectSiteInfoEntity != null) {
					siteSurveyEntity.setRoofMaterialType(permitProjectSiteInfoEntity.getRoofMaterialType()+"");
					//siteSurveyEntity.setNumberOfStories(Integer.parseInt(permitProjectSiteInfoEntity.getTallStructure()));
					if(permitProjectSiteInfoEntity.getTallStructure()!=null) {
					if(checkValue.NotEquals(permitProjectSiteInfoEntity.getTallStructure(),"OtheStory")) {
						siteSurveyEntity.setNumberOfStories(permitProjectSiteInfoEntity.getTallStructure());
					}
					else {
						try {
							siteSurveyEntity.setNumberOfStories("OtheStory");
							siteSurveyEntity.setOtherTallStructure(permitProjectSiteInfoEntity.getOtherTallStructure());
					 }catch (Exception e) {
							e.printStackTrace();
							
						}
					}
				    }

					siteSurveyEntity.setMeanHeight(permitProjectSiteInfoEntity.getMeanHeight());
					siteSurveyEntity.setUpgradingMainServicePanel(permitProjectSiteInfoEntity.getMainPanelUpgrade());
					if (checkValue.Equals(permitProjectSiteInfoEntity.getMainPanelUpgrade(),true)) {
						if (checkValue.Equals(permitProjectSiteInfoEntity.getProposedMainPanMan(),
								"Siemens W/Alt Energy Input")) {
							siteSurveyEntity.setMainPanelMake("");
						} else {
							siteSurveyEntity.setMainPanelMake(permitProjectSiteInfoEntity.getProposedMainPanMan());
						}
					} else {
						if (checkValue.Equals(permitProjectSiteInfoEntity.getExistingMainPanelManufac(),
								"Siemens W/Alt Energy Input")) {
							siteSurveyEntity.setMainPanelMake("");
						} else if (checkValue
								.Equals(permitProjectSiteInfoEntity.getExistingMainPanelManufac(), "Square D")) {
							siteSurveyEntity.setMainPanelMake("SquareD");
						} else {
							siteSurveyEntity
									.setMainPanelMake(permitProjectSiteInfoEntity.getExistingMainPanelManufac());
						}
						siteSurveyEntity.setOtherMainPanelMake(permitProjectSiteInfoEntity.getExistingMainPanelManufacOther());
					}
					
					siteSurveyEntity.setMspbusbarRating(permitProjectSiteInfoEntity.getPanelBusRating());
					siteSurveyEntity.setmSPMainBreakerRating(permitProjectSiteInfoEntity.getPanelMainBreakerRating());
					siteSurveyEntity.setOtherMSPBusbarRating(permitProjectSiteInfoEntity.getPanelBusRatingOther());
					siteSurveyEntity.setOtherMSPMainBreakerRating( permitProjectSiteInfoEntity.getPanelMainBreakerRatingOther());
					if(checkValue.Equals(permitProjectSiteInfoEntity.getThepontOfTheC(),"Sub Panel")) {
						siteSurveyEntity.setTieInPOCIsAtSubpanel(true);
						siteSurveyEntity.setSubpanelBusbarRating(permitProjectSiteInfoEntity.getSubPanelBusRating());
						siteSurveyEntity.setOtherSubpanelBusbarRating(permitProjectSiteInfoEntity.getSubPanelBusRatingOther());
						siteSurveyEntity.setSubpanelMainBreakerRating(permitProjectSiteInfoEntity.getIfApplicableSubPanelMainBreakerRating());
						if (checkValue.Equals(permitProjectSiteInfoEntity.getConnectionPoint(),"Existing")) {
							siteSurveyEntity.setExistingSubpanel(true) ;
						}
					}else {
						siteSurveyEntity.setTieInPOCIsAtSubpanel(false);
					}
					siteSurveyEntity.setSubPanelBreakerOCPD(permitProjectSiteInfoEntity.getSubPanelBreakerOCPD());
					siteSurveyEntity.setRafterTrussSpacing(permitProjectSiteInfoEntity.getRafterTrussSpacing());
					siteSurveyEntity.setRafterTrussSpOther(permitProjectSiteInfoEntity.getTextOtherRatfter());
					siteSurveyEntity.setOtherTallStructure(permitProjectSiteInfoEntity.getOtherTallStructure());
					
					siteSurveyEntity.setCrossSectionSize(permitProjectSiteInfoEntity.getCrossSectionSize());
					siteSurveyEntity.setCrossSectionSizeOther(permitProjectSiteInfoEntity.getTextOtherSize());
					
			}
			
			PermitCompanyInfoEntity permitCompanyInfoEntity = permitCompanyInfoRepo.findOneByPermitEntityId(project.getId());
			if (permitCompanyInfoEntity != null) {
					if (permitCompanyInfoEntity.getTheScirOfTheMain() != null) {
						siteSurveyEntity.setsCIR(Integer.toString(permitCompanyInfoEntity.getTheScirOfTheMain()));
					}
					
					if (permitCompanyInfoEntity.getApplicationType() != null) {
						siteSurveyEntity.setnEMType(permitCompanyInfoEntity.getApplicationType());
					}
					
					siteSurveyEntity.setPaceEntity(permitCompanyInfoEntity.getProjectWasPace());
					siteSurveyEntity.setaCDiscoWillbemorethan10FromUtilityMeter(permitCompanyInfoEntity.getTheAcDisconnectIsMoreThan());
					// 08/08/2019 : CI : CR 2860 : Electric Utility Meter Number” should map to “Electric Utility Meter Number” on Homeowner/Site Info Tab
//					if(checkValue.isStringInt(permitCompanyInfoEntity.getMeterNumber())) {
//					siteSurveyEntity.setMeterNumber(Integer.parseInt(permitCompanyInfoEntity.getMeterNumber()));
//					}

//				siteSurveyEntity.setRecentAnnualUsage(Integer.parseInt(permitCompanyInfoEntity.getKwhUsage()));
	
//				if (permitCompanyInfoEntity.getElectriccvehichule1() != null && permitCompanyInfoEntity.getElectriccvehichule1().equals("*other")) {
//					siteSurveyEntity.setNumberOfElectricVehicles(Integer.parseInt(permitCompanyInfoEntity.getOtherElectricVe()));
//				} else {
//					siteSurveyEntity.setNumberOfElectricVehicles(Integer.parseInt(permitCompanyInfoEntity.getElectriccvehichule1()));
//				}
				
				
				
			}
			
			PermitArraysEntity permitArraysEntity = projectArraysRepo.findOneByPermitEntityId(project.getId());
			if (permitArraysEntity != null) {
					siteSurveyEntity.setInverterTechnology(permitArraysEntity.getDeviceToIncorporate());
					siteSurveyEntity.setRoofMounted(permitArraysEntity.getRoofMounted());
					siteSurveyEntity.setGroundMounted(permitArraysEntity.getGroundMounted());
					siteSurveyEntity.setCarportMounted(permitArraysEntity.getCarportMounted());
					siteSurveyEntity.setPatioMounted(permitArraysEntity.getPatioMounted());
					siteSurveyEntity.setOtherMounted(permitArraysEntity.getOtherMounted());
					siteSurveyEntity.setTextOther(permitArraysEntity.getTextOther());
					siteSurveyEntity.setFirstInverter(permitArraysEntity.getFirstInverter());
					siteSurveyEntity.setSecondInverter(permitArraysEntity.getSecondInverter());
					siteSurveyEntity.setThirdInverter(permitArraysEntity.getThirdInverter());
					siteSurveyEntity.setPvModule(permitArraysEntity.getPvModule());
					siteSurveyEntity.setPvModuleCarpotPatio(permitArraysEntity.getPvModule());
					siteSurveyEntity.setPvModuleNonRoof(permitArraysEntity.getPvModule());
					siteSurveyEntity.setFrontAndBack(permitArraysEntity.getFrontAndBack());
					siteSurveyEntity.setCantelever(permitArraysEntity.getCantelever());
					siteSurveyEntity.setAttachedToExtWal(permitArraysEntity.getAttachedToExtWal());
					siteSurveyEntity.setAttachedToFascia(permitArraysEntity.getAttachedToFascia());
					siteSurveyEntity.setAttachedToSkylifts(permitArraysEntity.getAttachedToSkylifts());
					siteSurveyEntity.setFreeStanding(permitArraysEntity.getFreeStanding());
					siteSurveyEntity.setRoofOrOpenFrame(permitArraysEntity.getRoofOrOpenFrame());
				

			}
			PermitLayoutEntity permitLayoutEntity = permitLayoutRepo.findOneByPermitEntityId(project.getId());
			if (permitLayoutEntity != null) {

					// 08/08/2019 / CI: CR 2860
					// If “Wire Run on Roof” is checked, the "Roof Top" checkbox under “Will conduit
					// be run in attic or on roof?” on the Layout/Sketch Tab should be checked.
						if (checkValue.Equals(permitLayoutEntity.getConduitRun(), "Roof Top")) {
							siteSurveyEntity.setWireRunOnRoof( true);
					}

					// If “Wire Run in Attic” is checked AND the Inverter Technology equals
					// “String Inverters” OR “System Optimizers”, the "Attic" checkbox under “Will
					// conduit be run in attic or on roof?” on the Layout/Sketch Tab should be
					// checked.
					if (checkValue.Equals(permitLayoutEntity.getConduitRun(),"Attic")) {
						
						siteSurveyEntity.setWireRunInAttic(true);
					} 
					permitLayoutRepo.save(permitLayoutEntity);
				
			}
		
			// 08/08/2019 / CI: CR 2860 
			PermitAdditionalInfoEntity permitAdditionalInfoEntity = permitAdditionalInfoRepo.findOneByPermitEntityId(project.getId());
			if (permitAdditionalInfoEntity != null) {
					// If “Tilt Up Modules” is checked, the "System will include Tilted Modules with Tilt Legs" checkbox on the Additional Info Tab should be checked.
					if(checkValue.Equals(permitAdditionalInfoEntity.getTiltLegs(),true)) {
					
						siteSurveyEntity.setTiltupModules(true);
					}
					permitAdditionalInfoRepo.save(permitAdditionalInfoEntity);
				
			}
			siteSurveyRepo.save(siteSurveyEntity);
			//import Sktech Layout
			List<PermitSketchEntity> permitSketchEntitys = permitSketchRepo.findByPermitEntityId(project.getId());
			
			
			if(checkValue.Equals(siteSurveyEntity.getRoofMounted(), true)
					|| (!checkValue.NotEquals(siteSurveyEntity.getRoofMounted(), false)
							&& !checkValue.NotEquals(siteSurveyEntity.getGroundMounted(), false)
							&& !checkValue.NotEquals(siteSurveyEntity.getCarportMounted(), false)
							&& !checkValue.NotEquals(siteSurveyEntity.getPatioMounted(), false)
							&& !checkValue.NotEquals(siteSurveyEntity.getOtherMounted(), false))) {
			List<SiteSurveyArrayChartEntity> newSiteSurveySketchEntitys = siteSurveyArrayChartRepo.findBySiteSurveyEntityId(siteSurveyEntity.getId());
			
			if(newSiteSurveySketchEntitys != null) {
				for (SiteSurveyArrayChartEntity siteSurveySketchEntity : newSiteSurveySketchEntitys) {
					siteSurveyArrayChartRepo.delete(siteSurveySketchEntity);
				}
			}
			
			
			if (permitSketchEntitys != null) {
				for (PermitSketchEntity permitSketchEntity : permitSketchEntitys) {
					SiteSurveyArrayChartEntity newSiteSurveySketchEntity = new SiteSurveyArrayChartEntity();
					if(permitSketchEntity != null) {
						newSiteSurveySketchEntity.setArraySketch(permitSketchEntity.getArraySketch());
						newSiteSurveySketchEntity.setAzimuth(permitSketchEntity.getAzimuth());
						newSiteSurveySketchEntity.setRoofPitch(permitSketchEntity.getRoofPitch());
						newSiteSurveySketchEntity.setModuleTils(permitSketchEntity.getModuleTils());
						newSiteSurveySketchEntity.setEaveOverHang(permitSketchEntity.getEaveOverHang());
						newSiteSurveySketchEntity.setEaveOverHangOther(permitSketchEntity.getEaveOverHangOther());
						newSiteSurveySketchEntity.setModelvalue(permitSketchEntity.getModelvalue());
						newSiteSurveySketchEntity.setModuleQty(permitSketchEntity.getModuleQty());
					}
					newSiteSurveySketchEntity.setSiteSurveyEntity(siteSurveyEntity);
					siteSurveyArrayChartRepo.save(newSiteSurveySketchEntity);
				}
			}
			}
			
			//16/08/2019/ CI :CR 2860 : import sketch table to patio cover tab
			if(!checkValue.NotEquals(siteSurveyEntity.getRoofMounted(), false)
					&& (checkValue.Equals(siteSurveyEntity.getCarportMounted(), true) || checkValue.Equals(siteSurveyEntity.getPatioMounted(), true))) {

				List<SiteSurveyArrayChartPatioEntity> newSiteSurveySketchEntitys = siteSurveyArrayChartPatioRepo.findBySiteSurveyEntityId(siteSurveyEntity.getId());
				
				if(newSiteSurveySketchEntitys != null) {
					for (SiteSurveyArrayChartPatioEntity siteSurveySketchEntity : newSiteSurveySketchEntitys) {
						siteSurveyArrayChartPatioRepo.delete(siteSurveySketchEntity);
					}
				}
				
				
				if (permitSketchEntitys != null) {
					for (PermitSketchEntity permitSketchEntity : permitSketchEntitys) {
						SiteSurveyArrayChartPatioEntity newSiteSurveySketchEntity = new SiteSurveyArrayChartPatioEntity();
						if(permitSketchEntity != null) {
							newSiteSurveySketchEntity.setArraySketch(permitSketchEntity.getArraySketch());
							newSiteSurveySketchEntity.setAzimuth(permitSketchEntity.getAzimuth());
							newSiteSurveySketchEntity.setRoofPitch(permitSketchEntity.getRoofPitch());
							newSiteSurveySketchEntity.setModuleTils(permitSketchEntity.getModuleTils());
							newSiteSurveySketchEntity.setEaveOverHang(permitSketchEntity.getEaveOverHang());
							newSiteSurveySketchEntity.setEaveOverHangOther(permitSketchEntity.getEaveOverHangOther());
							newSiteSurveySketchEntity.setModelvalue(permitSketchEntity.getModelvalue());
							newSiteSurveySketchEntity.setModuleQty(permitSketchEntity.getModuleQty());
						}
						newSiteSurveySketchEntity.setSiteSurveyEntity(siteSurveyEntity);
						siteSurveyArrayChartPatioRepo.save(newSiteSurveySketchEntity);
					}
				}
		    }
			
			 //16/08/2019/ CI :CR 2860 : import sketch table to non roof mount tab
		   if((!checkValue.NotEquals(siteSurveyEntity.getRoofMounted(), false)
					&& !checkValue.NotEquals(siteSurveyEntity.getCarportMounted(), false)
					&& !checkValue.NotEquals(siteSurveyEntity.getPatioMounted(), false))
					&& (checkValue.Equals(siteSurveyEntity.getGroundMounted(), true)
				)) {
				List<SiteSurveyArrayChartNonRoofEntity> newSiteSurveySketchEntitys = siteSurveyArrayChartNonRoofRepo.findBySiteSurveyEntityId(siteSurveyEntity.getId());
				if(newSiteSurveySketchEntitys != null) {
					for (SiteSurveyArrayChartNonRoofEntity siteSurveySketchEntity : newSiteSurveySketchEntitys) {
						siteSurveyArrayChartNonRoofRepo.delete(siteSurveySketchEntity);
					}
				}
				
				
				if (permitSketchEntitys != null) {
					for (PermitSketchEntity permitSketchEntity : permitSketchEntitys) {
						SiteSurveyArrayChartNonRoofEntity newSiteSurveySketchEntity = new SiteSurveyArrayChartNonRoofEntity();
						if(permitSketchEntity != null) {
							newSiteSurveySketchEntity.setArraySketch(permitSketchEntity.getArraySketch());
							newSiteSurveySketchEntity.setAzimuth(permitSketchEntity.getAzimuth());
							newSiteSurveySketchEntity.setRoofPitch(permitSketchEntity.getRoofPitch());
							newSiteSurveySketchEntity.setModuleTils(permitSketchEntity.getModuleTils());
							newSiteSurveySketchEntity.setEaveOverHang(permitSketchEntity.getEaveOverHang());
							newSiteSurveySketchEntity.setEaveOverHangOther(permitSketchEntity.getEaveOverHangOther());
							newSiteSurveySketchEntity.setModelvalue(permitSketchEntity.getModelvalue());
							newSiteSurveySketchEntity.setModuleQty(permitSketchEntity.getModuleQty());
						}
						newSiteSurveySketchEntity.setSiteSurveyEntity(siteSurveyEntity);
						siteSurveyArrayChartNonRoofRepo.save(newSiteSurveySketchEntity);
					}
				}
		    }
			
			
			
			//End import sketch table
			
			siteSurveyModel.setSiteSurveyID(siteSurveyEntity.getId());
			siteSurveyModel.setHomeOwnName(siteSurveyEntity.getHomeOwnName());
			siteSurveyModel.setHomeOwnLastName(siteSurveyEntity.getHomeOwnLastName());
			siteSurveyModel.setProjectName(siteSurveyEntity.getProjectName());
			siteSurveyModel.setStreetAddress(siteSurveyEntity.getStreetAddress()); ;
			//CR 2860 :
			siteSurveyModel.setAddressLine2(siteSurveyEntity.getAddressLine2());
			siteSurveyModel.setStatus(siteSurveyEntity.getStatus());
			siteSurveyModel.setCreationDate(siteSurveyEntity.getCreationDate().toString());
			if (siteSurveyEntity.getLastUpdatedDate() != null) {
				siteSurveyModel.setLastUpdatedDate(siteSurveyEntity.getLastUpdatedDate().toString());
			}
			if (siteSurveyEntity.getDateOfSubmit() != null) {
				siteSurveyModel.setDateOfSubmit(siteSurveyEntity.getDateOfSubmit().toString());
			}
			siteSurveyModel.setSubmitted(siteSurveyEntity.isSubmitted());
			siteSurveyModel.setOwnerID(getUserInformation(user.getId()).getId());
			siteSurveyModel.setOwnerFirstName(getUserInformation(user.getId()).getFirstName());
			siteSurveyModel.setOwnerLastName(getUserInformation(user.getId()).getLastName());
			siteSurveyModel.setOwnerCompany(getUserInformation(user.getId()).getCompany());
			
			return siteSurveyModel;
				
		} catch (Exception e) {
			e.printStackTrace();
			return siteSurveyModel;
		}
		
	}
	
	
	
	/**
	 * cancel site survey
	 */
	
	public String cancelSiteSurvey(Long siteSurveyID){
		
		try {
			TimeZone.setDefault (  TimeZone.getTimeZone ("PST8PDT")) ;
			DateTime dt = new DateTime(new Date(),TimeZone.getTimeZone("PST8PDT"));
			SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			isoFormat.setTimeZone(TimeZone.getTimeZone("PST8PDT"));
			Date date = isoFormat.parse(dt+"");
			
			SiteSurveyEntity siteSurveyEntity = siteSurveyRepo.findByIdAndIsCanceled(siteSurveyID, false);
			if (siteSurveyEntity != null) {
				siteSurveyEntity.setStatus("Canceled");
				siteSurveyEntity.setCancelDate(date);
				siteSurveyEntity.setCanceled(true);
				
				siteSurveyRepo.save(siteSurveyEntity);
			}
			
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}
	
	
	
	public String uploadHomePicture(MultipartFile file, String fileName, String siteSurveyiD) throws IOException {

		try {
			SiteSurveyEntity siteSurvey = siteSurveyRepo.findById(Long.valueOf(siteSurveyiD)).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" +Long.valueOf(siteSurveyiD)));
				siteSurvey.setHasProjectSiteImage(true);
				siteSurveyRepo.save(siteSurvey);
				byte[] bytes5 = file.getBytes();
				new File(getfilesPath()+"SiteSurvey-"+siteSurveyiD+"/Image").mkdirs();
				Path pathUp5 = Paths.get(getfilesPath()+"SiteSurvey-"+siteSurveyiD+"/Image/HomePicture.png");
				Files.write(pathUp5, bytes5);

				return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
			
	}

	public String uploadFormPicture(MultipartFile file[], String fileName, String siteSurveyiD, String type) throws IOException {

		try {
			SiteSurveyEntity siteSurvey = siteSurveyRepo.findById(Long.valueOf(siteSurveyiD)).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" +Long.valueOf(siteSurveyiD)));

			
//	byte[] bytes5 = file.getBytes();
			new File(getfilesPath()+"SiteSurvey-"+siteSurveyiD+"/Image").mkdirs();
			new File(getfilesPath()+"SiteSurvey-"+siteSurveyiD+"/Image/"+type).mkdirs();
			String path = getfilesPath(); 
			 LocalDateTime currentTime = LocalDateTime.now();
			 String date ="";
			 if (currentTime.getMonthValue()<10) {
				   date = "0"+currentTime.getMonthValue()+"-"+currentTime.getYear();
			 }else  date = currentTime.getMonthValue()+"-"+currentTime.getYear();
					 
					 
			  String file2="";
			  int i;
				File[] files;
			switch(type) {
			  case "SiteInformation":
				  for (MultipartFile fileUpload : file) {
						
						files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
						
						if (files != null && files.length > 0) {
							i = files.length;
						//	file2 = siteSurvey.getImageOfSiteInformationRafter();
						//	file2 =  "Roof Trusses_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}else {
							i=0;
							file2 =  "Image Captures for Site Information_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}
							
						
						
						try {
							byte[] bytes = fileUpload.getBytes();
							
							Path pathUpl = Paths
									.get(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/Image Captures for Site Information_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png");
							Files.write(pathUpl, bytes);
							file2=fileUpload.getOriginalFilename();
							
						} catch (Exception e) {
							e.printStackTrace();
							return "error";
						}

						

					}
					
				 
					files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
					i=0;
					
					for(i=0;i<files.length;i++) {
						if(i==0) {
							file2 = files[i].getName();
						}else
						file2 = file2+":"+files[i].getName();
					}
					siteSurvey.setHasImageCapturesatRafterTrusses(true);
					siteSurvey.setImageOfSiteInformationRafter(file2);
						
				  break;
			  case "SiteNote":
				  for (MultipartFile fileUpload : file) {
						
						files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
						
						if (files != null && files.length > 0) {
							i = files.length;
						//	file2 = siteSurvey.getImageOfSiteInformationRafter();
						//	file2 =  "Roof Trusses_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}else {
							i=0;
							file2 =  "Image Capture for Site Notes_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}
							
						
						
						try {
							byte[] bytes = fileUpload.getBytes();
							
							Path pathUpl = Paths
									.get(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/Image Capture for Site Notes_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png");
							Files.write(pathUpl, bytes);
							file2=fileUpload.getOriginalFilename();
							
						} catch (Exception e) {
							e.printStackTrace();
							return "error";
						}

						

					}
					
				 
					files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
					i=0;
					
					for(i=0;i<files.length;i++) {
						if(i==0) {
							file2 = files[i].getName();
						}else
						file2 = file2+":"+files[i].getName();
					}
					siteSurvey.setImageOfSiteNote(file2);
						
				  break;
			  case "ExistingPV":
				  for (MultipartFile fileUpload : file) {
						
						files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
						
						if (files != null && files.length > 0) {
							i = files.length;
						//	file2 = siteSurvey.getImageOfSiteInformationRafter();
						//	file2 =  "Roof Trusses_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}else {
							i=0;
							file2 =  "Image Captures for Existing PV_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}
							
						
						
						try {
							byte[] bytes = fileUpload.getBytes();
							
							Path pathUpl = Paths
									.get(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/Image Captures for Existing PV_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png");
							Files.write(pathUpl, bytes);
							file2=fileUpload.getOriginalFilename();
							
						} catch (Exception e) {
							e.printStackTrace();
							return "error";
						}

						

					}
					
				 
					files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
					i=0;
					
					for(i=0;i<files.length;i++) {
						if(i==0) {
							file2 = files[i].getName();
						}else
						file2 = file2+":"+files[i].getName();
					}
					siteSurvey.setImageOfExistingPV(file2);
						
				  break;
			  case "UtilityInfo":
				  for (MultipartFile fileUpload : file) {
						
						files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
						
						if (files != null && files.length > 0) {
							i = files.length;
						//	file2 = siteSurvey.getImageOfSiteInformationRafter();
						//	file2 =  "Roof Trusses_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}else {
							i=0;
							file2 =  "Image Captures for Utility Info_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}
							
						
						
						try {
							byte[] bytes = fileUpload.getBytes();
							
							Path pathUpl = Paths
									.get(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/Image Captures for Utility Info_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png");
							Files.write(pathUpl, bytes);
							file2=fileUpload.getOriginalFilename();
							
						} catch (Exception e) {
							e.printStackTrace();
							return "error";
						}

						

					}
					
				 
					files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
					i=0;
					
					for(i=0;i<files.length;i++) {
						if(i==0) {
							file2 = files[i].getName();
						}else
						file2 = file2+":"+files[i].getName();
					}
					siteSurvey.setImageOfutilityInformation(file2);
						
				  break;
			  case "ExistMainPanel":
				  for (MultipartFile fileUpload : file) {
						
						
						files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
						
						if (files != null && files.length > 0) {
							i = files.length;
						
						}else {
							i=0;
						}
							
						
						
						try {
							byte[] bytes = fileUpload.getBytes();
							
							Path pathUpl = Paths
									.get(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/Captures at MSP_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png");
							Files.write(pathUpl, bytes);
							file2=fileUpload.getOriginalFilename();
							
						} catch (Exception e) {
							e.printStackTrace();
							return "error";
						}

						

					}
					
				  
					files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
				
					
					for(i=0;i<files.length;i++) {
						if(i==0) {
							file2 = files[i].getName();
						}else
						file2 = file2+":"+files[i].getName();
					}
					
					 siteSurvey.setHasImageAtMSP(true);
					  siteSurvey.setImageOfExistingMainPanel(file2);
			
			    break;
			  case "SubPanel":
				  
				  
				  for (MultipartFile fileUpload : file) {
						
						
						files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
						
						if (files != null && files.length > 0) {
							i = files.length;
						
						}else {
							i=0;

						}
							
						
						
						try {
							byte[] bytes = fileUpload.getBytes();
							
							Path pathUpl = Paths
									.get(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/Sup Panel_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png");
							Files.write(pathUpl, bytes);
							file2=fileUpload.getOriginalFilename();
							
						} catch (Exception e) {
							e.printStackTrace();
							return "error";
						}

						

					}
					
				  
					files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
				
					
					for(i=0;i<files.length;i++) {
						if(i==0) {
							file2 = files[i].getName();
						}else
						file2 = file2+":"+files[i].getName();
					}
					
					siteSurvey.setHasImageCapturesAtSupPanel(true);
					  siteSurvey.setImageOfExistingSubPanel(file2);
		
			    break;  
			  case "BatteryInfo":
				  
				  for (MultipartFile fileUpload : file) {
					
						
						files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
						
						if (files != null && files.length > 0) {
							i = files.length;
						
						}else {
							i=0;
						//	file2 =  "Existing Solar Equip Location_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}
							
						
						
						try {
							byte[] bytes = fileUpload.getBytes();
							
							Path pathUpl = Paths
									.get(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/Image Captures for Inverter-AC combiner Location_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png");
							Files.write(pathUpl, bytes);
							file2=fileUpload.getOriginalFilename();
							
						} catch (Exception e) {
							e.printStackTrace();
							return "error";
						}

						

					}
					
				  
					files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
				
					
					for(i=0;i<files.length;i++) {
						if(i==0) {
							file2 = files[i].getName();
						}else
						file2 = file2+":"+files[i].getName();
					}
					//siteSurvey.setHasIma(true);
					siteSurvey.setImageBatteryInfo(file2);
		
			    break;   
			  case "InternetConnectionRoof":
				  
				  for (MultipartFile fileUpload : file) {
						
						
						files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
						
						if (files != null && files.length > 0) {
							i = files.length;
						
						}else {
							i=0;
						//	file2 =  "Internet Connection Roof_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}
							
						
						
						try {
							byte[] bytes = fileUpload.getBytes();
							
							Path pathUpl = Paths
									.get(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/Image Captures for Internet and Monitoring_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png");
							Files.write(pathUpl, bytes);
							file2=fileUpload.getOriginalFilename();
							
						} catch (Exception e) {
							e.printStackTrace();
							return "error";
						}

						

					}
					
				  
					files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
				
					
					for(i=0;i<files.length;i++) {
						if(i==0) {
							file2 = files[i].getName();
						}else
						file2 = file2+":"+files[i].getName();
					}
					  siteSurvey.setHasImageCapturesOfRoof(true);
					  siteSurvey.setImageOfInternetConnectionRoof(file2);
		
			    break;  
			  case "InternetConnectionElevation":
				  
				  for (MultipartFile fileUpload : file) {
						
						
						files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
						
						if (files != null && files.length > 0) {
							i = files.length;
						
						}else {
							i=0;
						//	file2 =  "Applicable Elevations_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}
							
						
						
						try {
							byte[] bytes = fileUpload.getBytes();
							
							Path pathUpl = Paths
									.get(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/Applicable Elevations_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png");
							Files.write(pathUpl, bytes);
							file2=fileUpload.getOriginalFilename();
							
						} catch (Exception e) {
							e.printStackTrace();
							return "error";
						}

						

					}
					
				  
					files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
				
					
					for(i=0;i<files.length;i++) {
						if(i==0) {
							file2 = files[i].getName();
						}else
						file2 = file2+":"+files[i].getName();
					}
					 siteSurvey.setHasImageCapturesOfApplicableElevationsViews(true);
					  siteSurvey.setImageOfInternetConnectionElevation(file2);
				  
			    break;
			  case "RoofAttic":
				  for (MultipartFile fileUpload : file) {
						
						files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
						
						if (files != null && files.length > 0) {
							i = files.length;
						//	file2 = siteSurvey.getImageOfSiteInformationRafter();
						//	file2 =  "Roof Trusses_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}else {
							i=0;
							file2 =  "Image Captures for Roof Attic_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}
						
						try {
							byte[] bytes = fileUpload.getBytes();
							
							Path pathUpl = Paths
									.get(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/Image Captures for Roof Attic_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png");
							Files.write(pathUpl, bytes);
							file2=fileUpload.getOriginalFilename();
							
						} catch (Exception e) {
							e.printStackTrace();
							return "error";
						}

						

					}
					
				 
					files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
					i=0;
					
					for(i=0;i<files.length;i++) {
						if(i==0) {
							file2 = files[i].getName();
						}else
						file2 = file2+":"+files[i].getName();
					}
					siteSurvey.setImageOfRoofAttic(file2);
						
				  break;
			  case "ArrayLocation":
				  for (MultipartFile fileUpload : file) {
						
						files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
						
						if (files != null && files.length > 0) {
							i = files.length;
						//	file2 = siteSurvey.getImageOfSiteInformationRafter();
						//	file2 =  "Roof Trusses_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}else {
							i=0;
							file2 =  "Image Captures for Array Location_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}
						
						try {
							byte[] bytes = fileUpload.getBytes();
							
							Path pathUpl = Paths
									.get(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/Image Captures for Array Location_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png");
							Files.write(pathUpl, bytes);
							file2=fileUpload.getOriginalFilename();
							
						} catch (Exception e) {
							e.printStackTrace();
							return "error";
						}

						

					}
					
				 
					files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
					i=0;
					
					for(i=0;i<files.length;i++) {
						if(i==0) {
							file2 = files[i].getName();
						}else
						file2 = file2+":"+files[i].getName();
					}
					siteSurvey.setImageOfArrayLocation(file2);
						
				  break;
			  case "CarPortArrayLocation":
				  for (MultipartFile fileUpload : file) {
						
						files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
						
						if (files != null && files.length > 0) {
							i = files.length;
						//	file2 = siteSurvey.getImageOfSiteInformationRafter();
						//	file2 =  "Roof Trusses_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}else {
							i=0;
							file2 =  "Image Captures for CarPort-Patio Cover Array Location_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png";
						}
						
						try {
							byte[] bytes = fileUpload.getBytes();
							
							Path pathUpl = Paths
									.get(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/Image Captures for CarPort-Patio Cover Array Location_"+i+"_"+siteSurvey.getHomeOwnName()+"_"+date+".png");
							Files.write(pathUpl, bytes);
							file2=fileUpload.getOriginalFilename();
							
						} catch (Exception e) {
							e.printStackTrace();
							return "error";
						}

						

					}
					
				 
					files = new File(path + "SiteSurvey-" + siteSurveyiD +"/Image/"+type+"/").listFiles();
					i=0;
					
					for(i=0;i<files.length;i++) {
						if(i==0) {
							file2 = files[i].getName();
						}else
						file2 = file2+":"+files[i].getName();
					}
					siteSurvey.setImageOfCarPortArrayLocation(file2);
						
				  break;
			}		
			    siteSurveyRepo.save(siteSurvey);
				//Files.write(pathUp5, bytes5);		
				return file2;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
			
	}	
	
	
public List<PermitResponse> getAllProject(Long userID) {
		
		List<PermitResponse> allProject =  new ArrayList<>(); 
		
		try {
			AuthentificationEntity user = authRepo.findById(userID).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" +userID));
			
			if (user.getRoleEntity().getId() == 1 || user.getRoleEntity().getId() == 3 || user.getRoleEntity().getId() == 5) {
				allProject = siteSurveyRepo.getPermitResponse(false);
				
			} else if (user.getRoleEntity().getId() == 2){
				allProject = siteSurveyRepo.getPermitResponseById(false, user.getId());
			}
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allProject;
	}


/**
 * GET SITE SURVEY FOR REQUIRED FIELD logic
 * @param siteSurveyState
 * @return
 *//*
public SiteSurveyReqFieldLogicEntity getSiteSurveyReqFieldModel(String fieldstate){
	
	String fieldmodel=fieldstate.split(";;")[0];
	String fieldmodelstate=fieldstate.split(";;")[0];
	
	SiteSurveyReqFieldLogicEntity siteSurvey =  new SiteSurveyReqFieldLogicEntity(); 
	
	try {
		
			Query queryUser = em.createQuery("SELECT s FROM SiteSurveyReqFieldLogicEntity s where statereqfield = :p1")
					.setParameter("p1", fieldmodelstate);
			siteSurvey = (SiteSurveyReqFieldLogicEntity) queryUser.getSingleResult();
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	return siteSurvey;
}*/

public String getFiles(String typeFile, Long idSite) {
	String files = "";
	try {
		SiteSurveyEntity siteSurvey = siteSurveyRepo.findById(idSite).orElseThrow(
				() -> new ResourceNotFoundException("Entity not found for this id :" +idSite));
		switch(typeFile) {
		  case "SiteInformation":
			  files = siteSurvey.getImageOfSiteInformationRafter();
			  break;
		  case "SiteNote":
			  files = siteSurvey.getImageOfSiteNote();
			  break;
		  case "UtilityInfo":
			  files = siteSurvey.getImageOfutilityInformation();
			  break;
		  case "ExistMainPanel":
			  files = siteSurvey.getImageOfExistingMainPanel();
		    break;
		  case "ExistingPV":
			  files = siteSurvey.getImageOfExistingPV();
		    break;
		  case "SubPanel":
			  files = siteSurvey.getImageOfExistingSubPanel();
		    break;  
		  case "BatteryInfo":
			  files = siteSurvey.getImageBatteryInfo();
		    break;   
		  case "InternetConnectionRoof":
			  files = siteSurvey.getImageOfInternetConnectionRoof();
		    break;  
		  case "InternetConnectionElevation":
			  files = siteSurvey.getImageOfInternetConnectionElevation();
		    break;
	      case "RoofAttic":
		      files = siteSurvey.getImageOfRoofAttic();
	        break;
	      case "ArrayLocation":
		      files = siteSurvey.getImageOfArrayLocation();
	        break;
	      case "CarPortArrayLocation":
			      files = siteSurvey.getImageOfCarPortArrayLocation();
			break;
		}		
		return files;
	} catch (Exception e) {
		e.printStackTrace();
		return files;
	}

}

/**
 * SAVE SITE SURVEY ReqField
 * @param SiteSurveyReqFieldSettingEntity
 * @return RESULT
 */
public String saveSiteSurveyReqField(SiteSurveyReqFieldSettingEntity siteSurvey){
	
	try {
				SiteSurveyReqFieldSettingEntity siteSurveyEntityReqField = siteSurveyReqFieldSettingRepo.findById(siteSurvey.getId()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" +siteSurvey.getId()));
		if ( siteSurveyEntityReqField != null) {
			siteSurveyEntityReqField.setHomeOwnName(siteSurvey.getHomeOwnName());			
			/////------------- site information -----------/////
			siteSurveyEntityReqField.setHomeOwnLastName(siteSurvey.getHomeOwnLastName());
			siteSurveyEntityReqField.setProjectName(siteSurvey.getProjectName());
			siteSurveyEntityReqField.setBasicTypeOfSystem(siteSurvey.getBasicTypeOfSystem()); 
			siteSurveyEntityReqField.setContactName(siteSurvey.getContactName()); 
			siteSurveyEntityReqField.setStreetAddress(siteSurvey.getStreetAddress()); 
			siteSurveyEntityReqField.setCity(siteSurvey.getCity()); 
			siteSurveyEntityReqField.setCityOther(siteSurvey.getCity());
			siteSurveyEntityReqField.setState(siteSurvey.getState()); 
			siteSurveyEntityReqField.setzIP(siteSurvey.getzIP()); 
			siteSurveyEntityReqField.setMainContactPhone(siteSurvey.getMainContactPhone()); 
			siteSurveyEntityReqField.setOtherPhone(siteSurvey.getOtherPhone()); 
			siteSurveyEntityReqField.setEmailAddress(siteSurvey.getEmailAddress());
			siteSurveyEntityReqField.setHoa(siteSurvey.getHoa()); 
			siteSurveyEntityReqField.setPermittingAuthority(siteSurvey.getPermittingAuthority()); 
			siteSurveyEntityReqField.setProjectJurisOther(siteSurvey.getPermittingAuthority()); 
			siteSurveyEntityReqField.setLegalOwnerName(siteSurvey.getLegalOwnerName()); 
			siteSurveyEntityReqField.setParcelNumber(siteSurvey.getParcelNumber()); 
			siteSurveyEntityReqField.setRoofMaterialType(siteSurvey.getRoofMaterialType()); 
			siteSurveyEntityReqField.setWidthSeams(siteSurvey.getWidthSeams());
			siteSurveyEntityReqField.setWidthSeamsOther(siteSurvey.getWidthSeams());
			siteSurveyEntityReqField.setRiskCategory(siteSurvey.getRiskCategory());
			siteSurveyEntityReqField.setOtherRiskCategory(siteSurvey.getRiskCategory());
			siteSurveyEntityReqField.setBuildingOccupancy(siteSurvey.getBuildingOccupancy());
			siteSurveyEntityReqField.setOtherBuildingOccupancy(siteSurvey.getBuildingOccupancy());
			siteSurveyEntityReqField.setNumberOfStories(siteSurvey.getNumberOfStories());
			siteSurveyEntityReqField.setOtherTallStructure(siteSurvey.getNumberOfStories());
			siteSurveyEntityReqField.setMeanHeight(siteSurvey.getMeanHeight());
			siteSurveyEntityReqField.setProjectEquipmentStagingLocation(siteSurvey.getProjectEquipmentStagingLocation()); 
			siteSurveyEntityReqField.setOwnerPreferredRoofAccessLocation(siteSurvey.getOwnerPreferredRoofAccessLocation());
			siteSurveyEntityReqField.setAccessIssuesWith_MeterOrProposedACDisco(siteSurvey.getAccessIssuesWith_MeterOrProposedACDisco()); 
			siteSurveyEntityReqField.setDescribeAccessIssues(siteSurvey.getDescribeAccessIssues()); 
			siteSurveyEntityReqField.setContactpersonforutility(siteSurvey.getContactpersonforutility()); 
			siteSurveyEntityReqField.setOthercontactperson(siteSurvey.getOthercontactperson()); 
			siteSurveyEntityReqField.setContactphone(siteSurvey.getContactphone()); 
			siteSurveyEntityReqField.setOthercontactphone(siteSurvey.getOthercontactphone());
			siteSurveyEntityReqField.setImageOfSiteInformationRafter(siteSurvey.getImageOfSiteInformationRafter());
			
			/////------------- existing main panel -----------/////
	
			siteSurveyEntityReqField.setMainPanelMake(siteSurvey.getMainPanelMake()); 
			siteSurveyEntityReqField.setOtherMainPanelMake(siteSurvey.getMainPanelMake()); 
			siteSurveyEntityReqField.setMainPanelModel(siteSurvey.getMainPanelModel()); 
			siteSurveyEntityReqField.setVoltage(siteSurvey.getVoltage()); 
			siteSurveyEntityReqField.setOtherVoltage(siteSurvey.getOtherVoltage()); 
			siteSurveyEntityReqField.setOtherVoltageOther(siteSurvey.getOtherVoltage()); 
			siteSurveyEntityReqField.setOtherMSPBusbarRating(siteSurvey.getMspbusbarRating());
			siteSurveyEntityReqField.setmSPMainBreakerRating(siteSurvey.getmSPMainBreakerRating());
			siteSurveyEntityReqField.setOtherMSPMainBreakerRating(siteSurvey.getmSPMainBreakerRating());
			siteSurveyEntityReqField.setOpenBreakerSlots(siteSurvey.getOpenBreakerSlots()); 
			siteSurveyEntityReqField.setsCIR(siteSurvey.getsCIR()); 
			siteSurveyEntityReqField.setMainBreakerFeedGauge(siteSurvey.getMainBreakerFeedGauge());
			siteSurveyEntityReqField.setOtherMainBreakerFeedGauge(siteSurvey.getMainBreakerFeedGauge());
			siteSurveyEntityReqField.setUtilityFeederGauge(siteSurvey.getUtilityFeederGauge());
			siteSurveyEntityReqField.setGroundAccessible(siteSurvey.getGroundAccessible());
			siteSurveyEntityReqField.setUndergroundOrOverheadFeed(siteSurvey.getUndergroundOrOverheadFeed());
			siteSurveyEntityReqField.setCenterFed(siteSurvey.getCenterFed());
			siteSurveyEntityReqField.setImageOfExistingMainPanel(siteSurvey.getImageOfExistingMainPanel());
			siteSurveyEntityReqField.setMspbusbarRating(siteSurvey.getMspbusbarRating());
			siteSurveyEntityReqField.setUpgradingMainServicePanel(siteSurvey.getUpgradingMainServicePanel());

			/////------------- existing subpanel -----------/////
			
			siteSurveyEntityReqField.setSubpanelMake(siteSurvey.getSubpanelMake());
			siteSurveyEntityReqField.setSubpanelModel(siteSurvey.getSubpanelModel());
			siteSurveyEntityReqField.setSubpanelVoltage(siteSurvey.getSubpanelVoltage());
			siteSurveyEntityReqField.setOtherSubpanelVoltage(siteSurvey.getOtherSubpanelVoltage());
			siteSurveyEntityReqField.setSecondOtherVoltageOther(siteSurvey.getOtherSubpanelVoltage());
			siteSurveyEntityReqField.setSubpanelBusbarRating(siteSurvey.getSubpanelBusbarRating());
			siteSurveyEntityReqField.setOtherSubpanelBusbarRating(siteSurvey.getSubpanelBusbarRating());
			siteSurveyEntityReqField.setSubpanelMainBreakerRating(siteSurvey.getSubpanelMainBreakerRating());
			siteSurveyEntityReqField.setOtherSubpanelMainBreakerRating(siteSurvey.getOtherSubpanelMainBreakerRating());
			siteSurveyEntityReqField.setOpenBreakerSlotsinSubpanel(siteSurvey.getOpenBreakerSlotsinSubpanel());
			siteSurveyEntityReqField.setSubpanelFeederGauge(siteSurvey.getSubpanelFeederGauge());
			siteSurveyEntityReqField.setOtherSubpanelFeederGauge(siteSurvey.getSubpanelFeederGauge());
			siteSurveyEntityReqField.setExistingSubpanel(siteSurvey.getExistingSubpanel());
			siteSurveyEntityReqField.setTieInPOCIsAtSubpanel(siteSurvey.getTieInPOCIsAtSubpanel());
			siteSurveyEntityReqField.setImageOfExistingSubPanel(siteSurvey.getImageOfExistingSubPanel());

			/////------------- site note -----------/////
			 
			siteSurveyEntityReqField.setSiteNotes(siteSurvey.getSiteNotes());
			siteSurveyEntityReqField.setImageOfSiteNotes(siteSurvey.getImageOfSiteNotes());
			
			/////------------- utility information -----------/////
			
			siteSurveyEntityReqField.setUtilityCoName(siteSurvey.getUtilityCoName());
			siteSurveyEntityReqField.setUtilityCompanyNameOther(siteSurvey.getUtilityCoName());
			siteSurveyEntityReqField.setMeterNumber(siteSurvey.getMeterNumber());
			siteSurveyEntityReqField.setnEMType(siteSurvey.getnEMType());
			siteSurveyEntityReqField.setOtherNEMType(siteSurvey.getOtherNEMType());
			siteSurveyEntityReqField.setPostSolarRate(siteSurvey.getPostSolarRate());
			siteSurveyEntityReqField.setPostSolarRateOther(siteSurvey.getPostSolarRate());
			siteSurveyEntityReqField.setUsageHistoryOffset(siteSurvey.getUsageHistoryOffset());
			siteSurveyEntityReqField.setPaceEntity(siteSurvey.getPaceEntity());
			siteSurveyEntityReqField.setPaceEntityOther(siteSurvey.getPaceEntity());
			siteSurveyEntityReqField.setaCDiscoWillbemorethan10FromUtilityMeter(siteSurvey.getaCDiscoWillbemorethan10FromUtilityMeter());
			siteSurveyEntityReqField.setAtLeast4MonthsOfelectricbillinghistory(siteSurvey.getAtLeast4MonthsOfelectricbillinghistory()); 
			siteSurveyEntityReqField.setSquareFeetOfLivingArea(siteSurvey.getSquareFeetOfLivingArea());
			siteSurveyEntityReqField.setRecentAnnualUsage(siteSurvey.getRecentAnnualUsage());
			siteSurveyEntityReqField.setNumberOfElectricVehicles(siteSurvey.getNumberOfElectricVehicles());
			siteSurveyEntityReqField.setImageOfUtilityInfo(siteSurvey.getImageOfUtilityInfo());
			
			/////------------- existing pV system at site -----------/////

			siteSurveyEntityReqField.setpVSystemMake(siteSurvey.getpVSystemMake());
			siteSurveyEntityReqField.setpVSystemModel(siteSurvey.getpVSystemModel());
			siteSurveyEntityReqField.setpVQuantity(siteSurvey.getpVQuantity());
			siteSurveyEntityReqField.setGridTiedInverterMake(siteSurvey.getGridTiedInverterMake());
			siteSurveyEntityReqField.setGridTiedInverterModel(siteSurvey.getGridTiedInverterModel());
			siteSurveyEntityReqField.setGridTiedInverterQuantity(siteSurvey.getGridTiedInverterQuantity());
			siteSurveyEntityReqField.setaCDiscoMake(siteSurvey.getaCDiscoMake());
			siteSurveyEntityReqField.setaCDiscoModel(siteSurvey.getaCDiscoModel()); 
			siteSurveyEntityReqField.setACDiscoRating(siteSurvey.getACDiscoRating()); 
			siteSurveyEntityReqField.setConnectionType(siteSurvey.getConnectionType());
			siteSurveyEntityReqField.setOtherConnectionType(siteSurvey.getConnectionType());
			siteSurveyEntityReqField.setExistingBattery(siteSurvey.getExistingBattery());
			siteSurveyEntityReqField.setNotesOnExistingBatterySystem(siteSurvey.getNotesOnExistingBatterySystem());
			siteSurveyEntityReqField.setpVBreaker1(siteSurvey.getpVBreaker1());
			siteSurveyEntityReqField.setpVBreaker2(siteSurvey.getpVBreaker2());
			siteSurveyEntityReqField.setpVBreaker3(siteSurvey.getpVBreaker3());
			siteSurveyEntityReqField.setpVBreaker4(siteSurvey.getpVBreaker4());
			siteSurveyEntityReqField.setpVBreaker5(siteSurvey.getpVBreaker5());
			siteSurveyEntityReqField.setExistingPVSystemAtSite(siteSurvey.getExistingPVSystemAtSite());
			siteSurveyEntityReqField.setExistingACDisco(siteSurvey.getExistingACDisco());
			siteSurveyEntityReqField.setImageOfExistingPV(siteSurvey.getImageOfExistingPV());

			/////------------- battery info -----------/////

			siteSurveyEntityReqField.setCircuitstoRelocatetoCriticalLoadsPanel(siteSurvey.getCircuitstoRelocatetoCriticalLoadsPanel());
			siteSurveyEntityReqField.setBatteryLocation(siteSurvey.getBatteryLocation());
			siteSurveyEntityReqField.setInverter1Model(siteSurvey.getInverter1Model());
			siteSurveyEntityReqField.setInverter2Model(siteSurvey.getInverter2Model());
			siteSurveyEntityReqField.setInverter3Model(siteSurvey.getInverter3Model());
			siteSurveyEntityReqField.setBatteryInverterIsGridTiedInverter(siteSurvey.getBatteryInverterIsGridTiedInverter());
			siteSurveyEntityReqField.setProposedBattery(siteSurvey.getProposedBattery());
			siteSurveyEntityReqField.setImageBatteryInfo(siteSurvey.getImageBatteryInfo());
			//A.B 04-16 CR-3255-007
			siteSurveyEntityReqField.setInverterTechnology(siteSurvey.getInverterTechnology());
			siteSurveyEntityReqField.setNotesOnInverter(siteSurvey.getNotesOnInverter());
			siteSurveyEntityReqField.setTypeOfBatterySystem(siteSurvey.getTypeOfBatterySystem());
			siteSurveyEntityReqField.setCriticalLoadPanelLocation(siteSurvey.getCriticalLoadPanelLocation());

			/////------------- internet connection for production monitoring -----------/////	
			
			siteSurveyEntityReqField.setProductionMonitor(siteSurvey.getProductionMonitor());
			siteSurveyEntityReqField.setActiveInternetConnection(siteSurvey.getActiveInternetConnection());
			siteSurveyEntityReqField.setModemLocation(siteSurvey.getModemLocation());
			siteSurveyEntityReqField.setConnectTheMonitor(siteSurvey.getConnectTheMonitor()); 
			siteSurveyEntityReqField.setVerifyRoofMeasurementsPreRoofLayoutProvided(siteSurvey.getVerifyRoofMeasurementsPreRoofLayoutProvided());
			siteSurveyEntityReqField.setImageOfInternetConnectionRoof(siteSurvey.getImageOfInternetConnectionRoof());
			siteSurveyEntityReqField.setImageOfInternetConnectionElevation(siteSurvey.getImageOfInternetConnectionElevation());
			
			/////------------- roof mount -----------/////
	
			siteSurveyEntityReqField.setRoofMaterial(siteSurvey.getRoofMaterial());
			siteSurveyEntityReqField.setNumberOfLayers(siteSurvey.getNumberOfLayers());
			siteSurveyEntityReqField.setRoofAge(siteSurvey.getRoofAge());
			siteSurveyEntityReqField.setpVModel(siteSurvey.getpVModel());
			siteSurveyEntityReqField.setpVModelCarpotPatio(siteSurvey.getpVModelCarpotPatio());
			siteSurveyEntityReqField.setpVModelNonRoof(siteSurvey.getpVModelNonRoof());
			siteSurveyEntityReqField.setGableRakeOverhang(siteSurvey.getGableRakeOverhang());
			siteSurveyEntityReqField.setHeightAtGutter(siteSurvey.getHeightAtGutter());
			siteSurveyEntityReqField.setCrossSectionSize(siteSurvey.getCrossSectionSize()); 
			siteSurveyEntityReqField.setCrossSectionSizeOther(siteSurvey.getCrossSectionSize()); 
			siteSurveyEntityReqField.setRoofStructureChart(siteSurvey.getRoofStructureChart());
			siteSurveyEntityReqField.setStanchionMaxSpacing(siteSurvey.getStanchionMaxSpacing()); 
			siteSurveyEntityReqField.setRidgeBeamDepthAtArrays(siteSurvey.getRidgeBeamDepthAtArrays());
			siteSurveyEntityReqField.setMaxSpanAtArraysHS1(siteSurvey.getMaxSpanAtArraysHS1());
			siteSurveyEntityReqField.setMaxSpanAtArraysHS2(siteSurvey.getMaxSpanAtArraysHS2());
			siteSurveyEntityReqField.setRafterTrussSpacing(siteSurvey.getRafterTrussSpacing());
			siteSurveyEntityReqField.setRafterTrussSpOther(siteSurvey.getRafterTrussSpacing()); 
			
			//19/08/2019 :  CI : CR 2860 : save required field Setting for arrays chart fields
			siteSurveyEntityReqField.setRoofModuleAzimuth(siteSurvey.getRoofModuleAzimuth());
			siteSurveyEntityReqField.setRoofPitch(siteSurvey.getRoofPitch());
			siteSurveyEntityReqField.setRoofTiltKitUsed(siteSurvey.getRoofTiltKitUsed());
			siteSurveyEntityReqField.setRoofEaveOverhang(siteSurvey.getRoofEaveOverhang());
			siteSurveyEntityReqField.setOtherRoofEaveOverhang(siteSurvey.getRoofEaveOverhang());
			siteSurveyEntityReqField.setRoofModuleTilt(siteSurvey.getRoofModuleTilt());
			siteSurveyEntityReqField.setRoofModuleQty(siteSurvey.getRoofModuleQty());
			/////------------- non roof mount -----------/////

			siteSurveyEntityReqField.setNonRoofContourSlope(siteSurvey.getNonRoofContourSlope());
			siteSurveyEntityReqField.setNonRoofPathPoint(siteSurvey.getNonRoofPathPoint());
			siteSurveyEntityReqField.setNonRoofGradingGrubbing(siteSurvey.getNonRoofGradingGrubbing());
			siteSurveyEntityReqField.setNonRoofSiteComposition(siteSurvey.getNonRoofSiteComposition());
			siteSurveyEntityReqField.setNonRoofElevationStructure(siteSurvey.getNonRoofElevationStructure());
			siteSurveyEntityReqField.setNonRoofExistingSecurity(siteSurvey.getNonRoofExistingSecurity());
			siteSurveyEntityReqField.setNonRoofPatioCoverValue(siteSurvey.getNonRoofPatioCoverValue());				
			
			//19/08/2019 :  CI : CR 2860 : save required field Setting for arrays chart fields
			siteSurveyEntityReqField.setNonRoofModuleAzimuth(siteSurvey.getNonRoofModuleAzimuth());
			siteSurveyEntityReqField.setNonRoofModuleTilt(siteSurvey.getNonRoofModuleTilt());
			siteSurveyEntityReqField.setNonRoofModuleQty(siteSurvey.getNonRoofModuleQty());
			/////------------- Patio cover attached -----------/////
			siteSurveyEntityReqField.setPatioCoverAttachedTypeBeam(siteSurvey.getPatioCoverAttachedTypeBeam());	
			siteSurveyEntityReqField.setPatioCoverAttachedTypePosts(siteSurvey.getPatioCoverAttachedTypePosts());	
			siteSurveyEntityReqField.setOtherPatioCoverAttachedTypePosts(siteSurvey.getPatioCoverAttachedTypePosts());	
			/////------------- Patio cover freestanding -----------/////
			
			siteSurveyEntityReqField.setPatioCoverFreestandingTypeBeam(siteSurvey.getPatioCoverFreestandingTypeBeam());	
			siteSurveyEntityReqField.setPatioCoverFreestandingTypePosts(siteSurvey.getPatioCoverFreestandingTypePosts());		
			siteSurveyEntityReqField.setOtherPatioCoverFreestandingTypePosts(siteSurvey.getPatioCoverFreestandingTypePosts());	
			siteSurveyEntityReqField.setPatioCoverFreestandingExtendOver(siteSurvey.getPatioCoverFreestandingExtendOver());	
			siteSurveyEntityReqField.setPatioCoverFreestandingPastEave(siteSurvey.getPatioCoverFreestandingPastEave());
			siteSurveyEntityReqField.setOtherPatioCoverFreestandingTypeBeam(siteSurvey.getPatioCoverFreestandingPastEave());
			siteSurveyEntityReqField.setUtilityMeterOrProposedACDisco(siteSurvey.getUtilityMeterOrProposedACDisco());
			siteSurveyEntityReqField.setImageOfCarPortArrayLocation(siteSurvey.getImageOfCarPortArrayLocation());
			siteSurveyEntityReqField.setImageOfutilityInformation(siteSurvey.getImageOfutilityInformation());
			siteSurveyEntityReqField.setImageOfExistingPV(siteSurvey.getImageOfExistingPV());
			siteSurveyEntityReqField.setImageOfRoofAttic(siteSurvey.getImageOfRoofAttic());
			siteSurveyEntityReqField.setImageOfArrayLocation(siteSurvey.getImageOfArrayLocation());
			siteSurveyEntityReqField.setSelectIfAttach(siteSurvey.getSelectIfAttach());
			siteSurveyEntityReqField.setRoofOrOpenFrame(siteSurvey.getRoofOrOpenFrame());
			siteSurveyEntityReqField.setSumofexistCircuit(siteSurvey.getSumofexistCircuit());
			siteSurveyEntityReqField.setNotesGroundMount(siteSurvey.getNotesGroundMount());
			siteSurveyEntityReqField.setAddressLine2(siteSurvey.getAddressLine2());
			siteSurveyEntityReqField.setSubPanelBreakerOCPD(siteSurvey.getSubPanelBreakerOCPD());
			siteSurveyEntityReqField.setNotesExistingPvSystem(siteSurvey.getNotesExistingPvSystem());
			siteSurveyEntityReqField.setNetworkName(siteSurvey.getNetworkName());
			siteSurveyEntityReqField.setNetworkPassword(siteSurvey.getNetworkPassword());
			siteSurveyEntityReqField.setRoofCondition(siteSurvey.getRoofCondition());
			siteSurveyEntityReqField.setRoofNotes(siteSurvey.getRoofNotes());
			siteSurveyEntityReqField.setMeasurmentsOfArea(siteSurvey.getMeasurmentsOfArea());
			siteSurveyEntityReqField.setNotesOnCarpotOrPatiot(siteSurvey.getNotesOnCarpotOrPatiot());
			siteSurveyEntityReqField.setNonRoofCarport(siteSurvey.getNonRoofCarport());
			siteSurveyEntityReqField.setNonRoofPatioCover(siteSurvey.getNonRoofPatioCover());
			siteSurveyEntityReqField.setWireRunOnRoof(siteSurvey.getWireRunOnRoof());
			siteSurveyEntityReqField.setWireRunInAttic(siteSurvey.getWireRunInAttic());
			siteSurveyEntityReqField.setTiltupModules(siteSurvey.getTiltupModules());
		
			//19/08/2019 :  CI : CR 2860 : save required field Setting for arrays chart fields
			siteSurveyEntityReqField.setPatioModuleAzimuth(siteSurvey.getPatioModuleAzimuth());
			siteSurveyEntityReqField.setPatioroofPitch(siteSurvey.getPatioroofPitch());
			siteSurveyEntityReqField.setPatioroofTiltKitUsed(siteSurvey.getPatioroofTiltKitUsed());
			siteSurveyEntityReqField.setPatioroofEaveOverhang(siteSurvey.getPatioroofEaveOverhang());
			siteSurveyEntityReqField.setOtherPatioEaveOverhang(siteSurvey.getPatioroofEaveOverhang());
			siteSurveyEntityReqField.setPatioroofModuleTilt(siteSurvey.getPatioroofModuleTilt());
			siteSurveyEntityReqField.setPatioroofModuleQty(siteSurvey.getPatioroofModuleQty());
			siteSurveyEntityReqField.setMeasOfAreaCarpot(siteSurvey.getMeasOfAreaCarpot());
			siteSurveyReqFieldSettingRepo.save(siteSurveyEntityReqField);
			//em.merge(siteSurveyEntityReqField);
			
			return "Done";
			
		}else return "Fail";
		
		
	} catch (Exception e) {
		e.printStackTrace();
		return "Fail";
	}
}

/**
 * SAVE SITE SURVEY ReqField setting with logic
 * @param SiteSurveyFieldSetting
 * @return RESULT
 */
public String saveSiteSurveyReqFieldLogic(SiteSurveyReqFieldLogicEntity siteSurveylogic){

			
			/* SiteSurveyReqFieldSettingLogicEntity */
	
	try {	
						SiteSurveyReqFieldLogicEntity siteSurveyEntitylogic = siteSurveyReqFieldLogicRepo.findById(siteSurveylogic.getId()).orElseThrow(
								() -> new ResourceNotFoundException("Entity not found for this id :" +siteSurveylogic.getId()));
				if ( siteSurveyEntitylogic != null) {
			
		siteSurveyEntitylogic.setHomeOwnName(siteSurveylogic.getHomeOwnName());
		siteSurveyEntitylogic.setHomeOwnLastName(siteSurveylogic.getHomeOwnLastName());
		siteSurveyEntitylogic.setProjectName(siteSurveylogic.getProjectName());
					
					/////------------- site information -----------/////
					
					siteSurveyEntitylogic.setBasicTypeOfSystem(siteSurveylogic.getBasicTypeOfSystem()); 
					siteSurveyEntitylogic.setContactName(siteSurveylogic.getContactName()); 
					siteSurveyEntitylogic.setStreetAddress(siteSurveylogic.getStreetAddress()); 
					siteSurveyEntitylogic.setCity(siteSurveylogic.getCity()); 
					siteSurveyEntitylogic.setState(siteSurveylogic.getState()); 
					siteSurveyEntitylogic.setzIP(siteSurveylogic.getzIP());
					siteSurveyEntitylogic.setMainContactPhone(siteSurveylogic.getMainContactPhone());  
					siteSurveyEntitylogic.setOtherPhone(siteSurveylogic.getOtherPhone()); 
					siteSurveyEntitylogic.setEmailAddress(siteSurveylogic.getEmailAddress());
					siteSurveyEntitylogic.setHoa(siteSurveylogic.getHoa()); 
					siteSurveyEntitylogic.setPermittingAuthority(siteSurveylogic.getPermittingAuthority()); 
					siteSurveyEntitylogic.setLegalOwnerName(siteSurveylogic.getLegalOwnerName()); 
					siteSurveyEntitylogic.setParcelNumber(siteSurveylogic.getParcelNumber()); 
					siteSurveyEntitylogic.setRoofMaterialType(siteSurveylogic.getRoofMaterialType()); 
					siteSurveyEntitylogic.setWidthSeams(siteSurveylogic.getWidthSeams());
					siteSurveyEntitylogic.setRiskCategory(siteSurveylogic.getRiskCategory());
					siteSurveyEntitylogic.setBuildingOccupancy(siteSurveylogic.getBuildingOccupancy());
					siteSurveyEntitylogic.setNumberOfStories(siteSurveylogic.getNumberOfStories());
					siteSurveyEntitylogic.setMeanHeight(siteSurveylogic.getMeanHeight());
					siteSurveyEntitylogic.setProjectEquipmentStagingLocation(siteSurveylogic.getProjectEquipmentStagingLocation()); 
					siteSurveyEntitylogic.setOwnerPreferredRoofAccessLocation(siteSurveylogic.getOwnerPreferredRoofAccessLocation());
					siteSurveyEntitylogic.setAccessIssuesWith_MeterOrProposedACDisco(siteSurveylogic.getAccessIssuesWith_MeterOrProposedACDisco()); 
					siteSurveyEntitylogic.setDescribeAccessIssues(siteSurveylogic.getDescribeAccessIssues()); 
					siteSurveyEntitylogic.setContactpersonforutility(siteSurveylogic.getContactpersonforutility()); 
					siteSurveyEntitylogic.setOthercontactperson(siteSurveylogic.getOthercontactperson()); 
					siteSurveyEntitylogic.setContactphone(siteSurveylogic.getContactphone()); 
					siteSurveyEntitylogic.setOthercontactphone(siteSurveylogic.getOthercontactphone()); 
					
					/////------------- existing main panel -----------/////
			
					siteSurveyEntitylogic.setMainPanelMake(siteSurveylogic.getMainPanelMake()); 
					siteSurveyEntitylogic.setMainPanelModel(siteSurveylogic.getMainPanelModel()); 
					siteSurveyEntitylogic.setVoltage(siteSurveylogic.getVoltage()); 
					siteSurveyEntitylogic.setOtherVoltage(siteSurveylogic.getOtherVoltage()); 
					siteSurveyEntitylogic.setOtherMSPBusbarRating(siteSurveylogic.getOtherMSPBusbarRating());
					siteSurveyEntitylogic.setmSPMainBreakerRating(siteSurveylogic.getmSPMainBreakerRating());
					siteSurveyEntitylogic.setOpenBreakerSlots(siteSurveylogic.getOpenBreakerSlots()); 
					siteSurveyEntitylogic.setsCIR(siteSurveylogic.getsCIR()); 
					siteSurveyEntitylogic.setMainBreakerFeedGauge(siteSurveylogic.getMainBreakerFeedGauge());
					siteSurveyEntitylogic.setUtilityFeederGauge(siteSurveylogic.getUtilityFeederGauge());
					siteSurveyEntitylogic.setGroundAccessible(siteSurveylogic.getGroundAccessible());
					siteSurveyEntitylogic.setUndergroundOrOverheadFeed(siteSurveylogic.getUndergroundOrOverheadFeed());

					/////------------- existing subpanel -----------/////
					
					siteSurveyEntitylogic.setSubpanelMake(siteSurveylogic.getSubpanelMake());
					siteSurveyEntitylogic.setSubpanelModel(siteSurveylogic.getSubpanelModel());
					siteSurveyEntitylogic.setSubpanelVoltage(siteSurveylogic.getSubpanelVoltage());
					siteSurveyEntitylogic.setOtherSubpanelVoltage(siteSurveylogic.getOtherSubpanelVoltage());
					siteSurveyEntitylogic.setSubpanelBusbarRating(siteSurveylogic.getSubpanelBusbarRating());
					siteSurveyEntitylogic.setSubpanelMainBreakerRating(siteSurveylogic.getSubpanelMainBreakerRating());
					siteSurveyEntitylogic.setOtherSubpanelMainBreakerRating(siteSurveylogic.getOtherSubpanelMainBreakerRating());
					siteSurveyEntitylogic.setOpenBreakerSlotsinSubpanel(siteSurveylogic.getOpenBreakerSlotsinSubpanel());
					siteSurveyEntitylogic.setSubpanelFeederGauge(siteSurveylogic.getSubpanelFeederGauge());

					/////------------- site note -----------/////
					 
					siteSurveyEntitylogic.setSiteNotes(siteSurveylogic.getSiteNotes());
					
					/////------------- utility information -----------/////
					
					siteSurveyEntitylogic.setUtilityCoName(siteSurveylogic.getUtilityCoName());
					siteSurveyEntitylogic.setMeterNumber(siteSurveylogic.getMeterNumber());
					siteSurveyEntitylogic.setnEMType(siteSurveylogic.getnEMType());
					siteSurveyEntitylogic.setOtherNEMType(siteSurveylogic.getOtherNEMType());
					siteSurveyEntitylogic.setPostSolarRate(siteSurveylogic.getPostSolarRate());
					siteSurveyEntitylogic.setUsageHistoryOffset(siteSurveylogic.getUsageHistoryOffset());
					siteSurveyEntitylogic.setPaceEntity(siteSurveylogic.getPaceEntity());
					siteSurveyEntitylogic.setaCDiscoWillbemorethan10FromUtilityMeter(siteSurveylogic.getaCDiscoWillbemorethan10FromUtilityMeter());
					siteSurveyEntitylogic.setAtLeast4MonthsOfelectricbillinghistory(siteSurveylogic.getAtLeast4MonthsOfelectricbillinghistory()); 
					siteSurveyEntitylogic.setSquareFeetOfLivingArea(siteSurveylogic.getSquareFeetOfLivingArea());
					siteSurveyEntitylogic.setRecentAnnualUsage(siteSurveylogic.getRecentAnnualUsage());
					siteSurveyEntitylogic.setNumberOfElectricVehicles(siteSurveylogic.getNumberOfElectricVehicles()); 
					
					/////------------- existing pV system at site -----------/////

					siteSurveyEntitylogic.setpVSystemMake(siteSurveylogic.getpVSystemMake());
					siteSurveyEntitylogic.setpVSystemModel(siteSurveylogic.getpVSystemModel());
					siteSurveyEntitylogic.setpVQuantity(siteSurveylogic.getpVQuantity());
					siteSurveyEntitylogic.setGridTiedInverterMake(siteSurveylogic.getGridTiedInverterMake());
					siteSurveyEntitylogic.setGridTiedInverterModel(siteSurveylogic.getGridTiedInverterModel());
					siteSurveyEntitylogic.setGridTiedInverterQuantity(siteSurveylogic.getGridTiedInverterQuantity());
					siteSurveyEntitylogic.setaCDiscoMake(siteSurveylogic.getaCDiscoMake());
					siteSurveyEntitylogic.setaCDiscoModel(siteSurveylogic.getaCDiscoModel()); 
					siteSurveyEntitylogic.setACDiscoRating(siteSurveylogic.getACDiscoRating()); 
					siteSurveyEntitylogic.setConnectionType(siteSurveylogic.getConnectionType());
					siteSurveyEntitylogic.setpVBreaker1(siteSurveylogic.getpVBreaker1());
					siteSurveyEntitylogic.setpVBreaker2(siteSurveylogic.getpVBreaker2());
					siteSurveyEntitylogic.setpVBreaker3(siteSurveylogic.getpVBreaker3());
					siteSurveyEntitylogic.setpVBreaker4(siteSurveylogic.getpVBreaker4());
					siteSurveyEntitylogic.setpVBreaker5(siteSurveylogic.getpVBreaker5()); 

					/////------------- battery info -----------/////

					siteSurveyEntitylogic.setCircuitstoRelocatetoCriticalLoadsPanel(siteSurveylogic.getCircuitstoRelocatetoCriticalLoadsPanel());
					siteSurveyEntitylogic.setBatteryLocation(siteSurveylogic.getBatteryLocation());
					siteSurveyEntitylogic.setInverter1Model(siteSurveylogic.getInverter1Model());
					siteSurveyEntitylogic.setInverter2Model(siteSurveylogic.getInverter2Model());
					siteSurveyEntitylogic.setInverter3Model(siteSurveylogic.getInverter3Model());
					//A.B 04-16 CR-3255-007
					siteSurveyEntitylogic.setInverterTechnology(siteSurveylogic.getInverterTechnology());
					siteSurveyEntitylogic.setNotesOnInverter(siteSurveylogic.getNotesOnInverter());
					siteSurveyEntitylogic.setTypeOfBatterySystem(siteSurveylogic.getTypeOfBatterySystem());
					siteSurveyEntitylogic.setCriticalLoadPanelLocation(siteSurveylogic.getCriticalLoadPanelLocation());
					
					/////------------- internet connection for production monitoring -----------/////	
					
					siteSurveyEntitylogic.setProductionMonitor(siteSurveylogic.getProductionMonitor());
					siteSurveyEntitylogic.setActiveInternetConnection(siteSurveylogic.getActiveInternetConnection());
					siteSurveyEntitylogic.setModemLocation(siteSurveylogic.getModemLocation());
					siteSurveyEntitylogic.setConnectTheMonitor(siteSurveylogic.getConnectTheMonitor()); 
					siteSurveyEntitylogic.setVerifyRoofMeasurementsPreRoofLayoutProvided(siteSurveylogic.getVerifyRoofMeasurementsPreRoofLayoutProvided());
					
					/////------------- roof mount -----------/////
			
					siteSurveyEntitylogic.setRoofMaterial(siteSurveylogic.getRoofMaterial());
					siteSurveyEntitylogic.setNumberOfLayers(siteSurveylogic.getNumberOfLayers());
					siteSurveyEntitylogic.setRoofAge(siteSurveylogic.getRoofAge());
					siteSurveyEntitylogic.setpVModel(siteSurveylogic.getpVModel());
					siteSurveyEntitylogic.setpVModelCarpotPatio(siteSurveylogic.getpVModelCarpotPatio());
					siteSurveyEntitylogic.setpVModelNonRoof(siteSurveylogic.getpVModelNonRoof());
					siteSurveyEntitylogic.setGableRakeOverhang(siteSurveylogic.getGableRakeOverhang());
					siteSurveyEntitylogic.setHeightAtGutter(siteSurveylogic.getHeightAtGutter());
					siteSurveyEntitylogic.setCrossSectionSize(siteSurveylogic.getCrossSectionSize()); 
					siteSurveyEntitylogic.setCrossSectionSizeOther(siteSurveylogic.getCrossSectionSizeOther()); 
					siteSurveyEntitylogic.setRoofStructureChart(siteSurveylogic.getRoofStructureChart());
					siteSurveyEntitylogic.setStanchionMaxSpacing(siteSurveylogic.getStanchionMaxSpacing()); 
					siteSurveyEntitylogic.setRidgeBeamDepthAtArrays(siteSurveylogic.getRidgeBeamDepthAtArrays());
					siteSurveyEntitylogic.setMaxSpanAtArraysHS1(siteSurveylogic.getMaxSpanAtArraysHS1());
					siteSurveyEntitylogic.setMaxSpanAtArraysHS2(siteSurveylogic.getMaxSpanAtArraysHS2());
					siteSurveyEntitylogic.setRafterTrussSpacing(siteSurveylogic.getRafterTrussSpacing());
					
					//19/08/2019 :  CI : CR 2860 : save required field logic for arrays chart fields
					siteSurveyEntitylogic.setRoofModuleAzimuth(siteSurveylogic.getRoofModuleAzimuth());
					siteSurveyEntitylogic.setRoofPitch(siteSurveylogic.getRoofPitch());
					siteSurveyEntitylogic.setRoofTiltKitUsed(siteSurveylogic.getRoofTiltKitUsed());
					siteSurveyEntitylogic.setRoofEaveOverhang(siteSurveylogic.getRoofEaveOverhang());
					siteSurveyEntitylogic.setOtherRoofEaveOverhang(siteSurveylogic.getOtherRoofEaveOverhang());
					siteSurveyEntitylogic.setRoofModuleTilt(siteSurveylogic.getRoofModuleTilt());
					siteSurveyEntitylogic.setRoofModuleQty(siteSurveylogic.getRoofModuleQty());
					/////------------- non roof mount -----------/////

					siteSurveyEntitylogic.setNonRoofContourSlope(siteSurveylogic.getNonRoofContourSlope());
					siteSurveyEntitylogic.setNonRoofPathPoint(siteSurveylogic.getNonRoofPathPoint());
					siteSurveyEntitylogic.setNonRoofGradingGrubbing(siteSurveylogic.getNonRoofGradingGrubbing());
					siteSurveyEntitylogic.setNonRoofSiteComposition(siteSurveylogic.getNonRoofSiteComposition());
					siteSurveyEntitylogic.setNonRoofElevationStructure(siteSurveylogic.getNonRoofElevationStructure());
					siteSurveyEntitylogic.setNonRoofExistingSecurity(siteSurveylogic.getNonRoofExistingSecurity());
					siteSurveyEntitylogic.setNonRoofPatioCoverValue(siteSurveylogic.getNonRoofPatioCoverValue());	
					
					//19/08/2019 :  CI : CR 2860 : save required field logic for arrays chart fields
					siteSurveyEntitylogic.setNonRoofModuleAzimuth(siteSurveylogic.getNonRoofModuleAzimuth());
					siteSurveyEntitylogic.setNonRoofModuleTilt(siteSurveylogic.getNonRoofModuleTilt());
					siteSurveyEntitylogic.setNonRoofModuleQty(siteSurveylogic.getNonRoofModuleQty());
					/////------------- Patio cover attached -----------/////
					siteSurveyEntitylogic.setPatioCoverAttachedTypeBeam(siteSurveylogic.getPatioCoverAttachedTypeBeam());	
					siteSurveyEntitylogic.setPatioCoverAttachedTypePosts(siteSurveylogic.getPatioCoverAttachedTypePosts());	
					/////------------- Patio cover freestanding -----------/////
					
					siteSurveyEntitylogic.setPatioCoverFreestandingTypeBeam(siteSurveylogic.getPatioCoverFreestandingTypeBeam());	
					siteSurveyEntitylogic.setPatioCoverFreestandingTypePosts(siteSurveylogic.getPatioCoverFreestandingTypePosts());	
					siteSurveyEntitylogic.setPatioCoverFreestandingExtendOver(siteSurveylogic.getPatioCoverFreestandingExtendOver());	
					siteSurveyEntitylogic.setPatioCoverFreestandingPastEave(siteSurveylogic.getPatioCoverFreestandingPastEave());
					siteSurveyEntitylogic.setUtilityMeterOrProposedACDisco(siteSurveylogic.getUtilityMeterOrProposedACDisco());
					
					siteSurveyEntitylogic.setCenterFed(siteSurveylogic.getCenterFed());
					siteSurveyEntitylogic.setExistingPVSystemAtSite(siteSurveylogic.getExistingPVSystemAtSite());
					siteSurveyEntitylogic.setExistingBattery(siteSurveylogic.getExistingBattery());
					siteSurveyEntitylogic.setNotesOnExistingBatterySystem(siteSurveylogic.getNotesOnExistingBatterySystem());
					siteSurveyEntitylogic.setUpgradingMainServicePanel(siteSurveylogic.getUpgradingMainServicePanel());
					siteSurveyEntitylogic.setProposedBattery(siteSurveylogic.getProposedBattery());
					siteSurveyEntitylogic.setWireRunOnRoof(siteSurveylogic.getWireRunOnRoof());
					siteSurveyEntitylogic.setWireRunInAttic(siteSurveylogic.getWireRunInAttic());
					siteSurveyEntitylogic.setTiltupModules(siteSurveylogic.getTiltupModules());
					siteSurveyEntitylogic.setNonRoofCarport(siteSurveylogic.getNonRoofCarport());
					siteSurveyEntitylogic.setExistingACDisco(siteSurveylogic.getExistingACDisco());
					siteSurveyEntitylogic.setTieInPOCIsAtSubpanel(siteSurveylogic.getTieInPOCIsAtSubpanel());
					siteSurveyEntitylogic.setExistingSubpanel(siteSurveylogic.getExistingSubpanel());
					siteSurveyEntitylogic.setOpenBreakerSlotsinSubpanel(siteSurveylogic.getOpenBreakerSlotsinSubpanel());
					siteSurveyEntitylogic.setNonRoofPatioCover(siteSurveylogic.getNonRoofPatioCover());

					siteSurveyEntitylogic.setImageOfSiteInformationRafter(siteSurveylogic.getImageOfSiteInformationRafter());
					siteSurveyEntitylogic.setCenterFed(siteSurveylogic.getCenterFed());
					siteSurveyEntitylogic.setImageOfExistingMainPanel(siteSurveylogic.getImageOfExistingMainPanel());
					siteSurveyEntitylogic.setMspbusbarRating(siteSurveylogic.getMspbusbarRating());
					siteSurveyEntitylogic.setUpgradingMainServicePanel(siteSurveylogic.getUpgradingMainServicePanel());
					siteSurveyEntitylogic.setExistingSubpanel(siteSurveylogic.getExistingSubpanel());
					siteSurveyEntitylogic.setTieInPOCIsAtSubpanel(siteSurveylogic.getTieInPOCIsAtSubpanel());
					siteSurveyEntitylogic.setImageOfExistingSubPanel(siteSurveylogic.getImageOfExistingSubPanel());
					siteSurveyEntitylogic.setImageOfSiteNotes(siteSurveylogic.getImageOfSiteNotes());
					siteSurveyEntitylogic.setNumberOfElectricVehicles(siteSurveylogic.getNumberOfElectricVehicles());
					siteSurveyEntitylogic.setImageOfUtilityInfo(siteSurveylogic.getImageOfUtilityInfo());
					siteSurveyEntitylogic.setExistingPVSystemAtSite(siteSurveylogic.getExistingPVSystemAtSite());
					siteSurveyEntitylogic.setExistingACDisco(siteSurveylogic.getExistingACDisco());
					siteSurveyEntitylogic.setImageOfExistingPV(siteSurveylogic.getImageOfExistingPV());
					siteSurveyEntitylogic.setExistingBattery(siteSurveylogic.getExistingBattery());
					siteSurveyEntitylogic.setNotesOnExistingBatterySystem(siteSurveylogic.getNotesOnExistingBatterySystem());
					siteSurveyEntitylogic.setProposedBattery(siteSurveylogic.getProposedBattery());
					siteSurveyEntitylogic.setImageBatteryInfo(siteSurveylogic.getImageBatteryInfo());

					siteSurveyEntitylogic.setInverterTechnology(siteSurveylogic.getInverterTechnology());
					siteSurveyEntitylogic.setNotesOnInverter(siteSurveylogic.getNotesOnInverter());
					siteSurveyEntitylogic.setTypeOfBatterySystem(siteSurveylogic.getTypeOfBatterySystem());
					siteSurveyEntitylogic.setCriticalLoadPanelLocation(siteSurveylogic.getCriticalLoadPanelLocation());
					
					siteSurveyEntitylogic.setImageOfInternetConnectionRoof(siteSurveylogic.getImageOfInternetConnectionRoof());
					siteSurveyEntitylogic.setImageOfInternetConnectionElevation(siteSurveylogic.getImageOfInternetConnectionElevation());
					siteSurveyEntitylogic.setImageOfCarPortArrayLocation(siteSurveylogic.getImageOfCarPortArrayLocation());
					siteSurveyEntitylogic.setImageOfutilityInformation(siteSurveylogic.getImageOfutilityInformation());
					siteSurveyEntitylogic.setImageOfExistingPV(siteSurveylogic.getImageOfExistingPV());
					siteSurveyEntitylogic.setImageOfRoofAttic(siteSurveylogic.getImageOfRoofAttic());
					siteSurveyEntitylogic.setImageOfArrayLocation(siteSurveylogic.getImageOfArrayLocation());
					siteSurveyEntitylogic.setSelectIfAttach(siteSurveylogic.getSelectIfAttach());
					siteSurveyEntitylogic.setRoofOrOpenFrame(siteSurveylogic.getRoofOrOpenFrame());
					siteSurveyEntitylogic.setSumofexistCircuit(siteSurveylogic.getSumofexistCircuit());
					siteSurveyEntitylogic.setNotesGroundMount(siteSurveylogic.getNotesGroundMount());
					siteSurveyEntitylogic.setAddressLine2(siteSurveylogic.getAddressLine2());
					siteSurveyEntitylogic.setSubPanelBreakerOCPD(siteSurveylogic.getSubPanelBreakerOCPD());
					siteSurveyEntitylogic.setSecondOtherVoltageOther(siteSurveylogic.getSecondOtherVoltageOther());
					siteSurveyEntitylogic.setNotesExistingPvSystem(siteSurveylogic.getNotesExistingPvSystem());
					siteSurveyEntitylogic.setNetworkName(siteSurveylogic.getNetworkName());
					siteSurveyEntitylogic.setNetworkPassword(siteSurveylogic.getNetworkPassword());
					siteSurveyEntitylogic.setRoofCondition(siteSurveylogic.getRoofCondition());
					siteSurveyEntitylogic.setRoofNotes(siteSurveylogic.getRoofNotes());
					siteSurveyEntitylogic.setMeasurmentsOfArea(siteSurveylogic.getMeasurmentsOfArea());
					siteSurveyEntitylogic.setNotesOnCarpotOrPatiot(siteSurveylogic.getNotesOnCarpotOrPatiot());
					siteSurveyEntitylogic.setNonRoofCarport(siteSurveylogic.getNonRoofCarport());
					siteSurveyEntitylogic.setNonRoofPatioCover(siteSurveylogic.getNonRoofPatioCover());
					siteSurveyEntitylogic.setWireRunOnRoof(siteSurveylogic.getWireRunOnRoof());
					siteSurveyEntitylogic.setWireRunInAttic(siteSurveylogic.getWireRunInAttic());
					siteSurveyEntitylogic.setTiltupModules(siteSurveylogic.getTiltupModules());
					//19/08/2019 :  CI : CR 2860 : save required field logic for arrays chart fields
					siteSurveyEntitylogic.setPatioModuleAzimuth(siteSurveylogic.getPatioModuleAzimuth());
					siteSurveyEntitylogic.setPatioroofPitch(siteSurveylogic.getPatioroofPitch());
					siteSurveyEntitylogic.setPatioroofTiltKitUsed(siteSurveylogic.getPatioroofTiltKitUsed());
					siteSurveyEntitylogic.setPatioroofEaveOverhang(siteSurveylogic.getPatioroofEaveOverhang());
					siteSurveyEntitylogic.setOtherPatioEaveOverhang(siteSurveylogic.getOtherPatioEaveOverhang());
					siteSurveyEntitylogic.setPatioroofModuleTilt(siteSurveylogic.getPatioroofModuleTilt());
					siteSurveyEntitylogic.setPatioroofModuleQty(siteSurveylogic.getPatioroofModuleQty());
					siteSurveyEntitylogic.setMeasOfAreaCarpot(siteSurveylogic.getMeasOfAreaCarpot());
					
					siteSurveyReqFieldLogicRepo.save(siteSurveyEntitylogic);
			
			
			return "Done";
				
			
		}else return "Fail";
		
		
	} catch (Exception e) {
		e.printStackTrace();
		return "Fail";
	}
}


/**
 * GET SITE SURVEY FOR REQUIRED FIELD WITH LOGIC
 * @param siteSurveyState
 * @return
 */
public SiteSurveyFieldSetting getSiteSurveyReqLogField(String siteSurveyState){
	
	SiteSurveyFieldSetting siteSurvey =  new SiteSurveyFieldSetting(); 
	
	SiteSurveyReqFieldSettingEntity siteSurveyreq =  new SiteSurveyReqFieldSettingEntity(); 
	
	try {
			siteSurveyreq = siteSurveyReqFieldSettingRepo.findOneByStatereqfield(siteSurveyState);
			siteSurvey.setSiteSurveyReqFieldSettingEntity(siteSurveyreq);
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	SiteSurveyReqFieldLogicEntity siteSurveylog =  new SiteSurveyReqFieldLogicEntity(); 
	
	try {
		
			siteSurveylog = siteSurveyReqFieldLogicRepo.findOneByStatereqfield(siteSurveyState);
			
			siteSurvey.setSiteSurveyReqFieldLogicEntity(siteSurveylog);
			
			
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
	return siteSurvey;
}

public String convert (String s){
	return checkValue.Equals(s,"null") ? "" : s;
}

public LinkedHashMap<String, String[]> siteInformationMap(SiteSurveyEntity siteSurvey){
	//05-24-2019 : M.A : Junit correction
	
	LinkedHashMap<String, String[]> siteInfo = new LinkedHashMap<String, String[]>();
	
		try {
			String[] cache0 = { convert(siteSurvey.getHomeOwnName() + ""),
					"" };
			siteInfo.put("Homeowner First Name", cache0);
			
			String[] cache01 = { convert(siteSurvey.getHomeOwnLastName() + ""),
			"" };
			siteInfo.put("Homeowner Last Name", cache01);
			
			String[] cache02 = { convert(siteSurvey.getProjectName() + ""),
			"" };
			siteInfo.put("Project Name", cache02);
			
			String[] cache19 = { convert(siteSurvey.getContactName() + ""),
			"" };
			siteInfo.put("Contact Name (If Different)", cache19);
			
			String[] cache13 = { convert(siteSurvey.getStreetAddress() + ""),
			"" };
			siteInfo.put("Street Address", cache13);
			
			String[] cache133 = { convert(siteSurvey.getAddressLine2() + ""),
			"" };
			siteInfo.put("Address Line 2", cache133);

			String[] cache1 = { convert(checkValue.Equals(siteSurvey.getCity(), "Other")
					? siteSurvey.getCityOther() + "" : siteSurvey.getCity() + ""),
					"" };
			siteInfo.put("City", cache1);
			
			String[] cache2 = { convert(siteSurvey.getState() + ""),
			"" };
			siteInfo.put("State", cache2);
	
			String[] cache3 = { convert(siteSurvey.getzIP() + ""),
			"" };
			siteInfo.put("ZIP", cache3);
			
			String[] cache31 = { convert(siteSurvey.getMainContactPhone() + ""),
			"" };
			siteInfo.put("Main Contact Phone", cache31);
			
			String[] cache32 = { convert(siteSurvey.getOtherPhone() + ""),
			"" };
			siteInfo.put("Other Phone", cache32);
			
			String[] cache33 = { convert(siteSurvey.getEmailAddress() + ""),
			"" };
			siteInfo.put("Email Address", cache33);

			String[] cache4 = { convert(siteSurvey.getHoa() + ""),
			"" };
			siteInfo.put("HOA", cache4);
			
			String[] cache5 = { convert(siteSurvey.getPermittingAuthority() + ""),
			"" };
			siteInfo.put("Permitting Authority", cache5);
			
			String[] cache6 = { convert(siteSurvey.getLegalOwnerName() + ""),
			"" };
			siteInfo.put("Legal Owner's Name", cache6);
			
			String[] cache7 = { convert(siteSurvey.getParcelNumber() + ""),
			"" };
			siteInfo.put("Parcel # (APN)", cache7);
			
			String[] cache8 = { convert(checkValue.Equals(siteSurvey.getWidthSeams(), "Other") 
					? siteSurvey.getWidthSeamsOther()  + "" : siteSurvey.getWidthSeams()  + "" ),
			"" };
			siteInfo.put("Width of Seams", cache8);
			
			String[] cache9 = { convert(siteSurvey.getRoofMounted() + ""),
			"" };
			siteInfo.put("Roof Mounted", cache9);
			
			String[] cache91 = { convert(siteSurvey.getGroundMounted() + ""),
			"" };
			siteInfo.put("Ground Mounted", cache91);
			
			String[] cachePatio = { convert(siteSurvey.getPatioMounted() + ""),
			"" };
			siteInfo.put("Patio Cover", cachePatio);
			
			String[] cache93 = { convert(siteSurvey.getCarportMounted() + ""),
			"" };
			siteInfo.put("Carport", cache93);
			
			String[] cache94 = { convert(siteSurvey.getTextOther() + ""),
			"" };
			siteInfo.put("Other Basic Type of System", cache94);
			
			String[] cache941 = { convert(siteSurvey.getFrontAndBack() + ""),
			"" };
			siteInfo.put("Front and Back Posts", cache941);
			
			String[] cache942 = { convert(siteSurvey.getCantelever() + ""),
			"" };
			siteInfo.put("Cantilever & T-Type", cache942);
			
			String[] attachedToExtWal = { convert(siteSurvey.getAttachedToExtWal() + ""),
			"" };
			siteInfo.put("Attached to Ext Wal", attachedToExtWal);
			
			String[] attachedToFascia = { convert(siteSurvey.getAttachedToFascia() + ""),
			"" };
			siteInfo.put("Attached to Fascia", attachedToFascia);
			
			String[] attachedToSkylifts = { convert(siteSurvey.getAttachedToSkylifts() + ""),
			"" };
			siteInfo.put("Attached to Skylifts on Ext Walls", attachedToSkylifts);
			
			String[] freeStanding = { convert(siteSurvey.getFreeStanding() + ""),
			"" };
			siteInfo.put("Free Standing", freeStanding);
			
			String[] cache943 = { convert(siteSurvey.getRoofOrOpenFrame() + ""),
			"" };
			siteInfo.put("Roof or Open Frame", cache943);
			
			String[] cache10 = { convert(checkValue.Equals(siteSurvey.getRiskCategory(), "Other")  
					? siteSurvey.getOtherRiskCategory() + "" : siteSurvey.getRiskCategory() + ""),
			"" };
			siteInfo.put("Risk Category", cache10);
			
			String[] cache21 = { convert(checkValue.Equals(siteSurvey.getBuildingOccupancy(), "Other")  
				? siteSurvey.getOtherBuildingOccupancy() + "" : siteSurvey.getBuildingOccupancy() + ""),
			"" };
			siteInfo.put("Building Occupancy", cache21);
			
			String[] cache23 = { convert(checkValue.Equals(siteSurvey.getNumberOfStories(), "OtheStory")  
					? siteSurvey.getOtherTallStructure() + "" : siteSurvey.getNumberOfStories() + ""),
			"" };
			siteInfo.put("# of Stories", cache23);
			
			String[] cache232 = { convert(siteSurvey.getMeanHeight() + ""),
			"" };
			siteInfo.put("Mean Height", cache232);
			
			String[] cache11 = { convert(siteSurvey.getProjectEquipmentStagingLocation() + ""),
			"" };
			siteInfo.put("Project Equipment Staging Location", cache11);
			
			String[] cache24 = { convert(siteSurvey.getOwnerPreferredRoofAccessLocation() + ""),
			"" };
			siteInfo.put("Owner's Preferred Roof Access Location", cache24);
			
			String[] cache12 = { convert(siteSurvey.getAccessIssuesWith_MeterOrProposedACDisco() + ""),
			"" };
			siteInfo.put("Access Issues with the Meter or Proposed AC Disco", cache12);
			
			String[] cache14 = { convert(siteSurvey.getUtilityMeterOrProposedACDisco() + ""),
			"" };
			siteInfo.put("Utility Meter or Proposed AC Disco in a building or behind a locked gate", cache14);
			
			String[] cache15 = { convert(siteSurvey.getUnrestrainedAnimal() + ""),
			"" };
			siteInfo.put("Unrestrained animal", cache15);
			
			String[] cache16 = { convert(siteSurvey.getOtheraccessissue() + ""),
			"" };
			siteInfo.put("Other access issue", cache16);
			
			String[] cache25 = { convert(siteSurvey.getDescribeAccessIssues() + ""),
			"" };
			siteInfo.put("Describe", cache25);
			
			String[] cache17 = { convert(siteSurvey.getContactpersonforutility() + ""),
			"" };
			siteInfo.put("Contact person for the utility to gain access", cache17);
			
			String[] cache26 = { convert(siteSurvey.getOthercontactperson() + ""),
			"" };
			siteInfo.put("Other contact person", cache26);
			
			String[] cache18 = { convert(siteSurvey.getContactphone() + ""),
			"" };
			siteInfo.put("Contact phone", cache18);
			
			List<SiteSurveyTextAreaFieldsEntity> textAreaFields = siteSurveyTextAreaFieldsRepo.findBySiteSurveyEntityIdAndSiteSurveyCostumFieldEntityTabName(siteSurvey.getId(), "tab-r1");
			if(textAreaFields!=null && !textAreaFields.isEmpty()) {

				
				for (SiteSurveyTextAreaFieldsEntity textAreaField : textAreaFields) {
					String[] textBox = { convert(textAreaField.getTextBoxContent() ),
					"" };
					String fieldName =textAreaField.getSiteSurveyCostumFieldEntity().getFieldName();
					siteInfo.put(fieldName, textBox);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return siteInfo;
}

public LinkedHashMap<String, String[]> existMainPanelMap(SiteSurveyEntity siteSurvey){
	//05-24-2019 : M.A : Junit correction
	
	LinkedHashMap<String, String[]> siteInfo = new LinkedHashMap<>();
	
		try {
			String[] cache0 = { convert(siteSurvey.getCenterFed() + ""),
			"" };
			siteInfo.put("Center-Fed", cache0);
			
			if(checkValue.Equals(siteSurvey.getMainPanelMake(), "SquareD")) {
				String[] cache1 = { convert("Square D"),
				"" };
				siteInfo.put("Manufacturer of Main Service Panel", cache1);
			}else {
				String[] cache1 = { convert(siteSurvey.getMainPanelMake() + ""),
				"" };
				siteInfo.put("Manufacturer of Main Service Panel", cache1);
			}
		
			String[] cache2 = { convert(siteSurvey.getMainPanelModel() + ""),
			"" };
			siteInfo.put("Model of Main Service Panel", cache2);
		
			String[] cache3 = { convert(siteSurvey.getUpgradingMainServicePanel() + ""),
			"" };
			siteInfo.put("Upgrading Main Service Panel", cache3);
		
			String[] cache4 = { convert(siteSurvey.getVoltage() + ""),
			"" };
			siteInfo.put("Service Voltage", cache4);
			
			String[] cache16 = { convert( checkValue.Equals(siteSurvey.getOtherVoltage(), "Other")
					? siteSurvey.getOtherVoltageOther() + "" : siteSurvey.getOtherVoltage() + ""),
			"" };
			siteInfo.put("Other Service Voltage", cache16);
			
			String[] cache5 = { convert(checkValue.Equals(siteSurvey.getMspbusbarRating(), "Other")
					? siteSurvey.getOtherMSPBusbarRating() + "" : siteSurvey.getMspbusbarRating() + ""),
			"" };
			siteInfo.put("MSP Busbar Rating (Amps)", cache5);
			
			String[] cache6 = { convert(checkValue.Equals(siteSurvey.getmSPMainBreakerRating(), "Other")
					? siteSurvey.getOtherMSPMainBreakerRating() + "" : siteSurvey.getmSPMainBreakerRating() + ""),
			"" };
			siteInfo.put("MSP Main Breaker Rating (Amps)", cache6);
			
			String[] cache7 = { convert(siteSurvey.getOpenBreakerSlots() + ""),
			"" };
			siteInfo.put("Open Breaker Slots", cache7);
			
			String[] cache71 = { convert(siteSurvey.getSumofexistCircuit() + ""),
			"" };
			siteInfo.put("Sum of All Existing Circuit Breakers (Amps)", cache71);
			
			String[] cache8 = { convert(siteSurvey.getsCIR() + ""),
			"" };
			siteInfo.put("SCIR", cache8);
			
			String[] cache9 = { convert(checkValue.Equals(siteSurvey.getMainBreakerFeedGauge(), "Other")?  siteSurvey.getOtherMainBreakerFeedGauge() + "" : siteSurvey.getMainBreakerFeedGauge() + ""),
			"" };
			siteInfo.put("Main Breaker Feed Gauge", cache9);
			
			String[] cache10 = { convert(siteSurvey.getUtilityFeederGauge() + ""),
			"" };
			siteInfo.put("Utility Feeder Gauge", cache10);
			
			
			String[] cache14 = { convert(siteSurvey.getGroundAccessible() + ""),
			"" };
			siteInfo.put("Ground is Accessible", cache14);
			
			String[] cache15 = { convert(siteSurvey.getUndergroundOrOverheadFeed() + ""),
			"" };
			siteInfo.put("Underground or Overhead Feed", cache15);
			List<SiteSurveyTextAreaFieldsEntity> textAreaFields = siteSurveyTextAreaFieldsRepo.findBySiteSurveyEntityIdAndSiteSurveyCostumFieldEntityTabName(siteSurvey.getId(), "tab-r2");
			if(textAreaFields!=null && !textAreaFields.isEmpty()) {

				
				for (SiteSurveyTextAreaFieldsEntity textAreaField : textAreaFields) {
					String[] textBox = { convert(textAreaField.getTextBoxContent() ),
					"" };
					String fieldName =textAreaField.getSiteSurveyCostumFieldEntity().getFieldName();
					siteInfo.put(fieldName, textBox);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return siteInfo;
}

public LinkedHashMap<String, String[]> subPanelIfApl(SiteSurveyEntity siteSurvey){
	//05-24-2019 : M.A : Junit correction
	
	LinkedHashMap<String, String[]> siteInfo = new LinkedHashMap<>();
	
		try {
			String[] cache0 = { convert(siteSurvey.getExistingSubpanel() + ""),
			"" };
			siteInfo.put("Existing Subpanel", cache0);
		
			String[] cache1 = { convert(siteSurvey.getTieInPOCIsAtSubpanel() + ""),
					"" };
			siteInfo.put("Tie-In/POC is at the Subpanel", cache1);
			
			String[] cache3 = { convert(siteSurvey.getSubpanelMake() + ""),
			"" };
			siteInfo.put("Subpanel Make", cache3);
			
			String[] cache4 = { convert(siteSurvey.getSubpanelModel() + ""),
			"" };
			siteInfo.put("Subpanel Model", cache4);
			
			String[] cache5 = { convert(checkValue.Equals(siteSurvey.getSubpanelVoltage(), "Other")
					? siteSurvey.getOtherSubpanelVoltage() + "" : siteSurvey.getSubpanelVoltage() + ""),
			"" };
			siteInfo.put("Subpanel Voltage", cache5);
			
			String[] cache6 = { convert(siteSurvey.getSubpanelBusbarRating() + ""),
			"" };
			siteInfo.put("Subpanel Busbar Rating", cache6);
			
			String[] cache7 = { convert(siteSurvey.getSubpanelMainBreakerRating() + ""),
			"" };
			siteInfo.put("Subpanel Main Breaker Rating", cache7);
			
			String[] cache71 = { convert(siteSurvey.getSubPanelBreakerOCPD() + ""),
			"" };
			siteInfo.put("Sub Panel Breaker (OCPD) at Main Service", cache71);
			
			String[] cache8 = { convert(siteSurvey.getOpenBreakerSlotsinSubpanel() + ""),
			"" };
			siteInfo.put("Open Breaker Slots in Subpanel", cache8);
			
			String[] cache9 = { convert(checkValue.Equals(siteSurvey.getSubpanelFeederGauge(), "Other") ? siteSurvey.getOtherSubpanelFeederGauge() + "" : siteSurvey.getSubpanelFeederGauge() + "" ),
			"" };
			siteInfo.put("Subpanel Feeder Gauge", cache9);
			

			List<SiteSurveyTextAreaFieldsEntity> textAreaFields = siteSurveyTextAreaFieldsRepo.findBySiteSurveyEntityIdAndSiteSurveyCostumFieldEntityTabName(siteSurvey.getId(), "tab-r3");
			if(textAreaFields!=null && !textAreaFields.isEmpty()) {

				
				for (SiteSurveyTextAreaFieldsEntity TextAreaField : textAreaFields) {
					String[] textBox = { convert(TextAreaField.getTextBoxContent() ),
					"" };
					String fieldName =TextAreaField.getSiteSurveyCostumFieldEntity().getFieldName();
					siteInfo.put(fieldName, textBox);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return siteInfo;
}

public LinkedHashMap<String, String[]> siteNote(SiteSurveyEntity siteSurvey){
	//05-24-2019 : M.A : Junit correction
	
	LinkedHashMap<String, String[]> siteInfo = new LinkedHashMap<String, String[]>();
	
		try {
			String[] cache0 = { convert(siteSurvey.getSiteNotes() + ""),
			"" };
			siteInfo.put("Site Notes (existing Code issues, clearances, customer preferences, parking, etc.)", cache0);
			
			List<SiteSurveyTextAreaFieldsEntity> textAreaFields = siteSurveyTextAreaFieldsRepo.findBySiteSurveyEntityIdAndSiteSurveyCostumFieldEntityTabName(siteSurvey.getId(), "tab-r4");
			if(textAreaFields!=null && !textAreaFields.isEmpty()) {

				
				for (SiteSurveyTextAreaFieldsEntity TextAreaField : textAreaFields) {
					String[] textBox = { convert(TextAreaField.getTextBoxContent() ),
					"" };
					String fieldName =TextAreaField.getSiteSurveyCostumFieldEntity().getFieldName();
					siteInfo.put(fieldName, textBox);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return siteInfo;
}

public LinkedHashMap<String, String[]> utilityInfo(SiteSurveyEntity siteSurvey){
	//05-24-2019 : M.A : Junit correction
	
	LinkedHashMap<String, String[]> siteInfo = new LinkedHashMap<>();
	
		try {
			
				try {
					if(!checkValue.Equals(siteSurvey.getUtilityCoName(), "Other")) {
						if(checkValue.NotEquals(siteSurvey.getUtilityCoName(), "")) {
							ElectricalUtilityEntity electricalUtility = electricalUtilityRepo.findById(Long.parseLong(siteSurvey.getUtilityCoName())).orElseThrow(
									() -> new ResourceNotFoundException("Entity not found for this id :" +Long.parseLong(siteSurvey.getUtilityCoName())));
							String[] cache0 = { convert(electricalUtility.getUtilityCompanyName() + ""),
							"" };
							siteInfo.put("Utility Co. Name", cache0);
						}else {
							String[] cache0 = { convert(""),
							"" };
							siteInfo.put("Utility Co. Name", cache0);
						}
					}else {
						String[] cache01 = { convert(siteSurvey.getUtilityCompanyNameOther()+ ""),
								"" };
						siteInfo.put("Utility Co. Name", cache01);
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}

				
				
			String[] cache0 = { convert(siteSurvey.getMeterNumber() + ""), "" };
			siteInfo.put("Electric Utility Meter Number", cache0);
			
			String[] cache1 = { convert(checkValue.Equals( siteSurvey.getnEMType(), "Other")
					? siteSurvey.getOtherNEMType() + "" : siteSurvey.getnEMType() + ""),
					"" };
			siteInfo.put("NEM Type", cache1);
			
			String[] cache2 = { convert(checkValue.Equals(siteSurvey.getPostSolarRate(), "Other")
					? siteSurvey.getPostSolarRateOther() + "" : siteSurvey.getPostSolarRate() + ""),
			"" };
			siteInfo.put("Post-Solar Rate", cache2);
		
			String[] cache3 = { convert(siteSurvey.getUsageHistoryOffset() + ""),
			"" };
			siteInfo.put("Usage History Offset", cache3);
		
			String[] cache4 = { convert(checkValue.Equals(siteSurvey.getPaceEntity(), "Other")
					? siteSurvey.getPaceEntityOther() + "" : siteSurvey.getPaceEntity() + ""),
			"" };
			siteInfo.put("PACE Entity", cache4);
			
			String[] cache5 = { convert(siteSurvey.getaCDiscoWillbemorethan10FromUtilityMeter() + ""),
			"" };
			siteInfo.put("AC Disco will be more than 10' from Utility Meter", cache5);
			
			String[] cache6 = { convert(siteSurvey.getAtLeast4MonthsOfelectricbillinghistory() + ""),
			"" };
			siteInfo.put("At least 4 months of electric billing history", cache6);
			
			String[] cache7 = { convert(siteSurvey.getSquareFeetOfLivingArea() + ""),
			"" };
			siteInfo.put("Square Feet of Living Area", cache7);
			
			String[] cache8 = { convert(siteSurvey.getRecentAnnualUsage() + ""),
			"" };
			siteInfo.put("Recent Annual Usage (kWh)", cache8);
			
			String[] cache9 = { convert(siteSurvey.getNumberOfElectricVehicles() + ""),
			"" };
			siteInfo.put("# of Electric Vehicles", cache9);
			
			List<SiteSurveyTextAreaFieldsEntity> textAreaFields = siteSurveyTextAreaFieldsRepo.findBySiteSurveyEntityIdAndSiteSurveyCostumFieldEntityTabName(siteSurvey.getId(), "tab-r5");
			if(textAreaFields!=null && !textAreaFields.isEmpty()) {

				
				for (SiteSurveyTextAreaFieldsEntity TextAreaField : textAreaFields) {
					String[] textBox = { convert(TextAreaField.getTextBoxContent() ),
					"" };
					String fieldName =TextAreaField.getSiteSurveyCostumFieldEntity().getFieldName();
					siteInfo.put(fieldName, textBox);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return siteInfo;
}

public LinkedHashMap<String, String[]> existingPvSystem(SiteSurveyEntity siteSurvey){
	//05-24-2019 : M.A : Junit correction
	
	LinkedHashMap<String, String[]> siteInfo = new LinkedHashMap<>();
	
		try {
			
			String[] cache0 = { convert(siteSurvey.getExistingPVSystemAtSite() + ""),
			"" };
			siteInfo.put("Existing PV System At Site", cache0);
		
			String[] cache1 = { convert(siteSurvey.getpVSystemMake() + ""),
					"" };
			siteInfo.put("PV Make", cache1);
			
			String[] cache2 = { convert(siteSurvey.getpVSystemModel() + ""),
			"" };
			siteInfo.put("PV Model", cache2);
		
			String[] cache3 = { convert(siteSurvey.getpVQuantity() + ""),
			"" };
			siteInfo.put("PV Quantity", cache3);
		
			String[] cache4 = { convert(siteSurvey.getGridTiedInverterMake() + ""),
			"" };
			siteInfo.put("Grid-Tied Inverter Make", cache4);
			
			String[] cache5 = { convert(siteSurvey.getGridTiedInverterModel() + ""),
			"" };
			siteInfo.put("Grid-Tied Inverter Model", cache5);
			
			String[] cache6 = { convert(siteSurvey.getGridTiedInverterQuantity() + ""),
			"" };
			siteInfo.put("Grid-Tied Inverter Quantity", cache6);
			
			String[] cache7 = { convert(siteSurvey.getExistingACDisco() + ""),
			"" };
			siteInfo.put("Existing AC Disconnect", cache7);
			
			String[] cache8 = { convert(siteSurvey.getaCDiscoMake() + ""),
			"" };
			siteInfo.put("AC Disco Make", cache8);
			
			String[] cache9 = { convert(siteSurvey.getaCDiscoModel() + ""),
			"" };
			siteInfo.put("AC Disco Model", cache9);
			
			String[] cache10 = { convert(siteSurvey.getACDiscoRating() + ""),
			"" };
			siteInfo.put("AC Disco Rating", cache10);
			
			String[] cache11 = { convert(checkValue.Equals(siteSurvey.getConnectionType(), "Other") ? siteSurvey.getOtherConnectionType() + ""
					: siteSurvey.getConnectionType() + ""),
			"" };
			siteInfo.put("Connection Type", cache11);
			
			String[] cache111 = { convert(siteSurvey.getNotesExistingPvSystem() + ""),
			"" };
			siteInfo.put("Notes on Existing PV System", cache111);
			
			String[] cache00 = { convert(siteSurvey.getExistingBattery() + ""),
			"" };
			siteInfo.put("Existing Battery", cache00);
			
			String[] cache01 = { convert(siteSurvey.getNotesOnExistingBatterySystem() + ""),
			"" };
			siteInfo.put("Notes on Existing Battery System", cache01);
			
			String[] cache12 = { convert(siteSurvey.getpVBreaker1() + ""),
			"" };
			siteInfo.put("PV Breaker 1",cache12);
			
			String[] cache13 = { convert(siteSurvey.getpVBreaker2() + ""),
			"" };
			siteInfo.put("PV Breaker 2",cache13);
			
			
			String[] cache14 = { convert(siteSurvey.getpVBreaker3() + ""),
			"" };
			siteInfo.put("PV Breaker 3",cache14);
			
			String[] cache15 = { convert(siteSurvey.getpVBreaker4() + ""),
			"" };
			siteInfo.put("PV Breaker 4",cache15);
			
			
			String[] cache16 = { convert(siteSurvey.getpVBreaker5() + ""),
			"" };
			siteInfo.put("PV Breaker 5",cache16);
			List<SiteSurveyTextAreaFieldsEntity> textAreaFields = siteSurveyTextAreaFieldsRepo.findBySiteSurveyEntityIdAndSiteSurveyCostumFieldEntityTabName(siteSurvey.getId(), "tab-r6");
			if(textAreaFields!=null && !textAreaFields.isEmpty()) {

				
				for (SiteSurveyTextAreaFieldsEntity TextAreaField : textAreaFields) {
					String[] textBox = { convert(TextAreaField.getTextBoxContent() ),
					"" };
					String fieldName =TextAreaField.getSiteSurveyCostumFieldEntity().getFieldName();
					siteInfo.put(fieldName, textBox);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return siteInfo;
}

public LinkedHashMap<String, String[]> batteryInfo(SiteSurveyEntity siteSurvey){
	//05-24-2019 : M.A : Junit correction
	//A.B 04-16 CR-3255-007
	LinkedHashMap<String, String[]> siteInfo = new LinkedHashMap<>();
	
		try {
			String[] cache0 = { convert(siteSurvey.getInverterTechnology() + ""),
			"" };
			siteInfo.put("Inverter Technology", cache0);

			String[] cache1 = { convert(siteSurvey.getFirstInverter() != null ? siteSurvey.getFirstInverter().getModel() : ""),
			"" };
			siteInfo.put("Inverter 1 Model ", cache1);
			
			String[] cache2 = { convert(siteSurvey.getSecondInverter() != null ? siteSurvey.getSecondInverter().getModel() : ""),
			"" };
			siteInfo.put("Inverter 2 Model ", cache2);
			
			String[] cache3 = { convert(siteSurvey.getThirdInverter() != null ? siteSurvey.getThirdInverter().getModel() : ""),
			"" };
			siteInfo.put("Inverter 3 Model ", cache3);
			
			String[] cache4 = { convert(siteSurvey.getNotesOnInverter() + ""),
			"" };
			siteInfo.put("Notes on Inverter, AC Combiner and AC disconnect locations ", cache4);
			
			String[] cache5 = { convert(siteSurvey.getProposedBattery() + ""),
			"" };
			siteInfo.put("Proposed Battery", cache5);
			
			String[] cache6 = { convert(siteSurvey.getTypeOfBatterySystem() + ""),
			"" };
			siteInfo.put("Type of battery system", cache6);
			
			String[] cache7 = { convert(siteSurvey.getBatteryLocation() + ""),
			"" };
			siteInfo.put("Battery Location", cache7);
			
			String[] cache8 = { convert(siteSurvey.getCircuitstoRelocatetoCriticalLoadsPanel() + ""),
			"" };
			siteInfo.put("Circuits to Relocate to Critical Loads Panel", cache8);
			
			String[] cache9 = { convert(siteSurvey.getCriticalLoadPanelLocation() + ""),
			"" };
			siteInfo.put("Critical Load Panel Location", cache9);
			
			List<SiteSurveyTextAreaFieldsEntity> textAreaFields = siteSurveyTextAreaFieldsRepo.findBySiteSurveyEntityIdAndSiteSurveyCostumFieldEntityTabName(siteSurvey.getId(), "tab-r7");
			if(textAreaFields!=null && !textAreaFields.isEmpty()) {
				for (SiteSurveyTextAreaFieldsEntity TextAreaField : textAreaFields) {
					String[] textBox = { convert(TextAreaField.getTextBoxContent() ),
					"" };
					String fieldName =TextAreaField.getSiteSurveyCostumFieldEntity().getFieldName();
					siteInfo.put(fieldName, textBox);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return siteInfo;
}

public LinkedHashMap<String, String[]> internetConnection(SiteSurveyEntity siteSurvey){
	//05-24-2019 : M.A : Junit correction
	
	LinkedHashMap<String, String[]> siteInfo = new LinkedHashMap<>();
	
		try {
			String[] cache0 = { convert(siteSurvey.getProductionMonitor() + ""),
			"" };
			siteInfo.put("Production Monitor", cache0);
		
			String[] cache1 = { convert(siteSurvey.getActiveInternetConnection() + ""),
					"" };
			siteInfo.put("Is there an active internet connection?", cache1);
			
			String[] cache2 = { convert(siteSurvey.getModemLocation() + ""),
			"" };
			siteInfo.put("Modem location", cache2);
			
			String[] cache5 = { convert(siteSurvey.getNetworkName() + ""),
			"" };
			siteInfo.put("Network Name", cache5);
			
			String[] cache6 = { convert(siteSurvey.getNetworkPassword() + ""),
			"" };
			siteInfo.put("Network Password", cache6);
		
			String[] cache3 = { convert(siteSurvey.getConnectTheMonitor() + ""),
			"" };
			siteInfo.put("Work req'd to connect the monitor", cache3);
		

			List<SiteSurveyTextAreaFieldsEntity> textAreaFields = siteSurveyTextAreaFieldsRepo.findBySiteSurveyEntityIdAndSiteSurveyCostumFieldEntityTabName(siteSurvey.getId(), "tab-r8");
			if(textAreaFields!=null && !textAreaFields.isEmpty()) {

				
				for (SiteSurveyTextAreaFieldsEntity TextAreaField : textAreaFields) {
					String[] textBox = { convert(TextAreaField.getTextBoxContent() ),
					"" };
					String fieldName =TextAreaField.getSiteSurveyCostumFieldEntity().getFieldName();
					siteInfo.put(fieldName, textBox);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return siteInfo;
}

public LinkedHashMap<String, String[]> roofMount(SiteSurveyEntity siteSurvey){
	//05-24-2019 : M.A : Junit correction
	
	LinkedHashMap<String, String[]> siteInfo = new LinkedHashMap<>();
	
		try {
			
		
			String[] cache1 = { convert(siteSurvey.getWireRunOnRoof() + ""),
					"" };
			siteInfo.put("Wire Run On Roof", cache1);
			
			String[] cache2 = { convert(siteSurvey.getWireRunInAttic() + ""),
			"" };
			siteInfo.put("Wire Run in Attic", cache2);
		
			String[] cache3 = { convert(siteSurvey.getTiltupModules() + ""),
			"" };
			siteInfo.put("Tiltup Modules", cache3);
			
			if(siteSurvey.getRoofMaterialType() != null && checkValue.isStringInt(siteSurvey.getRoofMaterialType())) {
				String result = roofMaterialTypeRepo.getTypeRoofById(Long.valueOf(siteSurvey.getRoofMaterialType()));
				String[] cache41 = { convert(result + ""),
				"" };
				siteInfo.put("Roof Material Type", cache41);
			}else {
				String[] cache41 = { convert(siteSurvey.getRoofMaterialType() + ""),
				"" };
				siteInfo.put("Roof Material Type", cache41);
			}
			
			String[] cache5 = { convert(siteSurvey.getNumberOfLayers() + ""),
			"" };
			siteInfo.put("Number of Layers", cache5);
			
			String[] cache6 = { convert(siteSurvey.getRoofAge() + ""),
			"" };
			siteInfo.put("Roof Age (Years)", cache6);
			
			String[] cache61 = { convert(siteSurvey.getRoofCondition() + ""),
			"" };
			siteInfo.put("Roof Condition", cache61);
			
			String[] cache41 = { convert(siteSurvey.getVerifyRoofMeasurementsPreRoofLayoutProvided() + ""),
			"" };
			siteInfo.put("Verify Roof Measurements if a Pre-Roof Layout was Provided", cache41);
			
			String[] cache8 = { convert(siteSurvey.getpVModel() + ""),
			"" };
			siteInfo.put("PV Model", cache8);
			
			String[] cache12 = { convert(siteSurvey.getGableRakeOverhang() + ""),
			"" };
			siteInfo.put("Gable/Rake Overhang", cache12);
			
			String[] cache13 = { convert(siteSurvey.getHeightAtGutter() + ""),
			"" };
			siteInfo.put("Height at Gutter", cache13);
			
			String[] cache16 = { convert(checkValue.Equals(siteSurvey.getCrossSectionSize(), "OtherSize") ? siteSurvey.getCrossSectionSizeOther() + "" : siteSurvey.getCrossSectionSize() + "" ),
			"" };
			siteInfo.put("Roof Structural Member Cross Section Size", cache16);

			String[] cache133 = { convert(siteSurvey.getRoofStructureChart() + ""),
			"" };
			siteInfo.put("Roof Structure Chart", cache133);
			
			String[] cache134 = { convert(siteSurvey.getRoofRafterOther() + ""),
			"" };
			siteInfo.put("Other Roof Structure Chart", cache134);
			
			String[] cache135 = { convert(siteSurvey.getSecroofRafterOther() + ""),
			"" };
			siteInfo.put("Other Roof Structure Chart", cache135);
			
			List<SiteSurveyArrayChartEntity> permitSketchEntity = siteSurveyArrayChartRepo.findBySiteSurveyEntityId(siteSurvey.getId());
			if (permitSketchEntity != null && !permitSketchEntity.isEmpty()) {
				
				int i = 1;
				
				for (SiteSurveyArrayChartEntity sketch : permitSketchEntity) {
					String[] azimuth = { convert(sketch.getAzimuth()+""), "dataTable[x.modelAzim][x.num]"};
					String[] roofPitch = { convert(sketch.getRoofPitch()+""), "dataTable[x.modelRoof][x.num]"};
					String[] tiltKit = { convert(sketch.getModuleTils()+""), "dataTable[x.modelModule][x.num]" };
					String[] moduleDgree = {convert( sketch.getModelvalue()+""), "dataTable[x.modelvalue][x.num]"};
					String[] eaveOver = {convert( sketch.getEaveOverHang()+""), "dataTable[x.modelEave][x.num]"};
					String[] eaveOverOther = {convert( sketch.getEaveOverHangOther()+""), "dataTable[x.modelEaveOther][x.num]"};
					String[] moduleQty = {convert( sketch.getModuleQty()+""), "dataTable[x.moduleQty][x.num]"};
					
					siteInfo.put("Azimuth "+i, azimuth);
					siteInfo.put("Roof Pitch "+i, roofPitch);
					siteInfo.put("Tilt Kit Used "+i, tiltKit); 
					siteInfo.put("Module Degree Tilt "+i, moduleDgree); 
					siteInfo.put("Eave Overhang "+i, eaveOver);
					siteInfo.put("Eave Overhang Other "+i, eaveOverOther);
					siteInfo.put("Module Qty "+i, moduleQty);
					
					i++;
				}
				
			}
			
			String[] cache171 = { convert(siteSurvey.getRoofNotes() + ""),
			"" };
			siteInfo.put("Roof Notes", cache171);
			
			String[] cache17 = { convert(siteSurvey.getStanchionMaxSpacing() + ""),
			"" };
			siteInfo.put("Stanchion Max Spacing", cache17);
			
			String[] cache18 = { convert(siteSurvey.getRidgeBeamDepthAtArrays() + ""),
			"" };
			siteInfo.put("Ridge Beam Depth at Arrays", cache18);
			String maxSpanAtArraysHS1 ="";
			String maxSpanAtArraysHS2 ="";
			if (checkValue.NotEquals(siteSurvey.getMaxSpanAtArraysHS1(),"") && checkValue.NotEquals(siteSurvey.getMaxSpanAtArraysInchesHS1(),"")){
				maxSpanAtArraysHS1 = siteSurvey.getMaxSpanAtArraysHS1()+ "'" + "-"+siteSurvey.getMaxSpanAtArraysInchesHS1()+"\"";
			}
			if (checkValue.NotEquals(siteSurvey.getMaxSpanAtArraysHS2(),"") && checkValue.NotEquals(siteSurvey.getMaxSpanAtArraysInchesHS2(),"")){
				maxSpanAtArraysHS2 = siteSurvey.getMaxSpanAtArraysHS2()+ "'" + "-"+siteSurvey.getMaxSpanAtArraysInchesHS2()+"\"";
			}
			
			String[] cache19 = { convert(maxSpanAtArraysHS1),
			"" };
			siteInfo.put("Max Span At Arrays (HS1)", cache19);
			
			String[] cache20 = { convert(maxSpanAtArraysHS2),
			"" };
			siteInfo.put("Max Span At Arrays (HS2)", cache20);
			
			String[] cache21 = { convert(checkValue.Equals(siteSurvey.getRafterTrussSpacing() , "Other") ? siteSurvey.getRafterTrussSpOther() + "" : siteSurvey.getRafterTrussSpacing() + "\""),
			"" };
			siteInfo.put("Rafter or Truss Spacing", cache21);

			List<SiteSurveyTextAreaFieldsEntity> textAreaFields = siteSurveyTextAreaFieldsRepo.findBySiteSurveyEntityIdAndSiteSurveyCostumFieldEntityTabName(siteSurvey.getId(),"tab-r9");
			if(textAreaFields!=null && !textAreaFields.isEmpty()) {

				
				for (SiteSurveyTextAreaFieldsEntity TextAreaField : textAreaFields) {
					String[] textBox = { convert(TextAreaField.getTextBoxContent() ),
					"" };
					String fieldName =TextAreaField.getSiteSurveyCostumFieldEntity().getFieldName();
					siteInfo.put(fieldName, textBox);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return siteInfo;
}

public LinkedHashMap<String, String[]>  nonRoofMount(SiteSurveyEntity siteSurvey){
	
	LinkedHashMap<String, String[]> siteInfo = new LinkedHashMap<>();
	
	try {
		
		String[] cache4 = { convert(siteSurvey.getNonRoofContourSlope() + ""),
		"" };
		siteInfo.put("Contour and Slope", cache4);
		
		String[] cache5 = { convert(siteSurvey.getNonRoofPathPoint() + ""),
		"" };
		siteInfo.put("Path to Point of Interconnection", cache5);
		
		String[] cache51 = { convert(siteSurvey.getMeasurmentsOfArea()+ ""),
		"" };
		siteInfo.put("Measurements of Area Available for Array", cache51);
		
		String[] cache6 = { convert(siteSurvey.getNonRoofGradingGrubbing() + ""),
		"" };
		siteInfo.put("Grading or Grubbing Required?", cache6);
		
		String[] cache7 = { convert(siteSurvey.getNonRoofSiteComposition() + ""),
		"" };
		siteInfo.put("Site Composition", cache7);
		
		String[] cache8 = { convert(siteSurvey.getNonRoofElevationStructure() + ""),
		"" };
		siteInfo.put("Elevation of Structure Required", cache8);
		
		String[] cache9 = { convert(siteSurvey.getNonRoofExistingSecurity() + ""),
		"" };
		siteInfo.put("Existing Security", cache9);
		
		String[] cache91 = { convert(siteSurvey.getNotesGroundMount() + ""),
		"" };
		siteInfo.put("Notes on Ground Mount", cache91);
		
		String[] cache92 = { convert(siteSurvey.getpVModelNonRoof() + ""),
		"" };
		siteInfo.put("PV Model", cache92);
		
		List<SiteSurveyArrayChartNonRoofEntity> permitSketchEntity = siteSurveyArrayChartNonRoofRepo.findBySiteSurveyEntityId(siteSurvey.getId());
		
		if (permitSketchEntity != null && !permitSketchEntity.isEmpty()) {
			int i = 1;
			
			for (SiteSurveyArrayChartNonRoofEntity sketch : permitSketchEntity) {
				String[] azimuth = { convert(sketch.getAzimuth()+""), "dataTable[x.modelAzim][x.num]"};
				String[] roofPitch = { convert(sketch.getRoofPitch()+""), "dataTable[x.modelRoof][x.num]"};
				String[] tiltKit = { convert(sketch.getModuleTils()+""), "dataTable[x.modelModule][x.num]" };
				String[] moduleDgree = {convert( sketch.getModelvalue()+""), "dataTable[x.modelvalue][x.num]"};
				String[] eaveOver = {convert( sketch.getEaveOverHang()+""), "dataTable[x.modelEave][x.num]"};
				String[] eaveOverOther = {convert( sketch.getEaveOverHangOther()+""), "dataTable[x.modelEaveOther][x.num]"};
				String[] moduleQty = {convert( sketch.getModuleQty()+""), "dataTable[x.moduleQty][x.num]"};
				
				siteInfo.put("Azimuth "+i, azimuth);
				siteInfo.put("Roof Pitch "+i, roofPitch);
				siteInfo.put("Tilt Kit Used "+i, tiltKit); 
				siteInfo.put("Module Degree Tilt "+i, moduleDgree); 
				siteInfo.put("Eave Overhang "+i, eaveOver);
				siteInfo.put("Eave Overhang Other "+i, eaveOverOther);
				siteInfo.put("Module Qty "+i, moduleQty);
				
				i++;
			}
			
			List<SiteSurveyTextAreaFieldsEntity> textAreaFields = siteSurveyTextAreaFieldsRepo.findBySiteSurveyEntityIdAndSiteSurveyCostumFieldEntityTabName(siteSurvey.getId(), "tab-r10");
			if(textAreaFields!=null && !textAreaFields.isEmpty()) {

				
				for (SiteSurveyTextAreaFieldsEntity textAreaField : textAreaFields) {
					String[] textBox = { convert(textAreaField.getTextBoxContent() ),
					"" };
					String fieldName =textAreaField.getSiteSurveyCostumFieldEntity().getFieldName();
					siteInfo.put(fieldName, textBox);
				}
			}
		}
		
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return siteInfo;
}

public LinkedHashMap<String, String[]> patioCoverAttached(SiteSurveyEntity siteSurvey){
	
LinkedHashMap<String, String[]> siteInfo = new LinkedHashMap<>();
	
	try {
		
		String[] cache4 = { convert(siteSurvey.getNonRoofCarport() + ""),
		"" };
		siteInfo.put("Carport", cache4);
		
		String[] cache5 = { convert(siteSurvey.getNonRoofPatioCover() + ""),
		"" };
		siteInfo.put("Patio Cover", cache5);
		
		String[] cache0 = { convert(checkValue.Equals(siteSurvey.getPatioCoverAttachedTypeBeam(), "Other")
				? siteSurvey.getOtherPatioCoverFreestandingTypeBeam() + "" : siteSurvey.getPatioCoverAttachedTypeBeam() + ""),
		"" };
		siteInfo.put("Type of Beam", cache0);
		
		String[] cache1 = { convert(checkValue.Equals(siteSurvey.getPatioCoverAttachedTypePosts(), "Other")
				? siteSurvey.getOtherPatioCoverFreestandingTypePosts() + "" : siteSurvey.getPatioCoverAttachedTypePosts() + ""),
				"" };
		siteInfo.put("Type of Posts", cache1);

		List<SiteSurveyTextAreaFieldsEntity> textAreaFields = siteSurveyTextAreaFieldsRepo.findBySiteSurveyEntityIdAndSiteSurveyCostumFieldEntityTabName(siteSurvey.getId(), "tab-r11");
		if(textAreaFields!=null && !textAreaFields.isEmpty()) {

			
			for (SiteSurveyTextAreaFieldsEntity textAreaField : textAreaFields) {
				String[] textBox = { convert(textAreaField.getTextBoxContent() ),
				"" };
				String fieldName =textAreaField.getSiteSurveyCostumFieldEntity().getFieldName();
				siteInfo.put(fieldName, textBox);
			}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return siteInfo;
	
}

public LinkedHashMap<String, String[]> patioCoverFrrestanding(SiteSurveyEntity siteSurvey){
LinkedHashMap<String, String[]> siteInfo = new LinkedHashMap<>();
	
	try {
		
		String[] cache7 = { convert(siteSurvey.getNonRoofCarport() + ""),
		"" };
		siteInfo.put("Carport", cache7);
		
		String[] cache6 = { convert(siteSurvey.getNonRoofPatioCover() + ""),
		"" };
		siteInfo.put("Patio Cover", cache6);
		
		String[] cache0 = { convert(checkValue.Equals(siteSurvey.getPatioCoverFreestandingTypeBeam(), "Other")
				? siteSurvey.getOtherPatioCoverFreestandingTypeBeam() + "" : siteSurvey.getPatioCoverFreestandingTypeBeam() + ""),
		"" };
		siteInfo.put("Type of Beam", cache0);
		
		String[] cache1 = { convert(checkValue.Equals(siteSurvey.getPatioCoverFreestandingTypePosts(), "Other")
				? siteSurvey.getOtherPatioCoverFreestandingTypePosts() + "" : siteSurvey.getPatioCoverFreestandingTypePosts() + ""),
				"" };
		
		siteInfo.put("Type of Posts", cache1);
		String attachedorFreestanding = "";
		if(checkValue.Equals(siteSurvey.getFrontAndBack(), true)) {
			if(attachedorFreestanding != "") {
				attachedorFreestanding = attachedorFreestanding + " & Front and Back Posts";
			}else {
				attachedorFreestanding = attachedorFreestanding + "Front and Back Posts";
			}
		}
		if(checkValue.Equals(siteSurvey.getCantelever(), true)) {
			if(attachedorFreestanding != "") {
				attachedorFreestanding = attachedorFreestanding + " & Cantilever & T-Type";
			}else {
				attachedorFreestanding = attachedorFreestanding + "Cantilever & T-Type";
			}
		}
		if(checkValue.Equals(siteSurvey.getAttachedToExtWal(), true)) {
			if(attachedorFreestanding != "") {
				attachedorFreestanding = attachedorFreestanding + " & Attached to Ext Wal";
			}else {
				attachedorFreestanding = attachedorFreestanding+ "Attached to Ext Wal";
			}
		}
			
		if(checkValue.Equals(siteSurvey.getAttachedToFascia(), true)) {
			if(attachedorFreestanding != "") {
				attachedorFreestanding = attachedorFreestanding + " & Attached to Fascia";
			}else {
				attachedorFreestanding = attachedorFreestanding+ "Attached to Fascia";
			}
		}
		if(checkValue.Equals(siteSurvey.getAttachedToSkylifts(), true)) {
			if(attachedorFreestanding != "") {
				attachedorFreestanding = attachedorFreestanding + " & Attached to Skylifts on Ext Walls";
			}else {
				attachedorFreestanding = attachedorFreestanding + "Attached to Skylifts on Ext Walls";
			}
		}
		if(checkValue.Equals(siteSurvey.getFreeStanding(), true)) {
			if(attachedorFreestanding != "") {
				attachedorFreestanding = attachedorFreestanding + " & Free Standing";
			}else {
				attachedorFreestanding = attachedorFreestanding + "Free Standing";
			}
		}
			
		String[] cache31 = { convert(attachedorFreestanding),
		"" };
		siteInfo.put("Select Structural Type (If Mixed Check all that Apply)", cache31);
		
		String[] cache4 = { convert(siteSurvey.getRoofOrOpenFrame() + ""),
		"" };
		siteInfo.put("Roof or Open Frame", cache4);
		
		
		String[] cache2 = { convert(siteSurvey.getPatioCoverFreestandingExtendOver() + ""),
		"" };
		siteInfo.put("Will Carport or Patio Cover Extend Over an Existing Roof?", cache2);
		
		String[] cache8 = { convert(siteSurvey.getMeasOfAreaCarpot() + ""),
		"" };
		siteInfo.put("Measurements of Area Available for Array", cache8);
		
		String[] cache13 = { convert(siteSurvey.getNonRoofPatioCoverValue() + ""),
		"" };
		siteInfo.put("Patio Cover ", cache13);
		
		String[] cache9 = { convert(siteSurvey.getpVModelCarpotPatio() + ""),
		"" };
		siteInfo.put("PV Model", cache9);
		
		List<SiteSurveyArrayChartPatioEntity> permitSketchEntity = siteSurveyArrayChartPatioRepo.findBySiteSurveyEntityId(siteSurvey.getId());
		
		if (permitSketchEntity != null && !permitSketchEntity.isEmpty()) {
			

			
			int i = 1;
			
			for (SiteSurveyArrayChartPatioEntity sketch : permitSketchEntity) {
				String[] azimuth = { convert(sketch.getAzimuth()+""), "dataTable[x.modelAzim][x.num]"};
				String[] roofPitch = { convert(sketch.getRoofPitch()+""), "dataTable[x.modelRoof][x.num]"};
				String[] tiltKit = { convert(sketch.getModuleTils()+""), "dataTable[x.modelModule][x.num]" };
				String[] moduleDgree = {convert( sketch.getModelvalue()+""), "dataTable[x.modelvalue][x.num]"};
				String[] eaveOver = {convert( sketch.getEaveOverHang()+""), "dataTable[x.modelEave][x.num]"};
				String[] eaveOverOther = {convert( sketch.getEaveOverHangOther()+""), "dataTable[x.modelEaveOther][x.num]"};
				String[] moduleQty = {convert( sketch.getModuleQty()+""), "dataTable[x.moduleQty][x.num]"};
				
				siteInfo.put("Azimuth "+i, azimuth);
				siteInfo.put("Roof Pitch "+i, roofPitch);
				siteInfo.put("Tilt Kit Used "+i, tiltKit); 
				siteInfo.put("Module Degree Tilt "+i, moduleDgree); 
				siteInfo.put("Eave Overhang "+i, eaveOver);
				siteInfo.put("Eave Overhang Other "+i, eaveOverOther);
				siteInfo.put("Module Qty "+i, moduleQty);
				
				i++;
			}
			
		}
		
		String[] cache10 = { convert(siteSurvey.getNotesOnCarpotOrPatiot() + ""),
		"" };
		siteInfo.put("Notes on Carport or Patio cover", cache10);

		String[] cache3 = { convert(siteSurvey.getPatioCoverFreestandingPastEave() + ""),
		"" };
		siteInfo.put("How far Past Eave or Gutter Face?", cache3);
		List<SiteSurveyTextAreaFieldsEntity> textAreaFields = siteSurveyTextAreaFieldsRepo.findBySiteSurveyEntityIdAndSiteSurveyCostumFieldEntityTabName(siteSurvey.getId(), "tab-r12");
		if(textAreaFields!=null && !textAreaFields.isEmpty()) {

			
			for (SiteSurveyTextAreaFieldsEntity textAreaField : textAreaFields) {
				String[] textBox = { convert(textAreaField.getTextBoxContent() ),
				"" };
				String fieldName =textAreaField.getSiteSurveyCostumFieldEntity().getFieldName();
				siteInfo.put(fieldName, textBox);
			}
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return siteInfo;	
}

public String generateSiteSurvey(Long idSiteSurvey) {

	
	
	try {

		SiteSurveyEntity siteSurvey = siteSurveyRepo.findById(idSiteSurvey).orElseThrow(
				() -> new ResourceNotFoundException("Entity not found for this id :" +idSiteSurvey));
		
		FileOutputStream fileOut;
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		HSSFSheet homeOwnerSheet = workbook.createSheet("SITE INFORMATION");
		HSSFSheet arraysSheet = workbook.createSheet("EXISTING MAIN PANEL (MSP)");
		HSSFSheet bosSheet = workbook.createSheet("SUBPANEL (IF APPLICABLE)");
		HSSFSheet conduitContractorSheet = workbook.createSheet("SITE NOTE");
		HSSFSheet addInfoSheet = workbook.createSheet("UTILITY INFO.");
		HSSFSheet layoutsheet = workbook.createSheet("EXISTING PV SYSTEM AT SITE");
		HSSFSheet utilitySheet = workbook.createSheet("Inverter Battery Info");
		HSSFSheet advSheet = workbook.createSheet("INTERNET CONNECTION FOR PRODUCTION MONITORING");
		HSSFSheet drafterSheetRoof = null;
		HSSFSheet drafterSheetNonRoof = null;
		HSSFSheet drafterSheetPatiot = null;
		
		if(checkValue.Equals(siteSurvey.getRoofMounted(), true)
				|| (!checkValue.NotEquals(siteSurvey.getRoofMounted(), false)
						&& !checkValue.NotEquals(siteSurvey.getGroundMounted(), false)
						&& !checkValue.NotEquals(siteSurvey.getCarportMounted(), false)
						&& !checkValue.NotEquals(siteSurvey.getPatioMounted(), false)
						&& !checkValue.NotEquals(siteSurvey.getOtherMounted(), false))) {
			drafterSheetRoof = workbook.createSheet("ROOF MOUNT");
		}
		if(checkValue.Equals(siteSurvey.getCarportMounted(), true) || checkValue.Equals(siteSurvey.getPatioMounted(), true)) {
			drafterSheetPatiot = workbook.createSheet("CARPOT PATIO COVER");
		}
		if(checkValue.Equals(siteSurvey.getGroundMounted(), true)
			) {
			drafterSheetNonRoof = workbook.createSheet("NON ROOF MOUNT");
		}
		
		
		 HSSFCellStyle cellStyleOrange = workbook.createCellStyle();
         HSSFCellStyle cellStyleBlue = workbook.createCellStyle();
         HSSFCellStyle cellStyleRose = workbook.createCellStyle();
         HSSFCellStyle cellStyleRed = workbook.createCellStyle();
         HSSFCellStyle cellStyleGreen = workbook.createCellStyle();
         HSSFCellStyle cellStyleYellow = workbook.createCellStyle();
         HSSFCellStyle cellStyleSkyBlue = workbook.createCellStyle();
         HSSFCellStyle cellStyleOlive = workbook.createCellStyle();
         HSSFCellStyle cellStyleViolet = workbook.createCellStyle();
         
         cellStyleOrange.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
         cellStyleOrange.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         
         cellStyleBlue.setFillForegroundColor(HSSFColor.GOLD.index);
         cellStyleBlue.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         
         cellStyleRose.setFillForegroundColor(HSSFColor.LIME.index);
         cellStyleRose.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         
         cellStyleRed.setFillForegroundColor(HSSFColor.AQUA.index);
         cellStyleRed.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         
         cellStyleGreen.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
         cellStyleGreen.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         
         cellStyleYellow.setFillForegroundColor(HSSFColor.TAN.index);
         cellStyleYellow.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         
         cellStyleSkyBlue.setFillForegroundColor(HSSFColor.LAVENDER.index);
         cellStyleSkyBlue.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         
         cellStyleOlive.setFillForegroundColor(HSSFColor.ROSE.index);
         cellStyleOlive.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
         
         cellStyleViolet.setFillForegroundColor(HSSFColor.PLUM.index);
         cellStyleViolet.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		/*
		 * HOMEOWNER/SITE INFO 156 ---->  1220 ( Done )
		 */
		 Row homeOwnerRow = homeOwnerSheet.createRow(0);
		 homeOwnerRow.createCell(0).setCellStyle(cellStyleOrange);
		 homeOwnerRow.getCell(0).setCellValue("SITE INFORMATION");
         
		 homeOwnerRow = homeOwnerSheet.createRow(1);
         
		 homeOwnerRow.createCell(0).setCellStyle(cellStyleOrange);
		 homeOwnerRow.createCell(1).setCellStyle(cellStyleOrange);
         
		 homeOwnerRow.getCell(0).setCellValue("Field Name");
		 homeOwnerRow.getCell(1).setCellValue("Field Value");
         
         LinkedHashMap <String, String[]> siteInfo = siteInformationMap(siteSurvey);
         
         Iterator<String> keySetIteratorHomeOwner = siteInfo.keySet().iterator();
         
         int i = 2;
         while(keySetIteratorHomeOwner.hasNext()){ 
        	 homeOwnerRow = homeOwnerSheet.createRow(i);
        	 String key = keySetIteratorHomeOwner.next();
        	 
        	 homeOwnerRow.createCell(0).setCellValue(key);
        	 homeOwnerRow.createCell(1).setCellValue(siteInfo.get(key)[0]);
	         i++;
        }
         
         /*
          * ARRAY(S)  1221 ------> 2073 ( Done )
          */
         Row arraysRow = arraysSheet.createRow(0);
         arraysRow.createCell(0).setCellStyle(cellStyleBlue);
         arraysRow.getCell(0).setCellValue("EXISTING MAIN PANEL (MSP)");
         
         arraysRow = arraysSheet.createRow(1);
         
         arraysRow.createCell(0).setCellStyle(cellStyleBlue);
         arraysRow.createCell(1).setCellStyle(cellStyleBlue);
         
         arraysRow.getCell(0).setCellValue("Field Name");
         arraysRow.getCell(1).setCellValue("Field Value");
         
         LinkedHashMap <String, String[]> arrays = existMainPanelMap(siteSurvey);
         
         Iterator<String> keySetIteratorArrays = arrays.keySet().iterator();
         
         int i1 = 2;
         while(keySetIteratorArrays.hasNext()){ 
        	 arraysRow = arraysSheet.createRow(i1);
        	 String key = keySetIteratorArrays.next();
        	 arraysRow.createCell(0).setCellValue(key);
        	 arraysRow.createCell(1).setCellValue(arrays.get(key)[0]);
	         i1++;
        }
         
         /*
          * BALANCE OF SYSTEMS (BOS) 2077 -----> 6244 ( Done )
          */
         Row bosRow = bosSheet.createRow(0);
         bosRow.createCell(0).setCellStyle(cellStyleRose);
         bosRow.getCell(0).setCellValue("SUBPANEL (IF APPLICABLE)");
         
         bosRow = bosSheet.createRow(1);
         
         bosRow.createCell(0).setCellStyle(cellStyleRose);
         bosRow.createCell(1).setCellStyle(cellStyleRose);
         
         bosRow.getCell(0).setCellValue("Field Name");
         bosRow.getCell(1).setCellValue("Field Value");
         
         LinkedHashMap <String, String[]> bos = subPanelIfApl(siteSurvey);
         
         Iterator<String> keySetIteratorBOS = bos.keySet().iterator();
         
         int i2 = 2;
         
         while(keySetIteratorBOS.hasNext()){ 
        	 bosRow = bosSheet.createRow(i2);
        	 String key = keySetIteratorBOS.next();
        	 bosRow.createCell(0).setCellValue(key);
        	 bosRow.createCell(1).setCellValue(bos.get(key)[0]);
	         i2++;
        }
         
         /*
          * CONDUIT & CONDUCTOR SEGMENTS 6248 ------> 7581  ( Done )
          */ 
         
         Row conduitContractorRow = conduitContractorSheet.createRow(0);
         
         conduitContractorRow.createCell(0).setCellStyle(cellStyleRed);
         conduitContractorRow.getCell(0).setCellValue("SITE NOTE");
         
         conduitContractorRow = conduitContractorSheet.createRow(1);
         
         conduitContractorRow.createCell(0).setCellStyle(cellStyleRed);
         conduitContractorRow.createCell(1).setCellStyle(cellStyleRed);
         
         conduitContractorRow.getCell(0).setCellValue("Field Name");
         conduitContractorRow.getCell(1).setCellValue("Field Value");
         
         LinkedHashMap <String, String[]> conduitContractor = siteNote(siteSurvey);
         
         Iterator<String> keySetIteratorConduitContr = conduitContractor.keySet().iterator();
         
         int i3 = 2;
         while(keySetIteratorConduitContr.hasNext()){ 
        	 conduitContractorRow = conduitContractorSheet.createRow(i3);
        	 String key = keySetIteratorConduitContr.next();
        	 conduitContractorRow.createCell(0).setCellValue(key);
        	 conduitContractorRow.createCell(1).setCellValue(conduitContractor.get(key)[0]);
        	 i3++;
        }
         
         /*
          * Addional Info Tab 7585 -----> 8561  ( Done )     
          */
         
         Row additionalRow = addInfoSheet.createRow(0);
         
         additionalRow.createCell(0).setCellStyle(cellStyleGreen);
         additionalRow.getCell(0).setCellValue("UTILITY INFO.");
         
         additionalRow = addInfoSheet.createRow(1);
         
         additionalRow.createCell(0).setCellStyle(cellStyleGreen);
         additionalRow.createCell(1).setCellStyle(cellStyleGreen);
         
         additionalRow.getCell(0).setCellValue("Field Name");
         additionalRow.getCell(1).setCellValue("Field Value");
         
         LinkedHashMap <String, String[]> addtionalInfo = utilityInfo(siteSurvey);
         
         Iterator<String> keySetIteratoradditionalInfo = addtionalInfo.keySet().iterator();
         int i4 = 2;
         while(keySetIteratoradditionalInfo.hasNext()){ 
        	 additionalRow = addInfoSheet.createRow(i4);
        	 String key = keySetIteratoradditionalInfo.next();
        	 additionalRow.createCell(0).setCellValue(key);
        	 additionalRow.createCell(1).setCellValue(addtionalInfo.get(key)[0]);
        	 i4++;
        }
         
         /*
          * Layout Sketch Tab  8565 ----> 9130  ( Done )
          */
         Row sketchLayoutRow = layoutsheet.createRow(0);
         
         sketchLayoutRow.createCell(0).setCellStyle(cellStyleYellow);
         sketchLayoutRow.getCell(0).setCellValue("EXISTING PV SYSTEM AT SITE");
         
         sketchLayoutRow = layoutsheet.createRow(1);
         
         sketchLayoutRow.createCell(0).setCellStyle(cellStyleYellow);
         sketchLayoutRow.createCell(1).setCellStyle(cellStyleYellow);
         
         sketchLayoutRow.getCell(0).setCellValue("Field Name");
         sketchLayoutRow.getCell(1).setCellValue("Field Value");
         
         LinkedHashMap <String, String[]> sktechLayout = existingPvSystem(siteSurvey);
         
         Iterator<String> keySetIteratorSketchLayout= sktechLayout.keySet().iterator();
         
         int i5 = 2;
         while(keySetIteratorSketchLayout.hasNext()){ 
        	 sketchLayoutRow = layoutsheet.createRow(i5);
        	 String key = keySetIteratorSketchLayout.next();
        	 sketchLayoutRow.createCell(0).setCellValue(key);
        	 sketchLayoutRow.createCell(1).setCellValue(sktechLayout.get(key)[0]);
        	 i5++;
        }
         
         /*
          * Utility Company Info 9134 -----> 10240 ( Done )
          */
         Row utilityRow = utilitySheet.createRow(0);
         
         utilityRow.createCell(0).setCellStyle(cellStyleSkyBlue);
         utilityRow.getCell(0).setCellValue("Inverter Battery Info");
         
         utilityRow = utilitySheet.createRow(1);
         
         utilityRow.createCell(0).setCellStyle(cellStyleSkyBlue);
         utilityRow.createCell(1).setCellStyle(cellStyleSkyBlue);
         
         utilityRow.getCell(0).setCellValue("Field Name");
         utilityRow.getCell(1).setCellValue("Field Value");
         
         
         LinkedHashMap <String, String[]> utilityCompany = batteryInfo(siteSurvey);
         
                
         Iterator<String> keySetIteratorUtility= utilityCompany.keySet().iterator();
         
         int i6 = 2;
         while(keySetIteratorUtility.hasNext()){ 
        	 utilityRow = utilitySheet.createRow(i6);
        	 String key = keySetIteratorUtility.next();
        	 utilityRow.createCell(0).setCellValue(key);
        	 utilityRow.createCell(1).setCellValue(utilityCompany.get(key)[0]);
        	 i6++;
        }
         
         
         /*
          * ADV Permits Inputs Tab 10244 -----> 10699 ( Done )
          */
         
         Row advRow = advSheet.createRow(0);
         
         advRow.createCell(0).setCellStyle(cellStyleOlive);
         advRow.getCell(0).setCellValue("INTERNET CONNECTION FOR PRODUCTION MONITORING");
         
         advRow = advSheet.createRow(1);
         
         advRow.createCell(0).setCellStyle(cellStyleOlive);
         advRow.createCell(1).setCellStyle(cellStyleOlive);
         
         advRow.getCell(0).setCellValue("Field Name");
         advRow.getCell(1).setCellValue("Field Value");
         
         
         LinkedHashMap <String, String[]> adv = internetConnection(siteSurvey);
         
         Iterator<String> keySetIteratorAdv = adv.keySet().iterator();
         
         int i7 = 2;
         while(keySetIteratorAdv.hasNext()){ 
        	 advRow = advSheet.createRow(i7);
        	 String key = keySetIteratorAdv.next();
        	 advRow.createCell(0).setCellValue(key);
        	 advRow.createCell(1).setCellValue(adv.get(key)[0]);
        	 i7++;
        }
         
         /*
          * Drafter Data Tab 10702 ----> 11017 (Done)
          */
         
         
         Row drafterRowRoof= null;
         Row drafterRowPatiot= null;
         Row drafterRowNonRoof= null;
         if(checkValue.Equals(siteSurvey.getRoofMounted(), true)
 				|| (!checkValue.NotEquals(siteSurvey.getRoofMounted(), false)
 						&& !checkValue.NotEquals(siteSurvey.getGroundMounted(), false)
 						&& !checkValue.NotEquals(siteSurvey.getCarportMounted(), false)
 						&& !checkValue.NotEquals(siteSurvey.getPatioMounted(), false)
 						&& !checkValue.NotEquals(siteSurvey.getOtherMounted(), false))) {
        	 drafterRowRoof = drafterSheetRoof.createRow(0);
             
             drafterRowRoof.createCell(0).setCellStyle(cellStyleViolet);
        	 drafterRowRoof.getCell(0).setCellValue("ROOF MOUNT");
        	 
        	 drafterRowRoof = drafterSheetRoof.createRow(1);
             
      		drafterRowRoof.createCell(0).setCellStyle(cellStyleViolet);
      		drafterRowRoof.createCell(1).setCellStyle(cellStyleViolet);
              
      		drafterRowRoof.getCell(0).setCellValue("Field Name");
      		drafterRowRoof.getCell(1).setCellValue("Field Value");
 		}
         if(checkValue.Equals(siteSurvey.getCarportMounted(), true) || checkValue.Equals(siteSurvey.getPatioMounted(), true)) {
        	 drafterRowPatiot = drafterSheetPatiot.createRow(0);
 	         
 	         drafterRowPatiot.createCell(0).setCellStyle(cellStyleViolet);
 			drafterRowPatiot.getCell(0).setCellValue("CARPOT PATIO COVER");
 			
 			drafterRowPatiot = drafterSheetPatiot.createRow(1);
 	        
 	 		drafterRowPatiot.createCell(0).setCellStyle(cellStyleViolet);
 	 		drafterRowPatiot.createCell(1).setCellStyle(cellStyleViolet);
 	         
 	 		drafterRowPatiot.getCell(0).setCellValue("Field Name");
 	 		drafterRowPatiot.getCell(1).setCellValue("Field Value");
 		}
 		if(checkValue.Equals(siteSurvey.getGroundMounted(), true)
 			) {
 			drafterRowNonRoof = drafterSheetNonRoof.createRow(0);
	         
	         drafterRowNonRoof.createCell(0).setCellStyle(cellStyleViolet);
			drafterRowNonRoof.getCell(0).setCellValue("NON ROOF MOUNT");
			
			drafterRowNonRoof = drafterSheetNonRoof.createRow(1);
	        
	 		drafterRowNonRoof.createCell(0).setCellStyle(cellStyleViolet);
	 		drafterRowNonRoof.createCell(1).setCellStyle(cellStyleViolet);
	         
	 		drafterRowNonRoof.getCell(0).setCellValue("Field Name");
	 		drafterRowNonRoof.getCell(1).setCellValue("Field Value");
 		}
         
 		
 		
         LinkedHashMap <String, String[]> drafterRoof = null;
         LinkedHashMap <String, String[]> drafterNonRoof = null;
         LinkedHashMap <String, String[]> drafterPatiot = null;
         
         if(checkValue.Equals(siteSurvey.getRoofMounted(), true)
  				|| (!checkValue.NotEquals(siteSurvey.getRoofMounted(), false)
  						&& !checkValue.NotEquals(siteSurvey.getGroundMounted(), false)
  						&& !checkValue.NotEquals(siteSurvey.getCarportMounted(), false)
  						&& !checkValue.NotEquals(siteSurvey.getPatioMounted(), false)
  						&& !checkValue.NotEquals(siteSurvey.getOtherMounted(), false))) {
        	 drafterRoof = roofMount(siteSurvey);
         	Iterator<String> keySetIteratordrafterRoof = drafterRoof.keySet().iterator();
             
             int i8 = 2;
             while(keySetIteratordrafterRoof.hasNext()){ 
            	 drafterRowRoof = drafterSheetRoof.createRow(i8);
            	 String key = keySetIteratordrafterRoof.next();
            	 drafterRowRoof.createCell(0).setCellValue(key);
            	 drafterRowRoof.createCell(1).setCellValue(drafterRoof.get(key)[0]);
            	 i8++;
            }
  		}
         if(checkValue.Equals(siteSurvey.getCarportMounted(), true) || checkValue.Equals(siteSurvey.getPatioMounted(), true)) {
        	 drafterPatiot = patioCoverFrrestanding(siteSurvey);
  			Iterator<String> keySetIteratordrafterPatio = drafterPatiot.keySet().iterator();
  	         
  	         int i10 = 2;
  	         while(keySetIteratordrafterPatio.hasNext()){ 
  	        	 drafterRowPatiot = drafterSheetPatiot.createRow(i10);
  	        	 String key = keySetIteratordrafterPatio.next();
  	        	 drafterRowPatiot.createCell(0).setCellValue(key);
  	        	 drafterRowPatiot.createCell(1).setCellValue(drafterPatiot.get(key)[0]);
  	        	 i10++;
  	        }
 		}
 		if(checkValue.Equals(siteSurvey.getGroundMounted(), true)
 			) {
 			drafterNonRoof = nonRoofMount(siteSurvey);
 			Iterator<String> keySetIteratordrafterNonRoof = drafterNonRoof.keySet().iterator();
 	         
 	         int i9 = 2;
 	         while(keySetIteratordrafterNonRoof.hasNext()){ 
 	        	 drafterRowNonRoof = drafterSheetNonRoof.createRow(i9);
 	        	 String key = keySetIteratordrafterNonRoof.next();
 	        	 drafterRowNonRoof.createCell(0).setCellValue(key);
 	        	 drafterRowNonRoof.createCell(1).setCellValue(drafterNonRoof.get(key)[0]);
 	        	 i9++;
 	        }
 		}
         
         	File exportSaveFolder = null;
			exportSaveFolder = new File(getfilesPath()+siteSurvey.getId());
			if (!exportSaveFolder.exists()) {
				new File(getfilesPath()+siteSurvey.getId()).mkdirs();
			}
         
	        String filePath = getfilesPath()+siteSurvey.getId()+"/"+siteSurvey.getId()+".xls";
	        fileOut = new FileOutputStream(filePath);
	        for(int c =0; c<homeOwnerSheet.getLastRowNum();c++){
	        	homeOwnerSheet.autoSizeColumn(c);
	        }
	        for(int c =0; c<arraysSheet.getLastRowNum();c++){
	        	arraysSheet.autoSizeColumn(c);
	        }
	        for(int c =0; c<bosSheet.getLastRowNum();c++){
	        	bosSheet.autoSizeColumn(c);
	        }
	        for(int c =0; c<conduitContractorSheet.getLastRowNum();c++){
	        	conduitContractorSheet.autoSizeColumn(c);
	        }
	        for(int c =0; c<addInfoSheet.getLastRowNum();c++){
	        	addInfoSheet.autoSizeColumn(c);
	        }
	        for(int c =0; c<layoutsheet.getLastRowNum();c++){
	        	layoutsheet.autoSizeColumn(c);
	        }
	        for(int c =0; c<utilitySheet.getLastRowNum();c++){
	        	utilitySheet.autoSizeColumn(c);
	        }for(int c =0; c<advSheet.getLastRowNum();c++){
	        	advSheet.autoSizeColumn(c);
	        }
	        if(checkValue.Equals(siteSurvey.getRoofMounted(), true)
	  				|| (!checkValue.NotEquals(siteSurvey.getRoofMounted(), false)
	  						&& !checkValue.NotEquals(siteSurvey.getGroundMounted(), false)
	  						&& !checkValue.NotEquals(siteSurvey.getCarportMounted(), false)
	  						&& !checkValue.NotEquals(siteSurvey.getPatioMounted(), false)
	  						&& !checkValue.NotEquals(siteSurvey.getOtherMounted(), false))) {
	        	 for(int c =0; c<drafterSheetRoof.getLastRowNum();c++){
	 	        	drafterSheetRoof.autoSizeColumn(c);
	 	        }
	        }
	        if(checkValue.Equals(siteSurvey.getCarportMounted(), true) || checkValue.Equals(siteSurvey.getPatioMounted(), true)) {
	        	for(int c =0; c<drafterSheetPatiot.getLastRowNum();c++){
		        	drafterSheetPatiot.autoSizeColumn(c);
		        }
	        }
	        if(checkValue.Equals(siteSurvey.getGroundMounted(), true)
	  			) {
	        	for(int c =0; c<drafterSheetNonRoof.getLastRowNum();c++){
		        	drafterSheetNonRoof.autoSizeColumn(c);
		        }
	        }
	       
	        workbook.write(fileOut);
	        fileOut.flush();
	        fileOut.close();
	        workbook.close();
         
		return siteSurvey.getId()+"/"+siteSurvey.getId()+".xls";
	} catch (Exception e) {
		e.printStackTrace();
		return "error";
	}
	

}

	public ResponseEntity<byte[]> getPic(String url) {
		Path path = Paths.get(getfilesPath()+"SiteSurvey-"+url+"/Image/HomePicture.png");
		
		byte[] contents = null;
		try {
			if (new File(getfilesPath()+"SiteSurvey-"+url+"/Image/HomePicture.png").exists())
				contents = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		String typeImage = url.split("\\.")[url.split("\\.").length-1];
		headers.setContentType(MediaType.parseMediaType("image/"+typeImage));
		
		String filename = url.split("/")[url.split("/").length-1];
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers,
				org.springframework.http.HttpStatus.OK);
		return response;
	}


	//07/08/2019 : CI : CR2680 : save Array Chart 
	public String saveSiteSurveyArrays(LayoutSketchArraysModel SiteSurveyArrays) {

		try {
			SiteSurveyEntity siteSurveyEntity = siteSurveyRepo.findById(SiteSurveyArrays.getPermitId()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" +SiteSurveyArrays.getPermitId()));
			List<SiteSurveyArrayChartEntity> arraysList = siteSurveyArrayChartRepo.findBySiteSurveyEntityId(SiteSurveyArrays.getPermitId());

			if (arraysList != null && !arraysList.isEmpty()) {
				for (SiteSurveyArrayChartEntity array : arraysList)
					siteSurveyArrayChartRepo.delete(array);
			}

			for (int i = 0; i < SiteSurveyArrays.getArraysLength(); i++) {
				SiteSurveyArrayChartEntity arraysEntity = new SiteSurveyArrayChartEntity();
				arraysEntity.setArraySketch(i + 1);
				arraysEntity.setAzimuth(SiteSurveyArrays.getAzimuth()[i]);
				arraysEntity.setEaveOverHang(SiteSurveyArrays.getEaveOverHang()[i]);
				arraysEntity.setEaveOverHangOther(SiteSurveyArrays.getEaveOverHangOther()[i]);
				arraysEntity.setModelvalue(SiteSurveyArrays.getModelvalue()[i]);
				arraysEntity.setModuleQty(SiteSurveyArrays.getModuleQty()[i] + "");
				arraysEntity.setModuleTils(SiteSurveyArrays.getModuleTils()[i]);
				arraysEntity.setRoofPitch(SiteSurveyArrays.getRoofPitch()[i]);
				arraysEntity.setSiteSurveyEntity(siteSurveyEntity);
				siteSurveyArrayChartRepo.save(arraysEntity);
			}
				return "Done";
			
	} catch (Exception e) {
		e.printStackTrace();
		return "Fail";
	}

	}
	
	public String synchronizationLayoutSketchArrays(List<PermitSketchResults> siteSurveyArrays,Long idPermit) {

		try {
			
			PermitEntity permitEntity = permitRepo.findById(idPermit).orElseThrow(
							() -> new ResourceNotFoundException("Entity not found for this id :" +idPermit));
	
			
			List<PermitSketchEntity> arraysList = permitSketchRepo.findByPermitEntityId(idPermit);
			if (arraysList != null && !arraysList.isEmpty()) {
				for (PermitSketchEntity array : arraysList)
					permitSketchRepo.delete(array);
			}else {
				PermitSketchEntity arraysEntity = new PermitSketchEntity();
				arraysEntity.setPermitEntity(permitEntity);
				permitSketchRepo.save(arraysEntity);
			}
			if(siteSurveyArrays != null && !siteSurveyArrays.isEmpty()) {
				for (int i = 0; i < siteSurveyArrays.size(); i++) {
					PermitSketchEntity arraysEntity = new PermitSketchEntity();
					arraysEntity.setArraySketch(i + 1);
					arraysEntity.setAzimuth(siteSurveyArrays.get(i).getAzimuth());
					arraysEntity.setEaveOverHang(siteSurveyArrays.get(i).getEaveOverHang());
					arraysEntity.setEaveOverHangOther(siteSurveyArrays.get(i).getEaveOverHangOther());
					arraysEntity.setModelvalue(siteSurveyArrays.get(i).getModelvalue());
					arraysEntity.setModuleQty(siteSurveyArrays.get(i).getModuleQty() + "");
					arraysEntity.setModuleTils(siteSurveyArrays.get(i).getModuleTils());
					arraysEntity.setRoofPitch(siteSurveyArrays.get(i).getRoofPitch());
					arraysEntity.setPermitEntity(permitEntity);
					permitSketchRepo.save(arraysEntity);
				}
			}
			return "Success";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

	}
	
	//14/08/2019 : CI : CR2680 : get Array Chart 
	public List<PermitSketchResults> getSketchBySiteSurvey(Long idPermit) {
		List<PermitSketchResults> sketchPermit = siteSurveyRepo.getSiteSurveyArrayChart(idPermit);
		if (!sketchPermit.isEmpty()) {
			sketchPermit.get(0).setHaveSkecth(true);
			for (Iterator<PermitSketchResults> iter = sketchPermit.iterator(); iter.hasNext();) {
				PermitSketchResults p = iter.next();
				if (!checkValue.NotEquals(p.getModuleQty(),"")) {
					iter.remove();
				}
			}
		} else {
			PermitSketchResults permitSketchResults = new PermitSketchResults(false);
			sketchPermit.add(permitSketchResults);
		}

		return sketchPermit;

	}
	
	//16/08/2019 : CI : CR2680 : save Array Chart 
		public String saveNonRoofSketchTable(LayoutSketchArraysModel siteSurveyArrays) {

			try {
				SiteSurveyEntity siteSurveyEntity = siteSurveyRepo.findById(siteSurveyArrays.getPermitId()).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id :" +siteSurveyArrays.getPermitId()));

				List<SiteSurveyArrayChartNonRoofEntity> arraysList = siteSurveyArrayChartNonRoofRepo.findBySiteSurveyEntityId(siteSurveyArrays.getPermitId());

				if (arraysList != null && !arraysList.isEmpty()) {
					for (SiteSurveyArrayChartNonRoofEntity array : arraysList)
						siteSurveyArrayChartNonRoofRepo.delete(array);
				}
				if(siteSurveyArrays != null) {
					for (int i = 0; i < siteSurveyArrays.getArraysLength(); i++) {
						SiteSurveyArrayChartNonRoofEntity arraysEntity = new SiteSurveyArrayChartNonRoofEntity();
						arraysEntity.setArraySketch(i + 1);
						arraysEntity.setAzimuth(siteSurveyArrays.getAzimuth()[i]);
						arraysEntity.setEaveOverHang(siteSurveyArrays.getEaveOverHang()[i]);
						arraysEntity.setEaveOverHangOther(siteSurveyArrays.getEaveOverHangOther()[i]);
						arraysEntity.setModelvalue(siteSurveyArrays.getModelvalue()[i]);
						arraysEntity.setModuleQty(siteSurveyArrays.getModuleQty()[i] + "");
						arraysEntity.setModuleTils(siteSurveyArrays.getModuleTils()[i]);
						arraysEntity.setRoofPitch(siteSurveyArrays.getRoofPitch()[i]);
						arraysEntity.setSiteSurveyEntity(siteSurveyEntity);
						siteSurveyArrayChartNonRoofRepo.save(arraysEntity);
					}
				}
				return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}

		}
		
		//16/08/2019 : CI : CR2680 : save Patio Array Chart 
				public String savePatioSketchTable(LayoutSketchArraysModel siteSurveyArrays) {

					try {
						SiteSurveyEntity siteSurveyEntity = siteSurveyRepo.findById(siteSurveyArrays.getPermitId()).orElseThrow(
								() -> new ResourceNotFoundException("Entity not found for this id :" +siteSurveyArrays.getPermitId()));

						List<SiteSurveyArrayChartPatioEntity> arraysList = siteSurveyArrayChartPatioRepo.findBySiteSurveyEntityId(siteSurveyArrays.getPermitId());

						if (arraysList != null && !arraysList.isEmpty()) {
							for (SiteSurveyArrayChartPatioEntity array : arraysList) {
								siteSurveyArrayChartPatioRepo.delete(array);
							}
						}
			if(siteSurveyArrays != null) {
				for (int i = 0; i < siteSurveyArrays.getArraysLength(); i++) {
					SiteSurveyArrayChartPatioEntity arraysEntity = new SiteSurveyArrayChartPatioEntity();
					arraysEntity.setArraySketch(i + 1);
					arraysEntity.setAzimuth(siteSurveyArrays.getAzimuth()[i]);
					arraysEntity.setEaveOverHang(siteSurveyArrays.getEaveOverHang()[i]);
					arraysEntity.setEaveOverHangOther(siteSurveyArrays.getEaveOverHangOther()[i]);
					arraysEntity.setModelvalue(siteSurveyArrays.getModelvalue()[i]);
					arraysEntity.setModuleQty(siteSurveyArrays.getModuleQty()[i] + "");
					arraysEntity.setModuleTils(siteSurveyArrays.getModuleTils()[i]);
					arraysEntity.setRoofPitch(siteSurveyArrays.getRoofPitch()[i]);
					arraysEntity.setSiteSurveyEntity(siteSurveyEntity);
					siteSurveyArrayChartPatioRepo.save(arraysEntity);
				}
			}
					
				return "Done";
					
				} catch (Exception e) {
					e.printStackTrace();
					return "Fail";
				}

				}
				
				
				//14/08/2019 : CI : CR2680 : get Array Chart in non roof mount tab
				public List<PermitSketchResults> getNonRoofMountSketchBySiteSurvey(Long idPermit) {
					List<PermitSketchResults> sketchPermit = siteSurveyRepo.getSiteSurveyArrayChartNonRoof(idPermit);
					if (!sketchPermit.isEmpty()) {
						sketchPermit.get(0).setHaveSkecth(true);
						for (Iterator<PermitSketchResults> iter = sketchPermit.iterator(); iter.hasNext();) {
							PermitSketchResults p = iter.next();
							if (!checkValue.NotEquals(p.getModuleQty(),"")) {
								iter.remove();
							}
						}
					} else {
						PermitSketchResults permitSketchResults = new PermitSketchResults(false);
						sketchPermit.add(permitSketchResults);
					}

					return sketchPermit;

				}	
				
				//14/08/2019 : CI : CR2680 : get Array Chart in Patio Cover freestanding
				public List<PermitSketchResults> getPatioSketchBySiteSurvey(Long idPermit) {
					List<PermitSketchResults> sketchPermit = siteSurveyRepo.getSiteSurveyArrayChartPatio(idPermit);
					if (!sketchPermit.isEmpty()) {
						sketchPermit.get(0).setHaveSkecth(true);
						for (Iterator<PermitSketchResults> iter = sketchPermit.iterator(); iter.hasNext();) {
							PermitSketchResults p = iter.next();
							if (!checkValue.NotEquals(p.getModuleQty(),"")) {
								iter.remove();
							}
						}
					} else {
						PermitSketchResults permitSketchResults = new PermitSketchResults(false);
						sketchPermit.add(permitSketchResults);
					}

					return sketchPermit;

				}	
				
	public ResponseEntity<byte[]> downloadPackage(String homeOwnName, Long idSiteSurvey) {
		
		String url = getfilesPath() + "siteSurveyPackage/" + homeOwnName + "-SiteSurvey.zip";
		Path path = Paths.get(url);
		byte[] contents = null;
		try {
			/***************** Generate EXCEL File ********************/
			generateSiteSurvey(idSiteSurvey);

			String dir = getfilesPath();
			String[] srcFiles = { dir + idSiteSurvey + "/" + idSiteSurvey + ".xls" };
			/***************** END get data for EXCEL ********************/

			/*************** generation of all the photos ************************/
			
			File imgFile = new File(dir + "SiteSurvey-" + idSiteSurvey);
			if (imgFile.exists()) {
				File[] files = new File(dir + "SiteSurvey-" + idSiteSurvey + "/Image/").listFiles();
				if (files != null && files.length > 0) {
					for (int i = 0; i < files.length; i++) {
						File[] images = files[i].listFiles();

						for (int j = 0;images != null && j < images.length; j++) {
							srcFiles = push(srcFiles, images[j].getPath());
						}
					}
				}
			}

			/****************************/

			/******************************
			 * Creation du fichier zip
			 ******************************/

			String zipFile = dir + "siteSurveyPackage/" + homeOwnName + "-SiteSurvey.zip";
			File zip = new File(zipFile);
		
			if (zip != null && zip.exists()) {
				zip.delete();
			}
			zip.getParentFile().mkdirs();

			// create byte buffer
			byte[] buffer = new byte[1024];
			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			for (int i = 0; i < srcFiles.length; i++) {
				File srcFile = new File(srcFiles[i]);
				if (srcFile.exists()) {
					FileInputStream fis = new FileInputStream(srcFile);
					// begin writing a new ZIP entry, positions the stream to the start of the entry
					// data
					zos.putNextEntry(new ZipEntry(srcFile.getName().replace(".png", ".jpg")));
					int length;
					while ((length = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, length);
					}
					zos.closeEntry();
					// close the InputStream
					fis.close();
				}
			}
			// close the ZipOutputStream
			zos.close();

			try {
				contents = Files.readAllBytes(path);
				// downloadPackageTracker(idSiteSurvey);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
		String filename = "output.xls";
		headers.setContentDispositionFormData(filename, filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers,
				org.springframework.http.HttpStatus.OK);
		return response;

	}

	private String[] push(String[] array, String push) {
		String[] longer = new String[array.length + 1];
		System.arraycopy(array, 0, longer, 0, array.length);
		longer[array.length] = push;
		return longer;
	}
	
	public String addCostumField(SiteSurveyCostumFieldModel costumField) {
		try {
		
			SiteSurveyCostumFieldEntity siteSurveyCostumField = new SiteSurveyCostumFieldEntity();
				
			   siteSurveyCostumField.setFieldName(costumField.getFieldModel());
			   siteSurveyCostumField.setDisabled(costumField.getDisabled());
			   siteSurveyCostumField.setFieldModel(costumField.getFieldModel());
			   siteSurveyCostumField.setHasRequiredLogic(costumField.getHasRequiredLogic());
			   siteSurveyCostumField.setLogic(costumField.getLogic());
			   siteSurveyCostumField.setRequired(costumField.getRequired());
			   siteSurveyCostumField.setState(costumField.getState());
			   siteSurveyCostumField.setTabName(costumField.getTabName());
			   siteSurveyCostumField.setDeleted(costumField.getDeleted());
			   siteSurveyCostumFieldRepo.save(siteSurveyCostumField);

				
				return siteSurveyCostumField.getId()+"";
			

		} catch (Exception e) {
			
			e.printStackTrace();
			return "error";
		}
	}
	
	public List<SiteSurveyTextAreaFieldsEntity> getCostumFieldListByState(String state, Long siteSurveyID){
		List<SiteSurveyTextAreaFieldsEntity> textAreaFields = new ArrayList<>();
		List<SiteSurveyCostumFieldEntity> costumFieldList = new ArrayList<>();
		try {
			
			SiteSurveyEntity siteSurveyEn = siteSurveyRepo.findById(siteSurveyID).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" +siteSurveyID));
			
			costumFieldList = siteSurveyCostumFieldRepo.findByState(state);

			textAreaFields = siteSurveyTextAreaFieldsRepo.findBySiteSurveyEntityIdAndSiteSurveyCostumFieldEntityState(siteSurveyID, state);
			if(textAreaFields!=null && !textAreaFields.isEmpty()) {
				ArrayList<Long> listId = new ArrayList<>();
				for(int j=0;j<textAreaFields.size();j++) {
					listId.add(textAreaFields.get(j).getSiteSurveyCostumFieldEntity().getId());
				}
				
				for(int i=0;costumFieldList != null && i<costumFieldList.size();i++) {
					if(listId.contains(costumFieldList.get(i).getId())) {
						
					}else {
					SiteSurveyTextAreaFieldsEntity newSiteSurveyTextAreaFieldsEntity = new SiteSurveyTextAreaFieldsEntity();
					newSiteSurveyTextAreaFieldsEntity.setSiteSurveyCostumFieldEntity(costumFieldList.get(i));
					newSiteSurveyTextAreaFieldsEntity.setTextBoxContent("");
					newSiteSurveyTextAreaFieldsEntity.setSiteSurveyEntity(siteSurveyEn);
					textAreaFields.add(newSiteSurveyTextAreaFieldsEntity);
					siteSurveyTextAreaFieldsRepo.save(newSiteSurveyTextAreaFieldsEntity);
					}
				}
			}else {
				for(int i=0;costumFieldList != null && i<costumFieldList.size();i++) {
					SiteSurveyTextAreaFieldsEntity newSiteSurveyTextAreaFieldsEntity = new SiteSurveyTextAreaFieldsEntity();
					newSiteSurveyTextAreaFieldsEntity.setSiteSurveyCostumFieldEntity(costumFieldList.get(i));
					newSiteSurveyTextAreaFieldsEntity.setTextBoxContent("");
					newSiteSurveyTextAreaFieldsEntity.setSiteSurveyEntity(siteSurveyEn);
					textAreaFields.add(newSiteSurveyTextAreaFieldsEntity);
					siteSurveyTextAreaFieldsRepo.save(newSiteSurveyTextAreaFieldsEntity);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return textAreaFields;
	}
	
	
	
	public List<SiteSurveyCostumFieldEntity> getSiteSurveyCostumFieldSetting(String state){
		List<SiteSurveyCostumFieldEntity> costumFieldList = new ArrayList<>();
		try {
			costumFieldList = siteSurveyCostumFieldRepo.findByStateAndDeleted(state, false);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return costumFieldList;
	}
	
	public String deleteCostumField(Integer costumFieldId) {
		try {
			SiteSurveyCostumFieldEntity costumField = siteSurveyCostumFieldRepo.findById(Long.valueOf(costumFieldId)).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" +Long.valueOf(costumFieldId)));
			if(costumField!=null) {
			  costumField.setDeleted(true);
			  siteSurveyCostumFieldRepo.save(costumField);
			}
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	
	public String saveCostumFieldSetting(List<CostumFieldModelResult> changedCostumFieldList) {
		try {
			
			for (int i = 0; !changedCostumFieldList.isEmpty() &&  i < changedCostumFieldList.size(); i++) {
				SiteSurveyCostumFieldEntity costumField = siteSurveyCostumFieldRepo.findById(Long.valueOf(changedCostumFieldList.get(i).getId())).orElseThrow(
						() -> new ResourceNotFoundException("Entity not found for this id."));
				if(costumField !=null) {
				costumField.setHasRequiredLogic(changedCostumFieldList.get(i).getHasRequiredLogic());
				costumField.setRequired(changedCostumFieldList.get(i).getRequired());
				costumField.setLogic(changedCostumFieldList.get(i).getLogic());
				siteSurveyCostumFieldRepo.save(costumField);
				}
			}
			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String saveTextBoxFields(List<SiteSurveyTextAreaFieldsEntity> siteSurveyTextAreaFields,
			Long siteSurveyID) {
		List<SiteSurveyTextAreaFieldsEntity> textAreaFields = siteSurveyTextAreaFieldsRepo.findBySiteSurveyEntityId(siteSurveyID);
		
		if(!textAreaFields.isEmpty()){
			for (SiteSurveyTextAreaFieldsEntity textAreaField : textAreaFields) {
				siteSurveyTextAreaFieldsRepo.delete(textAreaField);
			}
		}
			for(int i=0;siteSurveyTextAreaFields != null && i<siteSurveyTextAreaFields.size();i++) {
				SiteSurveyTextAreaFieldsEntity siteSurveyTextAreaFieldsEntity = new SiteSurveyTextAreaFieldsEntity();
				siteSurveyTextAreaFieldsEntity.setSiteSurveyCostumFieldEntity(siteSurveyTextAreaFields.get(i).getSiteSurveyCostumFieldEntity());
				siteSurveyTextAreaFieldsEntity.setTextBoxContent(siteSurveyTextAreaFields.get(i).getTextBoxContent());
				siteSurveyTextAreaFieldsEntity.setSiteSurveyEntity(siteSurveyTextAreaFields.get(i).getSiteSurveyEntity());
				siteSurveyTextAreaFieldsRepo.save(siteSurveyTextAreaFieldsEntity);
				
			}
	
		return null;
	}
	
	public String checkProjectExisting(NewSiteSurveyModel siteSurvey) {
		try {
			
			if(checkValue.Equals(siteSurvey.getHomeOwnName(), "")) {
				siteSurvey.setHomeOwnName(null);
			}
			if(checkValue.Equals(siteSurvey.getHomeOwnLastName(), "")) {
				siteSurvey.setHomeOwnLastName(null);
			}
			String name2=siteSurvey.getHomeOwnName();
			String homeOwnLastName2=siteSurvey.getHomeOwnLastName();
			String projectName2=siteSurvey.getProjectName();
			if(checkValue.NotEquals(siteSurvey.getHomeOwnName(), "")) {
				name2= siteSurvey.getHomeOwnName().toLowerCase().trim();
			}
			if(checkValue.NotEquals(siteSurvey.getHomeOwnLastName(), "")) {
				homeOwnLastName2= siteSurvey.getHomeOwnLastName().toLowerCase().trim();
			}
			if(checkValue.NotEquals(siteSurvey.getProjectName(), "")) {
				projectName2= siteSurvey.getProjectName().toLowerCase().trim();
			}
			List<PermitEntity> listPermit = permitRepo.getListPermit(name2, homeOwnLastName2, projectName2);
			if(!listPermit.isEmpty()) {
				return "exist";
			}
			else {
				return "Doesn't exist";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	
	public String checkDuplicatedProject(String projectName, String firstName, String lastName, Long idSiteSurvey) {
		
		String name2=firstName;
		String homeOwnLastName2=lastName;
		String projectName2=projectName;
		
		
		
		
		try {
			if(checkValue.NotEquals(firstName, "")) {
				name2= firstName.toLowerCase().trim();
			}else {
				name2 = null;
			}
			if(checkValue.NotEquals(lastName, "")) {
				homeOwnLastName2= lastName.toLowerCase().trim();
			}else {
				homeOwnLastName2 = null;
			}
			if(checkValue.NotEquals(projectName, "")) {
				projectName2= projectName.toLowerCase().trim();
			}else {
				projectName2 = null;
			}


			
			SiteSurveyEntity site = siteSurveyRepo.findById(idSiteSurvey).orElseThrow(
					() -> new ResourceNotFoundException("Entity not found for this id :" +idSiteSurvey));
			
			List<PermitEntity> listP = permitRepo.getListPermits(name2, homeOwnLastName2, site.getPortalProject(), projectName2);
		    List<SiteSurveyEntity> listSS = siteSurveyRepo.getListSiteSurvey(name2, homeOwnLastName2, projectName2, idSiteSurvey);

			
			if(!listP.isEmpty() || !listSS.isEmpty()) {
				return "exist";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Fail";
	}
	//A.B 01-20 Test Romoved & Fav Removed
	public Long testInverterRemoved(Long inverterId, Long contractorId, Long permitId,Integer inv) {
		try {
			String inverterModelDeleted = inv == 1 ? projectArraysRepo.findFirstInverterModel(permitId) :
				inv == 2 ? projectArraysRepo.findFirstInverterModel(permitId) :
					inv == 3 ? projectArraysRepo.findSecondInverterModel(permitId) :"";
			boolean  inverterDeleted  = inverterRepo.findIfDeleted(inverterId);
			if (checkValue.Equals(inverterDeleted, true) || checkValue.Equals(inverterModelDeleted, "Removed")) return -1L;
			else {
				InverterLibraryEntity inverterFav = inverterFavRepo.findOneByAuthentificationEntityIdAndInvertersId(contractorId,inverterId);
				if (inverterFav == null || checkValue.Equals(inverterModelDeleted, "Fav Removed"))  return 0L;
			}
			return inverterId;
		} catch (Exception e) {
			e.printStackTrace();
			return inverterId;
		}
	}
}
