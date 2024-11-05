package com.PlayGroundAdv.Solar.service.ahj_library;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJACDisco;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJAddRemoveFromPlanset;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJAnchorSpacingRelatedRequirements;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJChecklistEntity;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJColumnsEntity;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJContacts;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJCustomFields;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJDesignCriteria;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJElectrical;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJFireSetbacksRoofMounted;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJGroundMountRelatedRequirementsOnly;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJGroundMountSetbacks;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJHowMany;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJLogEntity;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJPVMeter;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJPreApprovalReq;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJReqCustomPlanSetSheetsorSize;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJRevisionOrResubmittal;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJRoofMountRelatedRequirementsOnly;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJRoofVents;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJUsedByClients;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJWaterIntrusion;
import com.PlayGroundAdv.Solar.exception.ResourceNotFoundException;
import com.PlayGroundAdv.Solar.model.DropdownOptions;
import com.PlayGroundAdv.Solar.model.TableDataPage;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJACDiscoModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJAddRemoveFromPlansetModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJAnchorSpacingRelatedRequirementsModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJColumnsModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJContactsModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJCustomFieldsModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJDataModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJDesignCriteriaModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJElectricalModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJFilterList;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJFireSetbacksRoofMountedModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJGroundMountRelatedRequirementsModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJGroundMountSetbacksModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJHowManyModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJLastKnownPlanSetApproved;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJLibraryModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJPVMeterModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJPreApprovalReqModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJProjectDataModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJReqCustomPlaSetSheetorSizeModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJRevisionOrResubmittalModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJRoofMountRelatedRequirementOnlyModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJRoofVentsModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJUsedByClientsModel;
import com.PlayGroundAdv.Solar.model.ahj_library.AHJWaterIntrusionModel;
import com.PlayGroundAdv.Solar.model.ahj_library.GoverningCodesModel;
import com.PlayGroundAdv.Solar.model.ahj_library.PageModel;
import com.PlayGroundAdv.Solar.repositories.PathRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJACDiscoRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJAddRemoveFromPlansetRepository;
import com.PlayGroundAdv.Solar.repositories.ahj_library.AHJAnchorSpacingRelatedRequirementsRepository;
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
import com.PlayGroundAdv.Solar.repositories.ahj_library.CustomFieldsRepository;
import com.PlayGroundAdv.Solar.repositories.users.AuthenticationRepository;
import com.PlayGroundAdv.Solar.service.generate_planset.ahj.PlansetNotesService;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
public class AHJChecklistService {

	final AHJChecklistRepository aHJChecklistRepo;
	final AHJColumnsRepository ahjColumnsRepo;
	final AuthenticationRepository userRepo;
	final ModelMapper modelMapperAHJ;
	final PathRepository pathRepo;
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
	final AHJContactsRepository contactsRepo;
	final AHJUsedByClientsRepository usedByClientsRepo;
	final AHJLogService logService;
	final CheckValueTypesService checkValue;
	final PlansetNotesService plansetNotesService;
	final CustomFieldsRepository customFieldsRepo;
	final AHJLogRepository ahjLogRepo;
	public AHJChecklistService(AHJChecklistRepository aHJChecklistRepo, AHJColumnsRepository ahjColumnsRepo,
			AuthenticationRepository userRepo, ModelMapper modelMapperAHJ, PathRepository pathRepo,
			AHJACDiscoRepository acDiscoRepo, AHJAddRemoveFromPlansetRepository addRemoveFromPlansetRepo,
			AHJAnchorSpacingRelatedRequirementsRepository anchorSpacingRelatedRequirementsRepo,
			AHJDesignCriteriaRepository designCriteriaRepo, AHJElectricalRepository electricalRepo,
			AHJFireSetbacksRoofMountedRepository fireSetbacksRoofMountedRepo,
			AHJGroundMountRelatedRequirementsOnlyRepository groundMountRelatedRequirementsOnlyRepo,
			AHJGroundMountSetbacksRepository groundMountSetbacksRepo, AHJPVMeterRepository pvMeterRepo,
			AHJReqCustomPlanSetSheetsorSizeRepository reqCustomPlanSetSheetsorSizeRepo,
			AHJRoofMountRelatedRequirementsOnlyRepository roofMountRelatedRequirementsOnlyRepo,
			AHJRoofVentsRepository roofVentsRepo, AHJWaterIntrusionRepository waterIntrusionRepo,
			AHJHowManyRepository howManyRepo, AHJPreApprovalReqRepository preApprovalReqRepo,
			AHJRevisionOrResubmittalRepository revisionOrResubmittalRepo, AHJContactsRepository contactsRepoo,
			AHJUsedByClientsRepository usedByClientsRepo, AHJLogService logService, CheckValueTypesService checkValue,
			PlansetNotesService plansetNotesService, CustomFieldsRepository customFieldsRepo,
			AHJLogRepository ahjLogRepo) {
		super();
		this.aHJChecklistRepo = aHJChecklistRepo;
		this.ahjColumnsRepo = ahjColumnsRepo;
		this.userRepo = userRepo;
		this.modelMapperAHJ = modelMapperAHJ;
		this.pathRepo = pathRepo;
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
		this.contactsRepo = contactsRepoo;
		this.usedByClientsRepo = usedByClientsRepo;
		this.logService = logService;
		this.checkValue = checkValue;
		this.plansetNotesService = plansetNotesService;
		this.customFieldsRepo = customFieldsRepo;
		this.ahjLogRepo = ahjLogRepo;
	}

