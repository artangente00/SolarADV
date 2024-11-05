package com.PlayGroundAdv.Solar.repositories;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.model.ACCombinerSLCUsedModel;
import com.PlayGroundAdv.Solar.model.ACDisconnectUsedModel;
import com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.libraries.DCCombinerDisconnectUsedModel;

@Repository
public interface PermitProjectSiteInfoRepository extends JpaRepository<PermitProjectSiteInfoEntity, Long> {

	// A.B 11-13
	public static final String FIND_ROOF_RAILRACKING_LIST = "SELECT u.railRakingModel from PermitProjectSiteInfoEntity u where u.permitEntity.creationPermitDate > :p1 and  u.permitEntity.isDeleted = false and u.permitEntity.authentificationEntity.email NOT LIKE '%nuagetechnologies%' and u.permitEntity.authentificationEntity.email NOT LIKE '%arij%'";

	@Transactional
	@Modifying
	@Query(value = FIND_ROOF_RAILRACKING_LIST)
	public List<RailRacking> findRoofRailRackingList(@Param("p1") Date date);

	// A.B 11-13
	public static final String FIND_GROUND_RAILRACKING_LIST = "SELECT u.railRakingModelforGroundMounted from PermitProjectSiteInfoEntity u where u.permitEntity.creationPermitDate > :p1 and  u.permitEntity.isDeleted = false and u.permitEntity.authentificationEntity.email NOT LIKE '%nuagetechnologies%' and u.permitEntity.authentificationEntity.email NOT LIKE '%arij%'";

	@Transactional
	@Modifying
	@Query(value = FIND_GROUND_RAILRACKING_LIST)
	public List<RailRacking> findGroundRailRackingList(@Param("p1") Date date);

	// A.B 11-13
	public static final String FIND_ATTACHMENT_LIST = "SELECT u.railConnectionModel from PermitProjectSiteInfoEntity u where u.permitEntity.creationPermitDate > :p1 and  u.permitEntity.isDeleted = false and u.permitEntity.authentificationEntity.email NOT LIKE '%nuagetechnologies%' and u.permitEntity.authentificationEntity.email NOT LIKE '%arij%'";

	@Transactional
	@Modifying
	@Query(value = FIND_ATTACHMENT_LIST)
	public List<String> findAttachementList(@Param("p1") Date date);

	// A.B 11-13
	public static final String FIND_FLASHING_LIST = "SELECT u.flashing from PermitProjectSiteInfoEntity u where u.permitEntity.creationPermitDate > :p1 and  u.permitEntity.isDeleted = false and u.permitEntity.authentificationEntity.email NOT LIKE '%nuagetechnologies%' and u.permitEntity.authentificationEntity.email NOT LIKE '%arij%'";

	@Transactional
	@Modifying
	@Query(value = FIND_FLASHING_LIST)
	public List<String> findFlashingList(@Param("p1") Date date);

	// A.B 11-13
	public static final String FIND_DC_COMBINER_DISCONNECT_LIST = "SELECT "
			+ " new com.PlayGroundAdv.Solar.model.libraries.DCCombinerDisconnectUsedModel(u.roofTopDCDisco.id ,u.roofTopDCCombiner.id,u.disconnectModel.id,u.disconnectModelTwo.id,u.disconnectModelThree.id)"
			+ " from PermitProjectSiteInfoEntity u where u.permitEntity.creationPermitDate > :p1 and  u.permitEntity.isDeleted = false and u.permitEntity.authentificationEntity.email NOT LIKE '%nuagetechnologies%' and u.permitEntity.authentificationEntity.email NOT LIKE '%arij%'";

	@Transactional
	@Modifying
	@Query(value = FIND_DC_COMBINER_DISCONNECT_LIST)
	public List<DCCombinerDisconnectUsedModel> findDCList(@Param("p1") Date date);

	// A.B 11-13
	public static final String FIND_JUNCTION_BOX_LIST = "SELECT u.roofTopJbox "
			+ " from PermitProjectSiteInfoEntity u where u.permitEntity.creationPermitDate > :p1 and  u.permitEntity.isDeleted = false and u.permitEntity.authentificationEntity.email NOT LIKE '%nuagetechnologies%' and u.permitEntity.authentificationEntity.email NOT LIKE '%arij%'";

