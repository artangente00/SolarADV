package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJAnchorSpacingRelatedRequirements;

public interface AHJAnchorSpacingRelatedRequirementsRepository extends JpaRepository<AHJAnchorSpacingRelatedRequirements, Long>  {
	AHJAnchorSpacingRelatedRequirements findByAhjId(Long ahjId);
}
