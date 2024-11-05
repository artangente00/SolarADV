package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJDesignCriteria;

public interface AHJDesignCriteriaRepository extends JpaRepository<AHJDesignCriteria, Long> {
	AHJDesignCriteria findByAhjId(Long ahjId);
}
