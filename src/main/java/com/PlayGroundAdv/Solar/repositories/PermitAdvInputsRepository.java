package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.PermitAdvEntity;
import com.PlayGroundAdv.Solar.model.PermitAdvEntityResult;
import org.springframework.stereotype.Repository;

@Repository
public interface PermitAdvInputsRepository extends JpaRepository<PermitAdvEntity,Long>{
	
	PermitAdvEntity findByPermitEntityId(Long idPermit);
	PermitAdvEntity findOneByPermitEntityId(Long idPermit);
	
	public static final String FIND_PROJECT_ADV_INPUT = "SELECT new com.PlayGroundAdv.Solar.model.PermitAdvEntityResult " + 
			" (u.windSpeed,  u.snowLoad,  u.googleImage,  u.mapImage,  u.pVRailQte, " + 
			" u.pVRailLength,  u.stanchionQte,  u.stanchionLength,  u.spliceQte, " + 
			" u.spliceLength,  u.s200Qte,  u.s200Length,  u.pv1, " + 
			" u.customersServiceAgreementIDNumber,  u.customersRateSchedule, " + 
			" u.engineeringFirm,  u.customersAccountNumber,  u.customerName, " + 
			" u.windSpeedOther,  u.snowLoadOther, u.uploadCommentsGoogle, " + 
			" u.uploadCommentsNearMap, u.moduleLayout, u.sizeOfPipe," + 
			" u.thicknessOfPipe, u.bracedUnbraced, u.footingDiameter," + 
			" u.moduleLayoutOther, u.sizeOfPipeOther, u.thicknessOfPipeOther," + 
			" u.footingDiameterOther, u.openTldLibrary, u.tldShortList, u.tldList, u.rSheetList, u.bracedUnbracedOther, u.googleZoom )" + 
			" from PermitAdvEntity u " + 
			" where u.permitEntity.id = :p1";
			
			@Query(value = FIND_PROJECT_ADV_INPUT)
			PermitAdvEntityResult getProjectADVInputs(@Param("p1") Long idProject);
			Long deleteByPermitEntityId(Long id);
}
