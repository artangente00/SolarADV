package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.PermitConduitConductorSectionEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PermitConduitConductorSectionRepository extends JpaRepository<PermitConduitConductorSectionEntity,Long> {
	
	PermitConduitConductorSectionEntity findByPermitEntityId(Long idPermit);

	Long deleteByPermitEntityId(Long id);
	
}
