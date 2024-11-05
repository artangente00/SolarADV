package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.LeasePPAMeter;
import com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel;

import org.springframework.stereotype.Repository;

@Repository
public interface LeasePPAMeterRepository extends JpaRepository<LeasePPAMeter,Long>, JpaSpecificationExecutor<LeasePPAMeter> {

	// C.I 11-29 Get All LeasePPAMeter where the ID In module ID List
	Page<LeasePPAMeter> findByIdIn(List<Long> leasePPAMeterIDList, Pageable pageable);	

	// C.I 11-29 
	public static final String FILTER_LEASE_PPA_METER_LIST_MODEL = "SELECT u.id from LeasePPAMeter u where CONCAT('%',upper(u.manufacturer),'%') LIKE CONCAT('%',upper(:p1),'%') and CONCAT('%',upper(u.model),'%') LIKE CONCAT('%',upper(:p2),'%') and (u.id NOT IN :p3 OR :p3  = NULL ) and u.isDeleted = :p4 and (u.id IN :p5 OR :p5  = NULL )";


	@Query(value = FILTER_LEASE_PPA_METER_LIST_MODEL)
	public List<Long> filterLeasePPAMeterListModel(@Param("p1") String manufacturer, @Param("p2") String model,@Param("p3") List<Long> leasePPAMeterList, @Param("p4") Boolean isDeleted, @Param("p5") List<Long> leasePPAMeterFavList);
	
	LeasePPAMeter findByManufacturerAndModelAndIsDeleted (String manufacturer,String model, Boolean isDeleted);
	Long countByManufacturerAndModelAndIsDeleted (String manufacturer,String model, Boolean isDeleted);
	
	@Query("SELECT u from LeasePPAMeter u WHERE TRIM(LOWER(u.manufacturer)) = :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 ")
	List<LeasePPAMeter> findAllByManufacturerAndModelAndIsDeleted (@Param("p1") String manufacturer, @Param("p2") String model, @Param("p3") Boolean isDeleted);

	@Query("SELECT u from LeasePPAMeter u WHERE TRIM(LOWER(u.manufacturer)) != :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 ")
	List<LeasePPAMeter> findAllByManufacturerNotAndModelAndIsDeleted (@Param("p1") String manufacturer,  @Param("p2") String model, @Param("p3") Boolean isDeleted);
	
	@Query("SELECT u.model , u.manufacturer from LeasePPAMeter u where u.manufacturer=:p1 and u.model =:p2")
	List<String> findModelAndManufacturer(@Param("p1") String manufacturer, @Param("p2") String model);
    
    @Query("SELECT u.isDeleted from LeasePPAMeter u where u.id = :p1")
    Boolean findIfDeleted(@Param("p1") Long id);

	public static final String FIND_BY_PROJECT = "SELECT v from LeasePPAMeterFavLibraryEntity u,LeasePPAMeter v,PermitEntity w,AuthentificationEntity z "
			+ "where w.authentificationEntity.id = z.id AND v.isDeleted = false AND u.leasePPAMeter.id=v.id AND u.authentificationEntity.id=z.id AND w.id= :p1";

	@Query(value = FIND_BY_PROJECT)
	public List<LeasePPAMeter> findByProject(@Param("p1") Long idPermit);
	

	List<LeasePPAMeter> findByIdIn(List<Long> leasePPAMeterIDList);

	List<LeasePPAMeter> findByIsDeleted(boolean b);
	Page<LeasePPAMeter> findByIsDeleted(boolean b, Pageable pageable);
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel("
			+ " u.manufacturer, u.model, CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName), u.addDate,"
			+ " CONCAT(firstUpdater.firstName,' ',firstUpdater.lastName), CONCAT(secondUpdater.firstName,' ',secondUpdater.lastName),"
			+ " CONCAT(thirdUpdater.firstName,' ',thirdUpdater.lastName), CONCAT(verifiedBy.firstName,' ',verifiedBy.lastName), u.dateVerification) "
			+ " FROM  LeasePPAMeter u"
			+ " left join u.firstUpdater as firstUpdater left join  u.secondUpdater as secondUpdater left join u.thirdUpdater as thirdUpdater left join u.verifiedBy as verifiedBy"
			+ " WHERE u.isDeleted = false "
			+ " ORDER BY u.manufacturer, u.model")
	List<EquipmentVerificationModel> findVerifiedList();
}
