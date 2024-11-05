package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJHowMany;

public interface AHJHowManyRepository extends JpaRepository<AHJHowMany, Long> {
	AHJHowMany findByAhjId(Long ahjId);
}
