package com.PlayGroundAdv.Solar.repositories.sheets;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.SsheetLibraryEntity;
import com.PlayGroundAdv.Solar.entity.SsheetRackingMappingEntity;
import com.PlayGroundAdv.Solar.entity.SsheetSpacingMappingEntity;
import com.PlayGroundAdv.Solar.model.SsheetSpacingMappingModel;
import org.springframework.stereotype.Repository;

@Repository
public interface SsheetRackingMappingRepository extends JpaRepository<SsheetRackingMappingEntity, Long>, JpaSpecificationExecutor<SsheetRackingMappingEntity> {
	
	List<SsheetRackingMappingEntity> findBySheetNumberAndQuadNumberAndIsDeleted(String sheetNumber, String quadNumber, Boolean isDeleted);
	
	Page<SsheetRackingMappingEntity> findByIsDeleted(Boolean deleted, Pageable pageable);
	Page<SsheetRackingMappingEntity> findByIdIn(List<Long> sSheetRackingMappingListId, Pageable pageable);

	public static final String SEARCH_NON_DELETED_ID = "SELECT u.id"
			+ " from SsheetRackingMappingEntity u"
			+ " WHERE u.isDeleted = false and"
			+ " (:roofType = '' or (u.roofType is not null and CONCAT('%',upper(u.roofType),'%') LIKE CONCAT('%',upper(:roofType),'%') )) and"
			+ " (:roofTypeOther = '' or (u.roofTypeOther is not null and CONCAT('%',upper(u.roofTypeOther),'%') LIKE CONCAT('%',upper(:roofTypeOther),'%')) ) and"
			+ " (:rackingManufacturer = '' or (u.rackingManufacturer is not null and CONCAT('%',upper(u.rackingManufacturer),'%') LIKE CONCAT('%',upper(:rackingManufacturer),'%')) ) and"
			+ " (:rackingModel = '' or (u.rackingModel is not null and CONCAT('%',upper(u.rackingModel),'%') LIKE CONCAT('%',upper(:rackingModel),'%')) ) and"
			+ " (:roofManufacturer = '' or (u.roofManufacturer is not null and CONCAT('%',upper(u.roofManufacturer),'%') LIKE CONCAT('%',upper(:roofManufacturer),'%')) ) and"
			+ " (:roofModel = '' or (u.roofModel is not null and CONCAT('%',upper(u.roofModel),'%') LIKE CONCAT('%',upper(:roofModel),'%')) ) and"
			+ " (:flashingManufacturer = '' or (u.flashingManufacturer is not null and CONCAT('%',upper(u.flashingManufacturer),'%') LIKE CONCAT('%',upper(:flashingManufacturer),'%')) ) and"
			+ " (:sheetNumber = '' or (u.sheetNumber is not null and CONCAT('%',upper(u.sheetNumber),'%') LIKE CONCAT('%',upper(:sheetNumber),'%')) ) and"
			+ " (:quadNumber = '' or (u.quadNumber is not null and CONCAT('%',upper(u.quadNumber),'%') LIKE CONCAT('%',upper(:quadNumber),'%')) )"
			+ " ORDER BY u.pdfname ";
	@Query(value = SEARCH_NON_DELETED_ID)
	List<Long> searchSheetRaickingId(@Param("roofType") String roofType,
			@Param("roofTypeOther") String roofTypeOther, @Param("rackingManufacturer") String rackingManufacturer,
			@Param("rackingModel") String rackingModel, @Param("roofManufacturer") String roofManufacturer,
			@Param("roofModel") String roofModel, @Param("flashingManufacturer") String flashingManufacturer,
			@Param("sheetNumber") String sheetNumber, @Param("quadNumber") String quadNumber);
	
	
	@Query(value = "SELECT u from SsheetRackingMappingEntity u WHERE u.sSheetFile.id = :sSheetFile AND " + 
			" u.roofType = :roofType AND u.roofTypeOther = :roofTypeOther AND" + 
			" u.rackingManufacturer = :rackingManufacturer AND u.rackingModel = :rackingModel AND " + 
			" u.roofManufacturer = :roofManufacturer AND u.roofModel = :roofModel AND u.mountingType = :mountingType" + 
			" AND u.flashingManufacturer = :flashingManufacturer AND u.sheetNumber = :sheetNumber " + 
			" AND u.quadNumber = :quadNumber AND u.detailsHeading = :detailsHeading " + 
			" AND u.ahj = :ahj AND u.utilityCompany = :utilityCompany AND u.isDeleted = false ")
	List<SsheetRackingMappingEntity> isRackingExist(@Param("sSheetFile") Long sSheetFile, @Param("roofType") String roofType,
			@Param("roofTypeOther") String roofTypeOther, @Param("rackingManufacturer") String rackingManufacturer,
			@Param("rackingModel") String rackingModel, @Param("roofManufacturer") String roofManufacturer,
			@Param("roofModel") String roofModel, @Param("mountingType") String mountingType, @Param("flashingManufacturer") String flashingManufacturer,
			@Param("sheetNumber") String sheetNumber, @Param("quadNumber") String quadNumber, @Param("detailsHeading") String detailsHeading,
			@Param("ahj") String ahj, @Param("utilityCompany") String utilityCompany);

	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.SsheetSpacingMappingModel (u.detailsHeading, u.id, u.lastUpdate, u.sSheetFile.id, u.sSheetFile.pdfName,"
			+ " u.quadNumber, u.rafterTrussSpacing, u.roofType, u.roofTypeOther, u.sheetNumber,"
			+ " u.stanchionMaxSpacing, u.stanchionType, u.flashing) from SsheetSpacingMappingEntity u WHERE u.isDeleted = :p1 ORDER BY u.pdfname ")
	Page<SsheetSpacingMappingModel> getSsheetSpacingMapping(@Param("p1") boolean isDel, Pageable pageable);
	
