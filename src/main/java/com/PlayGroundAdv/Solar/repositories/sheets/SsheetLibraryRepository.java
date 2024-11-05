package com.PlayGroundAdv.Solar.repositories.sheets;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.SsheetLibraryEntity;
import com.PlayGroundAdv.Solar.entity.SsheetRackingMappingEntity;
import com.PlayGroundAdv.Solar.entity.SsheetSpacingMappingEntity;

@Repository
public interface SsheetLibraryRepository extends JpaRepository<SsheetLibraryEntity ,Long>, JpaSpecificationExecutor<SsheetLibraryEntity>{
	
	Boolean existsByPdfNameAndIsDeleted(String pdfName, Boolean isDeleted);
	Boolean existsByPdfNameAndIsDeletedFalse(String fileName);
	Boolean existsByPdfNameAndIsDeletedFalseAndIdNotIn(String fileName, List<Long> ids);
	Page<SsheetLibraryEntity> findByIsDeletedOrderByPdfName(Boolean deleted, Pageable pageable);
	
	Page<SsheetLibraryEntity> findByIsDeleted(Boolean deleted, Pageable pageable);
	
	List<SsheetLibraryEntity> findByIsDeleted(Boolean isDeleted);
	
	
	List<SsheetLibraryEntity> findByPdfNameAndIsDeleted(String fileName, boolean isDel);
	SsheetLibraryEntity findFirstByPdfNameAndIsDeleted(String fileName, boolean isDel);
	
	@Query(value = "SELECT u from SsheetSpacingMappingEntity u WHERE u.sSheetFile.id = :p1")
	List<SsheetSpacingMappingEntity> getAllSsheetSpacing(@Param("p1") Long sheetID);
	
	@Query(value = "SELECT u from SsheetRackingMappingEntity u WHERE u.sSheetFile.id = :p1")
	List<SsheetRackingMappingEntity> getAllSsheetRacking(@Param("p1") Long sheetID);
	Page<SsheetLibraryEntity> findByIdIn(List<Long> sSheetIds, Pageable pageable);

}
