package com.PlayGroundAdv.Solar.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.AuthentificationEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.model.InfoPdfPackageDrafterResult;
import com.PlayGroundAdv.Solar.model.PermitResponse;
import com.PlayGroundAdv.Solar.model.PermitResponsePrime;
import com.PlayGroundAdv.Solar.model.PermitResult;
import com.PlayGroundAdv.Solar.model.TemplateModelResponse;
import com.PlayGroundAdv.Solar.model.project.MondayAPIModel;
import com.PlayGroundAdv.Solar.model.project.ProjectsChart;

@Repository
public interface PermitRepository extends JpaRepository<PermitEntity, Long> {

	List<PermitEntity> findByAuthentificationEntityId(Long userID);

	PermitEntity findByIdAndIsCanceled(Long idPermit, Boolean isCancel);

	PermitEntity findOneByIdAndIsCanceled(Long idPermit, Boolean isCancel);

	PermitEntity findByWebSocketSessionAndOpenedTrue(String session);

	List<PermitEntity> findByOpenedTrue();

	// A.B 11-12
	public static final String FIND_BY_CREATION_PERMIT_DATE = "SELECT u.id from PermitEntity u where u.creationPermitDate > :p1";

	@Query(value = FIND_BY_CREATION_PERMIT_DATE)
	public List<Long> findByCreationPermitDate(@Param("p1") Date date);

	@Query(value = "SELECT u.dateOfSubmitPermit from PermitEntity u WHERE u.id = :p1 ")
	public Date findSubmissionDateByID(@Param("p1") Long id);

	@Query(value = "SELECT u.authentificationEntity from PermitEntity u WHERE u.id = :p1 ")
	public AuthentificationEntity findAuthentificationEntityByID(@Param("p1") Long id);

	@Query(value = "SELECT u.authentificationEntity.id from PermitEntity u WHERE u.id = :p1 ")
	public Long findProjectOwnerID(@Param("p1") Long id);

	@Query(value = "SELECT u.authentificationEntity.email from PermitEntity u WHERE u.id = :p1 ")
	public String findProjectOwnerEmail(@Param("p1") Long id);

	@Query(value = "SELECT u.authentificationEntity.active from PermitEntity u WHERE u.id = :p1 ")
	Boolean isUserActive(@Param("p1") Long id);