	public AHJFilterList getAHJFilterList() {
		try {
			return new AHJFilterList(aHJChecklistRepo.getAhjList(), 
					aHJChecklistRepo.getStateList(), aHJChecklistRepo.getCountyList());
		} catch (Exception e) {
			e.printStackTrace();
			return new AHJFilterList();
		}
	}
	
	public ResponseEntity<Object> addAHJ(AHJDataModel newAhj) {
		try {
			Boolean exist = aHJChecklistRepo.existsByAhjAndState(newAhj.getAhj(), newAhj.getState());
			if (Boolean.TRUE.equals(exist))
				return new ResponseEntity<>("Exist", HttpStatus.ALREADY_REPORTED);
			AuthentificationEntity by = userRepo.findById(newAhj.getMadeBy()).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" + newAhj.getMadeBy()));
			AHJChecklistEntity ahj = new AHJChecklistEntity(newAhj.getAhj().trim(), newAhj.getCounty() != null ? newAhj.getCounty().trim() : null, newAhj.getState(),
					newAhj.getIncorporated(), new Date(), new Date(), false, by, by,
					newAhj.getGoverningCodes().getIbc(), newAhj.getGoverningCodes().getIrc(),
					newAhj.getGoverningCodes().getIfc(), newAhj.getGoverningCodes().getNec(),
					newAhj.getGoverningCodes().getCbc(), newAhj.getGoverningCodes().getCec(),
					newAhj.getGoverningCodes().getCfc(), newAhj.getGoverningCodes().getCrc(),
					newAhj.getGoverningCodes().getCpc(), newAhj.getGoverningCodes().getCmc(),
					newAhj.getGoverningCodes().getCgbsc(),
					newAhj.getGoverningCodes().getImc(), newAhj.getGoverningCodes().getUmc(),
					newAhj.getGoverningCodes().getIpc(), newAhj.getGoverningCodes().getIecc(),
					newAhj.getGoverningCodes().getRequiredMunicipalSpecificCodeListed(),
					newAhj.getGoverningCodes().getIBCMustBeRemovedFromGoverningCodes(),
					newAhj.getGoverningCodes().getAhjRequiresIecc(),
					newAhj.getGoverningCodes().getBldgEnergyEffStandards(), newAhj.getGoverningCodes().getIebc(),
					newAhj.getGoverningCodes().getSpecialbbBuildingCodeNoteMustBeOnE003(),
					newAhj.getGoverningCodes().getNoteWithAllCodesListed(),newAhj.getGoverningCodes().getNotes());
			modelMapperAHJ.map(newAhj.getLastKnownPlanSetApproved(), ahj);
			aHJChecklistRepo.save(ahj);
			saveAHJData(newAhj, ahj, true, by);
			return new ResponseEntity<>(modelMapperAHJ.map(ahj, AHJLibraryModel.class), HttpStatus.ALREADY_REPORTED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.ALREADY_REPORTED);
		}
	}

