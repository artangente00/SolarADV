package com.PlayGroundAdv.Solar.repositories.libraries;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.libraries.Generator;
import com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel;

@Repository
public interface GeneratorRepository extends JpaRepository<Generator, Long>, JpaSpecificationExecutor<Generator> {

	Page<Generator> findByFavoriteIdUserIdAndDeleted(Long idUser, Boolean deleted, Pageable pageable);

	Page<Generator> findByDeleted(Boolean deleted, Pageable pageable);

	List<Generator> findByFavoriteIdUserIdAndDeletedIsFalse(Long idUser);

	Boolean existsByManufacturerAndModel(String manufacturer, String model);
	Boolean existsByManufacturerAndModelAndIdNot(String manufacturer, String model,Long id);
	Boolean existsByManufacturerAndModelAndDeleted(String manufacturer, String model, Boolean deleted);
	Boolean existsByManufacturerAndModelAndDeletedAndIdNot(String manufacturer, String model, Boolean deleted, Long id);

	List<Generator> findByModel(String model);

	@Query(value = "SELECT u.deleted from Generator u where u.id = :p1")
	Boolean findIfDeleted(@Param("p1") Long id);

	List<Generator> findByDeleted(boolean b);
	
	@Query("SELECT new com.PlayGroundAdv.Solar.model.libraries.EquipmentVerificationModel("
			+ " u.manufacturer, u.model, CONCAT(u.addedBy.firstName,' ',u.addedBy.lastName), u.creationDate,"
			+ " CONCAT(firstUpdater.firstName,' ',firstUpdater.lastName), CONCAT(secondUpdater.firstName,' ',secondUpdater.lastName),"
			+ " CONCAT(thirdUpdater.firstName,' ',thirdUpdater.lastName), CONCAT(verifiedBy.firstName,' ',verifiedBy.lastName), u.dateVerification) "
			+ " FROM  Generator u"
			+ " left join u.firstUpdater as firstUpdater left join  u.secondUpdater as secondUpdater left join u.thirdUpdater as thirdUpdater left join u.verifiedBy as verifiedBy"
			+ " WHERE u.deleted = false "
			+ " ORDER BY u.manufacturer, u.model")
	List<EquipmentVerificationModel> findVerifiedList();
}
