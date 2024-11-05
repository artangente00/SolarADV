package com.PlayGroundAdv.Solar.repositories.ahj_library;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJLogEntity;

public interface AHJLogRepository extends JpaRepository<AHJLogEntity, Long> {
	
	List<AHJLogEntity> findByMassUpdateLogId(Long massLog);

	Boolean existsByAhjIdAndCellNameAndMassUpdateAndLastUpdateAfter(Long ahj, String cellName, Boolean massUpdate,
			Date dMassUpdate);
	
	Page<AHJLogEntity> findByAhjId(Long ahj, Pageable pageable);

}
