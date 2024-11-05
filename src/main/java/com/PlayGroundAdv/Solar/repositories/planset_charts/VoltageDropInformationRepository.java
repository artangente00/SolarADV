package com.PlayGroundAdv.Solar.repositories.planset_charts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.planset_charts.VoltageDropInformation;

public interface VoltageDropInformationRepository extends JpaRepository<VoltageDropInformation, Integer> {

	VoltageDropInformation findByWireSize(String wireSize);
	
	@Query(value = "SELECT u.dcResistance from VoltageDropInformation u where u.wireSize = :wireSize ")
	float findDcResistanceByWireSize(@Param("wireSize") String wireSize);
	
	@Query(value = "SELECT u.acResistance from VoltageDropInformation u where u.wireSize = :wireSize ")
	float findAcResistanceByWireSize(@Param("wireSize") String wireSize);
}
