package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ConduitConductorCircuitEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ConduitConductorCircuitRepository extends JpaRepository<ConduitConductorCircuitEntity,Long> {
	
	ConduitConductorCircuitEntity findByPermitEntityId(Long idPermit);

	Long deleteByPermitEntityId(Long id);

}
