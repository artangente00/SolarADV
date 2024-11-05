package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.PermitSketchEntity;
import com.PlayGroundAdv.Solar.model.PermitSketchResults;
import org.springframework.stereotype.Repository;

@Repository
public interface PermitSketchRepository extends JpaRepository<PermitSketchEntity, Long> {
	
	List<PermitSketchEntity> findByPermitEntityId(Long projectId);	
	List<PermitSketchEntity> findByPermitEntityIdOrderByArraySketch(Long projectId);
	
	Long deleteByPermitEntityId(Long projectId);		
	
	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.PermitSketchResults(u.arraySketch, u.azimuth, u.roofPitch, u.moduleTils, u.eaveOverHang, " + 
			" u.eaveOverHangOther,   u.modelvalue, u.moduleQty, u.squareFootage) from PermitSketchEntity u where u.permitEntity.id = :p1  ORDER BY u.arraySketch")
	List<PermitSketchResults> getPermitSketchResults(@Param("p1") Long projectId);
}