	public ResponseEntity<Object> editAHJ(AHJDataModel ahjModel) {
		try {
			Boolean exist = aHJChecklistRepo.existsByAhjAndStateAndIdNot(ahjModel.getAhj(), ahjModel.getState(),
					ahjModel.getId());
			if (Boolean.TRUE.equals(exist))
				return new ResponseEntity<>("Exist", HttpStatus.ALREADY_REPORTED);
			AuthentificationEntity by = userRepo.findById(ahjModel.getMadeBy()).orElseThrow(
					() -> new ResourceNotFoundException("User not found for this id :" + ahjModel.getMadeBy()));
			AHJChecklistEntity ahj = aHJChecklistRepo.findById(ahjModel.getId())
					.orElseThrow(() -> new ResourceNotFoundException("AHJ not found for this id :" + ahjModel.getId()));
			logService.logUpdate(ahjModel, ahj, by);
			ahj.setAhj(ahjModel.getAhj().trim());
			ahj.setCounty(ahjModel.getCounty() != null ? ahjModel.getCounty().trim() : null);
			ahj.setState(ahjModel.getState());
			ahj.setIncorporated(ahjModel.getIncorporated());
			ahj.setLastUpdate(new Date());
			ahj.setLastUpdateBy(by);
			modelMapperAHJ.map(ahjModel.getGoverningCodes(), ahj);
			modelMapperAHJ.map(ahjModel.getLastKnownPlanSetApproved(), ahj);
			aHJChecklistRepo.save(ahj);
			saveAHJData(ahjModel, ahj, false, by);
			
			return new ResponseEntity<>(modelMapperAHJ.map(ahj, AHJLibraryModel.class), HttpStatus.ALREADY_REPORTED);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.ALREADY_REPORTED);
		}
	}

	public void saveAHJData(AHJDataModel ahjData, AHJChecklistEntity ahj, Boolean newInstance, AuthentificationEntity by) {
		try {

			AHJACDisco acDisco = Boolean.TRUE.equals(newInstance) ? new AHJACDisco(ahj)
					: acDiscoRepo.findByAhjId(ahj.getId());
			if (ahjData.getAcdisco() != null)
				modelMapperAHJ.map(ahjData.getAcdisco(), acDisco);
			acDiscoRepo.save(acDisco);

			AHJAddRemoveFromPlanset addRemoveFromPlanset = Boolean.TRUE.equals(newInstance)
					? new AHJAddRemoveFromPlanset(ahj)
					: addRemoveFromPlansetRepo.findByAhjId(ahj.getId());
			if (ahjData.getAddRemoveFromPlanset() != null)
				modelMapperAHJ.map(ahjData.getAddRemoveFromPlanset(), addRemoveFromPlanset);
			addRemoveFromPlansetRepo.save(addRemoveFromPlanset);

			AHJAnchorSpacingRelatedRequirements anchorSpacingRelatedRequirements = Boolean.TRUE.equals(newInstance)
					? new AHJAnchorSpacingRelatedRequirements(ahj)
					: anchorSpacingRelatedRequirementsRepo.findByAhjId(ahj.getId());
			if (ahjData.getAnchorSpacingRelatedRequirements() != null)
				modelMapperAHJ.map(ahjData.getAnchorSpacingRelatedRequirements(), anchorSpacingRelatedRequirements);
			anchorSpacingRelatedRequirementsRepo.save(anchorSpacingRelatedRequirements);

			AHJDesignCriteria designCriteria = Boolean.TRUE.equals(newInstance) ? new AHJDesignCriteria(ahj)
					: designCriteriaRepo.findByAhjId(ahj.getId());
			if (ahjData.getDesignCriteria() != null)
				modelMapperAHJ.map(ahjData.getDesignCriteria(), designCriteria);
			designCriteriaRepo.save(designCriteria);

			AHJElectrical electrical = Boolean.TRUE.equals(newInstance) ? new AHJElectrical(ahj)
					: electricalRepo.findByAhjId(ahj.getId());
			if (ahjData.getElectrical() != null)
				modelMapperAHJ.map(ahjData.getElectrical(), electrical);
			electricalRepo.save(electrical);

			AHJFireSetbacksRoofMounted fireSetbacksRoofMounted = Boolean.TRUE.equals(newInstance)
					? new AHJFireSetbacksRoofMounted(ahj)
					: fireSetbacksRoofMountedRepo.findByAhjId(ahj.getId());
			if (ahjData.getFireSetbacksRoofMounted() != null)
				modelMapperAHJ.map(ahjData.getFireSetbacksRoofMounted(), fireSetbacksRoofMounted);
			fireSetbacksRoofMountedRepo.save(fireSetbacksRoofMounted);

			AHJGroundMountRelatedRequirementsOnly groundMountRelatedRequirementsOnly = Boolean.TRUE.equals(newInstance)
					? new AHJGroundMountRelatedRequirementsOnly(ahj)
					: groundMountRelatedRequirementsOnlyRepo.findByAhjId(ahj.getId());
			if (ahjData.getFireSetbacksRoofMounted() != null)
				modelMapperAHJ.map(ahjData.getGroundMountRelatedRequirements(), groundMountRelatedRequirementsOnly);
			groundMountRelatedRequirementsOnlyRepo.save(groundMountRelatedRequirementsOnly);

			AHJGroundMountSetbacks groundMountSetbacks = Boolean.TRUE.equals(newInstance)
					? new AHJGroundMountSetbacks(ahj)
					: groundMountSetbacksRepo.findByAhjId(ahj.getId());
			if (ahjData.getGroundMountSetbacks() != null)
				modelMapperAHJ.map(ahjData.getGroundMountSetbacks(), groundMountSetbacks);
			groundMountSetbacksRepo.save(groundMountSetbacks);

			AHJPVMeter pvMeter = Boolean.TRUE.equals(newInstance) ? new AHJPVMeter(ahj)
					: pvMeterRepo.findByAhjId(ahj.getId());
			if (ahjData.getPvMeter() != null)
				modelMapperAHJ.map(ahjData.getPvMeter(), pvMeter);
			pvMeterRepo.save(pvMeter);

			AHJReqCustomPlanSetSheetsorSize reqCustomPlanSetSheetsorSize = Boolean.TRUE.equals(newInstance)
					? new AHJReqCustomPlanSetSheetsorSize(ahj)
					: reqCustomPlanSetSheetsorSizeRepo.findByAhjId(ahj.getId());
			if (ahjData.getReqCustomPlaSetSheetorSize() != null)
				modelMapperAHJ.map(ahjData.getReqCustomPlaSetSheetorSize(), reqCustomPlanSetSheetsorSize);
			reqCustomPlanSetSheetsorSizeRepo.save(reqCustomPlanSetSheetsorSize);

			AHJRoofMountRelatedRequirementsOnly roofMountRelatedRequirementsOnly = Boolean.TRUE.equals(newInstance)
					? new AHJRoofMountRelatedRequirementsOnly(ahj)
					: roofMountRelatedRequirementsOnlyRepo.findByAhjId(ahj.getId());
			if (ahjData.getRoofMountRelatedRequirementOnly() != null)
				modelMapperAHJ.map(ahjData.getRoofMountRelatedRequirementOnly(), roofMountRelatedRequirementsOnly);
			roofMountRelatedRequirementsOnlyRepo.save(roofMountRelatedRequirementsOnly);

			AHJRoofVents roofVents = Boolean.TRUE.equals(newInstance) ? new AHJRoofVents(ahj)
					: roofVentsRepo.findByAhjId(ahj.getId());
			if (ahjData.getRoofVents() != null)
				modelMapperAHJ.map(ahjData.getRoofVents(), roofVents);
			roofVentsRepo.save(roofVents);

			AHJWaterIntrusion waterIntrusion = Boolean.TRUE.equals(newInstance) ? new AHJWaterIntrusion(ahj)
					: waterIntrusionRepo.findByAhjId(ahj.getId());
			if (ahjData.getWaterIntrusion() != null)
				modelMapperAHJ.map(ahjData.getWaterIntrusion(), waterIntrusion);
			waterIntrusionRepo.save(waterIntrusion);

			AHJHowMany howMany = Boolean.TRUE.equals(newInstance) ? new AHJHowMany(ahj)
					: howManyRepo.findByAhjId(ahj.getId());
			if (ahjData.getHowMany() != null)
				modelMapperAHJ.map(ahjData.getHowMany(), howMany);
			howManyRepo.save(howMany);
			
			AHJPreApprovalReq preApprovalReq = Boolean.TRUE.equals(newInstance) ? new AHJPreApprovalReq(ahj)
					: preApprovalReqRepo.findByAhjId(ahj.getId());
			if (ahjData.getPreApprovalReq() != null)
				modelMapperAHJ.map(ahjData.getPreApprovalReq(), preApprovalReq);
			preApprovalReqRepo.save(preApprovalReq);
			
			AHJRevisionOrResubmittal revisionOrResubmittal = Boolean.TRUE.equals(newInstance) ? new AHJRevisionOrResubmittal(ahj)
					: revisionOrResubmittalRepo.findByAhjId(ahj.getId());
			if (ahjData.getRevisionOrResubmittal() != null)
				modelMapperAHJ.map(ahjData.getRevisionOrResubmittal(), revisionOrResubmittal);
			revisionOrResubmittalRepo.save(revisionOrResubmittal);
			
			AHJContacts contacts = Boolean.TRUE.equals(newInstance) ? new AHJContacts(ahj)
					: contactsRepo.findByAhjId(ahj.getId());
			if (ahjData.getContacts() != null)
				modelMapperAHJ.map(ahjData.getContacts(),contacts);
			contactsRepo.save(contacts);
			
			AHJUsedByClients usedByClients = Boolean.TRUE.equals(newInstance) ? new AHJUsedByClients(ahj)
					: usedByClientsRepo.findByAhjId(ahj.getId());
			if (ahjData.getUsedByClients() != null)
				modelMapperAHJ.map(ahjData.getUsedByClients(), usedByClients);
			usedByClientsRepo.save(usedByClients);
			saveCustomFields(ahjData.getCustomFields(), ahj.getId(), by);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void saveCustomFields(List<AHJCustomFieldsModel> list, Long ahjId, AuthentificationEntity by) {
		try {
			for (AHJCustomFieldsModel field : list) {
				AHJCustomFields col = customFieldsRepo.findByColumnsColumnNameAndAhjId(field.getColumn(),ahjId);
				if (col == null) {
					AHJChecklistEntity ahj = aHJChecklistRepo.findById(ahjId)
							.orElseThrow(() -> new ResourceNotFoundException("AHJ not found for this id :" + ahjId));
					AHJColumnsEntity column = ahjColumnsRepo.findByColumnName(field.getColumn());
					col = new AHJCustomFields(ahj, column, field.getText());
					ahjLogRepo.save(new AHJLogEntity(ahj, by, new Date(),column.getCategory(), column.getColumnTitle(), null, field.getText(), false, null));
				}else {
					if (checkValue.NotEquals(field.getText(), col.getText())) {
						ahjLogRepo.save(new AHJLogEntity(col.getAhj(), by, new Date(),col.getColumns().getCategory(), col.getColumns().getColumnTitle(), col.getText(), field.getText(), false, null));
					}
					col.setText(field.getText());
				}
				customFieldsRepo.save(col);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Boolean deleteAHJ(Long ahjId, Long userId) {
		try {
			AHJChecklistEntity ahj = aHJChecklistRepo.findById(ahjId)
					.orElseThrow(() -> new ResourceNotFoundException("AHJ not found for this id :" + ahjId));
			if (ahj != null) {
				ahj.setIsDeleted(true);
				ahj.setDeletedBy(getUserById(userId));
				ahj.setDeletedon(new Date());
				aHJChecklistRepo.save(ahj);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public TableDataPage getAHJList(PageModel page) {
		try {
			Page<AHJChecklistEntity> ahjList = getAHJPager(page);
			if (ahjList != null) {
				List<Object> data = new ArrayList<>();
				for (AHJChecklistEntity ahjChecklistEntity : ahjList.getContent()) {
					data.add(modelMapperAHJ.map(ahjChecklistEntity, AHJLibraryModel.class));
				}
				return new TableDataPage(data, ahjList.getTotalElements(), ahjList.getTotalPages());
				
			}else return new TableDataPage(new ArrayList<>(), 0l, 0);
			
		} catch (Exception e) {
			e.printStackTrace();
			return new TableDataPage(new ArrayList<>(), 0l, 0);
		}
	}
	
	private Page<AHJChecklistEntity> getAHJPager(PageModel page){
		try {
			Pageable pageable = PageRequest.of(page.getPage(), page.getSize(), Sort.by("ahj").and(Sort.by("county")));
			if (!checkValue.isStringNotEmpty(page.getAhj())) {
				if (!checkValue.isStringNotEmpty(page.getState())) {
					return aHJChecklistRepo.findByCountyOrCountyIsNullAndAhj(page.getCounty(), page.getCounty(), pageable);
				}else if(!checkValue.isStringNotEmpty(page.getCounty())) {
					return aHJChecklistRepo.findByState(page.getState(), pageable);
				}else return aHJChecklistRepo.findByStateAndCountyOrStateAndCountyIsNullAndAhj(page.getState(), page.getCounty(), page.getState(), page.getCounty(), pageable);
				
			}else if(!checkValue.isStringNotEmpty(page.getState())){
				if (!checkValue.isStringNotEmpty(page.getAhj())) {
					return aHJChecklistRepo.findByCountyOrCountyIsNullAndAhj(page.getCounty(),page.getCounty(), pageable);
				}else if(!checkValue.isStringNotEmpty(page.getCounty())) {
					return aHJChecklistRepo.findByAhj(page.getAhj(), pageable);
				}else return aHJChecklistRepo.findByAhjAndCountyOrAhjAndCountyIsNullAndAhj(page.getAhj(), page.getCounty(), page.getAhj(), page.getCounty(), pageable);
				
			}else if(!checkValue.isStringNotEmpty(page.getCounty())) {
				if (!checkValue.isStringNotEmpty(page.getState())) {
					return aHJChecklistRepo.findByAhj(page.getAhj(), pageable);
				}else if(!checkValue.isStringNotEmpty(page.getAhj())) {
					return aHJChecklistRepo.findByState(page.getState(), pageable);
				}else return aHJChecklistRepo.findByStateAndAhj(page.getState(), page.getAhj(), pageable);
				
			}else return aHJChecklistRepo.findByStateAndAhjAndCountyOrStateAndAhjAndCountyIsNullAndAhj(page.getState(), page.getAhj(), page.getCounty(), page.getState(), page.getAhj(), page.getCounty(), pageable);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public AHJDataModel getAHJ(Long ahjId) {
		try {
			AHJChecklistEntity ahj = aHJChecklistRepo.findById(ahjId)
					.orElseThrow(() -> new ResourceNotFoundException("AHJ not found for this id :" + ahjId));

			AHJDataModel ahjModel = modelMapperAHJ.map(ahj, AHJDataModel.class);
			ahjModel.setUpdatedBy(ahj.getLastUpdateBy() != null
					? ahj.getLastUpdateBy().getFirstName() + " " + ahj.getLastUpdateBy().getLastName()
					: "");
			ahjModel.setGoverningCodes(modelMapperAHJ.map(ahj, GoverningCodesModel.class));
			ahjModel.setLastKnownPlanSetApproved(modelMapperAHJ.map(ahj, AHJLastKnownPlanSetApproved.class));
			ahjModel.setAcdisco(modelMapperAHJ.map(acDiscoRepo.findByAhjId(ahjId), AHJACDiscoModel.class));
			ahjModel.setAddRemoveFromPlanset(
					modelMapperAHJ.map(addRemoveFromPlansetRepo.findByAhjId(ahjId), AHJAddRemoveFromPlansetModel.class));
			ahjModel.setAnchorSpacingRelatedRequirements(
					modelMapperAHJ.map(anchorSpacingRelatedRequirementsRepo.findByAhjId(ahjId),
							AHJAnchorSpacingRelatedRequirementsModel.class));
			ahjModel.setDesignCriteria(
					modelMapperAHJ.map(designCriteriaRepo.findByAhjId(ahjId), AHJDesignCriteriaModel.class));
			ahjModel.setElectrical(modelMapperAHJ.map(electricalRepo.findByAhjId(ahjId), AHJElectricalModel.class));
			ahjModel.setFireSetbacksRoofMounted(modelMapperAHJ.map(fireSetbacksRoofMountedRepo.findByAhjId(ahjId),
					AHJFireSetbacksRoofMountedModel.class));
			ahjModel.setGroundMountRelatedRequirements(
					modelMapperAHJ.map(groundMountRelatedRequirementsOnlyRepo.findByAhjId(ahjId),
							AHJGroundMountRelatedRequirementsModel.class));
			ahjModel.setGroundMountSetbacks(
					modelMapperAHJ.map(groundMountSetbacksRepo.findByAhjId(ahjId), AHJGroundMountSetbacksModel.class));
			ahjModel.setPvMeter(modelMapperAHJ.map(pvMeterRepo.findByAhjId(ahjId), AHJPVMeterModel.class));
			ahjModel.setReqCustomPlaSetSheetorSize(modelMapperAHJ.map(reqCustomPlanSetSheetsorSizeRepo.findByAhjId(ahjId),
					AHJReqCustomPlaSetSheetorSizeModel.class));
			ahjModel.setRoofMountRelatedRequirementOnly(
					modelMapperAHJ.map(roofMountRelatedRequirementsOnlyRepo.findByAhjId(ahjId),
							AHJRoofMountRelatedRequirementOnlyModel.class));
			ahjModel.setRoofVents(modelMapperAHJ.map(roofVentsRepo.findByAhjId(ahjId), AHJRoofVentsModel.class));
			ahjModel.setWaterIntrusion(
					modelMapperAHJ.map(waterIntrusionRepo.findByAhjId(ahjId), AHJWaterIntrusionModel.class));
			ahjModel.setHowMany(
					modelMapperAHJ.map(howManyRepo.findByAhjId(ahjId), AHJHowManyModel.class));
			ahjModel.setPreApprovalReq(
					modelMapperAHJ.map(preApprovalReqRepo.findByAhjId(ahjId), AHJPreApprovalReqModel.class));
			ahjModel.setRevisionOrResubmittal(
					modelMapperAHJ.map(revisionOrResubmittalRepo.findByAhjId(ahjId), AHJRevisionOrResubmittalModel.class));
			ahjModel.setContacts(
					modelMapperAHJ.map(contactsRepo.findByAhjId(ahjId), AHJContactsModel.class));
			ahjModel.setUsedByClients(
					modelMapperAHJ.map(usedByClientsRepo.findByAhjId(ahjId), AHJUsedByClientsModel.class));
			ahjModel.setCustomFields(customFieldsRepo.findColumnByAHJ(ahjId));
			return ahjModel;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}


	public Boolean getGoverningCodes(Long ahjId, Long userId) {
		try {
			AHJChecklistEntity ahj = aHJChecklistRepo.findById(ahjId)
					.orElseThrow(() -> new ResourceNotFoundException("AHJ not found for this id :" + ahjId));
			if (ahj != null) {
				ahj.setIsDeleted(true);
				ahj.setDeletedBy(getUserById(userId));
				ahj.setDeletedon(new Date());
				aHJChecklistRepo.save(ahj);
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<AHJColumnsModel> getAHJColumns(Long ahjId) {
		try {
			//A.B Get column from DB where all has attachment equals No by default
			List<AHJColumnsEntity> columns = ahjColumnsRepo.findAll(Sort.by(Sort.Direction.ASC, "custom").and(Sort.by(Sort.Direction.ASC, "columnTitle")));
			List<AHJColumnsModel> cols = new ArrayList<AHJColumnsModel>();

			for (AHJColumnsEntity col : columns) {
				List<DropdownOptions> options = new ArrayList<DropdownOptions>();
				if (col.getColumnType().equals("select")) {
					for (String op : col.getOptions()) {
						options.add(new DropdownOptions(op, op));
					}
				}
				//A.B Check if cell has attachment
				if (ahjId > 0) {
					String pathToFile = pathRepo.findFilePath() + "AHJ Attachement/" + ahjId + "/" + col.getId() + "/";
					Path path = Paths.get(pathToFile);
					if (new File(pathToFile).exists() && !isDirEmpty(path)) {
						cols.add(new AHJColumnsModel(col.getId(), col.getColumnName(), col.getColumnTitle(),
								col.getHeaderName(), col.getSubheaderName(), col.getColumnType(), col.getCategory(),
								options, true, col.getCustom()));
					} else {
						cols.add(new AHJColumnsModel(col.getId(), col.getColumnName(), col.getColumnTitle(),
								col.getHeaderName(), col.getSubheaderName(), col.getColumnType(), col.getCategory(),
								options, false, col.getCustom()));
					}
				} else {
					cols.add(new AHJColumnsModel(col.getId(), col.getColumnName(), col.getColumnTitle(),
							col.getHeaderName(), col.getSubheaderName(), col.getColumnType(), col.getCategory(),
							options, false, col.getCustom()));
				}

			}

			return cols;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public String addCustomColumn(AHJColumnsModel column) {
		try {
			Boolean exist = ahjColumnsRepo.existsByColumnTitleAndHeaderNameAndSubheaderName(column.getColumnTitle(), column.getHeaderName(), column.getSubheaderName());
			if (Boolean.TRUE.equals(exist)) {
				return "Column already exists under this category.";
			}
			AHJColumnsEntity c = new AHJColumnsEntity(column.getColumnTitle(), column.getHeaderName(), column.getCategory(), column.getSubheaderName());
			ahjColumnsRepo.save(c);
			c.setColumnName("custom-"+c.getId());
			ahjColumnsRepo.save(c);

			return "Done";
		} catch (Exception e) {
			e.printStackTrace();
			return "Technical Problem";
		}
	}

	public AHJProjectDataModel getProjectAHJData(String[] d) {
		try {
			Long ahjId = plansetNotesService.getAHJId(d[0], d[1], d[2], d[3], d[4]);
			AHJProjectDataModel ahjData = new AHJProjectDataModel();
			if (ahjId != null) {
				AHJACDisco ac= acDiscoRepo.findByAhjId(ahjId);
				AHJDesignCriteria designCriteria = designCriteriaRepo.findByAhjId(ahjId);
				AHJPVMeter pvMeter = pvMeterRepo.findByAhjId(ahjId);
				AHJElectrical electrical = electricalRepo.findByAhjId(ahjId);
				ahjData.setAsceStandard(designCriteria.getAsceStandard());
				ahjData.setWindSpeed(designCriteria.getWindSpeed());
				ahjData.setSnowLoad(designCriteria.getSnowLoad());
				ahjData.setExposureCategory(designCriteria.getExposureCategory());
				ahjData.setElevation(designCriteria.getElevation());
				ahjData.setHighTemp04(designCriteria.getHighTemp04());
				ahjData.setHighTemp02(designCriteria.getHighTemp02());
				ahjData.setExtremeMinimum(designCriteria.getExtremeMinimum());
				ahjData.setACDisconnectRequired(ac.getAcdisconnectRequired());
				ahjData.setIsPVMeterRequired(pvMeter.getIsPVMeterRequired());
				ahjData.setPvMeterLocationFromLtToRt(pvMeter.getPvMeterLocationFromLtToRt());
				ahjData.setReqElecEngStampWhenSizeIs(electrical.getReqElecEngStampWhenSizeIs());
				ahjData.setReqElectEngStampForGroundMountSystems(electrical.getReqElectEngStampForGroundMountSystems());
				return ahjData;
			}else return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	private AuthentificationEntity getUserById(Long userId) {
		try {
			return userRepo.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :" + userId));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static boolean isDirEmpty(final Path directory) throws IOException {
		try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory)) {
			return !dirStream.iterator().hasNext();
		}
	}

	

}
