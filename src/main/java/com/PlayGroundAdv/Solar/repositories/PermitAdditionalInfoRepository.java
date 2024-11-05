package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.PlayGroundAdv.Solar.entity.PermitAdditionalInfoEntity;
import com.PlayGroundAdv.Solar.entity.PermitEntity;
import com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;

import org.springframework.stereotype.Repository;

@Repository
public interface PermitAdditionalInfoRepository extends JpaRepository<PermitAdditionalInfoEntity,Long>{

	List<PermitAdditionalInfoEntity> findByPermitEntityAuthentificationEntityIdAndTiltLegsMod(Long idOwner,String idTiltLegs);
	
	public static final String FIND_PROJECT_USING = "SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel " + 
    		" ( u.homeOwnLastName,  u.homeOwnName,  u.projectName,  u.status, v.firstName,  v.lastName) from PermitEntity u,  AuthentificationEntity v, " + 
    		" PermitAdditionalInfoEntity w where (w.tiltLegsMod = :p1 ) " + 
    		" and  u.isDeleted  = false and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = false and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id";

	@Query(value = FIND_PROJECT_USING)
	public List<ProjectForLibrariesModel> findByTiltLegsMod1(@Param("p1") String idTiltLegs);
	
	List<PermitAdditionalInfoEntity> findByTiltLegsMod(String idTiltLegs);
	PermitAdditionalInfoEntity findOneByPermitEntityAndTiltLegsMod(PermitEntity permitEntity,String tiltLegsMod);
    Long countByPermitEntityAndTiltLegsMod(PermitEntity permitEntity,String tiltLegsMod);
    PermitAdditionalInfoEntity findByPermitEntityId(Long idPermit);

    PermitAdditionalInfoEntity findOneByPermitEntityId(Long idPermit);


	List<PermitAdditionalInfoEntity> findByPermitEntityAndBattery(PermitEntity per, String batt);
	public static final String FIND_PROJECT_USING_MODEL = "SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel " + 
    		" ( u.homeOwnLastName,  u.homeOwnName,  u.projectName,  u.status, v.firstName,  v.lastName) from PermitEntity u,  AuthentificationEntity v, " + 
    		" PermitAdditionalInfoEntity w where (w.battery = :p1 ) " + 
    		" and  u.isDeleted  = false and u.status != 'Delivered'  and u.status != 'Submitted' and u.isTemplate  = false and u.id = w.permitEntity.id and u.authentificationEntity.id = v.id";

	@Query(value = FIND_PROJECT_USING_MODEL)
	public List<ProjectForLibrariesModel> findProjectUsingModel(@Param("p1") String idBattery);
	List<PermitAdditionalInfoEntity> findByBattery(String batt);

	List<PermitAdditionalInfoEntity> findByPermitEntityAuthentificationEntityIdAndBattery(Long idUser, String idBattery);
	//A.B To Set Component Removed
	List<PermitAdditionalInfoEntity> findByPermitEntityAuthentificationEntityIdAndBatteryIn(Long idUser, List<String> models);
	List<PermitAdditionalInfoEntity> findByPermitEntityAuthentificationEntityIdAndTiltLegsModIn(Long idUser, List<String> models);

	public static final String PERMIT_ADDITIONAL_INFO_ENTITY_RESULT = "SELECT new com.PlayGroundAdv.Solar.model.PermitAdditionalInfoEntityResult " + 
			" ( u.formatSize, u.batteryStorage, u.quantityBatteries, u.tiltLegs, u.informationCovered, u.uploadComments, u.requiredElectricalStamp, u.battery, u.tiltLegsMod, u.gridTiedOrStandalone, " +
			" u.existSolarSystem, u.existpvmodule, u.existmoduleqty, u.existinvertermodel, u.existinverterqty, u.existinvertermodelTwo, " +
			" u.existinverterqtyTwo, u.existmicromodel, u.existmicroqty, u.existacdisconnect, u.existpvmeter, u.acdpvmorientation, u.pointofconnection, " +
			" u.pocwillbeat, u.sizebackfed, u.otherPointConnection, u.otherpocwillbeat, u.combiningpvin, u.existingInverterTech)" + 
			" from PermitAdditionalInfoEntity u where u.permitEntity.id = :p1 ";

	@Query(value = PERMIT_ADDITIONAL_INFO_ENTITY_RESULT)
	PermitAdditionalInfoEntityResult getPermitAdditionalInfoEntityResult(@Param("p1") Long idProject);

	Long deleteByPermitEntityId(Long id);
}
