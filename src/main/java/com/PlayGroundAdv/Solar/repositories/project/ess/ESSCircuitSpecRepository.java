package com.PlayGroundAdv.Solar.repositories.project.ess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.PlayGroundAdv.Solar.entity.projects.ess.ESSCircuitSpec;
import com.PlayGroundAdv.Solar.model.project.ess.ESSCircuitSpecModel;

@Repository
public interface ESSCircuitSpecRepository extends JpaRepository<ESSCircuitSpec, Long> {

	ESSCircuitSpec findByConnectorId(Long connectorId);

	@Query("SELECT new com.PlayGroundAdv.Solar.model.project.ess.ESSCircuitSpecModel"
			+ "(u.conductorQty, u.conductorType, u.conductorSize, u.conduitType,"
			+ " u.conduitSize, u.conductorQtyOther, u.conductorTypeOther, u.conductorSizeOther,"
			+ " u.conduitTypeOther, u.conduitSizeOther, u.existing,"
			+ " u.conductorNeutral, u.circuitEnvironment, u.circuitLength) from ESSCircuitSpec u where u.connector.connectorId = :p1")
	ESSCircuitSpecModel findByConnector(@Param("p1") String connectorId);
}