	@Query(value = "SELECT u from SsheetSpacingMappingEntity u WHERE u.sSheetFile.id = :p1 AND"
			+ " u.roofType = :p2 AND " + "u.rafterTrussSpacing = :p3 AND "
			+ " u.stanchionMaxSpacing = :p4 AND  "
			+ " u.sheetNumber = :p5 AND u.quadNumber = :p6 AND u.isDeleted = :p7 AND u.stanchionType = :p8 ")
	List<SsheetSpacingMappingEntity> getSsheetSpacingMappingEntity(@Param("p1") Long sSheet, @Param("p2") String roofType, @Param("p3") String rafter, @Param("p4") String maxSpacing, @Param("p5") String sheetNum, @Param("p6") String quadNum, @Param("p7") boolean isDel, @Param("p8") String stanchionType);
	
	@Query(value = "SELECT u from SsheetSpacingMappingEntity u WHERE u.sSheetFile.id = :p1 AND"
			+ " u.roofType = :p2 AND " + "u.rafterTrussSpacing = :p3 AND "
			+ " u.stanchionMaxSpacing = :p4 AND  "
			+ " u.sheetNumber = :p5 AND u.quadNumber = :p6 AND u.isDeleted = :p7 AND u.stanchionType = :p9 AND NOT u.id = :p8")
	List<SsheetSpacingMappingEntity> getSsheetSpacingMappingEntity1(@Param("p1") Long sSheet, @Param("p2") String roofType, @Param("p3") String rafter, @Param("p4") String maxSpacing, @Param("p5") String sheetNum, @Param("p6") String quadNum, @Param("p7") boolean isDel, @Param("p8") Long id, @Param("p9") String stanchionType);
	
	@Query(value = "SELECT u from SsheetRackingMappingEntity u WHERE u.id = :p1  AND (u.isDeleted = :p2 OR u.isDeleted = :p3)")
	SsheetRackingMappingEntity getSSHRME(@Param("p1") Long userID, @Param("p2") Boolean isDel, @Param("p3") Boolean isDel1);
	
	@Query(value = "SELECT u from SsheetSpacingMappingEntity u WHERE u.id = :p1  AND (u.isDeleted = :p2 OR u.isDeleted = :p3)")
	SsheetSpacingMappingEntity getSSHSME(@Param("p1") Long userID, @Param("p2") Boolean isDel, @Param("p3") Boolean isDel1);
	
	@Query(value = "SELECT u from SsheetLibraryEntity u WHERE u.pdfName = :p1 ")
	List<SsheetLibraryEntity> getByPdfName(@Param("p1") String fileName);
	
	

}
