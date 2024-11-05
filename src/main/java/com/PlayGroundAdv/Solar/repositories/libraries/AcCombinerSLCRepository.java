package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.ACCombinerSLC;
import com.PlayGroundAdv.Solar.entity.PermitProjectSiteInfoEntity;
import com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel;

@Repository
public interface AcCombinerSLCRepository extends JpaRepository<ACCombinerSLC ,Long>, JpaSpecificationExecutor<ACCombinerSLC>{
	
	Page<ACCombinerSLC> findByIsDeleted(Boolean isDel, Pageable pageable);	

	@Query("SELECT u from ACCombinerSLC u WHERE TRIM(LOWER(u.manufacturer)) = :p2 AND TRIM(LOWER(u.model)) = :p1 AND u.isDeleted = :p3 ")
	List<ACCombinerSLC> findByModelAndManufacturerAndIsDeleted(@Param("p1") String model, @Param("p2") String manufacturer, @Param("p3") Boolean isDeleted);
	
	@Query("SELECT u from ACCombinerSLC u WHERE TRIM(LOWER(u.manufacturer)) != :p2 AND TRIM(LOWER(u.model)) = :p1 AND u.isDeleted = :p3 ")
	List<ACCombinerSLC> findByModelAndManufacturerNotAndIsDeleted(@Param("p1") String model, @Param("p2") String manufacturer, @Param("p3") Boolean isDeleted);
	ACCombinerSLC findOneByModelAndManufacturerAndIsDeleted(String model, String manufacturer,Boolean isDeleted);
	
	@Query("SELECT u from ACCombinerSLC u WHERE u.ratedCurrent = :p1 AND TRIM(LOWER(u.manufacturer)) = :p2 AND u.numberOfSpaces = :p3 AND u.combinerDeviceType = :p4  AND u.isDeleted = :p5 ")
	List<ACCombinerSLC> findByRatedCurrentAndManufacturerAndNumberOfSpacesAndCombinerDeviceTypeAndIsDeleted(@Param("p1") String ratedCurrent, @Param("p2") String manufacturer, @Param("p3") String numberOfSpaces, @Param("p4") String combinerDeviceType, @Param("p5") Boolean isDeleted);

	String FIND_BY_PROJECT = "SELECT v from ACCombinerFavLibraryEntity u,ACCombinerSLC v,PermitEntity w,AuthentificationEntity z "
			+ "where w.authentificationEntity.id = z.id AND u.acCombinerSlc.id=v.id AND v.isDeleted = false AND u.authentificationEntity.id=z.id AND w.id= :p1 ORDER BY v.dropdownOption";

	@Query(value = FIND_BY_PROJECT)
	List<ACCombinerSLC> findByProject(@Param("p1") Long idPermit);
    
    @Query("SELECT u.isDeleted from ACCombinerSLC u where u.id = :p1")
    Boolean findIfDeleted(@Param("p1") Long id);
    
    @Query("SELECT count(*) > 0 from ACCombinerSLC u where u.id != :p1 and TRIM(LOWER(u.manufacturer)) = TRIM(LOWER(:p2)) AND TRIM(LOWER(u.model)) = TRIM(LOWER(:p3)) AND u.isDeleted = false")
    Boolean findIfExist(@Param("p1") Long id, @Param("p2") String manufacturer, @Param("p3") String model);
    
    
    String FIND_PROJECT_USING_MODEL = "SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel " +
    		" ( u.homeOwnLastName,  u.homeOwnName,  u.projectName,  u.status, v.firstName,  v.lastName) from PermitEntity u,  AuthentificationEntity v, " + 
    		" PermitProjectSiteInfoEntity w where (w.groundLevelACCombinerBoxModel.id = :p1 or w.aCCombinerInstalled.id = :p1 or w.roofTopACCombiner.id = :p1) " + 
    		" and  u.isDeleted  = false and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = false and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id";

	@Query(value = FIND_PROJECT_USING_MODEL)
	List<ProjectForLibrariesModel> findProjectUsingModel(@Param("p1") Long idSLC);
	
	String FIND_INPUT_USING_MODEL = "SELECT u from PermitProjectSiteInfoEntity u WHERE (u.groundLevelACCombinerBoxModel.id = :p2 or u.groundLevelACCombinerDisconnectModel = :p1 or " +
			" u.rooftopACCombinerModel = :p1 or u.rooftopACCombinerModelTwo = :p1 or u.roofTopACCombiner = :p2) " + 
    		" and  u.permitEntity.isDeleted  = false";

	@Query(value = FIND_INPUT_USING_MODEL)
	List<PermitProjectSiteInfoEntity> findInputsUsingModel(@Param("p1") String slc, @Param("p2") Long idSLC);
	
    @Query("SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult("
    		+ " u.id, " + " u.manufacturer, " + " u.model, " + " u.addDate," + " u.idOwner.firstName, "
    		+ " u.idOwner.lastName," + " u.type ) " + " FROM  ACCombinerSLC u " + " WHERE u.isDeleted = :p1 "
    		+ " AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")
    List<LibrariesManagementModelResult> getLibModel(@Param("p1") boolean del);
    List<ACCombinerSLC> findByManufacturerAndModel(String man ,String model);

    List<ACCombinerSLC> findByIdInAndIsDeleted(List<Long> acCombinerSLCIDList, Boolean isDel);

	List<ACCombinerSLC> findByIsDeleted(boolean b);
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel("
			+ " u.manufacturer, u.model, CONCAT(u.idOwner.firstName,' ',u.idOwner.lastName), u.addDate,"
			+ " CONCAT(firstUpdater.firstName,' ',firstUpdater.lastName), CONCAT(secondUpdater.firstName,' ',secondUpdater.lastName),"
			+ " CONCAT(thirdUpdater.firstName,' ',thirdUpdater.lastName), CONCAT(verifiedBy.firstName,' ',verifiedBy.lastName), u.dateVerification) "
			+ " FROM  ACCombinerSLC u"
			+ " left join u.firstUpdater as firstUpdater left join  u.secondUpdater as secondUpdater left join u.thirdUpdater as thirdUpdater left join u.verifiedBy as verifiedBy"
			+ " WHERE u.isDeleted = false "
			+ " ORDER BY u.manufacturer, u.model")
	List<EquipmentVerificationModel> findVerifiedList();

}
