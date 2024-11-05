package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.PlayGroundAdv.Solar.entity.DCCombinerDisconnectEntity;
import com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.UsersEntityResult;
import com.PlayGroundAdv.Solar.model.libraries.DCCombinerDisconnectRequest;
import com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel;

@Repository
public interface DcCombinerDiscoRepository
		extends JpaRepository<DCCombinerDisconnectEntity, Long>, JpaSpecificationExecutor<DCCombinerDisconnectEntity> {
	// M.A 12-12-2019 Get All DCCombinerDisconnect where the ID In
	// DCCombinerDisconnect ID List
	Page<DCCombinerDisconnectEntity> findByIdIn(List<Long> dcCombinerDiscoIDList, Pageable pageable);

	List<DCCombinerDisconnectEntity> findByIdIn(List<Long> dcCombinerDiscoIDList);


	Page<DCCombinerDisconnectEntity> findByIsDeletedAndTypeDcNot(Boolean isDel, String typeDc, Pageable pageable);

	public static final String FILTER_JUNCTION_LIST_MODEL = "SELECT u.id from DCCombinerDisconnectEntity u where CONCAT('%',upper(u.manufacturer),'%') LIKE CONCAT('%',upper(:p1),'%') and CONCAT('%',upper(u.model),'%') LIKE CONCAT('%',upper(:p2),'%') and (u.id NOT IN :p3 OR :p3  = NULL ) and u.isDeleted = :p4 and (u.id IN :p5 OR :p5 = NULL ) and u.typeDc = 'J Box'";

	@Query(value = FILTER_JUNCTION_LIST_MODEL)
	public List<Long> filterJunctionListModel(@Param("p1") String manufacturer, @Param("p2") String model,
			@Param("p3") List<Long> junctionBoxList, @Param("p4") Boolean isDeleted,
			@Param("p5") List<Long> junctionBoxFavList);

	@Query("SELECT u.isDeleted from DCCombinerDisconnectEntity u where u.id = :p1")
	Boolean findIfDeleted(@Param("p1") Long id);

	Boolean existsByManufacturerAndModelAndIsDeleted(String manufacturer, String model, Boolean isDeleted);

	Boolean existsByManufacturerAndModelAndIsDeletedAndTypeDcIn(String manufacturer, String model, Boolean isDeleted,
			List<String> typeDc);

	@Query("SELECT u from DCCombinerDisconnectEntity u WHERE TRIM(LOWER(u.manufacturer)) = :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = false AND u.typeDc != 'J Box' ")
	List<DCCombinerDisconnectEntity> findByModelAndManufacturerAndIsDeletedAndTypeDcNot(@Param("p2") String model,
			@Param("p1") String make);

	@Query("SELECT u from DCCombinerDisconnectEntity u WHERE TRIM(LOWER(u.manufacturer)) != :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = false AND u.typeDc != 'J Box' ")
	List<DCCombinerDisconnectEntity> findByModelAndManufacturerNotAndIsDeletedAndTypeDcNot(@Param("p2") String model,
			@Param("p1") String make);

	Boolean existsByManufacturerAndModelAndIdNotAndTypeDcNotAndIsDeleted(String manufacturer, String model, Long getId,
			String typeDC, Boolean isDeleted);

	@Query("SELECT u from DCCombinerDisconnectEntity u WHERE TRIM(LOWER(u.manufacturer)) != :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 AND u.typeDc = :p4 ")
	List<DCCombinerDisconnectEntity> findByModelAndManufacturerNotAndIsDeletedAndTypeDc(@Param("p2") String model,
			@Param("p1") String make, @Param("p3") Boolean isDeleted, @Param("p4") String typeDc);

	@Query("SELECT u from DCCombinerDisconnectEntity u WHERE TRIM(LOWER(u.manufacturer)) = :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 AND u.typeDc = :p4 ")
	List<DCCombinerDisconnectEntity> findByModelAndManufacturerAndIsDeletedAndTypeDc(@Param("p2") String model,
			@Param("p1") String make, @Param("p3") Boolean isDeleted, @Param("p4") String typeDc);

	List<DCCombinerDisconnectEntity> findByIdInAndTypeDcOrderByDropdownOptionAsc(List<Long> junctionBoxFavID,
			String jBox);

	// M.A: to check if the component is removed or not
	Boolean existsByIdAndIsDeleted(Long idDcCombo, Boolean isDel);

	DCCombinerDisconnectEntity findByIdAndIsDeletedAndTypeDc(Long idDcCombo, Boolean isDel, String typeDC);

	DCCombinerDisconnectEntity findByManufacturerAndModel(String manufactor, String model);

	public static final String FIND_BY_PROJECT = "SELECT v from DcCombinerorDiscFavoriteEntity u,DCCombinerDisconnectEntity v,PermitEntity w,AuthentificationEntity z "
			+ "where w.authentificationEntity.id = z.id AND u.dcCombinerDisconnectEntity.id=v.id AND v.isDeleted = false AND u.authentificationEntity.id=z.id AND w.id= :p1 AND v.typeDc IN :type "
			+ "ORDER BY v.dropdownOption";

	@Query(value = FIND_BY_PROJECT)
	public List<DCCombinerDisconnectEntity> findByProject(@Param("p1") Long idPermit, @Param("type") List<String> type);

	public static final String FIND_DCC_REQUEST = "SELECT new com.PlayGroundAdv.Solar.model.libraries.DCCombinerDisconnectRequest ( u.id, u.manufacturer, u.model, u.ocpd, u.weight, "
			+ " u.nemaRating, u.maxInput, u.maxContiOutputCurrent, u.maxOutputCurrent, u.typeDc, u.manufacturerMappingValue, u.modelMappingValue, "
			+ " u.rsd ) from DCCombinerDisconnectEntity u where u.id = :p1 and u.isDeleted = false AND u.typeDc != 'J Box'";

	@Query(value = FIND_DCC_REQUEST)
	DCCombinerDisconnectRequest findDCCRequest(@Param("p1") Long id);

	@Query("SELECT u.typeDc from DCCombinerDisconnectEntity u where u.id = :p1")
	String findTypeDcById(@Param("p1") Long id);

	@Query("SELECT u.modelMappingValue from DCCombinerDisconnectEntity u where u.id = :p1")
	String findModelMappingValueById(@Param("p1") Long id);

	@Query("SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult( "
			+ " u.id, u.manufacturer, u.model, u.addDate, u.idOwner.firstName, "
			+ " u.idOwner.lastName, u.typeDc) FROM  DCCombinerDisconnectEntity u"
			+ " WHERE u.isDeleted = :p1 AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")
	List<LibrariesManagementModelResult> getLibModel(@Param("p1") boolean del);

	List<DCCombinerDisconnectEntity> findAllByManufacturerAndModel(String manufactor, String model);

	@Query("SELECT u from DCCombinerDisconnectEntity u WHERE u.manufacturer = :p1 AND u.model = :p2 AND u.id !=:p3 AND u.isDeleted = false AND u.typeDc = :p4")
	List<DCCombinerDisconnectEntity> findByManufacturerAndModelAndNotIdAndTypeDc(@Param("p1") String manufacturer,
			@Param("p2") String model, @Param("p3") Long id, @Param("p4") String typeDc);

	public static final String SELECT_PROJECT_FOR_LIBRARIES = "SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel (u.homeOwnLastName, "
			+ " u.homeOwnName, u.projectName, u.status, v.firstName, v.lastName) from PermitEntity u, AuthentificationEntity v, PermitProjectSiteInfoEntity w ";
	public static final String WHERE_PROJECT_STATUS = " and u.isDeleted = :p2 and u.status != 'Delivered' and u.status != 'Submitted' and u.isTemplate = :p2 and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id";

	@Query(SELECT_PROJECT_FOR_LIBRARIES + " where (w.roofTopJboxDC =:p1 OR w.roofTopJbox=:p1 )" + WHERE_PROJECT_STATUS)
	List<ProjectForLibrariesModel> getProjectForLibrariesModel(@Param("p1") String id, @Param("p2") Boolean isDel);

	@Query(SELECT_PROJECT_FOR_LIBRARIES
			+ " where (w.roofTopDCDisco.id = :p1 OR w.roofTopDCCombiner.id = :p1 OR w.disconnectModel.id = :p1 OR w.disconnectModelTwo.id= :p1 OR w.disconnectModelThree.id = :p1)"
			+ WHERE_PROJECT_STATUS)
	List<ProjectForLibrariesModel> getProjectForLibrariesModel(@Param("p1") Long id, @Param("p2") Boolean isDel);

	public static final String SELECT_USERS_ENTITY_RESULT = "SELECT new com.PlayGroundAdv.Solar.model.UsersEntityResult (u.id, u.firstName, u.lastName )"
			+ " from AuthentificationEntity u ";

	@Query(SELECT_USERS_ENTITY_RESULT
			+ " WHERE u.id NOT IN :p1 and u.deleted = :p2 and u.active = :p3 and u.id != :p4 ORDER BY u.firstName")
	List<UsersEntityResult> getUsersEntityResultIdIN(@Param("p1") List<Long> usersFavID, @Param("p2") Boolean isDel,
			@Param("p3") Boolean isActive, @Param("p4") Long userID);

	@Query(SELECT_USERS_ENTITY_RESULT
			+ " WHERE u.deleted = :p2 and u.active = :p3 and u.id != :p4 ORDER BY u.firstName")
	List<UsersEntityResult> getUsersEntityResult(@Param("p2") Boolean isDel, @Param("p3") Boolean isActive,
			@Param("p4") Long userID);
	
	List<DCCombinerDisconnectEntity> findByIdInAndIsDeleted(List<Long> dcCombinerDiscoIDList, Boolean isDel);

	List<DCCombinerDisconnectEntity> findByIsDeleted(boolean b);
	


	@Query("SELECT u from DCCombinerDisconnectEntity u WHERE u.isDeleted = false AND u.typeDc != 'J Box' ")
	List<DCCombinerDisconnectEntity> findAllDCD();

	@Query("SELECT u from DCCombinerDisconnectEntity u WHERE u.isDeleted = false AND u.typeDc = 'J Box' ")
	List<DCCombinerDisconnectEntity> findAllJbox();
	
	@Query("SELECT u from DCCombinerDisconnectEntity u WHERE u.isDeleted = false AND u.typeDc = 'J Box' ")
	Page<DCCombinerDisconnectEntity> findAllJbox(Pageable pageable);
	@Query("SELECT u from DCCombinerDisconnectEntity u WHERE u.isDeleted = :p1 AND u.typeDc = 'J Box' ")
	Page<DCCombinerDisconnectEntity> findAllJbox(@Param("p1") Boolean isDel, Pageable pageable);
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel("
			+ " u.manufacturer, u.model, CONCAT(u.idOwner.firstName,' ',u.idOwner.lastName), u.addDate,"
			+ " CONCAT(firstUpdater.firstName,' ',firstUpdater.lastName), CONCAT(secondUpdater.firstName,' ',secondUpdater.lastName),"
			+ " CONCAT(thirdUpdater.firstName,' ',thirdUpdater.lastName), CONCAT(verifiedBy.firstName,' ',verifiedBy.lastName), u.dateVerification) "
			+ " FROM  DCCombinerDisconnectEntity u "
			+ " left join u.firstUpdater as firstUpdater left join  u.secondUpdater as secondUpdater left join u.thirdUpdater as thirdUpdater left join u.verifiedBy as verifiedBy"
			+ " WHERE u.isDeleted = false AND u.typeDc = 'J Box' "
			+ " ORDER BY u.manufacturer, u.model")
	List<EquipmentVerificationModel> findVerifiedListJB();
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel("
			+ " u.manufacturer, u.model, CONCAT(u.idOwner.firstName,' ',u.idOwner.lastName), u.addDate,"
			+ " CONCAT(firstUpdater.firstName,' ',firstUpdater.lastName), CONCAT(secondUpdater.firstName,' ',secondUpdater.lastName),"
			+ " CONCAT(thirdUpdater.firstName,' ',thirdUpdater.lastName), CONCAT(verifiedBy.firstName,' ',verifiedBy.lastName), u.dateVerification) "
			+ " FROM  DCCombinerDisconnectEntity u"
			+ " left join u.firstUpdater as firstUpdater left join  u.secondUpdater as secondUpdater left join u.thirdUpdater as thirdUpdater left join u.verifiedBy as verifiedBy"
			+ " WHERE u.isDeleted = false AND u.typeDc != 'J Box' "
			+ " ORDER BY u.manufacturer, u.model")
	List<EquipmentVerificationModel> findVerifiedList();
}
