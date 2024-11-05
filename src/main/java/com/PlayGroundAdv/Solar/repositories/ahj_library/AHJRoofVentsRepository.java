package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJRoofVents;

public interface AHJRoofVentsRepository extends JpaRepository<AHJRoofVents, Long> {
	AHJRoofVents findByAhjId(Long ahjId);
}
