package com.PlayGroundAdv.Solar.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.VoltageConfigurationEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface VoltageConfigurationRepository extends JpaRepository<VoltageConfigurationEntity,Long>{
	
	List<VoltageConfigurationEntity> findAllByIdInverterId(Long id);
	
	public static final String FIND_VOLTAGE = "SELECT u.voltage from VoltageConfigurationEntity u where u.idInverter.id = :p1";

	@Query(value = FIND_VOLTAGE)
	ArrayList<String> getVoltageByInverter(@Param("p1") Long inverterId);
}
