package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.ACDisconnect;
import com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel;

@Repository
public interface ACDisconnectRepository extends JpaRepository<ACDisconnect,Long>, JpaSpecificationExecutor<ACDisconnect>{

	Page<ACDisconnect> findByIsDeleted(Boolean isDel, Pageable pageable);	

	List<ACDisconnect> findByIsDeletedOrderByManufacturerAscModelAsc(Boolean delete);
	
	List<ACDisconnect>  findByIdInAndTypeAndIsDeletedOrderByDropdownOption(List<Long> id, String type, Boolean deleted);
	
	Boolean existsByManufacturerAndModelAndIsDeleted(String manufacturer, String model , Boolean isDel);
	
	List<ACDisconnect>  findByManufacturerAndModel(String manufacturer, String model);
	
	@Query("SELECT u from ACDisconnect u WHERE TRIM(LOWER(u.manufacturer)) = :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 ")
	List<ACDisconnect> findByManufacturerAndModelAndIsDeleted(@Param("p1") String manufacturer, @Param("p2") String model , @Param("p3") Boolean isDel);
	
	List<ACDisconnect> findByIdNotAndManufacturerAndModelAndIsDeleted(Long id, String manufacturer, String model , Boolean isDel);

	@Query("SELECT u from ACDisconnect u WHERE TRIM(LOWER(u.manufacturer)) != :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 ")
	List<ACDisconnect> findByManufacturerNotAndModelAndIsDeleted(@Param("p1") String manufacturer, @Param("p2") String model , @Param("p3") Boolean isDel);
	
	@Query(value = "SELECT u.ratedCurrent from ACDisconnect u WHERE u.isDeleted = false and u.ratedCurrent is not null GROUP BY u.ratedCurrent ORDER BY lpad( u.ratedCurrent, 5 ) ASC")
	public List<String> getDistinctRatedCurrrent();
	
	@Query(value = "SELECT u.disconnectDeviceType from ACDisconnect u WHERE u.id = :p1 AND u.isDeleted = false")
	public String getDisconnectDeviceType(@Param("p1") Long id);
    
    @Query("SELECT u.isDeleted from ACDisconnect u where u.id = :p1")
    Boolean findIfDeleted(@Param("p1") Long id);

	public static final String FIND_BY_PROJECT = "SELECT v from ACDisconnectFavLibraryEntity u,ACDisconnect v,PermitEntity w,AuthentificationEntity z "
			+ "where w.authentificationEntity.id = z.id AND u.ACDisconnect.id=v.id AND v.isDeleted = false AND u.authentificationEntity.id=z.id AND w.id= :p1 ORDER BY v.dropdownOption";

	@Query(value = FIND_BY_PROJECT)
	public List<ACDisconnect> findByProject(@Param("p1") Long idPermit);
	
	public static final String FIND_PROJECT_USING_MODEL = "SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel " + 
    		" ( u.homeOwnLastName,  u.homeOwnName,  u.projectName,  u.status, v.firstName,  v.lastName) from PermitEntity u,  AuthentificationEntity v, " + 
    		" PermitProjectSiteInfoEntity w where (w.rooftopACCombinerModel = :p1 or w.rooftopACCombinerModelTwo = :p1 or w.roofTopACDisco= :p1) " + 
    		" and  u.isDeleted  = false and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = false and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id";

	@Query(value = FIND_PROJECT_USING_MODEL)
	public List<ProjectForLibrariesModel> findProjectUsingModel(@Param("p1") String model);
	
    @Query("SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult("
    		+ " u.id, " + " u.manufacturer, " + " u.model, " + " u.addDate," + " u.idOwner.firstName, "
    		+ " u.idOwner.lastName," + " u.type ) " + " FROM  ACDisconnect u " + " WHERE u.isDeleted = :p1 "
    		+ " AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")
    List<LibrariesManagementModelResult> getLibModel(@Param("p1") boolean del);
        
    List<ACDisconnect>  findByIdInAndIsDeleted(List<Long> ids, Boolean isDeleted);

	List<ACDisconnect> findByIsDeleted(boolean b);

	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel("
			+ " u.manufacturer, u.model, CONCAT(u.idOwner.firstName,' ',u.idOwner.lastName), u.addDate,"
			+ " CONCAT(firstUpdater.firstName,' ',firstUpdater.lastName), CONCAT(secondUpdater.firstName,' ',secondUpdater.lastName),"
			+ " CONCAT(thirdUpdater.firstName,' ',thirdUpdater.lastName), CONCAT(verifiedBy.firstName,' ',verifiedBy.lastName), u.dateVerification) "
			+ " FROM  ACDisconnect u"
			+ " left join u.firstUpdater as firstUpdater left join  u.secondUpdater as secondUpdater left join u.thirdUpdater as thirdUpdater left join u.verifiedBy as verifiedBy"
			+ " WHERE u.isDeleted = false "
			+ " ORDER BY u.manufacturer, u.model")
	List<EquipmentVerificationModel> findVerifiedList();
}
