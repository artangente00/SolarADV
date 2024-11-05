package com.PlayGroundAdv.Solar.repositories.ahj_library;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.ahj_library.AHJRoofMountRelatedRequirementsOnly;

public interface AHJRoofMountRelatedRequirementsOnlyRepository extends JpaRepository<AHJRoofMountRelatedRequirementsOnly, Long>  {
	AHJRoofMountRelatedRequirementsOnly findByAhjId(Long ahjId);
}
