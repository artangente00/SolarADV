package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.SiteSurveyEntity;
import com.PlayGroundAdv.Solar.model.PermitResponse;
import com.PlayGroundAdv.Solar.model.PermitSketchResults;
import com.PlayGroundAdv.Solar.model.SiteSurveyResult;
import com.PlayGroundAdv.Solar.model.UserInformationResult;

import org.springframework.stereotype.Repository;

@Repository
public interface SiteSurveyRepository extends JpaRepository<SiteSurveyEntity, Long>{

	SiteSurveyEntity findByportalProjectId(Long idPermitInfo );
	
	@Query("SELECT u.createdBy from SiteSurveyEntity u WHERE u.id = :p1")
	AuthentificationEntity findCreator(@Param("p1") Long idPermitInfo );
	
	//A.B 01-20
	@Query(value = "SELECT u.inverter1Model FROM SiteSurveyEntity u WHERE u.id = :p1")
	String findFirstInverterModel(@Param("p1") Long projectId);
	
	//A.B 01-20
	@Query(value = "SELECT u.inverter2Model FROM SiteSurveyEntity u WHERE u.id = :p1")
	String findSecondInverterModel(@Param("p1") Long projectId);
	
	//A.B 01-20
	@Query(value = "SELECT u.inverter3Model FROM SiteSurveyEntity u WHERE u.id = :p1")
	String findThirdInverterModel(@Param("p1") Long projectId);
	
	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.UserInformationResult ( "
			+ "   u.id,  " + "   u.firstName,  " + "   u.lastName,  " + "   u.password,  " + "   u.email ,"
			+ "   u.country,  " + "   u.company,  " + "   u.contractorCode, " + "   u.active,  " + "   v.address,  " + "   v.secondAddressLine,"
			+ "   v.city,  " + "   v.state,  " + "   v.postalCode,  " + "   u.roleEntity.id ) "
			+ " from AuthentificationEntity u,ContractorInformationEntity v  " + " where u.id = :p1 and v.authentificationEntity.id = :p1 ")
	UserInformationResult getUserInformationResult(@Param("p1") Long userId);
	
	SiteSurveyEntity findByIdAndIsCanceled(Long id, Boolean isCancel);
	List<SiteSurveyEntity> findByIsCanceledOrderByCreationDateDesc(Boolean isCancel);
	Page<SiteSurveyEntity> findByIsCanceled(Boolean isCancel, Pageable pageable);
	
	@Query(value = "SELECT u from SiteSurveyEntity u where u.isCanceled = :p1 AND u.createdBy.email NOT LIKE :p2 ORDER BY u.creationDate DESC")
	List<SiteSurveyEntity> getSiteSurveyNotEmail(@Param("p1") Boolean isCancel, @Param("p2") String email);
	@Query(value = "SELECT u from SiteSurveyEntity u where u.isCanceled = :p1 AND u.createdBy.email NOT LIKE :p2")
	Page<SiteSurveyEntity> getSiteSurveyNotEmail(@Param("p1") Boolean isCancel, @Param("p2") String email, Pageable pageable);
	
	List<SiteSurveyEntity> findByIsCanceledAndCreatedByIdOrderByCreationDateDesc(Boolean isCancel, Long id);
	Page<SiteSurveyEntity> findByIsCanceledAndCreatedById(Boolean isCancel, Long id, Pageable pageable);
	
