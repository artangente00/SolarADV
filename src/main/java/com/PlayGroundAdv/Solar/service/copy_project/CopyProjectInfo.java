package com.PlayGroundAdv.Solar.service.copy_project;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.NotAllowedRackingNotes;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.repositories.NotAllowedRackingNotesRepository;
import com.PlayGroundAdv.Solar.repositories.PermitProjectSiteInfoRepository;
import com.PlayGroundAdv.Solar.service.project.ProjectNotAllowedRackingNote;
import com.PlayGroundAdv.Solar.service.utils.CheckValueTypesService;

@Service
@Transactional
public class CopyProjectInfo {

	final PermitProjectSiteInfoRepository projectSiteInfoRepo;
	final CopyBOSEquipmentFavorite copyBOSEquFav;
	final CheckValueTypesService checkValue;
	final NotAllowedRackingNotesRepository rackingNotesRepo;
	final ProjectNotAllowedRackingNote notAllowedRackingService;

	public CopyProjectInfo(PermitProjectSiteInfoRepository projectSiteInfoRepo, CopyBOSEquipmentFavorite copyBOSEquFav,
			CheckValueTypesService checkValue, NotAllowedRackingNotesRepository rackingNotesRepo,
			ProjectNotAllowedRackingNote notAllowedRackingService) {
		super();
		this.projectSiteInfoRepo = projectSiteInfoRepo;
		this.copyBOSEquFav = copyBOSEquFav;
		this.checkValue = checkValue;
		this.rackingNotesRepo = rackingNotesRepo;
		this.notAllowedRackingService = notAllowedRackingService;
	}

