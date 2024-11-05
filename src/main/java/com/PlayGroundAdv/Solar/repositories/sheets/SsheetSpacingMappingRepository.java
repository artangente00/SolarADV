package com.PlayGroundAdv.Solar.repositories.sheets;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.PlayGroundAdv.Solar.entity.SsheetSpacingMappingEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface SsheetSpacingMappingRepository extends JpaRepository<SsheetSpacingMappingEntity, Long>, JpaSpecificationExecutor<SsheetSpacingMappingEntity> {
	
	List<SsheetSpacingMappingEntity> findBySheetNumberAndQuadNumberAndIsDeleted(String sheetNumber, String quadNumber,
			Boolean isDeleted);

	Page<SsheetSpacingMappingEntity> findByIdIn(List<Long> sSheetSpacingMappingListId, Pageable pageable);

	Page<SsheetSpacingMappingEntity> findByIsDeleted(boolean b, Pageable pageable);	
	
}