	@Query(value = "SELECT u from SiteSurveyEntity u where u.isCanceled = :p1 and u.createdBy.id = :p2 AND u.createdBy.email NOT LIKE :p3 ORDER BY u.creationDate DESC")
	List<SiteSurveyEntity> getSiteSurveyByIdNotEmail(@Param("p1") Boolean isCancel,@Param("p2") Long id, @Param("p3") String email);
	@Query(value = "SELECT u from SiteSurveyEntity u where u.isCanceled = :p1 and u.createdBy.id = :p2 AND u.createdBy.email NOT LIKE :p3")
	Page<SiteSurveyEntity> getSiteSurveyByIdNotEmail(@Param("p1") Boolean isCancel,@Param("p2") Long id, @Param("p3") String email, Pageable pageable);
	
	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.SiteSurveyResult ( "+
			 "u.id, u.homeOwnName, u.createdBy.id, u.portalProject.id, u.status," + 
			" u.hasProject, u.isCanceled, u.submitted, u.creationDate, u.lastUpdatedDate," + 
			" u.dateOfSubmit, u.basicTypeOfSystem, u.hasProjectSiteImage, u.contactName," + 
			" u.streetAddress, u.city, u.state, u.zIP,u.mainContactPhone, u.otherPhone ," + 
			" u.emailAddress , u.hoa, u.permittingAuthority," + 
			" u.legalOwnerName, u.parcelNumber, u.roofMaterialType, u.widthSeams, u.riskCategory," + 
			" u.otherRiskCategory, u.buildingOccupancy, u.otherBuildingOccupancy, u.numberOfStories,u.meanHeight," + 
			" u.projectEquipmentStagingLocation, u.ownerPreferredRoofAccessLocation," + 
			" u.accessIssuesWith_MeterOrProposedACDisco, u.utilityMeterOrProposedACDisco," + 
			" u.unrestrainedAnimal, u.otheraccessissue, u.describeAccessIssues," + 
			" u.contactpersonforutility, u.othercontactperson, u.contactphone, u.othercontactphone," + 
			" u.hasImageCapturesatRafterTrusses, u.centerFed, u.mainPanelMake, u.mainPanelModel," + 
			" u.upgradingMainServicePanel, u.voltage, u.otherVoltage, u.mspbusbarRating," + 
			" u.otherMSPBusbarRating, u.mSPMainBreakerRating, u.otherMSPMainBreakerRating," + 
			" u.openBreakerSlots, u.sCIR, u.mainBreakerFeedGauge,u.otherMainBreakerFeedGauge, u.utilityFeederGauge," + 
			" u.copperWiresSameAsMainContact, u.copperWiresDifferentNumber," + 
			" u.aluminumWiresSameAsMainContact, u.aluminumWiresDifferentNumber, u.groundAccessible," + 
			" u.undergroundOrOverheadFeed, u.hasImageAtMSP, u.existingSubpanel," + 
			" u.tieInPOCIsAtSubpanel, u.subpanelMake, u.subpanelModel, u.subpanelVoltage," + 
			" u.otherSubpanelVoltage," + 
			" u.subpanelBusbarRating, u.otherSubpanelBusbarRating, u.subpanelMainBreakerRating," + 
			" u.otherSubpanelMainBreakerRating, u.openBreakerSlotsinSubpanel, u.subpanelFeederGauge," + 
			" u.otherSubpanelFeederGauge," + 
			" u.hasImageCapturesAtSupPanel, u.siteNotes, u.utilityCoName,u.meterNumber, u.nEMType," + 
			" u.otherNEMType, u.postSolarRate, u.usageHistoryOffset, u.paceEntity, u.paceEntityOther," + 
			" u.aCDiscoWillbemorethan10FromUtilityMeter, u.atLeast4MonthsOfelectricbillinghistory," + 
			" u.squareFeetOfLivingArea, u.recentAnnualUsage, u.numberOfElectricVehicles," + 
			" u.existingPVSystemAtSite, u.pVSystemMake, u.pVSystemModel, u.pVQuantity," + 
			" u.gridTiedInverterMake, u.gridTiedInverterModel, u.gridTiedInverterQuantity," + 
			" u.existingACDisconnect, u.aCDiscoMake, u.aCDiscoModel, u.aCDiscoRating," + 
			" u.connectionType, u.otherConnectionType, u.notesExistingPvSystem, u.existingBattery, u.notesOnExistingBatterySystem," + 
			" u.pVBreaker1, u.pVBreaker2, u.pVBreaker3, u.pVBreaker4,u.pVBreaker5, " + 
			" u.inverterTechnology, u.firstInverter.id, u.secondInverter.id," + 
			" u.thirdInverter.id, u.notesOnInverter, u.proposedBattery," + 
			" u.typeOfBatterySystem, u.batteryLocation, u.circuitstoRelocatetoCriticalLoadsPanel, " + 
			" u.criticalLoadPanelLocation, u.hasImageOfExistingSolarEquipLocations, u.imageBatteryInfo," + 
			" u.productionMonitor, u.activeInternetConnection," + 
			" u.modemLocation, u.connectTheMonitor, u.hasImageCapturesOfRoof," + 
			" u.verifyRoofMeasurementsPreRoofLayoutProvided, u.hasImageCapturesOfApplicableElevationsViews," + 
			" u.wireRunOnRoof, u.wireRunInAttic, u.tiltupModules," + 
			" u.roofMaterial, u.numberOfLayers, u.roofAge,  u.pvModule.id,u.gableRakeOverhang," + 
			" u.heightAtGutter, u.crossSectionSize, u.crossSectionSizeOther, u.roofStructureChart," + 
			" u.stanchionMaxSpacing, u.ridgeBeamDepthAtArrays, u.maxSpanAtArraysHS1,u.maxSpanAtArraysInchesHS1," + 
			" u.maxSpanAtArraysHS2, u.maxSpanAtArraysInchesHS2, u.rafterTrussSpacing, u.rafterTrussSpOther," + 
			" u.nonRoofCarport, u.nonRoofPatioCover," + 
			" u.nonRoofContourSlope, u.nonRoofPathPoint, u.nonRoofGradingGrubbing," + 
			" u.nonRoofSiteComposition, u.nonRoofElevationStructure, u.nonRoofExistingSecurity," + 
			" u.nonRoofPatioCoverValue, u.patioCoverAttachedTypeBeam, u.patioCoverAttachedTypePosts," + 
			" u.patioCoverFreestandingTypeBeam, u.patioCoverFreestandingTypePosts," + 
			" u.patioCoverFreestandingExtendOver, u.patioCoverFreestandingPastEave, u.otherVoltageOther," + 
			" u.widthSeamsOther, u.roofMaterialTypeOther, u.postSolarRateOther," + 
			" u.otherPatioCoverAttachedTypeBeam, u.otherPatioCoverAttachedTypePosts," + 
			" u.otherPatioCoverFreestandingTypeBeam, u.otherPatioCoverFreestandingTypePosts," + 
			" u.imageOfSiteInformationRafter, u.imageOfExistingMainPanel, u.imageOfExistingSubPanel," + 
			" u.imageOfInternetConnectionRoof, u.imageOfInternetConnectionElevation," + 
			" u.existingACDisco, u.addressLine2, u.projectJurisOther, u.otherTallStructure," + 
			" u.roofMounted, u.groundMounted, u.carportMounted, u.textOther," + 
			" u.subPanelBreakerOCPD, u.roofRafterOther, u.secroofRafterOther," + 
			" u.frontAndBack, u.cantelever, u.attachedToExtWal, u.attachedToFascia, u.attachedToSkylifts, u.freeStanding, u.roofOrOpenFrame," + 
			" u.sumofexistCircuit, u.secondOtherVoltageOther, u.networkName," + 
			" u.networkPassword, u.roofCondition, u.roofNotes, u.measurmentsOfArea," + 
			" u.notesGroundMount, u.notesOnCarpotOrPatiot, u.cityOther, u.otherMainPanelMake," + 
			" u.otherMounted, u.pvModuleCarpotPatio.id, u.pvModuleNonRoof.id, u.utilityCompanyNameOther," + 
			" u.measOfAreaCarpot, u.homeOwnLastName, u.projectName,u.imageOfCarPortArrayLocation,u.patioMounted) " + 
			"  from SiteSurveyEntity u WHERE u.id = :p1")
	SiteSurveyResult getSiteSurveyResult(@Param("p1") Long siteSurveyID);
	
