package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJACDisco;

public interface AHJACDiscoRepository extends JpaRepository<AHJACDisco, Long> {
	
	AHJACDisco findByAhjId(Long ahjId);
	
	@Query(value="Select u.acdiscoOtherRequirement from AHJACDisco u where u.ahj.id = :p1")
	String findACOtherRequirements(@Param("p1") Long ahjId);

}