	@Transactional
	@Modifying
	@Query(value = FIND_JUNCTION_BOX_LIST)
	public List<String> findJunctionBoxList(@Param("p1") Date date);

	// A.B 11-13
	public static final String FIND_JUNCTION_BOX_DC_LIST = "SELECT u.roofTopJboxDC "
			+ " from PermitProjectSiteInfoEntity u where u.permitEntity.creationPermitDate > :p1 and  u.permitEntity.isDeleted = false and u.permitEntity.authentificationEntity.email NOT LIKE '%nuagetechnologies%' and u.permitEntity.authentificationEntity.email NOT LIKE '%arij%'";

	@Transactional
	@Modifying
	@Query(value = FIND_JUNCTION_BOX_DC_LIST)
	public List<String> findJunctionBoxDCList(@Param("p1") Date date);

	// A.B 11-13
	public static final String FIND_AC_COMBINER_SLC_LIST = "SELECT "
			+ " new com.PlayGroundAdv.Solar.model.ACCombinerSLCUsedModel(u.roofTopACCombiner.id, u.aCCombinerInstalled.id,"
			+ "	u.groundLevelACCombinerBoxModel.id)"
			+ " from PermitProjectSiteInfoEntity u where u.permitEntity.creationPermitDate > :p1 and  u.permitEntity.isDeleted = false "
			+ " and u.permitEntity.authentificationEntity.email NOT LIKE '%nuagetechnologies%' and u.permitEntity.authentificationEntity.email NOT LIKE '%arij%' ";

	@Transactional
	@Modifying
	@Query(value = FIND_AC_COMBINER_SLC_LIST)
	public List<ACCombinerSLCUsedModel> findACCombinerSLCList(@Param("p1") Date date);

	// A.B 11-13
	public static final String FIND_AC_DISCONNECT_LIST = "SELECT "
			+ " new com.PlayGroundAdv.Solar.model.ACDisconnectUsedModel(u.roofTopACDisco, u.rooftopACCombinerModel, u.rooftopACCombinerModelTwo)"
			+ " from PermitProjectSiteInfoEntity u where u.permitEntity.creationPermitDate > :p1 and  u.permitEntity.isDeleted = false "
			+ " and u.permitEntity.authentificationEntity.email NOT LIKE '%nuagetechnologies%' and u.permitEntity.authentificationEntity.email NOT LIKE '%arij%'";

	@Transactional
	@Modifying
	@Query(value = FIND_AC_DISCONNECT_LIST)
	public List<ACDisconnectUsedModel> findACDisconnectList(@Param("p1") Date date);

	// A.B 11-13
	public static final String FIND_LEASE_PPA_METER_LIST = "SELECT u.leasePPAMeter "
			+ " from PermitProjectSiteInfoEntity u where u.permitEntity.creationPermitDate > :p1 and  u.permitEntity.isDeleted = false and u.permitEntity.authentificationEntity.email NOT LIKE '%nuagetechnologies%' and u.permitEntity.authentificationEntity.email NOT LIKE '%arij%'";

	@Transactional
	@Modifying
	@Query(value = FIND_LEASE_PPA_METER_LIST)
	public List<String> findLeasePPAMeterList(@Param("p1") Date date);

	// A.B 11-13
	public static final String FIND_ROOF_MATERIAL_LIST = "SELECT u.roofMaterialType "
			+ " from PermitProjectSiteInfoEntity u where u.permitEntity.creationPermitDate > :p1 and  u.permitEntity.isDeleted = false and u.permitEntity.authentificationEntity.email NOT LIKE '%nuagetechnologies%' and u.permitEntity.authentificationEntity.email NOT LIKE '%arij%'";

	@Transactional
	@Modifying
	@Query(value = FIND_ROOF_MATERIAL_LIST)
	public List<String> findRoofMaterialList(@Param("p1") Date date);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndRailConnectionModel(Long idUser,
			String railConnection);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndRoofTopJbox(Long idUser,
			String roofTopJbox);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndRoofTopJboxDC(Long idUser,
			String roofTopJbox);

	List<PermitProjectSiteInfoEntity> findByRoofTopJboxAndPermitEntityIsDeleted(String roofTop, Boolean isDel);