	@Query(value = "SELECT u.authentificationEntity.deleted from PermitEntity u WHERE u.id = :p1 ")
	Boolean isUserDeleted(@Param("p1") Long id);

	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.project.ProjectsChart(TO_CHAR(u.creationPermitDate, 'YYYY-MM-DD'),count(*)) from PermitEntity u Group BY TO_CHAR(u.creationPermitDate, 'YYYY-MM-DD') ORDER BY TO_CHAR(u.creationPermitDate, 'YYYY-MM-DD')")
	List<ProjectsChart> findProjectCountPerDay();

	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.project.ProjectsChart(TO_CHAR(u.creationPermitDate, 'YYYY-MM-DD'),count(*)) "
			+ "from PermitEntity u " + "WHERE u.authentificationEntity.id = :p1 "
			+ "Group BY TO_CHAR(u.creationPermitDate, 'YYYY-MM-DD') "
			+ "ORDER BY TO_CHAR(u.creationPermitDate, 'YYYY-MM-DD') ")
	List<ProjectsChart> findProjectCountPerDayPerUser(@Param("p1") Long idUser);

	@Query(value = "SELECT count(*) as nb from PermitEntity u ")
	public Long numberOfPermits();

	@Query(value = "SELECT u.creationPermitDate from PermitEntity u ORDER BY u.creationPermitDate")
	List<Date> creationPermitDates();

	@Query(value = "SELECT count(u)>0 from PermitEntity u where u.isDeleted = false AND (TRIM(LOWER(u.projectName)) =:p1 OR (u.projectName is NULL AND u.homeOwnLastName is NOT NULL AND u.homeOwnName is NOT NULL AND CONCAT (TRIM(LOWER(u.homeOwnLastName)), ', ' , TRIM(LOWER(u.homeOwnName)))=:p1))")
	Boolean isProjectNameExist(@Param("p1") String projectName);

	@Query(value = "SELECT count(u)>0 from PermitEntity u where u.isDeleted = false AND (:p1 is NOT NULL AND :p2 is NOT NULL AND (u.projectName is NOT NULL AND TRIM(LOWER(u.projectName)) = CONCAT(:p2,', ',:p1) OR u.projectName is NULL AND CONCAT (TRIM(LOWER(u.homeOwnLastName)), ', ' , TRIM(LOWER(u.homeOwnName))) = CONCAT(:p2,', ',:p1)))")
	Boolean isHomeownerNameExist(@Param("p1") String firstName, @Param("p2") String lastName);

	@Query(value = "SELECT count(u)>0 from PermitEntity u where u.isDeleted = false AND ((TRIM(LOWER(u.homeOwnName)) =:p1 AND TRIM(LOWER(u.homeOwnLastName)) =:p2 AND (:p1 is NOT NULL OR :p2 is NOT NULL)) OR (:p1 is NULL AND :p2 is NULL AND TRIM(LOWER(u.projectName)) =:p3)  OR (:p1 is NULL AND :p2 is NULL AND :p3 is NOT NULL AND u.homeOwnName is NOT NULL AND u.homeOwnLastName is NOT NULL AND CONCAT(TRIM(LOWER(u.homeOwnLastName)),', ',TRIM(LOWER(u.homeOwnName))) =:p3)) AND u.id != :p4")
	Boolean isProjectExist(@Param("p1") String firstName, @Param("p2") String lastName, @Param("p3") String projectName,
			@Param("p4") Long id);

	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.PermitResult (u.id, u.homeOwnName, u.advancement, u.status, u.updateDate, u.creationPermitDate, u.submitted)"
			+ " from PermitEntity u where u.authentificationEntity.id = :p1 ORDER BY u.creationPermitDate")
	List<PermitResult> getAllPermitForChart(@Param("p1") Long idUser);

	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.PermitResult (u.id, u.homeOwnName, u.advancement, u.status, u.updateDate, u.creationPermitDate, u.submitted)"
			+ " from PermitEntity u where u.isDeleted = false")
	List<PermitResult> getAllPermitForChartTwo();

	@Query(value = "SELECT u.RRVersion  from PermitEntity u  where u.id = :p1 ")
	Integer findRRVersion(@Param("p1") Long idUser);

	public static final String GET_DRAFTER_PERMIT = "SELECT new com.PlayGroundAdv.Solar.model.PermitResponsePrime (u.id, u.homeOwnName, u.advancement, u.status, u.updateDate, CONCAT(user.firstName,' ',user.lastName), u.submitted, "
			+ " u.creationPermitDate, u.authentificationEntity.firstName,  u.authentificationEntity.lastName, u.dateOfSubmitPermitOnHold, u.dateOfSubmitPermit, u.isCanceled,"
			+ " u.dateOfSubmitPermitCanceled, u.isOnHold, u.projectName, u.homeOwnLastName, u.plansetDriveId, u.authentificationEntity.company, "
			+ " TRIM(CASE WHEN u.projectName is not null AND u.projectName <> '' THEN u.projectName WHEN u.homeOwnLastName is not null AND u.homeOwnLastName <> ''  THEN CONCAT(u.homeOwnLastName,', ',u.homeOwnName) ELSE u.homeOwnName END),"
			+ " TRIM(CASE WHEN u.authentificationEntity.company is not null AND u.authentificationEntity.company <> '' THEN u.authentificationEntity.company ELSE CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName) END),u.archiveLink,u.archiveStatus,archive.archiveDate)"
			+ " from PermitEntity u left join u.updatedBy as user left join u.archive as archive  "
			+ " where (u.isTemplate = false or u.isTemplate is NULL) and u.isDeleted = false ";

	@Query(value = GET_DRAFTER_PERMIT + PERMIT_RESPONSE_ORDER_BY)
	Page<PermitResponsePrime> getDrafterPermit(Pageable pageable);

	@Query(value = GET_DRAFTER_PERMIT + ORDER_BY_PER)
	Page<PermitResponsePrime> getDrafterPermitSort(@Param("sortParam") String sortParam, Pageable pageable);

	@Query(value = GET_DRAFTER_PERMIT + PERMIT_RESPONSE_SEARCH_CONDITION + PERMIT_RESPONSE_ORDER_BY)
	Page<PermitResponsePrime> getSearchedPermitsDrafter(@Param("p") String filteredValue, Pageable pageable);

	@Query(value = GET_DRAFTER_PERMIT + PERMIT_RESPONSE_SEARCH_CONDITION
			+ ORDER_BY_PER, countQuery = "select count(u) from PermitEntity u left join u.updatedBy as user  where (u.isTemplate = false or u.isTemplate is NULL) and u.isDeleted = false "
					+ PERMIT_RESPONSE_SEARCH_CONDITION
					+ " GROUP BY u, u.authentificationEntity.firstName, u.authentificationEntity.lastName, u.authentificationEntity.company "
					+ ORDER_BY_PER)
	Page<PermitResponsePrime> getSearchedPermitsDrafterSort(@Param("p") String filteredValue,
			@Param("sortParam") String sortParam, Pageable pageable);

	public static final String GET_ALL_PROJECTS_BY_USER_AND_COMPANY = "SELECT new com.PlayGroundAdv.Solar.model.PermitResponsePrime (u.id, u.homeOwnName, u.advancement, u.status, u.updateDate, u.submitted, "
			+ " u.creationPermitDate, u.authentificationEntity.firstName,  u.authentificationEntity.lastName, u.dateOfSubmitPermitOnHold, u.dateOfSubmitPermit, u.isCanceled,"
			+ " u.dateOfSubmitPermitCanceled, u.isOnHold, u.projectName, u.homeOwnLastName, u.plansetDriveId, u.authentificationEntity.company,"
			+ " TRIM(CASE WHEN u.projectName is not null AND u.projectName <> '' THEN u.projectName WHEN u.homeOwnLastName is not null AND u.homeOwnLastName <> ''  THEN CONCAT(u.homeOwnLastName,', ',u.homeOwnName) ELSE u.homeOwnName END),"
			+ " TRIM(CASE WHEN u.authentificationEntity.company is not null AND u.authentificationEntity.company <> '' THEN u.authentificationEntity.company ELSE CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName) END),u.archiveLink,u.archiveStatus,archive.archiveDate)"
			+ " from PermitEntity u  left join u.archive as archive "
			+ " where (u.authentificationEntity.id = :p2 or ( u.authentificationEntity.company is not NULL and u.authentificationEntity.company != '' and UPPER(TRIM(u.authentificationEntity.company)) = :p1 ) ) and  u.isDeleted = false  and (u.isTemplate = false or u.isTemplate is NULL)";

	@Query(value = GET_ALL_PROJECTS_BY_USER_AND_COMPANY + PERMIT_RESPONSE_ORDER_BY)
	Page<PermitResponsePrime> getAllProjectsByUserAndCompany(@Param("p1") String company, @Param("p2") Long userID,
			Pageable pageable);

	@Query(value = GET_ALL_PROJECTS_BY_USER_AND_COMPANY
			+ ORDER_BY_PER, countQuery = "select count(u) from PermitEntity u where (u.authentificationEntity.id = :p2 or ( u.authentificationEntity.company is not NULL and u.authentificationEntity.company != '' and UPPER(TRIM(u.authentificationEntity.company)) = :p1 ) ) and  u.isDeleted = false  and (u.isTemplate = false or u.isTemplate is NULL) GROUP BY u, u.authentificationEntity.firstName, u.authentificationEntity.lastName, u.authentificationEntity.company "
					+ ORDER_BY_PER)
	Page<PermitResponsePrime> getAllProjectsByUserAndCompanySort(@Param("p1") String company, @Param("p2") Long userID,
			@Param("sortParam") String sortParam, Pageable pageable);

	@Query(value = GET_ALL_PROJECTS_BY_USER_AND_COMPANY + PERMIT_RESPONSE_SEARCH_CONDITION_CONTRACTOR
			+ PERMIT_RESPONSE_ORDER_BY)
	Page<PermitResponsePrime> getSearchedPermitsContractor(@Param("p1") String company, @Param("p2") Long userID,
			@Param("p") String filteredValue, Pageable pageable);

	@Query(value = GET_ALL_PROJECTS_BY_USER_AND_COMPANY + PERMIT_RESPONSE_SEARCH_CONDITION_CONTRACTOR
			+ ORDER_BY_PER, countQuery = "select count(u) from PermitEntity u where (u.authentificationEntity.id = :p2 or ( u.authentificationEntity.company is not NULL and u.authentificationEntity.company != '' and UPPER(TRIM(u.authentificationEntity.company)) = :p1 ) ) and  u.isDeleted = false  and (u.isTemplate = false or u.isTemplate is NULL) "
					+ PERMIT_RESPONSE_SEARCH_CONDITION_CONTRACTOR
					+ " GROUP BY u, u.authentificationEntity.firstName, u.authentificationEntity.lastName, u.authentificationEntity.company "
					+ ORDER_BY_PER)
	Page<PermitResponsePrime> getSearchedPermitsContractorSort(@Param("p1") String company, @Param("p2") Long userID,
			@Param("sortParam") String sortParam, @Param("p") String filteredValue, Pageable pageable);

	public static final String SEARCH_PROJECT = "Select new com.PlayGroundAdv.Solar.model.PermitResponsePrime (u.id, u.homeOwnName, u.advancement, u.status, u.updateDate, CONCAT(user.firstName,' ',user.lastName),u.submitted, u.creationPermitDate, u.authentificationEntity.firstName, u.authentificationEntity.lastName,u.dateOfSubmitPermitOnHold,u.dateOfSubmitPermit,u.isCanceled,u.dateOfSubmitPermitCanceled,u.isOnHold,u.projectName, u.homeOwnLastName, u.plansetDriveId,"
			+ " TRIM(CASE WHEN u.projectName is not null AND u.projectName <> '' THEN u.projectName WHEN u.homeOwnLastName is not null AND u.homeOwnLastName <> ''  THEN CONCAT(u.homeOwnLastName,', ',u.homeOwnName) ELSE u.homeOwnName END),"
			+ " TRIM(CASE WHEN u.authentificationEntity.company is not null AND u.authentificationEntity.company <> '' THEN u.authentificationEntity.company ELSE CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName) END),u.archiveLink,u.archiveStatus,archive.archiveDate)"
			+ " FROM PermitEntity u left join u.archive as archive, PermitHomeSiteInfoEntity h, PermitArraysEntity r, PermitProjectSiteInfoEntity p,"
			+ " PermitAdditionalInfoEntity a, PermitEnergyBatterySystem b, PermitEngineerEntity e left join u.updatedBy as user"
			+ " WHERE u.isDeleted = false AND (u.isTemplate = false or u.isTemplate is null) AND u.id=h.permitEntity.id AND u.id=r.permitEntity.id AND u.id=p.permitEntity.id AND u.id=a.permitEntity.id AND u.id = b.project.id AND u.id = e.permitEntity.id "
			+ " AND ((:p22 is NOT NULL AND :p23 is NOT NULL AND u.creationPermitDate BETWEEN :startDate AND :endDate) OR (:p22 is NOT NULL AND :p23 is NULL AND u.creationPermitDate BETWEEN :startDateSameMonth AND :endDateSameMonth) OR :p22 is NULL)"
			+ " AND (:p1 = '' OR (:p1 != '' AND (TRIM(LOWER(h.projectJurisdiction)) LIKE CONCAT('%',:p1,'%')) OR (h.projectJurisdiction = 'city' AND ( (TRIM(LOWER(h.projectJurisOther))) LIKE CONCAT('%',:p1,'%') OR (TRIM(LOWER(h.city))) LIKE CONCAT('%',:p1,'%')) ) OR (h.projectJurisdiction = 'Other' AND (TRIM(LOWER(h.projectJurisOther))) LIKE CONCAT('%',:p1,'%'))))"
			+ " AND (:p2 = '' OR (:p2 != '' AND (TRIM(LOWER(h.city)) LIKE CONCAT('%',:p2,'%')) OR (h.city = 'Other' AND TRIM(LOWER(h.cityOther)) LIKE CONCAT('%',:p2,'%'))))"
			+ " AND (TRIM(LOWER(h.state)) =:p3 OR :p3 is NULL) AND (h.roofRafter =:p5 OR h.roofRafterOther=:p5 OR (h.roofRafterOther='OTHER' AND (TRIM(LOWER(h.secroofRafterOther)))=:p20) OR :p5 is NULL)"
			+ " AND (p.roofMaterialType =:p6 OR :p6 is NULL)" + " AND (p.railConnectionModel =:p7 OR :p7 is NULL)"
			+ " AND (p.railRakingModel.id =:p8 OR (r.GroundMounted = true AND p.railRakingModelforGroundMounted.id =:p8) OR p.railRakingforPatioMounted.id =:p8 OR p.railRakingforCarport.id =:p8 OR :p8 is NULL)"
			+ " AND (r.firstInverter.id =:p9 OR r.secondInverter.id =:p9 OR r.thirdInverter.id =:p9 OR r.fourthInverter.id =:p9 OR r.fifthInverter.id =:p9 OR r.microInverterEntity.id =:p9 OR :p9 is NULL)"
			+ " AND (r.pvModule.id =:pvModule OR :pvModule is NULL)"
			+ " AND (e.engineeredBy =:engineerBy OR :engineerBy is NULL)" + " AND (b.idAts.id =:ats OR :ats is NULL)"
			+ " AND (b.idAts.id is not null OR :includeATS is false)"
			+ " AND (b.typeGridTied =:typeGridTied OR :typeGridTied is NULL)"
			+ " AND (p.solarLocation =:poc OR :poc is NULL)" + " AND (u.id in (:p10) OR :p10 is NULL) "
			+ " AND ((r.roofMounted =true AND :p15 ='Roof Mounted') OR (r.GroundMounted=true AND :p15 ='Ground Mounted') OR (r.carportMounted=true AND :p15 ='Carport') OR (r.patioMounted=true AND :p15 ='Patio Cover') OR (r.otherMounted=true AND :p15 ='Other' AND :p16 = (TRIM(LOWER(r.textOther)))) OR :p15 is NULL)"
			+ " AND (a.tiltLegsMod =:p11 OR :p11 is NULL) AND ((h.utilityCompanyName ='Other' AND h.UtilityCompanyNameOther =:p18) OR (h.utilityCompanyName <>'Other' AND h.utilityCompanyName IN (SELECT CONCAT(e.id,'')"
			+ " FROM ElectricalUtilityEntity e WHERE e.utilityCompanyName =:p4)) OR :p4 is NULL) AND ((a.requiredElectricalStamp=true AND :p21='Electrical Engineer') OR ((a.requiredElectricalStamp=false OR a.requiredElectricalStamp=null) AND :p21='Structural Engineer') OR :p21 is NULL) ";

	public static final String SEARCH_PROJECT_COUNT_QUERY = "Select count(u) FROM PermitEntity u, PermitHomeSiteInfoEntity h, PermitArraysEntity r, PermitProjectSiteInfoEntity p,"
			+ " PermitAdditionalInfoEntity a, PermitEnergyBatterySystem b, PermitEngineerEntity e left join u.updatedBy as user"
			+ " WHERE u.isDeleted = false AND (u.isTemplate = false or u.isTemplate is null) AND u.id=h.permitEntity.id AND u.id=r.permitEntity.id AND u.id=p.permitEntity.id AND u.id=a.permitEntity.id AND u.id = b.project.id AND u.id = e.permitEntity.id "
			+ " AND ((:p22 is NOT NULL AND :p23 is NOT NULL AND u.creationPermitDate BETWEEN :startDate AND :endDate) OR (:p22 is NOT NULL AND :p23 is NULL AND u.creationPermitDate BETWEEN :startDateSameMonth AND :endDateSameMonth) OR :p22 is NULL)"
			+ " AND (:p1 = '' OR (:p1 != '' AND (TRIM(LOWER(h.projectJurisdiction)) LIKE CONCAT('%',:p1,'%')) OR (h.projectJurisdiction = 'city' AND ( (TRIM(LOWER(h.projectJurisOther))) LIKE CONCAT('%',:p1,'%') OR (TRIM(LOWER(h.city))) LIKE CONCAT('%',:p1,'%')) ) OR (h.projectJurisdiction = 'Other' AND (TRIM(LOWER(h.projectJurisOther))) LIKE CONCAT('%',:p1,'%'))))"
			+ " AND (:p2 = '' OR (:p2 != '' AND (TRIM(LOWER(h.city)) LIKE CONCAT('%',:p2,'%')) OR (h.city = 'Other' AND TRIM(LOWER(h.cityOther)) LIKE CONCAT('%',:p2,'%'))))"
			+ " AND (TRIM(LOWER(h.state)) =:p3 OR :p3 is NULL) AND (h.roofRafter =:p5 OR h.roofRafterOther=:p5 OR (h.roofRafterOther='OTHER' AND (TRIM(LOWER(h.secroofRafterOther)))=:p20) OR :p5 is NULL)"
			+ " AND (p.roofMaterialType =:p6 OR :p6 is NULL)" + " AND (p.railConnectionModel =:p7 OR :p7 is NULL)"
			+ " AND (p.railRakingModel.id =:p8 OR (r.GroundMounted = true AND p.railRakingModelforGroundMounted.id =:p8) OR p.railRakingforPatioMounted.id =:p8 OR p.railRakingforCarport.id =:p8 OR :p8 is NULL)"
			+ " AND (r.firstInverter.id =:p9 OR r.secondInverter.id =:p9 OR r.thirdInverter.id =:p9 OR r.fourthInverter.id =:p9 OR r.fifthInverter.id =:p9 OR r.microInverterEntity.id =:p9 OR :p9 is NULL)"
			+ " AND (r.pvModule.id =:pvModule OR :pvModule is NULL)"
			+ " AND (e.engineeredBy =:engineerBy OR :engineerBy is NULL)" + " AND (b.idAts.id =:ats OR :ats is NULL)"
			+ " AND (b.idAts.id is not null OR :includeATS is false)"
			+ " AND (b.typeGridTied =:typeGridTied OR :typeGridTied is NULL)"
			+ " AND (p.solarLocation =:poc OR :poc is NULL)" + " AND (u.id in (:p10) OR :p10 is NULL) "
			+ " AND ((r.roofMounted =true AND :p15 ='Roof Mounted') OR (r.GroundMounted=true AND :p15 ='Ground Mounted') OR (r.carportMounted=true AND :p15 ='Carport') OR (r.patioMounted=true AND :p15 ='Patio Cover') OR (r.otherMounted=true AND :p15 ='Other' AND :p16 = (TRIM(LOWER(r.textOther)))) OR :p15 is NULL)"
			+ " AND (a.tiltLegsMod =:p11 OR :p11 is NULL) AND ((h.utilityCompanyName ='Other' AND h.UtilityCompanyNameOther =:p18) OR (h.utilityCompanyName <>'Other' AND h.utilityCompanyName IN (SELECT CONCAT(e.id,'')"
			+ " FROM ElectricalUtilityEntity e WHERE e.utilityCompanyName =:p4)) OR :p4 is NULL) AND ((a.requiredElectricalStamp=true AND :p21='Electrical Engineer') OR ((a.requiredElectricalStamp=false OR a.requiredElectricalStamp=null) AND :p21='Structural Engineer') OR :p21 is NULL) ";

	@Query(value = SEARCH_PROJECT + PERMIT_RESPONSE_ORDER_BY)
	Page<PermitResponsePrime> searchProject(@Param("p1") String projectJurisdiction, @Param("p2") String city,
			@Param("p3") String state, @Param("p4") String utilityCompany, @Param("p5") String roofRafter,
			@Param("p6") Long roofMaterial, @Param("p7") String railToRoof, @Param("p8") Long searchRailRaiking,
			@Param("p9") Long searchedInverterModel, @Param("p10") List<Long> projectBybattery,
			@Param("p11") String tiltLeg, @Param("p15") String tTypeOfSystem, @Param("p16") String typeOfSystemOther,
			@Param("p18") String utilityCompanyOther, @Param("p20") String roofRafterOther,
			@Param("p21") String engineerType, @Param("pvModule") Long pvModule, @Param("engineerBy") String engineerBy,
			@Param("includeATS") Boolean includeATS, @Param("ats") Long ats, @Param("typeGridTied") String typeGridTied,
			@Param("poc") String poc, @Param("startDate") Date dateBeforeRange, @Param("endDate") Date dateAfterRange,
			@Param("p22") String creationDate, @Param("p23") Integer dateRange,
			@Param("startDateSameMonth") Date dateBeforeSameMonth, @Param("endDateSameMonth") Date dateAfterSameMonth,
			Pageable pageable);

	@Query(value = SEARCH_PROJECT + ORDER_BY_PER, countQuery = SEARCH_PROJECT_COUNT_QUERY
			+ " GROUP BY u, u.authentificationEntity.firstName, u.authentificationEntity.lastName, u.authentificationEntity.company "
			+ ORDER_BY_PER)
	Page<PermitResponsePrime> searchProjectSort(@Param("p1") String projectJurisdiction, @Param("p2") String city,
			@Param("p3") String state, @Param("p4") String utilityCompany, @Param("p5") String roofRafter,
			@Param("p6") Long roofMaterial, @Param("p7") String railToRoof, @Param("p8") Long searchRailRaiking,
			@Param("p9") Long searchedInverterModel, @Param("p10") List<Long> projectBybattery,
			@Param("p11") String tiltLeg, @Param("p15") String tTypeOfSystem, @Param("p16") String typeOfSystemOther,
			@Param("p18") String utilityCompanyOther, @Param("p20") String roofRafterOther,
			@Param("p21") String engineerType, @Param("pvModule") Long pvModule, @Param("engineerBy") String engineerBy,
			@Param("includeATS") Boolean includeATS, @Param("ats") Long ats, @Param("typeGridTied") String typeGridTied,
			@Param("poc") String poc, @Param("startDate") Date dateBeforeRange, @Param("endDate") Date dateAfterRange,
			@Param("p22") String creationDate, @Param("p23") Integer dateRange,
			@Param("startDateSameMonth") Date dateBeforeSameMonth, @Param("endDateSameMonth") Date dateAfterSameMonth,
			@Param("sortParam") String sortParam, Pageable pageable);

	@Modifying
	@Query("update PermitEntity u set u.status = 'Delivered', u.isOnHold = false where u.dateOfSubmitPermitOnHold < :date and u.status = 'On Hold' and u.isDeleted = false")
	void deliverOnHoldProject(@Param("date") Date date);

	public static final String FIND_PACKAGE_DRAFTER_RESULT = "SELECT new com.PlayGroundAdv.Solar.model.InfoPdfPackageDrafterResult( "
			+ " u.authentificationEntity.company, u.homeOwnName, v.siteAddress, "
			+ " v.addressLine2, v.city, v.state, v.postalCode, v.propertyAPN, "
			+ " v.cityOther, v.utilityCompanyName, v.UtilityCompanyNameOther, "
			+ " w.tallStructure, w.otherTallStructure, w.roofMaterialType, "
			+ " w.roofMaterialTypeOther, w.crossSectionSize, w.textOtherSize, "
			+ " w.rafterTrussSpacing, w.textOtherRatfter, s.pvModule.id, s.stringOne, "
			+ " s.stringTwo, s.stringThree, s.stringFour, s.stringFive, "
			+ " s.secondStringOne, s.secondStringTwo, s.secondStringThree, "
			+ " s.secondStringFour, s.secondStringFive, s.thirdStringOne, s.thirdStringTwo, "
			+ " s.thirdStringThree, s.thirdStringFour, s.thirdStringFive, s.fourthStringOne, "
			+ " s.fourthStringTwo, s.fourthStringThree, s.fourthStringFour, "
			+ " s.fourthStringFive, s.fifthStringOne, s.fifthStringTwo, s.fifthStringThree, "
			+ " s.fifthStringFour, s.fifthStringFive, s.stringSix, s.stringSeven, "
			+ " s.stringEight, s.stringNine, s.stringTen, s.stringEleven, "
			+ " s.stringTwelve, s.secondStringSix, s.secondStringSeven, s.secondStringEight, "
			+ " s.secondStringNine, s.secondStringTen, s.secondStringEleven, "
			+ " s.secondStringTwelve, s.thirdStringSix, s.thirdStringSeven, "
			+ " s.thirdStringEight, s.thirdStringNine, s.thirdStringTen, "
			+ " s.thirdStringEleven, s.thirdStringTwelve, s.fourthStringSix, "
			+ " s.fourthStringSeven, s.fourthStringEight, s.fourthStringNine, "
			+ " s.fourthStringTen, s.fourthStringEleven, s.fourthStringTwelve, "
			+ " s.fifthStringSix, s.fifthStringSeven, s.fifthStringEight, s.fifthStringNine, "
			+ " s.fifthStringTen, s.fifthStringEleven, s.fifthStringTwelve, w.inverterModel, " + " 0L,0L, "
			+ " s.roofMounted, s.GroundMounted, " + " t.qtySegmentOne, t.qtySegmentSix, x.formatSize, "
			+ " x.tiltLegs, y.sketchNote, v.stanchionMaxSpacing, "
			+ " v.stanchionMaxSpacingOther, u.homeOwnLastName, u.projectName, z.stanchionsType,"
			+ " y.ignoreVents, y.firesetbacks, y.firesetbacksNote, y.fireVariance, y.fireVarianceNote,  y.modulesEncroaching,  "
			+ " u.insertRoofNote, w.uploadComments,s.uploadCommentsLayout, s.uploadCommentsAddInfo, adv.uploadCommentsGoogle, adv.uploadCommentsNearMap,  "
			+ " w.installationGuidelines, x.uploadComments, adv.moduleLayout, adv.moduleLayoutOther, v.projectJurisdiction, v.projectJurisOther )"
			+ " from PermitEntity u, PermitHomeSiteInfoEntity v,"
			+ "	PermitProjectSiteInfoEntity w, PermitArraysEntity s,"
			+ "	PermitConduitConductorSectionEntity t, PermitAdditionalInfoEntity x, "
			+ " UserSettingEntity z, PermitLayoutEntity y, PermitAdvEntity adv where u.id = :p1 "
			+ " and v.permitEntity.id = :p1 and w.permitEntity.id = :p1 "
			+ " and s.permitEntity.id = :p1 and t.permitEntity.id = :p1 "
			+ " and x.permitEntity.id = :p1 and y.permitEntity.id = :p1 "
			+ " and z.userId.id = u.authentificationEntity.id and adv.permitEntity.id = :p1";

	@Query(value = FIND_PACKAGE_DRAFTER_RESULT)
	InfoPdfPackageDrafterResult findPackageDrafterResult(@Param("p1") Long idPermit);

	@Query("SELECT new com.PlayGroundAdv.Solar.model.TemplateModelResponse(" + "u.id, " + "u.homeOwnName, "
			+ "u.creationPermitDate, " + "u.updateDate, " + "u.authentificationEntity.firstName, "
			+ "u.authentificationEntity.lastName, u.templateName, u.authentificationEntity.company) "
			+ "FROM  PermitEntity u " + "where u.isDeleted = false "
			+ "and u.isTemplate = true ORDER BY u.creationPermitDate DESC")
	List<TemplateModelResponse> getAllTemplates();

	@Query("SELECT new com.PlayGroundAdv.Solar.model.TemplateModelResponse(" + "u.id, " + "u.homeOwnName, "
			+ "u.creationPermitDate, " + "u.updateDate, " + "u.authentificationEntity.firstName, "
			+ "u.authentificationEntity.lastName, u.templateName, u.authentificationEntity.company) "
			+ "FROM  PermitEntity u " + "WHERE u.isDeleted = false "
			+ "AND ( u.authentificationEntity.id = :p1 or ( u.authentificationEntity.company is not NULL and UPPER(TRIM(u.authentificationEntity.company)) = :p2 ) ) "
			+ "AND u.isTemplate = true ORDER BY u.creationPermitDate DESC")
	List<TemplateModelResponse> getAllTemplatesByUser(@Param("p1") Long idUser, @Param("p2") String company);

	public static final String PERMIT_RESPONSE_PRIME = "SELECT new com.PlayGroundAdv.Solar.model.PermitResponsePrime (u.id, u.homeOwnName, u.advancement, u.status, u.updateDate,"
			+ " CONCAT(user.firstName,' ',user.lastName),"
			+ " u.submitted, u.creationPermitDate, u.authentificationEntity.firstName, "
			+ " u.authentificationEntity.lastName,u.dateOfSubmitPermitOnHold,u.dateOfSubmitPermit,"
			+ " u.isCanceled,u.dateOfSubmitPermitCanceled,u.isOnHold,u.projectName,u.homeOwnLastName,"
			+ " u.plansetDriveId,u.authentificationEntity.company,"
			+ " TRIM(CASE WHEN u.projectName is not null AND u.projectName <> '' THEN u.projectName WHEN u.homeOwnLastName is not null AND u.homeOwnLastName <> ''  THEN CONCAT(u.homeOwnLastName,', ',u.homeOwnName) ELSE u.homeOwnName END),"
			+ " TRIM(CASE WHEN u.authentificationEntity.company is not null AND u.authentificationEntity.company <> '' THEN u.authentificationEntity.company ELSE CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName) END),u.archiveLink,u.archiveStatus,archive.archiveDate)"
			+ " from PermitEntity u left join u.updatedBy as user left join u.archive as archive"
			+ " where u.isTemplate is null or u.isTemplate = false ";

	public static final String PERMIT_RESPONSE_NOT_IN = " AND"
			+ " u.authentificationEntity.email  NOT LIKE '%nuagetechnologies%' AND"
			+ " u.authentificationEntity.email  NOT LIKE '%arij%' AND"
			+ " u.authentificationEntity.email  NOT LIKE '%soumaya%' AND"
			+ " u.authentificationEntity.email  NOT LIKE '%nabil%' ";

	public static final String PERMIT_RESPONSE_SEARCH_CONDITION = " AND" + " (LOWER(u.advancement) LIKE %:p% or"
			+ " LOWER(u.status) LIKE %:p% or" + " TO_CHAR(u.dateOfSubmitPermit, 'mm/dd/yyyy') LIKE %:p% or"
			+ " TO_CHAR(u.updateDate, 'mm/dd/yyyy') LIKE %:p% or"
			+ " TO_CHAR(u.creationPermitDate, 'mm/dd/yyyy') LIKE %:p% or"
			+ " LOWER(TRIM(CASE WHEN u.projectName is not null AND u.projectName <> '' THEN u.projectName WHEN u.homeOwnLastName is not null AND u.homeOwnLastName <> ''  THEN CONCAT(u.homeOwnLastName,', ',u.homeOwnName) ELSE u.homeOwnName END)) LIKE %:p% or"
			+ " LOWER(TRIM(CASE WHEN u.authentificationEntity.company is not null AND u.authentificationEntity.company <> '' THEN u.authentificationEntity.company ELSE CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName) END)) LIKE %:p% or"
			+ " LOWER(CONCAT(user.firstName,' ',user.lastName)) LIKE %:p% )";

	public static final String PERMIT_RESPONSE_SEARCH_CONDITION_CONTRACTOR = " AND"
			+ " (LOWER(u.advancement) LIKE %:p% or" + " LOWER(u.status) LIKE %:p% or"
			+ " TO_CHAR(u.dateOfSubmitPermit, 'mm/dd/yyyy') LIKE %:p% or"
			+ " TO_CHAR(u.updateDate, 'mm/dd/yyyy') LIKE %:p% or"
			+ " LOWER(TRIM(CASE WHEN u.projectName is not null AND u.projectName <> '' THEN u.projectName WHEN u.homeOwnLastName is not null AND u.homeOwnLastName <> ''  THEN CONCAT(u.homeOwnLastName,', ',u.homeOwnName) ELSE u.homeOwnName END)) LIKE %:p% or"
			+ " LOWER(TRIM(CASE WHEN u.authentificationEntity.company is not null AND u.authentificationEntity.company <> '' THEN u.authentificationEntity.company ELSE CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName) END)) LIKE %:p% or"
			+ " TO_CHAR(u.creationPermitDate, 'mm/dd/yyyy') LIKE %:p% )";

	public static final String PERMIT_RESPONSE_ORDER_BY = " ORDER BY"
			+ " CASE WHEN u.dateOfSubmitPermit is not null THEN u.dateOfSubmitPermit WHEN u.updateDate is not null THEN u.updateDate ELSE u.creationPermitDate END DESC,"
			+ " CASE WHEN u.updateDate is not null THEN u.updateDate ELSE u.creationPermitDate END DESC,"
			+ " u.creationPermitDate DESC";

	@Query(value = PERMIT_RESPONSE_PRIME + PERMIT_RESPONSE_ORDER_BY)
	List<PermitResponsePrime> getPermitNotTemplatesByUpdateBy();

	@Query(value = PERMIT_RESPONSE_PRIME + PERMIT_RESPONSE_ORDER_BY)
	Page<PermitResponsePrime> getPermitNotTemplatesByUpdateBy(Pageable pageable);

	@Query(value = PERMIT_RESPONSE_PRIME + PERMIT_RESPONSE_NOT_IN + PERMIT_RESPONSE_ORDER_BY)
	List<PermitResponsePrime> getPermitNotTemplatesByUpdateByNotIn();

	@Query(value = PERMIT_RESPONSE_PRIME + PERMIT_RESPONSE_NOT_IN + PERMIT_RESPONSE_ORDER_BY)
	Page<PermitResponsePrime> getPermitNotTemplatesByUpdateByNotIn(Pageable pageable);

	@Query(value = PERMIT_RESPONSE_PRIME + PERMIT_RESPONSE_NOT_IN + ORDER_BY_PER)
	Page<PermitResponsePrime> getPermitNotTemplatesByUpdateByNotInCol(@Param("sortParam") String sortParam,
			Pageable pageable);

	@Query(value = PERMIT_RESPONSE_PRIME + PERMIT_RESPONSE_SEARCH_CONDITION + PERMIT_RESPONSE_ORDER_BY)
	Page<PermitResponsePrime> getSearchedPermitsSuperUser(@Param("p") String filteredValue, Pageable pageable);

	@Query(value = PERMIT_RESPONSE_PRIME + PERMIT_RESPONSE_SEARCH_CONDITION
			+ ORDER_BY_PER, countQuery = "select count(u) from PermitEntity u left join u.updatedBy as user where u.isTemplate is null or u.isTemplate = false "
					+ PERMIT_RESPONSE_SEARCH_CONDITION
					+ " GROUP BY u, u.authentificationEntity.firstName, u.authentificationEntity.lastName, u.authentificationEntity.company "
					+ ORDER_BY_PER)
	Page<PermitResponsePrime> getSearchedPermitsSuperUserSort(@Param("p") String filteredValue,
			@Param("sortParam") String sortParam, Pageable pageable);

	@Query(value = PERMIT_RESPONSE_PRIME + PERMIT_RESPONSE_SEARCH_CONDITION + PERMIT_RESPONSE_NOT_IN
			+ PERMIT_RESPONSE_ORDER_BY)
	Page<PermitResponsePrime> getSearchedPermitsSuperUserNotIn(@Param("p") String filteredValue, Pageable pageable);

	@Query(value = PERMIT_RESPONSE_PRIME + PERMIT_RESPONSE_SEARCH_CONDITION + PERMIT_RESPONSE_NOT_IN
			+ ORDER_BY_PER, countQuery = "select count(u) from PermitEntity u left join u.updatedBy as user where u.isTemplate is null or u.isTemplate = false "
					+ PERMIT_RESPONSE_SEARCH_CONDITION + PERMIT_RESPONSE_NOT_IN
					+ " GROUP BY u, u.authentificationEntity.firstName, u.authentificationEntity.lastName, u.authentificationEntity.company "
					+ ORDER_BY_PER)
	Page<PermitResponsePrime> getSearchedPermitsSuperUserNotInSort(@Param("p") String filteredValue,
			@Param("sortParam") String sortParam, Pageable pageable);

	@Query("Select u  from PermitEntity u where u.isDeleted = false and  u.submitted = true "
			+ "and u.authentificationEntity.email not like '%nuagetechnologies%'  "
			+ "and u.authentificationEntity.email not like '%arij%'  "
			+ "and u.authentificationEntity.email not like '%soumaya%'  "
			+ "and u.authentificationEntity.email not like '%imen%'  "
			+ "and u.authentificationEntity.email not like '%baccar%'  "
			+ "and u.authentificationEntity.email not like '%nabil%'  "
			+ "and u.authentificationEntity.email not like '%malek%' ")
	List<PermitEntity> getPermitSubmitted();

	public static final String FIND_MONDAY_API_RESULT = "SELECT new com.PlayGroundAdv.Solar.model.project.MondayAPIModel( "
			+ " v.formattedAddress, v.siteAddress, v.city, v.cityOther, v.state, v.postalCode, v.latitude, v.longitude,"
			+ " a.batteryStorage, a.gridTiedOrStandalone, a.existSolarSystem, e.generatorIncluded,"
			+ " r.roofMounted, r.GroundMounted, r.pvModule.id, s.applicablEngineering, a.requiredElectricalStamp )"
			+ " from PermitHomeSiteInfoEntity v, PermitAdditionalInfoEntity a, PermitEnergyBatterySystem e, PermitArraysEntity r, PermitEngineerEntity s"
			+ " Where v.permitEntity.id = :p1 and a.permitEntity.id = :p1 and r.permitEntity.id = :p1 and e.project.id = :p1 and s.permitEntity.id = :p1";

	@Query(value = FIND_MONDAY_API_RESULT)
	MondayAPIModel findMondayModel(@Param("p1") Long idPermit);

	@Query("SELECT new com.PlayGroundAdv.Solar.model.PermitResponse (u.id, u.homeOwnName, u.advancement, u.status, u.creationPermitDate, u.updateDate, u.submitted,u.projectName) "
			+ " from PermitEntity u where u.authentificationEntity.id = :p1  and (u.isTemplate = false or u.isTemplate = :p2) ORDER BY u.creationPermitDate ")
	List<PermitResponse> getPermitResponseByUserId(@Param("p1") Long userID, @Param("p2") Boolean isTemp);

	@Query(value = "Select CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName) from PermitEntity u WHERE u.id = :p1")
	String findProjectOwnerName(@Param("p1") Long idPermit);

	@Query("SELECT u from PermitEntity u where (TRIM(LOWER(u.homeOwnName)) =:p1 AND TRIM(LOWER(u.homeOwnLastName)) =:p2 AND (:p1 is NOT NULL OR :p2 is NOT NULL)) OR (:p1 is NULL AND :p2 is NULL AND TRIM(LOWER(u.projectName)) =:p3)  OR (:p1 is NULL AND :p2 is NULL AND :p3 is NOT NULL AND u.homeOwnName is NOT NULL AND u.homeOwnLastName is NOT NULL AND CONCAT(TRIM(LOWER(u.homeOwnLastName)),', ',TRIM(LOWER(u.homeOwnName))) =:p3)  ")
	List<PermitEntity> getListPermit(@Param("p1") String name, @Param("p2") String homeOwnLastName,
			@Param("p3") String projectName);

	@Query("SELECT u from PermitEntity u where u != :p4 AND ((TRIM(LOWER(u.homeOwnName)) =:p1 AND TRIM(LOWER(u.homeOwnLastName)) =:p2 AND (:p1 is NOT NULL OR :p2 is NOT NULL)) OR (:p1 is NULL AND :p2 is NULL AND TRIM(LOWER(u.projectName)) =:p3)  OR (:p1 is NULL AND :p2 is NULL AND :p3 is NOT NULL AND u.homeOwnName is NOT NULL AND u.homeOwnLastName is NOT NULL AND CONCAT(TRIM(LOWER(u.homeOwnLastName)),', ',TRIM(LOWER(u.homeOwnName))) =:p3))  ")
	List<PermitEntity> getListPermits(@Param("p1") String name, @Param("p2") String homeOwnLastName,
			@Param("p4") PermitEntity project, @Param("p3") String projectName);

	public static final String ORDER_BY_PER = " ORDER BY CASE WHEN :sortParam = 'projectNameComb ASC'  THEN TRIM(CASE WHEN u.projectName is not null AND u.projectName <> '' THEN u.projectName WHEN u.homeOwnLastName is not null AND u.homeOwnLastName <> ''  THEN CONCAT(u.homeOwnLastName,', ',u.homeOwnName) ELSE u.homeOwnName END) END ASC, "
			+ " CASE WHEN :sortParam = 'projectNameComb DESC' THEN TRIM(CASE WHEN u.projectName is not null AND u.projectName <> '' THEN u.projectName WHEN u.homeOwnLastName is not null AND u.homeOwnLastName <> ''  THEN CONCAT(u.homeOwnLastName,', ',u.homeOwnName) ELSE u.homeOwnName END) END DESC, "
			+ " CASE WHEN :sortParam = 'advancement ASC'  THEN CAST(u.advancement AS int) END ASC, "
			+ " CASE WHEN :sortParam = 'advancement DESC' THEN CAST(u.advancement AS int) END DESC, "
			+ " CASE WHEN :sortParam = 'status ASC' THEN u.status END ASC, "
			+ " CASE WHEN :sortParam = 'status DESC' THEN u.status END DESC, "
			+ " CASE WHEN :sortParam = 'dateOfSubmitPermit ASC' THEN u.dateOfSubmitPermit END ASC,"
			+ " CASE WHEN :sortParam = 'dateOfSubmitPermit DESC' THEN u.dateOfSubmitPermit END DESC, "
			+ " CASE WHEN :sortParam = 'updateDate ASC' THEN u.updateDate END ASC, "
			+ " CASE WHEN :sortParam = 'updateDate DESC' THEN u.updateDate END DESC, "
			+ " CASE WHEN :sortParam = 'updatedBy ASC' THEN u.updatedBy END ASC, "
			+ " CASE WHEN :sortParam = 'updatedBy DESC' THEN u.updatedBy END DESC, "
			+ " CASE WHEN :sortParam = 'creationPermitDate ASC' THEN u.creationPermitDate END ASC, "
			+ " CASE WHEN :sortParam = 'creationPermitDate DESC' THEN u.creationPermitDate END DESC, "
			+ " CASE WHEN :sortParam = 'companyComb ASC' THEN TRIM(CASE WHEN u.authentificationEntity.company is not null AND u.authentificationEntity.company <> '' THEN u.authentificationEntity.company ELSE CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName) END) END ASC, "
			+ " CASE WHEN :sortParam = 'companyComb DESC' THEN TRIM(CASE WHEN u.authentificationEntity.company is not null AND u.authentificationEntity.company <> '' THEN u.authentificationEntity.company ELSE CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName) END) END DESC ";

	@Query(value = PERMIT_RESPONSE_PRIME + ORDER_BY_PER)
	Page<PermitResponsePrime> getPermitNotTemplatesByCol(@Param("sortParam") String sortParam, Pageable pageable);

	@Query(value = PERMIT_RESPONSE_PRIME + PERMIT_RESPONSE_NOT_IN + ORDER_BY_PER)
	Page<PermitResponsePrime> getPermitNotTemplatesByUpdateByNotInByCol(@Param("sortParam") String sortParam,
			Pageable pageable);

	List<PermitEntity> findByAuthentificationEntityIdInAndUpdateDateBeforeAndIsTemplateFalse(List<Long> userID,
			Date date);

	List<PermitEntity> findByAuthentificationEntityIdAndUpdateDateBeforeAndIsTemplateFalseAndArchiveStatusIsNull(
			Long userID, Date date);

	List<PermitEntity> findByAuthentificationEntityIdAndUpdateDateBeforeAndArchiveStatus(Long userID, Date date,
			String status);

	List<PermitEntity> findByAuthentificationEntityIdAndUpdateDateBeforeAndArchiveStatusAndArchiveArchiveDateBefore(
			Long userID, Date date, String status, Date now);
	
	List<PermitEntity> findByArchiveStatusAndArchiveArchiveDateAfter(String status, Date d);
}
