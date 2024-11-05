package com.PlayGroundAdv.Solar.repositories.sheets;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.RsheetsLibraryEntity;

@Repository
public interface RsheetsLibraryRepository
		extends JpaRepository<RsheetsLibraryEntity, Long>, JpaSpecificationExecutor<RsheetsLibraryEntity> {

	Boolean existsByPdfNameInAndIsDeletedFalse(List<String> fileNames);

	Boolean existsByPdfNameAndIsDeletedFalse(String fileName);

	Boolean existsByPdfNameAndIsDeletedFalseAndIdNotIn(String fileName, List<Long> ids);

	@Query(value = "SELECT u.pdfName from RsheetsLibraryEntity u, PermitAdvEntity v where v.rSheetList = CONCAT(u.id,'') AND v.permitEntity.id = :permitId AND u.isDeleted = false")
	String findPdfName(@Param("permitId") Long permitId);

	@Query(value = "SELECT u.pdfName from RsheetsLibraryEntity u where (u.pdfName like CONCAT('RACK_',:make,:model,'%') OR u.pdfName like CONCAT(:state, '_RACK_',:make,:model,'%')) AND u.groundRailRacking = 'true' AND u.isDeleted = false")
	List<String> findGroundRackingPdfNames(@Param("state") String state, @Param("make") String make,
			@Param("model") String model);

	@Query(value = "SELECT u from RsheetsLibraryEntity u where u.manufacturer = :make AND u.model = :model AND u.componentType = 'RACK' "
			+ " AND u.groundRailRacking = 'true' AND u.isDeleted = false AND (u.state IS NULL OR u.state = :state OR u.state = '')"
			+ " AND (u.pipeSize = :pipeSize OR u.pipeSizeOther = :pipeSize) AND (u.thicknessPipe = :thicknessPipe OR u.thicknessPipeOther = :thicknessPipe)"
			+ " AND (u.moduleLayout = :moduleLayout OR u.moduleLayoutOther = :moduleLayout) AND (u.bracedOrUnbraced = :bracedOrUnbraced OR u.bracedOrUnbracedOther = :bracedOrUnbraced)"
			+ " AND (u.footingDiameter = :footingDiameter OR u.footingDiameterOther = :footingDiameter) AND (u.exposureCategory = :exposureCategory OR u.exposureCategoryOther = :exposureCategory)")
	List<RsheetsLibraryEntity> findGroundRacking(@Param("state") String state, @Param("make") String make,
			@Param("model") String model, @Param("pipeSize") String pipeSize,
			@Param("thicknessPipe") String thicknessPipe, @Param("moduleLayout") String moduleLayout,
			@Param("bracedOrUnbraced") String bracedOrUnbraced, @Param("footingDiameter") String footingDiameter,
			@Param("exposureCategory") String exposureCategory);

	Page<RsheetsLibraryEntity> findByIsDeletedOrderByPdfName(Boolean isDel, Pageable pageable);

	Page<RsheetsLibraryEntity> findByIdIn(List<Long> rSheetIds, Pageable pageable);

	List<RsheetsLibraryEntity> findByPdfNameAndIsDeleted(String fileName, boolean isDel);

	List<RsheetsLibraryEntity> findByManufacturerAndModelAndIsDeletedFalse(String manufacturer, String model);

	RsheetsLibraryEntity findOneByPdfNameAndIsDeleted(String fileName, boolean isDel);
	
	List<RsheetsLibraryEntity> findByIsDeletedFalse();
}
