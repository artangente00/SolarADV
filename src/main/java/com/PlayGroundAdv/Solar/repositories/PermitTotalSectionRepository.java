package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.PermitTotalSectionEntity;

@Repository
public interface PermitTotalSectionRepository extends JpaRepository<PermitTotalSectionEntity, Long> {
	Long deleteByPermitEntityId(Long projectId);
}
