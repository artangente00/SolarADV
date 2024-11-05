package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;
import com.PlayGroundAdv.Solar.entity.ahj_library.AHJGroundMountRelatedRequirementsOnly;

public interface AHJGroundMountRelatedRequirementsOnlyRepository extends JpaRepository<AHJGroundMountRelatedRequirementsOnly, Long>  {
	AHJGroundMountRelatedRequirementsOnly findByAhjId(Long ahjId);
}
