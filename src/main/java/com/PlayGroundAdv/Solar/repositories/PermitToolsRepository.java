package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PlayGroundAdv.Solar.entity.PermitToolsEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PermitToolsRepository extends JpaRepository<PermitToolsEntity , Long> {
	
	PermitToolsEntity findByPermitEntityId(long projectId);
	
	Long deleteByPermitEntityId(Long projectId);	
}
