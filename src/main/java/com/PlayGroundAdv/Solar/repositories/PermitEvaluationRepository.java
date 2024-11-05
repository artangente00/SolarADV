package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.PermitEvaluationEntity;

@Repository
public interface PermitEvaluationRepository extends JpaRepository<PermitEvaluationEntity, Long> {
	PermitEvaluationEntity findByPermitEntityId(Long idPermit);

	Long deleteByPermitEntityId(Long projectId);
}
