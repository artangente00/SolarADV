package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJReqCustomPlanSetSheetsorSize;

public interface AHJReqCustomPlanSetSheetsorSizeRepository extends JpaRepository<AHJReqCustomPlanSetSheetsorSize, Long> {
	AHJReqCustomPlanSetSheetsorSize findByAhjId(Long ahjId);
}
