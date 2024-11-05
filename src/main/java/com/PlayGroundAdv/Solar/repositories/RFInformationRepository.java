package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.RFInformationEntity;

@Repository
public interface RFInformationRepository extends JpaRepository<RFInformationEntity, Long> {
	RFInformationEntity findByIdPermitId(Long idPermit);
	Long deleteByIdPermitId(Long projectId);
}

