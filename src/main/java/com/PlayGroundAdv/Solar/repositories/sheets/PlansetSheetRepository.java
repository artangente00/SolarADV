package com.PlayGroundAdv.Solar.repositories.sheets;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.PlansetSheetEntity;

@Repository
public interface PlansetSheetRepository extends JpaRepository<PlansetSheetEntity, Long>{
	
	Page<PlansetSheetEntity> findByTypeOrderByPdfName(String type,Pageable pageable);
	Page<PlansetSheetEntity> findByTypeAndPdfName(String type, String pdfName,Pageable pageable);
	
	@Query("SELECT u.urlPath from PathEntity u" )
	String getUrlPath();

}
