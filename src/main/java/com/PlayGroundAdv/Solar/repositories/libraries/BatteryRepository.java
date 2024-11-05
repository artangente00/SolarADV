package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.Battery;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel;

@Repository
public interface BatteryRepository extends JpaRepository<Battery,Long>, JpaSpecificationExecutor<Battery>{

	Page<Battery> findByIsDeleted(Boolean isDel, Pageable pageable);	
	
	Boolean existsByModelAndManufacturerAndIsDeleted(String model, String manufacturer, Boolean isDel);
	Boolean existsByModelAndManufacturerAndIdNotAndIsDeleted(String model, String manufacturer, Long id, Boolean isDel);
	Boolean existsByModelAndManufacturerNotAndIsDeleted(String model, String manufacturer, Boolean isDel);
	
	List<Battery> findByModelAndManufacturerAndIsDeleted(String model, String manufacturer, Boolean isDel);
	List<Battery> findByModelAndManufacturerNotAndIsDeleted(String model, String manufacturer, Boolean isDel);

	public static final String FIND_BY_PROJECT = "SELECT new com.PlayGroundAdv.Solar.model.libraries.ComponentModel(v.id, v.manufacturer, v.model) "
			+ "from BatteryFavLibraryEntity u,Battery v,PermitEntity w,AuthentificationEntity z "
			+ "where w.authentificationEntity.id = z.id AND v.isDeleted = false AND u.battery.id=v.id AND u.authentificationEntity.id=z.id AND w.id= :p1";

	@Query(value = FIND_BY_PROJECT)
	public List<ComponentModel> findByProject(@Param("p1") Long idPermit);
    
    @Query("SELECT u.isDeleted from Battery u where u.id = :p1")
    Boolean findIfDeleted(@Param("p1") Long id);
	
	public static final String GET_ALL_Models = "Select new com.PlayGroundAdv.Solar.model.libraries.ComponentModel(u.id, u.manufacturer, u.model) FROM Battery u Where u.isDeleted = false ORDER BY u.manufacturer, u.model";

	@Query(value = GET_ALL_Models)
	public List<ComponentModel> getAllModels();

	List<Battery> findByIsDeleted(boolean b);
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel("
			+ " u.manufacturer, u.model, CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName), u.addDate,"
			+ " CONCAT(firstUpdater.firstName,' ',firstUpdater.lastName), CONCAT(secondUpdater.firstName,' ',secondUpdater.lastName),"
			+ " CONCAT(thirdUpdater.firstName,' ',thirdUpdater.lastName), CONCAT(verifiedBy.firstName,' ',verifiedBy.lastName), u.dateVerification) "
			+ " FROM  Battery u"
			+ " left join u.firstUpdater as firstUpdater left join  u.secondUpdater as secondUpdater left join u.thirdUpdater as thirdUpdater left join u.verifiedBy as verifiedBy"
			+ " WHERE u.isDeleted = false "
			+ " ORDER BY u.manufacturer, u.model")
	List<EquipmentVerificationModel> findVerifiedList();

}