	public void copyProjectInfo(Long idPermit, Long idNewPermit, AuthentificationEntity user) {
		try {
			PermitProjectSiteInfoEntity projectSiteInfoEntity = projectSiteInfoRepo.findByPermitEntityId(idPermit);
			PermitProjectSiteInfoEntity newProjectSiteInfoEntity = projectSiteInfoRepo
					.findByPermitEntityId(idNewPermit);
			newProjectSiteInfoEntity = clonePermitProjectSiteInfoEntity(newProjectSiteInfoEntity, projectSiteInfoEntity);
			projectSiteInfoRepo.save(newProjectSiteInfoEntity);
			
			NotAllowedRackingNotes projectNotes = rackingNotesRepo.findByProjectId(idPermit);
			if (projectNotes != null) {
				notAllowedRackingService.insertRackingNotetoADV(projectNotes.getNotAllowedRoofRackingNote(), idNewPermit, projectNotes.getNotAllowedRoofRackingFileName());
				if (Boolean.TRUE.equals(projectNotes.getHasRoofRackingNote())) {
					notAllowedRackingService.copyRackingNoteFile(idNewPermit, idPermit);
				}
			}

			copyBOSEquFav.copyRailRackingFav(projectSiteInfoEntity.getRailRakingModel(), user);
			copyBOSEquFav.copyRailRackingFav(projectSiteInfoEntity.getRailRakingModelforGroundMounted(), user);
			copyBOSEquFav.copyRailRackingFav(projectSiteInfoEntity.getRailRakingforCarport(), user);
			copyBOSEquFav.copyRailRackingFav(projectSiteInfoEntity.getRailRakingforPatioMounted(), user);
			copyBOSEquFav.copyDCDFav(projectSiteInfoEntity.getDisconnectModel(), user);
			copyBOSEquFav.copyDCDFav(projectSiteInfoEntity.getDisconnectModelTwo(), user);
			copyBOSEquFav.copyDCDFav(projectSiteInfoEntity.getDisconnectModelThree(), user);
			copyBOSEquFav.copyDCDFav(projectSiteInfoEntity.getRoofTopDCCombiner(), user);
			copyBOSEquFav.copyDCDFav(projectSiteInfoEntity.getRoofTopDCDisco(), user);
			copyBOSEquFav.copyACCFav(projectSiteInfoEntity.getACCombinerInstalled(), user);
			copyBOSEquFav.copyACCFav(projectSiteInfoEntity.getGroundLevelACCombinerBoxModel(), user);
			copyBOSEquFav.copyACCFav(projectSiteInfoEntity.getRoofTopACCombiner(), user);

			if (checkValue.isNumericNotZero(projectSiteInfoEntity.getRailConnectionModel())) {
				copyBOSEquFav.copyRoofAttachementFav(Long.valueOf(projectSiteInfoEntity.getRailConnectionModel()),
						user);
			}

			if (checkValue.isNumericNotZero(projectSiteInfoEntity.getDcJboxType())) {
				copyBOSEquFav.copyJBFav(Long.valueOf(projectSiteInfoEntity.getDcJboxType()), user);
			}
			if (checkValue.isNumericNotZero(projectSiteInfoEntity.getRoofTopJboxDC())) {
				copyBOSEquFav.copyJBFav(Long.valueOf(projectSiteInfoEntity.getRoofTopJboxDC()), user);
			}
			if (checkValue.isNumericNotZero(projectSiteInfoEntity.getRoofTopJbox())) {
				copyBOSEquFav.copyJBFav(Long.valueOf(projectSiteInfoEntity.getRoofTopJbox()), user);
			}
			if (checkValue.contains(projectSiteInfoEntity.getRooftopACCombinerModel(), ":")) {
				copyBOSEquFav.copyACDFav(projectSiteInfoEntity.getRooftopACCombinerModel(), user);
			}
			if (checkValue.contains(projectSiteInfoEntity.getRooftopACCombinerModelTwo(), ":")) {
				copyBOSEquFav.copyACDFav(projectSiteInfoEntity.getRooftopACCombinerModelTwo(), user);
				
				if (projectSiteInfoEntity.getAcDisconnectThree() != null) {
					copyBOSEquFav.copyACDFavById(projectSiteInfoEntity.getAcDisconnectThree().getId(), user);

					if (projectSiteInfoEntity.getAcDisconnectFour() != null) {
						copyBOSEquFav.copyACDFavById(projectSiteInfoEntity.getAcDisconnectFour().getId(), user);
					}
				}
			}
			if (checkValue.contains(projectSiteInfoEntity.getRoofTopACDisco(), ":")) {
				copyBOSEquFav.copyACDFav(projectSiteInfoEntity.getRoofTopACDisco(), user);
			}
			

			if (checkValue.isNumericNotZero(projectSiteInfoEntity.getFlashing())) {
				copyBOSEquFav.copyFlashingFav(Long.valueOf(projectSiteInfoEntity.getFlashing()), user);
			}

			if (checkValue.isNumericNotZero(projectSiteInfoEntity.getLeasePPAMeter())) {
				copyBOSEquFav.copyLeasePPAMeter(Long.valueOf(projectSiteInfoEntity.getLeasePPAMeter()), user);
			}

			if (checkValue.isNumericNotZero(projectSiteInfoEntity.getProposedSubPanel())) {
				copyBOSEquFav.copyProposedSubPanel(Long.valueOf(projectSiteInfoEntity.getProposedSubPanel()), user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PermitProjectSiteInfoEntity clonePermitProjectSiteInfoEntity(
			PermitProjectSiteInfoEntity newPermitProjectSiteInfoEntity,
			PermitProjectSiteInfoEntity permitProjectSiteInfoEntity) {

		if (newPermitProjectSiteInfoEntity != null && permitProjectSiteInfoEntity != null) {
			newPermitProjectSiteInfoEntity.setRailRakingModel(permitProjectSiteInfoEntity.getRailRakingModel());
			newPermitProjectSiteInfoEntity
					.setQtyIndependentACDisco(permitProjectSiteInfoEntity.getQtyIndependentACDisco());
			newPermitProjectSiteInfoEntity
					.setTrackingSystemManufacturer(permitProjectSiteInfoEntity.getTrackingSystemManufacturer());
			newPermitProjectSiteInfoEntity.setTrackingSystemModel(permitProjectSiteInfoEntity.getTrackingSystemModel());
			newPermitProjectSiteInfoEntity.setInverterModel(permitProjectSiteInfoEntity.getInverterModel());
		
			newPermitProjectSiteInfoEntity
					.setRafterTrussSpacing(permitProjectSiteInfoEntity.getRafterTrussSpacing());
			newPermitProjectSiteInfoEntity.setCrossSectionSize(permitProjectSiteInfoEntity.getCrossSectionSize());
			newPermitProjectSiteInfoEntity
					.setSpanBetweenAttachment(permitProjectSiteInfoEntity.getSpanBetweenAttachment());
			newPermitProjectSiteInfoEntity.setRoofMaterialType(permitProjectSiteInfoEntity.getRoofMaterialType());
			newPermitProjectSiteInfoEntity.setTextOtherSize(permitProjectSiteInfoEntity.getTextOtherSize());
			newPermitProjectSiteInfoEntity.setTextOtherRatfter(permitProjectSiteInfoEntity.getTextOtherRatfter());
			newPermitProjectSiteInfoEntity.setTallStructure(permitProjectSiteInfoEntity.getTallStructure());
			newPermitProjectSiteInfoEntity
					.setOtherTallStructure(permitProjectSiteInfoEntity.getOtherTallStructure());
			newPermitProjectSiteInfoEntity.setMeanHeight(permitProjectSiteInfoEntity.getMeanHeight());
			
			newPermitProjectSiteInfoEntity
					.setRoofMaterialTypeOther(permitProjectSiteInfoEntity.getRoofMaterialTypeOther());
			newPermitProjectSiteInfoEntity.setFlashing(permitProjectSiteInfoEntity.getFlashing());
			newPermitProjectSiteInfoEntity.setModuleGrounding(permitProjectSiteInfoEntity.getModuleGrounding());
			newPermitProjectSiteInfoEntity
					.setDisconnectManufacturer(permitProjectSiteInfoEntity.getDisconnectManufacturer());
			newPermitProjectSiteInfoEntity.setDisconnectModel(permitProjectSiteInfoEntity.getDisconnectModel());
			newPermitProjectSiteInfoEntity.setQuantityRooftop(permitProjectSiteInfoEntity.getQuantityRooftop());
			newPermitProjectSiteInfoEntity.setSolarLocation(permitProjectSiteInfoEntity.getSolarLocation());
			newPermitProjectSiteInfoEntity.setMainPanelUpgrade(permitProjectSiteInfoEntity.getMainPanelUpgrade());
			newPermitProjectSiteInfoEntity.setPanelBusRating(permitProjectSiteInfoEntity.getPanelBusRating());
			newPermitProjectSiteInfoEntity
					.setSolarInterconnection(permitProjectSiteInfoEntity.getSolarInterconnection());
			newPermitProjectSiteInfoEntity
					.setSecondSolarInterconnection(permitProjectSiteInfoEntity.getSecondSolarInterconnection());
			newPermitProjectSiteInfoEntity.setUseDisconectSwith(permitProjectSiteInfoEntity.getUseDisconectSwith());
			newPermitProjectSiteInfoEntity
					.setUsedByInverterManufacturer(permitProjectSiteInfoEntity.getUsedByInverterManufacturer());
			newPermitProjectSiteInfoEntity.setUsedRevenue(permitProjectSiteInfoEntity.getUsedRevenue());
			newPermitProjectSiteInfoEntity.setSubPanelBusRating(permitProjectSiteInfoEntity.getSubPanelBusRating());
			newPermitProjectSiteInfoEntity
					.setSubPanelMainBreakerRating(permitProjectSiteInfoEntity.getSubPanelMainBreakerRating());
			newPermitProjectSiteInfoEntity
					.setPanelExistingProposed(permitProjectSiteInfoEntity.getPanelExistingProposed());
			newPermitProjectSiteInfoEntity.setTrackingSystemManufacturerOther(
					permitProjectSiteInfoEntity.getTrackingSystemManufacturerOther());
			newPermitProjectSiteInfoEntity
					.setTrackingSystemModelOther(permitProjectSiteInfoEntity.getTrackingSystemModelOther());
			newPermitProjectSiteInfoEntity
					.setRooftopACCombinerModel(permitProjectSiteInfoEntity.getRooftopACCombinerModel());
			newPermitProjectSiteInfoEntity.setRoofTopDcJboxType(permitProjectSiteInfoEntity.getRoofTopDcJboxType());
			newPermitProjectSiteInfoEntity
					.setInstallRoofTopACDiscoCombiner(permitProjectSiteInfoEntity.getInstallRoofTopACDiscoCombiner());
			newPermitProjectSiteInfoEntity
					.setRoofTopACCombinerDisconnect(permitProjectSiteInfoEntity.getRoofTopACCombinerDisconnect());
			newPermitProjectSiteInfoEntity
					.setACDisconnectSwitchManufacturer(permitProjectSiteInfoEntity.getACDisconnectSwitchManufacturer());
			newPermitProjectSiteInfoEntity
					.setACDisconnectSwitchModel(permitProjectSiteInfoEntity.getACDisconnectSwitchModel());
			newPermitProjectSiteInfoEntity.setACDisconnectSwitchManufacturerOther(
					permitProjectSiteInfoEntity.getACDisconnectSwitchManufacturerOther());
			newPermitProjectSiteInfoEntity
					.setACDisconnectSwitchModelOther(permitProjectSiteInfoEntity.getACDisconnectSwitchModelOther());
			newPermitProjectSiteInfoEntity
					.setDCDisconnectSwitchManufacturer(permitProjectSiteInfoEntity.getDCDisconnectSwitchManufacturer());
			newPermitProjectSiteInfoEntity
					.setDCDisconnectSwitchModel(permitProjectSiteInfoEntity.getDCDisconnectSwitchModel());
			newPermitProjectSiteInfoEntity.setLeasePPAMeter(permitProjectSiteInfoEntity.getLeasePPAMeter());
			newPermitProjectSiteInfoEntity
					.setQuantityofCombinerBox(permitProjectSiteInfoEntity.getQuantityofCombinerBox());
			newPermitProjectSiteInfoEntity
					.setQuantityofCombinerBoxOther(permitProjectSiteInfoEntity.getQuantityofCombinerBoxOther());
			newPermitProjectSiteInfoEntity.setTrackingSystemManufacturerForSecondTracker(
					permitProjectSiteInfoEntity.getTrackingSystemManufacturerForSecondTracker());
			newPermitProjectSiteInfoEntity.setTrackingSystemManufacturerForSecondTrackerOther(
					permitProjectSiteInfoEntity.getTrackingSystemManufacturerForSecondTrackerOther());
			newPermitProjectSiteInfoEntity.setTrackingSystemModelForSecondTracker(
					permitProjectSiteInfoEntity.getTrackingSystemModelForSecondTracker());
			newPermitProjectSiteInfoEntity.setTrackingSystemModelForSecondTrackerOther(
					permitProjectSiteInfoEntity.getTrackingSystemModelForSecondTrackerOther());
			newPermitProjectSiteInfoEntity
					.setRankingRoofManufacturerOther(permitProjectSiteInfoEntity.getRankingRoofManufacturerOther());
			newPermitProjectSiteInfoEntity
					.setRankingRoofModelOther(permitProjectSiteInfoEntity.getRankingRoofModelOther());
			newPermitProjectSiteInfoEntity
					.setModuleGroundingOther(permitProjectSiteInfoEntity.getModuleGroundingOther());
			newPermitProjectSiteInfoEntity
					.setDisconnectManufacturerOther(permitProjectSiteInfoEntity.getDisconnectManufacturerOther());
			newPermitProjectSiteInfoEntity
					.setDisconnectModelOther(permitProjectSiteInfoEntity.getDisconnectModelOther());
			newPermitProjectSiteInfoEntity.setRailConnectionModel(permitProjectSiteInfoEntity.getRailConnectionModel());
			newPermitProjectSiteInfoEntity
					.setAtticJBoxesbeUtilized(permitProjectSiteInfoEntity.getAtticJBoxesbeUtilized());
			newPermitProjectSiteInfoEntity
					.setDescriptionOfBackFeed(permitProjectSiteInfoEntity.getDescriptionOfBackFeed());
			newPermitProjectSiteInfoEntity.setGroundLevelACDisconnectEnclosure(
					permitProjectSiteInfoEntity.getGroundLevelACDisconnectEnclosure());
			newPermitProjectSiteInfoEntity.setPanelBusRatingOther(permitProjectSiteInfoEntity.getPanelBusRatingOther());
			newPermitProjectSiteInfoEntity
					.setPanelMainBreakerRating(permitProjectSiteInfoEntity.getPanelMainBreakerRating());
			newPermitProjectSiteInfoEntity
					.setPanelMainBreakerRatingOther(permitProjectSiteInfoEntity.getPanelMainBreakerRatingOther());
			newPermitProjectSiteInfoEntity
					.setSolarInterconnectionOther(permitProjectSiteInfoEntity.getSolarInterconnectionOther());
			newPermitProjectSiteInfoEntity.setSecondSolarInterconnectionOther(
					permitProjectSiteInfoEntity.getSecondSolarInterconnectionOther());
			newPermitProjectSiteInfoEntity.setCombiningACCircuits(permitProjectSiteInfoEntity.getCombiningACCircuits());
			newPermitProjectSiteInfoEntity
					.setMorInterconnectingBackFeed(permitProjectSiteInfoEntity.getMorInterconnectingBackFeed());
			newPermitProjectSiteInfoEntity.setRailRakingModelforGroundMounted(
					permitProjectSiteInfoEntity.getRailRakingModelforGroundMounted());
			newPermitProjectSiteInfoEntity.setRailRakingforCarport(permitProjectSiteInfoEntity.getRailRakingforCarport());
			newPermitProjectSiteInfoEntity.setRailRakingforPatioMounted(permitProjectSiteInfoEntity.getRailRakingforPatioMounted());
			newPermitProjectSiteInfoEntity
					.setSizeAndTypeAtticJBox(permitProjectSiteInfoEntity.getSizeAndTypeAtticJBox());
			newPermitProjectSiteInfoEntity
					.setSizeAndTypeAtticJBoxOther(permitProjectSiteInfoEntity.getSizeAndTypeAtticJBoxOther());
			newPermitProjectSiteInfoEntity.setIfApplicableSubPanelMainBreakerRating(
					permitProjectSiteInfoEntity.getIfApplicableSubPanelMainBreakerRating());
			newPermitProjectSiteInfoEntity.setProposedSubPanel(permitProjectSiteInfoEntity.getProposedSubPanel());
			newPermitProjectSiteInfoEntity
					.setGroundLevelACCombinerBoxModel(permitProjectSiteInfoEntity.getGroundLevelACCombinerBoxModel());
			newPermitProjectSiteInfoEntity.setGroundLevelACCombinerDisconnectModel(
					permitProjectSiteInfoEntity.getGroundLevelACCombinerDisconnectModel());
			newPermitProjectSiteInfoEntity.setGroundLevelACJunctionBoxManufacturer(
					permitProjectSiteInfoEntity.getGroundLevelACJunctionBoxManufacturer());
			newPermitProjectSiteInfoEntity
					.setGroundLevelACJunctionBoxModel(permitProjectSiteInfoEntity.getGroundLevelACJunctionBoxModel());
			newPermitProjectSiteInfoEntity.setEquipmentRoofMountedACCombinerBox(
					permitProjectSiteInfoEntity.getEquipmentRoofMountedACCombinerBox());
			newPermitProjectSiteInfoEntity.setEquipmentRoofMountedACCombinerDisconnect(
					permitProjectSiteInfoEntity.getEquipmentRoofMountedACCombinerDisconnect());
			newPermitProjectSiteInfoEntity.setEquipmentRoofMountedJunctionBox(
					permitProjectSiteInfoEntity.getEquipmentRoofMountedJunctionBox());
			newPermitProjectSiteInfoEntity.setEquipmentRoofMountedSingleCircuit(
					permitProjectSiteInfoEntity.getEquipmentRoofMountedSingleCircuit());
			newPermitProjectSiteInfoEntity.setEquipmentGroundLevelACCombinerBox(
					permitProjectSiteInfoEntity.getEquipmentGroundLevelACCombinerBox());
			newPermitProjectSiteInfoEntity.setEquipmentGroundLevelACCombinerDisconnect(
					permitProjectSiteInfoEntity.getEquipmentGroundLevelACCombinerDisconnect());
			newPermitProjectSiteInfoEntity
					.setEquipmentGroundLevelACSubPanel(permitProjectSiteInfoEntity.getEquipmentGroundLevelACSubPanel());
			newPermitProjectSiteInfoEntity.setEquipmentGroundLevelACJunctionBox(
					permitProjectSiteInfoEntity.getEquipmentGroundLevelACJunctionBox());
			newPermitProjectSiteInfoEntity.setEquipmentCombiningInExistingSubPanel(
					permitProjectSiteInfoEntity.getEquipmentCombiningInExistingSubPanel());
			newPermitProjectSiteInfoEntity.setEquipmentCombiningInProposedSubPanel(
					permitProjectSiteInfoEntity.getEquipmentCombiningInProposedSubPanel());
			newPermitProjectSiteInfoEntity
					.setEquipmentCombiningInMainPanel(permitProjectSiteInfoEntity.getEquipmentCombiningInMainPanel());
			newPermitProjectSiteInfoEntity.setEquipmentisOther(permitProjectSiteInfoEntity.getEquipmentisOther());
			newPermitProjectSiteInfoEntity.setEquipmentOther(permitProjectSiteInfoEntity.getEquipmentOther());
			newPermitProjectSiteInfoEntity.setRoofMountedACCombinerBoxManufacturer(
					permitProjectSiteInfoEntity.getRoofMountedACCombinerBoxManufacturer());
			newPermitProjectSiteInfoEntity.setRoofMountedACCombinerBoxManufacturerOther(
					permitProjectSiteInfoEntity.getRoofMountedACCombinerBoxManufacturerOther());
			newPermitProjectSiteInfoEntity
					.setRoofMountedACCombinerBoxModel(permitProjectSiteInfoEntity.getRoofMountedACCombinerBoxModel());
			newPermitProjectSiteInfoEntity.setRoofMountedACCombinerBoxModelOther(
					permitProjectSiteInfoEntity.getRoofMountedACCombinerBoxModelOther());
			newPermitProjectSiteInfoEntity.setRoofMountedACCombiningDisconnectManufacturer(
					permitProjectSiteInfoEntity.getRoofMountedACCombiningDisconnectManufacturer());
			newPermitProjectSiteInfoEntity.setRoofMountedACCombiningDisconnectManufacturerOther(
					permitProjectSiteInfoEntity.getRoofMountedACCombiningDisconnectManufacturerOther());
			newPermitProjectSiteInfoEntity.setRoofMountedACCombiningDisconnectModel(
					permitProjectSiteInfoEntity.getRoofMountedACCombiningDisconnectModel());
			newPermitProjectSiteInfoEntity.setRoofMountedACCombiningDisconnectModelOther(
					permitProjectSiteInfoEntity.getRoofMountedACCombiningDisconnectModelOther());
			newPermitProjectSiteInfoEntity.setRoofMountedACJunctionBoxManufacturer(
					permitProjectSiteInfoEntity.getRoofMountedACJunctionBoxManufacturer());
			newPermitProjectSiteInfoEntity.setRoofMountedACJunctionBoxManufacturerOther(
					permitProjectSiteInfoEntity.getRoofMountedACJunctionBoxManufacturerOther());
			newPermitProjectSiteInfoEntity
					.setRoofMountedACJunctionBoxModel(permitProjectSiteInfoEntity.getRoofMountedACJunctionBoxModel());
			newPermitProjectSiteInfoEntity.setRoofMountedACJunctionBoxModelOther(
					permitProjectSiteInfoEntity.getRoofMountedACJunctionBoxModelOther());
			newPermitProjectSiteInfoEntity.setRoofMountedSingleCircuitACDisconnectManufacturer(
					permitProjectSiteInfoEntity.getRoofMountedSingleCircuitACDisconnectManufacturer());
			newPermitProjectSiteInfoEntity.setRoofMountedSingleCircuitACDisconnectManufacturerOther(
					permitProjectSiteInfoEntity.getRoofMountedSingleCircuitACDisconnectManufacturerOther());
			newPermitProjectSiteInfoEntity.setRoofMountedSingleCircuitACDisconnectModel(
					permitProjectSiteInfoEntity.getRoofMountedSingleCircuitACDisconnectModel());
			newPermitProjectSiteInfoEntity.setRoofMountedSingleCircuitACDisconnectModelOther(
					permitProjectSiteInfoEntity.getRoofMountedSingleCircuitACDisconnectModelOther());
			newPermitProjectSiteInfoEntity.setEquipmenModelOther(permitProjectSiteInfoEntity.getEquipmenModelOther());
			newPermitProjectSiteInfoEntity
					.setEquipmenManufacturerOther(permitProjectSiteInfoEntity.getEquipmenManufacturerOther());
			newPermitProjectSiteInfoEntity
					.setProposedMainPanelManufacturer(permitProjectSiteInfoEntity.getProposedMainPanelManufacturer());
			newPermitProjectSiteInfoEntity.setProposedMainPanelManufacturerOther(
					permitProjectSiteInfoEntity.getProposedMainPanelManufacturerOther());
			newPermitProjectSiteInfoEntity
					.setProposedMainPanelModel(permitProjectSiteInfoEntity.getProposedMainPanelModel());
			newPermitProjectSiteInfoEntity
					.setProposedMainPanelModelOther(permitProjectSiteInfoEntity.getProposedMainPanelModelOther());
			newPermitProjectSiteInfoEntity
					.setDeratingthisPanelString(permitProjectSiteInfoEntity.getDeratingthisPanelString());
			newPermitProjectSiteInfoEntity.setGroundLevelACJunctionBoxManufacturerOther(
					permitProjectSiteInfoEntity.getGroundLevelACJunctionBoxManufacturerOther());
			newPermitProjectSiteInfoEntity.setGroundLevelACJunctionBoxModelOther(
					permitProjectSiteInfoEntity.getGroundLevelACJunctionBoxModelOther());
			newPermitProjectSiteInfoEntity.setMainBraikerOcpdFile(permitProjectSiteInfoEntity.getMainBraikerOcpdFile());
			newPermitProjectSiteInfoEntity.setSubPanelBreakerOCPD(permitProjectSiteInfoEntity.getSubPanelBreakerOCPD());
			newPermitProjectSiteInfoEntity
					.setMainBreakerLocatedEndBusBar(permitProjectSiteInfoEntity.getMainBreakerLocatedEndBusBar());
			newPermitProjectSiteInfoEntity
					.setInstallationGuidelines(permitProjectSiteInfoEntity.getInstallationGuidelines());
			newPermitProjectSiteInfoEntity
					.setDisconnectManufacturerTwo(permitProjectSiteInfoEntity.getDisconnectManufacturerTwo());
			newPermitProjectSiteInfoEntity
					.setDisconnectManufacturerOtherTwo(permitProjectSiteInfoEntity.getDisconnectManufacturerOtherTwo());
			newPermitProjectSiteInfoEntity.setDisconnectModelTwo(permitProjectSiteInfoEntity.getDisconnectModelTwo());
			newPermitProjectSiteInfoEntity
					.setDisconnectModelTwoOther(permitProjectSiteInfoEntity.getDisconnectModelTwoOther());
			newPermitProjectSiteInfoEntity
					.setDisconnectManufacturerThree(permitProjectSiteInfoEntity.getDisconnectManufacturerThree());
			newPermitProjectSiteInfoEntity.setDisconnectManufacturerThreeOther(
					permitProjectSiteInfoEntity.getDisconnectManufacturerThreeOther());
			newPermitProjectSiteInfoEntity
					.setDisconnectModelThree(permitProjectSiteInfoEntity.getDisconnectModelThree());
			newPermitProjectSiteInfoEntity
					.setDisconnectModelThreeOther(permitProjectSiteInfoEntity.getDisconnectModelThreeOther());
			newPermitProjectSiteInfoEntity
					.setRooftopACCombinerModelTwo(permitProjectSiteInfoEntity.getRooftopACCombinerModelTwo());
			newPermitProjectSiteInfoEntity
			.setAcDisconnectThree(permitProjectSiteInfoEntity.getAcDisconnectThree());
			newPermitProjectSiteInfoEntity
			.setAcDisconnectFour(permitProjectSiteInfoEntity.getAcDisconnectFour());

			newPermitProjectSiteInfoEntity.setRoofTopDcJboxType(permitProjectSiteInfoEntity.getRoofTopDcJboxType());
			newPermitProjectSiteInfoEntity
					.setInstallRoofTopDcJbox(permitProjectSiteInfoEntity.getInstallRoofTopDcJbox());
			newPermitProjectSiteInfoEntity
					.setInstallRoofTopACDiscoCombiner(permitProjectSiteInfoEntity.getInstallRoofTopACDiscoCombiner());
			newPermitProjectSiteInfoEntity
					.setRoofTopACCombinerDisconnect(permitProjectSiteInfoEntity.getRoofTopACCombinerDisconnect());

			newPermitProjectSiteInfoEntity
					.setExistingMainPanelManufac(permitProjectSiteInfoEntity.getExistingMainPanelManufac());
			newPermitProjectSiteInfoEntity
					.setExistingMainPanelManufacOther(permitProjectSiteInfoEntity.getExistingMainPanelManufacOther());
			newPermitProjectSiteInfoEntity.setGroundLevelACJunctionBoxManufacturerString(
					permitProjectSiteInfoEntity.getGroundLevelACJunctionBoxManufacturerString());
			newPermitProjectSiteInfoEntity.setGroundLevelACJunctionBoxManufacturerStringOther(
					permitProjectSiteInfoEntity.getGroundLevelACJunctionBoxManufacturerStringOther());
			newPermitProjectSiteInfoEntity.setGroundLevelACJunctionBoxModelString(
					permitProjectSiteInfoEntity.getGroundLevelACJunctionBoxModelString());
			newPermitProjectSiteInfoEntity.setGroundLevelACJunctionBoxModelStringOther(
					permitProjectSiteInfoEntity.getGroundLevelACJunctionBoxModelStringOther());
			newPermitProjectSiteInfoEntity.setGroundLevelACSubPanelManufacturer(
					permitProjectSiteInfoEntity.getGroundLevelACSubPanelManufacturer());
			newPermitProjectSiteInfoEntity
					.setGroundLevelACSubPanelModel(permitProjectSiteInfoEntity.getGroundLevelACSubPanelModel());
			newPermitProjectSiteInfoEntity.setGroundLevelACJunctionBoxManufactuereOtherText(
					permitProjectSiteInfoEntity.getGroundLevelACJunctionBoxManufactuereOtherText());
			newPermitProjectSiteInfoEntity.setGroundLevelACJunctionBoxModelOtherText(
					permitProjectSiteInfoEntity.getGroundLevelACJunctionBoxModelOtherText());
			newPermitProjectSiteInfoEntity.setProposedSubPanelManufacturerOther(
					permitProjectSiteInfoEntity.getProposedSubPanelManufacturerOther());
			newPermitProjectSiteInfoEntity.setSolarLocationOther(permitProjectSiteInfoEntity.getSolarLocationOther());
			newPermitProjectSiteInfoEntity
					.setLeasePPAMeterModelOther(permitProjectSiteInfoEntity.getLeasePPAMeterModelOther());
			newPermitProjectSiteInfoEntity
					.setLeasePPAMeterManufacturerOther(permitProjectSiteInfoEntity.getLeasePPAMeterManufacturerOther());
			newPermitProjectSiteInfoEntity
					.setSubPanelBusRatingOther(permitProjectSiteInfoEntity.getSubPanelBusRatingOther());
			newPermitProjectSiteInfoEntity
					.setSubPanelBreakerOCPDOther(permitProjectSiteInfoEntity.getSubPanelBreakerOCPDOther());
			newPermitProjectSiteInfoEntity.setLocation(permitProjectSiteInfoEntity.getLocation());
			newPermitProjectSiteInfoEntity.setLocationTwo(permitProjectSiteInfoEntity.getLocationTwo());
			newPermitProjectSiteInfoEntity.setLocationThree(permitProjectSiteInfoEntity.getLocationThree());
			newPermitProjectSiteInfoEntity.setInstallingDCBo(permitProjectSiteInfoEntity.getInstallingDCBo());
			newPermitProjectSiteInfoEntity.setDcJboxType(permitProjectSiteInfoEntity.getDcJboxType());
			newPermitProjectSiteInfoEntity.setLocationDC(permitProjectSiteInfoEntity.getLocationDC());
			newPermitProjectSiteInfoEntity.setLocationFive(permitProjectSiteInfoEntity.getLocationFive());
			newPermitProjectSiteInfoEntity.setLocationSix(permitProjectSiteInfoEntity.getLocationSix());
			newPermitProjectSiteInfoEntity.setLocationFour(permitProjectSiteInfoEntity.getLocationFour());
			newPermitProjectSiteInfoEntity.setProposedMainPanMan(permitProjectSiteInfoEntity.getProposedMainPanMan());
			newPermitProjectSiteInfoEntity
					.setThirdSolarInterconnection(permitProjectSiteInfoEntity.getThirdSolarInterconnection());
			newPermitProjectSiteInfoEntity
					.setFourthSolarInterconnection(permitProjectSiteInfoEntity.getFourthSolarInterconnection());
			newPermitProjectSiteInfoEntity
					.setFifthSolarInterconnection(permitProjectSiteInfoEntity.getFifthSolarInterconnection());
			newPermitProjectSiteInfoEntity
					.setThirdSolarInterconnectionOther(permitProjectSiteInfoEntity.getThirdSolarInterconnectionOther());
			newPermitProjectSiteInfoEntity.setFourthSolarInterconnectionOther(
					permitProjectSiteInfoEntity.getFourthSolarInterconnectionOther());
			newPermitProjectSiteInfoEntity
					.setFifthSolarInterconnectionOther(permitProjectSiteInfoEntity.getFifthSolarInterconnectionOther());
			newPermitProjectSiteInfoEntity.setThepontOfTheC(permitProjectSiteInfoEntity.getThepontOfTheC());
			newPermitProjectSiteInfoEntity.setConnectionPoint(permitProjectSiteInfoEntity.getConnectionPoint());
			newPermitProjectSiteInfoEntity.setThepontOfTheCOther(permitProjectSiteInfoEntity.getThepontOfTheCOther());
			newPermitProjectSiteInfoEntity.setDcJboxTypeOther(permitProjectSiteInfoEntity.getDcJboxTypeOther());
			newPermitProjectSiteInfoEntity.setPanelLocation(permitProjectSiteInfoEntity.getPanelLocation());
			newPermitProjectSiteInfoEntity.setDisconnectLocation(permitProjectSiteInfoEntity.getDisconnectLocation());
			newPermitProjectSiteInfoEntity
					.setProposedACCombMainBreaker(permitProjectSiteInfoEntity.getProposedACCombMainBreaker());
			newPermitProjectSiteInfoEntity.setProposedACCombMainBreakerRating(
					permitProjectSiteInfoEntity.getProposedACCombMainBreakerRating());
			newPermitProjectSiteInfoEntity.setProposedACCombMainBreakerRatingOther(
					permitProjectSiteInfoEntity.getProposedACCombMainBreakerRatingOther());
			newPermitProjectSiteInfoEntity
					.setMicroInverterCabling(permitProjectSiteInfoEntity.getMicroInverterCabling());
			newPermitProjectSiteInfoEntity.setRoofTopJbox(permitProjectSiteInfoEntity.getRoofTopJbox());
			newPermitProjectSiteInfoEntity.setRoofTopACDisco(permitProjectSiteInfoEntity.getRoofTopACDisco());
			newPermitProjectSiteInfoEntity.setRoofTopACCombiner(permitProjectSiteInfoEntity.getRoofTopACCombiner());
			newPermitProjectSiteInfoEntity
					.setTransitioningPVWireIn(permitProjectSiteInfoEntity.getTransitioningPVWireIn());
			newPermitProjectSiteInfoEntity.setRoofTopJboxDC(permitProjectSiteInfoEntity.getRoofTopJboxDC());
			newPermitProjectSiteInfoEntity.setRoofTopDCDisco(permitProjectSiteInfoEntity.getRoofTopDCDisco());
			newPermitProjectSiteInfoEntity.setRoofTopDCCombiner(permitProjectSiteInfoEntity.getRoofTopDCCombiner());
			newPermitProjectSiteInfoEntity
					.setInstallingACCombiner(permitProjectSiteInfoEntity.getInstallingACCombiner());
			newPermitProjectSiteInfoEntity.setACCombinerInstalled(permitProjectSiteInfoEntity.getACCombinerInstalled());
			newPermitProjectSiteInfoEntity.setNorthToShouthFin(permitProjectSiteInfoEntity.getNorthToShouthFin());
			newPermitProjectSiteInfoEntity
					.setNorthToShouthFinOther(permitProjectSiteInfoEntity.getNorthToShouthFinOther());
			newPermitProjectSiteInfoEntity.setHeightOfSouth(permitProjectSiteInfoEntity.getHeightOfSouth());
			newPermitProjectSiteInfoEntity
					.setSubPanelConductorSizing(permitProjectSiteInfoEntity.getSubPanelConductorSizing());
			newPermitProjectSiteInfoEntity
					.setSubPanelConductorSize(permitProjectSiteInfoEntity.getSubPanelConductorSize());
			newPermitProjectSiteInfoEntity
					.setSubPanelConductorSizeOther(permitProjectSiteInfoEntity.getSubPanelConductorSizeOther());
			newPermitProjectSiteInfoEntity.setQtyJunctionBox(permitProjectSiteInfoEntity.getQtyJunctionBox());
			
			newPermitProjectSiteInfoEntity.setSubPanelSpecification(permitProjectSiteInfoEntity.getSubPanelSpecification());
			newPermitProjectSiteInfoEntity.setSubPanelBusRatingCombining(permitProjectSiteInfoEntity.getSubPanelBusRatingCombining());
			newPermitProjectSiteInfoEntity.setSubPanelBusRatingCombiningOther(permitProjectSiteInfoEntity.getSubPanelBusRatingCombiningOther());
			newPermitProjectSiteInfoEntity.setSubPanelMainBreakerRatingCombining(permitProjectSiteInfoEntity.getSubPanelMainBreakerRatingCombining());
			newPermitProjectSiteInfoEntity.setSubPanelMainBreakerRatingCombiningOther(permitProjectSiteInfoEntity.getSubPanelMainBreakerRatingCombiningOther());
			newPermitProjectSiteInfoEntity.setSubPanelBreakerAtMainServiceCombining(permitProjectSiteInfoEntity.getSubPanelBreakerAtMainServiceCombining());
			newPermitProjectSiteInfoEntity.setSubPanelBreakerAtMainServiceCombiningOther(permitProjectSiteInfoEntity.getSubPanelBreakerAtMainServiceCombiningOther());
			
		}
		return newPermitProjectSiteInfoEntity;

	}
}
