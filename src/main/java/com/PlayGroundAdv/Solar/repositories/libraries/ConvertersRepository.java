package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.libraries.DCOptimizerEntity;
import com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult;
import com.PlayGroundAdv.Solar.model.ProjectForConvertModelResult;
import com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel;

import org.springframework.stereotype.Repository;

@Repository
public interface ConvertersRepository extends JpaRepository<DCOptimizerEntity, Long> {

	// M.A 12-02 Find the List Of DC Optimizer on this List of ID
	Page<DCOptimizerEntity> findByIdIn(List<Long> convertsIdList, Pageable pageable);

	// M.A 12-02 find the list of id of DC Optimizer
	public static final String FILTER_CONVERTER_LIST_MODEL = "Select u.id from DCOptimizerEntity u where CONCAT('%',upper(u.manufacturer),'%') LIKE CONCAT('%',upper(:p1),'%') and CONCAT('%',upper(u.model),'%') LIKE CONCAT('%',upper(:p2),'%') and (u.id NOT IN :p3 OR :p3  = NULL) and u.isDeleted = :p4 and (u.id IN :p5 OR :p5  = NULL )";

	@Transactional
	@Modifying
	@Query(value = FILTER_CONVERTER_LIST_MODEL)
	public List<Long> filterConverterListModel(@Param("p1") String manufacturer, @Param("p2") String model,
			@Param("p3") List<Long> converterList, @Param("p4") Boolean isDeleted,
			@Param("p5") List<Long> converterFavList);

	@Transactional
	@Modifying
	@Query(value = " UPDATE DCOptimizerEntity u SET u.hasCorrectionRequest = :p1 , u.eroneousContent = :p2 , u.eroneousContentOther = :p3  , u.eroneousDescription = :p4 where u.id = :p5")
	public Integer updateCorrectionRequest(@Param("p1") Boolean hasCorrectionRequest,
			@Param("p2") String eroneousContent, @Param("p3") String eroneousContentOther,
			@Param("p4") String eroneousDescription, @Param("p5") Long getId);

	@Query("SELECT u from DCOptimizerEntity u WHERE TRIM(LOWER(u.manufacturer)) = :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 ")
	List<DCOptimizerEntity> findByManufacturerAndModelAndIsDeleted(@Param("p1") String manufacture, @Param("p2") String model, @Param("p3") Boolean deleted);
	
	@Query("SELECT u from DCOptimizerEntity u WHERE TRIM(LOWER(u.manufacturer)) = :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 AND u.id != :p4 ")
	List<DCOptimizerEntity> findByManufacturerAndModelAndIsDeletedNotIn(@Param("p1") String manufacture, @Param("p2") String model, @Param("p3") Boolean deleted, @Param("p4") Long id);
	
	@Query("SELECT u from DCOptimizerEntity u WHERE TRIM(LOWER(u.model)) = :p1 AND u.isDeleted = :p2 ")
	List<DCOptimizerEntity> findByModelAndIsDeleted(@Param("p1") String model, @Param("p2") Boolean deleted);
	List<DCOptimizerEntity> findByIsDeleted(Boolean deleted);
	Boolean existsByIdAndIsDeleted(Long id, Boolean isDel);

	// A.B 01-16 Get Deleted
	public static final String GET_CONVERTER_DELETED = "SELECT u.isDeleted from DCOptimizerEntity u where u.id = :p1";

	@Query(value = GET_CONVERTER_DELETED)
	Boolean getConverterDeleted(@Param("p1") Long inverterId);

	DCOptimizerEntity findByManufacturerAndModel(String manufactor, String model);
	
	List<DCOptimizerEntity> findAllByManufacturerAndModel(String manufactor, String model);

	public static final String FIND_BY_PROJECT = "SELECT v from DCOptimizerFavoritEntity u,DCOptimizerEntity v,PermitEntity w,AuthentificationEntity z where w.authentificationEntity.id = z.id "
			+ "AND u.optimizer.id=v.id AND v.isDeleted = false AND u.user.id=z.id AND w.id= :p1 ORDER BY u.optimizer.manufacturer, u.optimizer.model";

	@Query(value = FIND_BY_PROJECT)
	public List<DCOptimizerEntity> findByProject(@Param("p1") Long idPermit);

	public static final String FIND_PROJECT_USING_MODEL = "SELECT new com.PlayGroundAdv.Solar.model.ProjectForConvertModelResult  "
			+ " ( w.permitEntity.homeOwnLastName, " + "w.permitEntity.homeOwnName, " + " w.permitEntity.projectName, "
			+ " w.permitEntity.status, " + " w.permitEntity.authentificationEntity.firstName, "
			+ " w.permitEntity.authentificationEntity.lastName)" + " from " + " PermitArraysEntity w"
			+ " where w.systemOptimizerModel.id = :p1 " + "and w.permitEntity.isDeleted  = false "
			+ " and w.permitEntity.status != 'Delivered'  " + "and w.permitEntity.status != 'Submitted' "
			+ " and w.permitEntity.isTemplate  = false";

	@Query(value = FIND_PROJECT_USING_MODEL)
	public List<ProjectForConvertModelResult> findProjectUsingModel(@Param("p1") Long id);
	
	
    @Query("SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult("
    		+ " u.id, " + " u.manufacturer, " + " u.model, " + " u.addDate, " + " u.user.firstName, "
    		+ " u.user.lastName ) " + " FROM  DCOptimizerEntity u " + " WHERE u.isDeleted = :p1 "
    		+ " AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")
    List<LibrariesManagementModelResult> getLibModel(@Param("p1") boolean del);
    
	List<DCOptimizerEntity> findByIdInAndIsDeleted(List<Long> convertsIdList, Boolean isDel);
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel("
			+ " u.manufacturer, u.model, CONCAT(u.user.firstName,' ',u.user.lastName), u.addDate,"
			+ " CONCAT(firstUpdater.firstName,' ',firstUpdater.lastName), CONCAT(secondUpdater.firstName,' ',secondUpdater.lastName),"
			+ " CONCAT(thirdUpdater.firstName,' ',thirdUpdater.lastName), CONCAT(verifiedBy.firstName,' ',verifiedBy.lastName), u.dateVerification) "
			+ " FROM  DCOptimizerEntity u"
			+ " left join u.firstUpdater as firstUpdater left join  u.secondUpdater as secondUpdater left join u.thirdUpdater as thirdUpdater left join u.verifiedBy as verifiedBy"
			+ " WHERE u.isDeleted = false "
			+ " ORDER BY u.manufacturer, u.model")
	List<EquipmentVerificationModel> findVerifiedList();
    
}
