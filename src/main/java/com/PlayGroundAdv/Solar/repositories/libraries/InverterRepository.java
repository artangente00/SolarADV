package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.Inverters;
import com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult;
import com.PlayGroundAdv.Solar.model.libraries.ComponentModel;
import com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel;

@Repository
public interface InverterRepository extends JpaRepository<Inverters, Long>, JpaSpecificationExecutor<Inverters> {

	List<Inverters> findByIdIn(List<Long> inverterIDList);
	
	List<Inverters> findAllByMakeAndModel(String manufacturer, String model);

	List<Inverters> findByIsDeleted(Boolean isDeleted);

	Page<Inverters> findByIsDeleted(Boolean isDel, Pageable pageable);

	Inverters findAllByMakeAndModelAndIsDeleted(String manufacturer, String model, Boolean isDeleted);

	Inverters findByIdAndIsDeleted(Long microInverterId, Boolean isDeleted);

	Boolean existsByIdAndIsDeleted(Long idInverter, Boolean isDel);

	@Query("SELECT u from Inverters u where u.isDeleted = false ORDER BY u.make , u.model ")
	List<Inverters> findAllByDeletedFalse();

	// A.B 01-14 Get Iacmax
	@Query(value = "SELECT u.iacmax from Inverters u where u.id = :p1")
	String getInverterIacmax(@Param("p1") Long inverterId);

	@Query(value = "SELECT u.isDeleted from Inverters u where u.id = :p1")
	Boolean findIfDeleted(@Param("p1") Long inverterId);

	// A.B 01-14 Get wireQty
	@Query(value = "SELECT u.wireQty from Inverters u where u.id = :p1")
	String getInverterWireQty(@Param("p1") Long inverterId);

	// A.B 01-14 Get Iacmax
	@Query(value = "SELECT u.optimizer from Inverters u where u.id = :p1")
	Boolean getInverterOptimizer(@Param("p1") Long inverterId);

	// C.I 01-31 Get ocpd max
	@Query(value = "SELECT u.ocpd from Inverters u where u.id = :p1")
	String getMicroInverterOcpdMax(@Param("p1") Long microInverterId);

	// C.I 01-31 Get modPerMicro max
	@Query(value = "SELECT u.modPerMicro from Inverters u where u.id = :p1")
	Integer getMicroInverterModPerMicro(@Param("p1") Long microInverterId);

	public static final String FIND_BY_PROJECT = "SELECT v from InverterLibraryEntity u,Inverters v,PermitEntity w,AuthentificationEntity z "
			+ "where w.authentificationEntity.id = z.id AND u.inverters.id=v.id AND v.isDeleted = false AND u.authentificationEntity.id=z.id AND w.id= :p1";

	@Query(value = FIND_BY_PROJECT)
	public List<Inverters> findByProject(@Param("p1") Long idPermit);

	public static final String FIND_BY_SITE_SURVEY = "SELECT v from InverterLibraryEntity u,Inverters v,SiteSurveyEntity w,AuthentificationEntity z "
			+ "where w.createdBy.id = z.id AND u.inverters.id=v.id AND v.isDeleted = false AND u.authentificationEntity.id=z.id AND w.id= :p1";

	@Query(value = FIND_BY_SITE_SURVEY)
	public List<Inverters> findBySiteSurvey(@Param("p1") Long idSiteSurvey);

	public static final String GET_ALL_MODELS = "Select new com.PlayGroundAdv.Solar.model.libraries.ComponentModel(u.id, u.make, u.model) FROM Inverters u Where u.isDeleted = false ORDER BY u.make, u.model";

	@Query(value = GET_ALL_MODELS)
	public List<ComponentModel> getAllModels();

	@Query(value = "SELECT u.make from Inverters u GROUP BY u.make ORDER BY u.make ")
	List<String> findAllManufacturer();

	@Query(value = "SELECT u.make from Inverters u Where u.microInverter = true GROUP BY u.make ORDER BY u.make ")
	List<String> findAllMicroManufacturer();

	@Query(value = "SELECT u.model from Inverters u where u.make = :p1 AND u.microInverter = true  ORDER BY u.model")
	List<String> findAllMicroModelsByManufacturer(@Param("p1") String manufacturer);

	@Query(value = "SELECT u.integratedDCDisco FROM Inverters u WHERE u.id = :p1 ")
	Boolean findIntegratedDCDiscoById(@Param("p1") Long id);

	@Query(" SELECT new com.PlayGroundAdv.Solar.model.LibrariesManagementModelResult(  " + " u.id, " + " u.make, "
			+ " u.model, " + " u.addDate, " + " u.authentificationEntity.firstName, "
			+ " u.authentificationEntity.lastName ) " + " FROM  Inverters u " + " WHERE u.isDeleted = :p1 "
			+ " AND u.hasSuperUserEdit = :p1 ORDER BY u.addDate")
	List<LibrariesManagementModelResult> getLibModel(@Param("p1") boolean del);

	@Query(value = "SELECT count(*) > 0 from Inverters u WHERE TRIM(LOWER(u.model)) = :model AND TRIM(LOWER(u.make)) = :manufacturer and u.isDeleted = false")
	Boolean existByManufactuterAndModel(@Param("model") String model, @Param("manufacturer") String manufacturer);

	@Query("SELECT u from Inverters u WHERE TRIM(LOWER(u.model)) = :model AND TRIM(LOWER(u.make)) = :manufacturer and u.isDeleted = false")
	List<Inverters> findByManufactuterAndModel(@Param("model") String model,
			@Param("manufacturer") String manufacturer);

	@Query("SELECT u from Inverters u WHERE TRIM(LOWER(u.model)) = :model AND TRIM(LOWER(u.make)) != :manufacturer and u.isDeleted = false")
	List<Inverters> findByModel(@Param("model") String model, @Param("manufacturer") String manufacturer);
	
	List<Inverters> findByIdInAndIsDeleted(List<Long> inverterIDList, Boolean isDel);
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel("
			+ " u.make, u.model, CONCAT(u.authentificationEntity.firstName,' ',u.authentificationEntity.lastName), u.addDate,"
			+ " CONCAT(firstUpdater.firstName,' ',firstUpdater.lastName), CONCAT(secondUpdater.firstName,' ',secondUpdater.lastName),"
			+ " CONCAT(thirdUpdater.firstName,' ',thirdUpdater.lastName), CONCAT(verifiedBy.firstName,' ',verifiedBy.lastName), u.dateVerification) "
			+ " FROM  Inverters u"
			+ " left join u.firstUpdater as firstUpdater left join  u.secondUpdater as secondUpdater left join u.thirdUpdater as thirdUpdater left join u.verifiedBy as verifiedBy"
			+ " WHERE u.isDeleted = false "
			+ " ORDER BY u.make, u.model")
	List<EquipmentVerificationModel> findVerifiedList();

}
