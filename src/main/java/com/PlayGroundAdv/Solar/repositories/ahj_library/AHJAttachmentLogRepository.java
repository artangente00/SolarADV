package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJAttachmentLogEntity;

public interface AHJAttachmentLogRepository extends JpaRepository<AHJAttachmentLogEntity, Long> {

	Page<AHJAttachmentLogEntity> findByAhjId(Long ahj, Pageable pageable);
}
