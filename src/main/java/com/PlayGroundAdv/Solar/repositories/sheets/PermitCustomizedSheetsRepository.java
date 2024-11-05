package com.PlayGroundAdv.Solar.repositories.sheets;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.PermitCustomizedSheetsEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PermitCustomizedSheetsRepository extends JpaRepository<PermitCustomizedSheetsEntity, Long> {

	Long deleteByProjectId(Long projectId);

	@Query("SELECT u from PermitCustomizedSheetsEntity u where u.project.id = :p1 AND (SELECT count(*) from PermitCustomizedSheetsEntity v"
			+ " where u.sheet.pdfName = v.sheet.pdfName AND  u.project.id = v.project.id ) > 1 ")
	List<PermitCustomizedSheetsEntity> findDuplicateCompatileCustomizeSheets(@Param("p1") Long projectId);
	
	@Transactional
	@Modifying
	@Query("update PermitCustomizedSheetsEntity u set u.masterSheet = true where u.id IN :p1")
	void updateCustomizedSheetsMaster(@Param("p1") Long[] ids);

	List<PermitCustomizedSheetsEntity> findByProjectIdAndSheetPdfName(Long idPermit, String sheetIndex);
	List<PermitCustomizedSheetsEntity> findByProjectIdAndSheetPdfNameAndMasterSheet(Long idPermit, String sheetIndex, Boolean masterSheet);
}
