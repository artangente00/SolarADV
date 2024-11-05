package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.RFIConfirmationEntity;

@Repository
public interface RFIConfirmationRepository extends JpaRepository<RFIConfirmationEntity, Long> {
	RFIConfirmationEntity findByIdPermitId(Long idPermit);
	Long deleteByIdPermitId(Long projectId);
}