	List<PermitProjectSiteInfoEntity> findByRoofTopJboxDCAndPermitEntityIsDeleted(String roofTop, Boolean isDel);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndRailRakingModelId(Long userId,
			Long models);

	PermitProjectSiteInfoEntity findOneByPermitEntityAndFlashing(PermitEntity permitEntity, String idFlashing);

	PermitProjectSiteInfoEntity findOneByPermitEntityId(Long idPermit);

	Long countByPermitEntityAndFlashing(PermitEntity permitEntity, String idFlashing);

	List<PermitProjectSiteInfoEntity> findByFlashing(String idFlashing);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndFlashing(Long idOwner,
			String idFlashing);

	public static final String FIND_PROJECT_USING_MODEL = "SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel "
			+ " (u.homeOwnLastName,  u.homeOwnName, u.projectName,  u.status, v.firstName, v.lastName) from PermitEntity u, AuthentificationEntity v, PermitProjectSiteInfoEntity w"
			+ " where u.isDeleted = false and u.status != 'Delivered' and u.status != 'Submitted' and u.isTemplate  = false and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id ";

	@Query(FIND_PROJECT_USING_MODEL + " and w.flashing = :p1 ")
	List<ProjectForLibrariesModel> listOfNonDeletedAndNonSubmittedAndNonDeliveredAndNonTemplatePermitsUsingFlashing(
			@Param("p1") String flashing);

	@Query(FIND_PROJECT_USING_MODEL + " and w.leasePPAMeter = :p1 ")
	List<ProjectForLibrariesModel> getPermitEntityByLeasePPAMeter(@Param("p1") String leasePPAMeter);

	@Query(value = FIND_PROJECT_USING_MODEL + " and w.proposedSubPanel = :p1")
	public List<ProjectForLibrariesModel> findByProposedSubPanel1(@Param("p1") String idSubPanel);

	@Query(value = FIND_PROJECT_USING_MODEL
			+ " and (w.solarInterconnection = :p1 or w.secondSolarInterconnection = :p1 or w.thirdSolarInterconnection = :p1 or w.fourthSolarInterconnection = :p1 or w.fifthSolarInterconnection = :p1)")
	List<ProjectForLibrariesModel> findListOfProjectsWithOCPD1(@Param("p1") String backFeed);

	@Query("SELECT u FROM PermitProjectSiteInfoEntity u WHERE u.roofTopACCombinerDisconnect = :p1 OR u.rooftopACCombinerModel = :p1 OR u.rooftopACCombinerModelTwo = :p1 OR u.groundLevelACCombinerDisconnectModel = :p1 OR u.groundLevelACCombinerBoxModelString = :p1")
	List<PermitProjectSiteInfoEntity> findListOfProjectsUsingACCombiner(@Param("p1") String acCombiner);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndRoofTopACCombinerDisconnect(
			Long idUser, String roofTop);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndGroundLevelACCombinerDisconnectModel(
			Long idUser, String groundLevel);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndRooftopACCombinerModel(Long idUser,
			String roofTop);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndRooftopACCombinerModelTwo(
			Long idUser, String roofTop);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndRoofTopACDisco(Long idUser,
			String roofTop);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndLeasePPAMeter(Long idOwner,
			String idLeasePPAMeter);

	List<PermitProjectSiteInfoEntity> findByLeasePPAMeter(String idLeasePPAMeter);

	PermitProjectSiteInfoEntity findByPermitEntityAndLeasePPAMeter(PermitEntity permitEntity, String leasePPAMeter);

	Long countByPermitEntityAndLeasePPAMeter(PermitEntity permitEntity, String leasePPAMeter);

	List<PermitProjectSiteInfoEntity> findByGroundLevelACCombinerDisconnectModelOrRooftopACCombinerModelOrRooftopACCombinerModelTwoOrRoofTopACDiscoAndPermitEntityIsDeleted(
			String parm1, String parm2, String parm3, String parm4, Boolean isDel);

	List<PermitProjectSiteInfoEntity> findByRoofMaterialType(Long roofMaterialType);

	List<PermitProjectSiteInfoEntity> findByRoofMaterialTypeAndPermitEntityStatusNotInAndPermitEntityIsTemplateIsFalse(
			Long roofMaterialType, List<String> status);

	List<PermitProjectSiteInfoEntity> findByProposedSubPanel(String idproposedSubPanel);

