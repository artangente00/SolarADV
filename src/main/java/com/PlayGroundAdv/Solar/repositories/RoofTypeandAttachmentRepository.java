package com.PlayGroundAdv.Solar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.RoofTypeandAttachmentEntity;
import com.PlayGroundAdv.Solar.model.libraries.RoofAttachmentModel;

@Repository
public interface RoofTypeandAttachmentRepository extends JpaRepository<RoofTypeandAttachmentEntity, Long> {

	RoofTypeandAttachmentEntity findByAuthentificationEntityId(Long userId);
	
	@Query(value = "SELECT new com.PlayGroundAdv.Solar.model.libraries.RoofAttachmentModel(u.id, u.compShingle, u.flatConcrete, u.romanCurved," + 
			" u.standingMetal, u.barrelCurve, u.rolledComp, u.corrugatedMetal, " + 
			" u.trapezoidalMetal, u.woodorCedar, u.clayTile, u.fiberCement, u.slate, " + 
			" u.membraneTpo, u.buildUp, u.rolledAsphalt, u.foam, u.liquidApplied, " + 
			" u.corrugatedPolyCarb) from RoofTypeandAttachmentEntity u where u.authentificationEntity.id=:p1")
	RoofAttachmentModel findModelsByUser(@Param("p1") Long idUser);

	
}
