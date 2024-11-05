package com.PlayGroundAdv.Solar.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.PlayGroundAdv.Solar.entity.PermitEngineerEntity;
import com.PlayGroundAdv.Solar.model.PermitEngineerEntityResult;
import com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel;

@Repository
public interface PermitEngineerRepository extends JpaRepository<PermitEngineerEntity, Long> {

	PermitEngineerEntity findByPermitEntityId(Long permitId);

	PermitEngineerEntity findByPermitEntityIdAndEngineeredBy(Long permitId, String engineeredBy);

	List<PermitEngineerEntity> findByEngineeredBy(String engineeredBy);

	public static final String PERMIT_ENGINEER_ENTITY_RESULT = "SELECT new com.PlayGroundAdv.Solar.model.PermitEngineerEntityResult "
			+ " (u.applicablEngineering, u.name, u.email, u.mobile, "
			+ " u.phone, u.licenceNumber, u.licenceType, u.city, u.state, u.codePostale, u.engineeredBy, "
			+ " u.determineModification, u.isShingles, u.indicateLayers, u.mpptTrachers, u.NumberMpptTrachers, "
			+ " u.NumberStringFirstMpptTrachers, u.NumberStringSecondMpptTrachers, "
			+ " u.NumberModuleStringFirstMpptTrachers, "
			+ " u.NumberModuleStringSecondMpptTrachers, u.isTransformless, "
			+ " u.numberInputTransformless, u.isCombiner, u.numberInputCombiner, "
			+ " u.overhangArea, u.roofPitch, u.adressIng ) "
			+ " FROM PermitEngineerEntity u WHERE u.permitEntity.id = :p1";

	@Query(value = PERMIT_ENGINEER_ENTITY_RESULT)
	PermitEngineerEntityResult getPermitEngineerEntityResult(@Param("p1") Long idProject);

	public static final String DELETED_ENGINEER_PERMIT_LIST = "SELECT new com.PlayGroundAdv.Solar.model.ProjectForLibrariesModel (p.permitEntity.id, p.permitEntity.homeOwnLastName, "
			+ "	p.permitEntity.homeOwnName, p.permitEntity.projectName, p.permitEntity.status, p.permitEntity.authentificationEntity.firstName, p.permitEntity.authentificationEntity.lastName)"
			+ " from PermitEngineerEntity p "
			+ " where p.engineeredBy = :p1 AND p.permitEntity.status != 'Deleted' AND p.permitEntity.status != 'Submitted' AND p.permitEntity.status != 'Delivered' AND p.permitEntity.isTemplate = false ";

	@Query(value = DELETED_ENGINEER_PERMIT_LIST)
	List<ProjectForLibrariesModel> getPermitListForDeletedEngineer(@Param("p1") String engineeredBy);

	Long deleteByPermitEntityId(Long id);
}
