package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJRevisionOrResubmittal;

public interface AHJRevisionOrResubmittalRepository extends JpaRepository<AHJRevisionOrResubmittal, Long> {
	AHJRevisionOrResubmittal findByAhjId(Long ahjId);
}
