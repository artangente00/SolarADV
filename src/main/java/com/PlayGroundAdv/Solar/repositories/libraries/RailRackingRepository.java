package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.RailRacking;
import com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel;

@Repository
public interface RailRackingRepository extends JpaRepository<RailRacking, Long>, JpaSpecificationExecutor<RailRacking> {
	// C.I 06/12/2019 Get All Rail Racking where the ID In Rail Racking ID List
	Page<RailRacking> findByIdIn(List<Long> railRackingIDList, Pageable pageable);

	// C.I 06/12/2019
	//S.B 24/07/2020 CR-2996-MOD-004
	public static final String FILTER_RAILRACKING_LIST_MODEL = "SELECT u.id from RailRacking u "
			+ "INNER JOIN MountingTypeEntity m ON CONCAT('%',upper(u.manufacturer),'%')"
			+ " LIKE CONCAT('%',upper(:p1),'%') and CONCAT('%',upper(u.model),'%')"
			+ " LIKE CONCAT('%',upper(:p2),'%') and (m.mountingType = :p3 OR :p3  = '') "
			+ "and (u.id NOT IN :p4 OR :p4  = NULL ) and u.isDeleted = :p5 "
			+ "and (u.id IN :p6 OR :p6 = NULL ) and u.id = m.idRail.id GROUP BY u.id";
	
	@Query(value = FILTER_RAILRACKING_LIST_MODEL)
	public List<Long> filterRailRackingListModel(@Param("p1") String manufacturer, @Param("p2") String model,
			@Param("p3") String typeOfSystem, @Param("p4") List<Long> railRackingList, @Param("p5") Boolean isDeleted,
			@Param("p6") List<Long> railRackingFavList);

	public static final String FIND_BY_PROJECT = "SELECT v from RailRackingFavLibraryEntity u,RailRacking v,PermitEntity w,AuthentificationEntity z "
			+ " where w.authentificationEntity.id = z.id AND u.railRacking.id=v.id AND v.isDeleted = false AND"
			+ " u.authentificationEntity.id=z.id AND w.id= :p1 "
			+ " ORDER BY u.railRacking.manufacturer, u.railRacking.model";

	@Query(value = FIND_BY_PROJECT)
	public List<RailRacking> findByProject(@Param("p1") Long idPermit);
	


	public static final String GET_ALL_Models = "Select new com.PlayGroundAdv.Solar.model.libraries.ComponentModel( u.id, u.manufacturer, u.model) FROM RailRacking u Where u.isDeleted = false ORDER BY u.manufacturer, u.model";

	@Query(value = GET_ALL_Models)
	public List<ComponentModel> getAllModels();
    
    @Query("SELECT u.isDeleted from RailRacking u WHERE  u.id = :p1  ")
    Boolean findIfDeleted(@Param("p1") Long id);

    @Query("SELECT u from RailRacking u WHERE TRIM(LOWER(u.manufacturer)) = :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 ")
	List<RailRacking> findByManufacturerAndModelAndIsDeleted(@Param("p1") String manufacturer, @Param("p2") String model, @Param("p3") Boolean isDeleted);

	@Query("SELECT u from RailRacking u WHERE TRIM(LOWER(u.manufacturer)) != :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 ")
	List<RailRacking> findByManufacturerNotAndModelAndIsDeleted(@Param("p1") String manufacturer, @Param("p2") String model, @Param("p3") Boolean isDeleted);

    @Query("SELECT count(*) > 0 from RailRacking u WHERE TRIM(LOWER(u.manufacturer)) = :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.id != :p3 AND u.isDeleted = false ")
	Boolean existByManufacturerAndModelAndIdNot(@Param("p1") String manufacturer, @Param("p2") String model, @Param("p3") Long railRackingId);

	Long countByManufacturerAndModelAndIsDeleted(String manufacturer, String model, Boolean isDeleted);

	Boolean existsByManufacturerAndModelAndIdNotAndIsDeleted(String manufacturer, String model, Long railRackingId, Boolean isDeleted);

	Boolean existsByIdAndIsDeleted(Long getId, Boolean isDel);
	List<RailRacking> findByPvRailTypeId(Long opId);
	List<RailRacking> findByPvRailSpliceTypeId(Long opId);
	List<RailRacking> findByMidClampId(Long opId);
	List<RailRacking> findByEndClampId(Long opId);
	
	
    @Query("SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult("
    		+ " u.id, " + " u.manufacturer, " + " u.model, " + " u.addDate," + " u.idOwner.firstName, "
    		+ " u.idOwner.lastName ) " + " FROM  RailRacking u " + " WHERE u.isDeleted = :p1 "
    		+ " AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")
    List<LibrariesManagementModelResult> getLibModel(@Param("p1") boolean del);
    
    List<RailRacking> findByManufacturerAndModel(String manufacturer, String model);
    
    @Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.ComponentModel(u.id, u.manufacturer, u.model)"
    		+ " from RailRacking u WHERE u.isDeleted= :p1 ORDER BY u.manufacturer, u.model ")
    List<ComponentModel> getRailRackingList(@Param("p1") Boolean isDel);
    
	List<RailRacking> findByIdIn(List<Long> railRackingIDList);

	List<RailRacking> findByIsDeleted(boolean b);
	Page<RailRacking> findByIsDeleted(boolean b, Pageable pageable);
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel("
			+ " u.manufacturer, u.model, CONCAT(u.idOwner.firstName,' ',u.idOwner.lastName), u.addDate,"
			+ " CONCAT(firstUpdater.firstName,' ',firstUpdater.lastName), CONCAT(secondUpdater.firstName,' ',secondUpdater.lastName),"
			+ " CONCAT(thirdUpdater.firstName,' ',thirdUpdater.lastName), CONCAT(verifiedBy.firstName,' ',verifiedBy.lastName), u.dateVerification) "
			+ " FROM  RailRacking u"
			+ " left join u.firstUpdater as firstUpdater left join  u.secondUpdater as secondUpdater left join u.thirdUpdater as thirdUpdater left join u.verifiedBy as verifiedBy"
			+ " WHERE u.isDeleted = false "
			+ " ORDER BY u.manufacturer, u.model")
	List<EquipmentVerificationModel> findVerifiedList();

}