	PermitProjectSiteInfoEntity findByPermitEntityAndProposedSubPanel(PermitEntity permitEntity,
			String proposedSubPanel);

	Long countByPermitEntityAndProposedSubPanel(PermitEntity permitEntity, String proposedSubPanel);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndProposedSubPanel(Long idUser,
			String ProposedSubPanel);

	@Query("SELECT u FROM PermitProjectSiteInfoEntity u WHERE u.solarInterconnection = :p1 or u.secondSolarInterconnection = :p1 or u.thirdSolarInterconnection = :p1 or u.fourthSolarInterconnection = :p1 or u.fifthSolarInterconnection = :p1 ")
	List<PermitProjectSiteInfoEntity> findListOfProjectsWithOCPD(@Param("p1") String backFeed);

	PermitProjectSiteInfoEntity findByPermitEntityAndSolarInterconnection(PermitEntity permitEntity, String backFeed);

	PermitProjectSiteInfoEntity findByPermitEntityAndSecondSolarInterconnection(PermitEntity permitEntity,
			String backFeed);

	PermitProjectSiteInfoEntity findByPermitEntityAndThirdSolarInterconnection(PermitEntity permitEntity,
			String backFeed);

	PermitProjectSiteInfoEntity findByPermitEntityAndFourthSolarInterconnection(PermitEntity permitEntity,
			String backFeed);

	PermitProjectSiteInfoEntity findByPermitEntityAndFifthSolarInterconnection(PermitEntity permitEntity,
			String backFeed);

	Long countByPermitEntityAndSolarInterconnection(PermitEntity permitEntity, String backFeed);

	Long countByPermitEntityAndSecondSolarInterconnection(PermitEntity permitEntity, String backFeed);

	Long countByPermitEntityAndThirdSolarInterconnection(PermitEntity permitEntity, String backFeed);

	Long countByPermitEntityAndFourthSolarInterconnection(PermitEntity permitEntity, String backFeed);

	Long countByPermitEntityAndFifthSolarInterconnection(PermitEntity permitEntity, String backFeed);

	PermitProjectSiteInfoEntity findByPermitEntityId(Long idPermit);

	List<PermitProjectSiteInfoEntity> findAllByPermitEntityId(Long idPermit);

	@Transactional
	@Modifying
	@Query("update PermitProjectSiteInfoEntity u set u.subPanelConductorSizeNote = :p1, u.subPanelConductorSizeFiles = true where u.permitEntity.id= :p2")
	void updatesubPanelConductorSize(@Param("p1") String notes, @Param("p2") Long permitId);

	// A.B To Set Component Removed
	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndRailConnectionModelIn(Long idUser,
			List<String> models);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndRooftopACCombinerModelIn(Long idUser,
			List<String> models);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndRooftopACCombinerModelTwoIn(
			Long idUser, List<String> models);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndRoofTopACDiscoIn(Long idUser,
			List<String> models);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndRoofTopJboxIn(Long idUser,
			List<String> models);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndRoofTopJboxDCIn(Long idUser,
			List<String> models);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndFlashingIn(Long idUser,
			List<String> models);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndLeasePPAMeterIn(Long idUser,
			List<String> models);

	List<PermitProjectSiteInfoEntity> findByPermitEntityAuthentificationEntityIdAndProposedSubPanelIn(Long idUser,
			List<String> models);

