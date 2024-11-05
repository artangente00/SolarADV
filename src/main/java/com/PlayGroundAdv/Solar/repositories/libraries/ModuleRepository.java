package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.Cmodulev2;
import com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult;
import com.PlayGroundAdv.Solar.model.ModuleInfoModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel;

@Repository
public interface ModuleRepository extends JpaRepository<Cmodulev2, Long>, JpaSpecificationExecutor<Cmodulev2> {

	// A.B 11-18 Get All Module where the ID In module ID List
	Page<Cmodulev2> findByIdIn(List<Long> moduleIDList, Pageable pageable);

	// A.B 11-18
	public static final String FILTER_MODULE_LIST_MODEL = "SELECT u.id from Cmodulev2 u where CONCAT('%',upper(u.make),'%') LIKE CONCAT('%',upper(:p1),'%') and CONCAT('%',upper(u.model),'%') LIKE CONCAT('%',upper(:p2),'%') and (u.stcRounded = :p3 OR :p3  = '' ) and (u.id NOT IN :p4 OR :p4  = NULL ) and u.isDeleted = :p5 and (u.id IN :p6 OR :p6  = NULL )";

	@Query(value = FILTER_MODULE_LIST_MODEL)
	public List<Long> filterModuleListModel(@Param("p1") String manufacturer, @Param("p2") String model,
			@Param("p3") String wattage, @Param("p4") List<Long> moduleList, @Param("p5") Boolean isDeleted,
			@Param("p6") List<Long> moduleFavList);

	Long countByModelAndMakeAndIsDeleted(String model, String manufacturer, Boolean isDeleted);

	Cmodulev2 findByModelAndMakeAndIsDeleted(String model, String manufacturer, Boolean isDeleted);

	List<Cmodulev2> findByModelAndIsDeleted(String model, Boolean isDeleted);

	@Query("SELECT u.model , u.make from Cmodulev2 u where u.make=:p1 and u.model =:p2")
	List<String> findModelAndMake(@Param("p1") String manufacturer, @Param("p2") String model);

	@Query("SELECT new com.PlayGroundAdv.Solar.model.ModuleInfoModel(u.id, u.make, u.model, u.length, u.width, u.depth) from Cmodulev2 u where u.id=:p1")
	ModuleInfoModel getModuleById(@Param("p1") Long moduleId);

	@Query("SELECT u.stc from Cmodulev2 u where u.id = :p1")
	String findSTC(@Param("p1") Long moduleId);

	@Query("SELECT u.isDeleted from Cmodulev2 u where u.id = :p1")
	Boolean findIfDeleted(@Param("p1") Long moduleId);

	// C.I 01-16 Get Module Iacmax
	public static final String GET_MODULE_IACMAX = "SELECT u.iacmax from Cmodulev2 u where u.id = :p1";

	@Query(value = GET_MODULE_IACMAX)
	String getModuleIacmax(@Param("p1") Long moduleId);

	// C.I 01-20 Get Deleted
	public static final String GET_MODULE_DELETED = "SELECT u.isDeleted from Cmodulev2 u where u.id = :p1";

	@Query(value = GET_MODULE_DELETED)
	Boolean getModuleDeleted(@Param("p1") Long moduleId);

	Page<Cmodulev2> findByIsDeleted(Boolean isDel, Pageable pageable);

	@Query("SELECT u from Cmodulev2 u where u.isDeleted = false ORDER BY u.make , u.model ")
	List<Cmodulev2> findAllByDeletedFalse();

	List<Cmodulev2> findByMakeAndModel(String manufacturer, String model);

	public static final String FIND_BY_PROJECT = "SELECT v from ModuleLibraryEntity u,Cmodulev2 v,PermitEntity w,AuthentificationEntity z "
			+ "where w.authentificationEntity.id = z.id AND v.isDeleted = false AND u.cmodulev2.id=v.id AND u.authentificationEntity.id=z.id AND w.id= :p1";

	@Query(value = FIND_BY_PROJECT)
	public List<Cmodulev2> findByProject(@Param("p1") Long idPermit);

	public static final String FIND_BY_SITE_SURVEY = "SELECT v from ModuleLibraryEntity u,Cmodulev2 v,SiteSurveyEntity w,AuthentificationEntity z "
			+ "where w.createdBy.id = z.id AND u.cmodulev2.id=v.id AND v.isDeleted = false AND u.authentificationEntity.id=z.id AND w.id= :p1";

	@Query(value = FIND_BY_SITE_SURVEY)
	public List<Cmodulev2> findBySiteSurvey(@Param("p1") Long idSiteSurvey);

	@Query(" SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult( " + " u.id, " + " u.make, "
			+ " u.model, " + " u.addDate, " + " u.authentificationEntity.firstName, "
			+ " u.authentificationEntity.lastName ) " + " FROM  Cmodulev2 u " + " WHERE u.isDeleted = :p1 "
			+ " AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")
	List<LibrariesManagementModelResult> getLibModel(@Param("p1") boolean del);

	@Query(value = "SELECT count(*) > 0 from Cmodulev2 u WHERE TRIM(LOWER(u.model)) = TRIM(:p1) AND TRIM(LOWER(u.make)) = TRIM(:p2) and u.isDeleted = false")
	Boolean existByModelAndManufactuter(@Param("p1") String model, @Param("p2") String manufacturer);

	@Query("SELECT u from Cmodulev2 u WHERE TRIM(LOWER(u.model)) = :p1 AND TRIM(LOWER(u.make)) = :p2 AND u.isDeleted= :p3")
	List<Cmodulev2> findModuleByMake(@Param("p1") String model, @Param("p2") String manufacturer,
			@Param("p3") Boolean isDel);

	@Query("SELECT u from Cmodulev2 u WHERE TRIM(LOWER(u.model)) = :p1 AND TRIM(LOWER(u.make)) != :p2 AND u.isDeleted= :p3")
	List<Cmodulev2> findModuleByNotMake(@Param("p1") String model, @Param("p2") String manufacturer,
			@Param("p3") Boolean isDel);
	
	List<Cmodulev2> findByIdInAndIsDeleted(List<Long> moduleIdList, Boolean isDel);
	
	public static final String GET_ALL_MODELS = "Select new com.PlayGroundAdv.Solar.model.libraries.ComponentModel(u.id, u.model) FROM Cmodulev2 u Where u.isDeleted = false ORDER BY u.make, u.model";

	@Query(value = GET_ALL_MODELS)
	public List<ComponentModel> getAllModels();
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel("
			+ " u.make, u.model, CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName), u.addDate,"
			+ " CONCAT(firstUpdater.firstName,' ',firstUpdater.lastName), CONCAT(secondUpdater.firstName,' ',secondUpdater.lastName),"
			+ " CONCAT(thirdUpdater.firstName,' ',thirdUpdater.lastName), CONCAT(verifiedBy.firstName,' ',verifiedBy.lastName), u.dateVerification) "
			+ " FROM  Cmodulev2 u"
			+ " left join u.firstUpdater as firstUpdater left join  u.secondUpdater as secondUpdater left join u.thirdUpdater as thirdUpdater left join u.verifiedBy as verifiedBy"
			+ " WHERE u.isDeleted = false "
			+ " ORDER BY u.make, u.model")
	List<EquipmentVerificationModel> findVerifiedList();
}
