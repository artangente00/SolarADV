package com.PlayGroundAdv.Solar.repositories.sheets;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.PermitPlansetUploadEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PermitPlansetUploadRepository extends JpaRepository<PermitPlansetUploadEntity, Long> {
	PermitPlansetUploadEntity findByPermitEntityId(Long projectId);

	Long deleteByPermitEntityId(Long id);
}
