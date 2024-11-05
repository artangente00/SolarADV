package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.PermitGoodleDriveLinks;

@Repository
public interface PermitGoogleDriveLinksRepository extends JpaRepository<PermitGoodleDriveLinks, Long> {
	PermitGoodleDriveLinks findByPermitEntityId(Long idPermit);
	Long deleteByPermitEntityId(Long projectId);
}