	@Query(value = " SELECT new com.PlayGroundAdv.Solar.model.PermitSketchResults(  "
			+ " u.arraySketch,  " + " u.azimuth,  " + " u.roofPitch,  " + " u.moduleTils,  " + " u.eaveOverHang,  "
			+ " u.eaveOverHangOther,   " + "  u.modelvalue, " + " u.moduleQty" + "  ) "
			+ " from SiteSurveyArrayChartEntity u " + " where u.siteSurveyEntity.id = :p1   " + " ORDER BY u.arraySketch  ")
	List<PermitSketchResults> getSiteSurveyArrayChart(@Param("p1") Long id);
	
	@Query(value = " SELECT new com.PlayGroundAdv.Solar.model.PermitSketchResults(  "
			+ " u.arraySketch,  " + " u.azimuth,  " + " u.roofPitch,  " + " u.moduleTils,  " + " u.eaveOverHang,  "
			+ " u.eaveOverHangOther,   " + "  u.modelvalue, " + " u.moduleQty" + "  ) "
			+ " from SiteSurveyArrayChartPatioEntity u " + " where u.siteSurveyEntity.id = :p1  " + " ORDER BY u.arraySketch  ")
	List<PermitSketchResults> getSiteSurveyArrayChartPatio(@Param("p1") Long id);
	