	public static final String PERMIT_PROJECT_SITE_INFO = "SELECT new com.PlayGroundAdv.Solar.model.PermitProjectSiteInfoEntityTwo "
			+ "(0L, u.trackingSystemManufacturer, u.trackingSystemModel,u.inverterModel,u.rafterTrussSpacing, u.crossSectionSize,u.spanBetweenAttachment,u.roofMaterialType,"
			+ " u.roofMaterialTypeOther,u.rankingRoofManufacturer, u.rankingRoofModel,u.moduleGrounding,u.disconnectManufacturer, 0L,u.quantityRooftop,u.solarLocation,"
			+ " u.mainPanelUpgrade,u.panelBusRating,u.solarInterconnection, u.secondSolarInterconnection,u.useDisconectSwith, u.usedByInverterManufacturer,u.usedRevenue,"
			+ " u.SubPanelMainBreakerRating,u.SubPanelBusRating, u.panelExistingProposed,u.quantityofCombinerBox, u.quantityofCombinerBoxOther,u.trackingSystemManufacturerOther,"
			+ " u.trackingSystemModelOther,u.rooftopACCombinerModel, u.textOtherSize,u.ACDisconnectSwitchManufacturer, u.ACDisconnectSwitchModel,u.ACDisconnectSwitchManufacturerOther,"
			+ " u.ACDisconnectSwitchModelOther,u.DCDisconnectSwitchManufacturer, u.DCDisconnectSwitchModel,u.LeasePPAMeterManufacturer, u.LeasePPAMeterModel,u.trackingSystemManufacturerForSecondTracker,"
			+ " u.trackingSystemManufacturerForSecondTrackerOther, u.trackingSystemModelForSecondTracker, u.trackingSystemModelForSecondTrackerOther,"
			+ " u.rankingRoofManufacturerOther,u.rankingRoofModelOther, u.moduleGroundingOther,u.disconnectManufacturerOther, u.disconnectModelOther,u.railConnectionModel,"
			+ " u.atticJBoxesbeUtilized,u.descriptionOfBackFeed, u.groundLevelACDisconnectEnclosure,u.panelBusRatingOther, u.panelMainBreakerRating,u.panelMainBreakerRatingOther,"
			+ " u.solarInterconnectionOther,u.secondSolarInterconnectionOther, u.combiningACCircuits,u.morInterconnectingBackFeed, 0L,0L, u.sizeAndTypeAtticJBox,u.sizeAndTypeAtticJBoxOther,"
			+ " u.ifApplicableSubPanelMainBreakerRating,u.proposedSubPanelManufacturer, u.proposedSubPanelModel,u.proposedSubPanelModelOther, u.groundLevelACCombinerBoxModel.id,u.groundLevelACCombinerDisconnectModel,"
			+ " u.groundLevelACJunctionBoxManufacturer,u.groundLevelACJunctionBoxModel, u.equipmentRoofMountedACCombinerBox, u.equipmentRoofMountedACCombinerDisconnect,"
			+ " u.equipmentRoofMountedJunctionBox,u.equipmentRoofMountedSingleCircuit, u.equipmentGroundLevelACCombinerBox, u.equipmentGroundLevelACCombinerDisconnect,"
			+ " u.equipmentGroundLevelACSubPanel,u.equipmentGroundLevelACJunctionBox, u.equipmentCombiningInExistingSubPanel, u.equipmentCombiningInProposedSubPanel,"
			+ " u.equipmentCombiningInMainPanel, u.equipmentisOther,  u.equipmentOther,u.roofMountedACCombinerBoxManufacturer, u.roofMountedACCombinerBoxManufacturerOther,"
			+ " u.roofMountedACCombinerBoxModel,u.roofMountedACCombinerBoxModelOther, u.roofMountedACCombiningDisconnectManufacturer, u.roofMountedACCombiningDisconnectManufacturerOther,"
			+ " u.roofMountedACCombiningDisconnectModel, u.roofMountedACCombiningDisconnectModelOther, u.roofMountedACJunctionBoxManufacturer,"
			+ " u.roofMountedACJunctionBoxManufacturerOther, u.roofMountedACJunctionBoxModel,u.roofMountedACJunctionBoxModelOther, u.roofMountedSingleCircuitACDisconnectManufacturer,"
			+ " u.roofMountedSingleCircuitACDisconnectManufacturerOther, u.roofMountedSingleCircuitACDisconnectModel, u.roofMountedSingleCircuitACDisconnectModelOther,u.equipmenModelOther,"
			+ " u.equipmenManufacturerOther,u.proposedMainPanelManufacturer, u.proposedMainPanelManufacturerOther,u.proposedMainPanelModel, u.proposedMainPanelModelOther,u.deratingthisPanelString, "
			+ " u.groundLevelACJunctionBoxManufacturerOther, u.groundLevelACJunctionBoxModelOther,u.subPanelBreakerOCPD, u.MainBreakerLocatedEndBusBar, u.installationGuidelines,  "
			+ " u.textOtherRatfter,  u.disconnectManufacturerTwo,  u.disconnectManufacturerOtherTwo,  0L,  u.disconnectModelTwoOther,  u.disconnectManufacturerThree,  u.disconnectManufacturerThreeOther,  "
			+ " 0L,  u.disconnectModelThreeOther,  u.rooftopACCombinerModelTwo,  u.tallStructure,u.otherTallStructure,u.meanHeight,"
			+ " u.existingMainPanelManufac,  u.existingMainPanelManufacOther,  u.groundLevelACJunctionBoxManufacturerString,  u.groundLevelACJunctionBoxManufacturerStringOther,  "
			+ " u.groundLevelACJunctionBoxModelString,  u.groundLevelACJunctionBoxModelStringOther, u.groundLevelACSubPanelManufacturer, u.groundLevelACSubPanelModel, "
			+ " u.groundLevelACJunctionBoxManufactuereOtherText, u.groundLevelACJunctionBoxModelOtherText, u.proposedSubPanelManufacturerOther, u.solarLocationOther, "
			+ " u.LeasePPAMeterModelOther, u.LeasePPAMeterManufacturerOther, u.SubPanelBusRatingOther, u.subPanelBreakerOCPDOther, u.location, u.locationTwo, u.locationThree, "
			+ " u.installingDCBo, u.locationFive, u.locationSix, u.locationFour, u.proposedMainPanMan, u.thirdSolarInterconnection, "
			+ " u.fourthSolarInterconnection, u.fifthSolarInterconnection, u.thirdSolarInterconnectionOther, u.fourthSolarInterconnectionOther, u.fifthSolarInterconnectionOther, u.thepontOfTheC, "
			+ " u.connectionPoint, u.thepontOfTheCOther, u.panelLocation,u.disconnectLocation,u.uploadComments, u.roofTopACCombinerDisconnect, u.installRoofTopACDiscoCombiner,"
			+ " u.msphasNoBranchCircuitBreakers, u.proposedACCombMainBreaker,u.proposedACCombMainBreakerRating, u.proposedACCombMainBreakerRatingOther, u.microInverterCabling, u.roofTopJbox,u.roofTopACDisco,u.roofTopACCombiner.id,u.transitioningPVWireIn,"
			+ " u.roofTopJboxDC,0L,0L,u.qtyIndependentACDisco,u.flashing, u.leasePPAMeter, u.proposedSubPanel, u.installingACCombiner, u.aCCombinerInstalled.id,u.northToShouthFin, u.northToShouthFinOther, u.heightOfSouth, u.subPanelConductorSizing, u.subPanelConductorSize, u.subPanelConductorSizeOther,"
			+ " u.subPanelConductorSizeNote, u.subPanelConductorSizeFiles , u.checkSiteSurveyOCPDValidity, u.includeTransformer, 0L, u.qtyJunctionBox,"
			+ " u.subPanelSpecification, u.subPanelBusRatingCombining, u.subPanelBusRatingCombiningOther, u.subPanelMainBreakerRatingCombining,"
			+ "	u.subPanelMainBreakerRatingCombiningOther, u.subPanelBreakerAtMainServiceCombining, u.subPanelBreakerAtMainServiceCombiningOther, u.acDisconnectThree.id, u.acDisconnectFour.id)"

			+ " from PermitProjectSiteInfoEntity u where u.permitEntity.id = :p1";

	@Query(value = PERMIT_PROJECT_SITE_INFO)
	PermitProjectSiteInfoEntityTwo getPermitProjectSiteInfoEntityTwo(@Param("p1") Long idProject);

	List<PermitProjectSiteInfoEntity> findByRailConnectionModel(String railConnectionModel);

	List<PermitProjectSiteInfoEntity> findByRailConnectionModelAndPermitEntityIsDeleted(String railConnectionModel,
			Boolean isDel);

	@Query("SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel  "
			+ " ( u.homeOwnLastName, u.homeOwnName, u.projectName, u.status, "
			+ " v.firstName, v.lastName) from PermitEntity u, AuthentificationEntity v, "
			+ " PermitProjectSiteInfoEntity w"
			+ " where (w.railRakingModel.id = :p1 or  w.railRakingModelforGroundMounted.id = :p1) and  u.isDeleted  = :p2 and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = :p2 and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id")
	List<ProjectForLibrariesModel> getPermitEntityByRailRacking(@Param("p1") Long idRailRacking,
			@Param("p2") Boolean isDel);

	Long deleteByPermitEntityId(Long id);
}
