package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.TiltLegs;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel;

import org.springframework.stereotype.Repository;

@Repository
public interface TiltLegsRepository extends JpaRepository<TiltLegs,Long>{

	// C.I 30-12 Get All TiltLegs where the ID In module ID List
	Page<TiltLegs> findByIdIn(List<Long> tiltLegsIDList, Pageable pageable);	

	// C.I 30-12 
	public static final String FILTER_TILT_LEGS_LIST_MODEL = "SELECT u.id from TiltLegs u where CONCAT('%',upper(u.manufacturer),'%') LIKE CONCAT('%',upper(:p1),'%') and CONCAT('%',upper(u.model),'%') LIKE CONCAT('%',upper(:p2),'%') and (u.id NOT IN :p3 OR :p3  = NULL ) and u.isDeleted = :p4 and (u.id IN :p5 OR :p5  = NULL )";


	@Query(value = FILTER_TILT_LEGS_LIST_MODEL)
	public List<Long> filterTiltLegsListModel(@Param("p1") String manufacturer, @Param("p2") String model,@Param("p3") List<Long> tiltLegsList, @Param("p4") Boolean isDeleted, @Param("p5") List<Long> tiltLegsFavList);

	TiltLegs findByManufacturerAndModelAndIsDeleted (String manufacturer,String model, Boolean isDeleted);
	Long countByManufacturerAndModelAndIsDeleted (String manufacturer,String model, Boolean isDeleted);
	List<TiltLegs> findAllByManufacturerAndModelAndIsDeleted (String manufacturer,String model, Boolean isDeleted);
	List<TiltLegs> findAllByManufacturerNotAndModelAndIsDeleted (String manufacturer,String model, Boolean isDeleted);
	Long countByManufacturerNotAndModelAndIsDeleted (String manufacturer,String model, Boolean isDeleted);
	
	@Query("SELECT u.model , u.manufacturer from TiltLegs u where u.manufacturer=:p1 and u.model =:p2")
	List<String> findModelAndManufacturer(@Param("p1") String manufacturer, @Param("p2") String model);
    
    @Query("SELECT u.isDeleted from TiltLegs u where u.id = :p1")
    Boolean findIfDeleted(@Param("p1") Long id);

	public static final String FIND_BY_PROJECT = "SELECT v from TiltLegsFavLibraryEntity u,TiltLegs v,PermitEntity w,AuthentificationEntity z "
			+ "where w.authentificationEntity.id = z.id AND v.isDeleted = false AND u.tiltLegs.id=v.id AND u.authentificationEntity.id=z.id AND w.id= :p1";

	@Query(value = FIND_BY_PROJECT)
	public List<TiltLegs> findByProject(@Param("p1") Long idPermit);
	
	public static final String GET_ALL_Models = "Select new com.PlayGroundAdv.Solar.model.libraries.ComponentModel(u.id, u.manufacturer, u.model)  FROM TiltLegs u Where u.isDeleted = false ORDER BY u.manufacturer, u.model";

	@Query(value = GET_ALL_Models)
	public List<ComponentModel> getAllModels();

	List<TiltLegs> findByIsDeleted(boolean b);
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel("
			+ " u.manufacturer, u.model, CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName), u.addDate,"
			+ " CONCAT(firstUpdater.firstName,' ',firstUpdater.lastName), CONCAT(secondUpdater.firstName,' ',secondUpdater.lastName),"
			+ " CONCAT(thirdUpdater.firstName,' ',thirdUpdater.lastName), CONCAT(verifiedBy.firstName,' ',verifiedBy.lastName), u.dateVerification) "
			+ " FROM  TiltLegs u"
			+ " left join u.firstUpdater as firstUpdater left join  u.secondUpdater as secondUpdater left join u.thirdUpdater as thirdUpdater left join u.verifiedBy as verifiedBy"
			+ " WHERE u.isDeleted = false "
			+ " ORDER BY u.manufacturer, u.model")
	List<EquipmentVerificationModel> findVerifiedList();
}
