package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.Flashing;
import com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel;

import org.springframework.stereotype.Repository;

@Repository
public interface FlashingRepository extends JpaRepository<Flashing ,Long>, JpaSpecificationExecutor<Flashing> {

	// C.I 11-29 Get All Flashings where the ID In module ID List
	Page<Flashing> findByIdIn(List<Long> flashingIDList, Pageable pageable);	

	// C.I 11-29 
	public static final String FILTER_INVERTER_LIST_MODEL = "SELECT u.id from Flashing u where CONCAT('%',upper(u.manufacturer),'%') LIKE CONCAT('%',upper(:p1),'%') and CONCAT('%',upper(u.model),'%') LIKE CONCAT('%',upper(:p2),'%') and (u.id NOT IN :p3 OR :p3  = NULL ) and u.isDeleted = :p4 and (u.id IN :p5 OR :p5  = NULL )";

	@Query(value = FILTER_INVERTER_LIST_MODEL)
	public List<Long> filterFlashingListModel(@Param("p1") String manufacturer, @Param("p2") String model,@Param("p3") List<Long> flashingList, @Param("p4") Boolean isDeleted, @Param("p5") List<Long> flashingFavList);
	
	Flashing findByManufacturerAndModel(String manufacturer,String model);
	List<Flashing> findAllByManufacturerAndModel(String manufacturer,String model);
	Flashing findOneByManufacturerAndModel(String manufacturer,String model);
	List<Flashing> findAllByManufacturerAndMappedValue(String manufacturer,String mappedV);
	Flashing findOneByManufacturerAndMappedValue(String manufacturer,String mappedV);
	
	@Query("SELECT u from Flashing u WHERE TRIM(LOWER(u.manufacturer)) = :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 ")
	List<Flashing> findAllByManufacturerAndModelAndIsDeleted (@Param("p1") String manufacturer, @Param("p2") String model, @Param("p3") Boolean isDeleted);
	
	@Query("SELECT u from Flashing u WHERE TRIM(LOWER(u.manufacturer)) = :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 AND u.id != :p4 ")
	List<Flashing> findAllByManufacturerAndModelAndIsDeletedAndIdNot (@Param("p1") String manufacturer, @Param("p2") String model, @Param("p3") Boolean isDeleted, @Param("p4") Long id);

	@Query("SELECT u from Flashing u WHERE TRIM(LOWER(u.manufacturer)) != :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 ")
	List<Flashing>  findAllByManufacturerNotAndModelAndIsDeleted (@Param("p1") String manufacturer, @Param("p2") String model, @Param("p3") Boolean isDeleted);

	public static final String FIND_BY_PROJECT = "SELECT v from FlashingFavLibraryEntity u,Flashing v,PermitEntity w,AuthentificationEntity z "
			+ "where w.authentificationEntity.id = z.id AND v.isDeleted = false AND u.flashing.id=v.id AND u.authentificationEntity.id=z.id AND w.id= :p1";

	@Query(value = FIND_BY_PROJECT)
	public List<Flashing> findByProject(@Param("p1") Long idPermit);
    
    @Query("SELECT u.isDeleted FROM Flashing u WHERE  u.id = :p1")
    Boolean findIfDeleted(@Param("p1") Long flashingId);
	
	List<Flashing> findByIdIn(List<Long> flashingIDList);


	List<Flashing> findByIsDeleted(boolean b);
	Page<Flashing> findByIsDeleted(boolean b, Pageable pageable);
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel("
			+ " u.manufacturer, u.model, CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName), u.addDate,"
			+ " CONCAT(firstUpdater.firstName,' ',firstUpdater.lastName), CONCAT(secondUpdater.firstName,' ',secondUpdater.lastName),"
			+ " CONCAT(thirdUpdater.firstName,' ',thirdUpdater.lastName), CONCAT(verifiedBy.firstName,' ',verifiedBy.lastName), u.dateVerification) "
			+ " FROM  Flashing u"
			+ " left join u.firstUpdater as firstUpdater left join  u.secondUpdater as secondUpdater left join u.thirdUpdater as thirdUpdater left join u.verifiedBy as verifiedBy"
			+ " WHERE u.isDeleted = false "
			+ " ORDER BY u.manufacturer, u.model")
	List<EquipmentVerificationModel> findVerifiedList();
	
}
