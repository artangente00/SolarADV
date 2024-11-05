package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJAddRemoveFromPlanset;

public interface AHJAddRemoveFromPlansetRepository extends JpaRepository<AHJAddRemoveFromPlanset, Long>  {
	AHJAddRemoveFromPlanset  findByAhjId(Long ahjId);
}
