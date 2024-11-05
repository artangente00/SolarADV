package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.DrafterInformationEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface DrafterInformationRepository extends JpaRepository<DrafterInformationEntity,Long>{

	DrafterInformationEntity findByIdPermitId(Long idPermit);

	Long deleteByIdPermitId(Long id);
}