	@Query(value = " SELECT new com.PlayGroundAdv.Solar.model.PermitSketchResults(  "
			+ " u.arraySketch,  " + " u.azimuth,  " + " u.roofPitch,  " + " u.moduleTils,  " + " u.eaveOverHang,  "
			+ " u.eaveOverHangOther,   " + "  u.modelvalue, " + " u.moduleQty" + "  ) "
			+ " from SiteSurveyArrayChartNonRoofEntity u " + " where u.siteSurveyEntity.id = :p1  " + " ORDER BY u.arraySketch  ")
	List<PermitSketchResults> getSiteSurveyArrayChartNonRoof(@Param("p1") Long id);
	
	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.PermitResponse " + 
			" (u.id, u.homeOwnName, u.advancement, u.status," + 
			" u.creationPermitDate, u.updateDate, u.submitted, u.homeOwnLastName, u.projectName) " + 
			" from PermitEntity u where u.isCanceled = :p1 ")
	List<PermitResponse> getPermitResponse(@Param("p1") Boolean isCancel);
	
	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.PermitResponse " + 
			" (u.id, u.homeOwnName, u.advancement, u.status," + 
			" u.creationPermitDate, u.updateDate, u.submitted, u.homeOwnLastName, u.projectName) " + 
			" from PermitEntity u where u.isCanceled = :p1 and u.authentificationEntity.id = :p2")
	List<PermitResponse> getPermitResponseById(@Param("p1") Boolean isCancel, @Param("p2") Long idUser);
	
	@Query("SELECT u from SiteSurveyEntity u where u.id != :p4 AND ((TRIM(LOWER(u.homeOwnName)) =:p1 AND TRIM(LOWER(u.homeOwnLastName)) =:p2 AND (:p1 is NOT NULL OR :p2 is NOT NULL)) OR (:p1 is NULL AND :p2 is NULL AND TRIM(LOWER(u.projectName)) =:p3)  OR (:p1 is NULL AND :p2 is NULL AND :p3 is NOT NULL AND u.homeOwnName is NOT NULL AND u.homeOwnLastName is NOT NULL AND CONCAT(TRIM(LOWER(u.homeOwnLastName)),', ',TRIM(LOWER(u.homeOwnName))) =:p3))  ")
	List<SiteSurveyEntity> getListSiteSurvey(@Param("p1") String name, @Param("p2") String homeOwnLastName, @Param("p3") String projectName, @Param("p4") Long idSiteSurvey);
	
}
