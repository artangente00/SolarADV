package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.PlayGroundAdv.Solar.entity.PermitEnergyBatterySystem;

@Repository
public interface PermitEnergyBatterySystemRepository extends JpaRepository<PermitEnergyBatterySystem, Long> {
	
	PermitEnergyBatterySystem findByProjectId(Long projectId);

	@Query(value = "SELECT u.id from PermitEnergyBatterySystem u where u.project.id=:p1")
	Long findIdByProjectId(@Param("p1") Long projectId);

	Long deleteByProjectId(Long id);
	
	@Query(value = "SELECT u.generatorStatus from PermitEnergyBatterySystem u where u.project.id=:p1")
	String findGeneratorStatus(@Param("p1") Long projectId);
	
}
