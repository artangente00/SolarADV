package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.PermitLayoutEntity;
import com.PlayGroundAdv.Solar.model.PermitLayoutSketchResult;
import org.springframework.stereotype.Repository;

@Repository
public interface PermitLayoutRepository extends JpaRepository<PermitLayoutEntity,Long> {

	PermitLayoutEntity findByPermitEntityId(Long idPermit);
	PermitLayoutEntity findOneByPermitEntityId(Long idPermit);
	
	Long deleteByPermitEntityId(Long projectId);	

	@Query(value = "SELECT u.askAgain from PermitLayoutEntity u where u.permitEntity.id = :p1 ")
	Boolean findAskAgain(@Param("p1") Long idProject);
	
	public static final String PERMIT_LAYOUT_SKETCH_RESULT = "Select new com.PlayGroundAdv.Solar.model.PermitLayoutSketchResult(u.conduitRun, u.showConduitRoofAsHeight, u.sketchNote," + 
			" u.uploadCommentsLayout, u.uploadCommentsAddInfo, u.ignoreVents, u.firesetbacks, u.firesetbacksNote, u.fireVariance, u.fireVarianceNote, u.modulesEncroaching ) " + 
			" FROM PermitLayoutEntity u Where u.permitEntity.id = :p1";
	
	@Query(value = PERMIT_LAYOUT_SKETCH_RESULT)
	PermitLayoutSketchResult getPermitLayoutSketchResult(@Param("p1") Long idProject);
	
}
