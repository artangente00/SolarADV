package com.PlayGroundAdv.Solar.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.PermitDrafterDataEntity;
import com.PlayGroundAdv.Solar.model.PermitDrafterDataResult;

@Repository
public interface PermitDrafterDataRepository extends JpaRepository<PermitDrafterDataEntity,Long>{
	
	PermitDrafterDataEntity findByPermitEntityId(Long permitId );
	
	public static final String PERMIT_DRAFTER_DATA_RESULT = "SELECT new com.PlayGroundAdv.Solar.model.PermitDrafterDataResult(" + 
			" u.scale, u.totalRoofSquareFootage, u.totalArraySectionCount, u.scalerackingLayout, u.scaleelectricalLayout, u.uploadCommentsParcel," + 
			" u.uploadCommentsPV, u.uploadCommentsRacking, u.uploadCommentsElectrical, u.uploadCommentsPlacard, u.uploadCommentsAutoCad, u.stanchionQuantity, " + 
			" u.chooseScaleOther, u.chooseScaleArrayLayout, u.chooseScaleArrayOther, u.chooseScaleRackingOther, u.chooseScaleElectricalOther, u.customizeScale, " +
			" u.scalePV101, u.scalePV101Other)" +
			" From PermitDrafterDataEntity u Where u.permitEntity.id = :p1";
	
	@Query(value = PERMIT_DRAFTER_DATA_RESULT)
	PermitDrafterDataResult getPermitDrafterDataResult(@Param("p1") Long idProject);
	
	@Query(value = "SELECT u.googleMapImageName from PermitDrafterDataEntity u where u.permitEntity.id=:p1")
	String findGoogleMapImageName(@Param("p1") Long idProject);
	
	@Query(value = "SELECT u.nearMapImageName from PermitDrafterDataEntity u where u.permitEntity.id=:p1")
	String findNearMapImageName(@Param("p1") Long idProject);
	
	@Query(value = "SELECT u.parcelMapName from PermitDrafterDataEntity u where u.permitEntity.id=:p1")
	String findParcelMapName(@Param("p1") Long idProject);

	Long deleteByPermitEntityId(Long id);
}
