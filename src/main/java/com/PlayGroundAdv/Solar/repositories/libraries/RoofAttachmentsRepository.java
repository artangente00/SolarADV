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
import com.PlayGroundAdv.Solar.entity.RoofAttachmentsEntity;
import com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel;

@Repository
public interface RoofAttachmentsRepository
		extends JpaRepository<RoofAttachmentsEntity, Long>, JpaSpecificationExecutor<RoofAttachmentsEntity> {

	Page<RoofAttachmentsEntity> findByIsDeleted(Boolean isDeleted, Pageable pageable);

	@Query("SELECT count(*) > 0 from RoofAttachmentsEntity u WHERE TRIM(LOWER(u.manufacturer)) = :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.id != :p3 AND u.isDeleted = false ")
	Boolean findByManufacturerAndModelAndIdNot(@Param("p1") String make, @Param("p2") String model,
			@Param("p3") Long id);

	@Query("SELECT u from RoofAttachmentsEntity u WHERE TRIM(LOWER(u.manufacturer)) = :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 ")
	List<RoofAttachmentsEntity> findByManufacturerAndModelAndIsDeleted(@Param("p1") String make,
			@Param("p2") String model, @Param("p3") Boolean isDeleted);

	@Query("SELECT u from RoofAttachmentsEntity u WHERE TRIM(LOWER(u.manufacturer)) != :p1 AND TRIM(LOWER(u.model)) = :p2 AND u.isDeleted = :p3 ")
	List<RoofAttachmentsEntity> findByManufacturerNotAndModelAndIsDeleted(@Param("p1") String make,
			@Param("p2") String model, @Param("p3") Boolean isDeleted);

	public static final String FIND_BY_PROJECT = "SELECT v from RoofAttachmentFavLibraryEntity u,RoofAttachmentsEntity v,PermitEntity w,AuthentificationEntity z "
			+ "where w.authentificationEntity.id = z.id AND u.roofAttachment.id=v.id AND v.isDeleted = false AND u.authentificationEntity.id=z.id AND w.id= :p1 "
			+ "ORDER BY u.roofAttachment.manufacturer, u.roofAttachment.model";

	@Query(value = FIND_BY_PROJECT)
	public List<RoofAttachmentsEntity> findByProject(@Param("p1") Long idPermit);

	@Query("SELECT u.allowedRoof from RoofAttachmentsEntity u where u.id = :p1")
	String findAllowedRoof(@Param("p1") Long id);

	@Query("SELECT u.integrated from RoofAttachmentsEntity u where u.id = :p1")
	Boolean findIsIntegrated(@Param("p1") Long id);

	@Query("SELECT u.isDeleted from RoofAttachmentsEntity u where u.id = :p1")
	Boolean findIfDeleted(@Param("p1") Long id);

	public static final String GET_ALL_Models = "Select new com.PlayGroundAdv.Solar.model.libraries.ComponentModel(u.id, u.manufacturer, u.model) "
			+ "FROM RoofAttachmentsEntity u Where u.isDeleted = false ORDER BY u.manufacturer, u.model";

	@Query(value = GET_ALL_Models)
	public List<ComponentModel> getAllModels();

	@Query("SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult( u.id, u.manufacturer, "
			+ " u.model, u.addDate, u.idOwner.firstName, u.idOwner.lastName ) "
			+ " FROM  RoofAttachmentsEntity u WHERE u.isDeleted = :p1 "
			+ " AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")
	List<LibrariesManagementModelResult> getLibModel(@Param("p1") boolean del);

	List<RoofAttachmentsEntity> findByManufacturerAndModel(String man, String model);

	@Query("SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel( u.homeOwnLastName, "
			+ " u.homeOwnName, u.projectName, u.status, v.firstName, v.lastName)"
			+ " from PermitEntity u,  AuthentificationEntity v, PermitProjectSiteInfoEntity w"
			+ " where w.railConnectionModel = :p1 and  u.isDeleted  = :p2 and u.status != 'Delivered'"
			+ " and u.status != 'Submitted' and u.isTemplate  = :p2 and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id")
	List<ProjectForLibrariesModel> getProjectForLibrariesModel(@Param("p1") String roofAttachmentId,
			@Param("p2") Boolean isTemp);
	
	List<RoofAttachmentsEntity> findByIdIn(List<Long> roofAttachmentIDList);

	List<RoofAttachmentsEntity> findByIsDeleted(boolean b);
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel("
			+ " u.manufacturer, u.model, CONCAT(u.idOwner.firstName,' ',u.idOwner.lastName), u.addDate,"
			+ " CONCAT(firstUpdater.firstName,' ',firstUpdater.lastName), CONCAT(secondUpdater.firstName,' ',secondUpdater.lastName),"
			+ " CONCAT(thirdUpdater.firstName,' ',thirdUpdater.lastName), CONCAT(verifiedBy.firstName,' ',verifiedBy.lastName), u.dateVerification) "
			+ " FROM  RoofAttachmentsEntity u"
			+ " left join u.firstUpdater as firstUpdater left join  u.secondUpdater as secondUpdater left join u.thirdUpdater as thirdUpdater left join u.verifiedBy as verifiedBy"
			+ " WHERE u.isDeleted = false "
			+ " ORDER BY u.manufacturer, u.model")
	List<EquipmentVerificationModel> findVerifiedList();

}
