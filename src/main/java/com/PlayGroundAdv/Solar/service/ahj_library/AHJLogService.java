package com.PlayGroundAdv.Solar.service.ahj_library;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJAttachmentLogEntity;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJChecklistEntity;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJLogEntity;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.TableDataPage;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJACDiscoModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJAddRemoveFromPlansetModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJAnchorSpacingRelatedRequirementsModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJContactsModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJDataModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJDesignCriteriaModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJElectricalModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJFireSetbacksRoofMountedModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJGroundMountRelatedRequirementsModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJGroundMountSetbacksModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJHowManyModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJLastKnownPlanSetApproved;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJPVMeterModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJPreApprovalReqModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJReqCustomPlaSetSheetorSizeModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJRevisionOrResubmittalModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJRoofMountRelatedRequirementOnlyModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJRoofVentsModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJUsedByClientsModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJWaterIntrusionModel;
import com.PlayGroundAdv.Solar.model.ahj_library.DeletedAttachmentModel;
import com.PlayGroundAdv.Solar.model.ahj_library.GoverningCodesModel;
import com.PlayGroundAdv.Solar.model.ahj_library.PageModel;
import com.PlayGroundAdv.Solar.model.ahj_library.ResultUpdateLog;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJACDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJAddRemoveFromPlansetRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJAnchorSpacingRelatedRequirementsRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJAttachmentLogRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJChecklistRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJColumnsRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJContactsRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJDesignCriteriaRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJElectricalRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJFireSetbacksRoofMountedRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJGroundMountRelatedRequirementsOnlyRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJGroundMountSetbacksRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJHowManyRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJLogRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJPVMeterRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJPreApprovalReqRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJReqCustomPlanSetSheetsorSizeRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJRevisionOrResubmittalRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJRoofMountRelatedRequirementsOnlyRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJRoofVentsRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJUsedByClientsRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJWaterIntrusionRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class AHJLogService {

	final AHJChecklistRepository aHJChecklistRepo;
	final AuthenticationRepository userRepo;
	final ModelMapper modelMapperAHJ;
	final AHJLogRepository ahjLogRepo;
	final AHJAttachmentLogRepository attachementLogRepo;
	final AHJColumnsRepository ahjColumnsRepo;
	final AHJACDiscoRepository acDiscoRepo;
	final AHJAddRemoveFromPlansetRepository addRemoveFromPlansetRepo;
	final AHJAnchorSpacingRelatedRequirementsRepository anchorSpacingRelatedRequirementsRepo;
	final AHJDesignCriteriaRepository designCriteriaRepo;
	final AHJElectricalRepository electricalRepo;
	final AHJFireSetbacksRoofMountedRepository fireSetbacksRoofMountedRepo;
	final AHJGroundMountRelatedRequirementsOnlyRepository groundMountRelatedRequirementsOnlyRepo;
	final AHJGroundMountSetbacksRepository groundMountSetbacksRepo;
	final AHJPVMeterRepository pvMeterRepo;
	final AHJReqCustomPlanSetSheetsorSizeRepository reqCustomPlanSetSheetsorSizeRepo;
	final AHJRoofMountRelatedRequirementsOnlyRepository roofMountRelatedRequirementsOnlyRepo;
	final AHJRoofVentsRepository roofVentsRepo;
	final AHJWaterIntrusionRepository waterIntrusionRepo;
	final AHJHowManyRepository howManyRepo;
	final AHJPreApprovalReqRepository preApprovalReqRepo; 
	final AHJRevisionOrResubmittalRepository revisionOrResubmittalRepo;
	final AHJContactsRepository contactsRepository;
	final AHJUsedByClientsRepository usedByClientsRepo;
	final CheckValueTypesService checkValue;


	public AHJLogService(AHJChecklistRepository aHJChecklistRepo, AuthenticationRepository userRepo,
			ModelMapper modelMapperAHJ, AHJLogRepository ahjLogRepo, AHJAttachmentLogRepository attachementLogRepo,
			AHJColumnsRepository ahjColumnsRepo, AHJACDiscoRepository acDiscoRepo,
			AHJAddRemoveFromPlansetRepository addRemoveFromPlansetRepo,
			AHJAnchorSpacingRelatedRequirementsRepository anchorSpacingRelatedRequirementsRepo,
			AHJDesignCriteriaRepository designCriteriaRepo, AHJElectricalRepository electricalRepo,
			AHJFireSetbacksRoofMountedRepository fireSetbacksRoofMountedRepo,
			AHJGroundMountRelatedRequirementsOnlyRepository groundMountRelatedRequirementsOnlyRepo,
			AHJGroundMountSetbacksRepository groundMountSetbacksRepo, AHJPVMeterRepository pvMeterRepo,
			AHJReqCustomPlanSetSheetsorSizeRepository reqCustomPlanSetSheetsorSizeRepo,
			AHJRoofMountRelatedRequirementsOnlyRepository roofMountRelatedRequirementsOnlyRepo,
			AHJRoofVentsRepository roofVentsRepo, AHJWaterIntrusionRepository waterIntrusionRepo,
			AHJHowManyRepository howManyRepo, AHJPreApprovalReqRepository preApprovalReqRepo,
			AHJRevisionOrResubmittalRepository revisionOrResubmittalRepo, AHJContactsRepository contactsRepository,
			AHJUsedByClientsRepository usedByClientsRepo, CheckValueTypesService checkValue) {
		super();
		this.aHJChecklistRepo = aHJChecklistRepo;
		this.userRepo = userRepo;
		this.modelMapperAHJ = modelMapperAHJ;
		this.ahjLogRepo = ahjLogRepo;
		this.attachementLogRepo = attachementLogRepo;
		this.ahjColumnsRepo = ahjColumnsRepo;
		this.acDiscoRepo = acDiscoRepo;
		this.addRemoveFromPlansetRepo = addRemoveFromPlansetRepo;
		this.anchorSpacingRelatedRequirementsRepo = anchorSpacingRelatedRequirementsRepo;
		this.designCriteriaRepo = designCriteriaRepo;
		this.electricalRepo = electricalRepo;
		this.fireSetbacksRoofMountedRepo = fireSetbacksRoofMountedRepo;
		this.groundMountRelatedRequirementsOnlyRepo = groundMountRelatedRequirementsOnlyRepo;
		this.groundMountSetbacksRepo = groundMountSetbacksRepo;
		this.pvMeterRepo = pvMeterRepo;
		this.reqCustomPlanSetSheetsorSizeRepo = reqCustomPlanSetSheetsorSizeRepo;
		this.roofMountRelatedRequirementsOnlyRepo = roofMountRelatedRequirementsOnlyRepo;
		this.roofVentsRepo = roofVentsRepo;
		this.waterIntrusionRepo = waterIntrusionRepo;
		this.howManyRepo = howManyRepo;
		this.preApprovalReqRepo = preApprovalReqRepo;
		this.revisionOrResubmittalRepo = revisionOrResubmittalRepo;
		this.contactsRepository = contactsRepository;
		this.usedByClientsRepo = usedByClientsRepo;
		this.checkValue = checkValue;
	}

	public void logUpdate(AHJDataModel ahjModel, AHJChecklistEntity ahj, AuthentificationEntity by) {
		try {
			List<ResultUpdateLog> logList  = ahjModel.getGoverningCodes().compareFields(modelMapperAHJ.map(ahj, GoverningCodesModel.class));
			logList.addAll(ahjModel.getAcdisco().compareFields(modelMapperAHJ.map(acDiscoRepo.findByAhjId(ahj.getId()), AHJACDiscoModel.class)));
			logList.addAll(ahjModel.getAddRemoveFromPlanset().compareFields(modelMapperAHJ.map(addRemoveFromPlansetRepo.findByAhjId(ahj.getId()), AHJAddRemoveFromPlansetModel.class)));
			logList.addAll(ahjModel.getAnchorSpacingRelatedRequirements().compareFields(modelMapperAHJ.map(anchorSpacingRelatedRequirementsRepo.findByAhjId(ahj.getId()),AHJAnchorSpacingRelatedRequirementsModel.class)));
			logList.addAll(ahjModel.getDesignCriteria().compareFields(modelMapperAHJ.map(designCriteriaRepo.findByAhjId(ahj.getId()), AHJDesignCriteriaModel.class)));
			logList.addAll(ahjModel.getElectrical().compareFields(modelMapperAHJ.map(electricalRepo.findByAhjId(ahj.getId()), AHJElectricalModel.class)));
			logList.addAll(ahjModel.getFireSetbacksRoofMounted().compareFields(modelMapperAHJ.map(fireSetbacksRoofMountedRepo.findByAhjId(ahj.getId()),AHJFireSetbacksRoofMountedModel.class)));
			logList.addAll(ahjModel.getGroundMountRelatedRequirements().compareFields(modelMapperAHJ.map(groundMountRelatedRequirementsOnlyRepo.findByAhjId(ahj.getId()),AHJGroundMountRelatedRequirementsModel.class)));
			logList.addAll(ahjModel.getGroundMountSetbacks().compareFields(modelMapperAHJ.map(groundMountSetbacksRepo.findByAhjId(ahj.getId()), AHJGroundMountSetbacksModel.class)));
			logList.addAll(ahjModel.getPvMeter().compareFields(modelMapperAHJ.map(pvMeterRepo.findByAhjId(ahj.getId()), AHJPVMeterModel.class)));
			logList.addAll(ahjModel.getReqCustomPlaSetSheetorSize().compareFields(modelMapperAHJ.map(reqCustomPlanSetSheetsorSizeRepo.findByAhjId(ahj.getId()),AHJReqCustomPlaSetSheetorSizeModel.class)));
			logList.addAll(ahjModel.getRoofMountRelatedRequirementOnly().compareFields(modelMapperAHJ.map(roofMountRelatedRequirementsOnlyRepo.findByAhjId(ahj.getId()),AHJRoofMountRelatedRequirementOnlyModel.class)));
			logList.addAll(ahjModel.getRoofVents().compareFields(modelMapperAHJ.map(roofVentsRepo.findByAhjId(ahj.getId()), AHJRoofVentsModel.class)));
			logList.addAll(ahjModel.getWaterIntrusion().compareFields(modelMapperAHJ.map(waterIntrusionRepo.findByAhjId(ahj.getId()), AHJWaterIntrusionModel.class)));
			logList.addAll(ahjModel.getLastKnownPlanSetApproved().compareFields(modelMapperAHJ.map(ahj, AHJLastKnownPlanSetApproved.class)));
			logList.addAll(ahjModel.getHowMany().compareFields(modelMapperAHJ.map(howManyRepo.findByAhjId(ahj.getId()),AHJHowManyModel.class)));
			logList.addAll(ahjModel.getPreApprovalReq().compareFields(modelMapperAHJ.map(preApprovalReqRepo.findByAhjId(ahj.getId()),AHJPreApprovalReqModel.class)));
			logList.addAll(ahjModel.getRevisionOrResubmittal().compareFields(modelMapperAHJ.map(revisionOrResubmittalRepo.findByAhjId(ahj.getId()), AHJRevisionOrResubmittalModel.class)));
			logList.addAll(ahjModel.getContacts().compareFields(modelMapperAHJ.map(contactsRepository.findByAhjId(ahj.getId()), AHJContactsModel.class)));
			logList.addAll(ahjModel.getUsedByClients().compareFields(modelMapperAHJ.map(usedByClientsRepo.findByAhjId(ahj.getId()), AHJUsedByClientsModel.class)));
			
			for (ResultUpdateLog log : logList) {
				String cellName = log.getFieldName().equals("Special Notes") ? log.getFieldName() : ahjColumnsRepo.findColumnTitleByName(log.getFieldName());
				AHJLogEntity logEntity = new AHJLogEntity(ahj, by, new Date(), log.getCategory(), cellName, log.getOldValue(), log.getNewValue(), false, null);
				ahjLogRepo.save(logEntity);
			}
			String bInfo = "Basic Information";
			if(!ahjModel.getAhj().equals(ahj.getAhj())) {
				ahjLogRepo.save(new AHJLogEntity(ahj, by, new Date(),bInfo, "AHJ Name", ahj.getAhj(), ahjModel.getAhj(), false, null));
			}
			if(!ahjModel.getState().equals(ahj.getState())) {
				ahjLogRepo.save(new AHJLogEntity(ahj, by, new Date(),bInfo, "State", ahj.getState(), ahjModel.getState(), false, null));
			}
			if(!checkValue.compare(ahj.getCounty(), ahjModel.getCounty())) {
				ahjLogRepo.save(new AHJLogEntity(ahj, by, new Date(),bInfo, "County", ahj.getCounty(), ahjModel.getCounty(), false, null));
			}
			if(!checkValue.compareBoolean(ahj.getIncorporated(), ahjModel.getIncorporated())) {
				ahjLogRepo.save(new AHJLogEntity(ahj, by, new Date(),bInfo, "Incorporated/Unincorporated", Boolean.TRUE.equals(ahj.getIncorporated()) ? "Incorporated" :"Unincorporated", Boolean.TRUE.equals(ahjModel.getIncorporated()) ? "Incorporated" :"Unincorporated", false, null));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void logAttachmentDelete(DeletedAttachmentModel attModel) {
		try {
			AuthentificationEntity deletedBy = userRepo.findById(attModel.getUserId()).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" + attModel.getUserId()));
			AHJChecklistEntity ahj = aHJChecklistRepo.findById(attModel.getAhjId())
					.orElseThrow(() -> new ResourceNotFoundException("AHJ not found for this id :" + attModel.getAhjId()));
			
			attachementLogRepo.save(new AHJAttachmentLogEntity(ahj, deletedBy, new Date(), attModel.getCategory(), attModel.getCellTitle(), attModel.getCellId(), attModel.getFileName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public TableDataPage getAHJLog(PageModel page){
		
		try {
			Long ahjId = Long.valueOf(page.getAhj());
			Pageable pageable = PageRequest.of(page.getPage(), page.getSize(), Sort.by("lastUpdate").descending());
			Page<AHJLogEntity> logPage = ahjLogRepo.findByAhjId(ahjId, pageable);
			List<Object> data = new ArrayList<>();
			for (AHJLogEntity log : logPage) {
				data.add(new ResultUpdateLog(Boolean.TRUE.equals(log.getMassUpdate()) ? "Mass Update" : log.getCategory(), Boolean.TRUE.equals(log.getMassUpdate()) ? log.getCellName().toUpperCase() : log.getCellName(), log.getPreviousValue(), log.getNewValue(), 
						log.getUpdatedBy().getFirstName()+" "+log.getUpdatedBy().getLastName(), log.getLastUpdate()));
			}
			return new TableDataPage(data, logPage.getTotalElements(), logPage.getTotalPages());
		} catch (Exception e) {
			e.printStackTrace();
			return new TableDataPage(new ArrayList<>(), 0l, 0);
		}
		
	}
	
	public TableDataPage getAHJAttachmentLog(PageModel page){
		
		try {
			Long ahjId = Long.valueOf(page.getAhj());
			Pageable pageable = PageRequest.of(page.getPage(), page.getSize(), Sort.by("deleteDate").descending());
			Page<AHJAttachmentLogEntity> logPage = attachementLogRepo.findByAhjId(ahjId, pageable);
			List<Object> data = new ArrayList<>();
			for (AHJAttachmentLogEntity log : logPage) {
				data.add(new DeletedAttachmentModel(log.getAhj().getId(), log.getDeletedBy().getId(), log.getDeletedBy().getFirstName()+" "+log.getDeletedBy().getLastName(), 
						log.getFolder(), log.getCellTitle(),log.getCategory(), log.getFileName(), log.getDeleteDate()));
			}
			return new TableDataPage(data, logPage.getTotalElements(), logPage.getTotalPages());
		} catch (Exception e) {
			e.printStackTrace();
			return new TableDataPage(new ArrayList<>(), 0l, 0);
		}
		
	}
	
	
	
}
