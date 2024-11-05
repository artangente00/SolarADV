package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJWaterIntrusion;

public interface AHJWaterIntrusionRepository extends JpaRepository<AHJWaterIntrusion, Long> {
	AHJWaterIntrusion findByAhjId(Long ahjId);
}
