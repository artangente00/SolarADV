package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.PlansetSheetsLog;
import org.springframework.stereotype.Repository;

@Repository
public interface PlansetSheetsLogRepository extends JpaRepository<PlansetSheetsLog,Long> {
	
	List<PlansetSheetsLog> findBySheetType(String sheetType);
	List<PlansetSheetsLog> findBySheetTypeNot(String sheetType);

	
}
