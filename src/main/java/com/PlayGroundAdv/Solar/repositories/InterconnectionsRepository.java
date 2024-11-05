package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.InterconnectionsEntity;

@Repository
public interface InterconnectionsRepository extends JpaRepository<InterconnectionsEntity, Long>{
	
	InterconnectionsEntity findOneByPermitId(Long idPermit);

	Long deleteByPermitId(Long id);

}
